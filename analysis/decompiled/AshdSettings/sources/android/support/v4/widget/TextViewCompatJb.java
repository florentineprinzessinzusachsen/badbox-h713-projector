package android.support.v4.widget;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(16)
@TargetApi(16)
class TextViewCompatJb {
    TextViewCompatJb() {
    }

    static int getMaxLines(TextView textView) {
        return textView.getMaxLines();
    }

    static int getMinLines(TextView textView) {
        return textView.getMinLines();
    }
}
