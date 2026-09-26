package s0;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p extends q implements Iterable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f2133d = new ArrayList();

    @Override // s0.q
    public final String b() {
        ArrayList arrayList = this.f2133d;
        int size = arrayList.size();
        if (size == 1) {
            return ((q) arrayList.get(0)).b();
        }
        throw new IllegalStateException(a1.c.c(size, "Array must have size 1, but has size "));
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof p) && ((p) obj).f2133d.equals(this.f2133d);
        }
        return true;
    }

    public final int hashCode() {
        return this.f2133d.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.f2133d.iterator();
    }
}
