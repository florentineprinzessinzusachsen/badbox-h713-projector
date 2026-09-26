package com.android.settingslib.net;

import android.app.AppGlobals;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.android.settingslib.R;
import com.android.settingslib.Utils;

/* JADX INFO: loaded from: classes.dex */
public class UidDetailProvider {
    public static final int OTHER_USER_RANGE_START = -2000;
    private static final String TAG = "DataUsage";
    private final Context mContext;
    private final SparseArray<UidDetail> mUidDetailCache = new SparseArray<>();

    public static int buildKeyForUser(int i) {
        return (-2000) - i;
    }

    public static int getUserIdForKey(int i) {
        return (-2000) - i;
    }

    public static boolean isKeyForUser(int i) {
        return i <= -2000;
    }

    public UidDetailProvider(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void clearCache() {
        synchronized (this.mUidDetailCache) {
            this.mUidDetailCache.clear();
        }
    }

    public UidDetail getUidDetail(int i, boolean z) {
        UidDetail uidDetail;
        synchronized (this.mUidDetailCache) {
            uidDetail = this.mUidDetailCache.get(i);
        }
        if (uidDetail != null) {
            return uidDetail;
        }
        if (!z) {
            return null;
        }
        UidDetail uidDetailBuildUidDetail = buildUidDetail(i);
        synchronized (this.mUidDetailCache) {
            this.mUidDetailCache.put(i, uidDetailBuildUidDetail);
        }
        return uidDetailBuildUidDetail;
    }

    private UidDetail buildUidDetail(int i) {
        UserInfo userInfo;
        Resources resources = this.mContext.getResources();
        PackageManager packageManager = this.mContext.getPackageManager();
        UidDetail uidDetail = new UidDetail();
        uidDetail.label = packageManager.getNameForUid(i);
        uidDetail.icon = packageManager.getDefaultActivityIcon();
        if (i == 1000) {
            uidDetail.label = resources.getString(R.string.process_kernel_label);
            uidDetail.icon = packageManager.getDefaultActivityIcon();
            return uidDetail;
        }
        switch (i) {
            case -5:
                uidDetail.label = resources.getString(Utils.getTetheringLabel((ConnectivityManager) this.mContext.getSystemService("connectivity")));
                uidDetail.icon = packageManager.getDefaultActivityIcon();
                break;
            case -4:
                uidDetail.label = resources.getString(UserManager.supportsMultipleUsers() ? R.string.data_usage_uninstalled_apps_users : R.string.data_usage_uninstalled_apps);
                uidDetail.icon = packageManager.getDefaultActivityIcon();
                break;
            default:
                UserManager userManager = (UserManager) this.mContext.getSystemService("user");
                if (isKeyForUser(i) && (userInfo = userManager.getUserInfo(getUserIdForKey(i))) != null) {
                    uidDetail.label = Utils.getUserLabel(this.mContext, userInfo);
                    uidDetail.icon = Utils.getUserIcon(this.mContext, userManager, userInfo);
                } else {
                    String[] packagesForUid = packageManager.getPackagesForUid(i);
                    int length = packagesForUid != null ? packagesForUid.length : 0;
                    try {
                        int userId = UserHandle.getUserId(i);
                        UserHandle userHandle = new UserHandle(userId);
                        IPackageManager packageManager2 = AppGlobals.getPackageManager();
                        if (length == 1) {
                            ApplicationInfo applicationInfo = packageManager2.getApplicationInfo(packagesForUid[0], 0, userId);
                            if (applicationInfo != null) {
                                uidDetail.label = applicationInfo.loadLabel(packageManager).toString();
                                uidDetail.icon = userManager.getBadgedIconForUser(applicationInfo.loadIcon(packageManager), new UserHandle(userId));
                            }
                        } else if (length > 1) {
                            uidDetail.detailLabels = new CharSequence[length];
                            uidDetail.detailContentDescriptions = new CharSequence[length];
                            for (int i2 = 0; i2 < length; i2++) {
                                String str = packagesForUid[i2];
                                PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                                ApplicationInfo applicationInfo2 = packageManager2.getApplicationInfo(str, 0, userId);
                                if (applicationInfo2 != null) {
                                    uidDetail.detailLabels[i2] = applicationInfo2.loadLabel(packageManager).toString();
                                    uidDetail.detailContentDescriptions[i2] = userManager.getBadgedLabelForUser(uidDetail.detailLabels[i2], userHandle);
                                    if (packageInfo.sharedUserLabel != 0) {
                                        uidDetail.label = packageManager.getText(str, packageInfo.sharedUserLabel, packageInfo.applicationInfo).toString();
                                        uidDetail.icon = userManager.getBadgedIconForUser(applicationInfo2.loadIcon(packageManager), userHandle);
                                    }
                                }
                            }
                        }
                        uidDetail.contentDescription = userManager.getBadgedLabelForUser(uidDetail.label, userHandle);
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.w(TAG, "Error while building UI detail for uid " + i, e);
                    } catch (RemoteException e2) {
                        Log.w(TAG, "Error while building UI detail for uid " + i, e2);
                    }
                    if (TextUtils.isEmpty(uidDetail.label)) {
                        uidDetail.label = Integer.toString(i);
                    }
                }
                break;
        }
        return uidDetail;
    }
}
