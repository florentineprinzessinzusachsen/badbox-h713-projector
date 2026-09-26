package com.rk_itvui.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.UserHandle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class MyAppSecurityPermissions {
    private static final String TAG = "AppSecurityPermissions";
    public static final int WHICH_ALL = 65535;
    public static final int WHICH_NEW = 4;
    private static final boolean localLOGV = false;
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final CharSequence mNewPermPrefix;
    private String mPackageName;
    private final PermissionInfoComparator mPermComparator;
    private final PermissionGroupInfoComparator mPermGroupComparator;
    private final Map<String, MyPermissionGroupInfo> mPermGroups;
    private final List<MyPermissionGroupInfo> mPermGroupsList;
    private final List<MyPermissionInfo> mPermsList;
    private final PackageManager mPm;

    public List<MyPermissionInfo> getmPermsList() {
        return this.mPermsList;
    }

    public List<MyPermissionGroupInfo> getmPermGroupsList() {
        return this.mPermGroupsList;
    }

    static class MyPermissionGroupInfo extends PermissionGroupInfo {
        final ArrayList<MyPermissionInfo> mAllPermissions;
        CharSequence mLabel;
        final ArrayList<MyPermissionInfo> mNewPermissions;

        @Override // android.content.pm.PermissionGroupInfo
        public String toString() {
            return "MyPermissionGroupInfo{mLabel=" + ((Object) this.mLabel) + ", mNewPermissions=" + this.mNewPermissions + ", mAllPermissions=" + this.mAllPermissions + '}';
        }

        MyPermissionGroupInfo(PermissionInfo permissionInfo) {
            this.mNewPermissions = new ArrayList<>();
            this.mAllPermissions = new ArrayList<>();
            this.name = permissionInfo.packageName;
            this.packageName = permissionInfo.packageName;
        }

        MyPermissionGroupInfo(PermissionGroupInfo permissionGroupInfo) {
            super(permissionGroupInfo);
            this.mNewPermissions = new ArrayList<>();
            this.mAllPermissions = new ArrayList<>();
        }

        public Drawable loadGroupIcon(Context context, PackageManager packageManager) {
            if (this.icon != 0) {
                return loadUnbadgedIcon(packageManager);
            }
            return context.getDrawable(android.R.drawable.ic_corp_statusbar_icon);
        }
    }

    public static class MyPermissionInfo extends PermissionInfo {
        int mExistingReqFlags;
        public CharSequence mLabel;
        boolean mNew;
        int mNewReqFlags;

        @Override // android.content.pm.PermissionInfo
        public String toString() {
            return "MyPermissionInfo{mLabel=" + ((Object) this.mLabel) + ", mNewReqFlags=" + this.mNewReqFlags + ", mExistingReqFlags=" + this.mExistingReqFlags + ", mNew=" + this.mNew + '}';
        }

        MyPermissionInfo(PermissionInfo permissionInfo) {
            super(permissionInfo);
        }
    }

    public static class PermissionItemView extends LinearLayout implements View.OnClickListener {
        AlertDialog mDialog;
        MyPermissionGroupInfo mGroup;
        private String mPackageName;
        MyPermissionInfo mPerm;
        private boolean mShowRevokeUI;

        public PermissionItemView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mShowRevokeUI = false;
            setClickable(true);
        }

        public void setPermission(MyPermissionGroupInfo myPermissionGroupInfo, MyPermissionInfo myPermissionInfo, boolean z, CharSequence charSequence, String str, boolean z2) {
            CharSequence charSequence2;
            this.mGroup = myPermissionGroupInfo;
            this.mPerm = myPermissionInfo;
            this.mShowRevokeUI = z2;
            this.mPackageName = str;
            ImageView imageView = (ImageView) findViewById(android.R.id.hdr);
            TextView textView = (TextView) findViewById(android.R.id.header_text);
            Drawable drawableLoadGroupIcon = z ? myPermissionGroupInfo.loadGroupIcon(getContext(), getContext().getPackageManager()) : null;
            CharSequence charSequence3 = myPermissionInfo.mLabel;
            if (!myPermissionInfo.mNew || charSequence == null) {
                charSequence2 = charSequence3;
            } else {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                Parcel parcelObtain = Parcel.obtain();
                TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                parcelObtain.setDataPosition(0);
                CharSequence charSequence4 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcelObtain);
                parcelObtain.recycle();
                spannableStringBuilder.append(charSequence4);
                spannableStringBuilder.append(charSequence3);
                charSequence2 = spannableStringBuilder;
            }
            imageView.setImageDrawable(drawableLoadGroupIcon);
            textView.setText(charSequence2);
            setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CharSequence charSequenceLoadLabel;
            if (this.mGroup == null || this.mPerm == null) {
                return;
            }
            if (this.mDialog != null) {
                this.mDialog.dismiss();
            }
            PackageManager packageManager = getContext().getPackageManager();
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(this.mGroup.mLabel);
            if (this.mPerm.descriptionRes != 0) {
                builder.setMessage(this.mPerm.loadDescription(packageManager));
            } else {
                try {
                    charSequenceLoadLabel = packageManager.getApplicationInfo(this.mPerm.packageName, 0).loadLabel(packageManager);
                } catch (PackageManager.NameNotFoundException unused) {
                    charSequenceLoadLabel = this.mPerm.packageName;
                }
                StringBuilder sb = new StringBuilder(128);
                sb.append(getContext().getString(android.R.string.fileSizeSuffix, charSequenceLoadLabel));
                sb.append("\n\n");
                sb.append(this.mPerm.name);
                builder.setMessage(sb.toString());
            }
            builder.setCancelable(true);
            builder.setIcon(this.mGroup.loadGroupIcon(getContext(), packageManager));
            addRevokeUIIfNecessary(builder);
            this.mDialog = builder.show();
            this.mDialog.setCanceledOnTouchOutside(true);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.mDialog != null) {
                this.mDialog.dismiss();
            }
        }

        private void addRevokeUIIfNecessary(AlertDialog.Builder builder) {
            if (this.mShowRevokeUI) {
                if ((this.mPerm.mExistingReqFlags & 1) != 0) {
                    return;
                }
                builder.setNegativeButton(android.R.string.keyguard_accessibility_add_widget, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.MyAppSecurityPermissions.PermissionItemView.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PermissionItemView.this.getContext().getPackageManager().revokeRuntimePermission(PermissionItemView.this.mPackageName, PermissionItemView.this.mPerm.name, new UserHandle(PermissionItemView.this.mContext.getUserId()));
                        PermissionItemView.this.setVisibility(8);
                    }
                });
                builder.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
            }
        }
    }

    private MyAppSecurityPermissions(Context context) {
        this.mPermGroups = new HashMap();
        this.mPermGroupsList = new ArrayList();
        this.mPermGroupComparator = new PermissionGroupInfoComparator();
        this.mPermComparator = new PermissionInfoComparator();
        this.mPermsList = new ArrayList();
        this.mContext = context;
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        this.mPm = this.mContext.getPackageManager();
        this.mNewPermPrefix = this.mContext.getText(android.R.string.fcError);
    }

    public MyAppSecurityPermissions(Context context, String str) {
        this(context);
        this.mPackageName = str;
        HashSet hashSet = new HashSet();
        try {
            PackageInfo packageInfo = this.mPm.getPackageInfo(str, 4096);
            if (packageInfo.applicationInfo != null && packageInfo.applicationInfo.uid != -1) {
                getAllUsedPermissions(packageInfo.applicationInfo.uid, hashSet);
            }
            this.mPermsList.addAll(hashSet);
            setPermissions(this.mPermsList);
            Log.e(TAG, this.mPermsList.toString());
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w(TAG, "Couldn't retrieve permissions for package:" + str);
        }
    }

    public MyAppSecurityPermissions(Context context, PackageInfo packageInfo) throws PackageManager.NameNotFoundException {
        this(context);
        HashSet hashSet = new HashSet();
        if (packageInfo == null) {
            return;
        }
        this.mPackageName = packageInfo.packageName;
        PackageInfo packageInfo2 = null;
        if (packageInfo.requestedPermissions != null) {
            try {
                packageInfo2 = this.mPm.getPackageInfo(packageInfo.packageName, 4096);
            } catch (PackageManager.NameNotFoundException unused) {
            }
            extractPerms(packageInfo, hashSet, packageInfo2);
        }
        if (packageInfo.sharedUserId != null) {
            try {
                getAllUsedPermissions(this.mPm.getUidForSharedUser(packageInfo.sharedUserId), hashSet);
            } catch (PackageManager.NameNotFoundException unused2) {
                Log.w(TAG, "Couldn't retrieve shared user id for: " + packageInfo.packageName);
            }
        }
        this.mPermsList.addAll(hashSet);
        setPermissions(this.mPermsList);
    }

    public static View getPermissionItemView(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return getPermissionItemViewOld(context, (LayoutInflater) context.getSystemService("layout_inflater"), charSequence, charSequence2, z, context.getDrawable(z ? android.R.drawable.dialog_bottom_holo_dark : android.R.drawable.ic_go_search_api_holo_dark));
    }

    private void getAllUsedPermissions(int i, Set<MyPermissionInfo> set) {
        String[] packagesForUid = this.mPm.getPackagesForUid(i);
        if (packagesForUid == null || packagesForUid.length == 0) {
            return;
        }
        for (String str : packagesForUid) {
            getPermissionsForPackage(str, set);
        }
    }

    private void getPermissionsForPackage(String str, Set<MyPermissionInfo> set) {
        try {
            PackageInfo packageInfo = this.mPm.getPackageInfo(str, 4096);
            extractPerms(packageInfo, set, packageInfo);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w(TAG, "Couldn't retrieve permissions for package: " + str);
        }
    }

    private void extractPerms(PackageInfo packageInfo, Set<MyPermissionInfo> set, PackageInfo packageInfo2) {
        String str;
        MyPermissionGroupInfo myPermissionGroupInfo;
        String[] strArr = packageInfo.requestedPermissions;
        int[] iArr = packageInfo.requestedPermissionsFlags;
        if (strArr == null || strArr.length == 0) {
            return;
        }
        for (int i = 0; i < strArr.length; i++) {
            String str2 = strArr[i];
            try {
                PermissionInfo permissionInfo = this.mPm.getPermissionInfo(str2, 0);
                if (permissionInfo != null) {
                    int i2 = -1;
                    if (packageInfo2 != null && packageInfo2.requestedPermissions != null) {
                        for (int i3 = 0; i3 < packageInfo2.requestedPermissions.length; i3++) {
                            if (str2.equals(packageInfo2.requestedPermissions[i3])) {
                                i2 = i3;
                                break;
                            }
                        }
                    }
                    int i4 = i2 >= 0 ? packageInfo2.requestedPermissionsFlags[i2] : 0;
                    if (isDisplayablePermission(permissionInfo, iArr[i], i4)) {
                        String str3 = permissionInfo.group;
                        if (str3 == null) {
                            str = permissionInfo.packageName;
                            permissionInfo.group = str;
                        } else {
                            str = str3;
                        }
                        if (this.mPermGroups.get(str) == null) {
                            PermissionGroupInfo permissionGroupInfo = str3 != null ? this.mPm.getPermissionGroupInfo(str3, 0) : null;
                            if (permissionGroupInfo != null) {
                                myPermissionGroupInfo = new MyPermissionGroupInfo(permissionGroupInfo);
                            } else {
                                permissionInfo.group = permissionInfo.packageName;
                                if (this.mPermGroups.get(permissionInfo.group) == null) {
                                    new MyPermissionGroupInfo(permissionInfo);
                                }
                                myPermissionGroupInfo = new MyPermissionGroupInfo(permissionInfo);
                            }
                            this.mPermGroups.put(permissionInfo.group, myPermissionGroupInfo);
                        }
                        boolean z = packageInfo2 != null && (i4 & 2) == 0;
                        MyPermissionInfo myPermissionInfo = new MyPermissionInfo(permissionInfo);
                        myPermissionInfo.mNewReqFlags = iArr[i];
                        myPermissionInfo.mExistingReqFlags = i4;
                        myPermissionInfo.mNew = z;
                        set.add(myPermissionInfo);
                    }
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.i(TAG, "Ignoring unknown permission:" + str2);
            }
        }
    }

    public int getPermissionCount() {
        return getPermissionCount(65535);
    }

    private List<MyPermissionInfo> getPermissionList(MyPermissionGroupInfo myPermissionGroupInfo, int i) {
        if (i == 4) {
            return myPermissionGroupInfo.mNewPermissions;
        }
        return myPermissionGroupInfo.mAllPermissions;
    }

    public int getPermissionCount(int i) {
        int size = 0;
        for (int i2 = 0; i2 < this.mPermGroupsList.size(); i2++) {
            size += getPermissionList(this.mPermGroupsList.get(i2), i).size();
        }
        return size;
    }

    public View getPermissionsView() {
        return getPermissionsView(65535, false);
    }

    public View getPermissionsViewWithRevokeButtons() {
        return getPermissionsView(65535, true);
    }

    public View getPermissionsView(int i) {
        return getPermissionsView(i, false);
    }

    private View getPermissionsView(int i, boolean z) {
        LinearLayout linearLayout = (LinearLayout) this.mInflater.inflate(android.R.layout.alert_dialog_title_material, (ViewGroup) null);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(android.R.id.help);
        View viewFindViewById = linearLayout.findViewById(android.R.id.health);
        displayPermissions(this.mPermGroupsList, linearLayout2, i, z);
        if (linearLayout2.getChildCount() <= 0) {
            viewFindViewById.setVisibility(0);
        }
        return linearLayout;
    }

    private void displayPermissions(List<MyPermissionGroupInfo> list, LinearLayout linearLayout, int i, boolean z) {
        linearLayout.removeAllViews();
        int i2 = (int) (this.mContext.getResources().getDisplayMetrics().density * 8.0f);
        for (int i3 = 0; i3 < list.size(); i3++) {
            MyPermissionGroupInfo myPermissionGroupInfo = list.get(i3);
            List<MyPermissionInfo> permissionList = getPermissionList(myPermissionGroupInfo, i);
            int i4 = 0;
            while (i4 < permissionList.size()) {
                View permissionItemView = getPermissionItemView(myPermissionGroupInfo, permissionList.get(i4), i4 == 0, i != 4 ? this.mNewPermPrefix : null, z);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                if (i4 == 0) {
                    layoutParams.topMargin = i2;
                }
                if (i4 == myPermissionGroupInfo.mAllPermissions.size() - 1) {
                    layoutParams.bottomMargin = i2;
                }
                if (linearLayout.getChildCount() == 0) {
                    layoutParams.topMargin *= 2;
                }
                linearLayout.addView(permissionItemView, layoutParams);
                i4++;
            }
        }
    }

    private PermissionItemView getPermissionItemView(MyPermissionGroupInfo myPermissionGroupInfo, MyPermissionInfo myPermissionInfo, boolean z, CharSequence charSequence, boolean z2) {
        return getPermissionItemView(this.mContext, this.mInflater, myPermissionGroupInfo, myPermissionInfo, z, charSequence, this.mPackageName, z2);
    }

    private static PermissionItemView getPermissionItemView(Context context, LayoutInflater layoutInflater, MyPermissionGroupInfo myPermissionGroupInfo, MyPermissionInfo myPermissionInfo, boolean z, CharSequence charSequence, String str, boolean z2) {
        PermissionItemView permissionItemView = (PermissionItemView) layoutInflater.inflate((myPermissionInfo.flags & 1) != 0 ? android.R.layout.alert_dialog_progress_holo : android.R.layout.alert_dialog_progress, (ViewGroup) null);
        permissionItemView.setPermission(myPermissionGroupInfo, myPermissionInfo, z, charSequence, str, z2);
        return permissionItemView;
    }

    private static View getPermissionItemViewOld(Context context, LayoutInflater layoutInflater, CharSequence charSequence, CharSequence charSequence2, boolean z, Drawable drawable) {
        View viewInflate = layoutInflater.inflate(android.R.layout.alert_dialog_progress_material, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(android.R.id.header_text_secondary_divider);
        TextView textView2 = (TextView) viewInflate.findViewById(android.R.id.headers);
        ((ImageView) viewInflate.findViewById(android.R.id.hdr)).setImageDrawable(drawable);
        if (charSequence != null) {
            textView.setText(charSequence);
            textView2.setText(charSequence2);
        } else {
            textView.setText(charSequence2);
            textView2.setVisibility(8);
        }
        return viewInflate;
    }

    private boolean isDisplayablePermission(PermissionInfo permissionInfo, int i, int i2) {
        int i3 = permissionInfo.protectionLevel & 15;
        if (i3 == 0) {
            return false;
        }
        boolean z = i3 == 1 || (permissionInfo.protectionLevel & 128) != 0;
        boolean z2 = (i & 1) != 0;
        boolean z3 = (permissionInfo.protectionLevel & 32) != 0;
        boolean z4 = (i2 & 2) != 0;
        boolean z5 = (i & 2) != 0;
        if (z && (z2 || z4 || z5)) {
            return true;
        }
        return z3 && z4;
    }

    private static class PermissionGroupInfoComparator implements Comparator<MyPermissionGroupInfo> {
        private final Collator sCollator;

        private PermissionGroupInfoComparator() {
            this.sCollator = Collator.getInstance();
        }

        @Override // java.util.Comparator
        public final int compare(MyPermissionGroupInfo myPermissionGroupInfo, MyPermissionGroupInfo myPermissionGroupInfo2) {
            return this.sCollator.compare(myPermissionGroupInfo.mLabel, myPermissionGroupInfo2.mLabel);
        }
    }

    private static class PermissionInfoComparator implements Comparator<MyPermissionInfo> {
        private final Collator sCollator = Collator.getInstance();

        PermissionInfoComparator() {
        }

        @Override // java.util.Comparator
        public final int compare(MyPermissionInfo myPermissionInfo, MyPermissionInfo myPermissionInfo2) {
            return this.sCollator.compare(myPermissionInfo.mLabel, myPermissionInfo2.mLabel);
        }
    }

    private void addPermToList(List<MyPermissionInfo> list, MyPermissionInfo myPermissionInfo) {
        if (myPermissionInfo.mLabel == null) {
            myPermissionInfo.mLabel = myPermissionInfo.loadSafeLabel(this.mPm);
        }
        int iBinarySearch = Collections.binarySearch(list, myPermissionInfo, this.mPermComparator);
        if (iBinarySearch < 0) {
            list.add((-iBinarySearch) - 1, myPermissionInfo);
        }
    }

    private void setPermissions(List<MyPermissionInfo> list) {
        MyPermissionGroupInfo myPermissionGroupInfo;
        if (list != null) {
            for (MyPermissionInfo myPermissionInfo : list) {
                if (isDisplayablePermission(myPermissionInfo, myPermissionInfo.mNewReqFlags, myPermissionInfo.mExistingReqFlags) && (myPermissionGroupInfo = this.mPermGroups.get(myPermissionInfo.group)) != null) {
                    myPermissionInfo.mLabel = myPermissionInfo.loadSafeLabel(this.mPm);
                    addPermToList(myPermissionGroupInfo.mAllPermissions, myPermissionInfo);
                    if (myPermissionInfo.mNew) {
                        addPermToList(myPermissionGroupInfo.mNewPermissions, myPermissionInfo);
                    }
                }
            }
        }
        for (MyPermissionGroupInfo myPermissionGroupInfo2 : this.mPermGroups.values()) {
            if (myPermissionGroupInfo2.labelRes != 0 || myPermissionGroupInfo2.nonLocalizedLabel != null) {
                myPermissionGroupInfo2.mLabel = myPermissionGroupInfo2.loadSafeLabel(this.mPm);
            } else {
                try {
                    myPermissionGroupInfo2.mLabel = this.mPm.getApplicationInfo(myPermissionGroupInfo2.packageName, 0).loadSafeLabel(this.mPm);
                } catch (PackageManager.NameNotFoundException unused) {
                    myPermissionGroupInfo2.mLabel = myPermissionGroupInfo2.loadSafeLabel(this.mPm);
                }
            }
            this.mPermGroupsList.add(myPermissionGroupInfo2);
        }
        Collections.sort(this.mPermGroupsList, this.mPermGroupComparator);
    }
}
