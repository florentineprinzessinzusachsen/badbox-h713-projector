package ddth2;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class e2 {
    public static volatile e a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public static final AtomicInteger f64a = new AtomicInteger(0);

    public static void a() {
        AtomicInteger atomicInteger;
        int i;
        do {
            atomicInteger = f64a;
            i = atomicInteger.get();
        } while (!atomicInteger.compareAndSet(i, i > 0 ? i - 1 : 0));
    }

    public static void a(e eVar) {
        a = eVar;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public static boolean m45a() {
        AtomicInteger atomicInteger;
        int i;
        e eVar = a;
        if (eVar == null) {
            return true;
        }
        int iH = eVar.h();
        do {
            atomicInteger = f64a;
            i = atomicInteger.get();
            if (i >= iH) {
                return false;
            }
        } while (!atomicInteger.compareAndSet(i, i + 1));
        return true;
    }

    public static boolean a(int i) {
        e eVar = a;
        return eVar == null || i < eVar.e();
    }
}
