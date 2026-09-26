package android.support.v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(15)
@TargetApi(15)
class ViewCompatICSMr1 {
    ViewCompatICSMr1() {
    }

    public static boolean hasOnClickListeners(View view) {
        return view.hasOnClickListeners();
    }
}
