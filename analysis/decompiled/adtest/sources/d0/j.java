package d0;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j f464b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f465a;

    static {
        j jVar = new j(new LinkedHashMap());
        l3.h.n0(jVar);
        f464b = jVar;
    }

    public j(j jVar) {
        j2.i.e(jVar, "other");
        this.f465a = new HashMap(jVar.f465a);
    }

    public final boolean a(String str) {
        Object obj = this.f465a.get(str);
        return obj != null && String.class.isAssignableFrom(obj.getClass());
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0059  */
    public final boolean equals(Object obj) {
        boolean zEquals;
        if (this != obj) {
            if (obj != null && j.class.equals(obj.getClass())) {
                HashMap map = ((j) obj).f465a;
                HashMap map2 = this.f465a;
                Set<String> setKeySet = map2.keySet();
                if (j2.i.a(setKeySet, map.keySet())) {
                    for (String str : setKeySet) {
                        Object obj2 = map2.get(str);
                        Object obj3 = map.get(str);
                        if (obj2 == null || obj3 == null) {
                            zEquals = obj2 == obj3;
                        } else if (obj2 instanceof Object[]) {
                            Object[] objArr = (Object[]) obj2;
                            if (obj3 instanceof Object[]) {
                                zEquals = v1.i.S(objArr, (Object[]) obj3);
                            } else {
                                zEquals = obj2.equals(obj3);
                            }
                        } else {
                            zEquals = obj2.equals(obj3);
                        }
                        if (!zEquals) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int iHashCode = 0;
        for (Map.Entry entry : this.f465a.entrySet()) {
            Object value = entry.getValue();
            iHashCode += value instanceof Object[] ? Objects.hashCode(entry.getKey()) ^ Arrays.deepHashCode((Object[]) value) : entry.hashCode();
        }
        return iHashCode * 31;
    }

    public final String toString() {
        return "Data {" + v1.j.y0(this.f465a.entrySet(), null, null, null, new h(0), 31) + "}";
    }

    public j(LinkedHashMap linkedHashMap) {
        j2.i.e(linkedHashMap, "values");
        this.f465a = new HashMap(linkedHashMap);
    }
}
