package com.grpc.search.client;

import com.grpc.search.Result;

/**
 * User mdyminski
 */
public interface SearchClient {

    Result search(String query);
    void watch(String query);
    void shutdown() throws InterruptedException;
}
