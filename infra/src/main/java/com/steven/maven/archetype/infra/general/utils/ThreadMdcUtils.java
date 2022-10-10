package com.steven.maven.archetype.infra.general.utils;

import com.steven.maven.archetype.infra.general.constant.Constants;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
public class ThreadMdcUtils {

    public static void setTraceIdIfAbsent() {
        if (null == MDC.get(Constants.TRACE_ID)) {
            MDC.put(Constants.TRACE_ID, TraceIdUtils.getTraceId());
        }
    }

    public static void setTraceId(String traceId) {
        if (null == MDC.get(Constants.TRACE_ID)) {
            MDC.put(Constants.TRACE_ID, traceId);
        }
    }

    public static void clear() {
        MDC.clear();
    }

    public static String getTraceId() {
        return MDC.get(Constants.TRACE_ID);
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (null == context) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }

            setTraceIdIfAbsent();

            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (null == context) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }

            setTraceIdIfAbsent();

            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
