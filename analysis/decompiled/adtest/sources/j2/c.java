package j2;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class c implements n2.a, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient n2.a f1263d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f1264e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Class f1265f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f1266g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f1267h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f1268i;

    public c(Object obj, Class cls, String str, String str2, boolean z3) {
        this.f1264e = obj;
        this.f1265f = cls;
        this.f1266g = str;
        this.f1267h = str2;
        this.f1268i = z3;
    }

    public abstract n2.a c();

    public final d e() {
        boolean z3 = this.f1268i;
        Class cls = this.f1265f;
        if (!z3) {
            return o.a(cls);
        }
        o.f1277a.getClass();
        return new k(cls);
    }
}
