package com.grpc.models;

public class Req {
    private int batchSize;
    private int total;

    public Req() {}

    public Req(int batchSize, int total) {
        this.batchSize = batchSize;
        this.total = total;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
