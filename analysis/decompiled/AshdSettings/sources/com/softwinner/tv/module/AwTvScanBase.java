package com.softwinner.tv.module;

import android.content.Context;
import android.util.Log;
import com.softwinner.tv.data.AwChannelListFilter;
import com.softwinner.tv.data.AwProgramEvent;
import com.softwinner.tv.data.AwTvChannelInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AwTvScanBase {
    private static final String TAG = "AwScanBase";
    protected Context mContext;
    protected boolean mIsAutoScan = true;

    public enum ScanMode {
        SCAN_MODE_FULL,
        SCAN_MODE_UPDATE,
        SCAN_MODE_RANGE,
        SCAN_MODE_QUICK,
        SCAN_MODE_ADD_ON,
        SCAN_MODE_SINGLE_RF_CHANNEL,
        SCAN_MODE_RANGE_RF_CHANNEL,
        SCAN_MODE_MANUAL_RF_CHANNEL,
        SCAN_MODE_MANUAL_FREQ,
        SCAN_MODE_BGM,
        SCAN_MODE_DVBC_NIT_SEARCH_MODE_OFF,
        SCAN_MODE_DVBC_NIT_SEARCH_MODE_QUICK,
        SCAN_MODE_DVBC_NIT_SEARCH_MODE_EX_QUICK,
        SCAN_MODE_NUM
    }

    public enum ScanType {
        SCAN_TYPE_PAL,
        SCAN_TYPE_DVBT,
        SCAN_TYPE_DVBC,
        SCAN_TYPE_NTSC,
        SCAN_TYPE_ATSC,
        SCAN_TYPE_ISDB,
        SCAN_TYPE_CQAM,
        SCAN_TYPE_DVBT2,
        SCAN_TYPE_DVBS,
        SCAN_TYPE_DTMB,
        SCAN_TYPE_US,
        SCAN_TYPE_SA,
        SCAN_TYPE_ATV_AUTO,
        SCAN_TYPE_DTV_ATUO,
        SCAN_TYPE_NUM
    }

    public int cancelScan() {
        return 0;
    }

    public boolean isScanning() {
        return false;
    }

    public void onDtvProgramReceived(AwProgramEvent awProgramEvent) {
    }

    public int setNtscScanParams() {
        return 0;
    }

    public int setPalSecamScanParams() {
        return 0;
    }

    public AwTvScanBase(Context context) {
        this.mContext = context;
    }

    public int startScan(boolean z, int i, int i2, int i3) {
        Log.d(TAG, "[Default ScanBase] : startScan  auto=" + z + " startfreq =" + i3);
        this.mIsAutoScan = z;
        if (!z) {
            return 0;
        }
        AwTvHelper.getInstance(this.mContext).deleteTvChannelsToProviderByType(2, null);
        return 0;
    }

    public void onAtvProgramReceived(AwProgramEvent awProgramEvent) {
        Log.d(TAG, "[Default ScanBase] onAtvProgramReceived");
        AwTvChannelInfo awTvChannelInfoFromScanProgramEvent = AwTvChannelInfo.fromScanProgramEvent(awProgramEvent, AwTvHelper.getInstance(this.mContext).getTvInputId(2), true);
        if (this.mIsAutoScan) {
            handleAtvAutoScanEvent(awTvChannelInfoFromScanProgramEvent);
        } else {
            handleAtvManualScanEvent(awTvChannelInfoFromScanProgramEvent);
        }
    }

    private void handleAtvAutoScanEvent(AwTvChannelInfo awTvChannelInfo) throws Throwable {
        int size = AwTvHelper.getInstance(this.mContext).getChannelListByFilter(AwChannelListFilter.ENUM_CHANNEL_LIST_FILTER_ATV_ALL, 2, null).size() + 1;
        Log.d(TAG, "[AwTvScanHandler ScanBase] handleAtvAutoScanEvent: displaynum = " + size);
        awTvChannelInfo.setDisplayNumber(Integer.toString(size));
        AwTvHelper.getInstance(this.mContext).addTvChannelToProvider(awTvChannelInfo);
    }

    private int getATVManualScanDisplaynum(AwTvChannelInfo awTvChannelInfo) {
        List<AwTvChannelInfo> channelListByFilter = AwTvHelper.getInstance(this.mContext).getChannelListByFilter(AwChannelListFilter.ENUM_CHANNEL_LIST_FILTER_ATV_ALL, 2, null);
        int size = channelListByFilter.size();
        int orgFrequency = awTvChannelInfo.getOrgFrequency();
        int i = size + 1;
        Log.d(TAG, "channels size: " + size);
        Log.d(TAG, "freq: " + orgFrequency);
        if (size < 1) {
            return 1;
        }
        for (AwTvChannelInfo awTvChannelInfo2 : channelListByFilter) {
            if (awTvChannelInfo2.getOrgFrequency() == orgFrequency) {
                i = Integer.parseInt(awTvChannelInfo2.getDisplayNumber());
            }
        }
        Log.d(TAG, "tempDisplaynum: " + i);
        return i;
    }

    private void handleAtvManualScanEvent(AwTvChannelInfo awTvChannelInfo) throws Throwable {
        Log.d(TAG, "[Default ScanBase] handleAtvManualScanEvent");
        AwTvChannelInfo currentChannelInfo = AwTvHelper.getInstance(this.mContext).getCurrentChannelInfo(2);
        if (currentChannelInfo != null) {
            Log.d(TAG, "displaynum of current channel is " + Integer.parseInt(currentChannelInfo.getDisplayNumber()));
        }
        int aTVManualScanDisplaynum = getATVManualScanDisplaynum(awTvChannelInfo);
        awTvChannelInfo.setDisplayNumber(Integer.toString(aTVManualScanDisplaynum));
        AwTvHelper.getInstance(this.mContext).updateOrInsertChannelToProvider(awTvChannelInfo, aTVManualScanDisplaynum);
    }
}
