package v1;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class k extends l3.h {
    public static int r0(List list) {
        j2.i.e(list, "<this>");
        return list.size() - 1;
    }

    public static ArrayList s0(Object... objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static ArrayList t0(Object... objArr) {
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new g(objArr, true));
    }
}
