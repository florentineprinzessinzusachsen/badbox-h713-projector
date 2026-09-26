package n;

import android.os.Looper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import u2.r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f1466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d.a f1467b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f1468c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference f1469d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1470e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r f1471f;

    public f(g gVar) {
        new AtomicReference(null);
        this.f1466a = true;
        this.f1467b = new d.a();
        d dVar = d.f1461e;
        this.f1468c = dVar;
        new ArrayList();
        this.f1469d = new WeakReference(gVar);
        this.f1471f = new r(dVar);
    }

    public final void a(c cVar) {
        d dVar;
        j2.i.e(cVar, "event");
        if (this.f1466a) {
            c.b.J().f353h.getClass();
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                throw new IllegalStateException("Method handleLifecycleEvent must be called on the main thread");
            }
        }
        int i4 = b.f1459a[cVar.ordinal()];
        d dVar2 = d.f1462f;
        d dVar3 = d.f1460d;
        switch (i4) {
            case 1:
            case 2:
                dVar = dVar2;
                break;
            case 3:
            case 4:
                dVar = d.f1463g;
                break;
            case 5:
                dVar = d.f1464h;
                break;
            case 6:
                dVar = dVar3;
                break;
            case 7:
                throw new IllegalArgumentException(cVar + " has no target state");
            default:
                throw new a0.c();
        }
        if (this.f1468c == dVar) {
            return;
        }
        WeakReference weakReference = this.f1469d;
        e eVar = (e) weakReference.get();
        d dVar4 = this.f1468c;
        j2.i.e(dVar4, "current");
        if (dVar4 == d.f1461e && dVar == dVar3) {
            throw new IllegalStateException(("State must be at least '" + dVar2 + "' to be moved to '" + dVar + "' in component " + eVar).toString());
        }
        if (dVar4 == dVar3 && dVar4 != dVar) {
            throw new IllegalStateException(("State is '" + dVar3 + "' and cannot be moved to `" + dVar + "` in component " + eVar).toString());
        }
        this.f1468c = dVar;
        if (this.f1470e) {
            return;
        }
        this.f1470e = true;
        if (((e) weakReference.get()) == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state.");
        }
        this.f1467b.getClass();
        Object obj = this.f1468c;
        r rVar = this.f1471f;
        rVar.getClass();
        if (obj == null) {
            obj = v2.c.f2527b;
        }
        rVar.g(null, obj);
        this.f1470e = false;
        if (this.f1468c == dVar3) {
            this.f1467b = new d.a();
        }
    }
}
