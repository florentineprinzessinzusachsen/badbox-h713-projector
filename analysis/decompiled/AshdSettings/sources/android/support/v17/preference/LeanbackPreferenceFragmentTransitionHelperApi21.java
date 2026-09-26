package android.support.v17.preference;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v17.leanback.transition.FadeAndShortSlide;
import android.support.v4.view.GravityCompat;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
@TargetApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class LeanbackPreferenceFragmentTransitionHelperApi21 {
    public static void addTransitions(Fragment fragment) {
        FadeAndShortSlide fadeAndShortSlide = new FadeAndShortSlide(GravityCompat.START);
        FadeAndShortSlide fadeAndShortSlide2 = new FadeAndShortSlide(GravityCompat.END);
        fragment.setEnterTransition(fadeAndShortSlide2);
        fragment.setExitTransition(fadeAndShortSlide);
        fragment.setReenterTransition(fadeAndShortSlide);
        fragment.setReturnTransition(fadeAndShortSlide2);
    }
}
