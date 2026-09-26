package com.rk_itvui.settings.language;

import android.content.Context;
import android.os.SystemProperties;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ashd.settings.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LanguageArrayAdapter extends ArrayAdapter<Language> {
    private List<Language> mItemList;
    private LayoutInflater mLayoutInflater;
    private int showCN;

    public static class LanguageHoder {
        public ImageView imgIcon;
        public TextView txtTitle;
    }

    public LanguageArrayAdapter(Context context, List<Language> list) {
        super(context, 0, list);
        this.showCN = SystemProperties.getInt(LanguageActivity.LANG_SHOW_CN, 0);
        this.mItemList = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public List<Language> getmItemList() {
        return this.mItemList;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        LanguageHoder languageHoder;
        if (view == null) {
            view = this.mLayoutInflater.inflate(R.layout.setting_language_listitem, (ViewGroup) null);
            languageHoder = new LanguageHoder();
            languageHoder.imgIcon = (ImageView) view.findViewById(R.id.list_icon);
            languageHoder.txtTitle = (TextView) view.findViewById(R.id.list_title);
            view.setTag(languageHoder);
        } else {
            languageHoder = (LanguageHoder) view.getTag();
        }
        Language item = getItem(i);
        languageHoder.imgIcon.setImageResource(item.getIconRes());
        languageHoder.txtTitle.setText(item.getLabel());
        if (this.showCN == 1) {
            languageHoder.txtTitle.setText(item.getLabel() + "::" + item.getLocale().getDisplayName() + "::" + item.getLocale().getCountry());
        }
        return view;
    }
}
