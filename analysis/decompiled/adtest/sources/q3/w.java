package q3;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class w {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final v f1859d = new v();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1860a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1861b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f1862c;

    public w a() {
        this.f1860a = false;
        return this;
    }

    public w b() {
        this.f1862c = 0L;
        return this;
    }

    public long c() {
        if (this.f1860a) {
            return this.f1861b;
        }
        throw new IllegalStateException("No deadline");
    }

    public w d(long j4) {
        this.f1860a = true;
        this.f1861b = j4;
        return this;
    }

    public boolean e() {
        return this.f1860a;
    }

    public void f() throws InterruptedIOException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        if (this.f1860a && this.f1861b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public w g(long j4) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        j2.i.e(timeUnit, "unit");
        if (j4 >= 0) {
            this.f1862c = timeUnit.toNanos(j4);
            return this;
        }
        throw new IllegalArgumentException(("timeout < 0: " + j4).toString());
    }
}
