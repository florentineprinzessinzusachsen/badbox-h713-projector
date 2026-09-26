package c.a.b0.d;

/* JADX INFO: compiled from: QueueDrainObserver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p<T, U, V> extends r implements c.a.s<T>, c.a.b0.j.o<U, V> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final c.a.s<? super V> f1832b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final c.a.b0.c.i<U> f1833c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected volatile boolean f1834d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected volatile boolean f1835e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected Throwable f1836f;

    public p(c.a.s<? super V> sVar, c.a.b0.c.i<U> iVar) {
        this.f1832b = sVar;
        this.f1833c = iVar;
    }

    @Override // c.a.b0.j.o
    public void a(c.a.s<? super V> sVar, U u) {
    }

    @Override // c.a.b0.j.o
    public final boolean a() {
        return this.f1835e;
    }

    @Override // c.a.b0.j.o
    public final boolean b() {
        return this.f1834d;
    }

    @Override // c.a.b0.j.o
    public final Throwable c() {
        return this.f1836f;
    }

    public final boolean d() {
        return this.f1837a.getAndIncrement() == 0;
    }

    public final boolean e() {
        return this.f1837a.get() == 0 && this.f1837a.compareAndSet(0, 1);
    }

    protected final void a(U u, boolean z, c.a.y.b bVar) {
        c.a.s<? super V> sVar = this.f1832b;
        c.a.b0.c.i<U> iVar = this.f1833c;
        if (this.f1837a.get() == 0 && this.f1837a.compareAndSet(0, 1)) {
            a(sVar, u);
            if (a(-1) == 0) {
                return;
            }
        } else {
            iVar.offer(u);
            if (!d()) {
                return;
            }
        }
        c.a.b0.j.r.a(iVar, sVar, z, bVar, this);
    }

    protected final void b(U u, boolean z, c.a.y.b bVar) {
        c.a.s<? super V> sVar = this.f1832b;
        c.a.b0.c.i<U> iVar = this.f1833c;
        if (this.f1837a.get() != 0 || !this.f1837a.compareAndSet(0, 1)) {
            iVar.offer(u);
            if (!d()) {
                return;
            }
        } else if (iVar.isEmpty()) {
            a(sVar, u);
            if (a(-1) == 0) {
                return;
            }
        } else {
            iVar.offer(u);
        }
        c.a.b0.j.r.a(iVar, sVar, z, bVar, this);
    }

    @Override // c.a.b0.j.o
    public final int a(int i) {
        return this.f1837a.addAndGet(i);
    }
}
