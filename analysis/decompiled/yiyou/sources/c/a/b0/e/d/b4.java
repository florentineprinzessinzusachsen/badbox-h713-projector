package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: ObservableUnsubscribeOn.java */
/* JADX INFO: loaded from: classes.dex */
public final class b4<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.t f1985b;

    /* JADX INFO: compiled from: ObservableUnsubscribeOn.java */
    static final class a<T> extends AtomicBoolean implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f1986a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.t f1987b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f1988c;

        /* JADX INFO: renamed from: c.a.b0.e.d.b4$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableUnsubscribeOn.java */
        final class RunnableC0054a implements Runnable {
            RunnableC0054a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f1988c.dispose();
            }
        }

        a(c.a.s<? super T> sVar, c.a.t tVar) {
            this.f1986a = sVar;
            this.f1987b = tVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.f1987b.a(new RunnableC0054a());
            }
        }

        @Override // c.a.s
        public void onComplete() {
            if (get()) {
                return;
            }
            this.f1986a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (get()) {
                c.a.e0.a.b(th);
            } else {
                this.f1986a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (get()) {
                return;
            }
            this.f1986a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f1988c, bVar)) {
                this.f1988c = bVar;
                this.f1986a.onSubscribe(this);
            }
        }
    }

    public b4(c.a.q<T> qVar, c.a.t tVar) {
        super(qVar);
        this.f1985b = tVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f1985b));
    }
}
