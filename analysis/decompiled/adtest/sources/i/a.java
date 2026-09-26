package i;

import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import h.b;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    /* JADX WARN: Code duplicated, block: B:31:0x0090  */
    /* JADX WARN: Code duplicated, block: B:32:0x0092  */
    public static Intent a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i4) {
        int iC;
        if ((i4 & 4) == 0 || str != null) {
            return context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i4 & 1);
        }
        String str2 = context.getPackageName() + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
        int iMyPid = Process.myPid();
        int iMyUid = Process.myUid();
        String packageName = context.getPackageName();
        byte b4 = -1;
        if (context.checkPermission(str2, iMyPid, iMyUid) != -1) {
            String strD = h.a.d(str2);
            if (strD == null) {
                b4 = 0;
            } else if (packageName != null) {
                int iMyUid2 = Process.myUid();
                String packageName2 = context.getPackageName();
                if (iMyUid2 == iMyUid || !m.a.a(packageName2, packageName) || Build.VERSION.SDK_INT < 29) {
                    iC = h.a.c((AppOpsManager) h.a.a(context, AppOpsManager.class), strD, packageName);
                } else {
                    AppOpsManager appOpsManagerC = b.c(context);
                    iC = b.a(appOpsManagerC, strD, Binder.getCallingUid(), packageName);
                    if (iC == 0) {
                        iC = b.a(appOpsManagerC, strD, iMyUid, b.b(context));
                    }
                }
                if (iC == 0) {
                    b4 = 0;
                } else {
                    b4 = -2;
                }
            } else {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(iMyUid);
                if (packagesForUid != null && packagesForUid.length > 0) {
                    packageName = packagesForUid[0];
                    int iMyUid3 = Process.myUid();
                    String packageName3 = context.getPackageName();
                    iC = iMyUid3 == iMyUid ? h.a.c((AppOpsManager) h.a.a(context, AppOpsManager.class), strD, packageName) : h.a.c((AppOpsManager) h.a.a(context, AppOpsManager.class), strD, packageName);
                    if (iC == 0) {
                        b4 = 0;
                    } else {
                        b4 = -2;
                    }
                }
            }
        }
        if (b4 == 0) {
            return context.registerReceiver(broadcastReceiver, intentFilter, str2, handler);
        }
        throw new RuntimeException("Permission " + str2 + " is required by your application to receive broadcasts, please add it to your manifest");
    }

    public static ComponentName b(Context context, Intent intent) {
        return context.startForegroundService(intent);
    }
}
