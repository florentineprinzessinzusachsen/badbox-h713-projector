package c.a.x.b;

import android.os.Handler;
import android.os.Looper;
import c.a.t;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: AndroidSchedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final t f3181a = c.a.x.a.a.b(new CallableC0077a());

    /* JADX INFO: renamed from: c.a.x.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidSchedulers.java */
    static class CallableC0077a implements Callable<t> {
        CallableC0077a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public t call() {
            return b.f3182a;
        }
    }

    /* JADX INFO: compiled from: AndroidSchedulers.java */
    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final t f3182a = new c.a.x.b.b(new Handler(Looper.getMainLooper()), false);
    }

    public static t a() {
        return c.a.x.a.a.a(f3181a);
    }
}
