package c.a.f0;

import c.a.b0.g.n;
import c.a.b0.g.o;
import c.a.t;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: Schedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final t f3135a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final t f3136b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final t f3137c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final t f3138d;

    /* JADX INFO: compiled from: Schedulers.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final t f3139a = new c.a.b0.g.b();
    }

    /* JADX INFO: renamed from: c.a.f0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Schedulers.java */
    static final class CallableC0075b implements Callable<t> {
        CallableC0075b() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public t call() {
            return a.f3139a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class c implements Callable<t> {
        c() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public t call() {
            return d.f3140a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final t f3140a = new c.a.b0.g.d();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final t f3141a = new c.a.b0.g.e();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class f implements Callable<t> {
        f() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public t call() {
            return e.f3141a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final t f3142a = new n();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class h implements Callable<t> {
        h() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public t call() {
            return g.f3142a;
        }
    }

    static {
        c.a.e0.a.e(new h());
        f3135a = c.a.e0.a.b(new CallableC0075b());
        f3136b = c.a.e0.a.c(new c());
        f3137c = o.b();
        f3138d = c.a.e0.a.d(new f());
    }

    public static t a() {
        return c.a.e0.a.a(f3135a);
    }

    public static t b() {
        return c.a.e0.a.b(f3136b);
    }

    public static t c() {
        return c.a.e0.a.c(f3138d);
    }

    public static t d() {
        return f3137c;
    }
}
