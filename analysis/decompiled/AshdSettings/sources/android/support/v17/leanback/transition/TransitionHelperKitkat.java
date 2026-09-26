package android.support.v17.leanback.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(19)
@TargetApi(19)
final class TransitionHelperKitkat {
    TransitionHelperKitkat() {
    }

    static Object createScene(ViewGroup viewGroup, Runnable runnable) {
        Scene scene = new Scene(viewGroup);
        scene.setEnterAction(runnable);
        return scene;
    }

    static Object createTransitionSet(boolean z) {
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.setOrdering(z ? 1 : 0);
        return transitionSet;
    }

    static void addTransition(Object obj, Object obj2) {
        ((TransitionSet) obj).addTransition((Transition) obj2);
    }

    static Object createAutoTransition() {
        return new AutoTransition();
    }

    static Object createSlide(int i) {
        SlideKitkat slideKitkat = new SlideKitkat();
        slideKitkat.setSlideEdge(i);
        return slideKitkat;
    }

    static Object createScale() {
        return new Scale();
    }

    static Object createFadeTransition(int i) {
        return new Fade(i);
    }

    static class CustomChangeBounds extends ChangeBounds {
        int mDefaultStartDelay;
        final HashMap<View, Integer> mViewStartDelays = new HashMap<>();
        final SparseIntArray mIdStartDelays = new SparseIntArray();
        final HashMap<String, Integer> mClassStartDelays = new HashMap<>();

        CustomChangeBounds() {
        }

        private int getDelay(View view) {
            Integer num = this.mViewStartDelays.get(view);
            if (num != null) {
                return num.intValue();
            }
            int i = this.mIdStartDelays.get(view.getId(), -1);
            if (i != -1) {
                return i;
            }
            Integer num2 = this.mClassStartDelays.get(view.getClass().getName());
            if (num2 != null) {
                return num2.intValue();
            }
            return this.mDefaultStartDelay;
        }

        @Override // android.transition.ChangeBounds, android.transition.Transition
        public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
            Animator animatorCreateAnimator = super.createAnimator(viewGroup, transitionValues, transitionValues2);
            if (animatorCreateAnimator != null && transitionValues2 != null && transitionValues2.view != null) {
                animatorCreateAnimator.setStartDelay(getDelay(transitionValues2.view));
            }
            return animatorCreateAnimator;
        }

        public void setStartDelay(View view, int i) {
            this.mViewStartDelays.put(view, Integer.valueOf(i));
        }

        public void setStartDelay(int i, int i2) {
            this.mIdStartDelays.put(i, i2);
        }

        public void setStartDelay(String str, int i) {
            this.mClassStartDelays.put(str, Integer.valueOf(i));
        }

        public void setDefaultStartDelay(int i) {
            this.mDefaultStartDelay = i;
        }
    }

    static Object createChangeBounds(boolean z) {
        CustomChangeBounds customChangeBounds = new CustomChangeBounds();
        customChangeBounds.setReparent(z);
        return customChangeBounds;
    }

    static void setChangeBoundsStartDelay(Object obj, int i, int i2) {
        ((CustomChangeBounds) obj).setStartDelay(i, i2);
    }

    static void setChangeBoundsStartDelay(Object obj, View view, int i) {
        ((CustomChangeBounds) obj).setStartDelay(view, i);
    }

    static void setChangeBoundsStartDelay(Object obj, String str, int i) {
        ((CustomChangeBounds) obj).setStartDelay(str, i);
    }

    static void setChangeBoundsDefaultStartDelay(Object obj, int i) {
        ((CustomChangeBounds) obj).setDefaultStartDelay(i);
    }

    static void setStartDelay(Object obj, long j) {
        ((Transition) obj).setStartDelay(j);
    }

    static void setDuration(Object obj, long j) {
        ((Transition) obj).setDuration(j);
    }

    static void exclude(Object obj, int i, boolean z) {
        ((Transition) obj).excludeTarget(i, z);
    }

    static void exclude(Object obj, View view, boolean z) {
        ((Transition) obj).excludeTarget(view, z);
    }

    static void excludeChildren(Object obj, int i, boolean z) {
        ((Transition) obj).excludeChildren(i, z);
    }

    static void excludeChildren(Object obj, View view, boolean z) {
        ((Transition) obj).excludeChildren(view, z);
    }

    static void include(Object obj, int i) {
        ((Transition) obj).addTarget(i);
    }

    static void include(Object obj, View view) {
        ((Transition) obj).addTarget(view);
    }

    static void addTransitionListener(Object obj, final TransitionListener transitionListener) {
        if (transitionListener == null) {
            return;
        }
        transitionListener.mImpl = new Transition.TransitionListener() { // from class: android.support.v17.leanback.transition.TransitionHelperKitkat.1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition) {
                transitionListener.onTransitionStart(transition);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition) {
                transitionListener.onTransitionResume(transition);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition) {
                transitionListener.onTransitionPause(transition);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition) {
                transitionListener.onTransitionEnd(transition);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition) {
                transitionListener.onTransitionCancel(transition);
            }
        };
        ((Transition) obj).addListener((Transition.TransitionListener) transitionListener.mImpl);
    }

    static void removeTransitionListener(Object obj, TransitionListener transitionListener) {
        if (transitionListener == null || transitionListener.mImpl == null) {
            return;
        }
        ((Transition) obj).removeListener((Transition.TransitionListener) transitionListener.mImpl);
        transitionListener.mImpl = null;
    }

    static void runTransition(Object obj, Object obj2) {
        TransitionManager.go((Scene) obj, (Transition) obj2);
    }

    static void setInterpolator(Object obj, Object obj2) {
        ((Transition) obj).setInterpolator((TimeInterpolator) obj2);
    }

    static void addTarget(Object obj, View view) {
        ((Transition) obj).addTarget(view);
    }

    static Object loadTransition(Context context, int i) {
        return TransitionInflater.from(context).inflateTransition(i);
    }
}
