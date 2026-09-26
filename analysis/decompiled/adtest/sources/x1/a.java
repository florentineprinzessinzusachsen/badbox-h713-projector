package x1;

import j2.i;
import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Comparator {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f2672b = new a(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f2673c = new a(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2674a;

    public /* synthetic */ a(int i4) {
        this.f2674a = i4;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f2674a) {
            case 0:
                Comparable comparable = (Comparable) obj;
                Comparable comparable2 = (Comparable) obj2;
                i.e(comparable, "a");
                i.e(comparable2, "b");
                return comparable.compareTo(comparable2);
            default:
                Comparable comparable3 = (Comparable) obj;
                Comparable comparable4 = (Comparable) obj2;
                i.e(comparable3, "a");
                i.e(comparable4, "b");
                return comparable4.compareTo(comparable3);
        }
    }

    @Override // java.util.Comparator
    public final Comparator reversed() {
        switch (this.f2674a) {
            case 0:
                return f2673c;
            default:
                return f2672b;
        }
    }
}
