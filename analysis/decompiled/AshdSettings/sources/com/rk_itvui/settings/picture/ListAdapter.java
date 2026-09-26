package com.rk_itvui.settings.picture;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.picture.model.SettingItem;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ListAdapter extends BaseAdapter implements View.OnClickListener {
    private static String TAG = "ListAdapter";
    int mCurrentItemPos;
    private ArrayList<SettingItem> mSettings;
    private LayoutInflater mlayoutInflater;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public ListAdapter(Context context, ArrayList<SettingItem> arrayList) {
        this.mlayoutInflater = LayoutInflater.from(context);
        this.mSettings = arrayList;
    }

    public int getCurrentItemPos() {
        return this.mCurrentItemPos;
    }

    public void setCurrentItemPos(int i) {
        this.mCurrentItemPos = i;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mSettings.size();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d(TAG, "getView position=" + i);
        SettingItem settingItem = this.mSettings.get(i);
        View viewInflate = this.mlayoutInflater.inflate(R.layout.pic_list_item, viewGroup, false);
        ((TextView) viewInflate.findViewById(R.id.nameText)).setText(settingItem.getTitle());
        int type = settingItem.getType();
        Button button = (Button) viewInflate.findViewById(R.id.leftButton);
        Button button2 = (Button) viewInflate.findViewById(R.id.rightButton);
        if (type != 4) {
            button.setVisibility(8);
            button2.setVisibility(8);
        }
        TextView textView = (TextView) viewInflate.findViewById(R.id.valueText);
        switch (type) {
            case 1:
            case 2:
            case 5:
                textView.setText(settingItem.getStringValue());
                break;
            case 3:
                textView.setText(new Integer(settingItem.getIntValue()).toString());
                break;
            case 4:
                textView.setText(settingItem.getLevelValue());
                button.setEnabled(!settingItem.isMin());
                button2.setEnabled(!settingItem.isMax());
                break;
            default:
                Log.e(TAG, "invalid type");
                break;
        }
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        return viewInflate;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Log.d(TAG, "onClick" + view.getId());
        int id = view.getId();
        if (id == R.id.leftButton) {
            Log.d(TAG, "leftButton");
        } else {
            if (id != R.id.rightButton) {
                return;
            }
            Log.d(TAG, "rightButton");
        }
    }

    public void update(ArrayList<SettingItem> arrayList) {
        this.mSettings = arrayList;
    }
}
