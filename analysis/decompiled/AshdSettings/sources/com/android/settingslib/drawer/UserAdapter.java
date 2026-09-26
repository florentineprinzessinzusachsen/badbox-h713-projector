package com.android.settingslib.drawer;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.UserInfo;
import android.database.DataSetObserver;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.android.internal.util.UserIcons;
import com.android.settingslib.R;
import com.android.settingslib.drawable.UserIconDrawable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UserAdapter implements SpinnerAdapter, ListAdapter {
    private ArrayList<UserDetails> data;
    private final LayoutInflater mInflater;

    @Override // android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override // android.widget.Adapter
    public int getItemViewType(int i) {
        return 0;
    }

    @Override // android.widget.Adapter
    public int getViewTypeCount() {
        return 1;
    }

    @Override // android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return true;
    }

    @Override // android.widget.Adapter
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
    }

    @Override // android.widget.Adapter
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
    }

    public static class UserDetails {
        private final Drawable mIcon;
        private final String mName;
        private final UserHandle mUserHandle;

        public UserDetails(UserHandle userHandle, UserManager userManager, Context context) {
            Drawable defaultUserIcon;
            this.mUserHandle = userHandle;
            UserInfo userInfo = userManager.getUserInfo(this.mUserHandle.getIdentifier());
            if (userInfo.isManagedProfile()) {
                this.mName = context.getString(R.string.managed_user_title);
                defaultUserIcon = context.getDrawable(android.R.drawable.dropdown_ic_arrow_focused_holo_light);
            } else {
                this.mName = userInfo.name;
                int i = userInfo.id;
                if (userManager.getUserIcon(i) != null) {
                    defaultUserIcon = new BitmapDrawable(context.getResources(), userManager.getUserIcon(i));
                } else {
                    defaultUserIcon = UserIcons.getDefaultUserIcon(i, false);
                }
            }
            this.mIcon = encircle(context, defaultUserIcon);
        }

        private static Drawable encircle(Context context, Drawable drawable) {
            return new UserIconDrawable(UserIconDrawable.getSizeForList(context)).setIconDrawable(drawable).bake();
        }
    }

    public UserAdapter(Context context, ArrayList<UserDetails> arrayList) {
        if (arrayList == null) {
            throw new IllegalArgumentException("A list of user details must be provided");
        }
        this.data = arrayList;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public UserHandle getUserHandle(int i) {
        if (i < 0 || i >= this.data.size()) {
            return null;
        }
        return this.data.get(i).mUserHandle;
    }

    @Override // android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = createUser(viewGroup);
        }
        UserDetails userDetails = this.data.get(i);
        ((ImageView) view.findViewById(android.R.id.icon)).setImageDrawable(userDetails.mIcon);
        ((TextView) view.findViewById(android.R.id.title)).setText(getTitle(userDetails));
        return view;
    }

    private int getTitle(UserDetails userDetails) {
        int identifier = userDetails.mUserHandle.getIdentifier();
        if (identifier == -2 || identifier == ActivityManager.getCurrentUser()) {
            return R.string.category_personal;
        }
        return R.string.category_work;
    }

    private View createUser(ViewGroup viewGroup) {
        return this.mInflater.inflate(R.layout.user_preference, viewGroup, false);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.data.size();
    }

    @Override // android.widget.Adapter
    public UserDetails getItem(int i) {
        return this.data.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return this.data.get(i).mUserHandle.getIdentifier();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getDropDownView(i, view, viewGroup);
    }

    @Override // android.widget.Adapter
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    public static UserAdapter createUserSpinnerAdapter(UserManager userManager, Context context) {
        List<UserHandle> userProfiles = userManager.getUserProfiles();
        if (userProfiles.size() < 2) {
            return null;
        }
        UserHandle userHandle = new UserHandle(UserHandle.myUserId());
        userProfiles.remove(userHandle);
        userProfiles.add(0, userHandle);
        return createUserAdapter(userManager, context, userProfiles);
    }

    public static UserAdapter createUserAdapter(UserManager userManager, Context context, List<UserHandle> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new UserDetails(list.get(i), userManager, context));
        }
        return new UserAdapter(context, arrayList);
    }
}
