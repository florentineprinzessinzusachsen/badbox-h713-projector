package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableConcatWithMaybe.java */
/* JADX INFO: loaded from: classes.dex */
public final class x<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.j<? extends T> f2859b;

    /* JADX INFO: compiled from: ObservableConcatWithMaybe.java */
    static final class a<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.i<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2860a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.j<? extends T> f2861b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2862c;

        a(c.a.s<? super T> sVar, c.a.j<? extends T> jVar) {
            this.f2860a = sVar;
            this.f2861b = jVar;
        }

        @Override // c.a.i
        public void a(T t) {
            this.f2860a.onNext(t);
            this.f2860a.onComplete();
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2862c) {
                this.f2860a.onComplete();
                return;
            }
            this.f2862c = true;
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this, (c.a.y.b) null);
            c.a.j<? extends T> jVar = this.f2861b;
            this.f2861b = null;
            jVar.a(this);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2860a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2860a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (!c.a.b0.a.c.c(this, bVar) || this.f2862c) {
                return;
            }
            this.f2860a.onSubscribe(this);
        }
    }

    public x(c.a.l<T> lVar, c.a.j<? extends T> jVar) {
        super(lVar);
        this.f2859b = jVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2859b));
    }
}
