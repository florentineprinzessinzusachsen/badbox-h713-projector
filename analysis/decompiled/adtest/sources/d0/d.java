package d0;

import android.net.Uri;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f430a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f431b;

    public d(Uri uri, boolean z3) {
        this.f430a = uri;
        this.f431b = z3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!d.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        j2.i.c(obj, "null cannot be cast to non-null type androidx.work.Constraints.ContentUriTrigger");
        d dVar = (d) obj;
        return j2.i.a(this.f430a, dVar.f430a) && this.f431b == dVar.f431b;
    }

    public final int hashCode() {
        return (this.f430a.hashCode() * 31) + (this.f431b ? 1231 : 1237);
    }
}
