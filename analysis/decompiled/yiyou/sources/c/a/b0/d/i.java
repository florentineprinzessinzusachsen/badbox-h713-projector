package c.a.b0.d;

/* JADX INFO: compiled from: DeferredScalarDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public class i<T> extends b<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final c.a.s<? super T> f1810a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected T f1811b;

    public i(c.a.s<? super T> sVar) {
        this.f1810a = sVar;
    }

    @Override // c.a.b0.c.f
    public final int a(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    public final void b(T t) {
        int i = get();
        if ((i & 54) != 0) {
            return;
        }
        c.a.s<? super T> sVar = this.f1810a;
        if (i == 8) {
            this.f1811b = t;
            lazySet(16);
            sVar.onNext(null);
        } else {
            lazySet(2);
            sVar.onNext(t);
        }
        if (get() != 4) {
            sVar.onComplete();
        }
    }

    @Override // c.a.b0.c.j
    public final void clear() {
        lazySet(32);
        this.f1811b = null;
    }

    @Override // c.a.y.b
    public void dispose() {
        set(4);
        this.f1811b = null;
    }

    @Override // c.a.b0.c.j
    public final boolean isEmpty() {
        return get() != 16;
    }

    @Override // c.a.b0.c.j
    public final T poll() {
        if (get() != 16) {
            return null;
        }
        T t = this.f1811b;
        this.f1811b = null;
        lazySet(32);
        return t;
    }

    public final void a(Throwable th) {
        if ((get() & 54) != 0) {
            c.a.e0.a.b(th);
        } else {
            lazySet(2);
            this.f1810a.onError(th);
        }
    }

    public final void a() {
        if ((get() & 54) != 0) {
            return;
        }
        lazySet(2);
        this.f1810a.onComplete();
    }

    public final boolean b() {
        return get() == 4;
    }
}
