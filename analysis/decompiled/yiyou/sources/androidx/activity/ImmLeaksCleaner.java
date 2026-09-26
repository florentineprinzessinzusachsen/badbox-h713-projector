package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.e;
import androidx.lifecycle.f;
import androidx.lifecycle.h;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
final class ImmLeaksCleaner implements f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f196b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Field f197c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Field f198d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Field f199e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Activity f200a;

    ImmLeaksCleaner(Activity activity) {
        this.f200a = activity;
    }

    @Override // androidx.lifecycle.f
    public void a(h hVar, e.a aVar) {
        if (aVar != e.a.ON_DESTROY) {
            return;
        }
        if (f196b == 0) {
            a();
        }
        if (f196b == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f200a.getSystemService("input_method");
            try {
                Object obj = f197c.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f198d.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f199e.set(inputMethodManager, null);
                                inputMethodManager.isActive();
                            } catch (IllegalAccessException unused) {
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    } catch (ClassCastException unused2) {
                    } catch (IllegalAccessException unused3) {
                    }
                }
            } catch (IllegalAccessException unused4) {
            }
        }
    }

    private static void a() {
        try {
            f196b = 2;
            f198d = InputMethodManager.class.getDeclaredField("mServedView");
            f198d.setAccessible(true);
            f199e = InputMethodManager.class.getDeclaredField("mNextServedView");
            f199e.setAccessible(true);
            f197c = InputMethodManager.class.getDeclaredField("mH");
            f197c.setAccessible(true);
            f196b = 1;
        } catch (NoSuchFieldException unused) {
        }
    }
}
