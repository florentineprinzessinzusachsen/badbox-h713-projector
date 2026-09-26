package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableSingleMaybe.java */
/* JADX INFO: loaded from: classes.dex */
public final class c3<T> extends c.a.h<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2016a;

    /* JADX INFO: compiled from: ObservableSingleMaybe.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.i<? super T> f2017a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f2018b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        T f2019c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2020d;

        a(c.a.i<? super T> iVar) {
            this.f2017a = iVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2018b.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2020d) {
                return;
            }
            this.f2020d = true;
            T t = this.f2019c;
            this.f2019c = null;
            if (t == null) {
                this.f2017a.onComplete();
            } else {
                this.f2017a.a(t);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2020d) {
                c.a.e0.a.b(th);
            } else {
                this.f2020d = true;
                this.f2017a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2020d) {
                return;
            }
            if (this.f2019c == null) {
                this.f2019c = t;
                return;
            }
            this.f2020d = true;
            this.f2018b.dispose();
            this.f2017a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2018b, bVar)) {
                this.f2018b = bVar;
                this.f2017a.onSubscribe(this);
            }
        }
    }

    public c3(c.a.q<T> qVar) {
        this.f2016a = qVar;
    }

    @Override // c.a.h
    public void b(c.a.i<? super T> iVar) {
        this.f2016a.subscribe(new a(iVar));
    }
}
