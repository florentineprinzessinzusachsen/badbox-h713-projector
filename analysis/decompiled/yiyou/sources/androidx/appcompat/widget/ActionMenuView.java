package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import com.blankj.utilcode.constant.MemoryConstants;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements androidx.appcompat.view.menu.g.b, androidx.appcompat.view.menu.n {
    e A;
    private androidx.appcompat.view.menu.g p;
    private Context q;
    private int r;
    private boolean s;
    private ActionMenuPresenter t;
    private androidx.appcompat.view.menu.m.a u;
    androidx.appcompat.view.menu.g.a v;
    private boolean w;
    private int x;
    private int y;
    private int z;

    public interface a {
        boolean a();

        boolean b();
    }

    private static class b implements androidx.appcompat.view.menu.m.a {
        b() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            return false;
        }
    }

    public static class c extends LinearLayoutCompat.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f531c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f532d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f533e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f534f;

        @ViewDebug.ExportedProperty
        public boolean g;
        boolean h;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.f531c = cVar.f531c;
        }

        public c(int i, int i2) {
            super(i, i2);
            this.f531c = false;
        }
    }

    public interface e {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    static int a(View view, int i, int i2, int i3, int i4) {
        c cVar = (c) view.getLayoutParams();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3) - i4, View.MeasureSpec.getMode(i3));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z = actionMenuItemView != null && actionMenuItemView.d();
        int i5 = 2;
        if (i2 <= 0 || (z && i2 < 2)) {
            i5 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i, Integer.MIN_VALUE), iMakeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i6 = measuredWidth / i;
            if (measuredWidth % i != 0) {
                i6++;
            }
            if (!z || i6 >= 2) {
                i5 = i6;
            }
        }
        cVar.f534f = !cVar.f531c && z;
        cVar.f532d = i5;
        view.measure(View.MeasureSpec.makeMeasureSpec(i * i5, MemoryConstants.GB), iMakeMeasureSpec);
        return i5;
    }

    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v16 */
    private void c(int i, int i2) {
        boolean z;
        int i3;
        int i4;
        ?? r13;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
        int i5 = size - paddingLeft;
        int i6 = this.y;
        int i7 = i5 / i6;
        int i8 = i5 % i6;
        if (i7 == 0) {
            setMeasuredDimension(i5, 0);
            return;
        }
        int i9 = i6 + (i8 / i7);
        int childCount = getChildCount();
        int i10 = i7;
        int i11 = 0;
        int iMax = 0;
        boolean z2 = false;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        long j = 0;
        while (i11 < childCount) {
            View childAt = getChildAt(i11);
            int i15 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z3 = childAt instanceof ActionMenuItemView;
                int i16 = i12 + 1;
                if (z3) {
                    int i17 = this.z;
                    r13 = 0;
                    childAt.setPadding(i17, 0, i17, 0);
                } else {
                    r13 = 0;
                }
                c cVar = (c) childAt.getLayoutParams();
                cVar.h = r13;
                cVar.f533e = r13;
                cVar.f532d = r13;
                cVar.f534f = r13;
                ((ViewGroup.MarginLayoutParams) cVar).leftMargin = r13;
                ((ViewGroup.MarginLayoutParams) cVar).rightMargin = r13;
                cVar.g = z3 && ((ActionMenuItemView) childAt).d();
                int iA = a(childAt, i9, cVar.f531c ? 1 : i10, childMeasureSpec, paddingTop);
                int iMax2 = Math.max(i13, iA);
                if (cVar.f534f) {
                    i14++;
                }
                if (cVar.f531c) {
                    z2 = true;
                }
                i10 -= iA;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (iA == 1) {
                    j |= (long) (1 << i11);
                    iMax = iMax;
                }
                i13 = iMax2;
                i12 = i16;
            }
            i11++;
            size2 = i15;
        }
        int i18 = size2;
        boolean z4 = z2 && i12 == 2;
        boolean z5 = false;
        while (true) {
            if (i14 <= 0 || i10 <= 0) {
                z = z5;
                i3 = iMax;
                break;
            }
            int i19 = Integer.MAX_VALUE;
            int i20 = 0;
            int i21 = 0;
            long j2 = 0;
            while (i20 < childCount) {
                boolean z6 = z5;
                c cVar2 = (c) getChildAt(i20).getLayoutParams();
                int i22 = iMax;
                if (cVar2.f534f) {
                    int i23 = cVar2.f532d;
                    if (i23 < i19) {
                        i19 = i23;
                        j2 = 1 << i20;
                        i21 = 1;
                    } else if (i23 == i19) {
                        j2 |= 1 << i20;
                        i21++;
                    }
                }
                i20++;
                iMax = i22;
                z5 = z6;
            }
            z = z5;
            i3 = iMax;
            j |= j2;
            if (i21 > i10) {
                break;
            }
            int i24 = i19 + 1;
            int i25 = 0;
            while (i25 < childCount) {
                View childAt2 = getChildAt(i25);
                c cVar3 = (c) childAt2.getLayoutParams();
                int i26 = i5;
                int i27 = mode;
                long j3 = 1 << i25;
                if ((j2 & j3) == 0) {
                    if (cVar3.f532d == i24) {
                        j |= j3;
                    }
                    i24 = i24;
                } else {
                    if (z4 && cVar3.g && i10 == 1) {
                        int i28 = this.z;
                        childAt2.setPadding(i28 + i9, 0, i28, 0);
                    }
                    cVar3.f532d++;
                    cVar3.h = true;
                    i10--;
                }
                i25++;
                mode = i27;
                i24 = i24;
                i5 = i26;
            }
            iMax = i3;
            z5 = true;
        }
        boolean z7 = !z2 && i12 == 1;
        if (i10 <= 0 || j == 0 || (i10 >= i12 - 1 && !z7 && i13 <= 1)) {
            i4 = 0;
        } else {
            float fBitCount = Long.bitCount(j);
            if (z7) {
                i4 = 0;
            } else {
                i4 = 0;
                if ((j & 1) != 0 && !((c) getChildAt(0).getLayoutParams()).g) {
                    fBitCount -= 0.5f;
                }
                int i29 = childCount - 1;
                if ((j & ((long) (1 << i29))) != 0 && !((c) getChildAt(i29).getLayoutParams()).g) {
                    fBitCount -= 0.5f;
                }
            }
            int i30 = fBitCount > 0.0f ? (int) ((i10 * i9) / fBitCount) : 0;
            for (int i31 = 0; i31 < childCount; i31++) {
                if ((j & ((long) (1 << i31))) != 0) {
                    View childAt3 = getChildAt(i31);
                    c cVar4 = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar4.f533e = i30;
                        cVar4.h = true;
                        if (i31 == 0 && !cVar4.g) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = (-i30) / 2;
                        }
                    } else if (cVar4.f531c) {
                        cVar4.f533e = i30;
                        cVar4.h = true;
                        ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = (-i30) / 2;
                    } else {
                        if (i31 != 0) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = i30 / 2;
                        }
                        if (i31 != childCount - 1) {
                            ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = i30 / 2;
                        }
                    }
                    z = true;
                }
            }
        }
        if (z) {
            while (i4 < childCount) {
                View childAt4 = getChildAt(i4);
                c cVar5 = (c) childAt4.getLayoutParams();
                if (cVar5.h) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((cVar5.f532d * i9) + cVar5.f533e, MemoryConstants.GB), childMeasureSpec);
                }
                i4++;
            }
        }
        setMeasuredDimension(i5, mode != 1073741824 ? i3 : i18);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof c;
    }

    public c d() {
        c cVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        cVarGenerateDefaultLayoutParams.f531c = true;
        return cVarGenerateDefaultLayoutParams;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public boolean e() {
        ActionMenuPresenter actionMenuPresenter = this.t;
        return actionMenuPresenter != null && actionMenuPresenter.e();
    }

    public boolean f() {
        ActionMenuPresenter actionMenuPresenter = this.t;
        return actionMenuPresenter != null && actionMenuPresenter.g();
    }

    public boolean g() {
        ActionMenuPresenter actionMenuPresenter = this.t;
        return actionMenuPresenter != null && actionMenuPresenter.h();
    }

    public Menu getMenu() {
        if (this.p == null) {
            Context context = getContext();
            this.p = new androidx.appcompat.view.menu.g(context);
            this.p.a(new d());
            this.t = new ActionMenuPresenter(context);
            this.t.d(true);
            ActionMenuPresenter actionMenuPresenter = this.t;
            androidx.appcompat.view.menu.m.a bVar = this.u;
            if (bVar == null) {
                bVar = new b();
            }
            actionMenuPresenter.a(bVar);
            this.p.a(this.t, this.q);
            this.t.a(this);
        }
        return this.p;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.t.d();
    }

    public int getPopupTheme() {
        return this.r;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public boolean h() {
        return this.s;
    }

    public androidx.appcompat.view.menu.g i() {
        return this.p;
    }

    public boolean j() {
        ActionMenuPresenter actionMenuPresenter = this.t;
        return actionMenuPresenter != null && actionMenuPresenter.i();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.t;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.a(false);
            if (this.t.h()) {
                this.t.e();
                this.t.i();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int width;
        int paddingLeft;
        if (!this.w) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i7 = (i4 - i2) / 2;
        int dividerWidth = getDividerWidth();
        int i8 = i3 - i;
        int paddingRight = (i8 - getPaddingRight()) - getPaddingLeft();
        boolean zA = j0.a(this);
        int measuredWidth = paddingRight;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f531c) {
                    int measuredWidth2 = childAt.getMeasuredWidth();
                    if (d(i11)) {
                        measuredWidth2 += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zA) {
                        paddingLeft = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
                        width = paddingLeft + measuredWidth2;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                        paddingLeft = width - measuredWidth2;
                    }
                    int i12 = i7 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i12, width, measuredHeight + i12);
                    measuredWidth -= measuredWidth2;
                    i9 = 1;
                } else {
                    measuredWidth -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin) + ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                    d(i11);
                    i10++;
                }
            }
        }
        if (childCount == 1 && i9 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth3 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i13 = (i8 / 2) - (measuredWidth3 / 2);
            int i14 = i7 - (measuredHeight2 / 2);
            childAt2.layout(i13, i14, measuredWidth3 + i13, measuredHeight2 + i14);
            return;
        }
        int i15 = i10 - (i9 ^ 1);
        if (i15 > 0) {
            i6 = measuredWidth / i15;
            i5 = 0;
        } else {
            i5 = 0;
            i6 = 0;
        }
        int iMax = Math.max(i5, i6);
        if (zA) {
            int width2 = getWidth() - getPaddingRight();
            while (i5 < childCount) {
                View childAt3 = getChildAt(i5);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.f531c) {
                    int i16 = width2 - ((ViewGroup.MarginLayoutParams) cVar2).rightMargin;
                    int measuredWidth4 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i17 = i7 - (measuredHeight3 / 2);
                    childAt3.layout(i16 - measuredWidth4, i17, i16, measuredHeight3 + i17);
                    width2 = i16 - ((measuredWidth4 + ((ViewGroup.MarginLayoutParams) cVar2).leftMargin) + iMax);
                }
                i5++;
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        while (i5 < childCount) {
            View childAt4 = getChildAt(i5);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.f531c) {
                int i18 = paddingLeft2 + ((ViewGroup.MarginLayoutParams) cVar3).leftMargin;
                int measuredWidth5 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i19 = i7 - (measuredHeight4 / 2);
                childAt4.layout(i18, i19, i18 + measuredWidth5, measuredHeight4 + i19);
                paddingLeft2 = i18 + measuredWidth5 + ((ViewGroup.MarginLayoutParams) cVar3).rightMargin + iMax;
            }
            i5++;
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    protected void onMeasure(int i, int i2) {
        androidx.appcompat.view.menu.g gVar;
        boolean z = this.w;
        this.w = View.MeasureSpec.getMode(i) == 1073741824;
        if (z != this.w) {
            this.x = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.w && (gVar = this.p) != null && size != this.x) {
            this.x = size;
            gVar.b(true);
        }
        int childCount = getChildCount();
        if (this.w && childCount > 0) {
            c(i, i2);
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            c cVar = (c) getChildAt(i3).getLayoutParams();
            ((ViewGroup.MarginLayoutParams) cVar).rightMargin = 0;
            ((ViewGroup.MarginLayoutParams) cVar).leftMargin = 0;
        }
        super.onMeasure(i, i2);
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.t.c(z);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.A = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.t.a(drawable);
    }

    public void setOverflowReserved(boolean z) {
        this.s = z;
    }

    public void setPopupTheme(int i) {
        if (this.r != i) {
            this.r = i;
            if (i == 0) {
                this.q = getContext();
            } else {
                this.q = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.t = actionMenuPresenter;
        this.t.a(this);
    }

    private class d implements androidx.appcompat.view.menu.g.a {
        d() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            e eVar = ActionMenuView.this.A;
            return eVar != null && eVar.onMenuItemClick(menuItem);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void a(androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.g.a aVar = ActionMenuView.this.v;
            if (aVar != null) {
                aVar.a(gVar);
            }
        }
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.y = (int) (56.0f * f2);
        this.z = (int) (f2 * 4.0f);
        this.q = context;
        this.r = 0;
    }

    protected boolean d(int i) {
        boolean zA = false;
        if (i == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i - 1);
        KeyEvent.Callback childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof a)) {
            zA = false | ((a) childAt).a();
        }
        return (i <= 0 || !(childAt2 instanceof a)) ? zA : zA | ((a) childAt2).b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public c generateDefaultLayoutParams() {
        c cVar = new c(-2, -2);
        cVar.f627b = 16;
        return cVar;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams != null) {
            c cVar = layoutParams instanceof c ? new c((c) layoutParams) : new c(layoutParams);
            if (cVar.f627b <= 0) {
                cVar.f627b = 16;
            }
            return cVar;
        }
        return generateDefaultLayoutParams();
    }

    @Override // androidx.appcompat.view.menu.g.b
    public boolean a(androidx.appcompat.view.menu.j jVar) {
        return this.p.a(jVar, 0);
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(androidx.appcompat.view.menu.g gVar) {
        this.p = gVar;
    }

    public void a(androidx.appcompat.view.menu.m.a aVar, androidx.appcompat.view.menu.g.a aVar2) {
        this.u = aVar;
        this.v = aVar2;
    }

    public void c() {
        ActionMenuPresenter actionMenuPresenter = this.t;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.c();
        }
    }
}
