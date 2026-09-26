package d;

import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class d implements Iterable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakHashMap f397d = new WeakHashMap();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        iterator();
        ((d) obj).iterator();
        return true;
    }

    public final int hashCode() {
        iterator();
        return 0;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        b bVar = new b();
        this.f397d.put(bVar, Boolean.FALSE);
        return bVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        iterator();
        sb.append("]");
        return sb.toString();
    }
}
