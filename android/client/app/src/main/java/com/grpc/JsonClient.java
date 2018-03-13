package com.grpc;

import com.google.common.base.Stopwatch;
import com.grpc.models.Req;
import com.grpc.models.Res;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class JsonClient {

    private static final Logger logger = Logger.getLogger(JsonClient.class.getName());
    private LoadApi service;
    private String url;

    public JsonClient(String host, int port) {
        url = String.format("http://%s:%d/", host, port);
        Retrofit client = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        service = client.create(LoadApi.class);
    }

    /**
     * load loads specified number of users from grpc backend.
     */
    public String load(int batchSize, int total) {
        logger.info("Starting loading " + total + "users");
        Stopwatch stopwatch = Stopwatch.createStarted();

        int downloadNo = 0;
        while (downloadNo < total) {
            Req req = new Req(batchSize, total);

            try {
                Res response = service.load(req).execute().body();
                downloadNo += response.getUsers().size();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "can't load users", e);
            }
        }

        return stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms";
    }
}
