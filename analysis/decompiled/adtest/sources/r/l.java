package r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l implements w.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final w.c f1910d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f1911e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ s f1912f;

    public l(s sVar, w.c cVar) {
        j2.i.e(cVar, "delegate");
        this.f1912f = sVar;
        this.f1910d = cVar;
        this.f1911e = a.a.h();
    }

    @Override // w.c
    public final boolean F() {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            return this.f1910d.F();
        }
        l3.h.m0(21, "Attempted to use statement on a different thread");
        throw null;
    }

    @Override // w.c
    public final void a(int i4, long j4) {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            this.f1910d.a(i4, j4);
        } else {
            l3.h.m0(21, "Attempted to use statement on a different thread");
            throw null;
        }
    }

    @Override // java.lang.AutoCloseable
    public final void close() throws Exception {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            this.f1910d.close();
        } else {
            l3.h.m0(21, "Attempted to use statement on a different thread");
            throw null;
        }
    }

    @Override // w.c
    public final void d(int i4, byte[] bArr) {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            this.f1910d.d(i4, bArr);
        } else {
            l3.h.m0(21, "Attempted to use statement on a different thread");
            throw null;
        }
    }

    @Override // w.c
    public final void e(int i4) {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            this.f1910d.e(i4);
        } else {
            l3.h.m0(21, "Attempted to use statement on a different thread");
            throw null;
        }
    }

    @Override // w.c
    public final byte[] getBlob(int i4) {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            return this.f1910d.getBlob(i4);
        }
        l3.h.m0(21, "Attempted to use statement on a different thread");
        throw null;
    }

    @Override // w.c
    public final int getColumnCount() {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            return this.f1910d.getColumnCount();
        }
        l3.h.m0(21, "Attempted to use statement on a different thread");
        throw null;
    }

    @Override // w.c
    public final String getColumnName(int i4) {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            return this.f1910d.getColumnName(i4);
        }
        l3.h.m0(21, "Attempted to use statement on a different thread");
        throw null;
    }

    @Override // w.c
    public final long getLong(int i4) {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            return this.f1910d.getLong(i4);
        }
        l3.h.m0(21, "Attempted to use statement on a different thread");
        throw null;
    }

    @Override // w.c
    public final boolean isNull(int i4) {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            return this.f1910d.isNull(i4);
        }
        l3.h.m0(21, "Attempted to use statement on a different thread");
        throw null;
    }

    @Override // w.c
    public final String n(int i4) {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            return this.f1910d.n(i4);
        }
        l3.h.m0(21, "Attempted to use statement on a different thread");
        throw null;
    }

    @Override // w.c
    public final void o(int i4, String str) {
        j2.i.e(str, "value");
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            this.f1910d.o(i4, str);
        } else {
            l3.h.m0(21, "Attempted to use statement on a different thread");
            throw null;
        }
    }

    @Override // w.c
    public final void reset() {
        if (this.f1912f.f1944d.get()) {
            l3.h.m0(21, "Statement is recycled");
            throw null;
        }
        if (this.f1911e == a.a.h()) {
            this.f1910d.reset();
        } else {
            l3.h.m0(21, "Attempted to use statement on a different thread");
            throw null;
        }
    }

    @Override // w.c
    public final boolean v() {
        return getLong(0) != 0;
    }
}
