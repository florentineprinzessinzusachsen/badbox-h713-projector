package o2;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1562a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1563b;

    public b(c cVar, int i4) {
        this.f1562a = cVar;
        this.f1563b = i4;
        if (i4 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i4 + '.').toString());
    }

    @Override // o2.c
    public final Iterator iterator() {
        return new j2.a(this);
    }
}
