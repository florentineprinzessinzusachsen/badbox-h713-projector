package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: AppCompatSeekBarHelper.java */
/* JADX INFO: loaded from: classes.dex */
class j extends i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final SeekBar f779d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f780e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ColorStateList f781f;
    private PorterDuff.Mode g;
    private boolean h;
    private boolean i;

    j(SeekBar seekBar) {
        super(seekBar);
        this.f781f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.f779d = seekBar;
    }

    private void d() {
        if (this.f780e != null) {
            if (this.h || this.i) {
                this.f780e = androidx.core.graphics.drawable.a.h(this.f780e.mutate());
                if (this.h) {
                    androidx.core.graphics.drawable.a.a(this.f780e, this.f781f);
                }
                if (this.i) {
                    androidx.core.graphics.drawable.a.a(this.f780e, this.g);
                }
                if (this.f780e.isStateful()) {
                    this.f780e.setState(this.f779d.getDrawableState());
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.i
    void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        d0 d0VarA = d0.a(this.f779d.getContext(), attributeSet, R$styleable.AppCompatSeekBar, i, 0);
        Drawable drawableC = d0VarA.c(R$styleable.AppCompatSeekBar_android_thumb);
        if (drawableC != null) {
            this.f779d.setThumb(drawableC);
        }
        a(d0VarA.b(R$styleable.AppCompatSeekBar_tickMark));
        if (d0VarA.g(R$styleable.AppCompatSeekBar_tickMarkTintMode)) {
            this.g = p.a(d0VarA.d(R$styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.g);
            this.i = true;
        }
        if (d0VarA.g(R$styleable.AppCompatSeekBar_tickMarkTint)) {
            this.f781f = d0VarA.a(R$styleable.AppCompatSeekBar_tickMarkTint);
            this.h = true;
        }
        d0VarA.a();
        d();
    }

    void b() {
        Drawable drawable = this.f780e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f779d.getDrawableState())) {
            this.f779d.invalidateDrawable(drawable);
        }
    }

    void c() {
        Drawable drawable = this.f780e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void a(Drawable drawable) {
        Drawable drawable2 = this.f780e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f780e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f779d);
            androidx.core.graphics.drawable.a.a(drawable, androidx.core.f.t.j(this.f779d));
            if (drawable.isStateful()) {
                drawable.setState(this.f779d.getDrawableState());
            }
            d();
        }
        this.f779d.invalidate();
    }

    void a(Canvas canvas) {
        if (this.f780e != null) {
            int max = this.f779d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f780e.getIntrinsicWidth();
                int intrinsicHeight = this.f780e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f780e.setBounds(-i, -i2, i, i2);
                float width = ((this.f779d.getWidth() - this.f779d.getPaddingLeft()) - this.f779d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f779d.getPaddingLeft(), this.f779d.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.f780e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }
}
