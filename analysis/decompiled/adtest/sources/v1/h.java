package v1;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h extends e {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Object[] f2512g = new Object[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2513d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f2514e = f2512g;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2515f;

    @Override // v1.e
    public final int a() {
        return this.f2515f;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i4, Object obj) {
        int length;
        int i5 = this.f2515f;
        if (i4 < 0 || i4 > i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        if (i4 == i5) {
            addLast(obj);
            return;
        }
        if (i4 == 0) {
            addFirst(obj);
            return;
        }
        i();
        d(this.f2515f + 1);
        int iH = h(this.f2513d + i4);
        int i6 = this.f2515f;
        if (i4 < ((i6 + 1) >> 1)) {
            if (iH == 0) {
                Object[] objArr = this.f2514e;
                j2.i.e(objArr, "<this>");
                iH = objArr.length;
            }
            int i7 = iH - 1;
            int i8 = this.f2513d;
            if (i8 == 0) {
                Object[] objArr2 = this.f2514e;
                j2.i.e(objArr2, "<this>");
                length = objArr2.length - 1;
            } else {
                length = i8 - 1;
            }
            int i9 = this.f2513d;
            if (i7 >= i9) {
                Object[] objArr3 = this.f2514e;
                objArr3[length] = objArr3[i9];
                i.V(objArr3, objArr3, i9, i9 + 1, i7 + 1);
            } else {
                Object[] objArr4 = this.f2514e;
                i.V(objArr4, objArr4, i9 - 1, i9, objArr4.length);
                Object[] objArr5 = this.f2514e;
                objArr5[objArr5.length - 1] = objArr5[0];
                i.V(objArr5, objArr5, 0, 1, i7 + 1);
            }
            this.f2514e[i7] = obj;
            this.f2513d = length;
        } else {
            int iH2 = h(i6 + this.f2513d);
            if (iH < iH2) {
                Object[] objArr6 = this.f2514e;
                i.V(objArr6, objArr6, iH + 1, iH, iH2);
            } else {
                Object[] objArr7 = this.f2514e;
                i.V(objArr7, objArr7, 1, 0, iH2);
                Object[] objArr8 = this.f2514e;
                objArr8[0] = objArr8[objArr8.length - 1];
                i.V(objArr8, objArr8, iH + 1, iH, objArr8.length - 1);
            }
            this.f2514e[iH] = obj;
        }
        this.f2515f++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i4, Collection collection) {
        j2.i.e(collection, "elements");
        int i5 = this.f2515f;
        if (i4 < 0 || i4 > i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        if (collection.isEmpty()) {
            return false;
        }
        if (i4 == this.f2515f) {
            return addAll(collection);
        }
        i();
        d(collection.size() + this.f2515f);
        int iH = h(this.f2515f + this.f2513d);
        int iH2 = h(this.f2513d + i4);
        int size = collection.size();
        if (i4 >= ((this.f2515f + 1) >> 1)) {
            int i6 = iH2 + size;
            if (iH2 < iH) {
                int i7 = size + iH;
                Object[] objArr = this.f2514e;
                if (i7 <= objArr.length) {
                    i.V(objArr, objArr, i6, iH2, iH);
                } else if (i6 >= objArr.length) {
                    i.V(objArr, objArr, i6 - objArr.length, iH2, iH);
                } else {
                    int length = iH - (i7 - objArr.length);
                    i.V(objArr, objArr, 0, length, iH);
                    Object[] objArr2 = this.f2514e;
                    i.V(objArr2, objArr2, i6, iH2, length);
                }
            } else {
                Object[] objArr3 = this.f2514e;
                i.V(objArr3, objArr3, size, 0, iH);
                Object[] objArr4 = this.f2514e;
                if (i6 >= objArr4.length) {
                    i.V(objArr4, objArr4, i6 - objArr4.length, iH2, objArr4.length);
                } else {
                    i.V(objArr4, objArr4, 0, objArr4.length - size, objArr4.length);
                    Object[] objArr5 = this.f2514e;
                    i.V(objArr5, objArr5, i6, iH2, objArr5.length - size);
                }
            }
            c(iH2, collection);
            return true;
        }
        int i8 = this.f2513d;
        int length2 = i8 - size;
        if (iH2 < i8) {
            Object[] objArr6 = this.f2514e;
            i.V(objArr6, objArr6, length2, i8, objArr6.length);
            if (size >= iH2) {
                Object[] objArr7 = this.f2514e;
                i.V(objArr7, objArr7, objArr7.length - size, 0, iH2);
            } else {
                Object[] objArr8 = this.f2514e;
                i.V(objArr8, objArr8, objArr8.length - size, 0, size);
                Object[] objArr9 = this.f2514e;
                i.V(objArr9, objArr9, 0, size, iH2);
            }
        } else if (length2 >= 0) {
            Object[] objArr10 = this.f2514e;
            i.V(objArr10, objArr10, length2, i8, iH2);
        } else {
            Object[] objArr11 = this.f2514e;
            length2 += objArr11.length;
            int i9 = iH2 - i8;
            int length3 = objArr11.length - length2;
            if (length3 >= i9) {
                i.V(objArr11, objArr11, length2, i8, iH2);
            } else {
                i.V(objArr11, objArr11, length2, i8, i8 + length3);
                Object[] objArr12 = this.f2514e;
                i.V(objArr12, objArr12, 0, this.f2513d + length3, iH2);
            }
        }
        this.f2513d = length2;
        c(f(iH2 - size), collection);
        return true;
    }

    public final void addFirst(Object obj) {
        i();
        d(this.f2515f + 1);
        int length = this.f2513d;
        if (length == 0) {
            Object[] objArr = this.f2514e;
            j2.i.e(objArr, "<this>");
            length = objArr.length;
        }
        int i4 = length - 1;
        this.f2513d = i4;
        this.f2514e[i4] = obj;
        this.f2515f++;
    }

    public final void addLast(Object obj) {
        i();
        d(a() + 1);
        this.f2514e[h(a() + this.f2513d)] = obj;
        this.f2515f = a() + 1;
    }

    @Override // v1.e
    public final Object b(int i4) {
        int i5 = this.f2515f;
        if (i4 < 0 || i4 >= i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        if (i4 == k.r0(this)) {
            return removeLast();
        }
        if (i4 == 0) {
            return removeFirst();
        }
        i();
        int iH = h(this.f2513d + i4);
        Object[] objArr = this.f2514e;
        Object obj = objArr[iH];
        if (i4 < (this.f2515f >> 1)) {
            int i6 = this.f2513d;
            if (iH >= i6) {
                i.V(objArr, objArr, i6 + 1, i6, iH);
            } else {
                i.V(objArr, objArr, 1, 0, iH);
                Object[] objArr2 = this.f2514e;
                objArr2[0] = objArr2[objArr2.length - 1];
                int i7 = this.f2513d;
                i.V(objArr2, objArr2, i7 + 1, i7, objArr2.length - 1);
            }
            Object[] objArr3 = this.f2514e;
            int i8 = this.f2513d;
            objArr3[i8] = null;
            this.f2513d = e(i8);
        } else {
            int iH2 = h(k.r0(this) + this.f2513d);
            if (iH <= iH2) {
                Object[] objArr4 = this.f2514e;
                i.V(objArr4, objArr4, iH, iH + 1, iH2 + 1);
            } else {
                Object[] objArr5 = this.f2514e;
                i.V(objArr5, objArr5, iH, iH + 1, objArr5.length);
                Object[] objArr6 = this.f2514e;
                objArr6[objArr6.length - 1] = objArr6[0];
                i.V(objArr6, objArr6, 0, 1, iH2 + 1);
            }
            this.f2514e[iH2] = null;
        }
        this.f2515f--;
        return obj;
    }

    public final void c(int i4, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.f2514e.length;
        while (i4 < length && it.hasNext()) {
            this.f2514e[i4] = it.next();
            i4++;
        }
        int i5 = this.f2513d;
        for (int i6 = 0; i6 < i5 && it.hasNext(); i6++) {
            this.f2514e[i6] = it.next();
        }
        this.f2515f = collection.size() + this.f2515f;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        if (!isEmpty()) {
            i();
            g(this.f2513d, h(a() + this.f2513d));
        }
        this.f2513d = 0;
        this.f2515f = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void d(int i4) {
        if (i4 < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.f2514e;
        if (i4 <= objArr.length) {
            return;
        }
        if (objArr == f2512g) {
            if (i4 < 10) {
                i4 = 10;
            }
            this.f2514e = new Object[i4];
            return;
        }
        int length = objArr.length;
        int i5 = length + (length >> 1);
        if (i5 - i4 < 0) {
            i5 = i4;
        }
        if (i5 - 2147483639 > 0) {
            i5 = i4 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        Object[] objArr2 = new Object[i5];
        i.V(objArr, objArr2, 0, this.f2513d, objArr.length);
        Object[] objArr3 = this.f2514e;
        int length2 = objArr3.length;
        int i6 = this.f2513d;
        i.V(objArr3, objArr2, length2 - i6, 0, i6);
        this.f2513d = 0;
        this.f2514e = objArr2;
    }

    public final int e(int i4) {
        Object[] objArr = this.f2514e;
        j2.i.e(objArr, "<this>");
        if (i4 == objArr.length - 1) {
            return 0;
        }
        return i4 + 1;
    }

    public final int f(int i4) {
        return i4 < 0 ? i4 + this.f2514e.length : i4;
    }

    public final void g(int i4, int i5) {
        if (i4 < i5) {
            i.Z(this.f2514e, null, i4, i5);
            return;
        }
        Object[] objArr = this.f2514e;
        i.Z(objArr, null, i4, objArr.length);
        i.Z(this.f2514e, null, 0, i5);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i4) {
        int iA = a();
        if (i4 < 0 || i4 >= iA) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, iA, "index: ", ", size: "));
        }
        return this.f2514e[h(this.f2513d + i4)];
    }

    public final int h(int i4) {
        Object[] objArr = this.f2514e;
        return i4 >= objArr.length ? i4 - objArr.length : i4;
    }

    public final void i() {
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i4;
        int iH = h(a() + this.f2513d);
        int length = this.f2513d;
        if (length < iH) {
            while (length < iH) {
                if (j2.i.a(obj, this.f2514e[length])) {
                    i4 = this.f2513d;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (isEmpty() || (length = this.f2513d) < iH) {
            return -1;
        }
        int length2 = this.f2514e.length;
        while (length < length2) {
            if (j2.i.a(obj, this.f2514e[length])) {
                i4 = this.f2513d;
            } else {
                length++;
            }
        }
        for (int i5 = 0; i5 < iH; i5++) {
            if (j2.i.a(obj, this.f2514e[i5])) {
                length = i5 + this.f2514e.length;
                i4 = this.f2513d;
            }
        }
        return -1;
        return length - i4;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return a() == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i4;
        int iH = h(this.f2515f + this.f2513d);
        int i5 = this.f2513d;
        if (i5 < iH) {
            length = iH - 1;
            if (i5 <= length) {
                while (!j2.i.a(obj, this.f2514e[length])) {
                    if (length != i5) {
                        length--;
                    }
                }
                i4 = this.f2513d;
                return length - i4;
            }
            return -1;
        }
        if (!isEmpty() && this.f2513d >= iH) {
            for (int i6 = iH - 1; -1 < i6; i6--) {
                if (j2.i.a(obj, this.f2514e[i6])) {
                    length = i6 + this.f2514e.length;
                    i4 = this.f2513d;
                    return length - i4;
                }
            }
            Object[] objArr = this.f2514e;
            j2.i.e(objArr, "<this>");
            length = objArr.length - 1;
            int i7 = this.f2513d;
            if (i7 <= length) {
                while (!j2.i.a(obj, this.f2514e[length])) {
                    if (length != i7) {
                        length--;
                    }
                }
                i4 = this.f2513d;
                return length - i4;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        b(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int iH;
        j2.i.e(collection, "elements");
        boolean z3 = false;
        z3 = false;
        z3 = false;
        if (!isEmpty() && this.f2514e.length != 0) {
            int iH2 = h(a() + this.f2513d);
            int i4 = this.f2513d;
            if (i4 < iH2) {
                iH = i4;
                while (i4 < iH2) {
                    Object obj = this.f2514e[i4];
                    if (collection.contains(obj)) {
                        z3 = true;
                    } else {
                        this.f2514e[iH] = obj;
                        iH++;
                    }
                    i4++;
                }
                i.Z(this.f2514e, null, iH, iH2);
            } else {
                int length = this.f2514e.length;
                boolean z4 = false;
                int i5 = i4;
                while (i4 < length) {
                    Object[] objArr = this.f2514e;
                    Object obj2 = objArr[i4];
                    objArr[i4] = null;
                    if (collection.contains(obj2)) {
                        z4 = true;
                    } else {
                        this.f2514e[i5] = obj2;
                        i5++;
                    }
                    i4++;
                }
                iH = h(i5);
                for (int i6 = 0; i6 < iH2; i6++) {
                    Object[] objArr2 = this.f2514e;
                    Object obj3 = objArr2[i6];
                    objArr2[i6] = null;
                    if (collection.contains(obj3)) {
                        z4 = true;
                    } else {
                        this.f2514e[iH] = obj3;
                        iH = e(iH);
                    }
                }
                z3 = z4;
            }
            if (z3) {
                i();
                this.f2515f = f(iH - this.f2513d);
            }
        }
        return z3;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        i();
        Object[] objArr = this.f2514e;
        int i4 = this.f2513d;
        Object obj = objArr[i4];
        objArr[i4] = null;
        this.f2513d = e(i4);
        this.f2515f = a() - 1;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        i();
        int iH = h(k.r0(this) + this.f2513d);
        Object[] objArr = this.f2514e;
        Object obj = objArr[iH];
        objArr[iH] = null;
        this.f2515f = a() - 1;
        return obj;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i4, int i5) {
        a.a.g(i4, i5, this.f2515f);
        int i6 = i5 - i4;
        if (i6 == 0) {
            return;
        }
        if (i6 == this.f2515f) {
            clear();
            return;
        }
        if (i6 == 1) {
            b(i4);
            return;
        }
        i();
        if (i4 < this.f2515f - i5) {
            int iH = h(this.f2513d + (i4 - 1));
            int iH2 = h(this.f2513d + (i5 - 1));
            while (i4 > 0) {
                int i7 = iH + 1;
                int iMin = Math.min(i4, Math.min(i7, iH2 + 1));
                Object[] objArr = this.f2514e;
                int i8 = iH2 - iMin;
                int i9 = iH - iMin;
                i.V(objArr, objArr, i8 + 1, i9 + 1, i7);
                iH = f(i9);
                iH2 = f(i8);
                i4 -= iMin;
            }
            int iH3 = h(this.f2513d + i6);
            g(this.f2513d, iH3);
            this.f2513d = iH3;
        } else {
            int iH4 = h(this.f2513d + i5);
            int iH5 = h(this.f2513d + i4);
            int i10 = this.f2515f;
            while (true) {
                i10 -= i5;
                if (i10 <= 0) {
                    break;
                }
                Object[] objArr2 = this.f2514e;
                i5 = Math.min(i10, Math.min(objArr2.length - iH4, objArr2.length - iH5));
                Object[] objArr3 = this.f2514e;
                int i11 = iH4 + i5;
                i.V(objArr3, objArr3, iH5, iH4, i11);
                iH4 = h(i11);
                iH5 = h(iH5 + i5);
            }
            int iH6 = h(this.f2515f + this.f2513d);
            g(f(iH6 - i6), iH6);
        }
        this.f2515f -= i6;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int iH;
        j2.i.e(collection, "elements");
        boolean z3 = false;
        z3 = false;
        z3 = false;
        if (!isEmpty() && this.f2514e.length != 0) {
            int iH2 = h(a() + this.f2513d);
            int i4 = this.f2513d;
            if (i4 < iH2) {
                iH = i4;
                while (i4 < iH2) {
                    Object obj = this.f2514e[i4];
                    if (collection.contains(obj)) {
                        this.f2514e[iH] = obj;
                        iH++;
                    } else {
                        z3 = true;
                    }
                    i4++;
                }
                i.Z(this.f2514e, null, iH, iH2);
            } else {
                int length = this.f2514e.length;
                boolean z4 = false;
                int i5 = i4;
                while (i4 < length) {
                    Object[] objArr = this.f2514e;
                    Object obj2 = objArr[i4];
                    objArr[i4] = null;
                    if (collection.contains(obj2)) {
                        this.f2514e[i5] = obj2;
                        i5++;
                    } else {
                        z4 = true;
                    }
                    i4++;
                }
                iH = h(i5);
                for (int i6 = 0; i6 < iH2; i6++) {
                    Object[] objArr2 = this.f2514e;
                    Object obj3 = objArr2[i6];
                    objArr2[i6] = null;
                    if (collection.contains(obj3)) {
                        this.f2514e[iH] = obj3;
                        iH = e(iH);
                    } else {
                        z4 = true;
                    }
                }
                z3 = z4;
            }
            if (z3) {
                i();
                this.f2515f = f(iH - this.f2513d);
            }
        }
        return z3;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i4, Object obj) {
        int iA = a();
        if (i4 < 0 || i4 >= iA) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, iA, "index: ", ", size: "));
        }
        int iH = h(this.f2513d + i4);
        Object[] objArr = this.f2514e;
        Object obj2 = objArr[iH];
        objArr[iH] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[a()]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        j2.i.e(objArr, "array");
        int length = objArr.length;
        int i4 = this.f2515f;
        if (length < i4) {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), i4);
            j2.i.c(objNewInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
            objArr = (Object[]) objNewInstance;
        }
        int iH = h(this.f2515f + this.f2513d);
        int i5 = this.f2513d;
        if (i5 < iH) {
            i.W(this.f2514e, objArr, i5, iH, 2);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.f2514e;
            i.V(objArr2, objArr, 0, this.f2513d, objArr2.length);
            Object[] objArr3 = this.f2514e;
            i.V(objArr3, objArr, objArr3.length - this.f2513d, 0, iH);
        }
        int i6 = this.f2515f;
        if (i6 < objArr.length) {
            objArr[i6] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        j2.i.e(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        i();
        d(collection.size() + a());
        c(h(a() + this.f2513d), collection);
        return true;
    }
}
