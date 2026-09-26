package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import com.blankj.utilcode.constant.MemoryConstants;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TypedValue f604a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TypedValue f605b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f606c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TypedValue f607d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TypedValue f608e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TypedValue f609f;
    private final Rect g;
    private a h;

    public interface a {
        void a();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public void a(Rect rect) {
        fitSystemWindows(rect);
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f608e == null) {
            this.f608e = new TypedValue();
        }
        return this.f608e;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f609f == null) {
            this.f609f = new TypedValue();
        }
        return this.f609f;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f606c == null) {
            this.f606c = new TypedValue();
        }
        return this.f606c;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f607d == null) {
            this.f607d = new TypedValue();
        }
        return this.f607d;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f604a == null) {
            this.f604a = new TypedValue();
        }
        return this.f604a;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f605b == null) {
            this.f605b = new TypedValue();
        }
        return this.f605b;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.h;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.h;
        if (aVar != null) {
            aVar.onDetachedFromWindow();
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x004a  */
    /* JADX WARN: Code duplicated, block: B:22:0x0061  */
    /* JADX WARN: Code duplicated, block: B:37:0x0088  */
    /* JADX WARN: Code duplicated, block: B:54:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:56:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:57:0x00dd  */
    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int iMakeMeasureSpec;
        boolean z;
        int i3;
        int i4;
        float fraction;
        int i5;
        int i6;
        float fraction2;
        int i7;
        int i8;
        float fraction3;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        boolean z2 = true;
        boolean z3 = displayMetrics.widthPixels < displayMetrics.heightPixels;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != Integer.MIN_VALUE) {
            iMakeMeasureSpec = i;
            z = false;
        } else {
            TypedValue typedValue = z3 ? this.f607d : this.f606c;
            if (typedValue == null || (i7 = typedValue.type) == 0) {
                iMakeMeasureSpec = i;
                z = false;
            } else {
                if (i7 == 5) {
                    fraction3 = typedValue.getDimension(displayMetrics);
                } else {
                    if (i7 == 6) {
                        int i9 = displayMetrics.widthPixels;
                        fraction3 = typedValue.getFraction(i9, i9);
                    } else {
                        i8 = 0;
                    }
                    if (i8 > 0) {
                        Rect rect = this.g;
                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(i8 - (rect.left + rect.right), View.MeasureSpec.getSize(i)), MemoryConstants.GB);
                        z = true;
                    } else {
                        iMakeMeasureSpec = i;
                        z = false;
                    }
                }
                i8 = (int) fraction3;
                if (i8 > 0) {
                    Rect rect2 = this.g;
                    iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(i8 - (rect2.left + rect2.right), View.MeasureSpec.getSize(i)), MemoryConstants.GB);
                    z = true;
                } else {
                    iMakeMeasureSpec = i;
                    z = false;
                }
            }
        }
        if (mode2 == Integer.MIN_VALUE) {
            TypedValue typedValue2 = z3 ? this.f608e : this.f609f;
            if (typedValue2 != null && (i5 = typedValue2.type) != 0) {
                if (i5 == 5) {
                    fraction2 = typedValue2.getDimension(displayMetrics);
                } else {
                    if (i5 == 6) {
                        int i10 = displayMetrics.heightPixels;
                        fraction2 = typedValue2.getFraction(i10, i10);
                    } else {
                        i6 = 0;
                    }
                    if (i6 > 0) {
                        Rect rect3 = this.g;
                        i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i6 - (rect3.top + rect3.bottom), View.MeasureSpec.getSize(i2)), MemoryConstants.GB);
                    }
                }
                i6 = (int) fraction2;
                if (i6 > 0) {
                    Rect rect4 = this.g;
                    i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i6 - (rect4.top + rect4.bottom), View.MeasureSpec.getSize(i2)), MemoryConstants.GB);
                }
            }
        }
        super.onMeasure(iMakeMeasureSpec, i2);
        int measuredWidth = getMeasuredWidth();
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(measuredWidth, MemoryConstants.GB);
        if (z || mode != Integer.MIN_VALUE) {
            z2 = false;
        } else {
            TypedValue typedValue3 = z3 ? this.f605b : this.f604a;
            if (typedValue3 == null || (i3 = typedValue3.type) == 0) {
                z2 = false;
            } else {
                if (i3 == 5) {
                    fraction = typedValue3.getDimension(displayMetrics);
                } else {
                    if (i3 == 6) {
                        int i11 = displayMetrics.widthPixels;
                        fraction = typedValue3.getFraction(i11, i11);
                    } else {
                        i4 = 0;
                    }
                    if (i4 > 0) {
                        Rect rect5 = this.g;
                        i4 -= rect5.left + rect5.right;
                    }
                    if (measuredWidth < i4) {
                        iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i4, MemoryConstants.GB);
                    } else {
                        z2 = false;
                    }
                }
                i4 = (int) fraction;
                if (i4 > 0) {
                    Rect rect6 = this.g;
                    i4 -= rect6.left + rect6.right;
                }
                if (measuredWidth < i4) {
                    iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i4, MemoryConstants.GB);
                } else {
                    z2 = false;
                }
            }
        }
        if (z2) {
            super.onMeasure(iMakeMeasureSpec2, i2);
        }
    }

    public void setAttachListener(a aVar) {
        this.h = aVar;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.g.set(i, i2, i3, i4);
        if (androidx.core.f.t.r(this)) {
            requestLayout();
        }
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new Rect();
    }
}
