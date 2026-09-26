package b3;

import j2.i;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import q3.h;
import q3.m;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f343a = new byte[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m f344b;

    static {
        int i4;
        h hVar = h.f1823g;
        h[] hVarArr = {a1.a.l("efbbbf"), a1.a.l("feff"), a1.a.l("fffe0000"), a1.a.l("fffe"), a1.a.l("0000feff")};
        ArrayList arrayList = new ArrayList(new v1.g(hVarArr, false));
        if (arrayList.size() > 1) {
            Collections.sort(arrayList);
        }
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i5 = 0; i5 < size; i5++) {
            arrayList2.add(-1);
        }
        int i6 = 0;
        int i7 = 0;
        while (i6 < 5) {
            h hVar2 = hVarArr[i6];
            int i8 = i7 + 1;
            int size2 = arrayList.size();
            int size3 = arrayList.size();
            if (size2 < 0) {
                throw new IllegalArgumentException(a1.c.d(size2, "fromIndex (0) is greater than toIndex (", ")."));
            }
            if (size2 > size3) {
                throw new IndexOutOfBoundsException("toIndex (" + size2 + ") is greater than size (" + size3 + ").");
            }
            int i9 = size2 - 1;
            int i10 = 0;
            while (true) {
                if (i10 > i9) {
                    i4 = -(i10 + 1);
                    break;
                }
                i4 = (i10 + i9) >>> 1;
                int iO = l3.h.o((Comparable) arrayList.get(i4), hVar2);
                if (iO < 0) {
                    i10 = i4 + 1;
                } else if (iO <= 0) {
                    break;
                } else {
                    i9 = i4 - 1;
                }
            }
            arrayList2.set(i4, Integer.valueOf(i7));
            i6++;
            i7 = i8;
        }
        if (((h) arrayList.get(0)).a() <= 0) {
            throw new IllegalArgumentException("the empty byte string is not a supported option");
        }
        int i11 = 0;
        while (i11 < arrayList.size()) {
            h hVar3 = (h) arrayList.get(i11);
            int i12 = i11 + 1;
            int i13 = i12;
            while (i13 < arrayList.size()) {
                h hVar4 = (h) arrayList.get(i13);
                hVar4.getClass();
                i.e(hVar3, "prefix");
                if (!hVar4.f(hVar3, hVar3.a())) {
                    break;
                }
                if (hVar4.a() == hVar3.a()) {
                    throw new IllegalArgumentException(("duplicate option: " + hVar4).toString());
                }
                if (((Number) arrayList2.get(i13)).intValue() > ((Number) arrayList2.get(i11)).intValue()) {
                    arrayList.remove(i13);
                    ((Number) arrayList2.remove(i13)).intValue();
                } else {
                    i13++;
                }
            }
            i11 = i12;
        }
        q3.e eVar = new q3.e();
        l3.h.e(0L, eVar, 0, arrayList, 0, arrayList.size(), arrayList2);
        int i14 = (int) (eVar.f1822e / ((long) 4));
        int[] iArr = new int[i14];
        for (int i15 = 0; i15 < i14; i15++) {
            iArr[i15] = eVar.readInt();
        }
        Object[] objArrCopyOf = Arrays.copyOf(hVarArr, 5);
        i.d(objArrCopyOf, "copyOf(...)");
        f344b = new m((h[]) objArrCopyOf, iArr);
    }

    public static final void a(long j4, long j5, long j6) {
        if ((j5 | j6) < 0 || j5 > j4 || j4 - j5 < j6) {
            throw new ArrayIndexOutOfBoundsException("length=" + j4 + ", offset=" + j5 + ", count=" + j5);
        }
    }

    public static final void b(Closeable closeable) {
        i.e(closeable, "<this>");
        try {
            closeable.close();
        } catch (RuntimeException e4) {
            throw e4;
        } catch (Exception unused) {
        }
    }

    public static final int c(int i4, int i5, String str, String str2) {
        i.e(str, "<this>");
        while (i4 < i5) {
            if (p2.i.A0(str2, str.charAt(i4))) {
                return i4;
            }
            i4++;
        }
        return i5;
    }

    public static final int d(String str, char c4, int i4, int i5) {
        i.e(str, "<this>");
        while (i4 < i5) {
            if (str.charAt(i4) == c4) {
                return i4;
            }
            i4++;
        }
        return i5;
    }

    public static final boolean e(String[] strArr, String[] strArr2, Comparator comparator) {
        i.e(strArr, "<this>");
        if (strArr.length != 0 && strArr2 != null && strArr2.length != 0) {
            for (String str : strArr) {
                for (String str2 : strArr2) {
                    if (comparator.compare(str, str2) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final int f(String str) {
        int length = str.length();
        for (int i4 = 0; i4 < length; i4++) {
            char cCharAt = str.charAt(i4);
            if (i.f(cCharAt, 31) <= 0 || i.f(cCharAt, 127) >= 0) {
                return i4;
            }
        }
        return -1;
    }

    public static final int g(String str, int i4, int i5) {
        while (i4 < i5) {
            char cCharAt = str.charAt(i4);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i4;
            }
            i4++;
        }
        return i5;
    }

    public static final int h(String str, int i4, int i5) {
        int i6 = i5 - 1;
        if (i4 <= i6) {
            while (true) {
                char cCharAt = str.charAt(i6);
                if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                    return i6 + 1;
                }
                if (i6 == i4) {
                    break;
                }
                i6--;
            }
        }
        return i4;
    }

    public static final String[] i(String[] strArr, String[] strArr2, Comparator comparator) {
        i.e(strArr, "<this>");
        i.e(strArr2, "other");
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            for (String str2 : strArr2) {
                if (comparator.compare(str, str2) == 0) {
                    arrayList.add(str);
                    break;
                }
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static final boolean j(String str) {
        i.e(str, "name");
        return str.equalsIgnoreCase("Authorization") || str.equalsIgnoreCase("Cookie") || str.equalsIgnoreCase("Proxy-Authorization") || str.equalsIgnoreCase("Set-Cookie");
    }

    public static final int k(char c4) {
        if ('0' <= c4 && c4 < ':') {
            return c4 - '0';
        }
        if ('a' <= c4 && c4 < 'g') {
            return c4 - 'W';
        }
        if ('A' > c4 || c4 >= 'G') {
            return -1;
        }
        return c4 - '7';
    }

    public static final int l(q3.g gVar) {
        i.e(gVar, "<this>");
        return (gVar.readByte() & 255) | ((gVar.readByte() & 255) << 16) | ((gVar.readByte() & 255) << 8);
    }

    public static final int m(int i4, String str) {
        if (str == null) {
            return i4;
        }
        try {
            long j4 = Long.parseLong(str);
            if (j4 > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (j4 < 0) {
                return 0;
            }
            return (int) j4;
        } catch (NumberFormatException unused) {
            return i4;
        }
    }

    public static final String n(String str, int i4, int i5) {
        int iG = g(str, i4, i5);
        String strSubstring = str.substring(iG, h(str, iG, i5));
        i.d(strSubstring, "substring(...)");
        return strSubstring;
    }
}
