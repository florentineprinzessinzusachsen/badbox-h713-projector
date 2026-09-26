package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class StreamInfo {
    public int frequency = 0;
    public int video_pid = 0;
    public int audio_size = 0;
    public int[] audio_pid = new int[32];
    public int pcr_pid = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != StreamInfo.class) {
            return false;
        }
        StreamInfo streamInfo = (StreamInfo) obj;
        return this.frequency == streamInfo.frequency && this.video_pid == streamInfo.video_pid && this.audio_size == streamInfo.audio_size && HidlSupport.deepEquals(this.audio_pid, streamInfo.audio_pid) && this.pcr_pid == streamInfo.pcr_pid;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.frequency))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.video_pid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.audio_size))), Integer.valueOf(HidlSupport.deepHashCode(this.audio_pid)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pcr_pid))));
    }

    public final String toString() {
        return "{.frequency = " + this.frequency + ", .video_pid = " + this.video_pid + ", .audio_size = " + this.audio_size + ", .audio_pid = " + Arrays.toString(this.audio_pid) + ", .pcr_pid = " + this.pcr_pid + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(144L), 0L);
    }

    public static final ArrayList<StreamInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<StreamInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * TvSignalID.SIGNALID_SCART_MAC640480, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            StreamInfo streamInfo = new StreamInfo();
            streamInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * TvSignalID.SIGNALID_SCART_MAC640480);
            arrayList.add(streamInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.frequency = hwBlob.getInt32(0 + j);
        this.video_pid = hwBlob.getInt32(4 + j);
        this.audio_size = hwBlob.getInt32(8 + j);
        hwBlob.copyToInt32Array(12 + j, this.audio_pid, 32);
        this.pcr_pid = hwBlob.getInt32(j + 140);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(TvSignalID.SIGNALID_SCART_MAC640480);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<StreamInfo> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * TvSignalID.SIGNALID_SCART_MAC640480);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * TvSignalID.SIGNALID_SCART_MAC640480);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.frequency);
        hwBlob.putInt32(4 + j, this.video_pid);
        hwBlob.putInt32(8 + j, this.audio_size);
        long j2 = 12 + j;
        int[] iArr = this.audio_pid;
        if (iArr == null || iArr.length != 32) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt32Array(j2, iArr);
        hwBlob.putInt32(j + 140, this.pcr_pid);
    }
}
