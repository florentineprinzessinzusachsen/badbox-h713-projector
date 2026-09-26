package v0;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class m extends s0.b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m f2466a = new m();

    private m() {
    }

    public static s0.q d(a1.b bVar, int i4) throws IOException {
        int iA = o.e.a(i4);
        if (iA == 5) {
            return new s0.u(bVar.d0());
        }
        if (iA == 6) {
            return new s0.u(new u0.k(bVar.d0()));
        }
        if (iA == 7) {
            return new s0.u(Boolean.valueOf(bVar.V()));
        }
        if (iA != 8) {
            throw new IllegalStateException("Unexpected token: ".concat(a1.c.h(i4)));
        }
        bVar.b0();
        return s0.s.f2134d;
    }

    public static void e(a1.d dVar, s0.q qVar) throws IOException {
        if (qVar == null || (qVar instanceof s0.s)) {
            dVar.S();
            return;
        }
        if (qVar instanceof s0.u) {
            s0.u uVarA = qVar.a();
            Serializable serializable = uVarA.f2136d;
            if (serializable instanceof Number) {
                dVar.Z(uVarA.d());
                return;
            } else if (serializable instanceof Boolean) {
                dVar.b0(serializable instanceof Boolean ? ((Boolean) serializable).booleanValue() : Boolean.parseBoolean(uVarA.b()));
                return;
            } else {
                dVar.a0(uVarA.b());
                return;
            }
        }
        boolean z3 = qVar instanceof s0.p;
        if (z3) {
            dVar.c();
            if (!z3) {
                throw new IllegalStateException("Not a JSON Array: " + qVar);
            }
            ArrayList arrayList = ((s0.p) qVar).f2133d;
            int size = arrayList.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayList.get(i4);
                i4++;
                e(dVar, (s0.q) obj);
            }
            dVar.A();
            return;
        }
        boolean z4 = qVar instanceof s0.t;
        if (!z4) {
            throw new IllegalArgumentException("Couldn't write " + qVar.getClass());
        }
        dVar.k();
        if (!z4) {
            throw new IllegalStateException("Not a JSON Object: " + qVar);
        }
        Iterator it = ((u0.n) ((s0.t) qVar).f2135d.entrySet()).iterator();
        while (((u0.m) it).hasNext()) {
            u0.o oVarB = ((u0.m) it).b();
            dVar.J((String) oVarB.getKey());
            e(dVar, (s0.q) oVarB.getValue());
        }
        dVar.C();
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        s0.q pVar;
        s0.q pVar2;
        int iF0 = bVar.f0();
        int iA = o.e.a(iF0);
        if (iA == 0) {
            bVar.b();
            pVar = new s0.p();
        } else if (iA != 2) {
            pVar = null;
        } else {
            bVar.c();
            pVar = new s0.t();
        }
        if (pVar == null) {
            return d(bVar, iF0);
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (bVar.S()) {
                String strZ = pVar instanceof s0.t ? bVar.Z() : null;
                int iF1 = bVar.f0();
                int iA2 = o.e.a(iF1);
                if (iA2 == 0) {
                    bVar.b();
                    pVar2 = new s0.p();
                } else if (iA2 != 2) {
                    pVar2 = null;
                } else {
                    bVar.c();
                    pVar2 = new s0.t();
                }
                boolean z3 = pVar2 != null;
                if (pVar2 == null) {
                    pVar2 = d(bVar, iF1);
                }
                if (pVar instanceof s0.p) {
                    ((s0.p) pVar).f2133d.add(pVar2);
                } else {
                    ((s0.t) pVar).f2135d.put(strZ, pVar2);
                }
                if (z3) {
                    arrayDeque.addLast(pVar);
                    pVar = pVar2;
                }
            } else {
                if (pVar instanceof s0.p) {
                    bVar.A();
                } else {
                    bVar.C();
                }
                if (arrayDeque.isEmpty()) {
                    return pVar;
                }
                pVar = (s0.q) arrayDeque.removeLast();
            }
        }
    }

    @Override // s0.b0
    public final /* bridge */ /* synthetic */ void c(a1.d dVar, Object obj) throws IOException {
        e(dVar, (s0.q) obj);
    }
}
