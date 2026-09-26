package androidx.drawerlayout.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.f.t;
import com.blankj.utilcode.constant.MemoryConstants;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {
    private static final int[] L = {R.attr.colorPrimaryDark};
    static final int[] M = {R.attr.layout_gravity};
    static final boolean N;
    private static final boolean O;
    private CharSequence A;
    private Object B;
    private boolean C;
    private Drawable D;
    private Drawable F;
    private Drawable G;
    private Drawable H;
    private final ArrayList<View> I;
    private Rect J;
    private Matrix K;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f1182a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f1183b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1184c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1185d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f1186e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Paint f1187f;
    private final androidx.customview.b.a g;
    private final androidx.customview.b.a h;
    private final g i;
    private final g j;
    private int k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private boolean r;
    private d s;
    private List<d> t;
    private float u;
    private float v;
    private Drawable w;
    private Drawable x;
    private Drawable y;
    private CharSequence z;

    class a implements View.OnApplyWindowInsetsListener {
        a(DrawerLayout drawerLayout) {
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            ((DrawerLayout) view).a(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
            return windowInsets.consumeSystemWindowInsets();
        }
    }

    static final class c extends androidx.core.f.a {
        c() {
        }

        @Override // androidx.core.f.a
        public void a(View view, androidx.core.f.c0.b bVar) {
            super.a(view, bVar);
            if (DrawerLayout.m(view)) {
                return;
            }
            bVar.b((View) null);
        }
    }

    public interface d {
        void a(int i);

        void a(View view);

        void a(View view, float f2);

        void b(View view);
    }

    private class g extends androidx.customview.b.a.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f1198a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private androidx.customview.b.a f1199b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Runnable f1200c = new a();

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.a();
            }
        }

        g(int i) {
            this.f1198a = i;
        }

        public void a(androidx.customview.b.a aVar) {
            this.f1199b = aVar;
        }

        public void b() {
            DrawerLayout.this.removeCallbacks(this.f1200c);
        }

        @Override // androidx.customview.b.a.c
        public boolean b(int i) {
            return false;
        }

        @Override // androidx.customview.b.a.c
        public void c(int i) {
            DrawerLayout.this.a(this.f1198a, i, this.f1199b.b());
        }

        private void c() {
            View viewA = DrawerLayout.this.a(this.f1198a == 3 ? 5 : 3);
            if (viewA != null) {
                DrawerLayout.this.a(viewA);
            }
        }

        @Override // androidx.customview.b.a.c
        public void a(View view, int i, int i2, int i3, int i4) {
            int width = view.getWidth();
            float width2 = (DrawerLayout.this.a(view, 3) ? i + width : DrawerLayout.this.getWidth() - i) / width;
            DrawerLayout.this.c(view, width2);
            view.setVisibility(width2 == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        @Override // androidx.customview.b.a.c
        public boolean b(View view, int i) {
            return DrawerLayout.this.i(view) && DrawerLayout.this.a(view, this.f1198a) && DrawerLayout.this.d(view) == 0;
        }

        @Override // androidx.customview.b.a.c
        public void b(int i, int i2) {
            DrawerLayout.this.postDelayed(this.f1200c, 160L);
        }

        @Override // androidx.customview.b.a.c
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // androidx.customview.b.a.c
        public void a(View view, int i) {
            ((e) view.getLayoutParams()).f1192c = false;
            c();
        }

        @Override // androidx.customview.b.a.c
        public void a(View view, float f2, float f3) {
            int i;
            float f4 = DrawerLayout.this.f(view);
            int width = view.getWidth();
            if (DrawerLayout.this.a(view, 3)) {
                i = (f2 > 0.0f || (f2 == 0.0f && f4 > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f2 < 0.0f || (f2 == 0.0f && f4 > 0.5f)) {
                    width2 -= width;
                }
                i = width2;
            }
            this.f1199b.d(i, view.getTop());
            DrawerLayout.this.invalidate();
        }

        void a() {
            View viewA;
            int width;
            int iC = this.f1199b.c();
            boolean z = this.f1198a == 3;
            if (z) {
                viewA = DrawerLayout.this.a(3);
                width = (viewA != null ? -viewA.getWidth() : 0) + iC;
            } else {
                viewA = DrawerLayout.this.a(5);
                width = DrawerLayout.this.getWidth() - iC;
            }
            if (viewA != null) {
                if (((!z || viewA.getLeft() >= width) && (z || viewA.getLeft() <= width)) || DrawerLayout.this.d(viewA) != 0) {
                    return;
                }
                e eVar = (e) viewA.getLayoutParams();
                this.f1199b.b(viewA, width, viewA.getTop());
                eVar.f1192c = true;
                DrawerLayout.this.invalidate();
                c();
                DrawerLayout.this.a();
            }
        }

        @Override // androidx.customview.b.a.c
        public void a(int i, int i2) {
            View viewA;
            if ((i & 1) == 1) {
                viewA = DrawerLayout.this.a(3);
            } else {
                viewA = DrawerLayout.this.a(5);
            }
            if (viewA == null || DrawerLayout.this.d(viewA) != 0) {
                return;
            }
            this.f1199b.a(viewA, i2);
        }

        @Override // androidx.customview.b.a.c
        public int a(View view) {
            if (DrawerLayout.this.i(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // androidx.customview.b.a.c
        public int a(View view, int i, int i2) {
            if (DrawerLayout.this.a(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }
    }

    static {
        N = Build.VERSION.SDK_INT >= 19;
        O = Build.VERSION.SDK_INT >= 21;
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    private Drawable g() {
        int iJ = t.j(this);
        if (iJ == 0) {
            Drawable drawable = this.D;
            if (drawable != null) {
                a(drawable, iJ);
                return this.D;
            }
        } else {
            Drawable drawable2 = this.F;
            if (drawable2 != null) {
                a(drawable2, iJ);
                return this.F;
            }
        }
        return this.G;
    }

    private Drawable h() {
        int iJ = t.j(this);
        if (iJ == 0) {
            Drawable drawable = this.F;
            if (drawable != null) {
                a(drawable, iJ);
                return this.F;
            }
        } else {
            Drawable drawable2 = this.D;
            if (drawable2 != null) {
                a(drawable2, iJ);
                return this.D;
            }
        }
        return this.H;
    }

    private void i() {
        if (O) {
            return;
        }
        this.x = g();
        this.y = h();
    }

    private static boolean l(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    static boolean m(View view) {
        return (t.i(view) == 4 || t.i(view) == 2) ? false : true;
    }

    public void a(Object obj, boolean z) {
        this.B = obj;
        this.C = z;
        setWillNotDraw(!z && getBackground() == null);
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        boolean z = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (!i(childAt)) {
                this.I.add(childAt);
            } else if (h(childAt)) {
                childAt.addFocusables(arrayList, i, i2);
                z = true;
            }
        }
        if (!z) {
            int size = this.I.size();
            for (int i4 = 0; i4 < size; i4++) {
                View view = this.I.get(i4);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i, i2);
                }
            }
        }
        this.I.clear();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (c() != null || i(view)) {
            t.f(view, 4);
        } else {
            t.f(view, 1);
        }
        if (N) {
            return;
        }
        t.a(view, this.f1182a);
    }

    public void b(d dVar) {
        List<d> list;
        if (dVar == null || (list = this.t) == null) {
            return;
        }
        list.remove(dVar);
    }

    public CharSequence c(int i) {
        int iA = androidx.core.f.c.a(i, t.j(this));
        if (iA == 3) {
            return this.z;
        }
        if (iA == 5) {
            return this.A;
        }
        return null;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof e) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float fMax = 0.0f;
        for (int i = 0; i < childCount; i++) {
            fMax = Math.max(fMax, ((e) getChildAt(i).getLayoutParams()).f1191b);
        }
        this.f1186e = fMax;
        boolean zA = this.g.a(true);
        boolean zA2 = this.h.a(true);
        if (zA || zA2) {
            t.t(this);
        }
    }

    public int d(View view) {
        if (i(view)) {
            return b(((e) view.getLayoutParams()).f1190a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.f1186e <= 0.0f) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (int i = childCount - 1; i >= 0; i--) {
            View childAt = getChildAt(i);
            if (a(x, y, childAt) && !g(childAt) && a(motionEvent, childAt)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        int i;
        int height = getHeight();
        boolean zG = g(view);
        int width = getWidth();
        int iSave = canvas.save();
        int i2 = 0;
        if (zG) {
            int childCount = getChildCount();
            i = width;
            int i3 = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt != view && childAt.getVisibility() == 0 && l(childAt) && i(childAt) && childAt.getHeight() >= height) {
                    if (a(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i3) {
                            i3 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < i) {
                            i = left;
                        }
                    }
                }
            }
            canvas.clipRect(i3, 0, i, getHeight());
            i2 = i3;
        } else {
            i = width;
        }
        boolean zDrawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(iSave);
        float f2 = this.f1186e;
        if (f2 > 0.0f && zG) {
            int i5 = this.f1185d;
            this.f1187f.setColor((i5 & 16777215) | (((int) ((((-16777216) & i5) >>> 24) * f2)) << 24));
            canvas.drawRect(i2, 0.0f, i, getHeight(), this.f1187f);
        } else if (this.x != null && a(view, 3)) {
            int intrinsicWidth = this.x.getIntrinsicWidth();
            int right2 = view.getRight();
            float fMax = Math.max(0.0f, Math.min(right2 / this.g.c(), 1.0f));
            this.x.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.x.setAlpha((int) (fMax * 255.0f));
            this.x.draw(canvas);
        } else if (this.y != null && a(view, 5)) {
            int intrinsicWidth2 = this.y.getIntrinsicWidth();
            int left2 = view.getLeft();
            float fMax2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.h.c(), 1.0f));
            this.y.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.y.setAlpha((int) (fMax2 * 255.0f));
            this.y.draw(canvas);
        }
        return zDrawChild;
    }

    int e(View view) {
        return androidx.core.f.c.a(((e) view.getLayoutParams()).f1190a, t.j(this));
    }

    float f(View view) {
        return ((e) view.getLayoutParams()).f1191b;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof e) {
            return new e((e) layoutParams);
        }
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    public float getDrawerElevation() {
        if (O) {
            return this.f1183b;
        }
        return 0.0f;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.w;
    }

    public boolean j(View view) {
        if (i(view)) {
            return ((e) view.getLayoutParams()).f1191b > 0.0f;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public void k(View view) {
        b(view, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.m = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.m = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Object obj;
        super.onDraw(canvas);
        if (!this.C || this.w == null) {
            return;
        }
        int systemWindowInsetTop = (Build.VERSION.SDK_INT < 21 || (obj = this.B) == null) ? 0 : ((WindowInsets) obj).getSystemWindowInsetTop();
        if (systemWindowInsetTop > 0) {
            this.w.setBounds(0, 0, getWidth(), systemWindowInsetTop);
            this.w.draw(canvas);
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0031  */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View viewB;
        int actionMasked = motionEvent.getActionMasked();
        boolean zB = this.g.b(motionEvent) | this.h.b(motionEvent);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                a(true);
                this.r = false;
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                    a(true);
                    this.r = false;
                }
            } else if (this.g.a(3)) {
                this.i.b();
                this.j.b();
            }
            z = false;
        } else {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.u = x;
            this.v = y;
            z = this.f1186e > 0.0f && (viewB = this.g.b((int) x, (int) y)) != null && g(viewB);
            this.r = false;
        }
        return zB || z || e() || this.r;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !f()) {
            return super.onKeyDown(i, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View viewD = d();
        if (viewD != null && d(viewD) == 0) {
            b();
        }
        return viewD != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f2;
        int i5;
        this.l = true;
        int i6 = i3 - i;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (g(childAt)) {
                    int i8 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
                    childAt.layout(i8, ((ViewGroup.MarginLayoutParams) eVar).topMargin, childAt.getMeasuredWidth() + i8, ((ViewGroup.MarginLayoutParams) eVar).topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        float f3 = measuredWidth;
                        i5 = (-measuredWidth) + ((int) (eVar.f1191b * f3));
                        f2 = (measuredWidth + i5) / f3;
                    } else {
                        float f4 = measuredWidth;
                        int i9 = i6 - ((int) (eVar.f1191b * f4));
                        f2 = (i6 - i9) / f4;
                        i5 = i9;
                    }
                    boolean z2 = f2 != eVar.f1191b;
                    int i10 = eVar.f1190a & 112;
                    if (i10 == 16) {
                        int i11 = i4 - i2;
                        int i12 = (i11 - measuredHeight) / 2;
                        int i13 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        if (i12 < i13) {
                            i12 = i13;
                        } else {
                            int i14 = i12 + measuredHeight;
                            int i15 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                            if (i14 > i11 - i15) {
                                i12 = (i11 - i15) - measuredHeight;
                            }
                        }
                        childAt.layout(i5, i12, measuredWidth + i5, measuredHeight + i12);
                    } else if (i10 != 80) {
                        int i16 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        childAt.layout(i5, i16, measuredWidth + i5, measuredHeight + i16);
                    } else {
                        int i17 = i4 - i2;
                        childAt.layout(i5, (i17 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i5, i17 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
                    }
                    if (z2) {
                        c(childAt, f2);
                    }
                    int i18 = eVar.f1191b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i18) {
                        childAt.setVisibility(i18);
                    }
                }
            }
        }
        this.l = false;
        this.m = false;
    }

    @Override // android.view.View
    @SuppressLint({"WrongConstant"})
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824 || mode2 != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
            if (mode != Integer.MIN_VALUE && mode == 0) {
                size = 300;
            }
            if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                size2 = 300;
            }
        }
        setMeasuredDimension(size, size2);
        int i3 = 0;
        boolean z = this.B != null && t.h(this);
        int iJ = t.j(this);
        int childCount = getChildCount();
        int i4 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i4 < childCount) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (z) {
                    int iA = androidx.core.f.c.a(eVar.f1190a, iJ);
                    if (t.h(childAt)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            WindowInsets windowInsetsReplaceSystemWindowInsets = (WindowInsets) this.B;
                            if (iA == 3) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), i3, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            } else if (iA == 5) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(i3, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsetsReplaceSystemWindowInsets);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        WindowInsets windowInsetsReplaceSystemWindowInsets2 = (WindowInsets) this.B;
                        if (iA == 3) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), i3, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        } else if (iA == 5) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(i3, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        }
                        ((ViewGroup.MarginLayoutParams) eVar).leftMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft();
                        ((ViewGroup.MarginLayoutParams) eVar).topMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop();
                        ((ViewGroup.MarginLayoutParams) eVar).rightMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight();
                        ((ViewGroup.MarginLayoutParams) eVar).bottomMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom();
                    }
                }
                if (g(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - ((ViewGroup.MarginLayoutParams) eVar).leftMargin) - ((ViewGroup.MarginLayoutParams) eVar).rightMargin, MemoryConstants.GB), View.MeasureSpec.makeMeasureSpec((size2 - ((ViewGroup.MarginLayoutParams) eVar).topMargin) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, MemoryConstants.GB));
                } else {
                    if (!i(childAt)) {
                        throw new IllegalStateException("Child " + childAt + " at index " + i4 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                    }
                    if (O) {
                        float fG = t.g(childAt);
                        float f2 = this.f1183b;
                        if (fG != f2) {
                            t.a(childAt, f2);
                        }
                    }
                    int iE = e(childAt) & 7;
                    boolean z4 = iE == 3;
                    if ((z4 && z2) || (!z4 && z3)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + d(iE) + " but this DrawerLayout already has a drawer view along that edge");
                    }
                    if (z4) {
                        z2 = true;
                    } else {
                        z3 = true;
                    }
                    childAt.measure(ViewGroup.getChildMeasureSpec(i, this.f1184c + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin, ((ViewGroup.MarginLayoutParams) eVar).width), ViewGroup.getChildMeasureSpec(i2, ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, ((ViewGroup.MarginLayoutParams) eVar).height));
                }
            }
            i4++;
            i3 = 0;
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        View viewA;
        if (!(parcelable instanceof f)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        f fVar = (f) parcelable;
        super.onRestoreInstanceState(fVar.a());
        int i = fVar.f1194c;
        if (i != 0 && (viewA = a(i)) != null) {
            k(viewA);
        }
        int i2 = fVar.f1195d;
        if (i2 != 3) {
            a(i2, 3);
        }
        int i3 = fVar.f1196e;
        if (i3 != 3) {
            a(i3, 5);
        }
        int i4 = fVar.f1197f;
        if (i4 != 3) {
            a(i4, 8388611);
        }
        int i5 = fVar.g;
        if (i5 != 3) {
            a(i5, 8388613);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        i();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        f fVar = new f(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            e eVar = (e) getChildAt(i).getLayoutParams();
            boolean z = eVar.f1193d == 1;
            boolean z2 = eVar.f1193d == 2;
            if (z || z2) {
                fVar.f1194c = eVar.f1190a;
                break;
            }
        }
        fVar.f1195d = this.n;
        fVar.f1196e = this.o;
        fVar.f1197f = this.p;
        fVar.g = this.q;
        return fVar;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x005d  */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View viewC;
        this.g.a(motionEvent);
        this.h.a(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.u = x;
            this.v = y;
            this.r = false;
        } else if (action == 1) {
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            View viewB = this.g.b((int) x2, (int) y2);
            if (viewB != null && g(viewB)) {
                float f2 = x2 - this.u;
                float f3 = y2 - this.v;
                int iD = this.g.d();
                z = (f2 * f2) + (f3 * f3) >= ((float) (iD * iD)) || (viewC = c()) == null || d(viewC) == 2;
            }
            a(z);
        } else if (action == 3) {
            a(true);
            this.r = false;
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z) {
            a(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.l) {
            return;
        }
        super.requestLayout();
    }

    public void setDrawerElevation(float f2) {
        this.f1183b = f2;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (i(childAt)) {
                t.a(childAt, this.f1183b);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(d dVar) {
        d dVar2 = this.s;
        if (dVar2 != null) {
            b(dVar2);
        }
        if (dVar != null) {
            a(dVar);
        }
        this.s = dVar;
    }

    public void setDrawerLockMode(int i) {
        a(i, 3);
        a(i, 5);
    }

    public void setScrimColor(int i) {
        this.f1185d = i;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.w = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i) {
        this.w = new ColorDrawable(i);
        invalidate();
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private boolean f() {
        return d() != null;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1182a = new c();
        this.f1185d = -1728053248;
        this.f1187f = new Paint();
        this.m = true;
        this.n = 3;
        this.o = 3;
        this.p = 3;
        this.q = 3;
        this.D = null;
        this.F = null;
        this.G = null;
        this.H = null;
        setDescendantFocusability(262144);
        float f2 = getResources().getDisplayMetrics().density;
        this.f1184c = (int) ((64.0f * f2) + 0.5f);
        float f3 = 400.0f * f2;
        this.i = new g(3);
        this.j = new g(5);
        this.g = androidx.customview.b.a.a(this, 1.0f, this.i);
        this.g.d(1);
        this.g.a(f3);
        this.i.a(this.g);
        this.h = androidx.customview.b.a.a(this, 1.0f, this.j);
        this.h.d(2);
        this.h.a(f3);
        this.j.a(this.h);
        setFocusableInTouchMode(true);
        t.f(this, 1);
        t.a(this, new b());
        setMotionEventSplittingEnabled(false);
        if (t.h(this)) {
            if (Build.VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new a(this));
                setSystemUiVisibility(1280);
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(L);
                try {
                    this.w = typedArrayObtainStyledAttributes.getDrawable(0);
                    typedArrayObtainStyledAttributes.recycle();
                } catch (Throwable th) {
                    typedArrayObtainStyledAttributes.recycle();
                    throw th;
                }
            } else {
                this.w = null;
            }
        }
        this.f1183b = f2 * 10.0f;
        this.I = new ArrayList<>();
    }

    private boolean e() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((e) getChildAt(i).getLayoutParams()).f1192c) {
                return true;
            }
        }
        return false;
    }

    public int b(int i) {
        int iJ = t.j(this);
        if (i == 3) {
            int i2 = this.n;
            if (i2 != 3) {
                return i2;
            }
            int i3 = iJ == 0 ? this.p : this.q;
            if (i3 != 3) {
                return i3;
            }
            return 0;
        }
        if (i == 5) {
            int i4 = this.o;
            if (i4 != 3) {
                return i4;
            }
            int i5 = iJ == 0 ? this.q : this.p;
            if (i5 != 3) {
                return i5;
            }
            return 0;
        }
        if (i == 8388611) {
            int i6 = this.p;
            if (i6 != 3) {
                return i6;
            }
            int i7 = iJ == 0 ? this.n : this.o;
            if (i7 != 3) {
                return i7;
            }
            return 0;
        }
        if (i != 8388613) {
            return 0;
        }
        int i8 = this.q;
        if (i8 != 3) {
            return i8;
        }
        int i9 = iJ == 0 ? this.o : this.n;
        if (i9 != 3) {
            return i9;
        }
        return 0;
    }

    public void setStatusBarBackground(int i) {
        this.w = i != 0 ? androidx.core.content.a.c(getContext(), i) : null;
        invalidate();
    }

    boolean i(View view) {
        int iA = androidx.core.f.c.a(((e) view.getLayoutParams()).f1190a, t.j(view));
        return ((iA & 3) == 0 && (iA & 5) == 0) ? false : true;
    }

    public static class e extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1190a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1191b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1192c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1193d;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1190a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.M);
            this.f1190a = typedArrayObtainStyledAttributes.getInt(0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public e(int i, int i2) {
            super(i, i2);
            this.f1190a = 0;
        }

        public e(e eVar) {
            super((ViewGroup.MarginLayoutParams) eVar);
            this.f1190a = 0;
            this.f1190a = eVar.f1190a;
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1190a = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1190a = 0;
        }
    }

    static String d(int i) {
        if ((i & 3) == 3) {
            return "LEFT";
        }
        return (i & 5) == 5 ? "RIGHT" : Integer.toHexString(i);
    }

    public void a(d dVar) {
        if (dVar == null) {
            return;
        }
        if (this.t == null) {
            this.t = new ArrayList();
        }
        this.t.add(dVar);
    }

    void c(View view) {
        e eVar = (e) view.getLayoutParams();
        if ((eVar.f1193d & 1) == 0) {
            eVar.f1193d = 1;
            List<d> list = this.t;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.t.get(size).a(view);
                }
            }
            c(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    View d() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (i(childAt) && j(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    protected static class f extends androidx.customview.a.a {
        public static final Parcelable.Creator<f> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1194c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1195d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1196e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1197f;
        int g;

        static class a implements Parcelable.ClassLoaderCreator<f> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public f[] newArray(int i) {
                return new f[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public f createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new f(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public f createFromParcel(Parcel parcel) {
                return new f(parcel, null);
            }
        }

        public f(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1194c = 0;
            this.f1194c = parcel.readInt();
            this.f1195d = parcel.readInt();
            this.f1196e = parcel.readInt();
            this.f1197f = parcel.readInt();
            this.g = parcel.readInt();
        }

        @Override // androidx.customview.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1194c);
            parcel.writeInt(this.f1195d);
            parcel.writeInt(this.f1196e);
            parcel.writeInt(this.f1197f);
            parcel.writeInt(this.g);
        }

        public f(Parcelable parcelable) {
            super(parcelable);
            this.f1194c = 0;
        }
    }

    public void a(int i, int i2) {
        View viewA;
        int iA = androidx.core.f.c.a(i2, t.j(this));
        if (i2 == 3) {
            this.n = i;
        } else if (i2 == 5) {
            this.o = i;
        } else if (i2 == 8388611) {
            this.p = i;
        } else if (i2 == 8388613) {
            this.q = i;
        }
        if (i != 0) {
            (iA == 3 ? this.g : this.h).a();
        }
        if (i != 1) {
            if (i == 2 && (viewA = a(iA)) != null) {
                k(viewA);
                return;
            }
            return;
        }
        View viewA2 = a(iA);
        if (viewA2 != null) {
            a(viewA2);
        }
    }

    boolean g(View view) {
        return ((e) view.getLayoutParams()).f1190a == 0;
    }

    public boolean h(View view) {
        if (i(view)) {
            return (((e) view.getLayoutParams()).f1193d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    private MotionEvent b(MotionEvent motionEvent, View view) {
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(scrollX, scrollY);
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            if (this.K == null) {
                this.K = new Matrix();
            }
            matrix.invert(this.K);
            motionEventObtain.transform(this.K);
        }
        return motionEventObtain;
    }

    private void c(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((!z && !i(childAt)) || (z && childAt == view)) {
                t.f(childAt, 1);
            } else {
                t.f(childAt, 4);
            }
        }
    }

    class b extends androidx.core.f.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Rect f1188d = new Rect();

        b() {
        }

        @Override // androidx.core.f.a
        public void a(View view, androidx.core.f.c0.b bVar) {
            if (DrawerLayout.N) {
                super.a(view, bVar);
            } else {
                androidx.core.f.c0.b bVarA = androidx.core.f.c0.b.a(bVar);
                super.a(view, bVarA);
                bVar.c(view);
                Object objL = t.l(view);
                if (objL instanceof View) {
                    bVar.b((View) objL);
                }
                a(bVar, bVarA);
                bVarA.t();
                a(bVar, (ViewGroup) view);
            }
            bVar.a((CharSequence) DrawerLayout.class.getName());
            bVar.e(false);
            bVar.f(false);
            bVar.b(androidx.core.f.c0.b.a.f1066d);
            bVar.b(androidx.core.f.c0.b.a.f1067e);
        }

        @Override // androidx.core.f.a
        public void b(View view, AccessibilityEvent accessibilityEvent) {
            super.b(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        @Override // androidx.core.f.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() == 32) {
                List<CharSequence> text = accessibilityEvent.getText();
                View viewD = DrawerLayout.this.d();
                if (viewD == null) {
                    return true;
                }
                CharSequence charSequenceC = DrawerLayout.this.c(DrawerLayout.this.e(viewD));
                if (charSequenceC == null) {
                    return true;
                }
                text.add(charSequenceC);
                return true;
            }
            return super.a(view, accessibilityEvent);
        }

        @Override // androidx.core.f.a
        public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.N || DrawerLayout.m(view)) {
                return super.a(viewGroup, view, accessibilityEvent);
            }
            return false;
        }

        private void a(androidx.core.f.c0.b bVar, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (DrawerLayout.m(childAt)) {
                    bVar.a(childAt);
                }
            }
        }

        private void a(androidx.core.f.c0.b bVar, androidx.core.f.c0.b bVar2) {
            Rect rect = this.f1188d;
            bVar2.a(rect);
            bVar.c(rect);
            bVar2.b(rect);
            bVar.d(rect);
            bVar.l(bVar2.s());
            bVar.c(bVar2.e());
            bVar.a(bVar2.b());
            bVar.b(bVar2.c());
            bVar.d(bVar2.l());
            bVar.c(bVar2.k());
            bVar.e(bVar2.m());
            bVar.f(bVar2.n());
            bVar.a(bVar2.h());
            bVar.k(bVar2.r());
            bVar.h(bVar2.o());
            bVar.a(bVar2.a());
        }
    }

    void c(View view, float f2) {
        e eVar = (e) view.getLayoutParams();
        if (f2 == eVar.f1191b) {
            return;
        }
        eVar.f1191b = f2;
        a(view, f2);
    }

    private boolean a(float f2, float f3, View view) {
        if (this.J == null) {
            this.J = new Rect();
        }
        view.getHitRect(this.J);
        return this.J.contains((int) f2, (int) f3);
    }

    void b(View view) {
        View rootView;
        e eVar = (e) view.getLayoutParams();
        if ((eVar.f1193d & 1) == 1) {
            eVar.f1193d = 0;
            List<d> list = this.t;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.t.get(size).b(view);
                }
            }
            c(view, false);
            if (!hasWindowFocus() || (rootView = getRootView()) == null) {
                return;
            }
            rootView.sendAccessibilityEvent(32);
        }
    }

    View c() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((((e) childAt.getLayoutParams()).f1193d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    private boolean a(MotionEvent motionEvent, View view) {
        if (!view.getMatrix().isIdentity()) {
            MotionEvent motionEventB = b(motionEvent, view);
            boolean zDispatchGenericMotionEvent = view.dispatchGenericMotionEvent(motionEventB);
            motionEventB.recycle();
            return zDispatchGenericMotionEvent;
        }
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        motionEvent.offsetLocation(scrollX, scrollY);
        boolean zDispatchGenericMotionEvent2 = view.dispatchGenericMotionEvent(motionEvent);
        motionEvent.offsetLocation(-scrollX, -scrollY);
        return zDispatchGenericMotionEvent2;
    }

    void b(View view, float f2) {
        float f3 = f(view);
        float width = view.getWidth();
        int i = ((int) (width * f2)) - ((int) (f3 * width));
        if (!a(view, 3)) {
            i = -i;
        }
        view.offsetLeftAndRight(i);
        c(view, f2);
    }

    void a(int i, int i2, View view) {
        int iE = this.g.e();
        int iE2 = this.h.e();
        int i3 = 2;
        if (iE == 1 || iE2 == 1) {
            i3 = 1;
        } else if (iE != 2 && iE2 != 2) {
            i3 = 0;
        }
        if (view != null && i2 == 0) {
            float f2 = ((e) view.getLayoutParams()).f1191b;
            if (f2 == 0.0f) {
                b(view);
            } else if (f2 == 1.0f) {
                c(view);
            }
        }
        if (i3 != this.k) {
            this.k = i3;
            List<d> list = this.t;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.t.get(size).a(i3);
                }
            }
        }
    }

    public void b() {
        a(false);
    }

    public void b(View view, boolean z) {
        if (i(view)) {
            e eVar = (e) view.getLayoutParams();
            if (this.m) {
                eVar.f1191b = 1.0f;
                eVar.f1193d = 1;
                c(view, true);
            } else if (z) {
                eVar.f1193d |= 2;
                if (a(view, 3)) {
                    this.g.b(view, 0, view.getTop());
                } else {
                    this.h.b(view, getWidth() - view.getWidth(), view.getTop());
                }
            } else {
                b(view, 1.0f);
                a(eVar.f1190a, 0, view);
                view.setVisibility(0);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    void a(View view, float f2) {
        List<d> list = this.t;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.t.get(size).a(view, f2);
            }
        }
    }

    boolean a(View view, int i) {
        return (e(view) & i) == i;
    }

    View a(int i) {
        int iA = androidx.core.f.c.a(i, t.j(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((e(childAt) & 7) == iA) {
                return childAt;
            }
        }
        return null;
    }

    private boolean a(Drawable drawable, int i) {
        if (drawable == null || !androidx.core.graphics.drawable.a.e(drawable)) {
            return false;
        }
        androidx.core.graphics.drawable.a.a(drawable, i);
        return true;
    }

    void a(boolean z) {
        boolean zB;
        int childCount = getChildCount();
        boolean z2 = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            e eVar = (e) childAt.getLayoutParams();
            if (i(childAt) && (!z || eVar.f1192c)) {
                int width = childAt.getWidth();
                if (a(childAt, 3)) {
                    zB = this.g.b(childAt, -width, childAt.getTop());
                } else {
                    zB = this.h.b(childAt, getWidth(), childAt.getTop());
                }
                z2 |= zB;
                eVar.f1192c = false;
            }
        }
        this.i.b();
        this.j.b();
        if (z2) {
            invalidate();
        }
    }

    public void a(View view) {
        a(view, true);
    }

    public void a(View view, boolean z) {
        if (i(view)) {
            e eVar = (e) view.getLayoutParams();
            if (this.m) {
                eVar.f1191b = 0.0f;
                eVar.f1193d = 0;
            } else if (z) {
                eVar.f1193d |= 4;
                if (a(view, 3)) {
                    this.g.b(view, -view.getWidth(), view.getTop());
                } else {
                    this.h.b(view, getWidth(), view.getTop());
                }
            } else {
                b(view, 0.0f);
                a(eVar.f1190a, 0, view);
                view.setVisibility(4);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    void a() {
        if (this.r) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).dispatchTouchEvent(motionEventObtain);
        }
        motionEventObtain.recycle();
        this.r = true;
    }
}
