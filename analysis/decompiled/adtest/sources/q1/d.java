package q1;

import android.content.Context;
import android.content.SharedPreferences;
import d0.l0;
import u1.g;
import u1.h;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static void a(Context context) {
        Object objL;
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("AppPreferences", 0);
        if (sharedPreferences.getBoolean("pending_play_store_restore", false)) {
            try {
                objL = Boolean.valueOf(e.a(context, 0));
            } catch (Throwable th) {
                objL = l0.l(th);
            }
            Throwable thA = h.a(objL);
            if (thA != null) {
                l3.h.a0("[PlayStoreRecovery] 恢复 PlayStore 时出现异常: " + thA.getClass().getSimpleName() + ": " + thA.getMessage());
            }
            Boolean bool = Boolean.FALSE;
            if (objL instanceof g) {
                objL = bool;
            }
            if (((Boolean) objL).booleanValue()) {
                sharedPreferences.edit().putBoolean("pending_play_store_restore", false).apply();
            }
        }
    }
}
