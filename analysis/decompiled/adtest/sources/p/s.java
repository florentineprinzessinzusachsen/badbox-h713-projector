package p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s extends j2.h implements i2.a {
    @Override // i2.a
    public final Object a() throws Exception {
        t tVar = (t) this.f1264e;
        w2.c cVar = tVar.f1713a;
        if (cVar == null) {
            j2.i.h("coroutineScope");
            throw null;
        }
        r2.x.e(cVar);
        tVar.f();
        p pVar = tVar.f1716d;
        if (pVar != null) {
            pVar.f1688f.close();
            return u1.k.f2301a;
        }
        j2.i.h("connectionManager");
        throw null;
    }
}
