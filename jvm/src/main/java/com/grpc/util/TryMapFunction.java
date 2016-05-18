package com.grpc.util;

public interface TryMapFunction<T, R> {
    R apply(T t) throws Throwable;
}