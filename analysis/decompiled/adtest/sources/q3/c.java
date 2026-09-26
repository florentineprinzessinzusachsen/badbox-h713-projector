package q3;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class c extends w {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e3.w f1810h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static c f1811i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final ReentrantLock f1812j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Condition f1813k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final long f1814l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final long f1815m;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1816e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1817f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f1818g;

    static {
        e3.w wVar = new e3.w();
        wVar.f826b = new c[8];
        f1810h = wVar;
        ReentrantLock reentrantLock = new ReentrantLock();
        f1812j = reentrantLock;
        Condition conditionNewCondition = reentrantLock.newCondition();
        j2.i.d(conditionNewCondition, "newCondition(...)");
        f1813k = conditionNewCondition;
        long millis = TimeUnit.SECONDS.toMillis(60L);
        f1814l = millis;
        f1815m = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    public final void h() {
        long j4 = this.f1862c;
        boolean z3 = this.f1860a;
        if (j4 != 0 || z3) {
            ReentrantLock reentrantLock = f1812j;
            reentrantLock.lock();
            try {
                if (this.f1816e != 0) {
                    throw new IllegalStateException("Unbalanced enter/exit");
                }
                this.f1816e = 1;
                a1.a.h(this);
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
    }

    public final boolean i() {
        ReentrantLock reentrantLock = f1812j;
        reentrantLock.lock();
        try {
            int i4 = this.f1816e;
            this.f1816e = 0;
            if (i4 != 1) {
                return i4 == 2;
            }
            f1810h.d(this);
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public IOException j(IOException iOException) {
        throw null;
    }

    public void k() {
    }
}
