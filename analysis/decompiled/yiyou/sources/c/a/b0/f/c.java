package c.a.b0.f;

import c.a.b0.c.i;
import c.a.b0.j.q;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: SpscLinkedArrayQueue.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T> implements i<T> {
    static final int i = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final Object j = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f2988b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    long f2989c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f2990d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    AtomicReferenceArray<Object> f2991e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f2992f;
    AtomicReferenceArray<Object> g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final AtomicLong f2987a = new AtomicLong();
    final AtomicLong h = new AtomicLong();

    public c(int i2) {
        int iA = q.a(Math.max(8, i2));
        int i3 = iA - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(iA + 1);
        this.f2991e = atomicReferenceArray;
        this.f2990d = i3;
        a(iA);
        this.g = atomicReferenceArray;
        this.f2992f = i3;
        this.f2989c = i3 - 1;
        b(0L);
    }

    private boolean a(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j2, int i2) {
        a(atomicReferenceArray, i2, t);
        b(j2 + 1);
        return true;
    }

    private static int b(int i2) {
        return i2;
    }

    private AtomicReferenceArray<Object> b(AtomicReferenceArray<Object> atomicReferenceArray, int i2) {
        b(i2);
        AtomicReferenceArray<Object> atomicReferenceArray2 = (AtomicReferenceArray) a(atomicReferenceArray, i2);
        a(atomicReferenceArray, i2, (Object) null);
        return atomicReferenceArray2;
    }

    private long c() {
        return this.h.get();
    }

    private long d() {
        return this.f2987a.get();
    }

    private long e() {
        return this.h.get();
    }

    private long f() {
        return this.f2987a.get();
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
        return f() == e();
    }

    @Override // c.a.b0.c.j
    public boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        AtomicReferenceArray<Object> atomicReferenceArray = this.f2991e;
        long jD = d();
        int i2 = this.f2990d;
        int iA = a(jD, i2);
        if (jD < this.f2989c) {
            return a(atomicReferenceArray, t, jD, iA);
        }
        long j2 = ((long) this.f2988b) + jD;
        if (a(atomicReferenceArray, a(j2, i2)) == null) {
            this.f2989c = j2 - 1;
            return a(atomicReferenceArray, t, jD, iA);
        }
        if (a(atomicReferenceArray, a(1 + jD, i2)) == null) {
            return a(atomicReferenceArray, t, jD, iA);
        }
        a(atomicReferenceArray, jD, iA, t, i2);
        return true;
    }

    @Override // c.a.b0.c.i, c.a.b0.c.j
    public T poll() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.g;
        long jC = c();
        int i2 = this.f2992f;
        int iA = a(jC, i2);
        T t = (T) a(atomicReferenceArray, iA);
        boolean z = t == j;
        if (t == null || z) {
            if (z) {
                return b(b(atomicReferenceArray, i2 + 1), jC, i2);
            }
            return null;
        }
        a(atomicReferenceArray, iA, (Object) null);
        a(jC + 1);
        return t;
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i2, T t, long j3) {
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f2991e = atomicReferenceArray2;
        this.f2989c = (j3 + j2) - 1;
        a(atomicReferenceArray2, i2, t);
        a(atomicReferenceArray, atomicReferenceArray2);
        a(atomicReferenceArray, i2, j);
        b(j2 + 1);
    }

    private T b(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i2) {
        this.g = atomicReferenceArray;
        int iA = a(j2, i2);
        T t = (T) a(atomicReferenceArray, iA);
        if (t != null) {
            a(atomicReferenceArray, iA, (Object) null);
            a(j2 + 1);
        }
        return t;
    }

    public int b() {
        long jE = e();
        while (true) {
            long jF = f();
            long jE2 = e();
            if (jE == jE2) {
                return (int) (jF - jE2);
            }
            jE = jE2;
        }
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        int length = atomicReferenceArray.length() - 1;
        b(length);
        a(atomicReferenceArray, length, atomicReferenceArray2);
    }

    private void b(long j2) {
        this.f2987a.lazySet(j2);
    }

    public T a() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.g;
        long jC = c();
        int i2 = this.f2992f;
        T t = (T) a(atomicReferenceArray, a(jC, i2));
        return t == j ? a(b(atomicReferenceArray, i2 + 1), jC, i2) : t;
    }

    private T a(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i2) {
        this.g = atomicReferenceArray;
        return (T) a(atomicReferenceArray, a(j2, i2));
    }

    private void a(int i2) {
        this.f2988b = Math.min(i2 / 4, i);
    }

    private void a(long j2) {
        this.h.lazySet(j2);
    }

    private static int a(long j2, int i2) {
        int i3 = ((int) j2) & i2;
        b(i3);
        return i3;
    }

    private static void a(AtomicReferenceArray<Object> atomicReferenceArray, int i2, Object obj) {
        atomicReferenceArray.lazySet(i2, obj);
    }

    private static <E> Object a(AtomicReferenceArray<Object> atomicReferenceArray, int i2) {
        return atomicReferenceArray.get(i2);
    }

    public boolean a(T t, T t2) {
        AtomicReferenceArray<Object> atomicReferenceArray = this.f2991e;
        long jF = f();
        int i2 = this.f2990d;
        long j2 = 2 + jF;
        if (a(atomicReferenceArray, a(j2, i2)) == null) {
            int iA = a(jF, i2);
            a(atomicReferenceArray, iA + 1, t2);
            a(atomicReferenceArray, iA, t);
            b(j2);
            return true;
        }
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f2991e = atomicReferenceArray2;
        int iA2 = a(jF, i2);
        a(atomicReferenceArray2, iA2 + 1, t2);
        a(atomicReferenceArray2, iA2, t);
        a(atomicReferenceArray, atomicReferenceArray2);
        a(atomicReferenceArray, iA2, j);
        b(j2);
        return true;
    }
}
