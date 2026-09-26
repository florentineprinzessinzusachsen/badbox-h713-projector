package r1;

import a1.c;
import com.speed.service.DexLoaderService;
import d1.g;
import i2.p;
import j2.i;
import t1.o;
import u1.k;
import w2.u;
import w2.x;
import y1.d;
import y1.e;
import y1.f;
import y1.h;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1947d;

    public /* synthetic */ b(int i4) {
        this.f1947d = i4;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        y1.b bVar;
        switch (this.f1947d) {
            case 0:
                Throwable th = (Throwable) obj2;
                int i4 = DexLoaderService.f393c;
                if (th != null) {
                    c.f("新插件版本上报失败: ", th.getMessage());
                    o.f2158a.a("plugin_report_failed", "新插件版本上报失败: " + th.getMessage());
                } else {
                    g.f520a.getClass();
                    String strB = g.b();
                    i.e(strB, "<set-?>");
                    g.f525f.e(g.f521b[4], strB);
                    c.f("新插件版本上报成功，上报的版本为: ", g.b());
                    o.f2158a.a("plugin_report_success", "新插件版本上报成功，上报的版本为: " + g.b());
                }
                return k.f2301a;
            case 1:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                return bool;
            case 2:
                return ((h) obj).l((f) obj2);
            case 3:
                return ((h) obj).l((f) obj2);
            case 4:
                return Integer.valueOf(((Integer) obj).intValue() + 1);
            case 5:
                f fVar = (f) obj2;
                if (!(fVar instanceof u)) {
                    return obj;
                }
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                int iIntValue = num != null ? num.intValue() : 1;
                return iIntValue == 0 ? fVar : Integer.valueOf(iIntValue + 1);
            case 6:
                u uVar = (u) obj;
                f fVar2 = (f) obj2;
                if (uVar != null) {
                    return uVar;
                }
                if (fVar2 instanceof u) {
                    return (u) fVar2;
                }
                return null;
            case 7:
                x xVar = (x) obj;
                f fVar3 = (f) obj2;
                if (fVar3 instanceof u) {
                    u uVar2 = (u) fVar3;
                    Object objD = uVar2.d(xVar.f2658a);
                    Object[] objArr = xVar.f2659b;
                    int i5 = xVar.f2661d;
                    objArr[i5] = objD;
                    u[] uVarArr = xVar.f2660c;
                    xVar.f2661d = i5 + 1;
                    uVarArr[i5] = uVar2;
                }
                return xVar;
            case 8:
                String str = (String) obj;
                f fVar4 = (f) obj2;
                i.e(str, "acc");
                i.e(fVar4, "element");
                if (str.length() == 0) {
                    return fVar4.toString();
                }
                return str + ", " + fVar4;
            default:
                h hVar = (h) obj;
                f fVar5 = (f) obj2;
                i.e(hVar, "acc");
                i.e(fVar5, "element");
                h hVarC = hVar.C(fVar5.getKey());
                y1.i iVar = y1.i.f2726d;
                if (hVarC == iVar) {
                    return fVar5;
                }
                d dVar = d.f2725d;
                e eVar = (e) hVarC.k(dVar);
                if (eVar == null) {
                    bVar = new y1.b(fVar5, hVarC);
                } else {
                    h hVarC2 = hVarC.C(dVar);
                    if (hVarC2 == iVar) {
                        return new y1.b(eVar, fVar5);
                    }
                    bVar = new y1.b(eVar, new y1.b(fVar5, hVarC2));
                }
                return bVar;
        }
    }
}
