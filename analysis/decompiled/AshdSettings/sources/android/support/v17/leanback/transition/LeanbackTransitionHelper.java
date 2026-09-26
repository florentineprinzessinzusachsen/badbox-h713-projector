package android.support.v17.leanback.transition;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.v17.leanback.R;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class LeanbackTransitionHelper {
    static LeanbackTransitionHelperVersion sImpl;

    interface LeanbackTransitionHelperVersion {
        Object loadTitleInTransition(Context context);

        Object loadTitleOutTransition(Context context);
    }

    static class LeanbackTransitionHelperKitKatImpl implements LeanbackTransitionHelperVersion {
        LeanbackTransitionHelperKitKatImpl() {
        }

        @Override // android.support.v17.leanback.transition.LeanbackTransitionHelper.LeanbackTransitionHelperVersion
        public Object loadTitleInTransition(Context context) {
            return LeanbackTransitionHelperKitKat.loadTitleInTransition(context);
        }

        @Override // android.support.v17.leanback.transition.LeanbackTransitionHelper.LeanbackTransitionHelperVersion
        public Object loadTitleOutTransition(Context context) {
            return LeanbackTransitionHelperKitKat.loadTitleOutTransition(context);
        }
    }

    static class LeanbackTransitionHelperDefault implements LeanbackTransitionHelperVersion {
        LeanbackTransitionHelperDefault() {
        }

        @Override // android.support.v17.leanback.transition.LeanbackTransitionHelper.LeanbackTransitionHelperVersion
        public Object loadTitleInTransition(Context context) {
            return TransitionHelper.loadTransition(context, R.transition.lb_title_in);
        }

        @Override // android.support.v17.leanback.transition.LeanbackTransitionHelper.LeanbackTransitionHelperVersion
        public Object loadTitleOutTransition(Context context) {
            return TransitionHelper.loadTransition(context, R.transition.lb_title_out);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            sImpl = new LeanbackTransitionHelperDefault();
        } else if (Build.VERSION.SDK_INT >= 19) {
            sImpl = new LeanbackTransitionHelperKitKatImpl();
        } else {
            sImpl = new LeanbackTransitionHelperDefault();
        }
    }

    public static Object loadTitleInTransition(Context context) {
        return sImpl.loadTitleInTransition(context);
    }

    public static Object loadTitleOutTransition(Context context) {
        return sImpl.loadTitleOutTransition(context);
    }
}
