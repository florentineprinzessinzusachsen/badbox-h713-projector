package g3;

import q3.i;
import q3.n;
import q3.s;
import q3.w;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements s {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i f973d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f974e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ h f975f;

    public f(h hVar) {
        this.f975f = hVar;
        this.f973d = new i(((n) hVar.f980c.f47g).f1841d.f());
    }

    @Override // q3.s
    public final void R(long j4, q3.e eVar) {
        if (this.f974e) {
            throw new IllegalStateException("closed");
        }
        b3.d.a(eVar.f1822e, 0L, j4);
        ((n) this.f975f.f980c.f47g).R(j4, eVar);
    }

    @Override // q3.s, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f974e) {
            return;
        }
        this.f974e = true;
        i iVar = this.f973d;
        w wVar = iVar.f1827e;
        iVar.f1827e = w.f1859d;
        wVar.a();
        wVar.b();
        this.f975f.f981d = 3;
    }

    @Override // q3.s
    public final w f() {
        return this.f973d;
    }

    @Override // q3.s, java.io.Flushable
    public final void flush() {
        if (this.f974e) {
            return;
        }
        ((n) this.f975f.f980c.f47g).flush();
    }
}
