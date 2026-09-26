package c.a.g0;

import c.a.b0.j.n;
import c.a.s;

/* JADX INFO: compiled from: SerializedSubject.java */
/* JADX INFO: loaded from: classes.dex */
final class b<T> extends c<T> implements c.a.b0.j.a.InterfaceC0074a<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c<T> f3152a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f3153b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    c.a.b0.j.a<Object> f3154c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    volatile boolean f3155d;

    b(c<T> cVar) {
        this.f3152a = cVar;
    }

    @Override // c.a.b0.j.a.InterfaceC0074a, c.a.a0.p
    public boolean a(Object obj) {
        return n.b(obj, this.f3152a);
    }

    void b() {
        c.a.b0.j.a<Object> aVar;
        while (true) {
            synchronized (this) {
                aVar = this.f3154c;
                if (aVar == null) {
                    this.f3153b = false;
                    return;
                }
                this.f3154c = null;
            }
            aVar.a((c.a.b0.j.a.InterfaceC0074a<? super Object>) this);
        }
    }

    @Override // c.a.s
    public void onComplete() {
        if (this.f3155d) {
            return;
        }
        synchronized (this) {
            if (this.f3155d) {
                return;
            }
            this.f3155d = true;
            if (!this.f3153b) {
                this.f3153b = true;
                this.f3152a.onComplete();
                return;
            }
            c.a.b0.j.a<Object> aVar = this.f3154c;
            if (aVar == null) {
                aVar = new c.a.b0.j.a<>(4);
                this.f3154c = aVar;
            }
            aVar.a(n.a());
        }
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        boolean z;
        if (this.f3155d) {
            c.a.e0.a.b(th);
            return;
        }
        synchronized (this) {
            if (this.f3155d) {
                z = true;
            } else {
                this.f3155d = true;
                if (this.f3153b) {
                    c.a.b0.j.a<Object> aVar = this.f3154c;
                    if (aVar == null) {
                        aVar = new c.a.b0.j.a<>(4);
                        this.f3154c = aVar;
                    }
                    aVar.b(n.a(th));
                    return;
                }
                z = false;
                this.f3153b = true;
            }
            if (z) {
                c.a.e0.a.b(th);
            } else {
                this.f3152a.onError(th);
            }
        }
    }

    @Override // c.a.s
    public void onNext(T t) {
        if (this.f3155d) {
            return;
        }
        synchronized (this) {
            if (this.f3155d) {
                return;
            }
            if (!this.f3153b) {
                this.f3153b = true;
                this.f3152a.onNext(t);
                b();
            } else {
                c.a.b0.j.a<Object> aVar = this.f3154c;
                if (aVar == null) {
                    aVar = new c.a.b0.j.a<>(4);
                    this.f3154c = aVar;
                }
                n.e(t);
                aVar.a(t);
            }
        }
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        boolean z = true;
        if (!this.f3155d) {
            synchronized (this) {
                if (!this.f3155d) {
                    if (this.f3153b) {
                        c.a.b0.j.a<Object> aVar = this.f3154c;
                        if (aVar == null) {
                            aVar = new c.a.b0.j.a<>(4);
                            this.f3154c = aVar;
                        }
                        aVar.a(n.a(bVar));
                        return;
                    }
                    this.f3153b = true;
                    z = false;
                }
            }
        }
        if (z) {
            bVar.dispose();
        } else {
            this.f3152a.onSubscribe(bVar);
            b();
        }
    }

    @Override // c.a.l
    protected void subscribeActual(s<? super T> sVar) {
        this.f3152a.subscribe(sVar);
    }
}
