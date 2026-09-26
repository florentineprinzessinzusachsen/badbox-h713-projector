package o2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import v1.p;

/* JADX INFO: loaded from: classes.dex */
public abstract class e extends a.a {
    public static String J(c cVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        int i4 = 0;
        for (Object obj : cVar) {
            i4++;
            if (i4 > 1) {
                sb.append((CharSequence) str);
            }
            l3.h.c(sb, obj, null);
        }
        sb.append((CharSequence) "");
        return sb.toString();
    }

    public static List K(c cVar) {
        Iterator it = cVar.iterator();
        if (!it.hasNext()) {
            return p.f2517d;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return l3.h.S(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
