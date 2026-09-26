package a.c.a.j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Analyzer.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static void a(g gVar) {
        if ((gVar.M() & 32) != 32) {
            b(gVar);
            return;
        }
        gVar.D0 = true;
        gVar.x0 = false;
        gVar.y0 = false;
        gVar.z0 = false;
        ArrayList<f> arrayList = gVar.k0;
        List<h> list = gVar.w0;
        boolean z = gVar.j() == f.b.WRAP_CONTENT;
        boolean z2 = gVar.q() == f.b.WRAP_CONTENT;
        boolean z3 = z || z2;
        list.clear();
        for (f fVar : arrayList) {
            fVar.p = null;
            fVar.d0 = false;
            fVar.F();
        }
        for (f fVar2 : arrayList) {
            if (fVar2.p == null && !a(fVar2, list, z3)) {
                b(gVar);
                gVar.D0 = false;
                return;
            }
        }
        int iMax = 0;
        int iMax2 = 0;
        for (h hVar : list) {
            iMax = Math.max(iMax, a(hVar, 0));
            iMax2 = Math.max(iMax2, a(hVar, 1));
        }
        if (z) {
            gVar.a(f.b.FIXED);
            gVar.o(iMax);
            gVar.x0 = true;
            gVar.y0 = true;
            gVar.A0 = iMax;
        }
        if (z2) {
            gVar.b(f.b.FIXED);
            gVar.g(iMax2);
            gVar.x0 = true;
            gVar.z0 = true;
            gVar.B0 = iMax2;
        }
        a(list, 0, gVar.s());
        a(list, 1, gVar.i());
    }

    private static void b(g gVar) {
        gVar.w0.clear();
        gVar.w0.add(0, new h(gVar.k0));
    }

    private static boolean a(f fVar, List<h> list, boolean z) {
        h hVar = new h(new ArrayList(), true);
        list.add(hVar);
        return a(fVar, hVar, list, z);
    }

    /* JADX WARN: Code duplicated, block: B:123:0x0183  */
    /* JADX WARN: Code duplicated, block: B:92:0x012a  */
    private static boolean a(f fVar, h hVar, List<h> list, boolean z) {
        e eVar;
        e eVar2;
        e eVar3;
        e eVar4;
        e eVar5;
        e eVar6;
        if (fVar == null) {
            return true;
        }
        fVar.c0 = false;
        g gVar = (g) fVar.k();
        h hVar2 = fVar.p;
        if (hVar2 != null) {
            if (hVar2 != hVar) {
                hVar.f148a.addAll(hVar2.f148a);
                hVar.f153f.addAll(fVar.p.f153f);
                hVar.g.addAll(fVar.p.g);
                if (!fVar.p.f151d) {
                    hVar.f151d = false;
                }
                list.remove(fVar.p);
                Iterator<f> it = fVar.p.f148a.iterator();
                while (it.hasNext()) {
                    it.next().p = hVar;
                }
            }
            return true;
        }
        fVar.b0 = true;
        hVar.f148a.add(fVar);
        fVar.p = hVar;
        if (fVar.s.f118d == null && fVar.u.f118d == null && fVar.t.f118d == null && fVar.v.f118d == null && fVar.w.f118d == null && fVar.z.f118d == null) {
            a(gVar, fVar, hVar);
            if (z) {
                return false;
            }
        }
        if (fVar.t.f118d != null && fVar.v.f118d != null) {
            gVar.q();
            f.b bVar = f.b.WRAP_CONTENT;
            if (z) {
                a(gVar, fVar, hVar);
                return false;
            }
            if (fVar.t.f118d.f116b != fVar.k() || fVar.v.f118d.f116b != fVar.k()) {
                a(gVar, fVar, hVar);
            }
        }
        if (fVar.s.f118d != null && fVar.u.f118d != null) {
            gVar.j();
            f.b bVar2 = f.b.WRAP_CONTENT;
            if (z) {
                a(gVar, fVar, hVar);
                return false;
            }
            if (fVar.s.f118d.f116b != fVar.k() || fVar.u.f118d.f116b != fVar.k()) {
                a(gVar, fVar, hVar);
            }
        }
        if (((fVar.j() == f.b.MATCH_CONSTRAINT) ^ (fVar.q() == f.b.MATCH_CONSTRAINT)) && fVar.G != 0.0f) {
            a(fVar);
        } else if (fVar.j() == f.b.MATCH_CONSTRAINT || fVar.q() == f.b.MATCH_CONSTRAINT) {
            a(gVar, fVar, hVar);
            if (z) {
                return false;
            }
        }
        if ((fVar.s.f118d != null || fVar.u.f118d != null) && (((eVar = fVar.s.f118d) == null || eVar.f116b != fVar.D || fVar.u.f118d != null) && ((eVar2 = fVar.u.f118d) == null || eVar2.f116b != fVar.D || fVar.s.f118d != null))) {
            e eVar7 = fVar.s.f118d;
            if (eVar7 != null) {
                f fVar2 = eVar7.f116b;
                f fVar3 = fVar.D;
                if (fVar2 == fVar3 && (eVar3 = fVar.u.f118d) != null && eVar3.f116b == fVar3) {
                    if (fVar.z.f118d == null && !(fVar instanceof i) && !(fVar instanceof j)) {
                        hVar.f153f.add(fVar);
                    }
                }
            }
        } else if (fVar.z.f118d == null) {
            hVar.f153f.add(fVar);
        }
        if ((fVar.t.f118d != null || fVar.v.f118d != null) && (((eVar4 = fVar.t.f118d) == null || eVar4.f116b != fVar.D || fVar.v.f118d != null) && ((eVar5 = fVar.v.f118d) == null || eVar5.f116b != fVar.D || fVar.t.f118d != null))) {
            e eVar8 = fVar.t.f118d;
            if (eVar8 != null) {
                f fVar4 = eVar8.f116b;
                f fVar5 = fVar.D;
                if (fVar4 == fVar5 && (eVar6 = fVar.v.f118d) != null && eVar6.f116b == fVar5) {
                    if (fVar.z.f118d == null && fVar.w.f118d == null && !(fVar instanceof i) && !(fVar instanceof j)) {
                        hVar.g.add(fVar);
                    }
                }
            }
        } else if (fVar.z.f118d == null) {
            hVar.g.add(fVar);
        }
        if (fVar instanceof j) {
            a(gVar, fVar, hVar);
            if (z) {
                return false;
            }
            j jVar = (j) fVar;
            for (int i = 0; i < jVar.l0; i++) {
                if (!a(jVar.k0[i], hVar, list, z)) {
                    return false;
                }
            }
        }
        int length = fVar.A.length;
        for (int i2 = 0; i2 < length; i2++) {
            e eVar9 = fVar.A[i2];
            e eVar10 = eVar9.f118d;
            if (eVar10 != null && eVar10.f116b != fVar.k()) {
                if (eVar9.f117c == e.d.CENTER) {
                    a(gVar, fVar, hVar);
                    if (z) {
                        return false;
                    }
                } else {
                    a(eVar9);
                }
                if (!a(eVar9.f118d.f116b, hVar, list, z)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void a(g gVar, f fVar, h hVar) {
        hVar.f151d = false;
        gVar.D0 = false;
        fVar.b0 = false;
    }

    private static int a(h hVar, int i) {
        int i2 = i * 2;
        List<f> listA = hVar.a(i);
        int size = listA.size();
        int iMax = 0;
        for (int i3 = 0; i3 < size; i3++) {
            f fVar = listA.get(i3);
            e[] eVarArr = fVar.A;
            int i4 = i2 + 1;
            iMax = Math.max(iMax, a(fVar, i, eVarArr[i4].f118d == null || !(eVarArr[i2].f118d == null || eVarArr[i4].f118d == null), 0));
        }
        hVar.f152e[i] = iMax;
        return iMax;
    }

    private static int a(f fVar, int i, boolean z, int i2) {
        int i3;
        int iC;
        int i4;
        int i5;
        int i6;
        int i7;
        int iS;
        int i8;
        int i9;
        int iMax = 0;
        if (!fVar.b0) {
            return 0;
        }
        boolean z2 = fVar.w.f118d != null && i == 1;
        if (z) {
            i3 = fVar.c();
            iC = fVar.i() - fVar.c();
            i5 = i * 2;
            i4 = i5 + 1;
        } else {
            i3 = fVar.i() - fVar.c();
            iC = fVar.c();
            i4 = i * 2;
            i5 = i4 + 1;
        }
        e[] eVarArr = fVar.A;
        if (eVarArr[i4].f118d == null || eVarArr[i5].f118d != null) {
            i6 = i4;
            i7 = 1;
        } else {
            i6 = i5;
            i5 = i4;
            i7 = -1;
        }
        int i10 = z2 ? i2 - i3 : i2;
        int iB = (fVar.A[i5].b() * i7) + a(fVar, i);
        int i11 = i10 + iB;
        int iS2 = (i == 0 ? fVar.s() : fVar.i()) * i7;
        Iterator<o> it = fVar.A[i5].d().f161a.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, a(((m) it.next()).f156c.f116b, i, z, i11));
        }
        int iMax2 = 0;
        for (Iterator<o> it2 = fVar.A[i6].d().f161a.iterator(); it2.hasNext(); it2 = it2) {
            iMax2 = Math.max(iMax2, a(((m) it2.next()).f156c.f116b, i, z, iS2 + i11));
        }
        if (z2) {
            iMax -= i3;
            iS = iMax2 + iC;
        } else {
            iS = iMax2 + ((i == 0 ? fVar.s() : fVar.i()) * i7);
        }
        int i12 = 1;
        if (i == 1) {
            Iterator<o> it3 = fVar.w.d().f161a.iterator();
            int iMax3 = 0;
            while (it3.hasNext()) {
                Iterator<o> it4 = it3;
                m mVar = (m) it3.next();
                if (i7 == i12) {
                    iMax3 = Math.max(iMax3, a(mVar.f156c.f116b, i, z, i3 + i11));
                } else {
                    iMax3 = Math.max(iMax3, a(mVar.f156c.f116b, i, z, (iC * i7) + i11));
                }
                it3 = it4;
                i6 = i6;
                i12 = 1;
            }
            i8 = i6;
            int i13 = iMax3;
            i9 = (fVar.w.d().f161a.size() <= 0 || z2) ? i13 : i7 == 1 ? i13 + i3 : i13 - iC;
        } else {
            i8 = i6;
            i9 = 0;
        }
        int iMax4 = iB + Math.max(iMax, Math.max(iS, i9));
        int i14 = i11 + iS2;
        if (i7 != -1) {
            i11 = i14;
            i14 = i11;
        }
        if (z) {
            k.a(fVar, i, i14);
            fVar.a(i14, i11, i);
        } else {
            fVar.p.a(fVar, i);
            fVar.d(i14, i);
        }
        if (fVar.c(i) == f.b.MATCH_CONSTRAINT && fVar.G != 0.0f) {
            fVar.p.a(fVar, i);
        }
        e[] eVarArr2 = fVar.A;
        if (eVarArr2[i5].f118d != null && eVarArr2[i8].f118d != null) {
            f fVarK = fVar.k();
            e[] eVarArr3 = fVar.A;
            if (eVarArr3[i5].f118d.f116b == fVarK && eVarArr3[i8].f118d.f116b == fVarK) {
                fVar.p.a(fVar, i);
            }
        }
        return iMax4;
    }

    private static void a(e eVar) {
        m mVarD = eVar.d();
        e eVar2 = eVar.f118d;
        if (eVar2 == null || eVar2.f118d == eVar) {
            return;
        }
        eVar2.d().a(mVarD);
    }

    public static void a(List<h> list, int i, int i2) {
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            for (f fVar : list.get(i3).b(i)) {
                if (fVar.b0) {
                    a(fVar, i, i2);
                }
            }
        }
    }

    private static void a(f fVar, int i, int i2) {
        int i3 = i * 2;
        e[] eVarArr = fVar.A;
        e eVar = eVarArr[i3];
        e eVar2 = eVarArr[i3 + 1];
        if ((eVar.f118d == null || eVar2.f118d == null) ? false : true) {
            k.a(fVar, i, a(fVar, i) + eVar.b());
            return;
        }
        if (fVar.G != 0.0f && fVar.c(i) == f.b.MATCH_CONSTRAINT) {
            int iA = a(fVar);
            int i4 = (int) fVar.A[i3].d().g;
            eVar2.d().f159f = eVar.d();
            eVar2.d().g = iA;
            eVar2.d().f162b = 1;
            fVar.a(i4, i4 + iA, i);
            return;
        }
        int iE = i2 - fVar.e(i);
        int iD = iE - fVar.d(i);
        fVar.a(iD, iE, i);
        k.a(fVar, i, iD);
    }

    private static int a(f fVar, int i) {
        e eVar;
        int i2 = i * 2;
        e[] eVarArr = fVar.A;
        e eVar2 = eVarArr[i2];
        e eVar3 = eVarArr[i2 + 1];
        e eVar4 = eVar2.f118d;
        if (eVar4 == null) {
            return 0;
        }
        f fVar2 = eVar4.f116b;
        f fVar3 = fVar.D;
        if (fVar2 != fVar3 || (eVar = eVar3.f118d) == null || eVar.f116b != fVar3) {
            return 0;
        }
        return (int) ((((fVar3.d(i) - eVar2.b()) - eVar3.b()) - fVar.d(i)) * (i == 0 ? fVar.V : fVar.W));
    }

    private static int a(f fVar) {
        float fS;
        float fI;
        if (fVar.j() == f.b.MATCH_CONSTRAINT) {
            if (fVar.H == 0) {
                fI = fVar.i() * fVar.G;
            } else {
                fI = fVar.i() / fVar.G;
            }
            int i = (int) fI;
            fVar.o(i);
            return i;
        }
        if (fVar.q() != f.b.MATCH_CONSTRAINT) {
            return -1;
        }
        if (fVar.H == 1) {
            fS = fVar.s() * fVar.G;
        } else {
            fS = fVar.s() / fVar.G;
        }
        int i2 = (int) fS;
        fVar.g(i2);
        return i2;
    }
}
