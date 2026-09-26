package e;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import l3.h;
import v1.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Collection, Set {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f570d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f571e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f572f;

    public final Object a(int i4) {
        int i5 = this.f572f;
        Object[] objArr = this.f571e;
        Object obj = objArr[i4];
        if (i5 <= 1) {
            clear();
            return obj;
        }
        int i6 = i5 - 1;
        int[] iArr = this.f570d;
        if (iArr.length <= 8 || i5 >= iArr.length / 3) {
            if (i4 < i6) {
                int i7 = i4 + 1;
                i.U(iArr, iArr, i4, i7, i5);
                Object[] objArr2 = this.f571e;
                i.V(objArr2, objArr2, i4, i7, i5);
            }
            this.f571e[i6] = null;
        } else {
            int i8 = i5 > 8 ? i5 + (i5 >> 1) : 8;
            int[] iArr2 = new int[i8];
            this.f570d = iArr2;
            this.f571e = new Object[i8];
            if (i4 > 0) {
                i.U(iArr, iArr2, 0, 0, i4);
                i.W(objArr, this.f571e, 0, i4, 6);
            }
            if (i4 < i6) {
                int i9 = i4 + 1;
                i.U(iArr, this.f570d, i4, i9, i5);
                i.V(objArr, this.f571e, i4, i9, i5);
            }
        }
        if (i5 != this.f572f) {
            throw new ConcurrentModificationException();
        }
        this.f572f = i6;
        return obj;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        int i4;
        int iK;
        int i5 = this.f572f;
        if (obj == null) {
            iK = h.K(this, null, 0);
            i4 = 0;
        } else {
            int iHashCode = obj.hashCode();
            i4 = iHashCode;
            iK = h.K(this, obj, iHashCode);
        }
        if (iK >= 0) {
            return false;
        }
        int i6 = ~iK;
        int[] iArr = this.f570d;
        if (i5 >= iArr.length) {
            int i7 = 8;
            if (i5 >= 8) {
                i7 = (i5 >> 1) + i5;
            } else if (i5 < 4) {
                i7 = 4;
            }
            Object[] objArr = this.f571e;
            int[] iArr2 = new int[i7];
            this.f570d = iArr2;
            this.f571e = new Object[i7];
            if (i5 != this.f572f) {
                throw new ConcurrentModificationException();
            }
            if (iArr2.length != 0) {
                i.U(iArr, iArr2, 0, 0, iArr.length);
                i.W(objArr, this.f571e, 0, objArr.length, 6);
            }
        }
        if (i6 < i5) {
            int[] iArr3 = this.f570d;
            int i8 = i6 + 1;
            i.U(iArr3, iArr3, i8, i6, i5);
            Object[] objArr2 = this.f571e;
            i.V(objArr2, objArr2, i8, i6, i5);
        }
        int i9 = this.f572f;
        if (i5 == i9) {
            int[] iArr4 = this.f570d;
            if (i6 < iArr4.length) {
                iArr4[i6] = i4;
                this.f571e[i6] = obj;
                this.f572f = i9 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        j2.i.e(collection, "elements");
        int size = collection.size() + this.f572f;
        int i4 = this.f572f;
        int[] iArr = this.f570d;
        boolean zAdd = false;
        if (iArr.length < size) {
            Object[] objArr = this.f571e;
            int[] iArr2 = new int[size];
            this.f570d = iArr2;
            this.f571e = new Object[size];
            if (i4 > 0) {
                i.U(iArr, iArr2, 0, 0, i4);
                i.W(objArr, this.f571e, 0, this.f572f, 6);
            }
        }
        if (this.f572f != i4) {
            throw new ConcurrentModificationException();
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    @Override // java.util.Collection, java.util.Set
    public final void clear() {
        if (this.f572f != 0) {
            this.f570d = f.a.f834a;
            this.f571e = f.a.f835b;
            this.f572f = 0;
        }
        if (this.f572f != 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return (obj == null ? h.K(this, null, 0) : h.K(this, obj, obj.hashCode())) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        j2.i.e(collection, "elements");
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Set) || this.f572f != ((Set) obj).size()) {
            return false;
        }
        try {
            int i4 = this.f572f;
            for (int i5 = 0; i5 < i4; i5++) {
                if (!((Set) obj).contains(this.f571e[i5])) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        int[] iArr = this.f570d;
        int i4 = this.f572f;
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            i5 += iArr[i6];
        }
        return i5;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.f572f <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new a(this);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int iK = obj == null ? h.K(this, null, 0) : h.K(this, obj, obj.hashCode());
        if (iK < 0) {
            return false;
        }
        a(iK);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        j2.i.e(collection, "elements");
        Iterator it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        j2.i.e(collection, "elements");
        boolean z3 = false;
        for (int i4 = this.f572f - 1; -1 < i4; i4--) {
            if (!collection.contains(this.f571e[i4])) {
                a(i4);
                z3 = true;
            }
        }
        return z3;
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this.f572f;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        return i.Y(this.f571e, 0, this.f572f);
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f572f * 14);
        sb.append('{');
        int i4 = this.f572f;
        for (int i5 = 0; i5 < i4; i5++) {
            if (i5 > 0) {
                sb.append(", ");
            }
            Object obj = this.f571e[i5];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        j2.i.d(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        j2.i.e(objArr, "array");
        int i4 = this.f572f;
        if (objArr.length < i4) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i4);
        } else if (objArr.length > i4) {
            objArr[i4] = null;
        }
        i.V(this.f571e, objArr, 0, 0, this.f572f);
        return objArr;
    }
}
