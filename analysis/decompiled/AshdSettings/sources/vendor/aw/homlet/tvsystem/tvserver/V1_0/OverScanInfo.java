package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class OverScanInfo {
    public int cropUp = 0;
    public int cropDown = 0;
    public int cropLeft = 0;
    public int cropRight = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != OverScanInfo.class) {
            return false;
        }
        OverScanInfo overScanInfo = (OverScanInfo) obj;
        return this.cropUp == overScanInfo.cropUp && this.cropDown == overScanInfo.cropDown && this.cropLeft == overScanInfo.cropLeft && this.cropRight == overScanInfo.cropRight;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cropUp))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cropDown))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cropLeft))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cropRight))));
    }

    public final String toString() {
        return "{.cropUp = " + this.cropUp + ", .cropDown = " + this.cropDown + ", .cropLeft = " + this.cropLeft + ", .cropRight = " + this.cropRight + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(16L), 0L);
    }

    public static final ArrayList<OverScanInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<OverScanInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 16, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            OverScanInfo overScanInfo = new OverScanInfo();
            overScanInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 16);
            arrayList.add(overScanInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.cropUp = hwBlob.getInt32(0 + j);
        this.cropDown = hwBlob.getInt32(4 + j);
        this.cropLeft = hwBlob.getInt32(8 + j);
        this.cropRight = hwBlob.getInt32(j + 12);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(16);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<OverScanInfo> arrayList) {
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
        hwBlob.putInt32(0 + j, this.cropUp);
        hwBlob.putInt32(4 + j, this.cropDown);
        hwBlob.putInt32(8 + j, this.cropLeft);
        hwBlob.putInt32(j + 12, this.cropRight);
    }
}
