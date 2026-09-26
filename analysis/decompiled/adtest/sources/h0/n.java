package h0;

import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n extends a2.i implements i2.q {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1024h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ u2.h f1025i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public /* synthetic */ Object[] f1026j;

    @Override // i2.q
    public final Object d(Object obj, Object obj2, Object obj3) {
        n nVar = new n(3, (y1.c) obj3);
        nVar.f1025i = (u2.h) obj;
        nVar.f1026j = (Object[]) obj2;
        return nVar.l(u1.k.f2301a);
    }

    @Override // a2.a
    public final Object l(Object obj) {
        c cVar;
        c cVar2;
        int i4 = this.f1024h;
        if (i4 == 0) {
            l0.M(obj);
            u2.h hVar = this.f1025i;
            c[] cVarArr = (c[]) this.f1026j;
            int length = cVarArr.length;
            int i5 = 0;
            while (true) {
                cVar = a.f996a;
                if (i5 >= length) {
                    cVar2 = null;
                    break;
                }
                cVar2 = cVarArr[i5];
                if (!j2.i.a(cVar2, cVar)) {
                    break;
                }
                i5++;
            }
            if (cVar2 != null) {
                cVar = cVar2;
            }
            this.f1024h = 1;
            Object objC = hVar.c(cVar, this);
            z1.a aVar = z1.a.f2781d;
            if (objC == aVar) {
                return aVar;
            }
        } else {
            if (i4 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            l0.M(obj);
        }
        return u1.k.f2301a;
    }
}
