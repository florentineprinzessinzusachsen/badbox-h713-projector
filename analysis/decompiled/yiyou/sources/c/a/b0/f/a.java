package c.a.b0.f;

import c.a.b0.c.i;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: MpscLinkedQueue.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> implements i<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicReference<C0072a<T>> f2978a = new AtomicReference<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AtomicReference<C0072a<T>> f2979b = new AtomicReference<>();

    /* JADX INFO: renamed from: c.a.b0.f.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MpscLinkedQueue.java */
    static final class C0072a<E> extends AtomicReference<C0072a<E>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private E f2980a;

        C0072a() {
        }

        public E a() {
            E eB = b();
            a((Object) null);
            return eB;
        }

        public E b() {
            return this.f2980a;
        }

        public C0072a<E> c() {
            return get();
        }

        C0072a(E e2) {
            a(e2);
        }

        public void a(E e2) {
            this.f2980a = e2;
        }

        public void a(C0072a<E> c0072a) {
            lazySet(c0072a);
        }
    }

    public a() {
        C0072a<T> c0072a = new C0072a<>();
        a(c0072a);
        b(c0072a);
    }

    C0072a<T> a() {
        return this.f2979b.get();
    }

    C0072a<T> b(C0072a<T> c0072a) {
        return this.f2978a.getAndSet(c0072a);
    }

    C0072a<T> c() {
        return this.f2978a.get();
    }

    @Override // c.a.b0.c.j
    public void clear() {
        while (poll() != null && !isEmpty()) {
        }
    }

    @Override // c.a.b0.c.j
    public boolean isEmpty() {
        return b() == c();
    }

    @Override // c.a.b0.c.j
    public boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        C0072a<T> c0072a = new C0072a<>(t);
        b(c0072a).a(c0072a);
        return true;
    }

    @Override // c.a.b0.c.i, c.a.b0.c.j
    public T poll() {
        C0072a<T> c0072aC;
        C0072a<T> c0072aA = a();
        C0072a<T> c0072aC2 = c0072aA.c();
        if (c0072aC2 != null) {
            T tA = c0072aC2.a();
            a(c0072aC2);
            return tA;
        }
        if (c0072aA == c()) {
            return null;
        }
        do {
            c0072aC = c0072aA.c();
        } while (c0072aC == null);
        T tA2 = c0072aC.a();
        a(c0072aC);
        return tA2;
    }

    void a(C0072a<T> c0072a) {
        this.f2979b.lazySet(c0072a);
    }

    C0072a<T> b() {
        return this.f2979b.get();
    }
}
