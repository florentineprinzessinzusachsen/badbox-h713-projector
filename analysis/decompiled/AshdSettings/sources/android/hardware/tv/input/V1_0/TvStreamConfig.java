package android.hardware.tv.input.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class TvStreamConfig {
    public int streamId = 0;
    public int maxVideoWidth = 0;
    public int maxVideoHeight = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != TvStreamConfig.class) {
            return false;
        }
        TvStreamConfig tvStreamConfig = (TvStreamConfig) obj;
        return this.streamId == tvStreamConfig.streamId && this.maxVideoWidth == tvStreamConfig.maxVideoWidth && this.maxVideoHeight == tvStreamConfig.maxVideoHeight;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.streamId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxVideoWidth))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxVideoHeight))));
    }

    public final String toString() {
        return "{.streamId = " + this.streamId + ", .maxVideoWidth = " + this.maxVideoWidth + ", .maxVideoHeight = " + this.maxVideoHeight + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(12L), 0L);
    }

    public static final ArrayList<TvStreamConfig> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<TvStreamConfig> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 12, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            TvStreamConfig tvStreamConfig = new TvStreamConfig();
            tvStreamConfig.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 12);
            arrayList.add(tvStreamConfig);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.streamId = hwBlob.getInt32(0 + j);
        this.maxVideoWidth = hwBlob.getInt32(4 + j);
        this.maxVideoHeight = hwBlob.getInt32(j + 8);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(12);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<TvStreamConfig> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 12);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 12);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.streamId);
        hwBlob.putInt32(4 + j, this.maxVideoWidth);
        hwBlob.putInt32(j + 8, this.maxVideoHeight);
    }
}
