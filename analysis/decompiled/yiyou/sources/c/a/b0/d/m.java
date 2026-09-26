package c.a.b0.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: InnerQueuedObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class m<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final n<T> f1823a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f1824b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    c.a.b0.c.j<T> f1825c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    volatile boolean f1826d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1827e;

    public m(n<T> nVar, int i) {
        this.f1823a = nVar;
        this.f1824b = i;
    }

    public boolean a() {
        return this.f1826d;
    }

    public c.a.b0.c.j<T> b() {
        return this.f1825c;
    }

    public void c() {
        this.f1826d = true;
    }

    @Override // c.a.y.b
    public void dispose() {
        c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
    }

    @Override // c.a.s
    public void onComplete() {
        this.f1823a.a(this);
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        this.f1823a.a((m) this, th);
    }

    @Override // c.a.s
    public void onNext(T t) {
        if (this.f1827e == 0) {
            this.f1823a.a(this, t);
        } else {
            this.f1823a.a();
        }
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        if (c.a.b0.a.c.c(this, bVar)) {
            if (bVar instanceof c.a.b0.c.e) {
                c.a.b0.c.e eVar = (c.a.b0.c.e) bVar;
                int iA = eVar.a(3);
                if (iA == 1) {
                    this.f1827e = iA;
                    this.f1825c = eVar;
                    this.f1826d = true;
                    this.f1823a.a(this);
                    return;
                }
                if (iA == 2) {
                    this.f1827e = iA;
                    this.f1825c = eVar;
                    return;
                }
            }
            this.f1825c = c.a.b0.j.r.a(-this.f1824b);
        }
    }
}
