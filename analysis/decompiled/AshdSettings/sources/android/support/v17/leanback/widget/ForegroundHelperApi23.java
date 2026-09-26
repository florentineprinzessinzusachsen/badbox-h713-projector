package android.support.v17.leanback.widget;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(23)
@TargetApi(23)
class ForegroundHelperApi23 {
    ForegroundHelperApi23() {
    }

    public static Drawable getForeground(View view) {
        return view.getForeground();
    }

    public static void setForeground(View view, Drawable drawable) {
        view.setForeground(drawable);
    }
}
