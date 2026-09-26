package d3;

import android.os.Looper;
import d0.l0;
import h1.c0;
import i2.l;
import i2.p;
import j2.i;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import n.h;
import t1.o;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f539d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f540e;

    public /* synthetic */ d(int i4, Object obj) {
        this.f539d = i4;
        this.f540e = obj;
    }

    private final void a() {
        Object obj;
        synchronized (((h) this.f540e).f1474a) {
            obj = ((h) this.f540e).f1477d;
            ((h) this.f540e).f1477d = h.f1473h;
        }
        h hVar = (h) this.f540e;
        c.b.J().f353h.getClass();
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Cannot invoke setValue on a background thread");
        }
        hVar.f1476c = obj;
        if (hVar.f1478e) {
            hVar.f1479f = true;
            return;
        }
        hVar.f1478e = true;
        do {
            hVar.f1479f = false;
            d.d dVar = hVar.f1475b;
            dVar.getClass();
            d.c cVar = new d.c();
            cVar.f396h = true;
            dVar.f397d.put(cVar, Boolean.FALSE);
        } while (hVar.f1479f);
        hVar.f1478e = false;
    }

    private final void b() {
        synchronized (o.f2158a) {
            c0.f1036a.getClass();
            Map mapE = c0.e();
            mapE.clear();
            c0.o(mapE);
        }
        ((l) this.f540e).h(Boolean.TRUE);
    }

    @Override // java.lang.Runnable
    public final void run() {
        a aVarB;
        long jNanoTime;
        a aVarB2;
        switch (this.f539d) {
            case 0:
                e eVar = (e) this.f540e;
                synchronized (eVar) {
                    eVar.f549g++;
                    aVarB = eVar.b();
                }
                if (aVarB == null) {
                    return;
                }
                Thread threadCurrentThread = Thread.currentThread();
                String name = threadCurrentThread.getName();
                while (true) {
                    try {
                        threadCurrentThread.setName(aVarB.f527a);
                        Logger logger = ((e) this.f540e).f544b;
                        c cVar = aVarB.f529c;
                        i.b(cVar);
                        boolean zIsLoggable = logger.isLoggable(Level.FINE);
                        if (zIsLoggable) {
                            jNanoTime = System.nanoTime();
                            l0.c(logger, aVarB, cVar, "starting");
                        } else {
                            jNanoTime = -1;
                        }
                        try {
                            long jA = aVarB.a();
                            if (zIsLoggable) {
                                l0.c(logger, aVarB, cVar, "finished run in " + l0.r(System.nanoTime() - jNanoTime));
                            }
                            e eVar2 = (e) this.f540e;
                            synchronized (eVar2) {
                                e.a(eVar2, aVarB, jA, true);
                                aVarB2 = eVar2.b();
                            }
                            if (aVarB2 == null) {
                                threadCurrentThread.setName(name);
                                return;
                            }
                            aVarB = aVarB2;
                        } catch (Throwable th) {
                            if (zIsLoggable) {
                                l0.c(logger, aVarB, cVar, "failed a run in " + l0.r(System.nanoTime() - jNanoTime));
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        try {
                            e eVar3 = (e) this.f540e;
                            synchronized (eVar3) {
                                e.a(eVar3, aVarB, -1L, false);
                                if (!(th2 instanceof InterruptedException)) {
                                    throw th2;
                                }
                                Thread.currentThread().interrupt();
                            }
                        } catch (Throwable th3) {
                            threadCurrentThread.setName(name);
                            throw th3;
                        }
                    }
                }
                break;
            case 1:
                a();
                return;
            case 2:
                b();
                return;
            default:
                ((p) this.f540e).f(null, null);
                return;
        }
    }
}
