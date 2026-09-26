package u1;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i implements c, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i2.a f2297d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Object f2298e = j.f2300a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f2299f = this;

    public i(i2.a aVar) {
        this.f2297d = aVar;
    }

    @Override // u1.c
    public final Object getValue() {
        Object objA;
        Object obj = this.f2298e;
        j jVar = j.f2300a;
        if (obj != jVar) {
            return obj;
        }
        synchronized (this.f2299f) {
            objA = this.f2298e;
            if (objA == jVar) {
                i2.a aVar = this.f2297d;
                j2.i.b(aVar);
                objA = aVar.a();
                this.f2298e = objA;
                this.f2297d = null;
            }
        }
        return objA;
    }

    public final String toString() {
        return this.f2298e != j.f2300a ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
