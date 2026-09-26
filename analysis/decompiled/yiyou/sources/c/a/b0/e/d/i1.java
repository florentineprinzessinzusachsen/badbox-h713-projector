package c.a.b0.e.d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableGroupBy.java */
/* JADX INFO: loaded from: classes.dex */
public final class i1<T, K, V> extends c.a.b0.e.d.a<T, c.a.c0.b<K, V>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends K> f2258b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends V> f2259c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f2260d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final boolean f2261e;

    /* JADX INFO: compiled from: ObservableGroupBy.java */
    public static final class a<T, K, V> extends AtomicInteger implements c.a.s<T>, c.a.y.b {
        static final Object i = new Object();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super c.a.c0.b<K, V>> f2262a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends K> f2263b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends V> f2264c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f2265d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final boolean f2266e;
        c.a.y.b g;
        final AtomicBoolean h = new AtomicBoolean();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final Map<Object, b<K, V>> f2267f = new ConcurrentHashMap();

        public a(c.a.s<? super c.a.c0.b<K, V>> sVar, c.a.a0.n<? super T, ? extends K> nVar, c.a.a0.n<? super T, ? extends V> nVar2, int i2, boolean z) {
            this.f2262a = sVar;
            this.f2263b = nVar;
            this.f2264c = nVar2;
            this.f2265d = i2;
            this.f2266e = z;
            lazySet(1);
        }

        public void a(K k) {
            if (k == null) {
                k = (K) i;
            }
            this.f2267f.remove(k);
            if (decrementAndGet() == 0) {
                this.g.dispose();
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.h.compareAndSet(false, true) && decrementAndGet() == 0) {
                this.g.dispose();
            }
        }

        @Override // c.a.s
        public void onComplete() {
            ArrayList arrayList = new ArrayList(this.f2267f.values());
            this.f2267f.clear();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((b) it.next()).onComplete();
            }
            this.f2262a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            ArrayList arrayList = new ArrayList(this.f2267f.values());
            this.f2267f.clear();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((b) it.next()).onError(th);
            }
            this.f2262a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            try {
                K kApply = this.f2263b.apply(t);
                Object obj = kApply != null ? kApply : i;
                b<K, V> bVarA = this.f2267f.get(obj);
                if (bVarA == null) {
                    if (this.h.get()) {
                        return;
                    }
                    bVarA = b.a(kApply, this.f2265d, this, this.f2266e);
                    this.f2267f.put(obj, bVarA);
                    getAndIncrement();
                    this.f2262a.onNext(bVarA);
                }
                try {
                    V vApply = this.f2264c.apply(t);
                    c.a.b0.b.b.a(vApply, "The value supplied is null");
                    bVarA.onNext(vApply);
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    this.g.dispose();
                    onError(th);
                }
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                this.g.dispose();
                onError(th2);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.g, bVar)) {
                this.g = bVar;
                this.f2262a.onSubscribe(this);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableGroupBy.java */
    static final class b<K, T> extends c.a.c0.b<K, T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c<T, K> f2268a;

        protected b(K k, c<T, K> cVar) {
            super(k);
            this.f2268a = cVar;
        }

        public static <T, K> b<K, T> a(K k, int i, a<?, K, T> aVar, boolean z) {
            return new b<>(k, new c(i, aVar, k, z));
        }

        public void onComplete() {
            this.f2268a.b();
        }

        public void onError(Throwable th) {
            this.f2268a.a(th);
        }

        public void onNext(T t) {
            this.f2268a.a(t);
        }

        @Override // c.a.l
        protected void subscribeActual(c.a.s<? super T> sVar) {
            this.f2268a.subscribe(sVar);
        }
    }

    public i1(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends K> nVar, c.a.a0.n<? super T, ? extends V> nVar2, int i, boolean z) {
        super(qVar);
        this.f2258b = nVar;
        this.f2259c = nVar2;
        this.f2260d = i;
        this.f2261e = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super c.a.c0.b<K, V>> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2258b, this.f2259c, this.f2260d, this.f2261e));
    }

    /* JADX INFO: compiled from: ObservableGroupBy.java */
    static final class c<T, K> extends AtomicInteger implements c.a.y.b, c.a.q<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final K f2269a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.f.c<T> f2270b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final a<?, K, T> f2271c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f2272d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f2273e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        Throwable f2274f;
        final AtomicBoolean g = new AtomicBoolean();
        final AtomicBoolean h = new AtomicBoolean();
        final AtomicReference<c.a.s<? super T>> i = new AtomicReference<>();

        c(int i, a<?, K, T> aVar, K k, boolean z) {
            this.f2270b = new c.a.b0.f.c<>(i);
            this.f2271c = aVar;
            this.f2269a = k;
            this.f2272d = z;
        }

        public void a(T t) {
            this.f2270b.offer(t);
            a();
        }

        public void b() {
            this.f2273e = true;
            a();
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.g.compareAndSet(false, true) && getAndIncrement() == 0) {
                this.i.lazySet(null);
                this.f2271c.a(this.f2269a);
            }
        }

        @Override // c.a.q
        public void subscribe(c.a.s<? super T> sVar) {
            if (!this.h.compareAndSet(false, true)) {
                c.a.b0.a.d.a(new IllegalStateException("Only one Observer allowed!"), sVar);
                return;
            }
            sVar.onSubscribe(this);
            this.i.lazySet(sVar);
            if (this.g.get()) {
                this.i.lazySet(null);
            } else {
                a();
            }
        }

        public void a(Throwable th) {
            this.f2274f = th;
            this.f2273e = true;
            a();
        }

        void a() {
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.b0.f.c<T> cVar = this.f2270b;
            boolean z = this.f2272d;
            c.a.s<? super T> sVar = this.i.get();
            int iAddAndGet = 1;
            while (true) {
                if (sVar != null) {
                    while (true) {
                        boolean z2 = this.f2273e;
                        T tPoll = cVar.poll();
                        boolean z3 = tPoll == null;
                        if (a(z2, z3, sVar, z)) {
                            return;
                        }
                        if (z3) {
                            break;
                        } else {
                            sVar.onNext(tPoll);
                        }
                    }
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
                if (sVar == null) {
                    sVar = this.i.get();
                }
            }
        }

        boolean a(boolean z, boolean z2, c.a.s<? super T> sVar, boolean z3) {
            if (this.g.get()) {
                this.f2270b.clear();
                this.f2271c.a(this.f2269a);
                this.i.lazySet(null);
                return true;
            }
            if (!z) {
                return false;
            }
            if (z3) {
                if (!z2) {
                    return false;
                }
                Throwable th = this.f2274f;
                this.i.lazySet(null);
                if (th != null) {
                    sVar.onError(th);
                } else {
                    sVar.onComplete();
                }
                return true;
            }
            Throwable th2 = this.f2274f;
            if (th2 != null) {
                this.f2270b.clear();
                this.i.lazySet(null);
                sVar.onError(th2);
                return true;
            }
            if (!z2) {
                return false;
            }
            this.i.lazySet(null);
            sVar.onComplete();
            return true;
        }
    }
}
