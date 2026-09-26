package androidx.appcompat.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.OverScroller;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarOverlayLayout extends ViewGroup implements n, androidx.core.f.n, androidx.core.f.l, androidx.core.f.m {
    static final int[] B = {R$attr.actionBarSize, R.attr.windowContentOverlay};
    private final androidx.core.f.o A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f518b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ContentFrameLayout f519c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ActionBarContainer f520d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private o f521e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Drawable f522f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    boolean k;
    private int l;
    private int m;
    private final Rect n;
    private final Rect o;
    private final Rect p;
    private final Rect q;
    private final Rect r;
    private final Rect s;
    private final Rect t;
    private d u;
    private OverScroller v;
    ViewPropertyAnimator w;
    final AnimatorListenerAdapter x;
    private final Runnable y;
    private final Runnable z;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.w = null;
            actionBarOverlayLayout.k = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.w = null;
            actionBarOverlayLayout.k = false;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.h();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.w = actionBarOverlayLayout.f520d.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.x);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.h();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.w = actionBarOverlayLayout.f520d.animate().translationY(-ActionBarOverlayLayout.this.f520d.getHeight()).setListener(ActionBarOverlayLayout.this.x);
        }
    }

    public interface d {
        void a();

        void a(int i);

        void a(boolean z);

        void b();

        void c();

        void d();
    }

    public static class e extends ViewGroup.MarginLayoutParams {
        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(int i, int i2) {
            super(i, i2);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    private void a(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(B);
        this.f517a = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.f522f = typedArrayObtainStyledAttributes.getDrawable(1);
        setWillNotDraw(this.f522f == null);
        typedArrayObtainStyledAttributes.recycle();
        this.g = context.getApplicationInfo().targetSdkVersion < 19;
        this.v = new OverScroller(context);
    }

    private void k() {
        h();
        this.z.run();
    }

    private void l() {
        h();
        postDelayed(this.z, 600L);
    }

    private void m() {
        h();
        postDelayed(this.y, 600L);
    }

    private void n() {
        h();
        this.y.run();
    }

    @Override // androidx.core.f.l
    public boolean b(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    @Override // androidx.appcompat.widget.n
    public boolean c() {
        j();
        return this.f521e.c();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // androidx.appcompat.widget.n
    public boolean d() {
        j();
        return this.f521e.d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f522f == null || this.g) {
            return;
        }
        int bottom = this.f520d.getVisibility() == 0 ? (int) (this.f520d.getBottom() + this.f520d.getTranslationY() + 0.5f) : 0;
        this.f522f.setBounds(0, bottom, getWidth(), this.f522f.getIntrinsicHeight() + bottom);
        this.f522f.draw(canvas);
    }

    @Override // androidx.appcompat.widget.n
    public void e() {
        j();
        this.f521e.e();
    }

    @Override // androidx.appcompat.widget.n
    public boolean f() {
        j();
        return this.f521e.f();
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        j();
        int iN = androidx.core.f.t.n(this) & 256;
        boolean zA = a((View) this.f520d, rect, true, true, false, true);
        this.q.set(rect);
        j0.a(this, this.q, this.n);
        if (!this.r.equals(this.q)) {
            this.r.set(this.q);
            zA = true;
        }
        if (!this.o.equals(this.n)) {
            this.o.set(this.n);
            zA = true;
        }
        if (zA) {
            requestLayout();
        }
        return true;
    }

    @Override // androidx.appcompat.widget.n
    public void g() {
        j();
        this.f521e.g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f520d;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.A.a();
    }

    public CharSequence getTitle() {
        j();
        return this.f521e.getTitle();
    }

    void h() {
        removeCallbacks(this.y);
        removeCallbacks(this.z);
        ViewPropertyAnimator viewPropertyAnimator = this.w;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public boolean i() {
        return this.h;
    }

    void j() {
        if (this.f519c == null) {
            this.f519c = (ContentFrameLayout) findViewById(R$id.action_bar_activity_content);
            this.f520d = (ActionBarContainer) findViewById(R$id.action_bar_container);
            this.f521e = a(findViewById(R$id.action_bar));
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(getContext());
        androidx.core.f.t.u(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        h();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        getPaddingRight();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredHeight;
        j();
        measureChildWithMargins(this.f520d, i, 0, i2, 0);
        e eVar = (e) this.f520d.getLayoutParams();
        int iMax = Math.max(0, this.f520d.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.f520d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f520d.getMeasuredState());
        boolean z = (androidx.core.f.t.n(this) & 256) != 0;
        if (z) {
            measuredHeight = this.f517a;
            if (this.i && this.f520d.getTabContainer() != null) {
                measuredHeight += this.f517a;
            }
        } else {
            measuredHeight = this.f520d.getVisibility() != 8 ? this.f520d.getMeasuredHeight() : 0;
        }
        this.p.set(this.n);
        this.s.set(this.q);
        if (this.h || z) {
            Rect rect = this.s;
            rect.top += measuredHeight;
            rect.bottom += 0;
        } else {
            Rect rect2 = this.p;
            rect2.top += measuredHeight;
            rect2.bottom += 0;
        }
        a((View) this.f519c, this.p, true, true, true, true);
        if (!this.t.equals(this.s)) {
            this.t.set(this.s);
            this.f519c.a(this.s);
        }
        measureChildWithMargins(this.f519c, i, 0, i2, 0);
        e eVar2 = (e) this.f519c.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f519c.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f519c.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f519c.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        if (!this.j || !z) {
            return false;
        }
        if (a(f2, f3)) {
            k();
        } else {
            n();
        }
        this.k = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        this.l += i2;
        setActionBarHideOffset(this.l);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.A.a(view, view2, i);
        this.l = getActionBarHideOffset();
        h();
        d dVar = this.u;
        if (dVar != null) {
            dVar.b();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.f520d.getVisibility() != 0) {
            return false;
        }
        return this.j;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public void onStopNestedScroll(View view) {
        if (this.j && !this.k) {
            if (this.l <= this.f520d.getHeight()) {
                m();
            } else {
                l();
            }
        }
        d dVar = this.u;
        if (dVar != null) {
            dVar.d();
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i);
        }
        j();
        int i2 = this.m ^ i;
        this.m = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & 256) != 0;
        d dVar = this.u;
        if (dVar != null) {
            dVar.a(!z2);
            if (z || !z2) {
                this.u.a();
            } else {
                this.u.c();
            }
        }
        if ((i2 & 256) == 0 || this.u == null) {
            return;
        }
        androidx.core.f.t.u(this);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.f518b = i;
        d dVar = this.u;
        if (dVar != null) {
            dVar.a(i);
        }
    }

    public void setActionBarHideOffset(int i) {
        h();
        this.f520d.setTranslationY(-Math.max(0, Math.min(i, this.f520d.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.u = dVar;
        if (getWindowToken() != null) {
            this.u.a(this.f518b);
            int i = this.m;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                androidx.core.f.t.u(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.i = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.j) {
            this.j = z;
            if (z) {
                return;
            }
            h();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i) {
        j();
        this.f521e.setIcon(i);
    }

    public void setLogo(int i) {
        j();
        this.f521e.b(i);
    }

    public void setOverlayMode(boolean z) {
        this.h = z;
        this.g = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    @Override // androidx.appcompat.widget.n
    public void setWindowCallback(Window.Callback callback) {
        j();
        this.f521e.setWindowCallback(callback);
    }

    @Override // androidx.appcompat.widget.n
    public void setWindowTitle(CharSequence charSequence) {
        j();
        this.f521e.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f518b = 0;
        this.n = new Rect();
        this.o = new Rect();
        this.p = new Rect();
        this.q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.x = new a();
        this.y = new b();
        this.z = new c();
        a(context);
        this.A = new androidx.core.f.o(this);
    }

    @Override // androidx.appcompat.widget.n
    public boolean b() {
        j();
        return this.f521e.b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public e generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        j();
        this.f521e.setIcon(drawable);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    private boolean a(View view, Rect rect, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5;
        e eVar = (e) view.getLayoutParams();
        if (z) {
            int i = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
            int i2 = rect.left;
            if (i != i2) {
                ((ViewGroup.MarginLayoutParams) eVar).leftMargin = i2;
                z5 = true;
            } else {
                z5 = false;
            }
        } else {
            z5 = false;
        }
        if (z2) {
            int i3 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
            int i4 = rect.top;
            if (i3 != i4) {
                ((ViewGroup.MarginLayoutParams) eVar).topMargin = i4;
                z5 = true;
            }
        }
        if (z4) {
            int i5 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
            int i6 = rect.right;
            if (i5 != i6) {
                ((ViewGroup.MarginLayoutParams) eVar).rightMargin = i6;
                z5 = true;
            }
        }
        if (!z3) {
            return z5;
        }
        int i7 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
        int i8 = rect.bottom;
        if (i7 == i8) {
            return z5;
        }
        ((ViewGroup.MarginLayoutParams) eVar).bottomMargin = i8;
        return true;
    }

    @Override // androidx.core.f.m
    public void a(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        a(view, i, i2, i3, i4, i5);
    }

    @Override // androidx.core.f.l
    public void a(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // androidx.core.f.l
    public void a(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // androidx.core.f.l
    public void a(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // androidx.core.f.l
    public void a(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private o a(View view) {
        if (view instanceof o) {
            return (o) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    private boolean a(float f2, float f3) {
        this.v.fling(0, 0, 0, (int) f3, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.v.getFinalY() > this.f520d.getHeight();
    }

    @Override // androidx.appcompat.widget.n
    public void a(int i) {
        j();
        if (i == 2) {
            this.f521e.l();
        } else if (i == 5) {
            this.f521e.n();
        } else {
            if (i != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // androidx.appcompat.widget.n
    public boolean a() {
        j();
        return this.f521e.a();
    }

    @Override // androidx.appcompat.widget.n
    public void a(Menu menu, androidx.appcompat.view.menu.m.a aVar) {
        j();
        this.f521e.a(menu, aVar);
    }
}
