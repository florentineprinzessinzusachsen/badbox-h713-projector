package a3;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f200a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f203d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList f206g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f207h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f201b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f202c = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f204e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f205f = v1.k.t0("");

    public static ArrayList d(String str) {
        ArrayList arrayList = new ArrayList();
        int i4 = 0;
        while (i4 <= str.length()) {
            int iE0 = p2.i.E0(str, '&', i4, 4);
            if (iE0 == -1) {
                iE0 = str.length();
            }
            int iE1 = p2.i.E0(str, '=', i4, 4);
            if (iE1 == -1 || iE1 > iE0) {
                String strSubstring = str.substring(i4, iE0);
                j2.i.d(strSubstring, "substring(...)");
                arrayList.add(strSubstring);
                arrayList.add(null);
            } else {
                String strSubstring2 = str.substring(i4, iE1);
                j2.i.d(strSubstring2, "substring(...)");
                arrayList.add(strSubstring2);
                String strSubstring3 = str.substring(iE1 + 1, iE0);
                j2.i.d(strSubstring3, "substring(...)");
                arrayList.add(strSubstring3);
            }
            i4 = iE0 + 1;
        }
        return arrayList;
    }

    public final t a() {
        ArrayList arrayList;
        String str = this.f200a;
        if (str == null) {
            throw new IllegalStateException("scheme == null");
        }
        String strC = p3.a.c(this.f201b, 0, 0, 7);
        String strC2 = p3.a.c(this.f202c, 0, 0, 7);
        String str2 = this.f203d;
        if (str2 == null) {
            throw new IllegalStateException("host == null");
        }
        int iB = b();
        ArrayList arrayList2 = this.f205f;
        ArrayList arrayList3 = new ArrayList(v1.l.u0(arrayList2));
        int size = arrayList2.size();
        int i4 = 0;
        while (i4 < size) {
            Object obj = arrayList2.get(i4);
            i4++;
            arrayList3.add(p3.a.c((String) obj, 0, 0, 7));
        }
        ArrayList arrayList4 = this.f206g;
        if (arrayList4 != null) {
            arrayList = new ArrayList(v1.l.u0(arrayList4));
            int size2 = arrayList4.size();
            int i5 = 0;
            while (i5 < size2) {
                Object obj2 = arrayList4.get(i5);
                i5++;
                String str3 = (String) obj2;
                arrayList.add(str3 != null ? p3.a.c(str3, 0, 0, 3) : null);
            }
        } else {
            arrayList = null;
        }
        String str4 = this.f207h;
        return new t(str, strC, strC2, str2, iB, arrayList, str4 != null ? p3.a.c(str4, 0, 0, 7) : null, toString());
    }

    public final int b() {
        int i4 = this.f204e;
        if (i4 != -1) {
            return i4;
        }
        String str = this.f200a;
        j2.i.b(str);
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    /* JADX WARN: Code duplicated, block: B:4:0x0025  */
    public final void c(t tVar, String str) {
        int i4;
        int iC;
        int i5;
        char cCharAt;
        byte[] bArr = b3.d.f343a;
        int iG = b3.d.g(str, 0, str.length());
        int iH = b3.d.h(str, iG, str.length());
        if (iH - iG < 2) {
            i4 = -1;
            break;
        }
        char cCharAt2 = str.charAt(iG);
        if ((j2.i.f(cCharAt2, 97) < 0 || j2.i.f(cCharAt2, 122) > 0) && (j2.i.f(cCharAt2, 65) < 0 || j2.i.f(cCharAt2, 90) > 0)) {
            i4 = -1;
            break;
        }
        i4 = iG + 1;
        while (true) {
            if (i4 < iH) {
                char cCharAt3 = str.charAt(i4);
                if (('a' > cCharAt3 || cCharAt3 >= '{') && (('A' > cCharAt3 || cCharAt3 >= '[') && !(('0' <= cCharAt3 && cCharAt3 < ':') || cCharAt3 == '+' || cCharAt3 == '-' || cCharAt3 == '.'))) {
                    if (cCharAt3 != ':') {
                        break;
                    } else {
                        break;
                    }
                }
                i4++;
            }
            i4 = -1;
            break;
        }
        if (i4 == -1) {
            if (tVar == null) {
                throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no scheme was found for " + (str.length() > 6 ? p2.i.R0(6, str).concat("...") : str));
            }
            this.f200a = tVar.f208a;
        } else if (p2.p.y0(str, "https:", iG, true)) {
            this.f200a = "https";
            iG += 6;
        } else {
            if (!p2.p.y0(str, "http:", iG, true)) {
                StringBuilder sb = new StringBuilder("Expected URL scheme 'http' or 'https' but was '");
                String strSubstring = str.substring(0, i4);
                j2.i.d(strSubstring, "substring(...)");
                sb.append(strSubstring);
                sb.append('\'');
                throw new IllegalArgumentException(sb.toString());
            }
            this.f200a = "http";
            iG += 5;
        }
        int i6 = 0;
        for (int i7 = iG; i7 < iH && ((cCharAt = str.charAt(i7)) == '/' || cCharAt == '\\'); i7++) {
            i6++;
        }
        ArrayList arrayList = this.f205f;
        char c4 = '#';
        if (i6 >= 2 || tVar == null || !j2.i.a(tVar.f208a, this.f200a)) {
            int i8 = iG + i6;
            boolean z3 = false;
            boolean z4 = false;
            while (true) {
                iC = b3.d.c(i8, iH, str, "@/\\?#");
                byte bCharAt = iC != iH ? str.charAt(iC) : (byte) -1;
                if (bCharAt == -1 || bCharAt == c4 || bCharAt == 47 || bCharAt == 92 || bCharAt == 63) {
                    break;
                }
                if (bCharAt == 64) {
                    if (z3) {
                        this.f202c += "%40" + p3.a.a(str, i8, iC, " \"':;<=>@[]^`{}|/\\?#", 112);
                        z3 = z3;
                    } else {
                        boolean z5 = z3;
                        int iD = b3.d.d(str, ':', i8, iC);
                        String strA = p3.a.a(str, i8, iD, " \"':;<=>@[]^`{}|/\\?#", 112);
                        if (z4) {
                            strA = this.f201b + "%40" + strA;
                        }
                        this.f201b = strA;
                        if (iD != iC) {
                            this.f202c = p3.a.a(str, iD + 1, iC, " \"':;<=>@[]^`{}|/\\?#", 112);
                            z3 = true;
                        } else {
                            z3 = z5;
                        }
                        z4 = true;
                    }
                    i8 = iC + 1;
                    c4 = '#';
                }
            }
            int i9 = i8;
            while (true) {
                if (i9 >= iC) {
                    i9 = iC;
                    break;
                }
                char cCharAt4 = str.charAt(i9);
                if (cCharAt4 == ':') {
                    break;
                }
                if (cCharAt4 == '[') {
                    do {
                        i9++;
                        if (i9 >= iC) {
                            break;
                        }
                    } while (str.charAt(i9) != ']');
                }
                i9++;
            }
            int i10 = i9 + 1;
            if (i10 < iC) {
                this.f203d = b3.c.b(p3.a.c(str, i8, i9, 4));
                try {
                    i5 = Integer.parseInt(p3.a.a(str, i10, iC, "", 120));
                    if (1 > i5 || i5 >= 65536) {
                        i5 = -1;
                    }
                } catch (NumberFormatException unused) {
                }
                this.f204e = i5;
                if (i5 == -1) {
                    StringBuilder sb2 = new StringBuilder("Invalid URL port: \"");
                    String strSubstring2 = str.substring(i10, iC);
                    j2.i.d(strSubstring2, "substring(...)");
                    sb2.append(strSubstring2);
                    sb2.append('\"');
                    throw new IllegalArgumentException(sb2.toString().toString());
                }
            } else {
                this.f203d = b3.c.b(p3.a.c(str, i8, i9, 4));
                String str2 = this.f200a;
                j2.i.b(str2);
                this.f204e = str2.equals("http") ? 80 : str2.equals("https") ? 443 : -1;
            }
            if (this.f203d == null) {
                StringBuilder sb3 = new StringBuilder("Invalid URL host: \"");
                String strSubstring3 = str.substring(i8, i9);
                j2.i.d(strSubstring3, "substring(...)");
                sb3.append(strSubstring3);
                sb3.append('\"');
                throw new IllegalArgumentException(sb3.toString().toString());
            }
            iG = iC;
        } else {
            this.f201b = tVar.e();
            this.f202c = tVar.a();
            this.f203d = tVar.f211d;
            this.f204e = tVar.f212e;
            arrayList.clear();
            arrayList.addAll(tVar.c());
            if (iG == iH || str.charAt(iG) == '#') {
                String strD = tVar.d();
                this.f206g = strD != null ? d(p3.a.a(strD, 0, 0, " \"'<>#", 83)) : null;
            }
        }
        int iC2 = b3.d.c(iG, iH, str, "?#");
        if (iG != iC2) {
            char cCharAt5 = str.charAt(iG);
            if (cCharAt5 == '/' || cCharAt5 == '\\') {
                arrayList.clear();
                arrayList.add("");
                iG++;
            } else {
                arrayList.set(arrayList.size() - 1, "");
            }
            while (iG < iC2) {
                int iC3 = b3.d.c(iG, iC2, str, "/\\");
                boolean z6 = iC3 < iC2;
                String strA2 = p3.a.a(str, iG, iC3, " \"<>^`{}|/\\?#", 112);
                if (!strA2.equals(".") && !strA2.equalsIgnoreCase("%2e")) {
                    if (!strA2.equals("..") && !strA2.equalsIgnoreCase("%2e.") && !strA2.equalsIgnoreCase(".%2e") && !strA2.equalsIgnoreCase("%2e%2e")) {
                        if (((CharSequence) arrayList.get(arrayList.size() - 1)).length() == 0) {
                            arrayList.set(arrayList.size() - 1, strA2);
                        } else {
                            arrayList.add(strA2);
                        }
                        if (z6) {
                            arrayList.add("");
                        }
                    } else if (((String) arrayList.remove(arrayList.size() - 1)).length() != 0 || arrayList.isEmpty()) {
                        arrayList.add("");
                    } else {
                        arrayList.set(arrayList.size() - 1, "");
                    }
                }
                iG = z6 ? iC3 + 1 : iC3;
            }
        }
        if (iC2 < iH && str.charAt(iC2) == '?') {
            int iD2 = b3.d.d(str, '#', iC2, iH);
            this.f206g = d(p3.a.a(str, iC2 + 1, iD2, " \"'<>#", 80));
            iC2 = iD2;
        }
        if (iC2 >= iH || str.charAt(iC2) != '#') {
            return;
        }
        this.f207h = p3.a.a(str, iC2 + 1, iH, "", 48);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x008b  */
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.f200a;
        if (str != null) {
            sb.append(str);
            sb.append("://");
        } else {
            sb.append("//");
        }
        if (this.f201b.length() > 0 || this.f202c.length() > 0) {
            sb.append(this.f201b);
            if (this.f202c.length() > 0) {
                sb.append(':');
                sb.append(this.f202c);
            }
            sb.append('@');
        }
        String str2 = this.f203d;
        if (str2 != null) {
            if (p2.i.A0(str2, ':')) {
                sb.append('[');
                sb.append(this.f203d);
                sb.append(']');
            } else {
                sb.append(this.f203d);
            }
        }
        int i4 = -1;
        if (this.f204e != -1 || this.f200a != null) {
            int iB = b();
            String str3 = this.f200a;
            if (str3 == null) {
                sb.append(':');
                sb.append(iB);
            } else {
                if (str3.equals("http")) {
                    i4 = 80;
                } else if (str3.equals("https")) {
                    i4 = 443;
                }
                if (iB != i4) {
                    sb.append(':');
                    sb.append(iB);
                }
            }
        }
        ArrayList arrayList = this.f205f;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            sb.append('/');
            sb.append((String) arrayList.get(i5));
        }
        if (this.f206g != null) {
            sb.append('?');
            ArrayList arrayList2 = this.f206g;
            j2.i.b(arrayList2);
            b.b(arrayList2, sb);
        }
        if (this.f207h != null) {
            sb.append('#');
            sb.append(this.f207h);
        }
        return sb.toString();
    }
}
