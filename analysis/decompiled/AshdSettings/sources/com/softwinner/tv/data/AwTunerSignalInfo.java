package com.softwinner.tv.data;

/* JADX INFO: loaded from: classes.dex */
public class AwTunerSignalInfo {
    private int mQuality;
    private int mStrength;

    public AwTunerSignalInfo() {
        this.mQuality = -1;
        this.mStrength = -1;
    }

    public AwTunerSignalInfo(int i, int i2) {
        this.mQuality = i;
        this.mStrength = i2;
    }

    public int getQuality() {
        return this.mQuality;
    }

    public void setQuality(int i) {
        this.mQuality = i;
    }

    public int getStrength() {
        return this.mStrength;
    }

    public void setStrength(int i) {
        this.mStrength = i;
    }
}
