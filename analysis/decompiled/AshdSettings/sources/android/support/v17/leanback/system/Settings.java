package android.support.v17.leanback.system;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.support.annotation.RestrictTo;
import android.support.v17.leanback.widget.ShadowOverlayContainer;

/* JADX INFO: loaded from: classes.dex */
public class Settings {
    private static final String ACTION_PARTNER_CUSTOMIZATION = "android.support.v17.leanback.action.PARTNER_CUSTOMIZATION";
    private static final boolean DEBUG = false;
    public static final String PREFER_STATIC_SHADOWS = "PREFER_STATIC_SHADOWS";
    private static final String TAG = "Settings";
    private static Settings sInstance;
    private boolean mPreferStaticShadows;

    public static Settings getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Settings(context);
        }
        return sInstance;
    }

    private Settings(Context context) {
        generateShadowSetting(getCustomizations(context));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean preferStaticShadows() {
        return this.mPreferStaticShadows;
    }

    public boolean getBoolean(String str) {
        return getOrSetBoolean(str, false, false);
    }

    public void setBoolean(String str, boolean z) {
        getOrSetBoolean(str, true, z);
    }

    boolean getOrSetBoolean(String str, boolean z, boolean z2) {
        if (str.compareTo(PREFER_STATIC_SHADOWS) != 0) {
            throw new IllegalArgumentException("Invalid key");
        }
        if (!z) {
            return this.mPreferStaticShadows;
        }
        this.mPreferStaticShadows = z2;
        return z2;
    }

    private void generateShadowSetting(Customizations customizations) {
        if (ShadowOverlayContainer.supportsDynamicShadow()) {
            this.mPreferStaticShadows = false;
            if (customizations != null) {
                this.mPreferStaticShadows = customizations.getBoolean("leanback_prefer_static_shadows", this.mPreferStaticShadows);
                return;
            }
            return;
        }
        this.mPreferStaticShadows = true;
    }

    static class Customizations {
        String mPackageName;
        Resources mResources;

        public Customizations(Resources resources, String str) {
            this.mResources = resources;
            this.mPackageName = str;
        }

        public boolean getBoolean(String str, boolean z) {
            int identifier = this.mResources.getIdentifier(str, "bool", this.mPackageName);
            return identifier > 0 ? this.mResources.getBoolean(identifier) : z;
        }
    }

    private Customizations getCustomizations(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        Resources resourcesForApplication = null;
        String str = null;
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(new Intent(ACTION_PARTNER_CUSTOMIZATION), 0)) {
            String str2 = resolveInfo.activityInfo.packageName;
            if (str2 != null && isSystemApp(resolveInfo)) {
                try {
                    resourcesForApplication = packageManager.getResourcesForApplication(str2);
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            if (resourcesForApplication != null) {
                str = str2;
                break;
            }
            str = str2;
        }
        if (resourcesForApplication == null) {
            return null;
        }
        return new Customizations(resourcesForApplication, str);
    }

    private static boolean isSystemApp(ResolveInfo resolveInfo) {
        return (resolveInfo.activityInfo == null || (resolveInfo.activityInfo.applicationInfo.flags & 1) == 0) ? false : true;
    }
}
