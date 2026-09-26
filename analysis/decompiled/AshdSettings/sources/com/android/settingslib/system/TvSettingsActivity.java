package com.android.settingslib.system;

import android.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public abstract class TvSettingsActivity extends Activity {
    private static final String SETTINGS_FRAGMENT_TAG = "com.android.tv.settings.MainSettings.SETTINGS_FRAGMENT";

    protected abstract Fragment createSettingsFragment();

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            final ViewGroup viewGroup = (ViewGroup) findViewById(R.id.content);
            viewGroup.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.settingslib.system.TvSettingsActivity.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    viewGroup.getViewTreeObserver().removeOnPreDrawListener(this);
                    Scene scene = new Scene(viewGroup);
                    scene.setEnterAction(new Runnable() { // from class: com.android.settingslib.system.TvSettingsActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Fragment fragmentCreateSettingsFragment = TvSettingsActivity.this.createSettingsFragment();
                            if (fragmentCreateSettingsFragment != null) {
                                TvSettingsActivity.this.getFragmentManager().beginTransaction().add(R.id.content, fragmentCreateSettingsFragment, TvSettingsActivity.SETTINGS_FRAGMENT_TAG).commitNow();
                            }
                        }
                    });
                    Slide slide = new Slide(GravityCompat.END);
                    slide.setSlideFraction(TvSettingsActivity.this.getResources().getDimension(com.android.settingslib.R.dimen.lb_settings_pane_width) / viewGroup.getWidth());
                    TransitionManager.go(scene, slide);
                    return false;
                }
            });
        }
    }

    @Override // android.app.Activity
    public void finish() {
        final Fragment fragmentFindFragmentByTag = getFragmentManager().findFragmentByTag(SETTINGS_FRAGMENT_TAG);
        if (isResumed() && fragmentFindFragmentByTag != null) {
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.content);
            Scene scene = new Scene(viewGroup);
            scene.setEnterAction(new Runnable() { // from class: com.android.settingslib.system.TvSettingsActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    TvSettingsActivity.this.getFragmentManager().beginTransaction().remove(fragmentFindFragmentByTag).commitNow();
                }
            });
            Slide slide = new Slide(GravityCompat.END);
            slide.setSlideFraction(getResources().getDimension(com.android.settingslib.R.dimen.lb_settings_pane_width) / viewGroup.getWidth());
            slide.addListener(new Transition.TransitionListener() { // from class: com.android.settingslib.system.TvSettingsActivity.3
                @Override // android.transition.Transition.TransitionListener
                public void onTransitionCancel(Transition transition) {
                }

                @Override // android.transition.Transition.TransitionListener
                public void onTransitionPause(Transition transition) {
                }

                @Override // android.transition.Transition.TransitionListener
                public void onTransitionResume(Transition transition) {
                }

                @Override // android.transition.Transition.TransitionListener
                public void onTransitionStart(Transition transition) {
                    TvSettingsActivity.this.getWindow().setDimAmount(0.0f);
                }

                @Override // android.transition.Transition.TransitionListener
                public void onTransitionEnd(Transition transition) {
                    transition.removeListener(this);
                    TvSettingsActivity.super.finish();
                }
            });
            TransitionManager.go(scene, slide);
            return;
        }
        super.finish();
    }
}
