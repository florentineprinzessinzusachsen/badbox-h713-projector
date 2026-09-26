package com.szns.sdk;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
final class at implements bb {
    final /* synthetic */ bc a;
    final /* synthetic */ InputStream b;

    at(au auVar, InputStream inputStream) {
        this.a = auVar;
        this.b = inputStream;
    }

    @Override // com.szns.sdk.bb
    public final long a_(an anVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: ".concat(String.valueOf(j)));
        }
        if (j == 0) {
            return 0L;
        }
        try {
            this.a.g();
            ax axVarG = anVar.g();
            int i = this.b.read(axVarG.a, axVarG.c, (int) Math.min(j, 8192 - axVarG.c));
            if (i == -1) {
                return -1L;
            }
            axVarG.c += i;
            long j2 = i;
            anVar.b += j2;
            return j2;
        } catch (AssertionError e) {
            if (ar.a(e)) {
                throw new IOException(e);
            }
            throw e;
        }
    }

    @Override // com.szns.sdk.bb, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.b.close();
    }

    public final String toString() {
        return "source(" + this.b + ")";
    }
}
