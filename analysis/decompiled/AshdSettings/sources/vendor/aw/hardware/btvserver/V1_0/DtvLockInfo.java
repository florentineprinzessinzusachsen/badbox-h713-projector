package vendor.aw.hardware.btvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class DtvLockInfo {
    public int frequency = 0;
    public int symbolRate = 0;
    public int modulation = 0;
    public int bandwidth = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != DtvLockInfo.class) {
            return false;
        }
        DtvLockInfo dtvLockInfo = (DtvLockInfo) obj;
        return this.frequency == dtvLockInfo.frequency && this.symbolRate == dtvLockInfo.symbolRate && this.modulation == dtvLockInfo.modulation && this.bandwidth == dtvLockInfo.bandwidth;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.frequency))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.symbolRate))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.modulation))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.bandwidth))));
    }

    public final String toString() {
        return "{.frequency = " + this.frequency + ", .symbolRate = " + this.symbolRate + ", .modulation = " + this.modulation + ", .bandwidth = " + this.bandwidth + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(16L), 0L);
    }

    public static final ArrayList<DtvLockInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<DtvLockInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 16, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            DtvLockInfo dtvLockInfo = new DtvLockInfo();
            dtvLockInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 16);
            arrayList.add(dtvLockInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.frequency = hwBlob.getInt32(0 + j);
        this.symbolRate = hwBlob.getInt32(4 + j);
        this.modulation = hwBlob.getInt32(8 + j);
        this.bandwidth = hwBlob.getInt32(j + 12);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(16);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<DtvLockInfo> arrayList) {
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
        hwBlob.putInt32(0 + j, this.frequency);
        hwBlob.putInt32(4 + j, this.symbolRate);
        hwBlob.putInt32(8 + j, this.modulation);
        hwBlob.putInt32(j + 12, this.bandwidth);
    }
}
