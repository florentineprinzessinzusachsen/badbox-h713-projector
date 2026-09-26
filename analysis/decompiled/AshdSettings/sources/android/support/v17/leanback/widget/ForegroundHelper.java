package android.support.v17.leanback.widget;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
final class ForegroundHelper {
    static final ForegroundHelper sInstance = new ForegroundHelper();
    ForegroundHelperVersionImpl mImpl;

    interface ForegroundHelperVersionImpl {
        Drawable getForeground(View view);

        void setForeground(View view, Drawable drawable);
    }

    private static final class ForegroundHelperApi23Impl implements ForegroundHelperVersionImpl {
        ForegroundHelperApi23Impl() {
        }

        @Override // android.support.v17.leanback.widget.ForegroundHelper.ForegroundHelperVersionImpl
        public void setForeground(View view, Drawable drawable) {
            ForegroundHelperApi23.setForeground(view, drawable);
        }

        @Override // android.support.v17.leanback.widget.ForegroundHelper.ForegroundHelperVersionImpl
        public Drawable getForeground(View view) {
            return ForegroundHelperApi23.getForeground(view);
        }
    }

    private static final class ForegroundHelperStubImpl implements ForegroundHelperVersionImpl {
        @Override // android.support.v17.leanback.widget.ForegroundHelper.ForegroundHelperVersionImpl
        public Drawable getForeground(View view) {
            return null;
        }

        @Override // android.support.v17.leanback.widget.ForegroundHelper.ForegroundHelperVersionImpl
        public void setForeground(View view, Drawable drawable) {
        }

        ForegroundHelperStubImpl() {
        }
    }

    private ForegroundHelper() {
        if (supportsForeground()) {
            this.mImpl = new ForegroundHelperApi23Impl();
        } else {
            this.mImpl = new ForegroundHelperStubImpl();
        }
    }

    public static ForegroundHelper getInstance() {
        return sInstance;
    }

    public static boolean supportsForeground() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public Drawable getForeground(View view) {
        return this.mImpl.getForeground(view);
    }

    public void setForeground(View view, Drawable drawable) {
        this.mImpl.setForeground(view, drawable);
    }
}
