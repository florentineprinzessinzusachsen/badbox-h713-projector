package com.rk_itvui.settings.datetime;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.SimpleAdapter;
import com.rk_itvui.settings.ScreenInformation;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class RemindViewAdapter extends SimpleAdapter {
    private LayoutInflater flater;
    private Context mContext;
    private List<? extends Map<String, ?>> mData;
    private String[] mFrom;
    private int mIndicator;
    private LayoutInflater mInflater;
    private int mLevel;
    private int mParentId;
    private int mResource;
    private int[] mTo;
    private SimpleAdapter.ViewBinder mViewBinder;

    @Override // android.widget.SimpleAdapter, android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public RemindViewAdapter(Context context, List<? extends Map<String, ?>> list, int i, String[] strArr, int[] iArr) {
        super(context, list, i, strArr, iArr);
        this.mContext = null;
        this.flater = null;
        this.mLevel = 0;
        this.mParentId = -1;
        this.mIndicator = 0;
        this.mData = list;
        this.mResource = i;
        this.mFrom = strArr;
        this.mTo = iArr;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // android.widget.SimpleAdapter, android.widget.Adapter
    public int getCount() {
        return this.mData.size();
    }

    @Override // android.widget.SimpleAdapter, android.widget.Adapter
    public Object getItem(int i) {
        return this.mData.get(i);
    }

    public int getParentId() {
        return this.mParentId;
    }

    private Bitmap bitMapScale(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        float f2 = (ScreenInformation.mScreenWidth / 1280.0f) * f;
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * f2), (int) (bitmap.getHeight() * f2), true);
    }
}
