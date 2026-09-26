package e0;

import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f662a;

    static {
        String strG = d0.a0.g("WorkerWrapper");
        j2.i.d(strG, "tagWithPrefix(...)");
        f662a = strG;
    }

    public static final Object a(r0.a aVar, d0.z zVar, a2.i iVar) {
        Object obj;
        try {
            if (!aVar.isDone()) {
                r2.i iVar2 = new r2.i(1, z1.d.a(iVar));
                iVar2.v();
                aVar.a(new m(aVar, iVar2, 0), d0.m.f478d);
                iVar2.x(new l0(zVar, aVar));
                return iVar2.u();
            }
            boolean z3 = false;
            while (true) {
                try {
                    obj = aVar.get();
                    break;
                } catch (InterruptedException unused) {
                    z3 = true;
                } catch (Throwable th) {
                    if (z3) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
            if (z3) {
                Thread.currentThread().interrupt();
            }
            return obj;
        } catch (ExecutionException e4) {
            Throwable cause = e4.getCause();
            j2.i.b(cause);
            throw cause;
        }
    }
}
