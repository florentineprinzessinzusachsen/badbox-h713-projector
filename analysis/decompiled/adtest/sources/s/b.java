package s;

import a3.h;
import i2.p;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements r.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h f2067d;

    public b(h hVar) {
        this.f2067d = hVar;
    }

    @Override // java.lang.AutoCloseable
    public final void close() throws IOException {
        ((x.d) this.f2067d.f149e).close();
    }

    @Override // r.b
    public final Object x(boolean z3, p pVar, a2.c cVar) {
        x.d dVar = (x.d) this.f2067d.f149e;
        dVar.getClass();
        return pVar.f(new d(new a(dVar.M())), cVar);
    }
}
