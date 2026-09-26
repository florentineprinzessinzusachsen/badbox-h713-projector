package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableFilter.java */
/* JADX INFO: loaded from: classes.dex */
public final class u0<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.p<? super T> f2753b;

    /* JADX INFO: compiled from: ObservableFilter.java */
    static final class a<T> extends c.a.b0.d.a<T, T> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.a0.p<? super T> f2754f;

        a(c.a.s<? super T> sVar, c.a.a0.p<? super T> pVar) {
            super(sVar);
            this.f2754f = pVar;
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            return b(i);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onNext(T t) {
            if (this.f1799e != 0) {
                this.f1795a.onNext(null);
                return;
            }
            try {
                if (this.f2754f.a(t)) {
                    this.f1795a.onNext((Object) t);
                }
            } catch (Throwable th) {
                a(th);
            }
        }

        @Override // c.a.b0.c.j
        public T poll() {
            T tPoll;
            do {
                tPoll = this.f1797c.poll();
                if (tPoll == null) {
                    break;
                }
            } while (!this.f2754f.a(tPoll));
            return tPoll;
        }
    }

    public u0(c.a.q<T> qVar, c.a.a0.p<? super T> pVar) {
        super(qVar);
        this.f2753b = pVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2753b));
    }
}
