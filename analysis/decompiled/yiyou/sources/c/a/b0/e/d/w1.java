package c.a.b0.e.d;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableMapNotification.java */
/* JADX INFO: loaded from: classes.dex */
public final class w1<T, R> extends c.a.b0.e.d.a<T, c.a.q<? extends R>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends c.a.q<? extends R>> f2832b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.n<? super Throwable, ? extends c.a.q<? extends R>> f2833c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Callable<? extends c.a.q<? extends R>> f2834d;

    /* JADX INFO: compiled from: ObservableMapNotification.java */
    static final class a<T, R> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super c.a.q<? extends R>> f2835a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.q<? extends R>> f2836b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.a0.n<? super Throwable, ? extends c.a.q<? extends R>> f2837c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final Callable<? extends c.a.q<? extends R>> f2838d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a.y.b f2839e;

        a(c.a.s<? super c.a.q<? extends R>> sVar, c.a.a0.n<? super T, ? extends c.a.q<? extends R>> nVar, c.a.a0.n<? super Throwable, ? extends c.a.q<? extends R>> nVar2, Callable<? extends c.a.q<? extends R>> callable) {
            this.f2835a = sVar;
            this.f2836b = nVar;
            this.f2837c = nVar2;
            this.f2838d = callable;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2839e.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            try {
                c.a.q<? extends R> qVarCall = this.f2838d.call();
                c.a.b0.b.b.a(qVarCall, "The onComplete ObservableSource returned is null");
                this.f2835a.onNext(qVarCall);
                this.f2835a.onComplete();
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2835a.onError(th);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            try {
                c.a.q<? extends R> qVarApply = this.f2837c.apply(th);
                c.a.b0.b.b.a(qVarApply, "The onError ObservableSource returned is null");
                this.f2835a.onNext(qVarApply);
                this.f2835a.onComplete();
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                this.f2835a.onError(new c.a.z.a(th, th2));
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            try {
                c.a.q<? extends R> qVarApply = this.f2836b.apply(t);
                c.a.b0.b.b.a(qVarApply, "The onNext ObservableSource returned is null");
                this.f2835a.onNext(qVarApply);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2835a.onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2839e, bVar)) {
                this.f2839e = bVar;
                this.f2835a.onSubscribe(this);
            }
        }
    }

    public w1(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends c.a.q<? extends R>> nVar, c.a.a0.n<? super Throwable, ? extends c.a.q<? extends R>> nVar2, Callable<? extends c.a.q<? extends R>> callable) {
        super(qVar);
        this.f2832b = nVar;
        this.f2833c = nVar2;
        this.f2834d = callable;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super c.a.q<? extends R>> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2832b, this.f2833c, this.f2834d));
    }
}
