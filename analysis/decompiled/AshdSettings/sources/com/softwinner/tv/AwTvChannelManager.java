package com.softwinner.tv;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.rk_itvui.settings.picture.ConfigManager;
import com.softwinner.tv.client.AwTvBrocastManagerProxy;
import com.softwinner.tv.data.AwEPGEvent;
import com.softwinner.tv.data.AwProgramEvent;
import com.softwinner.tv.data.AwTunerLockInfo;
import com.softwinner.tv.data.AwTunerSignalInfo;
import com.softwinner.tv.data.AwTvChannelInfo;
import com.softwinner.tv.data.AwTvPlayEvent;
import com.softwinner.tv.module.AwTvCallbackHandler;
import com.softwinner.tv.module.AwTvConfigBase;
import com.softwinner.tv.module.AwTvScanBase;
import java.util.ArrayList;
import java.util.LinkedList;
import vendor.aw.hardware.btvserver.V1_0.AnalogChannelInfo;
import vendor.aw.hardware.btvserver.V1_0.CallbackParcel;
import vendor.aw.hardware.btvserver.V1_0.DtvChannelInfo;
import vendor.aw.hardware.btvserver.V1_0.DtvLockInfo;
import vendor.aw.hardware.btvserver.V1_0.EPGSearchParam;
import vendor.aw.hardware.btvserver.V1_0.IBtvServerCallback;
import vendor.aw.hardware.btvserver.V1_0.SignalInfo;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvChannelManager implements Handler.Callback {
    private static final String ATV_AUDIO_PATH_PARAMS_PREFIX = "tuner_input=";
    public static final int ATV_START_SCAN = 1;
    public static final int DEVICE_OFFLINE = -1;
    public static final int DEVICE_ONLINE = 0;
    public static final int DEVICE_UNKNOW = 255;
    private static final boolean ISDEBUG = false;
    public static final int TV_TYPE_ATV = 2;
    public static final int TV_TYPE_DTV = 1;
    private static AwTvChannelManager sAwTvChannelManager;
    private AudioManager mAudioManager;
    private AwTvBrocastManagerProxy mBrocastManagerProxy;
    private BtvEventCallback mBtvEvtCallback;
    private Context mContext;
    private AwTvScanBase mDefaultTvScanBase;
    private Handler mHandler;
    private AwTvSystemManager mTvSystemManager;
    private static final String TAG = "AwTvChannelManager";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private AwTvScanBase mTvScanBase = null;
    private LinkedList<AwTvCallbackHandler> mTvEventListeners = new LinkedList<>();
    private int mCurrentFreq = -1;

    private void debug(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    private AwTvChannelManager(Context context) {
        this.mBtvEvtCallback = null;
        this.mBrocastManagerProxy = null;
        debug("AwTvChannelManager created");
        HandlerThread handlerThread = new HandlerThread("AwTvChannelEventThread");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), this);
        this.mDefaultTvScanBase = new AwTvScanBase(context);
        this.mBtvEvtCallback = new BtvEventCallback();
        this.mBrocastManagerProxy = AwTvBrocastManagerProxy.getInstance();
        this.mBrocastManagerProxy.setBrocastEventCallback(this.mBtvEvtCallback);
        this.mContext = context;
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.mTvSystemManager = AwTvSystemManager.getInstance(context);
    }

    public static synchronized AwTvChannelManager getInstance(Context context) {
        if (sAwTvChannelManager == null) {
            sAwTvChannelManager = new AwTvChannelManager(context);
        }
        return sAwTvChannelManager;
    }

    public int registerTvEventHandler(AwTvCallbackHandler awTvCallbackHandler) {
        if (!this.mTvEventListeners.contains(awTvCallbackHandler)) {
            this.mTvEventListeners.add(awTvCallbackHandler);
        }
        return this.mTvEventListeners.indexOf(awTvCallbackHandler);
    }

    public void unregisterTvEventHandler(int i) {
        this.mTvEventListeners.remove(i);
    }

    public void initDtvDevice(int i) {
        tunerInit(i);
    }

    public void deinitDtvDevice(int i) {
        tunerDeInit(i);
    }

    public void startDtvLock(AwTunerLockInfo awTunerLockInfo) {
        tunerTryLockAsync(awTunerLockInfo);
    }

    public void stopDtvTuner() {
        tunerStop();
    }

    public void stopDtvTunerLock() {
        tunerStopLock();
    }

    public void startDtvSearchEpgAsync(int i, int[] iArr) {
        tunerSearchEPGAsync(i, iArr);
    }

    public void stopDtvSearchEpg() {
        tunerStopSearchEPG();
    }

    public AwTunerSignalInfo startDtvGetSignal() {
        return tunerGetSignalInfo();
    }

    public void startDtvPlay(AwTunerLockInfo awTunerLockInfo, int i, int i2, int[] iArr, int i3) {
        tunerStartAsync(awTunerLockInfo, i, i2, iArr, i3);
    }

    public void startDtvControl(int i, int i2) {
        tunerControl(i, i2);
    }

    public void initAtvDevice(int i) {
        awAnalogTvInit(i);
    }

    public void deinitAtvDevice(int i) {
        awAnalogTvDeInit(i);
    }

    public void startAtvPlay(AwTvChannelInfo awTvChannelInfo) {
        awAnalogTvStartAsync(awTvChannelInfo.getFrequency(), awTvChannelInfo.getOrgFrequency(), awTvChannelInfo.getVideoStd(), awTvChannelInfo.getAudioStd(), awTvChannelInfo.getChannelNo(), awTvChannelInfo.getFineTune());
    }

    public void startAtvPlayNullChannel(boolean z) {
        awAnalogTvStartNullChannel(z);
    }

    public void stopAtvPlay(int i) {
        if (isTeletextOn() == 1) {
            stopTTX();
        }
        this.mAudioManager.setParameters("tuner_input=off");
        awAnalogTvStop(i);
    }

    public void initAtvScan(AwTvScanBase awTvScanBase) {
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(9);
        this.mHandler.removeMessages(1);
        if (awTvScanBase == null) {
            awTvScanBase = this.mDefaultTvScanBase;
        }
        this.mTvScanBase = awTvScanBase;
        awAnglogAtvInitScan();
    }

    public void startAtvAutoScan(int i, int i2) {
        debug("startAtvAutoScan");
        this.mTvEventListeners.forEach(awTvCallbackHandler -> {
            awTvCallbackHandler.notifyTvTuneState(2, 1, 1);
        });
        this.mAudioManager.setParameters("reset_mts=true");
        if (this.mTvScanBase != null) {
            this.mTvScanBase.startScan(true, i, i2, -1);
        }
        awAnglogAtvAutoScan(i, i2);
    }

    public void startAtvManualScan(int i, int i2, int i3) {
        debug("startAtvManualScan :" + i);
        AwTvScanBase.ScanType scanType = AwTvScanBase.ScanType.SCAN_TYPE_ATV_AUTO;
        if (i3 == 2) {
            scanType = AwTvScanBase.ScanType.SCAN_TYPE_NTSC;
        } else if (i3 == 1 || i3 == 3) {
            scanType = AwTvScanBase.ScanType.SCAN_TYPE_PAL;
        }
        if (this.mTvScanBase != null) {
            this.mTvScanBase.startScan(false, AwTvScanBase.ScanMode.SCAN_MODE_RANGE.ordinal(), scanType.ordinal(), i);
        }
        awAnglogAtvManualScan(i, i2, scanType.ordinal());
    }

    public void stopAtvScan() {
        debug("stopAtvScan");
        if (this.mTvScanBase != null) {
            this.mTvScanBase.cancelScan();
        }
        this.mTvScanBase = null;
        awAnglogAtvExitScan();
    }

    public int startAtvFineTune(int i) {
        debug("startAtvFineTune");
        return awAnglogAtvFineTune(i);
    }

    public boolean isAtvScanning() {
        return awAnglogAtvIsScanning();
    }

    public int getCurrentFreq() {
        return this.mCurrentFreq;
    }

    public int setAfcState(boolean z) {
        debug("setAfcState : " + z);
        this.mTvSystemManager.setAfcEnable(z);
        return awAnglogAtvSetAfcState(z);
    }

    public boolean getAfcState() {
        debug("getAfcState");
        return this.mTvSystemManager.getAfcEnable();
    }

    public int setColorStd(int i) {
        debug("setSiganelStd");
        return awAnglogAtvSetSiganelStd(i);
    }

    public int setEnableSnowScreen(boolean z) {
        debug("setEnableSnowScreen");
        return awAnglogAtvSetEnableSnowScreen(z);
    }

    public int startTTX() {
        debug("setTTXStart  ");
        return awAnglogAtvTTXStart();
    }

    public int stopTTX() {
        debug("stopTTX  ");
        return awAnglogAtvTTXStop();
    }

    public int transmitTeleTextKeyEvent(int i) {
        debug("transmitTeleTextKeyEvent  ");
        return awAnglogAtvTTXSetKeycode(i);
    }

    public int isTeletextOn() {
        debug("isTeletextOn");
        return awAnglogAtvTTXIsActive();
    }

    public int isTeletextExist() {
        debug("isTeletextExit");
        return awAnglogAtvTTXIsExist();
    }

    public int setTeletextCountry(int i) {
        debug("setTeletextCountry");
        return awAnglogAtvTTXSetCountry(i);
    }

    public int getTeletextCountry() {
        debug("setTeletextCountry");
        return 0;
    }

    public int setBlackScreen(boolean z) {
        debug("setBlackScreen");
        return awAnglogAtvSetBlackScreen(z);
    }

    private int tunerInit(int i) {
        debug("tunerInit " + i);
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().tunerInit(i);
        } catch (RemoteException e) {
            Log.e(TAG, "exception, fail to get service ", e);
            return -1;
        }
    }

    private int tunerDeInit(int i) {
        debug("tunerDeInit " + i);
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().tunerDeInit(i);
        } catch (RemoteException e) {
            Log.e(TAG, "exception, fail to get service ", e);
            return -1;
        }
    }

    private int tunerTryLockAsync(AwTunerLockInfo awTunerLockInfo) {
        debug("tunerTryLockAsync freq=" + awTunerLockInfo.frequency);
        DtvLockInfo dtvLockInfo = new DtvLockInfo();
        dtvLockInfo.frequency = awTunerLockInfo.frequency;
        dtvLockInfo.symbolRate = awTunerLockInfo.symbolRate;
        dtvLockInfo.modulation = awTunerLockInfo.modulation;
        dtvLockInfo.bandwidth = awTunerLockInfo.bandwidth;
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().tunerTryLockAsync(dtvLockInfo);
        } catch (RemoteException e) {
            Log.e(TAG, "exception, fail to get service ", e);
            return -1;
        }
    }

    private int tunerStopLock() {
        debug("tunerStopLock");
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().tunerControl(0, 0);
        } catch (RemoteException e) {
            Log.e(TAG, "exception, fail to get service ", e);
            return -1;
        }
    }

    private int tunerStartAsync(AwTunerLockInfo awTunerLockInfo, int i, int i2, int[] iArr, int i3) {
        debug("tunerStartAsync");
        DtvChannelInfo dtvChannelInfo = new DtvChannelInfo();
        dtvChannelInfo.lockInfo.frequency = awTunerLockInfo.frequency;
        dtvChannelInfo.lockInfo.symbolRate = awTunerLockInfo.symbolRate;
        dtvChannelInfo.lockInfo.modulation = awTunerLockInfo.modulation;
        dtvChannelInfo.lockInfo.bandwidth = awTunerLockInfo.bandwidth;
        dtvChannelInfo.videoPid = i2;
        dtvChannelInfo.audioSize = 32;
        dtvChannelInfo.pcrPid = i;
        dtvChannelInfo.audioPid = iArr;
        dtvChannelInfo.programNumber = i3;
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().tunerStartAsync(dtvChannelInfo);
        } catch (RemoteException e) {
            Log.e(TAG, "exception, fail to get service ", e);
            return -1;
        }
    }

    private int tunerStop() {
        debug("tunerStop");
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().tunerStop();
        } catch (RemoteException e) {
            Log.e(TAG, "exception, fail to get service ", e);
            return -1;
        }
    }

    private AwTunerSignalInfo tunerGetSignalInfo() {
        debug("tunerGetSignalInfo");
        try {
            SignalInfo signalInfoTunerGetSignalInfo = this.mBrocastManagerProxy.getAwTvBrocastService().tunerGetSignalInfo();
            if (signalInfoTunerGetSignalInfo != null) {
                return new AwTunerSignalInfo(signalInfoTunerGetSignalInfo.quality, signalInfoTunerGetSignalInfo.strength);
            }
            return null;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    private int tunerSearchEPGAsync(int i, int[] iArr) {
        debug("tunerSearchEPGAsync");
        EPGSearchParam ePGSearchParam = new EPGSearchParam();
        ePGSearchParam.searchFreq = i;
        ePGSearchParam.searchCount = iArr.length;
        for (int i2 = 0; i2 < iArr.length && i2 < ePGSearchParam.serviceID.length; i2++) {
            ePGSearchParam.serviceID[i2] = iArr[i2];
        }
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().tunerSearchEPGAsync(ePGSearchParam);
        } catch (RemoteException e) {
            Log.e(TAG, "exception, fail to get service ", e);
            return -1;
        }
    }

    private int tunerStopSearchEPG() {
        debug("tunerStopSearchEPG");
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().tunerControl(2, 0);
        } catch (RemoteException e) {
            Log.e(TAG, "exception, fail to get service ", e);
            return -1;
        }
    }

    private int tunerControl(int i, int i2) {
        debug("tunerControl type=" + i);
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().tunerControl(i, i2);
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return -1;
        }
    }

    private int awAnalogTvInit(int i) {
        debug("analogTvTunerInit type=" + i);
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerInit(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnalogTvDeInit(int i) {
        debug("analogTvTunerDeInit type=" + i);
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerDeInit(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnalogTvStartAsync(int i, int i2, int i3, int i4, int i5, int i6) {
        debug("analogTvTunerStartAsync freq=" + i);
        AnalogChannelInfo analogChannelInfo = new AnalogChannelInfo();
        analogChannelInfo.frequency = i;
        analogChannelInfo.org_frequency = i2;
        analogChannelInfo.video_std = i3;
        analogChannelInfo.audio_std = i4;
        analogChannelInfo.channel_id = i5;
        analogChannelInfo.finetune = i6;
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerStartAsync(analogChannelInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnalogTvStartNullChannel(boolean z) {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerStartNull(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnalogTvStop(int i) {
        debug("analogTvTunerStop type=" + i);
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerStop(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnalogTvControl(int i, int i2) {
        debug("analogTvTunerControl type=" + i);
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerControl(i, i2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvInitScan() {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerInitScan();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvAutoScan(int i, int i2) {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerStartAutoScan(i, i2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvManualScan(int i, int i2, int i3) {
        debug("awAnglogAtvManualScan : freq = " + i + " dir = " + i2 + "  type = " + i3);
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerStartRangeScan(i, i2, i3);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvExitScan() {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerExitScan();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private boolean awAnglogAtvIsScanning() {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerIsScanning() == 1;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int awAnglogAtvFineTune(int i) {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTunerStartFineTune(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvSetAfcState(boolean z) {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvEnableAfc(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvSetSiganelStd(int i) {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvSetSiganlStd(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvSetEnableSnowScreen(boolean z) {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvSetEnableSnowScreen(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvTTXStart() {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTTXStart();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvTTXStop() {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTTXStop();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvTTXSetKeycode(int i) {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTTXSetKeycode(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvTTXIsActive() {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTTXIsActive();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvTTXIsExist() {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTTXIsExist();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvTTXSetCountry(int i) {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvTTXSetCountry(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int awAnglogAtvSetBlackScreen(boolean z) {
        try {
            return this.mBrocastManagerProxy.getAwTvBrocastService().analogTvSetBlackScreen(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void handleScanProgressData(CallbackParcel callbackParcel) {
        ArrayList<Integer> arrayList = callbackParcel.bodyInt;
        int iIntValue = arrayList.get(0).intValue();
        int iIntValue2 = arrayList.get(1).intValue();
        int iIntValue3 = arrayList.get(2).intValue();
        int iIntValue4 = arrayList.get(3).intValue();
        this.mCurrentFreq = iIntValue2;
        this.mTvEventListeners.forEach(awTvCallbackHandler -> {
            awTvCallbackHandler.notifyTvScanProgressInfo(iIntValue, 9, iIntValue3, iIntValue4, iIntValue2);
        });
    }

    private void handleScanFinishData(CallbackParcel callbackParcel) {
        ArrayList<Integer> arrayList = callbackParcel.bodyInt;
        int iIntValue = arrayList.get(0).intValue();
        if (iIntValue == 2) {
            int iIntValue2 = arrayList.get(1).intValue();
            int iIntValue3 = arrayList.get(2).intValue();
            this.mTvEventListeners.forEach(awTvCallbackHandler -> {
                awTvCallbackHandler.notifyTvScanProgressInfo(iIntValue, 2, 100, iIntValue3, iIntValue2);
            });
            return;
        }
        AwProgramEvent awProgramEvent = new AwProgramEvent();
        awProgramEvent.type = callbackParcel.bodyInt.get(0).intValue();
        awProgramEvent.freq = callbackParcel.bodyInt.get(1).intValue();
        int iIntValue4 = callbackParcel.bodyInt.get(2).intValue();
        awProgramEvent.lock = iIntValue4 == 0 ? 1 : 0;
        if (iIntValue4 == 0) {
            awProgramEvent.quality = callbackParcel.bodyInt.get(3).intValue();
            awProgramEvent.strength = callbackParcel.bodyInt.get(4).intValue();
        }
        debug("readScanFinish freq=" + callbackParcel.bodyInt.get(1) + " lock=" + awProgramEvent.lock);
        this.mTvEventListeners.forEach(awTvCallbackHandler2 -> {
            awTvCallbackHandler2.notifyDVBInfo(2, awProgramEvent);
        });
    }

    private void handleScanProgramData(CallbackParcel callbackParcel) {
        AwProgramEvent awProgramEvent = new AwProgramEvent();
        ArrayList<Integer> arrayList = callbackParcel.bodyInt;
        awProgramEvent.type = arrayList.get(0).intValue();
        if (awProgramEvent.type == 2) {
            awProgramEvent.freq = arrayList.get(1).intValue();
            awProgramEvent.org_freq = arrayList.get(2).intValue();
            awProgramEvent.videoStd = arrayList.get(3).intValue();
            awProgramEvent.audioStd = arrayList.get(4).intValue();
            awProgramEvent.channelNo = arrayList.get(5).intValue();
            awProgramEvent.audioSys = arrayList.get(6).intValue();
            awProgramEvent.country = AwTvConfigBase.getInstance().getTvDefaultCountryWithCode();
            debug("readProgramEvent: " + awProgramEvent.type + ConfigManager.DEFAULT_VALUE + awProgramEvent.freq + ConfigManager.DEFAULT_VALUE + awProgramEvent.channelNo);
            if (this.mTvScanBase != null) {
                this.mTvScanBase.onAtvProgramReceived(awProgramEvent);
            }
        } else if (awProgramEvent.type == 1) {
            awProgramEvent.freq = arrayList.get(1).intValue();
            awProgramEvent.pcr = arrayList.get(4).intValue();
            awProgramEvent.video_pid = arrayList.get(5).intValue();
            awProgramEvent.serviceID = arrayList.get(6).intValue();
            awProgramEvent.quality = arrayList.get(7).intValue();
            awProgramEvent.strength = arrayList.get(8).intValue();
            awProgramEvent.free_ca = arrayList.get(9).intValue();
            awProgramEvent.scrambled = arrayList.get(10).intValue();
            awProgramEvent.eit_schedule = arrayList.get(12).intValue();
            awProgramEvent.eit_present_following = arrayList.get(13).intValue();
            awProgramEvent.symbolRate = arrayList.get(14).intValue();
            awProgramEvent.modulation = arrayList.get(15).intValue();
            awProgramEvent.bandwidth = arrayList.get(16).intValue();
            awProgramEvent.audio_pids = new int[32];
            for (int i = 0; i < 32; i++) {
                awProgramEvent.audio_pids[i] = arrayList.get(i + 17).intValue();
            }
            awProgramEvent.programName = callbackParcel.bodyString.get(0);
            debug("readProgramEvent " + callbackParcel.bodyString.get(0));
            if (this.mTvScanBase != null) {
                this.mTvScanBase.onDtvProgramReceived(awProgramEvent);
            }
        }
        this.mTvEventListeners.forEach(awTvCallbackHandler -> {
            awTvCallbackHandler.notifyTvScanProgramData(awProgramEvent.type, awProgramEvent);
        });
    }

    private void handleAtvPlayState(CallbackParcel callbackParcel) {
        int iIntValue = callbackParcel.bodyInt.get(0).intValue();
        int iIntValue2 = callbackParcel.bodyInt.get(1).intValue();
        this.mCurrentFreq = callbackParcel.bodyInt.get(2).intValue();
        debug("get current freq = " + this.mCurrentFreq);
        this.mTvEventListeners.forEach(awTvCallbackHandler -> {
            awTvCallbackHandler.notifyTvTuneState(iIntValue, 7, iIntValue2);
        });
    }

    private void handleTvDeviceState(CallbackParcel callbackParcel) {
        int i = 0;
        int iIntValue = callbackParcel.bodyInt.get(0).intValue();
        switch (callbackParcel.bodyInt.get(1).intValue()) {
            case 1:
            case 3:
                i = -1;
                break;
            case 2:
            case 4:
                break;
            default:
                i = 255;
                break;
        }
        this.mTvEventListeners.forEach(awTvCallbackHandler -> {
            awTvCallbackHandler.notifyTvSingalState(iIntValue, 3, i);
        });
    }

    private void handleAtvAfcFrequencyChanged(CallbackParcel callbackParcel) {
        int iIntValue = callbackParcel.bodyInt.get(0).intValue();
        int iIntValue2 = callbackParcel.bodyInt.get(1).intValue();
        int iIntValue3 = callbackParcel.bodyInt.get(2).intValue();
        this.mTvEventListeners.forEach(awTvCallbackHandler -> {
            awTvCallbackHandler.notifyTvAfcCheckFrequncyChanged(iIntValue, iIntValue2, iIntValue3);
        });
    }

    private void handleDVBUrlData(CallbackParcel callbackParcel) {
        AwTvPlayEvent awTvPlayEvent = new AwTvPlayEvent();
        int iIntValue = callbackParcel.bodyInt.get(0).intValue();
        awTvPlayEvent.state = iIntValue;
        if (iIntValue == 0) {
            String str = callbackParcel.bodyString.get(0);
            awTvPlayEvent.url = str;
            awTvPlayEvent.freq = callbackParcel.bodyInt.get(1).intValue();
            awTvPlayEvent.serviceID = callbackParcel.bodyInt.get(2).intValue();
            debug("url " + str);
        }
        this.mTvEventListeners.forEach(awTvCallbackHandler -> {
            awTvCallbackHandler.notifyDVBInfo(4, awTvPlayEvent);
        });
    }

    private void handleDVBEpgData(CallbackParcel callbackParcel) {
        switch (callbackParcel.type) {
            case 5:
                AwEPGEvent ePGEvent = readEPGEvent(callbackParcel);
                this.mTvEventListeners.forEach(awTvCallbackHandler -> {
                    awTvCallbackHandler.notifyDVBInfo(5, ePGEvent);
                });
                break;
            case 6:
                AwEPGEvent ePGFinish = readEPGFinish(callbackParcel);
                this.mTvEventListeners.forEach(awTvCallbackHandler2 -> {
                    awTvCallbackHandler2.notifyDVBInfo(6, ePGFinish);
                });
                break;
        }
    }

    private AwEPGEvent readEPGEvent(CallbackParcel callbackParcel) {
        AwEPGEvent awEPGEvent = new AwEPGEvent();
        awEPGEvent.freq = callbackParcel.bodyInt.get(0).intValue();
        awEPGEvent.serviceID = callbackParcel.bodyInt.get(1).intValue();
        int iIntValue = callbackParcel.bodyInt.get(2).intValue();
        awEPGEvent.events = new AwEPGEvent.Event[iIntValue];
        for (int i = 0; i < iIntValue; i++) {
            awEPGEvent.events[i] = new AwEPGEvent.Event();
            int i2 = i * 4;
            awEPGEvent.events[i].title = callbackParcel.bodyString.get(i2);
            awEPGEvent.events[i].startTimeUtcMillis = callbackParcel.bodyString.get(i2 + 1);
            awEPGEvent.events[i].endTimeUtcMillis = callbackParcel.bodyString.get(i2 + 2);
            awEPGEvent.events[i].desc = callbackParcel.bodyString.get(i2 + 3);
        }
        return awEPGEvent;
    }

    private AwEPGEvent readEPGFinish(CallbackParcel callbackParcel) {
        AwEPGEvent awEPGEvent = new AwEPGEvent();
        awEPGEvent.freq = callbackParcel.bodyInt.get(0).intValue();
        awEPGEvent.serviceID = callbackParcel.bodyInt.get(1).intValue();
        debug("readEPGFinish freq=" + callbackParcel.bodyInt.get(0) + " serviceID=" + callbackParcel.bodyInt.get(1));
        return awEPGEvent;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                handleScanProgramData((CallbackParcel) message.obj);
                break;
            case 2:
                handleScanFinishData((CallbackParcel) message.obj);
                break;
            case 3:
                handleTvDeviceState((CallbackParcel) message.obj);
                break;
            case 4:
                handleDVBUrlData((CallbackParcel) message.obj);
                break;
            case 5:
            case 6:
                handleDVBEpgData((CallbackParcel) message.obj);
                break;
            case 7:
                handleAtvPlayState((CallbackParcel) message.obj);
                break;
            case 9:
                handleScanProgressData((CallbackParcel) message.obj);
                break;
            case 10:
                handleAtvAfcFrequencyChanged((CallbackParcel) message.obj);
                break;
        }
        return true;
    }

    class BtvEventCallback extends IBtvServerCallback.Stub {
        BtvEventCallback() {
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServerCallback
        public void onNotify(CallbackParcel callbackParcel) throws RemoteException {
            Log.d(AwTvChannelManager.TAG, "BtvEventCallback " + callbackParcel.toString());
            AwTvChannelManager.this.mHandler.sendMessage(AwTvChannelManager.this.mHandler.obtainMessage(callbackParcel.type, 0, 0, callbackParcel));
        }
    }
}
