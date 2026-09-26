package com.rk_itvui.settings;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.os.Bundle;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFrameLayout;
import android.preference.PreferenceGroup;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    public static final float DISABLED_ALPHA = 0.4f;
    private static final String META_DATA_PREFERENCE_ICON = "com.android.settings.icon";
    private static final String META_DATA_PREFERENCE_SUMMARY = "com.android.settings.summary";
    private static final String META_DATA_PREFERENCE_TITLE = "com.android.settings.title";
    public static final int UPDATE_PREFERENCE_FLAG_SET_TITLE_TO_MATCHING_ACTIVITY = 1;

    public static boolean FilterBtRequest(String str) {
        return true;
    }

    public static boolean updatePreferenceToSpecificActivityOrRemove(Context context, PreferenceGroup preferenceGroup, String str, int i) {
        Preference preferenceFindPreference = preferenceGroup.findPreference(str);
        if (preferenceFindPreference == null) {
            return false;
        }
        Intent intent = preferenceFindPreference.getIntent();
        if (intent != null) {
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            int size = listQueryIntentActivities.size();
            for (int i2 = 0; i2 < size; i2++) {
                ResolveInfo resolveInfo = listQueryIntentActivities.get(i2);
                if ((resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                    preferenceFindPreference.setIntent(new Intent().setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    if ((i & 1) != 0) {
                        preferenceFindPreference.setTitle(resolveInfo.loadLabel(packageManager));
                    }
                    return true;
                }
            }
        }
        preferenceGroup.removePreference(preferenceFindPreference);
        return false;
    }

    public static boolean updatePreferenceToSpecificActivityFromMetaDataOrRemove(Context context, PreferenceGroup preferenceGroup, String str) {
        Drawable drawable;
        String string;
        String string2;
        IconPreferenceScreen iconPreferenceScreen = (IconPreferenceScreen) preferenceGroup.findPreference(str);
        if (iconPreferenceScreen == null) {
            return false;
        }
        Intent intent = iconPreferenceScreen.getIntent();
        if (intent != null) {
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 128);
            int size = listQueryIntentActivities.size();
            for (int i = 0; i < size; i++) {
                ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
                if ((resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                    String str2 = null;
                    str2 = null;
                    str2 = null;
                    Drawable drawable2 = null;
                    try {
                        Resources resourcesForApplication = packageManager.getResourcesForApplication(resolveInfo.activityInfo.packageName);
                        Bundle bundle = resolveInfo.activityInfo.metaData;
                        if (resourcesForApplication == null || bundle == null) {
                            string2 = null;
                            string = null;
                        } else {
                            drawable = resourcesForApplication.getDrawable(bundle.getInt("com.android.settings.icon"));
                            try {
                                string = resourcesForApplication.getString(bundle.getInt("com.android.settings.title"));
                                try {
                                    string2 = resourcesForApplication.getString(bundle.getInt("com.android.settings.summary"));
                                    drawable2 = drawable;
                                } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
                                }
                            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused2) {
                                string = null;
                            }
                        }
                        drawable = drawable2;
                        str2 = string2;
                    } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused3) {
                        drawable = null;
                        string = null;
                    }
                    if (TextUtils.isEmpty(string)) {
                        string = resolveInfo.loadLabel(packageManager).toString();
                    }
                    iconPreferenceScreen.setIcon(drawable);
                    iconPreferenceScreen.setTitle(string);
                    iconPreferenceScreen.setSummary(str2);
                    iconPreferenceScreen.setIntent(new Intent().setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    return true;
                }
            }
        }
        preferenceGroup.removePreference(iconPreferenceScreen);
        return false;
    }

    public static boolean updateHeaderToSpecificActivityFromMetaDataOrRemove(Context context, List<PreferenceActivity.Header> list, PreferenceActivity.Header header) {
        String string;
        String string2;
        Intent intent = header.intent;
        if (intent != null) {
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 128);
            int size = listQueryIntentActivities.size();
            for (int i = 0; i < size; i++) {
                ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
                if ((resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                    String str = null;
                    try {
                        Resources resourcesForApplication = packageManager.getResourcesForApplication(resolveInfo.activityInfo.packageName);
                        Bundle bundle = resolveInfo.activityInfo.metaData;
                        if (resourcesForApplication == null || bundle == null) {
                            string2 = null;
                        } else {
                            resourcesForApplication.getDrawable(bundle.getInt("com.android.settings.icon"));
                            string = resourcesForApplication.getString(bundle.getInt("com.android.settings.title"));
                            try {
                                string2 = resourcesForApplication.getString(bundle.getInt("com.android.settings.summary"));
                                str = string;
                            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
                            }
                        }
                        string = str;
                        str = string2;
                    } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused2) {
                        string = null;
                    }
                    if (TextUtils.isEmpty(string)) {
                        string = resolveInfo.loadLabel(packageManager).toString();
                    }
                    header.title = string;
                    header.summary = str;
                    header.intent = new Intent().setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                    return true;
                }
            }
        }
        list.remove(header);
        return false;
    }

    public static boolean isMonkeyRunning() {
        return ActivityManager.isUserAMonkey();
    }

    public static boolean isVoiceCapable(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return telephonyManager != null && telephonyManager.isVoiceCapable();
    }

    public static boolean isWifiOnly(Context context) {
        return !((ConnectivityManager) context.getSystemService("connectivity")).isNetworkSupported(0);
    }

    public static String getWifiIpAddresses(Context context) {
        return formatIpAddresses(((ConnectivityManager) context.getSystemService("connectivity")).getLinkProperties(1));
    }

    public static String getDefaultIpAddresses(Context context) {
        return formatIpAddresses(((ConnectivityManager) context.getSystemService("connectivity")).getActiveLinkProperties());
    }

    private static String formatIpAddresses(LinkProperties linkProperties) {
        if (linkProperties == null) {
            return null;
        }
        Iterator it = linkProperties.getAllAddresses().iterator();
        if (!it.hasNext()) {
            return null;
        }
        String str = "";
        while (it.hasNext()) {
            str = str + ((InetAddress) it.next()).getHostAddress();
            if (it.hasNext()) {
                str = str + "\n";
            }
        }
        return str;
    }

    public static Locale createLocaleFromString(String str) {
        if (str == null) {
            return Locale.getDefault();
        }
        String[] strArrSplit = str.split("_", 3);
        if (1 == strArrSplit.length) {
            return new Locale(strArrSplit[0]);
        }
        if (2 == strArrSplit.length) {
            return new Locale(strArrSplit[0], strArrSplit[1]);
        }
        return new Locale(strArrSplit[0], strArrSplit[1], strArrSplit[2]);
    }

    public static boolean isBatteryPresent(Intent intent) {
        return intent.getBooleanExtra("present", true);
    }

    public static String getBatteryPercentage(Intent intent) {
        int intExtra = intent.getIntExtra("level", 0);
        return String.valueOf((intExtra * 100) / intent.getIntExtra("scale", 100)) + "%";
    }

    public static String getBatteryStatus(Resources resources, Intent intent) {
        int i;
        int intExtra = intent.getIntExtra("plugged", 0);
        int intExtra2 = intent.getIntExtra("status", 1);
        if (intExtra2 != 2) {
            if (intExtra2 == 3) {
                return resources.getString(com.ashd.settings.R.string.battery_info_status_discharging);
            }
            if (intExtra2 == 4) {
                return resources.getString(com.ashd.settings.R.string.battery_info_status_not_charging);
            }
            if (intExtra2 == 5) {
                return resources.getString(com.ashd.settings.R.string.battery_info_status_full);
            }
            return resources.getString(com.ashd.settings.R.string.battery_info_status_unknown);
        }
        String string = resources.getString(com.ashd.settings.R.string.battery_info_status_charging);
        if (intExtra <= 0) {
            return string;
        }
        if (intExtra == 1) {
            i = com.ashd.settings.R.string.battery_info_status_charging_ac;
        } else {
            i = intExtra == 2 ? com.ashd.settings.R.string.battery_info_status_charging_usb : com.ashd.settings.R.string.battery_info_status_charging_wireless;
        }
        return string + " " + resources.getString(i);
    }

    public static void forcePrepareCustomPreferencesList(ViewGroup viewGroup, View view, ListView listView, boolean z) {
        listView.setScrollBarStyle(33554432);
        listView.setClipToPadding(false);
        prepareCustomPreferencesList(viewGroup, view, listView, z);
    }

    public static void prepareCustomPreferencesList(ViewGroup viewGroup, View view, View view2, boolean z) {
        if ((view2.getScrollBarStyle() == 33554432) && (viewGroup instanceof PreferenceFrameLayout)) {
            view.getLayoutParams().removeBorders = true;
            Resources resources = view2.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(com.ashd.settings.R.dimen.settings_side_margin);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(android.R.dimen.action_button_min_width_material);
            if (z) {
                dimensionPixelSize = 0;
            }
            view2.setPaddingRelative(dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize2);
        }
    }

    public static int getTetheringLabel(ConnectivityManager connectivityManager) {
        String[] tetherableUsbRegexs = connectivityManager.getTetherableUsbRegexs();
        String[] tetherableWifiRegexs = connectivityManager.getTetherableWifiRegexs();
        String[] tetherableBluetoothRegexs = connectivityManager.getTetherableBluetoothRegexs();
        boolean z = tetherableUsbRegexs.length != 0;
        boolean z2 = tetherableWifiRegexs.length != 0;
        boolean z3 = tetherableBluetoothRegexs.length != 0;
        if (z2 && z && z3) {
            return com.ashd.settings.R.string.tether_settings_title_all;
        }
        if (z2 && z) {
            return com.ashd.settings.R.string.tether_settings_title_all;
        }
        if (z2 && z3) {
            return com.ashd.settings.R.string.tether_settings_title_all;
        }
        if (z2) {
            return com.ashd.settings.R.string.tether_settings_title_wifi;
        }
        if (z && z3) {
            return com.ashd.settings.R.string.tether_settings_title_usb_bluetooth;
        }
        return z ? com.ashd.settings.R.string.tether_settings_title_usb : com.ashd.settings.R.string.tether_settings_title_bluetooth;
    }

    public static boolean copyMeProfilePhoto(Context context, UserInfo userInfo) {
        InputStream inputStreamOpenContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), ContactsContract.Profile.CONTENT_URI, true);
        if (inputStreamOpenContactPhotoInputStream == null) {
            return false;
        }
        ((UserManager) context.getSystemService("user")).setUserIcon(userInfo != null ? userInfo.id : UserHandle.myUserId(), BitmapFactory.decodeStream(inputStreamOpenContactPhotoInputStream));
        try {
            inputStreamOpenContactPhotoInputStream.close();
        } catch (IOException unused) {
        }
        return true;
    }

    public static String getMeProfileName(Context context, boolean z) {
        if (z) {
            return getProfileDisplayName(context);
        }
        return getShorterNameIfPossible(context);
    }

    private static String getShorterNameIfPossible(Context context) {
        String localProfileGivenName = getLocalProfileGivenName(context);
        return !TextUtils.isEmpty(localProfileGivenName) ? localProfileGivenName : getProfileDisplayName(context);
    }

    private static String getLocalProfileGivenName(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursorQuery = contentResolver.query(ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI, new String[]{"_id"}, "account_type IS NULL AND account_name IS NULL", null, null);
        if (cursorQuery == null) {
            return null;
        }
        try {
            if (cursorQuery.moveToFirst()) {
                long j = cursorQuery.getLong(0);
                cursorQuery.close();
                Cursor cursorQuery2 = contentResolver.query(ContactsContract.Profile.CONTENT_URI.buildUpon().appendPath("data").build(), new String[]{"data2", "data3"}, "raw_contact_id=" + j, null, null);
                if (cursorQuery2 == null) {
                    return null;
                }
                try {
                    if (!cursorQuery2.moveToFirst()) {
                        return null;
                    }
                    String string = cursorQuery2.getString(0);
                    if (TextUtils.isEmpty(string)) {
                        string = cursorQuery2.getString(1);
                    }
                    return string;
                } finally {
                    cursorQuery2.close();
                }
            }
            cursorQuery.close();
            return null;
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    private static final String getProfileDisplayName(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, new String[]{"display_name"}, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        try {
            if (cursorQuery.moveToFirst()) {
                return cursorQuery.getString(0);
            }
            return null;
        } finally {
            cursorQuery.close();
        }
    }

    public static Dialog buildGlobalChangeWarningDialog(Context context, int i, final Runnable runnable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(i);
        builder.setMessage(com.ashd.settings.R.string.global_change_warning);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.Utils.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                runnable.run();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
        return builder.create();
    }

    public static boolean hasMultipleUsers(Context context) {
        return ((UserManager) context.getSystemService("user")).getUsers().size() > 1;
    }

    public static String checkChipType() {
        String str = SystemProperties.get("ro.yunos.product.name", "unknow");
        String str2 = SystemProperties.get("ro.product.device", "unknow");
        String str3 = SystemProperties.get("ro.product.board", "unknow");
        String str4 = SystemProperties.get("ro.yunos.product.chip", "unknow");
        if (str.contains("rk3128h") || str2.contains("rk3128h")) {
            return "rk3128h";
        }
        if (str.contains("m201") || str2.contains("m201") || str3.contains("m201")) {
            return "S805";
        }
        if (str.contains("rk312x") || str2.contains("rk312x") || str2.contains("rk3128_box")) {
            return "rk3128";
        }
        if (str.contains("rk3229") || str2.contains("rk322x_box")) {
            return "rk3229";
        }
        if (str.contains("k200") || str2.contains("k200") || str3.contains("k200")) {
            return "S802";
        }
        if (str.contains("Hi3798MV100") || str2.contains("Hi3798MV100")) {
            return "Hi3798M";
        }
        if (str.contains("mars") || str2.contains("mars")) {
            return "AWA31s";
        }
        if (str.contains("sugar") || str2.contains("sugar") || str3.contains("wing")) {
            return "AWA20";
        }
        if (str.contains("apollo") || str2.contains("apollo")) {
            return "AWA10";
        }
        if (str.contains("elite") || str2.contains("elite") || str3.contains("nuclear")) {
            return "AWA10s";
        }
        if (str.contains("dolphin") || str2.contains("dolphin") || str4.contains("H3")) {
            return "AWH3";
        }
        if (str.contains("cheetah") || str2.contains("cheetah") || str4.contains("H5")) {
            return "AWH5";
        }
        if (str.contains("eagle") || str2.contains("eagle") || str4.contains("H8")) {
            return "AWH8";
        }
        if (str.contains("p212") || str2.contains("p212")) {
            return "S905x";
        }
        if (str.contains("p201") || str2.contains("p201")) {
            return "S905";
        }
        if (str.contains("q201") || str2.contains("q201")) {
            return "S912";
        }
        String str5 = SystemProperties.get("ro.cloudmedia.chip", "unknow");
        return (str5 == null || str5.equals("unknow")) ? "unknow" : str5;
    }

    public static String formatSize(long j, String str) {
        String str2;
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMaximumFractionDigits(2);
        numberInstance.setMinimumFractionDigits(2);
        if (j <= 0) {
            Log.e("formatSize", "size=" + j + ",info=0KB,from=" + str);
            return "0KB";
        }
        if (j < 1048576) {
            str2 = numberInstance.format((j * 1.0f) / 1024) + "KB";
        } else if (j < 1073741824) {
            str2 = numberInstance.format((j * 1.0f) / 1048576) + "MB";
        } else {
            str2 = numberInstance.format((j * 1.0f) / 1073741824) + "GB";
        }
        Log.e("formatSize", "size=" + j + ",info=" + str2 + ",from=" + str);
        return str2;
    }

    public static void fixButtonStyle(Button button) {
        StringBuilder sb = new StringBuilder();
        sb.append("fixButtonStyle =");
        sb.append(button == null ? "NULL" : button.toString());
        Log.e("fixButtonStyle", sb.toString());
        if (button != null) {
            button.setBackgroundResource(com.ashd.settings.R.drawable.button_dialog_positive);
        }
    }

    public static void fixButtonStyle(AlertDialog alertDialog) {
        Log.e("fixButtonStyle", "fixButtonStyle");
        if (alertDialog != null) {
            Button button = alertDialog.getButton(-1);
            if (button != null) {
                button.setBackgroundResource(com.ashd.settings.R.drawable.button_dialog_positive);
            } else {
                Log.e("fixButtonStyle", "fixButtonStyle4");
            }
            Button button2 = alertDialog.getButton(-2);
            if (button2 != null) {
                button2.setBackgroundResource(com.ashd.settings.R.drawable.button_dialog_positive);
            } else {
                Log.e("fixButtonStyle", "fixButtonStyle3");
            }
            Button button3 = alertDialog.getButton(-3);
            if (button3 != null) {
                button3.setBackgroundResource(com.ashd.settings.R.drawable.button_dialog_positive);
                return;
            } else {
                Log.e("fixButtonStyle", "fixButtonStyle2");
                return;
            }
        }
        Log.e("fixButtonStyle", "fixButtonStyle1");
    }

    public static boolean isMusicOrVideoPlay(Context context) {
        return ((AudioManager) context.getSystemService("audio")).isMusicActive();
    }
}
