package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;

/* JADX INFO: compiled from: ThemeUtils.java */
/* JADX INFO: loaded from: classes.dex */
class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<TypedValue> f845a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int[] f846b = {-16842910};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final int[] f847c = {R.attr.state_focused};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final int[] f848d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final int[] f849e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final int[] f850f;
    private static final int[] g;

    static {
        new int[1][0] = 16843518;
        f848d = new int[]{R.attr.state_pressed};
        f849e = new int[]{R.attr.state_checked};
        new int[1][0] = 16842913;
        f850f = new int[0];
        g = new int[1];
    }

    public static int a(Context context, int i) {
        ColorStateList colorStateListC = c(context, i);
        if (colorStateListC != null && colorStateListC.isStateful()) {
            return colorStateListC.getColorForState(f846b, colorStateListC.getDefaultColor());
        }
        TypedValue typedValueA = a();
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValueA, true);
        return a(context, i, typedValueA.getFloat());
    }

    public static int b(Context context, int i) {
        int[] iArr = g;
        iArr[0] = i;
        d0 d0VarA = d0.a(context, (AttributeSet) null, iArr);
        try {
            return d0VarA.a(0, 0);
        } finally {
            d0VarA.a();
        }
    }

    public static ColorStateList c(Context context, int i) {
        int[] iArr = g;
        iArr[0] = i;
        d0 d0VarA = d0.a(context, (AttributeSet) null, iArr);
        try {
            return d0VarA.a(0);
        } finally {
            d0VarA.a();
        }
    }

    private static TypedValue a() {
        TypedValue typedValue = f845a.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        f845a.set(typedValue2);
        return typedValue2;
    }

    static int a(Context context, int i, float f2) {
        int iB = b(context, i);
        return androidx.core.a.a.c(iB, Math.round(Color.alpha(iB) * f2));
    }
}
