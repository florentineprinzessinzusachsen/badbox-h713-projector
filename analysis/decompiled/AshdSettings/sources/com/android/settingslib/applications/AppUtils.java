package com.android.settingslib.applications;

import android.content.pm.PackageManager;
import android.hardware.usb.IUsbManager;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AppUtils {
    private static final String TAG = "AppUtils";

    public static boolean hasUsbDefaults(IUsbManager iUsbManager, String str) {
        if (iUsbManager == null) {
            return false;
        }
        try {
            return iUsbManager.hasDefaults(str, UserHandle.myUserId());
        } catch (RemoteException e) {
            Log.e(TAG, "mUsbManager.hasDefaults", e);
            return false;
        }
    }

    public static boolean hasPreferredActivities(PackageManager packageManager, String str) {
        ArrayList arrayList = new ArrayList();
        packageManager.getPreferredActivities(new ArrayList(), arrayList, str);
        Log.d(TAG, "Have " + arrayList.size() + " number of activities in preferred list");
        return arrayList.size() > 0;
    }
}
