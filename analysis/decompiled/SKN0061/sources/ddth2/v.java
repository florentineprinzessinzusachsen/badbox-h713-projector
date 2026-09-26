package ddth2;

import android.util.Base64;

/* JADX INFO: loaded from: classes.dex */
public final class v {
    public static String a(String str, char c, char c2) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char c3 : str.toCharArray()) {
            if (c3 == c) {
                sb.append(c2);
            } else if (c3 == c2) {
                sb.append(c);
            } else {
                sb.append(c3);
            }
        }
        return sb.toString();
    }

    public static String a(byte[] bArr) {
        return bArr == null ? "" : Base64.encodeToString(bArr, 2);
    }

    public static byte[] a(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                return Base64.decode(str, 2);
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
