package android.support.v17.leanback.widget;

import android.support.v17.leanback.R;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class ControlButtonPresenterSelector extends PresenterSelector {
    private final Presenter mPrimaryPresenter = new ControlButtonPresenter(R.layout.lb_control_button_primary);
    private final Presenter mSecondaryPresenter = new ControlButtonPresenter(R.layout.lb_control_button_secondary);
    private final Presenter[] mPresenters = {this.mPrimaryPresenter};

    public Presenter getPrimaryPresenter() {
        return this.mPrimaryPresenter;
    }

    public Presenter getSecondaryPresenter() {
        return this.mSecondaryPresenter;
    }

    @Override // android.support.v17.leanback.widget.PresenterSelector
    public Presenter getPresenter(Object obj) {
        return this.mPrimaryPresenter;
    }

    @Override // android.support.v17.leanback.widget.PresenterSelector
    public Presenter[] getPresenters() {
        return this.mPresenters;
    }

    static class ActionViewHolder extends Presenter.ViewHolder {
        View mFocusableView;
        ImageView mIcon;
        TextView mLabel;

        public ActionViewHolder(View view) {
            super(view);
            this.mIcon = (ImageView) view.findViewById(R.id.icon);
            this.mLabel = (TextView) view.findViewById(R.id.label);
            this.mFocusableView = view.findViewById(R.id.button);
        }
    }

    static class ControlButtonPresenter extends Presenter {
        private int mLayoutResourceId;

        ControlButtonPresenter(int i) {
            this.mLayoutResourceId = i;
        }

        @Override // android.support.v17.leanback.widget.Presenter
        public Presenter.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
            return new ActionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.mLayoutResourceId, viewGroup, false));
        }

        @Override // android.support.v17.leanback.widget.Presenter
        public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
            Action action = (Action) obj;
            ActionViewHolder actionViewHolder = (ActionViewHolder) viewHolder;
            actionViewHolder.mIcon.setImageDrawable(action.getIcon());
            if (actionViewHolder.mLabel != null) {
                if (action.getIcon() == null) {
                    actionViewHolder.mLabel.setText(action.getLabel1());
                } else {
                    actionViewHolder.mLabel.setText((CharSequence) null);
                }
            }
            CharSequence label1 = TextUtils.isEmpty(action.getLabel2()) ? action.getLabel1() : action.getLabel2();
            if (TextUtils.equals(actionViewHolder.mFocusableView.getContentDescription(), label1)) {
                return;
            }
            actionViewHolder.mFocusableView.setContentDescription(label1);
            actionViewHolder.mFocusableView.sendAccessibilityEvent(32768);
        }

        @Override // android.support.v17.leanback.widget.Presenter
        public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
            ActionViewHolder actionViewHolder = (ActionViewHolder) viewHolder;
            actionViewHolder.mIcon.setImageDrawable(null);
            if (actionViewHolder.mLabel != null) {
                actionViewHolder.mLabel.setText((CharSequence) null);
            }
            actionViewHolder.mFocusableView.setContentDescription(null);
        }

        @Override // android.support.v17.leanback.widget.Presenter
        public void setOnClickListener(Presenter.ViewHolder viewHolder, View.OnClickListener onClickListener) {
            ((ActionViewHolder) viewHolder).mFocusableView.setOnClickListener(onClickListener);
        }
    }
}
