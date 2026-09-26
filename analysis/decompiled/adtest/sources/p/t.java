package p;

import android.os.Looper;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public w2.c f1713a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Executor f1714b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m0.j f1715c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public p f1716d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public h f1717e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1719g;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c3.b f1718f = new c3.b(new s(0, this, t.class, "onClosed", "onClosed()V", 0, 0));

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ThreadLocal f1720h = new ThreadLocal();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final LinkedHashMap f1721i = new LinkedHashMap();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1722j = true;

    public final void a() {
        if (this.f1719g) {
            return;
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public final void b() {
        a();
        a();
        x.a aVarM = g().M();
        if (!aVarM.N()) {
            a.a.C(new j1.d(f(), null, 2));
        }
        if (aVarM.p()) {
            aVarM.B();
        } else {
            aVarM.i();
        }
    }

    public List c(LinkedHashMap linkedHashMap) {
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(v1.t.J(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            n2.b bVar = (n2.b) entry.getKey();
            j2.i.e(bVar, "<this>");
            Class clsA = ((j2.d) bVar).a();
            j2.i.c(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
            linkedHashMap2.put(clsA, entry.getValue());
        }
        return v1.p.f2517d;
    }

    public abstract h d();

    public v e() {
        throw new u1.e(0);
    }

    public final h f() {
        h hVar = this.f1717e;
        if (hVar != null) {
            return hVar;
        }
        j2.i.h("internalTracker");
        throw null;
    }

    public final x.d g() {
        p pVar = this.f1716d;
        if (pVar == null) {
            j2.i.h("connectionManager");
            throw null;
        }
        x.d dVarC = pVar.c();
        if (dVarC != null) {
            return dVarC;
        }
        throw new IllegalStateException("Cannot return a SupportSQLiteOpenHelper since no SupportSQLiteOpenHelper.Factory was configured with Room.");
    }

    public Set h() {
        return v1.j.I0(new ArrayList(v1.l.u0(v1.r.f2519d)));
    }

    public LinkedHashMap i() {
        int iJ = v1.t.J(v1.l.u0(v1.r.f2519d));
        if (iJ < 16) {
            iJ = 16;
        }
        return new LinkedHashMap(iJ);
    }

    public final boolean j() {
        p pVar = this.f1716d;
        if (pVar != null) {
            return pVar.c() != null;
        }
        j2.i.h("connectionManager");
        throw null;
    }

    public final boolean k() {
        return m() && g().M().N();
    }

    public final void l() {
        g().M().h();
        if (k()) {
            return;
        }
        h hVarF = f();
        hVarF.f1646b.e(hVarF.f1649e, hVarF.f1650f);
    }

    public final boolean m() {
        p pVar = this.f1716d;
        if (pVar == null) {
            j2.i.h("connectionManager");
            throw null;
        }
        x.a aVar = pVar.f1689g;
        if (aVar != null) {
            return aVar.isOpen();
        }
        return false;
    }

    public final Object n(Callable callable) {
        b();
        try {
            Object objCall = callable.call();
            p();
            return objCall;
        } finally {
            l();
        }
    }

    public final void o(Runnable runnable) {
        b();
        try {
            runnable.run();
            p();
        } finally {
            l();
        }
    }

    public final void p() {
        g().M().y();
    }

    public final Object q(boolean z3, i2.p pVar, a2.c cVar) {
        p pVar2 = this.f1716d;
        if (pVar2 != null) {
            return pVar2.f1688f.x(z3, pVar, cVar);
        }
        j2.i.h("connectionManager");
        throw null;
    }
}
