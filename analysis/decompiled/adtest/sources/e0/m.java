package e0;

import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class m implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f659d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final r0.a f660e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r2.i f661f;

    public /* synthetic */ m(r0.a aVar, r2.i iVar, int i4) {
        this.f659d = i4;
        this.f660e = aVar;
        this.f661f = iVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f659d) {
            case 0:
                r0.a aVar = this.f660e;
                boolean zIsCancelled = aVar.isCancelled();
                r2.i iVar = this.f661f;
                if (zIsCancelled) {
                    iVar.q(null);
                    return;
                }
                boolean z3 = false;
                while (true) {
                    try {
                        try {
                            Object obj = aVar.get();
                            if (z3) {
                                Thread.currentThread().interrupt();
                            }
                            iVar.j(obj);
                            return;
                        } catch (ExecutionException e4) {
                            Throwable cause = e4.getCause();
                            j2.i.b(cause);
                            iVar.j(d0.l0.l(cause));
                            return;
                        }
                    } catch (InterruptedException unused) {
                        z3 = true;
                    } catch (Throwable th) {
                        if (z3) {
                            Thread.currentThread().interrupt();
                        }
                        throw th;
                    }
                }
                break;
            default:
                r0.a aVar2 = this.f660e;
                boolean zIsCancelled2 = aVar2.isCancelled();
                r2.i iVar2 = this.f661f;
                if (zIsCancelled2) {
                    iVar2.q(null);
                    return;
                }
                try {
                    iVar2.j(g.h.f(aVar2));
                    return;
                } catch (ExecutionException e5) {
                    Throwable cause2 = e5.getCause();
                    if (cause2 != null) {
                        iVar2.j(d0.l0.l(cause2));
                        return;
                    } else {
                        u1.b bVar = new u1.b();
                        j2.i.g(bVar, j2.i.class.getName());
                        throw bVar;
                    }
                }
        }
    }
}
