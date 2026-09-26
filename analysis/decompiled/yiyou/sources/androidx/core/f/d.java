package androidx.core.f;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: KeyEventDispatcher.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f1072a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f1073b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f1074c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Field f1075d;

    /* JADX INFO: compiled from: KeyEventDispatcher.java */
    public interface a {
        boolean a(KeyEvent keyEvent);
    }

    public static boolean a(View view, KeyEvent keyEvent) {
        return t.b(view, keyEvent);
    }

    public static boolean a(a aVar, View view, Window.Callback callback, KeyEvent keyEvent) {
        if (aVar == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return aVar.a(keyEvent);
        }
        if (callback instanceof Activity) {
            return a((Activity) callback, keyEvent);
        }
        if (callback instanceof Dialog) {
            return a((Dialog) callback, keyEvent);
        }
        return (view != null && t.a(view, keyEvent)) || aVar.a(keyEvent);
    }

    private static boolean a(ActionBar actionBar, KeyEvent keyEvent) {
        if (!f1072a) {
            try {
                f1073b = actionBar.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
            } catch (NoSuchMethodException unused) {
            }
            f1072a = true;
        }
        Method method = f1073b;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(actionBar, keyEvent)).booleanValue();
            } catch (IllegalAccessException | InvocationTargetException unused2) {
            }
        }
        return false;
    }

    private static boolean a(Activity activity, KeyEvent keyEvent) {
        activity.onUserInteraction();
        Window window = activity.getWindow();
        if (window.hasFeature(8)) {
            ActionBar actionBar = activity.getActionBar();
            if (keyEvent.getKeyCode() == 82 && actionBar != null && a(actionBar, keyEvent)) {
                return true;
            }
        }
        if (window.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View decorView = window.getDecorView();
        if (t.a(decorView, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(activity, decorView != null ? decorView.getKeyDispatcherState() : null, activity);
    }

    private static DialogInterface.OnKeyListener a(Dialog dialog) {
        if (!f1074c) {
            try {
                f1075d = Dialog.class.getDeclaredField("mOnKeyListener");
                f1075d.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f1074c = true;
        }
        Field field = f1075d;
        if (field == null) {
            return null;
        }
        try {
            return (DialogInterface.OnKeyListener) field.get(dialog);
        } catch (IllegalAccessException unused2) {
            return null;
        }
    }

    private static boolean a(Dialog dialog, KeyEvent keyEvent) {
        DialogInterface.OnKeyListener onKeyListenerA = a(dialog);
        if (onKeyListenerA != null && onKeyListenerA.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
            return true;
        }
        Window window = dialog.getWindow();
        if (window.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View decorView = window.getDecorView();
        if (t.a(decorView, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(dialog, decorView != null ? decorView.getKeyDispatcherState() : null, dialog);
    }
}
