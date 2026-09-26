package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e1 extends j1 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final y1.c f1975g;

    /* JADX WARN: Multi-variable type inference failed */
    public e1(y1.h hVar, i2.p pVar) {
        super(hVar, false);
        this.f1975g = ((a2.a) pVar).i(this, this);
    }

    @Override // r2.d1
    public final void S() {
        try {
            w2.a.h(u1.k.f2301a, z1.d.a(this.f1975g));
        } catch (Throwable th) {
            j(d0.l0.l(th));
            throw th;
        }
    }
}
