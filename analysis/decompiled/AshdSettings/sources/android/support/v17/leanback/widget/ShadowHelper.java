package android.support.v17.leanback.widget;

import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
final class ShadowHelper {
    static final ShadowHelper sInstance = new ShadowHelper();
    ShadowHelperVersionImpl mImpl;
    boolean mSupportsDynamicShadow;

    interface ShadowHelperVersionImpl {
        Object addDynamicShadow(View view, float f, float f2, int i);

        void setShadowFocusLevel(Object obj, float f);

        void setZ(View view, float f);
    }

    private static final class ShadowHelperStubImpl implements ShadowHelperVersionImpl {
        @Override // android.support.v17.leanback.widget.ShadowHelper.ShadowHelperVersionImpl
        public Object addDynamicShadow(View view, float f, float f2, int i) {
            return null;
        }

        @Override // android.support.v17.leanback.widget.ShadowHelper.ShadowHelperVersionImpl
        public void setShadowFocusLevel(Object obj, float f) {
        }

        @Override // android.support.v17.leanback.widget.ShadowHelper.ShadowHelperVersionImpl
        public void setZ(View view, float f) {
        }

        ShadowHelperStubImpl() {
        }
    }

    private static final class ShadowHelperApi21Impl implements ShadowHelperVersionImpl {
        ShadowHelperApi21Impl() {
        }

        @Override // android.support.v17.leanback.widget.ShadowHelper.ShadowHelperVersionImpl
        public Object addDynamicShadow(View view, float f, float f2, int i) {
            return ShadowHelperApi21.addDynamicShadow(view, f, f2, i);
        }

        @Override // android.support.v17.leanback.widget.ShadowHelper.ShadowHelperVersionImpl
        public void setShadowFocusLevel(Object obj, float f) {
            ShadowHelperApi21.setShadowFocusLevel(obj, f);
        }

        @Override // android.support.v17.leanback.widget.ShadowHelper.ShadowHelperVersionImpl
        public void setZ(View view, float f) {
            ShadowHelperApi21.setZ(view, f);
        }
    }

    private ShadowHelper() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mSupportsDynamicShadow = true;
            this.mImpl = new ShadowHelperApi21Impl();
        } else {
            this.mImpl = new ShadowHelperStubImpl();
        }
    }

    public static ShadowHelper getInstance() {
        return sInstance;
    }

    public boolean supportsDynamicShadow() {
        return this.mSupportsDynamicShadow;
    }

    public Object addDynamicShadow(View view, float f, float f2, int i) {
        return this.mImpl.addDynamicShadow(view, f, f2, i);
    }

    public void setShadowFocusLevel(Object obj, float f) {
        this.mImpl.setShadowFocusLevel(obj, f);
    }

    public void setZ(View view, float f) {
        this.mImpl.setZ(view, f);
    }
}
