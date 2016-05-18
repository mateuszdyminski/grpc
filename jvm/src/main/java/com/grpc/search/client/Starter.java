package com.grpc.search.client;

import com.google.common.collect.ImmutableMap;
import com.grpc.util.ClientType;

import java.util.Map;
import java.util.logging.Logger;

/**
 * User mdyminski
 */
public class Starter {

    private static final Logger logger = Logger.getLogger(Starter.class.getName());

    public static final String HOST = "localhost";
    public static final int PORT = 36060;
    private static final Map<ClientType, SearchClient> clients = ImmutableMap.<ClientType, SearchClient>builder()
            .put(ClientType.ASYNC, new AsyncClient(HOST, PORT))
            .put(ClientType.STREAM, new StreamClient(HOST, PORT))
            .put(ClientType.DEADLINE, new DeadlineClient(HOST, PORT))
            .put(ClientType.SYNC, new BlockingClient(HOST, PORT)).build();

    /**
     * Search Client. First argument is sync/async type of the client. Second if provided, is the query phrase
     */
    public static void main(String[] args) throws Exception {
        ClientType type = ClientType.STREAM;
        String query = "test";

        if (args.length > 0) {
            type = ClientType.fromString(args[0]).getUnchecked();
            if (args.length > 1) {
                query = args[1];
            }
        }

        try {
            long start = System.currentTimeMillis();
            clients.get(type).watch(query);
            logger.info("Request took: " + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            clients.get(type).shutdown();
        }
    }
}
