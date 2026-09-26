package c.a.d0;

import c.a.b0.j.h;
import c.a.s;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: DisposableObserver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> implements s<T>, c.a.y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final AtomicReference<c.a.y.b> f3116a = new AtomicReference<>();

    protected void a() {
    }

    @Override // c.a.y.b
    public final void dispose() {
        c.a.b0.a.c.a(this.f3116a);
    }

    @Override // c.a.s
    public final void onSubscribe(c.a.y.b bVar) {
        if (h.a(this.f3116a, bVar, getClass())) {
            a();
        }
    }
}
