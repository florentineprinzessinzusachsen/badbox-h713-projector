package c.a.b0.j;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: EndConsumerHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static boolean a(c.a.y.b bVar, c.a.y.b bVar2, Class<?> cls) {
        c.a.b0.b.b.a(bVar2, "next is null");
        if (bVar == null) {
            return true;
        }
        bVar2.dispose();
        if (bVar == c.a.b0.a.c.DISPOSED) {
            return false;
        }
        a(cls);
        return false;
    }

    public static boolean a(AtomicReference<c.a.y.b> atomicReference, c.a.y.b bVar, Class<?> cls) {
        c.a.b0.b.b.a(bVar, "next is null");
        if (atomicReference.compareAndSet(null, bVar)) {
            return true;
        }
        bVar.dispose();
        if (atomicReference.get() == c.a.b0.a.c.DISPOSED) {
            return false;
        }
        a(cls);
        return false;
    }

    public static String a(String str) {
        return "It is not allowed to subscribe with a(n) " + str + " multiple times. Please create a fresh instance of " + str + " and subscribe that to the target source instead.";
    }

    public static void a(Class<?> cls) {
        c.a.e0.a.b(new c.a.z.e(a(cls.getName())));
    }
}
