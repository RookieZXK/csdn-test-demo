package com.rookie.csdntestdemo.utils;

public class ThreadLocalContext {

    private static ThreadLocal<Long> START_TIME = new ThreadLocal();

    private static ThreadLocal<String> REQUEST_PARAMS = new ThreadLocal();


    public static Long getStartTime() {
        return START_TIME.get();
    }

    public static void setStartTime(Long startTime) {
        START_TIME.set(startTime);
    }

    public static String getParams() {
        return REQUEST_PARAMS.get();
    }

    public static void setParams(String params) {
        REQUEST_PARAMS.set(params);
    }


    public static void clear() {
        START_TIME.remove();
        REQUEST_PARAMS.remove();
    }


}
