package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class tag_cec_message {
    public int initiator = 0;
    public int destination = 0;
    public int length = 0;
    public byte[] body = new byte[16];

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != tag_cec_message.class) {
            return false;
        }
        tag_cec_message tag_cec_messageVar = (tag_cec_message) obj;
        return this.initiator == tag_cec_messageVar.initiator && this.destination == tag_cec_messageVar.destination && this.length == tag_cec_messageVar.length && HidlSupport.deepEquals(this.body, tag_cec_messageVar.body);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.initiator))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.destination))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.length))), Integer.valueOf(HidlSupport.deepHashCode(this.body)));
    }

    public final String toString() {
        return "{.initiator = " + tag_cec_logical_address_t.toString(this.initiator) + ", .destination = " + tag_cec_logical_address_t.toString(this.destination) + ", .length = " + this.length + ", .body = " + Arrays.toString(this.body) + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(28L), 0L);
    }

    public static final ArrayList<tag_cec_message> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<tag_cec_message> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 28, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            tag_cec_message tag_cec_messageVar = new tag_cec_message();
            tag_cec_messageVar.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 28);
            arrayList.add(tag_cec_messageVar);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.initiator = hwBlob.getInt32(0 + j);
        this.destination = hwBlob.getInt32(4 + j);
        this.length = hwBlob.getInt32(8 + j);
        hwBlob.copyToInt8Array(j + 12, this.body, 16);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(28);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<tag_cec_message> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 28);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 28);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.initiator);
        hwBlob.putInt32(4 + j, this.destination);
        hwBlob.putInt32(8 + j, this.length);
        long j2 = j + 12;
        byte[] bArr = this.body;
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt8Array(j2, bArr);
    }
}
