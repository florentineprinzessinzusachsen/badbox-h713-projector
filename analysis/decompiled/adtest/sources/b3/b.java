package b3;

import a3.f0;
import a3.v;
import d0.l0;
import j2.i;
import q3.u;
import q3.w;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends f0 implements u {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final v f340e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f341f;

    public b(v vVar, long j4) {
        this.f340e = vVar;
        this.f341f = j4;
    }

    @Override // a3.f0
    public final long b() {
        return this.f341f;
    }

    @Override // a3.f0
    public final v c() {
        return this.f340e;
    }

    @Override // q3.u
    public final w f() {
        return w.f1859d;
    }

    @Override // q3.u
    public final long g(long j4, q3.e eVar) {
        i.e(eVar, "sink");
        throw new IllegalStateException("Unreadable ResponseBody! These Response objects have bodies that are stripped:\n * Response.cacheResponse\n * Response.networkResponse\n * Response.priorResponse\n * EventSourceListener\n * WebSocketListener\n(It is safe to call contentType() and contentLength() on these response bodies.)");
    }

    @Override // a3.f0
    public final q3.g k() {
        return l0.f(this);
    }

    @Override // a3.f0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
