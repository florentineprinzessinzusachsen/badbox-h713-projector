package android.support.v17.leanback.widget;

import android.support.annotation.RestrictTo;
import android.support.v17.leanback.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class DividerPresenter extends Presenter {
    private final int mLayoutResourceId;

    @Override // android.support.v17.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
    }

    @Override // android.support.v17.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    public DividerPresenter() {
        this(R.layout.lb_divider);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public DividerPresenter(int i) {
        this.mLayoutResourceId = i;
    }

    @Override // android.support.v17.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new Presenter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.mLayoutResourceId, viewGroup, false));
    }
}
