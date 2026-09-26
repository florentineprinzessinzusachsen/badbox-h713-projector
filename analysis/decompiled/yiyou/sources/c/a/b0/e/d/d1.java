package c.a.b0.e.d;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ObservableFromFuture.java */
/* JADX INFO: loaded from: classes.dex */
public final class d1<T> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Future<? extends T> f2048a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2049b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final TimeUnit f2050c;

    public d1(Future<? extends T> future, long j, TimeUnit timeUnit) {
        this.f2048a = future;
        this.f2049b = j;
        this.f2050c = timeUnit;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        c.a.b0.d.i iVar = new c.a.b0.d.i(sVar);
        sVar.onSubscribe(iVar);
        if (iVar.b()) {
            return;
        }
        try {
            T t = this.f2050c != null ? this.f2048a.get(this.f2049b, this.f2050c) : this.f2048a.get();
            c.a.b0.b.b.a((Object) t, "Future returned null");
            iVar.b(t);
        } catch (Throwable th) {
            c.a.z.b.b(th);
            if (iVar.b()) {
                return;
            }
            sVar.onError(th);
        }
    }
}
