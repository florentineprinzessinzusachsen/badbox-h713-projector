package b3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import u0.p;
import u0.q;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f345d;

    @Override // u0.q
    public Object a() {
        switch (this.f345d) {
            case 1:
                return new p(true);
            case 2:
                return new LinkedHashMap();
            case 3:
                return new TreeMap();
            case 4:
                return new ConcurrentHashMap();
            case 5:
                return new ConcurrentSkipListMap();
            case 6:
                return new ArrayList();
            case 7:
                return new LinkedHashSet();
            case 8:
                return new TreeSet();
            default:
                return new ArrayDeque();
        }
    }
}
