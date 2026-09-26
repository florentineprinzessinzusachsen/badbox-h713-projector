package vendor.display.config.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ScreenMargin {
    public int left = 0;
    public int right = 0;
    public int top = 0;
    public int bottom = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != ScreenMargin.class) {
            return false;
        }
        ScreenMargin screenMargin = (ScreenMargin) obj;
        return this.left == screenMargin.left && this.right == screenMargin.right && this.top == screenMargin.top && this.bottom == screenMargin.bottom;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.left))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.right))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.top))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.bottom))));
    }

    public final String toString() {
        return "{.left = " + this.left + ", .right = " + this.right + ", .top = " + this.top + ", .bottom = " + this.bottom + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(16L), 0L);
    }

    public static final ArrayList<ScreenMargin> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<ScreenMargin> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 16, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            ScreenMargin screenMargin = new ScreenMargin();
            screenMargin.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 16);
            arrayList.add(screenMargin);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.left = hwBlob.getInt32(0 + j);
        this.right = hwBlob.getInt32(4 + j);
        this.top = hwBlob.getInt32(8 + j);
        this.bottom = hwBlob.getInt32(j + 12);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(16);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<ScreenMargin> arrayList) {
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
        hwBlob.putInt32(0 + j, this.left);
        hwBlob.putInt32(4 + j, this.right);
        hwBlob.putInt32(8 + j, this.top);
        hwBlob.putInt32(j + 12, this.bottom);
    }
}
