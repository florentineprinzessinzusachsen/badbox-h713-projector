package vendor.aw.hardware.btvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import com.alibaba.fastjson.asm.Opcodes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class DtvChannelInfo {
    public DtvLockInfo lockInfo = new DtvLockInfo();
    public int videoPid = 0;
    public int audioSize = 0;
    public int[] audioPid = new int[32];
    public int pcrPid = 0;
    public int programNumber = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != DtvChannelInfo.class) {
            return false;
        }
        DtvChannelInfo dtvChannelInfo = (DtvChannelInfo) obj;
        return HidlSupport.deepEquals(this.lockInfo, dtvChannelInfo.lockInfo) && this.videoPid == dtvChannelInfo.videoPid && this.audioSize == dtvChannelInfo.audioSize && HidlSupport.deepEquals(this.audioPid, dtvChannelInfo.audioPid) && this.pcrPid == dtvChannelInfo.pcrPid && this.programNumber == dtvChannelInfo.programNumber;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.lockInfo)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.videoPid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.audioSize))), Integer.valueOf(HidlSupport.deepHashCode(this.audioPid)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pcrPid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.programNumber))));
    }

    public final String toString() {
        return "{.lockInfo = " + this.lockInfo + ", .videoPid = " + this.videoPid + ", .audioSize = " + this.audioSize + ", .audioPid = " + Arrays.toString(this.audioPid) + ", .pcrPid = " + this.pcrPid + ", .programNumber = " + this.programNumber + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(160L), 0L);
    }

    public static final ArrayList<DtvChannelInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<DtvChannelInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * Opcodes.IF_ICMPNE, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            DtvChannelInfo dtvChannelInfo = new DtvChannelInfo();
            dtvChannelInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * Opcodes.IF_ICMPNE);
            arrayList.add(dtvChannelInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.lockInfo.readEmbeddedFromParcel(hwParcel, hwBlob, 0 + j);
        this.videoPid = hwBlob.getInt32(16 + j);
        this.audioSize = hwBlob.getInt32(20 + j);
        hwBlob.copyToInt32Array(24 + j, this.audioPid, 32);
        this.pcrPid = hwBlob.getInt32(152 + j);
        this.programNumber = hwBlob.getInt32(j + 156);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(Opcodes.IF_ICMPNE);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<DtvChannelInfo> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * Opcodes.IF_ICMPNE);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * Opcodes.IF_ICMPNE);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        this.lockInfo.writeEmbeddedToBlob(hwBlob, 0 + j);
        hwBlob.putInt32(16 + j, this.videoPid);
        hwBlob.putInt32(20 + j, this.audioSize);
        long j2 = 24 + j;
        int[] iArr = this.audioPid;
        if (iArr == null || iArr.length != 32) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt32Array(j2, iArr);
        hwBlob.putInt32(152 + j, this.pcrPid);
        hwBlob.putInt32(j + 156, this.programNumber);
    }
}
