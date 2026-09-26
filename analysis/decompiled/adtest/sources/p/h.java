package p;

import androidx.work.impl.WorkDatabase_Impl;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WorkDatabase_Impl f1645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i0 f1646b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashMap f1647c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ReentrantLock f1648d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h1.a f1649e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f f1650f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f1651g;

    /* JADX WARN: Type inference failed for: r1v5, types: [p.f] */
    public h(WorkDatabase_Impl workDatabase_Impl, LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2, String... strArr) {
        this.f1645a = workDatabase_Impl;
        i0 i0Var = new i0(workDatabase_Impl, linkedHashMap, linkedHashMap2, strArr, workDatabase_Impl.f1722j, new g(1, this, h.class, "notifyInvalidatedObservers", "notifyInvalidatedObservers(Ljava/util/Set;)V", 0, 0, 0));
        this.f1646b = i0Var;
        this.f1647c = new LinkedHashMap();
        this.f1648d = new ReentrantLock();
        this.f1649e = new h1.a(this);
        final int i4 = 0;
        this.f1650f = new i2.a(this) { // from class: p.f

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ h f1631e;

            {
                this.f1631e = this;
            }

            @Override // i2.a
            public final Object a() {
                switch (i4) {
                    case 0:
                        this.f1631e.getClass();
                        return u1.k.f2301a;
                    default:
                        WorkDatabase_Impl workDatabase_Impl2 = this.f1631e.f1645a;
                        return Boolean.valueOf(!workDatabase_Impl2.j() || workDatabase_Impl2.m());
                }
            }
        };
        j2.i.d(Collections.newSetFromMap(new IdentityHashMap()), "newSetFromMap(...)");
        this.f1651g = new Object();
        final int i5 = 1;
        i0Var.f1674k = new i2.a(this) { // from class: p.f

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ h f1631e;

            {
                this.f1631e = this;
            }

            @Override // i2.a
            public final Object a() {
                switch (i5) {
                    case 0:
                        this.f1631e.getClass();
                        return u1.k.f2301a;
                    default:
                        WorkDatabase_Impl workDatabase_Impl2 = this.f1631e.f1645a;
                        return Boolean.valueOf(!workDatabase_Impl2.j() || workDatabase_Impl2.m());
                }
            }
        };
    }

    public final Object a(a2.i iVar) {
        Object objF;
        WorkDatabase_Impl workDatabase_Impl = this.f1645a;
        return ((!workDatabase_Impl.j() || workDatabase_Impl.m()) && (objF = this.f1646b.f(iVar)) == z1.a.f2781d) ? objF : u1.k.f2301a;
    }
}
