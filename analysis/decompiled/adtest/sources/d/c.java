package d;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends a.a implements Iterator {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f396h;

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.f396h) {
            return null;
        }
        this.f396h = false;
        return null;
    }
}
