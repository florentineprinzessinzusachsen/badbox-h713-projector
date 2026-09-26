package com.szns.sdk;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
final class as implements ba {
    final /* synthetic */ bc a;
    final /* synthetic */ OutputStream b;

    as(au auVar, OutputStream outputStream) {
        this.a = auVar;
        this.b = outputStream;
    }

    @Override // com.szns.sdk.ba
    public final void a(an anVar, long j) throws IOException {
        be.a(anVar.b, 0L, j);
        while (j > 0) {
            this.a.g();
            ax axVar = anVar.a;
            int iMin = (int) Math.min(j, axVar.c - axVar.b);
            this.b.write(axVar.a, axVar.b, iMin);
            axVar.b += iMin;
            long j2 = iMin;
            j -= j2;
            anVar.b -= j2;
            if (axVar.b == axVar.c) {
                anVar.a = axVar.b();
                ay.a(axVar);
            }
        }
    }

    @Override // com.szns.sdk.ba, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.b.close();
    }

    @Override // com.szns.sdk.ba, java.io.Flushable
    public final void flush() throws IOException {
        this.b.flush();
    }

    public final String toString() {
        return "sink(" + this.b + ")";
    }
}
