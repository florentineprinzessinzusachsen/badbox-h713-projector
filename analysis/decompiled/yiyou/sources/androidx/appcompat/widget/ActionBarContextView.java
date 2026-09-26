package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import com.blankj.utilcode.constant.MemoryConstants;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends AbsActionBarView {
    private CharSequence i;
    private CharSequence j;
    private View k;
    private View l;
    private LinearLayout m;
    private TextView n;
    private TextView o;
    private int p;
    private int q;
    private boolean r;
    private int s;

    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ androidx.appcompat.d.b f516a;

        a(ActionBarContextView actionBarContextView, androidx.appcompat.d.b bVar) {
            this.f516a = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f516a.a();
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    private void e() {
        if (this.m == null) {
            LayoutInflater.from(getContext()).inflate(R$layout.abc_action_bar_title_item, this);
            this.m = (LinearLayout) getChildAt(getChildCount() - 1);
            this.n = (TextView) this.m.findViewById(R$id.action_bar_title);
            this.o = (TextView) this.m.findViewById(R$id.action_bar_subtitle);
            if (this.p != 0) {
                this.n.setTextAppearance(getContext(), this.p);
            }
            if (this.q != 0) {
                this.o.setTextAppearance(getContext(), this.q);
            }
        }
        this.n.setText(this.i);
        this.o.setText(this.j);
        boolean z = !TextUtils.isEmpty(this.i);
        boolean z2 = !TextUtils.isEmpty(this.j);
        int i = 0;
        this.o.setVisibility(z2 ? 0 : 8);
        LinearLayout linearLayout = this.m;
        if (!z && !z2) {
            i = 8;
        }
        linearLayout.setVisibility(i);
        if (this.m.getParent() == null) {
            addView(this.m);
        }
    }

    public boolean b() {
        return this.r;
    }

    public void c() {
        removeAllViews();
        this.l = null;
        this.f503c = null;
    }

    public boolean d() {
        ActionMenuPresenter actionMenuPresenter = this.f504d;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.i();
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.j;
    }

    public CharSequence getTitle() {
        return this.i;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.f504d;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.e();
            this.f504d.f();
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() != 32) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            return;
        }
        accessibilityEvent.setSource(this);
        accessibilityEvent.setClassName(ActionBarContextView.class.getName());
        accessibilityEvent.setPackageName(getContext().getPackageName());
        accessibilityEvent.setContentDescription(this.i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int iA;
        boolean zA = j0.a(this);
        int paddingRight = zA ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.k;
        if (view == null || view.getVisibility() == 8) {
            iA = paddingRight;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.k.getLayoutParams();
            int i5 = zA ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i6 = zA ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int iA2 = AbsActionBarView.a(paddingRight, i5, zA);
            iA = AbsActionBarView.a(iA2 + a(this.k, iA2, paddingTop, paddingTop2, zA), i6, zA);
        }
        LinearLayout linearLayout = this.m;
        if (linearLayout != null && this.l == null && linearLayout.getVisibility() != 8) {
            iA += a(this.m, iA, paddingTop, paddingTop2, zA);
        }
        int i7 = iA;
        View view2 = this.l;
        if (view2 != null) {
            a(view2, i7, paddingTop, paddingTop2, zA);
        }
        int paddingLeft = zA ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        ActionMenuView actionMenuView = this.f503c;
        if (actionMenuView != null) {
            a(actionMenuView, paddingLeft, paddingTop, paddingTop2, !zA);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int i3 = MemoryConstants.GB;
        if (mode != 1073741824) {
            throw new IllegalStateException(ActionBarContextView.class.getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View.MeasureSpec.getMode(i2) == 0) {
            throw new IllegalStateException(ActionBarContextView.class.getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
        int size = View.MeasureSpec.getSize(i);
        int size2 = this.f505e;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i2);
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingTop;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.k;
        if (view != null) {
            int iA = a(view, paddingLeft, iMakeMeasureSpec, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.k.getLayoutParams();
            paddingLeft = iA - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.f503c;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = a(this.f503c, paddingLeft, iMakeMeasureSpec, 0);
        }
        LinearLayout linearLayout = this.m;
        if (linearLayout != null && this.l == null) {
            if (this.r) {
                this.m.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.m.getMeasuredWidth();
                boolean z = measuredWidth <= paddingLeft;
                if (z) {
                    paddingLeft -= measuredWidth;
                }
                this.m.setVisibility(z ? 0 : 8);
            } else {
                paddingLeft = a(linearLayout, paddingLeft, iMakeMeasureSpec, 0);
            }
        }
        View view2 = this.l;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i4 = layoutParams.width != -2 ? MemoryConstants.GB : Integer.MIN_VALUE;
            int i5 = layoutParams.width;
            if (i5 >= 0) {
                paddingLeft = Math.min(i5, paddingLeft);
            }
            if (layoutParams.height == -2) {
                i3 = Integer.MIN_VALUE;
            }
            int i6 = layoutParams.height;
            if (i6 >= 0) {
                iMin = Math.min(i6, iMin);
            }
            this.l.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i4), View.MeasureSpec.makeMeasureSpec(iMin, i3));
        }
        if (this.f505e > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            int measuredHeight = getChildAt(i8).getMeasuredHeight() + paddingTop;
            if (measuredHeight > i7) {
                i7 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i7);
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public void setContentHeight(int i) {
        this.f505e = i;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.l;
        if (view2 != null) {
            removeView(view2);
        }
        this.l = view;
        if (view != null && (linearLayout = this.m) != null) {
            removeView(linearLayout);
            this.m = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.j = charSequence;
        e();
    }

    public void setTitle(CharSequence charSequence) {
        this.i = charSequence;
        e();
    }

    public void setTitleOptional(boolean z) {
        if (z != this.r) {
            requestLayout();
        }
        this.r = z;
    }

    @Override // androidx.appcompat.widget.AbsActionBarView, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.actionModeStyle);
    }

    public void a(androidx.appcompat.d.b bVar) {
        View view = this.k;
        if (view == null) {
            this.k = LayoutInflater.from(getContext()).inflate(this.s, (ViewGroup) this, false);
            addView(this.k);
        } else if (view.getParent() == null) {
            addView(this.k);
        }
        this.k.findViewById(R$id.action_mode_close_button).setOnClickListener(new a(this, bVar));
        androidx.appcompat.view.menu.g gVar = (androidx.appcompat.view.menu.g) bVar.c();
        ActionMenuPresenter actionMenuPresenter = this.f504d;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.c();
        }
        this.f504d = new ActionMenuPresenter(getContext());
        this.f504d.d(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        gVar.a(this.f504d, this.f502b);
        this.f503c = (ActionMenuView) this.f504d.b(this);
        androidx.core.f.t.a(this.f503c, (Drawable) null);
        addView(this.f503c, layoutParams);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d0 d0VarA = d0.a(context, attributeSet, R$styleable.ActionMode, i, 0);
        androidx.core.f.t.a(this, d0VarA.b(R$styleable.ActionMode_background));
        this.p = d0VarA.g(R$styleable.ActionMode_titleTextStyle, 0);
        this.q = d0VarA.g(R$styleable.ActionMode_subtitleTextStyle, 0);
        this.f505e = d0VarA.f(R$styleable.ActionMode_height, 0);
        this.s = d0VarA.g(R$styleable.ActionMode_closeItemLayout, R$layout.abc_action_mode_close_item_material);
        d0VarA.a();
    }

    public void a() {
        if (this.k == null) {
            c();
        }
    }
}
