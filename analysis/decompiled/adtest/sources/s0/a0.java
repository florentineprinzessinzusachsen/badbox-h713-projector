package s0;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a0 extends b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b0 f2084a;

    public a0(b0 b0Var) {
        this.f2084a = b0Var;
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        if (bVar.f0() != 9) {
            return this.f2084a.b(bVar);
        }
        bVar.b0();
        return null;
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        if (obj == null) {
            dVar.S();
        } else {
            this.f2084a.c(dVar, obj);
        }
    }

    public final String toString() {
        return "NullSafeTypeAdapter[" + this.f2084a + "]";
    }
}
