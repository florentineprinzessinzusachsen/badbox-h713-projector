package e;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: RealBufferedSource.java */
/* JADX INFO: loaded from: classes.dex */
final class n implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f4755a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f4756b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f4757c;

    n(s sVar) {
        if (sVar == null) {
            throw new NullPointerException("source == null");
        }
        this.f4756b = sVar;
    }

    @Override // e.e
    public long a(r rVar) {
        if (rVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long j = 0;
        while (this.f4756b.read(this.f4755a, 8192L) != -1) {
            long jB = this.f4755a.b();
            if (jB > 0) {
                j += jB;
                rVar.a(this.f4755a, jB);
            }
        }
        if (this.f4755a.q() <= 0) {
            return j;
        }
        long jQ = j + this.f4755a.q();
        c cVar = this.f4755a;
        rVar.a(cVar, cVar.q());
        return jQ;
    }

    @Override // e.e, e.d
    public c c() {
        return this.f4755a;
    }

    @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f4757c) {
            return;
        }
        this.f4757c = true;
        this.f4756b.close();
        this.f4755a.a();
    }

    @Override // e.e
    public short d() throws EOFException {
        g(2L);
        return this.f4755a.d();
    }

    @Override // e.e
    public f e(long j) throws EOFException {
        g(j);
        return this.f4755a.e(j);
    }

    @Override // e.e
    public String f(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long jA = a((byte) 10, 0L, j2);
        if (jA != -1) {
            return this.f4755a.j(jA);
        }
        if (j2 < Long.MAX_VALUE && c(j2) && this.f4755a.a(j2 - 1) == 13 && c(1 + j2) && this.f4755a.a(j2) == 10) {
            return this.f4755a.j(j2);
        }
        c cVar = new c();
        c cVar2 = this.f4755a;
        cVar2.a(cVar, 0L, Math.min(32L, cVar2.q()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f4755a.q(), j) + " content=" + cVar.n().b() + (char) 8230);
    }

    @Override // e.e
    public void g(long j) throws EOFException {
        if (!c(j)) {
            throw new EOFException();
        }
    }

    @Override // e.e
    public byte[] h() {
        this.f4755a.a(this.f4756b);
        return this.f4755a.h();
    }

    @Override // e.e
    public byte[] i(long j) throws EOFException {
        g(j);
        return this.f4755a.i(j);
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f4757c;
    }

    @Override // e.e
    public boolean j() {
        if (this.f4757c) {
            throw new IllegalStateException("closed");
        }
        return this.f4755a.j() && this.f4756b.read(this.f4755a, 8192L) == -1;
    }

    @Override // e.e
    public long k() throws EOFException {
        g(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!c(i2)) {
                break;
            }
            byte bA = this.f4755a.a(i);
            if ((bA < 48 || bA > 57) && ((bA < 97 || bA > 102) && (bA < 65 || bA > 70))) {
                if (i != 0) {
                    break;
                }
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(bA)));
            }
            i = i2;
        }
        return this.f4755a.k();
    }

    @Override // e.e
    public InputStream l() {
        return new a();
    }

    @Override // e.s
    public long read(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f4757c) {
            throw new IllegalStateException("closed");
        }
        c cVar2 = this.f4755a;
        if (cVar2.f4728b == 0 && this.f4756b.read(cVar2, 8192L) == -1) {
            return -1L;
        }
        return this.f4755a.read(cVar, Math.min(j, this.f4755a.f4728b));
    }

    @Override // e.e
    public byte readByte() throws EOFException {
        g(1L);
        return this.f4755a.readByte();
    }

    @Override // e.e
    public void readFully(byte[] bArr) throws EOFException {
        try {
            g(bArr.length);
            this.f4755a.readFully(bArr);
        } catch (EOFException e2) {
            int i = 0;
            while (true) {
                c cVar = this.f4755a;
                long j = cVar.f4728b;
                if (j <= 0) {
                    throw e2;
                }
                int iA = cVar.a(bArr, i, (int) j);
                if (iA == -1) {
                    throw new AssertionError();
                }
                i += iA;
            }
        }
    }

    @Override // e.e
    public int readInt() throws EOFException {
        g(4L);
        return this.f4755a.readInt();
    }

    @Override // e.e
    public short readShort() throws EOFException {
        g(2L);
        return this.f4755a.readShort();
    }

    @Override // e.e
    public void skip(long j) throws EOFException {
        if (this.f4757c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            c cVar = this.f4755a;
            if (cVar.f4728b == 0 && this.f4756b.read(cVar, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.f4755a.q());
            this.f4755a.skip(jMin);
            j -= jMin;
        }
    }

    @Override // e.s
    public t timeout() {
        return this.f4756b.timeout();
    }

    public String toString() {
        return "buffer(" + this.f4756b + ")";
    }

    @Override // e.e
    public boolean c(long j) {
        c cVar;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f4757c) {
            throw new IllegalStateException("closed");
        }
        do {
            cVar = this.f4755a;
            if (cVar.f4728b >= j) {
                return true;
            }
        } while (this.f4756b.read(cVar, 8192L) != -1);
        return false;
    }

    @Override // e.e
    public String g() {
        return f(Long.MAX_VALUE);
    }

    @Override // e.e
    public int i() throws EOFException {
        g(4L);
        return this.f4755a.i();
    }

    /* JADX INFO: compiled from: RealBufferedSource.java */
    class a extends InputStream {
        a() {
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            n nVar = n.this;
            if (nVar.f4757c) {
                throw new IOException("closed");
            }
            return (int) Math.min(nVar.f4755a.f4728b, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            n.this.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            n nVar = n.this;
            if (nVar.f4757c) {
                throw new IOException("closed");
            }
            c cVar = nVar.f4755a;
            if (cVar.f4728b == 0 && nVar.f4756b.read(cVar, 8192L) == -1) {
                return -1;
            }
            return n.this.f4755a.readByte() & 255;
        }

        public String toString() {
            return n.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (!n.this.f4757c) {
                u.a(bArr.length, i, i2);
                n nVar = n.this;
                c cVar = nVar.f4755a;
                if (cVar.f4728b == 0 && nVar.f4756b.read(cVar, 8192L) == -1) {
                    return -1;
                }
                return n.this.f4755a.a(bArr, i, i2);
            }
            throw new IOException("closed");
        }
    }

    @Override // e.e
    public String a(Charset charset) {
        if (charset != null) {
            this.f4755a.a(this.f4756b);
            return this.f4755a.a(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        c cVar = this.f4755a;
        if (cVar.f4728b == 0 && this.f4756b.read(cVar, 8192L) == -1) {
            return -1;
        }
        return this.f4755a.read(byteBuffer);
    }

    @Override // e.e
    public long a(byte b2) {
        return a(b2, 0L, Long.MAX_VALUE);
    }

    @Override // e.e
    public long f() throws EOFException {
        g(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!c(i2)) {
                break;
            }
            byte bA = this.f4755a.a(i);
            if ((bA < 48 || bA > 57) && !(i == 0 && bA == 45)) {
                if (i != 0) {
                    break;
                }
                throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(bA)));
            }
            i = i2;
        }
        return this.f4755a.f();
    }

    public long a(byte b2, long j, long j2) {
        if (this.f4757c) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        }
        while (j < j2) {
            long jA = this.f4755a.a(b2, j, j2);
            if (jA != -1) {
                return jA;
            }
            c cVar = this.f4755a;
            long j3 = cVar.f4728b;
            if (j3 >= j2 || this.f4756b.read(cVar, 8192L) == -1) {
                break;
            }
            j = Math.max(j, j3);
        }
        return -1L;
    }

    @Override // e.e
    public boolean a(long j, f fVar) {
        return a(j, fVar, 0, fVar.f());
    }

    public boolean a(long j, f fVar, int i, int i2) {
        if (!this.f4757c) {
            if (j < 0 || i < 0 || i2 < 0 || fVar.f() - i < i2) {
                return false;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                long j2 = ((long) i3) + j;
                if (!c(1 + j2) || this.f4755a.a(j2) != fVar.a(i + i3)) {
                    return false;
                }
            }
            return true;
        }
        throw new IllegalStateException("closed");
    }
}
