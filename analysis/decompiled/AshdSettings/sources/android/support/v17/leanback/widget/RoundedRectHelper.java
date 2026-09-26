package android.support.v17.leanback.widget;

import android.os.Build;
import android.support.v17.leanback.R;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
final class RoundedRectHelper {
    private static final RoundedRectHelper sInstance = new RoundedRectHelper();
    private final Impl mImpl;

    interface Impl {
        void setClipToRoundedOutline(View view, boolean z, int i);
    }

    public static RoundedRectHelper getInstance() {
        return sInstance;
    }

    public static boolean supportsRoundedCorner() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public void setClipToRoundedOutline(View view, boolean z, int i) {
        this.mImpl.setClipToRoundedOutline(view, z, i);
    }

    public void setClipToRoundedOutline(View view, boolean z) {
        this.mImpl.setClipToRoundedOutline(view, z, view.getResources().getDimensionPixelSize(R.dimen.lb_rounded_rect_corner_radius));
    }

    private static final class StubImpl implements Impl {
        @Override // android.support.v17.leanback.widget.RoundedRectHelper.Impl
        public void setClipToRoundedOutline(View view, boolean z, int i) {
        }

        StubImpl() {
        }
    }

    private static final class Api21Impl implements Impl {
        Api21Impl() {
        }

        @Override // android.support.v17.leanback.widget.RoundedRectHelper.Impl
        public void setClipToRoundedOutline(View view, boolean z, int i) {
            RoundedRectHelperApi21.setClipToRoundedOutline(view, z, i);
        }
    }

    private RoundedRectHelper() {
        if (supportsRoundedCorner()) {
            this.mImpl = new Api21Impl();
        } else {
            this.mImpl = new StubImpl();
        }
    }
}
