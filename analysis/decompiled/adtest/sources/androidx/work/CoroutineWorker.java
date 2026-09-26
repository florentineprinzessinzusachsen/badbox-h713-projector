package androidx.work;

import a.a;
import android.content.Context;
import d0.f;
import d0.g;
import d0.z;
import g.l;
import j2.i;
import l3.h;
import r2.x;
import r2.x0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class CoroutineWorker extends z {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final WorkerParameters f307e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f f308f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        i.e(context, "appContext");
        i.e(workerParameters, "params");
        this.f307e = workerParameters;
        this.f308f = f.f443f;
    }

    @Override // d0.z
    public final l a() {
        x0 x0VarB = x.b();
        f fVar = this.f308f;
        fVar.getClass();
        return a.u(h.Y(fVar, x0VarB), new g(this, null, 0));
    }

    @Override // d0.z
    public final l b() {
        f fVar = f.f443f;
        y1.h hVar = this.f308f;
        if (i.a(hVar, fVar)) {
            hVar = this.f307e.f313d;
        }
        i.b(hVar);
        return a.u(hVar.l(x.b()), new g(this, null, 1));
    }

    public abstract Object c(g gVar);
}
