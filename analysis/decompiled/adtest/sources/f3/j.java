package f3;

import a3.f0;
import a3.v;
import d0.l0;
import q3.o;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j extends f0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f906e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f907f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final o f908g;

    public j(String str, long j4, o oVar) {
        this.f906e = str;
        this.f907f = j4;
        this.f908g = oVar;
    }

    @Override // a3.f0
    public final long b() {
        return this.f907f;
    }

    @Override // a3.f0
    public final v c() {
        String str = this.f906e;
        if (str == null) {
            return null;
        }
        p2.h hVar = v.f216c;
        try {
            return l0.t(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    @Override // a3.f0
    public final q3.g k() {
        return this.f908g;
    }
}
