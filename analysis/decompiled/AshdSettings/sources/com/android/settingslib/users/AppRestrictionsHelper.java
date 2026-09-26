package com.android.settingslib.users;

import android.app.AppGlobals;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ParceledListSlice;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.support.v4.content.IntentCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class AppRestrictionsHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "AppRestrictionsHelper";
    private final Context mContext;
    private boolean mLeanback;
    private final PackageManager mPackageManager;
    private final boolean mRestrictedProfile;
    private final UserHandle mUser;
    private final UserManager mUserManager;
    private List<SelectableAppInfo> mVisibleApps;
    HashMap<String, Boolean> mSelectedPackages = new HashMap<>();
    private final IPackageManager mIPm = AppGlobals.getPackageManager();

    public interface OnDisableUiForPackageListener {
        void onDisableUiForPackage(String str);
    }

    public AppRestrictionsHelper(Context context, UserHandle userHandle) {
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
        this.mUser = userHandle;
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mRestrictedProfile = this.mUserManager.getUserInfo(this.mUser.getIdentifier()).isRestricted();
    }

    public void setPackageSelected(String str, boolean z) {
        this.mSelectedPackages.put(str, Boolean.valueOf(z));
    }

    public boolean isPackageSelected(String str) {
        return this.mSelectedPackages.get(str).booleanValue();
    }

    public void setLeanback(boolean z) {
        this.mLeanback = z;
    }

    public List<SelectableAppInfo> getVisibleApps() {
        return this.mVisibleApps;
    }

    public void applyUserAppsStates(OnDisableUiForPackageListener onDisableUiForPackageListener) {
        int identifier = this.mUser.getIdentifier();
        if (!this.mUserManager.getUserInfo(identifier).isRestricted() && identifier != UserHandle.myUserId()) {
            Log.e(TAG, "Cannot apply application restrictions on another user!");
            return;
        }
        for (Map.Entry<String, Boolean> entry : this.mSelectedPackages.entrySet()) {
            applyUserAppState(entry.getKey(), entry.getValue().booleanValue(), onDisableUiForPackageListener);
        }
    }

    public void applyUserAppState(String str, boolean z, OnDisableUiForPackageListener onDisableUiForPackageListener) {
        int identifier = this.mUser.getIdentifier();
        try {
            if (z) {
                ApplicationInfo applicationInfo = this.mIPm.getApplicationInfo(str, 8192, identifier);
                if (applicationInfo == null || !applicationInfo.enabled || (applicationInfo.flags & 8388608) == 0) {
                    this.mIPm.installExistingPackageAsUser(str, this.mUser.getIdentifier());
                }
                if (applicationInfo == null || (1 & applicationInfo.privateFlags) == 0 || (applicationInfo.flags & 8388608) == 0) {
                    return;
                }
                onDisableUiForPackageListener.onDisableUiForPackage(str);
                this.mIPm.setApplicationHiddenSettingAsUser(str, false, identifier);
                return;
            }
            if (this.mIPm.getApplicationInfo(str, 0, identifier) != null) {
                if (this.mRestrictedProfile) {
                    this.mIPm.deletePackageAsUser(str, (IPackageDeleteObserver) null, this.mUser.getIdentifier(), 4);
                } else {
                    onDisableUiForPackageListener.onDisableUiForPackage(str);
                    this.mIPm.setApplicationHiddenSettingAsUser(str, true, identifier);
                }
            }
        } catch (RemoteException unused) {
        }
    }

    public void fetchAndMergeApps() {
        List<ApplicationInfo> list;
        this.mVisibleApps = new ArrayList();
        PackageManager packageManager = this.mPackageManager;
        IPackageManager iPackageManager = this.mIPm;
        HashSet hashSet = new HashSet();
        addSystemImes(hashSet);
        Intent intent = new Intent("android.intent.action.MAIN");
        if (this.mLeanback) {
            intent.addCategory(IntentCompat.CATEGORY_LEANBACK_LAUNCHER);
        } else {
            intent.addCategory("android.intent.category.LAUNCHER");
        }
        addSystemApps(this.mVisibleApps, intent, hashSet);
        addSystemApps(this.mVisibleApps, new Intent("android.appwidget.action.APPWIDGET_UPDATE"), hashSet);
        for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(8192)) {
            if ((8388608 & applicationInfo.flags) != 0) {
                if ((applicationInfo.flags & 1) == 0 && (applicationInfo.flags & 128) == 0) {
                    SelectableAppInfo selectableAppInfo = new SelectableAppInfo();
                    selectableAppInfo.packageName = applicationInfo.packageName;
                    selectableAppInfo.appName = applicationInfo.loadLabel(packageManager);
                    selectableAppInfo.activityName = selectableAppInfo.appName;
                    selectableAppInfo.icon = applicationInfo.loadIcon(packageManager);
                    this.mVisibleApps.add(selectableAppInfo);
                } else {
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, 0);
                        if (this.mRestrictedProfile && packageInfo.requiredAccountType != null && packageInfo.restrictedAccountType == null) {
                            this.mSelectedPackages.put(applicationInfo.packageName, false);
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
            }
        }
        try {
            ParceledListSlice installedApplications = iPackageManager.getInstalledApplications(8192, this.mUser.getIdentifier());
            list = installedApplications != null ? installedApplications.getList() : null;
        } catch (RemoteException unused2) {
        }
        if (list != null) {
            for (ApplicationInfo applicationInfo2 : list) {
                if ((applicationInfo2.flags & 8388608) != 0 && (applicationInfo2.flags & 1) == 0 && (applicationInfo2.flags & 128) == 0) {
                    SelectableAppInfo selectableAppInfo2 = new SelectableAppInfo();
                    selectableAppInfo2.packageName = applicationInfo2.packageName;
                    selectableAppInfo2.appName = applicationInfo2.loadLabel(packageManager);
                    selectableAppInfo2.activityName = selectableAppInfo2.appName;
                    selectableAppInfo2.icon = applicationInfo2.loadIcon(packageManager);
                    this.mVisibleApps.add(selectableAppInfo2);
                }
            }
        }
        Collections.sort(this.mVisibleApps, new AppLabelComparator());
        HashSet hashSet2 = new HashSet();
        for (int size = this.mVisibleApps.size() - 1; size >= 0; size--) {
            SelectableAppInfo selectableAppInfo3 = this.mVisibleApps.get(size);
            String str = selectableAppInfo3.packageName + "+" + ((Object) selectableAppInfo3.activityName);
            if (!TextUtils.isEmpty(selectableAppInfo3.packageName) && !TextUtils.isEmpty(selectableAppInfo3.activityName) && hashSet2.contains(str)) {
                this.mVisibleApps.remove(size);
            } else {
                hashSet2.add(str);
            }
        }
        HashMap map = new HashMap();
        for (SelectableAppInfo selectableAppInfo4 : this.mVisibleApps) {
            if (map.containsKey(selectableAppInfo4.packageName)) {
                selectableAppInfo4.masterEntry = (SelectableAppInfo) map.get(selectableAppInfo4.packageName);
            } else {
                map.put(selectableAppInfo4.packageName, selectableAppInfo4);
            }
        }
    }

    private void addSystemImes(Set<String> set) {
        for (InputMethodInfo inputMethodInfo : ((InputMethodManager) this.mContext.getSystemService("input_method")).getInputMethodList()) {
            try {
                if (inputMethodInfo.isDefault(this.mContext) && isSystemPackage(inputMethodInfo.getPackageName())) {
                    set.add(inputMethodInfo.getPackageName());
                }
            } catch (Resources.NotFoundException unused) {
            }
        }
    }

    private void addSystemApps(List<SelectableAppInfo> list, Intent intent, Set<String> set) {
        int applicationEnabledSetting;
        ApplicationInfo appInfoForUser;
        PackageManager packageManager = this.mPackageManager;
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 8704)) {
            if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.applicationInfo != null) {
                String str = resolveInfo.activityInfo.packageName;
                int i = resolveInfo.activityInfo.applicationInfo.flags;
                if ((i & 1) != 0 || (i & 128) != 0) {
                    if (!set.contains(str) && (((applicationEnabledSetting = packageManager.getApplicationEnabledSetting(str)) != 4 && applicationEnabledSetting != 2) || ((appInfoForUser = getAppInfoForUser(str, 0, this.mUser)) != null && (appInfoForUser.flags & 8388608) != 0))) {
                        SelectableAppInfo selectableAppInfo = new SelectableAppInfo();
                        selectableAppInfo.packageName = resolveInfo.activityInfo.packageName;
                        selectableAppInfo.appName = resolveInfo.activityInfo.applicationInfo.loadLabel(packageManager);
                        selectableAppInfo.icon = resolveInfo.activityInfo.loadIcon(packageManager);
                        selectableAppInfo.activityName = resolveInfo.activityInfo.loadLabel(packageManager);
                        if (selectableAppInfo.activityName == null) {
                            selectableAppInfo.activityName = selectableAppInfo.appName;
                        }
                        list.add(selectableAppInfo);
                    }
                }
            }
        }
    }

    private boolean isSystemPackage(String str) {
        try {
            PackageInfo packageInfo = this.mPackageManager.getPackageInfo(str, 0);
            if (packageInfo.applicationInfo == null) {
                return false;
            }
            int i = packageInfo.applicationInfo.flags;
            if ((i & 1) != 0 || (i & 128) != 0) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    private ApplicationInfo getAppInfoForUser(String str, int i, UserHandle userHandle) {
        try {
            return this.mIPm.getApplicationInfo(str, i, userHandle.getIdentifier());
        } catch (RemoteException unused) {
            return null;
        }
    }

    public static class SelectableAppInfo {
        public CharSequence activityName;
        public CharSequence appName;
        public Drawable icon;
        public SelectableAppInfo masterEntry;
        public String packageName;

        public String toString() {
            return this.packageName + ": appName=" + ((Object) this.appName) + "; activityName=" + ((Object) this.activityName) + "; icon=" + this.icon + "; masterEntry=" + this.masterEntry;
        }
    }

    private static class AppLabelComparator implements Comparator<SelectableAppInfo> {
        private AppLabelComparator() {
        }

        @Override // java.util.Comparator
        public int compare(SelectableAppInfo selectableAppInfo, SelectableAppInfo selectableAppInfo2) {
            return selectableAppInfo.activityName.toString().toLowerCase().compareTo(selectableAppInfo2.activityName.toString().toLowerCase());
        }
    }
}
