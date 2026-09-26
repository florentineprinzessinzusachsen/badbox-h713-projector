package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$style;

/* JADX INFO: compiled from: TooltipPopup.java */
/* JADX INFO: loaded from: classes.dex */
class h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f768a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final View f769b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TextView f770c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final WindowManager.LayoutParams f771d = new WindowManager.LayoutParams();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Rect f772e = new Rect();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int[] f773f = new int[2];
    private final int[] g = new int[2];

    h0(Context context) {
        this.f768a = context;
        this.f769b = LayoutInflater.from(this.f768a).inflate(R$layout.abc_tooltip, (ViewGroup) null);
        this.f770c = (TextView) this.f769b.findViewById(R$id.message);
        this.f771d.setTitle(h0.class.getSimpleName());
        this.f771d.packageName = this.f768a.getPackageName();
        WindowManager.LayoutParams layoutParams = this.f771d;
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = R$style.Animation_AppCompat_Tooltip;
        layoutParams.flags = 24;
    }

    void a(View view, int i, int i2, boolean z, CharSequence charSequence) {
        if (b()) {
            a();
        }
        this.f770c.setText(charSequence);
        a(view, i, i2, z, this.f771d);
        ((WindowManager) this.f768a.getSystemService("window")).addView(this.f769b, this.f771d);
    }

    boolean b() {
        return this.f769b.getParent() != null;
    }

    void a() {
        if (b()) {
            ((WindowManager) this.f768a.getSystemService("window")).removeView(this.f769b);
        }
    }

    private void a(View view, int i, int i2, boolean z, WindowManager.LayoutParams layoutParams) {
        int height;
        int i3;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = this.f768a.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            int dimensionPixelOffset2 = this.f768a.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_extra_offset);
            height = i2 + dimensionPixelOffset2;
            i3 = i2 - dimensionPixelOffset2;
        } else {
            height = view.getHeight();
            i3 = 0;
        }
        layoutParams.gravity = 49;
        int dimensionPixelOffset3 = this.f768a.getResources().getDimensionPixelOffset(z ? R$dimen.tooltip_y_offset_touch : R$dimen.tooltip_y_offset_non_touch);
        View viewA = a(view);
        if (viewA == null) {
            Log.e("TooltipPopup", "Cannot find app view");
            return;
        }
        viewA.getWindowVisibleDisplayFrame(this.f772e);
        Rect rect = this.f772e;
        if (rect.left < 0 && rect.top < 0) {
            Resources resources = this.f768a.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.f772e.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        viewA.getLocationOnScreen(this.g);
        view.getLocationOnScreen(this.f773f);
        int[] iArr = this.f773f;
        int i4 = iArr[0];
        int[] iArr2 = this.g;
        iArr[0] = i4 - iArr2[0];
        iArr[1] = iArr[1] - iArr2[1];
        layoutParams.x = (iArr[0] + i) - (viewA.getWidth() / 2);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.f769b.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        int measuredHeight = this.f769b.getMeasuredHeight();
        int[] iArr3 = this.f773f;
        int i5 = ((iArr3[1] + i3) - dimensionPixelOffset3) - measuredHeight;
        int i6 = iArr3[1] + height + dimensionPixelOffset3;
        if (z) {
            if (i5 >= 0) {
                layoutParams.y = i5;
                return;
            } else {
                layoutParams.y = i6;
                return;
            }
        }
        if (measuredHeight + i6 <= this.f772e.height()) {
            layoutParams.y = i6;
        } else {
            layoutParams.y = i5;
        }
    }

    private static View a(View view) {
        View rootView = view.getRootView();
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        if ((layoutParams instanceof WindowManager.LayoutParams) && ((WindowManager.LayoutParams) layoutParams).type == 2) {
            return rootView;
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return ((Activity) context).getWindow().getDecorView();
            }
        }
        return rootView;
    }
}
