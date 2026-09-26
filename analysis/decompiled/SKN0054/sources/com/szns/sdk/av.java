package com.szns.sdk;

import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
final class av implements ao {
    public final an a = new an();
    public final ba b;
    boolean c;

    av(ak akVar) {
        Objects.requireNonNull(akVar, "sink == null");
        this.b = akVar;
    }

    private av a() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long jD = this.a.d();
        if (jD > 0) {
            this.b.a(this.a, jD);
        }
        return this;
    }

    @Override // com.szns.sdk.ao
    public final ao a(byte[] bArr, int i) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(bArr, 0, i);
        return a();
    }

    @Override // com.szns.sdk.ba
    public final void a(an anVar, long j) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(anVar, j);
        a();
    }

    @Override // com.szns.sdk.ao
    public final ao b(byte[] bArr) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(bArr);
        return a();
    }

    @Override // com.szns.sdk.ba, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        if (this.c) {
            return;
        }
        Throwable th = null;
        try {
            if (this.a.b > 0) {
                ba baVar = this.b;
                an anVar = this.a;
                baVar.a(anVar, anVar.b);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.b.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.c = true;
        if (th != null) {
            be.a(th);
        }
    }

    @Override // com.szns.sdk.ao, com.szns.sdk.ba, java.io.Flushable
    public final void flush() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.b > 0) {
            ba baVar = this.b;
            an anVar = this.a;
            baVar.a(anVar, anVar.b);
        }
        this.b.flush();
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.c;
    }

    public final String toString() {
        return "buffer(" + this.b + ")";
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        int iWrite = this.a.write(byteBuffer);
        a();
        return iWrite;
    }
}
