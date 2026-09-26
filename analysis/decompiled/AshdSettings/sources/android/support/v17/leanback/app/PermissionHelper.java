package android.support.v17.leanback.app;

import android.app.Fragment;
import android.os.Build;
import android.support.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class PermissionHelper {
    public static void requestPermissions(Fragment fragment, String[] strArr, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            PermissionHelper23.requestPermissions(fragment, strArr, i);
        }
    }

    public static void requestPermissions(android.support.v4.app.Fragment fragment, String[] strArr, int i) {
        fragment.requestPermissions(strArr, i);
    }
}
