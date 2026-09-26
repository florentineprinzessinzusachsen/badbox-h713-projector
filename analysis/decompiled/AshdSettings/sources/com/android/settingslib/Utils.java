package com.android.settingslib;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.UserManager;
import com.android.internal.util.UserIcons;
import com.android.settingslib.drawable.UserIconDrawable;
import java.text.NumberFormat;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    private static String sPermissionControllerPackageName;
    private static String sServicesSystemSharedLibPackageName;
    private static String sSharedSystemSharedLibPackageName;
    private static Signature[] sSystemSignature;

    public static int getTetheringLabel(ConnectivityManager connectivityManager) {
        String[] tetherableUsbRegexs = connectivityManager.getTetherableUsbRegexs();
        String[] tetherableWifiRegexs = connectivityManager.getTetherableWifiRegexs();
        String[] tetherableBluetoothRegexs = connectivityManager.getTetherableBluetoothRegexs();
        boolean z = tetherableUsbRegexs.length != 0;
        boolean z2 = tetherableWifiRegexs.length != 0;
        boolean z3 = tetherableBluetoothRegexs.length != 0;
        if (z2 && z && z3) {
            return R.string.tether_settings_title_all;
        }
        if (z2 && z) {
            return R.string.tether_settings_title_all;
        }
        if (z2 && z3) {
            return R.string.tether_settings_title_all;
        }
        if (z2) {
            return R.string.tether_settings_title_wifi;
        }
        if (z && z3) {
            return R.string.tether_settings_title_usb_bluetooth;
        }
        if (z) {
            return R.string.tether_settings_title_usb;
        }
        return R.string.tether_settings_title_bluetooth;
    }

    public static String getUserLabel(Context context, UserInfo userInfo) {
        String string = userInfo != null ? userInfo.name : null;
        if (userInfo.isManagedProfile()) {
            return context.getString(R.string.managed_user_title);
        }
        if (userInfo.isGuest()) {
            string = context.getString(R.string.user_guest);
        }
        if (string == null && userInfo != null) {
            string = Integer.toString(userInfo.id);
        } else if (userInfo == null) {
            string = context.getString(R.string.unknown);
        }
        return context.getResources().getString(R.string.running_process_item_user_label, string);
    }

    public static UserIconDrawable getUserIcon(Context context, UserManager userManager, UserInfo userInfo) {
        Bitmap userIcon;
        int sizeForList = UserIconDrawable.getSizeForList(context);
        if (userInfo.isManagedProfile()) {
            return new UserIconDrawable(sizeForList).setIcon(BitmapFactory.decodeResource(context.getResources(), android.R.drawable.dropdown_ic_arrow_focused_holo_light)).bake();
        }
        if (userInfo.iconPath != null && (userIcon = userManager.getUserIcon(userInfo.id)) != null) {
            return new UserIconDrawable(sizeForList).setIcon(userIcon).bake();
        }
        return new UserIconDrawable(sizeForList).setIconDrawable(UserIcons.getDefaultUserIcon(userInfo.id, false)).bake();
    }

    public static String formatPercentage(long j, long j2) {
        return formatPercentage(j / j2);
    }

    public static String formatPercentage(int i) {
        return formatPercentage(((double) i) / 100.0d);
    }

    private static String formatPercentage(double d) {
        return NumberFormat.getPercentInstance().format(d);
    }

    public static int getBatteryLevel(Intent intent) {
        return (intent.getIntExtra("level", 0) * 100) / intent.getIntExtra("scale", 100);
    }

    public static String getBatteryStatus(Resources resources, Intent intent) {
        return getBatteryStatus(resources, intent, false);
    }

    public static String getBatteryStatus(Resources resources, Intent intent, boolean z) {
        int i;
        int intExtra = intent.getIntExtra("plugged", 0);
        int intExtra2 = intent.getIntExtra("status", 1);
        if (intExtra2 != 2) {
            if (intExtra2 == 3) {
                return resources.getString(R.string.battery_info_status_discharging);
            }
            if (intExtra2 == 4) {
                return resources.getString(R.string.battery_info_status_not_charging);
            }
            if (intExtra2 == 5) {
                return resources.getString(R.string.battery_info_status_full);
            }
            return resources.getString(R.string.battery_info_status_unknown);
        }
        if (intExtra == 1) {
            i = z ? R.string.battery_info_status_charging_ac_short : R.string.battery_info_status_charging_ac;
        } else if (intExtra == 2) {
            i = z ? R.string.battery_info_status_charging_usb_short : R.string.battery_info_status_charging_usb;
        } else if (intExtra == 4) {
            i = z ? R.string.battery_info_status_charging_wireless_short : R.string.battery_info_status_charging_wireless;
        } else {
            i = R.string.battery_info_status_charging;
        }
        return resources.getString(i);
    }

    public static int getColorAccent(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{android.R.attr.colorAccent});
        int color = typedArrayObtainStyledAttributes.getColor(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return color;
    }

    public static boolean isSystemPackage(Resources resources, PackageManager packageManager, PackageInfo packageInfo) {
        if (sSystemSignature == null) {
            sSystemSignature = new Signature[]{getSystemSignature(packageManager)};
        }
        if (sPermissionControllerPackageName == null) {
            sPermissionControllerPackageName = packageManager.getPermissionControllerPackageName();
        }
        if (sServicesSystemSharedLibPackageName == null) {
            sServicesSystemSharedLibPackageName = packageManager.getServicesSystemSharedLibraryPackageName();
        }
        if (sSharedSystemSharedLibPackageName == null) {
            sSharedSystemSharedLibPackageName = packageManager.getSharedSystemSharedLibraryPackageName();
        }
        return (sSystemSignature[0] != null && sSystemSignature[0].equals(getFirstSignature(packageInfo))) || packageInfo.packageName.equals(sPermissionControllerPackageName) || packageInfo.packageName.equals(sServicesSystemSharedLibPackageName) || packageInfo.packageName.equals(sSharedSystemSharedLibPackageName) || packageInfo.packageName.equals("com.android.printspooler") || isDeviceProvisioningPackage(resources, packageInfo.packageName);
    }

    private static Signature getFirstSignature(PackageInfo packageInfo) {
        if (packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
            return null;
        }
        return packageInfo.signatures[0];
    }

    private static Signature getSystemSignature(PackageManager packageManager) {
        try {
            return getFirstSignature(packageManager.getPackageInfo("android", 64));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static boolean isDeviceProvisioningPackage(Resources resources, String str) {
        String string = resources.getString(android.R.string.PERSOSUBSTATE_RUIM_NETWORK1_PUK_SUCCESS);
        return string != null && string.equals(str);
    }
}
