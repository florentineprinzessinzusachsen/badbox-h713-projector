package vendor.aw.hardware.btvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class EPGSearchParam {
    public int searchFreq = 0;
    public int searchCount = 0;
    public int[] serviceID = new int[32];

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != EPGSearchParam.class) {
            return false;
        }
        EPGSearchParam ePGSearchParam = (EPGSearchParam) obj;
        return this.searchFreq == ePGSearchParam.searchFreq && this.searchCount == ePGSearchParam.searchCount && HidlSupport.deepEquals(this.serviceID, ePGSearchParam.serviceID);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.searchFreq))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.searchCount))), Integer.valueOf(HidlSupport.deepHashCode(this.serviceID)));
    }

    public final String toString() {
        return "{.searchFreq = " + this.searchFreq + ", .searchCount = " + this.searchCount + ", .serviceID = " + Arrays.toString(this.serviceID) + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(136L), 0L);
    }

    public static final ArrayList<EPGSearchParam> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<EPGSearchParam> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 136, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            EPGSearchParam ePGSearchParam = new EPGSearchParam();
            ePGSearchParam.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 136);
            arrayList.add(ePGSearchParam);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.searchFreq = hwBlob.getInt32(0 + j);
        this.searchCount = hwBlob.getInt32(4 + j);
        hwBlob.copyToInt32Array(j + 8, this.serviceID, 32);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(136);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<EPGSearchParam> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 136);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 136);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.searchFreq);
        hwBlob.putInt32(4 + j, this.searchCount);
        long j2 = j + 8;
        int[] iArr = this.serviceID;
        if (iArr == null || iArr.length != 32) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt32Array(j2, iArr);
    }
}
