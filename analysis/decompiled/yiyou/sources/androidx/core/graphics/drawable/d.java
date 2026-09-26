package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: WrappedDrawableApi14.java */
/* JADX INFO: loaded from: classes.dex */
class d extends Drawable implements Drawable.Callback, c, b {
    static final PorterDuff.Mode g = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1123a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PorterDuff.Mode f1124b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1125c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    f f1126d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1127e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Drawable f1128f;

    d(f fVar, Resources resources) {
        this.f1126d = fVar;
        a(resources);
    }

    private void a(Resources resources) {
        Drawable.ConstantState constantState;
        f fVar = this.f1126d;
        if (fVar == null || (constantState = fVar.f1130b) == null) {
            return;
        }
        a(constantState.newDrawable(resources));
    }

    private f c() {
        return new f(this.f1126d);
    }

    protected boolean b() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f1128f.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        f fVar = this.f1126d;
        return changingConfigurations | (fVar != null ? fVar.getChangingConfigurations() : 0) | this.f1128f.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        f fVar = this.f1126d;
        if (fVar == null || !fVar.a()) {
            return null;
        }
        this.f1126d.f1129a = getChangingConfigurations();
        return this.f1126d;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f1128f.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f1128f.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f1128f.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.f1128f.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.f1128f.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f1128f.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.f1128f.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.f1128f.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.f1128f.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.f1128f.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        f fVar;
        ColorStateList colorStateList = (!b() || (fVar = this.f1126d) == null) ? null : fVar.f1131c;
        return (colorStateList != null && colorStateList.isStateful()) || this.f1128f.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.f1128f.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f1127e && super.mutate() == this) {
            this.f1126d = c();
            Drawable drawable = this.f1128f;
            if (drawable != null) {
                drawable.mutate();
            }
            f fVar = this.f1126d;
            if (fVar != null) {
                Drawable drawable2 = this.f1128f;
                fVar.f1130b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.f1127e = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1128f;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        return this.f1128f.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f1128f.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        this.f1128f.setAutoMirrored(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i) {
        this.f1128f.setChangingConfigurations(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1128f.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.f1128f.setDither(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.f1128f.setFilterBitmap(z);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return a(iArr) || this.f1128f.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f1126d.f1131c = colorStateList;
        a(getState());
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f1126d.f1132d = mode;
        a(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f1128f.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    private boolean a(int[] iArr) {
        if (!b()) {
            return false;
        }
        f fVar = this.f1126d;
        ColorStateList colorStateList = fVar.f1131c;
        PorterDuff.Mode mode = fVar.f1132d;
        if (colorStateList != null && mode != null) {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!this.f1125c || colorForState != this.f1123a || mode != this.f1124b) {
                setColorFilter(colorForState, mode);
                this.f1123a = colorForState;
                this.f1124b = mode;
                this.f1125c = true;
                return true;
            }
        } else {
            this.f1125c = false;
            clearColorFilter();
        }
        return false;
    }

    d(Drawable drawable) {
        this.f1126d = c();
        a(drawable);
    }

    @Override // androidx.core.graphics.drawable.c
    public final Drawable a() {
        return this.f1128f;
    }

    @Override // androidx.core.graphics.drawable.c
    public final void a(Drawable drawable) {
        Drawable drawable2 = this.f1128f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1128f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            f fVar = this.f1126d;
            if (fVar != null) {
                fVar.f1130b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }
}
