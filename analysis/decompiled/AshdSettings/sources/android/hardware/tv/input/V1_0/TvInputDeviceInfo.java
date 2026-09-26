package android.hardware.tv.input.V1_0;

import android.hardware.audio.common.V2_0.AudioDevice;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class TvInputDeviceInfo {
    public int deviceId = 0;
    public int type = 0;
    public int portId = 0;
    public int cableConnectionStatus = 0;
    public int audioType = 0;
    public byte[] audioAddress = new byte[32];

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != TvInputDeviceInfo.class) {
            return false;
        }
        TvInputDeviceInfo tvInputDeviceInfo = (TvInputDeviceInfo) obj;
        return this.deviceId == tvInputDeviceInfo.deviceId && this.type == tvInputDeviceInfo.type && this.portId == tvInputDeviceInfo.portId && this.cableConnectionStatus == tvInputDeviceInfo.cableConnectionStatus && this.audioType == tvInputDeviceInfo.audioType && HidlSupport.deepEquals(this.audioAddress, tvInputDeviceInfo.audioAddress);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.deviceId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.portId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cableConnectionStatus))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.audioType))), Integer.valueOf(HidlSupport.deepHashCode(this.audioAddress)));
    }

    public final String toString() {
        return "{.deviceId = " + this.deviceId + ", .type = " + TvInputType.toString(this.type) + ", .portId = " + this.portId + ", .cableConnectionStatus = " + CableConnectionStatus.toString(this.cableConnectionStatus) + ", .audioType = " + AudioDevice.toString(this.audioType) + ", .audioAddress = " + Arrays.toString(this.audioAddress) + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(52L), 0L);
    }

    public static final ArrayList<TvInputDeviceInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<TvInputDeviceInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 52, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            TvInputDeviceInfo tvInputDeviceInfo = new TvInputDeviceInfo();
            tvInputDeviceInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 52);
            arrayList.add(tvInputDeviceInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.deviceId = hwBlob.getInt32(0 + j);
        this.type = hwBlob.getInt32(4 + j);
        this.portId = hwBlob.getInt32(8 + j);
        this.cableConnectionStatus = hwBlob.getInt32(12 + j);
        this.audioType = hwBlob.getInt32(16 + j);
        hwBlob.copyToInt8Array(j + 20, this.audioAddress, 32);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(52);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<TvInputDeviceInfo> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 52);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 52);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.deviceId);
        hwBlob.putInt32(4 + j, this.type);
        hwBlob.putInt32(8 + j, this.portId);
        hwBlob.putInt32(12 + j, this.cableConnectionStatus);
        hwBlob.putInt32(16 + j, this.audioType);
        long j2 = j + 20;
        byte[] bArr = this.audioAddress;
        if (bArr == null || bArr.length != 32) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt8Array(j2, bArr);
    }
}
