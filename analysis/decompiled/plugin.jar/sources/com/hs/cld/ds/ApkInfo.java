package com.hs.cld.ds;

import android.os.Parcel;
import android.os.Parcelable;
import com.hs.p.common.utils.JSONUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class ApkInfo implements Parcelable {
    public static final Parcelable.Creator<ApkInfo> CREATOR = new Parcelable.Creator<ApkInfo>() { // from class: com.hs.cld.ds.ApkInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApkInfo createFromParcel(Parcel parcel) {
            ApkInfo apkInfo = new ApkInfo();
            try {
                apkInfo.mPackageName = parcel.readString();
                apkInfo.mLocalPath = parcel.readString();
                apkInfo.mApkUrl = parcel.readString();
                apkInfo.mApkMd5 = parcel.readString();
                apkInfo.mCmdArgs = parcel.readString();
                apkInfo.mDplnk = parcel.readString();
                apkInfo.mActAction = parcel.readString();
                apkInfo.mActClazzName = parcel.readString();
                apkInfo.mSrvAction = parcel.readString();
                apkInfo.mSrvClazzName = parcel.readString();
                apkInfo.mBcAction = parcel.readString();
                apkInfo.mStartDownTrackers = parcel.createStringArray();
                apkInfo.mDownTrackers = parcel.createStringArray();
                apkInfo.mStartInstallTrackers = parcel.createStringArray();
                apkInfo.mInstallTrackers = parcel.createStringArray();
                apkInfo.mActiveTrackers = parcel.createStringArray();
            } catch (Exception unused) {
            }
            return apkInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApkInfo[] newArray(int i) {
            return new ApkInfo[i];
        }
    };
    public String mPackageName = null;
    public String mApkUrl = null;
    public String mLocalPath = null;
    public String mApkMd5 = null;
    public boolean mInstallOnSystemIdle = false;
    public String mCmdArgs = null;
    public String mDplnk = null;
    public String mActAction = null;
    public String mActClazzName = null;
    public String mSrvAction = null;
    public String mSrvClazzName = null;
    public String mBcAction = null;
    public String[] mStartDownTrackers = null;
    public String[] mDownTrackers = null;
    public String[] mStartInstallTrackers = null;
    public String[] mInstallTrackers = null;
    public String[] mActiveTrackers = null;

    public static ApkInfo fromJSONString(String str) {
        try {
            ApkInfo apkInfo = new ApkInfo();
            JSONObject jSONObject = new JSONObject(str);
            apkInfo.mPackageName = JSONUtils.getString(jSONObject, "pkg", "");
            apkInfo.mLocalPath = JSONUtils.getString(jSONObject, "path", "");
            apkInfo.mApkUrl = JSONUtils.getString(jSONObject, "url", "");
            apkInfo.mApkMd5 = JSONUtils.getString(jSONObject, "md5", "");
            apkInfo.mCmdArgs = JSONUtils.getString(jSONObject, "args", "");
            apkInfo.mDplnk = JSONUtils.getString(jSONObject, "dplnk", "");
            apkInfo.mActAction = JSONUtils.getString(jSONObject, "act_act", "");
            apkInfo.mActClazzName = JSONUtils.getString(jSONObject, "act_cn", "");
            apkInfo.mSrvAction = JSONUtils.getString(jSONObject, "srv_act", "");
            apkInfo.mSrvClazzName = JSONUtils.getString(jSONObject, "srv_cn", "");
            apkInfo.mBcAction = JSONUtils.getString(jSONObject, "bc_act", "");
            apkInfo.mStartDownTrackers = JSONUtils.getStringArray(jSONObject, "std_trackers");
            apkInfo.mDownTrackers = JSONUtils.getStringArray(jSONObject, "d_trackers");
            apkInfo.mStartInstallTrackers = JSONUtils.getStringArray(jSONObject, "sti_trackers");
            apkInfo.mInstallTrackers = JSONUtils.getStringArray(jSONObject, "i_trackers");
            apkInfo.mActiveTrackers = JSONUtils.getStringArray(jSONObject, "act_trackers");
            return apkInfo;
        } catch (Exception unused) {
            return null;
        }
    }

    private String nonNull(String str) {
        return str == null ? "" : str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toJSONString() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONUtils.putString(jSONObject, "pkg", this.mPackageName);
            JSONUtils.putString(jSONObject, "path", this.mLocalPath);
            JSONUtils.putString(jSONObject, "url", this.mApkUrl);
            JSONUtils.putString(jSONObject, "md5", this.mApkMd5);
            JSONUtils.putString(jSONObject, "args", this.mCmdArgs);
            JSONUtils.putString(jSONObject, "dplnk", this.mDplnk);
            JSONUtils.putString(jSONObject, "act_act", this.mActAction);
            JSONUtils.putString(jSONObject, "act_cn", this.mActClazzName);
            JSONUtils.putString(jSONObject, "srv_act", this.mSrvAction);
            JSONUtils.putString(jSONObject, "srv_cn", this.mSrvClazzName);
            JSONUtils.putString(jSONObject, "bc_act", this.mBcAction);
            JSONUtils.putArray(jSONObject, "std_trackers", this.mStartDownTrackers);
            JSONUtils.putArray(jSONObject, "d_trackers", this.mDownTrackers);
            JSONUtils.putArray(jSONObject, "sti_trackers", this.mStartInstallTrackers);
            JSONUtils.putArray(jSONObject, "i_trackers", this.mInstallTrackers);
            JSONUtils.putArray(jSONObject, "act_trackers", this.mActiveTrackers);
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nonNull(this.mPackageName));
        parcel.writeString(nonNull(this.mLocalPath));
        parcel.writeString(nonNull(this.mApkUrl));
        parcel.writeString(nonNull(this.mApkMd5));
        parcel.writeString(nonNull(this.mCmdArgs));
        parcel.writeString(nonNull(this.mDplnk));
        parcel.writeString(nonNull(this.mActAction));
        parcel.writeString(nonNull(this.mActClazzName));
        parcel.writeString(nonNull(this.mSrvAction));
        parcel.writeString(nonNull(this.mSrvClazzName));
        parcel.writeString(nonNull(this.mBcAction));
        parcel.writeStringArray(nonNull(this.mStartDownTrackers));
        parcel.writeStringArray(nonNull(this.mDownTrackers));
        parcel.writeStringArray(nonNull(this.mStartInstallTrackers));
        parcel.writeStringArray(nonNull(this.mInstallTrackers));
        parcel.writeStringArray(nonNull(this.mActiveTrackers));
    }

    private String[] nonNull(String[] strArr) {
        return strArr == null ? new String[0] : strArr;
    }
}
