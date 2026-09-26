package android.support.v17.leanback.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.R;
import android.support.v17.leanback.widget.PagingIndicator;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.util.Property;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class OnboardingSupportFragment extends Fragment {
    private static final boolean DEBUG = false;
    private static final long DESCRIPTION_START_DELAY_MS = 33;
    private static final long HEADER_ANIMATION_DURATION_MS = 417;
    private static final long HEADER_APPEAR_DELAY_MS = 500;
    private static final TimeInterpolator HEADER_APPEAR_INTERPOLATOR = new DecelerateInterpolator();
    private static final TimeInterpolator HEADER_DISAPPEAR_INTERPOLATOR = new AccelerateInterpolator();
    private static final String KEY_CURRENT_PAGE_INDEX = "leanback.onboarding.current_page_index";
    private static final long LOGO_SPLASH_PAUSE_DURATION_MS = 1333;
    private static final int SLIDE_DISTANCE = 60;
    private static final long START_DELAY_DESCRIPTION_MS = 33;
    private static final long START_DELAY_TITLE_MS = 33;
    private static final String TAG = "OnboardingSupportFragment";
    private static int sSlideDistance;
    private AnimatorSet mAnimator;
    int mCurrentPageIndex;
    TextView mDescriptionView;
    boolean mEnterTransitionFinished;
    boolean mIsLtr;
    private int mLogoResourceId;
    private ImageView mLogoView;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: android.support.v17.leanback.app.OnboardingSupportFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (OnboardingSupportFragment.this.mEnterTransitionFinished) {
                if (OnboardingSupportFragment.this.mCurrentPageIndex == OnboardingSupportFragment.this.getPageCount() - 1) {
                    OnboardingSupportFragment.this.onFinishFragment();
                } else {
                    OnboardingSupportFragment.this.moveToNextPage();
                }
            }
        }
    };
    private final View.OnKeyListener mOnKeyListener = new View.OnKeyListener() { // from class: android.support.v17.leanback.app.OnboardingSupportFragment.2
        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (!OnboardingSupportFragment.this.mEnterTransitionFinished) {
                return i != 4;
            }
            if (keyEvent.getAction() == 0) {
                return false;
            }
            if (i == 4) {
                if (OnboardingSupportFragment.this.mCurrentPageIndex == 0) {
                    return false;
                }
                OnboardingSupportFragment.this.moveToPreviousPage();
                return true;
            }
            switch (i) {
                case 21:
                    if (OnboardingSupportFragment.this.mIsLtr) {
                        OnboardingSupportFragment.this.moveToPreviousPage();
                    } else {
                        OnboardingSupportFragment.this.moveToNextPage();
                    }
                    return true;
                case 22:
                    if (OnboardingSupportFragment.this.mIsLtr) {
                        OnboardingSupportFragment.this.moveToNextPage();
                    } else {
                        OnboardingSupportFragment.this.moveToPreviousPage();
                    }
                    return true;
                default:
                    return false;
            }
        }
    };
    PagingIndicator mPageIndicator;
    View mStartButton;
    private ContextThemeWrapper mThemeWrapper;
    TextView mTitleView;

    protected abstract int getPageCount();

    protected abstract CharSequence getPageDescription(int i);

    protected abstract CharSequence getPageTitle(int i);

    @Nullable
    protected abstract View onCreateBackgroundView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    @Nullable
    protected abstract View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    @Nullable
    protected Animator onCreateEnterAnimation() {
        return null;
    }

    @Nullable
    protected abstract View onCreateForegroundView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    @Nullable
    protected Animator onCreateLogoAnimation() {
        return null;
    }

    protected void onFinishFragment() {
    }

    protected void onPageChanged(int i, int i2) {
    }

    public int onProvideTheme() {
        return -1;
    }

    void moveToPreviousPage() {
        if (this.mCurrentPageIndex > 0) {
            this.mCurrentPageIndex--;
            onPageChangedInternal(this.mCurrentPageIndex + 1);
        }
    }

    void moveToNextPage() {
        if (this.mCurrentPageIndex < getPageCount() - 1) {
            this.mCurrentPageIndex++;
            onPageChangedInternal(this.mCurrentPageIndex - 1);
        }
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        resolveTheme();
        final ViewGroup viewGroup2 = (ViewGroup) getThemeInflater(layoutInflater).inflate(R.layout.lb_onboarding_fragment, viewGroup, false);
        this.mIsLtr = getResources().getConfiguration().getLayoutDirection() == 0;
        this.mPageIndicator = (PagingIndicator) viewGroup2.findViewById(R.id.page_indicator);
        this.mPageIndicator.setOnClickListener(this.mOnClickListener);
        this.mPageIndicator.setOnKeyListener(this.mOnKeyListener);
        this.mStartButton = viewGroup2.findViewById(R.id.button_start);
        this.mStartButton.setOnClickListener(this.mOnClickListener);
        this.mStartButton.setOnKeyListener(this.mOnKeyListener);
        this.mLogoView = (ImageView) viewGroup2.findViewById(R.id.logo);
        this.mTitleView = (TextView) viewGroup2.findViewById(R.id.title);
        this.mDescriptionView = (TextView) viewGroup2.findViewById(R.id.description);
        Context context = getContext();
        if (sSlideDistance == 0) {
            sSlideDistance = (int) (context.getResources().getDisplayMetrics().scaledDensity * 60.0f);
        }
        if (bundle == null) {
            this.mCurrentPageIndex = 0;
            this.mEnterTransitionFinished = false;
            this.mPageIndicator.onPageSelected(0, false);
            viewGroup2.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: android.support.v17.leanback.app.OnboardingSupportFragment.3
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    viewGroup2.getViewTreeObserver().removeOnPreDrawListener(this);
                    if (OnboardingSupportFragment.this.startLogoAnimation()) {
                        return true;
                    }
                    OnboardingSupportFragment.this.startEnterAnimation();
                    return true;
                }
            });
        } else {
            this.mEnterTransitionFinished = true;
            this.mCurrentPageIndex = bundle.getInt(KEY_CURRENT_PAGE_INDEX);
            initializeViews(viewGroup2);
        }
        viewGroup2.requestFocus();
        return viewGroup2;
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_CURRENT_PAGE_INDEX, this.mCurrentPageIndex);
    }

    private void resolveTheme() {
        Context context = getContext();
        int iOnProvideTheme = onProvideTheme();
        if (iOnProvideTheme == -1) {
            int i = R.attr.onboardingTheme;
            TypedValue typedValue = new TypedValue();
            if (context.getTheme().resolveAttribute(i, typedValue, true)) {
                this.mThemeWrapper = new ContextThemeWrapper(context, typedValue.resourceId);
                return;
            }
            return;
        }
        this.mThemeWrapper = new ContextThemeWrapper(context, iOnProvideTheme);
    }

    private LayoutInflater getThemeInflater(LayoutInflater layoutInflater) {
        return this.mThemeWrapper == null ? layoutInflater : layoutInflater.cloneInContext(this.mThemeWrapper);
    }

    public final void setLogoResourceId(int i) {
        this.mLogoResourceId = i;
    }

    public final int getLogoResourceId() {
        return this.mLogoResourceId;
    }

    boolean startLogoAnimation() {
        Animator animatorOnCreateLogoAnimation;
        Animator animator;
        AnimatorSet animatorSet;
        final Context context = getContext();
        if (this.mLogoResourceId != 0) {
            this.mLogoView.setVisibility(0);
            this.mLogoView.setImageResource(this.mLogoResourceId);
            Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_logo_enter);
            Animator animatorLoadAnimator2 = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_logo_exit);
            animatorLoadAnimator2.setStartDelay(LOGO_SPLASH_PAUSE_DURATION_MS);
            animatorSet = new AnimatorSet();
            animatorSet.playSequentially(animatorLoadAnimator, animatorLoadAnimator2);
            animatorSet.setTarget(this.mLogoView);
        } else {
            animatorOnCreateLogoAnimation = onCreateLogoAnimation();
        }
        if (animator == null) {
            animator = animatorOnCreateLogoAnimation;
            animator = animatorSet;
            return false;
        }
        animator = animatorOnCreateLogoAnimation;
        animator = animatorSet;
        animator.addListener(new AnimatorListenerAdapter() { // from class: android.support.v17.leanback.app.OnboardingSupportFragment.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                if (context != null) {
                    OnboardingSupportFragment.this.startEnterAnimation();
                }
            }
        });
        animator.start();
        return true;
    }

    private void initializeViews(View view) {
        this.mLogoView.setVisibility(8);
        LayoutInflater themeInflater = getThemeInflater(LayoutInflater.from(getContext()));
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.background_container);
        View viewOnCreateBackgroundView = onCreateBackgroundView(themeInflater, viewGroup);
        if (viewOnCreateBackgroundView != null) {
            viewGroup.setVisibility(0);
            viewGroup.addView(viewOnCreateBackgroundView);
        }
        ViewGroup viewGroup2 = (ViewGroup) view.findViewById(R.id.content_container);
        View viewOnCreateContentView = onCreateContentView(themeInflater, viewGroup2);
        if (viewOnCreateContentView != null) {
            viewGroup2.setVisibility(0);
            viewGroup2.addView(viewOnCreateContentView);
        }
        ViewGroup viewGroup3 = (ViewGroup) view.findViewById(R.id.foreground_container);
        View viewOnCreateForegroundView = onCreateForegroundView(themeInflater, viewGroup3);
        if (viewOnCreateForegroundView != null) {
            viewGroup3.setVisibility(0);
            viewGroup3.addView(viewOnCreateForegroundView);
        }
        view.findViewById(R.id.page_container).setVisibility(0);
        view.findViewById(R.id.content_container).setVisibility(0);
        if (getPageCount() > 1) {
            this.mPageIndicator.setPageCount(getPageCount());
            this.mPageIndicator.onPageSelected(this.mCurrentPageIndex, false);
        }
        if (this.mCurrentPageIndex == getPageCount() - 1) {
            this.mStartButton.setVisibility(0);
        } else {
            this.mPageIndicator.setVisibility(0);
        }
        this.mTitleView.setText(getPageTitle(this.mCurrentPageIndex));
        this.mDescriptionView.setText(getPageDescription(this.mCurrentPageIndex));
    }

    void startEnterAnimation() {
        this.mEnterTransitionFinished = true;
        initializeViews(getView());
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_page_indicator_enter);
        animatorLoadAnimator.setTarget(getPageCount() <= 1 ? this.mStartButton : this.mPageIndicator);
        arrayList.add(animatorLoadAnimator);
        View viewFindViewById = getView().findViewById(R.id.title);
        viewFindViewById.setAlpha(0.0f);
        Animator animatorLoadAnimator2 = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_title_enter);
        animatorLoadAnimator2.setStartDelay(33L);
        animatorLoadAnimator2.setTarget(viewFindViewById);
        arrayList.add(animatorLoadAnimator2);
        View viewFindViewById2 = getView().findViewById(R.id.description);
        viewFindViewById2.setAlpha(0.0f);
        Animator animatorLoadAnimator3 = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_description_enter);
        animatorLoadAnimator3.setStartDelay(33L);
        animatorLoadAnimator3.setTarget(viewFindViewById2);
        arrayList.add(animatorLoadAnimator3);
        Animator animatorOnCreateEnterAnimation = onCreateEnterAnimation();
        if (animatorOnCreateEnterAnimation != null) {
            arrayList.add(animatorOnCreateEnterAnimation);
        }
        this.mAnimator = new AnimatorSet();
        this.mAnimator.playTogether(arrayList);
        this.mAnimator.start();
        getView().requestFocus();
    }

    protected final int getCurrentPageIndex() {
        return this.mCurrentPageIndex;
    }

    private void onPageChangedInternal(int i) {
        Animator animatorCreateAnimator;
        if (this.mAnimator != null) {
            this.mAnimator.end();
        }
        this.mPageIndicator.onPageSelected(this.mCurrentPageIndex, true);
        ArrayList arrayList = new ArrayList();
        if (i < getCurrentPageIndex()) {
            arrayList.add(createAnimator(this.mTitleView, false, GravityCompat.START, 0L));
            animatorCreateAnimator = createAnimator(this.mDescriptionView, false, GravityCompat.START, 33L);
            arrayList.add(animatorCreateAnimator);
            arrayList.add(createAnimator(this.mTitleView, true, GravityCompat.END, HEADER_APPEAR_DELAY_MS));
            arrayList.add(createAnimator(this.mDescriptionView, true, GravityCompat.END, 533L));
        } else {
            arrayList.add(createAnimator(this.mTitleView, false, GravityCompat.END, 0L));
            animatorCreateAnimator = createAnimator(this.mDescriptionView, false, GravityCompat.END, 33L);
            arrayList.add(animatorCreateAnimator);
            arrayList.add(createAnimator(this.mTitleView, true, GravityCompat.START, HEADER_APPEAR_DELAY_MS));
            arrayList.add(createAnimator(this.mDescriptionView, true, GravityCompat.START, 533L));
        }
        final int currentPageIndex = getCurrentPageIndex();
        animatorCreateAnimator.addListener(new AnimatorListenerAdapter() { // from class: android.support.v17.leanback.app.OnboardingSupportFragment.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                OnboardingSupportFragment.this.mTitleView.setText(OnboardingSupportFragment.this.getPageTitle(currentPageIndex));
                OnboardingSupportFragment.this.mDescriptionView.setText(OnboardingSupportFragment.this.getPageDescription(currentPageIndex));
            }
        });
        Context context = getContext();
        if (getCurrentPageIndex() == getPageCount() - 1) {
            this.mStartButton.setVisibility(0);
            Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_page_indicator_fade_out);
            animatorLoadAnimator.setTarget(this.mPageIndicator);
            animatorLoadAnimator.addListener(new AnimatorListenerAdapter() { // from class: android.support.v17.leanback.app.OnboardingSupportFragment.6
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    OnboardingSupportFragment.this.mPageIndicator.setVisibility(8);
                }
            });
            arrayList.add(animatorLoadAnimator);
            Animator animatorLoadAnimator2 = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_start_button_fade_in);
            animatorLoadAnimator2.setTarget(this.mStartButton);
            arrayList.add(animatorLoadAnimator2);
        } else if (i == getPageCount() - 1) {
            this.mPageIndicator.setVisibility(0);
            Animator animatorLoadAnimator3 = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_page_indicator_fade_in);
            animatorLoadAnimator3.setTarget(this.mPageIndicator);
            arrayList.add(animatorLoadAnimator3);
            Animator animatorLoadAnimator4 = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_start_button_fade_out);
            animatorLoadAnimator4.setTarget(this.mStartButton);
            animatorLoadAnimator4.addListener(new AnimatorListenerAdapter() { // from class: android.support.v17.leanback.app.OnboardingSupportFragment.7
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    OnboardingSupportFragment.this.mStartButton.setVisibility(8);
                }
            });
            arrayList.add(animatorLoadAnimator4);
        }
        this.mAnimator = new AnimatorSet();
        this.mAnimator.playTogether(arrayList);
        this.mAnimator.start();
        onPageChanged(this.mCurrentPageIndex, i);
    }

    private Animator createAnimator(View view, boolean z, int i, long j) {
        ObjectAnimator objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2;
        boolean z2 = getView().getLayoutDirection() == 0;
        boolean z3 = (z2 && i == 8388613) || (!z2 && i == 8388611) || i == 5;
        if (z) {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f);
            Property property = View.TRANSLATION_X;
            float[] fArr = new float[2];
            fArr[0] = z3 ? sSlideDistance : -sSlideDistance;
            fArr[1] = 0.0f;
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fArr);
            objectAnimatorOfFloat.setInterpolator(HEADER_APPEAR_INTERPOLATOR);
            objectAnimatorOfFloat2.setInterpolator(HEADER_APPEAR_INTERPOLATOR);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f, 0.0f);
            Property property2 = View.TRANSLATION_X;
            float[] fArr2 = new float[2];
            fArr2[0] = 0.0f;
            fArr2[1] = z3 ? sSlideDistance : -sSlideDistance;
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) property2, fArr2);
            objectAnimatorOfFloat.setInterpolator(HEADER_DISAPPEAR_INTERPOLATOR);
            objectAnimatorOfFloat2.setInterpolator(HEADER_DISAPPEAR_INTERPOLATOR);
        }
        objectAnimatorOfFloat.setDuration(HEADER_ANIMATION_DURATION_MS);
        objectAnimatorOfFloat.setTarget(view);
        objectAnimatorOfFloat2.setDuration(HEADER_ANIMATION_DURATION_MS);
        objectAnimatorOfFloat2.setTarget(view);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        if (j > 0) {
            animatorSet.setStartDelay(j);
        }
        return animatorSet;
    }
}
