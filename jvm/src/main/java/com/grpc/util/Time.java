package com.grpc.util;

import java.util.Random;

/**
 * Time helpers
 */
public final class Time {

    private final static Random generator = new Random();

    public static void sleepUpToMiilis(int millis) {
        Try.ofFailable(() -> {
            Thread.sleep((generator.nextInt(millis / 10) + 1) * 10);
            return 0;
        }).getUnchecked();
    }

    private Time(){}
}
