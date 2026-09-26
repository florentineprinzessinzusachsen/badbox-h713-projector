package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class McuCommParam_t {
    public int BufLen = 0;
    public String Buffer = new String();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != McuCommParam_t.class) {
            return false;
        }
        McuCommParam_t mcuCommParam_t = (McuCommParam_t) obj;
        return this.BufLen == mcuCommParam_t.BufLen && HidlSupport.deepEquals(this.Buffer, mcuCommParam_t.Buffer);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.BufLen))), Integer.valueOf(HidlSupport.deepHashCode(this.Buffer)));
    }

    public final String toString() {
        return "{.BufLen = " + this.BufLen + ", .Buffer = " + this.Buffer + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(24L), 0L);
    }

    public static final ArrayList<McuCommParam_t> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<McuCommParam_t> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 24, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            McuCommParam_t mcuCommParam_t = new McuCommParam_t();
            mcuCommParam_t.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 24);
            arrayList.add(mcuCommParam_t);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.BufLen = hwBlob.getInt32(j + 0);
        long j2 = j + 8;
        this.Buffer = hwBlob.getString(j2);
        hwParcel.readEmbeddedBuffer(this.Buffer.getBytes().length + 1, hwBlob.handle(), j2 + 0, false);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(24);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<McuCommParam_t> arrayList) {
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
        hwBlob.putInt32(0 + j, this.BufLen);
        hwBlob.putString(j + 8, this.Buffer);
    }
}
