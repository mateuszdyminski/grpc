package com.grpc.search.client;

import com.grpc.search.GoogleGrpc;
import com.grpc.search.Request;
import com.grpc.search.Result;
import com.grpc.util.Try;
import io.grpc.*;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User mdyminski
 */
public class BlockingClient implements SearchClient {

    private static final Logger logger = Logger.getLogger(BlockingClient.class.getName());

    private final ManagedChannel channel;
    private final GoogleGrpc.GoogleBlockingClient googleBlockingStub;

    /**
     * Construct client with blocking API to Load balancer server at {@code host:port}.
     */
    public BlockingClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        googleBlockingStub = GoogleGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Search query in dummy google backend.
     */
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
}
