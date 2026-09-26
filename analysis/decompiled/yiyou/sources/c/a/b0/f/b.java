package c.a.b0.f;

import c.a.b0.c.i;
import c.a.b0.j.q;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: SpscArrayQueue.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<E> extends AtomicReferenceArray<E> implements i<E> {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final Integer f2981f = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final int f2982a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final AtomicLong f2983b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    long f2984c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final AtomicLong f2985d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f2986e;

    public b(int i) {
        super(q.a(i));
        this.f2982a = length() - 1;
        this.f2983b = new AtomicLong();
        this.f2985d = new AtomicLong();
        this.f2986e = Math.min(i / 4, f2981f.intValue());
    }

    int a(long j) {
        return this.f2982a & ((int) j);
    }

    int a(long j, int i) {
        return ((int) j) & i;
    }

    void b(long j) {
        this.f2985d.lazySet(j);
    }

    void c(long j) {
        this.f2983b.lazySet(j);
    }

    @Override // c.a.b0.c.j
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // c.a.b0.c.j
    public boolean isEmpty() {
        return this.f2983b.get() == this.f2985d.get();
    }

    @Override // c.a.b0.c.j
    public boolean offer(E e2) {
        if (e2 == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        int i = this.f2982a;
        long j = this.f2983b.get();
        int iA = a(j, i);
        if (j >= this.f2984c) {
            long j2 = ((long) this.f2986e) + j;
            if (a(a(j2, i)) == null) {
                this.f2984c = j2;
            } else if (a(iA) != null) {
                return false;
            }
        }
        a(iA, e2);
        c(j + 1);
        return true;
    }

    @Override // c.a.b0.c.i, c.a.b0.c.j
    public E poll() {
        long j = this.f2985d.get();
        int iA = a(j);
        E eA = a(iA);
        if (eA == null) {
            return null;
        }
        b(j + 1);
        a(iA, (Object) null);
        return eA;
    }

    void a(int i, E e2) {
        lazySet(i, e2);
    }

    E a(int i) {
        return get(i);
    }
}
