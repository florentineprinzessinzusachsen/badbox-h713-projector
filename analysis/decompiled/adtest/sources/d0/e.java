package d0;

import android.net.NetworkRequest;
import android.os.Build;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final e f432j = new e();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b0 f433a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m0.f f434b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f435c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f436d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f437e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f438f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f439g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f440h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Set f441i;

    public e() {
        this.f434b = new m0.f(null);
        this.f433a = b0.f417d;
        this.f435c = false;
        this.f436d = false;
        this.f437e = false;
        this.f438f = false;
        this.f439g = -1L;
        this.f440h = -1L;
        this.f441i = v1.r.f2519d;
    }

    public final NetworkRequest a() {
        return (NetworkRequest) this.f434b.f1406a;
    }

    public final boolean b() {
        return Build.VERSION.SDK_INT < 24 || !this.f441i.isEmpty();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !e.class.equals(obj.getClass())) {
            return false;
        }
        e eVar = (e) obj;
        if (this.f435c == eVar.f435c && this.f436d == eVar.f436d && this.f437e == eVar.f437e && this.f438f == eVar.f438f && this.f439g == eVar.f439g && this.f440h == eVar.f440h && j2.i.a(a(), eVar.a()) && this.f433a == eVar.f433a) {
            return j2.i.a(this.f441i, eVar.f441i);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = ((((((((this.f433a.hashCode() * 31) + (this.f435c ? 1 : 0)) * 31) + (this.f436d ? 1 : 0)) * 31) + (this.f437e ? 1 : 0)) * 31) + (this.f438f ? 1 : 0)) * 31;
        long j4 = this.f439g;
        int i4 = (iHashCode + ((int) (j4 ^ (j4 >>> 32)))) * 31;
        long j5 = this.f440h;
        int iHashCode2 = (this.f441i.hashCode() + ((i4 + ((int) (j5 ^ (j5 >>> 32)))) * 31)) * 31;
        NetworkRequest networkRequestA = a();
        return iHashCode2 + (networkRequestA != null ? networkRequestA.hashCode() : 0);
    }

    public final String toString() {
        return "Constraints{requiredNetworkType=" + this.f433a + ", requiresCharging=" + this.f435c + ", requiresDeviceIdle=" + this.f436d + ", requiresBatteryNotLow=" + this.f437e + ", requiresStorageNotLow=" + this.f438f + ", contentTriggerUpdateDelayMillis=" + this.f439g + ", contentTriggerMaxDelayMillis=" + this.f440h + ", contentUriTriggers=" + this.f441i + ", }";
    }

    public e(m0.f fVar, b0 b0Var, boolean z3, boolean z4, boolean z5, boolean z6, long j4, long j5, LinkedHashSet linkedHashSet) {
        this.f434b = fVar;
        this.f433a = b0Var;
        this.f435c = z3;
        this.f436d = z4;
        this.f437e = z5;
        this.f438f = z6;
        this.f439g = j4;
        this.f440h = j5;
        this.f441i = linkedHashSet;
    }

    public e(e eVar) {
        j2.i.e(eVar, "other");
        this.f435c = eVar.f435c;
        this.f436d = eVar.f436d;
        this.f434b = eVar.f434b;
        this.f433a = eVar.f433a;
        this.f437e = eVar.f437e;
        this.f438f = eVar.f438f;
        this.f441i = eVar.f441i;
        this.f439g = eVar.f439g;
        this.f440h = eVar.f440h;
    }
}
