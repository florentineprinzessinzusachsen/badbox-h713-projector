package ddth2;

import java.util.concurrent.ThreadLocalRandom;

/* JADX INFO: loaded from: classes.dex */
public final class i2 {
    public static long a(e eVar) {
        if (eVar == null) {
            return 0L;
        }
        return System.currentTimeMillis() + Math.max(0L, eVar.m42e());
    }

    public static long a(e eVar, int i) {
        if (eVar == null || i <= 0) {
            return 0L;
        }
        long jMax = Math.max(1L, eVar.m37c());
        long jMin = (long) Math.min(Math.max(jMax, eVar.m40d()), jMax * Math.pow(Math.max(1.0d, eVar.m25a()), i - 1));
        double dMin = Math.min(0.95d, Math.max(0.0d, eVar.m31b()));
        return jMin + (dMin > 0.0d ? ThreadLocalRandom.current().nextLong(0L, ((long) (jMin * dMin)) + 1) : 0L);
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public static boolean m59a(e eVar, int i) {
        int iO;
        return eVar != null && (iO = eVar.o()) > 0 && i >= iO;
    }

    public static long b(e eVar, int i) {
        return System.currentTimeMillis() + a(eVar, i);
    }
}
