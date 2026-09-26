package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/* JADX INFO: compiled from: FragmentContainer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public abstract View a(int i);

    @Deprecated
    public Fragment a(Context context, String str, Bundle bundle) {
        return Fragment.a(context, str, bundle);
    }

    public abstract boolean c();
}
