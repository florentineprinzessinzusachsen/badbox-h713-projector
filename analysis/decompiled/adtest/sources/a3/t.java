package a3;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f208a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f209b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f210c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f211d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f212e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f213f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f214g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f215h;

    public t(String str, String str2, String str3, String str4, int i4, ArrayList arrayList, String str5, String str6) {
        this.f208a = str;
        this.f209b = str2;
        this.f210c = str3;
        this.f211d = str4;
        this.f212e = i4;
        this.f213f = arrayList;
        this.f214g = str5;
        this.f215h = str6;
    }

    public final String a() {
        if (this.f210c.length() == 0) {
            return "";
        }
        int length = this.f208a.length() + 3;
        String str = this.f215h;
        String strSubstring = str.substring(p2.i.E0(str, ':', length, 4) + 1, p2.i.E0(str, '@', 0, 6));
        j2.i.d(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final String b() {
        int length = this.f208a.length() + 3;
        String str = this.f215h;
        int iE0 = p2.i.E0(str, '/', length, 4);
        String strSubstring = str.substring(iE0, b3.d.c(iE0, str.length(), str, "?#"));
        j2.i.d(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final ArrayList c() {
        int length = this.f208a.length() + 3;
        String str = this.f215h;
        int iE0 = p2.i.E0(str, '/', length, 4);
        int iC = b3.d.c(iE0, str.length(), str, "?#");
        ArrayList arrayList = new ArrayList();
        while (iE0 < iC) {
            int i4 = iE0 + 1;
            int iD = b3.d.d(str, '/', i4, iC);
            String strSubstring = str.substring(i4, iD);
            j2.i.d(strSubstring, "substring(...)");
            arrayList.add(strSubstring);
            iE0 = iD;
        }
        return arrayList;
    }

    public final String d() {
        if (this.f213f == null) {
            return null;
        }
        String str = this.f215h;
        int iE0 = p2.i.E0(str, '?', 0, 6) + 1;
        String strSubstring = str.substring(iE0, b3.d.d(str, '#', iE0, str.length()));
        j2.i.d(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final String e() {
        if (this.f209b.length() == 0) {
            return "";
        }
        int length = this.f208a.length() + 3;
        String str = this.f215h;
        String strSubstring = str.substring(length, b3.d.c(length, str.length(), str, ":@"));
        j2.i.d(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof t) && j2.i.a(((t) obj).f215h, this.f215h);
    }

    public final String f() {
        s sVar;
        try {
            sVar = new s();
            sVar.c(this, "/...");
        } catch (IllegalArgumentException unused) {
            sVar = null;
        }
        j2.i.b(sVar);
        sVar.f201b = p3.a.a("", 0, 0, " \"':;<=>@[]^`{}|/\\?#", 123);
        sVar.f202c = p3.a.a("", 0, 0, " \"':;<=>@[]^`{}|/\\?#", 123);
        return sVar.a().f215h;
    }

    public final URI g() {
        String strSubstring;
        String strReplaceAll;
        s sVar = new s();
        String str = this.f208a;
        sVar.f200a = str;
        sVar.f201b = e();
        sVar.f202c = a();
        sVar.f203d = this.f211d;
        j2.i.e(str, "scheme");
        int i4 = str.equals("http") ? 80 : str.equals("https") ? 443 : -1;
        int i5 = this.f212e;
        sVar.f204e = i5 != i4 ? i5 : -1;
        ArrayList arrayList = sVar.f205f;
        arrayList.clear();
        arrayList.addAll(c());
        String strD = d();
        sVar.f206g = strD != null ? s.d(p3.a.a(strD, 0, 0, " \"'<>#", 83)) : null;
        if (this.f214g == null) {
            strSubstring = null;
        } else {
            String str2 = this.f215h;
            strSubstring = str2.substring(p2.i.E0(str2, '#', 0, 6) + 1);
            j2.i.d(strSubstring, "substring(...)");
        }
        sVar.f207h = strSubstring;
        String str3 = sVar.f203d;
        if (str3 != null) {
            Pattern patternCompile = Pattern.compile("[\"<>^`{|}]");
            j2.i.d(patternCompile, "compile(...)");
            strReplaceAll = patternCompile.matcher(str3).replaceAll("");
            j2.i.d(strReplaceAll, "replaceAll(...)");
        } else {
            strReplaceAll = null;
        }
        sVar.f203d = strReplaceAll;
        int size = arrayList.size();
        for (int i6 = 0; i6 < size; i6++) {
            arrayList.set(i6, p3.a.a((String) arrayList.get(i6), 0, 0, "[]", 99));
        }
        ArrayList arrayList2 = sVar.f206g;
        if (arrayList2 != null) {
            int size2 = arrayList2.size();
            for (int i7 = 0; i7 < size2; i7++) {
                String str4 = (String) arrayList2.get(i7);
                arrayList2.set(i7, str4 != null ? p3.a.a(str4, 0, 0, "\\^`{|}", 67) : null);
            }
        }
        String str5 = sVar.f207h;
        sVar.f207h = str5 != null ? p3.a.a(str5, 0, 0, " \"#<>\\^`{|}", 35) : null;
        String string = sVar.toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e4) {
            try {
                Pattern patternCompile2 = Pattern.compile("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]");
                j2.i.d(patternCompile2, "compile(...)");
                j2.i.e(string, "input");
                String strReplaceAll2 = patternCompile2.matcher(string).replaceAll("");
                j2.i.d(strReplaceAll2, "replaceAll(...)");
                URI uriCreate = URI.create(strReplaceAll2);
                j2.i.b(uriCreate);
                return uriCreate;
            } catch (Exception unused) {
                throw new RuntimeException(e4);
            }
        }
    }

    public final int hashCode() {
        return this.f215h.hashCode();
    }

    public final String toString() {
        return this.f215h;
    }
}
