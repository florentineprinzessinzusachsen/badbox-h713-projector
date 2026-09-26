package e;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: RealBufferedSink.java */
/* JADX INFO: loaded from: classes.dex */
final class m implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f4752a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f4753b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f4754c;

    m(r rVar) {
        if (rVar == null) {
            throw new NullPointerException("sink == null");
        }
        this.f4753b = rVar;
    }

    @Override // e.r
    public void a(c cVar, long j) {
        if (this.f4754c) {
            throw new IllegalStateException("closed");
        }
        this.f4752a.a(cVar, j);
        e();
    }

    @Override // e.d
    public d b(String str) {
        if (this.f4754c) {
            throw new IllegalStateException("closed");
        }
        this.f4752a.b(str);
        return e();
    }

    @Override // e.d
    public c c() {
        return this.f4752a;
    }

    @Override // e.r, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.f4754c) {
            return;
        }
        if (this.f4752a.f4728b > 0) {
            this.f4753b.a(this.f4752a, this.f4752a.f4728b);
        }
        th = null;
        try {
            this.f4753b.close();
        } catch (Throwable th) {
            if (th == null) {
                th = th;
            }
        }
        this.f4754c = true;
        if (th == null) {
            return;
        }
        u.a(th);
        throw null;
    }

    @Override // e.d
    public d d(long j) {
        if (this.f4754c) {
            throw new IllegalStateException("closed");
        }
        this.f4752a.d(j);
        return e();
    }

    @Override // e.d
    public d e() {
        if (this.f4754c) {
            throw new IllegalStateException("closed");
        }
        long jB = this.f4752a.b();
        if (jB > 0) {
            this.f4753b.a(this.f4752a, jB);
        }
        return this;
    }

    @Override // e.d, e.r, java.io.Flushable
    public void flush() {
        if (this.f4754c) {
            throw new IllegalStateException("closed");
        }
        c cVar = this.f4752a;
        long j = cVar.f4728b;
        if (j > 0) {
            this.f4753b.a(cVar, j);
        }
        this.f4753b.flush();
    }

    @Override // e.d
    public d h(long j) {
        if (this.f4754c) {
            throw new IllegalStateException("closed");
        }
        this.f4752a.h(j);
        e();
        return this;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f4754c;
    }

    @Override // e.r
    public t timeout() {
        return this.f4753b.timeout();
    }

    public String toString() {
        return "buffer(" + this.f4753b + ")";
    }

    @Override // e.d
    public d write(byte[] bArr) {
        if (this.f4754c) {
            throw new IllegalStateException("closed");
        }
        this.f4752a.write(bArr);
        e();
        return this;
    }

    @Override // e.d
    public d writeByte(int i) {
        if (this.f4754c) {
            throw new IllegalStateException("closed");
        }
        this.f4752a.writeByte(i);
        return e();
    }

    @Override // e.d
    public d writeInt(int i) {
        if (this.f4754c) {
            throw new IllegalStateException("closed");
        }
        this.f4752a.writeInt(i);
        return e();
    }

    @Override // e.d
    public d writeShort(int i) {
        if (this.f4754c) {
            throw new IllegalStateException("closed");
        }
        this.f4752a.writeShort(i);
        e();
        return this;
    }

    @Override // e.d
    public d a(f fVar) {
        if (!this.f4754c) {
            this.f4752a.a(fVar);
            e();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // e.d
    public d write(byte[] bArr, int i, int i2) {
        if (!this.f4754c) {
            this.f4752a.write(bArr, i, i2);
            e();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // e.d
    public long a(s sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long j2 = sVar.read(this.f4752a, 8192L);
            if (j2 == -1) {
                return j;
            }
            j += j2;
            e();
        }
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (!this.f4754c) {
            int iWrite = this.f4752a.write(byteBuffer);
            e();
            return iWrite;
        }
        throw new IllegalStateException("closed");
    }
}
