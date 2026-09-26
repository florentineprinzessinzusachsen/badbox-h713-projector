package android.support.v4.app;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.text.Html;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(16)
@TargetApi(16)
class ShareCompatJB {
    ShareCompatJB() {
    }

    public static String escapeHtml(CharSequence charSequence) {
        return Html.escapeHtml(charSequence);
    }
}
