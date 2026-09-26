package e;

import com.umeng.commonsdk.proguard.ap;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: Buffer.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements e, d, Cloneable, ByteChannel {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final byte[] f4726c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    o f4727a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long f4728b;

    /* JADX INFO: compiled from: Buffer.java */
    class a extends OutputStream {
        a() {
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
        }

        public String toString() {
            return c.this + ".outputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            c.this.writeByte((int) ((byte) i));
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            c.this.write(bArr, i, i2);
        }
    }

    /* JADX INFO: compiled from: Buffer.java */
    class b extends InputStream {
        b() {
        }

        @Override // java.io.InputStream
        public int available() {
            return (int) Math.min(c.this.f4728b, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.InputStream
        public int read() {
            c cVar = c.this;
            if (cVar.f4728b > 0) {
                return cVar.readByte() & 255;
            }
            return -1;
        }

        public String toString() {
            return c.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            return c.this.a(bArr, i, i2);
        }
    }

    @Override // e.d
    public /* bridge */ /* synthetic */ d a(f fVar) {
        a(fVar);
        return this;
    }

    @Override // e.d
    public /* bridge */ /* synthetic */ d b(String str) {
        b(str);
        return this;
    }

    @Override // e.e, e.d
    public c c() {
        return this;
    }

    @Override // e.e
    public boolean c(long j) {
        return this.f4728b >= j;
    }

    @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // e.d
    public /* bridge */ /* synthetic */ d d(long j) {
        d(j);
        return this;
    }

    @Override // e.d
    public c e() {
        return this;
    }

    @Override // e.d
    public /* bridge */ /* synthetic */ d e() {
        e();
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        long j = this.f4728b;
        if (j != cVar.f4728b) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        o oVar = this.f4727a;
        o oVar2 = cVar.f4727a;
        int i = oVar.f4760b;
        int i2 = oVar2.f4760b;
        while (j2 < this.f4728b) {
            long jMin = Math.min(oVar.f4761c - i, oVar2.f4761c - i2);
            int i3 = i2;
            int i4 = i;
            int i5 = 0;
            while (i5 < jMin) {
                int i6 = i4 + 1;
                int i7 = i3 + 1;
                if (oVar.f4759a[i4] != oVar2.f4759a[i3]) {
                    return false;
                }
                i5++;
                i4 = i6;
                i3 = i7;
            }
            if (i4 == oVar.f4761c) {
                oVar = oVar.f4764f;
                i = oVar.f4760b;
            } else {
                i = i4;
            }
            if (i3 == oVar2.f4761c) {
                oVar2 = oVar2.f4764f;
                i2 = oVar2.f4760b;
            } else {
                i2 = i3;
            }
            j2 += jMin;
        }
        return true;
    }

    @Override // e.e
    public long f() {
        long j = 0;
        if (this.f4728b == 0) {
            throw new IllegalStateException("size == 0");
        }
        int i = 0;
        long j2 = -7;
        boolean z = false;
        boolean z2 = false;
        do {
            o oVar = this.f4727a;
            byte[] bArr = oVar.f4759a;
            int i2 = oVar.f4760b;
            int i3 = oVar.f4761c;
            while (i2 < i3) {
                byte b2 = bArr[i2];
                if (b2 >= 48 && b2 <= 57) {
                    int i4 = 48 - b2;
                    if (j < -922337203685477580L || (j == -922337203685477580L && i4 < j2)) {
                        c cVar = new c();
                        cVar.h(j);
                        cVar.writeByte((int) b2);
                        if (!z) {
                            cVar.readByte();
                        }
                        throw new NumberFormatException("Number too large: " + cVar.o());
                    }
                    j = (j * 10) + ((long) i4);
                } else {
                    if (b2 != 45 || i != 0) {
                        if (i != 0) {
                            z2 = true;
                            break;
                        }
                        throw new NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + Integer.toHexString(b2));
                    }
                    j2--;
                    z = true;
                }
                i2++;
                i++;
            }
            if (i2 == i3) {
                this.f4727a = oVar.b();
                p.a(oVar);
            } else {
                oVar.f4760b = i2;
            }
            if (z2) {
                break;
            }
        } while (this.f4727a != null);
        this.f4728b -= (long) i;
        return z ? j : -j;
    }

    @Override // e.d, e.r, java.io.Flushable
    public void flush() {
    }

    @Override // e.e
    public void g(long j) throws EOFException {
        if (this.f4728b < j) {
            throw new EOFException();
        }
    }

    @Override // e.d
    public /* bridge */ /* synthetic */ d h(long j) {
        h(j);
        return this;
    }

    public int hashCode() {
        o oVar = this.f4727a;
        if (oVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = oVar.f4761c;
            for (int i3 = oVar.f4760b; i3 < i2; i3++) {
                i = (i * 31) + oVar.f4759a[i3];
            }
            oVar = oVar.f4764f;
        } while (oVar != this.f4727a);
        return i;
    }

    @Override // e.e
    public int i() {
        return u.a(readInt());
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // e.e
    public boolean j() {
        return this.f4728b == 0;
    }

    @Override // e.e
    public long k() {
        int i;
        int i2;
        if (this.f4728b == 0) {
            throw new IllegalStateException("size == 0");
        }
        boolean z = false;
        long j = 0;
        int i3 = 0;
        do {
            o oVar = this.f4727a;
            byte[] bArr = oVar.f4759a;
            int i4 = oVar.f4760b;
            int i5 = oVar.f4761c;
            while (i4 < i5) {
                byte b2 = bArr[i4];
                if (b2 < 48 || b2 > 57) {
                    if (b2 >= 97 && b2 <= 102) {
                        i = b2 - 97;
                    } else {
                        if (b2 < 65 || b2 > 70) {
                            if (i3 != 0) {
                                z = true;
                                break;
                            }
                            throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b2));
                        }
                        i = b2 - 65;
                    }
                    i2 = i + 10;
                } else {
                    i2 = b2 - 48;
                }
                if (((-1152921504606846976L) & j) != 0) {
                    c cVar = new c();
                    cVar.d(j);
                    cVar.writeByte((int) b2);
                    throw new NumberFormatException("Number too large: " + cVar.o());
                }
                j = (j << 4) | ((long) i2);
                i4++;
                i3++;
            }
            if (i4 == i5) {
                this.f4727a = oVar.b();
                p.a(oVar);
            } else {
                oVar.f4760b = i4;
            }
            if (z) {
                break;
            }
        } while (this.f4727a != null);
        this.f4728b -= (long) i3;
        return j;
    }

    @Override // e.e
    public InputStream l() {
        return new b();
    }

    public OutputStream m() {
        return new a();
    }

    public f n() {
        return new f(h());
    }

    public String o() {
        try {
            return a(this.f4728b, u.f4772a);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public int p() throws EOFException {
        int i;
        int i2;
        int i3;
        if (this.f4728b == 0) {
            throw new EOFException();
        }
        byte bA = a(0L);
        if ((bA & 128) == 0) {
            i = bA & 127;
            i2 = 1;
            i3 = 0;
        } else if ((bA & 224) == 192) {
            i = bA & 31;
            i2 = 2;
            i3 = 128;
        } else if ((bA & 240) == 224) {
            i = bA & ap.m;
            i2 = 3;
            i3 = 2048;
        } else {
            if ((bA & 248) != 240) {
                skip(1L);
                return 65533;
            }
            i = bA & 7;
            i2 = 4;
            i3 = 65536;
        }
        long j = i2;
        if (this.f4728b < j) {
            throw new EOFException("size < " + i2 + ": " + this.f4728b + " (to read code point prefixed 0x" + Integer.toHexString(bA) + ")");
        }
        for (int i4 = 1; i4 < i2; i4++) {
            long j2 = i4;
            byte bA2 = a(j2);
            if ((bA2 & 192) != 128) {
                skip(j2);
                return 65533;
            }
            i = (i << 6) | (bA2 & 63);
        }
        skip(j);
        if (i > 1114111) {
            return 65533;
        }
        if ((i < 55296 || i > 57343) && i >= i3) {
            return i;
        }
        return 65533;
    }

    public long q() {
        return this.f4728b;
    }

    public f r() {
        long j = this.f4728b;
        if (j <= 2147483647L) {
            return a((int) j);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f4728b);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        o oVar = this.f4727a;
        if (oVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), oVar.f4761c - oVar.f4760b);
        byteBuffer.put(oVar.f4759a, oVar.f4760b, iMin);
        oVar.f4760b += iMin;
        this.f4728b -= (long) iMin;
        if (oVar.f4760b == oVar.f4761c) {
            this.f4727a = oVar.b();
            p.a(oVar);
        }
        return iMin;
    }

    @Override // e.e
    public byte readByte() {
        long j = this.f4728b;
        if (j == 0) {
            throw new IllegalStateException("size == 0");
        }
        o oVar = this.f4727a;
        int i = oVar.f4760b;
        int i2 = oVar.f4761c;
        int i3 = i + 1;
        byte b2 = oVar.f4759a[i];
        this.f4728b = j - 1;
        if (i3 == i2) {
            this.f4727a = oVar.b();
            p.a(oVar);
        } else {
            oVar.f4760b = i3;
        }
        return b2;
    }

    @Override // e.e
    public void readFully(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int iA = a(bArr, i, bArr.length - i);
            if (iA == -1) {
                throw new EOFException();
            }
            i += iA;
        }
    }

    @Override // e.e
    public int readInt() {
        long j = this.f4728b;
        if (j < 4) {
            throw new IllegalStateException("size < 4: " + this.f4728b);
        }
        o oVar = this.f4727a;
        int i = oVar.f4760b;
        int i2 = oVar.f4761c;
        if (i2 - i < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = oVar.f4759a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        int i8 = i6 + 1;
        int i9 = i7 | (bArr[i6] & 255);
        this.f4728b = j - 4;
        if (i8 == i2) {
            this.f4727a = oVar.b();
            p.a(oVar);
        } else {
            oVar.f4760b = i8;
        }
        return i9;
    }

    @Override // e.e
    public short readShort() {
        long j = this.f4728b;
        if (j < 2) {
            throw new IllegalStateException("size < 2: " + this.f4728b);
        }
        o oVar = this.f4727a;
        int i = oVar.f4760b;
        int i2 = oVar.f4761c;
        if (i2 - i < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = oVar.f4759a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.f4728b = j - 2;
        if (i4 == i2) {
            this.f4727a = oVar.b();
            p.a(oVar);
        } else {
            oVar.f4760b = i4;
        }
        return (short) i5;
    }

    @Override // e.e
    public void skip(long j) throws EOFException {
        while (j > 0) {
            o oVar = this.f4727a;
            if (oVar == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, oVar.f4761c - oVar.f4760b);
            long j2 = iMin;
            this.f4728b -= j2;
            j -= j2;
            o oVar2 = this.f4727a;
            oVar2.f4760b += iMin;
            if (oVar2.f4760b == oVar2.f4761c) {
                this.f4727a = oVar2.b();
                p.a(oVar2);
            }
        }
    }

    @Override // e.s
    public t timeout() {
        return t.f4768d;
    }

    public String toString() {
        return r().toString();
    }

    @Override // e.d
    public /* bridge */ /* synthetic */ d write(byte[] bArr) {
        write(bArr);
        return this;
    }

    @Override // e.d
    public /* bridge */ /* synthetic */ d writeByte(int i) {
        writeByte(i);
        return this;
    }

    @Override // e.d
    public /* bridge */ /* synthetic */ d writeInt(int i) {
        writeInt(i);
        return this;
    }

    @Override // e.d
    public /* bridge */ /* synthetic */ d writeShort(int i) {
        writeShort(i);
        return this;
    }

    public c a(c cVar, long j, long j2) {
        if (cVar == null) {
            throw new IllegalArgumentException("out == null");
        }
        u.a(this.f4728b, j, j2);
        if (j2 == 0) {
            return this;
        }
        cVar.f4728b += j2;
        o oVar = this.f4727a;
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
            o oVarC = oVar.c();
            oVarC.f4760b = (int) (((long) oVarC.f4760b) + j);
            oVarC.f4761c = Math.min(oVarC.f4760b + ((int) j2), oVarC.f4761c);
            o oVar2 = cVar.f4727a;
            if (oVar2 == null) {
                oVarC.g = oVarC;
                oVarC.f4764f = oVarC;
                cVar.f4727a = oVarC;
            } else {
                oVar2.g.a(oVarC);
            }
            j2 -= (long) (oVarC.f4761c - oVarC.f4760b);
            oVar = oVar.f4764f;
            j = 0;
        }
        return this;
    }

    public long b() {
        long j = this.f4728b;
        if (j == 0) {
            return 0L;
        }
        o oVar = this.f4727a.g;
        int i = oVar.f4761c;
        return (i >= 8192 || !oVar.f4763e) ? j : j - ((long) (i - oVar.f4760b));
    }

    public c c(int i) {
        if (i < 128) {
            writeByte(i);
        } else if (i < 2048) {
            writeByte((i >> 6) | 192);
            writeByte((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                writeByte((i >> 12) | 224);
                writeByte(((i >> 6) & 63) | 128);
                writeByte((i & 63) | 128);
            } else {
                writeByte(63);
            }
        } else {
            if (i > 1114111) {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
            }
            writeByte((i >> 18) | 240);
            writeByte(((i >> 12) & 63) | 128);
            writeByte(((i >> 6) & 63) | 128);
            writeByte((i & 63) | 128);
        }
        return this;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public c m5clone() {
        c cVar = new c();
        if (this.f4728b == 0) {
            return cVar;
        }
        cVar.f4727a = this.f4727a.c();
        o oVar = cVar.f4727a;
        oVar.g = oVar;
        oVar.f4764f = oVar;
        o oVar2 = this.f4727a;
        while (true) {
            oVar2 = oVar2.f4764f;
            if (oVar2 == this.f4727a) {
                cVar.f4728b = this.f4728b;
                return cVar;
            }
            cVar.f4727a.g.a(oVar2.c());
        }
    }

    @Override // e.e
    public short d() {
        return u.a(readShort());
    }

    @Override // e.e
    public f e(long j) {
        return new f(i(j));
    }

    @Override // e.e
    public String g() {
        return f(Long.MAX_VALUE);
    }

    @Override // e.e
    public byte[] h() {
        try {
            return i(this.f4728b);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // e.e
    public byte[] i(long j) throws EOFException {
        u.a(this.f4728b, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            readFully(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    String j(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (a(j2) == 13) {
                String strB = b(j2);
                skip(2L);
                return strB;
            }
        }
        String strB2 = b(j);
        skip(1L);
        return strB2;
    }

    @Override // e.d
    public /* bridge */ /* synthetic */ d write(byte[] bArr, int i, int i2) {
        write(bArr, i, i2);
        return this;
    }

    @Override // e.d
    public c writeByte(int i) {
        o oVarB = b(1);
        byte[] bArr = oVarB.f4759a;
        int i2 = oVarB.f4761c;
        oVarB.f4761c = i2 + 1;
        bArr[i2] = (byte) i;
        this.f4728b++;
        return this;
    }

    @Override // e.d
    public c writeInt(int i) {
        o oVarB = b(4);
        byte[] bArr = oVarB.f4759a;
        int i2 = oVarB.f4761c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        oVarB.f4761c = i5 + 1;
        this.f4728b += 4;
        return this;
    }

    @Override // e.d
    public c writeShort(int i) {
        o oVarB = b(2);
        byte[] bArr = oVarB.f4759a;
        int i2 = oVarB.f4761c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        oVarB.f4761c = i3 + 1;
        this.f4728b += 2;
        return this;
    }

    @Override // e.d
    public c d(long j) {
        if (j == 0) {
            writeByte(48);
            return this;
        }
        int iNumberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        o oVarB = b(iNumberOfTrailingZeros);
        byte[] bArr = oVarB.f4759a;
        int i = oVarB.f4761c;
        for (int i2 = (i + iNumberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = f4726c[(int) (15 & j)];
            j >>>= 4;
        }
        oVarB.f4761c += iNumberOfTrailingZeros;
        this.f4728b += (long) iNumberOfTrailingZeros;
        return this;
    }

    @Override // e.d
    public c write(byte[] bArr) {
        if (bArr != null) {
            write(bArr, 0, bArr.length);
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // e.d
    public c h(long j) {
        if (j == 0) {
            writeByte(48);
            return this;
        }
        boolean z = false;
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                b("-9223372036854775808");
                return this;
            }
            z = true;
        }
        if (j < 100000000) {
            if (j < 10000) {
                if (j >= 100) {
                    i = j < 1000 ? 3 : 4;
                } else if (j >= 10) {
                    i = 2;
                }
            } else if (j < 1000000) {
                i = j < 100000 ? 5 : 6;
            } else {
                i = j < 10000000 ? 7 : 8;
            }
        } else if (j < 1000000000000L) {
            if (j < 10000000000L) {
                i = j < 1000000000 ? 9 : 10;
            } else {
                i = j < 100000000000L ? 11 : 12;
            }
        } else if (j < 1000000000000000L) {
            if (j < 10000000000000L) {
                i = 13;
            } else {
                i = j < 100000000000000L ? 14 : 15;
            }
        } else if (j < 100000000000000000L) {
            i = j < 10000000000000000L ? 16 : 17;
        } else {
            i = j < 1000000000000000000L ? 18 : 19;
        }
        if (z) {
            i++;
        }
        o oVarB = b(i);
        byte[] bArr = oVarB.f4759a;
        int i2 = oVarB.f4761c + i;
        while (j != 0) {
            i2--;
            bArr[i2] = f4726c[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        oVarB.f4761c += i;
        this.f4728b += (long) i;
        return this;
    }

    @Override // e.d
    public c write(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = i2;
            u.a(bArr.length, i, j);
            int i3 = i2 + i;
            while (i < i3) {
                o oVarB = b(1);
                int iMin = Math.min(i3 - i, 8192 - oVarB.f4761c);
                System.arraycopy(bArr, i, oVarB.f4759a, oVarB.f4761c, iMin);
                i += iMin;
                oVarB.f4761c += iMin;
            }
            this.f4728b += j;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    public String b(long j) {
        return a(j, u.f4772a);
    }

    @Override // e.d
    public c b(String str) {
        a(str, 0, str.length());
        return this;
    }

    o b(int i) {
        if (i >= 1 && i <= 8192) {
            o oVar = this.f4727a;
            if (oVar == null) {
                this.f4727a = p.a();
                o oVar2 = this.f4727a;
                oVar2.g = oVar2;
                oVar2.f4764f = oVar2;
                return oVar2;
            }
            o oVar3 = oVar.g;
            if (oVar3.f4761c + i <= 8192 && oVar3.f4763e) {
                return oVar3;
            }
            o oVarA = p.a();
            oVar3.a(oVarA);
            return oVarA;
        }
        throw new IllegalArgumentException();
    }

    @Override // e.s
    public long read(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j >= 0) {
            long j2 = this.f4728b;
            if (j2 == 0) {
                return -1L;
            }
            if (j > j2) {
                j = j2;
            }
            cVar.a(this, j);
            return j;
        }
        throw new IllegalArgumentException("byteCount < 0: " + j);
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            int iRemaining = byteBuffer.remaining();
            int i = iRemaining;
            while (i > 0) {
                o oVarB = b(1);
                int iMin = Math.min(i, 8192 - oVarB.f4761c);
                byteBuffer.get(oVarB.f4759a, oVarB.f4761c, iMin);
                i -= iMin;
                oVarB.f4761c += iMin;
            }
            this.f4728b += (long) iRemaining;
            return iRemaining;
        }
        throw new IllegalArgumentException("source == null");
    }

    public byte a(long j) {
        int i;
        u.a(this.f4728b, j, 1L);
        long j2 = this.f4728b;
        if (j2 - j > j) {
            o oVar = this.f4727a;
            while (true) {
                int i2 = oVar.f4761c;
                int i3 = oVar.f4760b;
                long j3 = i2 - i3;
                if (j < j3) {
                    return oVar.f4759a[i3 + ((int) j)];
                }
                j -= j3;
                oVar = oVar.f4764f;
            }
        } else {
            long j4 = j - j2;
            o oVar2 = this.f4727a;
            do {
                oVar2 = oVar2.g;
                int i4 = oVar2.f4761c;
                i = oVar2.f4760b;
                j4 += (long) (i4 - i);
            } while (j4 < 0);
            return oVar2.f4759a[i + ((int) j4)];
        }
    }

    @Override // e.e
    public String f(long j) throws EOFException {
        if (j >= 0) {
            long j2 = j != Long.MAX_VALUE ? j + 1 : Long.MAX_VALUE;
            long jA = a((byte) 10, 0L, j2);
            if (jA != -1) {
                return j(jA);
            }
            if (j2 < q() && a(j2 - 1) == 13 && a(j2) == 10) {
                return j(j2);
            }
            c cVar = new c();
            a(cVar, 0L, Math.min(32L, q()));
            throw new EOFException("\\n not found: limit=" + Math.min(q(), j) + " content=" + cVar.n().b() + (char) 8230);
        }
        throw new IllegalArgumentException("limit < 0: " + j);
    }

    @Override // e.e
    public long a(r rVar) {
        long j = this.f4728b;
        if (j > 0) {
            rVar.a(this, j);
        }
        return j;
    }

    @Override // e.e
    public String a(Charset charset) {
        try {
            return a(this.f4728b, charset);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public String a(long j, Charset charset) {
        u.a(this.f4728b, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        o oVar = this.f4727a;
        int i = oVar.f4760b;
        if (((long) i) + j > oVar.f4761c) {
            return new String(i(j), charset);
        }
        String str = new String(oVar.f4759a, i, (int) j, charset);
        oVar.f4760b = (int) (((long) oVar.f4760b) + j);
        this.f4728b -= j;
        if (oVar.f4760b == oVar.f4761c) {
            this.f4727a = oVar.b();
            p.a(oVar);
        }
        return str;
    }

    public int a(byte[] bArr, int i, int i2) {
        u.a(bArr.length, i, i2);
        o oVar = this.f4727a;
        if (oVar == null) {
            return -1;
        }
        int iMin = Math.min(i2, oVar.f4761c - oVar.f4760b);
        System.arraycopy(oVar.f4759a, oVar.f4760b, bArr, i, iMin);
        oVar.f4760b += iMin;
        this.f4728b -= (long) iMin;
        if (oVar.f4760b == oVar.f4761c) {
            this.f4727a = oVar.b();
            p.a(oVar);
        }
        return iMin;
    }

    public void a() {
        try {
            skip(this.f4728b);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // e.d
    public c a(f fVar) {
        if (fVar != null) {
            fVar.a(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    public c a(String str, int i, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i);
        }
        if (i2 >= i) {
            if (i2 > str.length()) {
                throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
            }
            while (i < i2) {
                char cCharAt = str.charAt(i);
                if (cCharAt < 128) {
                    o oVarB = b(1);
                    byte[] bArr = oVarB.f4759a;
                    int i3 = oVarB.f4761c - i;
                    int iMin = Math.min(i2, 8192 - i3);
                    int i4 = i + 1;
                    bArr[i + i3] = (byte) cCharAt;
                    while (i4 < iMin) {
                        char cCharAt2 = str.charAt(i4);
                        if (cCharAt2 >= 128) {
                            break;
                        }
                        bArr[i4 + i3] = (byte) cCharAt2;
                        i4++;
                    }
                    int i5 = oVarB.f4761c;
                    int i6 = (i3 + i4) - i5;
                    oVarB.f4761c = i5 + i6;
                    this.f4728b += (long) i6;
                    i = i4;
                } else {
                    if (cCharAt < 2048) {
                        writeByte((cCharAt >> 6) | 192);
                        writeByte((cCharAt & '?') | 128);
                    } else if (cCharAt >= 55296 && cCharAt <= 57343) {
                        int i7 = i + 1;
                        char cCharAt3 = i7 < i2 ? str.charAt(i7) : (char) 0;
                        if (cCharAt <= 56319 && cCharAt3 >= 56320 && cCharAt3 <= 57343) {
                            int i8 = (((cCharAt & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                            writeByte((i8 >> 18) | 240);
                            writeByte(((i8 >> 12) & 63) | 128);
                            writeByte(((i8 >> 6) & 63) | 128);
                            writeByte((i8 & 63) | 128);
                            i += 2;
                        } else {
                            writeByte(63);
                            i = i7;
                        }
                    } else {
                        writeByte((cCharAt >> '\f') | 224);
                        writeByte(((cCharAt >> 6) & 63) | 128);
                        writeByte((cCharAt & '?') | 128);
                    }
                    i++;
                }
            }
            return this;
        }
        throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
    }

    public c a(String str, Charset charset) {
        a(str, 0, str.length(), charset);
        return this;
    }

    public c a(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        }
        if (i2 >= i) {
            if (i2 > str.length()) {
                throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
            }
            if (charset != null) {
                if (charset.equals(u.f4772a)) {
                    a(str, i, i2);
                    return this;
                }
                byte[] bytes = str.substring(i, i2).getBytes(charset);
                write(bytes, 0, bytes.length);
                return this;
            }
            throw new IllegalArgumentException("charset == null");
        }
        throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
    }

    @Override // e.d
    public long a(s sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long j2 = sVar.read(this, 8192L);
            if (j2 == -1) {
                return j;
            }
            j += j2;
        }
    }

    @Override // e.r
    public void a(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (cVar != this) {
            u.a(cVar.f4728b, 0L, j);
            while (j > 0) {
                o oVar = cVar.f4727a;
                if (j < oVar.f4761c - oVar.f4760b) {
                    o oVar2 = this.f4727a;
                    o oVar3 = oVar2 != null ? oVar2.g : null;
                    if (oVar3 != null && oVar3.f4763e) {
                        if ((((long) oVar3.f4761c) + j) - ((long) (oVar3.f4762d ? 0 : oVar3.f4760b)) <= 8192) {
                            cVar.f4727a.a(oVar3, (int) j);
                            cVar.f4728b -= j;
                            this.f4728b += j;
                            return;
                        }
                    }
                    cVar.f4727a = cVar.f4727a.a((int) j);
                }
                o oVar4 = cVar.f4727a;
                long j2 = oVar4.f4761c - oVar4.f4760b;
                cVar.f4727a = oVar4.b();
                o oVar5 = this.f4727a;
                if (oVar5 == null) {
                    this.f4727a = oVar4;
                    o oVar6 = this.f4727a;
                    oVar6.g = oVar6;
                    oVar6.f4764f = oVar6;
                } else {
                    oVar5.g.a(oVar4);
                    oVar4.a();
                }
                cVar.f4728b -= j2;
                this.f4728b += j2;
                j -= j2;
            }
            return;
        }
        throw new IllegalArgumentException("source == this");
    }

    @Override // e.e
    public long a(byte b2) {
        return a(b2, 0L, Long.MAX_VALUE);
    }

    public long a(byte b2, long j, long j2) {
        o oVar;
        long j3 = 0;
        if (j >= 0 && j2 >= j) {
            long j4 = this.f4728b;
            if (j2 <= j4) {
                j4 = j2;
            }
            if (j == j4 || (oVar = this.f4727a) == null) {
                return -1L;
            }
            long j5 = this.f4728b;
            if (j5 - j >= j) {
                while (true) {
                    j5 = j3;
                    j3 = ((long) (oVar.f4761c - oVar.f4760b)) + j5;
                    if (j3 >= j) {
                        break;
                    }
                    oVar = oVar.f4764f;
                }
            } else {
                while (j5 > j) {
                    oVar = oVar.g;
                    j5 -= (long) (oVar.f4761c - oVar.f4760b);
                }
            }
            long j6 = j;
            while (j5 < j4) {
                byte[] bArr = oVar.f4759a;
                int iMin = (int) Math.min(oVar.f4761c, (((long) oVar.f4760b) + j4) - j5);
                for (int i = (int) ((((long) oVar.f4760b) + j6) - j5); i < iMin; i++) {
                    if (bArr[i] == b2) {
                        return ((long) (i - oVar.f4760b)) + j5;
                    }
                }
                j6 = ((long) (oVar.f4761c - oVar.f4760b)) + j5;
                oVar = oVar.f4764f;
                j5 = j6;
            }
            return -1L;
        }
        throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.f4728b), Long.valueOf(j), Long.valueOf(j2)));
    }

    @Override // e.e
    public boolean a(long j, f fVar) {
        return a(j, fVar, 0, fVar.f());
    }

    public boolean a(long j, f fVar, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.f4728b - j < i2 || fVar.f() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (a(((long) i3) + j) != fVar.a(i + i3)) {
                return false;
            }
        }
        return true;
    }

    public f a(int i) {
        if (i == 0) {
            return f.f4732e;
        }
        return new q(this, i);
    }
}
