package f0;

import a3.l;
import a3.q;
import a3.z;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import d0.a0;
import d0.k0;
import d0.l0;
import d0.t;
import e0.f;
import e0.h;
import h0.i;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import l0.k;
import l0.p;
import m0.j;
import r2.s;
import r2.v0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements h, i, e0.b {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final String f846o = a0.g("GreedyScheduler");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f847a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f849c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f850d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f f853g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final c3.b f854h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d0.b f855i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Boolean f857k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final q f858l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final l f859m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final e f860n;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f848b = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f851e = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c3.b f852f = new c3.b(new d0.i(1));

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final HashMap f856j = new HashMap();

    public d(Context context, d0.b bVar, z zVar, f fVar, c3.b bVar2, l lVar) {
        this.f847a = context;
        a3.h hVar = bVar.f410g;
        this.f849c = new b(this, hVar, bVar.f407d);
        this.f860n = new e(hVar, bVar2);
        this.f859m = lVar;
        this.f858l = new q(zVar);
        this.f855i = bVar;
        this.f853g = fVar;
        this.f854h = bVar2;
    }

    @Override // e0.h
    public final void a(String str) {
        List<e0.l> listD;
        Runnable runnable;
        String str2 = f846o;
        if (this.f857k == null) {
            this.f857k = Boolean.valueOf(m0.i.a(this.f847a, this.f855i));
        }
        if (!this.f857k.booleanValue()) {
            a0.e().f(str2, "Ignoring schedule request in non-main process");
            return;
        }
        if (!this.f850d) {
            this.f853g.a(this);
            this.f850d = true;
        }
        a0.e().a(str2, "Cancelling work ID " + str);
        b bVar = this.f849c;
        if (bVar != null && (runnable = (Runnable) bVar.f843d.remove(str)) != null) {
            ((Handler) bVar.f841b.f149e).removeCallbacks(runnable);
        }
        c3.b bVar2 = this.f852f;
        bVar2.getClass();
        j2.i.e(str, "workSpecId");
        synchronized (bVar2.f373e) {
            listD = ((d0.i) bVar2.f372d).d(str);
        }
        for (e0.l lVar : listD) {
            this.f860n.a(lVar);
            c3.b bVar3 = this.f854h;
            bVar3.getClass();
            bVar3.j(lVar, -512);
        }
    }

    @Override // h0.i
    public final void b(p pVar, h0.c cVar) {
        k kVarS = l0.s(pVar);
        boolean z3 = cVar instanceof h0.a;
        c3.b bVar = this.f854h;
        e eVar = this.f860n;
        String str = f846o;
        c3.b bVar2 = this.f852f;
        if (!z3) {
            a0.e().a(str, "Constraints not met: Cancelling work ID " + kVarS);
            e0.l lVarI = bVar2.i(kVarS);
            if (lVarI != null) {
                eVar.a(lVarI);
                int i4 = ((h0.b) cVar).f997a;
                bVar.getClass();
                bVar.j(lVarI, i4);
                return;
            }
            return;
        }
        if (bVar2.g(kVarS)) {
            return;
        }
        a0.e().a(str, "Constraints met: Scheduling work ID " + kVarS);
        e0.l lVarK = bVar2.k(kVarS);
        eVar.b(lVarK);
        ((j) ((l) bVar.f373e).f184e).execute(new t(bVar, lVarK, null, 3));
    }

    @Override // e0.h
    public final void c(p... pVarArr) {
        if (this.f857k == null) {
            this.f857k = Boolean.valueOf(m0.i.a(this.f847a, this.f855i));
        }
        if (!this.f857k.booleanValue()) {
            a0.e().f(f846o, "Ignoring schedule request in a secondary process");
            return;
        }
        if (!this.f850d) {
            this.f853g.a(this);
            this.f850d = true;
        }
        HashSet<p> hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (p pVar : pVarArr) {
            if (!this.f852f.g(l0.s(pVar))) {
                long jMax = Math.max(pVar.a(), g(pVar));
                this.f855i.f407d.getClass();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (pVar.f1332b == k0.f467d) {
                    if (jCurrentTimeMillis < jMax) {
                        b bVar = this.f849c;
                        if (bVar != null) {
                            a3.h hVar = bVar.f841b;
                            HashMap map = bVar.f843d;
                            Runnable runnable = (Runnable) map.remove(pVar.f1331a);
                            if (runnable != null) {
                                ((Handler) hVar.f149e).removeCallbacks(runnable);
                            }
                            a aVar = new a(0, bVar, pVar);
                            map.put(pVar.f1331a, aVar);
                            bVar.f842c.getClass();
                            ((Handler) hVar.f149e).postDelayed(aVar, jMax - System.currentTimeMillis());
                        }
                    } else if (!j2.i.a(d0.e.f432j, pVar.f1340j)) {
                        d0.e eVar = pVar.f1340j;
                        if (eVar.f436d) {
                            a0.e().a(f846o, "Ignoring " + pVar + ". Requires device idle.");
                        } else if (Build.VERSION.SDK_INT < 24 || !eVar.b()) {
                            hashSet.add(pVar);
                            hashSet2.add(pVar.f1331a);
                        } else {
                            a0.e().a(f846o, "Ignoring " + pVar + ". Requires ContentUri triggers.");
                        }
                    } else if (!this.f852f.g(l0.s(pVar))) {
                        a0.e().a(f846o, "Starting work for " + pVar.f1331a);
                        c3.b bVar2 = this.f852f;
                        bVar2.getClass();
                        e0.l lVarK = bVar2.k(l0.s(pVar));
                        this.f860n.b(lVarK);
                        c3.b bVar3 = this.f854h;
                        ((j) ((l) bVar3.f373e).f184e).execute(new t(bVar3, lVarK, null, 3));
                    }
                }
            }
        }
        synchronized (this.f851e) {
            try {
                if (!hashSet.isEmpty()) {
                    a0.e().a(f846o, "Starting tracking for " + TextUtils.join(",", hashSet2));
                    for (p pVar2 : hashSet) {
                        k kVarS = l0.s(pVar2);
                        if (!this.f848b.containsKey(kVarS)) {
                            this.f848b.put(kVarS, h0.q.a(this.f858l, pVar2, (s) this.f859m.f185f, this));
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // e0.b
    public final void d(k kVar, boolean z3) {
        e0.l lVarI = this.f852f.i(kVar);
        if (lVarI != null) {
            this.f860n.a(lVarI);
        }
        f(kVar);
        if (z3) {
            return;
        }
        synchronized (this.f851e) {
            this.f856j.remove(kVar);
        }
    }

    @Override // e0.h
    public final boolean e() {
        return false;
    }

    public final void f(k kVar) {
        v0 v0Var;
        synchronized (this.f851e) {
            v0Var = (v0) this.f848b.remove(kVar);
        }
        if (v0Var != null) {
            a0.e().a(f846o, "Stopping tracking for " + kVar);
            v0Var.b(null);
        }
    }

    public final long g(p pVar) {
        long jMax;
        synchronized (this.f851e) {
            try {
                k kVarS = l0.s(pVar);
                c cVar = (c) this.f856j.get(kVarS);
                if (cVar == null) {
                    int i4 = pVar.f1341k;
                    this.f855i.f407d.getClass();
                    cVar = new c(i4, System.currentTimeMillis());
                    this.f856j.put(kVarS, cVar);
                }
                jMax = (((long) Math.max((pVar.f1341k - cVar.f844a) - 5, 0)) * 30000) + cVar.f845b;
            } catch (Throwable th) {
                throw th;
            }
        }
        return jMax;
    }
}
