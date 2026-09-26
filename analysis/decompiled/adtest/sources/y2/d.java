package y2;

import java.util.concurrent.Executor;
import r2.p0;
import r2.s;
import w2.t;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends p0 implements Executor {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final d f2753f = new d();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final s f2754g;

    static {
        l lVar = l.f2767f;
        int i4 = t.f2651a;
        if (64 >= i4) {
            i4 = 64;
        }
        f2754g = lVar.V(w2.a.j("kotlinx.coroutines.io.parallelism", i4, 12));
    }

    @Override // r2.s
    public final void S(y1.h hVar, Runnable runnable) {
        f2754g.S(hVar, runnable);
    }

    @Override // r2.s
    public final void T(y1.h hVar, Runnable runnable) {
        f2754g.T(hVar, runnable);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO");
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        S(y1.i.f2726d, runnable);
    }

    @Override // r2.s
    public final String toString() {
        return "Dispatchers.IO";
    }
}
