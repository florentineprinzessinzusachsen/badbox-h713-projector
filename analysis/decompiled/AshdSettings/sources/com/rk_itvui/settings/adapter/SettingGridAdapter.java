package com.rk_itvui.settings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.model.ListItem;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SettingGridAdapter extends ArrayAdapter<ListItem> {
    private List<ListItem> mItemList;
    private LayoutInflater mLayoutInflater;

    public static class GridHoder {
        public ImageView imgIcon;
        public TextView txtTitle;
    }

    public SettingGridAdapter(Context context, List<ListItem> list) {
        super(context, 0, list);
        this.mItemList = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public List<ListItem> getmItemList() {
        return this.mItemList;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        GridHoder gridHoder;
        if (view == null) {
            view = this.mLayoutInflater.inflate(R.layout.setting_griditem, (ViewGroup) null);
            gridHoder = new GridHoder();
            gridHoder.imgIcon = (ImageView) view.findViewById(R.id.list_icon);
            gridHoder.txtTitle = (TextView) view.findViewById(R.id.list_title);
            view.setTag(gridHoder);
        } else {
            gridHoder = (GridHoder) view.getTag();
        }
        ListItem item = getItem(i);
        gridHoder.imgIcon.setImageResource(item.getIconRes());
        gridHoder.txtTitle.setText(item.getTitle());
        return view;
    }
}
