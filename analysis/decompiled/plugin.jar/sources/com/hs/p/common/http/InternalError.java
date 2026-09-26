package com.hs.p.common.http;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class InternalError {
    public static final int EBROKENPROTOCOL = 108;
    public static final int ECLIENTCALLBACK = 109;
    public static final int ECONNECT = 106;
    public static final int ECONNRESET = 105;
    public static final int EDATARECEIVE = 102;
    public static final int EHTTPSTATUSOFFSET = 1000;
    public static final int EILLEGALPARAMETER = 101;
    public static final int EIOEXCEPTION = 103;
    public static final int ETIMEOUT = 107;
    public static final int EUNKNOWNHOST = 104;
    public static final int FAIL = 100;
    public static final int OK = 0;
    private static Map<Integer, String> tableMap;
    private final int code;
    private final String message;

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        tableMap = concurrentHashMap;
        concurrentHashMap.put(0, "OK");
        tableMap.put(100, "failed");
        tableMap.put(101, "illegal parameter");
        tableMap.put(Integer.valueOf(EDATARECEIVE), "http receiving error");
        tableMap.put(Integer.valueOf(EIOEXCEPTION), "I/O exception");
        tableMap.put(Integer.valueOf(EUNKNOWNHOST), "unknown host");
        tableMap.put(Integer.valueOf(ECONNRESET), "I/O exception, econnreset");
        tableMap.put(Integer.valueOf(ECONNECT), "connection error");
        tableMap.put(Integer.valueOf(ETIMEOUT), "socket timeout");
        tableMap.put(Integer.valueOf(EBROKENPROTOCOL), "broken protocol error");
        tableMap.put(Integer.valueOf(ECLIENTCALLBACK), "client callback error");
    }

    public InternalError() {
        this(100);
    }

    private static String codeOf(int i) {
        if (tableMap.containsKey(Integer.valueOf(i))) {
            return tableMap.get(Integer.valueOf(i));
        }
        return "" + i;
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

    public InternalError(int i) {
        this(i, codeOf(i));
    }

    public InternalError(int i, String str) {
        this.code = i;
        this.message = str;
    }
}
