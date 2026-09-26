package d;

import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;

/* JADX INFO: compiled from: Cookie.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {
    private static final Pattern j = Pattern.compile("(\\d{2,4})[^\\d]*");
    private static final Pattern k = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern l = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern m = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f4636a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f4637b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f4638c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f4639d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f4640e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final boolean f4641f;
    private final boolean g;
    private final boolean h;
    private final boolean i;

    private l(String str, String str2, long j2, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f4636a = str;
        this.f4637b = str2;
        this.f4638c = j2;
        this.f4639d = str3;
        this.f4640e = str4;
        this.f4641f = z;
        this.g = z2;
        this.i = z3;
        this.h = z4;
    }

    public String a() {
        return this.f4636a;
    }

    public String b() {
        return this.f4637b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return lVar.f4636a.equals(this.f4636a) && lVar.f4637b.equals(this.f4637b) && lVar.f4639d.equals(this.f4639d) && lVar.f4640e.equals(this.f4640e) && lVar.f4638c == this.f4638c && lVar.f4641f == this.f4641f && lVar.g == this.g && lVar.h == this.h && lVar.i == this.i;
    }

    public int hashCode() {
        int iHashCode = (((((((527 + this.f4636a.hashCode()) * 31) + this.f4637b.hashCode()) * 31) + this.f4639d.hashCode()) * 31) + this.f4640e.hashCode()) * 31;
        long j2 = this.f4638c;
        return ((((((((iHashCode + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (!this.f4641f ? 1 : 0)) * 31) + (!this.g ? 1 : 0)) * 31) + (!this.h ? 1 : 0)) * 31) + (!this.i ? 1 : 0);
    }

    public String toString() {
        return a(false);
    }

    private static boolean a(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        return str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !d.h0.c.d(str);
    }

    private static long b(String str) {
        try {
            long j2 = Long.parseLong(str);
            if (j2 <= 0) {
                return Long.MIN_VALUE;
            }
            return j2;
        } catch (NumberFormatException e2) {
            if (str.matches("-?\\d+")) {
                return str.startsWith("-") ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
            throw e2;
        }
    }

    public static l a(t tVar, String str) {
        return a(System.currentTimeMillis(), tVar, str);
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00c7 A[PHI: r0
      0x00c7: PHI (r0v15 long) = (r0v2 long), (r0v5 long) binds: [B:42:0x00c5, B:53:0x00e8] A[DONT_GENERATE, DONT_INLINE]] */
    static l a(long j2, t tVar, String str) {
        long j3;
        l lVar;
        String str2;
        String strSubstring;
        int length = str.length();
        char c2 = ';';
        int iA = d.h0.c.a(str, 0, length, ';');
        char c3 = '=';
        int iA2 = d.h0.c.a(str, 0, iA, '=');
        if (iA2 == iA) {
            return null;
        }
        String strD = d.h0.c.d(str, 0, iA2);
        if (strD.isEmpty() || d.h0.c.c(strD) != -1) {
            return null;
        }
        String strD2 = d.h0.c.d(str, iA2 + 1, iA);
        if (d.h0.c.c(strD2) != -1) {
            return null;
        }
        int i = iA + 1;
        String strA = null;
        String str3 = null;
        long jB = -1;
        long jA = 253402300799999L;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = false;
        while (i < length) {
            int iA3 = d.h0.c.a(str, i, length, c2);
            int iA4 = d.h0.c.a(str, i, iA3, c3);
            String strD3 = d.h0.c.d(str, i, iA4);
            String strD4 = iA4 < iA3 ? d.h0.c.d(str, iA4 + 1, iA3) : "";
            if (strD3.equalsIgnoreCase("expires")) {
                try {
                    jA = a(strD4, 0, strD4.length());
                    z4 = true;
                } catch (NumberFormatException | IllegalArgumentException unused) {
                }
            } else if (strD3.equalsIgnoreCase("max-age")) {
                jB = b(strD4);
                z4 = true;
            } else if (strD3.equalsIgnoreCase("domain")) {
                strA = a(strD4);
                z3 = false;
            } else if (strD3.equalsIgnoreCase(Config.FEED_LIST_ITEM_PATH)) {
                str3 = strD4;
            } else if (strD3.equalsIgnoreCase("secure")) {
                z = true;
            } else if (strD3.equalsIgnoreCase("httponly")) {
                z2 = true;
            }
            i = iA3 + 1;
            c2 = ';';
            c3 = '=';
        }
        long j4 = Long.MIN_VALUE;
        if (jB == Long.MIN_VALUE) {
            j3 = j4;
        } else if (jB != -1) {
            j4 = j2 + (jB <= 9223372036854775L ? jB * 1000 : Long.MAX_VALUE);
            if (j4 < j2 || j4 > 253402300799999L) {
                j3 = 253402300799999L;
            } else {
                j3 = j4;
            }
        } else {
            j3 = jA;
        }
        String strG = tVar.g();
        if (strA == null) {
            str2 = strG;
            lVar = null;
        } else {
            if (!a(strG, strA)) {
                return null;
            }
            lVar = null;
            str2 = strA;
        }
        if (strG.length() != str2.length() && PublicSuffixDatabase.a().a(str2) == null) {
            return lVar;
        }
        String str4 = str3;
        if (str4 == null || !str4.startsWith("/")) {
            String strC = tVar.c();
            int iLastIndexOf = strC.lastIndexOf(47);
            strSubstring = iLastIndexOf != 0 ? strC.substring(0, iLastIndexOf) : "/";
        } else {
            strSubstring = str4;
        }
        return new l(strD, strD2, j3, str2, strSubstring, z, z2, z3, z4);
    }

    private static long a(String str, int i, int i2) {
        int iA = a(str, i, i2, false);
        Matcher matcher = m.matcher(str);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int iIndexOf = -1;
        int i6 = -1;
        int i7 = -1;
        while (iA < i2) {
            int iA2 = a(str, iA + 1, i2, true);
            matcher.region(iA, iA2);
            if (i4 == -1 && matcher.usePattern(m).matches()) {
                int i8 = Integer.parseInt(matcher.group(1));
                int i9 = Integer.parseInt(matcher.group(2));
                i7 = Integer.parseInt(matcher.group(3));
                i6 = i9;
                i4 = i8;
            } else if (i5 == -1 && matcher.usePattern(l).matches()) {
                i5 = Integer.parseInt(matcher.group(1));
            } else if (iIndexOf == -1 && matcher.usePattern(k).matches()) {
                iIndexOf = k.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            } else if (i3 == -1 && matcher.usePattern(j).matches()) {
                i3 = Integer.parseInt(matcher.group(1));
            }
            iA = a(str, iA2 + 1, i2, false);
        }
        if (i3 >= 70 && i3 <= 99) {
            i3 += 1900;
        }
        if (i3 >= 0 && i3 <= 69) {
            i3 += 2000;
        }
        if (i3 < 1601) {
            throw new IllegalArgumentException();
        }
        if (iIndexOf == -1) {
            throw new IllegalArgumentException();
        }
        if (i5 < 1 || i5 > 31) {
            throw new IllegalArgumentException();
        }
        if (i4 < 0 || i4 > 23) {
            throw new IllegalArgumentException();
        }
        if (i6 < 0 || i6 > 59) {
            throw new IllegalArgumentException();
        }
        if (i7 >= 0 && i7 <= 59) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(d.h0.c.n);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.set(1, i3);
            gregorianCalendar.set(2, iIndexOf - 1);
            gregorianCalendar.set(5, i5);
            gregorianCalendar.set(11, i4);
            gregorianCalendar.set(12, i6);
            gregorianCalendar.set(13, i7);
            gregorianCalendar.set(14, 0);
            return gregorianCalendar.getTimeInMillis();
        }
        throw new IllegalArgumentException();
    }

    private static int a(String str, int i, int i2, boolean z) {
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || (cCharAt >= '0' && cCharAt <= '9') || ((cCharAt >= 'a' && cCharAt <= 'z') || ((cCharAt >= 'A' && cCharAt <= 'Z') || cCharAt == ':'))) == (!z)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static String a(String str) {
        if (!str.endsWith(".")) {
            if (str.startsWith(".")) {
                str = str.substring(1);
            }
            String strA = d.h0.c.a(str);
            if (strA != null) {
                return strA;
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    public static List<l> a(t tVar, s sVar) {
        List<String> listB = sVar.b("Set-Cookie");
        int size = listB.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            l lVarA = a(tVar, listB.get(i));
            if (lVarA != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lVarA);
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f4636a);
        sb.append('=');
        sb.append(this.f4637b);
        if (this.h) {
            if (this.f4638c == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(d.h0.g.d.a(new Date(this.f4638c)));
            }
        }
        if (!this.i) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.f4639d);
        }
        sb.append("; path=");
        sb.append(this.f4640e);
        if (this.f4641f) {
            sb.append("; secure");
        }
        if (this.g) {
            sb.append("; httponly");
        }
        return sb.toString();
    }
}
