package vendor.aw.hardware.btvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class AnalogChannelInfo {
    public int frequency = 0;
    public int org_frequency = 0;
    public int video_std = 0;
    public int audio_std = 0;
    public int channel_id = 0;
    public int finetune = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != AnalogChannelInfo.class) {
            return false;
        }
        AnalogChannelInfo analogChannelInfo = (AnalogChannelInfo) obj;
        return this.frequency == analogChannelInfo.frequency && this.org_frequency == analogChannelInfo.org_frequency && this.video_std == analogChannelInfo.video_std && this.audio_std == analogChannelInfo.audio_std && this.channel_id == analogChannelInfo.channel_id && this.finetune == analogChannelInfo.finetune;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.frequency))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.org_frequency))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.video_std))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.audio_std))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.channel_id))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.finetune))));
    }

    public final String toString() {
        return "{.frequency = " + this.frequency + ", .org_frequency = " + this.org_frequency + ", .video_std = " + this.video_std + ", .audio_std = " + this.audio_std + ", .channel_id = " + this.channel_id + ", .finetune = " + this.finetune + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(24L), 0L);
    }

    public static final ArrayList<AnalogChannelInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<AnalogChannelInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 24, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            AnalogChannelInfo analogChannelInfo = new AnalogChannelInfo();
            analogChannelInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 24);
            arrayList.add(analogChannelInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.frequency = hwBlob.getInt32(0 + j);
        this.org_frequency = hwBlob.getInt32(4 + j);
        this.video_std = hwBlob.getInt32(8 + j);
        this.audio_std = hwBlob.getInt32(12 + j);
        this.channel_id = hwBlob.getInt32(16 + j);
        this.finetune = hwBlob.getInt32(j + 20);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(24);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<AnalogChannelInfo> arrayList) {
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
        hwBlob.putInt32(0 + j, this.frequency);
        hwBlob.putInt32(4 + j, this.org_frequency);
        hwBlob.putInt32(8 + j, this.video_std);
        hwBlob.putInt32(12 + j, this.audio_std);
        hwBlob.putInt32(16 + j, this.channel_id);
        hwBlob.putInt32(j + 20, this.finetune);
    }
}
