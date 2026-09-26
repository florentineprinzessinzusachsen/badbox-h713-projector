package c.a.b0.h;

import c.a.b0.j.k;
import c.a.g;
import f.a.b;
import f.a.c;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: StrictSubscriber.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> extends AtomicInteger implements g<T>, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b<? super T> f3070a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.b0.j.c f3071b = new c.a.b0.j.c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AtomicLong f3072c = new AtomicLong();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final AtomicReference<c> f3073d = new AtomicReference<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final AtomicBoolean f3074e = new AtomicBoolean();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    volatile boolean f3075f;

    public a(b<? super T> bVar) {
        this.f3070a = bVar;
    }

    @Override // f.a.b
    public void a(c cVar) {
        if (this.f3074e.compareAndSet(false, true)) {
            this.f3070a.a(this);
            c.a.b0.i.b.a(this.f3073d, this.f3072c, cVar);
        } else {
            cVar.cancel();
            cancel();
            onError(new IllegalStateException("§2.12 violated: onSubscribe must be called at most once"));
        }
    }

    @Override // f.a.c
    public void c(long j) {
        if (j > 0) {
            c.a.b0.i.b.a(this.f3073d, this.f3072c, j);
            return;
        }
        cancel();
        onError(new IllegalArgumentException("§3.9 violated: positive request amount required but it was " + j));
    }

    @Override // f.a.c
    public void cancel() {
        if (this.f3075f) {
            return;
        }
        c.a.b0.i.b.a(this.f3073d);
    }

    @Override // f.a.b
    public void onComplete() {
        this.f3075f = true;
        k.a(this.f3070a, this, this.f3071b);
    }

    @Override // f.a.b
    public void onError(Throwable th) {
        this.f3075f = true;
        k.a((b<?>) this.f3070a, th, (AtomicInteger) this, this.f3071b);
    }

    @Override // f.a.b
    public void onNext(T t) {
        k.a(this.f3070a, t, this, this.f3071b);
    }
}
