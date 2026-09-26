package m0;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j implements Executor {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1410d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Executor f1411e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayDeque f1412f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Runnable f1413g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f1414h;

    public j(Executor executor, int i4) {
        this.f1410d = i4;
        switch (i4) {
            case 1:
                j2.i.e(executor, "executor");
                this.f1411e = executor;
                this.f1412f = new ArrayDeque();
                this.f1414h = new Object();
                break;
            default:
                this.f1411e = executor;
                this.f1412f = new ArrayDeque();
                this.f1414h = new Object();
                break;
        }
    }

    private final void a(Runnable runnable) {
        synchronized (this.f1414h) {
            try {
                this.f1412f.add(new f0.a(this, runnable, 2, false));
                if (this.f1413g == null) {
                    b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b() {
        switch (this.f1410d) {
            case 0:
                Runnable runnable = (Runnable) this.f1412f.poll();
                this.f1413g = runnable;
                if (runnable != null) {
                    this.f1411e.execute(runnable);
                    return;
                }
                return;
            default:
                synchronized (this.f1414h) {
                    Object objPoll = this.f1412f.poll();
                    Runnable runnable2 = (Runnable) objPoll;
                    this.f1413g = runnable2;
                    if (objPoll != null) {
                        this.f1411e.execute(runnable2);
                    }
                    break;
                }
                return;
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.f1410d) {
            case 0:
                a(runnable);
                return;
            default:
                j2.i.e(runnable, "command");
                synchronized (this.f1414h) {
                    this.f1412f.offer(new e0.e(4, runnable, this));
                    if (this.f1413g == null) {
                        b();
                    }
                    break;
                }
                return;
        }
    }
}
