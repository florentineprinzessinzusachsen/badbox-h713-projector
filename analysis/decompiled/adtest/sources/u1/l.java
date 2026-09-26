package u1;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l implements c, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i2.a f2302d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f2303e;

    @Override // u1.c
    public final Object getValue() {
        if (this.f2303e == j.f2300a) {
            i2.a aVar = this.f2302d;
            j2.i.b(aVar);
            this.f2303e = aVar.a();
            this.f2302d = null;
        }
        return this.f2303e;
    }

    public final String toString() {
        return this.f2303e != j.f2300a ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
