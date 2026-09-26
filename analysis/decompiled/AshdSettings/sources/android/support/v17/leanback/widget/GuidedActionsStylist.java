package android.support.v17.leanback.widget;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v17.leanback.R;
import android.support.v17.leanback.transition.TransitionEpicenterCallback;
import android.support.v17.leanback.transition.TransitionHelper;
import android.support.v17.leanback.transition.TransitionListener;
import android.support.v17.leanback.widget.picker.DatePicker;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GuidedActionsStylist implements FragmentAnimationProvider {
    private static String TAG = "GuidedActionsStylist";
    public static final int VIEW_TYPE_DATE_PICKER = 1;
    public static final int VIEW_TYPE_DEFAULT = 0;
    static final ItemAlignmentFacet sGuidedActionItemAlignFacet = new ItemAlignmentFacet();
    private VerticalGridView mActionsGridView;
    private View mBgView;
    private boolean mButtonActions;
    private View mContentView;
    private int mDescriptionMinLines;
    private float mDisabledChevronAlpha;
    private float mDisabledDescriptionAlpha;
    private float mDisabledTextAlpha;
    private int mDisplayHeight;
    private GuidedActionAdapter.EditListener mEditListener;
    private float mEnabledChevronAlpha;
    private float mEnabledDescriptionAlpha;
    private float mEnabledTextAlpha;
    Object mExpandTransition;
    private float mKeyLinePercent;
    ViewGroup mMainView;
    private View mSubActionsBackground;
    VerticalGridView mSubActionsGridView;
    private int mTitleMaxLines;
    private int mTitleMinLines;
    private int mVerticalPadding;
    private GuidedAction mExpandedAction = null;
    private boolean mBackToCollapseSubActions = true;
    private boolean mBackToCollapseActivatorView = true;

    public void onAnimateItemFocused(ViewHolder viewHolder, boolean z) {
    }

    @Deprecated
    protected void onEditingModeChange(ViewHolder viewHolder, GuidedAction guidedAction, boolean z) {
    }

    @Override // android.support.v17.leanback.widget.FragmentAnimationProvider
    public void onImeAppearing(@NonNull List<Animator> list) {
    }

    @Override // android.support.v17.leanback.widget.FragmentAnimationProvider
    public void onImeDisappearing(@NonNull List<Animator> list) {
    }

    static {
        ItemAlignmentFacet.ItemAlignmentDef itemAlignmentDef = new ItemAlignmentFacet.ItemAlignmentDef();
        itemAlignmentDef.setItemAlignmentViewId(R.id.guidedactions_item_title);
        itemAlignmentDef.setAlignedToTextViewBaseline(true);
        itemAlignmentDef.setItemAlignmentOffset(0);
        itemAlignmentDef.setItemAlignmentOffsetWithPadding(true);
        itemAlignmentDef.setItemAlignmentOffsetPercent(0.0f);
        sGuidedActionItemAlignFacet.setAlignmentDefs(new ItemAlignmentFacet.ItemAlignmentDef[]{itemAlignmentDef});
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements FacetProvider {
        GuidedAction mAction;
        View mActivatorView;
        ImageView mCheckmarkView;
        ImageView mChevronView;
        private View mContentView;
        final View.AccessibilityDelegate mDelegate;
        TextView mDescriptionView;
        int mEditingMode;
        ImageView mIconView;
        private final boolean mIsSubAction;
        TextView mTitleView;

        public ViewHolder(View view) {
            this(view, false);
        }

        public ViewHolder(View view, boolean z) {
            super(view);
            this.mEditingMode = 0;
            this.mDelegate = new View.AccessibilityDelegate() { // from class: android.support.v17.leanback.widget.GuidedActionsStylist.ViewHolder.1
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityEvent(View view2, AccessibilityEvent accessibilityEvent) {
                    super.onInitializeAccessibilityEvent(view2, accessibilityEvent);
                    accessibilityEvent.setChecked(ViewHolder.this.mAction != null && ViewHolder.this.mAction.isChecked());
                }

                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfo accessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfo);
                    boolean z2 = false;
                    accessibilityNodeInfo.setCheckable((ViewHolder.this.mAction == null || ViewHolder.this.mAction.getCheckSetId() == 0) ? false : true);
                    if (ViewHolder.this.mAction != null && ViewHolder.this.mAction.isChecked()) {
                        z2 = true;
                    }
                    accessibilityNodeInfo.setChecked(z2);
                }
            };
            this.mContentView = view.findViewById(R.id.guidedactions_item_content);
            this.mTitleView = (TextView) view.findViewById(R.id.guidedactions_item_title);
            this.mActivatorView = view.findViewById(R.id.guidedactions_activator_item);
            this.mDescriptionView = (TextView) view.findViewById(R.id.guidedactions_item_description);
            this.mIconView = (ImageView) view.findViewById(R.id.guidedactions_item_icon);
            this.mCheckmarkView = (ImageView) view.findViewById(R.id.guidedactions_item_checkmark);
            this.mChevronView = (ImageView) view.findViewById(R.id.guidedactions_item_chevron);
            this.mIsSubAction = z;
            view.setAccessibilityDelegate(this.mDelegate);
        }

        public View getContentView() {
            return this.mContentView;
        }

        public TextView getTitleView() {
            return this.mTitleView;
        }

        public EditText getEditableTitleView() {
            if (this.mTitleView instanceof EditText) {
                return (EditText) this.mTitleView;
            }
            return null;
        }

        public TextView getDescriptionView() {
            return this.mDescriptionView;
        }

        public EditText getEditableDescriptionView() {
            if (this.mDescriptionView instanceof EditText) {
                return (EditText) this.mDescriptionView;
            }
            return null;
        }

        public ImageView getIconView() {
            return this.mIconView;
        }

        public ImageView getCheckmarkView() {
            return this.mCheckmarkView;
        }

        public ImageView getChevronView() {
            return this.mChevronView;
        }

        public boolean isInEditing() {
            return this.mEditingMode != 0;
        }

        public boolean isInEditingText() {
            return this.mEditingMode == 1 || this.mEditingMode == 2;
        }

        public boolean isInEditingTitle() {
            return this.mEditingMode == 1;
        }

        public boolean isInEditingDescription() {
            return this.mEditingMode == 2;
        }

        public boolean isInEditingActivatorView() {
            return this.mEditingMode == 3;
        }

        public View getEditingView() {
            switch (this.mEditingMode) {
                case 1:
                    return this.mTitleView;
                case 2:
                    return this.mDescriptionView;
                case 3:
                    return this.mActivatorView;
                default:
                    return null;
            }
        }

        public boolean isSubAction() {
            return this.mIsSubAction;
        }

        public GuidedAction getAction() {
            return this.mAction;
        }

        void setActivated(boolean z) {
            this.mActivatorView.setActivated(z);
            if (this.itemView instanceof GuidedActionItemContainer) {
                ((GuidedActionItemContainer) this.itemView).setFocusOutAllowed(!z);
            }
        }

        @Override // android.support.v17.leanback.widget.FacetProvider
        public Object getFacet(Class<?> cls) {
            if (cls == ItemAlignmentFacet.class) {
                return GuidedActionsStylist.sGuidedActionItemAlignFacet;
            }
            return null;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        float f = layoutInflater.getContext().getTheme().obtainStyledAttributes(R.styleable.LeanbackGuidedStepTheme).getFloat(R.styleable.LeanbackGuidedStepTheme_guidedStepKeyline, 40.0f);
        this.mMainView = (ViewGroup) layoutInflater.inflate(onProvideLayoutId(), viewGroup, false);
        this.mContentView = this.mMainView.findViewById(this.mButtonActions ? R.id.guidedactions_content2 : R.id.guidedactions_content);
        this.mBgView = this.mMainView.findViewById(this.mButtonActions ? R.id.guidedactions_list_background2 : R.id.guidedactions_list_background);
        if (this.mMainView instanceof VerticalGridView) {
            this.mActionsGridView = (VerticalGridView) this.mMainView;
        } else {
            this.mActionsGridView = (VerticalGridView) this.mMainView.findViewById(this.mButtonActions ? R.id.guidedactions_list2 : R.id.guidedactions_list);
            if (this.mActionsGridView == null) {
                throw new IllegalStateException("No ListView exists.");
            }
            this.mActionsGridView.setWindowAlignmentOffsetPercent(f);
            this.mActionsGridView.setWindowAlignment(0);
            if (!this.mButtonActions) {
                this.mSubActionsGridView = (VerticalGridView) this.mMainView.findViewById(R.id.guidedactions_sub_list);
                this.mSubActionsBackground = this.mMainView.findViewById(R.id.guidedactions_sub_list_background);
            }
        }
        this.mActionsGridView.setFocusable(false);
        this.mActionsGridView.setFocusableInTouchMode(false);
        Context context = this.mMainView.getContext();
        TypedValue typedValue = new TypedValue();
        this.mEnabledChevronAlpha = getFloat(context, typedValue, R.attr.guidedActionEnabledChevronAlpha);
        this.mDisabledChevronAlpha = getFloat(context, typedValue, R.attr.guidedActionDisabledChevronAlpha);
        this.mTitleMinLines = getInteger(context, typedValue, R.attr.guidedActionTitleMinLines);
        this.mTitleMaxLines = getInteger(context, typedValue, R.attr.guidedActionTitleMaxLines);
        this.mDescriptionMinLines = getInteger(context, typedValue, R.attr.guidedActionDescriptionMinLines);
        this.mVerticalPadding = getDimension(context, typedValue, R.attr.guidedActionVerticalPadding);
        this.mDisplayHeight = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
        this.mEnabledTextAlpha = Float.valueOf(context.getResources().getString(R.string.lb_guidedactions_item_unselected_text_alpha)).floatValue();
        this.mDisabledTextAlpha = Float.valueOf(context.getResources().getString(R.string.lb_guidedactions_item_disabled_text_alpha)).floatValue();
        this.mEnabledDescriptionAlpha = Float.valueOf(context.getResources().getString(R.string.lb_guidedactions_item_unselected_description_text_alpha)).floatValue();
        this.mDisabledDescriptionAlpha = Float.valueOf(context.getResources().getString(R.string.lb_guidedactions_item_disabled_description_text_alpha)).floatValue();
        this.mKeyLinePercent = GuidanceStylingRelativeLayout.getKeyLinePercent(context);
        if (this.mContentView instanceof GuidedActionsRelativeLayout) {
            ((GuidedActionsRelativeLayout) this.mContentView).setInterceptKeyEventListener(new GuidedActionsRelativeLayout.InterceptKeyEventListener() { // from class: android.support.v17.leanback.widget.GuidedActionsStylist.1
                @Override // android.support.v17.leanback.widget.GuidedActionsRelativeLayout.InterceptKeyEventListener
                public boolean onInterceptKeyEvent(KeyEvent keyEvent) {
                    if (keyEvent.getKeyCode() != 4 || keyEvent.getAction() != 1 || GuidedActionsStylist.this.mExpandedAction == null) {
                        return false;
                    }
                    if ((!GuidedActionsStylist.this.mExpandedAction.hasSubActions() || !GuidedActionsStylist.this.isBackKeyToCollapseSubActions()) && (!GuidedActionsStylist.this.mExpandedAction.hasEditableActivatorView() || !GuidedActionsStylist.this.isBackKeyToCollapseActivatorView())) {
                        return false;
                    }
                    GuidedActionsStylist.this.collapseAction(true);
                    return true;
                }
            });
        }
        return this.mMainView;
    }

    public void setAsButtonActions() {
        if (this.mMainView != null) {
            throw new IllegalStateException("setAsButtonActions() must be called before creating views");
        }
        this.mButtonActions = true;
    }

    public boolean isButtonActions() {
        return this.mButtonActions;
    }

    public void onDestroyView() {
        this.mExpandedAction = null;
        this.mExpandTransition = null;
        this.mActionsGridView = null;
        this.mSubActionsGridView = null;
        this.mSubActionsBackground = null;
        this.mContentView = null;
        this.mBgView = null;
        this.mMainView = null;
    }

    public VerticalGridView getActionsGridView() {
        return this.mActionsGridView;
    }

    public VerticalGridView getSubActionsGridView() {
        return this.mSubActionsGridView;
    }

    public int onProvideLayoutId() {
        return this.mButtonActions ? R.layout.lb_guidedbuttonactions : R.layout.lb_guidedactions;
    }

    public int getItemViewType(GuidedAction guidedAction) {
        return guidedAction instanceof GuidedDatePickerAction ? 1 : 0;
    }

    public int onProvideItemLayoutId() {
        return R.layout.lb_guidedactions_item;
    }

    public int onProvideItemLayoutId(int i) {
        if (i == 0) {
            return onProvideItemLayoutId();
        }
        if (i == 1) {
            return R.layout.lb_guidedactions_datepicker_item;
        }
        throw new RuntimeException("ViewType " + i + " not supported in GuidedActionsStylist");
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(onProvideItemLayoutId(), viewGroup, false), viewGroup == this.mSubActionsGridView);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return onCreateViewHolder(viewGroup);
        }
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(onProvideItemLayoutId(i), viewGroup, false), viewGroup == this.mSubActionsGridView);
    }

    public void onBindViewHolder(ViewHolder viewHolder, GuidedAction guidedAction) {
        viewHolder.mAction = guidedAction;
        if (viewHolder.mTitleView != null) {
            viewHolder.mTitleView.setText(guidedAction.getTitle());
            viewHolder.mTitleView.setAlpha(guidedAction.isEnabled() ? this.mEnabledTextAlpha : this.mDisabledTextAlpha);
            viewHolder.mTitleView.setFocusable(false);
            viewHolder.mTitleView.setClickable(false);
            viewHolder.mTitleView.setLongClickable(false);
        }
        if (viewHolder.mDescriptionView != null) {
            viewHolder.mDescriptionView.setText(guidedAction.getDescription());
            viewHolder.mDescriptionView.setVisibility(TextUtils.isEmpty(guidedAction.getDescription()) ? 8 : 0);
            viewHolder.mDescriptionView.setAlpha(guidedAction.isEnabled() ? this.mEnabledDescriptionAlpha : this.mDisabledDescriptionAlpha);
            viewHolder.mDescriptionView.setFocusable(false);
            viewHolder.mDescriptionView.setClickable(false);
            viewHolder.mDescriptionView.setLongClickable(false);
        }
        if (viewHolder.mCheckmarkView != null) {
            onBindCheckMarkView(viewHolder, guidedAction);
        }
        setIcon(viewHolder.mIconView, guidedAction);
        if (guidedAction.hasMultilineDescription()) {
            if (viewHolder.mTitleView != null) {
                setMaxLines(viewHolder.mTitleView, this.mTitleMaxLines);
                if (viewHolder.mDescriptionView != null) {
                    viewHolder.mDescriptionView.setMaxHeight(getDescriptionMaxHeight(viewHolder.itemView.getContext(), viewHolder.mTitleView));
                }
            }
        } else {
            if (viewHolder.mTitleView != null) {
                setMaxLines(viewHolder.mTitleView, this.mTitleMinLines);
            }
            if (viewHolder.mDescriptionView != null) {
                setMaxLines(viewHolder.mDescriptionView, this.mDescriptionMinLines);
            }
        }
        if (viewHolder.mActivatorView != null) {
            onBindActivatorView(viewHolder, guidedAction);
        }
        setEditingMode(viewHolder, false, false);
        if (guidedAction.isFocusable()) {
            viewHolder.itemView.setFocusable(true);
            ((ViewGroup) viewHolder.itemView).setDescendantFocusability(131072);
        } else {
            viewHolder.itemView.setFocusable(false);
            ((ViewGroup) viewHolder.itemView).setDescendantFocusability(393216);
        }
        setupImeOptions(viewHolder, guidedAction);
        updateChevronAndVisibility(viewHolder);
    }

    private static void setMaxLines(TextView textView, int i) {
        if (i == 1) {
            textView.setSingleLine(true);
        } else {
            textView.setSingleLine(false);
            textView.setMaxLines(i);
        }
    }

    protected void setupImeOptions(ViewHolder viewHolder, GuidedAction guidedAction) {
        setupNextImeOptions(viewHolder.getEditableTitleView());
        setupNextImeOptions(viewHolder.getEditableDescriptionView());
    }

    private void setupNextImeOptions(EditText editText) {
        if (editText != null) {
            editText.setImeOptions(5);
        }
    }

    @Deprecated
    public void setEditingMode(ViewHolder viewHolder, GuidedAction guidedAction, boolean z) {
        if (z == viewHolder.isInEditing() || !isInExpandTransition()) {
            return;
        }
        onEditingModeChange(viewHolder, guidedAction, z);
    }

    void setEditingMode(ViewHolder viewHolder, boolean z) {
        setEditingMode(viewHolder, z, true);
    }

    void setEditingMode(ViewHolder viewHolder, boolean z, boolean z2) {
        if (z == viewHolder.isInEditing() || isInExpandTransition()) {
            return;
        }
        onEditingModeChange(viewHolder, z, z2);
    }

    @CallSuper
    protected void onEditingModeChange(ViewHolder viewHolder, boolean z, boolean z2) {
        GuidedAction action = viewHolder.getAction();
        TextView titleView = viewHolder.getTitleView();
        TextView descriptionView = viewHolder.getDescriptionView();
        if (z) {
            CharSequence editTitle = action.getEditTitle();
            if (titleView != null && editTitle != null) {
                titleView.setText(editTitle);
            }
            CharSequence editDescription = action.getEditDescription();
            if (descriptionView != null && editDescription != null) {
                descriptionView.setText(editDescription);
            }
            if (action.isDescriptionEditable()) {
                if (descriptionView != null) {
                    descriptionView.setVisibility(0);
                    descriptionView.setInputType(action.getDescriptionEditInputType());
                }
                viewHolder.mEditingMode = 2;
            } else if (action.isEditable()) {
                if (titleView != null) {
                    titleView.setInputType(action.getEditInputType());
                }
                viewHolder.mEditingMode = 1;
            } else if (viewHolder.mActivatorView != null) {
                onEditActivatorView(viewHolder, z, z2);
                viewHolder.mEditingMode = 3;
            }
        } else {
            if (titleView != null) {
                titleView.setText(action.getTitle());
            }
            if (descriptionView != null) {
                descriptionView.setText(action.getDescription());
            }
            if (viewHolder.mEditingMode == 2) {
                if (descriptionView != null) {
                    descriptionView.setVisibility(TextUtils.isEmpty(action.getDescription()) ? 8 : 0);
                    descriptionView.setInputType(action.getDescriptionInputType());
                }
            } else if (viewHolder.mEditingMode == 1) {
                if (titleView != null) {
                    titleView.setInputType(action.getInputType());
                }
            } else if (viewHolder.mEditingMode == 3 && viewHolder.mActivatorView != null) {
                onEditActivatorView(viewHolder, z, z2);
            }
            viewHolder.mEditingMode = 0;
        }
        onEditingModeChange(viewHolder, action, z);
    }

    public void onAnimateItemPressed(ViewHolder viewHolder, boolean z) {
        createAnimator(viewHolder.itemView, z ? R.attr.guidedActionPressedAnimation : R.attr.guidedActionUnpressedAnimation).start();
    }

    public void onAnimateItemPressedCancelled(ViewHolder viewHolder) {
        createAnimator(viewHolder.itemView, R.attr.guidedActionUnpressedAnimation).end();
    }

    public void onAnimateItemChecked(ViewHolder viewHolder, boolean z) {
        if (viewHolder.mCheckmarkView instanceof Checkable) {
            ((Checkable) viewHolder.mCheckmarkView).setChecked(z);
        }
    }

    public void onBindCheckMarkView(ViewHolder viewHolder, GuidedAction guidedAction) {
        if (guidedAction.getCheckSetId() != 0) {
            viewHolder.mCheckmarkView.setVisibility(0);
            int i = guidedAction.getCheckSetId() == -1 ? android.R.attr.listChoiceIndicatorMultiple : android.R.attr.listChoiceIndicatorSingle;
            Context context = viewHolder.mCheckmarkView.getContext();
            TypedValue typedValue = new TypedValue();
            viewHolder.mCheckmarkView.setImageDrawable(context.getTheme().resolveAttribute(i, typedValue, true) ? ContextCompat.getDrawable(context, typedValue.resourceId) : null);
            if (viewHolder.mCheckmarkView instanceof Checkable) {
                ((Checkable) viewHolder.mCheckmarkView).setChecked(guidedAction.isChecked());
                return;
            }
            return;
        }
        viewHolder.mCheckmarkView.setVisibility(8);
    }

    public void onBindActivatorView(ViewHolder viewHolder, GuidedAction guidedAction) {
        if (guidedAction instanceof GuidedDatePickerAction) {
            GuidedDatePickerAction guidedDatePickerAction = (GuidedDatePickerAction) guidedAction;
            DatePicker datePicker = (DatePicker) viewHolder.mActivatorView;
            datePicker.setDatePickerFormat(guidedDatePickerAction.getDatePickerFormat());
            if (guidedDatePickerAction.getMinDate() != Long.MIN_VALUE) {
                datePicker.setMinDate(guidedDatePickerAction.getMinDate());
            }
            if (guidedDatePickerAction.getMaxDate() != Long.MAX_VALUE) {
                datePicker.setMaxDate(guidedDatePickerAction.getMaxDate());
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(guidedDatePickerAction.getDate());
            datePicker.updateDate(calendar.get(1), calendar.get(2), calendar.get(5), false);
        }
    }

    public boolean onUpdateActivatorView(ViewHolder viewHolder, GuidedAction guidedAction) {
        if (!(guidedAction instanceof GuidedDatePickerAction)) {
            return false;
        }
        GuidedDatePickerAction guidedDatePickerAction = (GuidedDatePickerAction) guidedAction;
        DatePicker datePicker = (DatePicker) viewHolder.mActivatorView;
        if (guidedDatePickerAction.getDate() == datePicker.getDate()) {
            return false;
        }
        guidedDatePickerAction.setDate(datePicker.getDate());
        return true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setEditListener(GuidedActionAdapter.EditListener editListener) {
        this.mEditListener = editListener;
    }

    void onEditActivatorView(final ViewHolder viewHolder, boolean z, boolean z2) {
        if (z) {
            startExpanded(viewHolder, z2);
            viewHolder.itemView.setFocusable(false);
            viewHolder.mActivatorView.requestFocus();
            viewHolder.mActivatorView.setOnClickListener(new View.OnClickListener() { // from class: android.support.v17.leanback.widget.GuidedActionsStylist.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (GuidedActionsStylist.this.isInExpandTransition()) {
                        return;
                    }
                    ((GuidedActionAdapter) GuidedActionsStylist.this.getActionsGridView().getAdapter()).performOnActionClick(viewHolder);
                }
            });
            return;
        }
        if (onUpdateActivatorView(viewHolder, viewHolder.getAction()) && this.mEditListener != null) {
            this.mEditListener.onGuidedActionEditedAndProceed(viewHolder.getAction());
        }
        viewHolder.itemView.setFocusable(true);
        viewHolder.itemView.requestFocus();
        startExpanded(null, z2);
        viewHolder.mActivatorView.setOnClickListener(null);
        viewHolder.mActivatorView.setClickable(false);
    }

    public void onBindChevronView(ViewHolder viewHolder, GuidedAction guidedAction) {
        boolean zHasNext = guidedAction.hasNext();
        boolean zHasSubActions = guidedAction.hasSubActions();
        if (zHasNext || zHasSubActions) {
            viewHolder.mChevronView.setVisibility(0);
            viewHolder.mChevronView.setAlpha(guidedAction.isEnabled() ? this.mEnabledChevronAlpha : this.mDisabledChevronAlpha);
            if (zHasNext) {
                viewHolder.mChevronView.setRotation((this.mMainView == null || this.mMainView.getLayoutDirection() != 1) ? 0.0f : 180.0f);
                return;
            } else if (guidedAction == this.mExpandedAction) {
                viewHolder.mChevronView.setRotation(270.0f);
                return;
            } else {
                viewHolder.mChevronView.setRotation(90.0f);
                return;
            }
        }
        viewHolder.mChevronView.setVisibility(8);
    }

    @Deprecated
    public void setExpandedViewHolder(ViewHolder viewHolder) {
        expandAction(viewHolder == null ? null : viewHolder.getAction(), isExpandTransitionSupported());
    }

    public boolean isInExpandTransition() {
        return this.mExpandTransition != null;
    }

    public boolean isExpandTransitionSupported() {
        return Build.VERSION.SDK_INT >= 21;
    }

    @Deprecated
    public void startExpandedTransition(ViewHolder viewHolder) {
        expandAction(viewHolder == null ? null : viewHolder.getAction(), isExpandTransitionSupported());
    }

    public final void setBackKeyToCollapseSubActions(boolean z) {
        this.mBackToCollapseSubActions = z;
    }

    public final boolean isBackKeyToCollapseSubActions() {
        return this.mBackToCollapseSubActions;
    }

    public final void setBackKeyToCollapseActivatorView(boolean z) {
        this.mBackToCollapseActivatorView = z;
    }

    public final boolean isBackKeyToCollapseActivatorView() {
        return this.mBackToCollapseActivatorView;
    }

    public void expandAction(GuidedAction guidedAction, boolean z) {
        int iIndexOf;
        if (isInExpandTransition() || this.mExpandedAction != null || (iIndexOf = ((GuidedActionAdapter) getActionsGridView().getAdapter()).indexOf(guidedAction)) < 0) {
            return;
        }
        if (!(isExpandTransitionSupported() && z)) {
            getActionsGridView().setSelectedPosition(iIndexOf, new ViewHolderTask() { // from class: android.support.v17.leanback.widget.GuidedActionsStylist.3
                @Override // android.support.v17.leanback.widget.ViewHolderTask
                public void run(RecyclerView.ViewHolder viewHolder) {
                    ViewHolder viewHolder2 = (ViewHolder) viewHolder;
                    if (viewHolder2.getAction().hasEditableActivatorView()) {
                        GuidedActionsStylist.this.setEditingMode(viewHolder2, true, false);
                    } else {
                        GuidedActionsStylist.this.onUpdateExpandedViewHolder(viewHolder2);
                    }
                }
            });
            if (guidedAction.hasSubActions()) {
                onUpdateSubActionsGridView(guidedAction, true);
                return;
            }
            return;
        }
        getActionsGridView().setSelectedPosition(iIndexOf, new ViewHolderTask() { // from class: android.support.v17.leanback.widget.GuidedActionsStylist.4
            @Override // android.support.v17.leanback.widget.ViewHolderTask
            public void run(RecyclerView.ViewHolder viewHolder) {
                ViewHolder viewHolder2 = (ViewHolder) viewHolder;
                if (viewHolder2.getAction().hasEditableActivatorView()) {
                    GuidedActionsStylist.this.setEditingMode(viewHolder2, true, true);
                } else {
                    GuidedActionsStylist.this.startExpanded(viewHolder2, true);
                }
            }
        });
    }

    public void collapseAction(boolean z) {
        if (isInExpandTransition() || this.mExpandedAction == null) {
            return;
        }
        boolean z2 = isExpandTransitionSupported() && z;
        int iIndexOf = ((GuidedActionAdapter) getActionsGridView().getAdapter()).indexOf(this.mExpandedAction);
        if (iIndexOf < 0) {
            return;
        }
        if (this.mExpandedAction.hasEditableActivatorView()) {
            setEditingMode((ViewHolder) getActionsGridView().findViewHolderForPosition(iIndexOf), false, z2);
        } else {
            startExpanded(null, z2);
        }
    }

    int getKeyLine() {
        return (int) ((this.mKeyLinePercent * this.mActionsGridView.getHeight()) / 100.0f);
    }

    void startExpanded(ViewHolder viewHolder, boolean z) {
        ViewHolder viewHolder2;
        float height;
        int childCount = this.mActionsGridView.getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                viewHolder2 = null;
                break;
            }
            viewHolder2 = (ViewHolder) this.mActionsGridView.getChildViewHolder(this.mActionsGridView.getChildAt(i));
            if ((viewHolder == null && viewHolder2.itemView.getVisibility() == 0) || (viewHolder != null && viewHolder2.getAction() == viewHolder.getAction())) {
                break;
            } else {
                i++;
            }
        }
        if (viewHolder2 == null) {
            return;
        }
        boolean z2 = viewHolder != null;
        boolean zHasSubActions = viewHolder2.getAction().hasSubActions();
        if (z) {
            Object objCreateTransitionSet = TransitionHelper.createTransitionSet(false);
            if (zHasSubActions) {
                height = viewHolder2.itemView.getHeight();
            } else {
                height = viewHolder2.itemView.getHeight() * 0.5f;
            }
            Object objCreateFadeAndShortSlide = TransitionHelper.createFadeAndShortSlide(112, height);
            TransitionHelper.setEpicenterCallback(objCreateFadeAndShortSlide, new TransitionEpicenterCallback() { // from class: android.support.v17.leanback.widget.GuidedActionsStylist.5
                Rect mRect = new Rect();

                @Override // android.support.v17.leanback.transition.TransitionEpicenterCallback
                public Rect onGetEpicenter(Object obj) {
                    int keyLine = GuidedActionsStylist.this.getKeyLine();
                    this.mRect.set(0, keyLine, 0, keyLine);
                    return this.mRect;
                }
            });
            Object objCreateChangeTransform = TransitionHelper.createChangeTransform();
            Object objCreateChangeBounds = TransitionHelper.createChangeBounds(false);
            Object objCreateFadeTransition = TransitionHelper.createFadeTransition(3);
            Object objCreateChangeBounds2 = TransitionHelper.createChangeBounds(false);
            if (viewHolder == null) {
                TransitionHelper.setStartDelay(objCreateFadeAndShortSlide, 150L);
                TransitionHelper.setStartDelay(objCreateChangeTransform, 100L);
                TransitionHelper.setStartDelay(objCreateChangeBounds, 100L);
                TransitionHelper.setStartDelay(objCreateChangeBounds2, 100L);
            } else {
                TransitionHelper.setStartDelay(objCreateFadeTransition, 100L);
                TransitionHelper.setStartDelay(objCreateChangeBounds2, 50L);
                TransitionHelper.setStartDelay(objCreateChangeTransform, 50L);
                TransitionHelper.setStartDelay(objCreateChangeBounds, 50L);
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                ViewHolder viewHolder3 = (ViewHolder) this.mActionsGridView.getChildViewHolder(this.mActionsGridView.getChildAt(i2));
                if (viewHolder3 != viewHolder2) {
                    TransitionHelper.include(objCreateFadeAndShortSlide, viewHolder3.itemView);
                    TransitionHelper.exclude(objCreateFadeTransition, viewHolder3.itemView, true);
                } else if (zHasSubActions) {
                    TransitionHelper.include(objCreateChangeTransform, viewHolder3.itemView);
                    TransitionHelper.include(objCreateChangeBounds, viewHolder3.itemView);
                }
            }
            TransitionHelper.include(objCreateChangeBounds2, this.mSubActionsGridView);
            TransitionHelper.include(objCreateChangeBounds2, this.mSubActionsBackground);
            TransitionHelper.addTransition(objCreateTransitionSet, objCreateFadeAndShortSlide);
            if (zHasSubActions) {
                TransitionHelper.addTransition(objCreateTransitionSet, objCreateChangeTransform);
                TransitionHelper.addTransition(objCreateTransitionSet, objCreateChangeBounds);
            }
            TransitionHelper.addTransition(objCreateTransitionSet, objCreateFadeTransition);
            TransitionHelper.addTransition(objCreateTransitionSet, objCreateChangeBounds2);
            this.mExpandTransition = objCreateTransitionSet;
            TransitionHelper.addTransitionListener(this.mExpandTransition, new TransitionListener() { // from class: android.support.v17.leanback.widget.GuidedActionsStylist.6
                @Override // android.support.v17.leanback.transition.TransitionListener
                public void onTransitionEnd(Object obj) {
                    GuidedActionsStylist.this.mExpandTransition = null;
                }
            });
            if (z2 && zHasSubActions) {
                int bottom = viewHolder.itemView.getBottom();
                this.mSubActionsGridView.offsetTopAndBottom(bottom - this.mSubActionsGridView.getTop());
                this.mSubActionsBackground.offsetTopAndBottom(bottom - this.mSubActionsBackground.getTop());
            }
            TransitionHelper.beginDelayedTransition(this.mMainView, this.mExpandTransition);
        }
        onUpdateExpandedViewHolder(viewHolder);
        if (zHasSubActions) {
            onUpdateSubActionsGridView(viewHolder2.getAction(), z2);
        }
    }

    public boolean isSubActionsExpanded() {
        return this.mExpandedAction != null && this.mExpandedAction.hasSubActions();
    }

    public boolean isExpanded() {
        return this.mExpandedAction != null;
    }

    public GuidedAction getExpandedAction() {
        return this.mExpandedAction;
    }

    public void onUpdateExpandedViewHolder(ViewHolder viewHolder) {
        if (viewHolder == null) {
            this.mExpandedAction = null;
            this.mActionsGridView.setPruneChild(true);
        } else if (viewHolder.getAction() != this.mExpandedAction) {
            this.mExpandedAction = viewHolder.getAction();
            this.mActionsGridView.setPruneChild(false);
        }
        this.mActionsGridView.setAnimateChildLayout(false);
        int childCount = this.mActionsGridView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            updateChevronAndVisibility((ViewHolder) this.mActionsGridView.getChildViewHolder(this.mActionsGridView.getChildAt(i)));
        }
    }

    void onUpdateSubActionsGridView(GuidedAction guidedAction, boolean z) {
        if (this.mSubActionsGridView != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mSubActionsGridView.getLayoutParams();
            GuidedActionAdapter guidedActionAdapter = (GuidedActionAdapter) this.mSubActionsGridView.getAdapter();
            if (z) {
                marginLayoutParams.topMargin = -2;
                marginLayoutParams.height = -1;
                this.mSubActionsGridView.setLayoutParams(marginLayoutParams);
                this.mSubActionsGridView.setVisibility(0);
                this.mSubActionsBackground.setVisibility(0);
                this.mSubActionsGridView.requestFocus();
                guidedActionAdapter.setActions(guidedAction.getSubActions());
                return;
            }
            marginLayoutParams.topMargin = this.mActionsGridView.getLayoutManager().findViewByPosition(((GuidedActionAdapter) this.mActionsGridView.getAdapter()).indexOf(guidedAction)).getBottom();
            marginLayoutParams.height = 0;
            this.mSubActionsGridView.setVisibility(4);
            this.mSubActionsBackground.setVisibility(4);
            this.mSubActionsGridView.setLayoutParams(marginLayoutParams);
            guidedActionAdapter.setActions(Collections.EMPTY_LIST);
            this.mActionsGridView.requestFocus();
        }
    }

    private void updateChevronAndVisibility(ViewHolder viewHolder) {
        if (!viewHolder.isSubAction()) {
            if (this.mExpandedAction == null) {
                viewHolder.itemView.setVisibility(0);
                viewHolder.itemView.setTranslationY(0.0f);
                if (viewHolder.mActivatorView != null) {
                    viewHolder.setActivated(false);
                }
            } else if (viewHolder.getAction() == this.mExpandedAction) {
                viewHolder.itemView.setVisibility(0);
                if (viewHolder.getAction().hasSubActions()) {
                    viewHolder.itemView.setTranslationY(getKeyLine() - viewHolder.itemView.getBottom());
                } else if (viewHolder.mActivatorView != null) {
                    viewHolder.itemView.setTranslationY(0.0f);
                    viewHolder.setActivated(true);
                }
            } else {
                viewHolder.itemView.setVisibility(4);
                viewHolder.itemView.setTranslationY(0.0f);
            }
        }
        if (viewHolder.mChevronView != null) {
            onBindChevronView(viewHolder, viewHolder.getAction());
        }
    }

    private float getFloat(Context context, TypedValue typedValue, int i) {
        context.getTheme().resolveAttribute(i, typedValue, true);
        return Float.valueOf(context.getResources().getString(typedValue.resourceId)).floatValue();
    }

    private int getInteger(Context context, TypedValue typedValue, int i) {
        context.getTheme().resolveAttribute(i, typedValue, true);
        return context.getResources().getInteger(typedValue.resourceId);
    }

    private int getDimension(Context context, TypedValue typedValue, int i) {
        context.getTheme().resolveAttribute(i, typedValue, true);
        return context.getResources().getDimensionPixelSize(typedValue.resourceId);
    }

    private static Animator createAnimator(View view, int i) {
        Context context = view.getContext();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, typedValue.resourceId);
        animatorLoadAnimator.setTarget(view);
        return animatorLoadAnimator;
    }

    private boolean setIcon(ImageView imageView, GuidedAction guidedAction) {
        Drawable icon;
        if (imageView != null) {
            imageView.getContext();
            icon = guidedAction.getIcon();
            if (icon != null) {
                imageView.setImageLevel(icon.getLevel());
                imageView.setImageDrawable(icon);
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        } else {
            icon = null;
        }
        return icon != null;
    }

    private int getDescriptionMaxHeight(Context context, TextView textView) {
        return (this.mDisplayHeight - (this.mVerticalPadding * 2)) - ((this.mTitleMaxLines * 2) * textView.getLineHeight());
    }
}
