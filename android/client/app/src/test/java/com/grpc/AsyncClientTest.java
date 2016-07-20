package com.grpc;

import org.junit.Test;

public class AsyncClientTest {

    @Test
    public void testClient() {
        BlockingClient client = new BlockingClient("192.168.3.152", 36111);
        String time = client.load(100, 1000);
        System.out.println(time);
    }
}