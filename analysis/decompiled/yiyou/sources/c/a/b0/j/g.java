package c.a.b0.j;

import c.a.v;

/* JADX INFO: compiled from: EmptyComponent.java */
/* JADX INFO: loaded from: classes.dex */
public enum g implements c.a.g<Object>, c.a.s<Object>, c.a.i<Object>, v<Object>, c.a.c, f.a.c, c.a.y.b {
    INSTANCE;

    public static <T> c.a.s<T> a() {
        return INSTANCE;
    }

    @Override // c.a.i
    public void a(Object obj) {
    }

    @Override // f.a.c
    public void c(long j) {
    }

    @Override // f.a.c
    public void cancel() {
    }

    @Override // c.a.y.b
    public void dispose() {
    }

    @Override // f.a.b
    public void onComplete() {
    }

    @Override // f.a.b
    public void onError(Throwable th) {
        c.a.e0.a.b(th);
    }

    @Override // f.a.b
    public void onNext(Object obj) {
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        bVar.dispose();
    }

    @Override // f.a.b
    public void a(f.a.c cVar) {
        cVar.cancel();
    }
}
