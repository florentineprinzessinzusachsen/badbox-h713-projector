package q3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends InputStream {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1819d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ g f1820e;

    public /* synthetic */ d(g gVar, int i4) {
        this.f1819d = i4;
        this.f1820e = gVar;
    }

    @Override // java.io.InputStream
    public final int available() throws IOException {
        long jMin;
        switch (this.f1819d) {
            case 0:
                jMin = Math.min(((e) this.f1820e).f1822e, Integer.MAX_VALUE);
                break;
            default:
                o oVar = (o) this.f1820e;
                if (oVar.f1846f) {
                    throw new IOException("closed");
                }
                jMin = Math.min(oVar.f1845e.f1822e, Integer.MAX_VALUE);
                break;
        }
        return (int) jMin;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        switch (this.f1819d) {
            case 0:
                break;
            default:
                ((o) this.f1820e).close();
                break;
        }
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        switch (this.f1819d) {
            case 0:
                e eVar = (e) this.f1820e;
                if (eVar.f1822e > 0) {
                    return eVar.readByte() & 255;
                }
                return -1;
            default:
                o oVar = (o) this.f1820e;
                e eVar2 = oVar.f1845e;
                if (oVar.f1846f) {
                    throw new IOException("closed");
                }
                if (eVar2.f1822e == 0 && oVar.f1844d.g(8192L, eVar2) == -1) {
                    return -1;
                }
                return eVar2.readByte() & 255;
        }
    }

    public final String toString() {
        switch (this.f1819d) {
            case 0:
                return ((e) this.f1820e) + ".inputStream()";
            default:
                return ((o) this.f1820e) + ".inputStream()";
        }
    }

    @Override // java.io.InputStream
    public long transferTo(OutputStream outputStream) throws IOException {
        switch (this.f1819d) {
            case 1:
                j2.i.e(outputStream, "out");
                o oVar = (o) this.f1820e;
                e eVar = oVar.f1845e;
                if (oVar.f1846f) {
                    throw new IOException("closed");
                }
                long j4 = 0;
                long j5 = 0;
                while (true) {
                    if (eVar.f1822e == j4 && oVar.f1844d.g(8192L, eVar) == -1) {
                        return j5;
                    }
                    long j6 = eVar.f1822e;
                    j5 += j6;
                    a.a.f(j6, 0L, j6);
                    p pVar = eVar.f1821d;
                    while (j6 > j4) {
                        j2.i.b(pVar);
                        int iMin = (int) Math.min(j6, pVar.f1849c - pVar.f1848b);
                        outputStream.write(pVar.f1847a, pVar.f1848b, iMin);
                        int i4 = pVar.f1848b + iMin;
                        pVar.f1848b = i4;
                        long j7 = iMin;
                        eVar.f1822e -= j7;
                        j6 -= j7;
                        if (i4 == pVar.f1849c) {
                            p pVarA = pVar.a();
                            eVar.f1821d = pVarA;
                            q.a(pVar);
                            pVar = pVarA;
                        }
                        j4 = 0;
                    }
                }
                break;
            default:
                return super.transferTo(outputStream);
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i4, int i5) throws IOException {
        switch (this.f1819d) {
            case 0:
                j2.i.e(bArr, "sink");
                return ((e) this.f1820e).read(bArr, i4, i5);
            default:
                j2.i.e(bArr, "data");
                o oVar = (o) this.f1820e;
                e eVar = oVar.f1845e;
                if (!oVar.f1846f) {
                    a.a.f(bArr.length, i4, i5);
                    if (eVar.f1822e == 0 && oVar.f1844d.g(8192L, eVar) == -1) {
                        return -1;
                    }
                    return eVar.read(bArr, i4, i5);
                }
                throw new IOException("closed");
        }
    }

    private final void b() {
    }
}
