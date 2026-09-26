package c.a.b0.a;

import c.a.s;
import c.a.v;

/* JADX INFO: compiled from: EmptyDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public enum d implements c.a.b0.c.e<Object> {
    INSTANCE,
    NEVER;

    public static void a(s<?> sVar) {
        sVar.onSubscribe(INSTANCE);
        sVar.onComplete();
    }

    @Override // c.a.b0.c.f
    public int a(int i) {
        return i & 2;
    }

    @Override // c.a.b0.c.j
    public void clear() {
    }

    @Override // c.a.y.b
    public void dispose() {
    }

    @Override // c.a.b0.c.j
    public boolean isEmpty() {
        return true;
    }

    @Override // c.a.b0.c.j
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // c.a.b0.c.j
    public Object poll() {
        return null;
    }

    public static void a(Throwable th, s<?> sVar) {
        sVar.onSubscribe(INSTANCE);
        sVar.onError(th);
    }

    public static void a(c.a.c cVar) {
        cVar.onSubscribe(INSTANCE);
        cVar.onComplete();
    }

    public static void a(Throwable th, c.a.c cVar) {
        cVar.onSubscribe(INSTANCE);
        cVar.onError(th);
    }

    public static void a(Throwable th, v<?> vVar) {
        vVar.onSubscribe(INSTANCE);
        vVar.onError(th);
    }
}
