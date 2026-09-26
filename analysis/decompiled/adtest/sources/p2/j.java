package p2;

import d0.l0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class j extends l3.h {
    public static String r0(String str) {
        j2.i.e(str, "<this>");
        return o2.e.J(new o2.i(new o2.f(1, str), new l0.b(18, "    ")), "\n");
    }

    public static String s0(String str) {
        List listS;
        Comparable comparable;
        String strSubstring;
        j2.i.e(str, "<this>");
        d dVar = new d(str);
        if (dVar.hasNext()) {
            Object next = dVar.next();
            if (dVar.hasNext()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                while (dVar.hasNext()) {
                    arrayList.add(dVar.next());
                }
                listS = arrayList;
            } else {
                listS = l3.h.S(next);
            }
        } else {
            listS = v1.p.f2517d;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listS) {
            if (!i.H0((String) obj)) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(v1.l.u0(arrayList2));
        int size = arrayList2.size();
        int i4 = 0;
        int i5 = 0;
        while (i5 < size) {
            Object obj2 = arrayList2.get(i5);
            i5++;
            String str2 = (String) obj2;
            int length = str2.length();
            int length2 = 0;
            while (true) {
                if (length2 >= length) {
                    length2 = -1;
                    break;
                }
                if (!l0.B(str2.charAt(length2))) {
                    break;
                }
                length2++;
            }
            if (length2 == -1) {
                length2 = str2.length();
            }
            arrayList3.add(Integer.valueOf(length2));
        }
        Iterator it = arrayList3.iterator();
        if (it.hasNext()) {
            comparable = (Comparable) it.next();
            while (it.hasNext()) {
                Comparable comparable2 = (Comparable) it.next();
                if (comparable.compareTo(comparable2) > 0) {
                    comparable = comparable2;
                }
            }
        } else {
            comparable = null;
        }
        Integer num = (Integer) comparable;
        int iIntValue = num != null ? num.intValue() : 0;
        int length3 = str.length();
        listS.size();
        int iR0 = v1.k.r0(listS);
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : listS) {
            int i6 = i4 + 1;
            if (i4 < 0) {
                throw new ArithmeticException("Index overflow has happened.");
            }
            String str3 = (String) obj3;
            if ((i4 == 0 || i4 == iR0) && i.H0(str3)) {
                strSubstring = null;
            } else {
                j2.i.e(str3, "<this>");
                if (iIntValue < 0) {
                    throw new IllegalArgumentException(a1.c.d(iIntValue, "Requested character count ", " is less than zero.").toString());
                }
                int length4 = str3.length();
                if (iIntValue <= length4) {
                    length4 = iIntValue;
                }
                strSubstring = str3.substring(length4);
                j2.i.d(strSubstring, "substring(...)");
            }
            if (strSubstring != null) {
                arrayList4.add(strSubstring);
            }
            i4 = i6;
        }
        StringBuilder sb = new StringBuilder(length3);
        v1.j.w0(arrayList4, sb, "\n", "", "", "...", null);
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0072 A[PHI: r8
      0x0072: PHI (r8v1 java.lang.String) = (r8v0 java.lang.String), (r8v2 java.lang.String) binds: [B:24:0x0070, B:39:0x00a2] A[DONT_GENERATE, DONT_INLINE]] */
    public static String t0(String str) {
        List listS;
        j2.i.e(str, "<this>");
        if (i.H0("|")) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.");
        }
        d dVar = new d(str);
        if (dVar.hasNext()) {
            Object next = dVar.next();
            if (dVar.hasNext()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                while (dVar.hasNext()) {
                    arrayList.add(dVar.next());
                }
                listS = arrayList;
            } else {
                listS = l3.h.S(next);
            }
        } else {
            listS = v1.p.f2517d;
        }
        int length = str.length();
        listS.size();
        int iR0 = v1.k.r0(listS);
        ArrayList arrayList2 = new ArrayList();
        int i4 = 0;
        for (Object obj : listS) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                throw new ArithmeticException("Index overflow has happened.");
            }
            String str2 = (String) obj;
            String strSubstring = null;
            if ((i4 == 0 || i4 == iR0) && i.H0(str2)) {
                str2 = strSubstring;
            } else {
                int length2 = str2.length();
                int i6 = 0;
                while (true) {
                    if (i6 >= length2) {
                        i6 = -1;
                        break;
                    }
                    if (!l0.B(str2.charAt(i6))) {
                        break;
                    }
                    i6++;
                }
                if (i6 != -1 && p.y0(str2, "|", i6, false)) {
                    strSubstring = str2.substring("|".length() + i6);
                    j2.i.d(strSubstring, "substring(...)");
                }
                if (strSubstring != null) {
                    str2 = strSubstring;
                }
            }
            if (str2 != null) {
                arrayList2.add(str2);
            }
            i4 = i5;
        }
        StringBuilder sb = new StringBuilder(length);
        v1.j.w0(arrayList2, sb, "\n", "", "", "...", null);
        return sb.toString();
    }
}
