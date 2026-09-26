package android.hardware.audio.common.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class AudioConfig {
    public int sampleRateHz = 0;
    public int channelMask = 0;
    public int format = 0;
    public AudioOffloadInfo offloadInfo = new AudioOffloadInfo();
    public long frameCount = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != AudioConfig.class) {
            return false;
        }
        AudioConfig audioConfig = (AudioConfig) obj;
        return this.sampleRateHz == audioConfig.sampleRateHz && this.channelMask == audioConfig.channelMask && this.format == audioConfig.format && HidlSupport.deepEquals(this.offloadInfo, audioConfig.offloadInfo) && this.frameCount == audioConfig.frameCount;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.sampleRateHz))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.channelMask))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.format))), Integer.valueOf(HidlSupport.deepHashCode(this.offloadInfo)), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.frameCount))));
    }

    public final String toString() {
        return "{.sampleRateHz = " + this.sampleRateHz + ", .channelMask = " + AudioChannelMask.toString(this.channelMask) + ", .format = " + AudioFormat.toString(this.format) + ", .offloadInfo = " + this.offloadInfo + ", .frameCount = " + this.frameCount + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(72L), 0L);
    }

    public static final ArrayList<AudioConfig> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<AudioConfig> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 72, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            AudioConfig audioConfig = new AudioConfig();
            audioConfig.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 72);
            arrayList.add(audioConfig);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.sampleRateHz = hwBlob.getInt32(0 + j);
        this.channelMask = hwBlob.getInt32(4 + j);
        this.format = hwBlob.getInt32(8 + j);
        this.offloadInfo.readEmbeddedFromParcel(hwParcel, hwBlob, 16 + j);
        this.frameCount = hwBlob.getInt64(j + 64);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(72);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<AudioConfig> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 72);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 72);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.sampleRateHz);
        hwBlob.putInt32(4 + j, this.channelMask);
        hwBlob.putInt32(8 + j, this.format);
        this.offloadInfo.writeEmbeddedToBlob(hwBlob, 16 + j);
        hwBlob.putInt64(j + 64, this.frameCount);
    }
}
