package c.a.d0;

import c.a.b0.j.n;
import c.a.s;

/* JADX INFO: compiled from: SerializedObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<T> implements s<T>, c.a.y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final s<? super T> f3120a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final boolean f3121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    c.a.y.b f3122c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f3123d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    c.a.b0.j.a<Object> f3124e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    volatile boolean f3125f;

    public f(s<? super T> sVar) {
        this(sVar, false);
    }

    void a() {
        c.a.b0.j.a<Object> aVar;
        do {
            synchronized (this) {
                aVar = this.f3124e;
                if (aVar == null) {
                    this.f3123d = false;
                    return;
                }
                this.f3124e = null;
            }
        } while (!aVar.a((s) this.f3120a));
    }

    @Override // c.a.y.b
    public void dispose() {
        this.f3122c.dispose();
    }

    @Override // c.a.s
    public void onComplete() {
        if (this.f3125f) {
            return;
        }
        synchronized (this) {
            if (this.f3125f) {
                return;
            }
            if (!this.f3123d) {
                this.f3125f = true;
                this.f3123d = true;
                this.f3120a.onComplete();
            } else {
                c.a.b0.j.a<Object> aVar = this.f3124e;
                if (aVar == null) {
                    aVar = new c.a.b0.j.a<>(4);
                    this.f3124e = aVar;
                }
                aVar.a(n.a());
            }
        }
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        if (this.f3125f) {
            c.a.e0.a.b(th);
            return;
        }
        synchronized (this) {
            boolean z = true;
            if (!this.f3125f) {
                if (this.f3123d) {
                    this.f3125f = true;
                    c.a.b0.j.a<Object> aVar = this.f3124e;
                    if (aVar == null) {
                        aVar = new c.a.b0.j.a<>(4);
                        this.f3124e = aVar;
                    }
                    Object objA = n.a(th);
                    if (this.f3121b) {
                        aVar.a(objA);
                    } else {
                        aVar.b(objA);
                    }
                    return;
                }
                this.f3125f = true;
                this.f3123d = true;
                z = false;
            }
            if (z) {
                c.a.e0.a.b(th);
            } else {
                this.f3120a.onError(th);
            }
        }
    }

    @Override // c.a.s
    public void onNext(T t) {
        if (this.f3125f) {
            return;
        }
        if (t == null) {
            this.f3122c.dispose();
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return;
        }
        synchronized (this) {
            if (this.f3125f) {
                return;
            }
            if (!this.f3123d) {
                this.f3123d = true;
                this.f3120a.onNext(t);
                a();
            } else {
                c.a.b0.j.a<Object> aVar = this.f3124e;
                if (aVar == null) {
                    aVar = new c.a.b0.j.a<>(4);
                    this.f3124e = aVar;
                }
                n.e(t);
                aVar.a(t);
            }
        }
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        if (c.a.b0.a.c.a(this.f3122c, bVar)) {
            this.f3122c = bVar;
            this.f3120a.onSubscribe(this);
        }
    }

    public f(s<? super T> sVar, boolean z) {
        this.f3120a = sVar;
        this.f3121b = z;
    }
}
