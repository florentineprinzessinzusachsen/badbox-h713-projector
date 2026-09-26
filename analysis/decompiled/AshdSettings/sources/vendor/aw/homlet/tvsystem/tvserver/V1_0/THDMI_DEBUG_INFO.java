package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class THDMI_DEBUG_INFO {
    public byte[] HDCP14_AKSV = new byte[5];
    public byte[] HDCP14_BKSV = new byte[5];

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != THDMI_DEBUG_INFO.class) {
            return false;
        }
        THDMI_DEBUG_INFO thdmi_debug_info = (THDMI_DEBUG_INFO) obj;
        return HidlSupport.deepEquals(this.HDCP14_AKSV, thdmi_debug_info.HDCP14_AKSV) && HidlSupport.deepEquals(this.HDCP14_BKSV, thdmi_debug_info.HDCP14_BKSV);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.HDCP14_AKSV)), Integer.valueOf(HidlSupport.deepHashCode(this.HDCP14_BKSV)));
    }

    public final String toString() {
        return "{.HDCP14_AKSV = " + Arrays.toString(this.HDCP14_AKSV) + ", .HDCP14_BKSV = " + Arrays.toString(this.HDCP14_BKSV) + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(10L), 0L);
    }

    public static final ArrayList<THDMI_DEBUG_INFO> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<THDMI_DEBUG_INFO> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 10, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            THDMI_DEBUG_INFO thdmi_debug_info = new THDMI_DEBUG_INFO();
            thdmi_debug_info.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 10);
            arrayList.add(thdmi_debug_info);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        hwBlob.copyToInt8Array(0 + j, this.HDCP14_AKSV, 5);
        hwBlob.copyToInt8Array(j + 5, this.HDCP14_BKSV, 5);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(10);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<THDMI_DEBUG_INFO> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 10);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 10);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        long j2 = 0 + j;
        byte[] bArr = this.HDCP14_AKSV;
        if (bArr == null || bArr.length != 5) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt8Array(j2, bArr);
        long j3 = j + 5;
        byte[] bArr2 = this.HDCP14_BKSV;
        if (bArr2 == null || bArr2.length != 5) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt8Array(j3, bArr2);
    }
}
