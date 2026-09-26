package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.R$styleable;
import com.blankj.utilcode.constant.MemoryConstants;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f620a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f621b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f622c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f623d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f624e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f625f;
    private float g;
    private boolean h;
    private int[] i;
    private int[] j;
    private Drawable k;
    private int l;
    private int m;
    private int n;
    private int o;

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    private void c(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MemoryConstants.GB);
        for (int i3 = 0; i3 < i; i3++) {
            View viewA = a(i3);
            if (viewA.getVisibility() != 8) {
                a aVar = (a) viewA.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).height == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) aVar).width;
                    ((ViewGroup.MarginLayoutParams) aVar).width = viewA.getMeasuredWidth();
                    measureChildWithMargins(viewA, i2, 0, iMakeMeasureSpec, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).width = i4;
                }
            }
        }
    }

    private void d(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MemoryConstants.GB);
        for (int i3 = 0; i3 < i; i3++) {
            View viewA = a(i3);
            if (viewA.getVisibility() != 8) {
                a aVar = (a) viewA.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).width == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) aVar).height;
                    ((ViewGroup.MarginLayoutParams) aVar).height = viewA.getMeasuredHeight();
                    measureChildWithMargins(viewA, iMakeMeasureSpec, 0, i2, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).height = i4;
                }
            }
        }
    }

    int a(View view) {
        return 0;
    }

    int a(View view, int i) {
        return 0;
    }

    void a(Canvas canvas) {
        int right;
        int left;
        int i;
        int virtualChildCount = getVirtualChildCount();
        boolean zA = j0.a(this);
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View viewA = a(i2);
            if (viewA != null && viewA.getVisibility() != 8 && b(i2)) {
                a aVar = (a) viewA.getLayoutParams();
                b(canvas, zA ? viewA.getRight() + ((ViewGroup.MarginLayoutParams) aVar).rightMargin : (viewA.getLeft() - ((ViewGroup.MarginLayoutParams) aVar).leftMargin) - this.l);
            }
        }
        if (b(virtualChildCount)) {
            View viewA2 = a(virtualChildCount - 1);
            if (viewA2 != null) {
                a aVar2 = (a) viewA2.getLayoutParams();
                if (zA) {
                    left = viewA2.getLeft() - ((ViewGroup.MarginLayoutParams) aVar2).leftMargin;
                    i = this.l;
                    right = left - i;
                } else {
                    right = viewA2.getRight() + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin;
                }
            } else if (zA) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i = this.l;
                right = left - i;
            }
            b(canvas, right);
        }
    }

    int b(View view) {
        return 0;
    }

    void b(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; i++) {
            View viewA = a(i);
            if (viewA != null && viewA.getVisibility() != 8 && b(i)) {
                a(canvas, (viewA.getTop() - ((ViewGroup.MarginLayoutParams) ((a) viewA.getLayoutParams())).topMargin) - this.m);
            }
        }
        if (b(virtualChildCount)) {
            View viewA2 = a(virtualChildCount - 1);
            a(canvas, viewA2 == null ? (getHeight() - getPaddingBottom()) - this.m : viewA2.getBottom() + ((ViewGroup.MarginLayoutParams) ((a) viewA2.getLayoutParams())).bottomMargin);
        }
    }

    int c(int i) {
        return 0;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.f621b < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.f621b;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.f621b == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.f622c;
        if (this.f623d == 1 && (i = this.f624e & 112) != 48) {
            if (i == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f625f) / 2;
            } else if (i == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.f625f;
            }
        }
        return bottom + ((ViewGroup.MarginLayoutParams) ((a) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.f621b;
    }

    public Drawable getDividerDrawable() {
        return this.k;
    }

    public int getDividerPadding() {
        return this.o;
    }

    public int getDividerWidth() {
        return this.l;
    }

    public int getGravity() {
        return this.f624e;
    }

    public int getOrientation() {
        return this.f623d;
    }

    public int getShowDividers() {
        return this.n;
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.g;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.k == null) {
            return;
        }
        if (this.f623d == 1) {
            b(canvas);
        } else {
            a(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f623d == 1) {
            b(i, i2, i3, i4);
        } else {
            a(i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.f623d == 1) {
            b(i, i2);
        } else {
            a(i, i2);
        }
    }

    public void setBaselineAligned(boolean z) {
        this.f620a = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i >= 0 && i < getChildCount()) {
            this.f621b = i;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.k) {
            return;
        }
        this.k = drawable;
        if (drawable != null) {
            this.l = drawable.getIntrinsicWidth();
            this.m = drawable.getIntrinsicHeight();
        } else {
            this.l = 0;
            this.m = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i) {
        this.o = i;
    }

    public void setGravity(int i) {
        if (this.f624e != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.f624e = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.f624e;
        if ((8388615 & i3) != i2) {
            this.f624e = i2 | ((-8388616) & i3);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.h = z;
    }

    public void setOrientation(int i) {
        if (this.f623d != i) {
            this.f623d = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.n) {
            requestLayout();
        }
        this.n = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.f624e;
        if ((i3 & 112) != i2) {
            this.f624e = i2 | (i3 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f2) {
        this.g = Math.max(0.0f, f2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public a generateDefaultLayoutParams() {
        int i = this.f623d;
        if (i == 0) {
            return new a(-2, -2);
        }
        if (i == 1) {
            return new a(-1, -2);
        }
        return null;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f620a = true;
        this.f621b = -1;
        this.f622c = 0;
        this.f624e = 8388659;
        d0 d0VarA = d0.a(context, attributeSet, R$styleable.LinearLayoutCompat, i, 0);
        int iD = d0VarA.d(R$styleable.LinearLayoutCompat_android_orientation, -1);
        if (iD >= 0) {
            setOrientation(iD);
        }
        int iD2 = d0VarA.d(R$styleable.LinearLayoutCompat_android_gravity, -1);
        if (iD2 >= 0) {
            setGravity(iD2);
        }
        boolean zA = d0VarA.a(R$styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!zA) {
            setBaselineAligned(zA);
        }
        this.g = d0VarA.a(R$styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.f621b = d0VarA.d(R$styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.h = d0VarA.a(R$styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(d0VarA.b(R$styleable.LinearLayoutCompat_divider));
        this.n = d0VarA.d(R$styleable.LinearLayoutCompat_showDividers, 0);
        this.o = d0VarA.c(R$styleable.LinearLayoutCompat_dividerPadding, 0);
        d0VarA.a();
    }

    @Override // android.view.ViewGroup
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    public static class a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f626a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f627b;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f627b = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.LinearLayoutCompat_Layout);
            this.f626a = typedArrayObtainStyledAttributes.getFloat(R$styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.f627b = typedArrayObtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public a(int i, int i2) {
            super(i, i2);
            this.f627b = -1;
            this.f626a = 0.0f;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f627b = -1;
        }
    }

    void b(Canvas canvas, int i) {
        this.k.setBounds(i, getPaddingTop() + this.o, this.l + i, (getHeight() - getPaddingBottom()) - this.o);
        this.k.draw(canvas);
    }

    void a(Canvas canvas, int i) {
        this.k.setBounds(getPaddingLeft() + this.o, i, (getWidth() - getPaddingRight()) - this.o, this.m + i);
        this.k.draw(canvas);
    }

    protected boolean b(int i) {
        if (i == 0) {
            return (this.n & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.n & 4) != 0;
        }
        if ((this.n & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    View a(int i) {
        return getChildAt(i);
    }

    /* JADX WARN: Code duplicated, block: B:198:0x0470  */
    /* JADX WARN: Code duplicated, block: B:60:0x0174  */
    /* JADX WARN: Code duplicated, block: B:67:0x0196  */
    /* JADX WARN: Code duplicated, block: B:74:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:77:0x01ca A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:81:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:83:0x01d9  */
    void a(int i, int i2) {
        int[] iArr;
        int i3;
        int iMax;
        int iMax2;
        int i4;
        int i5;
        int baseline;
        int i6;
        int i7;
        int i8;
        boolean z;
        boolean z2;
        View view;
        int i9;
        boolean z3;
        int i10;
        int measuredHeight;
        boolean z4;
        int baseline2;
        int i11;
        this.f625f = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (this.i == null || this.j == null) {
            this.i = new int[4];
            this.j = new int[4];
        }
        int[] iArr2 = this.i;
        int[] iArr3 = this.j;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z5 = this.f620a;
        boolean z6 = this.h;
        int i12 = MemoryConstants.GB;
        boolean z7 = mode == 1073741824;
        float f2 = 0.0f;
        int iA = 0;
        int iMax3 = 0;
        int i13 = 0;
        int iMax4 = 0;
        int iMax5 = 0;
        boolean z8 = false;
        int i14 = 0;
        boolean z9 = true;
        boolean z10 = false;
        while (true) {
            iArr = iArr3;
            if (iA >= virtualChildCount) {
                break;
            }
            View viewA = a(iA);
            if (viewA == null) {
                this.f625f += c(iA);
            } else {
                if (viewA.getVisibility() == 8) {
                    iA += a(viewA, iA);
                } else {
                    if (b(iA)) {
                        this.f625f += this.l;
                    }
                    a aVar = (a) viewA.getLayoutParams();
                    float f3 = f2 + aVar.f626a;
                    if (mode != i12 || ((ViewGroup.MarginLayoutParams) aVar).width != 0 || aVar.f626a <= 0.0f) {
                        if (((ViewGroup.MarginLayoutParams) aVar).width != 0 || aVar.f626a <= 0.0f) {
                            i7 = Integer.MIN_VALUE;
                        } else {
                            ((ViewGroup.MarginLayoutParams) aVar).width = -2;
                            i7 = 0;
                        }
                        i8 = iA;
                        int i15 = i7;
                        z = z6;
                        z2 = z5;
                        a(viewA, i8, i, f3 == 0.0f ? this.f625f : 0, i2, 0);
                        if (i15 != Integer.MIN_VALUE) {
                            ((ViewGroup.MarginLayoutParams) aVar).width = i15;
                        }
                        int measuredWidth = viewA.getMeasuredWidth();
                        if (z7) {
                            view = viewA;
                            this.f625f += ((ViewGroup.MarginLayoutParams) aVar).leftMargin + measuredWidth + ((ViewGroup.MarginLayoutParams) aVar).rightMargin + b(view);
                        } else {
                            view = viewA;
                            int i16 = this.f625f;
                            this.f625f = Math.max(i16, i16 + measuredWidth + ((ViewGroup.MarginLayoutParams) aVar).leftMargin + ((ViewGroup.MarginLayoutParams) aVar).rightMargin + b(view));
                        }
                        if (z) {
                            iMax3 = Math.max(measuredWidth, iMax3);
                        }
                    } else {
                        if (z7) {
                            this.f625f += ((ViewGroup.MarginLayoutParams) aVar).leftMargin + ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                        } else {
                            int i17 = this.f625f;
                            this.f625f = Math.max(i17, ((ViewGroup.MarginLayoutParams) aVar).leftMargin + i17 + ((ViewGroup.MarginLayoutParams) aVar).rightMargin);
                        }
                        if (z5) {
                            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                            viewA.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                            i8 = iA;
                            z = z6;
                            z2 = z5;
                            view = viewA;
                        } else {
                            i8 = iA;
                            z = z6;
                            z2 = z5;
                            view = viewA;
                            i9 = MemoryConstants.GB;
                            z8 = true;
                        }
                        if (mode2 == i9 && ((ViewGroup.MarginLayoutParams) aVar).height == -1) {
                            z3 = true;
                            z10 = true;
                        } else {
                            z3 = false;
                        }
                        i10 = ((ViewGroup.MarginLayoutParams) aVar).topMargin + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i10;
                        int iCombineMeasuredStates = View.combineMeasuredStates(i14, view.getMeasuredState());
                        if (z2 && (baseline2 = view.getBaseline()) != -1) {
                            i11 = aVar.f627b;
                            if (i11 < 0) {
                                i11 = this.f624e;
                            }
                            int i18 = (((i11 & 112) >> 4) & (-2)) >> 1;
                            iArr2[i18] = Math.max(iArr2[i18], baseline2);
                            iArr[i18] = Math.max(iArr[i18], measuredHeight - baseline2);
                        }
                        int iMax6 = Math.max(i13, measuredHeight);
                        if (z9 || ((ViewGroup.MarginLayoutParams) aVar).height != -1) {
                            z4 = false;
                        } else {
                            z4 = true;
                        }
                        if (aVar.f626a > 0.0f) {
                            if (!z3) {
                                i10 = measuredHeight;
                            }
                            iMax5 = Math.max(iMax5, i10);
                        } else {
                            int i19 = iMax5;
                            if (z3) {
                                measuredHeight = i10;
                            }
                            iMax4 = Math.max(iMax4, measuredHeight);
                            iMax5 = i19;
                        }
                        int i20 = i8;
                        i13 = iMax6;
                        i14 = iCombineMeasuredStates;
                        z9 = z4;
                        iA = a(view, i20) + i20;
                        f2 = f3;
                    }
                    i9 = MemoryConstants.GB;
                    if (mode2 == i9) {
                        z3 = false;
                    } else {
                        z3 = false;
                    }
                    i10 = ((ViewGroup.MarginLayoutParams) aVar).topMargin + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                    measuredHeight = view.getMeasuredHeight() + i10;
                    int iCombineMeasuredStates2 = View.combineMeasuredStates(i14, view.getMeasuredState());
                    if (z2) {
                        i11 = aVar.f627b;
                        if (i11 < 0) {
                            i11 = this.f624e;
                        }
                        int i110 = (((i11 & 112) >> 4) & (-2)) >> 1;
                        iArr2[i110] = Math.max(iArr2[i110], baseline2);
                        iArr[i110] = Math.max(iArr[i110], measuredHeight - baseline2);
                    }
                    int iMax7 = Math.max(i13, measuredHeight);
                    if (z9) {
                        z4 = false;
                    } else {
                        z4 = false;
                    }
                    if (aVar.f626a > 0.0f) {
                        if (!z3) {
                            i10 = measuredHeight;
                        }
                        iMax5 = Math.max(iMax5, i10);
                    } else {
                        int i111 = iMax5;
                        if (z3) {
                            measuredHeight = i10;
                        }
                        iMax4 = Math.max(iMax4, measuredHeight);
                        iMax5 = i111;
                    }
                    int i21 = i8;
                    i13 = iMax7;
                    i14 = iCombineMeasuredStates2;
                    z9 = z4;
                    iA = a(view, i21) + i21;
                    f2 = f3;
                }
                iA++;
                iArr3 = iArr;
                z6 = z;
                z5 = z2;
                i12 = MemoryConstants.GB;
            }
            z = z6;
            z2 = z5;
            iA++;
            iArr3 = iArr;
            z6 = z;
            z5 = z2;
            i12 = MemoryConstants.GB;
        }
        boolean z11 = z6;
        boolean z12 = z5;
        int iMax8 = i13;
        int i22 = iMax4;
        int i23 = iMax5;
        int i24 = i14;
        if (this.f625f > 0 && b(virtualChildCount)) {
            this.f625f += this.l;
        }
        if (iArr2[1] != -1 || iArr2[0] != -1 || iArr2[2] != -1 || iArr2[3] != -1) {
            iMax8 = Math.max(iMax8, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        }
        if (z11 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.f625f = 0;
            int iA2 = 0;
            while (iA2 < virtualChildCount) {
                View viewA2 = a(iA2);
                if (viewA2 == null) {
                    this.f625f += c(iA2);
                } else if (viewA2.getVisibility() == 8) {
                    iA2 += a(viewA2, iA2);
                } else {
                    a aVar2 = (a) viewA2.getLayoutParams();
                    if (z7) {
                        this.f625f += ((ViewGroup.MarginLayoutParams) aVar2).leftMargin + iMax3 + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin + b(viewA2);
                    } else {
                        int i25 = this.f625f;
                        this.f625f = Math.max(i25, i25 + iMax3 + ((ViewGroup.MarginLayoutParams) aVar2).leftMargin + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin + b(viewA2));
                    }
                    iA2++;
                    iMax8 = iMax8;
                }
                iA2++;
                iMax8 = iMax8;
            }
        }
        int i26 = iMax8;
        this.f625f += getPaddingLeft() + getPaddingRight();
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(this.f625f, getSuggestedMinimumWidth()), i, 0);
        int i27 = (16777215 & iResolveSizeAndState) - this.f625f;
        if (!z8 && (i27 == 0 || f2 <= 0.0f)) {
            iMax2 = Math.max(i22, i23);
            if (z11 && mode != 1073741824) {
                for (int i28 = 0; i28 < virtualChildCount; i28++) {
                    View viewA3 = a(i28);
                    if (viewA3 != null && viewA3.getVisibility() != 8 && ((a) viewA3.getLayoutParams()).f626a > 0.0f) {
                        viewA3.measure(View.MeasureSpec.makeMeasureSpec(iMax3, MemoryConstants.GB), View.MeasureSpec.makeMeasureSpec(viewA3.getMeasuredHeight(), MemoryConstants.GB));
                    }
                }
            }
            i3 = virtualChildCount;
            iMax = i26;
        } else {
            float f4 = this.g;
            if (f4 > 0.0f) {
                f2 = f4;
            }
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            this.f625f = 0;
            int i29 = i22;
            int iCombineMeasuredStates3 = i24;
            int iMax9 = -1;
            float f5 = f2;
            int i30 = 0;
            while (i30 < virtualChildCount) {
                View viewA4 = a(i30);
                if (viewA4 == null || viewA4.getVisibility() == 8) {
                    i4 = i27;
                    virtualChildCount = virtualChildCount;
                } else {
                    a aVar3 = (a) viewA4.getLayoutParams();
                    float f6 = aVar3.f626a;
                    if (f6 > 0.0f) {
                        int i31 = (int) ((i27 * f6) / f5);
                        float f7 = f5 - f6;
                        int i32 = i27 - i31;
                        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + ((ViewGroup.MarginLayoutParams) aVar3).topMargin + ((ViewGroup.MarginLayoutParams) aVar3).bottomMargin, ((ViewGroup.MarginLayoutParams) aVar3).height);
                        if (((ViewGroup.MarginLayoutParams) aVar3).width == 0) {
                            i6 = MemoryConstants.GB;
                            if (mode == 1073741824) {
                                if (i31 <= 0) {
                                    i31 = 0;
                                }
                                viewA4.measure(View.MeasureSpec.makeMeasureSpec(i31, MemoryConstants.GB), childMeasureSpec);
                            }
                            iCombineMeasuredStates3 = View.combineMeasuredStates(iCombineMeasuredStates3, viewA4.getMeasuredState() & (-16777216));
                            f5 = f7;
                            i4 = i32;
                        } else {
                            i6 = MemoryConstants.GB;
                        }
                        int measuredWidth2 = viewA4.getMeasuredWidth() + i31;
                        if (measuredWidth2 < 0) {
                            measuredWidth2 = 0;
                        }
                        viewA4.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, i6), childMeasureSpec);
                        iCombineMeasuredStates3 = View.combineMeasuredStates(iCombineMeasuredStates3, viewA4.getMeasuredState() & (-16777216));
                        f5 = f7;
                        i4 = i32;
                    } else {
                        i4 = i27;
                    }
                    if (z7) {
                        this.f625f += viewA4.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) aVar3).leftMargin + ((ViewGroup.MarginLayoutParams) aVar3).rightMargin + b(viewA4);
                    } else {
                        int i33 = this.f625f;
                        this.f625f = Math.max(i33, viewA4.getMeasuredWidth() + i33 + ((ViewGroup.MarginLayoutParams) aVar3).leftMargin + ((ViewGroup.MarginLayoutParams) aVar3).rightMargin + b(viewA4));
                    }
                    boolean z13 = mode2 != 1073741824 && ((ViewGroup.MarginLayoutParams) aVar3).height == -1;
                    int i34 = ((ViewGroup.MarginLayoutParams) aVar3).topMargin + ((ViewGroup.MarginLayoutParams) aVar3).bottomMargin;
                    int measuredHeight2 = viewA4.getMeasuredHeight() + i34;
                    iMax9 = Math.max(iMax9, measuredHeight2);
                    if (!z13) {
                        i34 = measuredHeight2;
                    }
                    int iMax10 = Math.max(i29, i34);
                    if (z9) {
                        i5 = -1;
                        boolean z14 = ((ViewGroup.MarginLayoutParams) aVar3).height == -1;
                        if (!z12 && (baseline = viewA4.getBaseline()) != i5) {
                            int i35 = aVar3.f627b;
                            if (i35 < 0) {
                                i35 = this.f624e;
                            }
                            int i36 = (((i35 & 112) >> 4) & (-2)) >> 1;
                            iArr2[i36] = Math.max(iArr2[i36], baseline);
                            iArr[i36] = Math.max(iArr[i36], measuredHeight2 - baseline);
                        }
                        i29 = iMax10;
                        z9 = z14;
                        f5 = f5;
                    } else {
                        i5 = -1;
                    }
                    if (!z12) {
                    }
                    i29 = iMax10;
                    z9 = z14;
                    f5 = f5;
                }
                i30++;
                i27 = i4;
                virtualChildCount = virtualChildCount;
            }
            i3 = virtualChildCount;
            this.f625f += getPaddingLeft() + getPaddingRight();
            iMax = (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) ? iMax9 : Math.max(iMax9, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
            i24 = iCombineMeasuredStates3;
            iMax2 = i29;
        }
        if (z9 || mode2 == 1073741824) {
            iMax2 = iMax;
        }
        setMeasuredDimension(iResolveSizeAndState | (i24 & (-16777216)), View.resolveSizeAndState(Math.max(iMax2 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, i24 << 16));
        if (z10) {
            c(i3, i);
        }
    }

    /* JADX WARN: Code duplicated, block: B:151:0x032e  */
    /* JADX WARN: Code duplicated, block: B:157:0x033b  */
    void b(int i, int i2) {
        int i3;
        int iCombineMeasuredStates;
        int iMax;
        int i4;
        float f2;
        int i5;
        int i6;
        boolean z;
        boolean z2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int iMax2;
        int i12;
        int i13;
        View view;
        int iMax3;
        boolean z3;
        int iMax4;
        this.f625f = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i14 = this.f621b;
        boolean z4 = this.h;
        float f3 = 0.0f;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int iA = 0;
        boolean z5 = false;
        boolean z6 = true;
        boolean z7 = false;
        while (true) {
            int i20 = 8;
            i18 = i18;
            if (iA < virtualChildCount) {
                View viewA = a(iA);
                if (viewA == null) {
                    this.f625f += c(iA);
                } else {
                    int i21 = i15;
                    if (viewA.getVisibility() == 8) {
                        iA += a(viewA, iA);
                        i15 = i21;
                    } else {
                        if (b(iA)) {
                            this.f625f += this.m;
                        }
                        a aVar = (a) viewA.getLayoutParams();
                        float f4 = f3 + aVar.f626a;
                        if (mode2 == 1073741824 && ((ViewGroup.MarginLayoutParams) aVar).height == 0 && aVar.f626a > 0.0f) {
                            int i22 = this.f625f;
                            this.f625f = Math.max(i22, ((ViewGroup.MarginLayoutParams) aVar).topMargin + i22 + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin);
                            iMax3 = i17;
                            view = viewA;
                            i12 = i19;
                            virtualChildCount = virtualChildCount;
                            i9 = i21;
                            i10 = i16;
                            z5 = true;
                            i13 = iA;
                            i11 = mode2;
                            iMax2 = i18;
                        } else {
                            int i23 = i16;
                            if (((ViewGroup.MarginLayoutParams) aVar).height != 0 || aVar.f626a <= 0.0f) {
                                i8 = Integer.MIN_VALUE;
                            } else {
                                ((ViewGroup.MarginLayoutParams) aVar).height = -2;
                                i8 = 0;
                            }
                            i9 = i21;
                            int i24 = i8;
                            i10 = i23;
                            int i25 = i17;
                            virtualChildCount = virtualChildCount;
                            i11 = mode2;
                            iMax2 = i18;
                            i12 = i19;
                            i13 = iA;
                            a(viewA, iA, i, 0, i2, f4 == 0.0f ? this.f625f : 0);
                            if (i24 != Integer.MIN_VALUE) {
                                ((ViewGroup.MarginLayoutParams) aVar).height = i24;
                            }
                            int measuredHeight = viewA.getMeasuredHeight();
                            int i26 = this.f625f;
                            view = viewA;
                            this.f625f = Math.max(i26, i26 + measuredHeight + ((ViewGroup.MarginLayoutParams) aVar).topMargin + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin + b(view));
                            iMax3 = z4 ? Math.max(measuredHeight, i25) : i25;
                        }
                        if (i14 >= 0 && i14 == i13 + 1) {
                            this.f622c = this.f625f;
                        }
                        if (i13 < i14 && aVar.f626a > 0.0f) {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                        if (mode == 1073741824 || ((ViewGroup.MarginLayoutParams) aVar).width != -1) {
                            z3 = false;
                        } else {
                            z3 = true;
                            z7 = true;
                        }
                        int i27 = ((ViewGroup.MarginLayoutParams) aVar).leftMargin + ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                        int measuredWidth = view.getMeasuredWidth() + i27;
                        int iMax5 = Math.max(i10, measuredWidth);
                        int iCombineMeasuredStates2 = View.combineMeasuredStates(i9, view.getMeasuredState());
                        boolean z8 = z6 && ((ViewGroup.MarginLayoutParams) aVar).width == -1;
                        if (aVar.f626a > 0.0f) {
                            if (!z3) {
                                i27 = measuredWidth;
                            }
                            iMax2 = Math.max(iMax2, i27);
                            iMax4 = i12;
                        } else {
                            if (!z3) {
                                i27 = measuredWidth;
                            }
                            iMax4 = Math.max(i12, i27);
                        }
                        int iA2 = a(view, i13) + i13;
                        i17 = iMax3;
                        z6 = z8;
                        i18 = iMax2;
                        f3 = f4;
                        i19 = iMax4;
                        i15 = iCombineMeasuredStates2;
                        iA = iA2;
                        i16 = iMax5;
                    }
                    iA++;
                    mode2 = i11;
                    virtualChildCount = virtualChildCount;
                }
                i11 = mode2;
                iA++;
                mode2 = i11;
                virtualChildCount = virtualChildCount;
            } else {
                int i28 = i15;
                int i29 = i17;
                int i30 = i19;
                int i31 = virtualChildCount;
                int iMax6 = i16;
                int i32 = mode2;
                if (this.f625f > 0) {
                    i3 = i31;
                    if (b(i3)) {
                        this.f625f += this.m;
                    }
                } else {
                    i3 = i31;
                }
                if (z4 && (i32 == Integer.MIN_VALUE || i32 == 0)) {
                    this.f625f = 0;
                    int iA3 = 0;
                    while (iA3 < i3) {
                        View viewA2 = a(iA3);
                        if (viewA2 == null) {
                            this.f625f += c(iA3);
                        } else if (viewA2.getVisibility() == i20) {
                            iA3 += a(viewA2, iA3);
                        } else {
                            a aVar2 = (a) viewA2.getLayoutParams();
                            int i33 = this.f625f;
                            this.f625f = Math.max(i33, i33 + i29 + ((ViewGroup.MarginLayoutParams) aVar2).topMargin + ((ViewGroup.MarginLayoutParams) aVar2).bottomMargin + b(viewA2));
                        }
                        iA3++;
                        i20 = 8;
                    }
                }
                this.f625f += getPaddingTop() + getPaddingBottom();
                int iResolveSizeAndState = View.resolveSizeAndState(Math.max(this.f625f, getSuggestedMinimumHeight()), i2, 0);
                int i34 = (16777215 & iResolveSizeAndState) - this.f625f;
                if (!z5 && (i34 == 0 || f3 <= 0.0f)) {
                    iMax = Math.max(i30, i18);
                    if (z4 && i32 != 1073741824) {
                        for (int i35 = 0; i35 < i3; i35++) {
                            View viewA3 = a(i35);
                            if (viewA3 != null && viewA3.getVisibility() != 8 && ((a) viewA3.getLayoutParams()).f626a > 0.0f) {
                                viewA3.measure(View.MeasureSpec.makeMeasureSpec(viewA3.getMeasuredWidth(), MemoryConstants.GB), View.MeasureSpec.makeMeasureSpec(i29, MemoryConstants.GB));
                            }
                        }
                    }
                    iCombineMeasuredStates = i28;
                } else {
                    float f5 = this.g;
                    if (f5 > 0.0f) {
                        f3 = f5;
                    }
                    this.f625f = 0;
                    float f6 = f3;
                    int i36 = 0;
                    int iMax7 = i30;
                    iCombineMeasuredStates = i28;
                    while (i36 < i3) {
                        View viewA4 = a(i36);
                        if (viewA4.getVisibility() == 8) {
                            f2 = f6;
                        } else {
                            a aVar3 = (a) viewA4.getLayoutParams();
                            float f7 = aVar3.f626a;
                            if (f7 > 0.0f) {
                                int i37 = (int) ((i34 * f7) / f6);
                                i4 = i34 - i37;
                                f2 = f6 - f7;
                                int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + ((ViewGroup.MarginLayoutParams) aVar3).leftMargin + ((ViewGroup.MarginLayoutParams) aVar3).rightMargin, ((ViewGroup.MarginLayoutParams) aVar3).width);
                                if (((ViewGroup.MarginLayoutParams) aVar3).height == 0) {
                                    i7 = MemoryConstants.GB;
                                    if (i32 == 1073741824) {
                                        if (i37 <= 0) {
                                            i37 = 0;
                                        }
                                        viewA4.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(i37, MemoryConstants.GB));
                                    }
                                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, viewA4.getMeasuredState() & (-256));
                                } else {
                                    i7 = MemoryConstants.GB;
                                }
                                int measuredHeight2 = viewA4.getMeasuredHeight() + i37;
                                if (measuredHeight2 < 0) {
                                    measuredHeight2 = 0;
                                }
                                viewA4.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight2, i7));
                                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, viewA4.getMeasuredState() & (-256));
                            } else {
                                i4 = i34;
                                f2 = f6;
                            }
                            int i38 = ((ViewGroup.MarginLayoutParams) aVar3).leftMargin + ((ViewGroup.MarginLayoutParams) aVar3).rightMargin;
                            int measuredWidth2 = viewA4.getMeasuredWidth() + i38;
                            iMax6 = Math.max(iMax6, measuredWidth2);
                            if (mode != 1073741824) {
                                i5 = iCombineMeasuredStates;
                                i6 = -1;
                                z = ((ViewGroup.MarginLayoutParams) aVar3).width == -1;
                                if (!z) {
                                    i38 = measuredWidth2;
                                }
                                iMax7 = Math.max(iMax7, i38);
                                if (z6 || ((ViewGroup.MarginLayoutParams) aVar3).width != i6) {
                                    z2 = false;
                                } else {
                                    z2 = true;
                                }
                                int i39 = this.f625f;
                                this.f625f = Math.max(i39, viewA4.getMeasuredHeight() + i39 + ((ViewGroup.MarginLayoutParams) aVar3).topMargin + ((ViewGroup.MarginLayoutParams) aVar3).bottomMargin + b(viewA4));
                                z6 = z2;
                                i34 = i4;
                                iCombineMeasuredStates = i5;
                            } else {
                                i5 = iCombineMeasuredStates;
                                i6 = -1;
                            }
                            if (!z) {
                                i38 = measuredWidth2;
                            }
                            iMax7 = Math.max(iMax7, i38);
                            if (z6) {
                                z2 = false;
                            } else {
                                z2 = false;
                            }
                            int i310 = this.f625f;
                            this.f625f = Math.max(i310, viewA4.getMeasuredHeight() + i310 + ((ViewGroup.MarginLayoutParams) aVar3).topMargin + ((ViewGroup.MarginLayoutParams) aVar3).bottomMargin + b(viewA4));
                            z6 = z2;
                            i34 = i4;
                            iCombineMeasuredStates = i5;
                        }
                        i36++;
                        f6 = f2;
                    }
                    this.f625f += getPaddingTop() + getPaddingBottom();
                    iMax = iMax7;
                }
                if (z6 || mode == 1073741824) {
                    iMax = iMax6;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, iCombineMeasuredStates), iResolveSizeAndState);
                if (z7) {
                    d(i3, i2);
                    return;
                }
                return;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00a0  */
    void b(int i, int i2, int i3, int i4) {
        int paddingTop;
        int i5;
        int i6;
        int i7;
        int paddingLeft = getPaddingLeft();
        int i8 = i3 - i;
        int paddingRight = i8 - getPaddingRight();
        int paddingRight2 = (i8 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i9 = this.f624e;
        int i10 = i9 & 112;
        int i11 = i9 & 8388615;
        if (i10 == 16) {
            paddingTop = getPaddingTop() + (((i4 - i2) - this.f625f) / 2);
        } else if (i10 != 80) {
            paddingTop = getPaddingTop();
        } else {
            paddingTop = ((getPaddingTop() + i4) - i2) - this.f625f;
        }
        int iA = 0;
        while (iA < virtualChildCount) {
            View viewA = a(iA);
            if (viewA == null) {
                paddingTop += c(iA);
            } else if (viewA.getVisibility() != 8) {
                int measuredWidth = viewA.getMeasuredWidth();
                int measuredHeight = viewA.getMeasuredHeight();
                a aVar = (a) viewA.getLayoutParams();
                int i12 = aVar.f627b;
                if (i12 < 0) {
                    i12 = i11;
                }
                int iA2 = androidx.core.f.c.a(i12, androidx.core.f.t.j(this)) & 7;
                if (iA2 == 1) {
                    i5 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + ((ViewGroup.MarginLayoutParams) aVar).leftMargin;
                    i6 = ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                } else {
                    if (iA2 != 5) {
                        i7 = ((ViewGroup.MarginLayoutParams) aVar).leftMargin + paddingLeft;
                    } else {
                        i5 = paddingRight - measuredWidth;
                        i6 = ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                    }
                    int i13 = i7;
                    if (b(iA)) {
                        paddingTop += this.m;
                    }
                    int i14 = paddingTop + ((ViewGroup.MarginLayoutParams) aVar).topMargin;
                    a(viewA, i13, i14 + a(viewA), measuredWidth, measuredHeight);
                    int iB = i14 + measuredHeight + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin + b(viewA);
                    iA += a(viewA, iA);
                    paddingTop = iB;
                }
                i7 = i5 - i6;
                int i15 = i7;
                if (b(iA)) {
                    paddingTop += this.m;
                }
                int i16 = paddingTop + ((ViewGroup.MarginLayoutParams) aVar).topMargin;
                a(viewA, i15, i16 + a(viewA), measuredWidth, measuredHeight);
                int iB2 = i16 + measuredHeight + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin + b(viewA);
                iA += a(viewA, iA);
                paddingTop = iB2;
            }
            iA++;
        }
    }

    void a(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:32:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:36:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:38:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:40:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:42:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:44:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:46:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:49:0x00f8  */
    void a(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int i6;
        int i7;
        int baseline;
        int i8;
        int i9;
        int i10;
        int i11;
        int measuredHeight;
        boolean zA = j0.a(this);
        int paddingTop = getPaddingTop();
        int i12 = i4 - i2;
        int paddingBottom = i12 - getPaddingBottom();
        int paddingBottom2 = (i12 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i13 = this.f624e;
        int i14 = i13 & 112;
        boolean z = this.f620a;
        int[] iArr = this.i;
        int[] iArr2 = this.j;
        int iA = androidx.core.f.c.a(8388615 & i13, androidx.core.f.t.j(this));
        if (iA == 1) {
            paddingLeft = getPaddingLeft() + (((i3 - i) - this.f625f) / 2);
        } else if (iA != 5) {
            paddingLeft = getPaddingLeft();
        } else {
            paddingLeft = ((getPaddingLeft() + i3) - i) - this.f625f;
        }
        if (zA) {
            i5 = virtualChildCount - 1;
            i6 = -1;
        } else {
            i5 = 0;
            i6 = 1;
        }
        int iA2 = 0;
        while (iA2 < virtualChildCount) {
            int i15 = i5 + (i6 * iA2);
            View viewA = a(i15);
            if (viewA == null) {
                paddingLeft += c(i15);
            } else {
                if (viewA.getVisibility() != 8) {
                    int measuredWidth = viewA.getMeasuredWidth();
                    int measuredHeight2 = viewA.getMeasuredHeight();
                    a aVar = (a) viewA.getLayoutParams();
                    int i16 = iA2;
                    if (z) {
                        i7 = virtualChildCount;
                        baseline = ((ViewGroup.MarginLayoutParams) aVar).height != -1 ? viewA.getBaseline() : -1;
                        i8 = aVar.f627b;
                        if (i8 < 0) {
                            i8 = i14;
                        }
                        i9 = i8 & 112;
                        if (i9 != 16) {
                            i10 = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + ((ViewGroup.MarginLayoutParams) aVar).topMargin) - ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                        } else if (i9 != 48) {
                            i11 = ((ViewGroup.MarginLayoutParams) aVar).topMargin + paddingTop;
                            if (baseline != -1) {
                                i11 += iArr[1] - baseline;
                            }
                            i10 = i11;
                        } else if (i9 != 80) {
                            i10 = paddingTop;
                        } else {
                            measuredHeight = (paddingBottom - measuredHeight2) - ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                            if (baseline != -1) {
                                measuredHeight -= iArr2[2] - (viewA.getMeasuredHeight() - baseline);
                            }
                            i10 = measuredHeight;
                        }
                        if (b(i15)) {
                            paddingLeft += this.l;
                        }
                        int i17 = ((ViewGroup.MarginLayoutParams) aVar).leftMargin + paddingLeft;
                        a(viewA, i17 + a(viewA), i10, measuredWidth, measuredHeight2);
                        int iB = i17 + measuredWidth + ((ViewGroup.MarginLayoutParams) aVar).rightMargin + b(viewA);
                        iA2 = i16 + a(viewA, i15);
                        paddingLeft = iB;
                    } else {
                        i7 = virtualChildCount;
                    }
                    i8 = aVar.f627b;
                    if (i8 < 0) {
                        i8 = i14;
                    }
                    i9 = i8 & 112;
                    if (i9 != 16) {
                        i10 = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + ((ViewGroup.MarginLayoutParams) aVar).topMargin) - ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                    } else if (i9 != 48) {
                        i11 = ((ViewGroup.MarginLayoutParams) aVar).topMargin + paddingTop;
                        if (baseline != -1) {
                            i11 += iArr[1] - baseline;
                        }
                        i10 = i11;
                    } else if (i9 != 80) {
                        i10 = paddingTop;
                    } else {
                        measuredHeight = (paddingBottom - measuredHeight2) - ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                        if (baseline != -1) {
                            measuredHeight -= iArr2[2] - (viewA.getMeasuredHeight() - baseline);
                        }
                        i10 = measuredHeight;
                    }
                    if (b(i15)) {
                        paddingLeft += this.l;
                    }
                    int i18 = ((ViewGroup.MarginLayoutParams) aVar).leftMargin + paddingLeft;
                    a(viewA, i18 + a(viewA), i10, measuredWidth, measuredHeight2);
                    int iB2 = i18 + measuredWidth + ((ViewGroup.MarginLayoutParams) aVar).rightMargin + b(viewA);
                    iA2 = i16 + a(viewA, i15);
                    paddingLeft = iB2;
                }
                iA2++;
                virtualChildCount = i7;
                i14 = i14;
                paddingTop = paddingTop;
            }
            i7 = virtualChildCount;
            iA2++;
            virtualChildCount = i7;
            i14 = i14;
            paddingTop = paddingTop;
        }
    }

    private void a(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }
}
