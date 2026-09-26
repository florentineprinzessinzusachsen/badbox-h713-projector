package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.LocaleList;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* JADX INFO: compiled from: AppCompatTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TextView f785a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b0 f786b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b0 f787c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b0 f788d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b0 f789e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b0 f790f;
    private b0 g;
    private b0 h;
    private final m i;
    private int j = 0;
    private int k = -1;
    private Typeface l;
    private boolean m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    private static class a extends androidx.core.content.c.f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<l> f791a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f792b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f793c;

        /* JADX INFO: renamed from: androidx.appcompat.widget.l$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AppCompatTextHelper.java */
        private class RunnableC0010a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final WeakReference<l> f794a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final Typeface f795b;

            RunnableC0010a(a aVar, WeakReference<l> weakReference, Typeface typeface) {
                this.f794a = weakReference;
                this.f795b = typeface;
            }

            @Override // java.lang.Runnable
            public void run() {
                l lVar = this.f794a.get();
                if (lVar == null) {
                    return;
                }
                lVar.a(this.f795b);
            }
        }

        a(l lVar, int i, int i2) {
            this.f791a = new WeakReference<>(lVar);
            this.f792b = i;
            this.f793c = i2;
        }

        @Override // androidx.core.content.c.f.a
        public void a(int i) {
        }

        @Override // androidx.core.content.c.f.a
        public void a(Typeface typeface) {
            int i;
            l lVar = this.f791a.get();
            if (lVar == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 28 && (i = this.f792b) != -1) {
                typeface = Typeface.create(typeface, i, (this.f793c & 2) != 0);
            }
            lVar.a(new RunnableC0010a(this, this.f791a, typeface));
        }
    }

    l(TextView textView) {
        this.f785a = textView;
        this.i = new m(this.f785a);
    }

    private void l() {
        b0 b0Var = this.h;
        this.f786b = b0Var;
        this.f787c = b0Var;
        this.f788d = b0Var;
        this.f789e = b0Var;
        this.f790f = b0Var;
        this.g = b0Var;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x0110  */
    /* JADX WARN: Code duplicated, block: B:49:0x0117  */
    /* JADX WARN: Code duplicated, block: B:55:0x012b  */
    @SuppressLint({"NewApi"})
    void a(AttributeSet attributeSet, int i) {
        String strD;
        ColorStateList colorStateListA;
        String strD2;
        ColorStateList colorStateListA2;
        boolean z;
        boolean zA;
        ColorStateList colorStateListA3;
        int i2;
        Context context = this.f785a.getContext();
        e eVarB = e.b();
        d0 d0VarA = d0.a(context, attributeSet, R$styleable.AppCompatTextHelper, i, 0);
        int iG = d0VarA.g(R$styleable.AppCompatTextHelper_android_textAppearance, -1);
        if (d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableLeft)) {
            this.f786b = a(context, eVarB, d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableTop)) {
            this.f787c = a(context, eVarB, d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableRight)) {
            this.f788d = a(context, eVarB, d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableBottom)) {
            this.f789e = a(context, eVarB, d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableBottom, 0));
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableStart)) {
                this.f790f = a(context, eVarB, d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableStart, 0));
            }
            if (d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableEnd)) {
                this.g = a(context, eVarB, d0VarA.g(R$styleable.AppCompatTextHelper_android_drawableEnd, 0));
            }
        }
        d0VarA.a();
        boolean z2 = this.f785a.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (iG != -1) {
            d0 d0VarA2 = d0.a(context, iG, R$styleable.TextAppearance);
            if (z2 || !d0VarA2.g(R$styleable.TextAppearance_textAllCaps)) {
                z = false;
                zA = false;
            } else {
                zA = d0VarA2.a(R$styleable.TextAppearance_textAllCaps, false);
                z = true;
            }
            a(context, d0VarA2);
            if (Build.VERSION.SDK_INT < 23) {
                ColorStateList colorStateListA4 = d0VarA2.g(R$styleable.TextAppearance_android_textColor) ? d0VarA2.a(R$styleable.TextAppearance_android_textColor) : null;
                colorStateListA = d0VarA2.g(R$styleable.TextAppearance_android_textColorHint) ? d0VarA2.a(R$styleable.TextAppearance_android_textColorHint) : null;
                if (d0VarA2.g(R$styleable.TextAppearance_android_textColorLink)) {
                    ColorStateList colorStateList = colorStateListA4;
                    colorStateListA3 = d0VarA2.a(R$styleable.TextAppearance_android_textColorLink);
                    colorStateListA2 = colorStateList;
                } else {
                    colorStateListA2 = colorStateListA4;
                }
                if (d0VarA2.g(R$styleable.TextAppearance_textLocale)) {
                    strD = d0VarA2.d(R$styleable.TextAppearance_textLocale);
                } else {
                    strD = null;
                }
                if (Build.VERSION.SDK_INT >= 26 || !d0VarA2.g(R$styleable.TextAppearance_fontVariationSettings)) {
                    strD2 = null;
                } else {
                    strD2 = d0VarA2.d(R$styleable.TextAppearance_fontVariationSettings);
                }
                d0VarA2.a();
            } else {
                colorStateListA = null;
                colorStateListA2 = null;
            }
            colorStateListA3 = null;
            if (d0VarA2.g(R$styleable.TextAppearance_textLocale)) {
                strD = d0VarA2.d(R$styleable.TextAppearance_textLocale);
            } else {
                strD = null;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                strD2 = null;
            } else {
                strD2 = null;
            }
            d0VarA2.a();
        } else {
            strD = null;
            colorStateListA = null;
            strD2 = null;
            colorStateListA2 = null;
            z = false;
            zA = false;
            colorStateListA3 = null;
        }
        d0 d0VarA3 = d0.a(context, attributeSet, R$styleable.TextAppearance, i, 0);
        if (!z2 && d0VarA3.g(R$styleable.TextAppearance_textAllCaps)) {
            zA = d0VarA3.a(R$styleable.TextAppearance_textAllCaps, false);
            z = true;
        }
        if (Build.VERSION.SDK_INT < 23) {
            if (d0VarA3.g(R$styleable.TextAppearance_android_textColor)) {
                colorStateListA2 = d0VarA3.a(R$styleable.TextAppearance_android_textColor);
            }
            if (d0VarA3.g(R$styleable.TextAppearance_android_textColorHint)) {
                colorStateListA = d0VarA3.a(R$styleable.TextAppearance_android_textColorHint);
            }
            if (d0VarA3.g(R$styleable.TextAppearance_android_textColorLink)) {
                colorStateListA3 = d0VarA3.a(R$styleable.TextAppearance_android_textColorLink);
            }
        }
        if (d0VarA3.g(R$styleable.TextAppearance_textLocale)) {
            strD = d0VarA3.d(R$styleable.TextAppearance_textLocale);
        }
        if (Build.VERSION.SDK_INT >= 26 && d0VarA3.g(R$styleable.TextAppearance_fontVariationSettings)) {
            strD2 = d0VarA3.d(R$styleable.TextAppearance_fontVariationSettings);
        }
        if (Build.VERSION.SDK_INT >= 28 && d0VarA3.g(R$styleable.TextAppearance_android_textSize) && d0VarA3.c(R$styleable.TextAppearance_android_textSize, -1) == 0) {
            this.f785a.setTextSize(0, 0.0f);
        }
        a(context, d0VarA3);
        d0VarA3.a();
        if (colorStateListA2 != null) {
            this.f785a.setTextColor(colorStateListA2);
        }
        if (colorStateListA != null) {
            this.f785a.setHintTextColor(colorStateListA);
        }
        if (colorStateListA3 != null) {
            this.f785a.setLinkTextColor(colorStateListA3);
        }
        if (!z2 && z) {
            a(zA);
        }
        Typeface typeface = this.l;
        if (typeface != null) {
            if (this.k == -1) {
                this.f785a.setTypeface(typeface, this.j);
            } else {
                this.f785a.setTypeface(typeface);
            }
        }
        if (strD2 != null) {
            this.f785a.setFontVariationSettings(strD2);
        }
        if (strD != null) {
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 24) {
                this.f785a.setTextLocales(LocaleList.forLanguageTags(strD));
            } else if (i3 >= 21) {
                this.f785a.setTextLocale(Locale.forLanguageTag(strD.substring(0, strD.indexOf(44))));
            }
        }
        this.i.a(attributeSet, i);
        if (androidx.core.widget.b.E && this.i.f() != 0) {
            int[] iArrE = this.i.e();
            if (iArrE.length > 0) {
                if (this.f785a.getAutoSizeStepGranularity() != -1.0f) {
                    this.f785a.setAutoSizeTextTypeUniformWithConfiguration(this.i.c(), this.i.b(), this.i.d(), 0);
                } else {
                    this.f785a.setAutoSizeTextTypeUniformWithPresetSizes(iArrE, 0);
                }
            }
        }
        d0 d0VarA4 = d0.a(context, attributeSet, R$styleable.AppCompatTextView);
        int iG2 = d0VarA4.g(R$styleable.AppCompatTextView_drawableLeftCompat, -1);
        Drawable drawableA = iG2 != -1 ? eVarB.a(context, iG2) : null;
        int iG3 = d0VarA4.g(R$styleable.AppCompatTextView_drawableTopCompat, -1);
        Drawable drawableA2 = iG3 != -1 ? eVarB.a(context, iG3) : null;
        int iG4 = d0VarA4.g(R$styleable.AppCompatTextView_drawableRightCompat, -1);
        Drawable drawableA3 = iG4 != -1 ? eVarB.a(context, iG4) : null;
        int iG5 = d0VarA4.g(R$styleable.AppCompatTextView_drawableBottomCompat, -1);
        Drawable drawableA4 = iG5 != -1 ? eVarB.a(context, iG5) : null;
        int iG6 = d0VarA4.g(R$styleable.AppCompatTextView_drawableStartCompat, -1);
        Drawable drawableA5 = iG6 != -1 ? eVarB.a(context, iG6) : null;
        int iG7 = d0VarA4.g(R$styleable.AppCompatTextView_drawableEndCompat, -1);
        a(drawableA, drawableA2, drawableA3, drawableA4, drawableA5, iG7 != -1 ? eVarB.a(context, iG7) : null);
        if (d0VarA4.g(R$styleable.AppCompatTextView_drawableTint)) {
            androidx.core.widget.i.a(this.f785a, d0VarA4.a(R$styleable.AppCompatTextView_drawableTint));
        }
        if (d0VarA4.g(R$styleable.AppCompatTextView_drawableTintMode)) {
            i2 = -1;
            androidx.core.widget.i.a(this.f785a, p.a(d0VarA4.d(R$styleable.AppCompatTextView_drawableTintMode, -1), null));
        } else {
            i2 = -1;
        }
        int iC = d0VarA4.c(R$styleable.AppCompatTextView_firstBaselineToTopHeight, i2);
        int iC2 = d0VarA4.c(R$styleable.AppCompatTextView_lastBaselineToBottomHeight, i2);
        int iC3 = d0VarA4.c(R$styleable.AppCompatTextView_lineHeight, i2);
        d0VarA4.a();
        if (iC != i2) {
            androidx.core.widget.i.a(this.f785a, iC);
        }
        if (iC2 != i2) {
            androidx.core.widget.i.b(this.f785a, iC2);
        }
        if (iC3 != i2) {
            androidx.core.widget.i.c(this.f785a, iC3);
        }
    }

    void b() {
        this.i.a();
    }

    int c() {
        return this.i.b();
    }

    int d() {
        return this.i.c();
    }

    int e() {
        return this.i.d();
    }

    int[] f() {
        return this.i.e();
    }

    int g() {
        return this.i.f();
    }

    ColorStateList h() {
        b0 b0Var = this.h;
        if (b0Var != null) {
            return b0Var.f714a;
        }
        return null;
    }

    PorterDuff.Mode i() {
        b0 b0Var = this.h;
        if (b0Var != null) {
            return b0Var.f715b;
        }
        return null;
    }

    boolean j() {
        return this.i.g();
    }

    void k() {
        a();
    }

    private void b(int i, float f2) {
        this.i.a(i, f2);
    }

    public void a(Typeface typeface) {
        if (this.m) {
            this.f785a.setTypeface(typeface);
            this.l = typeface;
        }
    }

    public void a(Runnable runnable) {
        this.f785a.post(runnable);
    }

    private void a(Context context, d0 d0Var) {
        String strD;
        this.j = d0Var.d(R$styleable.TextAppearance_android_textStyle, this.j);
        if (Build.VERSION.SDK_INT >= 28) {
            this.k = d0Var.d(R$styleable.TextAppearance_android_textFontWeight, -1);
            if (this.k != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        if (!d0Var.g(R$styleable.TextAppearance_android_fontFamily) && !d0Var.g(R$styleable.TextAppearance_fontFamily)) {
            if (d0Var.g(R$styleable.TextAppearance_android_typeface)) {
                this.m = false;
                int iD = d0Var.d(R$styleable.TextAppearance_android_typeface, 1);
                if (iD == 1) {
                    this.l = Typeface.SANS_SERIF;
                    return;
                } else if (iD == 2) {
                    this.l = Typeface.SERIF;
                    return;
                } else {
                    if (iD != 3) {
                        return;
                    }
                    this.l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.l = null;
        int i = d0Var.g(R$styleable.TextAppearance_fontFamily) ? R$styleable.TextAppearance_fontFamily : R$styleable.TextAppearance_android_fontFamily;
        int i2 = this.k;
        int i3 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceA = d0Var.a(i, this.j, new a(this, i2, i3));
                if (typefaceA != null) {
                    if (Build.VERSION.SDK_INT >= 28 && this.k != -1) {
                        this.l = Typeface.create(Typeface.create(typefaceA, 0), this.k, (this.j & 2) != 0);
                    } else {
                        this.l = typefaceA;
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (strD = d0Var.d(i)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 28 && this.k != -1) {
            this.l = Typeface.create(Typeface.create(strD, 0), this.k, (this.j & 2) != 0);
        } else {
            this.l = Typeface.create(strD, this.j);
        }
    }

    void a(Context context, int i) {
        String strD;
        ColorStateList colorStateListA;
        d0 d0VarA = d0.a(context, i, R$styleable.TextAppearance);
        if (d0VarA.g(R$styleable.TextAppearance_textAllCaps)) {
            a(d0VarA.a(R$styleable.TextAppearance_textAllCaps, false));
        }
        if (Build.VERSION.SDK_INT < 23 && d0VarA.g(R$styleable.TextAppearance_android_textColor) && (colorStateListA = d0VarA.a(R$styleable.TextAppearance_android_textColor)) != null) {
            this.f785a.setTextColor(colorStateListA);
        }
        if (d0VarA.g(R$styleable.TextAppearance_android_textSize) && d0VarA.c(R$styleable.TextAppearance_android_textSize, -1) == 0) {
            this.f785a.setTextSize(0, 0.0f);
        }
        a(context, d0VarA);
        if (Build.VERSION.SDK_INT >= 26 && d0VarA.g(R$styleable.TextAppearance_fontVariationSettings) && (strD = d0VarA.d(R$styleable.TextAppearance_fontVariationSettings)) != null) {
            this.f785a.setFontVariationSettings(strD);
        }
        d0VarA.a();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f785a.setTypeface(typeface, this.j);
        }
    }

    void a(boolean z) {
        this.f785a.setAllCaps(z);
    }

    void a() {
        if (this.f786b != null || this.f787c != null || this.f788d != null || this.f789e != null) {
            Drawable[] compoundDrawables = this.f785a.getCompoundDrawables();
            a(compoundDrawables[0], this.f786b);
            a(compoundDrawables[1], this.f787c);
            a(compoundDrawables[2], this.f788d);
            a(compoundDrawables[3], this.f789e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f790f == null && this.g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f785a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f790f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    private void a(Drawable drawable, b0 b0Var) {
        if (drawable == null || b0Var == null) {
            return;
        }
        e.a(drawable, b0Var, this.f785a.getDrawableState());
    }

    private static b0 a(Context context, e eVar, int i) {
        ColorStateList colorStateListB = eVar.b(context, i);
        if (colorStateListB == null) {
            return null;
        }
        b0 b0Var = new b0();
        b0Var.f717d = true;
        b0Var.f714a = colorStateListB;
        return b0Var;
    }

    void a(boolean z, int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.E) {
            return;
        }
        b();
    }

    void a(int i, float f2) {
        if (androidx.core.widget.b.E || j()) {
            return;
        }
        b(i, f2);
    }

    void a(int i) {
        this.i.b(i);
    }

    void a(int i, int i2, int i3, int i4) {
        this.i.a(i, i2, i3, i4);
    }

    void a(int[] iArr, int i) {
        this.i.a(iArr, i);
    }

    void a(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new b0();
        }
        b0 b0Var = this.h;
        b0Var.f714a = colorStateList;
        b0Var.f717d = colorStateList != null;
        l();
    }

    void a(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new b0();
        }
        b0 b0Var = this.h;
        b0Var.f715b = mode;
        b0Var.f716c = mode != null;
        l();
    }

    private void a(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (Build.VERSION.SDK_INT >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f785a.getCompoundDrawablesRelative();
            TextView textView = this.f785a;
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
            return;
        }
        if (drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            Drawable[] compoundDrawablesRelative2 = this.f785a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f785a;
                Drawable drawable7 = compoundDrawablesRelative2[0];
                if (drawable2 == null) {
                    drawable2 = compoundDrawablesRelative2[1];
                }
                Drawable drawable8 = compoundDrawablesRelative2[2];
                if (drawable4 == null) {
                    drawable4 = compoundDrawablesRelative2[3];
                }
                textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, drawable8, drawable4);
                return;
            }
        }
        Drawable[] compoundDrawables = this.f785a.getCompoundDrawables();
        TextView textView3 = this.f785a;
        if (drawable == null) {
            drawable = compoundDrawables[0];
        }
        if (drawable2 == null) {
            drawable2 = compoundDrawables[1];
        }
        if (drawable3 == null) {
            drawable3 = compoundDrawables[2];
        }
        if (drawable4 == null) {
            drawable4 = compoundDrawables[3];
        }
        textView3.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }
}
