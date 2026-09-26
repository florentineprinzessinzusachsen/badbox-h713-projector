package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.core.widget.i;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.e
public class PagerTitleStrip extends ViewGroup {
    private static final int[] o = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};
    private static final int[] p = {R.attr.textAllCaps};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ViewPager f1482a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    TextView f1483b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    TextView f1484c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    TextView f1485d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1486e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    float f1487f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private final a k;
    private WeakReference<androidx.viewpager.widget.a> l;
    private int m;
    int n;

    private class a extends DataSetObserver implements ViewPager.j, ViewPager.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f1488a;

        a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i, float f2, int i2) {
            if (f2 > 0.5f) {
                i++;
            }
            PagerTitleStrip.this.a(i, f2, false);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void b(int i) {
            if (this.f1488a == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.a(pagerTitleStrip.f1482a.getCurrentItem(), PagerTitleStrip.this.f1482a.getAdapter());
                float f2 = PagerTitleStrip.this.f1487f;
                if (f2 < 0.0f) {
                    f2 = 0.0f;
                }
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                pagerTitleStrip2.a(pagerTitleStrip2.f1482a.getCurrentItem(), f2, true);
            }
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.a(pagerTitleStrip.f1482a.getCurrentItem(), PagerTitleStrip.this.f1482a.getAdapter());
            float f2 = PagerTitleStrip.this.f1487f;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            pagerTitleStrip2.a(pagerTitleStrip2.f1482a.getCurrentItem(), f2, true);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i) {
            this.f1488a = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void a(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2) {
            PagerTitleStrip.this.a(aVar, aVar2);
        }
    }

    private static class b extends SingleLineTransformationMethod {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Locale f1490a;

        b(Context context) {
            this.f1490a = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.f1490a);
            }
            return null;
        }
    }

    public PagerTitleStrip(Context context) {
        this(context, null);
    }

    private static void setSingleLineAllCaps(TextView textView) {
        textView.setTransformationMethod(new b(textView.getContext()));
    }

    public void a(int i, float f2) {
        this.f1483b.setTextSize(i, f2);
        this.f1484c.setTextSize(i, f2);
        this.f1485d.setTextSize(i, f2);
    }

    int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.g;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        androidx.viewpager.widget.a adapter = viewPager.getAdapter();
        viewPager.a((ViewPager.j) this.k);
        viewPager.a((ViewPager.i) this.k);
        this.f1482a = viewPager;
        WeakReference<androidx.viewpager.widget.a> weakReference = this.l;
        a(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.f1482a;
        if (viewPager != null) {
            a(viewPager.getAdapter(), (androidx.viewpager.widget.a) null);
            this.f1482a.a((ViewPager.j) null);
            this.f1482a.b(this.k);
            this.f1482a = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f1482a != null) {
            float f2 = this.f1487f;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            a(this.f1486e, f2, true);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int iMax;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
        int size = View.MeasureSpec.getSize(i);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i, (int) (size * 0.2f), -2);
        this.f1483b.measure(childMeasureSpec2, childMeasureSpec);
        this.f1484c.measure(childMeasureSpec2, childMeasureSpec);
        this.f1485d.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            iMax = View.MeasureSpec.getSize(i2);
        } else {
            iMax = Math.max(getMinHeight(), this.f1484c.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(iMax, i2, this.f1484c.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.i) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i) {
        this.h = i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f2) {
        this.m = ((int) (f2 * 255.0f)) & 255;
        int i = (this.m << 24) | (this.n & 16777215);
        this.f1483b.setTextColor(i);
        this.f1485d.setTextColor(i);
    }

    public void setTextColor(int i) {
        this.n = i;
        this.f1484c.setTextColor(i);
        int i2 = (this.m << 24) | (this.n & 16777215);
        this.f1483b.setTextColor(i2);
        this.f1485d.setTextColor(i2);
    }

    public void setTextSpacing(int i) {
        this.g = i;
        requestLayout();
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1486e = -1;
        this.f1487f = -1.0f;
        this.k = new a();
        TextView textView = new TextView(context);
        this.f1483b = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.f1484c = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.f1485d = textView3;
        addView(textView3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o);
        boolean z = false;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            i.d(this.f1483b, resourceId);
            i.d(this.f1484c, resourceId);
            i.d(this.f1485d, resourceId);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            a(0, dimensionPixelSize);
        }
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            int color = typedArrayObtainStyledAttributes.getColor(2, 0);
            this.f1483b.setTextColor(color);
            this.f1484c.setTextColor(color);
            this.f1485d.setTextColor(color);
        }
        this.h = typedArrayObtainStyledAttributes.getInteger(3, 80);
        typedArrayObtainStyledAttributes.recycle();
        this.n = this.f1484c.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.f1483b.setEllipsize(TextUtils.TruncateAt.END);
        this.f1484c.setEllipsize(TextUtils.TruncateAt.END);
        this.f1485d.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, p);
            z = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (z) {
            setSingleLineAllCaps(this.f1483b);
            setSingleLineAllCaps(this.f1484c);
            setSingleLineAllCaps(this.f1485d);
        } else {
            this.f1483b.setSingleLine();
            this.f1484c.setSingleLine();
            this.f1485d.setSingleLine();
        }
        this.g = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }

    void a(int i, androidx.viewpager.widget.a aVar) {
        int iA = aVar != null ? aVar.a() : 0;
        this.i = true;
        CharSequence charSequenceA = null;
        this.f1483b.setText((i < 1 || aVar == null) ? null : aVar.a(i - 1));
        this.f1484c.setText((aVar == null || i >= iA) ? null : aVar.a(i));
        int i2 = i + 1;
        if (i2 < iA && aVar != null) {
            charSequenceA = aVar.a(i2);
        }
        this.f1485d.setText(charSequenceA);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.f1483b.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1484c.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1485d.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1486e = i;
        if (!this.j) {
            a(i, this.f1487f, false);
        }
        this.i = false;
    }

    void a(androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2) {
        if (aVar != null) {
            aVar.c(this.k);
            this.l = null;
        }
        if (aVar2 != null) {
            aVar2.a((DataSetObserver) this.k);
            this.l = new WeakReference<>(aVar2);
        }
        ViewPager viewPager = this.f1482a;
        if (viewPager != null) {
            this.f1486e = -1;
            this.f1487f = -1.0f;
            a(viewPager.getCurrentItem(), aVar2);
            requestLayout();
        }
    }

    void a(int i, float f2, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (i != this.f1486e) {
            a(i, this.f1482a.getAdapter());
        } else if (!z && f2 == this.f1487f) {
            return;
        }
        this.j = true;
        int measuredWidth = this.f1483b.getMeasuredWidth();
        int measuredWidth2 = this.f1484c.getMeasuredWidth();
        int measuredWidth3 = this.f1485d.getMeasuredWidth();
        int i6 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i7 = paddingRight + i6;
        int i8 = (width - (paddingLeft + i6)) - i7;
        float f3 = 0.5f + f2;
        if (f3 > 1.0f) {
            f3 -= 1.0f;
        }
        int i9 = ((width - i7) - ((int) (i8 * f3))) - i6;
        int i10 = measuredWidth2 + i9;
        int baseline = this.f1483b.getBaseline();
        int baseline2 = this.f1484c.getBaseline();
        int baseline3 = this.f1485d.getBaseline();
        int iMax = Math.max(Math.max(baseline, baseline2), baseline3);
        int i11 = iMax - baseline;
        int i12 = iMax - baseline2;
        int i13 = iMax - baseline3;
        int iMax2 = Math.max(Math.max(this.f1483b.getMeasuredHeight() + i11, this.f1484c.getMeasuredHeight() + i12), this.f1485d.getMeasuredHeight() + i13);
        int i14 = this.h & 112;
        if (i14 == 16) {
            i2 = (((height - paddingTop) - paddingBottom) - iMax2) / 2;
        } else {
            if (i14 != 80) {
                i3 = i11 + paddingTop;
                i4 = i12 + paddingTop;
                i5 = paddingTop + i13;
            } else {
                i2 = (height - paddingBottom) - iMax2;
            }
            TextView textView = this.f1484c;
            textView.layout(i9, i4, i10, textView.getMeasuredHeight() + i4);
            int iMin = Math.min(paddingLeft, (i9 - this.g) - measuredWidth);
            TextView textView2 = this.f1483b;
            textView2.layout(iMin, i3, measuredWidth + iMin, textView2.getMeasuredHeight() + i3);
            int iMax3 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.g);
            TextView textView3 = this.f1485d;
            textView3.layout(iMax3, i5, iMax3 + measuredWidth3, textView3.getMeasuredHeight() + i5);
            this.f1487f = f2;
            this.j = false;
        }
        i3 = i11 + i2;
        i4 = i12 + i2;
        i5 = i2 + i13;
        TextView textView4 = this.f1484c;
        textView4.layout(i9, i4, i10, textView4.getMeasuredHeight() + i4);
        int iMin2 = Math.min(paddingLeft, (i9 - this.g) - measuredWidth);
        TextView textView5 = this.f1483b;
        textView5.layout(iMin2, i3, measuredWidth + iMin2, textView5.getMeasuredHeight() + i3);
        int iMax4 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.g);
        TextView textView6 = this.f1485d;
        textView6.layout(iMax4, i5, iMax4 + measuredWidth3, textView6.getMeasuredHeight() + i5);
        this.f1487f = f2;
        this.j = false;
    }
}
