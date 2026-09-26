package vendor.aw.hardware.btvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class CallbackParcel {
    public int type = 0;
    public ArrayList<Integer> bodyInt = new ArrayList<>();
    public ArrayList<String> bodyString = new ArrayList<>();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != CallbackParcel.class) {
            return false;
        }
        CallbackParcel callbackParcel = (CallbackParcel) obj;
        return this.type == callbackParcel.type && HidlSupport.deepEquals(this.bodyInt, callbackParcel.bodyInt) && HidlSupport.deepEquals(this.bodyString, callbackParcel.bodyString);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(this.bodyInt)), Integer.valueOf(HidlSupport.deepHashCode(this.bodyString)));
    }

    public final String toString() {
        return "{.type = " + this.type + ", .bodyInt = " + this.bodyInt + ", .bodyString = " + this.bodyString + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(40L), 0L);
    }

    public static final ArrayList<CallbackParcel> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<CallbackParcel> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 40, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            CallbackParcel callbackParcel = new CallbackParcel();
            callbackParcel.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 40);
            arrayList.add(callbackParcel);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.type = hwBlob.getInt32(j + 0);
        long j2 = j + 8;
        int int32 = hwBlob.getInt32(j2 + 8);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 4, hwBlob.handle(), j2 + 0, true);
        this.bodyInt.clear();
        for (int i = 0; i < int32; i++) {
            this.bodyInt.add(Integer.valueOf(embeddedBuffer.getInt32(i * 4)));
        }
        long j3 = j + 24;
        int int33 = hwBlob.getInt32(8 + j3);
        HwBlob embeddedBuffer2 = hwParcel.readEmbeddedBuffer(int33 * 16, hwBlob.handle(), j3 + 0, true);
        this.bodyString.clear();
        for (int i2 = 0; i2 < int33; i2++) {
            new String();
            int i3 = i2 * 16;
            String string = embeddedBuffer2.getString(i3);
            hwParcel.readEmbeddedBuffer(string.getBytes().length + 1, embeddedBuffer2.handle(), i3 + 0, false);
            this.bodyString.add(string);
        }
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(40);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<CallbackParcel> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 40);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 40);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(j + 0, this.type);
        int size = this.bodyInt.size();
        long j2 = j + 8;
        hwBlob.putInt32(j2 + 8, size);
        hwBlob.putBool(j2 + 12, false);
        HwBlob hwBlob2 = new HwBlob(size * 4);
        for (int i = 0; i < size; i++) {
            hwBlob2.putInt32(i * 4, this.bodyInt.get(i).intValue());
        }
        hwBlob.putBlob(j2 + 0, hwBlob2);
        int size2 = this.bodyString.size();
        long j3 = j + 24;
        hwBlob.putInt32(8 + j3, size2);
        hwBlob.putBool(j3 + 12, false);
        HwBlob hwBlob3 = new HwBlob(size2 * 16);
        for (int i2 = 0; i2 < size2; i2++) {
            hwBlob3.putString(i2 * 16, this.bodyString.get(i2));
        }
        hwBlob.putBlob(j3 + 0, hwBlob3);
    }
}
