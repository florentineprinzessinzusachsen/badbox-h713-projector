package o2;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Iterator f1570d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ i f1571e;

    public h(i iVar) {
        this.f1571e = iVar;
        this.f1570d = iVar.f1572a.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f1570d.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.f1571e.f1573b.h(this.f1570d.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
