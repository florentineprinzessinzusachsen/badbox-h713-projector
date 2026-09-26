package v2;

import d0.u;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class d implements l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final y1.h f2529d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2530e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final t2.a f2531f;

    public d(y1.h hVar, int i4, t2.a aVar) {
        this.f2529d = hVar;
        this.f2530e = i4;
        this.f2531f = aVar;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0015  */
    @Override // v2.l
    public final u2.g a(y1.h hVar, int i4, t2.a aVar) {
        y1.h hVar2 = this.f2529d;
        y1.h hVarL = hVar.l(hVar2);
        t2.a aVar2 = t2.a.f2174d;
        t2.a aVar3 = this.f2531f;
        int i5 = this.f2530e;
        if (aVar == aVar2) {
            if (i5 != -3) {
                if (i4 == -3) {
                    i4 = i5;
                } else if (i5 != -2) {
                    if (i4 == -2) {
                        i4 = i5;
                    } else {
                        i4 += i5;
                        if (i4 < 0) {
                            i4 = Integer.MAX_VALUE;
                        }
                    }
                }
            }
            aVar = aVar3;
        }
        return (j2.i.a(hVarL, hVar2) && i4 == i5 && aVar == aVar3) ? this : d(hVarL, i4, aVar);
    }

    @Override // u2.g
    public Object b(u2.h hVar, y1.c cVar) {
        u uVar = new u(hVar, this, null, 7);
        w2.q qVar = new w2.q(cVar, cVar.g());
        Object objB = z1.d.b(qVar, qVar, uVar);
        return objB == z1.a.f2781d ? objB : u1.k.f2301a;
    }

    public abstract Object c(t2.s sVar, y1.c cVar);

    public abstract d d(y1.h hVar, int i4, t2.a aVar);

    public String toString() {
        ArrayList arrayList = new ArrayList(4);
        y1.i iVar = y1.i.f2726d;
        y1.h hVar = this.f2529d;
        if (hVar != iVar) {
            arrayList.add("context=" + hVar);
        }
        int i4 = this.f2530e;
        if (i4 != -3) {
            arrayList.add("capacity=" + i4);
        }
        t2.a aVar = t2.a.f2174d;
        t2.a aVar2 = this.f2531f;
        if (aVar2 != aVar) {
            arrayList.add("onBufferOverflow=" + aVar2);
        }
        return getClass().getSimpleName() + '[' + v1.j.y0(arrayList, ", ", null, null, null, 62) + ']';
    }
}
