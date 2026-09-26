package android.hardware.audio.common.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class AudioPortConfigDeviceExt {
    public int hwModule = 0;
    public int type = 0;
    public byte[] address = new byte[32];

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != AudioPortConfigDeviceExt.class) {
            return false;
        }
        AudioPortConfigDeviceExt audioPortConfigDeviceExt = (AudioPortConfigDeviceExt) obj;
        return this.hwModule == audioPortConfigDeviceExt.hwModule && this.type == audioPortConfigDeviceExt.type && HidlSupport.deepEquals(this.address, audioPortConfigDeviceExt.address);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.hwModule))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(this.address)));
    }

    public final String toString() {
        return "{.hwModule = " + this.hwModule + ", .type = " + AudioDevice.toString(this.type) + ", .address = " + Arrays.toString(this.address) + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(40L), 0L);
    }

    public static final ArrayList<AudioPortConfigDeviceExt> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<AudioPortConfigDeviceExt> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 40, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            AudioPortConfigDeviceExt audioPortConfigDeviceExt = new AudioPortConfigDeviceExt();
            audioPortConfigDeviceExt.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 40);
            arrayList.add(audioPortConfigDeviceExt);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.hwModule = hwBlob.getInt32(0 + j);
        this.type = hwBlob.getInt32(4 + j);
        hwBlob.copyToInt8Array(j + 8, this.address, 32);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(40);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<AudioPortConfigDeviceExt> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 40);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 40);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.hwModule);
        hwBlob.putInt32(4 + j, this.type);
        long j2 = j + 8;
        byte[] bArr = this.address;
        if (bArr == null || bArr.length != 32) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt8Array(j2, bArr);
    }
}
