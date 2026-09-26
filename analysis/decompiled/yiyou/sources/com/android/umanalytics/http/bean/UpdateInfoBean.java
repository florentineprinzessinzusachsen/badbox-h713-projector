package com.android.umanalytics.http.bean;

/* JADX INFO: loaded from: classes.dex */
public class UpdateInfoBean {
    private String MD5;
    private String action;
    private String apkName;
    private int date;
    private String description;
    private String deviceCode;
    private String downLoadURL;
    private boolean force;
    private String packageName;
    private String title;
    private int versionCode;
    private String versionName;

    public String getAction() {
        return this.action;
    }

    public String getApkName() {
        return this.apkName;
    }

    public int getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDeviceCode() {
        return this.deviceCode;
    }

    public String getDownLoadURL() {
        return this.downLoadURL;
    }

    public String getMD5() {
        return this.MD5;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getTitle() {
        return this.title;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public boolean isForce() {
        return this.force;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setApkName(String str) {
        this.apkName = str;
    }

    public void setDate(int i) {
        this.date = i;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDeviceCode(String str) {
        this.deviceCode = str;
    }

    public void setDownLoadURL(String str) {
        this.downLoadURL = str;
    }

    public void setForce(boolean z) {
        this.force = z;
    }

    public void setMD5(String str) {
        this.MD5 = str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public String toString() {
        return "UpdateInfoBean{date=" + this.date + ", action='" + this.action + "', versionCode=" + this.versionCode + ", versionName='" + this.versionName + "', packageName='" + this.packageName + "', apkName='" + this.apkName + "', downLoadURL='" + this.downLoadURL + "', deviceCode='" + this.deviceCode + "', title='" + this.title + "', description='" + this.description + "', force=" + this.force + ", MD5='" + this.MD5 + "'}";
    }
}
