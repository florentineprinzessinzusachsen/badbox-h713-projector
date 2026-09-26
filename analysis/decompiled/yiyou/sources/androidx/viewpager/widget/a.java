package androidx.viewpager.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: PagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final DataSetObservable f1516a = new DataSetObservable();

    public abstract int a();

    public int a(Object obj) {
        return -1;
    }

    public CharSequence a(int i) {
        return null;
    }

    public Object a(ViewGroup viewGroup, int i) {
        return a((View) viewGroup, i);
    }

    public void a(Parcelable parcelable, ClassLoader classLoader) {
    }

    @Deprecated
    public void a(View view) {
    }

    public abstract boolean a(View view, Object obj);

    public float b(int i) {
        return 1.0f;
    }

    public Parcelable b() {
        return null;
    }

    @Deprecated
    public void b(View view) {
    }

    @Deprecated
    public void b(View view, int i, Object obj) {
    }

    public void b(ViewGroup viewGroup) {
        b((View) viewGroup);
    }

    public void c(DataSetObserver dataSetObserver) {
        this.f1516a.unregisterObserver(dataSetObserver);
    }

    public void a(ViewGroup viewGroup, int i, Object obj) {
        a((View) viewGroup, i, obj);
    }

    public void b(ViewGroup viewGroup, int i, Object obj) {
        b((View) viewGroup, i, obj);
    }

    public void a(ViewGroup viewGroup) {
        a((View) viewGroup);
    }

    void b(DataSetObserver dataSetObserver) {
        synchronized (this) {
        }
    }

    @Deprecated
    public Object a(View view, int i) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    @Deprecated
    public void a(View view, int i, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void a(DataSetObserver dataSetObserver) {
        this.f1516a.registerObserver(dataSetObserver);
    }
}
