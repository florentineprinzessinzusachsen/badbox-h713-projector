package android.hardware.audio.common.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class Uuid {
    public int timeLow = 0;
    public short timeMid = 0;
    public short versionAndTimeHigh = 0;
    public short variantAndClockSeqHigh = 0;
    public byte[] node = new byte[6];

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != Uuid.class) {
            return false;
        }
        Uuid uuid = (Uuid) obj;
        return this.timeLow == uuid.timeLow && this.timeMid == uuid.timeMid && this.versionAndTimeHigh == uuid.versionAndTimeHigh && this.variantAndClockSeqHigh == uuid.variantAndClockSeqHigh && HidlSupport.deepEquals(this.node, uuid.node);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.timeLow))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.timeMid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.versionAndTimeHigh))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.variantAndClockSeqHigh))), Integer.valueOf(HidlSupport.deepHashCode(this.node)));
    }

    public final String toString() {
        return "{.timeLow = " + this.timeLow + ", .timeMid = " + ((int) this.timeMid) + ", .versionAndTimeHigh = " + ((int) this.versionAndTimeHigh) + ", .variantAndClockSeqHigh = " + ((int) this.variantAndClockSeqHigh) + ", .node = " + Arrays.toString(this.node) + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(16L), 0L);
    }

    public static final ArrayList<Uuid> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<Uuid> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 16, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            Uuid uuid = new Uuid();
            uuid.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 16);
            arrayList.add(uuid);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.timeLow = hwBlob.getInt32(0 + j);
        this.timeMid = hwBlob.getInt16(4 + j);
        this.versionAndTimeHigh = hwBlob.getInt16(6 + j);
        this.variantAndClockSeqHigh = hwBlob.getInt16(8 + j);
        hwBlob.copyToInt8Array(j + 10, this.node, 6);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(16);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<Uuid> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 16);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 16);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.timeLow);
        hwBlob.putInt16(4 + j, this.timeMid);
        hwBlob.putInt16(6 + j, this.versionAndTimeHigh);
        hwBlob.putInt16(8 + j, this.variantAndClockSeqHigh);
        long j2 = j + 10;
        byte[] bArr = this.node;
        if (bArr == null || bArr.length != 6) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt8Array(j2, bArr);
    }
}
