package v1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g implements Collection {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object[] f2510d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f2511e;

    public g(Object[] objArr, boolean z3) {
        this.f2510d = objArr;
        this.f2511e = z3;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        int i4;
        Object[] objArr = this.f2510d;
        j2.i.e(objArr, "<this>");
        if (obj == null) {
            int length = objArr.length;
            i4 = 0;
            while (i4 < length) {
                if (objArr[i4] != null) {
                    i4++;
                }
            }
            i4 = -1;
        } else {
            int length2 = objArr.length;
            for (int i5 = 0; i5 < length2; i5++) {
                if (obj.equals(objArr[i5])) {
                    i4 = i5;
                }
            }
            i4 = -1;
        }
        return i4 >= 0;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        j2.i.e(collection, "elements");
        if (collection.isEmpty()) {
            return true;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.f2510d.length == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new j2.a(0, this.f2510d);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final int size() {
        return this.f2510d.length;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        j2.i.e(objArr, "array");
        return j2.i.j(this, objArr);
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        Object[] objArr = this.f2510d;
        if (this.f2511e && objArr.getClass().equals(Object[].class)) {
            return objArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length, Object[].class);
        j2.i.d(objArrCopyOf, "copyOf(...)");
        return objArrCopyOf;
    }
}
