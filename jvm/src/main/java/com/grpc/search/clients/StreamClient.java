package com.grpc.search.clients;

import com.google.common.util.concurrent.Uninterruptibles;
import com.grpc.search.GoogleGrpc;
import com.grpc.search.Request;
import com.grpc.search.Result;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.grpc.common.Time.sleepUpToMiilis;
import static java.lang.String.format;

/**
 * StreamClient is a streaming client showing how to deal with one-side-streams and bidirectional-stream.
 */
public class StreamClient implements SearchClient {

    private static final Logger logger = Logger.getLogger(DeadlineClient.class.getName());

    private final ManagedChannel channel;
    private final GoogleGrpc.GoogleStub googleStub;
    private final SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    public StreamClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        googleStub = GoogleGrpc.newStub(channel);
    }

    @Override
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    @Override
    public Result search(String query) {
        throw new UnsupportedOperationException();
    }

    /**
     * Watch showing how to deal with one-directional stream.
     */
    @Override
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

    /**
     * BiWatch showing how to deal with bidirectional stream.
     */
    @Override
    public void biWatch(String query) {
        logger.info("Starting biWatching for " + query + "...");

        final CountDownLatch latch = new CountDownLatch(1);

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

        StreamObserver<com.grpc.search.Request> requests = googleStub.biWatch(stream);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            while(true) {
                requests.onNext(Request.newBuilder().setQuery(query + dt.format(new Date())).build());
                sleepUpToMiilis(10000);
            }
        });

        Uninterruptibles.awaitUninterruptibly(latch, 100, TimeUnit.SECONDS);
    }
}
