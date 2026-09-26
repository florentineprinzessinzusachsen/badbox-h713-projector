package a.b;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: compiled from: SimpleArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class g<K, V> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static Object[] f58d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static int f59e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static Object[] f60f;
    static int g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int[] f61a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Object[] f62b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f63c;

    public g() {
        this.f61a = c.f29a;
        this.f62b = c.f31c;
        this.f63c = 0;
    }

    private static int a(int[] iArr, int i, int i2) {
        try {
            return c.a(iArr, i, i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private void e(int i) {
        if (i == 8) {
            synchronized (g.class) {
                if (f60f != null) {
                    Object[] objArr = f60f;
                    this.f62b = objArr;
                    f60f = (Object[]) objArr[0];
                    this.f61a = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    g--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (g.class) {
                if (f58d != null) {
                    Object[] objArr2 = f58d;
                    this.f62b = objArr2;
                    f58d = (Object[]) objArr2[0];
                    this.f61a = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f59e--;
                    return;
                }
            }
        }
        this.f61a = new int[i];
        this.f62b = new Object[i << 1];
    }

    int b(Object obj) {
        int i = this.f63c * 2;
        Object[] objArr = this.f62b;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public V c(int i) {
        int i2;
        Object[] objArr = this.f62b;
        int i3 = i << 1;
        V v = (V) objArr[i3 + 1];
        int i4 = this.f63c;
        if (i4 <= 1) {
            a(this.f61a, objArr, i4);
            this.f61a = c.f29a;
            this.f62b = c.f31c;
            i2 = 0;
        } else {
            i2 = i4 - 1;
            int[] iArr = this.f61a;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i < i2) {
                    int[] iArr2 = this.f61a;
                    int i5 = i + 1;
                    int i6 = i2 - i;
                    System.arraycopy(iArr2, i5, iArr2, i, i6);
                    Object[] objArr2 = this.f62b;
                    System.arraycopy(objArr2, i5 << 1, objArr2, i3, i6 << 1);
                }
                Object[] objArr3 = this.f62b;
                int i7 = i2 << 1;
                objArr3[i7] = null;
                objArr3[i7 + 1] = null;
            } else {
                int i8 = i4 > 8 ? i4 + (i4 >> 1) : 8;
                int[] iArr3 = this.f61a;
                Object[] objArr4 = this.f62b;
                e(i8);
                if (i4 != this.f63c) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    System.arraycopy(iArr3, 0, this.f61a, 0, i);
                    System.arraycopy(objArr4, 0, this.f62b, 0, i3);
                }
                if (i < i2) {
                    int i9 = i + 1;
                    int i10 = i2 - i;
                    System.arraycopy(iArr3, i9, this.f61a, i, i10);
                    System.arraycopy(objArr4, i9 << 1, this.f62b, i3, i10 << 1);
                }
            }
        }
        if (i4 != this.f63c) {
            throw new ConcurrentModificationException();
        }
        this.f63c = i2;
        return v;
    }

    public void clear() {
        int i = this.f63c;
        if (i > 0) {
            int[] iArr = this.f61a;
            Object[] objArr = this.f62b;
            this.f61a = c.f29a;
            this.f62b = c.f31c;
            this.f63c = 0;
            a(iArr, objArr, i);
        }
        if (this.f63c > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return a(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return b(obj) >= 0;
    }

    public V d(int i) {
        return (V) this.f62b[(i << 1) + 1];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (size() != gVar.size()) {
                return false;
            }
            for (int i = 0; i < this.f63c; i++) {
                try {
                    K kB = b(i);
                    V vD = d(i);
                    Object obj2 = gVar.get(kB);
                    if (vD == null) {
                        if (obj2 != null || !gVar.containsKey(kB)) {
                            return false;
                        }
                    } else if (!vD.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.f63c; i2++) {
                try {
                    K kB2 = b(i2);
                    V vD2 = d(i2);
                    Object obj3 = map.get(kB2);
                    if (vD2 == null) {
                        if (obj3 != null || !map.containsKey(kB2)) {
                            return false;
                        }
                    } else if (!vD2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public V getOrDefault(Object obj, V v) {
        int iA = a(obj);
        return iA >= 0 ? (V) this.f62b[(iA << 1) + 1] : v;
    }

    public int hashCode() {
        int[] iArr = this.f61a;
        Object[] objArr = this.f62b;
        int i = this.f63c;
        int i2 = 0;
        int iHashCode = 0;
        int i3 = 1;
        while (i2 < i) {
            Object obj = objArr[i3];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i2];
            i2++;
            i3 += 2;
        }
        return iHashCode;
    }

    public boolean isEmpty() {
        return this.f63c <= 0;
    }

    public V put(K k, V v) {
        int i;
        int iA;
        int i2 = this.f63c;
        if (k == null) {
            iA = a();
            i = 0;
        } else {
            int iHashCode = k.hashCode();
            i = iHashCode;
            iA = a(k, iHashCode);
        }
        if (iA >= 0) {
            int i3 = (iA << 1) + 1;
            Object[] objArr = this.f62b;
            V v2 = (V) objArr[i3];
            objArr[i3] = v;
            return v2;
        }
        int i4 = iA ^ (-1);
        if (i2 >= this.f61a.length) {
            int i5 = 4;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.f61a;
            Object[] objArr2 = this.f62b;
            e(i5);
            if (i2 != this.f63c) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f61a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f62b, 0, objArr2.length);
            }
            a(iArr, objArr2, i2);
        }
        if (i4 < i2) {
            int[] iArr3 = this.f61a;
            int i6 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i6, i2 - i4);
            Object[] objArr3 = this.f62b;
            System.arraycopy(objArr3, i4 << 1, objArr3, i6 << 1, (this.f63c - i4) << 1);
        }
        int i7 = this.f63c;
        if (i2 == i7) {
            int[] iArr4 = this.f61a;
            if (i4 < iArr4.length) {
                iArr4[i4] = i;
                Object[] objArr4 = this.f62b;
                int i8 = i4 << 1;
                objArr4[i8] = k;
                objArr4[i8 + 1] = v;
                this.f63c = i7 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public V putIfAbsent(K k, V v) {
        V v2 = get(k);
        return v2 == null ? put(k, v) : v2;
    }

    public V remove(Object obj) {
        int iA = a(obj);
        if (iA >= 0) {
            return c(iA);
        }
        return null;
    }

    public V replace(K k, V v) {
        int iA = a(k);
        if (iA >= 0) {
            return a(iA, v);
        }
        return null;
    }

    public int size() {
        return this.f63c;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f63c * 28);
        sb.append('{');
        for (int i = 0; i < this.f63c; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            K kB = b(i);
            if (kB != this) {
                sb.append(kB);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V vD = d(i);
            if (vD != this) {
                sb.append(vD);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    int a(Object obj, int i) {
        int i2 = this.f63c;
        if (i2 == 0) {
            return -1;
        }
        int iA = a(this.f61a, i2, i);
        if (iA < 0 || obj.equals(this.f62b[iA << 1])) {
            return iA;
        }
        int i3 = iA + 1;
        while (i3 < i2 && this.f61a[i3] == i) {
            if (obj.equals(this.f62b[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iA - 1; i4 >= 0 && this.f61a[i4] == i; i4--) {
            if (obj.equals(this.f62b[i4 << 1])) {
                return i4;
            }
        }
        return i3 ^ (-1);
    }

    public boolean remove(Object obj, Object obj2) {
        int iA = a(obj);
        if (iA < 0) {
            return false;
        }
        V vD = d(iA);
        if (obj2 != vD && (obj2 == null || !obj2.equals(vD))) {
            return false;
        }
        c(iA);
        return true;
    }

    public boolean replace(K k, V v, V v2) {
        int iA = a(k);
        if (iA < 0) {
            return false;
        }
        V vD = d(iA);
        if (vD != v && (v == null || !v.equals(vD))) {
            return false;
        }
        a(iA, v2);
        return true;
    }

    public g(int i) {
        if (i == 0) {
            this.f61a = c.f29a;
            this.f62b = c.f31c;
        } else {
            e(i);
        }
        this.f63c = 0;
    }

    public K b(int i) {
        return (K) this.f62b[i << 1];
    }

    int a() {
        int i = this.f63c;
        if (i == 0) {
            return -1;
        }
        int iA = a(this.f61a, i, 0);
        if (iA < 0 || this.f62b[iA << 1] == null) {
            return iA;
        }
        int i2 = iA + 1;
        while (i2 < i && this.f61a[i2] == 0) {
            if (this.f62b[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = iA - 1; i3 >= 0 && this.f61a[i3] == 0; i3--) {
            if (this.f62b[i3 << 1] == null) {
                return i3;
            }
        }
        return i2 ^ (-1);
    }

    private static void a(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (g.class) {
                if (g < 10) {
                    objArr[0] = f60f;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f60f = objArr;
                    g++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (g.class) {
                if (f59e < 10) {
                    objArr[0] = f58d;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f58d = objArr;
                    f59e++;
                }
            }
        }
    }

    public void a(int i) {
        int i2 = this.f63c;
        int[] iArr = this.f61a;
        if (iArr.length < i) {
            Object[] objArr = this.f62b;
            e(i);
            if (this.f63c > 0) {
                System.arraycopy(iArr, 0, this.f61a, 0, i2);
                System.arraycopy(objArr, 0, this.f62b, 0, i2 << 1);
            }
            a(iArr, objArr, i2);
        }
        if (this.f63c != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public int a(Object obj) {
        return obj == null ? a() : a(obj, obj.hashCode());
    }

    public V a(int i, V v) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.f62b;
        V v2 = (V) objArr[i2];
        objArr[i2] = v;
        return v2;
    }
}
