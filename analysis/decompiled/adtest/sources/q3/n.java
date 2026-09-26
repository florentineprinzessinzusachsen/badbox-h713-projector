package q3;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n implements f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s f1841d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f1842e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1843f;

    public n(s sVar) {
        j2.i.e(sVar, "sink");
        this.f1841d = sVar;
        this.f1842e = new e();
    }

    @Override // q3.f
    public final f D(int i4, byte[] bArr) {
        if (this.f1843f) {
            throw new IllegalStateException("closed");
        }
        this.f1842e.U(i4, bArr);
        b();
        return this;
    }

    @Override // q3.f
    public final f H(String str) {
        j2.i.e(str, "string");
        if (this.f1843f) {
            throw new IllegalStateException("closed");
        }
        this.f1842e.c0(str);
        b();
        return this;
    }

    @Override // q3.s
    public final void R(long j4, e eVar) {
        j2.i.e(eVar, "source");
        if (this.f1843f) {
            throw new IllegalStateException("closed");
        }
        this.f1842e.R(j4, eVar);
        b();
    }

    public final f b() {
        if (this.f1843f) {
            throw new IllegalStateException("closed");
        }
        e eVar = this.f1842e;
        long j4 = eVar.f1822e;
        if (j4 == 0) {
            j4 = 0;
        } else {
            p pVar = eVar.f1821d;
            j2.i.b(pVar);
            p pVar2 = pVar.f1853g;
            j2.i.b(pVar2);
            int i4 = pVar2.f1849c;
            if (i4 < 8192 && pVar2.f1851e) {
                j4 -= (long) (i4 - pVar2.f1848b);
            }
        }
        if (j4 > 0) {
            this.f1841d.R(j4, eVar);
        }
        return this;
    }

    @Override // q3.s, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        s sVar = this.f1841d;
        if (this.f1843f) {
            return;
        }
        e eVar = this.f1842e;
        long j4 = eVar.f1822e;
        if (j4 > 0) {
            sVar.R(j4, eVar);
        }
        th = null;
        try {
            sVar.close();
        } catch (Throwable th) {
            if (th == null) {
                th = th;
            }
        }
        this.f1843f = true;
        if (th != null) {
            throw th;
        }
    }

    @Override // q3.s
    public final w f() {
        return this.f1841d.f();
    }

    @Override // q3.f, q3.s, java.io.Flushable
    public final void flush() {
        if (this.f1843f) {
            throw new IllegalStateException("closed");
        }
        e eVar = this.f1842e;
        long j4 = eVar.f1822e;
        s sVar = this.f1841d;
        if (j4 > 0) {
            sVar.R(j4, eVar);
        }
        sVar.flush();
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.f1843f;
    }

    @Override // q3.f
    public final f j(h hVar) {
        j2.i.e(hVar, "byteString");
        if (this.f1843f) {
            throw new IllegalStateException("closed");
        }
        this.f1842e.V(hVar);
        b();
        return this;
    }

    public final String toString() {
        return "buffer(" + this.f1841d + ')';
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        j2.i.e(byteBuffer, "source");
        if (this.f1843f) {
            throw new IllegalStateException("closed");
        }
        int iWrite = this.f1842e.write(byteBuffer);
        b();
        return iWrite;
    }

    @Override // q3.f
    public final f writeByte(int i4) {
        if (this.f1843f) {
            throw new IllegalStateException("closed");
        }
        this.f1842e.X(i4);
        b();
        return this;
    }

    @Override // q3.f
    public final f writeInt(int i4) {
        if (this.f1843f) {
            throw new IllegalStateException("closed");
        }
        this.f1842e.a0(i4);
        b();
        return this;
    }

    @Override // q3.f
    public final f writeShort(int i4) {
        if (this.f1843f) {
            throw new IllegalStateException("closed");
        }
        this.f1842e.b0(i4);
        b();
        return this;
    }

    @Override // q3.f
    public final f write(byte[] bArr) {
        if (!this.f1843f) {
            this.f1842e.U(bArr.length, bArr);
            b();
            return this;
        }
        throw new IllegalStateException("closed");
    }
}
