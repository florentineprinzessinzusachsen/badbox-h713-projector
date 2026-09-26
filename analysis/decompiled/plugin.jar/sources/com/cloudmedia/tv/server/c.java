package com.cloudmedia.tv.server;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile c f40a;

    private c() {
        d.R();
    }

    public static c a() {
        if (f40a == null) {
            synchronized (c.class) {
                if (f40a == null) {
                    f40a = new c();
                }
            }
        }
        return f40a;
    }
}
