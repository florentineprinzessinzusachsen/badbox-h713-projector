package s;

import j2.i;
import java.io.IOException;
import l3.h;
import y.j;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f extends g {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final j f2080g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(x.a aVar, String str) {
        super(aVar, str);
        i.e(aVar, "db");
        i.e(str, "sql");
        this.f2080g = aVar.z(str);
    }

    @Override // w.c
    public final boolean F() {
        b();
        this.f2080g.f2708e.execute();
        return false;
    }

    @Override // w.c
    public final void a(int i4, long j4) {
        b();
        this.f2080g.a(i4, j4);
    }

    @Override // java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f2080g.close();
        this.f2083f = true;
    }

    @Override // w.c
    public final void d(int i4, byte[] bArr) {
        b();
        this.f2080g.d(i4, bArr);
    }

    @Override // w.c
    public final void e(int i4) {
        b();
        this.f2080g.e(i4);
    }

    @Override // w.c
    public final byte[] getBlob(int i4) {
        b();
        h.m0(21, "no row");
        throw null;
    }

    @Override // w.c
    public final int getColumnCount() {
        b();
        return 0;
    }

    @Override // w.c
    public final String getColumnName(int i4) {
        b();
        h.m0(21, "no row");
        throw null;
    }

    @Override // w.c
    public final long getLong(int i4) {
        b();
        h.m0(21, "no row");
        throw null;
    }

    @Override // w.c
    public final boolean isNull(int i4) {
        b();
        h.m0(21, "no row");
        throw null;
    }

    @Override // w.c
    public final String n(int i4) {
        b();
        h.m0(21, "no row");
        throw null;
    }

    @Override // w.c
    public final void o(int i4, String str) {
        i.e(str, "value");
        b();
        this.f2080g.u(i4, str);
    }

    @Override // w.c
    public final void reset() {
    }
}
