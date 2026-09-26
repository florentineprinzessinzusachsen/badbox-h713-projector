package androidx.appcompat.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.appcompat.widget.u;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: AppCompatResources.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<TypedValue> f211a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final WeakHashMap<Context, SparseArray<C0004a>> f212b = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f213c = new Object();

    /* JADX INFO: renamed from: androidx.appcompat.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppCompatResources.java */
    private static class C0004a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ColorStateList f214a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Configuration f215b;

        C0004a(ColorStateList colorStateList, Configuration configuration) {
            this.f214a = colorStateList;
            this.f215b = configuration;
        }
    }

    private static ColorStateList a(Context context, int i) {
        C0004a c0004a;
        synchronized (f213c) {
            SparseArray<C0004a> sparseArray = f212b.get(context);
            if (sparseArray != null && sparseArray.size() > 0 && (c0004a = sparseArray.get(i)) != null) {
                if (c0004a.f215b.equals(context.getResources().getConfiguration())) {
                    return c0004a.f214a;
                }
                sparseArray.remove(i);
            }
            return null;
        }
    }

    public static ColorStateList b(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        ColorStateList colorStateListA = a(context, i);
        if (colorStateListA != null) {
            return colorStateListA;
        }
        ColorStateList colorStateListD = d(context, i);
        if (colorStateListD == null) {
            return androidx.core.content.a.b(context, i);
        }
        a(context, i, colorStateListD);
        return colorStateListD;
    }

    public static Drawable c(Context context, int i) {
        return u.a().a(context, i);
    }

    private static ColorStateList d(Context context, int i) {
        if (e(context, i)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return androidx.core.content.c.a.a(resources, resources.getXml(i), context.getTheme());
        } catch (Exception e2) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e2);
            return null;
        }
    }

    private static boolean e(Context context, int i) {
        Resources resources = context.getResources();
        TypedValue typedValueA = a();
        resources.getValue(i, typedValueA, true);
        int i2 = typedValueA.type;
        return i2 >= 28 && i2 <= 31;
    }

    private static void a(Context context, int i, ColorStateList colorStateList) {
        synchronized (f213c) {
            SparseArray<C0004a> sparseArray = f212b.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                f212b.put(context, sparseArray);
            }
            sparseArray.append(i, new C0004a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    private static TypedValue a() {
        TypedValue typedValue = f211a.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        f211a.set(typedValue2);
        return typedValue2;
    }
}
