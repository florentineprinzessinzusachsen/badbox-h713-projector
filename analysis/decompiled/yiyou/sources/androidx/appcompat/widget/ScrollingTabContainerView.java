package androidx.appcompat.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import com.blankj.utilcode.constant.MemoryConstants;

/* JADX INFO: loaded from: classes.dex */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Runnable f628a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f629b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LinearLayoutCompat f630c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Spinner f631d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f632e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f633f;
    int g;
    private int h;
    private int i;
    protected ViewPropertyAnimator j;

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f640a;

        a(View view) {
            this.f640a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ScrollingTabContainerView.this.smoothScrollTo(this.f640a.getLeft() - ((ScrollingTabContainerView.this.getWidth() - this.f640a.getWidth()) / 2), 0);
            ScrollingTabContainerView.this.f628a = null;
        }
    }

    private class b extends BaseAdapter {
        b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return ScrollingTabContainerView.this.f630c.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return ((TabView) ScrollingTabContainerView.this.f630c.getChildAt(i)).a();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return ScrollingTabContainerView.this.a((androidx.appcompat.app.a.c) getItem(i), true);
            }
            ((TabView) view).a((androidx.appcompat.app.a.c) getItem(i));
            return view;
        }
    }

    private class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((TabView) view).a().e();
            int childCount = ScrollingTabContainerView.this.f630c.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = ScrollingTabContainerView.this.f630c.getChildAt(i);
                childAt.setSelected(childAt == view);
            }
        }
    }

    protected class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f644a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f645b;

        protected d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f644a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f644a) {
                return;
            }
            ScrollingTabContainerView scrollingTabContainerView = ScrollingTabContainerView.this;
            scrollingTabContainerView.j = null;
            scrollingTabContainerView.setVisibility(this.f645b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.f644a = false;
        }
    }

    static {
        new DecelerateInterpolator();
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        new d();
        setHorizontalScrollBarEnabled(false);
        androidx.appcompat.d.a aVarA = androidx.appcompat.d.a.a(context);
        setContentHeight(aVarA.e());
        this.g = aVarA.d();
        this.f630c = b();
        addView(this.f630c, new ViewGroup.LayoutParams(-2, -1));
    }

    private Spinner a() {
        AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, R$attr.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    private LinearLayoutCompat b() {
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, R$attr.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.a(-2, -1));
        return linearLayoutCompat;
    }

    private boolean c() {
        Spinner spinner = this.f631d;
        return spinner != null && spinner.getParent() == this;
    }

    private void d() {
        if (c()) {
            return;
        }
        if (this.f631d == null) {
            this.f631d = a();
        }
        removeView(this.f630c);
        addView(this.f631d, new ViewGroup.LayoutParams(-2, -1));
        if (this.f631d.getAdapter() == null) {
            this.f631d.setAdapter((SpinnerAdapter) new b());
        }
        Runnable runnable = this.f628a;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.f628a = null;
        }
        this.f631d.setSelection(this.i);
    }

    private boolean e() {
        if (!c()) {
            return false;
        }
        removeView(this.f631d);
        addView(this.f630c, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f631d.getSelectedItemPosition());
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f628a;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        androidx.appcompat.d.a aVarA = androidx.appcompat.d.a.a(getContext());
        setContentHeight(aVarA.e());
        this.g = aVarA.d();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f628a;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ((TabView) view).a().e();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.f630c.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f633f = -1;
        } else {
            if (childCount > 2) {
                this.f633f = (int) (View.MeasureSpec.getSize(i) * 0.4f);
            } else {
                this.f633f = View.MeasureSpec.getSize(i) / 2;
            }
            this.f633f = Math.min(this.f633f, this.g);
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.h, MemoryConstants.GB);
        if (!z && this.f632e) {
            this.f630c.measure(0, iMakeMeasureSpec);
            if (this.f630c.getMeasuredWidth() > View.MeasureSpec.getSize(i)) {
                d();
            } else {
                e();
            }
        } else {
            e();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, iMakeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (!z || measuredWidth == measuredWidth2) {
            return;
        }
        setTabSelected(this.i);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z) {
        this.f632e = z;
    }

    public void setContentHeight(int i) {
        this.h = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.i = i;
        int childCount = this.f630c.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.f630c.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                a(i);
            }
            i2++;
        }
        Spinner spinner = this.f631d;
        if (spinner == null || i < 0) {
            return;
        }
        spinner.setSelection(i);
    }

    private class TabView extends LinearLayout {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int[] f634a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private androidx.appcompat.app.a.c f635b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private TextView f636c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ImageView f637d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private View f638e;

        public TabView(Context context, androidx.appcompat.app.a.c cVar, boolean z) {
            super(context, null, R$attr.actionBarTabStyle);
            this.f634a = new int[]{R.attr.background};
            this.f635b = cVar;
            d0 d0VarA = d0.a(context, null, this.f634a, R$attr.actionBarTabStyle, 0);
            if (d0VarA.g(0)) {
                setBackgroundDrawable(d0VarA.b(0));
            }
            d0VarA.a();
            if (z) {
                setGravity(8388627);
            }
            b();
        }

        public void a(androidx.appcompat.app.a.c cVar) {
            this.f635b = cVar;
            b();
        }

        public void b() {
            androidx.appcompat.app.a.c cVar = this.f635b;
            View viewB = cVar.b();
            if (viewB != null) {
                ViewParent parent = viewB.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewB);
                    }
                    addView(viewB);
                }
                this.f638e = viewB;
                TextView textView = this.f636c;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f637d;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f637d.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f638e;
            if (view != null) {
                removeView(view);
                this.f638e = null;
            }
            Drawable drawableC = cVar.c();
            CharSequence charSequenceD = cVar.d();
            if (drawableC != null) {
                if (this.f637d == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.f637d = appCompatImageView;
                }
                this.f637d.setImageDrawable(drawableC);
                this.f637d.setVisibility(0);
            } else {
                ImageView imageView2 = this.f637d;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.f637d.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(charSequenceD);
            if (z) {
                if (this.f636c == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, R$attr.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.f636c = appCompatTextView;
                }
                this.f636c.setText(charSequenceD);
                this.f636c.setVisibility(0);
            } else {
                TextView textView2 = this.f636c;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f636c.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.f637d;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.a());
            }
            f0.a(this, z ? null : cVar.a());
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (ScrollingTabContainerView.this.f633f > 0) {
                int measuredWidth = getMeasuredWidth();
                int i3 = ScrollingTabContainerView.this.f633f;
                if (measuredWidth > i3) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, MemoryConstants.GB), i2);
                }
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z) {
                sendAccessibilityEvent(4);
            }
        }

        public androidx.appcompat.app.a.c a() {
            return this.f635b;
        }
    }

    public void a(int i) {
        View childAt = this.f630c.getChildAt(i);
        Runnable runnable = this.f628a;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        this.f628a = new a(childAt);
        post(this.f628a);
    }

    TabView a(androidx.appcompat.app.a.c cVar, boolean z) {
        TabView tabView = new TabView(getContext(), cVar, z);
        if (z) {
            tabView.setBackgroundDrawable(null);
            tabView.setLayoutParams(new AbsListView.LayoutParams(-1, this.h));
        } else {
            tabView.setFocusable(true);
            if (this.f629b == null) {
                this.f629b = new c();
            }
            tabView.setOnClickListener(this.f629b);
        }
        return tabView;
    }
}
