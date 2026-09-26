package c.a.b0.e.d;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableError.java */
/* JADX INFO: loaded from: classes.dex */
public final class t0<T> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Callable<? extends Throwable> f2710a;

    public t0(Callable<? extends Throwable> callable) {
        this.f2710a = callable;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        try {
            Throwable thCall = this.f2710a.call();
            c.a.b0.b.b.a(thCall, "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
            th = thCall;
        } catch (Throwable th) {
            th = th;
            c.a.z.b.b(th);
        }
        c.a.b0.a.d.a(th, sVar);
    }
}
