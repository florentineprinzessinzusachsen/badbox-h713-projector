package v1;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class t extends a.a {
    public static int J(int i4) {
        if (i4 < 0) {
            return i4;
        }
        if (i4 < 3) {
            return i4 + 1;
        }
        if (i4 < 1073741824) {
            return (int) ((i4 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static Map K(u1.f... fVarArr) {
        if (fVarArr.length <= 0) {
            return q.f2518d;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(J(fVarArr.length));
        L(linkedHashMap, fVarArr);
        return linkedHashMap;
    }

    public static final void L(LinkedHashMap linkedHashMap, u1.f[] fVarArr) {
        for (u1.f fVar : fVarArr) {
            linkedHashMap.put(fVar.f2294d, fVar.f2295e);
        }
    }
}
