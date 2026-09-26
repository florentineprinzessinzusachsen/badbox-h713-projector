package a3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a0 f88a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public y f89b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f91d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public p f92e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public q3.t f95h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public d0 f96i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public d0 f97j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public d0 f98k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f99l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f100m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e3.h f101n;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f90c = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public f0 f94g = f0.f124d;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public i0 f102o = i0.f162b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public q f93f = new q();

    public static void b(d0 d0Var, String str) {
        if (d0Var != null) {
            if (d0Var.f111l != null) {
                throw new IllegalArgumentException(str.concat(".networkResponse != null").toString());
            }
            if (d0Var.f112m != null) {
                throw new IllegalArgumentException(str.concat(".cacheResponse != null").toString());
            }
            if (d0Var.f113n != null) {
                throw new IllegalArgumentException(str.concat(".priorResponse != null").toString());
            }
        }
    }

    public final d0 a() {
        int i4 = this.f90c;
        if (i4 < 0) {
            throw new IllegalStateException(("code < 0: " + this.f90c).toString());
        }
        a0 a0Var = this.f88a;
        if (a0Var == null) {
            throw new IllegalStateException("request == null");
        }
        y yVar = this.f89b;
        if (yVar == null) {
            throw new IllegalStateException("protocol == null");
        }
        String str = this.f91d;
        if (str != null) {
            return new d0(a0Var, yVar, str, i4, this.f92e, this.f93f.a(), this.f94g, this.f95h, this.f96i, this.f97j, this.f98k, this.f99l, this.f100m, this.f101n, this.f102o);
        }
        throw new IllegalStateException("message == null");
    }
}
