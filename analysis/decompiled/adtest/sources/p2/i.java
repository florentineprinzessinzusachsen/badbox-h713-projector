package p2;

import d0.l0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends p {
    public static boolean A0(CharSequence charSequence, char c4) {
        j2.i.e(charSequence, "<this>");
        return E0(charSequence, c4, 0, 2) >= 0;
    }

    public static boolean B0(CharSequence charSequence, String str) {
        j2.i.e(charSequence, "<this>");
        j2.i.e(str, "other");
        return F0(charSequence, str, 0, 2) >= 0;
    }

    public static final int C0(CharSequence charSequence) {
        j2.i.e(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int D0(CharSequence charSequence, String str, int i4, boolean z3) {
        j2.i.e(charSequence, "<this>");
        j2.i.e(str, "string");
        if (!z3 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(str, i4);
        }
        int length = charSequence.length();
        int i5 = i4 < 0 ? 0 : i4;
        int length2 = charSequence.length();
        if (length > length2) {
            length = length2;
        }
        m2.c cVar = new m2.c(i5, length, 1);
        boolean z4 = charSequence instanceof String;
        int i6 = cVar.f1445f;
        int i7 = cVar.f1444e;
        int i8 = cVar.f1443d;
        if (!z4 || !(str instanceof String)) {
            if ((i6 <= 0 || i8 > i7) && (i6 >= 0 || i7 > i8)) {
                return -1;
            }
            while (!K0(str, 0, charSequence, i8, str.length(), z3)) {
                if (i8 == i7) {
                    return -1;
                }
                i8 += i6;
            }
            return i8;
        }
        if ((i6 <= 0 || i8 > i7) && (i6 >= 0 || i7 > i8)) {
            return -1;
        }
        int i9 = i8;
        while (!p.w0(str, 0, (String) charSequence, i9, str.length(), z3)) {
            if (i9 == i7) {
                return -1;
            }
            i9 += i6;
        }
        return i9;
    }

    public static int E0(CharSequence charSequence, char c4, int i4, int i5) {
        if ((i5 & 2) != 0) {
            i4 = 0;
        }
        j2.i.e(charSequence, "<this>");
        return !(charSequence instanceof String) ? G0(charSequence, new char[]{c4}, i4, false) : ((String) charSequence).indexOf(c4, i4);
    }

    public static /* synthetic */ int F0(CharSequence charSequence, String str, int i4, int i5) {
        if ((i5 & 2) != 0) {
            i4 = 0;
        }
        return D0(charSequence, str, i4, false);
    }

    public static final int G0(CharSequence charSequence, char[] cArr, int i4, boolean z3) {
        j2.i.e(charSequence, "<this>");
        if (!z3 && cArr.length == 1 && (charSequence instanceof String)) {
            int length = cArr.length;
            if (length == 0) {
                throw new NoSuchElementException("Array is empty.");
            }
            if (length != 1) {
                throw new IllegalArgumentException("Array has more than one element.");
            }
            return ((String) charSequence).indexOf(cArr[0], i4);
        }
        if (i4 < 0) {
            i4 = 0;
        }
        int iC0 = C0(charSequence);
        if (i4 > iC0) {
            return -1;
        }
        while (true) {
            char cCharAt = charSequence.charAt(i4);
            for (char c4 : cArr) {
                if (l0.q(c4, cCharAt, z3)) {
                    return i4;
                }
            }
            if (i4 == iC0) {
                return -1;
            }
            i4++;
        }
    }

    public static boolean H0(CharSequence charSequence) {
        j2.i.e(charSequence, "<this>");
        for (int i4 = 0; i4 < charSequence.length(); i4++) {
            if (!l0.B(charSequence.charAt(i4))) {
                return false;
            }
        }
        return true;
    }

    public static int I0(String str, char c4, int i4, int i5) {
        if ((i5 & 2) != 0) {
            i4 = C0(str);
        }
        return str.lastIndexOf(c4, i4);
    }

    public static String J0(String str) {
        CharSequence charSequenceSubSequence;
        if (8 <= str.length()) {
            charSequenceSubSequence = str.subSequence(0, str.length());
        } else {
            StringBuilder sb = new StringBuilder(8);
            int length = 8 - str.length();
            int i4 = 1;
            if (1 <= length) {
                while (true) {
                    sb.append('0');
                    if (i4 == length) {
                        break;
                    }
                    i4++;
                }
            }
            sb.append((CharSequence) str);
            charSequenceSubSequence = sb;
        }
        return charSequenceSubSequence.toString();
    }

    public static final boolean K0(CharSequence charSequence, int i4, CharSequence charSequence2, int i5, int i6, boolean z3) {
        j2.i.e(charSequence, "<this>");
        j2.i.e(charSequence2, "other");
        if (i5 < 0 || i4 < 0 || i4 > charSequence.length() - i6 || i5 > charSequence2.length() - i6) {
            return false;
        }
        for (int i7 = 0; i7 < i6; i7++) {
            if (!l0.q(charSequence.charAt(i4 + i7), charSequence2.charAt(i5 + i7), z3)) {
                return false;
            }
        }
        return true;
    }

    public static String L0(String str, String str2) {
        if (!p.z0(str, str2, false)) {
            return str;
        }
        String strSubstring = str.substring(str2.length());
        j2.i.d(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static final void M0(int i4) {
        if (i4 < 0) {
            throw new IllegalArgumentException(a1.c.c(i4, "Limit must be non-negative, but was ").toString());
        }
    }

    public static final List N0(CharSequence charSequence, String str, int i4) {
        M0(i4);
        int iD0 = D0(charSequence, str, 0, false);
        if (iD0 == -1 || i4 == 1) {
            return l3.h.S(charSequence.toString());
        }
        boolean z3 = i4 > 0;
        int i5 = 10;
        if (z3 && i4 <= 10) {
            i5 = i4;
        }
        ArrayList arrayList = new ArrayList(i5);
        int length = 0;
        do {
            arrayList.add(charSequence.subSequence(length, iD0).toString());
            length = str.length() + iD0;
            if (z3 && arrayList.size() == i4 - 1) {
                break;
            }
            iD0 = D0(charSequence, str, length, false);
        } while (iD0 != -1);
        arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
        return arrayList;
    }

    public static List O0(CharSequence charSequence, String[] strArr, int i4) {
        int i5 = (i4 & 4) != 0 ? 0 : 2;
        j2.i.e(charSequence, "<this>");
        if (strArr.length == 1) {
            String str = strArr[0];
            if (str.length() != 0) {
                return N0(charSequence, str, i5);
            }
        }
        M0(i5);
        o2.g gVar = new o2.g(new c(charSequence, i5, new q(0, v1.i.R(strArr))));
        ArrayList arrayList = new ArrayList(v1.l.u0(gVar));
        Iterator it = gVar.iterator();
        while (true) {
            b bVar = (b) it;
            if (!bVar.hasNext()) {
                return arrayList;
            }
            m2.c cVar = (m2.c) bVar.next();
            j2.i.e(cVar, "range");
            arrayList.add(charSequence.subSequence(cVar.f1443d, cVar.f1444e + 1).toString());
        }
    }

    public static List P0(String str, char[] cArr) {
        j2.i.e(str, "<this>");
        if (cArr.length == 1) {
            return N0(str, String.valueOf(cArr[0]), 0);
        }
        M0(0);
        o2.g gVar = new o2.g(new c(str, 0, new q(1, cArr)));
        ArrayList arrayList = new ArrayList(v1.l.u0(gVar));
        Iterator it = gVar.iterator();
        while (true) {
            b bVar = (b) it;
            if (!bVar.hasNext()) {
                return arrayList;
            }
            m2.c cVar = (m2.c) bVar.next();
            j2.i.e(cVar, "range");
            arrayList.add(str.subSequence(cVar.f1443d, cVar.f1444e + 1).toString());
        }
    }

    public static String Q0(String str, String str2) {
        j2.i.e(str2, "delimiter");
        int iF0 = F0(str, str2, 0, 6);
        if (iF0 == -1) {
            return str;
        }
        String strSubstring = str.substring(str2.length() + iF0, str.length());
        j2.i.d(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String R0(int i4, String str) {
        j2.i.e(str, "<this>");
        if (i4 < 0) {
            throw new IllegalArgumentException(a1.c.d(i4, "Requested character count ", " is less than zero.").toString());
        }
        int length = str.length();
        if (i4 > length) {
            i4 = length;
        }
        String strSubstring = str.substring(0, i4);
        j2.i.d(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static CharSequence S0(String str) {
        j2.i.e(str, "<this>");
        int length = str.length() - 1;
        int i4 = 0;
        boolean z3 = false;
        while (i4 <= length) {
            boolean zB = l0.B(str.charAt(!z3 ? i4 : length));
            if (z3) {
                if (!zB) {
                    break;
                }
                length--;
            } else if (zB) {
                i4++;
            } else {
                z3 = true;
            }
        }
        return str.subSequence(i4, length + 1);
    }
}
