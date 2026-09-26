package c.a.b0.a;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: DisposableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public enum c implements c.a.y.b {
    DISPOSED;

    public static boolean a(c.a.y.b bVar) {
        return bVar == DISPOSED;
    }

    public static boolean b(AtomicReference<c.a.y.b> atomicReference, c.a.y.b bVar) {
        c.a.y.b bVar2;
        do {
            bVar2 = atomicReference.get();
            if (bVar2 == DISPOSED) {
                if (bVar == null) {
                    return false;
                }
                bVar.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(bVar2, bVar));
        if (bVar2 == null) {
            return true;
        }
        bVar2.dispose();
        return true;
    }

    public static boolean c(AtomicReference<c.a.y.b> atomicReference, c.a.y.b bVar) {
        c.a.b0.b.b.a(bVar, "d is null");
        if (atomicReference.compareAndSet(null, bVar)) {
            return true;
        }
        bVar.dispose();
        if (atomicReference.get() == DISPOSED) {
            return false;
        }
        a();
        return false;
    }

    public static boolean d(AtomicReference<c.a.y.b> atomicReference, c.a.y.b bVar) {
        if (atomicReference.compareAndSet(null, bVar)) {
            return true;
        }
        if (atomicReference.get() != DISPOSED) {
            return false;
        }
        bVar.dispose();
        return false;
    }

    @Override // c.a.y.b
    public void dispose() {
    }

    public static boolean a(AtomicReference<c.a.y.b> atomicReference, c.a.y.b bVar) {
        c.a.y.b bVar2;
        do {
            bVar2 = atomicReference.get();
            if (bVar2 == DISPOSED) {
                if (bVar == null) {
                    return false;
                }
                bVar.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(bVar2, bVar));
        return true;
    }

    public static boolean a(AtomicReference<c.a.y.b> atomicReference) {
        c.a.y.b andSet;
        c.a.y.b bVar = atomicReference.get();
        c cVar = DISPOSED;
        if (bVar == cVar || (andSet = atomicReference.getAndSet(cVar)) == cVar) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.dispose();
        return true;
    }

    public static boolean a(c.a.y.b bVar, c.a.y.b bVar2) {
        if (bVar2 == null) {
            c.a.e0.a.b(new NullPointerException("next is null"));
            return false;
        }
        if (bVar == null) {
            return true;
        }
        bVar2.dispose();
        a();
        return false;
    }

    public static void a() {
        c.a.e0.a.b(new c.a.z.e("Disposable already set!"));
    }
}
