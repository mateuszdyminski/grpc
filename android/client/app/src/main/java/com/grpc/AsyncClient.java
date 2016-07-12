package com.grpc;

import com.google.common.base.Stopwatch;
import com.grpc.load.LoadGrpc;
import com.grpc.load.Request;
import com.grpc.load.Result;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class AsyncClient {

    private static final Logger logger = Logger.getLogger(AsyncClient.class.getName());
    private final ManagedChannel channel;
    private final LoadGrpc.LoadBlockingStub client;

    public AsyncClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        client = LoadGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * load loads specified number of users from grpc backend.
     */
    public String load(int batchSize, int total) {
        logger.info("Starting loading " + total + "users");
        Stopwatch stopwatch = Stopwatch.createStarted();

        int downloadNo = 0;
        while (downloadNo < total) {
            Request request = Request.newBuilder().setBatchSize(batchSize).setTotal(total).build();
            Result result = client.load(request);
            downloadNo += result.getUsersList().size();
        }

        return stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms";
    }

    /**
     * loadStream loads specified number of users from grpc stream backend.
     */
    public String loadStream(int batchSize, int total) {
        logger.info("Starting loading " + total + "users");
        Stopwatch stopwatch = Stopwatch.createStarted();

        Request request = Request.newBuilder().setBatchSize(batchSize).setTotal(total).build();

        int downloadNo = 0;
        Iterator<Result> iter = client.loadStream(request);
        while (iter.hasNext() && downloadNo < total) {
            Result result = iter.next();
            downloadNo += result.getUsersList().size();
        }

        return stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms";
    }
}
