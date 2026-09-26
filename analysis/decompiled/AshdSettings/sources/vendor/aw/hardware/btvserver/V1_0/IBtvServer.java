package vendor.aw.hardware.btvserver.V1_0;

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
public interface IBtvServer extends IBase {
    public static final String kInterfaceName = "vendor.aw.hardware.btvserver@1.0::IBtvServer";

    @FunctionalInterface
    public interface getTvConfigerCallback {
        void onValues(int i, IBtvConfiger iBtvConfiger);
    }

    int analogTvEnableAfc(boolean z) throws RemoteException;

    int analogTvSetBlackScreen(boolean z) throws RemoteException;

    int analogTvSetEnableSnowScreen(boolean z) throws RemoteException;

    int analogTvSetSiganlStd(int i) throws RemoteException;

    int analogTvTTXIsActive() throws RemoteException;

    int analogTvTTXIsExist() throws RemoteException;

    int analogTvTTXSetCountry(int i) throws RemoteException;

    int analogTvTTXSetKeycode(int i) throws RemoteException;

    int analogTvTTXStart() throws RemoteException;

    int analogTvTTXStop() throws RemoteException;

    int analogTvTunerControl(int i, int i2) throws RemoteException;

    int analogTvTunerDeInit(int i) throws RemoteException;

    int analogTvTunerExitScan() throws RemoteException;

    int analogTvTunerInit(int i) throws RemoteException;

    int analogTvTunerInitScan() throws RemoteException;

    int analogTvTunerIsScanning() throws RemoteException;

    int analogTvTunerStartAsync(AnalogChannelInfo analogChannelInfo) throws RemoteException;

    int analogTvTunerStartAutoScan(int i, int i2) throws RemoteException;

    int analogTvTunerStartFineTune(int i) throws RemoteException;

    int analogTvTunerStartNull(boolean z) throws RemoteException;

    int analogTvTunerStartRangeScan(int i, int i2, int i3) throws RemoteException;

    int analogTvTunerStop(int i) throws RemoteException;

    int analogTvTunerTryLockAsync(AtvLockInfo atvLockInfo) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

    void getTvConfiger(getTvConfigerCallback gettvconfigercallback) throws RemoteException;

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

    void setCallback(IBtvServerCallback iBtvServerCallback) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    int tunerControl(int i, int i2) throws RemoteException;

    int tunerDeInit(int i) throws RemoteException;

    SignalInfo tunerGetSignalInfo() throws RemoteException;

    int tunerInit(int i) throws RemoteException;

    int tunerSearchEPGAsync(EPGSearchParam ePGSearchParam) throws RemoteException;

    int tunerStartAsync(DtvChannelInfo dtvChannelInfo) throws RemoteException;

    int tunerStop() throws RemoteException;

    int tunerSuspend(boolean z) throws RemoteException;

    int tunerTryLockAsync(DtvLockInfo dtvLockInfo) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    static IBtvServer asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface(kInterfaceName);
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IBtvServer)) {
            return (IBtvServer) iHwInterfaceQueryLocalInterface;
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

    static IBtvServer castFrom(IHwInterface iHwInterface) {
        if (iHwInterface == null) {
            return null;
        }
        return asInterface(iHwInterface.asBinder());
    }

    static IBtvServer getService(String str, boolean z) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str, z));
    }

    static IBtvServer getService(boolean z) throws RemoteException {
        return getService("default", z);
    }

    static IBtvServer getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str));
    }

    static IBtvServer getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IBtvServer {
        private IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.aw.hardware.btvserver@1.0::IBtvServer]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public void setCallback(IBtvServerCallback iBtvServerCallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeStrongBinder(iBtvServerCallback == null ? null : iBtvServerCallback.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int tunerSuspend(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeBool(z);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int tunerInit(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int tunerDeInit(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int tunerTryLockAsync(DtvLockInfo dtvLockInfo) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            dtvLockInfo.writeToParcel(hwParcel);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int tunerStartAsync(DtvChannelInfo dtvChannelInfo) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            dtvChannelInfo.writeToParcel(hwParcel);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int tunerStop() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public SignalInfo tunerGetSignalInfo() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(8, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                SignalInfo signalInfo = new SignalInfo();
                signalInfo.readFromParcel(hwParcel2);
                return signalInfo;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int tunerSearchEPGAsync(EPGSearchParam ePGSearchParam) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            ePGSearchParam.writeToParcel(hwParcel);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int tunerControl(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerInit(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerDeInit(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerTryLockAsync(AtvLockInfo atvLockInfo) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            atvLockInfo.writeToParcel(hwParcel);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerStartAsync(AnalogChannelInfo analogChannelInfo) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            analogChannelInfo.writeToParcel(hwParcel);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerStartNull(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeBool(z);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerStartFineTune(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerStop(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerControl(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerInitScan() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerStartAutoScan(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerStartRangeScan(int i, int i2, int i3) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(21, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerExitScan() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTunerIsScanning() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvEnableAfc(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeBool(z);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvSetSiganlStd(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvSetEnableSnowScreen(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTTXStart() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTTXStop() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTTXSetKeycode(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTTXIsActive() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(30, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTTXIsExist() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvTTXSetCountry(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public int analogTvSetBlackScreen(boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            hwParcel.writeBool(z);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer
        public void getTvConfiger(getTvConfigerCallback gettvconfigercallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvServer.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(34, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gettvconfigercallback.onValues(hwParcel2.readInt32(), IBtvConfiger.asInterface(hwParcel2.readStrongBinder()));
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException {
            return this.mRemote.linkToDeath(deathRecipient, j);
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(deathRecipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IBtvServer {
        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IBtvServer.kInterfaceName;
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IBtvServer.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo debugInfo = new DebugInfo();
            debugInfo.pid = HidlSupport.getPidIfSharable();
            debugInfo.ptr = 0L;
            debugInfo.arch = 0;
            return debugInfo;
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        public IHwInterface queryLocalInterface(String str) {
            if (IBtvServer.kInterfaceName.equals(str)) {
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
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            setCallback(IBtvServerCallback.asInterface(hwParcel.readStrongBinder()));
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 2:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iTunerSuspend = tunerSuspend(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iTunerSuspend);
                            hwParcel2.send();
                            return;
                        case 3:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iTunerInit = tunerInit(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iTunerInit);
                            hwParcel2.send();
                            return;
                        case 4:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iTunerDeInit = tunerDeInit(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iTunerDeInit);
                            hwParcel2.send();
                            return;
                        case 5:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            DtvLockInfo dtvLockInfo = new DtvLockInfo();
                            dtvLockInfo.readFromParcel(hwParcel);
                            int iTunerTryLockAsync = tunerTryLockAsync(dtvLockInfo);
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iTunerTryLockAsync);
                            hwParcel2.send();
                            return;
                        case 6:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            DtvChannelInfo dtvChannelInfo = new DtvChannelInfo();
                            dtvChannelInfo.readFromParcel(hwParcel);
                            int iTunerStartAsync = tunerStartAsync(dtvChannelInfo);
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iTunerStartAsync);
                            hwParcel2.send();
                            return;
                        case 7:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iTunerStop = tunerStop();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iTunerStop);
                            hwParcel2.send();
                            return;
                        case 8:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            SignalInfo signalInfoTunerGetSignalInfo = tunerGetSignalInfo();
                            hwParcel2.writeStatus(0);
                            signalInfoTunerGetSignalInfo.writeToParcel(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 9:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            EPGSearchParam ePGSearchParam = new EPGSearchParam();
                            ePGSearchParam.readFromParcel(hwParcel);
                            int iTunerSearchEPGAsync = tunerSearchEPGAsync(ePGSearchParam);
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iTunerSearchEPGAsync);
                            hwParcel2.send();
                            return;
                        case 10:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iTunerControl = tunerControl(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iTunerControl);
                            hwParcel2.send();
                            return;
                        case 11:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTunerInit = analogTvTunerInit(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerInit);
                            hwParcel2.send();
                            return;
                        case 12:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTunerDeInit = analogTvTunerDeInit(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerDeInit);
                            hwParcel2.send();
                            return;
                        case 13:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            AtvLockInfo atvLockInfo = new AtvLockInfo();
                            atvLockInfo.readFromParcel(hwParcel);
                            int iAnalogTvTunerTryLockAsync = analogTvTunerTryLockAsync(atvLockInfo);
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerTryLockAsync);
                            hwParcel2.send();
                            return;
                        case 14:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            AnalogChannelInfo analogChannelInfo = new AnalogChannelInfo();
                            analogChannelInfo.readFromParcel(hwParcel);
                            int iAnalogTvTunerStartAsync = analogTvTunerStartAsync(analogChannelInfo);
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerStartAsync);
                            hwParcel2.send();
                            return;
                        case 15:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTunerStartNull = analogTvTunerStartNull(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerStartNull);
                            hwParcel2.send();
                            return;
                        case 16:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTunerStartFineTune = analogTvTunerStartFineTune(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerStartFineTune);
                            hwParcel2.send();
                            return;
                        case 17:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTunerStop = analogTvTunerStop(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerStop);
                            hwParcel2.send();
                            return;
                        case 18:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTunerControl = analogTvTunerControl(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerControl);
                            hwParcel2.send();
                            return;
                        case 19:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTunerInitScan = analogTvTunerInitScan();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerInitScan);
                            hwParcel2.send();
                            return;
                        case 20:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTunerStartAutoScan = analogTvTunerStartAutoScan(hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerStartAutoScan);
                            hwParcel2.send();
                            return;
                        case 21:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTunerStartRangeScan = analogTvTunerStartRangeScan(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerStartRangeScan);
                            hwParcel2.send();
                            return;
                        case 22:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTunerExitScan = analogTvTunerExitScan();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerExitScan);
                            hwParcel2.send();
                            return;
                        case 23:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTunerIsScanning = analogTvTunerIsScanning();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTunerIsScanning);
                            hwParcel2.send();
                            return;
                        case 24:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvEnableAfc = analogTvEnableAfc(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvEnableAfc);
                            hwParcel2.send();
                            return;
                        case 25:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvSetSiganlStd = analogTvSetSiganlStd(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvSetSiganlStd);
                            hwParcel2.send();
                            return;
                        case 26:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvSetEnableSnowScreen = analogTvSetEnableSnowScreen(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvSetEnableSnowScreen);
                            hwParcel2.send();
                            return;
                        case 27:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTTXStart = analogTvTTXStart();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTTXStart);
                            hwParcel2.send();
                            return;
                        case 28:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTTXStop = analogTvTTXStop();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTTXStop);
                            hwParcel2.send();
                            return;
                        case 29:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTTXSetKeycode = analogTvTTXSetKeycode(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTTXSetKeycode);
                            hwParcel2.send();
                            return;
                        case 30:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTTXIsActive = analogTvTTXIsActive();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTTXIsActive);
                            hwParcel2.send();
                            return;
                        case 31:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTTXIsExist = analogTvTTXIsExist();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTTXIsExist);
                            hwParcel2.send();
                            return;
                        case 32:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvTTXSetCountry = analogTvTTXSetCountry(hwParcel.readInt32());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvTTXSetCountry);
                            hwParcel2.send();
                            return;
                        case 33:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            int iAnalogTvSetBlackScreen = analogTvSetBlackScreen(hwParcel.readBool());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(iAnalogTvSetBlackScreen);
                            hwParcel2.send();
                            return;
                        case 34:
                            hwParcel.enforceInterface(IBtvServer.kInterfaceName);
                            getTvConfiger(new getTvConfigerCallback() { // from class: vendor.aw.hardware.btvserver.V1_0.IBtvServer.Stub.1
                                @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer.getTvConfigerCallback
                                public void onValues(int i4, IBtvConfiger iBtvConfiger) {
                                    hwParcel2.writeStatus(0);
                                    hwParcel2.writeInt32(i4);
                                    hwParcel2.writeStrongBinder(iBtvConfiger == null ? null : iBtvConfiger.asBinder());
                                    hwParcel2.send();
                                }
                            });
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
