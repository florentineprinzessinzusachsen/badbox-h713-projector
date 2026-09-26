package com.android.settingslib.drawer;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class TileUtils {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_TIMING = false;
    private static final String EXTRA_CATEGORY_KEY = "com.android.settings.category";
    private static final String EXTRA_SETTINGS_ACTION = "com.android.settings.action.EXTRA_SETTINGS";
    private static final String LOG_TAG = "TileUtils";
    private static final String MANUFACTURER_DEFAULT_CATEGORY = "com.android.settings.category.device";
    private static final String MANUFACTURER_SETTINGS = "com.android.settings.MANUFACTURER_APPLICATION_SETTING";
    public static final String META_DATA_PREFERENCE_ICON = "com.android.settings.icon";
    public static final String META_DATA_PREFERENCE_SUMMARY = "com.android.settings.summary";
    public static final String META_DATA_PREFERENCE_TITLE = "com.android.settings.title";
    private static final String OPERATOR_DEFAULT_CATEGORY = "com.android.settings.category.wireless";
    private static final String OPERATOR_SETTINGS = "com.android.settings.OPERATOR_APPLICATION_SETTING";
    private static final String SETTINGS_ACTION = "com.android.settings.action.SETTINGS";
    private static final String SETTING_PKG = "com.android.settings";
    public static final Comparator<Tile> TILE_COMPARATOR = new Comparator<Tile>() { // from class: com.android.settingslib.drawer.TileUtils.1
        @Override // java.util.Comparator
        public int compare(Tile tile, Tile tile2) {
            return tile2.priority - tile.priority;
        }
    };
    private static final Comparator<DashboardCategory> CATEGORY_COMPARATOR = new Comparator<DashboardCategory>() { // from class: com.android.settingslib.drawer.TileUtils.2
        @Override // java.util.Comparator
        public int compare(DashboardCategory dashboardCategory, DashboardCategory dashboardCategory2) {
            return dashboardCategory2.priority - dashboardCategory.priority;
        }
    };

    public static List<DashboardCategory> getCategories(Context context, HashMap<Pair<String, String>, Tile> map) {
        System.currentTimeMillis();
        boolean z = Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 0;
        ArrayList<Tile> arrayList = new ArrayList();
        for (UserHandle userHandle : UserManager.get(context).getUserProfiles()) {
            if (userHandle.getIdentifier() == ActivityManager.getCurrentUser()) {
                getTilesForAction(context, userHandle, SETTINGS_ACTION, map, null, arrayList, true);
                getTilesForAction(context, userHandle, OPERATOR_SETTINGS, map, OPERATOR_DEFAULT_CATEGORY, arrayList, false);
                getTilesForAction(context, userHandle, MANUFACTURER_SETTINGS, map, MANUFACTURER_DEFAULT_CATEGORY, arrayList, false);
            }
            if (z) {
                getTilesForAction(context, userHandle, EXTRA_SETTINGS_ACTION, map, null, arrayList, false);
            }
        }
        boolean zEquals = "true".equals(SystemProperties.get("ro.rk.bt_enable"));
        HashMap map2 = new HashMap();
        for (Tile tile : arrayList) {
            DashboardCategory dashboardCategoryCreateCategory = (DashboardCategory) map2.get(tile.category);
            if (dashboardCategoryCreateCategory == null) {
                dashboardCategoryCreateCategory = createCategory(context, tile.category);
                if (dashboardCategoryCreateCategory == null) {
                    Log.w(LOG_TAG, "Couldn't find category " + tile.category);
                } else {
                    map2.put(dashboardCategoryCreateCategory.key, dashboardCategoryCreateCategory);
                }
            }
            Intent intent = tile.intent;
            if (zEquals || intent == null || !intent.toString().contains("com.android.settings/.Settings$BluetoothSettingsActivity")) {
                dashboardCategoryCreateCategory.addTile(tile);
            }
        }
        ArrayList arrayList2 = new ArrayList(map2.values());
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Collections.sort(((DashboardCategory) it.next()).tiles, TILE_COMPARATOR);
        }
        Collections.sort(arrayList2, CATEGORY_COMPARATOR);
        return arrayList2;
    }

    private static DashboardCategory createCategory(Context context, String str) {
        DashboardCategory dashboardCategory = new DashboardCategory();
        dashboardCategory.key = str;
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(new Intent(str), 0);
        if (listQueryIntentActivities.size() == 0) {
            return null;
        }
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            if (resolveInfo.system) {
                dashboardCategory.title = resolveInfo.activityInfo.loadLabel(packageManager);
                dashboardCategory.priority = SETTING_PKG.equals(resolveInfo.activityInfo.applicationInfo.packageName) ? resolveInfo.priority : 0;
            }
        }
        return dashboardCategory;
    }

    private static void getTilesForAction(Context context, UserHandle userHandle, String str, Map<Pair<String, String>, Tile> map, String str2, ArrayList<Tile> arrayList, boolean z) {
        Intent intent = new Intent(str);
        if (z) {
            intent.setPackage(SETTING_PKG);
        }
        getTilesForIntent(context, userHandle, intent, map, str2, arrayList, z, true);
    }

    public static void getTilesForIntent(Context context, UserHandle userHandle, Intent intent, Map<Pair<String, String>, Tile> map, String str, List<Tile> list, boolean z, boolean z2) {
        PackageManager packageManager = context.getPackageManager();
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivitiesAsUser(intent, 128, userHandle.getIdentifier())) {
            if (resolveInfo.system) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                Bundle bundle = activityInfo.metaData;
                if (z2 && ((bundle == null || !bundle.containsKey(EXTRA_CATEGORY_KEY)) && str == null)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Found ");
                    sb.append(resolveInfo.activityInfo.name);
                    sb.append(" for intent ");
                    sb.append(intent);
                    sb.append(" missing metadata ");
                    sb.append(bundle == null ? "" : EXTRA_CATEGORY_KEY);
                    Log.w(LOG_TAG, sb.toString());
                } else {
                    String string = bundle.getString(EXTRA_CATEGORY_KEY);
                    Pair<String, String> pair = new Pair<>(activityInfo.packageName, activityInfo.name);
                    Tile tile = map.get(pair);
                    if (tile == null) {
                        tile = new Tile();
                        tile.intent = new Intent().setClassName(activityInfo.packageName, activityInfo.name);
                        tile.category = string;
                        tile.priority = z ? resolveInfo.priority : 0;
                        tile.metaData = activityInfo.metaData;
                        updateTileData(context, tile, activityInfo, activityInfo.applicationInfo, packageManager);
                        map.put(pair, tile);
                    }
                    if (!tile.userHandle.contains(userHandle)) {
                        tile.userHandle.add(userHandle);
                    }
                    if (!list.contains(tile)) {
                        list.add(tile);
                    }
                }
            }
        }
    }

    private static DashboardCategory getCategory(List<DashboardCategory> list, String str) {
        for (DashboardCategory dashboardCategory : list) {
            if (str.equals(dashboardCategory.key)) {
                return dashboardCategory;
            }
        }
        return null;
    }

    private static boolean updateTileData(Context context, Tile tile, ActivityInfo activityInfo, ApplicationInfo applicationInfo, PackageManager packageManager) {
        String string;
        String string2;
        int i = 0;
        if (!applicationInfo.isSystemApp()) {
            return false;
        }
        String str = null;
        try {
            Resources resourcesForApplication = packageManager.getResourcesForApplication(applicationInfo.packageName);
            Bundle bundle = activityInfo.metaData;
            if (resourcesForApplication == null || bundle == null) {
                string = null;
            } else {
                i = bundle.containsKey(META_DATA_PREFERENCE_ICON) ? bundle.getInt(META_DATA_PREFERENCE_ICON) : 0;
                if (!bundle.containsKey(META_DATA_PREFERENCE_TITLE)) {
                    string = null;
                } else if (bundle.get(META_DATA_PREFERENCE_TITLE) instanceof Integer) {
                    string = resourcesForApplication.getString(bundle.getInt(META_DATA_PREFERENCE_TITLE));
                } else {
                    string = bundle.getString(META_DATA_PREFERENCE_TITLE);
                }
                try {
                    if (bundle.containsKey(META_DATA_PREFERENCE_SUMMARY)) {
                        if (bundle.get(META_DATA_PREFERENCE_SUMMARY) instanceof Integer) {
                            string2 = resourcesForApplication.getString(bundle.getInt(META_DATA_PREFERENCE_SUMMARY));
                        } else {
                            string2 = bundle.getString(META_DATA_PREFERENCE_SUMMARY);
                        }
                        str = string2;
                    }
                } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
                }
            }
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused2) {
        }
        if (TextUtils.isEmpty(string)) {
            string = activityInfo.loadLabel(packageManager).toString();
        }
        if (i == 0) {
            i = activityInfo.icon;
        }
        tile.icon = Icon.createWithResource(activityInfo.packageName, i);
        tile.title = string;
        tile.summary = str;
        tile.intent = new Intent().setClassName(activityInfo.packageName, activityInfo.name);
        return true;
    }
}
