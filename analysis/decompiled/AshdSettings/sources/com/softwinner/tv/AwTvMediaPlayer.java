package com.softwinner.tv;

import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.media.TimedText;
import android.os.RemoteException;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.SurfaceHolder;
import com.softwinner.tv.client.AwTvServerManagerProxy;
import com.softwinner.tv.common.AwTrackInfo;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer;

/* JADX INFO: loaded from: classes.dex */
public class AwTvMediaPlayer extends MediaPlayer {
    private static String TAG = "AwTvMediaPlayer";
    private boolean enableSubtitle;
    private List<AwTrackInfo> mAwSubtitleTrackInfos;
    private List<AwTrackInfo> mAwTrackInfos;
    private ITvServer mService;
    private SurfaceHolder mSurfaceHolder;
    private int mSubtitleSize = 40;
    private int mSubtitleColor = -1;
    private int mSubtitleStrokeWidth = 5;
    private List<String> extSubtitleList = new ArrayList();
    private SubtitleCallback mSubtitleCallback = new SubtitleCallback();

    private class SubtitleCallback implements MediaPlayer.OnTimedTextListener, MediaPlayer.OnPreparedListener {
        public SubtitleCallback() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            AwTvMediaPlayer.this.mAwTrackInfos = AwTvMediaPlayer.this.getAllTrackInfo();
            AwTvMediaPlayer.this.mAwSubtitleTrackInfos = AwTvMediaPlayer.this.getAllSubtitleTrackInfo();
            if (AwTvMediaPlayer.this.mAwSubtitleTrackInfos.size() > 0) {
                AwTvMediaPlayer.this.setSubtitleTrack((AwTrackInfo) AwTvMediaPlayer.this.mAwSubtitleTrackInfos.get(0));
            }
        }

        @Override // android.media.MediaPlayer.OnTimedTextListener
        public void onTimedText(MediaPlayer mediaPlayer, TimedText timedText) {
            String str;
            if (AwTvMediaPlayer.this.enableSubtitle) {
                mediaPlayer.getVideoHeight();
                mediaPlayer.getVideoWidth();
                Canvas canvasLockCanvas = AwTvMediaPlayer.this.mSurfaceHolder.lockCanvas();
                int width = canvasLockCanvas.getWidth();
                int height = canvasLockCanvas.getHeight();
                TextPaint textPaint = new TextPaint();
                textPaint.setColor(AwTvMediaPlayer.this.mSubtitleColor);
                textPaint.setTextSize(AwTvMediaPlayer.this.mSubtitleSize);
                textPaint.setAntiAlias(true);
                textPaint.setStrokeWidth(AwTvMediaPlayer.this.mSubtitleStrokeWidth);
                canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                if (timedText.getText() != null) {
                    String subtitleEncodingType = AwTvMediaPlayer.this.getSubtitleEncodingType();
                    String text = timedText.getText();
                    try {
                        str = new String(text.getBytes(subtitleEncodingType), subtitleEncodingType);
                    } catch (UnsupportedEncodingException unused) {
                        Log.w(AwTvMediaPlayer.TAG, "unsupported encoding. encodingType: " + subtitleEncodingType);
                        str = text;
                    }
                    StaticLayout staticLayout = new StaticLayout(str, textPaint, width, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true);
                    canvasLockCanvas.save();
                    canvasLockCanvas.translate(0, (height - staticLayout.getHeight()) - AwTvMediaPlayer.this.mSubtitleSize);
                    staticLayout.draw(canvasLockCanvas);
                    canvasLockCanvas.restore();
                }
                AwTvMediaPlayer.this.mSurfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
            }
        }
    }

    public AwTvMediaPlayer() {
        Log.d(TAG, "AwTvMediaPlayer Subtitle create");
        this.mService = AwTvServerManagerProxy.getInstance().getAwTvServerManager();
    }

    public void setSubtitleDisplay(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
        Log.d(TAG, "setSubtitleDisplay sfholder = " + this.mSurfaceHolder);
        setOnPreparedListener(this.mSubtitleCallback);
        setOnTimedTextListener(this.mSubtitleCallback);
    }

    public void onSubtitleTrack() {
        this.enableSubtitle = true;
    }

    public void offSubtitleTrack() {
        this.enableSubtitle = false;
    }

    public void setSubtitleSize(int i) {
        this.mSubtitleSize = i;
    }

    public void setSubtitleColor(int i) {
        this.mSubtitleColor = i;
    }

    public List<AwTrackInfo> getAllTrackInfo() {
        ArrayList arrayList = new ArrayList();
        MediaPlayer.TrackInfo[] trackInfo = getTrackInfo();
        if (trackInfo != null && trackInfo.length > 0) {
            for (int i = 0; i < trackInfo.length; i++) {
                arrayList.add(new AwTrackInfo(trackInfo[i], i));
            }
        }
        return arrayList;
    }

    public List<AwTrackInfo> getAllSubtitleTrackInfo() {
        ArrayList arrayList = new ArrayList();
        MediaPlayer.TrackInfo[] trackInfo = getTrackInfo();
        if (trackInfo != null && trackInfo.length > 0) {
            for (int i = 0; i < trackInfo.length; i++) {
                MediaPlayer.TrackInfo trackInfo2 = trackInfo[i];
                if (trackInfo2.getTrackType() != 1 && trackInfo2.getTrackType() != 2 && trackInfo2.getTrackType() == 3) {
                    arrayList.add(new AwTrackInfo(trackInfo[i], i));
                }
            }
        }
        return arrayList;
    }

    public void setSubtitleDataSource(String str, String str2) {
        Iterator<String> it = this.extSubtitleList.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next())) {
                Log.e(TAG, str + " This subtile always been load");
                return;
            }
        }
        this.extSubtitleList.add(str);
        try {
            addTimedTextSource(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSubtitleTrack(AwTrackInfo awTrackInfo) {
        selectTrack(awTrackInfo.mTrackId);
    }

    public int setSubtitleEncodingType(EnumEncodingStandard enumEncodingStandard, EnumRegionLanguage enumRegionLanguage) {
        try {
            return this.mService.setSubtitleEncodingType(enumEncodingStandard.getValue(), enumRegionLanguage.getValue());
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return -1;
        }
    }

    public String getSubtitleEncodingType() {
        try {
            return this.mService.getSubtitleEncodingType();
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public enum EnumEncodingStandard {
        E_AW_ENCODING_STANDARD_ISO(0),
        E_AW_ENCODING_STANDARD_WINDOWS(1);

        private final int value;

        EnumEncodingStandard(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumEncodingStandard valueOf(int i) {
            EnumEncodingStandard[] enumEncodingStandardArrValues = values();
            for (int i2 = 0; i2 < enumEncodingStandardArrValues.length; i2++) {
                if (enumEncodingStandardArrValues[i2].value == i) {
                    return enumEncodingStandardArrValues[i2];
                }
            }
            return E_AW_ENCODING_STANDARD_ISO;
        }
    }

    public enum EnumRegionLanguage {
        E_AW_REGION_LANGUAGE_WEST_EUROPE(0),
        E_AW_REGION_LANGUAGE_CENTER_EUROPE(1),
        E_AW_REGION_LANGUAGE_BALTIC(2),
        E_AW_REGION_LANGUAGE_CYRILLIC(3),
        E_AW_REGION_LANGUAGE_ARABIC(4),
        E_AW_REGION_LANGUAGE_GREEK(5),
        E_AW_REGION_LANGUAGE_HEBREW(6),
        E_AW_REGION_LANGUAGE_TURKEY(7),
        E_AW_REGION_LANGUAGE_THAL(8),
        E_AW_REGION_LANGUAGE_VIETNAMESE(9),
        E_AW_REGION_LANGUAGE_CHINESE_SIMPLIFIED(10),
        E_AW_REGION_LANGUAGE_DEFAULT(255);

        private final int value;

        EnumRegionLanguage(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumRegionLanguage valueOf(int i) {
            EnumRegionLanguage[] enumRegionLanguageArrValues = values();
            for (int i2 = 0; i2 < enumRegionLanguageArrValues.length; i2++) {
                if (enumRegionLanguageArrValues[i2].value == i) {
                    return enumRegionLanguageArrValues[i2];
                }
            }
            return E_AW_REGION_LANGUAGE_DEFAULT;
        }
    }
}
