package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableRangeLong.java */
/* JADX INFO: loaded from: classes.dex */
public final class i2 extends c.a.l<Long> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final long f2275a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final long f2276b;

    /* JADX INFO: compiled from: ObservableRangeLong.java */
    static final class a extends c.a.b0.d.b<Long> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super Long> f2277a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2278b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f2279c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2280d;

        a(c.a.s<? super Long> sVar, long j, long j2) {
            this.f2277a = sVar;
            this.f2279c = j;
            this.f2278b = j2;
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.f2280d = true;
            return 1;
        }

        @Override // c.a.b0.c.j
        public void clear() {
            this.f2279c = this.f2278b;
            lazySet(1);
        }

        @Override // c.a.y.b
        public void dispose() {
            set(1);
        }

        @Override // c.a.b0.c.j
        public boolean isEmpty() {
            return this.f2279c == this.f2278b;
        }

        void run() {
            if (this.f2280d) {
                return;
            }
            c.a.s<? super Long> sVar = this.f2277a;
            long j = this.f2278b;
            for (long j2 = this.f2279c; j2 != j && get() == 0; j2++) {
                sVar.onNext(Long.valueOf(j2));
            }
            if (get() == 0) {
                lazySet(1);
                sVar.onComplete();
            }
        }

        @Override // c.a.b0.c.j
        public Long poll() {
            long j = this.f2279c;
            if (j != this.f2278b) {
                this.f2279c = 1 + j;
                return Long.valueOf(j);
            }
            lazySet(1);
            return null;
        }
    }

    public i2(long j, long j2) {
        this.f2275a = j;
        this.f2276b = j2;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super Long> sVar) {
        long j = this.f2275a;
        a aVar = new a(sVar, j, j + this.f2276b);
        sVar.onSubscribe(aVar);
        aVar.run();
    }
}
