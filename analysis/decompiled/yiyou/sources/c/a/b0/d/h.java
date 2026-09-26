package c.a.b0.d;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: BlockingObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class h<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.y.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f1808b = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Queue<Object> f1809a;

    public h(Queue<Object> queue) {
        this.f1809a = queue;
    }

    public boolean a() {
        return get() == c.a.b0.a.c.DISPOSED;
    }

    @Override // c.a.y.b
    public void dispose() {
        if (c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this)) {
            this.f1809a.offer(f1808b);
        }
    }

    @Override // c.a.s
    public void onComplete() {
        this.f1809a.offer(c.a.b0.j.n.a());
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        this.f1809a.offer(c.a.b0.j.n.a(th));
    }

    @Override // c.a.s
    public void onNext(T t) {
        Queue<Object> queue = this.f1809a;
        c.a.b0.j.n.e(t);
        queue.offer(t);
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        c.a.b0.a.c.c(this, bVar);
    }
}
