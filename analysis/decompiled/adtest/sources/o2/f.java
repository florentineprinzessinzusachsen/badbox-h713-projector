package o2;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1567a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1568b;

    public /* synthetic */ f(int i4, Object obj) {
        this.f1567a = i4;
        this.f1568b = obj;
    }

    @Override // o2.c
    public final Iterator iterator() {
        switch (this.f1567a) {
            case 0:
                return (Iterator) this.f1568b;
            case 1:
                return new p2.d((CharSequence) this.f1568b);
            default:
                return ((Iterable) this.f1568b).iterator();
        }
    }
}
