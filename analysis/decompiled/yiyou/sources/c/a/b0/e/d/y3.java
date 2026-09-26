package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableTimer.java */
/* JADX INFO: loaded from: classes.dex */
public final class y3 extends c.a.l<Long> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.t f2929a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2930b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final TimeUnit f2931c;

    /* JADX INFO: compiled from: ObservableTimer.java */
    static final class a extends AtomicReference<c.a.y.b> implements c.a.y.b, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super Long> f2932a;

        a(c.a.s<? super Long> sVar) {
            this.f2932a = sVar;
        }

        public boolean a() {
            return get() == c.a.b0.a.c.DISPOSED;
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a()) {
                return;
            }
            this.f2932a.onNext(0L);
            lazySet(c.a.b0.a.d.INSTANCE);
            this.f2932a.onComplete();
        }

        public void a(c.a.y.b bVar) {
            c.a.b0.a.c.d(this, bVar);
        }
    }

    public y3(long j, TimeUnit timeUnit, c.a.t tVar) {
        this.f2930b = j;
        this.f2931c = timeUnit;
        this.f2929a = tVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super Long> sVar) {
        a aVar = new a(sVar);
        sVar.onSubscribe(aVar);
        aVar.a(this.f2929a.a(aVar, this.f2930b, this.f2931c));
    }
}
