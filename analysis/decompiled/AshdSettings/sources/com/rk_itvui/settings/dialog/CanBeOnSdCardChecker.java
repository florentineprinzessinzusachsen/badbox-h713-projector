package com.rk_itvui.settings.dialog;

import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

/* JADX INFO: compiled from: ManageApplications.java */
/* JADX INFO: loaded from: classes.dex */
final class CanBeOnSdCardChecker {
    int mInstallLocation;
    final IPackageManager mPm = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));

    CanBeOnSdCardChecker() {
    }

    void init() {
        try {
            this.mInstallLocation = this.mPm.getInstallLocation();
        } catch (RemoteException unused) {
            Log.e("CanBeOnSdCardChecker", "Is Package Manager running?");
        }
    }

    boolean check(ApplicationInfo applicationInfo) {
        if ((applicationInfo.flags & 262144) != 0) {
            return true;
        }
        if ((applicationInfo.flags & 4) == 0 && (applicationInfo.flags & 1) == 0) {
            if (applicationInfo.installLocation == 2 || applicationInfo.installLocation == 0) {
                return true;
            }
            if (applicationInfo.installLocation == -1 && this.mInstallLocation == 2) {
                return true;
            }
        }
        return false;
    }
}
