package com.grpc.search.client;

import com.google.common.util.concurrent.Uninterruptibles;
import com.grpc.search.GoogleGrpc;
import com.grpc.search.Request;
import com.grpc.search.Result;
import com.grpc.util.Try;
import io.grpc.*;
import io.grpc.internal.GrpcUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User mdyminski
 */
public class DeadlineClient implements SearchClient {

    private static final Logger logger = Logger.getLogger(DeadlineClient.class.getName());

    private final ManagedChannel channel;
    private final GoogleGrpc.GoogleBlockingStub googleBlockingStub;

    /**
     * Construct client with blocking API to Load balancer server at {@code host:port}.
     */
    public DeadlineClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        googleBlockingStub = GoogleGrpc.newBlockingStub(channel).withDeadlineAfter(500, TimeUnit.MILLISECONDS);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Search query in dummy google backend.
     */
    public Result search1(String query) {
        logger.info("Starting search for " + query + "...");

        final Request request = Request.newBuilder().setQuery(query).build();

        return Try
                .ofFailable(() -> googleBlockingStub.search(request))
                .onSuccess(r -> logger.info("Search result: " + r))
                .onFailure(e -> logger.log(Level.SEVERE, "RPC failed: {0}", e))
                .getUnchecked();
    }


    public Result search(String query) {
        final ClientCall<Request, Result> call =
                channel.newCall(GoogleGrpc.METHOD_SEARCH, CallOptions.DEFAULT.withDeadlineAfter(800, TimeUnit.MILLISECONDS));

        final CountDownLatch latch = new CountDownLatch(1);
        final List<Result> results = new ArrayList<>();

        call.start(new ClientCall.Listener<Result>() {
            @Override
            public void onHeaders(Metadata headers) {
                super.onHeaders(headers);
                String encoding = headers.get(GrpcUtil.MESSAGE_ENCODING_KEY);
                if (encoding == null) {
                    throw new RuntimeException("No compression selected!");
                }
            }

            @Override
            public void onMessage(Result message) {
                super.onMessage(message);
                logger.info("Search result: " + message.getTitle());
                results.add(message);
                latch.countDown();
            }

            @Override
            public void onClose(Status status, Metadata trailers) {
                latch.countDown();
                if (!status.isOk()) {
                    throw status.asRuntimeException();
                }
            }
        }, new Metadata());

        call.setMessageCompression(true);
        call.sendMessage(Request.newBuilder().setQuery(query).build());
        call.request(1);
        call.halfClose();

        Uninterruptibles.awaitUninterruptibly(latch, 100, TimeUnit.SECONDS);

        return results.get(0);
    }

    @Override
    public void watch(String query) {
        throw new UnsupportedOperationException();
    }
}
