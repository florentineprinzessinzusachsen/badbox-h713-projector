package android.support.v17.leanback.widget;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class BackgroundHelper {
    static final BackgroundHelperVersionImpl sImpl;

    interface BackgroundHelperVersionImpl {
        void setBackgroundPreservingAlpha(View view, Drawable drawable);
    }

    private static final class BackgroundHelperStubImpl implements BackgroundHelperVersionImpl {
        BackgroundHelperStubImpl() {
        }

        @Override // android.support.v17.leanback.widget.BackgroundHelper.BackgroundHelperVersionImpl
        public void setBackgroundPreservingAlpha(View view, Drawable drawable) {
            view.setBackground(drawable);
        }
    }

    private static final class BackgroundHelperKitkatImpl implements BackgroundHelperVersionImpl {
        BackgroundHelperKitkatImpl() {
        }

        @Override // android.support.v17.leanback.widget.BackgroundHelper.BackgroundHelperVersionImpl
        public void setBackgroundPreservingAlpha(View view, Drawable drawable) {
            BackgroundHelperKitkat.setBackgroundPreservingAlpha(view, drawable);
        }
    }

    private BackgroundHelper() {
    }

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            sImpl = new BackgroundHelperKitkatImpl();
        } else {
            sImpl = new BackgroundHelperStubImpl();
        }
    }

    public static void setBackgroundPreservingAlpha(View view, Drawable drawable) {
        sImpl.setBackgroundPreservingAlpha(view, drawable);
    }
}
