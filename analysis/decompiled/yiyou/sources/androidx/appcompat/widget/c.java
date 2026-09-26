package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: AppCompatBackgroundHelper.java */
/* JADX INFO: loaded from: classes.dex */
class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f718a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b0 f721d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b0 f722e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b0 f723f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f720c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f719b = e.b();

    c(View view) {
        this.f718a = view;
    }

    private boolean d() {
        int i = Build.VERSION.SDK_INT;
        if (i > 21) {
            return this.f721d != null;
        }
        return i == 21;
    }

    void a(AttributeSet attributeSet, int i) {
        d0 d0VarA = d0.a(this.f718a.getContext(), attributeSet, R$styleable.ViewBackgroundHelper, i, 0);
        try {
            if (d0VarA.g(R$styleable.ViewBackgroundHelper_android_background)) {
                this.f720c = d0VarA.g(R$styleable.ViewBackgroundHelper_android_background, -1);
                ColorStateList colorStateListB = this.f719b.b(this.f718a.getContext(), this.f720c);
                if (colorStateListB != null) {
                    a(colorStateListB);
                }
            }
            if (d0VarA.g(R$styleable.ViewBackgroundHelper_backgroundTint)) {
                androidx.core.f.t.a(this.f718a, d0VarA.a(R$styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (d0VarA.g(R$styleable.ViewBackgroundHelper_backgroundTintMode)) {
                androidx.core.f.t.a(this.f718a, p.a(d0VarA.d(R$styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        } finally {
            d0VarA.a();
        }
    }

    void b(ColorStateList colorStateList) {
        if (this.f722e == null) {
            this.f722e = new b0();
        }
        b0 b0Var = this.f722e;
        b0Var.f714a = colorStateList;
        b0Var.f717d = true;
        a();
    }

    PorterDuff.Mode c() {
        b0 b0Var = this.f722e;
        if (b0Var != null) {
            return b0Var.f715b;
        }
        return null;
    }

    ColorStateList b() {
        b0 b0Var = this.f722e;
        if (b0Var != null) {
            return b0Var.f714a;
        }
        return null;
    }

    private boolean b(Drawable drawable) {
        if (this.f723f == null) {
            this.f723f = new b0();
        }
        b0 b0Var = this.f723f;
        b0Var.a();
        ColorStateList colorStateListE = androidx.core.f.t.e(this.f718a);
        if (colorStateListE != null) {
            b0Var.f717d = true;
            b0Var.f714a = colorStateListE;
        }
        PorterDuff.Mode modeF = androidx.core.f.t.f(this.f718a);
        if (modeF != null) {
            b0Var.f716c = true;
            b0Var.f715b = modeF;
        }
        if (!b0Var.f717d && !b0Var.f716c) {
            return false;
        }
        e.a(drawable, b0Var, this.f718a.getDrawableState());
        return true;
    }

    void a(int i) {
        this.f720c = i;
        e eVar = this.f719b;
        a(eVar != null ? eVar.b(this.f718a.getContext(), i) : null);
        a();
    }

    void a(Drawable drawable) {
        this.f720c = -1;
        a((ColorStateList) null);
        a();
    }

    void a(PorterDuff.Mode mode) {
        if (this.f722e == null) {
            this.f722e = new b0();
        }
        b0 b0Var = this.f722e;
        b0Var.f715b = mode;
        b0Var.f716c = true;
        a();
    }

    void a() {
        Drawable background = this.f718a.getBackground();
        if (background != null) {
            if (d() && b(background)) {
                return;
            }
            b0 b0Var = this.f722e;
            if (b0Var != null) {
                e.a(background, b0Var, this.f718a.getDrawableState());
                return;
            }
            b0 b0Var2 = this.f721d;
            if (b0Var2 != null) {
                e.a(background, b0Var2, this.f718a.getDrawableState());
            }
        }
    }

    void a(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f721d == null) {
                this.f721d = new b0();
            }
            b0 b0Var = this.f721d;
            b0Var.f714a = colorStateList;
            b0Var.f717d = true;
        } else {
            this.f721d = null;
        }
        a();
    }
}
