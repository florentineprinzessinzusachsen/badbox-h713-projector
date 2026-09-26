package p2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class p extends o {
    public static boolean u0(String str, String str2) {
        j2.i.e(str, "<this>");
        j2.i.e(str2, "suffix");
        return str.endsWith(str2);
    }

    public static boolean v0(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equalsIgnoreCase(str2);
    }

    public static boolean w0(String str, int i4, String str2, int i5, int i6, boolean z3) {
        j2.i.e(str, "<this>");
        j2.i.e(str2, "other");
        return !z3 ? str.regionMatches(i4, str2, i5, i6) : str.regionMatches(z3, i4, str2, i5, i6);
    }

    public static String x0(String str, String str2, String str3) {
        j2.i.e(str, "<this>");
        int iD0 = i.D0(str, str2, 0, false);
        if (iD0 < 0) {
            return str;
        }
        int length = str2.length();
        int i4 = length >= 1 ? length : 1;
        int length2 = str3.length() + (str.length() - length);
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        int i5 = 0;
        do {
            sb.append((CharSequence) str, i5, iD0);
            sb.append(str3);
            i5 = iD0 + length;
            if (iD0 >= str.length()) {
                break;
            }
            iD0 = i.D0(str, str2, iD0 + i4, false);
        } while (iD0 > 0);
        sb.append((CharSequence) str, i5, str.length());
        String string = sb.toString();
        j2.i.d(string, "toString(...)");
        return string;
    }

    public static boolean y0(String str, String str2, int i4, boolean z3) {
        j2.i.e(str, "<this>");
        return !z3 ? str.startsWith(str2, i4) : w0(str, i4, str2, 0, str2.length(), z3);
    }

    public static boolean z0(String str, String str2, boolean z3) {
        j2.i.e(str, "<this>");
        j2.i.e(str2, "prefix");
        return !z3 ? str.startsWith(str2) : w0(str, 0, str2, 0, str2.length(), z3);
    }
}
