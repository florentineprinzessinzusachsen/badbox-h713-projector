package ddth2;

import android.util.Base64;

/* JADX INFO: loaded from: classes.dex */
public final class z {
    public static String a() {
        try {
            byte[] bArrDecode = Base64.decode("Fw8oEQ0jKzcpKQkpKxIJOBYPDhFoAG45CzkdMhwMDg==", 2);
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
