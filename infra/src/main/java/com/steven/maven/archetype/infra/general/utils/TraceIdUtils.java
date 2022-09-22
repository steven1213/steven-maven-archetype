package com.steven.maven.archetype.infra.general.utils;

import java.util.UUID;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
public final class TraceIdUtils {

    private TraceIdUtils() {
    }

    public static String getTraceId() {
        return UUID.randomUUID().toString();
    }
}
