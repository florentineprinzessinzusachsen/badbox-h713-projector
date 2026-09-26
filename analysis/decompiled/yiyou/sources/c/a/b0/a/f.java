package c.a.b0.a;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: SequentialDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends AtomicReference<c.a.y.b> implements c.a.y.b {
    public f() {
    }

    public boolean a(c.a.y.b bVar) {
        return c.a((AtomicReference<c.a.y.b>) this, bVar);
    }

    public boolean b(c.a.y.b bVar) {
        return c.b(this, bVar);
    }

    @Override // c.a.y.b
    public void dispose() {
        c.a((AtomicReference<c.a.y.b>) this);
    }

    public f(c.a.y.b bVar) {
        lazySet(bVar);
    }

    public boolean a() {
        return c.a(get());
    }
}
