package u0;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o implements Map.Entry {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o f2265d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public o f2266e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public o f2267f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public o f2268g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public o f2269h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f2270i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f2271j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Object f2272k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2273l;

    public o(boolean z3) {
        this.f2270i = null;
        this.f2271j = z3;
        this.f2269h = this;
        this.f2268g = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.f2270i;
            if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
                Object obj3 = this.f2272k;
                if (obj3 == null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                } else if (obj3.equals(entry.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f2270i;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f2272k;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.f2270i;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f2272k;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj == null && !this.f2271j) {
            throw new NullPointerException("value == null");
        }
        Object obj2 = this.f2272k;
        this.f2272k = obj;
        return obj2;
    }

    public final String toString() {
        return this.f2270i + "=" + this.f2272k;
    }

    public o(boolean z3, o oVar, Object obj, o oVar2, o oVar3) {
        this.f2265d = oVar;
        this.f2270i = obj;
        this.f2271j = z3;
        this.f2273l = 1;
        this.f2268g = oVar2;
        this.f2269h = oVar3;
        oVar3.f2268g = this;
        oVar2.f2269h = this;
    }
}
