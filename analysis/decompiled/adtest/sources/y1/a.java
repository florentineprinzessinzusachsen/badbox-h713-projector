package y1;

import i2.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g f2722d;

    public a(g gVar) {
        this.f2722d = gVar;
    }

    @Override // y1.h
    public /* bridge */ h C(g gVar) {
        return l3.h.U(this, gVar);
    }

    @Override // y1.h
    public final Object K(Object obj, p pVar) {
        return pVar.f(obj, this);
    }

    @Override // y1.f
    public final g getKey() {
        return this.f2722d;
    }

    @Override // y1.h
    public /* bridge */ f k(g gVar) {
        return l3.h.C(this, gVar);
    }

    @Override // y1.h
    public final /* bridge */ h l(h hVar) {
        return l3.h.Y(this, hVar);
    }
}
