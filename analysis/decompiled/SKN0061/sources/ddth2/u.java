package ddth2;

import android.util.Base64;

/* JADX INFO: loaded from: classes.dex */
public final class u {
    public static String a(byte[] bArr) {
        try {
            byte[] bArrDecode = Base64.decode(new String(bArr), 2);
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDecode) {
                sb.append((char) (b ^ 90));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
