package c.a.e0;

import c.a.a0.c;
import c.a.a0.e;
import c.a.a0.f;
import c.a.a0.n;
import c.a.b;
import c.a.b0.j.j;
import c.a.h;
import c.a.i;
import c.a.l;
import c.a.s;
import c.a.t;
import c.a.u;
import c.a.v;
import c.a.z.d;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: RxJavaPlugins.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static volatile f<? super Throwable> f3128a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static volatile n<? super Runnable, ? extends Runnable> f3129b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static volatile n<? super Callable<t>, ? extends t> f3130c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static volatile n<? super Callable<t>, ? extends t> f3131d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static volatile n<? super Callable<t>, ? extends t> f3132e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static volatile n<? super Callable<t>, ? extends t> f3133f;
    static volatile n<? super t, ? extends t> g;
    static volatile n<? super t, ? extends t> h;
    static volatile n<? super t, ? extends t> i;
    static volatile n<? super c.a.f, ? extends c.a.f> j;
    static volatile n<? super l, ? extends l> k;
    static volatile n<? super c.a.c0.a, ? extends c.a.c0.a> l;
    static volatile n<? super h, ? extends h> m;
    static volatile n<? super u, ? extends u> n;
    static volatile n<? super b, ? extends b> o;
    static volatile c<? super c.a.f, ? super f.a.b, ? extends f.a.b> p;
    static volatile c<? super h, ? super i, ? extends i> q;
    static volatile c<? super l, ? super s, ? extends s> r;
    static volatile c<? super u, ? super v, ? extends v> s;
    static volatile c<? super b, ? super c.a.c, ? extends c.a.c> t;
    static volatile e u;
    static volatile boolean v;

    public static boolean a() {
        return v;
    }

    public static t b(Callable<t> callable) {
        c.a.b0.b.b.a(callable, "Scheduler Callable can't be null");
        n<? super Callable<t>, ? extends t> nVar = f3130c;
        return nVar == null ? a(callable) : a(nVar, callable);
    }

    public static t c(Callable<t> callable) {
        c.a.b0.b.b.a(callable, "Scheduler Callable can't be null");
        n<? super Callable<t>, ? extends t> nVar = f3132e;
        return nVar == null ? a(callable) : a(nVar, callable);
    }

    public static t d(Callable<t> callable) {
        c.a.b0.b.b.a(callable, "Scheduler Callable can't be null");
        n<? super Callable<t>, ? extends t> nVar = f3133f;
        return nVar == null ? a(callable) : a(nVar, callable);
    }

    public static t e(Callable<t> callable) {
        c.a.b0.b.b.a(callable, "Scheduler Callable can't be null");
        n<? super Callable<t>, ? extends t> nVar = f3131d;
        return nVar == null ? a(callable) : a(nVar, callable);
    }

    public static t a(t tVar) {
        n<? super t, ? extends t> nVar = g;
        return nVar == null ? tVar : (t) a((n<t, R>) nVar, tVar);
    }

    static boolean a(Throwable th) {
        return (th instanceof d) || (th instanceof c.a.z.c) || (th instanceof IllegalStateException) || (th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof c.a.z.a);
    }

    public static void b(Throwable th) {
        f<? super Throwable> fVar = f3128a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!a(th)) {
            th = new c.a.z.f(th);
        }
        if (fVar != null) {
            try {
                fVar.a(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                c(th2);
            }
        }
        th.printStackTrace();
        c(th);
    }

    static void c(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }

    public static t c(t tVar) {
        n<? super t, ? extends t> nVar = i;
        return nVar == null ? tVar : (t) a((n<t, R>) nVar, tVar);
    }

    public static Runnable a(Runnable runnable) {
        c.a.b0.b.b.a(runnable, "run is null");
        n<? super Runnable, ? extends Runnable> nVar = f3129b;
        return nVar == null ? runnable : (Runnable) a((n<Runnable, R>) nVar, runnable);
    }

    public static <T> f.a.b<? super T> a(c.a.f<T> fVar, f.a.b<? super T> bVar) {
        c<? super c.a.f, ? super f.a.b, ? extends f.a.b> cVar = p;
        return cVar != null ? (f.a.b) a(cVar, fVar, bVar) : bVar;
    }

    public static t b(t tVar) {
        n<? super t, ? extends t> nVar = h;
        return nVar == null ? tVar : (t) a((n<t, R>) nVar, tVar);
    }

    public static <T> s<? super T> a(l<T> lVar, s<? super T> sVar) {
        c<? super l, ? super s, ? extends s> cVar = r;
        return cVar != null ? (s) a(cVar, lVar, sVar) : sVar;
    }

    public static boolean b() {
        e eVar = u;
        if (eVar == null) {
            return false;
        }
        try {
            return eVar.a();
        } catch (Throwable th) {
            throw j.a(th);
        }
    }

    public static <T> v<? super T> a(u<T> uVar, v<? super T> vVar) {
        c<? super u, ? super v, ? extends v> cVar = s;
        return cVar != null ? (v) a(cVar, uVar, vVar) : vVar;
    }

    public static c.a.c a(b bVar, c.a.c cVar) {
        c<? super b, ? super c.a.c, ? extends c.a.c> cVar2 = t;
        return cVar2 != null ? (c.a.c) a(cVar2, bVar, cVar) : cVar;
    }

    public static <T> i<? super T> a(h<T> hVar, i<? super T> iVar) {
        c<? super h, ? super i, ? extends i> cVar = q;
        return cVar != null ? (i) a(cVar, hVar, iVar) : iVar;
    }

    public static <T> h<T> a(h<T> hVar) {
        n<? super h, ? extends h> nVar = m;
        return nVar != null ? (h) a((n<h<T>, R>) nVar, hVar) : hVar;
    }

    public static <T> c.a.f<T> a(c.a.f<T> fVar) {
        n<? super c.a.f, ? extends c.a.f> nVar = j;
        return nVar != null ? (c.a.f) a((n<c.a.f<T>, R>) nVar, fVar) : fVar;
    }

    public static <T> l<T> a(l<T> lVar) {
        n<? super l, ? extends l> nVar = k;
        return nVar != null ? (l) a((n<l<T>, R>) nVar, lVar) : lVar;
    }

    public static <T> c.a.c0.a<T> a(c.a.c0.a<T> aVar) {
        n<? super c.a.c0.a, ? extends c.a.c0.a> nVar = l;
        return nVar != null ? (c.a.c0.a) a((n<c.a.c0.a<T>, R>) nVar, aVar) : aVar;
    }

    public static <T> u<T> a(u<T> uVar) {
        n<? super u, ? extends u> nVar = n;
        return nVar != null ? (u) a((n<u<T>, R>) nVar, uVar) : uVar;
    }

    public static b a(b bVar) {
        n<? super b, ? extends b> nVar = o;
        return nVar != null ? (b) a((n<b, R>) nVar, bVar) : bVar;
    }

    static <T, R> R a(n<T, R> nVar, T t2) {
        try {
            return nVar.apply(t2);
        } catch (Throwable th) {
            throw j.a(th);
        }
    }

    static <T, U, R> R a(c<T, U, R> cVar, T t2, U u2) {
        try {
            return cVar.a(t2, u2);
        } catch (Throwable th) {
            throw j.a(th);
        }
    }

    static t a(Callable<t> callable) {
        try {
            t tVarCall = callable.call();
            c.a.b0.b.b.a(tVarCall, "Scheduler Callable result can't be null");
            return tVarCall;
        } catch (Throwable th) {
            throw j.a(th);
        }
    }

    static t a(n<? super Callable<t>, ? extends t> nVar, Callable<t> callable) {
        Object objA = a((n<Callable<t>, Object>) nVar, callable);
        c.a.b0.b.b.a(objA, "Scheduler Callable result can't be null");
        return (t) objA;
    }
}
