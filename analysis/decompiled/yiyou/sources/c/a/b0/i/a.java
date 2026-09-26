package c.a.b0.i;

import c.a.b0.c.g;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: BasicIntQueueSubscription.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> extends AtomicInteger implements g<T> {
    @Override // c.a.b0.c.j
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
