package com.blankj.utilcode.util;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class ClickUtils {
    private static final long DEBOUNCING_DEFAULT_DURATION = 200;
    private static final int SCALE = -1;
    private static final float SCALE_DEFAULT_FACTOR = -0.06f;

    private static class LazyHolder {
        private static final OnUtilsTouchListener INSTANCE = new OnUtilsTouchListener();

        private LazyHolder() {
        }
    }

    public static abstract class OnDebouncingClickListener implements View.OnClickListener {
        private static final Runnable ENABLE_AGAIN = new Runnable() { // from class: com.blankj.utilcode.util.ClickUtils.OnDebouncingClickListener.1
            @Override // java.lang.Runnable
            public void run() {
                boolean unused = OnDebouncingClickListener.mEnabled = true;
            }
        };
        private static final int TAG_KEY = 2130706431;
        private static boolean mEnabled = true;
        private long mDuration;
        private boolean mIsGlobal;

        public OnDebouncingClickListener() {
            this(true, ClickUtils.DEBOUNCING_DEFAULT_DURATION);
        }

        private static boolean isValid(View view, long j) {
            if (view == null) {
                throw new NullPointerException("Argument 'view' of type View (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            Object tag = view.getTag(TAG_KEY);
            if (!(tag instanceof Long)) {
                view.setTag(TAG_KEY, Long.valueOf(jCurrentTimeMillis));
                return true;
            }
            if (jCurrentTimeMillis - ((Long) tag).longValue() <= j) {
                return false;
            }
            view.setTag(TAG_KEY, Long.valueOf(jCurrentTimeMillis));
            return true;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (!this.mIsGlobal) {
                if (isValid(view, this.mDuration)) {
                    onDebouncingClick(view);
                }
            } else if (mEnabled) {
                mEnabled = false;
                view.postDelayed(ENABLE_AGAIN, this.mDuration);
                onDebouncingClick(view);
            }
        }

        public abstract void onDebouncingClick(View view);

        public OnDebouncingClickListener(boolean z) {
            this(z, ClickUtils.DEBOUNCING_DEFAULT_DURATION);
        }

        public OnDebouncingClickListener(long j) {
            this(true, j);
        }

        public OnDebouncingClickListener(boolean z, long j) {
            this.mIsGlobal = z;
            this.mDuration = j;
        }
    }

    public static abstract class OnMultiClickListener implements View.OnClickListener {
        private static final long DEFAULT_INTERVAL = 666;
        private int mClickCount;
        private final long mClickInterval;
        private long mLastClickTime;
        private final int mTriggerClickCount;

        public OnMultiClickListener(int i) {
            this(i, DEFAULT_INTERVAL);
        }

        public abstract void onBeforeTriggerClick(View view, int i);

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.mTriggerClickCount <= 1) {
                onTriggerClick(view);
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.mLastClickTime < this.mClickInterval) {
                this.mClickCount++;
                int i = this.mClickCount;
                int i2 = this.mTriggerClickCount;
                if (i == i2) {
                    onTriggerClick(view);
                } else if (i < i2) {
                    onBeforeTriggerClick(view, i);
                } else {
                    this.mClickCount = 1;
                    onBeforeTriggerClick(view, this.mClickCount);
                }
            } else {
                this.mClickCount = 1;
                onBeforeTriggerClick(view, this.mClickCount);
            }
            this.mLastClickTime = jCurrentTimeMillis;
        }

        public abstract void onTriggerClick(View view);

        public OnMultiClickListener(int i, long j) {
            this.mTriggerClickCount = i;
            this.mClickInterval = j;
        }
    }

    private static class OnUtilsTouchListener implements View.OnTouchListener {
        public static OnUtilsTouchListener getInstance() {
            return LazyHolder.INSTANCE;
        }

        private void processScale(View view, boolean z) {
            Object tag = view.getTag(-1);
            if (tag instanceof Float) {
                float fFloatValue = z ? 1.0f + ((Float) tag).floatValue() : 1.0f;
                view.animate().scaleX(fFloatValue).scaleY(fFloatValue).setDuration(100L).start();
            }
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                processScale(view, true);
            } else if (action == 1 || action == 3) {
                processScale(view, false);
            }
            return false;
        }

        private OnUtilsTouchListener() {
        }
    }

    private static void applyDebouncing(View[] viewArr, boolean z, long j, final View.OnClickListener onClickListener) {
        if (viewArr == null || viewArr.length == 0 || onClickListener == null) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setOnClickListener(new OnDebouncingClickListener(z, j) { // from class: com.blankj.utilcode.util.ClickUtils.1
                    @Override // com.blankj.utilcode.util.ClickUtils.OnDebouncingClickListener
                    public void onDebouncingClick(View view2) {
                        onClickListener.onClick(view2);
                    }
                });
            }
        }
    }

    public static void applyGlobalDebouncing(View view, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(new View[]{view}, onClickListener);
    }

    public static void applyScale(View... viewArr) {
        applyScale(viewArr, null);
    }

    public static void applySingleDebouncing(View view, View.OnClickListener onClickListener) {
        applySingleDebouncing(new View[]{view}, onClickListener);
    }

    public static void applyGlobalDebouncing(View view, long j, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(new View[]{view}, j, onClickListener);
    }

    public static void applyScale(View[] viewArr, float[] fArr) {
        if (viewArr == null || viewArr.length == 0) {
            return;
        }
        for (int i = 0; i < viewArr.length; i++) {
            if (viewArr[i] != null) {
                if (fArr == null || i >= fArr.length) {
                    viewArr[i].setTag(-1, Float.valueOf(SCALE_DEFAULT_FACTOR));
                } else {
                    viewArr[i].setTag(-1, Float.valueOf(fArr[i]));
                }
                viewArr[i].setClickable(true);
                viewArr[i].setOnTouchListener(OnUtilsTouchListener.getInstance());
            }
        }
    }

    public static void applySingleDebouncing(View view, long j, View.OnClickListener onClickListener) {
        applySingleDebouncing(new View[]{view}, j, onClickListener);
    }

    public static void applyGlobalDebouncing(View[] viewArr, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(viewArr, DEBOUNCING_DEFAULT_DURATION, onClickListener);
    }

    public static void applySingleDebouncing(View[] viewArr, View.OnClickListener onClickListener) {
        applySingleDebouncing(viewArr, DEBOUNCING_DEFAULT_DURATION, onClickListener);
    }

    public static void applyGlobalDebouncing(View[] viewArr, long j, View.OnClickListener onClickListener) {
        applyDebouncing(viewArr, true, j, onClickListener);
    }

    public static void applySingleDebouncing(View[] viewArr, long j, View.OnClickListener onClickListener) {
        applyDebouncing(viewArr, false, j, onClickListener);
    }
}
