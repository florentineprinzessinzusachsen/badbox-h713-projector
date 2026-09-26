package q3;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l implements u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final InputStream f1837d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final w f1838e;

    public l(InputStream inputStream, w wVar) {
        this.f1837d = inputStream;
        this.f1838e = wVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f1837d.close();
    }

    @Override // q3.u
    public final w f() {
        return this.f1838e;
    }

    @Override // q3.u
    public final long g(long j4, e eVar) throws IOException {
        j2.i.e(eVar, "sink");
        try {
            this.f1838e.f();
            p pVarT = eVar.T(1);
            int i4 = this.f1837d.read(pVarT.f1847a, pVarT.f1849c, (int) Math.min(8192L, 8192 - pVarT.f1849c));
            if (i4 != -1) {
                pVarT.f1849c += i4;
                long j5 = i4;
                eVar.f1822e += j5;
                return j5;
            }
            if (pVarT.f1848b != pVarT.f1849c) {
                return -1L;
            }
            eVar.f1821d = pVarT.a();
            q.a(pVarT);
            return -1L;
        } catch (AssertionError e4) {
            if (r3.f.a(e4)) {
                throw new IOException(e4);
            }
            throw e4;
        }
    }

    public final String toString() {
        return "source(" + this.f1837d + ')';
    }
}
