package com.szns.sdk;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class aj extends bc {

    @Nullable
    static aj a;
    private static final long b;
    private static final long d;
    private boolean e;

    @Nullable
    private aj f;
    private long g;

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        b = millis;
        d = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0055 A[Catch: all -> 0x0062, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0016, B:10:0x0022, B:11:0x002b, B:16:0x003a, B:17:0x003f, B:19:0x0043, B:23:0x004d, B:25:0x0055, B:15:0x0034, B:30:0x005c, B:31:0x0061), top: B:35:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x005a A[DONT_GENERATE] */
    /* JADX WARN: Instruction removed from duplicated block: B:28:0x005a, please report this as an issue */
    private static synchronized void a(aj ajVar, long j, boolean z) {
        aj ajVar2;
        aj ajVar3;
        if (a == null) {
            a = new aj();
            new am().start();
        }
        long jNanoTime = System.nanoTime();
        if (j == 0 || !z) {
            if (j == 0) {
                if (!z) {
                    throw new AssertionError();
                }
                ajVar.g = ajVar.f();
            }
            long j2 = ajVar.g - jNanoTime;
            ajVar2 = a;
            while (true) {
                ajVar3 = ajVar2.f;
                if (ajVar3 != null || j2 < ajVar3.g - jNanoTime) {
                    break;
                    break;
                }
                ajVar2 = ajVar3;
            }
            ajVar.f = ajVar3;
            ajVar2.f = ajVar;
            if (ajVar2 == a) {
                aj.class.notify();
            }
        }
        j = Math.min(j, ajVar.f() - jNanoTime);
        ajVar.g = j + jNanoTime;
        long j3 = ajVar.g - jNanoTime;
        ajVar2 = a;
        while (true) {
            ajVar3 = ajVar2.f;
            if (ajVar3 != null) {
                break;
            } else {
                ajVar2 = ajVar3;
            }
        }
        ajVar.f = ajVar3;
        ajVar2.f = ajVar;
        if (ajVar2 == a) {
            aj.class.notify();
        }
    }

    private static synchronized boolean a(aj ajVar) {
        boolean z;
        aj ajVar2 = a;
        while (ajVar2 != null) {
            aj ajVar3 = ajVar2.f;
            if (ajVar3 == ajVar) {
                ajVar2.f = ajVar.f;
                ajVar.f = null;
                z = false;
            } else {
                ajVar2 = ajVar3;
            }
        }
        z = true;
        return z;
    }

    @Nullable
    static aj c() throws InterruptedException {
        aj ajVar = a.f;
        long jNanoTime = System.nanoTime();
        if (ajVar == null) {
            aj.class.wait(b);
            if (a.f != null || System.nanoTime() - jNanoTime < d) {
                return null;
            }
            return a;
        }
        long j = ajVar.g - jNanoTime;
        if (j > 0) {
            long j2 = j / 1000000;
            aj.class.wait(j2, (int) (j - (1000000 * j2)));
            return null;
        }
        a.f = ajVar.f;
        ajVar.f = null;
        return ajVar;
    }

    private boolean h() {
        if (!this.e) {
            return false;
        }
        this.e = false;
        return a(this);
    }

    final IOException a(IOException iOException) {
        return !h() ? iOException : b(iOException);
    }

    public final void a() {
        if (this.e) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long jD = d();
        boolean zE = e();
        if (jD != 0 || zE) {
            this.e = true;
            a(this, jD, zE);
        }
    }

    final void a(boolean z) throws IOException {
        if (h() && z) {
            throw b(null);
        }
    }

    protected IOException b(@Nullable IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    protected void b() {
    }
}
