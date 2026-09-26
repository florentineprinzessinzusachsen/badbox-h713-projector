package android.support.v17.leanback.widget;

/* JADX INFO: loaded from: classes.dex */
public final class SinglePresenterSelector extends PresenterSelector {
    private final Presenter mPresenter;

    public SinglePresenterSelector(Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // android.support.v17.leanback.widget.PresenterSelector
    public Presenter getPresenter(Object obj) {
        return this.mPresenter;
    }

    @Override // android.support.v17.leanback.widget.PresenterSelector
    public Presenter[] getPresenters() {
        return new Presenter[]{this.mPresenter};
    }
}
