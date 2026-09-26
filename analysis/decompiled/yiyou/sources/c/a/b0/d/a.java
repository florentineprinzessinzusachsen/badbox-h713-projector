package c.a.b0.d;

/* JADX INFO: compiled from: BasicFuseableObserver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T, R> implements c.a.s<T>, c.a.b0.c.e<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final c.a.s<? super R> f1795a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected c.a.y.b f1796b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected c.a.b0.c.e<T> f1797c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected boolean f1798d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f1799e;

    public a(c.a.s<? super R> sVar) {
        this.f1795a = sVar;
    }

    protected void a() {
    }

    protected final void a(Throwable th) {
        c.a.z.b.b(th);
        this.f1796b.dispose();
        onError(th);
    }

    protected final int b(int i) {
        c.a.b0.c.e<T> eVar = this.f1797c;
        if (eVar == null || (i & 4) != 0) {
            return 0;
        }
        int iA = eVar.a(i);
        if (iA != 0) {
            this.f1799e = iA;
        }
        return iA;
    }

    protected boolean b() {
        return true;
    }

    @Override // c.a.b0.c.j
    public void clear() {
        this.f1797c.clear();
    }

    @Override // c.a.y.b
    public void dispose() {
        this.f1796b.dispose();
    }

    @Override // c.a.b0.c.j
    public boolean isEmpty() {
        return this.f1797c.isEmpty();
    }

    @Override // c.a.b0.c.j
    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // c.a.s
    public void onComplete() {
        if (this.f1798d) {
            return;
        }
        this.f1798d = true;
        this.f1795a.onComplete();
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        if (this.f1798d) {
            c.a.e0.a.b(th);
        } else {
            this.f1798d = true;
            this.f1795a.onError(th);
        }
    }

    @Override // c.a.s
    public final void onSubscribe(c.a.y.b bVar) {
        if (c.a.b0.a.c.a(this.f1796b, bVar)) {
            this.f1796b = bVar;
            if (bVar instanceof c.a.b0.c.e) {
                this.f1797c = (c.a.b0.c.e) bVar;
            }
            if (b()) {
                this.f1795a.onSubscribe(this);
                a();
            }
        }
    }
}
