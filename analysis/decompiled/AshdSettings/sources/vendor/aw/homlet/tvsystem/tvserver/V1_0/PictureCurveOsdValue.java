package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class PictureCurveOsdValue {
    public int v0 = 0;
    public int v25 = 0;
    public int v50 = 0;
    public int v75 = 0;
    public int v100 = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != PictureCurveOsdValue.class) {
            return false;
        }
        PictureCurveOsdValue pictureCurveOsdValue = (PictureCurveOsdValue) obj;
        return this.v0 == pictureCurveOsdValue.v0 && this.v25 == pictureCurveOsdValue.v25 && this.v50 == pictureCurveOsdValue.v50 && this.v75 == pictureCurveOsdValue.v75 && this.v100 == pictureCurveOsdValue.v100;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.v0))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.v25))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.v50))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.v75))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.v100))));
    }

    public final String toString() {
        return "{.v0 = " + this.v0 + ", .v25 = " + this.v25 + ", .v50 = " + this.v50 + ", .v75 = " + this.v75 + ", .v100 = " + this.v100 + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(20L), 0L);
    }

    public static final ArrayList<PictureCurveOsdValue> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<PictureCurveOsdValue> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 20, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            PictureCurveOsdValue pictureCurveOsdValue = new PictureCurveOsdValue();
            pictureCurveOsdValue.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 20);
            arrayList.add(pictureCurveOsdValue);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.v0 = hwBlob.getInt32(0 + j);
        this.v25 = hwBlob.getInt32(4 + j);
        this.v50 = hwBlob.getInt32(8 + j);
        this.v75 = hwBlob.getInt32(12 + j);
        this.v100 = hwBlob.getInt32(j + 16);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(20);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<PictureCurveOsdValue> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 20);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 20);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.v0);
        hwBlob.putInt32(4 + j, this.v25);
        hwBlob.putInt32(8 + j, this.v50);
        hwBlob.putInt32(12 + j, this.v75);
        hwBlob.putInt32(j + 16, this.v100);
    }
}
