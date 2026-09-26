package com.szns.sdk;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
final class aw implements ap {
    public final an a = new an();
    public final bb b;
    boolean c;

    aw(al alVar) {
        Objects.requireNonNull(alVar, "source == null");
        this.b = alVar;
    }

    @Override // com.szns.sdk.ap
    public final int a(byte[] bArr, int i, int i2) {
        long j = i2;
        be.a(bArr.length, i, j);
        if (this.a.b == 0 && this.b.a_(this.a, 8192L) == -1) {
            return -1;
        }
        return this.a.a(bArr, i, (int) Math.min(j, this.a.b));
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0027  */
    /* JADX WARN: Code duplicated, block: B:15:0x002e  */
    @Override // com.szns.sdk.ap
    public final String a(long j) throws EOFException {
        boolean z;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: ".concat(String.valueOf(j)));
        }
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (this.a.b < j) {
            if (this.b.a_(this.a, 8192L) == -1) {
                z = false;
                if (z) {
                    return this.a.a(j);
                }
                throw new EOFException();
            }
        }
        z = true;
        if (z) {
            return this.a.a(j);
        }
        throw new EOFException();
    }

    @Override // com.szns.sdk.bb
    public final long a_(an anVar, long j) {
        if (anVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: ".concat(String.valueOf(j)));
        }
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.b == 0 && this.b.a_(this.a, 8192L) == -1) {
            return -1L;
        }
        return this.a.a_(anVar, Math.min(j, this.a.b));
    }

    @Override // com.szns.sdk.ap
    public final an b() {
        return this.a;
    }

    @Override // com.szns.sdk.ap
    public final void b(long j) throws EOFException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.a.b == 0 && this.b.a_(this.a, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.a.b);
            this.a.b(jMin);
            j -= jMin;
        }
    }

    @Override // com.szns.sdk.ap
    public final boolean c() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        return this.a.c() && this.b.a_(this.a, 8192L) == -1;
    }

    @Override // com.szns.sdk.bb, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.c) {
            return;
        }
        this.c = true;
        this.b.close();
        this.a.f();
    }

    @Override // com.szns.sdk.ap
    public final long h() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long jMax = 0;
        while (jMax < Long.MAX_VALUE) {
            long jA = this.a.a(jMax, Long.MAX_VALUE);
            if (jA != -1) {
                return jA;
            }
            long j = this.a.b;
            if (j >= Long.MAX_VALUE || this.b.a_(this.a, 8192L) == -1) {
                break;
            }
            jMax = Math.max(jMax, j);
        }
        return -1L;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.c;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        if (this.a.b == 0 && this.b.a_(this.a, 8192L) == -1) {
            return -1;
        }
        return this.a.read(byteBuffer);
    }

    public final String toString() {
        return "buffer(" + this.b + ")";
    }
}
