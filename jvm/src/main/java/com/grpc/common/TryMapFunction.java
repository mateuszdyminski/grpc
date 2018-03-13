package com.grpc.common;

public interface TryMapFunction<T, R> {
    R apply(T t) throws Throwable;
}