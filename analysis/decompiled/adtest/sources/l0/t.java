package l0;

import d0.k0;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p.t f1361a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f1362b = new c(4);

    public t(p.t tVar) {
        this.f1361a = tVar;
    }

    public final void a(String str) {
        j2.i.e(str, "id");
        l3.h.W(this.f1361a, false, true, new b(15, str));
    }

    public final k0 b(String str) {
        j2.i.e(str, "id");
        return (k0) l3.h.W(this.f1361a, true, false, new b(8, str));
    }

    public final p c(String str) {
        j2.i.e(str, "id");
        return (p) l3.h.W(this.f1361a, true, false, new b(7, str));
    }

    public final List d(String str) {
        j2.i.e(str, "name");
        return (List) l3.h.W(this.f1361a, true, false, new b(16, str));
    }

    public final int e(String str, long j4) {
        j2.i.e(str, "id");
        return ((Number) l3.h.W(this.f1361a, false, true, new r(j4, str, 0))).intValue();
    }

    public final void f(int i4, String str) {
        j2.i.e(str, "id");
        l3.h.W(this.f1361a, false, true, new i(str, i4, 1));
    }

    public final void g(String str, long j4) {
        j2.i.e(str, "id");
        l3.h.W(this.f1361a, false, true, new r(j4, str, 1));
    }

    public final int h(k0 k0Var, String str) {
        j2.i.e(str, "id");
        return ((Number) l3.h.W(this.f1361a, false, true, new h0.e(5, k0Var, str))).intValue();
    }

    public final void i(int i4, String str) {
        j2.i.e(str, "id");
        l3.h.W(this.f1361a, false, true, new i(i4, str));
    }
}
