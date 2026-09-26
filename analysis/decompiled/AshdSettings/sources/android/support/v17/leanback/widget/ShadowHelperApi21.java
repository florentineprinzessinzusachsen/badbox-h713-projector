package android.support.v17.leanback.widget;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
@TargetApi(21)
class ShadowHelperApi21 {
    static final ViewOutlineProvider sOutlineProvider = new ViewOutlineProvider() { // from class: android.support.v17.leanback.widget.ShadowHelperApi21.1
        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRect(0, 0, view.getWidth(), view.getHeight());
            outline.setAlpha(1.0f);
        }
    };

    ShadowHelperApi21() {
    }

    static class ShadowImpl {
        float mFocusedZ;
        float mNormalZ;
        View mShadowContainer;

        ShadowImpl() {
        }
    }

    public static Object addDynamicShadow(View view, float f, float f2, int i) {
        if (i > 0) {
            RoundedRectHelperApi21.setClipToRoundedOutline(view, true, i);
        } else {
            view.setOutlineProvider(sOutlineProvider);
        }
        ShadowImpl shadowImpl = new ShadowImpl();
        shadowImpl.mShadowContainer = view;
        shadowImpl.mNormalZ = f;
        shadowImpl.mFocusedZ = f2;
        view.setZ(shadowImpl.mNormalZ);
        return shadowImpl;
    }

    public static void setShadowFocusLevel(Object obj, float f) {
        ShadowImpl shadowImpl = (ShadowImpl) obj;
        shadowImpl.mShadowContainer.setZ(shadowImpl.mNormalZ + (f * (shadowImpl.mFocusedZ - shadowImpl.mNormalZ)));
    }

    public static void setZ(View view, float f) {
        view.setZ(f);
    }
}
