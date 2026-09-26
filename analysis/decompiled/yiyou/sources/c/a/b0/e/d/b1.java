package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableFromArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class b1<T> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final T[] f1978a;

    /* JADX INFO: compiled from: ObservableFromArray.java */
    static final class a<T> extends c.a.b0.d.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f1979a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final T[] f1980b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1981c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1982d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f1983e;

        a(c.a.s<? super T> sVar, T[] tArr) {
            this.f1979a = sVar;
            this.f1980b = tArr;
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.f1982d = true;
            return 1;
        }

        void b() {
            T[] tArr = this.f1980b;
            int length = tArr.length;
            for (int i = 0; i < length && !a(); i++) {
                T t = tArr[i];
                if (t == null) {
                    this.f1979a.onError(new NullPointerException("The " + i + "th element is null"));
                    return;
                }
                this.f1979a.onNext(t);
            }
            if (a()) {
                return;
            }
            this.f1979a.onComplete();
        }

        @Override // c.a.b0.c.j
        public void clear() {
            this.f1981c = this.f1980b.length;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f1983e = true;
        }

        @Override // c.a.b0.c.j
        public boolean isEmpty() {
            return this.f1981c == this.f1980b.length;
        }

        @Override // c.a.b0.c.j
        public T poll() {
            int i = this.f1981c;
            T[] tArr = this.f1980b;
            if (i == tArr.length) {
                return null;
            }
            this.f1981c = i + 1;
            T t = tArr[i];
            c.a.b0.b.b.a((Object) t, "The array element is null");
            return t;
        }

        public boolean a() {
            return this.f1983e;
        }
    }

    public b1(T[] tArr) {
        this.f1978a = tArr;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        a aVar = new a(sVar, this.f1978a);
        sVar.onSubscribe(aVar);
        if (aVar.f1982d) {
            return;
        }
        aVar.b();
    }
}
