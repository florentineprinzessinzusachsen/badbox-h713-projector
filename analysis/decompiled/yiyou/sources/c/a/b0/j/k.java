package c.a.b0.j;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: HalfSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void a(f.a.b<? super T> bVar, T t, AtomicInteger atomicInteger, c cVar) {
        if (atomicInteger.get() == 0 && atomicInteger.compareAndSet(0, 1)) {
            bVar.onNext(t);
            if (atomicInteger.decrementAndGet() != 0) {
                Throwable thA = cVar.a();
                if (thA != null) {
                    bVar.onError(thA);
                } else {
                    bVar.onComplete();
                }
            }
        }
    }

    public static void a(f.a.b<?> bVar, Throwable th, AtomicInteger atomicInteger, c cVar) {
        if (cVar.a(th)) {
            if (atomicInteger.getAndIncrement() == 0) {
                bVar.onError(cVar.a());
                return;
            }
            return;
        }
        c.a.e0.a.b(th);
    }

    public static void a(f.a.b<?> bVar, AtomicInteger atomicInteger, c cVar) {
        if (atomicInteger.getAndIncrement() == 0) {
            Throwable thA = cVar.a();
            if (thA != null) {
                bVar.onError(thA);
            } else {
                bVar.onComplete();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void a(c.a.s<? super T> sVar, T t, AtomicInteger atomicInteger, c cVar) {
        if (atomicInteger.get() == 0 && atomicInteger.compareAndSet(0, 1)) {
            sVar.onNext(t);
            if (atomicInteger.decrementAndGet() != 0) {
                Throwable thA = cVar.a();
                if (thA != null) {
                    sVar.onError(thA);
                } else {
                    sVar.onComplete();
                }
            }
        }
    }

    public static void a(c.a.s<?> sVar, Throwable th, AtomicInteger atomicInteger, c cVar) {
        if (cVar.a(th)) {
            if (atomicInteger.getAndIncrement() == 0) {
                sVar.onError(cVar.a());
                return;
            }
            return;
        }
        c.a.e0.a.b(th);
    }

    public static void a(c.a.s<?> sVar, AtomicInteger atomicInteger, c cVar) {
        if (atomicInteger.getAndIncrement() == 0) {
            Throwable thA = cVar.a();
            if (thA != null) {
                sVar.onError(thA);
            } else {
                sVar.onComplete();
            }
        }
    }
}
