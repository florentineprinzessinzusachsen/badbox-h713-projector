package c.a.b0.e.d;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableReduceWithSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class l2<T, R> extends c.a.u<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2394a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Callable<R> f2395b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.c<R, ? super T, R> f2396c;

    public l2(c.a.q<T> qVar, Callable<R> callable, c.a.a0.c<R, ? super T, R> cVar) {
        this.f2394a = qVar;
        this.f2395b = callable;
        this.f2396c = cVar;
    }

    @Override // c.a.u
    protected void b(c.a.v<? super R> vVar) {
        try {
            R rCall = this.f2395b.call();
            c.a.b0.b.b.a(rCall, "The seedSupplier returned a null value");
            this.f2394a.subscribe(new k2.a(vVar, this.f2396c, rCall));
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, vVar);
        }
    }
}
