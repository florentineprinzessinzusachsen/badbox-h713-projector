package i0;

import android.os.Build;
import d0.a0;
import d0.b0;
import h0.h;
import j0.g;
import j2.i;
import l0.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f extends b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f1209c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1210b;

    static {
        String strG = a0.g("NetworkNotRoamingCtrlr");
        i.d(strG, "tagWithPrefix(...)");
        f1209c = strG;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(g gVar) {
        super(gVar);
        i.e(gVar, "tracker");
        this.f1210b = 7;
    }

    @Override // i0.d
    public final boolean a(p pVar) {
        i.e(pVar, "workSpec");
        return pVar.f1340j.f433a == b0.f420g;
    }

    @Override // i0.b
    public final int d() {
        return this.f1210b;
    }

    @Override // i0.b
    public final boolean e(Object obj) {
        h hVar = (h) obj;
        i.e(hVar, "value");
        boolean z3 = hVar.f1013e;
        boolean z4 = hVar.f1009a;
        if (Build.VERSION.SDK_INT >= 24) {
            return (z4 && hVar.f1012d && !z3) ? false : true;
        }
        a0.e().a(f1209c, "Not-roaming network constraint is not supported before API 24, only checking for connected state.");
        return !z4 || z3;
    }
}
