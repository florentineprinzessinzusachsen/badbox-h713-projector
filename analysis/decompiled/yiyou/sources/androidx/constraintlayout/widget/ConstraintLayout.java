package androidx.constraintlayout.widget;

import a.c.a.j.e;
import a.c.a.j.f;
import a.c.a.j.g;
import a.c.a.j.i;
import a.c.a.j.m;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.constant.MemoryConstants;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    SparseArray<View> f857a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ArrayList<ConstraintHelper> f858b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ArrayList<f> f859c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    g f860d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f861e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f862f;
    private int g;
    private int h;
    private boolean i;
    private int j;
    private androidx.constraintlayout.widget.a k;
    private int l;
    private HashMap<String, Integer> m;
    private int n;
    private int o;
    private a.c.a.f p;

    public ConstraintLayout(Context context) {
        super(context);
        this.f857a = new SparseArray<>();
        this.f858b = new ArrayList<>(4);
        this.f859c = new ArrayList<>(100);
        this.f860d = new g();
        this.f861e = 0;
        this.f862f = 0;
        this.g = Integer.MAX_VALUE;
        this.h = Integer.MAX_VALUE;
        this.i = true;
        this.j = 7;
        this.k = null;
        this.l = -1;
        this.m = new HashMap<>();
        this.n = -1;
        this.o = -1;
        a((AttributeSet) null);
    }

    private void b() {
        int childCount = getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).isLayoutRequested()) {
                z = true;
                break;
            }
        }
        if (z) {
            this.f859c.clear();
            a();
        }
    }

    private void c() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof Placeholder) {
                ((Placeholder) childAt).a(this);
            }
        }
        int size = this.f858b.size();
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                this.f858b.get(i2).b(this);
            }
        }
    }

    public void a(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.m == null) {
                this.m = new HashMap<>();
            }
            String strSubstring = (String) obj;
            int iIndexOf = strSubstring.indexOf("/");
            if (iIndexOf != -1) {
                strSubstring = strSubstring.substring(iIndexOf + 1);
            }
            this.m.put(strSubstring, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (Build.VERSION.SDK_INT < 14) {
            onViewAdded(view);
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Object tag;
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = getWidth();
            float height = getHeight();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(",");
                    if (strArrSplit.length == 4) {
                        int i2 = Integer.parseInt(strArrSplit[0]);
                        int i3 = Integer.parseInt(strArrSplit[1]);
                        int i4 = Integer.parseInt(strArrSplit[2]);
                        int i5 = (int) ((i2 / 1080.0f) * width);
                        int i6 = (int) ((i3 / 1920.0f) * height);
                        int i7 = (int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f2 = i5;
                        float f3 = i6;
                        float f4 = i5 + ((int) ((i4 / 1080.0f) * width));
                        canvas.drawLine(f2, f3, f4, f3, paint);
                        float f5 = i6 + i7;
                        canvas.drawLine(f4, f3, f4, f5, paint);
                        canvas.drawLine(f4, f5, f2, f5, paint);
                        canvas.drawLine(f2, f5, f2, f3, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f2, f3, f4, f5, paint);
                        canvas.drawLine(f2, f5, f4, f3, paint);
                    }
                }
            }
        }
    }

    public int getMaxHeight() {
        return this.h;
    }

    public int getMaxWidth() {
        return this.g;
    }

    public int getMinHeight() {
        return this.f862f;
    }

    public int getMinWidth() {
        return this.f861e;
    }

    public int getOptimizationLevel() {
        return this.f860d.M();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View content;
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            a aVar = (a) childAt.getLayoutParams();
            f fVar = aVar.k0;
            if ((childAt.getVisibility() != 8 || aVar.X || aVar.Y || zIsInEditMode) && !aVar.Z) {
                int iG = fVar.g();
                int iH = fVar.h();
                int iS = fVar.s() + iG;
                int i6 = fVar.i() + iH;
                childAt.layout(iG, iH, iS, i6);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(iG, iH, iS, i6);
                }
            }
        }
        int size = this.f858b.size();
        if (size > 0) {
            for (int i7 = 0; i7 < size; i7++) {
                this.f858b.get(i7).a(this);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:61:0x011d  */
    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        boolean z;
        int i3;
        boolean z2;
        boolean z3;
        int i4;
        int i5;
        boolean z4;
        int baseline;
        System.currentTimeMillis();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.f860d.r(paddingLeft);
        this.f860d.s(paddingTop);
        this.f860d.j(this.g);
        this.f860d.i(this.h);
        if (Build.VERSION.SDK_INT >= 17) {
            this.f860d.c(getLayoutDirection() == 1);
        }
        c(i, i2);
        int iS = this.f860d.s();
        int i6 = this.f860d.i();
        if (this.i) {
            this.i = false;
            b();
            z = true;
        } else {
            z = false;
        }
        boolean z5 = (this.j & 8) == 8;
        if (z5) {
            this.f860d.T();
            this.f860d.f(iS, i6);
            b(i, i2);
        } else {
            a(i, i2);
        }
        c();
        if (getChildCount() > 0 && z) {
            a.c.a.j.a.a(this.f860d);
        }
        g gVar = this.f860d;
        if (gVar.x0) {
            if (gVar.y0 && mode == Integer.MIN_VALUE) {
                int i7 = gVar.A0;
                if (i7 < size) {
                    gVar.o(i7);
                }
                this.f860d.a(f.b.FIXED);
            }
            g gVar2 = this.f860d;
            if (gVar2.z0 && mode2 == Integer.MIN_VALUE) {
                int i8 = gVar2.B0;
                if (i8 < size2) {
                    gVar2.g(i8);
                }
                this.f860d.b(f.b.FIXED);
            }
        }
        if ((this.j & 32) == 32) {
            int iS2 = this.f860d.s();
            int i9 = this.f860d.i();
            if (this.n != iS2 && mode == 1073741824) {
                a.c.a.j.a.a(this.f860d.w0, 0, iS2);
            }
            if (this.o != i9 && mode2 == 1073741824) {
                a.c.a.j.a.a(this.f860d.w0, 1, i9);
            }
            g gVar3 = this.f860d;
            if (gVar3.y0 && gVar3.A0 > size) {
                a.c.a.j.a.a(gVar3.w0, 0, size);
            }
            g gVar4 = this.f860d;
            if (gVar4.z0 && gVar4.B0 > size2) {
                a.c.a.j.a.a(gVar4.w0, 1, size2);
            }
        }
        if (getChildCount() > 0) {
            a("First pass");
        }
        int size3 = this.f859c.size();
        int paddingBottom = paddingTop + getPaddingBottom();
        int paddingRight = paddingLeft + getPaddingRight();
        if (size3 > 0) {
            boolean z6 = this.f860d.j() == f.b.WRAP_CONTENT;
            boolean z7 = this.f860d.q() == f.b.WRAP_CONTENT;
            int iMax = Math.max(this.f860d.s(), this.f861e);
            int iMax2 = Math.max(this.f860d.i(), this.f862f);
            int i10 = 0;
            boolean z8 = false;
            int iCombineMeasuredStates = 0;
            while (i10 < size3) {
                f fVar = this.f859c.get(i10);
                int i11 = size3;
                View view = (View) fVar.e();
                if (view == null) {
                    i5 = iS;
                    z4 = z8;
                    i4 = i6;
                } else {
                    i4 = i6;
                    a aVar = (a) view.getLayoutParams();
                    i5 = iS;
                    if (aVar.Y || aVar.X) {
                        z4 = z8;
                    } else {
                        z4 = z8;
                        if (view.getVisibility() != 8 && (!z5 || !fVar.m().c() || !fVar.l().c())) {
                            view.measure((((ViewGroup.MarginLayoutParams) aVar).width == -2 && aVar.U) ? ViewGroup.getChildMeasureSpec(i, paddingRight, ((ViewGroup.MarginLayoutParams) aVar).width) : View.MeasureSpec.makeMeasureSpec(fVar.s(), MemoryConstants.GB), (((ViewGroup.MarginLayoutParams) aVar).height == -2 && aVar.V) ? ViewGroup.getChildMeasureSpec(i2, paddingBottom, ((ViewGroup.MarginLayoutParams) aVar).height) : View.MeasureSpec.makeMeasureSpec(fVar.i(), MemoryConstants.GB));
                            a.c.a.f fVar2 = this.p;
                            if (fVar2 != null) {
                                fVar2.f90b++;
                            }
                            int measuredWidth = view.getMeasuredWidth();
                            int measuredHeight = view.getMeasuredHeight();
                            if (measuredWidth != fVar.s()) {
                                fVar.o(measuredWidth);
                                if (z5) {
                                    fVar.m().a(measuredWidth);
                                }
                                if (z6 && fVar.n() > iMax) {
                                    iMax = Math.max(iMax, fVar.n() + fVar.a(e.d.RIGHT).b());
                                }
                                z4 = true;
                            }
                            if (measuredHeight != fVar.i()) {
                                fVar.g(measuredHeight);
                                if (z5) {
                                    fVar.l().a(measuredHeight);
                                }
                                if (z7 && fVar.d() > iMax2) {
                                    iMax2 = Math.max(iMax2, fVar.d() + fVar.a(e.d.BOTTOM).b());
                                }
                                z4 = true;
                            }
                            if (aVar.W && (baseline = view.getBaseline()) != -1 && baseline != fVar.c()) {
                                fVar.f(baseline);
                                z4 = true;
                            }
                            if (Build.VERSION.SDK_INT >= 11) {
                                iCombineMeasuredStates = ViewGroup.combineMeasuredStates(iCombineMeasuredStates, view.getMeasuredState());
                            }
                        }
                        z8 = z4;
                        i10++;
                        iS = i5;
                        size3 = i11;
                        i6 = i4;
                    }
                }
                iCombineMeasuredStates = iCombineMeasuredStates;
                z8 = z4;
                i10++;
                iS = i5;
                size3 = i11;
                i6 = i4;
            }
            int i12 = size3;
            int i13 = iS;
            int i14 = i6;
            i3 = iCombineMeasuredStates;
            if (z8) {
                this.f860d.o(i13);
                this.f860d.g(i14);
                if (z5) {
                    this.f860d.U();
                }
                a("2nd pass");
                if (this.f860d.s() < iMax) {
                    this.f860d.o(iMax);
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (this.f860d.i() < iMax2) {
                    this.f860d.g(iMax2);
                    z3 = true;
                } else {
                    z3 = z2;
                }
                if (z3) {
                    a("3rd pass");
                }
            }
            for (int i15 = 0; i15 < i12; i15++) {
                f fVar3 = this.f859c.get(i15);
                View view2 = (View) fVar3.e();
                if (view2 != null && (view2.getMeasuredWidth() != fVar3.s() || view2.getMeasuredHeight() != fVar3.i())) {
                    if (fVar3.r() != 8) {
                        view2.measure(View.MeasureSpec.makeMeasureSpec(fVar3.s(), MemoryConstants.GB), View.MeasureSpec.makeMeasureSpec(fVar3.i(), MemoryConstants.GB));
                        a.c.a.f fVar4 = this.p;
                        if (fVar4 != null) {
                            fVar4.f90b++;
                        }
                    }
                }
            }
        } else {
            i3 = 0;
        }
        int iS3 = this.f860d.s() + paddingRight;
        int i16 = this.f860d.i() + paddingBottom;
        if (Build.VERSION.SDK_INT < 11) {
            setMeasuredDimension(iS3, i16);
            this.n = iS3;
            this.o = i16;
            return;
        }
        int iResolveSizeAndState = ViewGroup.resolveSizeAndState(iS3, i, i3);
        int iResolveSizeAndState2 = ViewGroup.resolveSizeAndState(i16, i2, i3 << 16) & 16777215;
        int iMin = Math.min(this.g, iResolveSizeAndState & 16777215);
        int iMin2 = Math.min(this.h, iResolveSizeAndState2);
        if (this.f860d.Q()) {
            iMin |= 16777216;
        }
        if (this.f860d.O()) {
            iMin2 |= 16777216;
        }
        setMeasuredDimension(iMin, iMin2);
        this.n = iMin;
        this.o = iMin2;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        f fVarA = a(view);
        if ((view instanceof Guideline) && !(fVarA instanceof i)) {
            a aVar = (a) view.getLayoutParams();
            aVar.k0 = new i();
            aVar.X = true;
            ((i) aVar.k0).v(aVar.R);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.a();
            ((a) view.getLayoutParams()).Y = true;
            if (!this.f858b.contains(constraintHelper)) {
                this.f858b.add(constraintHelper);
            }
        }
        this.f857a.put(view.getId(), view);
        this.i = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.f857a.remove(view.getId());
        f fVarA = a(view);
        this.f860d.c(fVarA);
        this.f858b.remove(view);
        this.f859c.remove(fVarA);
        this.i = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        super.removeView(view);
        if (Build.VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        this.i = true;
        this.n = -1;
        this.o = -1;
    }

    public void setConstraintSet(androidx.constraintlayout.widget.a aVar) {
        this.k = aVar;
    }

    @Override // android.view.View
    public void setId(int i) {
        this.f857a.remove(getId());
        super.setId(i);
        this.f857a.put(getId(), this);
    }

    public void setMaxHeight(int i) {
        if (i == this.h) {
            return;
        }
        this.h = i;
        requestLayout();
    }

    public void setMaxWidth(int i) {
        if (i == this.g) {
            return;
        }
        this.g = i;
        requestLayout();
    }

    public void setMinHeight(int i) {
        if (i == this.f862f) {
            return;
        }
        this.f862f = i;
        requestLayout();
    }

    public void setMinWidth(int i) {
        if (i == this.f861e) {
            return;
        }
        this.f861e = i;
        requestLayout();
    }

    public void setOptimizationLevel(int i) {
        this.f860d.u(i);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public a generateDefaultLayoutParams() {
        return new a(-2, -2);
    }

    @Override // android.view.ViewGroup
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    private final f b(int i) {
        if (i == 0) {
            return this.f860d;
        }
        View viewFindViewById = this.f857a.get(i);
        if (viewFindViewById == null && (viewFindViewById = findViewById(i)) != null && viewFindViewById != this && viewFindViewById.getParent() == this) {
            onViewAdded(viewFindViewById);
        }
        if (viewFindViewById == this) {
            return this.f860d;
        }
        if (viewFindViewById == null) {
            return null;
        }
        return ((a) viewFindViewById.getLayoutParams()).k0;
    }

    private void c(int i, int i2) {
        int iMin;
        f.b bVar;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        f.b bVar2 = f.b.FIXED;
        getLayoutParams();
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                bVar = f.b.WRAP_CONTENT;
            } else if (mode != 1073741824) {
                bVar = bVar2;
            } else {
                iMin = Math.min(this.g, size) - paddingLeft;
                bVar = bVar2;
            }
            iMin = 0;
        } else {
            iMin = size;
            bVar = f.b.WRAP_CONTENT;
        }
        if (mode2 != Integer.MIN_VALUE) {
            if (mode2 == 0) {
                bVar2 = f.b.WRAP_CONTENT;
            } else if (mode2 == 1073741824) {
                size2 = Math.min(this.h, size2) - paddingTop;
            }
            size2 = 0;
        } else {
            bVar2 = f.b.WRAP_CONTENT;
        }
        this.f860d.l(0);
        this.f860d.k(0);
        this.f860d.a(bVar);
        this.f860d.o(iMin);
        this.f860d.b(bVar2);
        this.f860d.g(size2);
        this.f860d.l((this.f861e - getPaddingLeft()) - getPaddingRight());
        this.f860d.k((this.f862f - getPaddingTop()) - getPaddingBottom());
    }

    public Object a(int i, Object obj) {
        if (i != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> map = this.m;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.m.get(str);
    }

    private void a(AttributeSet attributeSet) {
        this.f860d.a(this);
        this.f857a.put(getId(), this);
        this.k = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.f861e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f861e);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.f862f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f862f);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.g);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.h = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.h);
                } else if (index == R$styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.j = typedArrayObtainStyledAttributes.getInt(index, this.j);
                } else if (index == R$styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        this.k = new androidx.constraintlayout.widget.a();
                        this.k.a(getContext(), resourceId);
                    } catch (Resources.NotFoundException unused) {
                        this.k = null;
                    }
                    this.l = resourceId;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f860d.u(this.j);
    }

    /* JADX WARN: Code duplicated, block: B:163:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:51:0x00d4  */
    private void b(int i, int i2) {
        long j;
        int i3;
        int i4;
        int i5;
        long j2;
        boolean z;
        int childMeasureSpec;
        boolean z2;
        int childMeasureSpec2;
        int baseline;
        int i6;
        int baseline2;
        ConstraintLayout constraintLayout = this;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        int i7 = 0;
        while (true) {
            j = 1;
            i3 = 8;
            if (i7 >= childCount) {
                break;
            }
            View childAt = constraintLayout.getChildAt(i7);
            if (childAt.getVisibility() == 8) {
                i6 = paddingTop;
            } else {
                a aVar = (a) childAt.getLayoutParams();
                f fVar = aVar.k0;
                if (aVar.X || aVar.Y) {
                    i6 = paddingTop;
                } else {
                    fVar.n(childAt.getVisibility());
                    int i8 = ((ViewGroup.MarginLayoutParams) aVar).width;
                    int i9 = ((ViewGroup.MarginLayoutParams) aVar).height;
                    if (i8 != 0 && i9 != 0) {
                        boolean z3 = i8 == -2;
                        int childMeasureSpec3 = ViewGroup.getChildMeasureSpec(i, paddingLeft, i8);
                        boolean z4 = i9 == -2;
                        childAt.measure(childMeasureSpec3, ViewGroup.getChildMeasureSpec(i2, paddingTop, i9));
                        a.c.a.f fVar2 = constraintLayout.p;
                        i6 = paddingTop;
                        if (fVar2 != null) {
                            fVar2.f89a++;
                        }
                        fVar.b(i8 == -2);
                        fVar.a(i9 == -2);
                        int measuredWidth = childAt.getMeasuredWidth();
                        int measuredHeight = childAt.getMeasuredHeight();
                        fVar.o(measuredWidth);
                        fVar.g(measuredHeight);
                        if (z3) {
                            fVar.q(measuredWidth);
                        }
                        if (z4) {
                            fVar.p(measuredHeight);
                        }
                        if (aVar.W && (baseline2 = childAt.getBaseline()) != -1) {
                            fVar.f(baseline2);
                        }
                        if (aVar.U && aVar.V) {
                            fVar.m().a(measuredWidth);
                            fVar.l().a(measuredHeight);
                        }
                    } else {
                        i6 = paddingTop;
                        fVar.m().b();
                        fVar.l().b();
                    }
                }
            }
            i7++;
            paddingTop = i6;
        }
        int i10 = paddingTop;
        constraintLayout.f860d.U();
        int i11 = 0;
        while (i11 < childCount) {
            View childAt2 = constraintLayout.getChildAt(i11);
            if (childAt2.getVisibility() == i3) {
                i4 = i11;
                i5 = childCount;
                j2 = j;
            } else {
                a aVar2 = (a) childAt2.getLayoutParams();
                f fVar3 = aVar2.k0;
                if (aVar2.X || aVar2.Y) {
                    i4 = i11;
                    i5 = childCount;
                    j2 = j;
                } else {
                    fVar3.n(childAt2.getVisibility());
                    int iF = ((ViewGroup.MarginLayoutParams) aVar2).width;
                    int iF2 = ((ViewGroup.MarginLayoutParams) aVar2).height;
                    if (iF == 0 || iF2 == 0) {
                        m mVarD = fVar3.a(e.d.LEFT).d();
                        m mVarD2 = fVar3.a(e.d.RIGHT).d();
                        boolean z5 = (fVar3.a(e.d.LEFT).g() == null || fVar3.a(e.d.RIGHT).g() == null) ? false : true;
                        m mVarD3 = fVar3.a(e.d.TOP).d();
                        m mVarD4 = fVar3.a(e.d.BOTTOM).d();
                        i5 = childCount;
                        boolean z6 = (fVar3.a(e.d.TOP).g() == null || fVar3.a(e.d.BOTTOM).g() == null) ? false : true;
                        if (iF == 0 && iF2 == 0 && z5 && z6) {
                            i4 = i11;
                            j2 = 1;
                        } else {
                            i4 = i11;
                            boolean z7 = constraintLayout.f860d.j() != f.b.WRAP_CONTENT;
                            boolean z8 = constraintLayout.f860d.q() != f.b.WRAP_CONTENT;
                            if (!z7) {
                                fVar3.m().b();
                            }
                            if (!z8) {
                                fVar3.l().b();
                            }
                            if (iF == 0) {
                                if (z7 && fVar3.C() && z5 && mVarD.c() && mVarD2.c()) {
                                    iF = (int) (mVarD2.f() - mVarD.f());
                                    fVar3.m().a(iF);
                                    childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, iF);
                                    z = false;
                                } else {
                                    childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, -2);
                                    z = true;
                                    z7 = false;
                                }
                            } else if (iF == -1) {
                                childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, -1);
                                z = false;
                            } else {
                                z = iF == -2;
                                childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, iF);
                            }
                            if (iF2 == 0) {
                                if (z8 && fVar3.B() && z6 && mVarD3.c() && mVarD4.c()) {
                                    iF2 = (int) (mVarD4.f() - mVarD3.f());
                                    fVar3.l().a(iF2);
                                    childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, i10, iF2);
                                    z2 = false;
                                } else {
                                    childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, i10, -2);
                                    z2 = true;
                                    z8 = false;
                                }
                            } else if (iF2 == -1) {
                                childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, i10, -1);
                                z2 = false;
                            } else {
                                z2 = iF2 == -2;
                                z8 = z8;
                                childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, i10, iF2);
                            }
                            childAt2.measure(childMeasureSpec, childMeasureSpec2);
                            constraintLayout = this;
                            a.c.a.f fVar4 = constraintLayout.p;
                            if (fVar4 != null) {
                                j2 = 1;
                                fVar4.f89a++;
                            } else {
                                j2 = 1;
                            }
                            fVar3.b(iF == -2);
                            fVar3.a(iF2 == -2);
                            int measuredWidth2 = childAt2.getMeasuredWidth();
                            int measuredHeight2 = childAt2.getMeasuredHeight();
                            fVar3.o(measuredWidth2);
                            fVar3.g(measuredHeight2);
                            if (z) {
                                fVar3.q(measuredWidth2);
                            }
                            if (z2) {
                                fVar3.p(measuredHeight2);
                            }
                            if (z7) {
                                fVar3.m().a(measuredWidth2);
                            } else {
                                fVar3.m().f();
                            }
                            if (z8) {
                                fVar3.l().a(measuredHeight2);
                            } else {
                                fVar3.l().f();
                            }
                            if (aVar2.W && (baseline = childAt2.getBaseline()) != -1) {
                                fVar3.f(baseline);
                            }
                        }
                    } else {
                        i4 = i11;
                        i5 = childCount;
                        j2 = j;
                    }
                }
            }
            i11 = i4 + 1;
            childCount = i5;
            j = j2;
            i3 = 8;
        }
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f857a = new SparseArray<>();
        this.f858b = new ArrayList<>(4);
        this.f859c = new ArrayList<>(100);
        this.f860d = new g();
        this.f861e = 0;
        this.f862f = 0;
        this.g = Integer.MAX_VALUE;
        this.h = Integer.MAX_VALUE;
        this.i = true;
        this.j = 7;
        this.k = null;
        this.l = -1;
        this.m = new HashMap<>();
        this.n = -1;
        this.o = -1;
        a(attributeSet);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f857a = new SparseArray<>();
        this.f858b = new ArrayList<>(4);
        this.f859c = new ArrayList<>(100);
        this.f860d = new g();
        this.f861e = 0;
        this.f862f = 0;
        this.g = Integer.MAX_VALUE;
        this.h = Integer.MAX_VALUE;
        this.i = true;
        this.j = 7;
        this.k = null;
        this.l = -1;
        this.m = new HashMap<>();
        this.n = -1;
        this.o = -1;
        a(attributeSet);
    }

    /* JADX WARN: Code duplicated, block: B:122:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:132:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:137:0x0205  */
    /* JADX WARN: Code duplicated, block: B:139:0x020b  */
    /* JADX WARN: Code duplicated, block: B:140:0x0214 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:141:0x0216  */
    /* JADX WARN: Code duplicated, block: B:143:0x021c  */
    /* JADX WARN: Code duplicated, block: B:144:0x0229  */
    /* JADX WARN: Code duplicated, block: B:145:0x022c  */
    /* JADX WARN: Code duplicated, block: B:151:0x0242  */
    /* JADX WARN: Code duplicated, block: B:153:0x0248  */
    /* JADX WARN: Code duplicated, block: B:154:0x0255  */
    /* JADX WARN: Code duplicated, block: B:160:0x026c  */
    /* JADX WARN: Code duplicated, block: B:162:0x0272  */
    /* JADX WARN: Code duplicated, block: B:163:0x027e  */
    /* JADX WARN: Code duplicated, block: B:170:0x0298  */
    /* JADX WARN: Code duplicated, block: B:172:0x029e  */
    /* JADX WARN: Code duplicated, block: B:173:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:180:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:204:0x0346  */
    /* JADX WARN: Code duplicated, block: B:206:0x034a  */
    /* JADX WARN: Code duplicated, block: B:207:0x0364  */
    /* JADX WARN: Code duplicated, block: B:208:0x036e  */
    /* JADX WARN: Code duplicated, block: B:211:0x037c  */
    /* JADX WARN: Code duplicated, block: B:213:0x0380  */
    /* JADX WARN: Code duplicated, block: B:214:0x039b  */
    /* JADX WARN: Code duplicated, block: B:215:0x03a5  */
    /* JADX WARN: Code duplicated, block: B:218:0x03b4  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r26v0, types: [android.view.ViewGroup, androidx.constraintlayout.widget.ConstraintLayout] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v56 */
    private void a() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        float f2;
        int i6;
        float f3;
        f fVarB;
        f fVarB2;
        int i7;
        int i8;
        f fVarB3;
        int i9;
        int i10;
        f fVarB4;
        int i11;
        float f4;
        float f5;
        View view;
        f fVarB5;
        f fVarB6;
        f fVarB7;
        f fVarB8;
        f fVarB9;
        String str;
        f fVarB10;
        boolean zIsInEditMode = isInEditMode();
        int childCount = getChildCount();
        ?? r3 = 0;
        if (zIsInEditMode) {
            for (int i12 = 0; i12 < childCount; i12++) {
                View childAt = getChildAt(i12);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    a(0, resourceName, Integer.valueOf(childAt.getId()));
                    int iIndexOf = resourceName.indexOf(47);
                    if (iIndexOf != -1) {
                        resourceName = resourceName.substring(iIndexOf + 1);
                    }
                    b(childAt.getId()).a(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        for (int i13 = 0; i13 < childCount; i13++) {
            f fVarA = a(getChildAt(i13));
            if (fVarA != null) {
                fVarA.D();
            }
        }
        if (this.l != -1) {
            for (int i14 = 0; i14 < childCount; i14++) {
                View childAt2 = getChildAt(i14);
                if (childAt2.getId() == this.l && (childAt2 instanceof Constraints)) {
                    this.k = ((Constraints) childAt2).getConstraintSet();
                }
            }
        }
        androidx.constraintlayout.widget.a aVar = this.k;
        if (aVar != null) {
            aVar.a((ConstraintLayout) this);
        }
        this.f860d.L();
        int size = this.f858b.size();
        if (size > 0) {
            for (int i15 = 0; i15 < size; i15++) {
                this.f858b.get(i15).c(this);
            }
        }
        for (int i16 = 0; i16 < childCount; i16++) {
            View childAt3 = getChildAt(i16);
            if (childAt3 instanceof Placeholder) {
                ((Placeholder) childAt3).b(this);
            }
        }
        int i17 = 0;
        while (i17 < childCount) {
            View childAt4 = getChildAt(i17);
            f fVarA2 = a(childAt4);
            if (fVarA2 != null) {
                a aVar2 = (a) childAt4.getLayoutParams();
                aVar2.a();
                if (aVar2.l0) {
                    aVar2.l0 = r3;
                } else if (zIsInEditMode) {
                    try {
                        String resourceName2 = getResources().getResourceName(childAt4.getId());
                        a(r3, resourceName2, Integer.valueOf(childAt4.getId()));
                        b(childAt4.getId()).a(resourceName2.substring(resourceName2.indexOf("id/") + 3));
                    } catch (Resources.NotFoundException unused2) {
                    }
                }
                fVarA2.n(childAt4.getVisibility());
                if (aVar2.Z) {
                    fVarA2.n(8);
                }
                fVarA2.a(childAt4);
                this.f860d.b(fVarA2);
                if (!aVar2.V || !aVar2.U) {
                    this.f859c.add(fVarA2);
                }
                if (aVar2.X) {
                    i iVar = (i) fVarA2;
                    int i18 = aVar2.h0;
                    int i19 = aVar2.i0;
                    float f6 = aVar2.j0;
                    if (Build.VERSION.SDK_INT < 17) {
                        i18 = aVar2.f863a;
                        i19 = aVar2.f864b;
                        f6 = aVar2.f865c;
                    }
                    if (f6 != -1.0f) {
                        iVar.e(f6);
                    } else if (i18 != -1) {
                        iVar.t(i18);
                    } else if (i19 != -1) {
                        iVar.u(i19);
                    }
                } else if (aVar2.f866d != -1 || aVar2.f867e != -1 || aVar2.f868f != -1 || aVar2.g != -1 || aVar2.q != -1 || aVar2.p != -1 || aVar2.r != -1 || aVar2.s != -1 || aVar2.h != -1 || aVar2.i != -1 || aVar2.j != -1 || aVar2.k != -1 || aVar2.l != -1 || aVar2.P != -1 || aVar2.Q != -1 || aVar2.m != -1 || ((ViewGroup.MarginLayoutParams) aVar2).width == -1 || ((ViewGroup.MarginLayoutParams) aVar2).height == -1) {
                    int i20 = aVar2.a0;
                    int i21 = aVar2.b0;
                    int i22 = aVar2.c0;
                    int i23 = aVar2.d0;
                    int i24 = aVar2.e0;
                    int i25 = aVar2.f0;
                    float f7 = aVar2.g0;
                    if (Build.VERSION.SDK_INT < 17) {
                        int i26 = aVar2.f866d;
                        int i27 = aVar2.f867e;
                        int i28 = aVar2.f868f;
                        i23 = aVar2.g;
                        int i29 = aVar2.t;
                        int i30 = aVar2.v;
                        f7 = aVar2.z;
                        if (i26 == -1 && i27 == -1) {
                            int i31 = aVar2.q;
                            if (i31 != -1) {
                                i4 = i27;
                                i20 = i31;
                            } else {
                                i4 = aVar2.p;
                                if (i4 == -1) {
                                    i4 = i27;
                                }
                                i20 = i26;
                            }
                        } else {
                            i4 = i27;
                            i20 = i26;
                        }
                        if (i28 == -1 && i23 == -1) {
                            i = aVar2.r;
                            if (i != -1) {
                                i3 = i29;
                                i2 = i30;
                            } else {
                                int i32 = aVar2.s;
                                if (i32 != -1) {
                                    i3 = i29;
                                    i2 = i30;
                                    f2 = f7;
                                    i5 = i32;
                                    i = i28;
                                } else {
                                    i3 = i29;
                                    i2 = i30;
                                    i = i28;
                                }
                            }
                        } else {
                            i3 = i29;
                            i2 = i30;
                            i = i28;
                        }
                        i6 = aVar2.m;
                        if (i6 != -1) {
                            fVarB10 = b(i6);
                            if (fVarB10 != null) {
                                fVarA2.a(fVarB10, aVar2.o, aVar2.n);
                            }
                        } else {
                            if (i20 != -1) {
                                fVarB9 = b(i20);
                                if (fVarB9 != null) {
                                    e.d dVar = e.d.LEFT;
                                    f3 = f2;
                                    fVarA2.a(dVar, fVarB9, dVar, ((ViewGroup.MarginLayoutParams) aVar2).leftMargin, i3);
                                } else {
                                    f3 = f2;
                                }
                            } else {
                                f3 = f2;
                                if (i4 != -1 && (fVarB = b(i4)) != null) {
                                    fVarA2.a(e.d.LEFT, fVarB, e.d.RIGHT, ((ViewGroup.MarginLayoutParams) aVar2).leftMargin, i3);
                                }
                            }
                            if (i != -1) {
                                fVarB8 = b(i);
                                if (fVarB8 != null) {
                                    fVarA2.a(e.d.RIGHT, fVarB8, e.d.LEFT, ((ViewGroup.MarginLayoutParams) aVar2).rightMargin, i2);
                                }
                            } else if (i5 != -1 && (fVarB2 = b(i5)) != null) {
                                e.d dVar2 = e.d.RIGHT;
                                fVarA2.a(dVar2, fVarB2, dVar2, ((ViewGroup.MarginLayoutParams) aVar2).rightMargin, i2);
                            }
                            i7 = aVar2.h;
                            if (i7 != -1) {
                                fVarB7 = b(i7);
                                if (fVarB7 != null) {
                                    e.d dVar3 = e.d.TOP;
                                    fVarA2.a(dVar3, fVarB7, dVar3, ((ViewGroup.MarginLayoutParams) aVar2).topMargin, aVar2.u);
                                }
                            } else {
                                i8 = aVar2.i;
                                if (i8 != -1 && (fVarB3 = b(i8)) != null) {
                                    fVarA2.a(e.d.TOP, fVarB3, e.d.BOTTOM, ((ViewGroup.MarginLayoutParams) aVar2).topMargin, aVar2.u);
                                }
                            }
                            i9 = aVar2.j;
                            if (i9 != -1) {
                                fVarB6 = b(i9);
                                if (fVarB6 != null) {
                                    fVarA2.a(e.d.BOTTOM, fVarB6, e.d.TOP, ((ViewGroup.MarginLayoutParams) aVar2).bottomMargin, aVar2.w);
                                }
                            } else {
                                i10 = aVar2.k;
                                if (i10 != -1 && (fVarB4 = b(i10)) != null) {
                                    e.d dVar4 = e.d.BOTTOM;
                                    fVarA2.a(dVar4, fVarB4, dVar4, ((ViewGroup.MarginLayoutParams) aVar2).bottomMargin, aVar2.w);
                                }
                            }
                            i11 = aVar2.l;
                            if (i11 != -1) {
                                view = this.f857a.get(i11);
                                fVarB5 = b(aVar2.l);
                                if (fVarB5 != null && view != null && (view.getLayoutParams() instanceof a)) {
                                    a aVar3 = (a) view.getLayoutParams();
                                    aVar2.W = true;
                                    aVar3.W = true;
                                    fVarA2.a(e.d.BASELINE).a(fVarB5.a(e.d.BASELINE), 0, -1, e.c.STRONG, 0, true);
                                    fVarA2.a(e.d.TOP).j();
                                    fVarA2.a(e.d.BOTTOM).j();
                                }
                            }
                            f4 = f3;
                            if (f4 >= 0.0f && f4 != 0.5f) {
                                fVarA2.a(f4);
                            }
                            f5 = aVar2.A;
                            if (f5 >= 0.0f && f5 != 0.5f) {
                                fVarA2.c(f5);
                            }
                        }
                        if (zIsInEditMode && (aVar2.P != -1 || aVar2.Q != -1)) {
                            fVarA2.c(aVar2.P, aVar2.Q);
                        }
                        if (!aVar2.U) {
                            if (((ViewGroup.MarginLayoutParams) aVar2).width == -1) {
                                fVarA2.a(f.b.MATCH_PARENT);
                                fVarA2.a(e.d.LEFT).f119e = ((ViewGroup.MarginLayoutParams) aVar2).leftMargin;
                                fVarA2.a(e.d.RIGHT).f119e = ((ViewGroup.MarginLayoutParams) aVar2).rightMargin;
                            } else {
                                fVarA2.a(f.b.MATCH_CONSTRAINT);
                                fVarA2.o(0);
                            }
                        } else {
                            fVarA2.a(f.b.FIXED);
                            fVarA2.o(((ViewGroup.MarginLayoutParams) aVar2).width);
                        }
                        if (!aVar2.V) {
                            if (((ViewGroup.MarginLayoutParams) aVar2).height == -1) {
                                fVarA2.b(f.b.MATCH_PARENT);
                                fVarA2.a(e.d.TOP).f119e = ((ViewGroup.MarginLayoutParams) aVar2).topMargin;
                                fVarA2.a(e.d.BOTTOM).f119e = ((ViewGroup.MarginLayoutParams) aVar2).bottomMargin;
                                r3 = 0;
                            } else {
                                fVarA2.b(f.b.MATCH_CONSTRAINT);
                                r3 = 0;
                                fVarA2.g(0);
                            }
                        } else {
                            r3 = 0;
                            fVarA2.b(f.b.FIXED);
                            fVarA2.g(((ViewGroup.MarginLayoutParams) aVar2).height);
                        }
                        str = aVar2.B;
                        if (str != null) {
                            fVarA2.b(str);
                        }
                        fVarA2.b(aVar2.D);
                        fVarA2.d(aVar2.E);
                        fVarA2.h(aVar2.F);
                        fVarA2.m(aVar2.G);
                        fVarA2.a(aVar2.H, aVar2.J, aVar2.L, aVar2.N);
                        fVarA2.b(aVar2.I, aVar2.K, aVar2.M, aVar2.O);
                    } else {
                        i = i22;
                        i2 = i25;
                        i3 = i24;
                        i4 = i21;
                    }
                    float f8 = f7;
                    i5 = i23;
                    f2 = f8;
                    i6 = aVar2.m;
                    if (i6 != -1) {
                        fVarB10 = b(i6);
                        if (fVarB10 != null) {
                            fVarA2.a(fVarB10, aVar2.o, aVar2.n);
                        }
                    } else {
                        if (i20 != -1) {
                            fVarB9 = b(i20);
                            if (fVarB9 != null) {
                                e.d dVar5 = e.d.LEFT;
                                f3 = f2;
                                fVarA2.a(dVar5, fVarB9, dVar5, ((ViewGroup.MarginLayoutParams) aVar2).leftMargin, i3);
                            } else {
                                f3 = f2;
                            }
                        } else {
                            f3 = f2;
                            if (i4 != -1) {
                                fVarA2.a(e.d.LEFT, fVarB, e.d.RIGHT, ((ViewGroup.MarginLayoutParams) aVar2).leftMargin, i3);
                            }
                        }
                        if (i != -1) {
                            fVarB8 = b(i);
                            if (fVarB8 != null) {
                                fVarA2.a(e.d.RIGHT, fVarB8, e.d.LEFT, ((ViewGroup.MarginLayoutParams) aVar2).rightMargin, i2);
                            }
                        } else if (i5 != -1) {
                            e.d dVar6 = e.d.RIGHT;
                            fVarA2.a(dVar6, fVarB2, dVar6, ((ViewGroup.MarginLayoutParams) aVar2).rightMargin, i2);
                        }
                        i7 = aVar2.h;
                        if (i7 != -1) {
                            fVarB7 = b(i7);
                            if (fVarB7 != null) {
                                e.d dVar7 = e.d.TOP;
                                fVarA2.a(dVar7, fVarB7, dVar7, ((ViewGroup.MarginLayoutParams) aVar2).topMargin, aVar2.u);
                            }
                        } else {
                            i8 = aVar2.i;
                            if (i8 != -1) {
                                fVarA2.a(e.d.TOP, fVarB3, e.d.BOTTOM, ((ViewGroup.MarginLayoutParams) aVar2).topMargin, aVar2.u);
                            }
                        }
                        i9 = aVar2.j;
                        if (i9 != -1) {
                            fVarB6 = b(i9);
                            if (fVarB6 != null) {
                                fVarA2.a(e.d.BOTTOM, fVarB6, e.d.TOP, ((ViewGroup.MarginLayoutParams) aVar2).bottomMargin, aVar2.w);
                            }
                        } else {
                            i10 = aVar2.k;
                            if (i10 != -1) {
                                e.d dVar8 = e.d.BOTTOM;
                                fVarA2.a(dVar8, fVarB4, dVar8, ((ViewGroup.MarginLayoutParams) aVar2).bottomMargin, aVar2.w);
                            }
                        }
                        i11 = aVar2.l;
                        if (i11 != -1) {
                            view = this.f857a.get(i11);
                            fVarB5 = b(aVar2.l);
                            if (fVarB5 != null) {
                                a aVar4 = (a) view.getLayoutParams();
                                aVar2.W = true;
                                aVar4.W = true;
                                fVarA2.a(e.d.BASELINE).a(fVarB5.a(e.d.BASELINE), 0, -1, e.c.STRONG, 0, true);
                                fVarA2.a(e.d.TOP).j();
                                fVarA2.a(e.d.BOTTOM).j();
                            }
                        }
                        f4 = f3;
                        if (f4 >= 0.0f) {
                            fVarA2.a(f4);
                        }
                        f5 = aVar2.A;
                        if (f5 >= 0.0f) {
                            fVarA2.c(f5);
                        }
                    }
                    if (zIsInEditMode) {
                        fVarA2.c(aVar2.P, aVar2.Q);
                    }
                    if (!aVar2.U) {
                        if (((ViewGroup.MarginLayoutParams) aVar2).width == -1) {
                            fVarA2.a(f.b.MATCH_PARENT);
                            fVarA2.a(e.d.LEFT).f119e = ((ViewGroup.MarginLayoutParams) aVar2).leftMargin;
                            fVarA2.a(e.d.RIGHT).f119e = ((ViewGroup.MarginLayoutParams) aVar2).rightMargin;
                        } else {
                            fVarA2.a(f.b.MATCH_CONSTRAINT);
                            fVarA2.o(0);
                        }
                    } else {
                        fVarA2.a(f.b.FIXED);
                        fVarA2.o(((ViewGroup.MarginLayoutParams) aVar2).width);
                    }
                    if (!aVar2.V) {
                        if (((ViewGroup.MarginLayoutParams) aVar2).height == -1) {
                            fVarA2.b(f.b.MATCH_PARENT);
                            fVarA2.a(e.d.TOP).f119e = ((ViewGroup.MarginLayoutParams) aVar2).topMargin;
                            fVarA2.a(e.d.BOTTOM).f119e = ((ViewGroup.MarginLayoutParams) aVar2).bottomMargin;
                            r3 = 0;
                        } else {
                            fVarA2.b(f.b.MATCH_CONSTRAINT);
                            r3 = 0;
                            fVarA2.g(0);
                        }
                    } else {
                        r3 = 0;
                        fVarA2.b(f.b.FIXED);
                        fVarA2.g(((ViewGroup.MarginLayoutParams) aVar2).height);
                    }
                    str = aVar2.B;
                    if (str != null) {
                        fVarA2.b(str);
                    }
                    fVarA2.b(aVar2.D);
                    fVarA2.d(aVar2.E);
                    fVarA2.h(aVar2.F);
                    fVarA2.m(aVar2.G);
                    fVarA2.a(aVar2.H, aVar2.J, aVar2.L, aVar2.N);
                    fVarA2.b(aVar2.I, aVar2.K, aVar2.M, aVar2.O);
                }
            }
            i17++;
            r3 = r3;
        }
    }

    public static class a extends ViewGroup.MarginLayoutParams {
        public float A;
        public String B;
        int C;
        public float D;
        public float E;
        public int F;
        public int G;
        public int H;
        public int I;
        public int J;
        public int K;
        public int L;
        public int M;
        public float N;
        public float O;
        public int P;
        public int Q;
        public int R;
        public boolean S;
        public boolean T;
        boolean U;
        boolean V;
        boolean W;
        boolean X;
        boolean Y;
        boolean Z;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f863a;
        int a0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f864b;
        int b0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f865c;
        int c0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f866d;
        int d0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f867e;
        int e0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f868f;
        int f0;
        public int g;
        float g0;
        public int h;
        int h0;
        public int i;
        int i0;
        public int j;
        float j0;
        public int k;
        f k0;
        public int l;
        public boolean l0;
        public int m;
        public int n;
        public float o;
        public int p;
        public int q;
        public int r;
        public int s;
        public int t;
        public int u;
        public int v;
        public int w;
        public int x;
        public int y;
        public float z;

        /* JADX INFO: renamed from: androidx.constraintlayout.widget.ConstraintLayout$a$a, reason: collision with other inner class name */
        private static class C0011a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final SparseIntArray f869a = new SparseIntArray();

            static {
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                f869a.append(R$styleable.ConstraintLayout_Layout_android_orientation, 1);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                f869a.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }
        }

        public a(Context context, AttributeSet attributeSet) {
            int i;
            super(context, attributeSet);
            this.f863a = -1;
            this.f864b = -1;
            this.f865c = -1.0f;
            this.f866d = -1;
            this.f867e = -1;
            this.f868f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 1;
            this.D = -1.0f;
            this.E = -1.0f;
            this.F = 0;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 1.0f;
            this.O = 1.0f;
            this.P = -1;
            this.Q = -1;
            this.R = -1;
            this.S = false;
            this.T = false;
            this.U = true;
            this.V = true;
            this.W = false;
            this.X = false;
            this.Y = false;
            this.Z = false;
            this.a0 = -1;
            this.b0 = -1;
            this.c0 = -1;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = 0.5f;
            this.k0 = new f();
            this.l0 = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                switch (C0011a.f869a.get(index)) {
                    case 1:
                        this.R = typedArrayObtainStyledAttributes.getInt(index, this.R);
                        break;
                    case 2:
                        this.m = typedArrayObtainStyledAttributes.getResourceId(index, this.m);
                        if (this.m == -1) {
                            this.m = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 3:
                        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.n);
                        break;
                    case 4:
                        this.o = typedArrayObtainStyledAttributes.getFloat(index, this.o) % 360.0f;
                        float f2 = this.o;
                        if (f2 < 0.0f) {
                            this.o = (360.0f - f2) % 360.0f;
                        }
                        break;
                    case 5:
                        this.f863a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f863a);
                        break;
                    case 6:
                        this.f864b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f864b);
                        break;
                    case 7:
                        this.f865c = typedArrayObtainStyledAttributes.getFloat(index, this.f865c);
                        break;
                    case 8:
                        this.f866d = typedArrayObtainStyledAttributes.getResourceId(index, this.f866d);
                        if (this.f866d == -1) {
                            this.f866d = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 9:
                        this.f867e = typedArrayObtainStyledAttributes.getResourceId(index, this.f867e);
                        if (this.f867e == -1) {
                            this.f867e = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 10:
                        this.f868f = typedArrayObtainStyledAttributes.getResourceId(index, this.f868f);
                        if (this.f868f == -1) {
                            this.f868f = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 11:
                        this.g = typedArrayObtainStyledAttributes.getResourceId(index, this.g);
                        if (this.g == -1) {
                            this.g = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 12:
                        this.h = typedArrayObtainStyledAttributes.getResourceId(index, this.h);
                        if (this.h == -1) {
                            this.h = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 13:
                        this.i = typedArrayObtainStyledAttributes.getResourceId(index, this.i);
                        if (this.i == -1) {
                            this.i = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 14:
                        this.j = typedArrayObtainStyledAttributes.getResourceId(index, this.j);
                        if (this.j == -1) {
                            this.j = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 15:
                        this.k = typedArrayObtainStyledAttributes.getResourceId(index, this.k);
                        if (this.k == -1) {
                            this.k = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 16:
                        this.l = typedArrayObtainStyledAttributes.getResourceId(index, this.l);
                        if (this.l == -1) {
                            this.l = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 17:
                        this.p = typedArrayObtainStyledAttributes.getResourceId(index, this.p);
                        if (this.p == -1) {
                            this.p = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 18:
                        this.q = typedArrayObtainStyledAttributes.getResourceId(index, this.q);
                        if (this.q == -1) {
                            this.q = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 19:
                        this.r = typedArrayObtainStyledAttributes.getResourceId(index, this.r);
                        if (this.r == -1) {
                            this.r = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 20:
                        this.s = typedArrayObtainStyledAttributes.getResourceId(index, this.s);
                        if (this.s == -1) {
                            this.s = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 21:
                        this.t = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.t);
                        break;
                    case 22:
                        this.u = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.u);
                        break;
                    case 23:
                        this.v = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.v);
                        break;
                    case 24:
                        this.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.w);
                        break;
                    case 25:
                        this.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.x);
                        break;
                    case 26:
                        this.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.y);
                        break;
                    case 27:
                        this.S = typedArrayObtainStyledAttributes.getBoolean(index, this.S);
                        break;
                    case 28:
                        this.T = typedArrayObtainStyledAttributes.getBoolean(index, this.T);
                        break;
                    case 29:
                        this.z = typedArrayObtainStyledAttributes.getFloat(index, this.z);
                        break;
                    case 30:
                        this.A = typedArrayObtainStyledAttributes.getFloat(index, this.A);
                        break;
                    case 31:
                        this.H = typedArrayObtainStyledAttributes.getInt(index, 0);
                        if (this.H == 1) {
                            Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        }
                        break;
                    case 32:
                        this.I = typedArrayObtainStyledAttributes.getInt(index, 0);
                        if (this.I == 1) {
                            Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                        }
                        break;
                    case 33:
                        try {
                            this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                        } catch (Exception unused) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.J) == -2) {
                                this.J = -2;
                            }
                        }
                        break;
                    case 34:
                        try {
                            this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                        } catch (Exception unused2) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.L) == -2) {
                                this.L = -2;
                            }
                        }
                        break;
                    case 35:
                        this.N = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.N));
                        break;
                    case 36:
                        try {
                            this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.K);
                        } catch (Exception unused3) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.K) == -2) {
                                this.K = -2;
                            }
                        }
                        break;
                    case 37:
                        try {
                            this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                        } catch (Exception unused4) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.M) == -2) {
                                this.M = -2;
                            }
                        }
                        break;
                    case 38:
                        this.O = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.O));
                        break;
                    case 44:
                        this.B = typedArrayObtainStyledAttributes.getString(index);
                        this.C = -1;
                        String str = this.B;
                        if (str != null) {
                            int length = str.length();
                            int iIndexOf = this.B.indexOf(44);
                            if (iIndexOf <= 0 || iIndexOf >= length - 1) {
                                i = 0;
                            } else {
                                String strSubstring = this.B.substring(0, iIndexOf);
                                if (strSubstring.equalsIgnoreCase("W")) {
                                    this.C = 0;
                                } else if (strSubstring.equalsIgnoreCase("H")) {
                                    this.C = 1;
                                }
                                i = iIndexOf + 1;
                            }
                            int iIndexOf2 = this.B.indexOf(58);
                            if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                                String strSubstring2 = this.B.substring(i);
                                if (strSubstring2.length() > 0) {
                                    Float.parseFloat(strSubstring2);
                                }
                            } else {
                                String strSubstring3 = this.B.substring(i, iIndexOf2);
                                String strSubstring4 = this.B.substring(iIndexOf2 + 1);
                                if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                                    try {
                                        float f3 = Float.parseFloat(strSubstring3);
                                        float f4 = Float.parseFloat(strSubstring4);
                                        if (f3 > 0.0f && f4 > 0.0f) {
                                            if (this.C == 1) {
                                                Math.abs(f4 / f3);
                                            } else {
                                                Math.abs(f3 / f4);
                                            }
                                        }
                                    } catch (NumberFormatException unused5) {
                                    }
                                }
                            }
                        }
                        break;
                    case 45:
                        this.D = typedArrayObtainStyledAttributes.getFloat(index, this.D);
                        break;
                    case 46:
                        this.E = typedArrayObtainStyledAttributes.getFloat(index, this.E);
                        break;
                    case 47:
                        this.F = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 48:
                        this.G = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 49:
                        this.P = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.P);
                        break;
                    case 50:
                        this.Q = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.Q);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            a();
        }

        public void a() {
            this.X = false;
            this.U = true;
            this.V = true;
            if (((ViewGroup.MarginLayoutParams) this).width == -2 && this.S) {
                this.U = false;
                this.H = 1;
            }
            if (((ViewGroup.MarginLayoutParams) this).height == -2 && this.T) {
                this.V = false;
                this.I = 1;
            }
            if (((ViewGroup.MarginLayoutParams) this).width == 0 || ((ViewGroup.MarginLayoutParams) this).width == -1) {
                this.U = false;
                if (((ViewGroup.MarginLayoutParams) this).width == 0 && this.H == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.S = true;
                }
            }
            if (((ViewGroup.MarginLayoutParams) this).height == 0 || ((ViewGroup.MarginLayoutParams) this).height == -1) {
                this.V = false;
                if (((ViewGroup.MarginLayoutParams) this).height == 0 && this.I == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.T = true;
                }
            }
            if (this.f865c == -1.0f && this.f863a == -1 && this.f864b == -1) {
                return;
            }
            this.X = true;
            this.U = true;
            this.V = true;
            if (!(this.k0 instanceof i)) {
                this.k0 = new i();
            }
            ((i) this.k0).v(this.R);
        }

        /* JADX WARN: Code duplicated, block: B:16:0x004c  */
        /* JADX WARN: Code duplicated, block: B:19:0x0053  */
        /* JADX WARN: Code duplicated, block: B:22:0x005a  */
        /* JADX WARN: Code duplicated, block: B:25:0x0060  */
        /* JADX WARN: Code duplicated, block: B:28:0x0066  */
        /* JADX WARN: Code duplicated, block: B:35:0x007c  */
        /* JADX WARN: Code duplicated, block: B:36:0x0084  */
        /* JADX WARN: Code duplicated, block: B:38:0x0088  */
        /* JADX WARN: Code duplicated, block: B:39:0x008f  */
        /* JADX WARN: Code duplicated, block: B:41:0x0093  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        @TargetApi(17)
        public void resolveLayoutDirection(int i) {
            int i2;
            int i3;
            int i4;
            int i5;
            float f2;
            int i6;
            int i7;
            int i8 = ((ViewGroup.MarginLayoutParams) this).leftMargin;
            int i9 = ((ViewGroup.MarginLayoutParams) this).rightMargin;
            super.resolveLayoutDirection(i);
            this.c0 = -1;
            this.d0 = -1;
            this.a0 = -1;
            this.b0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.e0 = this.t;
            this.f0 = this.v;
            this.g0 = this.z;
            this.h0 = this.f863a;
            this.i0 = this.f864b;
            this.j0 = this.f865c;
            boolean z = false;
            if (1 == getLayoutDirection()) {
                int i10 = this.p;
                if (i10 != -1) {
                    this.c0 = i10;
                } else {
                    int i11 = this.q;
                    if (i11 != -1) {
                        this.d0 = i11;
                    } else {
                        i2 = this.r;
                        if (i2 != -1) {
                            this.b0 = i2;
                            z = true;
                        }
                        i3 = this.s;
                        if (i3 != -1) {
                            this.a0 = i3;
                            z = true;
                        }
                        i4 = this.x;
                        if (i4 != -1) {
                            this.f0 = i4;
                        }
                        i5 = this.y;
                        if (i5 != -1) {
                            this.e0 = i5;
                        }
                        if (z) {
                            this.g0 = 1.0f - this.z;
                        }
                        if (this.X && this.R == 1) {
                            f2 = this.f865c;
                            if (f2 != -1.0f) {
                                this.j0 = 1.0f - f2;
                                this.h0 = -1;
                                this.i0 = -1;
                            } else {
                                i6 = this.f863a;
                                if (i6 != -1) {
                                    this.i0 = i6;
                                    this.h0 = -1;
                                    this.j0 = -1.0f;
                                } else {
                                    i7 = this.f864b;
                                    if (i7 != -1) {
                                        this.h0 = i7;
                                        this.i0 = -1;
                                        this.j0 = -1.0f;
                                    }
                                }
                            }
                        }
                    }
                }
                z = true;
                i2 = this.r;
                if (i2 != -1) {
                    this.b0 = i2;
                    z = true;
                }
                i3 = this.s;
                if (i3 != -1) {
                    this.a0 = i3;
                    z = true;
                }
                i4 = this.x;
                if (i4 != -1) {
                    this.f0 = i4;
                }
                i5 = this.y;
                if (i5 != -1) {
                    this.e0 = i5;
                }
                if (z) {
                    this.g0 = 1.0f - this.z;
                }
                if (this.X) {
                    f2 = this.f865c;
                    if (f2 != -1.0f) {
                        this.j0 = 1.0f - f2;
                        this.h0 = -1;
                        this.i0 = -1;
                    } else {
                        i6 = this.f863a;
                        if (i6 != -1) {
                            this.i0 = i6;
                            this.h0 = -1;
                            this.j0 = -1.0f;
                        } else {
                            i7 = this.f864b;
                            if (i7 != -1) {
                                this.h0 = i7;
                                this.i0 = -1;
                                this.j0 = -1.0f;
                            }
                        }
                    }
                }
            } else {
                int i12 = this.p;
                if (i12 != -1) {
                    this.b0 = i12;
                }
                int i13 = this.q;
                if (i13 != -1) {
                    this.a0 = i13;
                }
                int i14 = this.r;
                if (i14 != -1) {
                    this.c0 = i14;
                }
                int i15 = this.s;
                if (i15 != -1) {
                    this.d0 = i15;
                }
                int i16 = this.x;
                if (i16 != -1) {
                    this.e0 = i16;
                }
                int i17 = this.y;
                if (i17 != -1) {
                    this.f0 = i17;
                }
            }
            if (this.r == -1 && this.s == -1 && this.q == -1 && this.p == -1) {
                int i18 = this.f868f;
                if (i18 != -1) {
                    this.c0 = i18;
                    if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0 && i9 > 0) {
                        ((ViewGroup.MarginLayoutParams) this).rightMargin = i9;
                    }
                } else {
                    int i19 = this.g;
                    if (i19 != -1) {
                        this.d0 = i19;
                        if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0 && i9 > 0) {
                            ((ViewGroup.MarginLayoutParams) this).rightMargin = i9;
                        }
                    }
                }
                int i20 = this.f866d;
                if (i20 != -1) {
                    this.a0 = i20;
                    if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i8 <= 0) {
                        return;
                    }
                    ((ViewGroup.MarginLayoutParams) this).leftMargin = i8;
                    return;
                }
                int i21 = this.f867e;
                if (i21 != -1) {
                    this.b0 = i21;
                    if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i8 <= 0) {
                        return;
                    }
                    ((ViewGroup.MarginLayoutParams) this).leftMargin = i8;
                }
            }
        }

        public a(int i, int i2) {
            super(i, i2);
            this.f863a = -1;
            this.f864b = -1;
            this.f865c = -1.0f;
            this.f866d = -1;
            this.f867e = -1;
            this.f868f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 1;
            this.D = -1.0f;
            this.E = -1.0f;
            this.F = 0;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 1.0f;
            this.O = 1.0f;
            this.P = -1;
            this.Q = -1;
            this.R = -1;
            this.S = false;
            this.T = false;
            this.U = true;
            this.V = true;
            this.W = false;
            this.X = false;
            this.Y = false;
            this.Z = false;
            this.a0 = -1;
            this.b0 = -1;
            this.c0 = -1;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = 0.5f;
            this.k0 = new f();
            this.l0 = false;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f863a = -1;
            this.f864b = -1;
            this.f865c = -1.0f;
            this.f866d = -1;
            this.f867e = -1;
            this.f868f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 1;
            this.D = -1.0f;
            this.E = -1.0f;
            this.F = 0;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 1.0f;
            this.O = 1.0f;
            this.P = -1;
            this.Q = -1;
            this.R = -1;
            this.S = false;
            this.T = false;
            this.U = true;
            this.V = true;
            this.W = false;
            this.X = false;
            this.Y = false;
            this.Z = false;
            this.a0 = -1;
            this.b0 = -1;
            this.c0 = -1;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = 0.5f;
            this.k0 = new f();
            this.l0 = false;
        }
    }

    public final f a(View view) {
        if (view == this) {
            return this.f860d;
        }
        if (view == null) {
            return null;
        }
        return ((a) view.getLayoutParams()).k0;
    }

    private void a(int i, int i2) {
        boolean z;
        boolean z2;
        int baseline;
        int childMeasureSpec;
        int childMeasureSpec2;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                a aVar = (a) childAt.getLayoutParams();
                f fVar = aVar.k0;
                if (!aVar.X && !aVar.Y) {
                    fVar.n(childAt.getVisibility());
                    int measuredWidth = ((ViewGroup.MarginLayoutParams) aVar).width;
                    int measuredHeight = ((ViewGroup.MarginLayoutParams) aVar).height;
                    boolean z3 = aVar.U;
                    if (z3 || aVar.V || (!z3 && aVar.H == 1) || ((ViewGroup.MarginLayoutParams) aVar).width == -1 || (!aVar.V && (aVar.I == 1 || ((ViewGroup.MarginLayoutParams) aVar).height == -1))) {
                        if (measuredWidth == 0) {
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, -2);
                            z = true;
                        } else if (measuredWidth == -1) {
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, -1);
                            z = false;
                        } else {
                            z = measuredWidth == -2;
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, measuredWidth);
                        }
                        if (measuredHeight == 0) {
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
                            z2 = true;
                        } else if (measuredHeight == -1) {
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, paddingTop, -1);
                            z2 = false;
                        } else {
                            z2 = measuredHeight == -2;
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, paddingTop, measuredHeight);
                        }
                        childAt.measure(childMeasureSpec, childMeasureSpec2);
                        a.c.a.f fVar2 = this.p;
                        if (fVar2 != null) {
                            fVar2.f89a++;
                        }
                        fVar.b(measuredWidth == -2);
                        fVar.a(measuredHeight == -2);
                        measuredWidth = childAt.getMeasuredWidth();
                        measuredHeight = childAt.getMeasuredHeight();
                    } else {
                        z = false;
                        z2 = false;
                    }
                    fVar.o(measuredWidth);
                    fVar.g(measuredHeight);
                    if (z) {
                        fVar.q(measuredWidth);
                    }
                    if (z2) {
                        fVar.p(measuredHeight);
                    }
                    if (aVar.W && (baseline = childAt.getBaseline()) != -1) {
                        fVar.f(baseline);
                    }
                }
            }
        }
    }

    protected void a(String str) {
        this.f860d.K();
        a.c.a.f fVar = this.p;
        if (fVar != null) {
            fVar.f91c++;
        }
    }

    public View a(int i) {
        return this.f857a.get(i);
    }
}
