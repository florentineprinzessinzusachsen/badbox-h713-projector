package androidx.core.widget;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import androidx.core.f.t;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: PopupWindowCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Method f1163a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f1164b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Field f1165c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static boolean f1166d;

    public static void a(PopupWindow popupWindow, View view, int i, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 19) {
            popupWindow.showAsDropDown(view, i, i2, i3);
            return;
        }
        if ((androidx.core.f.c.a(i3, t.j(view)) & 7) == 5) {
            i -= popupWindow.getWidth() - view.getWidth();
        }
        popupWindow.showAsDropDown(view, i, i2);
    }

    public static void a(PopupWindow popupWindow, boolean z) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 23) {
            popupWindow.setOverlapAnchor(z);
            return;
        }
        if (i >= 21) {
            if (!f1166d) {
                try {
                    f1165c = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    f1165c.setAccessible(true);
                } catch (NoSuchFieldException e2) {
                    Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", e2);
                }
                f1166d = true;
            }
            Field field = f1165c;
            if (field != null) {
                try {
                    field.set(popupWindow, Boolean.valueOf(z));
                } catch (IllegalAccessException e3) {
                    Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", e3);
                }
            }
        }
    }

    public static void a(PopupWindow popupWindow, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setWindowLayoutType(i);
            return;
        }
        if (!f1164b) {
            try {
                f1163a = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                f1163a.setAccessible(true);
            } catch (Exception unused) {
            }
            f1164b = true;
        }
        Method method = f1163a;
        if (method != null) {
            try {
                method.invoke(popupWindow, Integer.valueOf(i));
            } catch (Exception unused2) {
            }
        }
    }
}
