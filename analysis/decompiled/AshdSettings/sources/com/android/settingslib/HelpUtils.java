package com.android.settingslib;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.android.internal.logging.MetricsLogger;
import java.net.URISyntaxException;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class HelpUtils {
    private static final String EXTRA_BACKUP_URI = "EXTRA_BACKUP_URI";
    private static final String EXTRA_CONTEXT = "EXTRA_CONTEXT";
    private static final String EXTRA_PRIMARY_COLOR = "EXTRA_PRIMARY_COLOR";
    private static final String EXTRA_THEME = "EXTRA_THEME";
    private static final int MENU_HELP = 101;
    private static final String PARAM_LANGUAGE_CODE = "hl";
    private static final String PARAM_VERSION = "version";
    private static final String TAG = "HelpUtils";
    private static String sCachedVersionCode;

    private HelpUtils() {
    }

    public static boolean prepareHelpMenuItem(Activity activity, Menu menu, String str, String str2) {
        return prepareHelpMenuItem(activity, menu.add(0, 101, 0, R.string.help_feedback_label), str, str2);
    }

    public static boolean prepareHelpMenuItem(Activity activity, Menu menu, int i, String str) {
        return prepareHelpMenuItem(activity, menu.add(0, 101, 0, R.string.help_feedback_label), activity.getString(i), str);
    }

    public static boolean prepareHelpMenuItem(final Activity activity, MenuItem menuItem, String str, String str2) {
        if (Settings.Global.getInt(activity.getContentResolver(), "device_provisioned", 0) == 0) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            menuItem.setVisible(false);
            return false;
        }
        final Intent helpIntent = getHelpIntent(activity, str, str2);
        if (helpIntent != null) {
            menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.android.settingslib.HelpUtils.1
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public boolean onMenuItemClick(MenuItem menuItem2) {
                    MetricsLogger.action(activity, 496, helpIntent.getStringExtra(HelpUtils.EXTRA_CONTEXT));
                    try {
                        activity.startActivityForResult(helpIntent, 0);
                        return true;
                    } catch (ActivityNotFoundException unused) {
                        Log.e(HelpUtils.TAG, "No activity found for intent: " + helpIntent);
                        return true;
                    }
                }
            });
            menuItem.setShowAsAction(0);
            menuItem.setVisible(true);
            return true;
        }
        menuItem.setVisible(false);
        return false;
    }

    public static Intent getHelpIntent(Context context, String str, String str2) {
        if (Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) == 0) {
            return null;
        }
        try {
            Intent uri = Intent.parseUri(str, 3);
            addIntentParameters(context, uri, str2, true);
            if (uri.resolveActivity(context.getPackageManager()) != null) {
                return uri;
            }
            if (uri.hasExtra(EXTRA_BACKUP_URI)) {
                return getHelpIntent(context, uri.getStringExtra(EXTRA_BACKUP_URI), str2);
            }
            return null;
        } catch (URISyntaxException unused) {
            Intent intent = new Intent("android.intent.action.VIEW", uriWithAddedParameters(context, Uri.parse(str)));
            intent.setFlags(276824064);
            return intent;
        }
    }

    public static void addIntentParameters(Context context, Intent intent, String str, boolean z) {
        if (!intent.hasExtra(EXTRA_CONTEXT)) {
            intent.putExtra(EXTRA_CONTEXT, str);
        }
        Resources resources = context.getResources();
        boolean z2 = resources.getBoolean(R.bool.config_sendPackageName);
        if (z && z2) {
            String[] strArr = {resources.getString(R.string.config_helpPackageNameKey)};
            String[] strArr2 = {resources.getString(R.string.config_helpPackageNameValue)};
            String string = resources.getString(R.string.config_helpIntentExtraKey);
            String string2 = resources.getString(R.string.config_helpIntentNameKey);
            String string3 = resources.getString(R.string.config_feedbackIntentExtraKey);
            String string4 = resources.getString(R.string.config_feedbackIntentNameKey);
            intent.putExtra(string, strArr);
            intent.putExtra(string2, strArr2);
            intent.putExtra(string3, strArr);
            intent.putExtra(string4, strArr2);
        }
        intent.putExtra(EXTRA_THEME, 1);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{android.R.attr.colorPrimary});
        intent.putExtra(EXTRA_PRIMARY_COLOR, typedArrayObtainStyledAttributes.getColor(0, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    public static Uri uriWithAddedParameters(Context context, Uri uri) {
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.appendQueryParameter(PARAM_LANGUAGE_CODE, Locale.getDefault().toString());
        if (sCachedVersionCode == null) {
            try {
                sCachedVersionCode = Integer.toString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                builderBuildUpon.appendQueryParameter(PARAM_VERSION, sCachedVersionCode);
            } catch (PackageManager.NameNotFoundException e) {
                Log.wtf(TAG, "Invalid package name for context", e);
            }
        } else {
            builderBuildUpon.appendQueryParameter(PARAM_VERSION, sCachedVersionCode);
        }
        return builderBuildUpon.build();
    }
}
