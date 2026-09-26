package m0;

import android.net.NetworkRequest;
import d0.a0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f1405b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1406a;

    static {
        String strG = a0.g("NetworkRequestCompat");
        j2.i.d(strG, "tagWithPrefix(...)");
        f1405b = strG;
    }

    public f(NetworkRequest networkRequest) {
        this.f1406a = networkRequest;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof f) && j2.i.a(this.f1406a, ((f) obj).f1406a);
    }

    public final int hashCode() {
        Object obj = this.f1406a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        return "NetworkRequestCompat(wrapped=" + this.f1406a + ')';
    }
}
