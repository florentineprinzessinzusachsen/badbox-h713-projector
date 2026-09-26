package vendor.display.config.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class SNRInfo {
    public int mode = 0;
    public int y = 0;
    public int u = 0;
    public int v = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != SNRInfo.class) {
            return false;
        }
        SNRInfo sNRInfo = (SNRInfo) obj;
        return this.mode == sNRInfo.mode && this.y == sNRInfo.y && this.u == sNRInfo.u && this.v == sNRInfo.v;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.mode))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.y))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.u))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.v))));
    }

    public final String toString() {
        return "{.mode = " + SNRFeatureMode.toString(this.mode) + ", .y = " + this.y + ", .u = " + this.u + ", .v = " + this.v + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(16L), 0L);
    }

    public static final ArrayList<SNRInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<SNRInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 16, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            SNRInfo sNRInfo = new SNRInfo();
            sNRInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 16);
            arrayList.add(sNRInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.mode = hwBlob.getInt32(0 + j);
        this.y = hwBlob.getInt32(4 + j);
        this.u = hwBlob.getInt32(8 + j);
        this.v = hwBlob.getInt32(j + 12);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(16);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<SNRInfo> arrayList) {
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
        hwBlob.putInt32(0 + j, this.mode);
        hwBlob.putInt32(4 + j, this.y);
        hwBlob.putInt32(8 + j, this.u);
        hwBlob.putInt32(j + 12, this.v);
    }
}
