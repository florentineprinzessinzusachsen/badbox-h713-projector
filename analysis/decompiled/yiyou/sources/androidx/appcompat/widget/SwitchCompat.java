package androidx.appcompat.widget;

import android.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import com.blankj.utilcode.constant.TimeConstants;

/* JADX INFO: loaded from: classes.dex */
public class SwitchCompat extends CompoundButton {
    private static final Property<SwitchCompat, Float> O = new a(Float.class, "thumbPos");
    private static final int[] P = {R.attr.state_checked};
    private int A;
    private int B;
    private int C;
    private int D;
    private int F;
    private final TextPaint G;
    private ColorStateList H;
    private Layout I;
    private Layout J;
    private TransformationMethod K;
    ObjectAnimator L;
    private final l M;
    private final Rect N;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Drawable f671a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ColorStateList f672b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PorterDuff.Mode f673c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f674d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f675e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Drawable f676f;
    private ColorStateList g;
    private PorterDuff.Mode h;
    private boolean i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private CharSequence o;
    private CharSequence p;
    private boolean q;
    private int r;
    private int s;
    private float t;
    private float u;
    private VelocityTracker v;
    private int w;
    float x;
    private int y;
    private int z;

    static class a extends Property<SwitchCompat, Float> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(SwitchCompat switchCompat) {
            return Float.valueOf(switchCompat.x);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(SwitchCompat switchCompat, Float f2) {
            switchCompat.setThumbPosition(f2.floatValue());
        }
    }

    public SwitchCompat(Context context) {
        this(context, null);
    }

    private static float a(float f2, float f3, float f4) {
        if (f2 < f3) {
            return f3;
        }
        return f2 > f4 ? f4 : f2;
    }

    private void b() {
        if (this.f676f != null) {
            if (this.i || this.j) {
                this.f676f = androidx.core.graphics.drawable.a.h(this.f676f).mutate();
                if (this.i) {
                    androidx.core.graphics.drawable.a.a(this.f676f, this.g);
                }
                if (this.j) {
                    androidx.core.graphics.drawable.a.a(this.f676f, this.h);
                }
                if (this.f676f.isStateful()) {
                    this.f676f.setState(getDrawableState());
                }
            }
        }
    }

    private void c() {
        ObjectAnimator objectAnimator = this.L;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    private boolean getTargetCheckedState() {
        return this.x > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((j0.a(this) ? 1.0f - this.x : this.x) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.f676f;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.N;
        drawable.getPadding(rect);
        Drawable drawable2 = this.f671a;
        Rect rectD = drawable2 != null ? p.d(drawable2) : p.f804c;
        return ((((this.y - this.A) - rect.left) - rect.right) - rectD.left) - rectD.right;
    }

    public void a(Context context, int i) {
        d0 d0VarA = d0.a(context, i, R$styleable.TextAppearance);
        ColorStateList colorStateListA = d0VarA.a(R$styleable.TextAppearance_android_textColor);
        if (colorStateListA != null) {
            this.H = colorStateListA;
        } else {
            this.H = getTextColors();
        }
        int iC = d0VarA.c(R$styleable.TextAppearance_android_textSize, 0);
        if (iC != 0) {
            float f2 = iC;
            if (f2 != this.G.getTextSize()) {
                this.G.setTextSize(f2);
                requestLayout();
            }
        }
        a(d0VarA.d(R$styleable.TextAppearance_android_typeface, -1), d0VarA.d(R$styleable.TextAppearance_android_textStyle, -1));
        if (d0VarA.a(R$styleable.TextAppearance_textAllCaps, false)) {
            this.K = new androidx.appcompat.c.a(getContext());
        } else {
            this.K = null;
        }
        d0VarA.a();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i;
        int i2;
        Rect rect = this.N;
        int i3 = this.B;
        int i4 = this.C;
        int i5 = this.D;
        int i6 = this.F;
        int thumbOffset = getThumbOffset() + i3;
        Drawable drawable = this.f671a;
        Rect rectD = drawable != null ? p.d(drawable) : p.f804c;
        Drawable drawable2 = this.f676f;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            int i7 = rect.left;
            thumbOffset += i7;
            if (rectD != null) {
                int i8 = rectD.left;
                if (i8 > i7) {
                    i3 += i8 - i7;
                }
                int i9 = rectD.top;
                int i10 = rect.top;
                i = i9 > i10 ? (i9 - i10) + i4 : i4;
                int i11 = rectD.right;
                int i12 = rect.right;
                if (i11 > i12) {
                    i5 -= i11 - i12;
                }
                int i13 = rectD.bottom;
                int i14 = rect.bottom;
                if (i13 > i14) {
                    i2 = i6 - (i13 - i14);
                }
                this.f676f.setBounds(i3, i, i5, i2);
            } else {
                i = i4;
            }
            i2 = i6;
            this.f676f.setBounds(i3, i, i5, i2);
        }
        Drawable drawable3 = this.f671a;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i15 = thumbOffset - rect.left;
            int i16 = thumbOffset + this.A + rect.right;
            this.f671a.setBounds(i15, i4, i16, i6);
            Drawable background = getBackground();
            if (background != null) {
                androidx.core.graphics.drawable.a.a(background, i15, i4, i16, i6);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableHotspotChanged(float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f2, f3);
        }
        Drawable drawable = this.f671a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, f2, f3);
        }
        Drawable drawable2 = this.f676f;
        if (drawable2 != null) {
            androidx.core.graphics.drawable.a.a(drawable2, f2, f3);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f671a;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.f676f;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (!j0.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.y;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.m : compoundPaddingLeft;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (j0.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.y;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.m : compoundPaddingRight;
    }

    public boolean getShowText() {
        return this.q;
    }

    public boolean getSplitTrack() {
        return this.n;
    }

    public int getSwitchMinWidth() {
        return this.l;
    }

    public int getSwitchPadding() {
        return this.m;
    }

    public CharSequence getTextOff() {
        return this.p;
    }

    public CharSequence getTextOn() {
        return this.o;
    }

    public Drawable getThumbDrawable() {
        return this.f671a;
    }

    public int getThumbTextPadding() {
        return this.k;
    }

    public ColorStateList getThumbTintList() {
        return this.f672b;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.f673c;
    }

    public Drawable getTrackDrawable() {
        return this.f676f;
    }

    public ColorStateList getTrackTintList() {
        return this.g;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.h;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f671a;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f676f;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.L;
        if (objectAnimator == null || !objectAnimator.isStarted()) {
            return;
        }
        this.L.end();
        this.L = null;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            CompoundButton.mergeDrawableStates(iArrOnCreateDrawableState, P);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        int width;
        super.onDraw(canvas);
        Rect rect = this.N;
        Drawable drawable = this.f676f;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i = this.C;
        int i2 = this.F;
        int i3 = i + rect.top;
        int i4 = i2 - rect.bottom;
        Drawable drawable2 = this.f671a;
        if (drawable != null) {
            if (!this.n || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect rectD = p.d(drawable2);
                drawable2.copyBounds(rect);
                rect.left += rectD.left;
                rect.right -= rectD.right;
                int iSave = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(iSave);
            }
        }
        int iSave2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.I : this.J;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.H;
            if (colorStateList != null) {
                this.G.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.G.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.left + bounds.right;
            } else {
                width = getWidth();
            }
            canvas.translate((width / 2) - (layout.getWidth() / 2), ((i3 + i4) / 2) - (layout.getHeight() / 2));
            layout.draw(canvas);
        }
        canvas.restoreToCount(iSave2);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        CharSequence charSequence = isChecked() ? this.o : this.p;
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        CharSequence text = accessibilityNodeInfo.getText();
        if (TextUtils.isEmpty(text)) {
            accessibilityNodeInfo.setText(charSequence);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(text);
        sb.append(' ');
        sb.append(charSequence);
        accessibilityNodeInfo.setText(sb);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int iMax;
        int width;
        int paddingLeft;
        int i5;
        int paddingTop;
        int height;
        super.onLayout(z, i, i2, i3, i4);
        int iMax2 = 0;
        if (this.f671a != null) {
            Rect rect = this.N;
            Drawable drawable = this.f676f;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect rectD = p.d(this.f671a);
            iMax = Math.max(0, rectD.left - rect.left);
            iMax2 = Math.max(0, rectD.right - rect.right);
        } else {
            iMax = 0;
        }
        if (j0.a(this)) {
            paddingLeft = getPaddingLeft() + iMax;
            width = ((this.y + paddingLeft) - iMax) - iMax2;
        } else {
            width = (getWidth() - getPaddingRight()) - iMax2;
            paddingLeft = (width - this.y) + iMax + iMax2;
        }
        int gravity = getGravity() & 112;
        if (gravity != 16) {
            if (gravity != 80) {
                paddingTop = getPaddingTop();
                i5 = this.z;
            } else {
                height = getHeight() - getPaddingBottom();
                paddingTop = height - this.z;
            }
            this.B = paddingLeft;
            this.C = paddingTop;
            this.F = height;
            this.D = width;
        }
        int paddingTop2 = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2;
        i5 = this.z;
        paddingTop = paddingTop2 - (i5 / 2);
        height = i5 + paddingTop;
        this.B = paddingLeft;
        this.C = paddingTop;
        this.F = height;
        this.D = width;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        int intrinsicWidth;
        int intrinsicHeight;
        if (this.q) {
            if (this.I == null) {
                this.I = a(this.o);
            }
            if (this.J == null) {
                this.J = a(this.p);
            }
        }
        Rect rect = this.N;
        Drawable drawable = this.f671a;
        int intrinsicHeight2 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            intrinsicWidth = (this.f671a.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.f671a.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
            intrinsicHeight = 0;
        }
        this.A = Math.max(this.q ? Math.max(this.I.getWidth(), this.J.getWidth()) + (this.k * 2) : 0, intrinsicWidth);
        Drawable drawable2 = this.f676f;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            intrinsicHeight2 = this.f676f.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int iMax = rect.left;
        int iMax2 = rect.right;
        Drawable drawable3 = this.f671a;
        if (drawable3 != null) {
            Rect rectD = p.d(drawable3);
            iMax = Math.max(iMax, rectD.left);
            iMax2 = Math.max(iMax2, rectD.right);
        }
        int iMax3 = Math.max(this.l, (this.A * 2) + iMax + iMax2);
        int iMax4 = Math.max(intrinsicHeight2, intrinsicHeight);
        this.y = iMax3;
        this.z = iMax4;
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < iMax4) {
            setMeasuredDimension(getMeasuredWidthAndState(), iMax4);
        }
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.o : this.p;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x008d  */
    /* JADX WARN: Code duplicated, block: B:37:0x0091  */
    /* JADX WARN: Code duplicated, block: B:39:0x0098  */
    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f2;
        this.v.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (isEnabled() && a(x, y)) {
                this.r = 1;
                this.t = x;
                this.u = y;
            }
        } else if (actionMasked == 1) {
            if (this.r == 2) {
                b(motionEvent);
                super.onTouchEvent(motionEvent);
                return true;
            }
            this.r = 0;
            this.v.clear();
        } else if (actionMasked == 2) {
            int i = this.r;
            if (i != 0) {
                if (i == 1) {
                    float x2 = motionEvent.getX();
                    float y2 = motionEvent.getY();
                    if (Math.abs(x2 - this.t) > this.s || Math.abs(y2 - this.u) > this.s) {
                        this.r = 2;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        this.t = x2;
                        this.u = y2;
                        return true;
                    }
                } else if (i == 2) {
                    float x3 = motionEvent.getX();
                    int thumbScrollRange = getThumbScrollRange();
                    float f3 = x3 - this.t;
                    if (thumbScrollRange != 0) {
                        f2 = f3 / thumbScrollRange;
                    } else {
                        f2 = f3 > 0.0f ? 1.0f : -1.0f;
                    }
                    if (j0.a(this)) {
                        f2 = -f2;
                    }
                    float fA = a(this.x + f2, 0.0f, 1.0f);
                    if (fA != this.x) {
                        this.t = x3;
                        setThumbPosition(fA);
                    }
                    return true;
                }
            }
        } else if (actionMasked == 3) {
            if (this.r == 2) {
                b(motionEvent);
                super.onTouchEvent(motionEvent);
                return true;
            }
            this.r = 0;
            this.v.clear();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        super.setChecked(z);
        boolean zIsChecked = isChecked();
        if (getWindowToken() != null && androidx.core.f.t.r(this)) {
            a(zIsChecked);
        } else {
            c();
            setThumbPosition(zIsChecked ? 1.0f : 0.0f);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.a(this, callback));
    }

    public void setShowText(boolean z) {
        if (this.q != z) {
            this.q = z;
            requestLayout();
        }
    }

    public void setSplitTrack(boolean z) {
        this.n = z;
        invalidate();
    }

    public void setSwitchMinWidth(int i) {
        this.l = i;
        requestLayout();
    }

    public void setSwitchPadding(int i) {
        this.m = i;
        requestLayout();
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.G.getTypeface() == null || this.G.getTypeface().equals(typeface)) && (this.G.getTypeface() != null || typeface == null)) {
            return;
        }
        this.G.setTypeface(typeface);
        requestLayout();
        invalidate();
    }

    public void setTextOff(CharSequence charSequence) {
        this.p = charSequence;
        requestLayout();
    }

    public void setTextOn(CharSequence charSequence) {
        this.o = charSequence;
        requestLayout();
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.f671a;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f671a = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    void setThumbPosition(float f2) {
        this.x = f2;
        invalidate();
    }

    public void setThumbResource(int i) {
        setThumbDrawable(androidx.appcompat.a.a.a.c(getContext(), i));
    }

    public void setThumbTextPadding(int i) {
        this.k = i;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.f672b = colorStateList;
        this.f674d = true;
        a();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.f673c = mode;
        this.f675e = true;
        a();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.f676f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f676f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i) {
        setTrackDrawable(androidx.appcompat.a.a.a.c(getContext(), i));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.g = colorStateList;
        this.i = true;
        b();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.h = mode;
        this.j = true;
        b();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f671a || drawable == this.f676f;
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f672b = null;
        this.f673c = null;
        this.f674d = false;
        this.f675e = false;
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = false;
        this.v = VelocityTracker.obtain();
        this.N = new Rect();
        this.G = new TextPaint(1);
        Resources resources = getResources();
        this.G.density = resources.getDisplayMetrics().density;
        d0 d0VarA = d0.a(context, attributeSet, R$styleable.SwitchCompat, i, 0);
        this.f671a = d0VarA.b(R$styleable.SwitchCompat_android_thumb);
        Drawable drawable = this.f671a;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.f676f = d0VarA.b(R$styleable.SwitchCompat_track);
        Drawable drawable2 = this.f676f;
        if (drawable2 != null) {
            drawable2.setCallback(this);
        }
        this.o = d0VarA.e(R$styleable.SwitchCompat_android_textOn);
        this.p = d0VarA.e(R$styleable.SwitchCompat_android_textOff);
        this.q = d0VarA.a(R$styleable.SwitchCompat_showText, true);
        this.k = d0VarA.c(R$styleable.SwitchCompat_thumbTextPadding, 0);
        this.l = d0VarA.c(R$styleable.SwitchCompat_switchMinWidth, 0);
        this.m = d0VarA.c(R$styleable.SwitchCompat_switchPadding, 0);
        this.n = d0VarA.a(R$styleable.SwitchCompat_splitTrack, false);
        ColorStateList colorStateListA = d0VarA.a(R$styleable.SwitchCompat_thumbTint);
        if (colorStateListA != null) {
            this.f672b = colorStateListA;
            this.f674d = true;
        }
        PorterDuff.Mode modeA = p.a(d0VarA.d(R$styleable.SwitchCompat_thumbTintMode, -1), null);
        if (this.f673c != modeA) {
            this.f673c = modeA;
            this.f675e = true;
        }
        if (this.f674d || this.f675e) {
            a();
        }
        ColorStateList colorStateListA2 = d0VarA.a(R$styleable.SwitchCompat_trackTint);
        if (colorStateListA2 != null) {
            this.g = colorStateListA2;
            this.i = true;
        }
        PorterDuff.Mode modeA2 = p.a(d0VarA.d(R$styleable.SwitchCompat_trackTintMode, -1), null);
        if (this.h != modeA2) {
            this.h = modeA2;
            this.j = true;
        }
        if (this.i || this.j) {
            b();
        }
        int iG = d0VarA.g(R$styleable.SwitchCompat_switchTextAppearance, 0);
        if (iG != 0) {
            a(context, iG);
        }
        this.M = new l(this);
        this.M.a(attributeSet, i);
        d0VarA.a();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.s = viewConfiguration.getScaledTouchSlop();
        this.w = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    private void b(MotionEvent motionEvent) {
        boolean targetCheckedState;
        this.r = 0;
        boolean z = true;
        boolean z2 = motionEvent.getAction() == 1 && isEnabled();
        boolean zIsChecked = isChecked();
        if (z2) {
            this.v.computeCurrentVelocity(TimeConstants.SEC);
            float xVelocity = this.v.getXVelocity();
            if (Math.abs(xVelocity) > this.w) {
                if (!j0.a(this) ? xVelocity <= 0.0f : xVelocity >= 0.0f) {
                    z = false;
                }
                targetCheckedState = z;
            } else {
                targetCheckedState = getTargetCheckedState();
            }
        } else {
            targetCheckedState = zIsChecked;
        }
        if (targetCheckedState != zIsChecked) {
            playSoundEffect(0);
        }
        setChecked(targetCheckedState);
        a(motionEvent);
    }

    private void a(int i, int i2) {
        Typeface typeface;
        if (i == 1) {
            typeface = Typeface.SANS_SERIF;
        } else if (i != 2) {
            typeface = i != 3 ? null : Typeface.MONOSPACE;
        } else {
            typeface = Typeface.SERIF;
        }
        a(typeface, i2);
    }

    public void a(Typeface typeface, int i) {
        Typeface typefaceCreate;
        if (i > 0) {
            if (typeface == null) {
                typefaceCreate = Typeface.defaultFromStyle(i);
            } else {
                typefaceCreate = Typeface.create(typeface, i);
            }
            setSwitchTypeface(typefaceCreate);
            int style = ((typefaceCreate != null ? typefaceCreate.getStyle() : 0) ^ (-1)) & i;
            this.G.setFakeBoldText((style & 1) != 0);
            this.G.setTextSkewX((style & 2) != 0 ? -0.25f : 0.0f);
            return;
        }
        this.G.setFakeBoldText(false);
        this.G.setTextSkewX(0.0f);
        setSwitchTypeface(typeface);
    }

    private void a() {
        if (this.f671a != null) {
            if (this.f674d || this.f675e) {
                this.f671a = androidx.core.graphics.drawable.a.h(this.f671a).mutate();
                if (this.f674d) {
                    androidx.core.graphics.drawable.a.a(this.f671a, this.f672b);
                }
                if (this.f675e) {
                    androidx.core.graphics.drawable.a.a(this.f671a, this.f673c);
                }
                if (this.f671a.isStateful()) {
                    this.f671a.setState(getDrawableState());
                }
            }
        }
    }

    private Layout a(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.K;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        CharSequence charSequence2 = charSequence;
        TextPaint textPaint = this.G;
        return new StaticLayout(charSequence2, textPaint, charSequence2 != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence2, textPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    private boolean a(float f2, float f3) {
        if (this.f671a == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.f671a.getPadding(this.N);
        int i = this.C;
        int i2 = this.s;
        int i3 = i - i2;
        int i4 = (this.B + thumbOffset) - i2;
        int i5 = this.A + i4;
        Rect rect = this.N;
        return f2 > ((float) i4) && f2 < ((float) (((i5 + rect.left) + rect.right) + i2)) && f3 > ((float) i3) && f3 < ((float) (this.F + i2));
    }

    private void a(MotionEvent motionEvent) {
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.setAction(3);
        super.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    private void a(boolean z) {
        this.L = ObjectAnimator.ofFloat(this, O, z ? 1.0f : 0.0f);
        this.L.setDuration(250L);
        if (Build.VERSION.SDK_INT >= 18) {
            this.L.setAutoCancel(true);
        }
        this.L.start();
    }
}
