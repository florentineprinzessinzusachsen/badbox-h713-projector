package com.softwinner.tv.data;

import android.database.Cursor;
import android.text.TextUtils;
import com.softwinner.tv.common.AwTvMultilingualText;
import com.softwinner.tv.common.AwTvUtils;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AwTvChannelInfo {
    public static final String[] COMMON_PROJECTION = {"_id", "input_id", "type", "service_type", "service_id", "display_number", "display_name", "original_network_id", "transport_stream_id", "video_format", "internal_provider_data", "browsable", "locked", "internal_provider_flag2", "internal_provider_flag3", "internal_provider_flag4"};
    private static final boolean DEBUG = false;
    public static final String KEY_AUDIO_COMPENSATION = "audio_compensation";
    public static final String KEY_AUDIO_MANUAL_OUT_MTS = "audio_manual_out_mts";
    public static final String KEY_AUDIO_MTS = "audio_mts";
    public static final String KEY_AUDIO_OUT_MTS = "audio_out_mts";
    public static final String KEY_AUDIO_PIDS = "audio_pids";
    public static final String KEY_AUDIO_STD = "audio_std";
    public static final String KEY_AUDIO_SYS = "audio_sys";
    public static final String KEY_AUDIO_TRACK_INDEX = "audio_track_idx";
    public static final String KEY_BAND_WIDTH = "band_width";
    public static final String KEY_CHANNEL_FAVOURITE = "channel_fav";
    public static final String KEY_CHANNEL_FAVOURITE_GROUP = "channel_fav_group";
    public static final String KEY_CHANNEL_NO = "channel_no";
    public static final String KEY_CHANNEL_SKIP = "channel_skip";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_EIT_PRESENT_FOLLOWING = "eit_present_following";
    public static final String KEY_EIT_SCHEDULE = "eit_schedule";
    public static final String KEY_FINE_TUNE = "fine_tune";
    public static final String KEY_FREE_CA = "free_ca";
    public static final String KEY_FREQUENCY = "frequency";
    public static final String KEY_IS_AUTO_STD = "is_auto_std";
    public static final String KEY_MODULATION = "modulation";
    public static final String KEY_MULTI_NAME = "multi_name";
    public static final String KEY_ORIGFREQUENCY = "org_frequency";
    public static final String KEY_PCR_PID = "pcr_pid";
    public static final String KEY_PROGRAM_NUMBER = "program_number";
    public static final String KEY_SCRAMBLED = "scrambled";
    public static final String KEY_SUBT_TRACK_INDEX = "subtitle_track_idx";
    public static final String KEY_SYMBOL_RATE = "symbol_rate";
    public static final String KEY_VFMT = "vfmt";
    public static final String KEY_VIDEO_PID = "video_pid";
    public static final String KEY_VIDEO_STD = "video_std";
    private static final String TAG = "AwTvChannelInfo";
    private int mAudioCompensation;
    private int mAudioManualOutMts;
    private int mAudioMts;
    private int mAudioOutMts;
    private int mAudioOutPutMode;
    private int[] mAudioPids;
    private int mAudioStd;
    private int mAudioSys;
    private int mAudioTrackIndex;
    private int mBandwidth;
    private boolean mBrowsable;
    private int mChannelNo;
    private String mCountry;
    private String mDisplayName;
    private String mDisplayNameMulti;
    private String mDisplayNumber;
    private int mEitPresentFollowing;
    private int mEitSchedule;
    private int mFavgroup;
    private int mFavourite;
    private int mFineTune;
    private int mFreeCa;
    private int mFrequency;
    private long mId;
    private String mInputId;
    private int mIsAutoStd;
    private boolean mLocked;
    private String mLogoUrl;
    private int mModulation;
    private int mNumber;
    private int mOrgFrequency;
    private int mOriginalNetworkId;
    private int mPcrPid;
    private int mProgramNumber;
    private int mScrambled;
    private int mServiceId;
    private String mServiceType;
    private int mSkip;
    private int mSubtitleTrackIndex;
    private int mSymbolRate;
    private int mTransportStreamId;
    private String mType;
    private int mVfmt;
    private String mVideoFormat;
    private int mVideoPid;
    private int mVideoStd;

    public static boolean isSameChannel(AwTvChannelInfo awTvChannelInfo, AwTvChannelInfo awTvChannelInfo2) {
        return false;
    }

    private AwTvChannelInfo() {
        this.mAudioTrackIndex = -1;
        this.mSubtitleTrackIndex = -1;
    }

    public static AwTvChannelInfo fromCommonCursor(Cursor cursor) {
        Builder builder = new Builder();
        int columnIndex = cursor.getColumnIndex("_id");
        if (columnIndex >= 0) {
            builder.setId(cursor.getLong(columnIndex));
        }
        int columnIndex2 = cursor.getColumnIndex("input_id");
        if (columnIndex2 >= 0) {
            builder.setInputId(cursor.getString(columnIndex2));
        }
        int columnIndex3 = cursor.getColumnIndex("type");
        if (columnIndex3 >= 0) {
            builder.setType(cursor.getString(columnIndex3));
        }
        int columnIndex4 = cursor.getColumnIndex("service_type");
        if (columnIndex4 >= 0) {
            builder.setServiceType(cursor.getString(columnIndex4));
        }
        int columnIndex5 = cursor.getColumnIndex("service_id");
        if (columnIndex5 >= 0) {
            builder.setServiceId(cursor.getInt(columnIndex5));
        }
        int columnIndex6 = cursor.getColumnIndex("display_number");
        if (columnIndex6 >= 0) {
            builder.setDisplayNumber(cursor.getString(columnIndex6));
        }
        int columnIndex7 = cursor.getColumnIndex("display_name");
        if (columnIndex7 >= 0) {
            builder.setDisplayName(cursor.getString(columnIndex7));
        }
        int columnIndex8 = cursor.getColumnIndex("original_network_id");
        if (columnIndex8 >= 0) {
            builder.setOriginalNetworkId(cursor.getInt(columnIndex8));
        }
        int columnIndex9 = cursor.getColumnIndex("transport_stream_id");
        if (columnIndex9 >= 0) {
            builder.setTransportStreamId(cursor.getInt(columnIndex9));
        }
        int columnIndex10 = cursor.getColumnIndex("internal_provider_data");
        if (columnIndex10 >= 0) {
            Map<String, String> mapJsonToMap = AwTvUtils.jsonToMap(cursor.getString(columnIndex10));
            if (mapJsonToMap != null && mapJsonToMap.size() > 0 && mapJsonToMap.get(KEY_AUDIO_PIDS) != null) {
                String[] strArrSplit = mapJsonToMap.get(KEY_AUDIO_PIDS).replace("[", "").replace("]", "").split(",");
                int length = strArrSplit[0].compareTo("null") == 0 ? 0 : strArrSplit.length;
                if (length > 0) {
                    int[] iArr = new int[length];
                    for (int i = 0; i < strArrSplit.length; i++) {
                        iArr[i] = Integer.parseInt(strArrSplit[i]);
                    }
                    builder.setAudioPids(iArr);
                }
            } else {
                builder.setAudioPids(null);
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_FREQUENCY) != null) {
                builder.setFrequency(Integer.parseInt(mapJsonToMap.get(KEY_FREQUENCY)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_ORIGFREQUENCY) != null) {
                builder.setOrgFrequency(Integer.parseInt(mapJsonToMap.get(KEY_ORIGFREQUENCY)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_BAND_WIDTH) != null) {
                builder.setBandwidth(Integer.parseInt(mapJsonToMap.get(KEY_BAND_WIDTH)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_SYMBOL_RATE) != null) {
                builder.setSymbolRate(Integer.parseInt(mapJsonToMap.get(KEY_SYMBOL_RATE)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_MODULATION) != null) {
                builder.setModulation(Integer.parseInt(mapJsonToMap.get(KEY_MODULATION)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_PROGRAM_NUMBER) != null) {
                builder.setProgramNumber(Integer.parseInt(mapJsonToMap.get(KEY_PROGRAM_NUMBER)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_EIT_SCHEDULE) != null) {
                builder.setEitSchedule(Integer.parseInt(mapJsonToMap.get(KEY_EIT_SCHEDULE)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_EIT_PRESENT_FOLLOWING) != null) {
                builder.setEitPresentFollowing(Integer.parseInt(mapJsonToMap.get(KEY_EIT_PRESENT_FOLLOWING)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_VIDEO_PID) != null) {
                builder.setVideoPid(Integer.parseInt(mapJsonToMap.get(KEY_VIDEO_PID)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_PCR_PID) != null) {
                builder.setPcrPid(Integer.parseInt(mapJsonToMap.get(KEY_PCR_PID)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_VFMT) != null) {
                builder.setVfmt(Integer.parseInt(mapJsonToMap.get(KEY_VFMT)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_AUDIO_TRACK_INDEX) != null) {
                builder.setAudioTrackIndex(Integer.parseInt(mapJsonToMap.get(KEY_AUDIO_TRACK_INDEX)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_VIDEO_STD) != null) {
                builder.setVideoStd(Integer.parseInt(mapJsonToMap.get(KEY_VIDEO_STD)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_AUDIO_STD) != null) {
                builder.setAudioStd(Integer.parseInt(mapJsonToMap.get(KEY_AUDIO_STD)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_IS_AUTO_STD) != null) {
                builder.setIsAutoStd(Integer.parseInt(mapJsonToMap.get(KEY_IS_AUTO_STD)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_FINE_TUNE) != null) {
                builder.setFineTune(Integer.parseInt(mapJsonToMap.get(KEY_FINE_TUNE)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_AUDIO_COMPENSATION) != null) {
                builder.setAudioCompensation(Integer.parseInt(mapJsonToMap.get(KEY_AUDIO_COMPENSATION)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_SUBT_TRACK_INDEX) != null) {
                builder.setSubtitleTrackIndex(Integer.parseInt(mapJsonToMap.get(KEY_SUBT_TRACK_INDEX)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_MULTI_NAME) != null) {
                builder.setDisplayNameMulti(AwTvUtils.TvString.fromString(mapJsonToMap.get(KEY_MULTI_NAME)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_FREE_CA) != null) {
                builder.setFreeCa(Integer.parseInt(mapJsonToMap.get(KEY_FREE_CA)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_SCRAMBLED) != null) {
                builder.setScrambled(Integer.parseInt(mapJsonToMap.get(KEY_SCRAMBLED)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_CHANNEL_NO) != null) {
                builder.setChannelNo(Integer.parseInt(mapJsonToMap.get(KEY_CHANNEL_NO)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_CHANNEL_FAVOURITE) != null) {
                builder.setFavourtite(Integer.parseInt(mapJsonToMap.get(KEY_CHANNEL_FAVOURITE)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_CHANNEL_FAVOURITE_GROUP) != null) {
                builder.setFavGroup(Integer.parseInt(mapJsonToMap.get(KEY_CHANNEL_FAVOURITE_GROUP)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_CHANNEL_SKIP) != null) {
                builder.setSkip(Integer.parseInt(mapJsonToMap.get(KEY_CHANNEL_SKIP)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_AUDIO_SYS) != null) {
                builder.setAudioSys(Integer.parseInt(mapJsonToMap.get(KEY_AUDIO_SYS)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_AUDIO_MTS) != null) {
                builder.setAudioMts(Integer.parseInt(mapJsonToMap.get(KEY_AUDIO_MTS)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_AUDIO_OUT_MTS) != null) {
                builder.setAudioOutMts(Integer.parseInt(mapJsonToMap.get(KEY_AUDIO_OUT_MTS)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_AUDIO_MANUAL_OUT_MTS) != null) {
                builder.setAudioManualOutMts(Integer.parseInt(mapJsonToMap.get(KEY_AUDIO_MANUAL_OUT_MTS)));
            }
            if (mapJsonToMap != null && mapJsonToMap.get(KEY_COUNTRY) != null) {
                builder.setCountry(AwTvUtils.TvString.fromString(mapJsonToMap.get(KEY_COUNTRY)));
            }
        }
        int columnIndex11 = cursor.getColumnIndex("browsable");
        if (columnIndex11 >= 0) {
            builder.setBrowsable(cursor.getInt(columnIndex11) == 1);
        }
        int columnIndex12 = cursor.getColumnIndex("locked");
        if (columnIndex12 >= 0) {
            builder.setLocked(cursor.getInt(columnIndex12) == 1);
        }
        return builder.build();
    }

    public static AwTvChannelInfo fromScanProgramEvent(AwProgramEvent awProgramEvent, String str, boolean z) {
        String text;
        if (awProgramEvent == null) {
            return null;
        }
        if (z) {
            return new Builder().setInputId(str).setType("AnalogTv").setServiceType("SERVICE_TYPE_AUDIO_VIDEO").setDisplayName("ATV Program").setFrequency(awProgramEvent.freq).setOrgFrequency(awProgramEvent.org_freq).setVideoStd(awProgramEvent.videoStd).setVfmt(0).setAudioStd(awProgramEvent.audioStd).setIsAutoStd(0).setAudioCompensation(0).setFineTune(0).setChannelNo(awProgramEvent.channelNo).setAudioSys(awProgramEvent.audioSys).setCountry(awProgramEvent.country).build();
        }
        try {
            text = AwTvMultilingualText.getText(awProgramEvent.programName);
        } catch (Exception e) {
            e.printStackTrace();
            text = "????";
        }
        return new Builder().setInputId(str).setType("TYPE_DTMB").setServiceType("SERVICE_TYPE_AUDIO_VIDEO").setDisplayName(text).setPcrPid(awProgramEvent.pcr).setVideoPid(awProgramEvent.video_pid).setAudioPids(awProgramEvent.audio_pids).setFrequency(awProgramEvent.freq).setServiceId(awProgramEvent.serviceID).setDisplayNameMulti(awProgramEvent.programName).setFreeCa(awProgramEvent.free_ca).setScrambled(awProgramEvent.scrambled).setEitSchedule(awProgramEvent.eit_schedule).setEitPresentFollowing(awProgramEvent.eit_present_following).setSymbolRate(awProgramEvent.symbolRate).setModulation(awProgramEvent.modulation).setBandwidth(awProgramEvent.bandwidth).setLocked(false).build();
    }

    public long getId() {
        return this.mId;
    }

    public String getInputId() {
        return this.mInputId;
    }

    public String getType() {
        return this.mType;
    }

    public String getServiceType() {
        return this.mServiceType;
    }

    public int getServiceId() {
        return this.mServiceId;
    }

    public String getDisplayNumber() {
        return this.mDisplayNumber;
    }

    public int getNumber() {
        return this.mNumber;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getLogoUrl() {
        return this.mLogoUrl;
    }

    public int getOriginalNetworkId() {
        return this.mOriginalNetworkId;
    }

    public int getTransportStreamId() {
        return this.mTransportStreamId;
    }

    public int getProgramNumber() {
        return this.mProgramNumber;
    }

    public int getEitSchedule() {
        return this.mEitSchedule;
    }

    public int getEitPresentFollowing() {
        return this.mEitPresentFollowing;
    }

    public int getVideoPid() {
        return this.mVideoPid;
    }

    public int[] getAudioPids() {
        return this.mAudioPids;
    }

    public int getAudioTrackIndex() {
        return this.mAudioTrackIndex;
    }

    public int getPcrPid() {
        return this.mPcrPid;
    }

    public int getFrequency() {
        return this.mFrequency;
    }

    public int getOrgFrequency() {
        return this.mOrgFrequency;
    }

    public int getBandwidth() {
        return this.mBandwidth;
    }

    public int getSymbolRate() {
        return this.mSymbolRate;
    }

    public int getModulation() {
        return this.mModulation;
    }

    public int getSubtitleTrackIndex() {
        return this.mSubtitleTrackIndex;
    }

    public String getDisplayNameMulti() {
        return this.mDisplayNameMulti;
    }

    public String getDisplayNameLocal() {
        return AwTvMultilingualText.getText(getDisplayNameMulti());
    }

    public String getDisplayName(String str) {
        return AwTvMultilingualText.getText(getDisplayNameMulti(), str);
    }

    public int getFreeCa() {
        return this.mFreeCa;
    }

    public int getScrambled() {
        return this.mScrambled;
    }

    public int getVideoStd() {
        return this.mVideoStd;
    }

    public int getVfmt() {
        return this.mVfmt;
    }

    public int getChannelNo() {
        return this.mChannelNo;
    }

    public int getAudioStd() {
        return this.mAudioStd;
    }

    public int getFineTune() {
        return this.mFineTune;
    }

    public int getAudioOutPutMode() {
        return this.mAudioOutPutMode;
    }

    public int getAudioCompensation() {
        return this.mAudioCompensation;
    }

    public int getIsAutoStd() {
        return this.mIsAutoStd;
    }

    public int getFavourite() {
        return this.mFavourite;
    }

    public int getFavgroup() {
        return this.mFavgroup;
    }

    public int getSkip() {
        return this.mSkip;
    }

    public int getAudioSys() {
        return this.mAudioSys;
    }

    public int getAudioMts() {
        return this.mAudioMts;
    }

    public int getAudioOutMts() {
        return this.mAudioOutMts;
    }

    public int getAudioManualOutMts() {
        return this.mAudioManualOutMts;
    }

    public String getCountry() {
        return this.mCountry;
    }

    public boolean isBrowsable() {
        return this.mBrowsable;
    }

    public boolean isLocked() {
        return this.mLocked;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public void setFrequency(int i) {
        this.mFrequency = i;
    }

    public void setOrgFrequency(int i) {
        this.mOrgFrequency = i;
    }

    public void setVideoStd(int i) {
        this.mVideoStd = i;
    }

    public void setFineTune(int i) {
        this.mFineTune = i;
    }

    public void setVfmt(int i) {
        this.mVfmt = i;
    }

    public void setAudioStd(int i) {
        this.mAudioStd = i;
    }

    public void setDisplayNumber(String str) {
        this.mDisplayNumber = str;
        this.mNumber = stringToInteger(str);
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public void setDisplayNameLocal(String str) {
        if (this.mDisplayNameMulti != null) {
            this.mDisplayNameMulti = this.mDisplayNameMulti.replace(getDisplayNameLocal(), str);
        }
    }

    public void setDisplayNameMulti(String str) {
        this.mDisplayNameMulti = str;
    }

    public void setProgramNumber(int i) {
        this.mProgramNumber = i;
    }

    public void setEitSchedule(int i) {
        this.mEitSchedule = i;
    }

    public void setEitPresentFollowing(int i) {
        this.mEitPresentFollowing = i;
    }

    public void setVideoPid(int i) {
        this.mVideoPid = i;
    }

    public void setAudioPids(int[] iArr) {
        this.mAudioPids = iArr;
    }

    public void setAudioTrackIndex(int i) {
        this.mAudioTrackIndex = i;
    }

    public void setPcrPid(int i) {
        this.mPcrPid = i;
    }

    public void setBrowsable(boolean z) {
        this.mBrowsable = z;
    }

    public void setLocked(boolean z) {
        this.mLocked = z;
    }

    public void setSubtitleTrackIndex(int i) {
        this.mSubtitleTrackIndex = i;
    }

    public void setFreeCa(int i) {
        this.mFreeCa = i;
    }

    public void setScrambled(int i) {
        this.mScrambled = i;
    }

    public void setFavourite(int i) {
        this.mFavourite = i;
    }

    public void setFavouriteGroup(int i) {
        this.mFavgroup = i;
    }

    public void setSkip(int i) {
        this.mSkip = i;
    }

    public void setAudioSys(int i) {
        this.mAudioSys = i;
    }

    public void setAudioMts(int i) {
        this.mAudioMts = i;
    }

    public void setAudioOutMts(int i) {
        this.mAudioOutMts = i;
    }

    public void setAudioManualOutMts(int i) {
        this.mAudioManualOutMts = i;
    }

    public void setCountry(String str) {
        this.mCountry = str;
    }

    public boolean isAnalogChannel() {
        return this.mType.equals("AnalogTv");
    }

    public static final class Builder {
        private final AwTvChannelInfo mChannel = new AwTvChannelInfo();

        public Builder() {
            this.mChannel.mId = -1L;
            this.mChannel.mInputId = "";
            this.mChannel.mType = "";
            this.mChannel.mServiceType = "";
            this.mChannel.mServiceId = -1;
            this.mChannel.mDisplayNumber = "-1";
            this.mChannel.mNumber = -1;
            this.mChannel.mDisplayName = "";
            this.mChannel.mVideoFormat = "";
            this.mChannel.mLogoUrl = "";
            this.mChannel.mOriginalNetworkId = -1;
            this.mChannel.mTransportStreamId = -1;
            this.mChannel.mVideoPid = -1;
            this.mChannel.mAudioPids = null;
            this.mChannel.mPcrPid = -1;
            this.mChannel.mFrequency = -1;
            this.mChannel.mBandwidth = -1;
            this.mChannel.mSymbolRate = -1;
            this.mChannel.mModulation = -1;
            this.mChannel.mVideoStd = -1;
            this.mChannel.mVfmt = -1;
            this.mChannel.mAudioStd = -1;
            this.mChannel.mIsAutoStd = -1;
            this.mChannel.mAudioTrackIndex = -1;
            this.mChannel.mAudioCompensation = -1;
            this.mChannel.mFineTune = 0;
            this.mChannel.mChannelNo = 0;
            this.mChannel.mBrowsable = false;
            this.mChannel.mLocked = false;
            this.mChannel.mSubtitleTrackIndex = -1;
            this.mChannel.mFreeCa = 0;
            this.mChannel.mScrambled = 0;
            this.mChannel.mFavourite = 0;
            this.mChannel.mFavgroup = -1;
            this.mChannel.mSkip = 0;
            this.mChannel.mAudioSys = 0;
            this.mChannel.mAudioMts = 0;
            this.mChannel.mAudioOutMts = 0;
            this.mChannel.mAudioManualOutMts = -1;
            this.mChannel.mCountry = "";
        }

        public Builder setId(long j) {
            this.mChannel.mId = j;
            return this;
        }

        public Builder setInputId(String str) {
            this.mChannel.mInputId = str;
            return this;
        }

        public Builder setType(String str) {
            this.mChannel.mType = str;
            return this;
        }

        public Builder setServiceType(String str) {
            this.mChannel.mServiceType = str;
            return this;
        }

        public Builder setServiceId(int i) {
            this.mChannel.mServiceId = i;
            return this;
        }

        public Builder setDisplayNumber(String str) {
            this.mChannel.mDisplayNumber = str;
            this.mChannel.mNumber = AwTvChannelInfo.stringToInteger(str);
            return this;
        }

        public Builder setDisplayName(String str) {
            this.mChannel.mDisplayName = str;
            return this;
        }

        public Builder setLogoUrl(String str) {
            this.mChannel.mLogoUrl = str;
            return this;
        }

        public Builder setOriginalNetworkId(int i) {
            this.mChannel.mOriginalNetworkId = i;
            return this;
        }

        public Builder setTransportStreamId(int i) {
            this.mChannel.mTransportStreamId = i;
            return this;
        }

        public Builder setProgramNumber(int i) {
            this.mChannel.mProgramNumber = i;
            return this;
        }

        public Builder setEitSchedule(int i) {
            this.mChannel.mEitSchedule = i;
            return this;
        }

        public Builder setEitPresentFollowing(int i) {
            this.mChannel.mEitPresentFollowing = i;
            return this;
        }

        public Builder setVideoPid(int i) {
            this.mChannel.mVideoPid = i;
            return this;
        }

        public Builder setAudioPids(int[] iArr) {
            this.mChannel.mAudioPids = iArr;
            return this;
        }

        public Builder setChannelNo(int i) {
            this.mChannel.mChannelNo = i;
            return this;
        }

        public Builder setVfmt(int i) {
            this.mChannel.mVfmt = i;
            return this;
        }

        public Builder setVideoStd(int i) {
            this.mChannel.mVideoStd = i;
            return this;
        }

        public Builder setAudioStd(int i) {
            this.mChannel.mAudioStd = i;
            return this;
        }

        public Builder setIsAutoStd(int i) {
            this.mChannel.mIsAutoStd = i;
            return this;
        }

        public Builder setAudioTrackIndex(int i) {
            this.mChannel.mAudioTrackIndex = i;
            return this;
        }

        public Builder setAudioCompensation(int i) {
            this.mChannel.mAudioCompensation = i;
            return this;
        }

        public Builder setPcrPid(int i) {
            this.mChannel.mPcrPid = i;
            return this;
        }

        public Builder setFrequency(int i) {
            this.mChannel.mFrequency = i;
            return this;
        }

        public Builder setOrgFrequency(int i) {
            this.mChannel.mOrgFrequency = i;
            return this;
        }

        public Builder setBandwidth(int i) {
            this.mChannel.mBandwidth = i;
            return this;
        }

        public Builder setSymbolRate(int i) {
            this.mChannel.mSymbolRate = i;
            return this;
        }

        public Builder setModulation(int i) {
            this.mChannel.mModulation = i;
            return this;
        }

        public Builder setFineTune(int i) {
            this.mChannel.mFineTune = i;
            return this;
        }

        public Builder setBrowsable(boolean z) {
            this.mChannel.mBrowsable = z;
            return this;
        }

        public Builder setLocked(boolean z) {
            this.mChannel.mLocked = z;
            return this;
        }

        public Builder setSubtitleTrackIndex(int i) {
            this.mChannel.mSubtitleTrackIndex = i;
            return this;
        }

        public Builder setDisplayNameMulti(String str) {
            this.mChannel.mDisplayNameMulti = str;
            return this;
        }

        public Builder setFreeCa(int i) {
            this.mChannel.mFreeCa = i;
            return this;
        }

        public Builder setScrambled(int i) {
            this.mChannel.mScrambled = i;
            return this;
        }

        public Builder setFavourtite(int i) {
            this.mChannel.mFavourite = i;
            return this;
        }

        public Builder setFavGroup(int i) {
            this.mChannel.mFavgroup = i;
            return this;
        }

        public Builder setSkip(int i) {
            this.mChannel.mSkip = i;
            return this;
        }

        public Builder setAudioSys(int i) {
            this.mChannel.mAudioSys = i;
            return this;
        }

        public Builder setAudioMts(int i) {
            this.mChannel.mAudioMts = i;
            return this;
        }

        public Builder setAudioOutMts(int i) {
            this.mChannel.mAudioOutMts = i;
            return this;
        }

        public Builder setAudioManualOutMts(int i) {
            this.mChannel.mAudioManualOutMts = i;
            return this;
        }

        public Builder setCountry(String str) {
            this.mChannel.mCountry = str;
            return this;
        }

        public AwTvChannelInfo build() {
            return this.mChannel;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int stringToInteger(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        String strReplaceAll = str.replaceAll("\\D", "");
        if (TextUtils.isEmpty(strReplaceAll)) {
            return -1;
        }
        return Integer.valueOf(strReplaceAll).intValue();
    }

    public boolean isSameChannel(AwTvChannelInfo awTvChannelInfo) {
        return awTvChannelInfo != null && awTvChannelInfo.getFrequency() == this.mFrequency && awTvChannelInfo.getServiceId() == this.mServiceId;
    }

    public String toString() {
        return "Id = " + this.mId + "\n InputId = " + this.mInputId + "\n Type = " + this.mType + "\n ServiceType = " + this.mServiceType + "\n ServiceId = " + this.mServiceId + "\n DisplayNumber = " + this.mDisplayNumber + "\n DisplayName = " + this.mDisplayName + "\n LogoUrl = " + this.mLogoUrl + "\n OriginalNetworkId = " + this.mOriginalNetworkId + "\n TransportStreamId = " + this.mTransportStreamId + "\n ProgramNumber = " + this.mProgramNumber + "\n EITSchedule = " + this.mEitSchedule + "\n EITPresentFollowing = " + this.mEitPresentFollowing + "\n VideoPid = " + this.mVideoPid + "\n AudioPids = " + Arrays.toString(this.mAudioPids) + "\n PcrPid = " + this.mPcrPid + "\n mFrequency = " + this.mFrequency + "\n mOrgFrequency = " + this.mOrgFrequency + "\n mFinetune = " + this.mFineTune + "\n Browsable = " + this.mBrowsable + "\n mLocked = " + this.mLocked + "\n FreeCa = " + this.mFreeCa + "\n Scrambled = " + this.mScrambled;
    }
}
