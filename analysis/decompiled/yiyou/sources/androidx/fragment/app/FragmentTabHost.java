package androidx.fragment.app;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TabHost;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<b> f1219a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f1220b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private h f1221c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1222d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TabHost.OnTabChangeListener f1223e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f1224f;
    private boolean g;

    static class a extends View.BaseSavedState {
        public static final Parcelable.Creator<a> CREATOR = new C0028a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f1225a;

        /* JADX INFO: renamed from: androidx.fragment.app.FragmentTabHost$a$a, reason: collision with other inner class name */
        static class C0028a implements Parcelable.Creator<a> {
            C0028a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public a createFromParcel(Parcel parcel) {
                return new a(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public a[] newArray(int i) {
                return new a[i];
            }
        }

        a(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f1225a + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f1225a);
        }

        a(Parcel parcel) {
            super(parcel);
            this.f1225a = parcel.readString();
        }
    }

    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f1226a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<?> f1227b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Bundle f1228c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Fragment f1229d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f1219a = new ArrayList<>();
        a(context, (AttributeSet) null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.f1222d = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f1219a.size();
        m mVarA = null;
        for (int i = 0; i < size; i++) {
            b bVar = this.f1219a.get(i);
            bVar.f1229d = this.f1221c.a(bVar.f1226a);
            Fragment fragment = bVar.f1229d;
            if (fragment != null && !fragment.K()) {
                if (bVar.f1226a.equals(currentTabTag)) {
                    this.f1224f = bVar;
                } else {
                    if (mVarA == null) {
                        mVarA = this.f1221c.a();
                    }
                    mVarA.b(bVar.f1229d);
                }
            }
        }
        this.g = true;
        m mVarA2 = a(currentTabTag, mVarA);
        if (mVarA2 != null) {
            mVarA2.b();
            this.f1221c.b();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.g = false;
    }

    @Override // android.view.View
    @Deprecated
    protected void onRestoreInstanceState(@SuppressLint({"UnknownNullness"}) Parcelable parcelable) {
        if (!(parcelable instanceof a)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        a aVar = (a) parcelable;
        super.onRestoreInstanceState(aVar.getSuperState());
        setCurrentTabByTag(aVar.f1225a);
    }

    @Override // android.view.View
    @Deprecated
    protected Parcelable onSaveInstanceState() {
        a aVar = new a(super.onSaveInstanceState());
        aVar.f1225a = getCurrentTabTag();
        return aVar;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        m mVarA;
        if (this.g && (mVarA = a(str, (m) null)) != null) {
            mVarA.b();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.f1223e;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.f1223e = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Deprecated
    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1219a = new ArrayList<>();
        a(context, attributeSet);
    }

    private m a(String str, m mVar) {
        Fragment fragment;
        b bVarA = a(str);
        if (this.f1224f != bVarA) {
            if (mVar == null) {
                mVar = this.f1221c.a();
            }
            b bVar = this.f1224f;
            if (bVar != null && (fragment = bVar.f1229d) != null) {
                mVar.b(fragment);
            }
            if (bVarA != null) {
                Fragment fragment2 = bVarA.f1229d;
                if (fragment2 == null) {
                    bVarA.f1229d = this.f1221c.d().a(this.f1220b.getClassLoader(), bVarA.f1227b.getName());
                    bVarA.f1229d.m(bVarA.f1228c);
                    mVar.a(this.f1222d, bVarA.f1229d, bVarA.f1226a);
                } else {
                    mVar.a(fragment2);
                }
            }
            this.f1224f = bVarA;
        }
        return mVar;
    }

    private b a(String str) {
        int size = this.f1219a.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1219a.get(i);
            if (bVar.f1226a.equals(str)) {
                return bVar;
            }
        }
        return null;
    }
}
