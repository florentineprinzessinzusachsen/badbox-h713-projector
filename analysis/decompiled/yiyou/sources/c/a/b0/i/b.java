package c.a.b0.i;

import c.a.b0.j.d;
import c.a.z.e;
import f.a.c;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: SubscriptionHelper.java */
/* JADX INFO: loaded from: classes.dex */
public enum b implements c {
    CANCELLED;

    public static boolean a(c cVar, c cVar2) {
        if (cVar2 == null) {
            c.a.e0.a.b(new NullPointerException("next is null"));
            return false;
        }
        if (cVar == null) {
            return true;
        }
        cVar2.cancel();
        a();
        return false;
    }

    @Override // f.a.c
    public void c(long j) {
    }

    @Override // f.a.c
    public void cancel() {
    }

    public static void a() {
        c.a.e0.a.b(new e("Subscription already set!"));
    }

    public static boolean a(long j) {
        if (j > 0) {
            return true;
        }
        c.a.e0.a.b(new IllegalArgumentException("n > 0 required but it was " + j));
        return false;
    }

    public static boolean a(AtomicReference<c> atomicReference, c cVar) {
        c.a.b0.b.b.a(cVar, "s is null");
        if (atomicReference.compareAndSet(null, cVar)) {
            return true;
        }
        cVar.cancel();
        if (atomicReference.get() == CANCELLED) {
            return false;
        }
        a();
        return false;
    }

    public static boolean a(AtomicReference<c> atomicReference) {
        c andSet;
        c cVar = atomicReference.get();
        b bVar = CANCELLED;
        if (cVar == bVar || (andSet = atomicReference.getAndSet(bVar)) == CANCELLED) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.cancel();
        return true;
    }

    public static boolean a(AtomicReference<c> atomicReference, AtomicLong atomicLong, c cVar) {
        if (!a(atomicReference, cVar)) {
            return false;
        }
        long andSet = atomicLong.getAndSet(0L);
        if (andSet == 0) {
            return true;
        }
        cVar.c(andSet);
        return true;
    }

    public static void a(AtomicReference<c> atomicReference, AtomicLong atomicLong, long j) {
        c cVar = atomicReference.get();
        if (cVar != null) {
            cVar.c(j);
            return;
        }
        if (a(j)) {
            d.a(atomicLong, j);
            c cVar2 = atomicReference.get();
            if (cVar2 != null) {
                long andSet = atomicLong.getAndSet(0L);
                if (andSet != 0) {
                    cVar2.c(andSet);
                }
            }
        }
    }
}
