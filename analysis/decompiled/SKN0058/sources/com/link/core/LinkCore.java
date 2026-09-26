package com.link.core;

import com.link.core.a.a;
import com.link.core.a.f;
import com.link.core.a.s;
import com.link.core.a.t;
import com.link.core.a.w;

/* JADX INFO: loaded from: classes.dex */
public final class LinkCore {
    public static boolean isRunning() {
        Thread thread = s.d.get();
        return thread != null && thread.isAlive();
    }

    public static boolean start(String str, Object obj) {
        String str2 = s.a;
        a.a = obj;
        s.a = f.a(str, obj);
        Thread thread = new Thread(new t(), "link-core-app");
        if (s.d.compareAndSet(null, thread)) {
            thread.start();
        } else {
            thread = null;
        }
        return thread != null;
    }

    public static void stop() {
        Thread thread = s.d.get();
        if (thread == null) {
            return;
        }
        boolean z = false;
        s.b.set(false);
        w.e();
        thread.interrupt();
        if (Thread.currentThread() == thread) {
            return;
        }
        while (thread.isAlive()) {
            try {
                thread.join();
            } catch (InterruptedException unused) {
                z = true;
                thread.interrupt();
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        s.d.compareAndSet(thread, null);
    }
}
