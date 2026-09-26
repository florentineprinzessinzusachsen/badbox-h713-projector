package com.szns.sdk;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class al implements bb {
    final /* synthetic */ bb a;
    final /* synthetic */ aj b;

    al(au auVar, at atVar) {
        this.b = auVar;
        this.a = atVar;
    }

    @Override // com.szns.sdk.bb
    public final long a_(an anVar, long j) throws IOException {
        this.b.a();
        try {
            try {
                long jA_ = this.a.a_(anVar, j);
                this.b.a(true);
                return jA_;
            } catch (IOException e) {
                throw this.b.a(e);
            }
        } catch (Throwable th) {
            this.b.a(false);
            throw th;
        }
    }

    @Override // com.szns.sdk.bb, java.io.Closeable, java.lang.AutoCloseable
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

    public final String toString() {
        return "AsyncTimeout.source(" + this.a + ")";
    }
}
