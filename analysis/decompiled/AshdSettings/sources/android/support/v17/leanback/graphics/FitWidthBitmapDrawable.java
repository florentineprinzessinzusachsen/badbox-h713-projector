package android.support.v17.leanback.graphics;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.IntProperty;
import android.util.Property;

/* JADX INFO: loaded from: classes.dex */
public class FitWidthBitmapDrawable extends Drawable {
    public static final Property<FitWidthBitmapDrawable, Integer> PROPERTY_VERTICAL_OFFSET;
    BitmapState mBitmapState;
    final Rect mDest;
    boolean mMutated;

    static class BitmapState extends Drawable.ConstantState {
        Bitmap mBitmap;
        final Rect mDefaultSource;
        int mOffset;
        Paint mPaint;
        Rect mSource;

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        BitmapState() {
            this.mDefaultSource = new Rect();
            this.mPaint = new Paint();
        }

        BitmapState(BitmapState bitmapState) {
            this.mDefaultSource = new Rect();
            this.mBitmap = bitmapState.mBitmap;
            this.mPaint = new Paint(bitmapState.mPaint);
            this.mSource = bitmapState.mSource != null ? new Rect(bitmapState.mSource) : null;
            this.mDefaultSource.set(bitmapState.mDefaultSource);
            this.mOffset = bitmapState.mOffset;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new FitWidthBitmapDrawable(this);
        }
    }

    public FitWidthBitmapDrawable() {
        this.mDest = new Rect();
        this.mMutated = false;
        this.mBitmapState = new BitmapState();
    }

    FitWidthBitmapDrawable(BitmapState bitmapState) {
        this.mDest = new Rect();
        this.mMutated = false;
        this.mBitmapState = bitmapState;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.mBitmapState;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mBitmapState = new BitmapState(this.mBitmapState);
            this.mMutated = true;
        }
        return this;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmapState.mBitmap = bitmap;
        if (bitmap != null) {
            this.mBitmapState.mDefaultSource.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        } else {
            this.mBitmapState.mDefaultSource.set(0, 0, 0, 0);
        }
        this.mBitmapState.mSource = null;
    }

    public Bitmap getBitmap() {
        return this.mBitmapState.mBitmap;
    }

    public void setSource(Rect rect) {
        this.mBitmapState.mSource = rect;
    }

    public Rect getSource() {
        return this.mBitmapState.mSource;
    }

    public void setVerticalOffset(int i) {
        this.mBitmapState.mOffset = i;
        invalidateSelf();
    }

    public int getVerticalOffset() {
        return this.mBitmapState.mOffset;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mBitmapState.mBitmap != null) {
            Rect bounds = getBounds();
            this.mDest.left = 0;
            this.mDest.top = this.mBitmapState.mOffset;
            this.mDest.right = bounds.width();
            Rect rectValidateSource = validateSource();
            this.mDest.bottom = this.mDest.top + ((int) (rectValidateSource.height() * (bounds.width() / rectValidateSource.width())));
            int iSave = canvas.save();
            canvas.clipRect(bounds);
            canvas.drawBitmap(this.mBitmapState.mBitmap, rectValidateSource, this.mDest, this.mBitmapState.mPaint);
            canvas.restoreToCount(iSave);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i != this.mBitmapState.mPaint.getAlpha()) {
            this.mBitmapState.mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mBitmapState.mPaint.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mBitmapState.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Bitmap bitmap = this.mBitmapState.mBitmap;
        return (bitmap == null || bitmap.hasAlpha() || this.mBitmapState.mPaint.getAlpha() < 255) ? -3 : -1;
    }

    private Rect validateSource() {
        if (this.mBitmapState.mSource == null) {
            return this.mBitmapState.mDefaultSource;
        }
        return this.mBitmapState.mSource;
    }

    static {
        if (Build.VERSION.SDK_INT >= 24) {
            PROPERTY_VERTICAL_OFFSET = getVerticalOffsetIntProperty();
        } else {
            PROPERTY_VERTICAL_OFFSET = new Property<FitWidthBitmapDrawable, Integer>(Integer.class, "verticalOffset") { // from class: android.support.v17.leanback.graphics.FitWidthBitmapDrawable.1
                @Override // android.util.Property
                public void set(FitWidthBitmapDrawable fitWidthBitmapDrawable, Integer num) {
                    fitWidthBitmapDrawable.setVerticalOffset(num.intValue());
                }

                @Override // android.util.Property
                public Integer get(FitWidthBitmapDrawable fitWidthBitmapDrawable) {
                    return Integer.valueOf(fitWidthBitmapDrawable.getVerticalOffset());
                }
            };
        }
    }

    @TargetApi(24)
    static IntProperty<FitWidthBitmapDrawable> getVerticalOffsetIntProperty() {
        return new IntProperty<FitWidthBitmapDrawable>("verticalOffset") { // from class: android.support.v17.leanback.graphics.FitWidthBitmapDrawable.2
            @Override // android.util.IntProperty
            public void setValue(FitWidthBitmapDrawable fitWidthBitmapDrawable, int i) {
                fitWidthBitmapDrawable.setVerticalOffset(i);
            }

            @Override // android.util.Property
            public Integer get(FitWidthBitmapDrawable fitWidthBitmapDrawable) {
                return Integer.valueOf(fitWidthBitmapDrawable.getVerticalOffset());
            }
        };
    }
}
