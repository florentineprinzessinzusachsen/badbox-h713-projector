package i0;

import android.os.Build;
import d0.b0;
import h0.h;
import j0.g;
import j2.i;
import l0.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1205b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1206c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(g gVar, int i4) {
        super(gVar);
        this.f1205b = i4;
        switch (i4) {
            case 2:
                i.e(gVar, "tracker");
                super(gVar);
                this.f1206c = 7;
                break;
            case 3:
                i.e(gVar, "tracker");
                super(gVar);
                this.f1206c = 7;
                break;
            case 4:
                i.e(gVar, "tracker");
                super(gVar);
                this.f1206c = 9;
                break;
            default:
                i.e(gVar, "tracker");
                this.f1206c = 6;
                break;
        }
    }

    @Override // i0.d
    public final boolean a(p pVar) {
        switch (this.f1205b) {
            case 0:
                i.e(pVar, "workSpec");
                return pVar.f1340j.f435c;
            case 1:
                i.e(pVar, "workSpec");
                return pVar.f1340j.f437e;
            case 2:
                i.e(pVar, "workSpec");
                return pVar.f1340j.f433a == b0.f418e;
            case 3:
                i.e(pVar, "workSpec");
                b0 b0Var = pVar.f1340j.f433a;
                return b0Var == b0.f419f || (Build.VERSION.SDK_INT >= 30 && b0Var == b0.f422i);
            default:
                i.e(pVar, "workSpec");
                return pVar.f1340j.f438f;
        }
    }

    @Override // i0.b
    public final int d() {
        switch (this.f1205b) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        return this.f1206c;
    }

    @Override // i0.b
    public final boolean e(Object obj) {
        boolean zBooleanValue;
        switch (this.f1205b) {
            case 0:
                zBooleanValue = ((Boolean) obj).booleanValue();
                break;
            case 1:
                zBooleanValue = ((Boolean) obj).booleanValue();
                break;
            case 2:
                h hVar = (h) obj;
                i.e(hVar, "value");
                return hVar.f1013e || !hVar.f1009a || (Build.VERSION.SDK_INT >= 26 && !hVar.f1010b);
            case 3:
                h hVar2 = (h) obj;
                i.e(hVar2, "value");
                return !hVar2.f1009a || hVar2.f1011c || hVar2.f1013e;
            default:
                zBooleanValue = ((Boolean) obj).booleanValue();
                break;
        }
        return !zBooleanValue;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(j0.a aVar) {
        super(aVar);
        this.f1205b = 1;
        i.e(aVar, "tracker");
        this.f1206c = 5;
    }
}
