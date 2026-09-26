package c.a.x.a;

import c.a.a0.n;
import c.a.t;
import c.a.z.b;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: RxAndroidPlugins.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile n<Callable<t>, t> f3179a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile n<t, t> f3180b;

    public static t a(t tVar) {
        if (tVar == null) {
            throw new NullPointerException("scheduler == null");
        }
        n<t, t> nVar = f3180b;
        return nVar == null ? tVar : (t) a((n<t, R>) nVar, tVar);
    }

    public static t b(Callable<t> callable) {
        if (callable == null) {
            throw new NullPointerException("scheduler == null");
        }
        n<Callable<t>, t> nVar = f3179a;
        return nVar == null ? a(callable) : a(nVar, callable);
    }

    static t a(Callable<t> callable) {
        try {
            t tVarCall = callable.call();
            if (tVarCall != null) {
                return tVarCall;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            b.a(th);
            throw null;
        }
    }

    static t a(n<Callable<t>, t> nVar, Callable<t> callable) {
        t tVar = (t) a((n<Callable<t>, R>) nVar, callable);
        if (tVar != null) {
            return tVar;
        }
        throw new NullPointerException("Scheduler Callable returned null");
    }

    static <T, R> R a(n<T, R> nVar, T t) {
        try {
            return nVar.apply(t);
        } catch (Throwable th) {
            b.a(th);
            throw null;
        }
    }
}
