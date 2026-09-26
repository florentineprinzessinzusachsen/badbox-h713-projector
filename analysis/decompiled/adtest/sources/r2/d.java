package r2;

import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Thread f1969g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final o0 f1970h;

    public d(y1.h hVar, Thread thread, o0 o0Var) {
        super(hVar, true);
        this.f1969g = thread;
        this.f1970h = o0Var;
    }

    @Override // r2.d1
    public final void p(Object obj) {
        Thread threadCurrentThread = Thread.currentThread();
        Thread thread = this.f1969g;
        if (j2.i.a(threadCurrentThread, thread)) {
            return;
        }
        LockSupport.unpark(thread);
    }
}
