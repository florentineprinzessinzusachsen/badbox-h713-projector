package d;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: HttpUrl.java */
/* JADX INFO: loaded from: classes.dex */
public final class t {
    private static final char[] j = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f4664a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f4665b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f4666c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final String f4667d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f4668e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final List<String> f4669f;
    private final List<String> g;
    private final String h;
    private final String i;

    t(a aVar) {
        this.f4664a = aVar.f4670a;
        this.f4665b = a(aVar.f4671b, false);
        this.f4666c = a(aVar.f4672c, false);
        this.f4667d = aVar.f4673d;
        this.f4668e = aVar.b();
        this.f4669f = a(aVar.f4675f, false);
        List<String> list = aVar.g;
        this.g = list != null ? a(list, true) : null;
        String str = aVar.h;
        this.h = str != null ? a(str, false) : null;
        this.i = aVar.toString();
    }

    static void a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = list.get(i);
            String str2 = list.get(i + 1);
            if (i > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    public static int c(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    public String b() {
        if (this.f4666c.isEmpty()) {
            return "";
        }
        return this.i.substring(this.i.indexOf(58, this.f4664a.length() + 3) + 1, this.i.indexOf(64));
    }

    public List<String> d() {
        int iIndexOf = this.i.indexOf(47, this.f4664a.length() + 3);
        String str = this.i;
        int iA = d.h0.c.a(str, iIndexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (iIndexOf < iA) {
            int i = iIndexOf + 1;
            int iA2 = d.h0.c.a(this.i, i, iA, '/');
            arrayList.add(this.i.substring(i, iA2));
            iIndexOf = iA2;
        }
        return arrayList;
    }

    public String e() {
        if (this.g == null) {
            return null;
        }
        int iIndexOf = this.i.indexOf(63) + 1;
        String str = this.i;
        return this.i.substring(iIndexOf, d.h0.c.a(str, iIndexOf, str.length(), '#'));
    }

    public boolean equals(Object obj) {
        return (obj instanceof t) && ((t) obj).i.equals(this.i);
    }

    public String f() {
        if (this.f4665b.isEmpty()) {
            return "";
        }
        int length = this.f4664a.length() + 3;
        String str = this.i;
        return this.i.substring(length, d.h0.c.a(str, length, str.length(), ":@"));
    }

    public String g() {
        return this.f4667d;
    }

    public boolean h() {
        return this.f4664a.equals("https");
    }

    public int hashCode() {
        return this.i.hashCode();
    }

    public a i() {
        a aVar = new a();
        aVar.f4670a = this.f4664a;
        aVar.f4671b = f();
        aVar.f4672c = b();
        aVar.f4673d = this.f4667d;
        aVar.f4674e = this.f4668e != c(this.f4664a) ? this.f4668e : -1;
        aVar.f4675f.clear();
        aVar.f4675f.addAll(d());
        aVar.a(e());
        aVar.h = a();
        return aVar;
    }

    public List<String> j() {
        return this.f4669f;
    }

    public int k() {
        return this.f4668e;
    }

    public String l() {
        if (this.g == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        a(sb, this.g);
        return sb.toString();
    }

    public int m() {
        List<String> list = this.g;
        if (list != null) {
            return list.size() / 2;
        }
        return 0;
    }

    public String n() {
        a aVarA = a("/...");
        aVarA.f("");
        aVarA.c("");
        return aVarA.a().toString();
    }

    public String o() {
        return this.f4664a;
    }

    public URI p() {
        a aVarI = i();
        aVarI.c();
        String string = aVarI.toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e2) {
            try {
                return URI.create(string.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e2);
            }
        }
    }

    public String toString() {
        return this.i;
    }

    /* JADX INFO: compiled from: HttpUrl.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f4670a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        String f4673d;
        List<String> g;
        String h;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f4671b = "";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f4672c = "";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f4674e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final List<String> f4675f = new ArrayList();

        public a() {
            this.f4675f.add("");
        }

        private boolean g(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        private boolean h(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        private void i(String str) {
            for (int size = this.g.size() - 2; size >= 0; size -= 2) {
                if (str.equals(this.g.get(size))) {
                    this.g.remove(size + 1);
                    this.g.remove(size);
                    if (this.g.isEmpty()) {
                        this.g = null;
                        return;
                    }
                }
            }
        }

        public a a(int i) {
            if (i > 0 && i <= 65535) {
                this.f4674e = i;
                return this;
            }
            throw new IllegalArgumentException("unexpected port: " + i);
        }

        public a b(String str) {
            if (str == null) {
                throw new NullPointerException("host == null");
            }
            String strA = a(str, 0, str.length());
            if (strA != null) {
                this.f4673d = strA;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + str);
        }

        public a c(String str) {
            if (str == null) {
                throw new NullPointerException("password == null");
            }
            this.f4672c = t.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a d(String str) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (this.g == null) {
                return this;
            }
            i(t.a(str, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, true));
            return this;
        }

        public a e(String str) {
            if (str == null) {
                throw new NullPointerException("scheme == null");
            }
            if (str.equalsIgnoreCase("http")) {
                this.f4670a = "http";
            } else {
                if (!str.equalsIgnoreCase("https")) {
                    throw new IllegalArgumentException("unexpected scheme: " + str);
                }
                this.f4670a = "https";
            }
            return this;
        }

        public a f(String str) {
            if (str == null) {
                throw new NullPointerException("username == null");
            }
            this.f4671b = t.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f4670a);
            sb.append("://");
            if (!this.f4671b.isEmpty() || !this.f4672c.isEmpty()) {
                sb.append(this.f4671b);
                if (!this.f4672c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.f4672c);
                }
                sb.append('@');
            }
            if (this.f4673d.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.f4673d);
                sb.append(']');
            } else {
                sb.append(this.f4673d);
            }
            int iB = b();
            if (iB != t.c(this.f4670a)) {
                sb.append(':');
                sb.append(iB);
            }
            t.b(sb, this.f4675f);
            if (this.g != null) {
                sb.append('?');
                t.a(sb, this.g);
            }
            if (this.h != null) {
                sb.append('#');
                sb.append(this.h);
            }
            return sb.toString();
        }

        private static int f(String str, int i, int i2) {
            int i3 = 0;
            while (i < i2) {
                char cCharAt = str.charAt(i);
                if (cCharAt != '\\' && cCharAt != '/') {
                    break;
                }
                i3++;
                i++;
            }
            return i3;
        }

        public a a(String str) {
            this.g = str != null ? t.f(t.a(str, " \"'<>#", true, false, true, true)) : null;
            return this;
        }

        a c() {
            int size = this.f4675f.size();
            for (int i = 0; i < size; i++) {
                this.f4675f.set(i, t.a(this.f4675f.get(i), "[]", true, true, false, true));
            }
            List<String> list = this.g;
            if (list != null) {
                int size2 = list.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str = this.g.get(i2);
                    if (str != null) {
                        this.g.set(i2, t.a(str, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            String str2 = this.h;
            if (str2 != null) {
                this.h = t.a(str2, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }

        private void d(String str, int i, int i2) {
            if (i == i2) {
                return;
            }
            char cCharAt = str.charAt(i);
            if (cCharAt != '/' && cCharAt != '\\') {
                List<String> list = this.f4675f;
                list.set(list.size() - 1, "");
            } else {
                this.f4675f.clear();
                this.f4675f.add("");
                i++;
            }
            while (true) {
                int i3 = i;
                if (i3 >= i2) {
                    return;
                }
                i = d.h0.c.a(str, i3, i2, "/\\");
                boolean z = i < i2;
                a(str, i3, i, z, true);
                if (z) {
                    i++;
                }
            }
        }

        int b() {
            int i = this.f4674e;
            return i != -1 ? i : t.c(this.f4670a);
        }

        public a a(String str, String str2) {
            if (str != null) {
                if (this.g == null) {
                    this.g = new ArrayList();
                }
                this.g.add(t.a(str, " \"'<>#&=", true, false, true, true));
                this.g.add(str2 != null ? t.a(str2, " \"'<>#&=", true, false, true, true) : null);
                return this;
            }
            throw new NullPointerException("encodedName == null");
        }

        public a b(String str, String str2) {
            if (str != null) {
                if (this.g == null) {
                    this.g = new ArrayList();
                }
                this.g.add(t.a(str, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, true));
                this.g.add(str2 != null ? t.a(str2, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, true) : null);
                return this;
            }
            throw new NullPointerException("name == null");
        }

        private static int e(String str, int i, int i2) {
            if (i2 - i < 2) {
                return -1;
            }
            char cCharAt = str.charAt(i);
            if ((cCharAt >= 'a' && cCharAt <= 'z') || (cCharAt >= 'A' && cCharAt <= 'Z')) {
                while (true) {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                    char cCharAt2 = str.charAt(i);
                    if (cCharAt2 < 'a' || cCharAt2 > 'z') {
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            if (cCharAt2 < '0' || cCharAt2 > '9') {
                                if (cCharAt2 != '+' && cCharAt2 != '-' && cCharAt2 != '.') {
                                    if (cCharAt2 == ':') {
                                        return i;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }

        private void d() {
            List<String> list = this.f4675f;
            if (list.remove(list.size() - 1).isEmpty() && !this.f4675f.isEmpty()) {
                List<String> list2 = this.f4675f;
                list2.set(list2.size() - 1, "");
            } else {
                this.f4675f.add("");
            }
        }

        private static int b(String str, int i, int i2) {
            try {
                int i3 = Integer.parseInt(t.a(str, i, i2, "", false, false, false, true, null));
                if (i3 <= 0 || i3 > 65535) {
                    return -1;
                }
                return i3;
            } catch (NumberFormatException unused) {
            }
        }

        public t a() {
            if (this.f4670a != null) {
                if (this.f4673d != null) {
                    return new t(this);
                }
                throw new IllegalStateException("host == null");
            }
            throw new IllegalStateException("scheme == null");
        }

        private static int c(String str, int i, int i2) {
            while (i < i2) {
                char cCharAt = str.charAt(i);
                if (cCharAt == ':') {
                    return i;
                }
                if (cCharAt == '[') {
                    do {
                        i++;
                        if (i >= i2) {
                            break;
                        }
                    } while (str.charAt(i) != ']');
                }
                i++;
            }
            return i2;
        }

        a a(t tVar, String str) {
            int iA;
            int i;
            int iB = d.h0.c.b(str, 0, str.length());
            int iC = d.h0.c.c(str, iB, str.length());
            int iE = e(str, iB, iC);
            if (iE != -1) {
                if (str.regionMatches(true, iB, "https:", 0, 6)) {
                    this.f4670a = "https";
                    iB += 6;
                } else if (str.regionMatches(true, iB, "http:", 0, 5)) {
                    this.f4670a = "http";
                    iB += 5;
                } else {
                    throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but was '" + str.substring(0, iE) + "'");
                }
            } else if (tVar != null) {
                this.f4670a = tVar.f4664a;
            } else {
                throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
            }
            int iF = f(str, iB, iC);
            char c2 = '?';
            char c3 = '#';
            if (iF < 2 && tVar != null && tVar.f4664a.equals(this.f4670a)) {
                this.f4671b = tVar.f();
                this.f4672c = tVar.b();
                this.f4673d = tVar.f4667d;
                this.f4674e = tVar.f4668e;
                this.f4675f.clear();
                this.f4675f.addAll(tVar.d());
                if (iB == iC || str.charAt(iB) == '#') {
                    a(tVar.e());
                }
            } else {
                int i2 = iB + iF;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    iA = d.h0.c.a(str, i2, iC, "@/\\?#");
                    byte bCharAt = iA != iC ? str.charAt(iA) : (byte) -1;
                    if (bCharAt == -1 || bCharAt == c3 || bCharAt == 47 || bCharAt == 92 || bCharAt == c2) {
                        break;
                    }
                    if (bCharAt == 64) {
                        if (!z) {
                            int iA2 = d.h0.c.a(str, i2, iA, ':');
                            i = iA;
                            String strA = t.a(str, i2, iA2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                            if (z2) {
                                strA = this.f4671b + "%40" + strA;
                            }
                            this.f4671b = strA;
                            if (iA2 != i) {
                                this.f4672c = t.a(str, iA2 + 1, i, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                z = true;
                            }
                            z2 = true;
                        } else {
                            i = iA;
                            this.f4672c += "%40" + t.a(str, i2, i, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                        }
                        i2 = i + 1;
                    }
                    c2 = '?';
                    c3 = '#';
                }
                int iC2 = c(str, i2, iA);
                int i3 = iC2 + 1;
                if (i3 < iA) {
                    this.f4673d = a(str, i2, iC2);
                    this.f4674e = b(str, i3, iA);
                    if (this.f4674e == -1) {
                        throw new IllegalArgumentException("Invalid URL port: \"" + str.substring(i3, iA) + '\"');
                    }
                } else {
                    this.f4673d = a(str, i2, iC2);
                    this.f4674e = t.c(this.f4670a);
                }
                if (this.f4673d == null) {
                    throw new IllegalArgumentException("Invalid URL host: \"" + str.substring(i2, iC2) + '\"');
                }
                iB = iA;
            }
            int iA3 = d.h0.c.a(str, iB, iC, "?#");
            d(str, iB, iA3);
            if (iA3 < iC && str.charAt(iA3) == '?') {
                int iA4 = d.h0.c.a(str, iA3, iC, '#');
                this.g = t.f(t.a(str, iA3 + 1, iA4, " \"'<>#", true, false, true, true, null));
                iA3 = iA4;
            }
            if (iA3 < iC && str.charAt(iA3) == '#') {
                this.h = t.a(str, 1 + iA3, iC, "", true, false, false, false, null);
            }
            return this;
        }

        private void a(String str, int i, int i2, boolean z, boolean z2) {
            String strA = t.a(str, i, i2, " \"<>^`{}|/\\?#", z2, false, false, true, null);
            if (g(strA)) {
                return;
            }
            if (h(strA)) {
                d();
                return;
            }
            List<String> list = this.f4675f;
            if (list.get(list.size() - 1).isEmpty()) {
                List<String> list2 = this.f4675f;
                list2.set(list2.size() - 1, strA);
            } else {
                this.f4675f.add(strA);
            }
            if (z) {
                this.f4675f.add("");
            }
        }

        private static String a(String str, int i, int i2) {
            return d.h0.c.a(t.a(str, i, i2, false));
        }
    }

    public String c() {
        int iIndexOf = this.i.indexOf(47, this.f4664a.length() + 3);
        String str = this.i;
        return this.i.substring(iIndexOf, d.h0.c.a(str, iIndexOf, str.length(), "?#"));
    }

    static void b(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append('/');
            sb.append(list.get(i));
        }
    }

    public static t e(String str) {
        try {
            return d(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    static List<String> f(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length()) {
            int iIndexOf = str.indexOf(38, i);
            if (iIndexOf == -1) {
                iIndexOf = str.length();
            }
            int iIndexOf2 = str.indexOf(61, i);
            if (iIndexOf2 != -1 && iIndexOf2 <= iIndexOf) {
                arrayList.add(str.substring(i, iIndexOf2));
                arrayList.add(str.substring(iIndexOf2 + 1, iIndexOf));
            } else {
                arrayList.add(str.substring(i, iIndexOf));
                arrayList.add(null);
            }
            i = iIndexOf + 1;
        }
        return arrayList;
    }

    public static t d(String str) {
        a aVar = new a();
        aVar.a((t) null, str);
        return aVar.a();
    }

    public String a(int i) {
        List<String> list = this.g;
        if (list != null) {
            return list.get(i * 2);
        }
        throw new IndexOutOfBoundsException();
    }

    public String b(int i) {
        List<String> list = this.g;
        if (list != null) {
            return list.get((i * 2) + 1);
        }
        throw new IndexOutOfBoundsException();
    }

    public String a() {
        if (this.h == null) {
            return null;
        }
        return this.i.substring(this.i.indexOf(35) + 1);
    }

    public t b(String str) {
        a aVarA = a(str);
        if (aVarA != null) {
            return aVarA.a();
        }
        return null;
    }

    public a a(String str) {
        try {
            a aVar = new a();
            aVar.a(this, str);
            return aVar;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    static String a(String str, boolean z) {
        return a(str, 0, str.length(), z);
    }

    private List<String> a(List<String> list, boolean z) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            arrayList.add(str != null ? a(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    static String a(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            char cCharAt = str.charAt(i3);
            if (cCharAt == '%' || (cCharAt == '+' && z)) {
                e.c cVar = new e.c();
                cVar.a(str, i, i3);
                a(cVar, str, i3, i2, z);
                return cVar.o();
            }
        }
        return str.substring(i, i2);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0039  */
    static void a(e.c cVar, String str, int i, int i2, boolean z) {
        int i3;
        while (i < i2) {
            int iCodePointAt = str.codePointAt(i);
            if (iCodePointAt == 37 && (i3 = i + 2) < i2) {
                int iA = d.h0.c.a(str.charAt(i + 1));
                int iA2 = d.h0.c.a(str.charAt(i3));
                if (iA != -1 && iA2 != -1) {
                    cVar.writeByte((iA << 4) + iA2);
                    i = i3;
                } else {
                    cVar.c(iCodePointAt);
                }
            } else if (iCodePointAt == 43 && z) {
                cVar.writeByte(32);
            } else {
                cVar.c(iCodePointAt);
            }
            i += Character.charCount(iCodePointAt);
        }
    }

    static boolean a(String str, int i, int i2) {
        int i3 = i + 2;
        return i3 < i2 && str.charAt(i) == '%' && d.h0.c.a(str.charAt(i + 1)) != -1 && d.h0.c.a(str.charAt(i3)) != -1;
    }

    static String a(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        int iCharCount = i;
        while (iCharCount < i2) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt >= 32 && iCodePointAt != 127 && (iCodePointAt < 128 || !z4)) {
                if (str2.indexOf(iCodePointAt) == -1 && ((iCodePointAt != 37 || (z && (!z2 || a(str, iCharCount, i2)))) && (iCodePointAt != 43 || !z3))) {
                    iCharCount += Character.charCount(iCodePointAt);
                }
            }
            e.c cVar = new e.c();
            cVar.a(str, i, iCharCount);
            a(cVar, str, iCharCount, i2, str2, z, z2, z3, z4, charset);
            return cVar.o();
        }
        return str.substring(i, i2);
    }

    static void a(e.c cVar, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        e.c cVar2 = null;
        while (i < i2) {
            int iCodePointAt = str.codePointAt(i);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt == 43 && z3) {
                    cVar.b(z ? "+" : "%2B");
                } else if (iCodePointAt >= 32 && iCodePointAt != 127 && ((iCodePointAt < 128 || !z4) && str2.indexOf(iCodePointAt) == -1 && (iCodePointAt != 37 || (z && (!z2 || a(str, i, i2)))))) {
                    cVar.c(iCodePointAt);
                } else {
                    if (cVar2 == null) {
                        cVar2 = new e.c();
                    }
                    if (charset != null && !charset.equals(d.h0.c.i)) {
                        cVar2.a(str, i, Character.charCount(iCodePointAt) + i, charset);
                    } else {
                        cVar2.c(iCodePointAt);
                    }
                    while (!cVar2.j()) {
                        int i3 = cVar2.readByte() & 255;
                        cVar.writeByte(37);
                        cVar.writeByte((int) j[(i3 >> 4) & 15]);
                        cVar.writeByte((int) j[i3 & 15]);
                    }
                }
            }
            i += Character.charCount(iCodePointAt);
        }
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, charset);
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, null);
    }
}
