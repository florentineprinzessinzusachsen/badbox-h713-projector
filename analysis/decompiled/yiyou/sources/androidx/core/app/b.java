package androidx.core.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

/* JADX INFO: compiled from: ActivityOptionsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: compiled from: ActivityOptionsCompat.java */
    private static class a extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ActivityOptions f906a;

        a(ActivityOptions activityOptions) {
            this.f906a = activityOptions;
        }

        @Override // androidx.core.app.b
        public Bundle a() {
            return this.f906a.toBundle();
        }
    }

    protected b() {
    }

    public static b a(Context context, int i, int i2) {
        return Build.VERSION.SDK_INT >= 16 ? new a(ActivityOptions.makeCustomAnimation(context, i, i2)) : new b();
    }

    public Bundle a() {
        return null;
    }

    public static b a(Activity activity, androidx.core.e.d<View, String>... dVarArr) {
        if (Build.VERSION.SDK_INT >= 21) {
            Pair[] pairArr = null;
            if (dVarArr != null) {
                pairArr = new Pair[dVarArr.length];
                for (int i = 0; i < dVarArr.length; i++) {
                    pairArr[i] = Pair.create(dVarArr[i].f1050a, dVarArr[i].f1051b);
                }
            }
            return new a(ActivityOptions.makeSceneTransitionAnimation(activity, pairArr));
        }
        return new b();
    }
}
