package android.support.v17.leanback.app;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
class FragmentUtil {
    FragmentUtil() {
    }

    @TargetApi(23)
    private static Context getContextNew(Fragment fragment) {
        return fragment.getContext();
    }

    public static Context getContext(Fragment fragment) {
        if (Build.VERSION.SDK_INT >= 23) {
            return getContextNew(fragment);
        }
        return fragment.getActivity();
    }
}
