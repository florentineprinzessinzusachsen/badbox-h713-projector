package com.ad.proxy.b;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class J {
    public static volatile J d;
    public final ConcurrentHashMap a = new ConcurrentHashMap();
    public final LinkedList b = new LinkedList();
    public final Object c = new Object();

    public static J a() {
        if (d == null) {
            synchronized (J.class) {
                if (d == null) {
                    d = new J();
                }
            }
        }
        return d;
    }

    public final void a(long j) {
        D d2;
        while (this.a.size() < a0.n && !this.b.isEmpty()) {
            synchronized (this.c) {
                d2 = (D) this.b.poll();
            }
            if (d2 != null) {
                d2.b().a(d2);
            }
        }
    }
}
