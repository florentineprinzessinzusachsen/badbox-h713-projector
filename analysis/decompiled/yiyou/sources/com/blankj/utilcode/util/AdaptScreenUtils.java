package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AdaptScreenUtils {
    private static List<Field> sMetricsFields;

    private AdaptScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Resources adaptHeight(Resources resources, int i) {
        return adaptHeight(resources, i, false);
    }

    public static Resources adaptWidth(Resources resources, int i) {
        applyDisplayMetrics(resources, (resources.getDisplayMetrics().widthPixels * 72.0f) / i);
        return resources;
    }

    private static void applyDisplayMetrics(Resources resources, float f2) {
        resources.getDisplayMetrics().xdpi = f2;
        Utils.getApp().getResources().getDisplayMetrics().xdpi = f2;
        applyOtherDisplayMetrics(resources, f2);
    }

    private static void applyMetricsFields(Resources resources, float f2) {
        Iterator<Field> it = sMetricsFields.iterator();
        while (it.hasNext()) {
            try {
                DisplayMetrics displayMetrics = (DisplayMetrics) it.next().get(resources);
                if (displayMetrics != null) {
                    displayMetrics.xdpi = f2;
                }
            } catch (Exception e2) {
                Log.e("AdaptScreenUtils", "applyMetricsFields: " + e2);
            }
        }
    }

    private static void applyOtherDisplayMetrics(Resources resources, float f2) {
        if (sMetricsFields != null) {
            applyMetricsFields(resources, f2);
            return;
        }
        sMetricsFields = new ArrayList();
        Class<?> superclass = resources.getClass();
        Field[] declaredFields = superclass.getDeclaredFields();
        while (declaredFields != null && declaredFields.length > 0) {
            for (Field field : declaredFields) {
                if (field.getType().isAssignableFrom(DisplayMetrics.class)) {
                    field.setAccessible(true);
                    DisplayMetrics metricsFromField = getMetricsFromField(resources, field);
                    if (metricsFromField != null) {
                        sMetricsFields.add(field);
                        metricsFromField.xdpi = f2;
                    }
                }
            }
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                return;
            } else {
                declaredFields = superclass.getDeclaredFields();
            }
        }
    }

    public static Resources closeAdapt(Resources resources) {
        applyDisplayMetrics(resources, Resources.getSystem().getDisplayMetrics().density * 72.0f);
        return resources;
    }

    private static DisplayMetrics getMetricsFromField(Resources resources, Field field) {
        try {
            return (DisplayMetrics) field.get(resources);
        } catch (Exception e2) {
            Log.e("AdaptScreenUtils", "getMetricsFromField: " + e2);
            return null;
        }
    }

    private static int getNavBarHeight(Resources resources) {
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int pt2Px(float f2) {
        double d2 = (f2 * Utils.getApp().getResources().getDisplayMetrics().xdpi) / 72.0f;
        Double.isNaN(d2);
        return (int) (d2 + 0.5d);
    }

    public static int px2Pt(float f2) {
        double d2 = (f2 * 72.0f) / Utils.getApp().getResources().getDisplayMetrics().xdpi;
        Double.isNaN(d2);
        return (int) (d2 + 0.5d);
    }

    public static Resources adaptHeight(Resources resources, int i, boolean z) {
        applyDisplayMetrics(resources, ((resources.getDisplayMetrics().heightPixels + (z ? getNavBarHeight(resources) : 0)) * 72.0f) / i);
        return resources;
    }
}
