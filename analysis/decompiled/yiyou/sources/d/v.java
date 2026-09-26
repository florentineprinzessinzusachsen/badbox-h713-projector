package d;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: MediaType.java */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Pattern f4676e = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final Pattern f4677f = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f4678a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f4679b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f4680c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f4681d;

    private v(String str, String str2, String str3, String str4) {
        this.f4678a = str;
        this.f4679b = str2;
        this.f4680c = str3;
        this.f4681d = str4;
    }

    public static v a(String str) {
        Matcher matcher = f4676e.matcher(str);
        if (!matcher.lookingAt()) {
            throw new IllegalArgumentException("No subtype found for: \"" + str + '\"');
        }
        String lowerCase = matcher.group(1).toLowerCase(Locale.US);
        String lowerCase2 = matcher.group(2).toLowerCase(Locale.US);
        String str2 = null;
        Matcher matcher2 = f4677f.matcher(str);
        for (int iEnd = matcher.end(); iEnd < str.length(); iEnd = matcher2.end()) {
            matcher2.region(iEnd, str.length());
            if (!matcher2.lookingAt()) {
                throw new IllegalArgumentException("Parameter is not formatted correctly: \"" + str.substring(iEnd) + "\" for: \"" + str + '\"');
            }
            String strGroup = matcher2.group(1);
            if (strGroup != null && strGroup.equalsIgnoreCase("charset")) {
                String strGroup2 = matcher2.group(2);
                if (strGroup2 == null) {
                    strGroup2 = matcher2.group(3);
                } else if (strGroup2.startsWith("'") && strGroup2.endsWith("'") && strGroup2.length() > 2) {
                    strGroup2 = strGroup2.substring(1, strGroup2.length() - 1);
                }
                if (str2 != null && !strGroup2.equalsIgnoreCase(str2)) {
                    throw new IllegalArgumentException("Multiple charsets defined: \"" + str2 + "\" and: \"" + strGroup2 + "\" for: \"" + str + '\"');
                }
                str2 = strGroup2;
            }
        }
        return new v(str, lowerCase, lowerCase2, str2);
    }

    public static v b(String str) {
        try {
            return a(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public String c() {
        return this.f4679b;
    }

    public boolean equals(Object obj) {
        return (obj instanceof v) && ((v) obj).f4678a.equals(this.f4678a);
    }

    public int hashCode() {
        return this.f4678a.hashCode();
    }

    public String toString() {
        return this.f4678a;
    }

    public String b() {
        return this.f4680c;
    }

    public Charset a() {
        return a((Charset) null);
    }

    public Charset a(Charset charset) {
        try {
            return this.f4681d != null ? Charset.forName(this.f4681d) : charset;
        } catch (IllegalArgumentException unused) {
            return charset;
        }
    }
}
