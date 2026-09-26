package ddth2;

import android.content.Context;
import android.provider.Settings;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static String a(Context context) {
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            String strA = l.a(context);
            if (string != null && !string.isEmpty() && !"9774d56d682e549c".equals(string)) {
                StringBuilder sb = new StringBuilder();
                sb.append("ANDROID_");
                sb.append(string);
                sb.append("_");
                if (strA == null || strA.isEmpty()) {
                    strA = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
                }
                sb.append(strA);
                return sb.toString();
            }
        } catch (Exception unused) {
        }
        return "ANDROID_" + UUID.randomUUID().toString().replace("-", "");
    }
}
