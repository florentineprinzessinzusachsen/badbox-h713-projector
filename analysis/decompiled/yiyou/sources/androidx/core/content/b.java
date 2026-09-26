package androidx.core.content;

import android.content.Context;
import android.os.Process;
import androidx.core.app.d;

/* JADX INFO: compiled from: PermissionChecker.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static int a(Context context, String str, int i, int i2, String str2) {
        if (context.checkPermission(str, i, i2) == -1) {
            return -1;
        }
        String strA = d.a(str);
        if (strA == null) {
            return 0;
        }
        if (str2 == null) {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i2);
            if (packagesForUid == null || packagesForUid.length <= 0) {
                return -1;
            }
            str2 = packagesForUid[0];
        }
        return d.a(context, strA, str2) != 0 ? -2 : 0;
    }

    public static int a(Context context, String str) {
        return a(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }
}
