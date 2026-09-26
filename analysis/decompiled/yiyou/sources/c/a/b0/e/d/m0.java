package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableDoFinally.java */
/* JADX INFO: loaded from: classes.dex */
public final class m0<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.a f2431b;

    public m0(c.a.q<T> qVar, c.a.a0.a aVar) {
        super(qVar);
        this.f2431b = aVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2431b));
    }

    /* JADX INFO: compiled from: ObservableDoFinally.java */
    static final class a<T> extends c.a.b0.d.b<T> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2432a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.a f2433b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2434c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c.a.b0.c.e<T> f2435d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2436e;

        a(c.a.s<? super T> sVar, c.a.a0.a aVar) {
            this.f2432a = sVar;
            this.f2433b = aVar;
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            c.a.b0.c.e<T> eVar = this.f2435d;
            if (eVar == null || (i & 4) != 0) {
                return 0;
            }
            int iA = eVar.a(i);
            if (iA != 0) {
                this.f2436e = iA == 1;
            }
            return iA;
        }

        @Override // c.a.b0.c.j
        public void clear() {
            this.f2435d.clear();
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2434c.dispose();
            a();
        }

        @Override // c.a.b0.c.j
        public boolean isEmpty() {
            return this.f2435d.isEmpty();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2432a.onComplete();
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2432a.onError(th);
            a();
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2432a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2434c, bVar)) {
                this.f2434c = bVar;
                if (bVar instanceof c.a.b0.c.e) {
                    this.f2435d = (c.a.b0.c.e) bVar;
                }
                this.f2432a.onSubscribe(this);
            }
        }

        @Override // c.a.b0.c.j
        public T poll() {
            T tPoll = this.f2435d.poll();
            if (tPoll == null && this.f2436e) {
                a();
            }
            return tPoll;
        }

        void a() {
            if (compareAndSet(0, 1)) {
                try {
                    this.f2433b.run();
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    c.a.e0.a.b(th);
                }
            }
        }
    }
}
