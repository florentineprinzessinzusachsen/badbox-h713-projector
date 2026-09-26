package h3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final q3.h f1079d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final q3.h f1080e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final q3.h f1081f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final q3.h f1082g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final q3.h f1083h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final q3.h f1084i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q3.h f1085a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q3.h f1086b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1087c;

    static {
        q3.h hVar = q3.h.f1823g;
        f1079d = a1.a.m(":");
        f1080e = a1.a.m(":status");
        f1081f = a1.a.m(":method");
        f1082g = a1.a.m(":path");
        f1083h = a1.a.m(":scheme");
        f1084i = a1.a.m(":authority");
    }

    public d(q3.h hVar, q3.h hVar2) {
        j2.i.e(hVar, "name");
        j2.i.e(hVar2, "value");
        this.f1085a = hVar;
        this.f1086b = hVar2;
        this.f1087c = hVar2.a() + hVar.a() + 32;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return j2.i.a(this.f1085a, dVar.f1085a) && j2.i.a(this.f1086b, dVar.f1086b);
    }

    public final int hashCode() {
        return this.f1086b.hashCode() + (this.f1085a.hashCode() * 31);
    }

    public final String toString() {
        return this.f1085a.j() + ": " + this.f1086b.j();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public d(String str, String str2) {
        this(a1.a.m(str), a1.a.m(str2));
        q3.h hVar = q3.h.f1823g;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public d(q3.h hVar, String str) {
        this(hVar, a1.a.m(str));
        j2.i.e(hVar, "name");
        j2.i.e(str, "value");
        q3.h hVar2 = q3.h.f1823g;
    }
}
