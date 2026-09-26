package e;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Timeout.java */
/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final t f4768d = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f4769a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f4770b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f4771c;

    /* JADX INFO: compiled from: Timeout.java */
    final class a extends t {
        a() {
        }

        @Override // e.t
        public t a(long j) {
            return this;
        }

        @Override // e.t
        public t a(long j, TimeUnit timeUnit) {
            return this;
        }

        @Override // e.t
        public void e() {
        }
    }

    public t a(long j, TimeUnit timeUnit) {
        if (j >= 0) {
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            this.f4771c = timeUnit.toNanos(j);
            return this;
        }
        throw new IllegalArgumentException("timeout < 0: " + j);
    }

    public t b() {
        this.f4771c = 0L;
        return this;
    }

    public long c() {
        if (this.f4769a) {
            return this.f4770b;
        }
        throw new IllegalStateException("No deadline");
    }

    public boolean d() {
        return this.f4769a;
    }

    public void e() throws InterruptedIOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        }
        if (this.f4769a && this.f4770b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public long f() {
        return this.f4771c;
    }

    public t a(long j) {
        this.f4769a = true;
        this.f4770b = j;
        return this;
    }

    public t a() {
        this.f4769a = false;
        return this;
    }
}
