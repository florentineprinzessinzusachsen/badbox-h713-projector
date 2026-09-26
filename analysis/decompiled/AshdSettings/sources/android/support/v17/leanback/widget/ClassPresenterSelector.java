package android.support.v17.leanback.widget;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ClassPresenterSelector extends PresenterSelector {
    private final ArrayList<Presenter> mPresenters = new ArrayList<>();
    private final HashMap<Class<?>, Object> mClassMap = new HashMap<>();

    public ClassPresenterSelector addClassPresenter(Class<?> cls, Presenter presenter) {
        this.mClassMap.put(cls, presenter);
        if (!this.mPresenters.contains(presenter)) {
            this.mPresenters.add(presenter);
        }
        return this;
    }

    public ClassPresenterSelector addClassPresenterSelector(Class<?> cls, PresenterSelector presenterSelector) {
        this.mClassMap.put(cls, presenterSelector);
        Presenter[] presenters = presenterSelector.getPresenters();
        for (int i = 0; i < presenters.length; i++) {
            if (!this.mPresenters.contains(presenters[i])) {
                this.mPresenters.add(presenters[i]);
            }
        }
        return this;
    }

    @Override // android.support.v17.leanback.widget.PresenterSelector
    public Presenter getPresenter(Object obj) {
        Object obj2;
        Presenter presenter;
        Class<?> superclass = obj.getClass();
        do {
            obj2 = this.mClassMap.get(superclass);
            if ((obj2 instanceof PresenterSelector) && (presenter = ((PresenterSelector) obj2).getPresenter(obj)) != null) {
                return presenter;
            }
            superclass = superclass.getSuperclass();
            if (obj2 != null) {
                break;
            }
        } while (superclass != null);
        return (Presenter) obj2;
    }

    @Override // android.support.v17.leanback.widget.PresenterSelector
    public Presenter[] getPresenters() {
        return (Presenter[]) this.mPresenters.toArray(new Presenter[this.mPresenters.size()]);
    }
}
