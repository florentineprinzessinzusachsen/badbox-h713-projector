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
public class SettingListAdapter extends ArrayAdapter<ListItem> {
    private List<ListItem> mItemList;
    private LayoutInflater mLayoutInflater;

    public static class ListHoder {
        public ImageView imgIcon;
        public TextView txtDetail;
        public TextView txtTitle;
    }

    public SettingListAdapter(Context context, List<ListItem> list) {
        super(context, 0, list);
        this.mItemList = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public List<ListItem> getmItemList() {
        return this.mItemList;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListHoder listHoder;
        if (view == null) {
            view = this.mLayoutInflater.inflate(R.layout.setting_listitem, (ViewGroup) null);
            listHoder = new ListHoder();
            listHoder.imgIcon = (ImageView) view.findViewById(R.id.list_icon);
            listHoder.txtTitle = (TextView) view.findViewById(R.id.list_title);
            listHoder.txtDetail = (TextView) view.findViewById(R.id.list_detail);
            view.setTag(listHoder);
        } else {
            listHoder = (ListHoder) view.getTag();
        }
        ListItem item = getItem(i);
        listHoder.imgIcon.setImageResource(item.getIconRes());
        listHoder.txtTitle.setText(item.getTitle());
        listHoder.txtDetail.setText(item.getDetail());
        return view;
    }
}
