package com.rk_itvui.settings.dialog.old;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class Action implements Parcelable {
    public static Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() { // from class: com.rk_itvui.settings.dialog.old.Action.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Action createFromParcel(Parcel parcel) {
            return new Builder().key(parcel.readString()).title(parcel.readString()).description(parcel.readString()).intent((Intent) parcel.readParcelable(Intent.class.getClassLoader())).resourcePackageName(parcel.readString()).drawableResource(parcel.readInt()).checked(parcel.readInt() != 0).multilineDescription(parcel.readInt() != 0).checkSetId(parcel.readInt()).build();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Action[] newArray(int i) {
            return new Action[i];
        }
    };
    public static final int DEFAULT_CHECK_SET_ID = 1;
    public static final int NO_CHECK_SET = 0;
    public static final int NO_DRAWABLE = 0;
    private static final String TAG = "Action";
    private final int mCheckSetId;
    private boolean mChecked;
    private final String mDescription;
    private final int mDrawableResource;
    private boolean mEnabled;
    private final boolean mHasNext;
    private final boolean mInfoOnly;
    private final Intent mIntent;
    private final String mKey;
    private final boolean mMultilineDescription;
    private final String mResourcePackageName;
    private final String mTitle;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Uri getIconUri() {
        return null;
    }

    public static class Builder {
        private boolean mChecked;
        private String mDescription;
        private boolean mHasNext;
        private boolean mInfoOnly;
        private Intent mIntent;
        private String mKey;
        private boolean mMultilineDescription;
        private String mResourcePackageName;
        private String mTitle;
        private int mDrawableResource = 0;
        private int mCheckSetId = 0;
        private boolean mEnabled = true;

        public Action build() {
            return new Action(this.mKey, this.mTitle, this.mDescription, this.mResourcePackageName, this.mDrawableResource, this.mChecked, this.mMultilineDescription, this.mHasNext, this.mInfoOnly, this.mIntent, this.mCheckSetId, this.mEnabled);
        }

        public Builder key(String str) {
            this.mKey = str;
            return this;
        }

        public Builder title(String str) {
            this.mTitle = str;
            return this;
        }

        public Builder description(String str) {
            this.mDescription = str;
            return this;
        }

        public Builder intent(Intent intent) {
            this.mIntent = intent;
            return this;
        }

        public Builder resourcePackageName(String str) {
            this.mResourcePackageName = str;
            return this;
        }

        public Builder drawableResource(int i) {
            this.mDrawableResource = i;
            return this;
        }

        public Builder checked(boolean z) {
            this.mChecked = z;
            return this;
        }

        public Builder multilineDescription(boolean z) {
            this.mMultilineDescription = z;
            return this;
        }

        public Builder hasNext(boolean z) {
            this.mHasNext = z;
            return this;
        }

        public Builder infoOnly(boolean z) {
            this.mInfoOnly = z;
            return this;
        }

        public Builder checkSetId(int i) {
            this.mCheckSetId = i;
            return this;
        }

        public Builder enabled(boolean z) {
            this.mEnabled = z;
            return this;
        }
    }

    protected Action(String str, String str2, String str3, String str4, int i, boolean z, boolean z2, boolean z3, boolean z4, Intent intent, int i2, boolean z5) {
        this.mKey = str;
        this.mTitle = str2;
        this.mDescription = str3;
        this.mResourcePackageName = str4;
        this.mDrawableResource = i;
        this.mChecked = z;
        this.mMultilineDescription = z2;
        this.mHasNext = z3;
        this.mInfoOnly = z4;
        this.mIntent = intent;
        this.mCheckSetId = i2;
        this.mEnabled = z5;
    }

    public static ArrayList<Action> createActionsFromArrays(String[] strArr, String[] strArr2) {
        return createActionsFromArrays(strArr, strArr2, 0, null);
    }

    public static ArrayList<Action> createActionsFromArrays(String[] strArr, String[] strArr2, int i, String str) {
        int length = strArr.length;
        if (length != strArr2.length) {
            throw new IllegalArgumentException("Keys and titles dimensions must match");
        }
        ArrayList<Action> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < length; i2++) {
            Builder builder = new Builder();
            builder.key(strArr[i2]).title(strArr2[i2]).checkSetId(i);
            if (str != null) {
                if (str.equals(strArr[i2])) {
                    builder.checked(true);
                } else {
                    builder.checked(false);
                }
            }
            arrayList.add(builder.build());
        }
        return arrayList;
    }

    public String getKey() {
        return this.mKey;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public int getCheckSetId() {
        return this.mCheckSetId;
    }

    public boolean hasMultilineDescription() {
        return this.mMultilineDescription;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    public boolean hasNext() {
        return this.mHasNext;
    }

    public boolean infoOnly() {
        return this.mInfoOnly;
    }

    public Drawable getIndicator(Context context) {
        if (this.mDrawableResource == 0) {
            return null;
        }
        if (this.mResourcePackageName == null) {
            return context.getDrawable(this.mDrawableResource);
        }
        try {
            return context.createPackageContext(this.mResourcePackageName, 0).getDrawable(this.mDrawableResource);
        } catch (PackageManager.NameNotFoundException unused) {
            if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "No icon for this action.");
            }
            return null;
        } catch (Resources.NotFoundException unused2) {
            if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "No icon for this action.");
            }
            return null;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mKey);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
        parcel.writeParcelable(this.mIntent, i);
        parcel.writeString(this.mResourcePackageName);
        parcel.writeInt(this.mDrawableResource);
        parcel.writeInt(this.mChecked ? 1 : 0);
        parcel.writeInt(this.mMultilineDescription ? 1 : 0);
        parcel.writeInt(this.mCheckSetId);
    }
}
