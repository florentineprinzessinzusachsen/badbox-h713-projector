package e;

/* JADX INFO: compiled from: SegmentPool.java */
/* JADX INFO: loaded from: classes.dex */
final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static o f4765a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static long f4766b;

    private p() {
    }

    static o a() {
        synchronized (p.class) {
            if (f4765a == null) {
                return new o();
            }
            o oVar = f4765a;
            f4765a = oVar.f4764f;
            oVar.f4764f = null;
            f4766b -= 8192;
            return oVar;
        }
    }

    static void a(o oVar) {
        if (oVar.f4764f == null && oVar.g == null) {
            if (oVar.f4762d) {
                return;
            }
            synchronized (p.class) {
                if (f4766b + 8192 > 65536) {
                    return;
                }
                f4766b += 8192;
                oVar.f4764f = f4765a;
                oVar.f4761c = 0;
                oVar.f4760b = 0;
                f4765a = oVar;
                return;
            }
        }
        throw new IllegalArgumentException();
    }
}
