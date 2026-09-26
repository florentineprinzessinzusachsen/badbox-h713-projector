package android.support.v4.widget;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(19)
@TargetApi(19)
class ListViewCompatKitKat {
    ListViewCompatKitKat() {
    }

    static void scrollListBy(ListView listView, int i) {
        listView.scrollListBy(i);
    }
}
