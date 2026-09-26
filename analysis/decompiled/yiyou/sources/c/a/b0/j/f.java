package c.a.b0.j;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: BlockingIgnoringReceiver.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends CountDownLatch implements c.a.a0.f<Throwable>, c.a.a0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Throwable f3084a;

    public f() {
        super(1);
    }

    @Override // c.a.a0.a
    public void run() {
        countDown();
    }

    @Override // c.a.a0.f
    public void a(Throwable th) {
        this.f3084a = th;
        countDown();
    }
}
