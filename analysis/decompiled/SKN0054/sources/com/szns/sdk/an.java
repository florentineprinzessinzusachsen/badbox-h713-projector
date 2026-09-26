package com.szns.sdk;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class an implements ao, ap, Cloneable, ByteChannel {
    private static final byte[] c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    @Nullable
    ax a;
    long b;

    private String a(long j, Charset charset) {
        be.a(this.b, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: ".concat(String.valueOf(j)));
        }
        if (j == 0) {
            return "";
        }
        ax axVar = this.a;
        if (((long) axVar.b) + j > axVar.c) {
            return new String(c(j), charset);
        }
        String str = new String(axVar.a, axVar.b, (int) j, charset);
        axVar.b = (int) (((long) axVar.b) + j);
        this.b -= j;
        if (axVar.b == axVar.c) {
            this.a = axVar.b();
            ay.a(axVar);
        }
        return str;
    }

    private void c(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int iA = a(bArr, i, bArr.length - i);
            if (iA == -1) {
                throw new EOFException();
            }
            i += iA;
        }
    }

    private byte[] c(long j) throws EOFException {
        be.a(this.b, 0L, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: ".concat(String.valueOf(j)));
        }
        byte[] bArr = new byte[(int) j];
        c(bArr);
        return bArr;
    }

    @Override // com.szns.sdk.ap
    public final int a(byte[] bArr, int i, int i2) {
        be.a(bArr.length, i, i2);
        ax axVar = this.a;
        if (axVar == null) {
            return -1;
        }
        int iMin = Math.min(i2, axVar.c - axVar.b);
        System.arraycopy(axVar.a, axVar.b, bArr, i, iMin);
        axVar.b += iMin;
        this.b -= (long) iMin;
        if (axVar.b == axVar.c) {
            this.a = axVar.b();
            ay.a(axVar);
        }
        return iMin;
    }

    public final long a() {
        return this.b;
    }

    public final long a(long j, long j2) {
        ax axVar;
        long j3 = 0;
        if (j < 0 || Long.MAX_VALUE < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.b), Long.valueOf(j), Long.MAX_VALUE));
        }
        long j4 = this.b;
        if (Long.MAX_VALUE > j4) {
            j2 = j4;
        }
        if (j == j2 || (axVar = this.a) == null) {
            return -1L;
        }
        if (j4 - j < j) {
            while (j4 > j) {
                axVar = axVar.g;
                j4 -= (long) (axVar.c - axVar.b);
            }
        } else {
            while (true) {
                long j5 = ((long) (axVar.c - axVar.b)) + j3;
                if (j5 >= j) {
                    break;
                }
                axVar = axVar.f;
                j3 = j5;
            }
            j4 = j3;
        }
        while (j4 < j2) {
            byte[] bArr = axVar.a;
            int iMin = (int) Math.min(axVar.c, (((long) axVar.b) + j2) - j4);
            for (int i = (int) ((((long) axVar.b) + j) - j4); i < iMin; i++) {
                if (bArr[i] == 95) {
                    return ((long) (i - axVar.b)) + j4;
                }
            }
            j4 += (long) (axVar.c - axVar.b);
            axVar = axVar.f;
            j = j4;
        }
        return -1L;
    }

    @Override // com.szns.sdk.ao
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final an b(byte[] bArr) {
        if (bArr != null) {
            return b(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // com.szns.sdk.ao
    public final /* synthetic */ ao a(byte[] bArr, int i) {
        return b(bArr, 0, i);
    }

    @Override // com.szns.sdk.ap
    public final String a(long j) {
        return a(j, be.a);
    }

    @Override // com.szns.sdk.ba
    public final void a(an anVar, long j) {
        ax axVarA;
        if (anVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (anVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        be.a(anVar.b, 0L, j);
        while (j > 0) {
            if (j < anVar.a.c - anVar.a.b) {
                ax axVar = this.a;
                ax axVar2 = axVar != null ? axVar.g : null;
                if (axVar2 != null && axVar2.e) {
                    if ((((long) axVar2.c) + j) - ((long) (axVar2.d ? 0 : axVar2.b)) <= 8192) {
                        anVar.a.a(axVar2, (int) j);
                        anVar.b -= j;
                        this.b += j;
                        return;
                    }
                }
                ax axVar3 = anVar.a;
                int i = (int) j;
                if (i <= 0 || i > axVar3.c - axVar3.b) {
                    throw new IllegalArgumentException();
                }
                if (i >= 1024) {
                    axVarA = axVar3.a();
                } else {
                    axVarA = ay.a();
                    System.arraycopy(axVar3.a, axVar3.b, axVarA.a, 0, i);
                }
                axVarA.c = axVarA.b + i;
                axVar3.b += i;
                axVar3.g.a(axVarA);
                anVar.a = axVarA;
            }
            ax axVar4 = anVar.a;
            long j2 = axVar4.c - axVar4.b;
            anVar.a = axVar4.b();
            ax axVar5 = this.a;
            if (axVar5 == null) {
                this.a = axVar4;
                axVar4.g = axVar4;
                axVar4.f = axVar4;
            } else {
                ax axVarA2 = axVar5.g.a(axVar4);
                if (axVarA2.g == axVarA2) {
                    throw new IllegalStateException();
                }
                if (axVarA2.g.e) {
                    int i2 = axVarA2.c - axVarA2.b;
                    if (i2 <= (8192 - axVarA2.g.c) + (axVarA2.g.d ? 0 : axVarA2.g.b)) {
                        axVarA2.a(axVarA2.g, i2);
                        axVarA2.b();
                        ay.a(axVarA2);
                    }
                }
            }
            anVar.b -= j2;
            this.b += j2;
            j -= j2;
        }
    }

    @Override // com.szns.sdk.bb
    public final long a_(an anVar, long j) {
        if (anVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: ".concat(String.valueOf(j)));
        }
        long j2 = this.b;
        if (j2 == 0) {
            return -1L;
        }
        if (j > j2) {
            j = j2;
        }
        anVar.a(this, j);
        return j;
    }

    @Override // com.szns.sdk.ap
    public final an b() {
        return this;
    }

    public final an b(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = i2;
        be.a(bArr.length, 0L, j);
        int i3 = i2 + 0;
        while (i < i3) {
            ax axVarG = g();
            int iMin = Math.min(i3 - i, 8192 - axVarG.c);
            System.arraycopy(bArr, i, axVarG.a, axVarG.c, iMin);
            i += iMin;
            axVarG.c += iMin;
        }
        this.b += j;
        return this;
    }

    @Override // com.szns.sdk.ap
    public final void b(long j) throws EOFException {
        while (j > 0) {
            ax axVar = this.a;
            if (axVar == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, axVar.c - this.a.b);
            long j2 = iMin;
            this.b -= j2;
            j -= j2;
            this.a.b += iMin;
            if (this.a.b == this.a.c) {
                ax axVar2 = this.a;
                this.a = axVar2.b();
                ay.a(axVar2);
            }
        }
    }

    @Override // com.szns.sdk.ap
    public final boolean c() {
        return this.b == 0;
    }

    public final /* synthetic */ Object clone() {
        an anVar = new an();
        if (this.b == 0) {
            return anVar;
        }
        ax axVarA = this.a.a();
        anVar.a = axVarA;
        axVarA.g = axVarA;
        axVarA.f = axVarA;
        ax axVar = this.a;
        while (true) {
            axVar = axVar.f;
            if (axVar == this.a) {
                anVar.b = this.b;
                return anVar;
            }
            anVar.a.g.a(axVar.a());
        }
    }

    @Override // com.szns.sdk.ba, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    public final long d() {
        long j = this.b;
        if (j == 0) {
            return 0L;
        }
        ax axVar = this.a.g;
        return (axVar.c >= 8192 || !axVar.e) ? j : j - ((long) (axVar.c - axVar.b));
    }

    public final String e() {
        try {
            return a(this.b, be.a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof an)) {
            return false;
        }
        an anVar = (an) obj;
        long j = this.b;
        if (j != anVar.b) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        ax axVar = this.a;
        ax axVar2 = anVar.a;
        int i = axVar.b;
        int i2 = axVar2.b;
        while (j2 < this.b) {
            long jMin = Math.min(axVar.c - i, axVar2.c - i2);
            int i3 = 0;
            while (i3 < jMin) {
                int i4 = i + 1;
                int i5 = i2 + 1;
                if (axVar.a[i] != axVar2.a[i2]) {
                    return false;
                }
                i3++;
                i = i4;
                i2 = i5;
            }
            if (i == axVar.c) {
                axVar = axVar.f;
                i = axVar.b;
            }
            if (i2 == axVar2.c) {
                axVar2 = axVar2.f;
                i2 = axVar2.b;
            }
            j2 += jMin;
        }
        return true;
    }

    public final void f() {
        try {
            b(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.szns.sdk.ao, com.szns.sdk.ba, java.io.Flushable
    public final void flush() {
    }

    final ax g() {
        ax axVar = this.a;
        if (axVar != null) {
            ax axVar2 = axVar.g;
            return (axVar2.c + 1 > 8192 || !axVar2.e) ? axVar2.a(ay.a()) : axVar2;
        }
        ax axVarA = ay.a();
        this.a = axVarA;
        axVarA.g = axVarA;
        axVarA.f = axVarA;
        return axVarA;
    }

    @Override // com.szns.sdk.ap
    public final long h() {
        return a(0L, Long.MAX_VALUE);
    }

    public final int hashCode() {
        ax axVar = this.a;
        if (axVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = axVar.c;
            for (int i3 = axVar.b; i3 < i2; i3++) {
                i = (i * 31) + axVar.a[i3];
            }
            axVar = axVar.f;
        } while (axVar != this.a);
        return i;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return true;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        ax axVar = this.a;
        if (axVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), axVar.c - axVar.b);
        byteBuffer.put(axVar.a, axVar.b, iMin);
        axVar.b += iMin;
        this.b -= (long) iMin;
        if (axVar.b == axVar.c) {
            this.a = axVar.b();
            ay.a(axVar);
        }
        return iMin;
    }

    public final String toString() {
        long j = this.b;
        if (j <= 2147483647L) {
            int i = (int) j;
            return (i == 0 ? aq.b : new az(this, i)).toString();
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.b);
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int iRemaining = byteBuffer.remaining();
        int i = iRemaining;
        while (i > 0) {
            ax axVarG = g();
            int iMin = Math.min(i, 8192 - axVarG.c);
            byteBuffer.get(axVarG.a, axVarG.c, iMin);
            i -= iMin;
            axVarG.c += iMin;
        }
        this.b += (long) iRemaining;
        return iRemaining;
    }
}
