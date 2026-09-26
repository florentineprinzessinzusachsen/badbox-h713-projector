package ddth2.hidden;

/* JADX INFO: renamed from: ddth2.hidden.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0018s {
    public final String a;
    public final String b;
    public final String c;
    public final String d;

    public C0018s(String str, String str2) {
        String str3;
        String property;
        this.a = str;
        try {
            Object obj = Class.forName("android.os.Build").getField("SUPPORTED_ABIS").get(null);
            if (obj instanceof String[]) {
                String[] strArr = (String[]) obj;
                str3 = strArr.length == 0 ? null : strArr[0];
            }
        } catch (Throwable unused) {
        }
        String strA = a(32, str3);
        String str4 = "android";
        if (strA == null && (strA = a(32, a("CPU_ABI"))) == null) {
            try {
                property = System.getProperty("os.arch");
            } catch (Throwable unused2) {
                property = null;
            }
            strA = a(32, property);
            if (strA == null) {
                strA = "android";
            }
        }
        StringBuilder sb = new StringBuilder(strA);
        a(sb, "m", a("MANUFACTURER"), 24);
        a(sb, "b", a("BRAND"), 24);
        a(sb, "mo", a("MODEL"), 32);
        a(sb, "h", a("HARDWARE"), 24);
        this.b = sb.toString();
        try {
            int i = Class.forName("android.os.Build$VERSION").getField("SDK_INT").getInt(null);
            if (i > 0) {
                str4 = "android-api" + i;
            }
        } catch (Throwable unused3) {
        }
        this.c = str4;
        this.d = str2;
    }

    public static String a(String str) {
        try {
            Object obj = Class.forName("android.os.Build").getField(str).get(null);
            if (obj instanceof String) {
                return (String) obj;
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static void a(StringBuilder sb, String str, String str2, int i) {
        String strA = a(i, str2);
        if (strA == null) {
            return;
        }
        String str3 = "|" + str + "=" + strA;
        if (str3.length() + sb.length() <= 128) {
            sb.append(str3);
        }
    }

    public static String a(int i, String str) {
        String strTrim;
        int length;
        if (str == null || (length = (strTrim = str.trim()).length()) == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(Math.min(length, i));
        for (int i2 = 0; i2 < length && sb.length() < i; i2++) {
            char cCharAt = strTrim.charAt(i2);
            if ((cCharAt >= 'A' && cCharAt <= 'Z') || ((cCharAt >= 'a' && cCharAt <= 'z') || ((cCharAt >= '0' && cCharAt <= '9') || cCharAt == '.' || cCharAt == '_' || cCharAt == '-' || cCharAt == '+'))) {
                sb.append(cCharAt);
            } else if (cCharAt == ' ') {
                sb.append('_');
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }
}
