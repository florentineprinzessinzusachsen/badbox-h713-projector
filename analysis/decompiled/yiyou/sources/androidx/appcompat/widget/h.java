package androidx.appcompat.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: AppCompatPopupWindow.java */
/* JADX INFO: loaded from: classes.dex */
class h extends PopupWindow {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final boolean f766b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f767a;

    static {
        f766b = Build.VERSION.SDK_INT < 21;
    }

    public h(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    private void a(Context context, AttributeSet attributeSet, int i, int i2) {
        d0 d0VarA = d0.a(context, attributeSet, R$styleable.PopupWindow, i, i2);
        if (d0VarA.g(R$styleable.PopupWindow_overlapAnchor)) {
            a(d0VarA.a(R$styleable.PopupWindow_overlapAnchor, false));
        }
        setBackgroundDrawable(d0VarA.b(R$styleable.PopupWindow_android_popupBackground));
        d0VarA.a();
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        if (f766b && this.f767a) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        if (f766b && this.f767a) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (f766b && this.f767a) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }

    private void a(boolean z) {
        if (f766b) {
            this.f767a = z;
        } else {
            androidx.core.widget.h.a(this, z);
        }
    }
}
