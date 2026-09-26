package e;

import j2.i;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Iterator, Map.Entry {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f559d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f560e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f561f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ e f562g;

    public c(e eVar) {
        this.f562g = eVar;
        this.f559d = eVar.f566f - 1;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.f561f) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        int i4 = this.f560e;
        e eVar = this.f562g;
        return i.a(key, eVar.h(i4)) && i.a(entry.getValue(), eVar.l(this.f560e));
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        if (this.f561f) {
            return this.f562g.h(this.f560e);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.f561f) {
            return this.f562g.l(this.f560e);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f560e < this.f559d;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.f561f) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        int i4 = this.f560e;
        e eVar = this.f562g;
        Object objH = eVar.h(i4);
        Object objL = eVar.l(this.f560e);
        return (objH == null ? 0 : objH.hashCode()) ^ (objL != null ? objL.hashCode() : 0);
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f560e++;
        this.f561f = true;
        return this;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f561f) {
            throw new IllegalStateException();
        }
        this.f562g.j(this.f560e);
        this.f560e--;
        this.f559d--;
        this.f561f = false;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (this.f561f) {
            return this.f562g.k(this.f560e, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
