package q3;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j implements u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte f1828d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final o f1829e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Inflater f1830f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final k f1831g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final CRC32 f1832h;

    public j(g gVar) {
        j2.i.e(gVar, "source");
        o oVar = new o(gVar);
        this.f1829e = oVar;
        Inflater inflater = new Inflater(true);
        this.f1830f = inflater;
        this.f1831g = new k(oVar, inflater);
        this.f1832h = new CRC32();
    }

    public static void b(String str, int i4, int i5) throws IOException {
        if (i5 == i4) {
            return;
        }
        throw new IOException(str + ": actual 0x" + p2.i.J0(a.a.H(i5)) + " != expected 0x" + p2.i.J0(a.a.H(i4)));
    }

    public final void c(e eVar, long j4, long j5) {
        p pVar = eVar.f1821d;
        j2.i.b(pVar);
        while (true) {
            int i4 = pVar.f1849c;
            int i5 = pVar.f1848b;
            if (j4 < i4 - i5) {
                break;
            }
            j4 -= (long) (i4 - i5);
            pVar = pVar.f1852f;
            j2.i.b(pVar);
        }
        while (j5 > 0) {
            int i6 = (int) (((long) pVar.f1848b) + j4);
            int iMin = (int) Math.min(pVar.f1849c - i6, j5);
            this.f1832h.update(pVar.f1847a, i6, iMin);
            j5 -= (long) iMin;
            pVar = pVar.f1852f;
            j2.i.b(pVar);
            j4 = 0;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f1831g.close();
    }

    @Override // q3.u
    public final w f() {
        return this.f1829e.f1844d.f();
    }

    @Override // q3.u
    public final long g(long j4, e eVar) throws IOException {
        long j5;
        j jVar = this;
        j2.i.e(eVar, "sink");
        byte b4 = jVar.f1828d;
        CRC32 crc32 = jVar.f1832h;
        o oVar = jVar.f1829e;
        if (b4 == 0) {
            oVar.G(10L);
            e eVar2 = oVar.f1845e;
            byte bK = eVar2.k(3L);
            boolean z3 = ((bK >> 1) & 1) == 1;
            if (z3) {
                jVar.c(eVar2, 0L, 10L);
            }
            b("ID1ID2", 8075, oVar.readShort());
            oVar.skip(8L);
            if (((bK >> 2) & 1) == 1) {
                oVar.G(2L);
                if (z3) {
                    c(eVar2, 0L, 2L);
                }
                short s3 = eVar2.readShort();
                long j6 = ((short) (((s3 & 255) << 8) | ((s3 & 65280) >>> 8))) & 65535;
                oVar.G(j6);
                if (z3) {
                    c(eVar2, 0L, j6);
                }
                oVar.skip(j6);
            }
            if (((bK >> 3) & 1) == 1) {
                long jC = oVar.c((byte) 0, 0L, Long.MAX_VALUE);
                if (jC == -1) {
                    throw new EOFException();
                }
                if (z3) {
                    j5 = 2;
                    c(eVar2, 0L, jC + 1);
                } else {
                    j5 = 2;
                }
                oVar.skip(jC + 1);
            } else {
                j5 = 2;
            }
            if (((bK >> 4) & 1) == 1) {
                j5 = j5;
                long jC2 = oVar.c((byte) 0, 0L, Long.MAX_VALUE);
                if (jC2 == -1) {
                    throw new EOFException();
                }
                if (z3) {
                    jVar = this;
                    jVar.c(eVar2, 0L, jC2 + 1);
                } else {
                    jVar = this;
                }
                oVar.skip(jC2 + 1);
            } else {
                jVar = this;
            }
            if (z3) {
                oVar.G(j5);
                short s4 = eVar2.readShort();
                b("FHCRC", (short) (((s4 & 255) << 8) | ((s4 & 65280) >>> 8)), (short) crc32.getValue());
                crc32.reset();
            }
            jVar.f1828d = (byte) 1;
        }
        if (jVar.f1828d == 1) {
            long j7 = eVar.f1822e;
            long jG = jVar.f1831g.g(8192L, eVar);
            if (jG != -1) {
                jVar.c(eVar, j7, jG);
                return jG;
            }
            jVar.f1828d = (byte) 2;
        }
        if (jVar.f1828d == 2) {
            b("CRC", oVar.k(), (int) crc32.getValue());
            b("ISIZE", oVar.k(), (int) jVar.f1830f.getBytesWritten());
            jVar.f1828d = (byte) 3;
            if (!oVar.b()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }
}
