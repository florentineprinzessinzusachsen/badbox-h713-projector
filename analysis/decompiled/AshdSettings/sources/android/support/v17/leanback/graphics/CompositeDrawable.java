package android.support.v17.leanback.graphics;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Property;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class CompositeDrawable extends Drawable implements Drawable.Callback {
    boolean mMutated;
    CompositeState mState;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    static class CompositeState extends Drawable.ConstantState {
        final ArrayList<ChildDrawable> mChildren;

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        CompositeState() {
            this.mChildren = new ArrayList<>();
        }

        CompositeState(CompositeState compositeState, CompositeDrawable compositeDrawable, Resources resources) {
            int size = compositeState.mChildren.size();
            this.mChildren = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                this.mChildren.add(new ChildDrawable(compositeState.mChildren.get(i), compositeDrawable, resources));
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new CompositeDrawable(this);
        }
    }

    public CompositeDrawable() {
        this.mMutated = false;
        this.mState = new CompositeState();
    }

    CompositeDrawable(CompositeState compositeState) {
        this.mMutated = false;
        this.mState = compositeState;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.mState;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mState = new CompositeState(this.mState, this, null);
            ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Drawable drawable = arrayList.get(i).mDrawable;
                if (drawable != null) {
                    drawable.mutate();
                }
            }
            this.mMutated = true;
        }
        return this;
    }

    public void addChildDrawable(Drawable drawable) {
        this.mState.mChildren.add(new ChildDrawable(drawable, this));
    }

    public void setChildDrawableAt(int i, Drawable drawable) {
        this.mState.mChildren.set(i, new ChildDrawable(drawable, this));
    }

    public Drawable getDrawable(int i) {
        return this.mState.mChildren.get(i).mDrawable;
    }

    public ChildDrawable getChildAt(int i) {
        return this.mState.mChildren.get(i);
    }

    public void removeChild(int i) {
        this.mState.mChildren.remove(i);
    }

    public void removeDrawable(Drawable drawable) {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        for (int i = 0; i < arrayList.size(); i++) {
            if (drawable == arrayList.get(i).mDrawable) {
                arrayList.get(i).mDrawable.setCallback(null);
                arrayList.remove(i);
                return;
            }
        }
    }

    public int getChildCount() {
        return this.mState.mChildren.size();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).mDrawable.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updateBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).mDrawable.setColorFilter(colorFilter);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList.get(i2).mDrawable.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable firstNonNullDrawable = getFirstNonNullDrawable();
        if (firstNonNullDrawable != null) {
            return DrawableCompat.getAlpha(firstNonNullDrawable);
        }
        return 255;
    }

    final Drawable getFirstNonNullDrawable() {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Drawable drawable = arrayList.get(i).mDrawable;
            if (drawable != null) {
                return drawable;
            }
        }
        return null;
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

    void updateBounds(Rect rect) {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).updateBounds(rect);
        }
    }

    @TargetApi(14)
    public static final class ChildDrawable {
        private final Rect adjustedBounds;
        private final BoundsRule mBoundsRule;
        private final Drawable mDrawable;
        final CompositeDrawable mParent;
        public static final Property<ChildDrawable, Integer> TOP_ABSOLUTE = new Property<ChildDrawable, Integer>(Integer.class, "absoluteTop") { // from class: android.support.v17.leanback.graphics.CompositeDrawable.ChildDrawable.1
            @Override // android.util.Property
            public void set(ChildDrawable childDrawable, Integer num) {
                if (childDrawable.getBoundsRule().top == null) {
                    childDrawable.getBoundsRule().top = BoundsRule.ValueRule.absoluteValue(num.intValue());
                } else {
                    childDrawable.getBoundsRule().top.setAbsoluteValue(num.intValue());
                }
                childDrawable.recomputeBounds();
            }

            @Override // android.util.Property
            public Integer get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().top == null) {
                    return Integer.valueOf(childDrawable.mParent.getBounds().top);
                }
                return Integer.valueOf(childDrawable.getBoundsRule().top.getAbsoluteValue());
            }
        };
        public static final Property<ChildDrawable, Integer> BOTTOM_ABSOLUTE = new Property<ChildDrawable, Integer>(Integer.class, "absoluteBottom") { // from class: android.support.v17.leanback.graphics.CompositeDrawable.ChildDrawable.2
            @Override // android.util.Property
            public void set(ChildDrawable childDrawable, Integer num) {
                if (childDrawable.getBoundsRule().bottom == null) {
                    childDrawable.getBoundsRule().bottom = BoundsRule.ValueRule.absoluteValue(num.intValue());
                } else {
                    childDrawable.getBoundsRule().bottom.setAbsoluteValue(num.intValue());
                }
                childDrawable.recomputeBounds();
            }

            @Override // android.util.Property
            public Integer get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().bottom == null) {
                    return Integer.valueOf(childDrawable.mParent.getBounds().bottom);
                }
                return Integer.valueOf(childDrawable.getBoundsRule().bottom.getAbsoluteValue());
            }
        };
        public static final Property<ChildDrawable, Integer> LEFT_ABSOLUTE = new Property<ChildDrawable, Integer>(Integer.class, "absoluteLeft") { // from class: android.support.v17.leanback.graphics.CompositeDrawable.ChildDrawable.3
            @Override // android.util.Property
            public void set(ChildDrawable childDrawable, Integer num) {
                if (childDrawable.getBoundsRule().left == null) {
                    childDrawable.getBoundsRule().left = BoundsRule.ValueRule.absoluteValue(num.intValue());
                } else {
                    childDrawable.getBoundsRule().left.setAbsoluteValue(num.intValue());
                }
                childDrawable.recomputeBounds();
            }

            @Override // android.util.Property
            public Integer get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().left == null) {
                    return Integer.valueOf(childDrawable.mParent.getBounds().left);
                }
                return Integer.valueOf(childDrawable.getBoundsRule().left.getAbsoluteValue());
            }
        };
        public static final Property<ChildDrawable, Integer> RIGHT_ABSOLUTE = new Property<ChildDrawable, Integer>(Integer.class, "absoluteRight") { // from class: android.support.v17.leanback.graphics.CompositeDrawable.ChildDrawable.4
            @Override // android.util.Property
            public void set(ChildDrawable childDrawable, Integer num) {
                if (childDrawable.getBoundsRule().right == null) {
                    childDrawable.getBoundsRule().right = BoundsRule.ValueRule.absoluteValue(num.intValue());
                } else {
                    childDrawable.getBoundsRule().right.setAbsoluteValue(num.intValue());
                }
                childDrawable.recomputeBounds();
            }

            @Override // android.util.Property
            public Integer get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().right == null) {
                    return Integer.valueOf(childDrawable.mParent.getBounds().right);
                }
                return Integer.valueOf(childDrawable.getBoundsRule().right.getAbsoluteValue());
            }
        };
        public static final Property<ChildDrawable, Float> TOP_FRACTION = new Property<ChildDrawable, Float>(Float.class, "fractionTop") { // from class: android.support.v17.leanback.graphics.CompositeDrawable.ChildDrawable.5
            @Override // android.util.Property
            public void set(ChildDrawable childDrawable, Float f) {
                if (childDrawable.getBoundsRule().top == null) {
                    childDrawable.getBoundsRule().top = BoundsRule.ValueRule.inheritFromParent(f.floatValue());
                } else {
                    childDrawable.getBoundsRule().top.setFraction(f.floatValue());
                }
                childDrawable.recomputeBounds();
            }

            @Override // android.util.Property
            public Float get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().top == null) {
                    return Float.valueOf(0.0f);
                }
                return Float.valueOf(childDrawable.getBoundsRule().top.getFraction());
            }
        };
        public static final Property<ChildDrawable, Float> BOTTOM_FRACTION = new Property<ChildDrawable, Float>(Float.class, "fractionBottom") { // from class: android.support.v17.leanback.graphics.CompositeDrawable.ChildDrawable.6
            @Override // android.util.Property
            public void set(ChildDrawable childDrawable, Float f) {
                if (childDrawable.getBoundsRule().bottom == null) {
                    childDrawable.getBoundsRule().bottom = BoundsRule.ValueRule.inheritFromParent(f.floatValue());
                } else {
                    childDrawable.getBoundsRule().bottom.setFraction(f.floatValue());
                }
                childDrawable.recomputeBounds();
            }

            @Override // android.util.Property
            public Float get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().bottom == null) {
                    return Float.valueOf(1.0f);
                }
                return Float.valueOf(childDrawable.getBoundsRule().bottom.getFraction());
            }
        };
        public static final Property<ChildDrawable, Float> LEFT_FRACTION = new Property<ChildDrawable, Float>(Float.class, "fractionLeft") { // from class: android.support.v17.leanback.graphics.CompositeDrawable.ChildDrawable.7
            @Override // android.util.Property
            public void set(ChildDrawable childDrawable, Float f) {
                if (childDrawable.getBoundsRule().left == null) {
                    childDrawable.getBoundsRule().left = BoundsRule.ValueRule.inheritFromParent(f.floatValue());
                } else {
                    childDrawable.getBoundsRule().left.setFraction(f.floatValue());
                }
                childDrawable.recomputeBounds();
            }

            @Override // android.util.Property
            public Float get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().left == null) {
                    return Float.valueOf(0.0f);
                }
                return Float.valueOf(childDrawable.getBoundsRule().left.getFraction());
            }
        };
        public static final Property<ChildDrawable, Float> RIGHT_FRACTION = new Property<ChildDrawable, Float>(Float.class, "fractionRight") { // from class: android.support.v17.leanback.graphics.CompositeDrawable.ChildDrawable.8
            @Override // android.util.Property
            public void set(ChildDrawable childDrawable, Float f) {
                if (childDrawable.getBoundsRule().right == null) {
                    childDrawable.getBoundsRule().right = BoundsRule.ValueRule.inheritFromParent(f.floatValue());
                } else {
                    childDrawable.getBoundsRule().right.setFraction(f.floatValue());
                }
                childDrawable.recomputeBounds();
            }

            @Override // android.util.Property
            public Float get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().right == null) {
                    return Float.valueOf(1.0f);
                }
                return Float.valueOf(childDrawable.getBoundsRule().right.getFraction());
            }
        };

        public ChildDrawable(Drawable drawable, CompositeDrawable compositeDrawable) {
            this.adjustedBounds = new Rect();
            this.mDrawable = drawable;
            this.mParent = compositeDrawable;
            this.mBoundsRule = new BoundsRule();
            drawable.setCallback(compositeDrawable);
        }

        ChildDrawable(ChildDrawable childDrawable, CompositeDrawable compositeDrawable, Resources resources) {
            Drawable drawableNewDrawable;
            this.adjustedBounds = new Rect();
            Drawable drawable = childDrawable.mDrawable;
            if (drawable != null) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (resources != null) {
                    drawableNewDrawable = constantState.newDrawable(resources);
                } else {
                    drawableNewDrawable = constantState.newDrawable();
                }
                drawableNewDrawable.setCallback(compositeDrawable);
                DrawableCompat.setLayoutDirection(drawableNewDrawable, DrawableCompat.getLayoutDirection(drawable));
                drawableNewDrawable.setBounds(drawable.getBounds());
                drawableNewDrawable.setLevel(drawable.getLevel());
            } else {
                drawableNewDrawable = null;
            }
            if (childDrawable.mBoundsRule != null) {
                this.mBoundsRule = new BoundsRule(childDrawable.mBoundsRule);
            } else {
                this.mBoundsRule = new BoundsRule();
            }
            this.mDrawable = drawableNewDrawable;
            this.mParent = compositeDrawable;
        }

        public BoundsRule getBoundsRule() {
            return this.mBoundsRule;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        void updateBounds(Rect rect) {
            this.mBoundsRule.calculateBounds(rect, this.adjustedBounds);
            this.mDrawable.setBounds(this.adjustedBounds);
        }

        public void recomputeBounds() {
            updateBounds(this.mParent.getBounds());
        }
    }
}
