package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: AppCompatImageHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ImageView f754a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b0 f755b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b0 f756c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b0 f757d;

    public g(ImageView imageView) {
        this.f754a = imageView;
    }

    private boolean e() {
        int i = Build.VERSION.SDK_INT;
        if (i > 21) {
            return this.f755b != null;
        }
        return i == 21;
    }

    public void a(AttributeSet attributeSet, int i) {
        int iG;
        d0 d0VarA = d0.a(this.f754a.getContext(), attributeSet, R$styleable.AppCompatImageView, i, 0);
        try {
            Drawable drawable = this.f754a.getDrawable();
            if (drawable == null && (iG = d0VarA.g(R$styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = androidx.appcompat.a.a.a.c(this.f754a.getContext(), iG)) != null) {
                this.f754a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                p.b(drawable);
            }
            if (d0VarA.g(R$styleable.AppCompatImageView_tint)) {
                androidx.core.widget.e.a(this.f754a, d0VarA.a(R$styleable.AppCompatImageView_tint));
            }
            if (d0VarA.g(R$styleable.AppCompatImageView_tintMode)) {
                androidx.core.widget.e.a(this.f754a, p.a(d0VarA.d(R$styleable.AppCompatImageView_tintMode, -1), null));
            }
        } finally {
            d0VarA.a();
        }
    }

    ColorStateList b() {
        b0 b0Var = this.f756c;
        if (b0Var != null) {
            return b0Var.f714a;
        }
        return null;
    }

    PorterDuff.Mode c() {
        b0 b0Var = this.f756c;
        if (b0Var != null) {
            return b0Var.f715b;
        }
        return null;
    }

    boolean d() {
        return Build.VERSION.SDK_INT < 21 || !(this.f754a.getBackground() instanceof RippleDrawable);
    }

    public void a(int i) {
        if (i != 0) {
            Drawable drawableC = androidx.appcompat.a.a.a.c(this.f754a.getContext(), i);
            if (drawableC != null) {
                p.b(drawableC);
            }
            this.f754a.setImageDrawable(drawableC);
        } else {
            this.f754a.setImageDrawable(null);
        }
        a();
    }

    void a(ColorStateList colorStateList) {
        if (this.f756c == null) {
            this.f756c = new b0();
        }
        b0 b0Var = this.f756c;
        b0Var.f714a = colorStateList;
        b0Var.f717d = true;
        a();
    }

    void a(PorterDuff.Mode mode) {
        if (this.f756c == null) {
            this.f756c = new b0();
        }
        b0 b0Var = this.f756c;
        b0Var.f715b = mode;
        b0Var.f716c = true;
        a();
    }

    void a() {
        Drawable drawable = this.f754a.getDrawable();
        if (drawable != null) {
            p.b(drawable);
        }
        if (drawable != null) {
            if (e() && a(drawable)) {
                return;
            }
            b0 b0Var = this.f756c;
            if (b0Var != null) {
                e.a(drawable, b0Var, this.f754a.getDrawableState());
                return;
            }
            b0 b0Var2 = this.f755b;
            if (b0Var2 != null) {
                e.a(drawable, b0Var2, this.f754a.getDrawableState());
            }
        }
    }

    private boolean a(Drawable drawable) {
        if (this.f757d == null) {
            this.f757d = new b0();
        }
        b0 b0Var = this.f757d;
        b0Var.a();
        ColorStateList colorStateListA = androidx.core.widget.e.a(this.f754a);
        if (colorStateListA != null) {
            b0Var.f717d = true;
            b0Var.f714a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.e.b(this.f754a);
        if (modeB != null) {
            b0Var.f716c = true;
            b0Var.f715b = modeB;
        }
        if (!b0Var.f717d && !b0Var.f716c) {
            return false;
        }
        e.a(drawable, b0Var, this.f754a.getDrawableState());
        return true;
    }
}
