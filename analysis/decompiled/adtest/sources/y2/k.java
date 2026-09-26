package y2;

import java.util.concurrent.TimeUnit;
import w2.t;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2761a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f2762b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f2763c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f2764d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f2765e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final g f2766f;

    static {
        String property;
        int i4 = t.f2651a;
        try {
            property = System.getProperty("kotlinx.coroutines.scheduler.default.name");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            property = "DefaultDispatcher";
        }
        f2761a = property;
        f2762b = w2.a.i("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 1L, Long.MAX_VALUE);
        int i5 = t.f2651a;
        if (i5 < 2) {
            i5 = 2;
        }
        f2763c = w2.a.j("kotlinx.coroutines.scheduler.core.pool.size", i5, 8);
        f2764d = w2.a.j("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 4);
        f2765e = TimeUnit.SECONDS.toNanos(w2.a.i("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 1L, Long.MAX_VALUE));
        f2766f = g.f2756a;
    }
}
