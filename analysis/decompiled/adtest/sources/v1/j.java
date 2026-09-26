package v1;

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class j extends n {
    public static ArrayList A0(List list, List list2) {
        j2.i.e(list2, "elements");
        ArrayList arrayList = new ArrayList(list2.size() + list.size());
        arrayList.addAll(list);
        arrayList.addAll(list2);
        return arrayList;
    }

    public static Object B0(AbstractList abstractList) {
        j2.i.e(abstractList, "<this>");
        if (abstractList.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return abstractList.remove(k.r0(abstractList));
    }

    public static List C0(Iterable iterable) {
        j2.i.e(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            List listH0 = H0(iterable);
            if (((ArrayList) listH0).size() > 1) {
                Collections.sort(listH0);
            }
            return listH0;
        }
        Collection collection = (Collection) iterable;
        if (collection.size() <= 1) {
            return G0(iterable);
        }
        Object[] array = collection.toArray(new Comparable[0]);
        Comparable[] comparableArr = (Comparable[]) array;
        j2.i.e(comparableArr, "<this>");
        if (comparableArr.length > 1) {
            Arrays.sort(comparableArr);
        }
        return i.R(array);
    }

    public static List D0(Iterable iterable, Comparator comparator) {
        j2.i.e(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            List listH0 = H0(iterable);
            if (((ArrayList) listH0).size() > 1) {
                Collections.sort(listH0, comparator);
            }
            return listH0;
        }
        Collection collection = (Collection) iterable;
        if (collection.size() <= 1) {
            return G0(iterable);
        }
        Object[] array = collection.toArray(new Object[0]);
        j2.i.e(array, "<this>");
        if (array.length > 1) {
            Arrays.sort(array, comparator);
        }
        return i.R(array);
    }

    public static final void E0(Iterable iterable, AbstractCollection abstractCollection) {
        j2.i.e(iterable, "<this>");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            abstractCollection.add(it.next());
        }
    }

    public static int[] F0(ArrayList arrayList) {
        int[] iArr = new int[arrayList.size()];
        int size = arrayList.size();
        int i4 = 0;
        int i5 = 0;
        while (i5 < size) {
            Object obj = arrayList.get(i5);
            i5++;
            iArr[i4] = ((Number) obj).intValue();
            i4++;
        }
        return iArr;
    }

    public static List G0(Iterable iterable) {
        j2.i.e(iterable, "<this>");
        boolean z3 = iterable instanceof Collection;
        p pVar = p.f2517d;
        if (!z3) {
            List listH0 = H0(iterable);
            ArrayList arrayList = (ArrayList) listH0;
            int size = arrayList.size();
            if (size != 0) {
                return size != 1 ? listH0 : l3.h.S(arrayList.get(0));
            }
            return pVar;
        }
        Collection collection = (Collection) iterable;
        int size2 = collection.size();
        if (size2 == 0) {
            return pVar;
        }
        if (size2 != 1) {
            return new ArrayList(collection);
        }
        return l3.h.S(iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next());
    }

    public static final List H0(Iterable iterable) {
        j2.i.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            return new ArrayList((Collection) iterable);
        }
        ArrayList arrayList = new ArrayList();
        E0(iterable, arrayList);
        return arrayList;
    }

    public static Set I0(ArrayList arrayList) {
        int size = arrayList.size();
        if (size == 0) {
            return r.f2519d;
        }
        if (size != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(t.J(arrayList.size()));
            E0(arrayList, linkedHashSet);
            return linkedHashSet;
        }
        Set setSingleton = Collections.singleton(arrayList.get(0));
        j2.i.d(setSingleton, "singleton(...)");
        return setSingleton;
    }

    public static void v0(ArrayList arrayList, Iterable iterable) {
        j2.i.e(iterable, "elements");
        if (iterable instanceof Collection) {
            arrayList.addAll((Collection) iterable);
            return;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
    }

    public static final void w0(Iterable iterable, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, i2.l lVar) {
        j2.i.e(iterable, "<this>");
        sb.append(charSequence2);
        int i4 = 0;
        for (Object obj : iterable) {
            i4++;
            if (i4 > 1) {
                sb.append(charSequence);
            }
            l3.h.c(sb, obj, lVar);
        }
        sb.append(charSequence3);
    }

    public static String y0(Collection collection, String str, String str2, String str3, i2.l lVar, int i4) {
        if ((i4 & 1) != 0) {
            str = ", ";
        }
        String str4 = str;
        String str5 = (i4 & 2) != 0 ? "" : str2;
        String str6 = (i4 & 4) != 0 ? "" : str3;
        if ((i4 & 32) != 0) {
            lVar = null;
        }
        j2.i.e(collection, "<this>");
        StringBuilder sb = new StringBuilder();
        w0(collection, sb, str4, str5, str6, "...", lVar);
        return sb.toString();
    }

    public static Object z0(List list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(k.r0(list));
    }
}
