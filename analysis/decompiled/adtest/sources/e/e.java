package e;

import j2.i;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Set;
import u0.n;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Map {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f564d = f.a.f834a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f565e = f.a.f835b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f566f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public n f567g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b f568h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public d f569i;

    public final int a(Object obj) {
        int i4 = this.f566f * 2;
        Object[] objArr = this.f565e;
        if (obj == null) {
            for (int i5 = 1; i5 < i4; i5 += 2) {
                if (objArr[i5] == null) {
                    return i5 >> 1;
                }
            }
            return -1;
        }
        for (int i6 = 1; i6 < i4; i6 += 2) {
            if (obj.equals(objArr[i6])) {
                return i6 >> 1;
            }
        }
        return -1;
    }

    public final boolean b(Object obj) {
        return f(obj) >= 0;
    }

    public final boolean c(Object obj) {
        return a(obj) >= 0;
    }

    @Override // java.util.Map
    public final void clear() {
        if (this.f566f > 0) {
            this.f564d = f.a.f834a;
            this.f565e = f.a.f835b;
            this.f566f = 0;
        }
        if (this.f566f > 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return b(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return c(obj);
    }

    public final Object d(Object obj) {
        int iF = f(obj);
        if (iF >= 0) {
            return this.f565e[(iF << 1) + 1];
        }
        return null;
    }

    public final int e(int i4, Object obj) {
        int i5 = this.f566f;
        if (i5 == 0) {
            return -1;
        }
        int iA = f.a.a(this.f564d, i5, i4);
        if (iA < 0 || i.a(obj, this.f565e[iA << 1])) {
            return iA;
        }
        int i6 = iA + 1;
        while (i6 < i5 && this.f564d[i6] == i4) {
            if (i.a(obj, this.f565e[i6 << 1])) {
                return i6;
            }
            i6++;
        }
        for (int i7 = iA - 1; i7 >= 0 && this.f564d[i7] == i4; i7--) {
            if (i.a(obj, this.f565e[i7 << 1])) {
                return i7;
            }
        }
        return ~i6;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        n nVar = this.f567g;
        if (nVar != null) {
            return nVar;
        }
        n nVar2 = new n(this, 2);
        this.f567g = nVar2;
        return nVar2;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof e) {
                int i4 = this.f566f;
                if (i4 != ((e) obj).f566f) {
                    return false;
                }
                e eVar = (e) obj;
                for (int i5 = 0; i5 < i4; i5++) {
                    Object objH = h(i5);
                    Object objL = l(i5);
                    Object objD = eVar.d(objH);
                    if (objL == null) {
                        if (objD != null || !eVar.b(objH)) {
                            return false;
                        }
                    } else if (!objL.equals(objD)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || this.f566f != ((Map) obj).size()) {
                return false;
            }
            int i6 = this.f566f;
            for (int i7 = 0; i7 < i6; i7++) {
                Object objH2 = h(i7);
                Object objL2 = l(i7);
                Object obj2 = ((Map) obj).get(objH2);
                if (objL2 == null) {
                    if (obj2 != null || !((Map) obj).containsKey(objH2)) {
                        return false;
                    }
                } else if (!objL2.equals(obj2)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final int f(Object obj) {
        return obj == null ? g() : e(obj.hashCode(), obj);
    }

    public final int g() {
        int i4 = this.f566f;
        if (i4 == 0) {
            return -1;
        }
        int iA = f.a.a(this.f564d, i4, 0);
        if (iA < 0 || this.f565e[iA << 1] == null) {
            return iA;
        }
        int i5 = iA + 1;
        while (i5 < i4 && this.f564d[i5] == 0) {
            if (this.f565e[i5 << 1] == null) {
                return i5;
            }
            i5++;
        }
        for (int i6 = iA - 1; i6 >= 0 && this.f564d[i6] == 0; i6--) {
            if (this.f565e[i6 << 1] == null) {
                return i6;
            }
        }
        return ~i5;
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        return d(obj);
    }

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        int iF = f(obj);
        return iF >= 0 ? this.f565e[(iF << 1) + 1] : obj2;
    }

    public final Object h(int i4) {
        if (i4 < 0 || i4 >= this.f566f) {
            throw new IllegalArgumentException(a1.c.c(i4, "Expected index to be within 0..size()-1, but was ").toString());
        }
        return this.f565e[i4 << 1];
    }

    @Override // java.util.Map
    public final int hashCode() {
        int[] iArr = this.f564d;
        Object[] objArr = this.f565e;
        int i4 = this.f566f;
        int i5 = 1;
        int i6 = 0;
        int iHashCode = 0;
        while (i6 < i4) {
            Object obj = objArr[i5];
            iHashCode += (obj != null ? obj.hashCode() : 0) ^ iArr[i6];
            i6++;
            i5 += 2;
        }
        return iHashCode;
    }

    public final Object i(Object obj) {
        int iF = f(obj);
        if (iF >= 0) {
            return j(iF);
        }
        return null;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.f566f <= 0;
    }

    public final Object j(int i4) {
        int i5;
        if (i4 < 0 || i4 >= (i5 = this.f566f)) {
            throw new IllegalArgumentException(a1.c.c(i4, "Expected index to be within 0..size()-1, but was ").toString());
        }
        Object[] objArr = this.f565e;
        int i6 = i4 << 1;
        Object obj = objArr[i6 + 1];
        if (i5 <= 1) {
            clear();
            return obj;
        }
        int i7 = i5 - 1;
        int[] iArr = this.f564d;
        if (iArr.length <= 8 || i5 >= iArr.length / 3) {
            if (i4 < i7) {
                int i8 = i4 + 1;
                v1.i.U(iArr, iArr, i4, i8, i5);
                Object[] objArr2 = this.f565e;
                v1.i.V(objArr2, objArr2, i6, i8 << 1, i5 << 1);
            }
            Object[] objArr3 = this.f565e;
            int i9 = i7 << 1;
            objArr3[i9] = null;
            objArr3[i9 + 1] = null;
        } else {
            int i10 = i5 > 8 ? i5 + (i5 >> 1) : 8;
            int[] iArrCopyOf = Arrays.copyOf(iArr, i10);
            i.d(iArrCopyOf, "copyOf(this, newSize)");
            this.f564d = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f565e, i10 << 1);
            i.d(objArrCopyOf, "copyOf(this, newSize)");
            this.f565e = objArrCopyOf;
            if (i5 != this.f566f) {
                throw new ConcurrentModificationException();
            }
            if (i4 > 0) {
                v1.i.U(iArr, this.f564d, 0, 0, i4);
                v1.i.V(objArr, this.f565e, 0, 0, i6);
            }
            if (i4 < i7) {
                int i11 = i4 + 1;
                v1.i.U(iArr, this.f564d, i4, i11, i5);
                v1.i.V(objArr, this.f565e, i6, i11 << 1, i5 << 1);
            }
        }
        if (i5 != this.f566f) {
            throw new ConcurrentModificationException();
        }
        this.f566f = i7;
        return obj;
    }

    public final Object k(int i4, Object obj) {
        if (i4 < 0 || i4 >= this.f566f) {
            throw new IllegalArgumentException(a1.c.c(i4, "Expected index to be within 0..size()-1, but was ").toString());
        }
        int i5 = (i4 << 1) + 1;
        Object[] objArr = this.f565e;
        Object obj2 = objArr[i5];
        objArr[i5] = obj;
        return obj2;
    }

    @Override // java.util.Map
    public final Set keySet() {
        b bVar = this.f568h;
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b(this);
        this.f568h = bVar2;
        return bVar2;
    }

    public final Object l(int i4) {
        if (i4 < 0 || i4 >= this.f566f) {
            throw new IllegalArgumentException(a1.c.c(i4, "Expected index to be within 0..size()-1, but was ").toString());
        }
        return this.f565e[(i4 << 1) + 1];
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        int i4 = this.f566f;
        int iHashCode = obj != null ? obj.hashCode() : 0;
        int iE = obj != null ? e(iHashCode, obj) : g();
        if (iE >= 0) {
            int i5 = (iE << 1) + 1;
            Object[] objArr = this.f565e;
            Object obj3 = objArr[i5];
            objArr[i5] = obj2;
            return obj3;
        }
        int i6 = ~iE;
        int[] iArr = this.f564d;
        if (i4 >= iArr.length) {
            int i7 = 8;
            if (i4 >= 8) {
                i7 = (i4 >> 1) + i4;
            } else if (i4 < 4) {
                i7 = 4;
            }
            int[] iArrCopyOf = Arrays.copyOf(iArr, i7);
            i.d(iArrCopyOf, "copyOf(this, newSize)");
            this.f564d = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f565e, i7 << 1);
            i.d(objArrCopyOf, "copyOf(this, newSize)");
            this.f565e = objArrCopyOf;
            if (i4 != this.f566f) {
                throw new ConcurrentModificationException();
            }
        }
        if (i6 < i4) {
            int[] iArr2 = this.f564d;
            int i8 = i6 + 1;
            v1.i.U(iArr2, iArr2, i8, i6, i4);
            Object[] objArr2 = this.f565e;
            v1.i.V(objArr2, objArr2, i8 << 1, i6 << 1, this.f566f << 1);
        }
        int i9 = this.f566f;
        if (i4 == i9) {
            int[] iArr3 = this.f564d;
            if (i6 < iArr3.length) {
                iArr3[i6] = iHashCode;
                Object[] objArr3 = this.f565e;
                int i10 = i6 << 1;
                objArr3[i10] = obj;
                objArr3[i10 + 1] = obj2;
                this.f566f = i9 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        int size = map.size() + this.f566f;
        int i4 = this.f566f;
        int[] iArr = this.f564d;
        if (iArr.length < size) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, size);
            i.d(iArrCopyOf, "copyOf(this, newSize)");
            this.f564d = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f565e, size * 2);
            i.d(objArrCopyOf, "copyOf(this, newSize)");
            this.f565e = objArrCopyOf;
        }
        if (this.f566f != i4) {
            throw new ConcurrentModificationException();
        }
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Object putIfAbsent(Object obj, Object obj2) {
        Object objD = d(obj);
        return objD == null ? put(obj, obj2) : objD;
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        return i(obj);
    }

    @Override // java.util.Map
    public final Object replace(Object obj, Object obj2) {
        int iF = f(obj);
        if (iF >= 0) {
            return k(iF, obj2);
        }
        return null;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f566f;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f566f * 28);
        sb.append('{');
        int i4 = this.f566f;
        for (int i5 = 0; i5 < i4; i5++) {
            if (i5 > 0) {
                sb.append(", ");
            }
            Object objH = h(i5);
            if (objH != sb) {
                sb.append(objH);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object objL = l(i5);
            if (objL != sb) {
                sb.append(objL);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        i.d(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    @Override // java.util.Map
    public final Collection values() {
        d dVar = this.f569i;
        if (dVar != null) {
            return dVar;
        }
        d dVar2 = new d(this);
        this.f569i = dVar2;
        return dVar2;
    }

    @Override // java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        int iF = f(obj);
        if (iF < 0 || !i.a(obj2, l(iF))) {
            return false;
        }
        j(iF);
        return true;
    }

    @Override // java.util.Map
    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int iF = f(obj);
        if (iF < 0 || !i.a(obj2, l(iF))) {
            return false;
        }
        k(iF, obj3);
        return true;
    }
}
