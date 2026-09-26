package c.a.b0.g;

import c.a.t;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: NewThreadScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends t {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final h f3032c = new h("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ThreadFactory f3033b;

    public e() {
        this(f3032c);
    }

    @Override // c.a.t
    public t.c a() {
        return new f(this.f3033b);
    }

    public e(ThreadFactory threadFactory) {
        this.f3033b = threadFactory;
    }
}
