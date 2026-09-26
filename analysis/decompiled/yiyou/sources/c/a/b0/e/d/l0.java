package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableDoAfterNext.java */
/* JADX INFO: loaded from: classes.dex */
public final class l0<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.f<? super T> f2390b;

    /* JADX INFO: compiled from: ObservableDoAfterNext.java */
    static final class a<T> extends c.a.b0.d.a<T, T> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.a0.f<? super T> f2391f;

        a(c.a.s<? super T> sVar, c.a.a0.f<? super T> fVar) {
            super(sVar);
            this.f2391f = fVar;
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            return b(i);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onNext(T t) {
            this.f1795a.onNext((Object) t);
            if (this.f1799e == 0) {
                try {
                    this.f2391f.a(t);
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        @Override // c.a.b0.c.j
        public T poll() {
            T tPoll = this.f1797c.poll();
            if (tPoll != null) {
                this.f2391f.a(tPoll);
            }
            return tPoll;
        }
    }

    public l0(c.a.q<T> qVar, c.a.a0.f<? super T> fVar) {
        super(qVar);
        this.f2390b = fVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2390b));
    }
}
