package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: TintContextWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class a0 extends ContextWrapper {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f698c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static ArrayList<WeakReference<a0>> f699d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Resources f700a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources.Theme f701b;

    private a0(Context context) {
        super(context);
        if (!i0.b()) {
            this.f700a = new c0(this, context.getResources());
            this.f701b = null;
        } else {
            this.f700a = new i0(this, context.getResources());
            this.f701b = this.f700a.newTheme();
            this.f701b.setTo(context.getTheme());
        }
    }

    private static boolean a(Context context) {
        if ((context instanceof a0) || (context.getResources() instanceof c0) || (context.getResources() instanceof i0)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || i0.b();
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (f698c) {
            if (f699d == null) {
                f699d = new ArrayList<>();
            } else {
                for (int size = f699d.size() - 1; size >= 0; size--) {
                    WeakReference<a0> weakReference = f699d.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        f699d.remove(size);
                    }
                }
                for (int size2 = f699d.size() - 1; size2 >= 0; size2--) {
                    WeakReference<a0> weakReference2 = f699d.get(size2);
                    a0 a0Var = weakReference2 != null ? weakReference2.get() : null;
                    if (a0Var != null && a0Var.getBaseContext() == context) {
                        return a0Var;
                    }
                }
            }
            a0 a0Var2 = new a0(context);
            f699d.add(new WeakReference<>(a0Var2));
            return a0Var2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f700a.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f700a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f701b;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.f701b;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
