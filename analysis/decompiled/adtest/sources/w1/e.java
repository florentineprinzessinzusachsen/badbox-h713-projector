package w1;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Map.Entry {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f2587d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2588e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2589f;

    public e(f fVar, int i4) {
        j2.i.e(fVar, "map");
        this.f2587d = fVar;
        this.f2588e = i4;
        this.f2589f = fVar.f2598k;
    }

    public final void a() {
        if (this.f2587d.f2598k != this.f2589f) {
            throw new ConcurrentModificationException("The backing map has been modified after this entry was obtained.");
        }
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return j2.i.a(entry.getKey(), getKey()) && j2.i.a(entry.getValue(), getValue());
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        a();
        return this.f2587d.f2591d[this.f2588e];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        a();
        Object[] objArr = this.f2587d.f2592e;
        j2.i.b(objArr);
        return objArr[this.f2588e];
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object key = getKey();
        int iHashCode = key != null ? key.hashCode() : 0;
        Object value = getValue();
        return iHashCode ^ (value != null ? value.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        a();
        f fVar = this.f2587d;
        fVar.b();
        Object[] objArr = fVar.f2592e;
        if (objArr == null) {
            int length = fVar.f2591d.length;
            if (length < 0) {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
            objArr = new Object[length];
            fVar.f2592e = objArr;
        }
        int i4 = this.f2588e;
        Object obj2 = objArr[i4];
        objArr[i4] = obj;
        return obj2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append('=');
        sb.append(getValue());
        return sb.toString();
    }
}
