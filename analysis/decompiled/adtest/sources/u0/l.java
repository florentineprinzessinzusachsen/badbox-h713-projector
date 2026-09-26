package u0;

import java.util.Comparator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l implements Comparator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2257a;

    public /* synthetic */ l(int i4) {
        this.f2257a = i4;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f2257a) {
            case 0:
                return ((Comparable) obj).compareTo((Comparable) obj2);
            case 1:
                return l3.h.o((Integer) ((Map.Entry) obj).getKey(), (Integer) ((Map.Entry) obj2).getKey());
            case 2:
                return l3.h.o((Integer) ((Map.Entry) obj).getKey(), (Integer) ((Map.Entry) obj2).getKey());
            case 3:
                return l3.h.o(((v.h) obj).f2400a, ((v.h) obj2).f2400a);
            default:
                return l3.h.o(((v.j) obj).f2412a, ((v.j) obj2).f2412a);
        }
    }
}
