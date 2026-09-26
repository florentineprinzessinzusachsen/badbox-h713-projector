package android.support.v17.leanback.widget;

import android.os.Build;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
final class StaticShadowHelper {
    static final StaticShadowHelper sInstance = new StaticShadowHelper();
    ShadowHelperVersionImpl mImpl;
    boolean mSupportsShadow;

    interface ShadowHelperVersionImpl {
        Object addStaticShadow(ViewGroup viewGroup);

        void prepareParent(ViewGroup viewGroup);

        void setShadowFocusLevel(Object obj, float f);
    }

    private static final class ShadowHelperStubImpl implements ShadowHelperVersionImpl {
        @Override // android.support.v17.leanback.widget.StaticShadowHelper.ShadowHelperVersionImpl
        public Object addStaticShadow(ViewGroup viewGroup) {
            return null;
        }

        @Override // android.support.v17.leanback.widget.StaticShadowHelper.ShadowHelperVersionImpl
        public void prepareParent(ViewGroup viewGroup) {
        }

        @Override // android.support.v17.leanback.widget.StaticShadowHelper.ShadowHelperVersionImpl
        public void setShadowFocusLevel(Object obj, float f) {
        }

        ShadowHelperStubImpl() {
        }
    }

    private static final class ShadowHelperJbmr2Impl implements ShadowHelperVersionImpl {
        ShadowHelperJbmr2Impl() {
        }

        @Override // android.support.v17.leanback.widget.StaticShadowHelper.ShadowHelperVersionImpl
        public void prepareParent(ViewGroup viewGroup) {
            ShadowHelperJbmr2.prepareParent(viewGroup);
        }

        @Override // android.support.v17.leanback.widget.StaticShadowHelper.ShadowHelperVersionImpl
        public Object addStaticShadow(ViewGroup viewGroup) {
            return ShadowHelperJbmr2.addShadow(viewGroup);
        }

        @Override // android.support.v17.leanback.widget.StaticShadowHelper.ShadowHelperVersionImpl
        public void setShadowFocusLevel(Object obj, float f) {
            ShadowHelperJbmr2.setShadowFocusLevel(obj, f);
        }
    }

    private StaticShadowHelper() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mSupportsShadow = true;
            this.mImpl = new ShadowHelperJbmr2Impl();
        } else {
            this.mSupportsShadow = false;
            this.mImpl = new ShadowHelperStubImpl();
        }
    }

    public static StaticShadowHelper getInstance() {
        return sInstance;
    }

    public boolean supportsShadow() {
        return this.mSupportsShadow;
    }

    public void prepareParent(ViewGroup viewGroup) {
        this.mImpl.prepareParent(viewGroup);
    }

    public Object addStaticShadow(ViewGroup viewGroup) {
        return this.mImpl.addStaticShadow(viewGroup);
    }

    public void setShadowFocusLevel(Object obj, float f) {
        this.mImpl.setShadowFocusLevel(obj, f);
    }
}
