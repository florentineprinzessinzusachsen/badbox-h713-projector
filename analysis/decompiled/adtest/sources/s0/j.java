package s0;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j extends b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2092a;

    public /* synthetic */ j(int i4) {
        this.f2092a = i4;
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        switch (this.f2092a) {
            case 0:
                if (bVar.f0() != 9) {
                    return Double.valueOf(bVar.W());
                }
                bVar.b0();
                return null;
            case 1:
                if (bVar.f0() != 9) {
                    return Float.valueOf((float) bVar.W());
                }
                bVar.b0();
                return null;
            default:
                bVar.m0();
                return null;
        }
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        switch (this.f2092a) {
            case 0:
                Number number = (Number) obj;
                if (number != null) {
                    double dDoubleValue = number.doubleValue();
                    n.a(dDoubleValue);
                    dVar.X(dDoubleValue);
                } else {
                    dVar.S();
                }
                break;
            case 1:
                Number numberValueOf = (Number) obj;
                if (numberValueOf != null) {
                    float fFloatValue = numberValueOf.floatValue();
                    n.a(fFloatValue);
                    if (!(numberValueOf instanceof Float)) {
                        numberValueOf = Float.valueOf(fFloatValue);
                    }
                    dVar.Z(numberValueOf);
                } else {
                    dVar.S();
                }
                break;
            default:
                dVar.S();
                break;
        }
    }

    public String toString() {
        switch (this.f2092a) {
            case 2:
                return "AnonymousOrNonStaticLocalClassAdapter";
            default:
                return super.toString();
        }
    }
}
