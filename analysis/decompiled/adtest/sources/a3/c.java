package a3;

import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f74n = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f75a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f76b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f77c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f78d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f79e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f80f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f81g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f82h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f83i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f84j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f85k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f86l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f87m;

    static {
        a1.a aVar = q2.a.f1800d;
        q2.c cVar = q2.c.SECONDS;
        long jB = q2.a.b(l0.N(Integer.MAX_VALUE, cVar), cVar);
        if (jB >= 0) {
            return;
        }
        throw new IllegalArgumentException(("maxStale < 0: " + jB).toString());
    }

    public c(boolean z3, boolean z4, int i4, int i5, boolean z5, boolean z6, boolean z7, int i6, int i7, boolean z8, boolean z9, boolean z10, String str) {
        this.f75a = z3;
        this.f76b = z4;
        this.f77c = i4;
        this.f78d = i5;
        this.f79e = z5;
        this.f80f = z6;
        this.f81g = z7;
        this.f82h = i6;
        this.f83i = i7;
        this.f84j = z8;
        this.f85k = z9;
        this.f86l = z10;
        this.f87m = str;
    }

    public final String toString() {
        String str = this.f87m;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (this.f75a) {
            sb.append("no-cache, ");
        }
        if (this.f76b) {
            sb.append("no-store, ");
        }
        int i4 = this.f77c;
        if (i4 != -1) {
            sb.append("max-age=");
            sb.append(i4);
            sb.append(", ");
        }
        int i5 = this.f78d;
        if (i5 != -1) {
            sb.append("s-maxage=");
            sb.append(i5);
            sb.append(", ");
        }
        if (this.f79e) {
            sb.append("private, ");
        }
        if (this.f80f) {
            sb.append("public, ");
        }
        if (this.f81g) {
            sb.append("must-revalidate, ");
        }
        int i6 = this.f82h;
        if (i6 != -1) {
            sb.append("max-stale=");
            sb.append(i6);
            sb.append(", ");
        }
        int i7 = this.f83i;
        if (i7 != -1) {
            sb.append("min-fresh=");
            sb.append(i7);
            sb.append(", ");
        }
        if (this.f84j) {
            sb.append("only-if-cached, ");
        }
        if (this.f85k) {
            sb.append("no-transform, ");
        }
        if (this.f86l) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        j2.i.d(sb.delete(sb.length() - 2, sb.length()), "delete(...)");
        String string = sb.toString();
        this.f87m = string;
        return string;
    }
}
