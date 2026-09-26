package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: AppCompatCompoundButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CompoundButton f725a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ColorStateList f726b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PorterDuff.Mode f727c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f728d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f729e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f730f;

    d(CompoundButton compoundButton) {
        this.f725a = compoundButton;
    }

    void a(AttributeSet attributeSet, int i) {
        boolean z;
        int resourceId;
        int resourceId2;
        TypedArray typedArrayObtainStyledAttributes = this.f725a.getContext().obtainStyledAttributes(attributeSet, R$styleable.CompoundButton, i, 0);
        try {
            if (!typedArrayObtainStyledAttributes.hasValue(R$styleable.CompoundButton_buttonCompat) || (resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CompoundButton_buttonCompat, 0)) == 0) {
                z = false;
            } else {
                try {
                    this.f725a.setButtonDrawable(androidx.appcompat.a.a.a.c(this.f725a.getContext(), resourceId2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z && typedArrayObtainStyledAttributes.hasValue(R$styleable.CompoundButton_android_button) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CompoundButton_android_button, 0)) != 0) {
                this.f725a.setButtonDrawable(androidx.appcompat.a.a.a.c(this.f725a.getContext(), resourceId));
            }
            if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CompoundButton_buttonTint)) {
                androidx.core.widget.c.a(this.f725a, typedArrayObtainStyledAttributes.getColorStateList(R$styleable.CompoundButton_buttonTint));
            }
            if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CompoundButton_buttonTintMode)) {
                androidx.core.widget.c.a(this.f725a, p.a(typedArrayObtainStyledAttributes.getInt(R$styleable.CompoundButton_buttonTintMode, -1), null));
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    ColorStateList b() {
        return this.f726b;
    }

    PorterDuff.Mode c() {
        return this.f727c;
    }

    void d() {
        if (this.f730f) {
            this.f730f = false;
        } else {
            this.f730f = true;
            a();
        }
    }

    void a(ColorStateList colorStateList) {
        this.f726b = colorStateList;
        this.f728d = true;
        a();
    }

    void a(PorterDuff.Mode mode) {
        this.f727c = mode;
        this.f729e = true;
        a();
    }

    void a() {
        Drawable drawableA = androidx.core.widget.c.a(this.f725a);
        if (drawableA != null) {
            if (this.f728d || this.f729e) {
                Drawable drawableMutate = androidx.core.graphics.drawable.a.h(drawableA).mutate();
                if (this.f728d) {
                    androidx.core.graphics.drawable.a.a(drawableMutate, this.f726b);
                }
                if (this.f729e) {
                    androidx.core.graphics.drawable.a.a(drawableMutate, this.f727c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f725a.getDrawableState());
                }
                this.f725a.setButtonDrawable(drawableMutate);
            }
        }
    }

    int a(int i) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = androidx.core.widget.c.a(this.f725a)) == null) ? i : i + drawableA.getIntrinsicWidth();
    }
}
