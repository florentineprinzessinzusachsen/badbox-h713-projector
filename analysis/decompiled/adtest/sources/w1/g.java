package w1;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g extends v1.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2604d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f f2605e;

    public /* synthetic */ g(f fVar, int i4) {
        this.f2604d = i4;
        this.f2605e = fVar;
    }

    @Override // v1.f
    public final int a() {
        switch (this.f2604d) {
            case 0:
                break;
        }
        return this.f2605e.f2599l;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        switch (this.f2604d) {
            case 0:
                j2.i.e((Map.Entry) obj, "element");
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        switch (this.f2604d) {
            case 0:
                j2.i.e(collection, "elements");
                throw new UnsupportedOperationException();
            default:
                j2.i.e(collection, "elements");
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.f2604d) {
            case 0:
                this.f2605e.clear();
                break;
            default:
                this.f2605e.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.f2604d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                return this.f2605e.e((Map.Entry) obj);
            default:
                return this.f2605e.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        switch (this.f2604d) {
            case 0:
                j2.i.e(collection, "elements");
                return this.f2605e.d(collection);
            default:
                return super.containsAll(collection);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        switch (this.f2604d) {
            case 0:
                break;
        }
        return this.f2605e.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.f2604d) {
            case 0:
                f fVar = this.f2605e;
                fVar.getClass();
                return new d(fVar, 0);
            default:
                f fVar2 = this.f2605e;
                fVar2.getClass();
                return new d(fVar2, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.f2604d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                f fVar = this.f2605e;
                fVar.getClass();
                fVar.b();
                int iG = fVar.g(entry.getKey());
                if (iG < 0) {
                    return false;
                }
                Object[] objArr = fVar.f2592e;
                j2.i.b(objArr);
                if (!j2.i.a(objArr[iG], entry.getValue())) {
                    return false;
                }
                fVar.k(iG);
                return true;
            default:
                f fVar2 = this.f2605e;
                fVar2.b();
                int iG2 = fVar2.g(obj);
                if (iG2 < 0) {
                    return false;
                }
                fVar2.k(iG2);
                return true;
        }
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        switch (this.f2604d) {
            case 0:
                j2.i.e(collection, "elements");
                this.f2605e.b();
                break;
            default:
                j2.i.e(collection, "elements");
                this.f2605e.b();
                break;
        }
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        switch (this.f2604d) {
            case 0:
                j2.i.e(collection, "elements");
                this.f2605e.b();
                break;
            default:
                j2.i.e(collection, "elements");
                this.f2605e.b();
                break;
        }
        return super.retainAll(collection);
    }
}
