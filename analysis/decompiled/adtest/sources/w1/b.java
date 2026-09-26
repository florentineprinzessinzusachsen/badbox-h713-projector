package w1;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends v1.e implements RandomAccess, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f2573d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2574e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2575f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b f2576g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final c f2577h;

    public b(Object[] objArr, int i4, int i5, b bVar, c cVar) {
        j2.i.e(objArr, "backing");
        j2.i.e(cVar, "root");
        this.f2573d = objArr;
        this.f2574e = i4;
        this.f2575f = i5;
        this.f2576g = bVar;
        this.f2577h = cVar;
        ((AbstractList) this).modCount = ((AbstractList) cVar).modCount;
    }

    @Override // v1.e
    public final int a() {
        f();
        return this.f2575f;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        g();
        f();
        e(this.f2574e + this.f2575f, obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        j2.i.e(collection, "elements");
        g();
        f();
        int size = collection.size();
        d(this.f2574e + this.f2575f, collection, size);
        return size > 0;
    }

    @Override // v1.e
    public final Object b(int i4) {
        g();
        f();
        int i5 = this.f2575f;
        if (i4 < 0 || i4 >= i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        return h(this.f2574e + i4);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        g();
        f();
        i(this.f2574e, this.f2575f);
    }

    public final void d(int i4, Collection collection, int i5) {
        ((AbstractList) this).modCount++;
        c cVar = this.f2577h;
        b bVar = this.f2576g;
        if (bVar != null) {
            bVar.d(i4, collection, i5);
        } else {
            c cVar2 = c.f2578g;
            cVar.d(i4, collection, i5);
        }
        this.f2573d = cVar.f2579d;
        this.f2575f += i5;
    }

    public final void e(int i4, Object obj) {
        ((AbstractList) this).modCount++;
        c cVar = this.f2577h;
        b bVar = this.f2576g;
        if (bVar != null) {
            bVar.e(i4, obj);
        } else {
            c cVar2 = c.f2578g;
            cVar.e(i4, obj);
        }
        this.f2573d = cVar.f2579d;
        this.f2575f++;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        f();
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            Object[] objArr = this.f2573d;
            int i4 = this.f2575f;
            if (i4 == list.size()) {
                for (int i5 = 0; i5 < i4; i5++) {
                    if (j2.i.a(objArr[this.f2574e + i5], list.get(i5))) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final void f() {
        if (((AbstractList) this.f2577h).modCount != ((AbstractList) this).modCount) {
            throw new ConcurrentModificationException();
        }
    }

    public final void g() {
        if (this.f2577h.f2581f) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i4) {
        f();
        int i5 = this.f2575f;
        if (i4 < 0 || i4 >= i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        return this.f2573d[this.f2574e + i4];
    }

    public final Object h(int i4) {
        Object objH;
        ((AbstractList) this).modCount++;
        b bVar = this.f2576g;
        if (bVar != null) {
            objH = bVar.h(i4);
        } else {
            c cVar = c.f2578g;
            objH = this.f2577h.h(i4);
        }
        this.f2575f--;
        return objH;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        f();
        Object[] objArr = this.f2573d;
        int i4 = this.f2575f;
        int iHashCode = 1;
        for (int i5 = 0; i5 < i4; i5++) {
            Object obj = objArr[this.f2574e + i5];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    public final void i(int i4, int i5) {
        if (i5 > 0) {
            ((AbstractList) this).modCount++;
        }
        b bVar = this.f2576g;
        if (bVar != null) {
            bVar.i(i4, i5);
        } else {
            c cVar = c.f2578g;
            this.f2577h.i(i4, i5);
        }
        this.f2575f -= i5;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        f();
        for (int i4 = 0; i4 < this.f2575f; i4++) {
            if (j2.i.a(this.f2573d[this.f2574e + i4], obj)) {
                return i4;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        f();
        return this.f2575f == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    public final int j(int i4, int i5, Collection collection, boolean z3) {
        int iJ;
        b bVar = this.f2576g;
        if (bVar != null) {
            iJ = bVar.j(i4, i5, collection, z3);
        } else {
            c cVar = c.f2578g;
            iJ = this.f2577h.j(i4, i5, collection, z3);
        }
        if (iJ > 0) {
            ((AbstractList) this).modCount++;
        }
        this.f2575f -= iJ;
        return iJ;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        f();
        for (int i4 = this.f2575f - 1; i4 >= 0; i4--) {
            if (j2.i.a(this.f2573d[this.f2574e + i4], obj)) {
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
        g();
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
        g();
        f();
        return j(this.f2574e, this.f2575f, collection, false) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        j2.i.e(collection, "elements");
        g();
        f();
        return j(this.f2574e, this.f2575f, collection, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i4, Object obj) {
        g();
        f();
        int i5 = this.f2575f;
        if (i4 < 0 || i4 >= i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        Object[] objArr = this.f2573d;
        int i6 = this.f2574e;
        Object obj2 = objArr[i6 + i4];
        objArr[i6 + i4] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i4, int i5) {
        a.a.g(i4, i5, this.f2575f);
        return new b(this.f2573d, this.f2574e + i4, i5 - i4, this, this.f2577h);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        j2.i.e(objArr, "array");
        f();
        int length = objArr.length;
        int i4 = this.f2575f;
        int i5 = this.f2574e;
        if (length < i4) {
            Object[] objArrCopyOfRange = Arrays.copyOfRange(this.f2573d, i5, i4 + i5, objArr.getClass());
            j2.i.d(objArrCopyOfRange, "copyOfRange(...)");
            return objArrCopyOfRange;
        }
        v1.i.V(this.f2573d, objArr, 0, i5, i4 + i5);
        int i6 = this.f2575f;
        if (i6 < objArr.length) {
            objArr[i6] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        f();
        return a.a.c(this.f2573d, this.f2574e, this.f2575f, this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i4) {
        f();
        int i5 = this.f2575f;
        if (i4 < 0 || i4 > i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        return new a(this, i4);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i4, Object obj) {
        g();
        f();
        int i5 = this.f2575f;
        if (i4 >= 0 && i4 <= i5) {
            e(this.f2574e + i4, obj);
            return;
        }
        throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i4, Collection collection) {
        j2.i.e(collection, "elements");
        g();
        f();
        int i5 = this.f2575f;
        if (i4 >= 0 && i4 <= i5) {
            int size = collection.size();
            d(this.f2574e + i4, collection, size);
            return size > 0;
        }
        throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        f();
        Object[] objArr = this.f2573d;
        int i4 = this.f2575f;
        int i5 = this.f2574e;
        return v1.i.Y(objArr, i5, i4 + i5);
    }
}
