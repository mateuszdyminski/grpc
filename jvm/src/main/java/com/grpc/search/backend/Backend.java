package com.grpc.search.backend;

import com.grpc.search.GoogleGrpc;
import com.grpc.search.Request;
import com.grpc.search.Result;
import com.grpc.util.Try;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * User mdyminski
 */
public class Backend {
    private static final Logger logger = Logger.getLogger(Backend.class.getName());

    /* The port on which the server should run */
    private int port = 36061;
    private Server server;

    private void start(int id) throws IOException {
        server = ServerBuilder.forPort((port + id))
                .addService(new GoogleImpl(id))
                .build()
                .start();
        logger.info("Server started, listening on " + (port + id));
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                Backend.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        String id = "0";

        if (args.length > 0) {
            id = args[0];
        }

        final Backend server = new Backend();
        server.start(Integer.parseInt(id));
        server.blockUntilShutdown();
    }

    private class GoogleImpl extends GoogleGrpc.AbstractGoogle {

        private final int id;
        private final Random generator = new Random();
        private final Logger logger = Logger.getLogger(GoogleImpl.class.getName());

        public GoogleImpl(int id) {
            this.id = id;
        }

        @Override
        public void search(Request req, StreamObserver<Result> responseObserver) {
            logger.info("Start searching for: " + req.getQuery());

            sleepUpToMiilis(100);
            Result reply = Result.newBuilder().setTitle(format("result for [%s] from backend %d", req.getQuery(), id)).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void watch(Request req, StreamObserver<Result> responseObserver) {
            logger.info("Start watching: " + req.getQuery());

            int responseNo = 0;
            final ServerCallStreamObserver responseObserver2 = (ServerCallStreamObserver) responseObserver;
            while (!responseObserver2.isCancelled()) {
                sleepUpToMiilis(1000);
                responseObserver.onNext(Result.newBuilder().setTitle(format("result %d for [%s] from backend %d", responseNo++, req.getQuery(), id)).build());
            }

            responseObserver2.setOnCancelHandler(() -> logger.warning("Request canceled!"));
        }

        private void sleepUpToMiilis(int millis) {
            Try.ofFailable(() -> {
                Thread.sleep((generator.nextInt(millis / 10) + 1) * 10);
                return 0;
            }).getUnchecked();
        }
    }
}
