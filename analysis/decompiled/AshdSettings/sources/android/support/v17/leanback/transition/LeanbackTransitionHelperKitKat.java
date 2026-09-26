package android.support.v17.leanback.transition;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.animation.AnimationUtils;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(19)
@TargetApi(19)
class LeanbackTransitionHelperKitKat {
    LeanbackTransitionHelperKitKat() {
    }

    public static Object loadTitleInTransition(Context context) {
        SlideKitkat slideKitkat = new SlideKitkat();
        slideKitkat.setSlideEdge(48);
        slideKitkat.setInterpolator(AnimationUtils.loadInterpolator(context, R.anim.decelerate_interpolator));
        slideKitkat.addTarget(android.support.v17.leanback.R.id.browse_title_group);
        return slideKitkat;
    }

    public static Object loadTitleOutTransition(Context context) {
        SlideKitkat slideKitkat = new SlideKitkat();
        slideKitkat.setSlideEdge(48);
        slideKitkat.setInterpolator(AnimationUtils.loadInterpolator(context, android.support.v17.leanback.R.animator.lb_decelerator_4));
        slideKitkat.addTarget(android.support.v17.leanback.R.id.browse_title_group);
        return slideKitkat;
    }
}
