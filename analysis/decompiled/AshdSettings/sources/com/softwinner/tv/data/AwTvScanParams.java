package com.softwinner.tv.data;

/* JADX INFO: loaded from: classes.dex */
public class AwTvScanParams {
    public static final int TYPE_ATV_SCAN_DIR_DOWN = -1;
    public static final int TYPE_ATV_SCAN_DIR_UP = 1;
    private boolean auto;
    private int channelno;
    private String country;
    private int direction;
    private int freq;
    private AwTunerLockInfo lockInfo = new AwTunerLockInfo();
    private int maxfreq;
    private int minfreq;
    private int scanstep;
    private int scantype;
    private int statndard;
    private int type;

    public AwTvScanParams(int i, int i2, boolean z) {
        this.type = i;
        this.freq = i2;
        this.auto = z;
    }

    public AwTvScanParams(int i, int i2, String str, boolean z) {
        this.type = i;
        this.freq = i2;
        this.auto = z;
        this.country = str;
    }

    public void setScanTvType(int i) {
        this.type = i;
    }

    public void setScanType(int i) {
        this.scantype = i;
    }

    public void setStandard(int i) {
        this.statndard = i;
    }

    public void setScanFreq(int i) {
        this.freq = i;
    }

    public void setScanAuto(boolean z) {
        this.auto = z;
    }

    public void setMinFreq(int i) {
        this.minfreq = i;
    }

    public void setMaxFreq(int i) {
        this.maxfreq = i;
    }

    public void setScanCountry(String str) {
        this.country = str;
    }

    public void setChannelNumber(int i) {
        this.channelno = i;
    }

    public void setScanDirection(int i) {
        this.direction = i;
    }

    public void setScanStep(int i) {
        this.scanstep = i;
    }

    public void setLockInfo(AwTunerLockInfo awTunerLockInfo) {
        this.lockInfo.frequency = awTunerLockInfo.frequency;
        this.lockInfo.symbolRate = awTunerLockInfo.symbolRate;
        this.lockInfo.modulation = awTunerLockInfo.modulation;
        this.lockInfo.bandwidth = awTunerLockInfo.bandwidth;
        this.lockInfo.videostd = awTunerLockInfo.videostd;
        this.lockInfo.audiostd = awTunerLockInfo.audiostd;
        this.lockInfo.tunerstd = awTunerLockInfo.tunerstd;
        this.lockInfo.channelno = awTunerLockInfo.channelno;
    }

    public int getScanTvType() {
        return this.type;
    }

    public int getScanType() {
        return this.scantype;
    }

    public int getStandard() {
        return this.statndard;
    }

    public boolean getScanAuto() {
        return this.auto;
    }

    public int getMinFreq() {
        return this.minfreq;
    }

    public int getMaxFreq() {
        return this.maxfreq;
    }

    public String getScanCountry() {
        return this.country;
    }

    public int getScanFreq() {
        return this.freq;
    }

    public int getChannelNumber() {
        return this.channelno;
    }

    public int getScanDirection() {
        return this.direction;
    }

    public int getScanStep() {
        return this.scanstep;
    }

    public AwTunerLockInfo getLockInfo() {
        return this.lockInfo;
    }
}
