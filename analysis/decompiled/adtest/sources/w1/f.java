package w1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Map, Serializable {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final f f2590q;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f2591d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f2592e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f2593f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f2594g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2595h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2596i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2597j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2598k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2599l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public g f2600m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public h f2601n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public g f2602o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2603p;

    static {
        f fVar = new f(0);
        fVar.f2603p = true;
        f2590q = fVar;
    }

    public f() {
        this(8);
    }

    public final int a(Object obj) {
        b();
        while (true) {
            int i4 = i(obj);
            int i5 = this.f2595h * 2;
            int length = this.f2594g.length / 2;
            if (i5 > length) {
                i5 = length;
            }
            int i6 = 0;
            while (true) {
                int[] iArr = this.f2594g;
                int i7 = iArr[i4];
                if (i7 <= 0) {
                    int i8 = this.f2596i;
                    Object[] objArr = this.f2591d;
                    if (i8 >= objArr.length) {
                        f(1);
                        break;
                    }
                    int i9 = i8 + 1;
                    this.f2596i = i9;
                    objArr[i8] = obj;
                    this.f2593f[i8] = i4;
                    iArr[i4] = i9;
                    this.f2599l++;
                    this.f2598k++;
                    if (i6 > this.f2595h) {
                        this.f2595h = i6;
                    }
                    return i8;
                }
                if (j2.i.a(this.f2591d[i7 - 1], obj)) {
                    return -i7;
                }
                i6++;
                if (i6 > i5) {
                    j(this.f2594g.length * 2);
                    break;
                }
                i4 = i4 == 0 ? this.f2594g.length - 1 : i4 - 1;
            }
        }
    }

    public final void b() {
        if (this.f2603p) {
            throw new UnsupportedOperationException();
        }
    }

    public final void c(boolean z3) {
        int i4;
        Object[] objArr = this.f2592e;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            i4 = this.f2596i;
            if (i5 >= i4) {
                break;
            }
            int[] iArr = this.f2593f;
            int i7 = iArr[i5];
            if (i7 >= 0) {
                Object[] objArr2 = this.f2591d;
                objArr2[i6] = objArr2[i5];
                if (objArr != null) {
                    objArr[i6] = objArr[i5];
                }
                if (z3) {
                    iArr[i6] = i7;
                    this.f2594g[i7] = i6 + 1;
                }
                i6++;
            }
            i5++;
        }
        a.a.B(this.f2591d, i6, i4);
        if (objArr != null) {
            a.a.B(objArr, i6, this.f2596i);
        }
        this.f2596i = i6;
    }

    @Override // java.util.Map
    public final void clear() {
        b();
        int i4 = this.f2596i - 1;
        if (i4 >= 0) {
            int i5 = 0;
            while (true) {
                int[] iArr = this.f2593f;
                int i6 = iArr[i5];
                if (i6 >= 0) {
                    this.f2594g[i6] = 0;
                    iArr[i5] = -1;
                }
                if (i5 == i4) {
                    break;
                } else {
                    i5++;
                }
            }
        }
        a.a.B(this.f2591d, 0, this.f2596i);
        Object[] objArr = this.f2592e;
        if (objArr != null) {
            a.a.B(objArr, 0, this.f2596i);
        }
        this.f2599l = 0;
        this.f2596i = 0;
        this.f2598k++;
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return g(obj) >= 0;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return h(obj) >= 0;
    }

    public final boolean d(Collection collection) {
        j2.i.e(collection, "m");
        for (Object obj : collection) {
            if (obj != null) {
                try {
                    if (!e((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean e(Map.Entry entry) {
        j2.i.e(entry, "entry");
        int iG = g(entry.getKey());
        if (iG < 0) {
            return false;
        }
        Object[] objArr = this.f2592e;
        j2.i.b(objArr);
        return j2.i.a(objArr[iG], entry.getValue());
    }

    @Override // java.util.Map
    public final Set entrySet() {
        g gVar = this.f2602o;
        if (gVar != null) {
            return gVar;
        }
        g gVar2 = new g(this, 0);
        this.f2602o = gVar2;
        return gVar2;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        return this.f2599l == map.size() && d(map.entrySet());
    }

    public final void f(int i4) {
        Object[] objArrCopyOf;
        Object[] objArr = this.f2591d;
        int length = objArr.length;
        int i5 = this.f2596i;
        int i6 = length - i5;
        int i7 = i5 - this.f2599l;
        if (i6 < i4 && i6 + i7 >= i4 && i7 >= objArr.length / 4) {
            c(true);
            return;
        }
        int i8 = i5 + i4;
        if (i8 < 0) {
            throw new OutOfMemoryError();
        }
        if (i8 > objArr.length) {
            int length2 = objArr.length;
            int i9 = length2 + (length2 >> 1);
            if (i9 - i8 < 0) {
                i9 = i8;
            }
            if (i9 - 2147483639 > 0) {
                i9 = i8 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            Object[] objArrCopyOf2 = Arrays.copyOf(objArr, i9);
            j2.i.d(objArrCopyOf2, "copyOf(...)");
            this.f2591d = objArrCopyOf2;
            Object[] objArr2 = this.f2592e;
            if (objArr2 != null) {
                objArrCopyOf = Arrays.copyOf(objArr2, i9);
                j2.i.d(objArrCopyOf, "copyOf(...)");
            } else {
                objArrCopyOf = null;
            }
            this.f2592e = objArrCopyOf;
            int[] iArrCopyOf = Arrays.copyOf(this.f2593f, i9);
            j2.i.d(iArrCopyOf, "copyOf(...)");
            this.f2593f = iArrCopyOf;
            int iHighestOneBit = Integer.highestOneBit((i9 >= 1 ? i9 : 1) * 3);
            if (iHighestOneBit > this.f2594g.length) {
                j(iHighestOneBit);
            }
        }
    }

    public final int g(Object obj) {
        int i4 = i(obj);
        int i5 = this.f2595h;
        while (true) {
            int i6 = this.f2594g[i4];
            if (i6 == 0) {
                return -1;
            }
            if (i6 > 0) {
                int i7 = i6 - 1;
                if (j2.i.a(this.f2591d[i7], obj)) {
                    return i7;
                }
            }
            i5--;
            if (i5 < 0) {
                return -1;
            }
            i4 = i4 == 0 ? this.f2594g.length - 1 : i4 - 1;
        }
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        int iG = g(obj);
        if (iG < 0) {
            return null;
        }
        Object[] objArr = this.f2592e;
        j2.i.b(objArr);
        return objArr[iG];
    }

    public final int h(Object obj) {
        int i4 = this.f2596i;
        while (true) {
            i4--;
            if (i4 < 0) {
                return -1;
            }
            if (this.f2593f[i4] >= 0) {
                Object[] objArr = this.f2592e;
                j2.i.b(objArr);
                if (j2.i.a(objArr[i4], obj)) {
                    return i4;
                }
            }
        }
    }

    @Override // java.util.Map
    public final int hashCode() {
        d dVar = new d(this, 0);
        int i4 = 0;
        while (dVar.hasNext()) {
            int i5 = dVar.f2583e;
            f fVar = dVar.f2582d;
            if (i5 >= fVar.f2596i) {
                throw new NoSuchElementException();
            }
            dVar.f2583e = i5 + 1;
            dVar.f2584f = i5;
            Object obj = fVar.f2591d[i5];
            int iHashCode = obj != null ? obj.hashCode() : 0;
            Object[] objArr = fVar.f2592e;
            j2.i.b(objArr);
            Object obj2 = objArr[dVar.f2584f];
            int iHashCode2 = obj2 != null ? obj2.hashCode() : 0;
            dVar.b();
            i4 += iHashCode ^ iHashCode2;
        }
        return i4;
    }

    public final int i(Object obj) {
        return ((obj != null ? obj.hashCode() : 0) * (-1640531527)) >>> this.f2597j;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.f2599l == 0;
    }

    public final void j(int i4) {
        int[] iArr;
        this.f2598k++;
        int i5 = 0;
        if (this.f2596i > this.f2599l) {
            c(false);
        }
        this.f2594g = new int[i4];
        this.f2597j = Integer.numberOfLeadingZeros(i4) + 1;
        while (i5 < this.f2596i) {
            int i6 = i5 + 1;
            int i7 = i(this.f2591d[i5]);
            int i8 = this.f2595h;
            while (true) {
                iArr = this.f2594g;
                if (iArr[i7] == 0) {
                    break;
                }
                i8--;
                if (i8 < 0) {
                    throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
                }
                i7 = i7 == 0 ? iArr.length - 1 : i7 - 1;
            }
            iArr[i7] = i6;
            this.f2593f[i5] = i7;
            i5 = i6;
        }
    }

    public final void k(int i4) {
        Object[] objArr = this.f2591d;
        j2.i.e(objArr, "<this>");
        objArr[i4] = null;
        Object[] objArr2 = this.f2592e;
        if (objArr2 != null) {
            objArr2[i4] = null;
        }
        int length = this.f2593f[i4];
        int i5 = this.f2595h * 2;
        int length2 = this.f2594g.length / 2;
        if (i5 > length2) {
            i5 = length2;
        }
        int i6 = i5;
        int i7 = 0;
        int i8 = length;
        do {
            length = length == 0 ? this.f2594g.length - 1 : length - 1;
            i7++;
            if (i7 > this.f2595h) {
                this.f2594g[i8] = 0;
            } else {
                int[] iArr = this.f2594g;
                int i9 = iArr[length];
                if (i9 == 0) {
                    iArr[i8] = 0;
                } else {
                    if (i9 < 0) {
                        iArr[i8] = -1;
                    } else {
                        int i10 = i9 - 1;
                        int i11 = i(this.f2591d[i10]) - length;
                        int[] iArr2 = this.f2594g;
                        if ((i11 & (iArr2.length - 1)) >= i7) {
                            iArr2[i8] = i9;
                            this.f2593f[i10] = i8;
                        }
                        i6--;
                    }
                    i8 = length;
                    i7 = 0;
                    i6--;
                }
            }
            this.f2593f[i4] = -1;
            this.f2599l--;
            this.f2598k++;
        } while (i6 >= 0);
        this.f2594g[i8] = -1;
        this.f2593f[i4] = -1;
        this.f2599l--;
        this.f2598k++;
    }

    @Override // java.util.Map
    public final Set keySet() {
        g gVar = this.f2600m;
        if (gVar != null) {
            return gVar;
        }
        g gVar2 = new g(this, 1);
        this.f2600m = gVar2;
        return gVar2;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        b();
        int iA = a(obj);
        Object[] objArr = this.f2592e;
        if (objArr == null) {
            int length = this.f2591d.length;
            if (length < 0) {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
            objArr = new Object[length];
            this.f2592e = objArr;
        }
        if (iA >= 0) {
            objArr[iA] = obj2;
            return null;
        }
        int i4 = (-iA) - 1;
        Object obj3 = objArr[i4];
        objArr[i4] = obj2;
        return obj3;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        j2.i.e(map, "from");
        b();
        Set<Map.Entry> setEntrySet = map.entrySet();
        if (setEntrySet.isEmpty()) {
            return;
        }
        f(setEntrySet.size());
        for (Map.Entry entry : setEntrySet) {
            int iA = a(entry.getKey());
            Object[] objArr = this.f2592e;
            if (objArr == null) {
                int length = this.f2591d.length;
                if (length < 0) {
                    throw new IllegalArgumentException("capacity must be non-negative.");
                }
                objArr = new Object[length];
                this.f2592e = objArr;
            }
            if (iA >= 0) {
                objArr[iA] = entry.getValue();
            } else {
                int i4 = (-iA) - 1;
                if (!j2.i.a(entry.getValue(), objArr[i4])) {
                    objArr[i4] = entry.getValue();
                }
            }
        }
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        b();
        int iG = g(obj);
        if (iG < 0) {
            return null;
        }
        Object[] objArr = this.f2592e;
        j2.i.b(objArr);
        Object obj2 = objArr[iG];
        k(iG);
        return obj2;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f2599l;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.f2599l * 3) + 2);
        sb.append("{");
        int i4 = 0;
        d dVar = new d(this, 0);
        while (dVar.hasNext()) {
            if (i4 > 0) {
                sb.append(", ");
            }
            int i5 = dVar.f2583e;
            f fVar = dVar.f2582d;
            if (i5 >= fVar.f2596i) {
                throw new NoSuchElementException();
            }
            dVar.f2583e = i5 + 1;
            dVar.f2584f = i5;
            Object obj = fVar.f2591d[i5];
            if (obj == fVar) {
                sb.append("(this Map)");
            } else {
                sb.append(obj);
            }
            sb.append('=');
            Object[] objArr = fVar.f2592e;
            j2.i.b(objArr);
            Object obj2 = objArr[dVar.f2584f];
            if (obj2 == fVar) {
                sb.append("(this Map)");
            } else {
                sb.append(obj2);
            }
            dVar.b();
            i4++;
        }
        sb.append("}");
        String string = sb.toString();
        j2.i.d(string, "toString(...)");
        return string;
    }

    @Override // java.util.Map
    public final Collection values() {
        h hVar = this.f2601n;
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h(this);
        this.f2601n = hVar2;
        return hVar2;
    }

    public f(int i4) {
        if (i4 < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        Object[] objArr = new Object[i4];
        int[] iArr = new int[i4];
        int iHighestOneBit = Integer.highestOneBit((i4 < 1 ? 1 : i4) * 3);
        this.f2591d = objArr;
        this.f2592e = null;
        this.f2593f = iArr;
        this.f2594g = new int[iHighestOneBit];
        this.f2595h = 2;
        this.f2596i = 0;
        this.f2597j = Integer.numberOfLeadingZeros(iHighestOneBit) + 1;
    }
}
