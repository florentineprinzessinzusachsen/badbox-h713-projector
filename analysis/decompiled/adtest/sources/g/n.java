package g;

import j2.p;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n implements i2.l, j2.g, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ r0.a f945d;

    public n(r0.a aVar) {
        this.f945d = aVar;
    }

    @Override // j2.g
    public final int b() {
        return 1;
    }

    @Override // i2.l
    public final Object h(Object obj) {
        this.f945d.cancel(false);
        return u1.k.f2301a;
    }

    public final String toString() {
        j2.o.f1277a.getClass();
        String strA = p.a(this);
        j2.i.d(strA, "renderLambdaToString(...)");
        return strA;
    }
}
