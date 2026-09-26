package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableTake.java */
/* JADX INFO: loaded from: classes.dex */
public final class m3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2454b;

    /* JADX INFO: compiled from: ObservableTake.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2455a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f2456b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2457c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f2458d;

        a(c.a.s<? super T> sVar, long j) {
            this.f2455a = sVar;
            this.f2458d = j;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2457c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2456b) {
                return;
            }
            this.f2456b = true;
            this.f2457c.dispose();
            this.f2455a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2456b) {
                c.a.e0.a.b(th);
                return;
            }
            this.f2456b = true;
            this.f2457c.dispose();
            this.f2455a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2456b) {
                return;
            }
            long j = this.f2458d;
            this.f2458d = j - 1;
            if (j > 0) {
                boolean z = this.f2458d == 0;
                this.f2455a.onNext(t);
                if (z) {
                    onComplete();
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2457c, bVar)) {
                this.f2457c = bVar;
                if (this.f2458d != 0) {
                    this.f2455a.onSubscribe(this);
                    return;
                }
                this.f2456b = true;
                bVar.dispose();
                c.a.b0.a.d.a(this.f2455a);
            }
        }
    }

    public m3(c.a.q<T> qVar, long j) {
        super(qVar);
        this.f2454b = j;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2454b));
    }
}
