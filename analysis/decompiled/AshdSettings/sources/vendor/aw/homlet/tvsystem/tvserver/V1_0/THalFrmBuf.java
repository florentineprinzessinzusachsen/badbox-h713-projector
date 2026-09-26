package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class THalFrmBuf {
    public int read_index = 0;
    public int[] buf_addr_y = new int[6];
    public int[] buf_addr_c = new int[6];
    public int[] is_bottom_field = new int[6];
    public int buf_num = 0;
    public int buf_stride_in_byte = 0;
    public THalWin pic_size = new THalWin();
    public int color_format = 0;
    public boolean b_interlace = false;
    public int frame_line_cnt = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != THalFrmBuf.class) {
            return false;
        }
        THalFrmBuf tHalFrmBuf = (THalFrmBuf) obj;
        return this.read_index == tHalFrmBuf.read_index && HidlSupport.deepEquals(this.buf_addr_y, tHalFrmBuf.buf_addr_y) && HidlSupport.deepEquals(this.buf_addr_c, tHalFrmBuf.buf_addr_c) && HidlSupport.deepEquals(this.is_bottom_field, tHalFrmBuf.is_bottom_field) && this.buf_num == tHalFrmBuf.buf_num && this.buf_stride_in_byte == tHalFrmBuf.buf_stride_in_byte && HidlSupport.deepEquals(this.pic_size, tHalFrmBuf.pic_size) && this.color_format == tHalFrmBuf.color_format && this.b_interlace == tHalFrmBuf.b_interlace && this.frame_line_cnt == tHalFrmBuf.frame_line_cnt;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.read_index))), Integer.valueOf(HidlSupport.deepHashCode(this.buf_addr_y)), Integer.valueOf(HidlSupport.deepHashCode(this.buf_addr_c)), Integer.valueOf(HidlSupport.deepHashCode(this.is_bottom_field)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.buf_num))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.buf_stride_in_byte))), Integer.valueOf(HidlSupport.deepHashCode(this.pic_size)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.color_format))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.b_interlace))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.frame_line_cnt))));
    }

    public final String toString() {
        return "{.read_index = " + this.read_index + ", .buf_addr_y = " + Arrays.toString(this.buf_addr_y) + ", .buf_addr_c = " + Arrays.toString(this.buf_addr_c) + ", .is_bottom_field = " + Arrays.toString(this.is_bottom_field) + ", .buf_num = " + this.buf_num + ", .buf_stride_in_byte = " + this.buf_stride_in_byte + ", .pic_size = " + this.pic_size + ", .color_format = " + THalColorFormat.toString(this.color_format) + ", .b_interlace = " + this.b_interlace + ", .frame_line_cnt = " + this.frame_line_cnt + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(112L), 0L);
    }

    public static final ArrayList<THalFrmBuf> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<THalFrmBuf> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 112, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            THalFrmBuf tHalFrmBuf = new THalFrmBuf();
            tHalFrmBuf.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 112);
            arrayList.add(tHalFrmBuf);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.read_index = hwBlob.getInt32(0 + j);
        hwBlob.copyToInt32Array(4 + j, this.buf_addr_y, 6);
        hwBlob.copyToInt32Array(28 + j, this.buf_addr_c, 6);
        hwBlob.copyToInt32Array(52 + j, this.is_bottom_field, 6);
        this.buf_num = hwBlob.getInt32(76 + j);
        this.buf_stride_in_byte = hwBlob.getInt32(80 + j);
        this.pic_size.readEmbeddedFromParcel(hwParcel, hwBlob, 84 + j);
        this.color_format = hwBlob.getInt32(100 + j);
        this.b_interlace = hwBlob.getBool(104 + j);
        this.frame_line_cnt = hwBlob.getInt32(j + 108);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(112);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<THalFrmBuf> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 112);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 112);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.read_index);
        long j2 = 4 + j;
        int[] iArr = this.buf_addr_y;
        if (iArr == null || iArr.length != 6) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt32Array(j2, iArr);
        long j3 = 28 + j;
        int[] iArr2 = this.buf_addr_c;
        if (iArr2 == null || iArr2.length != 6) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt32Array(j3, iArr2);
        long j4 = 52 + j;
        int[] iArr3 = this.is_bottom_field;
        if (iArr3 == null || iArr3.length != 6) {
            throw new IllegalArgumentException("Array element is not of the expected length");
        }
        hwBlob.putInt32Array(j4, iArr3);
        hwBlob.putInt32(76 + j, this.buf_num);
        hwBlob.putInt32(80 + j, this.buf_stride_in_byte);
        this.pic_size.writeEmbeddedToBlob(hwBlob, 84 + j);
        hwBlob.putInt32(100 + j, this.color_format);
        hwBlob.putBool(104 + j, this.b_interlace);
        hwBlob.putInt32(j + 108, this.frame_line_cnt);
    }
}
