package ddth2;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static String a(Context context) {
        String strA = m.a(k.a);
        if (strA == null || strA.isEmpty()) {
            return null;
        }
        return context.getSharedPreferences(strA, 0).getString(m.a(k.b), null);
    }

    public static void a(Context context, String str) {
        String strA;
        String strA2 = m.a(k.a);
        if (strA2 == null || strA2.isEmpty() || (strA = m.a(k.b)) == null || strA.isEmpty()) {
            return;
        }
        context.getSharedPreferences(strA2, 0).edit().putString(strA, str).apply();
    }
}
