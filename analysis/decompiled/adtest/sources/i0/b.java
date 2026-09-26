package i0;

import j0.g;
import j2.i;
import l0.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f1204a;

    public b(g gVar) {
        i.e(gVar, "tracker");
        this.f1204a = gVar;
    }

    @Override // i0.d
    public final boolean b(p pVar) {
        return a(pVar) && e(this.f1204a.a());
    }

    @Override // i0.d
    public final u2.c c(d0.e eVar) {
        i.e(eVar, "constraints");
        return new u2.c(new h0.f(this, null, 1), y1.i.f2726d, -2, t2.a.f2174d);
    }

    public abstract int d();

    public abstract boolean e(Object obj);
}
