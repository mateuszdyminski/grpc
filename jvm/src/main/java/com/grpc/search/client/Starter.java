package com.grpc.search.client;

import com.google.common.collect.ImmutableMap;
import com.grpc.util.ClientType;
import com.grpc.util.Method;

import java.util.Map;
import java.util.logging.Logger;

import static com.grpc.util.Method.BIWATCH;
import static com.grpc.util.Method.SEARCH;
import static com.grpc.util.Method.WATCH;

/**
 * Starter starts gRPC clients.
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
     * Search Client. First argument is sync/async/deadline/stream type of the client. Second is a method {search/watch/biwatch}. Third if provided, is the query phrase
     */
    public static void main(String[] args) throws Exception {
        ClientType type = ClientType.STREAM;
        Method method = WATCH;
        String query = "siili-java";

        if (args.length > 0) {
            type = ClientType.fromString(args[0]).getUnchecked();
            if (args.length > 1) {
                method = Method.fromString(args[1]).getUnchecked();
                if (args.length > 2) {
                    query = args[2];
                }
            }
        }

        try {
            long start = System.currentTimeMillis();
            run(type, method, query);
            logger.info("Request took: " + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            clients.get(type).shutdown();
        }
    }

    private static void run(ClientType cType, Method method, String query) {
        switch(method) {
            case SEARCH:
                clients.get(cType).search(query);
                break;
            case WATCH:
                clients.get(cType).watch(query);
                break;
            case BIWATCH:
                clients.get(cType).biWatch(query);
                break;
            default:
                new RuntimeException("No mapping for method " + method);
        }
    }
}
