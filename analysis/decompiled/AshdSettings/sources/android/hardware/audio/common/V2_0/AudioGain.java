package android.hardware.audio.common.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class AudioGain {
    public int mode = 0;
    public int channelMask = 0;
    public int minValue = 0;
    public int maxValue = 0;
    public int defaultValue = 0;
    public int stepValue = 0;
    public int minRampMs = 0;
    public int maxRampMs = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != AudioGain.class) {
            return false;
        }
        AudioGain audioGain = (AudioGain) obj;
        return this.mode == audioGain.mode && this.channelMask == audioGain.channelMask && this.minValue == audioGain.minValue && this.maxValue == audioGain.maxValue && this.defaultValue == audioGain.defaultValue && this.stepValue == audioGain.stepValue && this.minRampMs == audioGain.minRampMs && this.maxRampMs == audioGain.maxRampMs;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.mode))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.channelMask))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.minValue))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxValue))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.defaultValue))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.stepValue))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.minRampMs))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxRampMs))));
    }

    public final String toString() {
        return "{.mode = " + AudioGainMode.toString(this.mode) + ", .channelMask = " + AudioChannelMask.toString(this.channelMask) + ", .minValue = " + this.minValue + ", .maxValue = " + this.maxValue + ", .defaultValue = " + this.defaultValue + ", .stepValue = " + this.stepValue + ", .minRampMs = " + this.minRampMs + ", .maxRampMs = " + this.maxRampMs + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(32L), 0L);
    }

    public static final ArrayList<AudioGain> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<AudioGain> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 32, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            AudioGain audioGain = new AudioGain();
            audioGain.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 32);
            arrayList.add(audioGain);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.mode = hwBlob.getInt32(0 + j);
        this.channelMask = hwBlob.getInt32(4 + j);
        this.minValue = hwBlob.getInt32(8 + j);
        this.maxValue = hwBlob.getInt32(12 + j);
        this.defaultValue = hwBlob.getInt32(16 + j);
        this.stepValue = hwBlob.getInt32(20 + j);
        this.minRampMs = hwBlob.getInt32(24 + j);
        this.maxRampMs = hwBlob.getInt32(j + 28);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(32);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<AudioGain> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 32);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 32);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.mode);
        hwBlob.putInt32(4 + j, this.channelMask);
        hwBlob.putInt32(8 + j, this.minValue);
        hwBlob.putInt32(12 + j, this.maxValue);
        hwBlob.putInt32(16 + j, this.defaultValue);
        hwBlob.putInt32(20 + j, this.stepValue);
        hwBlob.putInt32(24 + j, this.minRampMs);
        hwBlob.putInt32(j + 28, this.maxRampMs);
    }
}
