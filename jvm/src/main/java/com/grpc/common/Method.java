package com.grpc.common;

/**
 * Client type {Search, Watch, BiWatch}
 */
public enum Method {
    SEARCH,
    WATCH,
    BIWATCH;

    public static Try<Method> fromString(String text) {
        if (text != null) {
            for (Method typ : Method.values()) {
                if (text.equalsIgnoreCase(typ.toString())) {
                    return Try.successful(typ);
                }
            }
        }

        return Try.failure(new IllegalArgumentException("Can't map Method for specified string: " + text));
    }
}
