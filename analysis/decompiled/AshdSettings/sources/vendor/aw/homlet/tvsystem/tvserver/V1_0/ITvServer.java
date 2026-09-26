package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import android.support.v4.media.TransportMediator;
import com.alibaba.fastjson.asm.Opcodes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface ITvServer extends IBase {
    public static final String kInterfaceName = "vendor.aw.homlet.tvsystem.tvserver@1.0::ITvServer";

    @FunctionalInterface
    public interface SubDeviceVpGetWindowCallback {
        void onValues(ScreenWin screenWin, ScreenWin screenWin2, int i);
    }

    int DeviceSvpStart() throws RemoteException;

    int DeviceSvpStop() throws RemoteException;

    int SubDeviceAtvChannelChange(int i, int i2) throws RemoteException;

    int SubDeviceAtvChannelScanEnd() throws RemoteException;

    int SubDeviceAtvChannelScanSetVstd(int i) throws RemoteException;

    int SubDeviceAtvChannelScanStart() throws RemoteException;

    int SubDeviceAtvEnableSnowScreen(boolean z) throws RemoteException;

    int SubDeviceAtvIsFastSyncLock() throws RemoteException;

    int SubDeviceAtvScanGetSoundStd() throws RemoteException;

    int SubDeviceAtvScanSoundInit() throws RemoteException;

    int SubDeviceAtvScanSoundUninit() throws RemoteException;

    int SubDeviceAtvSetRegion(int i) throws RemoteException;

    int SubDeviceAtvSetSignalStd(int i) throws RemoteException;

    int SubDeviceAtvTTXEnableVBILine(short s, byte b) throws RemoteException;

    int SubDeviceAtvTTXGetVBIAddr() throws RemoteException;

    int SubDeviceAtvTTXGetVBIOffset() throws RemoteException;

    int SubDeviceAtvTTXGetVBISize() throws RemoteException;

    int SubDeviceAtvTTXResetVBI() throws RemoteException;

    int SubDeviceAtvTTXStartVBI() throws RemoteException;

    int SubDeviceAtvTTXStopVBI() throws RemoteException;

    int SubDeviceCECARCnotReady(int i) throws RemoteException;

    int SubDeviceCECARCnotSupport(int i) throws RemoteException;

    int SubDeviceCECBroadcastVendorID(byte b) throws RemoteException;

    int SubDeviceCECEnableFunction(boolean z) throws RemoteException;

    int SubDeviceCECInitSystemAudioMode(byte b, short s) throws RemoteException;

    int SubDeviceCECMenuRequest(byte b, int i) throws RemoteException;

    int SubDeviceCECNotifySource(short s) throws RemoteException;

    int SubDeviceCECReportARCInit() throws RemoteException;

    int SubDeviceCECReportCECVersion(byte b, int i) throws RemoteException;

    int SubDeviceCECReportTVPhyAddr() throws RemoteException;

    int SubDeviceCECRequestARCTerm() throws RemoteException;

    int SubDeviceCECRequestAudioStatus() throws RemoteException;

    int SubDeviceCECRequestDevicePhyAddr(byte b) throws RemoteException;

    int SubDeviceCECRequestOSDName(byte b) throws RemoteException;

    int SubDeviceCECRequestPowerStatus(byte b) throws RemoteException;

    int SubDeviceCECRequestRequestARCInit() throws RemoteException;

    int SubDeviceCECRequestShortAudioDescriptor() throws RemoteException;

    int SubDeviceCECRequestSysAudioModeStatus() throws RemoteException;

    int SubDeviceCECRequestVendorID(byte b) throws RemoteException;

    int SubDeviceCECRoutingChange(short s, short s2) throws RemoteException;

    int SubDeviceCECSendMessage(tag_cec_message tag_cec_messageVar) throws RemoteException;

    int SubDeviceCECSetARCOnlynMode(boolean z) throws RemoteException;

    int SubDeviceCECSetMenuLang(int i) throws RemoteException;

    int SubDeviceCECSetPing(boolean z, short s) throws RemoteException;

    int SubDeviceCECSetPowerStatus(int i) throws RemoteException;

    int SubDeviceCECSetWakeupEnable(boolean z) throws RemoteException;

    int SubDeviceCECStandbyDevice(byte b) throws RemoteException;

    void SubDeviceCECSwitchARCTXPath(int i) throws RemoteException;

    void SubDeviceCECTurnOnARCAudioPath(int i) throws RemoteException;

    int SubDeviceCvbsPlugStatus(int i) throws RemoteException;

    int SubDeviceDisableScreenCover(int i) throws RemoteException;

    int SubDeviceEnableScreenCover(int i, int i2) throws RemoteException;

    int SubDeviceGetCurrentHDMIChannelPortId() throws RemoteException;

    String SubDeviceGetHdcpSum(int i) throws RemoteException;

    int SubDeviceGetSource() throws RemoteException;

    THalSignalInfo SubDeviceGetSourceSignalInfo(int i) throws RemoteException;

    boolean SubDeviceGetVideoFreeze() throws RemoteException;

    int SubDeviceHDMICheckEDIDUpdateStatus() throws RemoteException;

    boolean SubDeviceHDMIGetCurrentPortStatus() throws RemoteException;

    THDMI_DEBUG_INFO SubDeviceHDMIGetHDMIDebugInfo() throws RemoteException;

    THalFrmBuf SubDeviceHDMIGetIncapFrameBuffer() throws RemoteException;

    byte SubDeviceHDMIGetPortStatus() throws RemoteException;

    int SubDeviceHDMIInitHandler() throws RemoteException;

    int SubDeviceHDMIPortRemap(byte b) throws RemoteException;

    void SubDeviceHDMIPullHotPlug(byte b, byte b2) throws RemoteException;

    int SubDeviceHDMIRequestEDID(int i) throws RemoteException;

    int SubDeviceHDMIRequestHDMIPortNumber() throws RemoteException;

    void SubDeviceHDMIResetEDIDModule() throws RemoteException;

    void SubDeviceHDMIResetPhy(boolean z) throws RemoteException;

    int SubDeviceHDMISET5VFlag(byte b, byte b2) throws RemoteException;

    int SubDeviceHDMISendDataToCPUS(McuCommParam_t mcuCommParam_t) throws RemoteException;

    int SubDeviceHDMISetEDIDAudioMode(int i, int i2) throws RemoteException;

    int SubDeviceHDMISetEDIDVersion(byte b) throws RemoteException;

    void SubDeviceHDMISetHpdTimeInterval(int i) throws RemoteException;

    void SubDeviceHDMISetPortMap(int i, byte b) throws RemoteException;

    int SubDeviceHDMISourceToPort(int i) throws RemoteException;

    int SubDeviceHDMIUpdateEDID(ArrayList<Byte> arrayList, int i) throws RemoteException;

    int SubDeviceNotifyAtvHotplugState(boolean z) throws RemoteException;

    int SubDeviceSeamlessDisable() throws RemoteException;

    int SubDeviceSeamlessEnable() throws RemoteException;

    int SubDeviceSetCecmsgCallback(ICecmsgCallback iCecmsgCallback) throws RemoteException;

    void SubDeviceSetCurrentHDMIChannelPortId(int i) throws RemoteException;

    void SubDeviceSetHDCP22Key() throws RemoteException;

    int SubDeviceSetMonitorCallback(byte b, ITvCallback iTvCallback, int i) throws RemoteException;

    int SubDeviceSetSource(int i) throws RemoteException;

    int SubDeviceSetSourceAsync(int i) throws RemoteException;

    int SubDeviceSetVideoFreeze(boolean z) throws RemoteException;

    int SubDeviceSetVideoWindow(ScreenWin screenWin, ScreenWin screenWin2, int i) throws RemoteException;

    int SubDeviceSourceInit() throws RemoteException;

    String SubDeviceSourceLoadConfig() throws RemoteException;

    int SubDeviceUnsetCecmsgCallback(ICecmsgCallback iCecmsgCallback) throws RemoteException;

    int SubDeviceUnsetMonitorCallback(byte b, ITvCallback iTvCallback) throws RemoteException;

    int SubDeviceVpDeInit() throws RemoteException;

    int SubDeviceVpDisableBlackScreen() throws RemoteException;

    int SubDeviceVpDisablePixel2PiexlMode() throws RemoteException;

    int SubDeviceVpEnableBlackScreen() throws RemoteException;

    int SubDeviceVpEnablePixel2PiexlMode(ScreenWin screenWin, ScreenWin screenWin2) throws RemoteException;

    void SubDeviceVpGetWindow(SubDeviceVpGetWindowCallback subDeviceVpGetWindowCallback) throws RemoteException;

    int SubDeviceVpInit() throws RemoteException;

    int SubDeviceVpSetWindow(ScreenWin screenWin, ScreenWin screenWin2, int i) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    int checkPictureMode(int i, String str) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    int delEdidKey(int i) throws RemoteException;

    int[] factoryAdvanceGetColorManager(int i) throws RemoteException;

    int factoryAdvanceResetColorManager(int i, int i2) throws RemoteException;

    int factoryAdvanceSetColorManager(int i, int i2, int i3, int i4) throws RemoteException;

    int factoryColorTempNotSaveValueWriteIni() throws RemoteException;

    int factoryCopyWBInfoToOtherSrc(int i) throws RemoteException;

    int factoryCopyWBInfoToOtherSrcNotSave(int i) throws RemoteException;

    int factoryExportConfig(int i, String str) throws RemoteException;

    int factoryGetDDRValue(int i) throws RemoteException;

    PictureCurveOsdValue factoryGetNonLinearPQValue(int i, int i2) throws RemoteException;

    OverScanInfo factoryGetOverScan(int i, int i2, int i3) throws RemoteException;

    String factoryGetPanelModel() throws RemoteException;

    int factoryGetPanelValue(int i) throws RemoteException;

    int factoryGetPictureLevel(int i) throws RemoteException;

    int factoryGetPictureParam(int i, int i2) throws RemoteException;

    int factoryGetPowerMode() throws RemoteException;

    ArrayList<Integer> factoryGetTimerList(int i) throws RemoteException;

    int factoryGetTimerMode(int i, boolean z) throws RemoteException;

    PQRgbOffsetGain factoryGetWBData(int i, int i2) throws RemoteException;

    int factoryGetWBValue(int i, int i2, int i3) throws RemoteException;

    int factoryImportConfig(int i, String str) throws RemoteException;

    int factoryResetPanelSettings() throws RemoteException;

    int factoryResetWBInfo() throws RemoteException;

    int factorySetDDRValue(int i, int i2) throws RemoteException;

    int factorySetNonLinearPQValue(int i, int i2, int i3, int i4) throws RemoteException;

    int factorySetOverScan(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    int factorySetPQValue(int i, String str, int i2, int i3) throws RemoteException;

    int factorySetPanelValue(int i, int i2) throws RemoteException;

    int factorySetPictureLevel(int i, int i2) throws RemoteException;

    int factorySetPictureParam(int i, int i2, int i3) throws RemoteException;

    int factorySetPowerMode(int i) throws RemoteException;

    int factorySetTimerMode(int i, int i2) throws RemoteException;

    int factorySetWBValue(int i, int i2, int i3, int i4) throws RemoteException;

    int factorySetWBValueNotSave(int i, int i2, int i3, int i4) throws RemoteException;

    int getArcPort() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    ArrayList<Byte> getEdidKey(int i) throws RemoteException;

    String getEnv(String str) throws RemoteException;

    int[] getGammaRGBValue() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

    String getHdcpKeySum(int i) throws RemoteException;

    String getMacKey(String str) throws RemoteException;

    int getPQConfig(int i, int i2) throws RemoteException;

    int getPQValue(int i) throws RemoteException;

    int getPannelHeight() throws RemoteException;

    int getPannelWidth() throws RemoteException;

    ArrayList<String> getPictureModeList() throws RemoteException;

    String getPictureModeName() throws RemoteException;

    String getSecureStorageKey(String str) throws RemoteException;

    int getStorePictureMode() throws RemoteException;

    String getSubtitleEncodingType() throws RemoteException;

    ArrayList<String> getTvSourceSignalInfo() throws RemoteException;

    int getVideoRange() throws RemoteException;

    String getWidevineKeySum() throws RemoteException;

    void init_input() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<String> interfaceChain() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    String interfaceDescriptor() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void notifySyspropsChanged() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void ping() throws RemoteException;

    int reloadHdcpKey(int i) throws RemoteException;

    int removeHdcpKey(int i) throws RemoteException;

    int removeWidevineKey() throws RemoteException;

    int resetAllSettings() throws RemoteException;

    int setAspectRatioNotSave(int i) throws RemoteException;

    int setEdidKey(int i, ArrayList<Byte> arrayList) throws RemoteException;

    int setEnv(String str, String str2) throws RemoteException;

    int setGammaRGBValue(int i, int i2) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    int setHdcpKey(int i, ArrayList<Byte> arrayList) throws RemoteException;

    int setMacKey(String str, String str2) throws RemoteException;

    int setPQCallback(IPQCallback iPQCallback) throws RemoteException;

    int setPQValue(int i, int i2) throws RemoteException;

    int setPictureModeByName(String str) throws RemoteException;

    int setSecureStorageKey(String str, String str2) throws RemoteException;

    int setSourcePortInfo(int i, int i2, String str) throws RemoteException;

    int setStorePictureMode(boolean z) throws RemoteException;

    int setSubtitleEncodingType(int i, int i2) throws RemoteException;

    int setVideoRange(int i) throws RemoteException;

    int setWidevineKey(ArrayList<Byte> arrayList) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    int unsetPQCallback(int i) throws RemoteException;

    int userSetScene(int i, boolean z) throws RemoteException;

    static ITvServer asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface(kInterfaceName);
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof ITvServer)) {
            return (ITvServer) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static ITvServer castFrom(IHwInterface iHwInterface) {
        if (iHwInterface == null) {
            return null;
        }
        return asInterface(iHwInterface.asBinder());
    }

    static ITvServer getService(String str, boolean z) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str, z));
    }

    static ITvServer getService(boolean z) throws RemoteException {
        return getService("default", z);
    }

    static ITvServer getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str));
    }

    static ITvServer getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements ITvServer {
        private IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.aw.homlet.tvsystem.tvserver@1.0::ITvServer]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public void init_input() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int DeviceSvpStart() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int DeviceSvpStop() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceVpInit() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceVpDeInit() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(5, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceSetVideoFreeze(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public boolean SubDeviceGetVideoFreeze() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceVpEnableBlackScreen() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(8, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceVpDisableBlackScreen() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(9, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceEnableScreenCover(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(10, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceDisableScreenCover(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(11, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public String SubDeviceSourceLoadConfig() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(12, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceSourceInit() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(13, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceSetSource(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(14, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceSetSourceAsync(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(15, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceGetSource() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(16, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public void SubDeviceSetCurrentHDMIChannelPortId(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(17, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceGetCurrentHDMIChannelPortId() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(18, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public THalSignalInfo SubDeviceGetSourceSignalInfo(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(19, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                THalSignalInfo tHalSignalInfo = new THalSignalInfo();
                tHalSignalInfo.readFromParcel(hwParcel2);
                return tHalSignalInfo;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceVpSetWindow(ScreenWin screenWin, ScreenWin screenWin2, int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            screenWin.writeToParcel(hwParcel);
            screenWin2.writeToParcel(hwParcel);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(20, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public void SubDeviceVpGetWindow(SubDeviceVpGetWindowCallback subDeviceVpGetWindowCallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(21, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                ScreenWin screenWin = new ScreenWin();
                screenWin.readFromParcel(hwParcel2);
                ScreenWin screenWin2 = new ScreenWin();
                screenWin2.readFromParcel(hwParcel2);
                subDeviceVpGetWindowCallback.onValues(screenWin, screenWin2, hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceVpEnablePixel2PiexlMode(ScreenWin screenWin, ScreenWin screenWin2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            screenWin.writeToParcel(hwParcel);
            screenWin2.writeToParcel(hwParcel);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(22, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceVpDisablePixel2PiexlMode() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(23, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceSetMonitorCallback(byte b, ITvCallback iTvCallback, int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            hwParcel.writeStrongBinder(iTvCallback == null ? null : iTvCallback.asBinder());
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(24, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceUnsetMonitorCallback(byte b, ITvCallback iTvCallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            hwParcel.writeStrongBinder(iTvCallback == null ? null : iTvCallback.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(25, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceNotifyAtvHotplugState(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(26, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public String SubDeviceGetHdcpSum(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(27, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public THalFrmBuf SubDeviceHDMIGetIncapFrameBuffer() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(28, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                THalFrmBuf tHalFrmBuf = new THalFrmBuf();
                tHalFrmBuf.readFromParcel(hwParcel2);
                return tHalFrmBuf;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public void SubDeviceSetHDCP22Key() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(29, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public byte SubDeviceHDMIGetPortStatus() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(30, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt8();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public boolean SubDeviceHDMIGetCurrentPortStatus() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(31, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public void SubDeviceHDMIResetPhy(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(32, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceHDMIInitHandler() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(33, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceHDMIUpdateEDID(ArrayList<Byte> arrayList, int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8Vector(arrayList);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(34, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceHDMICheckEDIDUpdateStatus() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(35, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceHDMISetEDIDAudioMode(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(36, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceHDMISetEDIDVersion(byte b) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(37, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceHDMIRequestEDID(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(38, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceHDMISET5VFlag(byte b, byte b2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            hwParcel.writeInt8(b2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(39, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceHDMIRequestHDMIPortNumber() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(40, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceHDMISendDataToCPUS(McuCommParam_t mcuCommParam_t) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            mcuCommParam_t.writeToParcel(hwParcel);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(41, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public void SubDeviceHDMISetPortMap(int i, byte b) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt8(b);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(42, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceHDMIPortRemap(byte b) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(43, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceHDMISourceToPort(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(44, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public void SubDeviceHDMISetHpdTimeInterval(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(45, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public THDMI_DEBUG_INFO SubDeviceHDMIGetHDMIDebugInfo() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(46, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                THDMI_DEBUG_INFO thdmi_debug_info = new THDMI_DEBUG_INFO();
                thdmi_debug_info.readFromParcel(hwParcel2);
                return thdmi_debug_info;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public void SubDeviceHDMIPullHotPlug(byte b, byte b2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            hwParcel.writeInt8(b2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(47, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public void SubDeviceHDMIResetEDIDModule() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(48, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECSetPowerStatus(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(49, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECEnableFunction(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(50, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECSetWakeupEnable(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(51, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECSetPing(boolean z, short s) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeBool(z);
            hwParcel.writeInt16(s);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(52, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECSendMessage(tag_cec_message tag_cec_messageVar) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            tag_cec_messageVar.writeToParcel(hwParcel);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(53, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECSetARCOnlynMode(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(54, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECBroadcastVendorID(byte b) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(55, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECRequestDevicePhyAddr(byte b) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(56, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECNotifySource(short s) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt16(s);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(57, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECRoutingChange(short s, short s2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt16(s);
            hwParcel.writeInt16(s2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(58, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECRequestVendorID(byte b) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(59, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECSetMenuLang(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(60, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECRequestPowerStatus(byte b) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(61, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECRequestShortAudioDescriptor() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(62, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECRequestRequestARCInit() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(63, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECRequestARCTerm() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(64, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECARCnotSupport(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(65, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECARCnotReady(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(66, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECReportARCInit() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(67, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECInitSystemAudioMode(byte b, short s) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            hwParcel.writeInt16(s);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(68, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECRequestSysAudioModeStatus() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(69, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECRequestAudioStatus() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(70, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECRequestOSDName(byte b) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(71, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECReportTVPhyAddr() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(72, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECMenuRequest(byte b, int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(73, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECStandbyDevice(byte b) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(74, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCECReportCECVersion(byte b, int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8(b);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(75, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public void SubDeviceCECTurnOnARCAudioPath(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(76, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public void SubDeviceCECSwitchARCTXPath(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(77, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceSeamlessDisable() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(78, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceSeamlessEnable() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(79, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvChannelScanStart() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(80, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvChannelScanEnd() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(81, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvChannelChange(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(82, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvSetRegion(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(83, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvSetSignalStd(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(84, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvIsFastSyncLock() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(85, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvEnableSnowScreen(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(86, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvTTXStartVBI() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(87, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvTTXStopVBI() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(88, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvTTXResetVBI() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(89, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvTTXGetVBIOffset() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(90, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvTTXGetVBISize() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(91, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvTTXGetVBIAddr() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(92, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvTTXEnableVBILine(short s, byte b) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt16(s);
            hwParcel.writeInt8(b);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(93, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvScanSoundInit() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(94, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvScanSoundUninit() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(95, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvScanGetSoundStd() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(96, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceAtvChannelScanSetVstd(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(97, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceCvbsPlugStatus(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(98, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceSetCecmsgCallback(ICecmsgCallback iCecmsgCallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeStrongBinder(iCecmsgCallback == null ? null : iCecmsgCallback.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(99, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceUnsetCecmsgCallback(ICecmsgCallback iCecmsgCallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeStrongBinder(iCecmsgCallback == null ? null : iCecmsgCallback.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(100, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int getArcPort() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(101, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setPQValue(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(102, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int getPQValue(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(103, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setPictureModeByName(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(104, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public String getPictureModeName() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(105, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setStorePictureMode(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(106, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int getStorePictureMode() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(107, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int checkPictureMode(int i, String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(108, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public ArrayList<String> getPictureModeList() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(109, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setAspectRatioNotSave(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(110, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int resetAllSettings() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(111, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int getPQConfig(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(112, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setVideoRange(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(113, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int getVideoRange() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(114, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int getPannelWidth() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(115, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int getPannelHeight() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(116, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factorySetWBValue(int i, int i2, int i3, int i4) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(117, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryGetWBValue(int i, int i2, int i3) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(118, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public PQRgbOffsetGain factoryGetWBData(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(119, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                PQRgbOffsetGain pQRgbOffsetGain = new PQRgbOffsetGain();
                pQRgbOffsetGain.readFromParcel(hwParcel2);
                return pQRgbOffsetGain;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryResetWBInfo() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(120, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryCopyWBInfoToOtherSrc(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(121, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factorySetWBValueNotSave(int i, int i2, int i3, int i4) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(122, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryCopyWBInfoToOtherSrcNotSave(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(123, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryColorTempNotSaveValueWriteIni() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(124, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setGammaRGBValue(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(125, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int[] getGammaRGBValue() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(126, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                int[] iArr = new int[33];
                hwParcel2.readBuffer(132L).copyToInt32Array(0L, iArr, 33);
                return iArr;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factorySetOverScan(int i, int i2, int i3, int i4, int i5) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            hwParcel.writeInt32(i5);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(TransportMediator.KEYCODE_MEDIA_PAUSE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public OverScanInfo factoryGetOverScan(int i, int i2, int i3) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(128, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                OverScanInfo overScanInfo = new OverScanInfo();
                overScanInfo.readFromParcel(hwParcel2);
                return overScanInfo;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factorySetNonLinearPQValue(int i, int i2, int i3, int i4) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(TvSignalID.SIGNALID_SCART_PAL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public PictureCurveOsdValue factoryGetNonLinearPQValue(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(130, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                PictureCurveOsdValue pictureCurveOsdValue = new PictureCurveOsdValue();
                pictureCurveOsdValue.readFromParcel(hwParcel2);
                return pictureCurveOsdValue;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factorySetPQValue(int i, String str, int i2, int i3) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(131, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factorySetPictureParam(int i, int i2, int i3) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(132, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryGetPictureParam(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(133, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factorySetPictureLevel(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(134, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryGetPictureLevel(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(135, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryAdvanceSetColorManager(int i, int i2, int i3, int i4) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(136, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryAdvanceResetColorManager(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(137, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int[] factoryAdvanceGetColorManager(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(138, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                int[] iArr = new int[36];
                hwParcel2.readBuffer(144L).copyToInt32Array(0L, iArr, 36);
                return iArr;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public String factoryGetPanelModel() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(139, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factorySetPanelValue(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(140, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryGetPanelValue(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(141, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryResetPanelSettings() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(142, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setPQCallback(IPQCallback iPQCallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeStrongBinder(iPQCallback == null ? null : iPQCallback.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(143, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int unsetPQCallback(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(TvSignalID.SIGNALID_SCART_MAC640480, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setSubtitleEncodingType(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(TvSignalID.SIGNALID_SCART_MAC1152870, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public String getSubtitleEncodingType() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(TvSignalID.SIGNALID_SCART_MAC832624, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int SubDeviceSetVideoWindow(ScreenWin screenWin, ScreenWin screenWin2, int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            screenWin.writeToParcel(hwParcel);
            screenWin2.writeToParcel(hwParcel);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(147, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int userSetScene(int i, boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.LCMP, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public ArrayList<String> getTvSourceSignalInfo() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.FCMPL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryImportConfig(int i, String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(150, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryExportConfig(int i, String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.DCMPL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factorySetPowerMode(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(152, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryGetPowerMode() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.IFEQ, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factorySetDDRValue(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.IFNE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryGetDDRValue(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(155, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factoryGetTimerMode(int i, boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(156, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int factorySetTimerMode(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(157, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public ArrayList<Integer> factoryGetTimerList(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.IFLE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32Vector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setEdidKey(int i, ArrayList<Byte> arrayList) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt8Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.IF_ICMPEQ, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public ArrayList<Byte> getEdidKey(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.IF_ICMPNE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt8Vector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int delEdidKey(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.IF_ICMPLT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public String getMacKey(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.IF_ICMPGE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setMacKey(String str, String str2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.IF_ICMPGT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public String getHdcpKeySum(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(164, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setHdcpKey(int i, ArrayList<Byte> arrayList) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt8Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.IF_ACMPEQ, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int removeHdcpKey(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.IF_ACMPNE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int reloadHdcpKey(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.GOTO, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public String getWidevineKeySum() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(168, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setWidevineKey(ArrayList<Byte> arrayList) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt8Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(Opcodes.RET, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int removeWidevineKey() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(170, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setSecureStorageKey(String str, String str2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(171, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public String getSecureStorageKey(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(172, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public String getEnv(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(173, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setEnv(String str, String str2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(174, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer
        public int setSourcePortInfo(int i, int i2, String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(175, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            hwParcel.writeNativeHandle(nativeHandle);
            hwParcel.writeStringVector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256131655, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public String interfaceDescriptor() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public ArrayList<byte[]> getHashChain() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256398152, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                ArrayList<byte[]> arrayList = new ArrayList<>();
                HwBlob buffer = hwParcel2.readBuffer(16L);
                int int32 = buffer.getInt32(8L);
                HwBlob embeddedBuffer = hwParcel2.readEmbeddedBuffer(int32 * 32, buffer.handle(), 0L, true);
                arrayList.clear();
                for (int i = 0; i < int32; i++) {
                    byte[] bArr = new byte[32];
                    embeddedBuffer.copyToInt8Array(i * 32, bArr, 32);
                    arrayList.add(bArr);
                }
                hwParcel2.release();
                return arrayList;
            } catch (Throwable th) {
                hwParcel2.release();
                throw th;
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public void setHALInstrumentation() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256462420, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException {
            return this.mRemote.linkToDeath(deathRecipient, j);
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public void ping() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256921159, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public DebugInfo getDebugInfo() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(257049926, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                DebugInfo debugInfo = new DebugInfo();
                debugInfo.readFromParcel(hwParcel2);
                return debugInfo;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public void notifySyspropsChanged() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(257120595, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(deathRecipient);
        }
    }

    public static abstract class Stub extends HwBinder implements ITvServer {
        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return ITvServer.kInterfaceName;
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(ITvServer.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo debugInfo = new DebugInfo();
            debugInfo.pid = HidlSupport.getPidIfSharable();
            debugInfo.ptr = 0L;
            debugInfo.arch = 0;
            return debugInfo;
        }

        @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        public IHwInterface queryLocalInterface(String str) {
            if (ITvServer.kInterfaceName.equals(str)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String str) throws RemoteException {
            registerService(str);
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        public void onTransact(int i, HwParcel hwParcel, final HwParcel hwParcel2, int i2) throws RemoteException {
            if (i == 256067662) {
                hwParcel.enforceInterface(IBase.kInterfaceName);
                ArrayList<String> arrayListInterfaceChain = interfaceChain();
                hwParcel2.writeStatus(0);
                hwParcel2.writeStringVector(arrayListInterfaceChain);
                hwParcel2.send();
                return;
            }
            if (i == 256131655) {
                hwParcel.enforceInterface(IBase.kInterfaceName);
                debug(hwParcel.readNativeHandle(), hwParcel.readStringVector());
                hwParcel2.writeStatus(0);
                hwParcel2.send();
                return;
            }
            if (i == 256136003) {
                hwParcel.enforceInterface(IBase.kInterfaceName);
                String strInterfaceDescriptor = interfaceDescriptor();
                hwParcel2.writeStatus(0);
                hwParcel2.writeString(strInterfaceDescriptor);
                hwParcel2.send();
                return;
            }
            if (i == 256398152) {
                hwParcel.enforceInterface(IBase.kInterfaceName);
                ArrayList<byte[]> hashChain = getHashChain();
                hwParcel2.writeStatus(0);
                HwBlob hwBlob = new HwBlob(16);
                int size = hashChain.size();
                hwBlob.putInt32(8L, size);
                hwBlob.putBool(12L, false);
                HwBlob hwBlob2 = new HwBlob(size * 32);
                for (int i3 = 0; i3 < size; i3++) {
                    long j = i3 * 32;
                    byte[] bArr = hashChain.get(i3);
                    if (bArr == null || bArr.length != 32) {
                        throw new IllegalArgumentException("Array element is not of the expected length");
                    }
                    hwBlob2.putInt8Array(j, bArr);
                }
                hwBlob.putBlob(0L, hwBlob2);
                hwParcel2.writeBuffer(hwBlob);
                hwParcel2.send();
                return;
            }
            if (i == 256462420) {
                hwParcel.enforceInterface(IBase.kInterfaceName);
                setHALInstrumentation();
                return;
            }
            if (i != 256660548) {
                if (i == 256921159) {
                    hwParcel.enforceInterface(IBase.kInterfaceName);
                    ping();
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                }
                if (i == 257049926) {
                    hwParcel.enforceInterface(IBase.kInterfaceName);
                    DebugInfo debugInfo = getDebugInfo();
                    hwParcel2.writeStatus(0);
                    debugInfo.writeToParcel(hwParcel2);
                    hwParcel2.send();
                    return;
                }
                if (i != 257120595) {
                    switch (i) {
                        case 1:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            init_input();
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 2:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iDeviceSvpStart = DeviceSvpStart();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iDeviceSvpStart);
                            hwParcel2.send();
                            return;
                        case 3:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iDeviceSvpStop = DeviceSvpStop();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iDeviceSvpStop);
                            hwParcel2.send();
                            return;
                        case 4:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceVpInit = SubDeviceVpInit();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceVpInit);
                            hwParcel2.send();
                            return;
                        case 5:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceVpDeInit = SubDeviceVpDeInit();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceVpDeInit);
                            hwParcel2.send();
                            return;
                        case 6:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceSetVideoFreeze = SubDeviceSetVideoFreeze(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceSetVideoFreeze);
                            hwParcel2.send();
                            return;
                        case 7:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            boolean zSubDeviceGetVideoFreeze = SubDeviceGetVideoFreeze();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeBool(zSubDeviceGetVideoFreeze);
                            hwParcel2.send();
                            return;
                        case 8:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceVpEnableBlackScreen = SubDeviceVpEnableBlackScreen();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceVpEnableBlackScreen);
                            hwParcel2.send();
                            return;
                        case 9:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceVpDisableBlackScreen = SubDeviceVpDisableBlackScreen();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceVpDisableBlackScreen);
                            hwParcel2.send();
                            return;
                        case 10:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceEnableScreenCover = SubDeviceEnableScreenCover(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceEnableScreenCover);
                            hwParcel2.send();
                            return;
                        case 11:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceDisableScreenCover = SubDeviceDisableScreenCover(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceDisableScreenCover);
                            hwParcel2.send();
                            return;
                        case 12:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            String strSubDeviceSourceLoadConfig = SubDeviceSourceLoadConfig();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strSubDeviceSourceLoadConfig);
                            hwParcel2.send();
                            return;
                        case 13:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceSourceInit = SubDeviceSourceInit();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceSourceInit);
                            hwParcel2.send();
                            return;
                        case 14:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceSetSource = SubDeviceSetSource(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceSetSource);
                            hwParcel2.send();
                            return;
                        case 15:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceSetSourceAsync = SubDeviceSetSourceAsync(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceSetSourceAsync);
                            hwParcel2.send();
                            return;
                        case 16:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceGetSource = SubDeviceGetSource();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceGetSource);
                            hwParcel2.send();
                            return;
                        case 17:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            SubDeviceSetCurrentHDMIChannelPortId(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 18:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceGetCurrentHDMIChannelPortId = SubDeviceGetCurrentHDMIChannelPortId();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceGetCurrentHDMIChannelPortId);
                            hwParcel2.send();
                            return;
                        case 19:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            THalSignalInfo tHalSignalInfoSubDeviceGetSourceSignalInfo = SubDeviceGetSourceSignalInfo(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            tHalSignalInfoSubDeviceGetSourceSignalInfo.writeToParcel(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 20:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            ScreenWin screenWin = new ScreenWin();
                            screenWin.readFromParcel(hwParcel);
                            ScreenWin screenWin2 = new ScreenWin();
                            screenWin2.readFromParcel(hwParcel);
                            int iSubDeviceVpSetWindow = SubDeviceVpSetWindow(screenWin, screenWin2, hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceVpSetWindow);
                            hwParcel2.send();
                            return;
                        case 21:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            SubDeviceVpGetWindow(new SubDeviceVpGetWindowCallback() { // from class: vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer.Stub.1
                                @Override // vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer.SubDeviceVpGetWindowCallback
                                public void onValues(ScreenWin screenWin3, ScreenWin screenWin4, int i4) {
                                    hwParcel2.writeStatus(0);
                                    screenWin3.writeToParcel(hwParcel2);
                                    screenWin4.writeToParcel(hwParcel2);
                                    hwParcel2.writeInt32(i4);
                                    hwParcel2.send();
                                }
                            });
                            return;
                        case 22:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            ScreenWin screenWin3 = new ScreenWin();
                            screenWin3.readFromParcel(hwParcel);
                            ScreenWin screenWin4 = new ScreenWin();
                            screenWin4.readFromParcel(hwParcel);
                            int iSubDeviceVpEnablePixel2PiexlMode = SubDeviceVpEnablePixel2PiexlMode(screenWin3, screenWin4);
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceVpEnablePixel2PiexlMode);
                            hwParcel2.send();
                            return;
                        case 23:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceVpDisablePixel2PiexlMode = SubDeviceVpDisablePixel2PiexlMode();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceVpDisablePixel2PiexlMode);
                            hwParcel2.send();
                            return;
                        case 24:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceSetMonitorCallback = SubDeviceSetMonitorCallback(hwParcel.readInt8(), ITvCallback.asInterface(hwParcel.readStrongBinder()), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceSetMonitorCallback);
                            hwParcel2.send();
                            return;
                        case 25:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceUnsetMonitorCallback = SubDeviceUnsetMonitorCallback(hwParcel.readInt8(), ITvCallback.asInterface(hwParcel.readStrongBinder()));
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceUnsetMonitorCallback);
                            hwParcel2.send();
                            return;
                        case 26:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceNotifyAtvHotplugState = SubDeviceNotifyAtvHotplugState(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceNotifyAtvHotplugState);
                            hwParcel2.send();
                            return;
                        case 27:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            String strSubDeviceGetHdcpSum = SubDeviceGetHdcpSum(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strSubDeviceGetHdcpSum);
                            hwParcel2.send();
                            return;
                        case 28:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            THalFrmBuf tHalFrmBufSubDeviceHDMIGetIncapFrameBuffer = SubDeviceHDMIGetIncapFrameBuffer();
                            hwParcel2.writeStatus(0);
                            tHalFrmBufSubDeviceHDMIGetIncapFrameBuffer.writeToParcel(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 29:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            SubDeviceSetHDCP22Key();
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 30:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            byte bSubDeviceHDMIGetPortStatus = SubDeviceHDMIGetPortStatus();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt8(bSubDeviceHDMIGetPortStatus);
                            hwParcel2.send();
                            return;
                        case 31:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            boolean zSubDeviceHDMIGetCurrentPortStatus = SubDeviceHDMIGetCurrentPortStatus();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeBool(zSubDeviceHDMIGetCurrentPortStatus);
                            hwParcel2.send();
                            return;
                        case 32:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            SubDeviceHDMIResetPhy(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 33:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceHDMIInitHandler = SubDeviceHDMIInitHandler();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceHDMIInitHandler);
                            hwParcel2.send();
                            return;
                        case 34:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceHDMIUpdateEDID = SubDeviceHDMIUpdateEDID(hwParcel.readInt8Vector(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceHDMIUpdateEDID);
                            hwParcel2.send();
                            return;
                        case 35:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceHDMICheckEDIDUpdateStatus = SubDeviceHDMICheckEDIDUpdateStatus();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceHDMICheckEDIDUpdateStatus);
                            hwParcel2.send();
                            return;
                        case 36:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceHDMISetEDIDAudioMode = SubDeviceHDMISetEDIDAudioMode(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceHDMISetEDIDAudioMode);
                            hwParcel2.send();
                            return;
                        case 37:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceHDMISetEDIDVersion = SubDeviceHDMISetEDIDVersion(hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceHDMISetEDIDVersion);
                            hwParcel2.send();
                            return;
                        case 38:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceHDMIRequestEDID = SubDeviceHDMIRequestEDID(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceHDMIRequestEDID);
                            hwParcel2.send();
                            return;
                        case 39:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceHDMISET5VFlag = SubDeviceHDMISET5VFlag(hwParcel.readInt8(), hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceHDMISET5VFlag);
                            hwParcel2.send();
                            return;
                        case 40:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceHDMIRequestHDMIPortNumber = SubDeviceHDMIRequestHDMIPortNumber();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceHDMIRequestHDMIPortNumber);
                            hwParcel2.send();
                            return;
                        case 41:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            McuCommParam_t mcuCommParam_t = new McuCommParam_t();
                            mcuCommParam_t.readFromParcel(hwParcel);
                            int iSubDeviceHDMISendDataToCPUS = SubDeviceHDMISendDataToCPUS(mcuCommParam_t);
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceHDMISendDataToCPUS);
                            hwParcel2.send();
                            return;
                        case 42:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            SubDeviceHDMISetPortMap(hwParcel.readInt32(), hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 43:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceHDMIPortRemap = SubDeviceHDMIPortRemap(hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceHDMIPortRemap);
                            hwParcel2.send();
                            return;
                        case 44:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceHDMISourceToPort = SubDeviceHDMISourceToPort(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceHDMISourceToPort);
                            hwParcel2.send();
                            return;
                        case 45:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            SubDeviceHDMISetHpdTimeInterval(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 46:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            THDMI_DEBUG_INFO thdmi_debug_infoSubDeviceHDMIGetHDMIDebugInfo = SubDeviceHDMIGetHDMIDebugInfo();
                            hwParcel2.writeStatus(0);
                            thdmi_debug_infoSubDeviceHDMIGetHDMIDebugInfo.writeToParcel(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 47:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            SubDeviceHDMIPullHotPlug(hwParcel.readInt8(), hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 48:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            SubDeviceHDMIResetEDIDModule();
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 49:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECSetPowerStatus = SubDeviceCECSetPowerStatus(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECSetPowerStatus);
                            hwParcel2.send();
                            return;
                        case 50:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECEnableFunction = SubDeviceCECEnableFunction(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECEnableFunction);
                            hwParcel2.send();
                            return;
                        case 51:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECSetWakeupEnable = SubDeviceCECSetWakeupEnable(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECSetWakeupEnable);
                            hwParcel2.send();
                            return;
                        case 52:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECSetPing = SubDeviceCECSetPing(hwParcel.readBool(), hwParcel.readInt16());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECSetPing);
                            hwParcel2.send();
                            return;
                        case 53:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            tag_cec_message tag_cec_messageVar = new tag_cec_message();
                            tag_cec_messageVar.readFromParcel(hwParcel);
                            int iSubDeviceCECSendMessage = SubDeviceCECSendMessage(tag_cec_messageVar);
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECSendMessage);
                            hwParcel2.send();
                            return;
                        case 54:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECSetARCOnlynMode = SubDeviceCECSetARCOnlynMode(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECSetARCOnlynMode);
                            hwParcel2.send();
                            return;
                        case 55:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECBroadcastVendorID = SubDeviceCECBroadcastVendorID(hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECBroadcastVendorID);
                            hwParcel2.send();
                            return;
                        case 56:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECRequestDevicePhyAddr = SubDeviceCECRequestDevicePhyAddr(hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECRequestDevicePhyAddr);
                            hwParcel2.send();
                            return;
                        case 57:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECNotifySource = SubDeviceCECNotifySource(hwParcel.readInt16());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECNotifySource);
                            hwParcel2.send();
                            return;
                        case 58:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECRoutingChange = SubDeviceCECRoutingChange(hwParcel.readInt16(), hwParcel.readInt16());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECRoutingChange);
                            hwParcel2.send();
                            return;
                        case 59:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECRequestVendorID = SubDeviceCECRequestVendorID(hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECRequestVendorID);
                            hwParcel2.send();
                            return;
                        case 60:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECSetMenuLang = SubDeviceCECSetMenuLang(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECSetMenuLang);
                            hwParcel2.send();
                            return;
                        case 61:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECRequestPowerStatus = SubDeviceCECRequestPowerStatus(hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECRequestPowerStatus);
                            hwParcel2.send();
                            return;
                        case 62:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECRequestShortAudioDescriptor = SubDeviceCECRequestShortAudioDescriptor();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECRequestShortAudioDescriptor);
                            hwParcel2.send();
                            return;
                        case 63:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECRequestRequestARCInit = SubDeviceCECRequestRequestARCInit();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECRequestRequestARCInit);
                            hwParcel2.send();
                            return;
                        case 64:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECRequestARCTerm = SubDeviceCECRequestARCTerm();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECRequestARCTerm);
                            hwParcel2.send();
                            return;
                        case 65:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECARCnotSupport = SubDeviceCECARCnotSupport(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECARCnotSupport);
                            hwParcel2.send();
                            return;
                        case 66:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECARCnotReady = SubDeviceCECARCnotReady(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECARCnotReady);
                            hwParcel2.send();
                            return;
                        case 67:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECReportARCInit = SubDeviceCECReportARCInit();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECReportARCInit);
                            hwParcel2.send();
                            return;
                        case 68:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECInitSystemAudioMode = SubDeviceCECInitSystemAudioMode(hwParcel.readInt8(), hwParcel.readInt16());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECInitSystemAudioMode);
                            hwParcel2.send();
                            return;
                        case 69:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECRequestSysAudioModeStatus = SubDeviceCECRequestSysAudioModeStatus();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECRequestSysAudioModeStatus);
                            hwParcel2.send();
                            return;
                        case 70:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECRequestAudioStatus = SubDeviceCECRequestAudioStatus();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECRequestAudioStatus);
                            hwParcel2.send();
                            return;
                        case 71:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECRequestOSDName = SubDeviceCECRequestOSDName(hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECRequestOSDName);
                            hwParcel2.send();
                            return;
                        case 72:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECReportTVPhyAddr = SubDeviceCECReportTVPhyAddr();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECReportTVPhyAddr);
                            hwParcel2.send();
                            return;
                        case 73:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECMenuRequest = SubDeviceCECMenuRequest(hwParcel.readInt8(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECMenuRequest);
                            hwParcel2.send();
                            return;
                        case 74:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECStandbyDevice = SubDeviceCECStandbyDevice(hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECStandbyDevice);
                            hwParcel2.send();
                            return;
                        case 75:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCECReportCECVersion = SubDeviceCECReportCECVersion(hwParcel.readInt8(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCECReportCECVersion);
                            hwParcel2.send();
                            return;
                        case 76:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            SubDeviceCECTurnOnARCAudioPath(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 77:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            SubDeviceCECSwitchARCTXPath(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 78:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceSeamlessDisable = SubDeviceSeamlessDisable();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceSeamlessDisable);
                            hwParcel2.send();
                            return;
                        case 79:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceSeamlessEnable = SubDeviceSeamlessEnable();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceSeamlessEnable);
                            hwParcel2.send();
                            return;
                        case 80:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvChannelScanStart = SubDeviceAtvChannelScanStart();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvChannelScanStart);
                            hwParcel2.send();
                            return;
                        case 81:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvChannelScanEnd = SubDeviceAtvChannelScanEnd();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvChannelScanEnd);
                            hwParcel2.send();
                            return;
                        case 82:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvChannelChange = SubDeviceAtvChannelChange(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvChannelChange);
                            hwParcel2.send();
                            return;
                        case 83:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvSetRegion = SubDeviceAtvSetRegion(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvSetRegion);
                            hwParcel2.send();
                            return;
                        case 84:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvSetSignalStd = SubDeviceAtvSetSignalStd(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvSetSignalStd);
                            hwParcel2.send();
                            return;
                        case 85:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvIsFastSyncLock = SubDeviceAtvIsFastSyncLock();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvIsFastSyncLock);
                            hwParcel2.send();
                            return;
                        case 86:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvEnableSnowScreen = SubDeviceAtvEnableSnowScreen(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvEnableSnowScreen);
                            hwParcel2.send();
                            return;
                        case 87:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvTTXStartVBI = SubDeviceAtvTTXStartVBI();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvTTXStartVBI);
                            hwParcel2.send();
                            return;
                        case 88:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvTTXStopVBI = SubDeviceAtvTTXStopVBI();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvTTXStopVBI);
                            hwParcel2.send();
                            return;
                        case 89:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvTTXResetVBI = SubDeviceAtvTTXResetVBI();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvTTXResetVBI);
                            hwParcel2.send();
                            return;
                        case 90:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvTTXGetVBIOffset = SubDeviceAtvTTXGetVBIOffset();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvTTXGetVBIOffset);
                            hwParcel2.send();
                            return;
                        case 91:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvTTXGetVBISize = SubDeviceAtvTTXGetVBISize();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvTTXGetVBISize);
                            hwParcel2.send();
                            return;
                        case 92:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvTTXGetVBIAddr = SubDeviceAtvTTXGetVBIAddr();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvTTXGetVBIAddr);
                            hwParcel2.send();
                            return;
                        case 93:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvTTXEnableVBILine = SubDeviceAtvTTXEnableVBILine(hwParcel.readInt16(), hwParcel.readInt8());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvTTXEnableVBILine);
                            hwParcel2.send();
                            return;
                        case 94:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvScanSoundInit = SubDeviceAtvScanSoundInit();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvScanSoundInit);
                            hwParcel2.send();
                            return;
                        case 95:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvScanSoundUninit = SubDeviceAtvScanSoundUninit();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvScanSoundUninit);
                            hwParcel2.send();
                            return;
                        case 96:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvScanGetSoundStd = SubDeviceAtvScanGetSoundStd();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvScanGetSoundStd);
                            hwParcel2.send();
                            return;
                        case 97:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceAtvChannelScanSetVstd = SubDeviceAtvChannelScanSetVstd(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceAtvChannelScanSetVstd);
                            hwParcel2.send();
                            return;
                        case 98:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceCvbsPlugStatus = SubDeviceCvbsPlugStatus(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceCvbsPlugStatus);
                            hwParcel2.send();
                            return;
                        case 99:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceSetCecmsgCallback = SubDeviceSetCecmsgCallback(ICecmsgCallback.asInterface(hwParcel.readStrongBinder()));
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceSetCecmsgCallback);
                            hwParcel2.send();
                            return;
                        case 100:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iSubDeviceUnsetCecmsgCallback = SubDeviceUnsetCecmsgCallback(ICecmsgCallback.asInterface(hwParcel.readStrongBinder()));
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceUnsetCecmsgCallback);
                            hwParcel2.send();
                            return;
                        case 101:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int arcPort = getArcPort();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(arcPort);
                            hwParcel2.send();
                            return;
                        case 102:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int pQValue = setPQValue(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(pQValue);
                            hwParcel2.send();
                            return;
                        case 103:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int pQValue2 = getPQValue(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(pQValue2);
                            hwParcel2.send();
                            return;
                        case 104:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int pictureModeByName = setPictureModeByName(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(pictureModeByName);
                            hwParcel2.send();
                            return;
                        case 105:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            String pictureModeName = getPictureModeName();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(pictureModeName);
                            hwParcel2.send();
                            return;
                        case 106:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int storePictureMode = setStorePictureMode(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(storePictureMode);
                            hwParcel2.send();
                            return;
                        case 107:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int storePictureMode2 = getStorePictureMode();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(storePictureMode2);
                            hwParcel2.send();
                            return;
                        case 108:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iCheckPictureMode = checkPictureMode(hwParcel.readInt32(), hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iCheckPictureMode);
                            hwParcel2.send();
                            return;
                        case 109:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            ArrayList<String> pictureModeList = getPictureModeList();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeStringVector(pictureModeList);
                            hwParcel2.send();
                            return;
                        case 110:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int aspectRatioNotSave = setAspectRatioNotSave(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(aspectRatioNotSave);
                            hwParcel2.send();
                            return;
                        case 111:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iResetAllSettings = resetAllSettings();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iResetAllSettings);
                            hwParcel2.send();
                            return;
                        case 112:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int pQConfig = getPQConfig(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(pQConfig);
                            hwParcel2.send();
                            return;
                        case 113:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int videoRange = setVideoRange(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(videoRange);
                            hwParcel2.send();
                            return;
                        case 114:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int videoRange2 = getVideoRange();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(videoRange2);
                            hwParcel2.send();
                            return;
                        case 115:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int pannelWidth = getPannelWidth();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(pannelWidth);
                            hwParcel2.send();
                            return;
                        case 116:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int pannelHeight = getPannelHeight();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(pannelHeight);
                            hwParcel2.send();
                            return;
                        case 117:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactorySetWBValue = factorySetWBValue(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactorySetWBValue);
                            hwParcel2.send();
                            return;
                        case 118:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryGetWBValue = factoryGetWBValue(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryGetWBValue);
                            hwParcel2.send();
                            return;
                        case 119:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            PQRgbOffsetGain pQRgbOffsetGainFactoryGetWBData = factoryGetWBData(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            pQRgbOffsetGainFactoryGetWBData.writeToParcel(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 120:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryResetWBInfo = factoryResetWBInfo();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryResetWBInfo);
                            hwParcel2.send();
                            return;
                        case 121:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryCopyWBInfoToOtherSrc = factoryCopyWBInfoToOtherSrc(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryCopyWBInfoToOtherSrc);
                            hwParcel2.send();
                            return;
                        case 122:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactorySetWBValueNotSave = factorySetWBValueNotSave(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactorySetWBValueNotSave);
                            hwParcel2.send();
                            return;
                        case 123:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryCopyWBInfoToOtherSrcNotSave = factoryCopyWBInfoToOtherSrcNotSave(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryCopyWBInfoToOtherSrcNotSave);
                            hwParcel2.send();
                            return;
                        case 124:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryColorTempNotSaveValueWriteIni = factoryColorTempNotSaveValueWriteIni();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryColorTempNotSaveValueWriteIni);
                            hwParcel2.send();
                            return;
                        case 125:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int gammaRGBValue = setGammaRGBValue(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(gammaRGBValue);
                            hwParcel2.send();
                            return;
                        case 126:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int[] gammaRGBValue2 = getGammaRGBValue();
                            hwParcel2.writeStatus(0);
                            HwBlob hwBlob3 = new HwBlob(132);
                            if (gammaRGBValue2 == null || gammaRGBValue2.length != 33) {
                                throw new IllegalArgumentException("Array element is not of the expected length");
                            }
                            hwBlob3.putInt32Array(0L, gammaRGBValue2);
                            hwParcel2.writeBuffer(hwBlob3);
                            hwParcel2.send();
                            return;
                        case TransportMediator.KEYCODE_MEDIA_PAUSE /* 127 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactorySetOverScan = factorySetOverScan(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactorySetOverScan);
                            hwParcel2.send();
                            return;
                        case 128:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            OverScanInfo overScanInfoFactoryGetOverScan = factoryGetOverScan(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            overScanInfoFactoryGetOverScan.writeToParcel(hwParcel2);
                            hwParcel2.send();
                            return;
                        case TvSignalID.SIGNALID_SCART_PAL /* 129 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactorySetNonLinearPQValue = factorySetNonLinearPQValue(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactorySetNonLinearPQValue);
                            hwParcel2.send();
                            return;
                        case 130:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            PictureCurveOsdValue pictureCurveOsdValueFactoryGetNonLinearPQValue = factoryGetNonLinearPQValue(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            pictureCurveOsdValueFactoryGetNonLinearPQValue.writeToParcel(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 131:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactorySetPQValue = factorySetPQValue(hwParcel.readInt32(), hwParcel.readString(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactorySetPQValue);
                            hwParcel2.send();
                            return;
                        case 132:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactorySetPictureParam = factorySetPictureParam(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactorySetPictureParam);
                            hwParcel2.send();
                            return;
                        case 133:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryGetPictureParam = factoryGetPictureParam(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryGetPictureParam);
                            hwParcel2.send();
                            return;
                        case 134:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactorySetPictureLevel = factorySetPictureLevel(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactorySetPictureLevel);
                            hwParcel2.send();
                            return;
                        case 135:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryGetPictureLevel = factoryGetPictureLevel(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryGetPictureLevel);
                            hwParcel2.send();
                            return;
                        case 136:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryAdvanceSetColorManager = factoryAdvanceSetColorManager(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryAdvanceSetColorManager);
                            hwParcel2.send();
                            return;
                        case 137:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryAdvanceResetColorManager = factoryAdvanceResetColorManager(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryAdvanceResetColorManager);
                            hwParcel2.send();
                            return;
                        case 138:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int[] iArrFactoryAdvanceGetColorManager = factoryAdvanceGetColorManager(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            HwBlob hwBlob4 = new HwBlob(TvSignalID.SIGNALID_SCART_MAC640480);
                            if (iArrFactoryAdvanceGetColorManager == null || iArrFactoryAdvanceGetColorManager.length != 36) {
                                throw new IllegalArgumentException("Array element is not of the expected length");
                            }
                            hwBlob4.putInt32Array(0L, iArrFactoryAdvanceGetColorManager);
                            hwParcel2.writeBuffer(hwBlob4);
                            hwParcel2.send();
                            return;
                        case 139:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            String strFactoryGetPanelModel = factoryGetPanelModel();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strFactoryGetPanelModel);
                            hwParcel2.send();
                            return;
                        case 140:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactorySetPanelValue = factorySetPanelValue(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactorySetPanelValue);
                            hwParcel2.send();
                            return;
                        case 141:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryGetPanelValue = factoryGetPanelValue(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryGetPanelValue);
                            hwParcel2.send();
                            return;
                        case 142:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryResetPanelSettings = factoryResetPanelSettings();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryResetPanelSettings);
                            hwParcel2.send();
                            return;
                        case 143:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int pQCallback = setPQCallback(IPQCallback.asInterface(hwParcel.readStrongBinder()));
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(pQCallback);
                            hwParcel2.send();
                            return;
                        case TvSignalID.SIGNALID_SCART_MAC640480 /* 144 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iUnsetPQCallback = unsetPQCallback(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iUnsetPQCallback);
                            hwParcel2.send();
                            return;
                        case TvSignalID.SIGNALID_SCART_MAC1152870 /* 145 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int subtitleEncodingType = setSubtitleEncodingType(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(subtitleEncodingType);
                            hwParcel2.send();
                            return;
                        case TvSignalID.SIGNALID_SCART_MAC832624 /* 146 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            String subtitleEncodingType2 = getSubtitleEncodingType();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(subtitleEncodingType2);
                            hwParcel2.send();
                            return;
                        case 147:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            ScreenWin screenWin5 = new ScreenWin();
                            screenWin5.readFromParcel(hwParcel);
                            ScreenWin screenWin6 = new ScreenWin();
                            screenWin6.readFromParcel(hwParcel);
                            int iSubDeviceSetVideoWindow = SubDeviceSetVideoWindow(screenWin5, screenWin6, hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iSubDeviceSetVideoWindow);
                            hwParcel2.send();
                            return;
                        case Opcodes.LCMP /* 148 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iUserSetScene = userSetScene(hwParcel.readInt32(), hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iUserSetScene);
                            hwParcel2.send();
                            return;
                        case Opcodes.FCMPL /* 149 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            ArrayList<String> tvSourceSignalInfo = getTvSourceSignalInfo();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeStringVector(tvSourceSignalInfo);
                            hwParcel2.send();
                            return;
                        case 150:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryImportConfig = factoryImportConfig(hwParcel.readInt32(), hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryImportConfig);
                            hwParcel2.send();
                            return;
                        case Opcodes.DCMPL /* 151 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryExportConfig = factoryExportConfig(hwParcel.readInt32(), hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryExportConfig);
                            hwParcel2.send();
                            return;
                        case 152:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactorySetPowerMode = factorySetPowerMode(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactorySetPowerMode);
                            hwParcel2.send();
                            return;
                        case Opcodes.IFEQ /* 153 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryGetPowerMode = factoryGetPowerMode();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryGetPowerMode);
                            hwParcel2.send();
                            return;
                        case Opcodes.IFNE /* 154 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactorySetDDRValue = factorySetDDRValue(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactorySetDDRValue);
                            hwParcel2.send();
                            return;
                        case 155:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryGetDDRValue = factoryGetDDRValue(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryGetDDRValue);
                            hwParcel2.send();
                            return;
                        case 156:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactoryGetTimerMode = factoryGetTimerMode(hwParcel.readInt32(), hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactoryGetTimerMode);
                            hwParcel2.send();
                            return;
                        case 157:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iFactorySetTimerMode = factorySetTimerMode(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iFactorySetTimerMode);
                            hwParcel2.send();
                            return;
                        case Opcodes.IFLE /* 158 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            ArrayList<Integer> arrayListFactoryGetTimerList = factoryGetTimerList(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32Vector(arrayListFactoryGetTimerList);
                            hwParcel2.send();
                            return;
                        case Opcodes.IF_ICMPEQ /* 159 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int edidKey = setEdidKey(hwParcel.readInt32(), hwParcel.readInt8Vector());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(edidKey);
                            hwParcel2.send();
                            return;
                        case Opcodes.IF_ICMPNE /* 160 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            ArrayList<Byte> edidKey2 = getEdidKey(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt8Vector(edidKey2);
                            hwParcel2.send();
                            return;
                        case Opcodes.IF_ICMPLT /* 161 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iDelEdidKey = delEdidKey(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iDelEdidKey);
                            hwParcel2.send();
                            return;
                        case Opcodes.IF_ICMPGE /* 162 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            String macKey = getMacKey(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(macKey);
                            hwParcel2.send();
                            return;
                        case Opcodes.IF_ICMPGT /* 163 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int macKey2 = setMacKey(hwParcel.readString(), hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(macKey2);
                            hwParcel2.send();
                            return;
                        case 164:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            String hdcpKeySum = getHdcpKeySum(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(hdcpKeySum);
                            hwParcel2.send();
                            return;
                        case Opcodes.IF_ACMPEQ /* 165 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int hdcpKey = setHdcpKey(hwParcel.readInt32(), hwParcel.readInt8Vector());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(hdcpKey);
                            hwParcel2.send();
                            return;
                        case Opcodes.IF_ACMPNE /* 166 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iRemoveHdcpKey = removeHdcpKey(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iRemoveHdcpKey);
                            hwParcel2.send();
                            return;
                        case Opcodes.GOTO /* 167 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iReloadHdcpKey = reloadHdcpKey(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iReloadHdcpKey);
                            hwParcel2.send();
                            return;
                        case 168:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            String widevineKeySum = getWidevineKeySum();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(widevineKeySum);
                            hwParcel2.send();
                            return;
                        case Opcodes.RET /* 169 */:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int widevineKey = setWidevineKey(hwParcel.readInt8Vector());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(widevineKey);
                            hwParcel2.send();
                            return;
                        case 170:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int iRemoveWidevineKey = removeWidevineKey();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iRemoveWidevineKey);
                            hwParcel2.send();
                            return;
                        case 171:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int secureStorageKey = setSecureStorageKey(hwParcel.readString(), hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(secureStorageKey);
                            hwParcel2.send();
                            return;
                        case 172:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            String secureStorageKey2 = getSecureStorageKey(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(secureStorageKey2);
                            hwParcel2.send();
                            return;
                        case 173:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            String env = getEnv(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(env);
                            hwParcel2.send();
                            return;
                        case 174:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int env2 = setEnv(hwParcel.readString(), hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(env2);
                            hwParcel2.send();
                            return;
                        case 175:
                            hwParcel.enforceInterface(ITvServer.kInterfaceName);
                            int sourcePortInfo = setSourcePortInfo(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(sourcePortInfo);
                            hwParcel2.send();
                            return;
                        default:
                            return;
                    }
                }
                hwParcel.enforceInterface(IBase.kInterfaceName);
                notifySyspropsChanged();
            }
        }
    }
}
