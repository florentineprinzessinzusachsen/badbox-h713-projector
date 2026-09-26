package com.rk_itvui.settings.developer;

import android.content.Context;
import android.graphics.Bitmap;
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
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater flater;
    private Context mContext;
    private Map<String, ArrayList<SettingItem>> mMap;
    private int mIndicator = 0;
    private int mLevel = 0;
    private int mParentId = -1;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public void setParentId(int i) {
        this.mParentId = i;
    }

    public int getParentId() {
        return this.mParentId;
    }

    public void setLevel(int i) {
        this.mLevel = i;
    }

    public int getLevel() {
        return this.mLevel;
    }

    public ListViewAdapter(Context context, Map<String, ArrayList<SettingItem>> map) {
        this.mMap = null;
        this.mContext = null;
        this.flater = null;
        this.mContext = context;
        this.mMap = map;
        this.flater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public void invalidate() {
        notifyDataSetChanged();
    }

    public void setSelection(int i) {
        this.mIndicator = i;
        invalidate();
    }

    public int getSelection() {
        return this.mIndicator;
    }

    private int getCount(String str) {
        ArrayList<SettingItem> arrayList;
        if (str == null || this.mMap == null || (arrayList = this.mMap.get(str)) == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            SettingItem settingItem = arrayList.get(i2);
            if (settingItem.mParentId == this.mParentId && this.mLevel == settingItem.mLevel) {
                i++;
            }
        }
        Log.d("ListViewAdapter", "getCount(), size = " + i);
        return i;
    }

    private SettingItem getContentId(int i) {
        ArrayList<SettingItem> arrayList;
        String content = getContent();
        if (content != null && i >= 0 && this.mMap != null && (arrayList = this.mMap.get(content)) != null) {
            int i2 = 0;
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                SettingItem settingItem = arrayList.get(i3);
                if (settingItem.mLevel == this.mLevel && settingItem.mParentId == this.mParentId) {
                    if (i2 == i) {
                        return settingItem;
                    }
                    i2++;
                }
            }
        }
        return null;
    }

    public String getContent() {
        if (this.mIndicator != 0) {
            return null;
        }
        return "development";
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.mIndicator != 0) {
            return 0;
        }
        return getCount("development");
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        String content;
        if (this.mMap == null || (content = getContent()) == null) {
            return null;
        }
        return this.mMap.get(content);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        SettingItem contentId = getContentId(i);
        if (contentId == null) {
            return null;
        }
        if (contentId.getView() != null) {
            return contentId.getView();
        }
        RelativeLayout relativeLayout = (RelativeLayout) this.flater.inflate(R.layout.listview_item, (ViewGroup) null);
        int i2 = contentId.mId;
        boolean clickable = contentId.getClickable();
        relativeLayout.setClickable(!clickable);
        int i3 = !clickable ? -7829368 : -1;
        if (contentId.getStatus() != null) {
            TextView textView = (TextView) relativeLayout.findViewById(R.id.list_status_text);
            textView.setVisibility(0);
            textView.setTextColor(i3);
            textView.setText(contentId.getStatus());
            textView.setTextSize(this.mContext.getResources().getDimension(R.dimen.setting_list_size));
        }
        if (contentId.getSummary() != null) {
            TextView textView2 = (TextView) relativeLayout.findViewById(R.id.summary);
            textView2.setVisibility(0);
            textView2.setTextColor(i3);
            textView2.setText(contentId.getSummary());
            textView2.setTextSize(this.mContext.getResources().getDimension(R.dimen.setting_list_size));
        }
        if (contentId.getDrawable() != null) {
            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.list_status_img);
            imageView.setVisibility(0);
            Bitmap bitmapBitMapScale = bitMapScale(contentId.getDrawable(), ScreenInformation.mDpiRatio);
            if (bitmapBitMapScale != null) {
                imageView.setImageBitmap(bitmapBitMapScale);
            }
        }
        if (i2 != -1) {
            TextView textView3 = (TextView) relativeLayout.findViewById(R.id.list_content);
            textView3.setTextColor(i3);
            textView3.setTextSize(this.mContext.getResources().getDimension(R.dimen.setting_list_size));
            if (contentId.mText != null && contentId.isAdd()) {
                textView3.setText(contentId.mText);
            } else {
                textView3.setText(this.mContext.getResources().getString(i2));
            }
            relativeLayout.setTag(Integer.valueOf(i2));
        }
        return relativeLayout;
    }

    private Bitmap bitMapScale(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        float f2 = (ScreenInformation.mScreenWidth / 1280.0f) * f;
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * f2), (int) (bitmap.getHeight() * f2), true);
    }

    private SettingItem findSettingItem(String str, int i) {
        ArrayList<SettingItem> arrayList = this.mMap.get(str);
        if (arrayList == null) {
            return null;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            SettingItem settingItem = arrayList.get(i2);
            if (settingItem.mId == i) {
                return settingItem;
            }
        }
        return null;
    }

    public boolean setSettingItemClickable(int i, boolean z) {
        if (this.mMap == null) {
            return false;
        }
        SettingItem settingItem = null;
        settingItem.setClickable(z);
        return true;
    }
}
