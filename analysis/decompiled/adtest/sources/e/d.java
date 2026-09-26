package e;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements Collection {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ e f563d;

    public d(e eVar) {
        this.f563d = eVar;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final void clear() {
        this.f563d.clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.f563d.a(obj) >= 0;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
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
        return this.f563d.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new a(this.f563d, 1);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        e eVar = this.f563d;
        int iA = eVar.a(obj);
        if (iA < 0) {
            return false;
        }
        eVar.j(iA);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        e eVar = this.f563d;
        int i4 = eVar.f566f;
        int i5 = 0;
        boolean z3 = false;
        while (i5 < i4) {
            if (collection.contains(eVar.l(i5))) {
                eVar.j(i5);
                i5--;
                i4--;
                z3 = true;
            }
            i5++;
        }
        return z3;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        e eVar = this.f563d;
        int i4 = eVar.f566f;
        int i5 = 0;
        boolean z3 = false;
        while (i5 < i4) {
            if (!collection.contains(eVar.l(i5))) {
                eVar.j(i5);
                i5--;
                i4--;
                z3 = true;
            }
            i5++;
        }
        return z3;
    }

    @Override // java.util.Collection
    public final int size() {
        return this.f563d.f566f;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        e eVar = this.f563d;
        int i4 = eVar.f566f;
        Object[] objArr = new Object[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            objArr[i5] = eVar.l(i5);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        e eVar = this.f563d;
        int i4 = eVar.f566f;
        if (objArr.length < i4) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i4);
        }
        for (int i5 = 0; i5 < i4; i5++) {
            objArr[i5] = eVar.l(i5);
        }
        if (objArr.length > i4) {
            objArr[i4] = null;
        }
        return objArr;
    }
}
