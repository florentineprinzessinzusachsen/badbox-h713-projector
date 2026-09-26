package g;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f936a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public l f937b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public o f938c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f939d;

    public final void a(Object obj) {
        this.f939d = true;
        l lVar = this.f937b;
        if (lVar != null) {
            k kVar = lVar.f942e;
            kVar.getClass();
            if (obj == null) {
                obj = h.f932j;
            }
            if (h.f931i.h(kVar, null, obj)) {
                h.c(kVar);
                this.f936a = null;
                this.f937b = null;
                this.f938c = null;
            }
        }
    }

    public final void b(Throwable th) {
        this.f939d = true;
        l lVar = this.f937b;
        if (lVar == null || !lVar.f942e.i(th)) {
            return;
        }
        this.f936a = null;
        this.f937b = null;
        this.f938c = null;
    }

    public final void finalize() {
        o oVar;
        l lVar = this.f937b;
        if (lVar != null) {
            k kVar = lVar.f942e;
            if (!kVar.isDone()) {
                kVar.i(new b(1, "The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.f936a));
            }
        }
        if (this.f939d || (oVar = this.f938c) == null) {
            return;
        }
        oVar.j(null);
    }
}
