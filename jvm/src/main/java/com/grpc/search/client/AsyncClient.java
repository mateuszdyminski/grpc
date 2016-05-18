package com.grpc.search.client;

import com.google.common.util.concurrent.ListenableFuture;
import com.grpc.search.GoogleGrpc;
import com.grpc.search.Request;
import com.grpc.search.Result;
import com.grpc.util.Try;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User mdyminski
 */
public class AsyncClient implements SearchClient {

    private static final Logger logger = Logger.getLogger(AsyncClient.class.getName());
    private final ManagedChannel channel;
    private final GoogleGrpc.GoogleFutureClient googleFutureClient;

    /**
     * Construct client with async API to Load balancer server at {@code host:port}.
     */
    public AsyncClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        googleFutureClient = GoogleGrpc.newFutureStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Search query in async way in dummy google backend.
     */
    public Result search(String query) {
        logger.info("Starting search for " + query + " ...");

        Request request = Request.newBuilder().setQuery(query).build();
        ListenableFuture<Result> resultFuture = googleFutureClient.search(request);

        return Try.ofFailable(() -> resultFuture.get(1000, TimeUnit.MILLISECONDS))
                .onSuccess(r -> logger.info("Search result: " + r))
                .onFailure(e -> logger.log(Level.SEVERE, "RPC failed: {0}", e))
                .getUnchecked();
    }

    @Override
    public void watch(String query) {
        throw new UnsupportedOperationException();
    }
}
