package c.a.b0.g;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: ScheduledRunnable.java */
/* JADX INFO: loaded from: classes.dex */
public final class k extends AtomicReferenceArray<Object> implements Runnable, Callable<Object>, c.a.y.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Object f3039b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Object f3040c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final Object f3041d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final Object f3042e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Runnable f3043a;

    public k(Runnable runnable, c.a.b0.a.b bVar) {
        super(3);
        this.f3043a = runnable;
        lazySet(0, bVar);
    }

    public void a(Future<?> future) {
        Object obj;
        do {
            obj = get(1);
            if (obj == f3042e) {
                return;
            }
            if (obj == f3040c) {
                future.cancel(false);
                return;
            } else if (obj == f3041d) {
                future.cancel(true);
                return;
            }
        } while (!compareAndSet(1, obj, future));
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        run();
        return null;
    }

    @Override // c.a.y.b
    public void dispose() {
        Object obj;
        Object obj2;
        while (true) {
            Object obj3 = get(1);
            if (obj3 == f3042e || obj3 == f3040c || obj3 == f3041d) {
                break;
            }
            boolean z = get(2) != Thread.currentThread();
            if (compareAndSet(1, obj3, z ? f3041d : f3040c)) {
                if (obj3 == null) {
                    break;
                }
                ((Future) obj3).cancel(z);
                break;
            }
        }
        do {
            obj = get(0);
            if (obj == f3042e || obj == (obj2 = f3039b) || obj == null) {
                return;
            }
        } while (!compareAndSet(0, obj, obj2));
        ((c.a.b0.a.b) obj).a(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        Object obj2;
        lazySet(2, Thread.currentThread());
        try {
            this.f3043a.run();
        } catch (Throwable th) {
            try {
                c.a.e0.a.b(th);
            } finally {
                lazySet(2, null);
                Object obj3 = get(0);
                if (obj3 != f3039b && compareAndSet(0, obj3, f3042e) && obj3 != null) {
                    ((c.a.b0.a.b) obj3).a(this);
                }
                do {
                    obj = get(1);
                    if (obj == f3040c || obj == f3041d) {
                        break;
                    }
                } while (!compareAndSet(1, obj, f3042e));
            }
        }
        lazySet(2, null);
        Object obj4 = get(0);
        if (obj4 != f3039b && compareAndSet(0, obj4, f3042e) && obj4 != null) {
            ((c.a.b0.a.b) obj4).a(this);
        }
        do {
            obj = get(1);
            Object obj5 = f3040c;
            if (obj == obj5) {
                return;
            } else {
                if (obj == obj2) {
                    return;
                }
            }
        } while (!compareAndSet(1, obj, f3042e));
    }
}
