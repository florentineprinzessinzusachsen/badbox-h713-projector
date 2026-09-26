package com.rk_itvui.settings;

import android.app.Activity;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.widget.PagerAdapter;
import com.android.internal.widget.ViewPager;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class SettingsGridViewAdpter extends PagerAdapter {
    private static final String TAG = "SettingsGridViewAdpter:BaseAdapter";
    private LayoutInflater flater;
    private Activity mActivity;
    protected ChangeObserver mChangeObserver;
    protected Context mContext;
    private ArrayList<SettingItems> mList;
    OnItemClickListener mOnItemClickListener;
    OnItemLongClickListener mOnItemLongClickListener;
    private final int EDGE_PADDING = 2;
    private int mTextColor = -1;
    protected int mItemCountPerPage = 1;

    public interface OnItemClickListener {
        void onItemClick(ViewPager viewPager, View view, int i);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(ViewPager viewPager, View view, int i);
    }

    public abstract void bindView(View view, Context context, int i);

    public long getItemId(int i) {
        return i;
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public abstract View newView(Context context, ViewGroup viewGroup, int i);

    protected void onContentChanged() {
    }

    public SettingsGridViewAdpter(Context context, Settings settings, ArrayList<SettingItems> arrayList) {
        this.mActivity = null;
        this.mList = null;
        this.flater = null;
        this.mActivity = settings;
        this.mList = arrayList;
        this.flater = (LayoutInflater) this.mActivity.getSystemService("layout_inflater");
        this.mContext = context;
    }

    void setTextColor(int i) {
        this.mTextColor = i;
    }

    public int getListCount() {
        if (this.mList != null) {
            return this.mList.size();
        }
        return 0;
    }

    public int getCount() {
        if (this.mList != null) {
            return this.mList.size() % this.mItemCountPerPage > 0 ? (this.mList.size() / this.mItemCountPerPage) + 1 : this.mList.size() / this.mItemCountPerPage;
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.mList != null) {
            return this.mList.get(i);
        }
        return null;
    }

    public void setCountPerPage(int i) {
        if (i <= 0) {
            i = 1;
        }
        this.mItemCountPerPage = i;
    }

    public int getCountPerPage() {
        return this.mItemCountPerPage;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = getView(i, null, viewGroup);
        viewGroup.addView(view);
        return view;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = newView(this.mContext, viewGroup, i);
        }
        bindView(view, this.mContext, i);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    private class ChangeObserver extends ContentObserver {
        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        public ChangeObserver() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            SettingsGridViewAdpter.this.onContentChanged();
        }
    }
}
