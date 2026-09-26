package android.hardware.tv.input.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class TvInputEvent {
    public int type = 0;
    public TvInputDeviceInfo deviceInfo = new TvInputDeviceInfo();
    public TvSignalInfo tvSignalInfo = new TvSignalInfo();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != TvInputEvent.class) {
            return false;
        }
        TvInputEvent tvInputEvent = (TvInputEvent) obj;
        return this.type == tvInputEvent.type && HidlSupport.deepEquals(this.deviceInfo, tvInputEvent.deviceInfo) && HidlSupport.deepEquals(this.tvSignalInfo, tvInputEvent.tvSignalInfo);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(this.deviceInfo)), Integer.valueOf(HidlSupport.deepHashCode(this.tvSignalInfo)));
    }

    public final String toString() {
        return "{.type = " + TvInputEventType.toString(this.type) + ", .deviceInfo = " + this.deviceInfo + ", .tvSignalInfo = " + this.tvSignalInfo + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(92L), 0L);
    }

    public static final ArrayList<TvInputEvent> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<TvInputEvent> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 92, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            TvInputEvent tvInputEvent = new TvInputEvent();
            tvInputEvent.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 92);
            arrayList.add(tvInputEvent);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.type = hwBlob.getInt32(0 + j);
        this.deviceInfo.readEmbeddedFromParcel(hwParcel, hwBlob, 4 + j);
        this.tvSignalInfo.readEmbeddedFromParcel(hwParcel, hwBlob, j + 56);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(92);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<TvInputEvent> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 92);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 92);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.type);
        this.deviceInfo.writeEmbeddedToBlob(hwBlob, 4 + j);
        this.tvSignalInfo.writeEmbeddedToBlob(hwBlob, j + 56);
    }
}
