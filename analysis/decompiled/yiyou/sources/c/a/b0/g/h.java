package c.a.b0.g;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: RxThreadFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends AtomicLong implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f3036a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f3037b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f3038c;

    /* JADX INFO: compiled from: RxThreadFactory.java */
    static final class a extends Thread implements g {
        a(Runnable runnable, String str) {
            super(runnable, str);
        }
    }

    public h(String str) {
        this(str, 5, false);
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        String str = this.f3036a + '-' + incrementAndGet();
        Thread aVar = this.f3038c ? new a(runnable, str) : new Thread(runnable, str);
        aVar.setPriority(this.f3037b);
        aVar.setDaemon(true);
        return aVar;
    }

    @Override // java.util.concurrent.atomic.AtomicLong
    public String toString() {
        return "RxThreadFactory[" + this.f3036a + "]";
    }

    public h(String str, int i) {
        this(str, i, false);
    }

    public h(String str, int i, boolean z) {
        this.f3036a = str;
        this.f3037b = i;
        this.f3038c = z;
    }
}
