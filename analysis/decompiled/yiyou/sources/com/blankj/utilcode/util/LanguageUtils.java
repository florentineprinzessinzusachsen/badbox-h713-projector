package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class LanguageUtils {
    private static final String KEY_LOCALE = "KEY_LOCALE";
    private static final String VALUE_FOLLOW_SYSTEM = "VALUE_FOLLOW_SYSTEM";

    private LanguageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void applyLanguage(Locale locale, Class<? extends Activity> cls) {
        if (locale == null) {
            throw new NullPointerException("Argument 'locale' of type Locale (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        applyLanguage(locale, cls, false);
    }

    public static void applySystemLanguage(Class<? extends Activity> cls) {
        applyLanguage(Resources.getSystem().getConfiguration().locale, cls, true);
    }

    private static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        int length;
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null || (length = charSequence.length()) != charSequence2.length()) {
            return false;
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return charSequence.equals(charSequence2);
        }
        for (int i = 0; i < length; i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static String getLauncherActivity() {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(Utils.getApp().getPackageName());
        ResolveInfo next = Utils.getApp().getPackageManager().queryIntentActivities(intent, 0).iterator().next();
        return next != null ? next.activityInfo.name : "no launcher activity";
    }

    private static void updateLanguage(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale2 = configuration.locale;
        if (equals(locale2.getLanguage(), locale.getLanguage()) && equals(locale2.getCountry(), locale.getCountry())) {
            return;
        }
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            configuration.setLocale(locale);
            context.createConfigurationContext(configuration);
        } else {
            configuration.locale = locale;
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static void applySystemLanguage(String str) {
        applyLanguage(Resources.getSystem().getConfiguration().locale, str, true);
    }

    public static void applyLanguage(Locale locale, String str) {
        if (locale != null) {
            applyLanguage(locale, str, false);
            return;
        }
        throw new NullPointerException("Argument 'locale' of type Locale (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static void applyLanguage(Locale locale, Class<? extends Activity> cls, boolean z) {
        if (locale == null) {
            throw new NullPointerException("Argument 'locale' of type Locale (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls == null) {
            applyLanguage(locale, "", z);
        } else {
            applyLanguage(locale, cls.getName(), z);
        }
    }

    private static void applyLanguage(Locale locale, String str, boolean z) {
        if (locale != null) {
            if (z) {
                Utils.getSpUtils4Utils().put(KEY_LOCALE, VALUE_FOLLOW_SYSTEM);
            } else {
                String language = locale.getLanguage();
                String country = locale.getCountry();
                Utils.getSpUtils4Utils().put(KEY_LOCALE, language + "$" + country);
            }
            updateLanguage(Utils.getApp(), locale);
            Intent intent = new Intent();
            if (TextUtils.isEmpty(str)) {
                str = getLauncherActivity();
            }
            intent.setComponent(new ComponentName(Utils.getApp(), str));
            intent.addFlags(335577088);
            Utils.getApp().startActivity(intent);
            return;
        }
        throw new NullPointerException("Argument 'locale' of type Locale (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    static void applyLanguage(Activity activity) {
        if (activity != null) {
            String string = Utils.getSpUtils4Utils().getString(KEY_LOCALE);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            if (VALUE_FOLLOW_SYSTEM.equals(string)) {
                Locale locale = Resources.getSystem().getConfiguration().locale;
                updateLanguage(Utils.getApp(), locale);
                updateLanguage(activity, locale);
                return;
            }
            String[] strArrSplit = string.split("\\$");
            if (strArrSplit.length != 2) {
                Log.e("LanguageUtils", "The string of " + string + " is not in the correct format.");
                return;
            }
            Locale locale2 = new Locale(strArrSplit[0], strArrSplit[1]);
            updateLanguage(Utils.getApp(), locale2);
            updateLanguage(activity, locale2);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }
}
