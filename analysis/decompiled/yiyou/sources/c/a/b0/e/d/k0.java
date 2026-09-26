package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableDistinctUntilChanged.java */
/* JADX INFO: loaded from: classes.dex */
public final class k0<T, K> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, K> f2342b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.d<? super K, ? super K> f2343c;

    /* JADX INFO: compiled from: ObservableDistinctUntilChanged.java */
    static final class a<T, K> extends c.a.b0.d.a<T, T> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.a0.n<? super T, K> f2344f;
        final c.a.a0.d<? super K, ? super K> g;
        K h;
        boolean i;

        a(c.a.s<? super T> sVar, c.a.a0.n<? super T, K> nVar, c.a.a0.d<? super K, ? super K> dVar) {
            super(sVar);
            this.f2344f = nVar;
            this.g = dVar;
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
                this.f1795a.onNext((Object) t);
                return;
            }
            try {
                K kApply = this.f2344f.apply(t);
                if (this.i) {
                    boolean zA = this.g.a(this.h, kApply);
                    this.h = kApply;
                    if (zA) {
                        return;
                    }
                } else {
                    this.i = true;
                    this.h = kApply;
                }
                this.f1795a.onNext((Object) t);
            } catch (Throwable th) {
                a(th);
            }
        }

        @Override // c.a.b0.c.j
        public T poll() {
            while (true) {
                T tPoll = this.f1797c.poll();
                if (tPoll == null) {
                    return null;
                }
                K kApply = this.f2344f.apply(tPoll);
                if (!this.i) {
                    this.i = true;
                    this.h = kApply;
                    return tPoll;
                }
                if (!this.g.a(this.h, kApply)) {
                    this.h = kApply;
                    return tPoll;
                }
                this.h = kApply;
            }
        }
    }

    public k0(c.a.q<T> qVar, c.a.a0.n<? super T, K> nVar, c.a.a0.d<? super K, ? super K> dVar) {
        super(qVar);
        this.f2342b = nVar;
        this.f2343c = dVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2342b, this.f2343c));
    }
}
