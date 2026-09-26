package com.szns.sdk;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class ak implements ba {
    final /* synthetic */ ba a;
    final /* synthetic */ aj b;

    ak(au auVar, as asVar) {
        this.b = auVar;
        this.a = asVar;
    }

    @Override // com.szns.sdk.ba
    public final void a(an anVar, long j) throws IOException {
        be.a(anVar.b, 0L, j);
        while (true) {
            long j2 = 0;
            if (j <= 0) {
                return;
            }
            ax axVar = anVar.a;
            while (j2 < 65536) {
                j2 += (long) (axVar.c - axVar.b);
                if (j2 >= j) {
                    j2 = j;
                    break;
                }
                axVar = axVar.f;
            }
            this.b.a();
            try {
                try {
                    this.a.a(anVar, j2);
                    j -= j2;
                    this.b.a(true);
                } catch (IOException e) {
                    throw this.b.a(e);
                }
            } catch (Throwable th) {
                this.b.a(false);
                throw th;
            }
        }
    }

    @Override // com.szns.sdk.ba, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.b.a();
        try {
            try {
                this.a.close();
                this.b.a(true);
            } catch (IOException e) {
                throw this.b.a(e);
            }
        } catch (Throwable th) {
            this.b.a(false);
            throw th;
        }
    }

    @Override // com.szns.sdk.ba, java.io.Flushable
    public final void flush() throws IOException {
        this.b.a();
        try {
            try {
                this.a.flush();
                this.b.a(true);
            } catch (IOException e) {
                throw this.b.a(e);
            }
        } catch (Throwable th) {
            this.b.a(false);
            throw th;
        }
    }

    public final String toString() {
        return "AsyncTimeout.sink(" + this.a + ")";
    }
}
