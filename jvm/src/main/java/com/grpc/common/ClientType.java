package com.grpc.common;

/**
 * Client type {Sync, Async}
 */
public enum ClientType {
    SYNC,
    ASYNC,
    DEADLINE,
    STREAM;

    public static Try<ClientType> fromString(String text) {
        if (text != null) {
            for (ClientType typ : ClientType.values()) {
                if (text.equalsIgnoreCase(typ.toString())) {
                    return Try.successful(typ);
                }
            }
        }

        return Try.failure(new IllegalArgumentException("Can't map ClientType for specified string: " + text));
    }
}
