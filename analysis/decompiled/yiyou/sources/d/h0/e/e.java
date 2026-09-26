package d.h0.e;

import e.g;
import e.r;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: compiled from: FaultHidingSink.java */
/* JADX INFO: loaded from: classes.dex */
class e extends g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4381b;

    e(r rVar) {
        super(rVar);
    }

    @Override // e.g, e.r
    public void a(e.c cVar, long j) throws EOFException {
        if (this.f4381b) {
            cVar.skip(j);
            return;
        }
        try {
            super.a(cVar, j);
        } catch (IOException e2) {
            this.f4381b = true;
            a(e2);
        }
    }

    protected void a(IOException iOException) {
        throw null;
    }

    @Override // e.g, e.r, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f4381b) {
            return;
        }
        try {
            super.close();
        } catch (IOException e2) {
            this.f4381b = true;
            a(e2);
        }
    }

    @Override // e.g, e.r, java.io.Flushable
    public void flush() {
        if (this.f4381b) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e2) {
            this.f4381b = true;
            a(e2);
        }
    }
}
