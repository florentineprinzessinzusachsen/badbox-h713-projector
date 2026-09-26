package g3;

import q3.i;
import q3.n;
import q3.s;
import q3.w;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c implements s {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i f965d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f966e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ h f967f;

    public c(h hVar) {
        this.f967f = hVar;
        this.f965d = new i(((n) hVar.f980c.f47g).f1841d.f());
    }

    @Override // q3.s
    public final void R(long j4, q3.e eVar) {
        if (this.f966e) {
            throw new IllegalStateException("closed");
        }
        if (j4 == 0) {
            return;
        }
        n nVar = (n) this.f967f.f980c.f47g;
        if (nVar.f1843f) {
            throw new IllegalStateException("closed");
        }
        nVar.f1842e.Z(j4);
        nVar.b();
        nVar.H("\r\n");
        nVar.R(j4, eVar);
        nVar.H("\r\n");
    }

    @Override // q3.s, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (this.f966e) {
            return;
        }
        this.f966e = true;
        ((n) this.f967f.f980c.f47g).H("0\r\n\r\n");
        i iVar = this.f965d;
        w wVar = iVar.f1827e;
        iVar.f1827e = w.f1859d;
        wVar.a();
        wVar.b();
        this.f967f.f981d = 3;
    }

    @Override // q3.s
    public final w f() {
        return this.f965d;
    }

    @Override // q3.s, java.io.Flushable
    public final synchronized void flush() {
        if (this.f966e) {
            return;
        }
        ((n) this.f967f.f980c.f47g).flush();
    }
}
