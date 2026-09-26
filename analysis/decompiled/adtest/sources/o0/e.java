package o0;

import d0.l0;
import j2.n;
import u1.k;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e implements u2.h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1552d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f1553e;

    public /* synthetic */ e(int i4, Object obj) {
        this.f1552d = i4;
        this.f1553e = obj;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0024  */
    @Override // u2.h
    public final Object c(Object obj, y1.c cVar) {
        d dVar;
        switch (this.f1552d) {
            case 0:
                if (cVar instanceof d) {
                    dVar = (d) cVar;
                    int i4 = dVar.f1550h;
                    if ((i4 & Integer.MIN_VALUE) != 0) {
                        dVar.f1550h = i4 - Integer.MIN_VALUE;
                    } else {
                        dVar = new d(this, cVar);
                    }
                } else {
                    dVar = new d(this, cVar);
                }
                Object obj2 = dVar.f1549g;
                int i5 = dVar.f1550h;
                if (i5 == 0) {
                    l0.M(obj2);
                    u2.h hVar = (u2.h) this.f1553e;
                    if (obj instanceof h0.b) {
                        dVar.f1550h = 1;
                        Object objC = hVar.c(obj, dVar);
                        z1.a aVar = z1.a.f2781d;
                        if (objC == aVar) {
                            return aVar;
                        }
                    }
                } else {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj2);
                }
                return k.f2301a;
            default:
                ((n) this.f1553e).f1276d = obj;
                throw new v2.a(this);
        }
    }
}
