package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class v1<T, U> extends c.a.b0.e.d.a<T, U> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends U> f2801b;

    /* JADX INFO: compiled from: ObservableMap.java */
    static final class a<T, U> extends c.a.b0.d.a<T, U> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends U> f2802f;

        a(c.a.s<? super U> sVar, c.a.a0.n<? super T, ? extends U> nVar) {
            super(sVar);
            this.f2802f = nVar;
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            return b(i);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onNext(T t) {
            if (this.f1798d) {
                return;
            }
            if (this.f1799e != 0) {
                this.f1795a.onNext(null);
                return;
            }
            try {
                U uApply = this.f2802f.apply(t);
                c.a.b0.b.b.a(uApply, "The mapper function returned a null value.");
                this.f1795a.onNext((Object) uApply);
            } catch (Throwable th) {
                a(th);
            }
        }

        @Override // c.a.b0.c.j
        public U poll() {
            T tPoll = this.f1797c.poll();
            if (tPoll == null) {
                return null;
            }
            U uApply = this.f2802f.apply(tPoll);
            c.a.b0.b.b.a(uApply, "The mapper function returned a null value.");
            return uApply;
        }
    }

    public v1(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends U> nVar) {
        super(qVar);
        this.f2801b = nVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super U> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2801b));
    }
}
