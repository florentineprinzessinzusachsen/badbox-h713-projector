package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f510a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private View f511b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f512c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f513d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Drawable f514e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Drawable f515f;
    Drawable g;
    boolean h;
    boolean i;
    private int j;

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    private int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    private boolean b(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f514e;
        if (drawable != null && drawable.isStateful()) {
            this.f514e.setState(getDrawableState());
        }
        Drawable drawable2 = this.f515f;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f515f.setState(getDrawableState());
        }
        Drawable drawable3 = this.g;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.g.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.f511b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f514e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f515f;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.g;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f512c = findViewById(R$id.action_bar);
        this.f513d = findViewById(R$id.action_context_bar);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f510a || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Drawable drawable;
        super.onLayout(z, i, i2, i3, i4);
        View view = this.f511b;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = (view == null || view.getVisibility() == 8) ? false : true;
        if (view != null && view.getVisibility() != 8) {
            int measuredHeight = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            view.layout(i, (measuredHeight - view.getMeasuredHeight()) - layoutParams.bottomMargin, i3, measuredHeight - layoutParams.bottomMargin);
        }
        if (this.h) {
            Drawable drawable2 = this.g;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z2 = false;
            }
        } else {
            if (this.f514e != null) {
                if (this.f512c.getVisibility() == 0) {
                    this.f514e.setBounds(this.f512c.getLeft(), this.f512c.getTop(), this.f512c.getRight(), this.f512c.getBottom());
                } else {
                    View view2 = this.f513d;
                    if (view2 == null || view2.getVisibility() != 0) {
                        this.f514e.setBounds(0, 0, 0, 0);
                    } else {
                        this.f514e.setBounds(this.f513d.getLeft(), this.f513d.getTop(), this.f513d.getRight(), this.f513d.getBottom());
                    }
                }
                z3 = true;
            }
            this.i = z4;
            if (!z4 || (drawable = this.f515f) == null) {
                z2 = z3;
            } else {
                drawable.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
        }
        if (z2) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int iA;
        int i3;
        if (this.f512c == null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE && (i3 = this.j) >= 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i3, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        if (this.f512c == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        View view = this.f511b;
        if (view == null || view.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        if (b(this.f512c)) {
            iA = !b(this.f513d) ? a(this.f513d) : 0;
        } else {
            iA = a(this.f512c);
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min(iA + a(this.f511b), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i2) : Integer.MAX_VALUE));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f514e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f514e);
        }
        this.f514e = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f512c;
            if (view != null) {
                this.f514e.setBounds(view.getLeft(), this.f512c.getTop(), this.f512c.getRight(), this.f512c.getBottom());
            }
        }
        boolean z = true;
        if (!this.h ? this.f514e != null || this.f515f != null : this.g != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.g;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.g);
        }
        this.g = drawable;
        boolean z = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.h && (drawable2 = this.g) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.h ? !(this.f514e != null || this.f515f != null) : this.g == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f515f;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f515f);
        }
        this.f515f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.i && (drawable2 = this.f515f) != null) {
                drawable2.setBounds(this.f511b.getLeft(), this.f511b.getTop(), this.f511b.getRight(), this.f511b.getBottom());
            }
        }
        boolean z = true;
        if (!this.h ? this.f514e != null || this.f515f != null : this.g != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f511b;
        if (view != null) {
            removeView(view);
        }
        this.f511b = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.f510a = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.f514e;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.f515f;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.g;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f514e && !this.h) || (drawable == this.f515f && this.i) || ((drawable == this.g && this.h) || super.verifyDrawable(drawable));
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        androidx.core.f.t.a(this, new a(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionBar);
        this.f514e = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActionBar_background);
        this.f515f = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActionBar_backgroundStacked);
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_height, -1);
        if (getId() == R$id.split_action_bar) {
            this.h = true;
            this.g = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActionBar_backgroundSplit);
        }
        typedArrayObtainStyledAttributes.recycle();
        boolean z = false;
        if (!this.h ? !(this.f514e != null || this.f515f != null) : this.g == null) {
            z = true;
        }
        setWillNotDraw(z);
    }
}
