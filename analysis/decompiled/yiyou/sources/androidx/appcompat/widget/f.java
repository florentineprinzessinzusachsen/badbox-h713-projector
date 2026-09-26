package androidx.appcompat.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/* JADX INFO: compiled from: AppCompatHintHelper.java */
/* JADX INFO: loaded from: classes.dex */
class f {
    static InputConnection a(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        if (inputConnection != null && editorInfo.hintText == null) {
            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                if (parent instanceof k0) {
                    editorInfo.hintText = ((k0) parent).a();
                    break;
                }
            }
        }
        return inputConnection;
    }
}
