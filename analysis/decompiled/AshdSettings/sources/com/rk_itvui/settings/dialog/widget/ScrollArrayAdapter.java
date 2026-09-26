package com.rk_itvui.settings.dialog.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ScrollArrayAdapter<T> extends ArrayAdapter<T> implements ScrollAdapter {
    private int mLayoutResource;

    @Override // com.rk_itvui.settings.dialog.widget.ScrollAdapter
    public ScrollAdapterBase getExpandAdapter() {
        return null;
    }

    @Override // com.rk_itvui.settings.dialog.widget.ScrollAdapterBase
    public void viewRemoved(View view) {
    }

    public ScrollArrayAdapter(Context context, int i) {
        super(context, i);
        this.mLayoutResource = -1;
    }

    public ScrollArrayAdapter(Context context, int i, int i2) {
        super(context, i, i2);
        this.mLayoutResource = -1;
        this.mLayoutResource = i;
    }

    public ScrollArrayAdapter(Context context, int i, T[] tArr) {
        super(context, i, tArr);
        this.mLayoutResource = -1;
    }

    public ScrollArrayAdapter(Context context, int i, int i2, T[] tArr) {
        super(context, i, i2, tArr);
        this.mLayoutResource = -1;
        this.mLayoutResource = i;
    }

    public ScrollArrayAdapter(Context context, int i, List<T> list) {
        super(context, i, list);
        this.mLayoutResource = -1;
    }

    public ScrollArrayAdapter(Context context, int i, int i2, List<T> list) {
        super(context, i, i2, list);
        this.mLayoutResource = -1;
        this.mLayoutResource = i;
    }

    @Override // com.rk_itvui.settings.dialog.widget.ScrollAdapterBase
    public View getScrapView(ViewGroup viewGroup) {
        if (getCount() > 0) {
            return getView(0, null, viewGroup);
        }
        if (this.mLayoutResource != -1) {
            return ((LayoutInflater) viewGroup.getContext().getSystemService("layout_inflater")).inflate(this.mLayoutResource, viewGroup);
        }
        return new TextView(viewGroup.getContext());
    }
}
