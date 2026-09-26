package c.a.d0;

import c.a.s;

/* JADX INFO: compiled from: SafeObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<T> implements s<T>, c.a.y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final s<? super T> f3117a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c.a.y.b f3118b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f3119c;

    public e(s<? super T> sVar) {
        this.f3117a = sVar;
    }

    void a() {
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            this.f3117a.onSubscribe(c.a.b0.a.d.INSTANCE);
            try {
                this.f3117a.onError(nullPointerException);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                c.a.e0.a.b(new c.a.z.a(nullPointerException, th));
            }
        } catch (Throwable th2) {
            c.a.z.b.b(th2);
            c.a.e0.a.b(new c.a.z.a(nullPointerException, th2));
        }
    }

    void b() {
        this.f3119c = true;
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            this.f3117a.onSubscribe(c.a.b0.a.d.INSTANCE);
            try {
                this.f3117a.onError(nullPointerException);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                c.a.e0.a.b(new c.a.z.a(nullPointerException, th));
            }
        } catch (Throwable th2) {
            c.a.z.b.b(th2);
            c.a.e0.a.b(new c.a.z.a(nullPointerException, th2));
        }
    }

    @Override // c.a.y.b
    public void dispose() {
        this.f3118b.dispose();
    }

    @Override // c.a.s
    public void onComplete() {
        if (this.f3119c) {
            return;
        }
        this.f3119c = true;
        if (this.f3118b == null) {
            a();
            return;
        }
        try {
            this.f3117a.onComplete();
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.e0.a.b(th);
        }
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        if (this.f3119c) {
            c.a.e0.a.b(th);
            return;
        }
        this.f3119c = true;
        if (this.f3118b != null) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            try {
                this.f3117a.onError(th);
                return;
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                c.a.e0.a.b(new c.a.z.a(th, th2));
                return;
            }
        }
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            this.f3117a.onSubscribe(c.a.b0.a.d.INSTANCE);
            try {
                this.f3117a.onError(new c.a.z.a(th, nullPointerException));
            } catch (Throwable th3) {
                c.a.z.b.b(th3);
                c.a.e0.a.b(new c.a.z.a(th, nullPointerException, th3));
            }
        } catch (Throwable th4) {
            c.a.z.b.b(th4);
            c.a.e0.a.b(new c.a.z.a(th, nullPointerException, th4));
        }
    }

    @Override // c.a.s
    public void onNext(T t) {
        if (this.f3119c) {
            return;
        }
        if (this.f3118b == null) {
            b();
            return;
        }
        if (t == null) {
            NullPointerException nullPointerException = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
            try {
                this.f3118b.dispose();
                onError(nullPointerException);
                return;
            } catch (Throwable th) {
                c.a.z.b.b(th);
                onError(new c.a.z.a(nullPointerException, th));
                return;
            }
        }
        try {
            this.f3117a.onNext(t);
        } catch (Throwable th2) {
            c.a.z.b.b(th2);
            try {
                this.f3118b.dispose();
                onError(th2);
            } catch (Throwable th3) {
                c.a.z.b.b(th3);
                onError(new c.a.z.a(th2, th3));
            }
        }
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        if (c.a.b0.a.c.a(this.f3118b, bVar)) {
            this.f3118b = bVar;
            try {
                this.f3117a.onSubscribe(this);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f3119c = true;
                try {
                    bVar.dispose();
                    c.a.e0.a.b(th);
                } catch (Throwable th2) {
                    c.a.z.b.b(th2);
                    c.a.e0.a.b(new c.a.z.a(th, th2));
                }
            }
        }
    }
}
