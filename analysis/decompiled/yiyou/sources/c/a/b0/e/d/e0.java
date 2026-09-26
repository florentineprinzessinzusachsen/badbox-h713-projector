package c.a.b0.e.d;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableDefer.java */
/* JADX INFO: loaded from: classes.dex */
public final class e0<T> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Callable<? extends c.a.q<? extends T>> f2090a;

    public e0(Callable<? extends c.a.q<? extends T>> callable) {
        this.f2090a = callable;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        try {
            c.a.q<? extends T> qVarCall = this.f2090a.call();
            c.a.b0.b.b.a(qVarCall, "null ObservableSource supplied");
            qVarCall.subscribe(sVar);
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, sVar);
        }
    }
}
