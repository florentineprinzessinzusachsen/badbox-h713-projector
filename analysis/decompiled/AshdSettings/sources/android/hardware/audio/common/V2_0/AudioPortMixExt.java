package android.hardware.audio.common.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class AudioPortMixExt {
    public int hwModule = 0;
    public int ioHandle = 0;
    public int latencyClass = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != AudioPortMixExt.class) {
            return false;
        }
        AudioPortMixExt audioPortMixExt = (AudioPortMixExt) obj;
        return this.hwModule == audioPortMixExt.hwModule && this.ioHandle == audioPortMixExt.ioHandle && this.latencyClass == audioPortMixExt.latencyClass;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.hwModule))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.ioHandle))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.latencyClass))));
    }

    public final String toString() {
        return "{.hwModule = " + this.hwModule + ", .ioHandle = " + this.ioHandle + ", .latencyClass = " + AudioMixLatencyClass.toString(this.latencyClass) + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(12L), 0L);
    }

    public static final ArrayList<AudioPortMixExt> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<AudioPortMixExt> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 12, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            AudioPortMixExt audioPortMixExt = new AudioPortMixExt();
            audioPortMixExt.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 12);
            arrayList.add(audioPortMixExt);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.hwModule = hwBlob.getInt32(0 + j);
        this.ioHandle = hwBlob.getInt32(4 + j);
        this.latencyClass = hwBlob.getInt32(j + 8);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(12);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<AudioPortMixExt> arrayList) {
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
        hwBlob.putInt32(0 + j, this.hwModule);
        hwBlob.putInt32(4 + j, this.ioHandle);
        hwBlob.putInt32(j + 8, this.latencyClass);
    }
}
