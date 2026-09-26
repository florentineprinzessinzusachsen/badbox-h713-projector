package android.support.v17.leanback.app;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v17.leanback.R;
import android.support.v17.leanback.widget.BackgroundHelper;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class BackgroundManager {
    private static final int CHANGE_BG_DELAY_MS = 500;
    static final boolean DEBUG = false;
    private static final int FADE_DURATION = 500;
    private static final String FRAGMENT_TAG = BackgroundManager.class.getCanonicalName();
    static final int FULL_ALPHA = 255;
    static final String TAG = "BackgroundManager";
    private final Interpolator mAccelerateInterpolator;
    final ValueAnimator mAnimator;
    private boolean mAttached;
    int mBackgroundColor;
    Drawable mBackgroundDrawable;
    private View mBgView;
    ChangeBackgroundRunnable mChangeRunnable;
    private boolean mChangeRunnablePending;
    Activity mContext;
    private final Interpolator mDecelerateInterpolator;
    private BackgroundFragment mFragmentState;
    private int mHeightPx;
    int mImageInWrapperIndex;
    int mImageOutWrapperIndex;
    private long mLastSetTime;
    TranslucentLayerDrawable mLayerDrawable;
    private int mThemeDrawableResourceId;
    private int mWidthPx;
    private boolean mAutoReleaseOnStop = true;
    private final Animator.AnimatorListener mAnimationListener = new Animator.AnimatorListener() { // from class: android.support.v17.leanback.app.BackgroundManager.1
        final Runnable mRunnable = new Runnable() { // from class: android.support.v17.leanback.app.BackgroundManager.1.1
            @Override // java.lang.Runnable
            public void run() {
                BackgroundManager.this.postChangeRunnable();
            }
        };

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (BackgroundManager.this.mLayerDrawable != null) {
                BackgroundManager.this.mLayerDrawable.clearDrawable(R.id.background_imageout, BackgroundManager.this.mContext);
            }
            BackgroundManager.this.mHandler.post(this.mRunnable);
        }
    };
    private final ValueAnimator.AnimatorUpdateListener mAnimationUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v17.leanback.app.BackgroundManager.2
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (BackgroundManager.this.mImageInWrapperIndex != -1) {
                BackgroundManager.this.mLayerDrawable.setWrapperAlpha(BackgroundManager.this.mImageInWrapperIndex, iIntValue);
            }
        }
    };
    private BackgroundContinuityService mService = BackgroundContinuityService.getInstance();
    Handler mHandler = new Handler();

    @Deprecated
    public Drawable getDimLayer() {
        return null;
    }

    @Deprecated
    public void setDimLayer(Drawable drawable) {
    }

    static class BitmapDrawable extends Drawable {
        boolean mMutated;
        ConstantState mState;

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        static final class ConstantState extends Drawable.ConstantState {
            final Bitmap mBitmap;
            final Matrix mMatrix;
            final Paint mPaint = new Paint();

            @Override // android.graphics.drawable.Drawable.ConstantState
            public int getChangingConfigurations() {
                return 0;
            }

            ConstantState(Bitmap bitmap, Matrix matrix) {
                this.mBitmap = bitmap;
                this.mMatrix = matrix == null ? new Matrix() : matrix;
                this.mPaint.setFilterBitmap(true);
            }

            ConstantState(ConstantState constantState) {
                this.mBitmap = constantState.mBitmap;
                this.mMatrix = constantState.mMatrix != null ? new Matrix(constantState.mMatrix) : new Matrix();
                if (constantState.mPaint.getAlpha() != 255) {
                    this.mPaint.setAlpha(constantState.mPaint.getAlpha());
                }
                if (constantState.mPaint.getColorFilter() != null) {
                    this.mPaint.setColorFilter(constantState.mPaint.getColorFilter());
                }
                this.mPaint.setFilterBitmap(true);
            }

            @Override // android.graphics.drawable.Drawable.ConstantState
            public Drawable newDrawable() {
                return new BitmapDrawable(this);
            }
        }

        BitmapDrawable(Resources resources, Bitmap bitmap) {
            this(resources, bitmap, null);
        }

        BitmapDrawable(Resources resources, Bitmap bitmap, Matrix matrix) {
            this.mState = new ConstantState(bitmap, matrix);
        }

        BitmapDrawable(ConstantState constantState) {
            this.mState = constantState;
        }

        Bitmap getBitmap() {
            return this.mState.mBitmap;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.mState.mBitmap == null) {
                return;
            }
            if (this.mState.mPaint.getAlpha() < 255 && this.mState.mPaint.getColorFilter() != null) {
                throw new IllegalStateException("Can't draw with translucent alpha and color filter");
            }
            canvas.drawBitmap(this.mState.mBitmap, this.mState.mMatrix, this.mState.mPaint);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            mutate();
            if (this.mState.mPaint.getAlpha() != i) {
                this.mState.mPaint.setAlpha(i);
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            mutate();
            this.mState.mPaint.setColorFilter(colorFilter);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public ColorFilter getColorFilter() {
            return this.mState.mPaint.getColorFilter();
        }

        @Override // android.graphics.drawable.Drawable
        public ConstantState getConstantState() {
            return this.mState;
        }

        @Override // android.graphics.drawable.Drawable
        @NonNull
        public Drawable mutate() {
            if (!this.mMutated) {
                this.mMutated = true;
                this.mState = new ConstantState(this.mState);
            }
            return this;
        }
    }

    static final class DrawableWrapper {
        int mAlpha;
        final Drawable mDrawable;

        public DrawableWrapper(Drawable drawable) {
            this.mAlpha = 255;
            this.mDrawable = drawable;
        }

        public DrawableWrapper(DrawableWrapper drawableWrapper, Drawable drawable) {
            this.mAlpha = 255;
            this.mDrawable = drawable;
            this.mAlpha = drawableWrapper.mAlpha;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        public void setColor(int i) {
            ((ColorDrawable) this.mDrawable).setColor(i);
        }
    }

    static final class TranslucentLayerDrawable extends LayerDrawable {
        int mAlpha;
        boolean mSuspendInvalidation;
        DrawableWrapper[] mWrapper;

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        public TranslucentLayerDrawable(Drawable[] drawableArr) {
            super(drawableArr);
            this.mAlpha = 255;
            int length = drawableArr.length;
            this.mWrapper = new DrawableWrapper[length];
            for (int i = 0; i < length; i++) {
                this.mWrapper[i] = new DrawableWrapper(drawableArr[i]);
            }
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.mAlpha = i;
            invalidateSelf();
        }

        void setWrapperAlpha(int i, int i2) {
            if (this.mWrapper[i] != null) {
                this.mWrapper[i].mAlpha = i2;
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public int getAlpha() {
            return this.mAlpha;
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public Drawable mutate() {
            Drawable drawableMutate = super.mutate();
            int numberOfLayers = getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                if (this.mWrapper[i] != null) {
                    this.mWrapper[i] = new DrawableWrapper(this.mWrapper[i], getDrawable(i));
                }
            }
            return drawableMutate;
        }

        @Override // android.graphics.drawable.LayerDrawable
        public boolean setDrawableByLayerId(int i, Drawable drawable) {
            return updateDrawable(i, drawable) != null;
        }

        public DrawableWrapper updateDrawable(int i, Drawable drawable) {
            super.setDrawableByLayerId(i, drawable);
            for (int i2 = 0; i2 < getNumberOfLayers(); i2++) {
                if (getId(i2) == i) {
                    this.mWrapper[i2] = new DrawableWrapper(drawable);
                    invalidateSelf();
                    return this.mWrapper[i2];
                }
            }
            return null;
        }

        public void clearDrawable(int i, Context context) {
            for (int i2 = 0; i2 < getNumberOfLayers(); i2++) {
                if (getId(i2) == i) {
                    this.mWrapper[i2] = null;
                    if (getDrawable(i2) instanceof EmptyDrawable) {
                        return;
                    }
                    super.setDrawableByLayerId(i, BackgroundManager.createEmptyDrawable(context));
                    return;
                }
            }
        }

        public int findWrapperIndexById(int i) {
            for (int i2 = 0; i2 < getNumberOfLayers(); i2++) {
                if (getId(i2) == i) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            if (this.mSuspendInvalidation) {
                return;
            }
            super.invalidateDrawable(drawable);
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            Drawable drawable;
            int i;
            int i2;
            for (int i3 = 0; i3 < this.mWrapper.length; i3++) {
                if (this.mWrapper[i3] != null && (drawable = this.mWrapper[i3].getDrawable()) != null) {
                    int alpha = Build.VERSION.SDK_INT >= 19 ? DrawableCompat.getAlpha(drawable) : 255;
                    if (this.mAlpha < 255) {
                        i2 = this.mAlpha * alpha;
                        i = 1;
                    } else {
                        i = 0;
                        i2 = alpha;
                    }
                    if (this.mWrapper[i3].mAlpha < 255) {
                        i2 *= this.mWrapper[i3].mAlpha;
                        i++;
                    }
                    if (i == 0) {
                        drawable.draw(canvas);
                    } else {
                        if (i == 1) {
                            i2 /= 255;
                        } else if (i == 2) {
                            i2 /= 65025;
                        }
                        try {
                            this.mSuspendInvalidation = true;
                            drawable.setAlpha(i2);
                            drawable.draw(canvas);
                            drawable.setAlpha(alpha);
                            this.mSuspendInvalidation = false;
                        } catch (Throwable th) {
                            this.mSuspendInvalidation = false;
                            throw th;
                        }
                    }
                }
            }
        }
    }

    TranslucentLayerDrawable createTranslucentLayerDrawable(LayerDrawable layerDrawable) {
        int numberOfLayers = layerDrawable.getNumberOfLayers();
        Drawable[] drawableArr = new Drawable[numberOfLayers];
        for (int i = 0; i < numberOfLayers; i++) {
            drawableArr[i] = layerDrawable.getDrawable(i);
        }
        TranslucentLayerDrawable translucentLayerDrawable = new TranslucentLayerDrawable(drawableArr);
        for (int i2 = 0; i2 < numberOfLayers; i2++) {
            translucentLayerDrawable.setId(i2, layerDrawable.getId(i2));
        }
        return translucentLayerDrawable;
    }

    private static class BackgroundContinuityService {
        private static boolean DEBUG = false;
        private static final String TAG = "BackgroundContinuity";
        private static BackgroundContinuityService sService = new BackgroundContinuityService();
        private int mColor;
        private int mCount;
        private Drawable mDrawable;
        private int mLastThemeDrawableId;
        private WeakReference<Drawable.ConstantState> mLastThemeDrawableState;

        private BackgroundContinuityService() {
            reset();
        }

        private void reset() {
            this.mColor = 0;
            this.mDrawable = null;
        }

        public static BackgroundContinuityService getInstance() {
            BackgroundContinuityService backgroundContinuityService = sService;
            int i = backgroundContinuityService.mCount;
            backgroundContinuityService.mCount = i + 1;
            if (DEBUG) {
                Log.v(TAG, "Returning instance with new count " + i);
            }
            return sService;
        }

        public void unref() {
            if (this.mCount <= 0) {
                throw new IllegalStateException("Can't unref, count " + this.mCount);
            }
            int i = this.mCount - 1;
            this.mCount = i;
            if (i == 0) {
                if (DEBUG) {
                    Log.v(TAG, "mCount is zero, resetting");
                }
                reset();
            }
        }

        public int getColor() {
            return this.mColor;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        public void setColor(int i) {
            this.mColor = i;
            this.mDrawable = null;
        }

        public void setDrawable(Drawable drawable) {
            this.mDrawable = drawable;
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0031  */
        public Drawable getThemeDrawable(Context context, int i) {
            Drawable drawable;
            if (this.mLastThemeDrawableState == null || this.mLastThemeDrawableId != i) {
                drawable = null;
            } else {
                Drawable.ConstantState constantState = this.mLastThemeDrawableState.get();
                if (DEBUG) {
                    Log.v(TAG, "got cached theme drawable state " + constantState);
                }
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                } else {
                    drawable = null;
                }
            }
            if (drawable == null) {
                drawable = ContextCompat.getDrawable(context, i);
                if (DEBUG) {
                    Log.v(TAG, "loaded theme drawable " + drawable);
                }
                this.mLastThemeDrawableState = new WeakReference<>(drawable.getConstantState());
                this.mLastThemeDrawableId = i;
            }
            return drawable;
        }
    }

    Drawable getDefaultDrawable() {
        if (this.mBackgroundColor != 0) {
            return new ColorDrawable(this.mBackgroundColor);
        }
        return getThemeDrawable();
    }

    private Drawable getThemeDrawable() {
        Drawable themeDrawable = this.mThemeDrawableResourceId != -1 ? this.mService.getThemeDrawable(this.mContext, this.mThemeDrawableResourceId) : null;
        return themeDrawable == null ? createEmptyDrawable(this.mContext) : themeDrawable;
    }

    public static BackgroundManager getInstance(Activity activity) {
        BackgroundManager backgroundManager;
        BackgroundFragment backgroundFragment = (BackgroundFragment) activity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        return (backgroundFragment == null || (backgroundManager = backgroundFragment.getBackgroundManager()) == null) ? new BackgroundManager(activity) : backgroundManager;
    }

    private BackgroundManager(Activity activity) {
        this.mContext = activity;
        this.mHeightPx = this.mContext.getResources().getDisplayMetrics().heightPixels;
        this.mWidthPx = this.mContext.getResources().getDisplayMetrics().widthPixels;
        FastOutLinearInInterpolator fastOutLinearInInterpolator = new FastOutLinearInInterpolator();
        this.mAccelerateInterpolator = AnimationUtils.loadInterpolator(this.mContext, android.R.anim.accelerate_interpolator);
        this.mDecelerateInterpolator = AnimationUtils.loadInterpolator(this.mContext, android.R.anim.decelerate_interpolator);
        this.mAnimator = ValueAnimator.ofInt(0, 255);
        this.mAnimator.addListener(this.mAnimationListener);
        this.mAnimator.addUpdateListener(this.mAnimationUpdateListener);
        this.mAnimator.setInterpolator(fastOutLinearInInterpolator);
        TypedArray typedArrayObtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowBackground});
        this.mThemeDrawableResourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        int i = this.mThemeDrawableResourceId;
        typedArrayObtainStyledAttributes.recycle();
        createFragment(activity);
    }

    private void createFragment(Activity activity) {
        BackgroundFragment backgroundFragment = (BackgroundFragment) activity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (backgroundFragment == null) {
            backgroundFragment = new BackgroundFragment();
            activity.getFragmentManager().beginTransaction().add(backgroundFragment, FRAGMENT_TAG).commit();
        } else if (backgroundFragment.getBackgroundManager() != null) {
            throw new IllegalStateException("Created duplicated BackgroundManager for same activity, please use getInstance() instead");
        }
        backgroundFragment.setBackgroundManager(this);
        this.mFragmentState = backgroundFragment;
    }

    DrawableWrapper getImageInWrapper() {
        if (this.mLayerDrawable == null) {
            return null;
        }
        return this.mLayerDrawable.mWrapper[this.mImageInWrapperIndex];
    }

    DrawableWrapper getImageOutWrapper() {
        if (this.mLayerDrawable == null) {
            return null;
        }
        return this.mLayerDrawable.mWrapper[this.mImageOutWrapperIndex];
    }

    void onActivityStart() {
        updateImmediate();
    }

    void onStop() {
        if (isAutoReleaseOnStop()) {
            release();
        }
    }

    void onResume() {
        postChangeRunnable();
    }

    private void syncWithService() {
        int color = this.mService.getColor();
        Drawable drawable = this.mService.getDrawable();
        this.mBackgroundColor = color;
        this.mBackgroundDrawable = drawable == null ? null : drawable.getConstantState().newDrawable().mutate();
        updateImmediate();
    }

    public void attach(Window window) {
        attachToViewInternal(window.getDecorView());
    }

    public void setThemeDrawableResourceId(int i) {
        this.mThemeDrawableResourceId = i;
    }

    public void attachToView(View view) {
        attachToViewInternal(view);
    }

    void attachToViewInternal(View view) {
        if (this.mAttached) {
            throw new IllegalStateException("Already attached to " + this.mBgView);
        }
        this.mBgView = view;
        this.mAttached = true;
        syncWithService();
    }

    public boolean isAttached() {
        return this.mAttached;
    }

    void detach() {
        release();
        this.mBgView = null;
        this.mAttached = false;
        if (this.mService != null) {
            this.mService.unref();
            this.mService = null;
        }
    }

    public void release() {
        if (this.mLayerDrawable != null) {
            this.mLayerDrawable.clearDrawable(R.id.background_imagein, this.mContext);
            this.mLayerDrawable.clearDrawable(R.id.background_imageout, this.mContext);
            this.mLayerDrawable = null;
        }
        if (this.mChangeRunnable != null) {
            this.mHandler.removeCallbacks(this.mChangeRunnable);
            this.mChangeRunnable = null;
        }
        this.mBackgroundDrawable = null;
    }

    @Deprecated
    public Drawable getDefaultDimLayer() {
        return ContextCompat.getDrawable(this.mContext, R.color.lb_background_protection);
    }

    void postChangeRunnable() {
        if (this.mChangeRunnable == null || !this.mChangeRunnablePending || this.mAnimator.isStarted() || !this.mFragmentState.isResumed() || this.mLayerDrawable.getAlpha() < 255) {
            return;
        }
        long runnableDelay = getRunnableDelay();
        this.mLastSetTime = System.currentTimeMillis();
        this.mHandler.postDelayed(this.mChangeRunnable, runnableDelay);
        this.mChangeRunnablePending = false;
    }

    private void lazyInit() {
        if (this.mLayerDrawable != null) {
            return;
        }
        this.mLayerDrawable = createTranslucentLayerDrawable((LayerDrawable) ContextCompat.getDrawable(this.mContext, R.drawable.lb_background).mutate());
        this.mImageInWrapperIndex = this.mLayerDrawable.findWrapperIndexById(R.id.background_imagein);
        this.mImageOutWrapperIndex = this.mLayerDrawable.findWrapperIndexById(R.id.background_imageout);
        BackgroundHelper.setBackgroundPreservingAlpha(this.mBgView, this.mLayerDrawable);
    }

    private void updateImmediate() {
        if (this.mAttached) {
            lazyInit();
            if (this.mBackgroundDrawable == null) {
                this.mLayerDrawable.updateDrawable(R.id.background_imagein, getDefaultDrawable());
            } else {
                this.mLayerDrawable.updateDrawable(R.id.background_imagein, this.mBackgroundDrawable);
            }
            this.mLayerDrawable.clearDrawable(R.id.background_imageout, this.mContext);
        }
    }

    public void setColor(@ColorInt int i) {
        this.mService.setColor(i);
        this.mBackgroundColor = i;
        this.mBackgroundDrawable = null;
        if (this.mLayerDrawable == null) {
            return;
        }
        setDrawableInternal(getDefaultDrawable());
    }

    public void setDrawable(Drawable drawable) {
        this.mService.setDrawable(drawable);
        this.mBackgroundDrawable = drawable;
        if (this.mLayerDrawable == null) {
            return;
        }
        if (drawable == null) {
            setDrawableInternal(getDefaultDrawable());
        } else {
            setDrawableInternal(drawable);
        }
    }

    public void clearDrawable() {
        setDrawable(null);
    }

    private void setDrawableInternal(Drawable drawable) {
        if (!this.mAttached) {
            throw new IllegalStateException("Must attach before setting background drawable");
        }
        if (this.mChangeRunnable != null) {
            if (sameDrawable(drawable, this.mChangeRunnable.mDrawable)) {
                return;
            }
            this.mHandler.removeCallbacks(this.mChangeRunnable);
            this.mChangeRunnable = null;
        }
        this.mChangeRunnable = new ChangeBackgroundRunnable(drawable);
        this.mChangeRunnablePending = true;
        postChangeRunnable();
    }

    private long getRunnableDelay() {
        return Math.max(0L, (this.mLastSetTime + 500) - System.currentTimeMillis());
    }

    public void setBitmap(Bitmap bitmap) {
        float f;
        Matrix matrix = null;
        if (bitmap == null) {
            setDrawable(null);
            return;
        }
        if (bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            return;
        }
        if (bitmap.getWidth() != this.mWidthPx || bitmap.getHeight() != this.mHeightPx) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (this.mHeightPx * width > this.mWidthPx * height) {
                f = this.mHeightPx / height;
            } else {
                f = this.mWidthPx / width;
            }
            int iMax = Math.max(0, (width - Math.min((int) (this.mWidthPx / f), width)) / 2);
            Matrix matrix2 = new Matrix();
            matrix2.setScale(f, f);
            matrix2.preTranslate(-iMax, 0.0f);
            matrix = matrix2;
        }
        setDrawable(new BitmapDrawable(this.mContext.getResources(), bitmap, matrix));
    }

    public void setAutoReleaseOnStop(boolean z) {
        this.mAutoReleaseOnStop = z;
    }

    public boolean isAutoReleaseOnStop() {
        return this.mAutoReleaseOnStop;
    }

    @ColorInt
    public final int getColor() {
        return this.mBackgroundColor;
    }

    public Drawable getDrawable() {
        return this.mBackgroundDrawable;
    }

    boolean sameDrawable(Drawable drawable, Drawable drawable2) {
        if (drawable == null || drawable2 == null) {
            return false;
        }
        if (drawable == drawable2) {
            return true;
        }
        if ((drawable instanceof BitmapDrawable) && (drawable2 instanceof BitmapDrawable) && ((BitmapDrawable) drawable).getBitmap().sameAs(((BitmapDrawable) drawable2).getBitmap())) {
            return true;
        }
        return (drawable instanceof ColorDrawable) && (drawable2 instanceof ColorDrawable) && ((ColorDrawable) drawable).getColor() == ((ColorDrawable) drawable2).getColor();
    }

    final class ChangeBackgroundRunnable implements Runnable {
        final Drawable mDrawable;

        ChangeBackgroundRunnable(Drawable drawable) {
            this.mDrawable = drawable;
        }

        @Override // java.lang.Runnable
        public void run() {
            runTask();
            BackgroundManager.this.mChangeRunnable = null;
        }

        private void runTask() {
            if (BackgroundManager.this.mLayerDrawable == null) {
                return;
            }
            DrawableWrapper imageInWrapper = BackgroundManager.this.getImageInWrapper();
            if (imageInWrapper != null) {
                if (BackgroundManager.this.sameDrawable(this.mDrawable, imageInWrapper.getDrawable())) {
                    return;
                }
                BackgroundManager.this.mLayerDrawable.clearDrawable(R.id.background_imagein, BackgroundManager.this.mContext);
                BackgroundManager.this.mLayerDrawable.updateDrawable(R.id.background_imageout, imageInWrapper.getDrawable());
            }
            applyBackgroundChanges();
        }

        void applyBackgroundChanges() {
            if (BackgroundManager.this.mAttached) {
                if (BackgroundManager.this.getImageInWrapper() == null && this.mDrawable != null) {
                    BackgroundManager.this.mLayerDrawable.updateDrawable(R.id.background_imagein, this.mDrawable);
                    BackgroundManager.this.mLayerDrawable.setWrapperAlpha(BackgroundManager.this.mImageInWrapperIndex, 0);
                }
                BackgroundManager.this.mAnimator.setDuration(500L);
                BackgroundManager.this.mAnimator.start();
            }
        }
    }

    static class EmptyDrawable extends BitmapDrawable {
        EmptyDrawable(Resources resources) {
            super(resources, (Bitmap) null);
        }
    }

    static Drawable createEmptyDrawable(Context context) {
        return new EmptyDrawable(context.getResources());
    }
}
