package n;

import d0.f0;
import d0.l;
import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Object f1473h = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1474a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d.d f1475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile Object f1476c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile Object f1477d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1478e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1479f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d3.d f1480g;

    public h() {
        f0 f0Var = l.f477d;
        this.f1474a = new Object();
        this.f1475b = new d.d();
        this.f1477d = f1473h;
        this.f1480g = new d3.d(1, this);
        this.f1476c = f0Var;
    }

    public final void a(l0 l0Var) {
        boolean z3;
        synchronized (this.f1474a) {
            z3 = this.f1477d == f1473h;
            this.f1477d = l0Var;
        }
        if (z3) {
            c.b.J().K(this.f1480g);
        }
    }
}
