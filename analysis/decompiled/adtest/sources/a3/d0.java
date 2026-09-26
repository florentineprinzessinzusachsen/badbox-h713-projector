package a3;

import java.io.Closeable;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d0 implements Closeable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a0 f103d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final y f104e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f105f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f106g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final p f107h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r f108i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final f0 f109j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final q3.t f110k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d0 f111l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final d0 f112m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final d0 f113n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f114o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f115p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final e3.h f116q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final i0 f117r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f118s;

    public d0(a0 a0Var, y yVar, String str, int i4, p pVar, r rVar, f0 f0Var, q3.t tVar, d0 d0Var, d0 d0Var2, d0 d0Var3, long j4, long j5, e3.h hVar, i0 i0Var) {
        j2.i.e(a0Var, "request");
        j2.i.e(yVar, "protocol");
        j2.i.e(str, "message");
        j2.i.e(f0Var, "body");
        j2.i.e(i0Var, "trailersSource");
        this.f103d = a0Var;
        this.f104e = yVar;
        this.f105f = str;
        this.f106g = i4;
        this.f107h = pVar;
        this.f108i = rVar;
        this.f109j = f0Var;
        this.f110k = tVar;
        this.f111l = d0Var;
        this.f112m = d0Var2;
        this.f113n = d0Var3;
        this.f114o = j4;
        this.f115p = j5;
        this.f116q = hVar;
        this.f117r = i0Var;
        boolean z3 = false;
        if (200 <= i4 && i4 < 300) {
            z3 = true;
        }
        this.f118s = z3;
    }

    public final c0 b() {
        c0 c0Var = new c0();
        c0Var.f90c = -1;
        c0Var.f94g = f0.f124d;
        c0Var.f102o = i0.f162b;
        c0Var.f88a = this.f103d;
        c0Var.f89b = this.f104e;
        c0Var.f90c = this.f106g;
        c0Var.f91d = this.f105f;
        c0Var.f92e = this.f107h;
        c0Var.f93f = this.f108i.c();
        c0Var.f94g = this.f109j;
        c0Var.f95h = this.f110k;
        c0Var.f96i = this.f111l;
        c0Var.f97j = this.f112m;
        c0Var.f98k = this.f113n;
        c0Var.f99l = this.f114o;
        c0Var.f100m = this.f115p;
        c0Var.f101n = this.f116q;
        c0Var.f102o = this.f117r;
        return c0Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f109j.close();
    }

    public final String toString() {
        return "Response{protocol=" + this.f104e + ", code=" + this.f106g + ", message=" + this.f105f + ", url=" + ((t) this.f103d.f63c) + '}';
    }
}
