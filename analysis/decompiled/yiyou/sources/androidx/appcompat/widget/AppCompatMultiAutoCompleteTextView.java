package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;
import androidx.appcompat.R$attr;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView implements androidx.core.f.s {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f570c = {R.attr.popupBackground};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l f572b;

    public AppCompatMultiAutoCompleteTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        c cVar = this.f571a;
        if (cVar != null) {
            cVar.a();
        }
        l lVar = this.f572b;
        if (lVar != null) {
            lVar.a();
        }
    }

    @Override // androidx.core.f.s
    public ColorStateList getSupportBackgroundTintList() {
        c cVar = this.f571a;
        if (cVar != null) {
            return cVar.b();
        }
        return null;
    }

    @Override // androidx.core.f.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        c cVar = this.f571a;
        if (cVar != null) {
            return cVar.c();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        f.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        c cVar = this.f571a;
        if (cVar != null) {
            cVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        c cVar = this.f571a;
        if (cVar != null) {
            cVar.a(i);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(androidx.appcompat.a.a.a.c(getContext(), i));
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        c cVar = this.f571a;
        if (cVar != null) {
            cVar.b(colorStateList);
        }
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        c cVar = this.f571a;
        if (cVar != null) {
            cVar.a(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        l lVar = this.f572b;
        if (lVar != null) {
            lVar.a(context, i);
        }
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(a0.b(context), attributeSet, i);
        d0 d0VarA = d0.a(getContext(), attributeSet, f570c, i, 0);
        if (d0VarA.g(0)) {
            setDropDownBackgroundDrawable(d0VarA.b(0));
        }
        d0VarA.a();
        this.f571a = new c(this);
        this.f571a.a(attributeSet, i);
        this.f572b = new l(this);
        this.f572b.a(attributeSet, i);
        this.f572b.a();
    }
}
