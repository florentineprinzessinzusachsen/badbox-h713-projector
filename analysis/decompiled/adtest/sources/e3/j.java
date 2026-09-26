package e3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f753a;

    public j(Throwable th) {
        this.f753a = new u(this, th, 2);
    }

    @Override // e3.v
    public final v a() {
        throw new IllegalStateException("unexpected retry");
    }

    @Override // e3.v
    public final boolean b() {
        return false;
    }

    @Override // e3.v, f3.f
    public final void cancel() {
        throw new IllegalStateException("unexpected cancel");
    }

    @Override // e3.v
    public final u d() {
        return this.f753a;
    }

    @Override // e3.v
    public final u e() {
        return this.f753a;
    }

    @Override // e3.v
    public final q f() {
        throw new IllegalStateException("unexpected call");
    }
}
