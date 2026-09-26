package android.support.v17.leanback.widget;

import android.graphics.drawable.Drawable;
import android.support.v17.leanback.R;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/* JADX INFO: loaded from: classes.dex */
class ActionPresenterSelector extends PresenterSelector {
    private final Presenter mOneLineActionPresenter = new OneLineActionPresenter();
    private final Presenter mTwoLineActionPresenter = new TwoLineActionPresenter();
    private final Presenter[] mPresenters = {this.mOneLineActionPresenter, this.mTwoLineActionPresenter};

    ActionPresenterSelector() {
    }

    @Override // android.support.v17.leanback.widget.PresenterSelector
    public Presenter getPresenter(Object obj) {
        if (TextUtils.isEmpty(((Action) obj).getLabel2())) {
            return this.mOneLineActionPresenter;
        }
        return this.mTwoLineActionPresenter;
    }

    @Override // android.support.v17.leanback.widget.PresenterSelector
    public Presenter[] getPresenters() {
        return this.mPresenters;
    }

    static class ActionViewHolder extends Presenter.ViewHolder {
        Action mAction;
        Button mButton;
        int mLayoutDirection;

        public ActionViewHolder(View view, int i) {
            super(view);
            this.mButton = (Button) view.findViewById(R.id.lb_action_button);
            this.mLayoutDirection = i;
        }
    }

    class OneLineActionPresenter extends Presenter {
        OneLineActionPresenter() {
        }

        @Override // android.support.v17.leanback.widget.Presenter
        public Presenter.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
            return new ActionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lb_action_1_line, viewGroup, false), viewGroup.getLayoutDirection());
        }

        @Override // android.support.v17.leanback.widget.Presenter
        public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
            Action action = (Action) obj;
            ActionViewHolder actionViewHolder = (ActionViewHolder) viewHolder;
            actionViewHolder.mAction = action;
            actionViewHolder.mButton.setText(action.getLabel1());
        }

        @Override // android.support.v17.leanback.widget.Presenter
        public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
            ((ActionViewHolder) viewHolder).mAction = null;
        }
    }

    class TwoLineActionPresenter extends Presenter {
        TwoLineActionPresenter() {
        }

        @Override // android.support.v17.leanback.widget.Presenter
        public Presenter.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
            return new ActionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lb_action_2_lines, viewGroup, false), viewGroup.getLayoutDirection());
        }

        @Override // android.support.v17.leanback.widget.Presenter
        public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
            Action action = (Action) obj;
            ActionViewHolder actionViewHolder = (ActionViewHolder) viewHolder;
            Drawable icon = action.getIcon();
            actionViewHolder.mAction = action;
            if (icon != null) {
                actionViewHolder.view.setPaddingRelative(actionViewHolder.view.getResources().getDimensionPixelSize(R.dimen.lb_action_with_icon_padding_start), 0, actionViewHolder.view.getResources().getDimensionPixelSize(R.dimen.lb_action_with_icon_padding_end), 0);
            } else {
                int dimensionPixelSize = actionViewHolder.view.getResources().getDimensionPixelSize(R.dimen.lb_action_padding_horizontal);
                actionViewHolder.view.setPaddingRelative(dimensionPixelSize, 0, dimensionPixelSize, 0);
            }
            if (actionViewHolder.mLayoutDirection == 1) {
                actionViewHolder.mButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, icon, (Drawable) null);
            } else {
                actionViewHolder.mButton.setCompoundDrawablesWithIntrinsicBounds(icon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            CharSequence label1 = action.getLabel1();
            CharSequence label2 = action.getLabel2();
            if (TextUtils.isEmpty(label1)) {
                actionViewHolder.mButton.setText(label2);
                return;
            }
            if (TextUtils.isEmpty(label2)) {
                actionViewHolder.mButton.setText(label1);
                return;
            }
            actionViewHolder.mButton.setText(((Object) label1) + "\n" + ((Object) label2));
        }

        @Override // android.support.v17.leanback.widget.Presenter
        public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
            ActionViewHolder actionViewHolder = (ActionViewHolder) viewHolder;
            actionViewHolder.mButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            actionViewHolder.view.setPadding(0, 0, 0, 0);
            actionViewHolder.mAction = null;
        }
    }
}
