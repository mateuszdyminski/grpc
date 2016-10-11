package com.grpc.search.clients;

import com.grpc.search.Result;

public interface SearchClient {

    Result search(String query);

    void watch(String query);

    void biWatch(String query);

    void shutdown() throws InterruptedException;
}
