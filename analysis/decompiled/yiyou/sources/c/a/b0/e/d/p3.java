package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: ObservableTakeLastTimed.java */
/* JADX INFO: loaded from: classes.dex */
public final class p3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final long f2566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final TimeUnit f2567d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final c.a.t f2568e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f2569f;
    final boolean g;

    /* JADX INFO: compiled from: ObservableTakeLastTimed.java */
    static final class a<T> extends AtomicBoolean implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2570a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2571b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final long f2572c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final TimeUnit f2573d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.t f2574e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.b0.f.c<Object> f2575f;
        final boolean g;
        c.a.y.b h;
        volatile boolean i;
        Throwable j;

        a(c.a.s<? super T> sVar, long j, long j2, TimeUnit timeUnit, c.a.t tVar, int i, boolean z) {
            this.f2570a = sVar;
            this.f2571b = j;
            this.f2572c = j2;
            this.f2573d = timeUnit;
            this.f2574e = tVar;
            this.f2575f = new c.a.b0.f.c<>(i);
            this.g = z;
        }

        void a() {
            Throwable th;
            if (compareAndSet(false, true)) {
                c.a.s<? super T> sVar = this.f2570a;
                c.a.b0.f.c<Object> cVar = this.f2575f;
                boolean z = this.g;
                while (!this.i) {
                    if (!z && (th = this.j) != null) {
                        cVar.clear();
                        sVar.onError(th);
                        return;
                    }
                    Object objPoll = cVar.poll();
                    if (objPoll == null) {
                        Throwable th2 = this.j;
                        if (th2 != null) {
                            sVar.onError(th2);
                            return;
                        } else {
                            sVar.onComplete();
                            return;
                        }
                    }
                    Object objPoll2 = cVar.poll();
                    if (((Long) objPoll).longValue() >= this.f2574e.a(this.f2573d) - this.f2572c) {
                        sVar.onNext(objPoll2);
                    }
                }
                cVar.clear();
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.i) {
                return;
            }
            this.i = true;
            this.h.dispose();
            if (compareAndSet(false, true)) {
                this.f2575f.clear();
            }
        }

        @Override // c.a.s
        public void onComplete() {
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.j = th;
            a();
        }

        @Override // c.a.s
        public void onNext(T t) {
            c.a.b0.f.c<Object> cVar = this.f2575f;
            long jA = this.f2574e.a(this.f2573d);
            long j = this.f2572c;
            long j2 = this.f2571b;
            boolean z = j2 == Long.MAX_VALUE;
            cVar.a(Long.valueOf(jA), t);
            while (!cVar.isEmpty()) {
                if (((Long) cVar.a()).longValue() > jA - j && (z || (cVar.b() >> 1) <= j2)) {
                    return;
                }
                cVar.poll();
                cVar.poll();
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.h, bVar)) {
                this.h = bVar;
                this.f2570a.onSubscribe(this);
            }
        }
    }

    public p3(c.a.q<T> qVar, long j, long j2, TimeUnit timeUnit, c.a.t tVar, int i, boolean z) {
        super(qVar);
        this.f2565b = j;
        this.f2566c = j2;
        this.f2567d = timeUnit;
        this.f2568e = tVar;
        this.f2569f = i;
        this.g = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2565b, this.f2566c, this.f2567d, this.f2568e, this.f2569f, this.g));
    }
}
