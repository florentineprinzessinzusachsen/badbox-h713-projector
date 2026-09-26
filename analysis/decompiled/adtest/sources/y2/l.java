package y2;

import r2.s;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l extends s {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final l f2767f = new l();

    @Override // r2.s
    public final void S(y1.h hVar, Runnable runnable) {
        e.f2755g.f2757f.c(runnable, true, false);
    }

    @Override // r2.s
    public final void T(y1.h hVar, Runnable runnable) {
        e.f2755g.f2757f.c(runnable, true, true);
    }

    @Override // r2.s
    public final s V(int i4) {
        w2.a.a(i4);
        return i4 >= k.f2764d ? this : super.V(i4);
    }

    @Override // r2.s
    public final String toString() {
        return "Dispatchers.IO";
    }
}
