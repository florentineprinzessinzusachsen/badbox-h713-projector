package c.a.d0;

import c.a.b0.j.h;
import c.a.s;

/* JADX INFO: compiled from: DefaultObserver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<T> implements s<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c.a.y.b f3115a;

    protected void a() {
    }

    @Override // c.a.s
    public final void onSubscribe(c.a.y.b bVar) {
        if (h.a(this.f3115a, bVar, getClass())) {
            this.f3115a = bVar;
            a();
        }
    }
}
