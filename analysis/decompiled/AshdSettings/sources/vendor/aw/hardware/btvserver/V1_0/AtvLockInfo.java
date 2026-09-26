package vendor.aw.hardware.btvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class AtvLockInfo {
    public int frequency = 0;
    public int video_std = 0;
    public int audio_std = 0;
    public int tuner_std = 0;
    public int channel_id = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != AtvLockInfo.class) {
            return false;
        }
        AtvLockInfo atvLockInfo = (AtvLockInfo) obj;
        return this.frequency == atvLockInfo.frequency && this.video_std == atvLockInfo.video_std && this.audio_std == atvLockInfo.audio_std && this.tuner_std == atvLockInfo.tuner_std && this.channel_id == atvLockInfo.channel_id;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.frequency))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.video_std))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.audio_std))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.tuner_std))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.channel_id))));
    }

    public final String toString() {
        return "{.frequency = " + this.frequency + ", .video_std = " + this.video_std + ", .audio_std = " + this.audio_std + ", .tuner_std = " + this.tuner_std + ", .channel_id = " + this.channel_id + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(20L), 0L);
    }

    public static final ArrayList<AtvLockInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<AtvLockInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 20, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            AtvLockInfo atvLockInfo = new AtvLockInfo();
            atvLockInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 20);
            arrayList.add(atvLockInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.frequency = hwBlob.getInt32(0 + j);
        this.video_std = hwBlob.getInt32(4 + j);
        this.audio_std = hwBlob.getInt32(8 + j);
        this.tuner_std = hwBlob.getInt32(12 + j);
        this.channel_id = hwBlob.getInt32(j + 16);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(20);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<AtvLockInfo> arrayList) {
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
        hwBlob.putInt32(0 + j, this.frequency);
        hwBlob.putInt32(4 + j, this.video_std);
        hwBlob.putInt32(8 + j, this.audio_std);
        hwBlob.putInt32(12 + j, this.tuner_std);
        hwBlob.putInt32(j + 16, this.channel_id);
    }
}
