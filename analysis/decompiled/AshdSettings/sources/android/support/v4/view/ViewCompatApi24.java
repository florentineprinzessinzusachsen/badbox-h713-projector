package android.support.v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.PointerIcon;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(24)
@TargetApi(24)
class ViewCompatApi24 {
    ViewCompatApi24() {
    }

    public static void setPointerIcon(View view, Object obj) {
        view.setPointerIcon((PointerIcon) obj);
    }
}
