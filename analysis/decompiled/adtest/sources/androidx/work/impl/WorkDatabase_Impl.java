package androidx.work.impl;

import androidx.work.impl.WorkDatabase_Impl;
import e0.c;
import e0.u;
import e0.w;
import i2.a;
import j2.e;
import j2.o;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import l0.d;
import l0.f;
import l0.g;
import l0.j;
import l0.m;
import l0.n;
import l0.t;
import p.h;
import p.v;
import u1.i;
import v1.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class WorkDatabase_Impl extends WorkDatabase {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final i f316k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i f317l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i f318m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final i f319n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final i f320o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final i f321p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final i f322q;

    public WorkDatabase_Impl() {
        final int i4 = 0;
        this.f316k = new i(new a(this) { // from class: e0.v

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ WorkDatabase_Impl f687e;

            {
                this.f687e = this;
            }

            @Override // i2.a
            public final Object a() {
                switch (i4) {
                    case 0:
                        return new l0.t(this.f687e);
                    case 1:
                        return new l0.d(this.f687e);
                    case 2:
                        return new l0.v(this.f687e);
                    case 3:
                        return new l0.j(this.f687e);
                    case 4:
                        return new l0.m(this.f687e);
                    case 5:
                        return new l0.n(this.f687e);
                    default:
                        return new l0.f(this.f687e);
                }
            }
        });
        final int i5 = 1;
        this.f317l = new i(new a(this) { // from class: e0.v

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ WorkDatabase_Impl f687e;

            {
                this.f687e = this;
            }

            @Override // i2.a
            public final Object a() {
                switch (i5) {
                    case 0:
                        return new l0.t(this.f687e);
                    case 1:
                        return new l0.d(this.f687e);
                    case 2:
                        return new l0.v(this.f687e);
                    case 3:
                        return new l0.j(this.f687e);
                    case 4:
                        return new l0.m(this.f687e);
                    case 5:
                        return new l0.n(this.f687e);
                    default:
                        return new l0.f(this.f687e);
                }
            }
        });
        final int i6 = 2;
        this.f318m = new i(new a(this) { // from class: e0.v

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ WorkDatabase_Impl f687e;

            {
                this.f687e = this;
            }

            @Override // i2.a
            public final Object a() {
                switch (i6) {
                    case 0:
                        return new l0.t(this.f687e);
                    case 1:
                        return new l0.d(this.f687e);
                    case 2:
                        return new l0.v(this.f687e);
                    case 3:
                        return new l0.j(this.f687e);
                    case 4:
                        return new l0.m(this.f687e);
                    case 5:
                        return new l0.n(this.f687e);
                    default:
                        return new l0.f(this.f687e);
                }
            }
        });
        final int i7 = 3;
        this.f319n = new i(new a(this) { // from class: e0.v

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ WorkDatabase_Impl f687e;

            {
                this.f687e = this;
            }

            @Override // i2.a
            public final Object a() {
                switch (i7) {
                    case 0:
                        return new l0.t(this.f687e);
                    case 1:
                        return new l0.d(this.f687e);
                    case 2:
                        return new l0.v(this.f687e);
                    case 3:
                        return new l0.j(this.f687e);
                    case 4:
                        return new l0.m(this.f687e);
                    case 5:
                        return new l0.n(this.f687e);
                    default:
                        return new l0.f(this.f687e);
                }
            }
        });
        final int i8 = 4;
        this.f320o = new i(new a(this) { // from class: e0.v

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ WorkDatabase_Impl f687e;

            {
                this.f687e = this;
            }

            @Override // i2.a
            public final Object a() {
                switch (i8) {
                    case 0:
                        return new l0.t(this.f687e);
                    case 1:
                        return new l0.d(this.f687e);
                    case 2:
                        return new l0.v(this.f687e);
                    case 3:
                        return new l0.j(this.f687e);
                    case 4:
                        return new l0.m(this.f687e);
                    case 5:
                        return new l0.n(this.f687e);
                    default:
                        return new l0.f(this.f687e);
                }
            }
        });
        final int i9 = 5;
        this.f321p = new i(new a(this) { // from class: e0.v

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ WorkDatabase_Impl f687e;

            {
                this.f687e = this;
            }

            @Override // i2.a
            public final Object a() {
                switch (i9) {
                    case 0:
                        return new l0.t(this.f687e);
                    case 1:
                        return new l0.d(this.f687e);
                    case 2:
                        return new l0.v(this.f687e);
                    case 3:
                        return new l0.j(this.f687e);
                    case 4:
                        return new l0.m(this.f687e);
                    case 5:
                        return new l0.n(this.f687e);
                    default:
                        return new l0.f(this.f687e);
                }
            }
        });
        final int i10 = 6;
        this.f322q = new i(new a(this) { // from class: e0.v

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ WorkDatabase_Impl f687e;

            {
                this.f687e = this;
            }

            @Override // i2.a
            public final Object a() {
                switch (i10) {
                    case 0:
                        return new l0.t(this.f687e);
                    case 1:
                        return new l0.d(this.f687e);
                    case 2:
                        return new l0.v(this.f687e);
                    case 3:
                        return new l0.j(this.f687e);
                    case 4:
                        return new l0.m(this.f687e);
                    case 5:
                        return new l0.n(this.f687e);
                    default:
                        return new l0.f(this.f687e);
                }
            }
        });
    }

    @Override // p.t
    public final List c(LinkedHashMap linkedHashMap) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c(13, 14, 10));
        arrayList.add(new u(0));
        int i4 = 17;
        arrayList.add(new c(16, i4, 11));
        int i5 = 18;
        arrayList.add(new c(i4, i5, 12));
        arrayList.add(new c(i5, 19, 13));
        arrayList.add(new u(1));
        arrayList.add(new c(20, 21, 14));
        int i6 = 23;
        arrayList.add(new c(22, i6, 15));
        arrayList.add(new c(i6, 24, 16));
        return arrayList;
    }

    @Override // p.t
    public final h d() {
        return new h(this, new LinkedHashMap(), new LinkedHashMap(), "Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName", "WorkProgress", "Preference");
    }

    @Override // p.t
    public final v e() {
        return new w(this);
    }

    @Override // p.t
    public final Set h() {
        return new LinkedHashSet();
    }

    @Override // p.t
    public final LinkedHashMap i() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        e eVarA = o.a(t.class);
        p pVar = p.f2517d;
        linkedHashMap.put(eVarA, pVar);
        linkedHashMap.put(o.a(d.class), pVar);
        linkedHashMap.put(o.a(l0.v.class), pVar);
        linkedHashMap.put(o.a(j.class), pVar);
        linkedHashMap.put(o.a(m.class), pVar);
        linkedHashMap.put(o.a(n.class), pVar);
        linkedHashMap.put(o.a(f.class), pVar);
        linkedHashMap.put(o.a(g.class), pVar);
        return linkedHashMap;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final d r() {
        return (d) this.f317l.getValue();
    }

    @Override // androidx.work.impl.WorkDatabase
    public final f s() {
        return (f) this.f322q.getValue();
    }

    @Override // androidx.work.impl.WorkDatabase
    public final j t() {
        return (j) this.f319n.getValue();
    }

    @Override // androidx.work.impl.WorkDatabase
    public final m u() {
        return (m) this.f320o.getValue();
    }

    @Override // androidx.work.impl.WorkDatabase
    public final n v() {
        return (n) this.f321p.getValue();
    }

    @Override // androidx.work.impl.WorkDatabase
    public final t w() {
        return (t) this.f316k.getValue();
    }

    @Override // androidx.work.impl.WorkDatabase
    public final l0.v x() {
        return (l0.v) this.f318m.getValue();
    }
}
