package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ScreenWin {
    public int h_start = 0;
    public int h_size = 0;
    public int v_start = 0;
    public int v_size = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != ScreenWin.class) {
            return false;
        }
        ScreenWin screenWin = (ScreenWin) obj;
        return this.h_start == screenWin.h_start && this.h_size == screenWin.h_size && this.v_start == screenWin.v_start && this.v_size == screenWin.v_size;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.h_start))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.h_size))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.v_start))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.v_size))));
    }

    public final String toString() {
        return "{.h_start = " + this.h_start + ", .h_size = " + this.h_size + ", .v_start = " + this.v_start + ", .v_size = " + this.v_size + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(16L), 0L);
    }

    public static final ArrayList<ScreenWin> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<ScreenWin> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 16, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            ScreenWin screenWin = new ScreenWin();
            screenWin.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 16);
            arrayList.add(screenWin);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.h_start = hwBlob.getInt32(0 + j);
        this.h_size = hwBlob.getInt32(4 + j);
        this.v_start = hwBlob.getInt32(8 + j);
        this.v_size = hwBlob.getInt32(j + 12);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(16);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<ScreenWin> arrayList) {
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
        hwBlob.putInt32(0 + j, this.h_start);
        hwBlob.putInt32(4 + j, this.h_size);
        hwBlob.putInt32(8 + j, this.v_start);
        hwBlob.putInt32(j + 12, this.v_size);
    }
}
