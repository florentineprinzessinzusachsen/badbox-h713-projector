package q3;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k implements u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final o f1833d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Inflater f1834e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1835f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1836g;

    public k(o oVar, Inflater inflater) {
        this.f1833d = oVar;
        this.f1834e = inflater;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f1836g) {
            return;
        }
        this.f1834e.end();
        this.f1836g = true;
        this.f1833d.close();
    }

    @Override // q3.u
    public final w f() {
        return this.f1833d.f1844d.f();
    }

    @Override // q3.u
    public final long g(long j4, e eVar) throws IOException {
        long j5;
        Inflater inflater = this.f1834e;
        j2.i.e(eVar, "sink");
        while (!this.f1836g) {
            try {
                p pVarT = eVar.T(1);
                int iMin = (int) Math.min(8192L, 8192 - pVarT.f1849c);
                boolean zNeedsInput = inflater.needsInput();
                o oVar = this.f1833d;
                if (zNeedsInput && !oVar.b()) {
                    p pVar = oVar.f1845e.f1821d;
                    j2.i.b(pVar);
                    int i4 = pVar.f1849c;
                    int i5 = pVar.f1848b;
                    int i6 = i4 - i5;
                    this.f1835f = i6;
                    inflater.setInput(pVar.f1847a, i5, i6);
                }
                int iInflate = inflater.inflate(pVarT.f1847a, pVarT.f1849c, iMin);
                int i7 = this.f1835f;
                if (i7 != 0) {
                    int remaining = i7 - inflater.getRemaining();
                    this.f1835f -= remaining;
                    oVar.skip(remaining);
                }
                if (iInflate > 0) {
                    pVarT.f1849c += iInflate;
                    j5 = iInflate;
                    eVar.f1822e += j5;
                } else {
                    if (pVarT.f1848b == pVarT.f1849c) {
                        eVar.f1821d = pVarT.a();
                        q.a(pVarT);
                    }
                    j5 = 0;
                }
                if (j5 > 0) {
                    return j5;
                }
                if (inflater.finished() || inflater.needsDictionary()) {
                    return -1L;
                }
                if (oVar.b()) {
                    throw new EOFException("source exhausted prematurely");
                }
            } catch (DataFormatException e4) {
                throw new IOException(e4);
            }
        }
        throw new IllegalStateException("closed");
    }
}
