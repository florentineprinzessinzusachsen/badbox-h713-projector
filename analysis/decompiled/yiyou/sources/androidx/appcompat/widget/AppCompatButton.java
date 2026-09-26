package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.R$attr;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatButton extends Button implements androidx.core.f.s, androidx.core.widget.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f556a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l f557b;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        c cVar = this.f556a;
        if (cVar != null) {
            cVar.a();
        }
        l lVar = this.f557b;
        if (lVar != null) {
            lVar.a();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (androidx.core.widget.b.E) {
            return super.getAutoSizeMaxTextSize();
        }
        l lVar = this.f557b;
        if (lVar != null) {
            return lVar.c();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (androidx.core.widget.b.E) {
            return super.getAutoSizeMinTextSize();
        }
        l lVar = this.f557b;
        if (lVar != null) {
            return lVar.d();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (androidx.core.widget.b.E) {
            return super.getAutoSizeStepGranularity();
        }
        l lVar = this.f557b;
        if (lVar != null) {
            return lVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (androidx.core.widget.b.E) {
            return super.getAutoSizeTextAvailableSizes();
        }
        l lVar = this.f557b;
        return lVar != null ? lVar.f() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (androidx.core.widget.b.E) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        l lVar = this.f557b;
        if (lVar != null) {
            return lVar.g();
        }
        return 0;
    }

    @Override // androidx.core.f.s
    public ColorStateList getSupportBackgroundTintList() {
        c cVar = this.f556a;
        if (cVar != null) {
            return cVar.b();
        }
        return null;
    }

    @Override // androidx.core.f.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        c cVar = this.f556a;
        if (cVar != null) {
            return cVar.c();
        }
        return null;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        l lVar = this.f557b;
        if (lVar != null) {
            lVar.a(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        l lVar = this.f557b;
        if (lVar == null || androidx.core.widget.b.E || !lVar.j()) {
            return;
        }
        this.f557b.b();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.E) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        l lVar = this.f557b;
        if (lVar != null) {
            lVar.a(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (androidx.core.widget.b.E) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        l lVar = this.f557b;
        if (lVar != null) {
            lVar.a(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (androidx.core.widget.b.E) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        l lVar = this.f557b;
        if (lVar != null) {
            lVar.a(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        c cVar = this.f556a;
        if (cVar != null) {
            cVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        c cVar = this.f556a;
        if (cVar != null) {
            cVar.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.a(this, callback));
    }

    public void setSupportAllCaps(boolean z) {
        l lVar = this.f557b;
        if (lVar != null) {
            lVar.a(z);
        }
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        c cVar = this.f556a;
        if (cVar != null) {
            cVar.b(colorStateList);
        }
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        c cVar = this.f556a;
        if (cVar != null) {
            cVar.a(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        l lVar = this.f557b;
        if (lVar != null) {
            lVar.a(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f2) {
        if (androidx.core.widget.b.E) {
            super.setTextSize(i, f2);
            return;
        }
        l lVar = this.f557b;
        if (lVar != null) {
            lVar.a(i, f2);
        }
    }

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet, int i) {
        super(a0.b(context), attributeSet, i);
        this.f556a = new c(this);
        this.f556a.a(attributeSet, i);
        this.f557b = new l(this);
        this.f557b.a(attributeSet, i);
        this.f557b.a();
    }
}
