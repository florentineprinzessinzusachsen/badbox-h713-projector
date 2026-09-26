package androidx.core.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.core.f.m;
import androidx.core.f.o;
import androidx.core.f.r;
import androidx.core.f.t;
import com.blankj.utilcode.constant.MemoryConstants;
import com.blankj.utilcode.constant.TimeConstants;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements m, androidx.core.f.i, r {
    private static final a A = new a();
    private static final int[] B = {R.attr.fillViewport};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f1141a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Rect f1142b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private OverScroller f1143c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private EdgeEffect f1144d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private EdgeEffect f1145e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1146f;
    private boolean g;
    private boolean h;
    private View i;
    private boolean j;
    private VelocityTracker k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private final int[] r;
    private final int[] s;
    private int t;
    private int u;
    private c v;
    private final o w;
    private final androidx.core.f.k x;
    private float y;
    private b z;

    public interface b {
        void a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4);
    }

    static class c extends View.BaseSavedState {
        public static final Parcelable.Creator<c> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1147a;

        static class a implements Parcelable.Creator<c> {
            a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public c createFromParcel(Parcel parcel) {
                return new c(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public c[] newArray(int i) {
                return new c[i];
            }
        }

        c(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f1147a + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1147a);
        }

        c(Parcel parcel) {
            super(parcel);
            this.f1147a = parcel.readInt();
        }
    }

    public NestedScrollView(Context context) {
        this(context, null);
    }

    private static int a(int i, int i2, int i3) {
        if (i2 >= i3 || i < 0) {
            return 0;
        }
        return i2 + i > i3 ? i3 - i2 : i;
    }

    private boolean b() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private void e() {
        VelocityTracker velocityTracker = this.k;
        if (velocityTracker == null) {
            this.k = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void g() {
        if (this.k == null) {
            this.k = VelocityTracker.obtain();
        }
    }

    private float getVerticalScrollFactorCompat() {
        if (this.y == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.y = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.y;
    }

    private void h() {
        VelocityTracker velocityTracker = this.k;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.k = null;
        }
    }

    public void a(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        this.x.a(i, i2, i3, i4, iArr, i5, iArr2);
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    @Override // androidx.core.f.l
    public boolean b(View view, View view2, int i, int i2) {
        return (i & 2) != 0;
    }

    public boolean c(int i, int i2) {
        return this.x.a(i, i2);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.f1143c.isFinished()) {
            return;
        }
        this.f1143c.computeScrollOffset();
        int currY = this.f1143c.getCurrY();
        int i = currY - this.u;
        this.u = currY;
        int[] iArr = this.s;
        boolean z = false;
        iArr[1] = 0;
        a(0, i, iArr, (int[]) null, 1);
        int i2 = i - this.s[1];
        int scrollRange = getScrollRange();
        if (i2 != 0) {
            int scrollY = getScrollY();
            a(0, i2, getScrollX(), scrollY, 0, scrollRange, 0, 0, false);
            int scrollY2 = getScrollY() - scrollY;
            int i3 = i2 - scrollY2;
            int[] iArr2 = this.s;
            iArr2[1] = 0;
            a(0, scrollY2, 0, i3, this.r, 1, iArr2);
            i2 = i3 - this.s[1];
        }
        if (i2 != 0) {
            int overScrollMode = getOverScrollMode();
            if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                z = true;
            }
            if (z) {
                d();
                if (i2 < 0) {
                    if (this.f1144d.isFinished()) {
                        this.f1144d.onAbsorb((int) this.f1143c.getCurrVelocity());
                    }
                } else if (this.f1145e.isFinished()) {
                    this.f1145e.onAbsorb((int) this.f1143c.getCurrVelocity());
                }
            }
            a();
        }
        if (this.f1143c.isFinished()) {
            return;
        }
        t.t(this);
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int iMax = Math.max(0, bottom - height);
        if (scrollY < 0) {
            return bottom - scrollY;
        }
        return scrollY > iMax ? bottom + (scrollY - iMax) : bottom;
    }

    public boolean d(int i) {
        return this.x.a(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return this.x.a(f2, f3, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.x.a(f2, f3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return a(i, i2, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.x.a(i, i2, i3, i4, iArr);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int paddingLeft;
        super.draw(canvas);
        if (this.f1144d != null) {
            int scrollY = getScrollY();
            int paddingLeft2 = 0;
            if (!this.f1144d.isFinished()) {
                int iSave = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int iMin = Math.min(0, scrollY);
                if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    paddingLeft = getPaddingLeft() + 0;
                } else {
                    paddingLeft = 0;
                }
                if (Build.VERSION.SDK_INT >= 21 && getClipToPadding()) {
                    height -= getPaddingTop() + getPaddingBottom();
                    iMin += getPaddingTop();
                }
                canvas.translate(paddingLeft, iMin);
                this.f1144d.setSize(width, height);
                if (this.f1144d.draw(canvas)) {
                    t.t(this);
                }
                canvas.restoreToCount(iSave);
            }
            if (this.f1145e.isFinished()) {
                return;
            }
            int iSave2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int iMax = Math.max(getScrollRange(), scrollY) + height2;
            if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                width2 -= getPaddingLeft() + getPaddingRight();
                paddingLeft2 = 0 + getPaddingLeft();
            }
            if (Build.VERSION.SDK_INT >= 21 && getClipToPadding()) {
                height2 -= getPaddingTop() + getPaddingBottom();
                iMax -= getPaddingBottom();
            }
            canvas.translate(paddingLeft2 - width2, iMax);
            canvas.rotate(180.0f, width2, 0.0f);
            this.f1145e.setSize(width2, height2);
            if (this.f1145e.draw(canvas)) {
                t.t(this);
            }
            canvas.restoreToCount(iSave2);
        }
    }

    public void f(int i) {
        this.x.b(i);
    }

    @Override // android.view.View
    protected float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + layoutParams.bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.w.a();
    }

    int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return d(0);
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.x.a();
    }

    @Override // android.view.ViewGroup
    protected void measureChild(View view, int i, int i2) {
        view.measure(FrameLayout.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h = false;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.j) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                int verticalScrollFactorCompat = (int) (axisValue * getVerticalScrollFactorCompat());
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int i = scrollY - verticalScrollFactorCompat;
                if (i < 0) {
                    i = 0;
                } else if (i > scrollRange) {
                    i = scrollRange;
                }
                if (i != scrollY) {
                    super.scrollTo(getScrollX(), i);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x007d  */
    /* JADX WARN: Code duplicated, block: B:33:0x009b  */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.j) {
            return true;
        }
        int i = action & 255;
        if (i == 0) {
            int y = (int) motionEvent.getY();
            if (d((int) motionEvent.getX(), y)) {
                this.f1146f = y;
                this.q = motionEvent.getPointerId(0);
                e();
                this.k.addMovement(motionEvent);
                this.f1143c.computeScrollOffset();
                this.j = !this.f1143c.isFinished();
                c(2, 0);
            } else {
                this.j = false;
                h();
            }
        } else if (i == 1) {
            this.j = false;
            this.q = -1;
            h();
            if (this.f1143c.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                t.t(this);
            }
            f(0);
        } else if (i == 2) {
            int i2 = this.q;
            if (i2 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i2);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + i2 + " in onInterceptTouchEvent");
                } else {
                    int y2 = (int) motionEvent.getY(iFindPointerIndex);
                    if (Math.abs(y2 - this.f1146f) > this.n && (2 & getNestedScrollAxes()) == 0) {
                        this.j = true;
                        this.f1146f = y2;
                        g();
                        this.k.addMovement(motionEvent);
                        this.t = 0;
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
            }
        } else if (i == 3) {
            this.j = false;
            this.q = -1;
            h();
            if (this.f1143c.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                t.t(this);
            }
            f(0);
        } else if (i == 6) {
            a(motionEvent);
        }
        return this.j;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int measuredHeight = 0;
        this.g = false;
        View view = this.i;
        if (view != null && a(view, this)) {
            b(this.i);
        }
        this.i = null;
        if (!this.h) {
            if (this.v != null) {
                scrollTo(getScrollX(), this.v.f1147a);
                this.v = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int iA = a(scrollY, paddingTop, measuredHeight);
            if (iA != scrollY) {
                scrollTo(getScrollX(), iA);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.h = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.l && View.MeasureSpec.getMode(i2) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(FrameLayout.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, MemoryConstants.GB));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        if (z) {
            return false;
        }
        dispatchNestedFling(0.0f, f3, true);
        b((int) f3);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        a(view, i, i2, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        a(i4, 0, (int[]) null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public void onNestedScrollAccepted(View view, View view2, int i) {
        a(view, view2, i, 0);
    }

    @Override // android.view.View
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.scrollTo(i, i2);
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (i == 2) {
            i = 130;
        } else if (i == 1) {
            i = 33;
        }
        View viewFindNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i);
        if (viewFindNextFocus == null || a(viewFindNextFocus)) {
            return false;
        }
        return viewFindNextFocus.requestFocus(i, rect);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof c)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        c cVar = (c) parcelable;
        super.onRestoreInstanceState(cVar.getSuperState());
        this.v = cVar;
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        c cVar = new c(super.onSaveInstanceState());
        cVar.f1147a = getScrollY();
        return cVar;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        b bVar = this.z;
        if (bVar != null) {
            bVar.a(this, i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !a(viewFindFocus, 0, i4)) {
            return;
        }
        viewFindFocus.getDrawingRect(this.f1142b);
        offsetDescendantRectToMyCoords(viewFindFocus, this.f1142b);
        g(a(this.f1142b));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return b(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.f.n
    public void onStopNestedScroll(View view) {
        a(view, 0);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        g();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.t = 0;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(0.0f, this.t);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.k;
                velocityTracker.computeCurrentVelocity(TimeConstants.SEC, this.p);
                int yVelocity = (int) velocityTracker.getYVelocity(this.q);
                if (Math.abs(yVelocity) > this.o) {
                    int i = -yVelocity;
                    float f2 = i;
                    if (!dispatchNestedPreFling(0.0f, f2)) {
                        dispatchNestedFling(0.0f, f2, true);
                        b(i);
                    }
                } else if (this.f1143c.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    t.t(this);
                }
                this.q = -1;
                c();
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.q);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.q + " in onTouchEvent");
                } else {
                    int y = (int) motionEvent.getY(iFindPointerIndex);
                    int i2 = this.f1146f - y;
                    if (a(0, i2, this.s, this.r, 0)) {
                        i2 -= this.s[1];
                        this.t += this.r[1];
                    }
                    if (!this.j && Math.abs(i2) > this.n) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.j = true;
                        i2 = i2 > 0 ? i2 - this.n : i2 + this.n;
                    }
                    int i3 = i2;
                    if (this.j) {
                        this.f1146f = y - this.r[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        int overScrollMode = getOverScrollMode();
                        boolean z = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
                        if (a(0, i3, 0, getScrollY(), 0, scrollRange, 0, 0, true) && !d(0)) {
                            this.k.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        int[] iArr = this.s;
                        iArr[1] = 0;
                        a(0, scrollY2, 0, i3 - scrollY2, this.r, 0, iArr);
                        int i4 = this.f1146f;
                        int[] iArr2 = this.r;
                        this.f1146f = i4 - iArr2[1];
                        this.t += iArr2[1];
                        if (z) {
                            int i5 = i3 - this.s[1];
                            d();
                            int i6 = scrollY + i5;
                            if (i6 < 0) {
                                d.a(this.f1144d, i5 / getHeight(), motionEvent.getX(iFindPointerIndex) / getWidth());
                                if (!this.f1145e.isFinished()) {
                                    this.f1145e.onRelease();
                                }
                            } else if (i6 > scrollRange) {
                                d.a(this.f1145e, i5 / getHeight(), 1.0f - (motionEvent.getX(iFindPointerIndex) / getWidth()));
                                if (!this.f1144d.isFinished()) {
                                    this.f1144d.onRelease();
                                }
                            }
                            EdgeEffect edgeEffect = this.f1144d;
                            if (edgeEffect != null && (!edgeEffect.isFinished() || !this.f1145e.isFinished())) {
                                t.t(this);
                            }
                        }
                    }
                }
            } else if (actionMasked == 3) {
                if (this.j && getChildCount() > 0 && this.f1143c.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    t.t(this);
                }
                this.q = -1;
                c();
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.f1146f = (int) motionEvent.getY(actionIndex);
                this.q = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                a(motionEvent);
                this.f1146f = (int) motionEvent.getY(motionEvent.findPointerIndex(this.q));
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            boolean z2 = !this.f1143c.isFinished();
            this.j = z2;
            if (z2 && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.f1143c.isFinished()) {
                a();
            }
            this.f1146f = (int) motionEvent.getY();
            this.q = motionEvent.getPointerId(0);
            c(2, 0);
        }
        VelocityTracker velocityTracker2 = this.k;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (this.g) {
            this.i = view2;
        } else {
            b(view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return a(rect, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            h();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.g = true;
        super.requestLayout();
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int iA = a(i, width, width2);
            int iA2 = a(i2, height, height2);
            if (iA == getScrollX() && iA2 == getScrollY()) {
                return;
            }
            super.scrollTo(iA, iA2);
        }
    }

    public void setFillViewport(boolean z) {
        if (z != this.l) {
            this.l = z;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.x.a(z);
    }

    public void setOnScrollChangeListener(b bVar) {
        this.z = bVar;
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.m = z;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i) {
        return c(i, 0);
    }

    @Override // android.view.View, androidx.core.f.j
    public void stopNestedScroll() {
        f(0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private boolean d(int i, int i2) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        return i2 >= childAt.getTop() - scrollY && i2 < childAt.getBottom() - scrollY && i >= childAt.getLeft() && i < childAt.getRight();
    }

    private void f() {
        this.f1143c = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.n = viewConfiguration.getScaledTouchSlop();
        this.o = viewConfiguration.getScaledMinimumFlingVelocity();
        this.p = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return this.x.a(i, i2, iArr, iArr2, i3);
    }

    public boolean c(int i) {
        int childCount;
        boolean z = i == 130;
        int height = getHeight();
        Rect rect = this.f1142b;
        rect.top = 0;
        rect.bottom = height;
        if (z && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.f1142b.bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
            Rect rect2 = this.f1142b;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.f1142b;
        return b(i, rect3.top, rect3.bottom);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1142b = new Rect();
        this.g = true;
        this.h = false;
        this.i = null;
        this.j = false;
        this.m = true;
        this.q = -1;
        this.r = new int[2];
        this.s = new int[2];
        f();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, B, i, 0);
        setFillViewport(typedArrayObtainStyledAttributes.getBoolean(0, false));
        typedArrayObtainStyledAttributes.recycle();
        this.w = new o(this);
        this.x = new androidx.core.f.k(this);
        setNestedScrollingEnabled(true);
        t.a(this, A);
    }

    private void g(int i) {
        if (i != 0) {
            if (this.m) {
                a(0, i);
            } else {
                scrollBy(0, i);
            }
        }
    }

    @Override // androidx.core.f.m
    public void a(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        a(i4, i5, iArr);
    }

    private void a(int i, int i2, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.x.a(0, scrollY2, 0, i - scrollY2, null, i2, iArr);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        if (getChildCount() <= 0) {
            super.addView(view, i);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public boolean e(int i) {
        boolean z = i == 130;
        int height = getHeight();
        if (z) {
            this.f1142b.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
                Rect rect = this.f1142b;
                if (rect.top + height > bottom) {
                    rect.top = bottom - height;
                }
            }
        } else {
            this.f1142b.top = getScrollY() - height;
            Rect rect2 = this.f1142b;
            if (rect2.top < 0) {
                rect2.top = 0;
            }
        }
        Rect rect3 = this.f1142b;
        int i2 = rect3.top;
        rect3.bottom = height + i2;
        return b(i, i2, rect3.bottom);
    }

    private boolean b(int i, int i2, int i3) {
        int height = getHeight();
        int scrollY = getScrollY();
        int i4 = height + scrollY;
        boolean z = false;
        boolean z2 = i == 33;
        View viewA = a(z2, i2, i3);
        if (viewA == null) {
            viewA = this;
        }
        if (i2 < scrollY || i3 > i4) {
            g(z2 ? i2 - scrollY : i3 - i4);
            z = true;
        }
        if (viewA != findFocus()) {
            viewA.requestFocus(i);
        }
        return z;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    private void d() {
        if (getOverScrollMode() != 2) {
            if (this.f1144d == null) {
                Context context = getContext();
                this.f1144d = new EdgeEffect(context);
                this.f1145e = new EdgeEffect(context);
                return;
            }
            return;
        }
        this.f1144d = null;
        this.f1145e = null;
    }

    @Override // androidx.core.f.l
    public void a(View view, View view2, int i, int i2) {
        this.w.a(view, view2, i, i2);
        c(2, i2);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    private void c() {
        this.j = false;
        h();
        f(0);
        EdgeEffect edgeEffect = this.f1144d;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.f1145e.onRelease();
        }
    }

    @Override // androidx.core.f.l
    public void a(View view, int i) {
        this.w.a(view, i);
        f(i);
    }

    public final void b(int i, int i2) {
        a(i - getScrollX(), i2 - getScrollY());
    }

    private void b(View view) {
        view.getDrawingRect(this.f1142b);
        offsetDescendantRectToMyCoords(view, this.f1142b);
        int iA = a(this.f1142b);
        if (iA != 0) {
            scrollBy(0, iA);
        }
    }

    @Override // androidx.core.f.l
    public void a(View view, int i, int i2, int i3, int i4, int i5) {
        a(i4, i5, (int[]) null);
    }

    @Override // androidx.core.f.l
    public void a(View view, int i, int i2, int[] iArr, int i3) {
        a(i, i2, iArr, (int[]) null, i3);
    }

    static class a extends androidx.core.f.a {
        a() {
        }

        @Override // androidx.core.f.a
        public boolean a(View view, int i, Bundle bundle) {
            if (super.a(view, i, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            if (i == 4096) {
                int iMin = Math.min(nestedScrollView.getScrollY() + ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.getScrollRange());
                if (iMin == nestedScrollView.getScrollY()) {
                    return false;
                }
                nestedScrollView.b(0, iMin);
                return true;
            }
            if (i != 8192) {
                return false;
            }
            int iMax = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
            if (iMax == nestedScrollView.getScrollY()) {
                return false;
            }
            nestedScrollView.b(0, iMax);
            return true;
        }

        @Override // androidx.core.f.a
        public void b(View view, AccessibilityEvent accessibilityEvent) {
            super.b(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            accessibilityEvent.setScrollable(nestedScrollView.getScrollRange() > 0);
            accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
            accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
            androidx.core.f.c0.d.a(accessibilityEvent, nestedScrollView.getScrollX());
            androidx.core.f.c0.d.b(accessibilityEvent, nestedScrollView.getScrollRange());
        }

        @Override // androidx.core.f.a
        public void a(View view, androidx.core.f.c0.b bVar) {
            int scrollRange;
            super.a(view, bVar);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            bVar.a((CharSequence) ScrollView.class.getName());
            if (!nestedScrollView.isEnabled() || (scrollRange = nestedScrollView.getScrollRange()) <= 0) {
                return;
            }
            bVar.j(true);
            if (nestedScrollView.getScrollY() > 0) {
                bVar.a(8192);
            }
            if (nestedScrollView.getScrollY() < scrollRange) {
                bVar.a(4096);
            }
        }
    }

    public boolean a(KeyEvent keyEvent) {
        this.f1142b.setEmpty();
        if (!b()) {
            if (!isFocused() || keyEvent.getKeyCode() == 4) {
                return false;
            }
            View viewFindFocus = findFocus();
            if (viewFindFocus == this) {
                viewFindFocus = null;
            }
            View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, 130);
            return (viewFindNextFocus == null || viewFindNextFocus == this || !viewFindNextFocus.requestFocus(130)) ? false : true;
        }
        if (keyEvent.getAction() != 0) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 19) {
            if (!keyEvent.isAltPressed()) {
                return a(33);
            }
            return c(33);
        }
        if (keyCode == 20) {
            if (!keyEvent.isAltPressed()) {
                return a(130);
            }
            return c(130);
        }
        if (keyCode != 62) {
            return false;
        }
        e(keyEvent.isShiftPressed() ? 33 : 130);
        return false;
    }

    public void b(int i) {
        if (getChildCount() > 0) {
            this.f1143c.fling(getScrollX(), getScrollY(), 0, i, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            a(true);
        }
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.q) {
            int i = actionIndex == 0 ? 1 : 0;
            this.f1146f = (int) motionEvent.getY(i);
            this.q = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.k;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0057  */
    /* JADX WARN: Code duplicated, block: B:40:0x005a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x005c  */
    /* JADX WARN: Code duplicated, block: B:42:0x005e  */
    /* JADX WARN: Code duplicated, block: B:44:0x0061  */
    /* JADX WARN: Code duplicated, block: B:49:0x0083 A[ADDED_TO_REGION] */
    boolean a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        boolean z2;
        boolean z3;
        int overScrollMode = getOverScrollMode();
        boolean z4 = computeHorizontalScrollRange() > computeHorizontalScrollExtent();
        boolean z5 = computeVerticalScrollRange() > computeVerticalScrollExtent();
        boolean z6 = overScrollMode == 0 || (overScrollMode == 1 && z4);
        boolean z7 = overScrollMode == 0 || (overScrollMode == 1 && z5);
        int i9 = i3 + i;
        int i10 = !z6 ? 0 : i7;
        int i11 = i4 + i2;
        int i12 = !z7 ? 0 : i8;
        int i13 = -i10;
        int i14 = i10 + i5;
        int i15 = -i12;
        int i16 = i12 + i6;
        if (i9 <= i14) {
            if (i9 >= i13) {
                i13 = i9;
                z2 = false;
            }
            if (i11 > i16) {
                if (i11 < i15) {
                    i11 = i15;
                } else {
                    z3 = false;
                }
                if (z3 && !d(1)) {
                    this.f1143c.springBack(i13, i11, 0, 0, 0, getScrollRange());
                }
                onOverScrolled(i13, i11, z2, z3);
                return !z2 || z3;
            }
            i11 = i16;
            z3 = true;
            if (z3) {
                this.f1143c.springBack(i13, i11, 0, 0, 0, getScrollRange());
            }
            onOverScrolled(i13, i11, z2, z3);
            if (z2) {
            }
        }
        i13 = i14;
        z2 = true;
        if (i11 > i16) {
            if (i11 < i15) {
                i11 = i15;
            } else {
                z3 = false;
            }
            if (z3) {
                this.f1143c.springBack(i13, i11, 0, 0, 0, getScrollRange());
            }
            onOverScrolled(i13, i11, z2, z3);
            if (z2) {
            }
        }
        i11 = i16;
        z3 = true;
        if (z3) {
            this.f1143c.springBack(i13, i11, 0, 0, 0, getScrollRange());
        }
        onOverScrolled(i13, i11, z2, z3);
        if (z2) {
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0050  */
    private View a(boolean z, int i, int i2) {
        ArrayList focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view2 = (View) focusables.get(i3);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i < bottom && top < i2) {
                boolean z3 = i < top && bottom < i2;
                if (view == null) {
                    view = view2;
                    z2 = z3;
                } else {
                    boolean z4 = (z && top < view.getTop()) || (!z && bottom > view.getBottom());
                    if (z2) {
                        if (z3 && z4) {
                            view = view2;
                        }
                    } else if (z3) {
                        view = view2;
                        z2 = true;
                    } else if (z4) {
                        view = view2;
                    }
                }
            }
        }
        return view;
    }

    public boolean a(int i) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i);
        int maxScrollAmount = getMaxScrollAmount();
        if (viewFindNextFocus != null && a(viewFindNextFocus, maxScrollAmount, getHeight())) {
            viewFindNextFocus.getDrawingRect(this.f1142b);
            offsetDescendantRectToMyCoords(viewFindNextFocus, this.f1142b);
            g(a(this.f1142b));
            viewFindNextFocus.requestFocus(i);
        } else {
            if (i == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            g(maxScrollAmount);
        }
        if (viewFindFocus == null || !viewFindFocus.isFocused() || !a(viewFindFocus)) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    private boolean a(View view) {
        return !a(view, 0, getHeight());
    }

    private boolean a(View view, int i, int i2) {
        view.getDrawingRect(this.f1142b);
        offsetDescendantRectToMyCoords(view, this.f1142b);
        return this.f1142b.bottom + i >= getScrollY() && this.f1142b.top - i <= getScrollY() + i2;
    }

    public final void a(int i, int i2) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f1141a > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            this.f1143c.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i2 + scrollY, Math.max(0, height - height2))) - scrollY);
            a(false);
        } else {
            if (!this.f1143c.isFinished()) {
                a();
            }
            scrollBy(i, i2);
        }
        this.f1141a = AnimationUtils.currentAnimationTimeMillis();
    }

    private void a(boolean z) {
        if (z) {
            c(2, 1);
        } else {
            f(1);
        }
        this.u = getScrollY();
        t.t(this);
    }

    private void a() {
        this.f1143c.abortAnimation();
        f(1);
    }

    private boolean a(Rect rect, boolean z) {
        int iA = a(rect);
        boolean z2 = iA != 0;
        if (z2) {
            if (z) {
                scrollBy(0, iA);
            } else {
                a(0, iA);
            }
        }
        return z2;
    }

    protected int a(Rect rect) {
        int i;
        int i2;
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i3 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i4 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i3 - verticalFadingEdgeLength : i3;
        if (rect.bottom > i4 && rect.top > scrollY) {
            if (rect.height() > height) {
                i2 = rect.top - scrollY;
            } else {
                i2 = rect.bottom - i4;
            }
            return Math.min(i2 + 0, (childAt.getBottom() + layoutParams.bottomMargin) - i3);
        }
        if (rect.top >= scrollY || rect.bottom >= i4) {
            return 0;
        }
        if (rect.height() > height) {
            i = 0 - (i4 - rect.bottom);
        } else {
            i = 0 - (scrollY - rect.top);
        }
        return Math.max(i, -getScrollY());
    }

    private static boolean a(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && a((View) parent, view2);
    }
}
