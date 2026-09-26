package r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i implements u2.g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ u2.g f1895d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ p.t f1896e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ d0.h f1897f;

    public i(u2.g gVar, p.t tVar, d0.h hVar) {
        this.f1895d = gVar;
        this.f1896e = tVar;
        this.f1897f = hVar;
    }

    @Override // u2.g
    public final Object b(u2.h hVar, y1.c cVar) {
        Object objB = this.f1895d.b(new h(hVar, this.f1896e, this.f1897f), cVar);
        return objB == z1.a.f2781d ? objB : u1.k.f2301a;
    }
}
