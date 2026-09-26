package g3;

import a3.r;
import j2.i;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g extends b {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f976h;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f963f) {
            return;
        }
        if (!this.f976h) {
            b(h.f977f);
        }
        this.f963f = true;
    }

    @Override // g3.b, q3.u
    public final long g(long j4, q3.e eVar) throws IOException {
        i.e(eVar, "sink");
        if (this.f963f) {
            throw new IllegalStateException("closed");
        }
        if (this.f976h) {
            return -1L;
        }
        long jG = super.g(8192L, eVar);
        if (jG != -1) {
            return jG;
        }
        this.f976h = true;
        b(r.f198e);
        return -1L;
    }
}
