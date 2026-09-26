package com.android.settingslib.drawable;

import android.R;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class UserIconDrawable extends Drawable implements Drawable.Callback {
    private Drawable mBadge;
    private float mBadgeMargin;
    private float mBadgeRadius;
    private Bitmap mBitmap;
    private Paint mClearPaint;
    private float mDisplayRadius;
    private ColorStateList mFrameColor;
    private float mFramePadding;
    private Paint mFramePaint;
    private float mFrameWidth;
    private final Matrix mIconMatrix;
    private final Paint mIconPaint;
    private float mIntrinsicRadius;
    private boolean mInvalidated;
    private float mPadding;
    private final Paint mPaint;
    private int mSize;
    private ColorStateList mTintColor;
    private PorterDuff.Mode mTintMode;
    private Drawable mUserDrawable;
    private Bitmap mUserIcon;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public static Drawable getManagedUserBadgeDrawable(Context context) {
        return context.getResources().getDrawableForDensity(R.drawable.dropdown_ic_arrow_pressed_holo_dark, context.getResources().getDisplayMetrics().densityDpi, context.getTheme());
    }

    public static int getSizeForList(Context context) {
        return (int) context.getResources().getDimension(com.android.settingslib.R.dimen.circle_avatar_size);
    }

    public UserIconDrawable() {
        this(0);
    }

    public UserIconDrawable(int i) {
        this.mIconPaint = new Paint();
        this.mPaint = new Paint();
        this.mIconMatrix = new Matrix();
        this.mPadding = 0.0f;
        this.mSize = 0;
        this.mInvalidated = true;
        this.mTintColor = null;
        this.mTintMode = PorterDuff.Mode.SRC_ATOP;
        this.mFrameColor = null;
        this.mIconPaint.setAntiAlias(true);
        this.mIconPaint.setFilterBitmap(true);
        this.mPaint.setFilterBitmap(true);
        this.mPaint.setAntiAlias(true);
        if (i > 0) {
            setBounds(0, 0, i, i);
            setIntrinsicSize(i);
        }
        setIcon(null);
    }

    public UserIconDrawable setIcon(Bitmap bitmap) {
        if (this.mUserDrawable != null) {
            this.mUserDrawable.setCallback(null);
            this.mUserDrawable = null;
        }
        this.mUserIcon = bitmap;
        if (this.mUserIcon == null) {
            this.mIconPaint.setShader(null);
            this.mBitmap = null;
        } else {
            this.mIconPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        }
        onBoundsChange(getBounds());
        return this;
    }

    public UserIconDrawable setIconDrawable(Drawable drawable) {
        if (this.mUserDrawable != null) {
            this.mUserDrawable.setCallback(null);
        }
        this.mUserIcon = null;
        this.mUserDrawable = drawable;
        if (this.mUserDrawable == null) {
            this.mBitmap = null;
        } else {
            this.mUserDrawable.setCallback(this);
        }
        onBoundsChange(getBounds());
        return this;
    }

    public UserIconDrawable setBadge(Drawable drawable) {
        this.mBadge = drawable;
        if (this.mBadge != null) {
            if (this.mClearPaint == null) {
                this.mClearPaint = new Paint();
                this.mClearPaint.setAntiAlias(true);
                this.mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                this.mClearPaint.setStyle(Paint.Style.FILL);
            }
            onBoundsChange(getBounds());
        } else {
            invalidateSelf();
        }
        return this;
    }

    public UserIconDrawable setBadgeIfManagedUser(Context context, int i) {
        return setBadge(((DevicePolicyManager) context.getSystemService(DevicePolicyManager.class)).getProfileOwnerAsUser(i) != null ? getManagedUserBadgeDrawable(context) : null);
    }

    public void setBadgeRadius(float f) {
        this.mBadgeRadius = f;
        onBoundsChange(getBounds());
    }

    public void setBadgeMargin(float f) {
        this.mBadgeMargin = f;
        onBoundsChange(getBounds());
    }

    public void setPadding(float f) {
        this.mPadding = f;
        onBoundsChange(getBounds());
    }

    private void initFramePaint() {
        if (this.mFramePaint == null) {
            this.mFramePaint = new Paint();
            this.mFramePaint.setStyle(Paint.Style.STROKE);
            this.mFramePaint.setAntiAlias(true);
        }
    }

    public void setFrameWidth(float f) {
        initFramePaint();
        this.mFrameWidth = f;
        this.mFramePaint.setStrokeWidth(f);
        onBoundsChange(getBounds());
    }

    public void setFramePadding(float f) {
        initFramePaint();
        this.mFramePadding = f;
        onBoundsChange(getBounds());
    }

    public void setFrameColor(int i) {
        initFramePaint();
        this.mFramePaint.setColor(i);
        invalidateSelf();
    }

    public void setFrameColor(ColorStateList colorStateList) {
        initFramePaint();
        this.mFrameColor = colorStateList;
        invalidateSelf();
    }

    public void setIntrinsicSize(int i) {
        this.mSize = i;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mInvalidated) {
            rebake();
        }
        if (this.mBitmap != null) {
            if (this.mTintColor == null) {
                this.mPaint.setColorFilter(null);
            } else {
                int colorForState = this.mTintColor.getColorForState(getState(), this.mTintColor.getDefaultColor());
                if (this.mPaint.getColorFilter() == null) {
                    this.mPaint.setColorFilter(new PorterDuffColorFilter(colorForState, this.mTintMode));
                } else {
                    ((PorterDuffColorFilter) this.mPaint.getColorFilter()).setMode(this.mTintMode);
                    ((PorterDuffColorFilter) this.mPaint.getColorFilter()).setColor(colorForState);
                }
            }
            canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, this.mPaint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.mTintColor = colorStateList;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.mTintMode = mode;
        super.invalidateSelf();
    }

    public UserIconDrawable bake() {
        if (this.mSize <= 0) {
            throw new IllegalStateException("Baking requires an explicit intrinsic size");
        }
        onBoundsChange(new Rect(0, 0, this.mSize, this.mSize));
        rebake();
        this.mFrameColor = null;
        this.mFramePaint = null;
        this.mClearPaint = null;
        if (this.mUserDrawable != null) {
            this.mUserDrawable.setCallback(null);
            this.mUserDrawable = null;
        } else if (this.mUserIcon != null) {
            this.mUserIcon.recycle();
            this.mUserIcon = null;
        }
        return this;
    }

    private void rebake() {
        this.mInvalidated = false;
        if (this.mBitmap != null) {
            if (this.mUserDrawable == null && this.mUserIcon == null) {
                return;
            }
            Canvas canvas = new Canvas(this.mBitmap);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            if (this.mUserDrawable != null) {
                this.mUserDrawable.draw(canvas);
            } else if (this.mUserIcon != null) {
                int iSave = canvas.save();
                canvas.concat(this.mIconMatrix);
                canvas.drawCircle(this.mUserIcon.getWidth() * 0.5f, this.mUserIcon.getHeight() * 0.5f, this.mIntrinsicRadius, this.mIconPaint);
                canvas.restoreToCount(iSave);
            }
            if (this.mFrameColor != null) {
                this.mFramePaint.setColor(this.mFrameColor.getColorForState(getState(), 0));
            }
            if (this.mFrameWidth + this.mFramePadding > 0.001f) {
                canvas.drawCircle(getBounds().exactCenterX(), getBounds().exactCenterY(), (this.mDisplayRadius - this.mPadding) - (this.mFrameWidth * 0.5f), this.mFramePaint);
            }
            if (this.mBadge == null || this.mBadgeRadius <= 0.001f) {
                return;
            }
            float f = this.mBadgeRadius * 2.0f;
            float height = this.mBitmap.getHeight() - f;
            float width = this.mBitmap.getWidth() - f;
            this.mBadge.setBounds((int) width, (int) height, (int) (width + f), (int) (f + height));
            canvas.drawCircle(width + this.mBadgeRadius, height + this.mBadgeRadius, (this.mBadge.getBounds().width() * 0.5f) + this.mBadgeMargin, this.mClearPaint);
            this.mBadge.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        if (rect.isEmpty()) {
            return;
        }
        if (this.mUserIcon == null && this.mUserDrawable == null) {
            return;
        }
        float fMin = Math.min(rect.width(), rect.height()) * 0.5f;
        int i = (int) (fMin * 2.0f);
        if (this.mBitmap == null || i != ((int) (this.mDisplayRadius * 2.0f))) {
            this.mDisplayRadius = fMin;
            if (this.mBitmap != null) {
                this.mBitmap.recycle();
            }
            this.mBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        }
        this.mDisplayRadius = Math.min(rect.width(), rect.height()) * 0.5f;
        float f = ((this.mDisplayRadius - this.mFrameWidth) - this.mFramePadding) - this.mPadding;
        RectF rectF = new RectF(rect.exactCenterX() - f, rect.exactCenterY() - f, rect.exactCenterX() + f, rect.exactCenterY() + f);
        if (this.mUserDrawable != null) {
            Rect rect2 = new Rect();
            rectF.round(rect2);
            this.mIntrinsicRadius = Math.min(this.mUserDrawable.getIntrinsicWidth(), this.mUserDrawable.getIntrinsicHeight()) * 0.5f;
            this.mUserDrawable.setBounds(rect2);
        } else if (this.mUserIcon != null) {
            float width = this.mUserIcon.getWidth() * 0.5f;
            float height = this.mUserIcon.getHeight() * 0.5f;
            this.mIntrinsicRadius = Math.min(width, height);
            this.mIconMatrix.setRectToRect(new RectF(width - this.mIntrinsicRadius, height - this.mIntrinsicRadius, width + this.mIntrinsicRadius, height + this.mIntrinsicRadius), rectF, Matrix.ScaleToFit.FILL);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        super.invalidateSelf();
        this.mInvalidated = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.mFrameColor != null && this.mFrameColor.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mSize <= 0 ? ((int) this.mIntrinsicRadius) * 2 : this.mSize;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
