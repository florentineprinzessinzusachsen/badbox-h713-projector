package com.softwinner.tv.common;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvSignalInfo {
    public int mColorFormat;
    public int mColorSpace;
    public int mDviMode;
    public int mFrameRate;
    public boolean mFullRange;
    public int mHSize;
    public int mHdrMode;
    public boolean mInterlace;
    public int mSignalId;
    public int mVSize;

    public AwTvSignalInfo(int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7, boolean z2, int i8) {
        this.mSignalId = i;
        this.mFrameRate = i2;
        this.mInterlace = z;
        this.mColorFormat = i3;
        this.mColorSpace = i4;
        this.mHSize = i5;
        this.mVSize = i6;
        this.mHdrMode = i7;
        this.mFullRange = z2;
        this.mDviMode = i8;
    }
}
