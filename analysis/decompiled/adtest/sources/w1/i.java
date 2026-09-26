package w1;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i extends v1.f implements Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final i f2607e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f2608d;

    static {
        f fVar = f.f2590q;
        f2607e = new i(f.f2590q);
    }

    public i(f fVar) {
        j2.i.e(fVar, "backing");
        this.f2608d = fVar;
    }

    @Override // v1.f
    public final int a() {
        return this.f2608d.f2599l;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        return this.f2608d.a(obj) >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        j2.i.e(collection, "elements");
        this.f2608d.b();
        return super.addAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.f2608d.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f2608d.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.f2608d.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        f fVar = this.f2608d;
        fVar.getClass();
        return new d(fVar, 1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        f fVar = this.f2608d;
        fVar.b();
        int iG = fVar.g(obj);
        if (iG < 0) {
            return false;
        }
        fVar.k(iG);
        return true;
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        j2.i.e(collection, "elements");
        this.f2608d.b();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        j2.i.e(collection, "elements");
        this.f2608d.b();
        return super.retainAll(collection);
    }

    public i() {
        this(new f());
    }
}
