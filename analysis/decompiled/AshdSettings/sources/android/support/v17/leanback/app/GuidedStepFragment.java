package android.support.v17.leanback.app;

import android.R;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v17.leanback.transition.TransitionHelper;
import android.support.v17.leanback.widget.GuidanceStylist;
import android.support.v17.leanback.widget.GuidedAction;
import android.support.v17.leanback.widget.GuidedActionAdapter;
import android.support.v17.leanback.widget.GuidedActionAdapterGroup;
import android.support.v17.leanback.widget.GuidedActionsStylist;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GuidedStepFragment extends Fragment implements GuidedActionAdapter.FocusListener {
    private static final boolean DEBUG = false;
    private static final String ENTRY_NAME_ENTRANCE = "GuidedStepEntrance";
    private static final String ENTRY_NAME_REPLACE = "GuidedStepDefault";
    private static final String EXTRA_ACTION_PREFIX = "action_";
    private static final String EXTRA_BUTTON_ACTION_PREFIX = "buttonaction_";
    public static final String EXTRA_UI_STYLE = "uiStyle";
    private static final boolean IS_FRAMEWORK_FRAGMENT = true;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int SLIDE_FROM_BOTTOM = 1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int SLIDE_FROM_SIDE = 0;
    private static final String TAG = "GuidedStepFragment";
    private static final String TAG_LEAN_BACK_ACTIONS_FRAGMENT = "leanBackGuidedStepFragment";
    public static final int UI_STYLE_ACTIVITY_ROOT = 2;

    @Deprecated
    public static final int UI_STYLE_DEFAULT = 0;
    public static final int UI_STYLE_ENTRANCE = 1;
    public static final int UI_STYLE_REPLACE = 0;
    private GuidedActionAdapter mAdapter;
    private GuidedActionAdapterGroup mAdapterGroup;
    private GuidedActionAdapter mButtonAdapter;
    private GuidedActionAdapter mSubAdapter;
    private ContextThemeWrapper mThemeWrapper;
    private List<GuidedAction> mActions = new ArrayList();
    private List<GuidedAction> mButtonActions = new ArrayList();
    private int entranceTransitionType = 0;
    private GuidanceStylist mGuidanceStylist = onCreateGuidanceStylist();
    GuidedActionsStylist mActionsStylist = onCreateActionsStylist();
    private GuidedActionsStylist mButtonActionsStylist = onCreateButtonActionsStylist();

    public boolean isFocusOutEndAllowed() {
        return false;
    }

    public boolean isFocusOutStartAllowed() {
        return false;
    }

    public void onCreateActions(@NonNull List<GuidedAction> list, Bundle bundle) {
    }

    public void onCreateButtonActions(@NonNull List<GuidedAction> list, Bundle bundle) {
    }

    public void onGuidedActionClicked(GuidedAction guidedAction) {
    }

    @Deprecated
    public void onGuidedActionEdited(GuidedAction guidedAction) {
    }

    @Override // android.support.v17.leanback.widget.GuidedActionAdapter.FocusListener
    public void onGuidedActionFocused(GuidedAction guidedAction) {
    }

    public int onProvideTheme() {
        return -1;
    }

    public boolean onSubGuidedActionClicked(GuidedAction guidedAction) {
        return true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class DummyFragment extends Fragment {
        @Override // android.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View view = new View(layoutInflater.getContext());
            view.setVisibility(8);
            return view;
        }
    }

    public GuidedStepFragment() {
        onProvideFragmentTransitions();
    }

    public GuidanceStylist onCreateGuidanceStylist() {
        return new GuidanceStylist();
    }

    public GuidedActionsStylist onCreateActionsStylist() {
        return new GuidedActionsStylist();
    }

    public GuidedActionsStylist onCreateButtonActionsStylist() {
        GuidedActionsStylist guidedActionsStylist = new GuidedActionsStylist();
        guidedActionsStylist.setAsButtonActions();
        return guidedActionsStylist;
    }

    @NonNull
    public GuidanceStylist.Guidance onCreateGuidance(Bundle bundle) {
        return new GuidanceStylist.Guidance("", "", "", null);
    }

    public boolean isExpanded() {
        return this.mActionsStylist.isExpanded();
    }

    public boolean isSubActionsExpanded() {
        return this.mActionsStylist.isSubActionsExpanded();
    }

    public void expandSubActions(GuidedAction guidedAction) {
        if (guidedAction.hasSubActions()) {
            expandAction(guidedAction, true);
        }
    }

    public void expandAction(GuidedAction guidedAction, boolean z) {
        this.mActionsStylist.expandAction(guidedAction, z);
    }

    public void collapseSubActions() {
        collapseAction(true);
    }

    public void collapseAction(boolean z) {
        if (this.mActionsStylist == null || this.mActionsStylist.getActionsGridView() == null) {
            return;
        }
        this.mActionsStylist.collapseAction(z);
    }

    public void onGuidedActionEditCanceled(GuidedAction guidedAction) {
        onGuidedActionEdited(guidedAction);
    }

    public long onGuidedActionEditedAndProceed(GuidedAction guidedAction) {
        onGuidedActionEdited(guidedAction);
        return -2L;
    }

    public static int add(FragmentManager fragmentManager, GuidedStepFragment guidedStepFragment) {
        return add(fragmentManager, guidedStepFragment, R.id.content);
    }

    public static int add(FragmentManager fragmentManager, GuidedStepFragment guidedStepFragment, int i) {
        GuidedStepFragment currentGuidedStepFragment = getCurrentGuidedStepFragment(fragmentManager);
        int i2 = currentGuidedStepFragment != null ? 1 : 0;
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23 && i2 == 0) {
            fragmentManager.beginTransaction().replace(i, new DummyFragment(), TAG_LEAN_BACK_ACTIONS_FRAGMENT).commit();
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        guidedStepFragment.setUiStyle(1 ^ i2);
        fragmentTransactionBeginTransaction.addToBackStack(guidedStepFragment.generateStackEntryName());
        if (currentGuidedStepFragment != null) {
            guidedStepFragment.onAddSharedElementTransition(fragmentTransactionBeginTransaction, currentGuidedStepFragment);
        }
        return fragmentTransactionBeginTransaction.replace(i, guidedStepFragment, TAG_LEAN_BACK_ACTIONS_FRAGMENT).commit();
    }

    protected void onAddSharedElementTransition(FragmentTransaction fragmentTransaction, GuidedStepFragment guidedStepFragment) {
        View view = guidedStepFragment.getView();
        addNonNullSharedElementTransition(fragmentTransaction, view.findViewById(android.support.v17.leanback.R.id.action_fragment_root), "action_fragment_root");
        addNonNullSharedElementTransition(fragmentTransaction, view.findViewById(android.support.v17.leanback.R.id.action_fragment_background), "action_fragment_background");
        addNonNullSharedElementTransition(fragmentTransaction, view.findViewById(android.support.v17.leanback.R.id.action_fragment), "action_fragment");
        addNonNullSharedElementTransition(fragmentTransaction, view.findViewById(android.support.v17.leanback.R.id.guidedactions_root), "guidedactions_root");
        addNonNullSharedElementTransition(fragmentTransaction, view.findViewById(android.support.v17.leanback.R.id.guidedactions_content), "guidedactions_content");
        addNonNullSharedElementTransition(fragmentTransaction, view.findViewById(android.support.v17.leanback.R.id.guidedactions_list_background), "guidedactions_list_background");
        addNonNullSharedElementTransition(fragmentTransaction, view.findViewById(android.support.v17.leanback.R.id.guidedactions_root2), "guidedactions_root2");
        addNonNullSharedElementTransition(fragmentTransaction, view.findViewById(android.support.v17.leanback.R.id.guidedactions_content2), "guidedactions_content2");
        addNonNullSharedElementTransition(fragmentTransaction, view.findViewById(android.support.v17.leanback.R.id.guidedactions_list_background2), "guidedactions_list_background2");
    }

    private static void addNonNullSharedElementTransition(FragmentTransaction fragmentTransaction, View view, String str) {
        if (view != null) {
            TransitionHelper.addSharedElement(fragmentTransaction, view, str);
        }
    }

    final String generateStackEntryName() {
        return generateStackEntryName(getUiStyle(), getClass());
    }

    static String generateStackEntryName(int i, Class cls) {
        switch (i) {
            case 0:
                return ENTRY_NAME_REPLACE + cls.getName();
            case 1:
                return ENTRY_NAME_ENTRANCE + cls.getName();
            default:
                return "";
        }
    }

    static boolean isStackEntryUiStyleEntrance(String str) {
        return str != null && str.startsWith(ENTRY_NAME_ENTRANCE);
    }

    static String getGuidedStepFragmentClassName(String str) {
        if (str.startsWith(ENTRY_NAME_REPLACE)) {
            return str.substring(ENTRY_NAME_REPLACE.length());
        }
        return str.startsWith(ENTRY_NAME_ENTRANCE) ? str.substring(ENTRY_NAME_ENTRANCE.length()) : "";
    }

    public static int addAsRoot(Activity activity, GuidedStepFragment guidedStepFragment, int i) {
        activity.getWindow().getDecorView();
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag(TAG_LEAN_BACK_ACTIONS_FRAGMENT) != null) {
            Log.w(TAG, "Fragment is already exists, likely calling addAsRoot() when savedInstanceState is not null in Activity.onCreate().");
            return -1;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        guidedStepFragment.setUiStyle(2);
        return fragmentTransactionBeginTransaction.replace(i, guidedStepFragment, TAG_LEAN_BACK_ACTIONS_FRAGMENT).commit();
    }

    public static GuidedStepFragment getCurrentGuidedStepFragment(FragmentManager fragmentManager) {
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(TAG_LEAN_BACK_ACTIONS_FRAGMENT);
        if (fragmentFindFragmentByTag instanceof GuidedStepFragment) {
            return (GuidedStepFragment) fragmentFindFragmentByTag;
        }
        return null;
    }

    public GuidanceStylist getGuidanceStylist() {
        return this.mGuidanceStylist;
    }

    public GuidedActionsStylist getGuidedActionsStylist() {
        return this.mActionsStylist;
    }

    public List<GuidedAction> getButtonActions() {
        return this.mButtonActions;
    }

    public GuidedAction findButtonActionById(long j) {
        int iFindButtonActionPositionById = findButtonActionPositionById(j);
        if (iFindButtonActionPositionById >= 0) {
            return this.mButtonActions.get(iFindButtonActionPositionById);
        }
        return null;
    }

    public int findButtonActionPositionById(long j) {
        if (this.mButtonActions == null) {
            return -1;
        }
        for (int i = 0; i < this.mButtonActions.size(); i++) {
            this.mButtonActions.get(i);
            if (this.mButtonActions.get(i).getId() == j) {
                return i;
            }
        }
        return -1;
    }

    public GuidedActionsStylist getGuidedButtonActionsStylist() {
        return this.mButtonActionsStylist;
    }

    public void setButtonActions(List<GuidedAction> list) {
        this.mButtonActions = list;
        if (this.mButtonAdapter != null) {
            this.mButtonAdapter.setActions(this.mButtonActions);
        }
    }

    public void notifyButtonActionChanged(int i) {
        if (this.mButtonAdapter != null) {
            this.mButtonAdapter.notifyItemChanged(i);
        }
    }

    public View getButtonActionItemView(int i) {
        RecyclerView.ViewHolder viewHolderFindViewHolderForPosition = this.mButtonActionsStylist.getActionsGridView().findViewHolderForPosition(i);
        if (viewHolderFindViewHolderForPosition == null) {
            return null;
        }
        return viewHolderFindViewHolderForPosition.itemView;
    }

    public void setSelectedButtonActionPosition(int i) {
        this.mButtonActionsStylist.getActionsGridView().setSelectedPosition(i);
    }

    public int getSelectedButtonActionPosition() {
        return this.mButtonActionsStylist.getActionsGridView().getSelectedPosition();
    }

    public List<GuidedAction> getActions() {
        return this.mActions;
    }

    public GuidedAction findActionById(long j) {
        int iFindActionPositionById = findActionPositionById(j);
        if (iFindActionPositionById >= 0) {
            return this.mActions.get(iFindActionPositionById);
        }
        return null;
    }

    public int findActionPositionById(long j) {
        if (this.mActions == null) {
            return -1;
        }
        for (int i = 0; i < this.mActions.size(); i++) {
            this.mActions.get(i);
            if (this.mActions.get(i).getId() == j) {
                return i;
            }
        }
        return -1;
    }

    public void setActions(List<GuidedAction> list) {
        this.mActions = list;
        if (this.mAdapter != null) {
            this.mAdapter.setActions(this.mActions);
        }
    }

    public void notifyActionChanged(int i) {
        if (this.mAdapter != null) {
            this.mAdapter.notifyItemChanged(i);
        }
    }

    public View getActionItemView(int i) {
        RecyclerView.ViewHolder viewHolderFindViewHolderForPosition = this.mActionsStylist.getActionsGridView().findViewHolderForPosition(i);
        if (viewHolderFindViewHolderForPosition == null) {
            return null;
        }
        return viewHolderFindViewHolderForPosition.itemView;
    }

    public void setSelectedActionPosition(int i) {
        this.mActionsStylist.getActionsGridView().setSelectedPosition(i);
    }

    public int getSelectedActionPosition() {
        return this.mActionsStylist.getActionsGridView().getSelectedPosition();
    }

    protected void onProvideFragmentTransitions() {
        if (Build.VERSION.SDK_INT >= 21) {
            int uiStyle = getUiStyle();
            if (uiStyle == 0) {
                Object objCreateFadeAndShortSlide = TransitionHelper.createFadeAndShortSlide(GravityCompat.END);
                TransitionHelper.exclude(objCreateFadeAndShortSlide, android.support.v17.leanback.R.id.guidedstep_background, true);
                TransitionHelper.exclude(objCreateFadeAndShortSlide, android.support.v17.leanback.R.id.guidedactions_sub_list_background, true);
                TransitionHelper.setEnterTransition(this, objCreateFadeAndShortSlide);
                Object objCreateFadeTransition = TransitionHelper.createFadeTransition(3);
                TransitionHelper.include(objCreateFadeTransition, android.support.v17.leanback.R.id.guidedactions_sub_list_background);
                Object objCreateChangeBounds = TransitionHelper.createChangeBounds(false);
                Object objCreateTransitionSet = TransitionHelper.createTransitionSet(false);
                TransitionHelper.addTransition(objCreateTransitionSet, objCreateFadeTransition);
                TransitionHelper.addTransition(objCreateTransitionSet, objCreateChangeBounds);
                TransitionHelper.setSharedElementEnterTransition(this, objCreateTransitionSet);
            } else if (uiStyle == 1) {
                if (this.entranceTransitionType == 0) {
                    Object objCreateFadeTransition2 = TransitionHelper.createFadeTransition(3);
                    TransitionHelper.include(objCreateFadeTransition2, android.support.v17.leanback.R.id.guidedstep_background);
                    Object objCreateFadeAndShortSlide2 = TransitionHelper.createFadeAndShortSlide(GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK);
                    TransitionHelper.include(objCreateFadeAndShortSlide2, android.support.v17.leanback.R.id.content_fragment);
                    TransitionHelper.include(objCreateFadeAndShortSlide2, android.support.v17.leanback.R.id.action_fragment_root);
                    Object objCreateTransitionSet2 = TransitionHelper.createTransitionSet(false);
                    TransitionHelper.addTransition(objCreateTransitionSet2, objCreateFadeTransition2);
                    TransitionHelper.addTransition(objCreateTransitionSet2, objCreateFadeAndShortSlide2);
                    TransitionHelper.setEnterTransition(this, objCreateTransitionSet2);
                } else {
                    Object objCreateFadeAndShortSlide3 = TransitionHelper.createFadeAndShortSlide(80);
                    TransitionHelper.include(objCreateFadeAndShortSlide3, android.support.v17.leanback.R.id.guidedstep_background_view_root);
                    Object objCreateTransitionSet3 = TransitionHelper.createTransitionSet(false);
                    TransitionHelper.addTransition(objCreateTransitionSet3, objCreateFadeAndShortSlide3);
                    TransitionHelper.setEnterTransition(this, objCreateTransitionSet3);
                }
                TransitionHelper.setSharedElementEnterTransition(this, (Object) null);
            } else if (uiStyle == 2) {
                TransitionHelper.setEnterTransition(this, (Object) null);
                TransitionHelper.setSharedElementEnterTransition(this, (Object) null);
            }
            Object objCreateFadeAndShortSlide4 = TransitionHelper.createFadeAndShortSlide(GravityCompat.START);
            TransitionHelper.exclude(objCreateFadeAndShortSlide4, android.support.v17.leanback.R.id.guidedstep_background, true);
            TransitionHelper.exclude(objCreateFadeAndShortSlide4, android.support.v17.leanback.R.id.guidedactions_sub_list_background, true);
            TransitionHelper.setExitTransition(this, objCreateFadeAndShortSlide4);
        }
    }

    public View onCreateBackgroundView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(android.support.v17.leanback.R.layout.lb_guidedstep_background, viewGroup, false);
    }

    public void setUiStyle(int i) {
        boolean z;
        int uiStyle = getUiStyle();
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            z = true;
        } else {
            z = false;
        }
        arguments.putInt("uiStyle", i);
        if (z) {
            setArguments(arguments);
        }
        if (i != uiStyle) {
            onProvideFragmentTransitions();
        }
    }

    public int getUiStyle() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return 1;
        }
        return arguments.getInt("uiStyle", 1);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        onProvideFragmentTransitions();
        ArrayList arrayList = new ArrayList();
        onCreateActions(arrayList, bundle);
        if (bundle != null) {
            onRestoreActions(arrayList, bundle);
        }
        setActions(arrayList);
        ArrayList arrayList2 = new ArrayList();
        onCreateButtonActions(arrayList2, bundle);
        if (bundle != null) {
            onRestoreButtonActions(arrayList2, bundle);
        }
        setButtonActions(arrayList2);
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        this.mGuidanceStylist.onDestroyView();
        this.mActionsStylist.onDestroyView();
        this.mButtonActionsStylist.onDestroyView();
        this.mAdapter = null;
        this.mSubAdapter = null;
        this.mButtonAdapter = null;
        this.mAdapterGroup = null;
        super.onDestroyView();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        resolveTheme();
        LayoutInflater themeInflater = getThemeInflater(layoutInflater);
        GuidedStepRootLayout guidedStepRootLayout = (GuidedStepRootLayout) themeInflater.inflate(android.support.v17.leanback.R.layout.lb_guidedstep_fragment, viewGroup, false);
        guidedStepRootLayout.setFocusOutStart(isFocusOutStartAllowed());
        guidedStepRootLayout.setFocusOutEnd(isFocusOutEndAllowed());
        ViewGroup viewGroup2 = (ViewGroup) guidedStepRootLayout.findViewById(android.support.v17.leanback.R.id.content_fragment);
        ViewGroup viewGroup3 = (ViewGroup) guidedStepRootLayout.findViewById(android.support.v17.leanback.R.id.action_fragment);
        viewGroup2.addView(this.mGuidanceStylist.onCreateView(themeInflater, viewGroup2, onCreateGuidance(bundle)));
        viewGroup3.addView(this.mActionsStylist.onCreateView(themeInflater, viewGroup3));
        View viewOnCreateView = this.mButtonActionsStylist.onCreateView(themeInflater, viewGroup3);
        viewGroup3.addView(viewOnCreateView);
        GuidedActionAdapter.EditListener editListener = new GuidedActionAdapter.EditListener() { // from class: android.support.v17.leanback.app.GuidedStepFragment.1
            @Override // android.support.v17.leanback.widget.GuidedActionAdapter.EditListener
            public void onImeOpen() {
                GuidedStepFragment.this.runImeAnimations(true);
            }

            @Override // android.support.v17.leanback.widget.GuidedActionAdapter.EditListener
            public void onImeClose() {
                GuidedStepFragment.this.runImeAnimations(false);
            }

            @Override // android.support.v17.leanback.widget.GuidedActionAdapter.EditListener
            public long onGuidedActionEditedAndProceed(GuidedAction guidedAction) {
                return GuidedStepFragment.this.onGuidedActionEditedAndProceed(guidedAction);
            }

            @Override // android.support.v17.leanback.widget.GuidedActionAdapter.EditListener
            public void onGuidedActionEditCanceled(GuidedAction guidedAction) {
                GuidedStepFragment.this.onGuidedActionEditCanceled(guidedAction);
            }
        };
        this.mAdapter = new GuidedActionAdapter(this.mActions, new GuidedActionAdapter.ClickListener() { // from class: android.support.v17.leanback.app.GuidedStepFragment.2
            @Override // android.support.v17.leanback.widget.GuidedActionAdapter.ClickListener
            public void onGuidedActionClicked(GuidedAction guidedAction) {
                GuidedStepFragment.this.onGuidedActionClicked(guidedAction);
                if (GuidedStepFragment.this.isExpanded()) {
                    GuidedStepFragment.this.collapseAction(true);
                } else if (guidedAction.hasSubActions() || guidedAction.hasEditableActivatorView()) {
                    GuidedStepFragment.this.expandAction(guidedAction, true);
                }
            }
        }, this, this.mActionsStylist, false);
        this.mButtonAdapter = new GuidedActionAdapter(this.mButtonActions, new GuidedActionAdapter.ClickListener() { // from class: android.support.v17.leanback.app.GuidedStepFragment.3
            @Override // android.support.v17.leanback.widget.GuidedActionAdapter.ClickListener
            public void onGuidedActionClicked(GuidedAction guidedAction) {
                GuidedStepFragment.this.onGuidedActionClicked(guidedAction);
            }
        }, this, this.mButtonActionsStylist, false);
        this.mSubAdapter = new GuidedActionAdapter(null, new GuidedActionAdapter.ClickListener() { // from class: android.support.v17.leanback.app.GuidedStepFragment.4
            @Override // android.support.v17.leanback.widget.GuidedActionAdapter.ClickListener
            public void onGuidedActionClicked(GuidedAction guidedAction) {
                if (!GuidedStepFragment.this.mActionsStylist.isInExpandTransition() && GuidedStepFragment.this.onSubGuidedActionClicked(guidedAction)) {
                    GuidedStepFragment.this.collapseSubActions();
                }
            }
        }, this, this.mActionsStylist, true);
        this.mAdapterGroup = new GuidedActionAdapterGroup();
        this.mAdapterGroup.addAdpter(this.mAdapter, this.mButtonAdapter);
        this.mAdapterGroup.addAdpter(this.mSubAdapter, null);
        this.mAdapterGroup.setEditListener(editListener);
        this.mActionsStylist.setEditListener(editListener);
        this.mActionsStylist.getActionsGridView().setAdapter(this.mAdapter);
        if (this.mActionsStylist.getSubActionsGridView() != null) {
            this.mActionsStylist.getSubActionsGridView().setAdapter(this.mSubAdapter);
        }
        this.mButtonActionsStylist.getActionsGridView().setAdapter(this.mButtonAdapter);
        if (this.mButtonActions.size() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewOnCreateView.getLayoutParams();
            layoutParams.weight = 0.0f;
            viewOnCreateView.setLayoutParams(layoutParams);
        } else {
            Context context = this.mThemeWrapper != null ? this.mThemeWrapper : FragmentUtil.getContext(this);
            TypedValue typedValue = new TypedValue();
            if (context.getTheme().resolveAttribute(android.support.v17.leanback.R.attr.guidedActionContentWidthWeightTwoPanels, typedValue, true)) {
                View viewFindViewById = guidedStepRootLayout.findViewById(android.support.v17.leanback.R.id.action_fragment_root);
                float f = typedValue.getFloat();
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) viewFindViewById.getLayoutParams();
                layoutParams2.weight = f;
                viewFindViewById.setLayoutParams(layoutParams2);
            }
        }
        View viewOnCreateBackgroundView = onCreateBackgroundView(themeInflater, guidedStepRootLayout, bundle);
        if (viewOnCreateBackgroundView != null) {
            ((FrameLayout) guidedStepRootLayout.findViewById(android.support.v17.leanback.R.id.guidedstep_background_view_root)).addView(viewOnCreateBackgroundView, 0);
        }
        return guidedStepRootLayout;
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        getView().findViewById(android.support.v17.leanback.R.id.action_fragment).requestFocus();
    }

    final String getAutoRestoreKey(GuidedAction guidedAction) {
        return EXTRA_ACTION_PREFIX + guidedAction.getId();
    }

    final String getButtonAutoRestoreKey(GuidedAction guidedAction) {
        return EXTRA_BUTTON_ACTION_PREFIX + guidedAction.getId();
    }

    static final boolean isSaveEnabled(GuidedAction guidedAction) {
        return guidedAction.isAutoSaveRestoreEnabled() && guidedAction.getId() != -1;
    }

    final void onRestoreActions(List<GuidedAction> list, Bundle bundle) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            GuidedAction guidedAction = list.get(i);
            if (isSaveEnabled(guidedAction)) {
                guidedAction.onRestoreInstanceState(bundle, getAutoRestoreKey(guidedAction));
            }
        }
    }

    final void onRestoreButtonActions(List<GuidedAction> list, Bundle bundle) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            GuidedAction guidedAction = list.get(i);
            if (isSaveEnabled(guidedAction)) {
                guidedAction.onRestoreInstanceState(bundle, getButtonAutoRestoreKey(guidedAction));
            }
        }
    }

    final void onSaveActions(List<GuidedAction> list, Bundle bundle) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            GuidedAction guidedAction = list.get(i);
            if (isSaveEnabled(guidedAction)) {
                guidedAction.onSaveInstanceState(bundle, getAutoRestoreKey(guidedAction));
            }
        }
    }

    final void onSaveButtonActions(List<GuidedAction> list, Bundle bundle) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            GuidedAction guidedAction = list.get(i);
            if (isSaveEnabled(guidedAction)) {
                guidedAction.onSaveInstanceState(bundle, getButtonAutoRestoreKey(guidedAction));
            }
        }
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        onSaveActions(this.mActions, bundle);
        onSaveButtonActions(this.mButtonActions, bundle);
    }

    private static boolean isGuidedStepTheme(Context context) {
        int i = android.support.v17.leanback.R.attr.guidedStepThemeFlag;
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(i, typedValue, true) && typedValue.type == 18 && typedValue.data != 0;
    }

    public void finishGuidedStepFragments() {
        FragmentManager fragmentManager = getFragmentManager();
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 0) {
            for (int i = backStackEntryCount - 1; i >= 0; i--) {
                FragmentManager.BackStackEntry backStackEntryAt = fragmentManager.getBackStackEntryAt(i);
                if (isStackEntryUiStyleEntrance(backStackEntryAt.getName())) {
                    GuidedStepFragment currentGuidedStepFragment = getCurrentGuidedStepFragment(fragmentManager);
                    if (currentGuidedStepFragment != null) {
                        currentGuidedStepFragment.setUiStyle(1);
                    }
                    fragmentManager.popBackStackImmediate(backStackEntryAt.getId(), 1);
                    return;
                }
            }
        }
        ActivityCompat.finishAfterTransition(getActivity());
    }

    public void popBackStackToGuidedStepFragment(Class cls, int i) {
        if (GuidedStepFragment.class.isAssignableFrom(cls)) {
            FragmentManager fragmentManager = getFragmentManager();
            int backStackEntryCount = fragmentManager.getBackStackEntryCount();
            String name = cls.getName();
            if (backStackEntryCount > 0) {
                for (int i2 = backStackEntryCount - 1; i2 >= 0; i2--) {
                    FragmentManager.BackStackEntry backStackEntryAt = fragmentManager.getBackStackEntryAt(i2);
                    if (name.equals(getGuidedStepFragmentClassName(backStackEntryAt.getName()))) {
                        fragmentManager.popBackStackImmediate(backStackEntryAt.getId(), i);
                        return;
                    }
                }
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setEntranceTransitionType(int i) {
        this.entranceTransitionType = i;
    }

    private void resolveTheme() {
        Context context = FragmentUtil.getContext(this);
        int iOnProvideTheme = onProvideTheme();
        if (iOnProvideTheme != -1 || isGuidedStepTheme(context)) {
            if (iOnProvideTheme != -1) {
                this.mThemeWrapper = new ContextThemeWrapper(context, iOnProvideTheme);
                return;
            }
            return;
        }
        int i = android.support.v17.leanback.R.attr.guidedStepTheme;
        TypedValue typedValue = new TypedValue();
        boolean zResolveAttribute = context.getTheme().resolveAttribute(i, typedValue, true);
        if (zResolveAttribute) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, typedValue.resourceId);
            if (isGuidedStepTheme(contextThemeWrapper)) {
                this.mThemeWrapper = contextThemeWrapper;
            } else {
                zResolveAttribute = false;
                this.mThemeWrapper = null;
            }
        }
        if (zResolveAttribute) {
            return;
        }
        Log.e(TAG, "GuidedStepFragment does not have an appropriate theme set.");
    }

    private LayoutInflater getThemeInflater(LayoutInflater layoutInflater) {
        return this.mThemeWrapper == null ? layoutInflater : layoutInflater.cloneInContext(this.mThemeWrapper);
    }

    private int getFirstCheckedAction() {
        int size = this.mActions.size();
        for (int i = 0; i < size; i++) {
            if (this.mActions.get(i).isChecked()) {
                return i;
            }
        }
        return 0;
    }

    void runImeAnimations(boolean z) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            this.mGuidanceStylist.onImeAppearing(arrayList);
            this.mActionsStylist.onImeAppearing(arrayList);
            this.mButtonActionsStylist.onImeAppearing(arrayList);
        } else {
            this.mGuidanceStylist.onImeDisappearing(arrayList);
            this.mActionsStylist.onImeDisappearing(arrayList);
            this.mButtonActionsStylist.onImeDisappearing(arrayList);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        animatorSet.start();
    }
}
