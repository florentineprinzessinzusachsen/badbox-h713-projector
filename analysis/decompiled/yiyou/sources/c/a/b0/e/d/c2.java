package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableObserveOn.java */
/* JADX INFO: loaded from: classes.dex */
public final class c2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.t f2007b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f2008c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f2009d;

    public c2(c.a.q<T> qVar, c.a.t tVar, boolean z, int i) {
        super(qVar);
        this.f2007b = tVar;
        this.f2008c = z;
        this.f2009d = i;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        c.a.t tVar = this.f2007b;
        if (tVar instanceof c.a.b0.g.o) {
            this.f1932a.subscribe(sVar);
        } else {
            this.f1932a.subscribe(new a(sVar, tVar.a(), this.f2008c, this.f2009d));
        }
    }

    /* JADX INFO: compiled from: ObservableObserveOn.java */
    static final class a<T> extends c.a.b0.d.b<T> implements c.a.s<T>, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2010a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.t.c f2011b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f2012c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f2013d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a.b0.c.j<T> f2014e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.y.b f2015f;
        Throwable g;
        volatile boolean h;
        volatile boolean i;
        int j;
        boolean k;

        a(c.a.s<? super T> sVar, c.a.t.c cVar, boolean z, int i) {
            this.f2010a = sVar;
            this.f2011b = cVar;
            this.f2012c = z;
            this.f2013d = i;
        }

        void a() {
            int iAddAndGet = 1;
            while (!this.i) {
                boolean z = this.h;
                Throwable th = this.g;
                if (!this.f2012c && z && th != null) {
                    this.f2010a.onError(th);
                    this.f2011b.dispose();
                    return;
                }
                this.f2010a.onNext(null);
                if (z) {
                    Throwable th2 = this.g;
                    if (th2 != null) {
                        this.f2010a.onError(th2);
                    } else {
                        this.f2010a.onComplete();
                    }
                    this.f2011b.dispose();
                    return;
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }

        void b() {
            c.a.b0.c.j<T> jVar = this.f2014e;
            c.a.s<? super T> sVar = this.f2010a;
            int iAddAndGet = 1;
            while (!a(this.h, jVar.isEmpty(), sVar)) {
                while (true) {
                    boolean z = this.h;
                    try {
                        T tPoll = jVar.poll();
                        boolean z2 = tPoll == null;
                        if (a(z, z2, sVar)) {
                            return;
                        }
                        if (z2) {
                            break;
                        } else {
                            sVar.onNext(tPoll);
                        }
                    } catch (Throwable th) {
                        c.a.z.b.b(th);
                        this.f2015f.dispose();
                        jVar.clear();
                        sVar.onError(th);
                        this.f2011b.dispose();
                        return;
                    }
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }

        void c() {
            if (getAndIncrement() == 0) {
                this.f2011b.a(this);
            }
        }

        @Override // c.a.b0.c.j
        public void clear() {
            this.f2014e.clear();
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.i) {
                return;
            }
            this.i = true;
            this.f2015f.dispose();
            this.f2011b.dispose();
            if (getAndIncrement() == 0) {
                this.f2014e.clear();
            }
        }

        @Override // c.a.b0.c.j
        public boolean isEmpty() {
            return this.f2014e.isEmpty();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.h) {
                return;
            }
            this.h = true;
            c();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.h) {
                c.a.e0.a.b(th);
                return;
            }
            this.g = th;
            this.h = true;
            c();
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.h) {
                return;
            }
            if (this.j != 2) {
                this.f2014e.offer(t);
            }
            c();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2015f, bVar)) {
                this.f2015f = bVar;
                if (bVar instanceof c.a.b0.c.e) {
                    c.a.b0.c.e eVar = (c.a.b0.c.e) bVar;
                    int iA = eVar.a(7);
                    if (iA == 1) {
                        this.j = iA;
                        this.f2014e = eVar;
                        this.h = true;
                        this.f2010a.onSubscribe(this);
                        c();
                        return;
                    }
                    if (iA == 2) {
                        this.j = iA;
                        this.f2014e = eVar;
                        this.f2010a.onSubscribe(this);
                        return;
                    }
                }
                this.f2014e = new c.a.b0.f.c(this.f2013d);
                this.f2010a.onSubscribe(this);
            }
        }

        @Override // c.a.b0.c.j
        public T poll() {
            return this.f2014e.poll();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.k) {
                a();
            } else {
                b();
            }
        }

        boolean a(boolean z, boolean z2, c.a.s<? super T> sVar) {
            if (this.i) {
                this.f2014e.clear();
                return true;
            }
            if (!z) {
                return false;
            }
            Throwable th = this.g;
            if (this.f2012c) {
                if (!z2) {
                    return false;
                }
                if (th != null) {
                    sVar.onError(th);
                } else {
                    sVar.onComplete();
                }
                this.f2011b.dispose();
                return true;
            }
            if (th != null) {
                this.f2014e.clear();
                sVar.onError(th);
                this.f2011b.dispose();
                return true;
            }
            if (!z2) {
                return false;
            }
            sVar.onComplete();
            this.f2011b.dispose();
            return true;
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.k = true;
            return 2;
        }
    }
}
