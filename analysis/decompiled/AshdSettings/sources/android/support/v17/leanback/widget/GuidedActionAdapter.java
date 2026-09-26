package android.support.v17.leanback.widget;

import android.support.annotation.RestrictTo;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class GuidedActionAdapter extends RecyclerView.Adapter {
    static final boolean DEBUG = false;
    static final boolean DEBUG_EDIT = false;
    static final String TAG = "GuidedActionAdapter";
    static final String TAG_EDIT = "EditableAction";
    private final ActionEditListener mActionEditListener;
    private final ActionOnFocusListener mActionOnFocusListener;
    private final ActionOnKeyListener mActionOnKeyListener;
    private final List<GuidedAction> mActions;
    private ClickListener mClickListener;
    GuidedActionAdapterGroup mGroup;
    private final boolean mIsSubAdapter;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: android.support.v17.leanback.widget.GuidedActionAdapter.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == null || view.getWindowToken() == null || GuidedActionAdapter.this.getRecyclerView() == null) {
                return;
            }
            GuidedActionsStylist.ViewHolder viewHolder = (GuidedActionsStylist.ViewHolder) GuidedActionAdapter.this.getRecyclerView().getChildViewHolder(view);
            GuidedAction action = viewHolder.getAction();
            if (action.hasTextEditable()) {
                GuidedActionAdapter.this.mGroup.openIme(GuidedActionAdapter.this, viewHolder);
                return;
            }
            if (action.hasEditableActivatorView()) {
                GuidedActionAdapter.this.performOnActionClick(viewHolder);
                return;
            }
            GuidedActionAdapter.this.handleCheckedActions(viewHolder);
            if (!action.isEnabled() || action.infoOnly()) {
                return;
            }
            GuidedActionAdapter.this.performOnActionClick(viewHolder);
        }
    };
    final GuidedActionsStylist mStylist;

    public interface ClickListener {
        void onGuidedActionClicked(GuidedAction guidedAction);
    }

    public interface EditListener {
        void onGuidedActionEditCanceled(GuidedAction guidedAction);

        long onGuidedActionEditedAndProceed(GuidedAction guidedAction);

        void onImeClose();

        void onImeOpen();
    }

    public interface FocusListener {
        void onGuidedActionFocused(GuidedAction guidedAction);
    }

    public GuidedActionAdapter(List<GuidedAction> list, ClickListener clickListener, FocusListener focusListener, GuidedActionsStylist guidedActionsStylist, boolean z) {
        this.mActions = list == null ? new ArrayList() : new ArrayList(list);
        this.mClickListener = clickListener;
        this.mStylist = guidedActionsStylist;
        this.mActionOnKeyListener = new ActionOnKeyListener();
        this.mActionOnFocusListener = new ActionOnFocusListener(focusListener);
        this.mActionEditListener = new ActionEditListener();
        this.mIsSubAdapter = z;
    }

    public void setActions(List<GuidedAction> list) {
        if (!this.mIsSubAdapter) {
            this.mStylist.collapseAction(false);
        }
        this.mActionOnFocusListener.unFocus();
        this.mActions.clear();
        this.mActions.addAll(list);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mActions.size();
    }

    public GuidedAction getItem(int i) {
        return this.mActions.get(i);
    }

    public int indexOf(GuidedAction guidedAction) {
        return this.mActions.indexOf(guidedAction);
    }

    public GuidedActionsStylist getGuidedActionsStylist() {
        return this.mStylist;
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public void setFocusListener(FocusListener focusListener) {
        this.mActionOnFocusListener.setFocusListener(focusListener);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public List<GuidedAction> getActions() {
        return new ArrayList(this.mActions);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mStylist.getItemViewType(this.mActions.get(i));
    }

    RecyclerView getRecyclerView() {
        return this.mIsSubAdapter ? this.mStylist.getSubActionsGridView() : this.mStylist.getActionsGridView();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        GuidedActionsStylist.ViewHolder viewHolderOnCreateViewHolder = this.mStylist.onCreateViewHolder(viewGroup, i);
        View view = viewHolderOnCreateViewHolder.itemView;
        view.setOnKeyListener(this.mActionOnKeyListener);
        view.setOnClickListener(this.mOnClickListener);
        view.setOnFocusChangeListener(this.mActionOnFocusListener);
        setupListeners(viewHolderOnCreateViewHolder.getEditableTitleView());
        setupListeners(viewHolderOnCreateViewHolder.getEditableDescriptionView());
        return viewHolderOnCreateViewHolder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setupListeners(EditText editText) {
        if (editText != 0) {
            editText.setPrivateImeOptions("EscapeNorth=1;");
            editText.setOnEditorActionListener(this.mActionEditListener);
            if (editText instanceof ImeKeyMonitor) {
                ((ImeKeyMonitor) editText).setImeKeyListener(this.mActionEditListener);
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i >= this.mActions.size()) {
            return;
        }
        GuidedAction guidedAction = this.mActions.get(i);
        this.mStylist.onBindViewHolder((GuidedActionsStylist.ViewHolder) viewHolder, guidedAction);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mActions.size();
    }

    private class ActionOnFocusListener implements View.OnFocusChangeListener {
        private FocusListener mFocusListener;
        private View mSelectedView;

        ActionOnFocusListener(FocusListener focusListener) {
            this.mFocusListener = focusListener;
        }

        public void setFocusListener(FocusListener focusListener) {
            this.mFocusListener = focusListener;
        }

        public void unFocus() {
            if (this.mSelectedView == null || GuidedActionAdapter.this.getRecyclerView() == null) {
                return;
            }
            RecyclerView.ViewHolder childViewHolder = GuidedActionAdapter.this.getRecyclerView().getChildViewHolder(this.mSelectedView);
            if (childViewHolder != null) {
                GuidedActionAdapter.this.mStylist.onAnimateItemFocused((GuidedActionsStylist.ViewHolder) childViewHolder, false);
            } else {
                Log.w(GuidedActionAdapter.TAG, "RecyclerView returned null view holder", new Throwable());
            }
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (GuidedActionAdapter.this.getRecyclerView() == null) {
                return;
            }
            GuidedActionsStylist.ViewHolder viewHolder = (GuidedActionsStylist.ViewHolder) GuidedActionAdapter.this.getRecyclerView().getChildViewHolder(view);
            if (z) {
                this.mSelectedView = view;
                if (this.mFocusListener != null) {
                    this.mFocusListener.onGuidedActionFocused(viewHolder.getAction());
                }
            } else if (this.mSelectedView == view) {
                GuidedActionAdapter.this.mStylist.onAnimateItemPressedCancelled(viewHolder);
                this.mSelectedView = null;
            }
            GuidedActionAdapter.this.mStylist.onAnimateItemFocused(viewHolder, z);
        }
    }

    public GuidedActionsStylist.ViewHolder findSubChildViewHolder(View view) {
        if (getRecyclerView() == null) {
            return null;
        }
        ViewParent parent = view.getParent();
        while (parent != getRecyclerView() && parent != null && view != null) {
            view = parent;
            parent = parent.getParent();
        }
        if (parent == null || view == null) {
            return null;
        }
        return (GuidedActionsStylist.ViewHolder) getRecyclerView().getChildViewHolder(view);
    }

    public void handleCheckedActions(GuidedActionsStylist.ViewHolder viewHolder) {
        GuidedAction action = viewHolder.getAction();
        int checkSetId = action.getCheckSetId();
        if (getRecyclerView() == null || checkSetId == 0) {
            return;
        }
        if (checkSetId != -1) {
            int size = this.mActions.size();
            for (int i = 0; i < size; i++) {
                GuidedAction guidedAction = this.mActions.get(i);
                if (guidedAction != action && guidedAction.getCheckSetId() == checkSetId && guidedAction.isChecked()) {
                    guidedAction.setChecked(false);
                    GuidedActionsStylist.ViewHolder viewHolder2 = (GuidedActionsStylist.ViewHolder) getRecyclerView().findViewHolderForPosition(i);
                    if (viewHolder2 != null) {
                        this.mStylist.onAnimateItemChecked(viewHolder2, false);
                    }
                }
            }
        }
        if (!action.isChecked()) {
            action.setChecked(true);
            this.mStylist.onAnimateItemChecked(viewHolder, true);
        } else if (checkSetId == -1) {
            action.setChecked(false);
            this.mStylist.onAnimateItemChecked(viewHolder, false);
        }
    }

    public void performOnActionClick(GuidedActionsStylist.ViewHolder viewHolder) {
        if (this.mClickListener != null) {
            this.mClickListener.onGuidedActionClicked(viewHolder.getAction());
        }
    }

    private class ActionOnKeyListener implements View.OnKeyListener {
        private boolean mKeyPressed = false;

        ActionOnKeyListener() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:16:0x001e  */
        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            GuidedAction action;
            if (view == null || keyEvent == null || GuidedActionAdapter.this.getRecyclerView() == null) {
                return false;
            }
            if (i == 23 || i == 66 || i == 160) {
                GuidedActionsStylist.ViewHolder viewHolder = (GuidedActionsStylist.ViewHolder) GuidedActionAdapter.this.getRecyclerView().getChildViewHolder(view);
                action = viewHolder.getAction();
                if (action.isEnabled() || action.infoOnly()) {
                    keyEvent.getAction();
                    return true;
                }
                switch (keyEvent.getAction()) {
                    case 0:
                        if (!this.mKeyPressed) {
                            this.mKeyPressed = true;
                            GuidedActionAdapter.this.mStylist.onAnimateItemPressed(viewHolder, this.mKeyPressed);
                        }
                        break;
                    case 1:
                        if (this.mKeyPressed) {
                            this.mKeyPressed = false;
                            GuidedActionAdapter.this.mStylist.onAnimateItemPressed(viewHolder, this.mKeyPressed);
                        }
                        break;
                }
            } else {
                switch (i) {
                    case 99:
                    case 100:
                        GuidedActionsStylist.ViewHolder viewHolder2 = (GuidedActionsStylist.ViewHolder) GuidedActionAdapter.this.getRecyclerView().getChildViewHolder(view);
                        action = viewHolder2.getAction();
                        if (action.isEnabled()) {
                            break;
                        }
                        keyEvent.getAction();
                        return true;
                    default:
                        return false;
                }
            }
            return false;
        }
    }

    private class ActionEditListener implements TextView.OnEditorActionListener, ImeKeyMonitor.ImeKeyListener {
        ActionEditListener() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 5 || i == 6) {
                GuidedActionAdapter.this.mGroup.fillAndGoNext(GuidedActionAdapter.this, textView);
                return true;
            }
            if (i != 1) {
                return false;
            }
            GuidedActionAdapter.this.mGroup.fillAndStay(GuidedActionAdapter.this, textView);
            return true;
        }

        @Override // android.support.v17.leanback.widget.ImeKeyMonitor.ImeKeyListener
        public boolean onKeyPreIme(EditText editText, int i, KeyEvent keyEvent) {
            if (i == 4 && keyEvent.getAction() == 1) {
                GuidedActionAdapter.this.mGroup.fillAndStay(GuidedActionAdapter.this, editText);
                return true;
            }
            if (i != 66 || keyEvent.getAction() != 1) {
                return false;
            }
            GuidedActionAdapter.this.mGroup.fillAndGoNext(GuidedActionAdapter.this, editText);
            return true;
        }
    }
}
