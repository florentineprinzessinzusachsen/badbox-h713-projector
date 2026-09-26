package com.android.settingslib.drawer;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DashboardCategory implements Parcelable {
    public static final Parcelable.Creator<DashboardCategory> CREATOR = new Parcelable.Creator<DashboardCategory>() { // from class: com.android.settingslib.drawer.DashboardCategory.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DashboardCategory createFromParcel(Parcel parcel) {
            return new DashboardCategory(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DashboardCategory[] newArray(int i) {
            return new DashboardCategory[i];
        }
    };
    public String key;
    public int priority;
    public List<Tile> tiles = new ArrayList();
    public CharSequence title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DashboardCategory() {
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }

    public void addTile(int i, Tile tile) {
        this.tiles.add(i, tile);
    }

    public void removeTile(Tile tile) {
        this.tiles.remove(tile);
    }

    public void removeTile(int i) {
        this.tiles.remove(i);
    }

    public int getTilesCount() {
        return this.tiles.size();
    }

    public Tile getTile(int i) {
        return this.tiles.get(i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.title, parcel, i);
        parcel.writeString(this.key);
        parcel.writeInt(this.priority);
        int size = this.tiles.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.tiles.get(i2).writeToParcel(parcel, i);
        }
    }

    public void readFromParcel(Parcel parcel) {
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.key = parcel.readString();
        this.priority = parcel.readInt();
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            this.tiles.add(Tile.CREATOR.createFromParcel(parcel));
        }
    }

    DashboardCategory(Parcel parcel) {
        readFromParcel(parcel);
    }
}
