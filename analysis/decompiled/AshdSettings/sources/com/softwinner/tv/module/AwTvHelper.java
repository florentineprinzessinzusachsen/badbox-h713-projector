package com.softwinner.tv.module;

import android.content.Context;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.media.tv.TvContract;
import android.media.tv.TvInputInfo;
import android.media.tv.TvInputManager;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.softwinner.tv.AwTvAudioManager;
import com.softwinner.tv.AwTvChannelManager;
import com.softwinner.tv.common.AwTvAudioTypes;
import com.softwinner.tv.data.AwChannelListFilter;
import com.softwinner.tv.data.AwChannelSortMode;
import com.softwinner.tv.data.AwTvChannelInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AwTvHelper {
    private static final boolean ISDEBUG = false;
    private static final int MATCH_CHANNEL_CHANGED = 2;
    private static final int MATCH_CHANNEL_DELETE = 1;
    private static final int MATCH_CURRENT_CHANNEL_UPDATE = 3;
    private static AwTvHelper sInstance;
    private Handler mChannelHandler;
    private Context mContext;
    private AwContentObserver mObserver;
    private AwTvAudioManager mTvAudioManager;
    private AwTvDataBaseHelper mTvChannelDataHelper;
    private AwTvChannelManager mTvChannelManager;
    private TvInputManager mTvInputManager;
    private static final String TAG = "AwTvHelper";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final UriMatcher sUriMatcher = new UriMatcher(-1);
    private HashMap<Integer, String> mTvInputMaps = new HashMap<>();
    private LinkedList<ChannelObserver> mObservers = new LinkedList<>();

    public static class ChannelObserver {
        public static final int CHANNEL_DELETE = 2;
        public static final int CHANNEL_LIST_DELETE = 3;
        public static final int CHANNEL_UNKNOWN_CHANGED = -1;
        public static final int CHANNEL_UPDATE = 1;

        public void onChanged(int i, Uri uri) {
        }
    }

    static {
        sUriMatcher.addURI("android.media.tv", "channel", 1);
        sUriMatcher.addURI("android.media.tv", "channel/#", 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void debug(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    public static synchronized AwTvHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AwTvHelper(context);
        }
        return sInstance;
    }

    private AwTvHelper(Context context) {
        this.mChannelHandler = null;
        this.mObserver = null;
        debug("AwTvHelper has created!");
        this.mContext = context;
        this.mTvChannelDataHelper = new AwTvDataBaseHelper(context);
        HandlerThread handlerThread = new HandlerThread("AwTvChannelDataObserver");
        handlerThread.start();
        this.mChannelHandler = new Handler(handlerThread.getLooper());
        this.mTvInputManager = (TvInputManager) this.mContext.getApplicationContext().getSystemService("tv_input");
        if (this.mTvInputManager == null) {
            Log.e(TAG, "fail to get tv input manager");
        }
        getTvInputList();
        this.mObserver = new AwContentObserver(this.mChannelHandler);
        this.mTvChannelDataHelper.registerChannelObserver(this.mObserver);
    }

    private void getTvInputList() {
        if (this.mTvInputManager != null) {
            Iterator<TvInputInfo> it = this.mTvInputManager.getTvInputList().iterator();
            while (it.hasNext()) {
                String id = it.next().getId();
                debug("input id = " + id);
                if (id.toLowerCase().contains("atv")) {
                    debug("add atv input");
                    this.mTvInputMaps.put(2, id);
                } else if (id.toLowerCase().contains("dtv")) {
                    debug("add dtv input");
                    this.mTvInputMaps.put(1, id);
                }
            }
        }
    }

    private String getTvInputWithType(int i) {
        if (!this.mTvInputMaps.containsKey(2) || !this.mTvInputMaps.containsKey(1)) {
            Log.e(TAG, "TvInputMap is empty");
            getTvInputList();
        }
        if (this.mTvInputMaps.containsKey(Integer.valueOf(i))) {
            return this.mTvInputMaps.get(Integer.valueOf(i));
        }
        Log.e(TAG, "Fail to find tvtype" + i);
        return null;
    }

    public String getTvInputId(int i) {
        return getTvInputWithType(i);
    }

    public synchronized AwTvChannelInfo getCurrentChannelInfo(int i) {
        String tvInputWithType = getTvInputWithType(i);
        if (tvInputWithType != null) {
            long currentChannelId = this.mTvChannelDataHelper.getCurrentChannelId(tvInputWithType);
            if (currentChannelId != -1) {
                return getChannelInfoById(currentChannelId);
            }
        }
        return null;
    }

    public synchronized void setCurrentChannelTuneId(int i, int i2) {
        debug("setCurrentChannelTuneId : " + i);
        Uri uriBuildChannelUri = TvContract.buildChannelUri((long) i);
        String tvInputWithType = getTvInputWithType(i2);
        if (tvInputWithType != null) {
            this.mTvChannelDataHelper.updateLastUri(tvInputWithType, uriBuildChannelUri);
        }
    }

    public synchronized long getCurrentChannelTuneId(int i) {
        long j;
        String tvInputWithType = getTvInputWithType(i);
        j = -1;
        if (tvInputWithType != null) {
            long currentChannelId = this.mTvChannelDataHelper.getCurrentChannelId(tvInputWithType);
            if (currentChannelId == -1) {
                Log.e(TAG, "do not find any channel record.");
            }
            j = currentChannelId;
        }
        return j;
    }

    public int registerChannelObserver(ChannelObserver channelObserver) {
        if (this.mObserver == null) {
            this.mObserver = new AwContentObserver(this.mChannelHandler);
            this.mTvChannelDataHelper.registerChannelObserver(this.mObserver);
        }
        if (!this.mObservers.contains(channelObserver)) {
            this.mObservers.add(channelObserver);
        }
        return this.mObservers.indexOf(channelObserver);
    }

    public void unregisterChannelObserver(ChannelObserver channelObserver) {
        if (this.mObservers.contains(channelObserver)) {
            this.mObservers.remove(channelObserver);
        }
        if (this.mObservers.size() == 0) {
            this.mTvChannelDataHelper.unregisterChannelObserver(this.mObserver);
            this.mObserver = null;
        }
    }

    public boolean setChannelName(int i, String str) throws Throwable {
        AwTvChannelInfo channelInfoById = getChannelInfoById(i);
        if (channelInfoById == null || str == null) {
            return false;
        }
        debug("setChannelName : oldname = " + channelInfoById.getDisplayName() + " newname = " + str);
        channelInfoById.setDisplayName(str);
        this.mTvChannelDataHelper.updateChannelInfo(channelInfoById);
        return true;
    }

    public AwTvChannelInfo getChannelInfoById(long j) {
        return this.mTvChannelDataHelper.getChannelInfo(TvContract.buildChannelUri(j));
    }

    public int addTvChannelToProvider(AwTvChannelInfo awTvChannelInfo) throws Throwable {
        if (awTvChannelInfo.isAnalogChannel()) {
            this.mTvChannelDataHelper.insertAtvChannelWithOrder(awTvChannelInfo);
            return 0;
        }
        this.mTvChannelDataHelper.insertDtvChannel(awTvChannelInfo);
        return 0;
    }

    public int addTvChannelToProviderWithDisplayNum(AwTvChannelInfo awTvChannelInfo, int i) {
        if (awTvChannelInfo.isAnalogChannel()) {
            this.mTvChannelDataHelper.insertAtvChannel(awTvChannelInfo, String.valueOf(i));
            return 0;
        }
        this.mTvChannelDataHelper.insertDtvChannel(awTvChannelInfo, String.valueOf(i));
        return 0;
    }

    public int updateTvChannelToProvider(AwTvChannelInfo awTvChannelInfo) {
        this.mTvChannelDataHelper.updateChannelInfo(awTvChannelInfo);
        return 0;
    }

    public int updateOrInsertChannelToProvider(AwTvChannelInfo awTvChannelInfo, int i) throws Throwable {
        if (awTvChannelInfo.isAnalogChannel()) {
            this.mTvChannelDataHelper.updateOrInsertAtvChannelWithNumber(awTvChannelInfo);
            return 0;
        }
        this.mTvChannelDataHelper.updateOrInsertDtvChannelWithNumber(awTvChannelInfo, String.valueOf(i));
        return 0;
    }

    public int deleteTvChannelsToProviderByType(int i, String str) {
        int iDeleteChannels;
        if (i != 3) {
            iDeleteChannels = this.mTvChannelDataHelper.deleteChannels(getTvInputWithType(i), str);
        } else {
            if (this.mTvInputMaps.isEmpty()) {
                getTvInputList();
            }
            Iterator<Integer> it = this.mTvInputMaps.keySet().iterator();
            int iDeleteChannels2 = 0;
            while (it.hasNext()) {
                iDeleteChannels2 = this.mTvChannelDataHelper.deleteChannels(this.mTvInputMaps.get(it.next()), str);
            }
            iDeleteChannels = iDeleteChannels2;
        }
        debug("delete source  = " + i + " ----- counts " + iDeleteChannels);
        return iDeleteChannels;
    }

    public int deleteTvChannelToProvider(AwTvChannelInfo awTvChannelInfo) {
        return this.mTvChannelDataHelper.deleteChannel(awTvChannelInfo);
    }

    public boolean updateTvChannelColorStandard(int i, int i2) {
        AwTvChannelInfo channelInfoById;
        long j = i;
        if (j == -1 || (channelInfoById = getChannelInfoById(j)) == null) {
            return false;
        }
        channelInfoById.setVideoStd(i2);
        updateTvChannelToProvider(channelInfoById);
        AwTvChannelManager awTvChannelManager = this.mTvChannelManager;
        AwTvChannelManager.getInstance(this.mContext).setColorStd(i2);
        AwTvChannelManager awTvChannelManager2 = this.mTvChannelManager;
        AwTvChannelManager.getInstance(this.mContext).startAtvPlay(channelInfoById);
        return true;
    }

    public int getTvChannelColorStandard(int i) {
        AwTvChannelInfo channelInfoById;
        long j = i;
        if (j == -1 || (channelInfoById = getChannelInfoById(j)) == null) {
            return 0;
        }
        return channelInfoById.getVideoStd();
    }

    public boolean updateTvChannelAudioStandard(int i, int i2) {
        AwTvChannelInfo channelInfoById;
        int iValue;
        long j = i;
        if (j == -1 || (channelInfoById = getChannelInfoById(j)) == null) {
            return false;
        }
        channelInfoById.setAudioStd(i2);
        switch (i2) {
            case 1:
                AwTvAudioTypes.EnumATVStandardMode enumATVStandardMode = AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_M;
                iValue = AwTvAudioTypes.EnumATVAudioStd.E_AW_ATV_M_BTSC.value();
                break;
            case 2:
                AwTvAudioTypes.EnumATVStandardMode enumATVStandardMode2 = AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_BG;
                iValue = AwTvAudioTypes.EnumATVAudioStd.E_AW_ATV_BG_A2_FM.value();
                break;
            case 3:
                AwTvAudioTypes.EnumATVStandardMode enumATVStandardMode3 = AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_I;
                iValue = AwTvAudioTypes.EnumATVAudioStd.E_AW_ATV_I_NICAM_FM.value();
                break;
            case 4:
                AwTvAudioTypes.EnumATVStandardMode enumATVStandardMode4 = AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_DK;
                iValue = AwTvAudioTypes.EnumATVAudioStd.E_AW_ATV_DK_NICAM_FM.value();
                break;
            case 5:
                AwTvAudioTypes.EnumATVStandardMode enumATVStandardMode5 = AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_L;
                iValue = AwTvAudioTypes.EnumATVAudioStd.E_AW_ATV_L_NICAM_AM.value();
                break;
            default:
                AwTvAudioTypes.EnumATVStandardMode enumATVStandardMode6 = AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_AUTO;
                iValue = AwTvAudioTypes.EnumATVAudioStd.E_AW_ATV_NO_STD.value();
                break;
        }
        AwTvAudioManager.getInstance(this.mContext).setATVAudioSys(iValue);
        AwTvChannelManager awTvChannelManager = this.mTvChannelManager;
        AwTvChannelManager.getInstance(this.mContext).startAtvPlay(channelInfoById);
        channelInfoById.setAudioSys(iValue);
        updateTvChannelToProvider(channelInfoById);
        return true;
    }

    public int getTvChannelAudioStandard(int i) {
        AwTvChannelInfo channelInfoById;
        long j = i;
        if (j == -1 || (channelInfoById = getChannelInfoById(j)) == null) {
            return 0;
        }
        return channelInfoById.getAudioStd();
    }

    public List<AwTvChannelInfo> getChannelListByFilter(AwChannelListFilter awChannelListFilter, int i, HashMap<String, Integer> map) {
        int i2 = 2;
        switch (awChannelListFilter) {
            case ENUM_CHANNEL_LIST_FILTER_ALL:
                i2 = 3;
                break;
            case ENUM_CHANNEL_LIST_FILTER_FAV:
                i2 = i;
                break;
        }
        if (i2 != 3) {
            return this.mTvChannelDataHelper.getChannelListWithQueryMap(getTvInputWithType(i2), null, null, null, map);
        }
        return getChannelListAll(map);
    }

    public int getChannelCountByFilter(AwChannelListFilter awChannelListFilter, int i, HashMap<String, Integer> map) {
        return getChannelListByFilter(awChannelListFilter, i, map).size();
    }

    public boolean setChannelFavourite(int i, int i2) {
        AwTvChannelInfo channelInfoById = getChannelInfoById(i);
        if (channelInfoById == null) {
            return false;
        }
        channelInfoById.setFavourite(1);
        channelInfoById.setFavouriteGroup(i2);
        updateTvChannelToProvider(channelInfoById);
        debug("set channel to favourite: id = " + i);
        return true;
    }

    public boolean resetChannelFavourite(int i) {
        AwTvChannelInfo channelInfoById = getChannelInfoById(i);
        if (channelInfoById == null) {
            return false;
        }
        channelInfoById.setFavourite(0);
        channelInfoById.setFavouriteGroup(-1);
        updateTvChannelToProvider(channelInfoById);
        debug("remove channel from favourite: id = " + i);
        return true;
    }

    public boolean setChannelSkip(int i, boolean z) {
        AwTvChannelInfo channelInfoById = getChannelInfoById(i);
        if (channelInfoById == null) {
            return false;
        }
        channelInfoById.setSkip(z ? 1 : 0);
        updateTvChannelToProvider(channelInfoById);
        debug("set chanel skip: " + z);
        return true;
    }

    public boolean getChannelSkip(int i) {
        AwTvChannelInfo channelInfoById = getChannelInfoById(i);
        return channelInfoById != null && channelInfoById.getSkip() == 1;
    }

    public boolean setChannelLock(int i, boolean z) {
        AwTvChannelInfo channelInfoById = getChannelInfoById(i);
        if (channelInfoById == null) {
            return false;
        }
        channelInfoById.setLocked(z);
        updateTvChannelToProvider(channelInfoById);
        return true;
    }

    public boolean getChannelLock(int i) {
        AwTvChannelInfo channelInfoById = getChannelInfoById(i);
        if (channelInfoById != null) {
            return channelInfoById.isLocked();
        }
        return false;
    }

    public void swapChannel(int i, int i2) {
        AwTvChannelInfo channelInfoById = getChannelInfoById(i);
        AwTvChannelInfo channelInfoById2 = getChannelInfoById(i2);
        if (channelInfoById == null || channelInfoById2 == null) {
            return;
        }
        this.mTvChannelDataHelper.swapChannel(channelInfoById, channelInfoById2);
    }

    public void moveChannel(int i, int i2) throws Throwable {
        AwTvChannelInfo channelInfoById = getChannelInfoById(i);
        AwTvChannelInfo channelInfoById2 = getChannelInfoById(i2);
        if (channelInfoById == null || channelInfoById2 == null) {
            return;
        }
        this.mTvChannelDataHelper.moveChannel(channelInfoById, channelInfoById2);
    }

    public ChannelSortComparator getChannelSortComparator(AwChannelSortMode awChannelSortMode) {
        return new ChannelSortComparator(awChannelSortMode);
    }

    public static class ChannelSortComparator implements Comparator<AwTvChannelInfo> {
        private AwChannelSortMode mSortType;

        public ChannelSortComparator(AwChannelSortMode awChannelSortMode) {
            this.mSortType = awChannelSortMode;
        }

        @Override // java.util.Comparator
        public int compare(AwTvChannelInfo awTvChannelInfo, AwTvChannelInfo awTvChannelInfo2) {
            if (awTvChannelInfo == awTvChannelInfo2) {
                return 0;
            }
            if (this.mSortType == AwChannelSortMode.Aw_Sort_By_Freq) {
                return awTvChannelInfo.getFrequency() - awTvChannelInfo2.getFrequency();
            }
            if (this.mSortType != AwChannelSortMode.Aw_Sort_By_DisplayNum) {
                return 0;
            }
            if (awTvChannelInfo.getDisplayNumber() == null || awTvChannelInfo2.getDisplayNumber() == null) {
                return -1;
            }
            return awTvChannelInfo.getNumber() - awTvChannelInfo2.getNumber();
        }
    }

    private List<AwTvChannelInfo> getChannelListAll(HashMap<String, Integer> map) {
        ArrayList arrayList = new ArrayList();
        if (this.mTvInputMaps.isEmpty()) {
            getTvInputList();
        }
        Iterator<Integer> it = this.mTvInputMaps.keySet().iterator();
        while (it.hasNext()) {
            arrayList.addAll(this.mTvChannelDataHelper.getChannelListWithQueryMap(this.mTvInputMaps.get(it.next()), null, null, null, map));
        }
        return arrayList;
    }

    private class AwContentObserver extends ContentObserver {
        AwContentObserver(Handler handler) {
            super(handler);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:18:0x006f  */
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            int i;
            AwTvHelper.this.debug("channel database has changed : " + uri.toString() + "  -- selfchange = " + z);
            switch (AwTvHelper.sUriMatcher.match(uri)) {
                case 1:
                    String queryParameter = uri.getQueryParameter("input");
                    if (queryParameter == null) {
                        i = -1;
                    } else {
                        if (queryParameter.toLowerCase().contains("atv")) {
                            AwTvHelper.this.debug("ATV channels has deleted");
                        } else if (queryParameter.toLowerCase().contains("dtv")) {
                            AwTvHelper.this.debug("DTV channels has deleted");
                        }
                        i = 3;
                    }
                    break;
                case 2:
                    i = AwTvHelper.this.mTvChannelDataHelper.getChannelInfo(uri) != null ? 1 : 2;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i > 0) {
                Iterator it = AwTvHelper.this.mObservers.iterator();
                while (it.hasNext()) {
                    ((ChannelObserver) it.next()).onChanged(i, uri);
                }
            }
        }
    }
}
