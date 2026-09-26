package androidx.appcompat.widget;

import android.content.res.Resources;
import android.widget.SpinnerAdapter;

/* JADX INFO: compiled from: ThemedSpinnerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public interface z extends SpinnerAdapter {
    Resources.Theme getDropDownViewTheme();

    void setDropDownViewTheme(Resources.Theme theme);
}
