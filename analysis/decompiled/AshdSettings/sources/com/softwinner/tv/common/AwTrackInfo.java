package com.softwinner.tv.common;

import android.media.MediaPlayer;

/* JADX INFO: loaded from: classes.dex */
public class AwTrackInfo {
    public int mTrackId;
    public MediaPlayer.TrackInfo mTrackInfo;

    public AwTrackInfo(MediaPlayer.TrackInfo trackInfo, int i) {
        this.mTrackId = i;
        this.mTrackInfo = trackInfo;
    }
}
