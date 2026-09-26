package com.android.settingslib.location;

import android.app.AppGlobals;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecentLocationApps {
    private static final String ANDROID_SYSTEM_PACKAGE_NAME = "android";
    private static final int[] LOCATION_OPS = {41, 42};
    private static final int RECENT_TIME_INTERVAL_MILLIS = 900000;
    private static final String TAG = "RecentLocationApps";
    private final Context mContext;
    private final PackageManager mPackageManager;

    public RecentLocationApps(Context context) {
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
    }

    public List<Request> getAppList() {
        Request requestFromOps;
        List packagesForOps = ((AppOpsManager) this.mContext.getSystemService("appops")).getPackagesForOps(LOCATION_OPS);
        int size = packagesForOps != null ? packagesForOps.size() : 0;
        ArrayList arrayList = new ArrayList(size);
        long jCurrentTimeMillis = System.currentTimeMillis();
        List<UserHandle> userProfiles = ((UserManager) this.mContext.getSystemService("user")).getUserProfiles();
        for (int i = 0; i < size; i++) {
            AppOpsManager.PackageOps packageOps = (AppOpsManager.PackageOps) packagesForOps.get(i);
            String packageName = packageOps.getPackageName();
            int uid = packageOps.getUid();
            int userId = UserHandle.getUserId(uid);
            if (!(uid == 1000 && ANDROID_SYSTEM_PACKAGE_NAME.equals(packageName)) && userProfiles.contains(new UserHandle(userId)) && (requestFromOps = getRequestFromOps(jCurrentTimeMillis, packageOps)) != null) {
                arrayList.add(requestFromOps);
            }
        }
        return arrayList;
    }

    private Request getRequestFromOps(long j, AppOpsManager.PackageOps packageOps) {
        String packageName = packageOps.getPackageName();
        long j2 = j - 900000;
        boolean z = false;
        boolean z2 = false;
        for (AppOpsManager.OpEntry opEntry : packageOps.getOps()) {
            if (opEntry.isRunning() || opEntry.getTime() >= j2) {
                switch (opEntry.getOp()) {
                    case 41:
                        z = true;
                        break;
                    case 42:
                        z2 = true;
                        break;
                }
            }
        }
        if (!z2 && !z) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, packageName + " hadn't used location within the time interval.");
            }
            return null;
        }
        int userId = UserHandle.getUserId(packageOps.getUid());
        try {
            ApplicationInfo applicationInfo = AppGlobals.getPackageManager().getApplicationInfo(packageName, 128, userId);
            if (applicationInfo == null) {
                Log.w(TAG, "Null application info retrieved for package " + packageName + ", userId " + userId);
                return null;
            }
            UserHandle userHandle = new UserHandle(userId);
            Drawable userBadgedIcon = this.mPackageManager.getUserBadgedIcon(this.mPackageManager.getApplicationIcon(applicationInfo), userHandle);
            CharSequence applicationLabel = this.mPackageManager.getApplicationLabel(applicationInfo);
            CharSequence userBadgedLabel = this.mPackageManager.getUserBadgedLabel(applicationLabel, userHandle);
            return new Request(packageName, userHandle, userBadgedIcon, applicationLabel, z2, applicationLabel.toString().contentEquals(userBadgedLabel) ? null : userBadgedLabel);
        } catch (RemoteException e) {
            Log.w(TAG, "Error while retrieving application info for package " + packageName + ", userId " + userId, e);
            return null;
        }
    }

    public static class Request {
        public final CharSequence contentDescription;
        public final Drawable icon;
        public final boolean isHighBattery;
        public final CharSequence label;
        public final String packageName;
        public final UserHandle userHandle;

        private Request(String str, UserHandle userHandle, Drawable drawable, CharSequence charSequence, boolean z, CharSequence charSequence2) {
            this.packageName = str;
            this.userHandle = userHandle;
            this.icon = drawable;
            this.label = charSequence;
            this.isHighBattery = z;
            this.contentDescription = charSequence2;
        }
    }
}
