package android.support.v17.leanback.app;

import android.os.Bundle;
import android.support.v17.leanback.transition.TransitionHelper;
import android.support.v17.leanback.transition.TransitionListener;
import android.support.v17.leanback.util.StateMachine;
import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
class BaseFragment extends BrandedFragment {
    Object mEntranceTransition;
    private final StateMachine.State STATE_ALLOWED = new StateMachine.State() { // from class: android.support.v17.leanback.app.BaseFragment.1
        @Override // android.support.v17.leanback.util.StateMachine.State
        public boolean canRun() {
            return TransitionHelper.systemSupportsEntranceTransitions();
        }

        @Override // android.support.v17.leanback.util.StateMachine.State
        public void run() {
            BaseFragment.this.mProgressBarManager.show();
        }
    };
    private final StateMachine.State STATE_PREPARE = new StateMachine.State() { // from class: android.support.v17.leanback.app.BaseFragment.2
        @Override // android.support.v17.leanback.util.StateMachine.State
        public boolean canRun() {
            return BaseFragment.this.isReadyForPrepareEntranceTransition();
        }

        @Override // android.support.v17.leanback.util.StateMachine.State
        public void run() {
            BaseFragment.this.onEntranceTransitionPrepare();
        }
    };
    private final StateMachine.State STATE_START = new StateMachine.State() { // from class: android.support.v17.leanback.app.BaseFragment.3
        @Override // android.support.v17.leanback.util.StateMachine.State
        public boolean canRun() {
            return BaseFragment.this.isReadyForStartEntranceTransition();
        }

        @Override // android.support.v17.leanback.util.StateMachine.State
        public void run() {
            BaseFragment.this.mProgressBarManager.hide();
            BaseFragment.this.onExecuteEntranceTransition();
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

    BaseFragment() {
        this.mEnterTransitionStates.addState(this.STATE_ALLOWED);
        this.mEnterTransitionStates.addState(this.STATE_PREPARE);
        this.mEnterTransitionStates.addState(this.STATE_START);
    }

    @Override // android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
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
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: android.support.v17.leanback.app.BaseFragment.4
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                if (FragmentUtil.getContext(BaseFragment.this) == null || BaseFragment.this.getView() == null) {
                    return true;
                }
                BaseFragment.this.internalCreateEntranceTransition();
                if (BaseFragment.this.mEntranceTransition == null) {
                    return false;
                }
                BaseFragment.this.onEntranceTransitionStart();
                BaseFragment.this.runEntranceTransition(BaseFragment.this.mEntranceTransition);
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
        TransitionHelper.addTransitionListener(this.mEntranceTransition, new TransitionListener() { // from class: android.support.v17.leanback.app.BaseFragment.5
            @Override // android.support.v17.leanback.transition.TransitionListener
            public void onTransitionEnd(Object obj) {
                BaseFragment.this.mEntranceTransition = null;
                BaseFragment.this.onEntranceTransitionEnd();
                BaseFragment.this.mEnterTransitionStates.resetStatus();
            }
        });
    }

    public final ProgressBarManager getProgressBarManager() {
        return this.mProgressBarManager;
    }
}
