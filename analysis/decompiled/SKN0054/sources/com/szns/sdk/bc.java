package com.szns.sdk;

import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes.dex */
public class bc {
    public static final bc c = new bd();
    private boolean a;
    private long b;
    private long d;

    public final long d() {
        return this.d;
    }

    public final boolean e() {
        return this.a;
    }

    public final long f() {
        if (this.a) {
            return this.b;
        }
        throw new IllegalStateException("No deadline");
    }

    public void g() throws InterruptedIOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.a && this.b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
