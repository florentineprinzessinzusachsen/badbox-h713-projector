package v0;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p extends s0.b0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final o f2473b = new o(0, new p(s0.z.f2138e));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s0.z f2474a;

    public p(s0.z zVar) {
        this.f2474a = zVar;
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        int iF0 = bVar.f0();
        int iA = o.e.a(iF0);
        if (iA == 5 || iA == 6) {
            return this.f2474a.a(bVar);
        }
        if (iA == 8) {
            bVar.b0();
            return null;
        }
        throw new s0.r("Expecting number, got: " + a1.c.h(iF0) + "; at path " + bVar.K(false));
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        dVar.Z((Number) obj);
    }
}
