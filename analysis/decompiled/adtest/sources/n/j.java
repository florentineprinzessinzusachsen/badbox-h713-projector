package n;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f1481d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f1482e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1483f;

    public j(f fVar, c cVar) {
        j2.i.e(fVar, "registry");
        j2.i.e(cVar, "event");
        this.f1481d = fVar;
        this.f1482e = cVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.f1483f) {
            return;
        }
        this.f1481d.a(this.f1482e);
        this.f1483f = true;
    }
}
