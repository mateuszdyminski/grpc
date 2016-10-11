package com.grpc.search.clients;

import com.google.common.util.concurrent.ListenableFuture;
import com.grpc.search.GoogleGrpc;
import com.grpc.search.Request;
import com.grpc.search.Result;
import com.grpc.common.Try;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AsyncClient is a async client based on ListenableFuture.
 */
public class AsyncClient implements SearchClient {

    private static final Logger logger = Logger.getLogger(AsyncClient.class.getName());
    private final ManagedChannel channel;
    private final GoogleGrpc.GoogleFutureStub googleFutureClient;

    public AsyncClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        googleFutureClient = GoogleGrpc.newFutureStub(channel);
    }

    @Override
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Search searches query in async way in dummy google backend.
     */
    @Override
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

    @Override
    public void biWatch(String query) {
        throw new UnsupportedOperationException();
    }
}
