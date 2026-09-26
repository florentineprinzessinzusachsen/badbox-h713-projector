package e;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: GzipSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f4739b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Inflater f4740c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final k f4741d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4738a = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final CRC32 f4742e = new CRC32();

    public j(s sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.f4740c = new Inflater(true);
        this.f4739b = l.a(sVar);
        this.f4741d = new k(this.f4739b, this.f4740c);
    }

    private void a() throws IOException {
        this.f4739b.g(10L);
        byte bA = this.f4739b.c().a(3L);
        boolean z = ((bA >> 1) & 1) == 1;
        if (z) {
            a(this.f4739b.c(), 0L, 10L);
        }
        a("ID1ID2", 8075, this.f4739b.readShort());
        this.f4739b.skip(8L);
        if (((bA >> 2) & 1) == 1) {
            this.f4739b.g(2L);
            if (z) {
                a(this.f4739b.c(), 0L, 2L);
            }
            long jD = this.f4739b.c().d();
            this.f4739b.g(jD);
            if (z) {
                a(this.f4739b.c(), 0L, jD);
            }
            this.f4739b.skip(jD);
        }
        if (((bA >> 3) & 1) == 1) {
            long jA = this.f4739b.a((byte) 0);
            if (jA == -1) {
                throw new EOFException();
            }
            if (z) {
                a(this.f4739b.c(), 0L, jA + 1);
            }
            this.f4739b.skip(jA + 1);
        }
        if (((bA >> 4) & 1) == 1) {
            long jA2 = this.f4739b.a((byte) 0);
            if (jA2 == -1) {
                throw new EOFException();
            }
            if (z) {
                a(this.f4739b.c(), 0L, jA2 + 1);
            }
            this.f4739b.skip(jA2 + 1);
        }
        if (z) {
            a("FHCRC", this.f4739b.d(), (short) this.f4742e.getValue());
            this.f4742e.reset();
        }
    }

    private void b() throws IOException {
        a("CRC", this.f4739b.i(), (int) this.f4742e.getValue());
        a("ISIZE", this.f4739b.i(), (int) this.f4740c.getBytesWritten());
    }

    @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f4741d.close();
    }

    @Override // e.s
    public long read(c cVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return 0L;
        }
        if (this.f4738a == 0) {
            a();
            this.f4738a = 1;
        }
        if (this.f4738a == 1) {
            long j2 = cVar.f4728b;
            long j3 = this.f4741d.read(cVar, j);
            if (j3 != -1) {
                a(cVar, j2, j3);
                return j3;
            }
            this.f4738a = 2;
        }
        if (this.f4738a == 2) {
            b();
            this.f4738a = 3;
            if (!this.f4739b.j()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    @Override // e.s
    public t timeout() {
        return this.f4739b.timeout();
    }

    private void a(c cVar, long j, long j2) {
        o oVar = cVar.f4727a;
        while (true) {
            int i = oVar.f4761c;
            int i2 = oVar.f4760b;
            if (j < i - i2) {
                break;
            }
            j -= (long) (i - i2);
            oVar = oVar.f4764f;
        }
        while (j2 > 0) {
            int i3 = (int) (((long) oVar.f4760b) + j);
            int iMin = (int) Math.min(oVar.f4761c - i3, j2);
            this.f4742e.update(oVar.f4759a, i3, iMin);
            j2 -= (long) iMin;
            oVar = oVar.f4764f;
            j = 0;
        }
    }

    private void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }
}
