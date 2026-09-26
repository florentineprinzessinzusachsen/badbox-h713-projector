package v2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y1.c[] f2526a = new y1.c[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a3.h f2527b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a3.h f2528c;

    static {
        int i4 = 10;
        f2527b = new a3.h(i4, "NULL");
        f2528c = new a3.h(i4, "UNINITIALIZED");
    }

    public static final Object a(y1.h hVar, Object obj, Object obj2, i2.p pVar, y1.c cVar) {
        Object objF;
        Object objL = w2.a.l(hVar, obj2);
        try {
            s sVar = new s(cVar, hVar);
            if (pVar != null) {
                j2.q.a(2, pVar);
                objF = pVar.f(obj, sVar);
            } else {
                j2.i.e(pVar, "<this>");
                y1.h hVarG = sVar.g();
                Object bVar = hVarG == y1.i.f2726d ? new z1.b(sVar) : new z1.c(sVar, hVarG);
                j2.q.a(2, pVar);
                objF = pVar.f(obj, bVar);
            }
            w2.a.g(hVar, objL);
            if (objF == z1.a.f2781d) {
                j2.i.e(cVar, "frame");
            }
            return objF;
        } catch (Throwable th) {
            w2.a.g(hVar, objL);
            throw th;
        }
    }
}
