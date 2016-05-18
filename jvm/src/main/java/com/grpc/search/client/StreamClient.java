package com.grpc.search.client;

import com.google.common.util.concurrent.Uninterruptibles;
import com.grpc.search.GoogleGrpc;
import com.grpc.search.Request;
import com.grpc.search.Result;
import com.grpc.util.Try;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * User mdyminski
 */
public class StreamClient implements SearchClient {

    private static final Logger logger = Logger.getLogger(DeadlineClient.class.getName());

    private final ManagedChannel channel;
    private final GoogleGrpc.GoogleStub googleStub;

    /**
     * Construct client with Streaming API to Load balancer at {@code host:port}.
     */
    public StreamClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        googleStub = GoogleGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Search query in dummy google backend.
     */
    public Result search(String query) {
        logger.info("Starting search for " + query + "...");

        final Request request = Request.newBuilder().setQuery(query).build();
        final CountDownLatch latch = new CountDownLatch(1); // we expect only 1 result
        final List<Result> results = new ArrayList<Result>();

        StreamObserver<Result> stream = new StreamObserver<Result>() {
            @Override
            public void onNext(Result value) {
                logger.info("Search result: " + value.getTitle());
                results.add(value);
                latch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                logger.severe(("Error while watching for results! " + t.getMessage()));
                throw new RuntimeException("Error while watching for results!", t);
            }

            @Override
            public void onCompleted() {
                logger.info("Watch done!");
            }
        };

        googleStub.watch(request, stream);
        Try.ofFailable(() -> {
            latch.await();
            return null;
        }).getUnchecked();

        return results.get(0);
    }

    /**
     * Watch for query in dummy google backend.
     */
    public void watch(String query) {
        logger.info("Starting watching for " + query + "...");

        final Request request = Request.newBuilder().setQuery(query).build();
        final CountDownLatch latch = new CountDownLatch(1); // we expect only 1 result

        StreamObserver<Result> stream = new StreamObserver<Result>() {
            @Override
            public void onNext(Result value) {
                logger.info("Search result: " + value.getTitle());
            }

            @Override
            public void onError(Throwable t) {
                logger.severe(("Error while watching for results! " + t.getMessage()));
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Watch done!");
                latch.countDown();
            }
        };

        googleStub.watch(request, stream);

        Uninterruptibles.awaitUninterruptibly(latch, 100, TimeUnit.SECONDS);
    }
}
