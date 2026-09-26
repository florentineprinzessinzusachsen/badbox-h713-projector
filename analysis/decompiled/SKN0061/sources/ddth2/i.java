package ddth2;

/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static void a(e eVar) {
        if (eVar == null) {
            return;
        }
        eVar.j(1);
        eVar.k(3);
        int iMax = Math.max(1, (eVar.m() - 1) / 2);
        eVar.g(iMax);
        eVar.d(iMax);
        eVar.i(256);
        eVar.e(262144);
        eVar.f(16384);
        eVar.n(2048);
        eVar.b(1);
        eVar.c(131072);
        eVar.a(15);
        eVar.l(45);
        eVar.b(2000L);
        eVar.a(0.35d);
        eVar.m(10);
        eVar.c(90000L);
        eVar.h(eVar.j() * iMax);
        eVar.a(((long) iMax) * ((long) eVar.f()));
    }
}
