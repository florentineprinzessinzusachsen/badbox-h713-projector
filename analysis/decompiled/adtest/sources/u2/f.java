package u2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g f2320d;

    public f(g gVar) {
        this.f2320d = gVar;
    }

    @Override // u2.g
    public final Object b(h hVar, y1.c cVar) {
        j2.n nVar = new j2.n();
        nVar.f1276d = v2.c.f2527b;
        Object objB = this.f2320d.b(new e(this, nVar, hVar), cVar);
        return objB == z1.a.f2781d ? objB : u1.k.f2301a;
    }
}
