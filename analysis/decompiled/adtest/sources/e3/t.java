package e3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class t implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f821a;

    public t(q qVar) {
        j2.i.e(qVar, "connection");
        this.f821a = qVar;
    }

    @Override // e3.v
    public final v a() {
        throw new IllegalStateException("unexpected retry");
    }

    @Override // e3.v
    public final boolean b() {
        return true;
    }

    @Override // e3.v, f3.f
    public final void cancel() {
        throw new IllegalStateException("unexpected cancel");
    }

    @Override // e3.v
    public final u d() {
        throw new IllegalStateException("already connected");
    }

    @Override // e3.v
    public final u e() {
        throw new IllegalStateException("already connected");
    }

    @Override // e3.v
    public final q f() {
        return this.f821a;
    }
}
