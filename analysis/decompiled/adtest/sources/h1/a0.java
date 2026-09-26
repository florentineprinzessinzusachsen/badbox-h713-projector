package h1;

import java.lang.reflect.Type;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a0 implements i2.l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1034d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f1035e;

    public /* synthetic */ a0(int i4, Object obj) {
        this.f1034d = i4;
        this.f1035e = obj;
    }

    @Override // i2.l
    public final Object h(Object obj) {
        switch (this.f1034d) {
            case 0:
                String str = (String) obj;
                return a1.c.e(str, "it").b(str, (Type) this.f1035e);
            default:
                r2.i iVar = (r2.i) this.f1035e;
                u1.k kVar = u1.k.f2301a;
                iVar.j(kVar);
                return kVar;
        }
    }
}
