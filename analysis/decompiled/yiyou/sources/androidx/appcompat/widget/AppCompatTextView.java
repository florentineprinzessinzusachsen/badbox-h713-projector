package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatTextView extends TextView implements androidx.core.f.s, androidx.core.widget.k, androidx.core.widget.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f596a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l f597b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final k f598c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Future<androidx.core.d.a> f599d;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    private void d() {
        Future<androidx.core.d.a> future = this.f599d;
        if (future != null) {
            try {
                this.f599d = null;
                androidx.core.widget.i.a(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        c cVar = this.f596a;
        if (cVar != null) {
            cVar.a();
        }
        l lVar = this.f597b;
        if (lVar != null) {
            lVar.a();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (androidx.core.widget.b.E) {
            return super.getAutoSizeMaxTextSize();
        }
        l lVar = this.f597b;
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
        l lVar = this.f597b;
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
        l lVar = this.f597b;
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
        l lVar = this.f597b;
        return lVar != null ? lVar.f() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (androidx.core.widget.b.E) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        l lVar = this.f597b;
        if (lVar != null) {
            return lVar.g();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return androidx.core.widget.i.a(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return androidx.core.widget.i.b(this);
    }

    @Override // androidx.core.f.s
    public ColorStateList getSupportBackgroundTintList() {
        c cVar = this.f596a;
        if (cVar != null) {
            return cVar.b();
        }
        return null;
    }

    @Override // androidx.core.f.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        c cVar = this.f596a;
        if (cVar != null) {
            return cVar.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f597b.h();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f597b.i();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        d();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        k kVar;
        return (Build.VERSION.SDK_INT >= 28 || (kVar = this.f598c) == null) ? super.getTextClassifier() : kVar.a();
    }

    public androidx.core.d.a.C0020a getTextMetricsParamsCompat() {
        return androidx.core.widget.i.d(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        f.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        l lVar = this.f597b;
        if (lVar != null) {
            lVar.a(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        d();
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        l lVar = this.f597b;
        if (lVar == null || androidx.core.widget.b.E || !lVar.j()) {
            return;
        }
        this.f597b.b();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.E) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        l lVar = this.f597b;
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
        l lVar = this.f597b;
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
        l lVar = this.f597b;
        if (lVar != null) {
            lVar.a(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        c cVar = this.f596a;
        if (cVar != null) {
            cVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        c cVar = this.f596a;
        if (cVar != null) {
            cVar.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        l lVar = this.f597b;
        if (lVar != null) {
            lVar.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        l lVar = this.f597b;
        if (lVar != null) {
            lVar.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        l lVar = this.f597b;
        if (lVar != null) {
            lVar.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        l lVar = this.f597b;
        if (lVar != null) {
            lVar.k();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.a(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            androidx.core.widget.i.a(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            androidx.core.widget.i.b(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        androidx.core.widget.i.c(this, i);
    }

    public void setPrecomputedText(androidx.core.d.a aVar) {
        androidx.core.widget.i.a(this, aVar);
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        c cVar = this.f596a;
        if (cVar != null) {
            cVar.b(colorStateList);
        }
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        c cVar = this.f596a;
        if (cVar != null) {
            cVar.a(mode);
        }
    }

    @Override // androidx.core.widget.k
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f597b.a(colorStateList);
        this.f597b.a();
    }

    @Override // androidx.core.widget.k
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f597b.a(mode);
        this.f597b.a();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        l lVar = this.f597b;
        if (lVar != null) {
            lVar.a(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        k kVar;
        if (Build.VERSION.SDK_INT >= 28 || (kVar = this.f598c) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            kVar.a(textClassifier);
        }
    }

    public void setTextFuture(Future<androidx.core.d.a> future) {
        this.f599d = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(androidx.core.d.a.C0020a c0020a) {
        androidx.core.widget.i.a(this, c0020a);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f2) {
        if (androidx.core.widget.b.E) {
            super.setTextSize(i, f2);
            return;
        }
        l lVar = this.f597b;
        if (lVar != null) {
            lVar.a(i, f2);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i) {
        Typeface typefaceA = (typeface == null || i <= 0) ? null : androidx.core.a.c.a(getContext(), typeface, i);
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        super.setTypeface(typeface, i);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(a0.b(context), attributeSet, i);
        this.f596a = new c(this);
        this.f596a.a(attributeSet, i);
        this.f597b = new l(this);
        this.f597b.a(attributeSet, i);
        this.f597b.a();
        this.f598c = new k(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? androidx.appcompat.a.a.a.c(context, i) : null, i2 != 0 ? androidx.appcompat.a.a.a.c(context, i2) : null, i3 != 0 ? androidx.appcompat.a.a.a.c(context, i3) : null, i4 != 0 ? androidx.appcompat.a.a.a.c(context, i4) : null);
        l lVar = this.f597b;
        if (lVar != null) {
            lVar.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? androidx.appcompat.a.a.a.c(context, i) : null, i2 != 0 ? androidx.appcompat.a.a.a.c(context, i2) : null, i3 != 0 ? androidx.appcompat.a.a.a.c(context, i3) : null, i4 != 0 ? androidx.appcompat.a.a.a.c(context, i4) : null);
        l lVar = this.f597b;
        if (lVar != null) {
            lVar.k();
        }
    }
}
