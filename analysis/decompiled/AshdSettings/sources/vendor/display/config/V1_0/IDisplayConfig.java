package vendor.display.config.V1_0;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface IDisplayConfig extends IBase {
    public static final String kInterfaceName = "vendor.display.config@1.0::IDisplayConfig";

    @FunctionalInterface
    public interface getActiveModeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSNRInfoCallback {
        void onValues(int i, SNRInfo sNRInfo);
    }

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    int configHdcp(boolean z) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    String dumpDebugInfo() throws RemoteException;

    int get3DLayerMode(int i) throws RemoteException;

    void getActiveMode(int i, getActiveModeCallback getactivemodecallback) throws RemoteException;

    int getAspectRatio(int i) throws RemoteException;

    int getAuthorizedStatus() throws RemoteException;

    int getConnectedHdcpLevel() throws RemoteException;

    int getCurrentDataspace(int i) throws RemoteException;

    int getDataspaceMode(int i) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    int getDisplayPortType(int i) throws RemoteException;

    int getEnhanceComponent(int i, int i2) throws RemoteException;

    int getHDMINativeMode(int i) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

    int getHdmiUserSetting(int i) throws RemoteException;

    int getPixelFormat(int i) throws RemoteException;

    void getSNRInfo(int i, getSNRInfoCallback getsnrinfocallback) throws RemoteException;

    ScreenMargin getScreenMargin(int i) throws RemoteException;

    ArrayList<Integer> getSupportedModes(int i) throws RemoteException;

    ArrayList<Integer> getSupportedPixelFormats(int i) throws RemoteException;

    int getTvAspectRatio(int i) throws RemoteException;

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

    int set3DLayerMode(int i, int i2) throws RemoteException;

    int setActiveMode(int i, int i2) throws RemoteException;

    int setAspectRatio(int i, int i2) throws RemoteException;

    int setDataspaceMode(int i, int i2) throws RemoteException;

    int setDisplayArgs(int i, int i2, int i3, int i4) throws RemoteException;

    int setDisplayPortType(int i, int i2) throws RemoteException;

    int setEnhanceComponent(int i, int i2, int i3) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    int setOverScan(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    int setPixelFormat(int i, int i2) throws RemoteException;

    int setSNRInfo(int i, SNRInfo sNRInfo) throws RemoteException;

    int setScreenMargin(int i, ScreenMargin screenMargin) throws RemoteException;

    int setSource(int i, int i2) throws RemoteException;

    int setTvAspectRatio(int i, int i2) throws RemoteException;

    boolean supported3D(int i) throws RemoteException;

    boolean supportedSNRSetting(int i) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    static IDisplayConfig asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface(kInterfaceName);
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IDisplayConfig)) {
            return (IDisplayConfig) iHwInterfaceQueryLocalInterface;
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

    static IDisplayConfig castFrom(IHwInterface iHwInterface) {
        if (iHwInterface == null) {
            return null;
        }
        return asInterface(iHwInterface.asBinder());
    }

    static IDisplayConfig getService(String str, boolean z) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str, z));
    }

    static IDisplayConfig getService(boolean z) throws RemoteException {
        return getService("default", z);
    }

    static IDisplayConfig getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str));
    }

    static IDisplayConfig getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IDisplayConfig {
        private IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.display.config@1.0::IDisplayConfig]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setDisplayArgs(int i, int i2, int i3, int i4) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int getDisplayPortType(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setDisplayPortType(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public void getActiveMode(int i, getActiveModeCallback getactivemodecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getactivemodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setActiveMode(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public ArrayList<Integer> getSupportedModes(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32Vector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public boolean supported3D(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int get3DLayerMode(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int set3DLayerMode(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public ArrayList<Integer> getSupportedPixelFormats(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(10, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32Vector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int getPixelFormat(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setPixelFormat(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(12, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int getCurrentDataspace(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int getDataspaceMode(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setDataspaceMode(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setAspectRatio(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int getAspectRatio(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(17, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setTvAspectRatio(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int getTvAspectRatio(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(19, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setScreenMargin(int i, ScreenMargin screenMargin) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            screenMargin.writeToParcel(hwParcel);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public ScreenMargin getScreenMargin(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(21, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                ScreenMargin screenMargin = new ScreenMargin();
                screenMargin.readFromParcel(hwParcel2);
                return screenMargin;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int getEnhanceComponent(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setEnhanceComponent(int i, int i2, int i3) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public boolean supportedSNRSetting(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(24, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readBool();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public void getSNRInfo(int i, getSNRInfoCallback getsnrinfocallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(25, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                int int32 = hwParcel2.readInt32();
                SNRInfo sNRInfo = new SNRInfo();
                sNRInfo.readFromParcel(hwParcel2);
                getsnrinfocallback.onValues(int32, sNRInfo);
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setSNRInfo(int i, SNRInfo sNRInfo) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            sNRInfo.writeToParcel(hwParcel);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int configHdcp(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(27, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int getConnectedHdcpLevel() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(28, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int getAuthorizedStatus() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(29, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public String dumpDebugInfo() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(30, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int getHDMINativeMode(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(31, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int getHdmiUserSetting(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(32, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setSource(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig
        public int setOverScan(int i, int i2, int i3, int i4, int i5) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IDisplayConfig.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            hwParcel.writeInt32(i5);
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

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
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

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
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

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
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

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
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

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
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

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException {
            return this.mRemote.linkToDeath(deathRecipient, j);
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
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

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
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

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
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

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(deathRecipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IDisplayConfig {
        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IDisplayConfig.kInterfaceName;
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IDisplayConfig.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{-88, -26, 23, 97, 58, -77, 81, -39, 48, -100, -95, -3, -6, 58, 58, -101, -91, 72, -73, -113, -72, 4, -43, -90, -89, -52, 113, 63, -44, 27, -59, 22}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo debugInfo = new DebugInfo();
            debugInfo.pid = HidlSupport.getPidIfSharable();
            debugInfo.ptr = 0L;
            debugInfo.arch = 0;
            return debugInfo;
        }

        @Override // vendor.display.config.V1_0.IDisplayConfig, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        public IHwInterface queryLocalInterface(String str) {
            if (IDisplayConfig.kInterfaceName.equals(str)) {
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
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int displayArgs = setDisplayArgs(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(displayArgs);
                            hwParcel2.send();
                            return;
                        case 2:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int displayPortType = getDisplayPortType(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(displayPortType);
                            hwParcel2.send();
                            return;
                        case 3:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int displayPortType2 = setDisplayPortType(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(displayPortType2);
                            hwParcel2.send();
                            return;
                        case 4:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            getActiveMode(hwParcel.readInt32(), new getActiveModeCallback() { // from class: vendor.display.config.V1_0.IDisplayConfig.Stub.1
                                @Override // vendor.display.config.V1_0.IDisplayConfig.getActiveModeCallback
                                public void onValues(int i4, int i5) {
                                    hwParcel2.writeStatus(0);
                                    hwParcel2.writeInt32(i4);
                                    hwParcel2.writeInt32(i5);
                                    hwParcel2.send();
                                }
                            });
                            return;
                        case 5:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int activeMode = setActiveMode(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(activeMode);
                            hwParcel2.send();
                            return;
                        case 6:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            ArrayList<Integer> supportedModes = getSupportedModes(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32Vector(supportedModes);
                            hwParcel2.send();
                            return;
                        case 7:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            boolean zSupported3D = supported3D(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeBool(zSupported3D);
                            hwParcel2.send();
                            return;
                        case 8:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int i4 = get3DLayerMode(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                            return;
                        case 9:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int i5 = set3DLayerMode(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.send();
                            return;
                        case 10:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            ArrayList<Integer> supportedPixelFormats = getSupportedPixelFormats(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32Vector(supportedPixelFormats);
                            hwParcel2.send();
                            return;
                        case 11:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int pixelFormat = getPixelFormat(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(pixelFormat);
                            hwParcel2.send();
                            return;
                        case 12:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int pixelFormat2 = setPixelFormat(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(pixelFormat2);
                            hwParcel2.send();
                            return;
                        case 13:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int currentDataspace = getCurrentDataspace(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(currentDataspace);
                            hwParcel2.send();
                            return;
                        case 14:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int dataspaceMode = getDataspaceMode(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(dataspaceMode);
                            hwParcel2.send();
                            return;
                        case 15:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int dataspaceMode2 = setDataspaceMode(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(dataspaceMode2);
                            hwParcel2.send();
                            return;
                        case 16:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int aspectRatio = setAspectRatio(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(aspectRatio);
                            hwParcel2.send();
                            return;
                        case 17:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int aspectRatio2 = getAspectRatio(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(aspectRatio2);
                            hwParcel2.send();
                            return;
                        case 18:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int tvAspectRatio = setTvAspectRatio(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(tvAspectRatio);
                            hwParcel2.send();
                            return;
                        case 19:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int tvAspectRatio2 = getTvAspectRatio(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(tvAspectRatio2);
                            hwParcel2.send();
                            return;
                        case 20:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int int32 = hwParcel.readInt32();
                            ScreenMargin screenMargin = new ScreenMargin();
                            screenMargin.readFromParcel(hwParcel);
                            int screenMargin2 = setScreenMargin(int32, screenMargin);
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(screenMargin2);
                            hwParcel2.send();
                            return;
                        case 21:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            ScreenMargin screenMargin3 = getScreenMargin(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            screenMargin3.writeToParcel(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 22:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int enhanceComponent = getEnhanceComponent(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(enhanceComponent);
                            hwParcel2.send();
                            return;
                        case 23:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int enhanceComponent2 = setEnhanceComponent(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(enhanceComponent2);
                            hwParcel2.send();
                            return;
                        case 24:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            boolean zSupportedSNRSetting = supportedSNRSetting(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeBool(zSupportedSNRSetting);
                            hwParcel2.send();
                            return;
                        case 25:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            getSNRInfo(hwParcel.readInt32(), new getSNRInfoCallback() { // from class: vendor.display.config.V1_0.IDisplayConfig.Stub.2
                                @Override // vendor.display.config.V1_0.IDisplayConfig.getSNRInfoCallback
                                public void onValues(int i6, SNRInfo sNRInfo) {
                                    hwParcel2.writeStatus(0);
                                    hwParcel2.writeInt32(i6);
                                    sNRInfo.writeToParcel(hwParcel2);
                                    hwParcel2.send();
                                }
                            });
                            return;
                        case 26:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int int33 = hwParcel.readInt32();
                            SNRInfo sNRInfo = new SNRInfo();
                            sNRInfo.readFromParcel(hwParcel);
                            int sNRInfo2 = setSNRInfo(int33, sNRInfo);
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(sNRInfo2);
                            hwParcel2.send();
                            return;
                        case 27:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int iConfigHdcp = configHdcp(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iConfigHdcp);
                            hwParcel2.send();
                            return;
                        case 28:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int connectedHdcpLevel = getConnectedHdcpLevel();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(connectedHdcpLevel);
                            hwParcel2.send();
                            return;
                        case 29:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int authorizedStatus = getAuthorizedStatus();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(authorizedStatus);
                            hwParcel2.send();
                            return;
                        case 30:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            String strDumpDebugInfo = dumpDebugInfo();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strDumpDebugInfo);
                            hwParcel2.send();
                            return;
                        case 31:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int hDMINativeMode = getHDMINativeMode(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(hDMINativeMode);
                            hwParcel2.send();
                            return;
                        case 32:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int hdmiUserSetting = getHdmiUserSetting(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(hdmiUserSetting);
                            hwParcel2.send();
                            return;
                        case 33:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int source = setSource(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(source);
                            hwParcel2.send();
                            return;
                        case 34:
                            hwParcel.enforceInterface(IDisplayConfig.kInterfaceName);
                            int overScan = setOverScan(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(overScan);
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
