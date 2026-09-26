package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: VectorEnabledTintResources.java */
/* JADX INFO: loaded from: classes.dex */
public class i0 extends Resources {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f777b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WeakReference<Context> f778a;

    public i0(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f778a = new WeakReference<>(context);
    }

    public static boolean b() {
        return a() && Build.VERSION.SDK_INT <= 20;
    }

    final Drawable a(int i) {
        return super.getDrawable(i);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        Context context = this.f778a.get();
        return context != null ? u.a().a(context, this, i) : super.getDrawable(i);
    }

    public static boolean a() {
        return f777b;
    }
}
