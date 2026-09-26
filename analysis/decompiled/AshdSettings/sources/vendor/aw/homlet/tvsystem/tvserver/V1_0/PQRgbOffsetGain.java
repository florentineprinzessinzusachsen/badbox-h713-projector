package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class PQRgbOffsetGain {
    public int r_offset = 0;
    public int g_offset = 0;
    public int b_offset = 0;
    public int r_gain = 0;
    public int g_gain = 0;
    public int b_gain = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != PQRgbOffsetGain.class) {
            return false;
        }
        PQRgbOffsetGain pQRgbOffsetGain = (PQRgbOffsetGain) obj;
        return this.r_offset == pQRgbOffsetGain.r_offset && this.g_offset == pQRgbOffsetGain.g_offset && this.b_offset == pQRgbOffsetGain.b_offset && this.r_gain == pQRgbOffsetGain.r_gain && this.g_gain == pQRgbOffsetGain.g_gain && this.b_gain == pQRgbOffsetGain.b_gain;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.r_offset))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.g_offset))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.b_offset))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.r_gain))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.g_gain))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.b_gain))));
    }

    public final String toString() {
        return "{.r_offset = " + this.r_offset + ", .g_offset = " + this.g_offset + ", .b_offset = " + this.b_offset + ", .r_gain = " + this.r_gain + ", .g_gain = " + this.g_gain + ", .b_gain = " + this.b_gain + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(24L), 0L);
    }

    public static final ArrayList<PQRgbOffsetGain> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<PQRgbOffsetGain> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 24, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            PQRgbOffsetGain pQRgbOffsetGain = new PQRgbOffsetGain();
            pQRgbOffsetGain.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 24);
            arrayList.add(pQRgbOffsetGain);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.r_offset = hwBlob.getInt32(0 + j);
        this.g_offset = hwBlob.getInt32(4 + j);
        this.b_offset = hwBlob.getInt32(8 + j);
        this.r_gain = hwBlob.getInt32(12 + j);
        this.g_gain = hwBlob.getInt32(16 + j);
        this.b_gain = hwBlob.getInt32(j + 20);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(24);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<PQRgbOffsetGain> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 24);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 24);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.r_offset);
        hwBlob.putInt32(4 + j, this.g_offset);
        hwBlob.putInt32(8 + j, this.b_offset);
        hwBlob.putInt32(12 + j, this.r_gain);
        hwBlob.putInt32(16 + j, this.g_gain);
        hwBlob.putInt32(j + 20, this.b_gain);
    }
}
