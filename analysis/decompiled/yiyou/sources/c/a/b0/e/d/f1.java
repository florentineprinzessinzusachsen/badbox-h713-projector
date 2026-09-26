package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableFromPublisher.java */
/* JADX INFO: loaded from: classes.dex */
public final class f1<T> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final f.a.a<? extends T> f2136a;

    /* JADX INFO: compiled from: ObservableFromPublisher.java */
    static final class a<T> implements c.a.g<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2137a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        f.a.c f2138b;

        a(c.a.s<? super T> sVar) {
            this.f2137a = sVar;
        }

        @Override // f.a.b
        public void a(f.a.c cVar) {
            if (c.a.b0.i.b.a(this.f2138b, cVar)) {
                this.f2138b = cVar;
                this.f2137a.onSubscribe(this);
                cVar.c(Long.MAX_VALUE);
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2138b.cancel();
            this.f2138b = c.a.b0.i.b.CANCELLED;
        }

        @Override // f.a.b
        public void onComplete() {
            this.f2137a.onComplete();
        }

        @Override // f.a.b
        public void onError(Throwable th) {
            this.f2137a.onError(th);
        }

        @Override // f.a.b
        public void onNext(T t) {
            this.f2137a.onNext(t);
        }
    }

    public f1(f.a.a<? extends T> aVar) {
        this.f2136a = aVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f2136a.a(new a(sVar));
    }
}
