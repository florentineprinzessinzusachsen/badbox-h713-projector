package com.rk_itvui.settings.dialog.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

/* JADX INFO: loaded from: classes.dex */
public interface ScrollAdapterBase extends ListAdapter {
    View getScrapView(ViewGroup viewGroup);

    void viewRemoved(View view);
}
