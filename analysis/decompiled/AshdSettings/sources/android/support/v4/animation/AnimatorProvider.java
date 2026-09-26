package android.support.v4.animation;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
interface AnimatorProvider {
    void clearInterpolator(View view);

    ValueAnimatorCompat emptyValueAnimator();
}
