package o2;

import i2.l;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1572a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l f1573b;

    public i(c cVar, l lVar) {
        this.f1572a = cVar;
        this.f1573b = lVar;
    }

    @Override // o2.c
    public final Iterator iterator() {
        return new h(this);
    }
}
