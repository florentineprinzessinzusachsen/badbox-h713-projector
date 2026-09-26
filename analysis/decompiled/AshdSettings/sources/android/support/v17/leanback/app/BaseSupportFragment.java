package android.support.v17.leanback.app;

import android.os.Bundle;
import android.support.v17.leanback.transition.TransitionHelper;
import android.support.v17.leanback.transition.TransitionListener;
import android.support.v17.leanback.util.StateMachine;
import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
class BaseSupportFragment extends BrandedSupportFragment {
    Object mEntranceTransition;
    private final StateMachine.State STATE_ALLOWED = new StateMachine.State() { // from class: android.support.v17.leanback.app.BaseSupportFragment.1
        @Override // android.support.v17.leanback.util.StateMachine.State
        public boolean canRun() {
            return TransitionHelper.systemSupportsEntranceTransitions();
        }

        @Override // android.support.v17.leanback.util.StateMachine.State
        public void run() {
            BaseSupportFragment.this.mProgressBarManager.show();
        }
    };
    private final StateMachine.State STATE_PREPARE = new StateMachine.State() { // from class: android.support.v17.leanback.app.BaseSupportFragment.2
        @Override // android.support.v17.leanback.util.StateMachine.State
        public boolean canRun() {
            return BaseSupportFragment.this.isReadyForPrepareEntranceTransition();
        }

        @Override // android.support.v17.leanback.util.StateMachine.State
        public void run() {
            BaseSupportFragment.this.onEntranceTransitionPrepare();
        }
    };
    private final StateMachine.State STATE_START = new StateMachine.State() { // from class: android.support.v17.leanback.app.BaseSupportFragment.3
        @Override // android.support.v17.leanback.util.StateMachine.State
        public boolean canRun() {
            return BaseSupportFragment.this.isReadyForStartEntranceTransition();
        }

        @Override // android.support.v17.leanback.util.StateMachine.State
        public void run() {
            BaseSupportFragment.this.mProgressBarManager.hide();
            BaseSupportFragment.this.onExecuteEntranceTransition();
        }
    };
    final ProgressBarManager mProgressBarManager = new ProgressBarManager();
    final StateMachine mEnterTransitionStates = new StateMachine();

    protected Object createEntranceTransition() {
        return null;
    }

    protected void onEntranceTransitionEnd() {
    }

    protected void onEntranceTransitionPrepare() {
    }

    protected void onEntranceTransitionStart() {
    }

    protected void runEntranceTransition(Object obj) {
    }

    BaseSupportFragment() {
        this.mEnterTransitionStates.addState(this.STATE_ALLOWED);
        this.mEnterTransitionStates.addState(this.STATE_PREPARE);
        this.mEnterTransitionStates.addState(this.STATE_START);
    }

    @Override // android.support.v17.leanback.app.BrandedSupportFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        performPendingStates();
    }

    final void performPendingStates() {
        this.mEnterTransitionStates.runPendingStates();
    }

    public void prepareEntranceTransition() {
        this.mEnterTransitionStates.runState(this.STATE_ALLOWED);
        this.mEnterTransitionStates.runState(this.STATE_PREPARE);
    }

    boolean isEntranceTransitionEnabled() {
        return this.STATE_ALLOWED.getStatus() == 2;
    }

    boolean isReadyForPrepareEntranceTransition() {
        return getView() != null;
    }

    boolean isReadyForStartEntranceTransition() {
        return getView() != null;
    }

    public void startEntranceTransition() {
        this.mEnterTransitionStates.runState(this.STATE_START);
    }

    void onExecuteEntranceTransition() {
        final View view = getView();
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: android.support.v17.leanback.app.BaseSupportFragment.4
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                if (BaseSupportFragment.this.getContext() == null || BaseSupportFragment.this.getView() == null) {
                    return true;
                }
                BaseSupportFragment.this.internalCreateEntranceTransition();
                if (BaseSupportFragment.this.mEntranceTransition == null) {
                    return false;
                }
                BaseSupportFragment.this.onEntranceTransitionStart();
                BaseSupportFragment.this.runEntranceTransition(BaseSupportFragment.this.mEntranceTransition);
                return false;
            }
        });
        view.invalidate();
    }

    void internalCreateEntranceTransition() {
        this.mEntranceTransition = createEntranceTransition();
        if (this.mEntranceTransition == null) {
            return;
        }
        TransitionHelper.addTransitionListener(this.mEntranceTransition, new TransitionListener() { // from class: android.support.v17.leanback.app.BaseSupportFragment.5
            @Override // android.support.v17.leanback.transition.TransitionListener
            public void onTransitionEnd(Object obj) {
                BaseSupportFragment.this.mEntranceTransition = null;
                BaseSupportFragment.this.onEntranceTransitionEnd();
                BaseSupportFragment.this.mEnterTransitionStates.resetStatus();
            }
        });
    }

    public final ProgressBarManager getProgressBarManager() {
        return this.mProgressBarManager;
    }
}
