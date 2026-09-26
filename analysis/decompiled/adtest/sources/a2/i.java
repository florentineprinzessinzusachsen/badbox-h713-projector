package a2;

import j2.o;
import j2.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class i extends c implements j2.g {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f50g;

    public i(int i4, y1.c cVar) {
        super(cVar);
        this.f50g = i4;
    }

    @Override // j2.g
    public final int b() {
        return this.f50g;
    }

    @Override // a2.a
    public final String toString() {
        if (this.f40d != null) {
            return super.toString();
        }
        o.f1277a.getClass();
        String strA = p.a(this);
        j2.i.d(strA, "renderLambdaToString(...)");
        return strA;
    }
}
