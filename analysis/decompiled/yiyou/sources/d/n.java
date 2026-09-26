package d;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Dispatcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Runnable f4645c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ExecutorService f4646d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4643a = 64;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4644b = 5;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Deque<z.a> f4647e = new ArrayDeque();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Deque<z.a> f4648f = new ArrayDeque();
    private final Deque<z> g = new ArrayDeque();

    private void c() {
        if (this.f4648f.size() < this.f4643a && !this.f4647e.isEmpty()) {
            Iterator<z.a> it = this.f4647e.iterator();
            while (it.hasNext()) {
                z.a next = it.next();
                if (c(next) < this.f4644b) {
                    it.remove();
                    this.f4648f.add(next);
                    a().execute(next);
                }
                if (this.f4648f.size() >= this.f4643a) {
                    return;
                }
            }
        }
    }

    public synchronized ExecutorService a() {
        if (this.f4646d == null) {
            this.f4646d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), d.h0.c.a("OkHttp Dispatcher", false));
        }
        return this.f4646d;
    }

    void b(z.a aVar) {
        a(this.f4648f, aVar, true);
    }

    void b(z zVar) {
        a(this.g, zVar, false);
    }

    public synchronized int b() {
        return this.f4648f.size() + this.g.size();
    }

    synchronized void a(z.a aVar) {
        if (this.f4648f.size() < this.f4643a && c(aVar) < this.f4644b) {
            this.f4648f.add(aVar);
            a().execute(aVar);
        } else {
            this.f4647e.add(aVar);
        }
    }

    private int c(z.a aVar) {
        int i = 0;
        for (z.a aVar2 : this.f4648f) {
            if (!aVar2.c().f4715e && aVar2.d().equals(aVar.d())) {
                i++;
            }
        }
        return i;
    }

    synchronized void a(z zVar) {
        this.g.add(zVar);
    }

    private <T> void a(Deque<T> deque, T t, boolean z) {
        int iB;
        Runnable runnable;
        synchronized (this) {
            if (deque.remove(t)) {
                if (z) {
                    c();
                }
                iB = b();
                runnable = this.f4645c;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (iB != 0 || runnable == null) {
            return;
        }
        runnable.run();
    }
}
