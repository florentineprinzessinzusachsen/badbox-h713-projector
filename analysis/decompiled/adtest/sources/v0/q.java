package v0;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class q extends s0.b0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o f2475c = new o(1, s0.z.f2137d);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s0.n f2476a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s0.z f2477b;

    public q(s0.n nVar, s0.z zVar) {
        this.f2476a = nVar;
        this.f2477b = zVar;
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        Object arrayList;
        Serializable arrayList2;
        int iF0 = bVar.f0();
        int iA = o.e.a(iF0);
        if (iA == 0) {
            bVar.b();
            arrayList = new ArrayList();
        } else if (iA != 2) {
            arrayList = null;
        } else {
            bVar.c();
            arrayList = new u0.p(true);
        }
        if (arrayList == null) {
            return d(bVar, iF0);
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (bVar.S()) {
                String strZ = arrayList instanceof Map ? bVar.Z() : null;
                int iF1 = bVar.f0();
                int iA2 = o.e.a(iF1);
                if (iA2 == 0) {
                    bVar.b();
                    arrayList2 = new ArrayList();
                } else if (iA2 != 2) {
                    arrayList2 = null;
                } else {
                    bVar.c();
                    arrayList2 = new u0.p(true);
                }
                boolean z3 = arrayList2 != null;
                if (arrayList2 == null) {
                    arrayList2 = d(bVar, iF1);
                }
                if (arrayList instanceof List) {
                    ((List) arrayList).add(arrayList2);
                } else {
                    ((Map) arrayList).put(strZ, arrayList2);
                }
                if (z3) {
                    arrayDeque.addLast(arrayList);
                    arrayList = arrayList2;
                }
            } else {
                if (arrayList instanceof List) {
                    bVar.A();
                } else {
                    bVar.C();
                }
                if (arrayDeque.isEmpty()) {
                    return arrayList;
                }
                arrayList = arrayDeque.removeLast();
            }
        }
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        if (obj == null) {
            dVar.S();
            return;
        }
        Class<?> cls = obj.getClass();
        s0.n nVar = this.f2476a;
        nVar.getClass();
        s0.b0 b0VarC = nVar.c(new z0.a(cls));
        if (!(b0VarC instanceof q)) {
            b0VarC.c(dVar, obj);
        } else {
            dVar.k();
            dVar.C();
        }
    }

    public final Serializable d(a1.b bVar, int i4) throws IOException {
        int iA = o.e.a(i4);
        if (iA == 5) {
            return bVar.d0();
        }
        if (iA == 6) {
            return this.f2477b.a(bVar);
        }
        if (iA == 7) {
            return Boolean.valueOf(bVar.V());
        }
        if (iA != 8) {
            throw new IllegalStateException("Unexpected token: ".concat(a1.c.h(i4)));
        }
        bVar.b0();
        return null;
    }
}
