package android.support.v17.leanback.widget;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(19)
@TargetApi(19)
class BackgroundHelperKitkat {
    BackgroundHelperKitkat() {
    }

    public static void setBackgroundPreservingAlpha(View view, Drawable drawable) {
        if (view.getBackground() != null) {
            drawable.setAlpha(view.getBackground().getAlpha());
        }
        view.setBackground(drawable);
    }
}
