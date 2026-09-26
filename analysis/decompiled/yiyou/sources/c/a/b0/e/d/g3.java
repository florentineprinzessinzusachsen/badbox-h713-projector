package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ObservableSkipLastTimed.java */
/* JADX INFO: loaded from: classes.dex */
public final class g3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2182b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final TimeUnit f2183c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.t f2184d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f2185e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final boolean f2186f;

    /* JADX INFO: compiled from: ObservableSkipLastTimed.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2187a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2188b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final TimeUnit f2189c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.t f2190d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.b0.f.c<Object> f2191e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final boolean f2192f;
        c.a.y.b g;
        volatile boolean h;
        volatile boolean i;
        Throwable j;

        a(c.a.s<? super T> sVar, long j, TimeUnit timeUnit, c.a.t tVar, int i, boolean z) {
            this.f2187a = sVar;
            this.f2188b = j;
            this.f2189c = timeUnit;
            this.f2190d = tVar;
            this.f2191e = new c.a.b0.f.c<>(i);
            this.f2192f = z;
        }

        void a() {
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.s<? super T> sVar = this.f2187a;
            c.a.b0.f.c<Object> cVar = this.f2191e;
            boolean z = this.f2192f;
            TimeUnit timeUnit = this.f2189c;
            c.a.t tVar = this.f2190d;
            long j = this.f2188b;
            int iAddAndGet = 1;
            while (!this.h) {
                boolean z2 = this.i;
                Long l = (Long) cVar.a();
                boolean z3 = l == null;
                long jA = tVar.a(timeUnit);
                if (!z3 && l.longValue() > jA - j) {
                    z3 = true;
                }
                if (z2) {
                    if (!z) {
                        Throwable th = this.j;
                        if (th != null) {
                            this.f2191e.clear();
                            sVar.onError(th);
                            return;
                        } else if (z3) {
                            sVar.onComplete();
                            return;
                        }
                    } else if (z3) {
                        Throwable th2 = this.j;
                        if (th2 != null) {
                            sVar.onError(th2);
                            return;
                        } else {
                            sVar.onComplete();
                            return;
                        }
                    }
                }
                if (z3) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    cVar.poll();
                    sVar.onNext(cVar.poll());
                }
            }
            this.f2191e.clear();
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.h) {
                return;
            }
            this.h = true;
            this.g.dispose();
            if (getAndIncrement() == 0) {
                this.f2191e.clear();
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.i = true;
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.j = th;
            this.i = true;
            a();
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2191e.a(Long.valueOf(this.f2190d.a(this.f2189c)), t);
            a();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.g, bVar)) {
                this.g = bVar;
                this.f2187a.onSubscribe(this);
            }
        }
    }

    public g3(c.a.q<T> qVar, long j, TimeUnit timeUnit, c.a.t tVar, int i, boolean z) {
        super(qVar);
        this.f2182b = j;
        this.f2183c = timeUnit;
        this.f2184d = tVar;
        this.f2185e = i;
        this.f2186f = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2182b, this.f2183c, this.f2184d, this.f2185e, this.f2186f));
    }
}
