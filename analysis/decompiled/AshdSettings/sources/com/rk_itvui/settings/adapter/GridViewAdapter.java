package com.rk_itvui.settings.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class GridViewAdapter extends BaseAdapter {
    private Integer[] mBackground;
    private String[] mTitle;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public GridViewAdapter(Integer[] numArr, String[] strArr) {
        this.mBackground = numArr;
        this.mTitle = strArr;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mTitle.length;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mTitle[i];
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_view, viewGroup, false);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView = (TextView) view.findViewById(R.id.tv_info);
        viewHolder.textView.setBackgroundResource(this.mBackground[i].intValue());
        viewHolder.textView.setText(this.mTitle[i]);
        return view;
    }

    static class ViewHolder {
        TextView textView;

        ViewHolder() {
        }
    }
}
