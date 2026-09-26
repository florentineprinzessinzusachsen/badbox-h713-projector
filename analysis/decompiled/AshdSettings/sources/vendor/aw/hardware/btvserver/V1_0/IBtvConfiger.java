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
public interface IBtvConfiger extends IBase {
    public static final String kInterfaceName = "vendor.aw.hardware.btvserver@1.0::IBtvConfiger";

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

    int getTvAtvColorSys(String str) throws RemoteException;

    int getTvAtvMaxFreq(String str) throws RemoteException;

    int getTvAtvMinFreq(String str) throws RemoteException;

    ArrayList<TvLockInfo> getTvAtvScanFreqList() throws RemoteException;

    int getTvAtvScanStep(String str) throws RemoteException;

    int getTvAtvSoundSys(String str) throws RemoteException;

    int getTvAtvSupport(String str) throws RemoteException;

    String getTvDefaultCountryWithCode() throws RemoteException;

    String getTvDefaultCountryWithName() throws RemoteException;

    ArrayList<TvLockInfo> getTvDtvScanFreqList() throws RemoteException;

    int getTvDtvStandard(String str) throws RemoteException;

    int getTvDtvSupport(String str) throws RemoteException;

    ArrayList<String> getTvSupportCountryList() throws RemoteException;

    int getTvTunerStandardType(String str) throws RemoteException;

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

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    static IBtvConfiger asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface(kInterfaceName);
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IBtvConfiger)) {
            return (IBtvConfiger) iHwInterfaceQueryLocalInterface;
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

    static IBtvConfiger castFrom(IHwInterface iHwInterface) {
        if (iHwInterface == null) {
            return null;
        }
        return asInterface(iHwInterface.asBinder());
    }

    static IBtvConfiger getService(String str, boolean z) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str, z));
    }

    static IBtvConfiger getService(boolean z) throws RemoteException {
        return getService("default", z);
    }

    static IBtvConfiger getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str));
    }

    static IBtvConfiger getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IBtvConfiger {
        private IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.aw.hardware.btvserver@1.0::IBtvConfiger]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public ArrayList<String> getTvSupportCountryList() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public String getTvDefaultCountryWithCode() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public String getTvDefaultCountryWithName() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public int getTvAtvSupport(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            hwParcel.writeString(str);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public int getTvAtvColorSys(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            hwParcel.writeString(str);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public int getTvAtvSoundSys(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            hwParcel.writeString(str);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public int getTvAtvMinFreq(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            hwParcel.writeString(str);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public int getTvAtvMaxFreq(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            hwParcel.writeString(str);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public int getTvTunerStandardType(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            hwParcel.writeString(str);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public int getTvAtvScanStep(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            hwParcel.writeString(str);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public int getTvDtvSupport(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            hwParcel.writeString(str);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public int getTvDtvStandard(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            hwParcel.writeString(str);
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public ArrayList<TvLockInfo> getTvAtvScanFreqList() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(13, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return TvLockInfo.readVectorFromParcel(hwParcel2);
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger
        public ArrayList<TvLockInfo> getTvDtvScanFreqList() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBtvConfiger.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(14, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return TvLockInfo.readVectorFromParcel(hwParcel2);
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException {
            return this.mRemote.linkToDeath(deathRecipient, j);
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
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

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(deathRecipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IBtvConfiger {
        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IBtvConfiger.kInterfaceName;
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IBtvConfiger.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo debugInfo = new DebugInfo();
            debugInfo.pid = HidlSupport.getPidIfSharable();
            debugInfo.ptr = 0L;
            debugInfo.arch = 0;
            return debugInfo;
        }

        @Override // vendor.aw.hardware.btvserver.V1_0.IBtvConfiger, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        public IHwInterface queryLocalInterface(String str) {
            if (IBtvConfiger.kInterfaceName.equals(str)) {
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

        public void onTransact(int i, HwParcel hwParcel, HwParcel hwParcel2, int i2) throws RemoteException {
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
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            ArrayList<String> tvSupportCountryList = getTvSupportCountryList();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeStringVector(tvSupportCountryList);
                            hwParcel2.send();
                            return;
                        case 2:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            String tvDefaultCountryWithCode = getTvDefaultCountryWithCode();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(tvDefaultCountryWithCode);
                            hwParcel2.send();
                            return;
                        case 3:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            String tvDefaultCountryWithName = getTvDefaultCountryWithName();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(tvDefaultCountryWithName);
                            hwParcel2.send();
                            return;
                        case 4:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            int tvAtvSupport = getTvAtvSupport(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(tvAtvSupport);
                            hwParcel2.send();
                            return;
                        case 5:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            int tvAtvColorSys = getTvAtvColorSys(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(tvAtvColorSys);
                            hwParcel2.send();
                            return;
                        case 6:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            int tvAtvSoundSys = getTvAtvSoundSys(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(tvAtvSoundSys);
                            hwParcel2.send();
                            return;
                        case 7:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            int tvAtvMinFreq = getTvAtvMinFreq(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(tvAtvMinFreq);
                            hwParcel2.send();
                            return;
                        case 8:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            int tvAtvMaxFreq = getTvAtvMaxFreq(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(tvAtvMaxFreq);
                            hwParcel2.send();
                            return;
                        case 9:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            int tvTunerStandardType = getTvTunerStandardType(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(tvTunerStandardType);
                            hwParcel2.send();
                            return;
                        case 10:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            int tvAtvScanStep = getTvAtvScanStep(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(tvAtvScanStep);
                            hwParcel2.send();
                            return;
                        case 11:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            int tvDtvSupport = getTvDtvSupport(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(tvDtvSupport);
                            hwParcel2.send();
                            return;
                        case 12:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            int tvDtvStandard = getTvDtvStandard(hwParcel.readString());
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(tvDtvStandard);
                            hwParcel2.send();
                            return;
                        case 13:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            ArrayList<TvLockInfo> tvAtvScanFreqList = getTvAtvScanFreqList();
                            hwParcel2.writeStatus(0);
                            TvLockInfo.writeVectorToParcel(hwParcel2, tvAtvScanFreqList);
                            hwParcel2.send();
                            return;
                        case 14:
                            hwParcel.enforceInterface(IBtvConfiger.kInterfaceName);
                            ArrayList<TvLockInfo> tvDtvScanFreqList = getTvDtvScanFreqList();
                            hwParcel2.writeStatus(0);
                            TvLockInfo.writeVectorToParcel(hwParcel2, tvDtvScanFreqList);
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
