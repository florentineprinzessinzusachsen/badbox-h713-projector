package c.a.x.b;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import c.a.t;
import c.a.y.c;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: HandlerScheduler.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f3183b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f3184c;

    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class a extends t.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Handler f3185a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f3186b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private volatile boolean f3187c;

        a(Handler handler, boolean z) {
            this.f3185a = handler;
            this.f3186b = z;
        }

        @Override // c.a.t.c
        @SuppressLint({"NewApi"})
        public c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (runnable == null) {
                throw new NullPointerException("run == null");
            }
            if (timeUnit == null) {
                throw new NullPointerException("unit == null");
            }
            if (this.f3187c) {
                return c.a();
            }
            RunnableC0078b runnableC0078b = new RunnableC0078b(this.f3185a, c.a.e0.a.a(runnable));
            Message messageObtain = Message.obtain(this.f3185a, runnableC0078b);
            messageObtain.obj = this;
            if (this.f3186b) {
                messageObtain.setAsynchronous(true);
            }
            this.f3185a.sendMessageDelayed(messageObtain, timeUnit.toMillis(j));
            if (!this.f3187c) {
                return runnableC0078b;
            }
            this.f3185a.removeCallbacks(runnableC0078b);
            return c.a();
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f3187c = true;
            this.f3185a.removeCallbacksAndMessages(this);
        }
    }

    /* JADX INFO: renamed from: c.a.x.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class RunnableC0078b implements Runnable, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Handler f3188a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Runnable f3189b;

        RunnableC0078b(Handler handler, Runnable runnable) {
            this.f3188a = handler;
            this.f3189b = runnable;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f3188a.removeCallbacks(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f3189b.run();
            } catch (Throwable th) {
                c.a.e0.a.b(th);
            }
        }
    }

    b(Handler handler, boolean z) {
        this.f3183b = handler;
        this.f3184c = z;
    }

    @Override // c.a.t
    public c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        if (runnable == null) {
            throw new NullPointerException("run == null");
        }
        if (timeUnit == null) {
            throw new NullPointerException("unit == null");
        }
        RunnableC0078b runnableC0078b = new RunnableC0078b(this.f3183b, c.a.e0.a.a(runnable));
        this.f3183b.postDelayed(runnableC0078b, timeUnit.toMillis(j));
        return runnableC0078b;
    }

    @Override // c.a.t
    public t.c a() {
        return new a(this.f3183b, this.f3184c);
    }
}
