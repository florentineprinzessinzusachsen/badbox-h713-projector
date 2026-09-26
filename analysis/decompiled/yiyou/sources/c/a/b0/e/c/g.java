package c.a.b0.e.c;

import c.a.a0.n;
import c.a.j;
import c.a.s;
import c.a.w;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ScalarXMapZHelper.java */
/* JADX INFO: loaded from: classes.dex */
final class g {
    static <T> boolean a(Object obj, n<? super T, ? extends c.a.d> nVar, c.a.c cVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        c.a.d dVar = null;
        try {
            a.a.a.b.b.a aVar = (Object) ((Callable) obj).call();
            if (aVar != null) {
                c.a.d dVarApply = nVar.apply(aVar);
                c.a.b0.b.b.a(dVarApply, "The mapper returned a null CompletableSource");
                dVar = dVarApply;
            }
            if (dVar == null) {
                c.a.b0.a.d.a(cVar);
            } else {
                dVar.a(cVar);
            }
            return true;
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, cVar);
            return true;
        }
    }

    static <T, R> boolean b(Object obj, n<? super T, ? extends w<? extends R>> nVar, s<? super R> sVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        w<? extends R> wVar = null;
        try {
            a.a.a.b.b.a aVar = (Object) ((Callable) obj).call();
            if (aVar != null) {
                w<? extends R> wVarApply = nVar.apply(aVar);
                c.a.b0.b.b.a(wVarApply, "The mapper returned a null SingleSource");
                wVar = wVarApply;
            }
            if (wVar == null) {
                c.a.b0.a.d.a(sVar);
            } else {
                wVar.a(c.a.b0.e.e.b.a(sVar));
            }
            return true;
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, sVar);
            return true;
        }
    }

    static <T, R> boolean a(Object obj, n<? super T, ? extends j<? extends R>> nVar, s<? super R> sVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        j<? extends R> jVar = null;
        try {
            a.a.a.b.b.a aVar = (Object) ((Callable) obj).call();
            if (aVar != null) {
                j<? extends R> jVarApply = nVar.apply(aVar);
                c.a.b0.b.b.a(jVarApply, "The mapper returned a null MaybeSource");
                jVar = jVarApply;
            }
            if (jVar == null) {
                c.a.b0.a.d.a(sVar);
            } else {
                jVar.a(c.a.b0.e.b.a.a(sVar));
            }
            return true;
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, sVar);
            return true;
        }
    }
}
