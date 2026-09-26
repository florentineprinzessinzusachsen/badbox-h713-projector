package r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements y1.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a1.a f1863e = new a1.a(20);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s f1864d;

    public a(s sVar) {
        j2.i.e(sVar, "connectionWrapper");
        this.f1864d = sVar;
    }

    @Override // y1.h
    public final y1.h C(y1.g gVar) {
        return l3.h.U(this, gVar);
    }

    @Override // y1.h
    public final Object K(Object obj, i2.p pVar) {
        return pVar.f(obj, this);
    }

    @Override // y1.f
    public final y1.g getKey() {
        return f1863e;
    }

    @Override // y1.h
    public final y1.f k(y1.g gVar) {
        return l3.h.C(this, gVar);
    }

    @Override // y1.h
    public final y1.h l(y1.h hVar) {
        return l3.h.Y(this, hVar);
    }
}
