package q3;

import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o implements g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u f1844d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f1845e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1846f;

    public o(u uVar) {
        j2.i.e(uVar, "source");
        this.f1844d = uVar;
        this.f1845e = new e();
    }

    @Override // q3.g
    public final void G(long j4) throws EOFException {
        if (!l(j4)) {
            throw new EOFException();
        }
    }

    @Override // q3.g
    public final String O(Charset charset) {
        j2.i.e(charset, "charset");
        u uVar = this.f1844d;
        e eVar = this.f1845e;
        eVar.W(uVar);
        return eVar.O(charset);
    }

    @Override // q3.g
    public final InputStream Q() {
        return new d(this, 1);
    }

    public final boolean b() {
        if (this.f1846f) {
            throw new IllegalStateException("closed");
        }
        e eVar = this.f1845e;
        return eVar.c() && this.f1844d.g(8192L, eVar) == -1;
    }

    public final long c(byte b4, long j4, long j5) {
        if (this.f1846f) {
            throw new IllegalStateException("closed");
        }
        if (0 > j5) {
            throw new IllegalArgumentException(("fromIndex=0 toIndex=" + j5).toString());
        }
        long jMax = 0;
        while (jMax < j5) {
            e eVar = this.f1845e;
            byte b5 = b4;
            long j6 = j5;
            long jL = eVar.l(b5, jMax, j6);
            if (jL != -1) {
                return jL;
            }
            long j7 = eVar.f1822e;
            if (j7 >= j6 || this.f1844d.g(8192L, eVar) == -1) {
                break;
            }
            jMax = Math.max(jMax, j7);
            b4 = b5;
            j5 = j6;
        }
        return -1L;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() {
        if (this.f1846f) {
            return;
        }
        this.f1846f = true;
        this.f1844d.close();
        e eVar = this.f1845e;
        eVar.skip(eVar.f1822e);
    }

    @Override // q3.u
    public final w f() {
        return this.f1844d.f();
    }

    @Override // q3.u
    public final long g(long j4, e eVar) {
        j2.i.e(eVar, "sink");
        if (j4 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j4).toString());
        }
        if (this.f1846f) {
            throw new IllegalStateException("closed");
        }
        e eVar2 = this.f1845e;
        if (eVar2.f1822e == 0) {
            if (j4 == 0) {
                return 0L;
            }
            if (this.f1844d.g(8192L, eVar2) == -1) {
                return -1L;
            }
        }
        return eVar2.g(Math.min(j4, eVar2.f1822e), eVar);
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.f1846f;
    }

    public final int k() throws EOFException {
        G(4L);
        int i4 = this.f1845e.readInt();
        return ((i4 & 255) << 24) | (((-16777216) & i4) >>> 24) | ((16711680 & i4) >>> 8) | ((65280 & i4) << 8);
    }

    public final boolean l(long j4) {
        e eVar;
        if (j4 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j4).toString());
        }
        if (this.f1846f) {
            throw new IllegalStateException("closed");
        }
        do {
            eVar = this.f1845e;
            if (eVar.f1822e >= j4) {
                return true;
            }
        } while (this.f1844d.g(8192L, eVar) != -1);
        return false;
    }

    @Override // q3.g
    public final int m(m mVar) throws EOFException {
        e eVar;
        j2.i.e(mVar, "options");
        if (this.f1846f) {
            throw new IllegalStateException("closed");
        }
        do {
            eVar = this.f1845e;
            int iB = r3.a.b(eVar, mVar, true);
            if (iB != -2) {
                if (iB == -1) {
                    break;
                }
                eVar.skip(mVar.f1839d[iB].a());
                return iB;
            }
        } while (this.f1844d.g(8192L, eVar) != -1);
        return -1;
    }

    @Override // q3.g
    public final h q(long j4) throws EOFException {
        G(j4);
        return this.f1845e.q(j4);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        j2.i.e(byteBuffer, "sink");
        e eVar = this.f1845e;
        if (eVar.f1822e == 0 && this.f1844d.g(8192L, eVar) == -1) {
            return -1;
        }
        return eVar.read(byteBuffer);
    }

    @Override // q3.g
    public final byte readByte() throws EOFException {
        G(1L);
        return this.f1845e.readByte();
    }

    @Override // q3.g
    public final int readInt() throws EOFException {
        G(4L);
        return this.f1845e.readInt();
    }

    @Override // q3.g
    public final short readShort() throws EOFException {
        G(2L);
        return this.f1845e.readShort();
    }

    @Override // q3.g
    public final void skip(long j4) throws EOFException {
        if (this.f1846f) {
            throw new IllegalStateException("closed");
        }
        while (j4 > 0) {
            e eVar = this.f1845e;
            if (eVar.f1822e == 0 && this.f1844d.g(8192L, eVar) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j4, eVar.f1822e);
            eVar.skip(jMin);
            j4 -= jMin;
        }
    }

    @Override // q3.g
    public final String t(long j4) throws EOFException {
        if (j4 < 0) {
            throw new IllegalArgumentException(("limit < 0: " + j4).toString());
        }
        long j5 = j4 == Long.MAX_VALUE ? Long.MAX_VALUE : j4 + 1;
        long jC = c((byte) 10, 0L, j5);
        e eVar = this.f1845e;
        if (jC != -1) {
            return r3.a.a(jC, eVar);
        }
        if (j5 < Long.MAX_VALUE && l(j5) && eVar.k(j5 - 1) == 13 && l(j5 + 1) && eVar.k(j5) == 10) {
            return r3.a.a(j5, eVar);
        }
        e eVar2 = new e();
        eVar.b(eVar2, 0L, Math.min(32, eVar.f1822e));
        throw new EOFException("\\n not found: limit=" + Math.min(eVar.f1822e, j4) + " content=" + eVar2.q(eVar2.f1822e).b() + (char) 8230);
    }

    public final String toString() {
        return "buffer(" + this.f1844d + ')';
    }
}
