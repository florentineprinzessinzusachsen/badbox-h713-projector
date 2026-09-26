package c.a.b0.d;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: BasicIntQueueDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<T> extends AtomicInteger implements c.a.b0.c.e<T> {
    @Override // c.a.b0.c.j
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
