package com.hs.p.common.http;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class HTTPError {
    public static final int ERROR = 100;
    public static final int NETWORK_ERROR = 101;
    public static final int OK = 0;
    private static Map<Integer, String> tables;
    private final int code;
    private final String message;

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        tables = concurrentHashMap;
        concurrentHashMap.put(0, "OK");
        tables.put(100, "error");
        tables.put(101, "network error");
    }

    public HTTPError(int i, String str) {
        this.code = i;
        this.message = str;
    }

    private static String asMessage(int i) {
        if (tables.containsKey(Integer.valueOf(i))) {
            return tables.get(Integer.valueOf(i));
        }
        return "" + i;
    }

    public static HTTPError fail() {
        return new HTTPError(100, asMessage(100));
    }

    public static HTTPError ok() {
        return new HTTPError(0, asMessage(0));
    }

    public boolean codeEquals(int i) {
        return this.code == i;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return this.code + " " + this.message;
    }

    public static HTTPError fail(int i) {
        return new HTTPError(i, asMessage(i));
    }

    public static HTTPError fail(int i, String str) {
        return new HTTPError(i, str);
    }
}
