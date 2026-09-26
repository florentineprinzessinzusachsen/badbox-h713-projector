package ddth2;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class p extends a {
    public final Context a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final n f90a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public boolean f91a;

    public p(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.f90a = new n(applicationContext);
    }

    public static synchronized p a(Context context) {
        a aVar = a.a;
        if (aVar == null) {
            a.a = new p(context);
        } else if (!(aVar instanceof p)) {
            throw new IllegalStateException("SDK already bound to another implementation");
        }
        return (p) a.a;
    }

    public long a(Runnable runnable, Runnable runnable2, Runnable runnable3, Runnable runnable4, Runnable runnable5) {
        return ((a) this).f1a.a(runnable, runnable2, runnable3, runnable4, runnable5);
    }

    public MetricsSnapshot a() {
        return ((a) this).f1a.m46a();
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public final String m68a() {
        String strA = h.a(this.a);
        if (strA != null && !strA.isEmpty()) {
            return strA;
        }
        String strA2 = g.a(this.a);
        h.a(this.a, strA2);
        return strA2;
    }

    public final void b(e eVar) {
        if (eVar.m41d() == null || eVar.m41d().isEmpty()) {
            eVar.b(m68a());
        }
    }

    public boolean b() {
        return this.f91a;
    }

    public final void c() {
        e eVarA = j.a("ddth2");
        i.a(eVarA);
        b(eVarA);
        a(eVarA);
    }

    /* JADX INFO: renamed from: c, reason: collision with other method in class */
    public boolean m69c() {
        return m0a();
    }

    public void d() {
        this.f90a.a(((a) this).f0a);
    }

    public void e() {
        this.f90a.b(((a) this).f0a);
    }

    public void f() {
        this.f90a.c(((a) this).f0a);
    }

    public void g() {
        this.f90a.d(((a) this).f0a);
    }

    public void h() {
        this.f90a.e(((a) this).f0a);
    }

    public void i() {
        this.f90a.f(((a) this).f0a);
    }

    public void j() {
        if (this.f91a) {
            return;
        }
        if (w1.a.length == 0) {
            throw new AssertionError();
        }
        this.f91a = true;
        c();
        u2.a(this.a);
    }

    public void k() {
        this.f90a.a();
    }

    public void l() {
        if (m0a()) {
            return;
        }
        a();
    }

    public void m() {
        if (this.f91a) {
            b();
        }
    }
}
