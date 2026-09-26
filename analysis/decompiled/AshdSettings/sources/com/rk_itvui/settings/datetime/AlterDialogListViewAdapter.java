package com.rk_itvui.settings.datetime;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.ScreenInformation;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AlterDialogListViewAdapter extends BaseAdapter {
    private LayoutInflater flater;
    private ArrayList<String> mArrayList;
    private Context mContext;
    private int mSelection = -1;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public AlterDialogListViewAdapter(Context context, ArrayList<String> arrayList) {
        this.mContext = null;
        this.mArrayList = null;
        this.flater = null;
        this.mContext = context;
        this.mArrayList = arrayList;
        this.flater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
    }

    public void setSelection(int i) {
        if (i < 0 || i >= this.mArrayList.size()) {
            return;
        }
        this.mSelection = i;
    }

    public int getSelection() {
        return this.mSelection;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Log.d("AlterDialogListViewAdapter", "getCount() = " + this.mArrayList.size());
        if (this.mArrayList != null) {
            return this.mArrayList.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (this.mArrayList != null) {
            return this.mArrayList.get(i);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        RelativeLayout relativeLayout = (RelativeLayout) this.flater.inflate(R.layout.alterdialog_listview_item, (ViewGroup) null);
        TextView textView = (TextView) relativeLayout.findViewById(R.id.list_content);
        ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.list_img);
        textView.setText(this.mArrayList.get(i));
        textView.setTextSize((ScreenInformation.mScreenWidth / 40.0f) * ScreenInformation.mDpiRatio);
        if (this.mSelection == i) {
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.selected);
        } else {
            imageView.setVisibility(4);
            imageView.setImageBitmap(null);
        }
        return relativeLayout;
    }
}
