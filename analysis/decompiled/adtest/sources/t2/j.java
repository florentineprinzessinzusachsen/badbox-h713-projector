package t2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f2220a;

    public j(Throwable th) {
        this.f2220a = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof j) {
            return j2.i.a(this.f2220a, ((j) obj).f2220a);
        }
        return false;
    }

    public final int hashCode() {
        Throwable th = this.f2220a;
        if (th != null) {
            return th.hashCode();
        }
        return 0;
    }

    @Override // t2.k
    public final String toString() {
        return "Closed(" + this.f2220a + ')';
    }
}
