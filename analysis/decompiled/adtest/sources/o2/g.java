package o2;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g implements Iterable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ p2.c f1569d;

    public g(p2.c cVar) {
        this.f1569d = cVar;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new p2.b(this.f1569d);
    }
}
