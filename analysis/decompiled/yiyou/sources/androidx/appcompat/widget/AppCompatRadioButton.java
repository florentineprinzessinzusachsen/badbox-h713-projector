package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import androidx.appcompat.R$attr;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatRadioButton extends RadioButton implements androidx.core.widget.j, androidx.core.f.s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f573a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f574b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final l f575c;

    public AppCompatRadioButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        c cVar = this.f574b;
        if (cVar != null) {
            cVar.a();
        }
        l lVar = this.f575c;
        if (lVar != null) {
            lVar.a();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        d dVar = this.f573a;
        return dVar != null ? dVar.a(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // androidx.core.f.s
    public ColorStateList getSupportBackgroundTintList() {
        c cVar = this.f574b;
        if (cVar != null) {
            return cVar.b();
        }
        return null;
    }

    @Override // androidx.core.f.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        c cVar = this.f574b;
        if (cVar != null) {
            return cVar.c();
        }
        return null;
    }

    public ColorStateList getSupportButtonTintList() {
        d dVar = this.f573a;
        if (dVar != null) {
            return dVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        d dVar = this.f573a;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        c cVar = this.f574b;
        if (cVar != null) {
            cVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        c cVar = this.f574b;
        if (cVar != null) {
            cVar.a(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        d dVar = this.f573a;
        if (dVar != null) {
            dVar.d();
        }
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        c cVar = this.f574b;
        if (cVar != null) {
            cVar.b(colorStateList);
        }
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        c cVar = this.f574b;
        if (cVar != null) {
            cVar.a(mode);
        }
    }

    @Override // androidx.core.widget.j
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        d dVar = this.f573a;
        if (dVar != null) {
            dVar.a(colorStateList);
        }
    }

    @Override // androidx.core.widget.j
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        d dVar = this.f573a;
        if (dVar != null) {
            dVar.a(mode);
        }
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.radioButtonStyle);
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(a0.b(context), attributeSet, i);
        this.f573a = new d(this);
        this.f573a.a(attributeSet, i);
        this.f574b = new c(this);
        this.f574b.a(attributeSet, i);
        this.f575c = new l(this);
        this.f575c.a(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(androidx.appcompat.a.a.a.c(getContext(), i));
    }
}
