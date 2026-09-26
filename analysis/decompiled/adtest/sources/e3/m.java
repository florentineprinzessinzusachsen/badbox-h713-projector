package e3;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class m implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a3.d f762d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile AtomicInteger f763e = new AtomicInteger(0);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ p f764f;

    public m(p pVar, a3.d dVar) {
        this.f764f = pVar;
        this.f762d = dVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a3.l lVar;
        String str = "OkHttp " + ((a3.t) this.f764f.f768e.f63c).f();
        p pVar = this.f764f;
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        threadCurrentThread.setName(str);
        try {
            pVar.f770g.h();
            boolean z3 = false;
            try {
                try {
                    try {
                        this.f762d.b(pVar, pVar.h());
                        lVar = pVar.f767d.f244a;
                    } catch (IOException e4) {
                        e = e4;
                        z3 = true;
                        if (z3) {
                            k3.e eVar = k3.e.f1300a;
                            k3.e.f1300a.j("Callback failure for " + p.a(pVar), 4, e);
                        } else {
                            this.f762d.e(pVar, e);
                        }
                        lVar = pVar.f767d.f244a;
                    } catch (Throwable th) {
                        th = th;
                        z3 = true;
                        pVar.d();
                        if (!z3) {
                            IOException iOException = new IOException("canceled due to " + th);
                            iOException.initCause(th);
                            this.f762d.e(pVar, iOException);
                        }
                        if (!(th instanceof InterruptedException)) {
                            throw th;
                        }
                        Thread.currentThread().interrupt();
                        lVar = pVar.f767d.f244a;
                    }
                } catch (Throwable th2) {
                    a3.l lVar2 = pVar.f767d.f244a;
                    lVar2.getClass();
                    a3.l.g(lVar2, null, null, this, 3);
                    throw th2;
                }
            } catch (IOException e5) {
                e = e5;
            } catch (Throwable th3) {
                th = th3;
            }
            lVar.getClass();
            a3.l.g(lVar, null, null, this, 3);
            threadCurrentThread.setName(name);
        } catch (Throwable th4) {
            threadCurrentThread.setName(name);
            throw th4;
        }
    }
}
