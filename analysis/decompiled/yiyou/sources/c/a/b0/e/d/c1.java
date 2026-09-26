package c.a.b0.e.d;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableFromCallable.java */
/* JADX INFO: loaded from: classes.dex */
public final class c1<T> extends c.a.l<T> implements Callable<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Callable<? extends T> f2006a;

    public c1(Callable<? extends T> callable) {
        this.f2006a = callable;
    }

    @Override // java.util.concurrent.Callable
    public T call() throws Exception {
        T tCall = this.f2006a.call();
        c.a.b0.b.b.a((Object) tCall, "The callable returned a null value");
        return tCall;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        c.a.b0.d.i iVar = new c.a.b0.d.i(sVar);
        sVar.onSubscribe(iVar);
        if (iVar.b()) {
            return;
        }
        try {
            T tCall = this.f2006a.call();
            c.a.b0.b.b.a((Object) tCall, "Callable returned null");
            iVar.b(tCall);
        } catch (Throwable th) {
            c.a.z.b.b(th);
            if (iVar.b()) {
                c.a.e0.a.b(th);
            } else {
                sVar.onError(th);
            }
        }
    }
}
