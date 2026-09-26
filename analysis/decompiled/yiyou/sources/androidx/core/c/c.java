package androidx.core.c;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: SelfDestructiveThread.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HandlerThread f998b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f999c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f1002f;
    private final int g;
    private final String h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f997a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Handler.Callback f1001e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1000d = 0;

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                c.this.a();
                return true;
            }
            if (i != 1) {
                return true;
            }
            c.this.a((Runnable) message.obj);
            return true;
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Callable f1004a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Handler f1005b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f1006c;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Object f1007a;

            a(Object obj) {
                this.f1007a = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f1006c.a(this.f1007a);
            }
        }

        b(c cVar, Callable callable, Handler handler, d dVar) {
            this.f1004a = callable;
            this.f1005b = handler;
            this.f1006c = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f1004a.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f1005b.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: androidx.core.c.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    class RunnableC0017c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ AtomicReference f1009a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Callable f1010b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ReentrantLock f1011c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ AtomicBoolean f1012d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Condition f1013e;

        RunnableC0017c(c cVar, AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f1009a = atomicReference;
            this.f1010b = callable;
            this.f1011c = reentrantLock;
            this.f1012d = atomicBoolean;
            this.f1013e = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f1009a.set(this.f1010b.call());
            } catch (Exception unused) {
            }
            this.f1011c.lock();
            try {
                this.f1012d.set(false);
                this.f1013e.signal();
            } finally {
                this.f1011c.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public interface d<T> {
        void a(T t);
    }

    public c(String str, int i, int i2) {
        this.h = str;
        this.g = i;
        this.f1002f = i2;
    }

    private void b(Runnable runnable) {
        synchronized (this.f997a) {
            if (this.f998b == null) {
                this.f998b = new HandlerThread(this.h, this.g);
                this.f998b.start();
                this.f999c = new Handler(this.f998b.getLooper(), this.f1001e);
                this.f1000d++;
            }
            this.f999c.removeMessages(0);
            this.f999c.sendMessage(this.f999c.obtainMessage(1, runnable));
        }
    }

    public <T> void a(Callable<T> callable, d<T> dVar) {
        b(new b(this, callable, new Handler(), dVar));
    }

    public <T> T a(Callable<T> callable, int i) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionNewCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        b(new RunnableC0017c(this, atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                T t = (T) atomicReference.get();
                reentrantLock.unlock();
                return t;
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos(i);
            do {
                try {
                    nanos = conditionNewCondition.awaitNanos(nanos);
                } catch (InterruptedException unused) {
                }
                if (!atomicBoolean.get()) {
                    T t2 = (T) atomicReference.get();
                    reentrantLock.unlock();
                    return t2;
                }
            } while (nanos > 0);
            throw new InterruptedException("timeout");
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    void a(Runnable runnable) {
        runnable.run();
        synchronized (this.f997a) {
            this.f999c.removeMessages(0);
            this.f999c.sendMessageDelayed(this.f999c.obtainMessage(0), this.f1002f);
        }
    }

    void a() {
        synchronized (this.f997a) {
            if (this.f999c.hasMessages(1)) {
                return;
            }
            this.f998b.quit();
            this.f998b = null;
            this.f999c = null;
        }
    }
}
