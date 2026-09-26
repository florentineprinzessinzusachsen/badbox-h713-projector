package w1;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends v1.e implements RandomAccess, Serializable {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c f2578g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f2579d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2580e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2581f;

    static {
        c cVar = new c(0);
        cVar.f2581f = true;
        f2578g = cVar;
    }

    public c(int i4) {
        if (i4 < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        this.f2579d = new Object[i4];
    }

    @Override // v1.e
    public final int a() {
        return this.f2580e;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        f();
        int i4 = this.f2580e;
        ((AbstractList) this).modCount++;
        g(i4, 1);
        this.f2579d[i4] = obj;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        j2.i.e(collection, "elements");
        f();
        int size = collection.size();
        d(this.f2580e, collection, size);
        return size > 0;
    }

    @Override // v1.e
    public final Object b(int i4) {
        f();
        int i5 = this.f2580e;
        if (i4 < 0 || i4 >= i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        return h(i4);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        f();
        i(0, this.f2580e);
    }

    public final void d(int i4, Collection collection, int i5) {
        ((AbstractList) this).modCount++;
        g(i4, i5);
        Iterator it = collection.iterator();
        for (int i6 = 0; i6 < i5; i6++) {
            this.f2579d[i4 + i6] = it.next();
        }
    }

    public final void e(int i4, Object obj) {
        ((AbstractList) this).modCount++;
        g(i4, 1);
        this.f2579d[i4] = obj;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            Object[] objArr = this.f2579d;
            int i4 = this.f2580e;
            if (i4 == list.size()) {
                for (int i5 = 0; i5 < i4; i5++) {
                    if (j2.i.a(objArr[i5], list.get(i5))) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final void f() {
        if (this.f2581f) {
            throw new UnsupportedOperationException();
        }
    }

    public final void g(int i4, int i5) {
        int i6 = this.f2580e + i5;
        if (i6 < 0) {
            throw new OutOfMemoryError();
        }
        Object[] objArr = this.f2579d;
        if (i6 > objArr.length) {
            int length = objArr.length;
            int i7 = length + (length >> 1);
            if (i7 - i6 < 0) {
                i7 = i6;
            }
            if (i7 - 2147483639 > 0) {
                i7 = i6 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            Object[] objArrCopyOf = Arrays.copyOf(objArr, i7);
            j2.i.d(objArrCopyOf, "copyOf(...)");
            this.f2579d = objArrCopyOf;
        }
        Object[] objArr2 = this.f2579d;
        v1.i.V(objArr2, objArr2, i4 + i5, i4, this.f2580e);
        this.f2580e += i5;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i4) {
        int i5 = this.f2580e;
        if (i4 < 0 || i4 >= i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        return this.f2579d[i4];
    }

    public final Object h(int i4) {
        ((AbstractList) this).modCount++;
        Object[] objArr = this.f2579d;
        Object obj = objArr[i4];
        v1.i.V(objArr, objArr, i4, i4 + 1, this.f2580e);
        Object[] objArr2 = this.f2579d;
        int i5 = this.f2580e - 1;
        j2.i.e(objArr2, "<this>");
        objArr2[i5] = null;
        this.f2580e--;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        Object[] objArr = this.f2579d;
        int i4 = this.f2580e;
        int iHashCode = 1;
        for (int i5 = 0; i5 < i4; i5++) {
            Object obj = objArr[i5];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    public final void i(int i4, int i5) {
        if (i5 > 0) {
            ((AbstractList) this).modCount++;
        }
        Object[] objArr = this.f2579d;
        v1.i.V(objArr, objArr, i4, i4 + i5, this.f2580e);
        Object[] objArr2 = this.f2579d;
        int i6 = this.f2580e;
        a.a.B(objArr2, i6 - i5, i6);
        this.f2580e -= i5;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        for (int i4 = 0; i4 < this.f2580e; i4++) {
            if (j2.i.a(this.f2579d[i4], obj)) {
                return i4;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.f2580e == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    public final int j(int i4, int i5, Collection collection, boolean z3) {
        int i6 = 0;
        int i7 = 0;
        while (i6 < i5) {
            int i8 = i4 + i6;
            if (collection.contains(this.f2579d[i8]) == z3) {
                Object[] objArr = this.f2579d;
                i6++;
                objArr[i7 + i4] = objArr[i8];
                i7++;
            } else {
                i6++;
            }
        }
        int i9 = i5 - i7;
        Object[] objArr2 = this.f2579d;
        v1.i.V(objArr2, objArr2, i4 + i7, i5 + i4, this.f2580e);
        Object[] objArr3 = this.f2579d;
        int i10 = this.f2580e;
        a.a.B(objArr3, i10 - i9, i10);
        if (i9 > 0) {
            ((AbstractList) this).modCount++;
        }
        this.f2580e -= i9;
        return i9;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        for (int i4 = this.f2580e - 1; i4 >= 0; i4--) {
            if (j2.i.a(this.f2579d[i4], obj)) {
                return i4;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        f();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            b(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        j2.i.e(collection, "elements");
        f();
        return j(0, this.f2580e, collection, false) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        j2.i.e(collection, "elements");
        f();
        return j(0, this.f2580e, collection, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i4, Object obj) {
        f();
        int i5 = this.f2580e;
        if (i4 < 0 || i4 >= i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        Object[] objArr = this.f2579d;
        Object obj2 = objArr[i4];
        objArr[i4] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i4, int i5) {
        a.a.g(i4, i5, this.f2580e);
        return new b(this.f2579d, i4, i5 - i4, null, this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        j2.i.e(objArr, "array");
        int length = objArr.length;
        int i4 = this.f2580e;
        if (length < i4) {
            Object[] objArrCopyOfRange = Arrays.copyOfRange(this.f2579d, 0, i4, objArr.getClass());
            j2.i.d(objArrCopyOfRange, "copyOfRange(...)");
            return objArrCopyOfRange;
        }
        v1.i.V(this.f2579d, objArr, 0, 0, i4);
        int i5 = this.f2580e;
        if (i5 < objArr.length) {
            objArr[i5] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return a.a.c(this.f2579d, 0, this.f2580e, this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i4) {
        int i5 = this.f2580e;
        if (i4 < 0 || i4 > i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        return new a(this, i4);
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i4, Collection collection) {
        j2.i.e(collection, "elements");
        f();
        int i5 = this.f2580e;
        if (i4 >= 0 && i4 <= i5) {
            int size = collection.size();
            d(i4, collection, size);
            return size > 0;
        }
        throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i4, Object obj) {
        f();
        int i5 = this.f2580e;
        if (i4 >= 0 && i4 <= i5) {
            ((AbstractList) this).modCount++;
            g(i4, 1);
            this.f2579d[i4] = obj;
            return;
        }
        throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return v1.i.Y(this.f2579d, 0, this.f2580e);
    }
}
