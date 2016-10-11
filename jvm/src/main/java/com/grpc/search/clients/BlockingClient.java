package com.grpc.search.clients;

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
 * BlockingClient is a client which blocks the execution until gets its response.
 */
public class BlockingClient implements SearchClient {

    private static final Logger logger = Logger.getLogger(BlockingClient.class.getName());

    private final ManagedChannel channel;
    private final GoogleGrpc.GoogleBlockingStub googleBlockingStub;

    public BlockingClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();

        googleBlockingStub = GoogleGrpc.newBlockingStub(channel);
    }

    @Override
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Search query in dummy google backend.
     */
    @Override
    public Result search(String query) {
        logger.info("Starting search for " + query + " ...");

        final Request request = Request.newBuilder().setQuery(query).build();

        return Try
                .ofFailable(() -> googleBlockingStub.search(request))
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
