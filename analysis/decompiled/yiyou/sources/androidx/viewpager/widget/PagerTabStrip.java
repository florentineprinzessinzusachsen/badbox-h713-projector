package androidx.viewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class PagerTabStrip extends PagerTitleStrip {
    private boolean A;
    private int B;
    private boolean C;
    private float D;
    private float F;
    private int G;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private final Paint w;
    private final Rect x;
    private int y;
    private boolean z;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewPager viewPager = PagerTabStrip.this.f1482a;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewPager viewPager = PagerTabStrip.this.f1482a;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    void a(int i, float f2, boolean z) {
        Rect rect = this.x;
        int height = getHeight();
        int left = this.f1484c.getLeft() - this.v;
        int right = this.f1484c.getRight() + this.v;
        int i2 = height - this.r;
        rect.set(left, i2, right, height);
        super.a(i, f2, z);
        this.y = (int) (Math.abs(f2 - 0.5f) * 2.0f * 255.0f);
        rect.union(this.f1484c.getLeft() - this.v, i2, this.f1484c.getRight() + this.v, height);
        invalidate(rect);
    }

    public boolean getDrawFullUnderline() {
        return this.z;
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    int getMinHeight() {
        return Math.max(super.getMinHeight(), this.u);
    }

    public int getTabIndicatorColor() {
        return this.q;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int left = this.f1484c.getLeft() - this.v;
        int right = this.f1484c.getRight() + this.v;
        int i = height - this.r;
        this.w.setColor((this.y << 24) | (this.q & 16777215));
        float f2 = height;
        canvas.drawRect(left, i, right, f2, this.w);
        if (this.z) {
            this.w.setColor((-16777216) | (this.q & 16777215));
            canvas.drawRect(getPaddingLeft(), height - this.B, getWidth() - getPaddingRight(), f2, this.w);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 && this.C) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (action == 0) {
            this.D = x;
            this.F = y;
            this.C = false;
        } else if (action != 1) {
            if (action == 2 && (Math.abs(x - this.D) > this.G || Math.abs(y - this.F) > this.G)) {
                this.C = true;
            }
        } else if (x < this.f1484c.getLeft() - this.v) {
            ViewPager viewPager = this.f1482a;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        } else if (x > this.f1484c.getRight() + this.v) {
            ViewPager viewPager2 = this.f1482a;
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        if (this.A) {
            return;
        }
        this.z = (i & (-16777216)) == 0;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.A) {
            return;
        }
        this.z = drawable == null;
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.A) {
            return;
        }
        this.z = i == 0;
    }

    public void setDrawFullUnderline(boolean z) {
        this.z = z;
        this.A = true;
        invalidate();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        int i5 = this.s;
        if (i4 < i5) {
            i4 = i5;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setTabIndicatorColor(int i) {
        this.q = i;
        this.w.setColor(this.q);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i) {
        setTabIndicatorColor(androidx.core.content.a.a(getContext(), i));
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int i) {
        int i2 = this.t;
        if (i < i2) {
            i = i2;
        }
        super.setTextSpacing(i);
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.w = new Paint();
        this.x = new Rect();
        this.y = 255;
        this.z = false;
        this.A = false;
        this.q = this.n;
        this.w.setColor(this.q);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.r = (int) ((3.0f * f2) + 0.5f);
        this.s = (int) ((6.0f * f2) + 0.5f);
        this.t = (int) (64.0f * f2);
        this.v = (int) ((16.0f * f2) + 0.5f);
        this.B = (int) ((1.0f * f2) + 0.5f);
        this.u = (int) ((f2 * 32.0f) + 0.5f);
        this.G = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.f1483b.setFocusable(true);
        this.f1483b.setOnClickListener(new a());
        this.f1485d.setFocusable(true);
        this.f1485d.setOnClickListener(new b());
        if (getBackground() == null) {
            this.z = true;
        }
    }
}
