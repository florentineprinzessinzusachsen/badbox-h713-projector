package com.link.core.a;

/* JADX INFO: loaded from: classes.dex */
public final class t implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        try {
            s.c.run();
        } finally {
            s.b.set(false);
            w.e();
            s.d.compareAndSet(Thread.currentThread(), null);
        }
    }
}
