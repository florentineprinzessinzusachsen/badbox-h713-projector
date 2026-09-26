package com.android.settingslib.drawer;

import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.settingslib.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SettingsDrawerAdapter extends BaseAdapter {
    private final SettingsDrawerActivity mActivity;
    private final ArrayList<Item> mItems = new ArrayList<>();

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SettingsDrawerAdapter(SettingsDrawerActivity settingsDrawerActivity) {
        this.mActivity = settingsDrawerActivity;
    }

    void updateCategories() {
        List<DashboardCategory> dashboardCategories = this.mActivity.getDashboardCategories();
        this.mItems.clear();
        this.mItems.add(null);
        Item item = new Item();
        item.label = this.mActivity.getString(R.string.home);
        item.icon = Icon.createWithResource(this.mActivity, R.drawable.home);
        this.mItems.add(item);
        for (int i = 0; i < dashboardCategories.size(); i++) {
            Item item2 = new Item();
            item2.icon = null;
            DashboardCategory dashboardCategory = dashboardCategories.get(i);
            item2.label = dashboardCategory.title;
            this.mItems.add(item2);
            for (int i2 = 0; i2 < dashboardCategory.tiles.size(); i2++) {
                Item item3 = new Item();
                Tile tile = dashboardCategory.tiles.get(i2);
                item3.label = tile.title;
                item3.icon = tile.icon;
                item3.tile = tile;
                this.mItems.add(item3);
            }
        }
        notifyDataSetChanged();
    }

    public Tile getTile(int i) {
        if (this.mItems.get(i) != null) {
            return this.mItems.get(i).tile;
        }
        return null;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mItems.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mItems.get(i);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return (this.mItems.get(i) == null || this.mItems.get(i).icon == null) ? false : true;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0043  */
    /* JADX WARN: Code duplicated, block: B:25:0x004b  */
    /* JADX WARN: Code duplicated, block: B:26:0x004e  */
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        Item item = this.mItems.get(i);
        if (item == null) {
            return (view == null || view.getId() != R.id.spacer) ? LayoutInflater.from(this.mActivity).inflate(R.layout.drawer_spacer, viewGroup, false) : view;
        }
        if (view != null && view.getId() == R.id.spacer) {
            view = null;
        }
        boolean z = item.icon != null;
        if (view != null) {
            if (z != (view.getId() == R.id.tile_item)) {
                LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.mActivity);
                if (z) {
                    i2 = R.layout.drawer_item;
                } else {
                    i2 = R.layout.drawer_category;
                }
                view = layoutInflaterFrom.inflate(i2, viewGroup, false);
            }
        } else {
            LayoutInflater layoutInflaterFrom2 = LayoutInflater.from(this.mActivity);
            if (z) {
                i2 = R.layout.drawer_item;
            } else {
                i2 = R.layout.drawer_category;
            }
            view = layoutInflaterFrom2.inflate(i2, viewGroup, false);
        }
        if (z) {
            ((ImageView) view.findViewById(android.R.id.icon)).setImageIcon(item.icon);
        }
        ((TextView) view.findViewById(android.R.id.title)).setText(item.label);
        return view;
    }

    private static class Item {
        public Icon icon;
        public CharSequence label;
        public Tile tile;

        private Item() {
        }
    }
}
