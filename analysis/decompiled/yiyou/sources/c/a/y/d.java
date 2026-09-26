package c.a.y;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ReferenceDisposable.java */
/* JADX INFO: loaded from: classes.dex */
abstract class d<T> extends AtomicReference<T> implements b {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    d(T t) {
        super(t);
        c.a.b0.b.b.a((Object) t, "value is null");
    }

    protected abstract void a(T t);

    public final boolean a() {
        return get() == null;
    }

    @Override // c.a.y.b
    public final void dispose() {
        T andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        a(andSet);
    }
}
