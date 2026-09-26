package e3;

import a3.d0;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements a3.u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f705a = new a();

    @Override // a3.u
    public final d0 a(f3.i iVar) throws IOException {
        f3.g hVar;
        p pVar = iVar.f897a;
        synchronized (pVar) {
            if (!pVar.f781r) {
                throw new IllegalStateException("released");
            }
            if (pVar.f778o || pVar.f777n || pVar.f780q || pVar.f779p) {
                throw new IllegalStateException("Check failed.");
            }
        }
        i iVar2 = pVar.f773j;
        j2.i.b(iVar2);
        q qVarC = iVar2.c();
        a3.x xVar = pVar.f767d;
        qVarC.getClass();
        int i4 = iVar.f903g;
        a2.f fVar = qVarC.f791h;
        h3.q qVar = qVarC.f792i;
        if (qVar != null) {
            hVar = new h3.r(xVar, qVarC, iVar, qVar);
        } else {
            qVarC.f788e.setSoTimeout(i4);
            q3.w wVarF = ((q3.o) fVar.f46f).f1844d.f();
            long j4 = i4;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            wVarF.g(j4);
            ((q3.n) fVar.f47g).f1841d.f().g(iVar.f904h);
            hVar = new g3.h(xVar, qVarC, fVar);
        }
        h hVar2 = new h(pVar, iVar2, hVar);
        pVar.f776m = hVar2;
        pVar.f783t = hVar2;
        synchronized (pVar) {
            pVar.f777n = true;
            pVar.f778o = true;
        }
        if (pVar.f782s) {
            throw new IOException("Canceled");
        }
        return f3.i.a(iVar, 0, hVar2, null, 61).b(iVar.f901e);
    }
}
