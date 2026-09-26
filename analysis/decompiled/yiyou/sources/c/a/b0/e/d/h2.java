package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableRange.java */
/* JADX INFO: loaded from: classes.dex */
public final class h2 extends c.a.l<Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f2224a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final long f2225b;

    /* JADX INFO: compiled from: ObservableRange.java */
    static final class a extends c.a.b0.d.b<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super Integer> f2226a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2227b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f2228c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2229d;

        a(c.a.s<? super Integer> sVar, long j, long j2) {
            this.f2226a = sVar;
            this.f2228c = j;
            this.f2227b = j2;
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.f2229d = true;
            return 1;
        }

        @Override // c.a.b0.c.j
        public void clear() {
            this.f2228c = this.f2227b;
            lazySet(1);
        }

        @Override // c.a.y.b
        public void dispose() {
            set(1);
        }

        @Override // c.a.b0.c.j
        public boolean isEmpty() {
            return this.f2228c == this.f2227b;
        }

        void run() {
            if (this.f2229d) {
                return;
            }
            c.a.s<? super Integer> sVar = this.f2226a;
            long j = this.f2227b;
            for (long j2 = this.f2228c; j2 != j && get() == 0; j2++) {
                sVar.onNext(Integer.valueOf((int) j2));
            }
            if (get() == 0) {
                lazySet(1);
                sVar.onComplete();
            }
        }

        @Override // c.a.b0.c.j
        public Integer poll() {
            long j = this.f2228c;
            if (j != this.f2227b) {
                this.f2228c = 1 + j;
                return Integer.valueOf((int) j);
            }
            lazySet(1);
            return null;
        }
    }

    public h2(int i, int i2) {
        this.f2224a = i;
        this.f2225b = ((long) i) + ((long) i2);
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super Integer> sVar) {
        a aVar = new a(sVar, this.f2224a, this.f2225b);
        sVar.onSubscribe(aVar);
        aVar.run();
    }
}
