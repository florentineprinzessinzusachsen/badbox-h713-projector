package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableTimeoutTimed.java */
/* JADX INFO: loaded from: classes.dex */
public final class x3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2881b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final TimeUnit f2882c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.t f2883d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final c.a.q<? extends T> f2884e;

    /* JADX INFO: compiled from: ObservableTimeoutTimed.java */
    static final class a<T> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2885a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2886b;

        a(c.a.s<? super T> sVar, AtomicReference<c.a.y.b> atomicReference) {
            this.f2885a = sVar;
            this.f2886b = atomicReference;
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2885a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2885a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2885a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.a(this.f2886b, bVar);
        }
    }

    /* JADX INFO: compiled from: ObservableTimeoutTimed.java */
    static final class b<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.y.b, d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2887a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2888b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final TimeUnit f2889c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.t.c f2890d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.b0.a.f f2891e = new c.a.b0.a.f();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final AtomicLong f2892f = new AtomicLong();
        final AtomicReference<c.a.y.b> g = new AtomicReference<>();
        c.a.q<? extends T> h;

        b(c.a.s<? super T> sVar, long j, TimeUnit timeUnit, c.a.t.c cVar, c.a.q<? extends T> qVar) {
            this.f2887a = sVar;
            this.f2888b = j;
            this.f2889c = timeUnit;
            this.f2890d = cVar;
            this.h = qVar;
        }

        @Override // c.a.b0.e.d.x3.d
        public void a(long j) {
            if (this.f2892f.compareAndSet(j, Long.MAX_VALUE)) {
                c.a.b0.a.c.a(this.g);
                c.a.q<? extends T> qVar = this.h;
                this.h = null;
                qVar.subscribe(new a(this.f2887a, this));
                this.f2890d.dispose();
            }
        }

        void b(long j) {
            this.f2891e.a(this.f2890d.a(new e(j, this), this.f2888b, this.f2889c));
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.g);
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
            this.f2890d.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2892f.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.f2891e.dispose();
                this.f2887a.onComplete();
                this.f2890d.dispose();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2892f.getAndSet(Long.MAX_VALUE) == Long.MAX_VALUE) {
                c.a.e0.a.b(th);
                return;
            }
            this.f2891e.dispose();
            this.f2887a.onError(th);
            this.f2890d.dispose();
        }

        @Override // c.a.s
        public void onNext(T t) {
            long j = this.f2892f.get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (this.f2892f.compareAndSet(j, j2)) {
                    this.f2891e.get().dispose();
                    this.f2887a.onNext(t);
                    b(j2);
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.g, bVar);
        }
    }

    /* JADX INFO: compiled from: ObservableTimeoutTimed.java */
    static final class c<T> extends AtomicLong implements c.a.s<T>, c.a.y.b, d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2893a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2894b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final TimeUnit f2895c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.t.c f2896d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.b0.a.f f2897e = new c.a.b0.a.f();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2898f = new AtomicReference<>();

        c(c.a.s<? super T> sVar, long j, TimeUnit timeUnit, c.a.t.c cVar) {
            this.f2893a = sVar;
            this.f2894b = j;
            this.f2895c = timeUnit;
            this.f2896d = cVar;
        }

        @Override // c.a.b0.e.d.x3.d
        public void a(long j) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                c.a.b0.a.c.a(this.f2898f);
                this.f2893a.onError(new TimeoutException());
                this.f2896d.dispose();
            }
        }

        void b(long j) {
            this.f2897e.a(this.f2896d.a(new e(j, this), this.f2894b, this.f2895c));
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.f2898f);
            this.f2896d.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.f2897e.dispose();
                this.f2893a.onComplete();
                this.f2896d.dispose();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (getAndSet(Long.MAX_VALUE) == Long.MAX_VALUE) {
                c.a.e0.a.b(th);
                return;
            }
            this.f2897e.dispose();
            this.f2893a.onError(th);
            this.f2896d.dispose();
        }

        @Override // c.a.s
        public void onNext(T t) {
            long j = get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    this.f2897e.get().dispose();
                    this.f2893a.onNext(t);
                    b(j2);
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f2898f, bVar);
        }
    }

    /* JADX INFO: compiled from: ObservableTimeoutTimed.java */
    interface d {
        void a(long j);
    }

    /* JADX INFO: compiled from: ObservableTimeoutTimed.java */
    static final class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final d f2899a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2900b;

        e(long j, d dVar) {
            this.f2900b = j;
            this.f2899a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2899a.a(this.f2900b);
        }
    }

    public x3(c.a.l<T> lVar, long j, TimeUnit timeUnit, c.a.t tVar, c.a.q<? extends T> qVar) {
        super(lVar);
        this.f2881b = j;
        this.f2882c = timeUnit;
        this.f2883d = tVar;
        this.f2884e = qVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        if (this.f2884e == null) {
            c cVar = new c(sVar, this.f2881b, this.f2882c, this.f2883d.a());
            sVar.onSubscribe(cVar);
            cVar.b(0L);
            this.f1932a.subscribe(cVar);
            return;
        }
        b bVar = new b(sVar, this.f2881b, this.f2882c, this.f2883d.a(), this.f2884e);
        sVar.onSubscribe(bVar);
        bVar.b(0L);
        this.f1932a.subscribe(bVar);
    }
}
