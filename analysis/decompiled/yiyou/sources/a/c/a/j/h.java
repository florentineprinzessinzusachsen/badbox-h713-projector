package a.c.a.j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: ConstraintWidgetGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<f> f148a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f149b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f150c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f151d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f152e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    List<f> f153f;
    List<f> g;
    HashSet<f> h;
    HashSet<f> i;
    List<f> j;
    List<f> k;

    h(List<f> list) {
        this.f149b = -1;
        this.f150c = -1;
        this.f151d = false;
        this.f152e = new int[]{this.f149b, this.f150c};
        this.f153f = new ArrayList();
        this.g = new ArrayList();
        this.h = new HashSet<>();
        this.i = new HashSet<>();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.f148a = list;
    }

    public List<f> a(int i) {
        if (i == 0) {
            return this.f153f;
        }
        if (i == 1) {
            return this.g;
        }
        return null;
    }

    Set<f> b(int i) {
        if (i == 0) {
            return this.h;
        }
        if (i == 1) {
            return this.i;
        }
        return null;
    }

    void a(f fVar, int i) {
        if (i == 0) {
            this.h.add(fVar);
        } else if (i == 1) {
            this.i.add(fVar);
        }
    }

    void b() {
        int size = this.k.size();
        for (int i = 0; i < size; i++) {
            a(this.k.get(i));
        }
    }

    List<f> a() {
        if (!this.j.isEmpty()) {
            return this.j;
        }
        int size = this.f148a.size();
        for (int i = 0; i < size; i++) {
            f fVar = this.f148a.get(i);
            if (!fVar.b0) {
                a((ArrayList<f>) this.j, fVar);
            }
        }
        this.k.clear();
        this.k.addAll(this.f148a);
        this.k.removeAll(this.j);
        return this.j;
    }

    h(List<f> list, boolean z) {
        this.f149b = -1;
        this.f150c = -1;
        this.f151d = false;
        this.f152e = new int[]{this.f149b, this.f150c};
        this.f153f = new ArrayList();
        this.g = new ArrayList();
        this.h = new HashSet<>();
        this.i = new HashSet<>();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.f148a = list;
        this.f151d = z;
    }

    private void a(ArrayList<f> arrayList, f fVar) {
        if (fVar.d0) {
            return;
        }
        arrayList.add(fVar);
        fVar.d0 = true;
        if (fVar.y()) {
            return;
        }
        if (fVar instanceof j) {
            j jVar = (j) fVar;
            int i = jVar.l0;
            for (int i2 = 0; i2 < i; i2++) {
                a(arrayList, jVar.k0[i2]);
            }
        }
        int length = fVar.A.length;
        for (int i3 = 0; i3 < length; i3++) {
            e eVar = fVar.A[i3].f118d;
            if (eVar != null) {
                f fVar2 = eVar.f116b;
                if (eVar != null && fVar2 != fVar.k()) {
                    a(arrayList, fVar2);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0045  */
    private void a(f fVar) {
        e eVar;
        int iS;
        int iB;
        e eVar2;
        int iB2;
        if (!fVar.b0 || fVar.y()) {
            return;
        }
        boolean z = fVar.u.f118d != null;
        if (z) {
            eVar = fVar.u.f118d;
        } else {
            eVar = fVar.s.f118d;
        }
        if (eVar != null) {
            f fVar2 = eVar.f116b;
            if (!fVar2.c0) {
                a(fVar2);
            }
            e.d dVar = eVar.f117c;
            if (dVar == e.d.RIGHT) {
                f fVar3 = eVar.f116b;
                iS = fVar3.s() + fVar3.I;
            } else if (dVar == e.d.LEFT) {
                iS = eVar.f116b.I;
            } else {
                iS = 0;
            }
        } else {
            iS = 0;
        }
        if (z) {
            iB = iS - fVar.u.b();
        } else {
            iB = iS + fVar.s.b() + fVar.s();
        }
        fVar.a(iB - fVar.s(), iB);
        e eVar3 = fVar.w.f118d;
        if (eVar3 != null) {
            f fVar4 = eVar3.f116b;
            if (!fVar4.c0) {
                a(fVar4);
            }
            f fVar5 = eVar3.f116b;
            int i = (fVar5.J + fVar5.Q) - fVar.Q;
            fVar.e(i, fVar.F + i);
            fVar.c0 = true;
            return;
        }
        boolean z2 = fVar.v.f118d != null;
        if (z2) {
            eVar2 = fVar.v.f118d;
        } else {
            eVar2 = fVar.t.f118d;
        }
        if (eVar2 != null) {
            f fVar6 = eVar2.f116b;
            if (!fVar6.c0) {
                a(fVar6);
            }
            e.d dVar2 = eVar2.f117c;
            if (dVar2 == e.d.BOTTOM) {
                f fVar7 = eVar2.f116b;
                iB = fVar7.J + fVar7.i();
            } else if (dVar2 == e.d.TOP) {
                iB = eVar2.f116b.J;
            }
        }
        if (z2) {
            iB2 = iB - fVar.v.b();
        } else {
            iB2 = iB + fVar.t.b() + fVar.i();
        }
        fVar.e(iB2 - fVar.i(), iB2);
        fVar.c0 = true;
    }
}
