package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class THalSignalInfo {
    public int signal_id = 0;
    public int frame_rate_x100 = 0;
    public boolean b_interlace = false;
    public int color_format = 0;
    public int color_space = 0;
    public THalResolution resolution = new THalResolution();
    public int hdr_mode = 0;
    public boolean b_full_range = false;
    public int b_dvi_mode = 0;
    public int atv_uid = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != THalSignalInfo.class) {
            return false;
        }
        THalSignalInfo tHalSignalInfo = (THalSignalInfo) obj;
        return this.signal_id == tHalSignalInfo.signal_id && this.frame_rate_x100 == tHalSignalInfo.frame_rate_x100 && this.b_interlace == tHalSignalInfo.b_interlace && this.color_format == tHalSignalInfo.color_format && this.color_space == tHalSignalInfo.color_space && HidlSupport.deepEquals(this.resolution, tHalSignalInfo.resolution) && this.hdr_mode == tHalSignalInfo.hdr_mode && this.b_full_range == tHalSignalInfo.b_full_range && this.b_dvi_mode == tHalSignalInfo.b_dvi_mode && this.atv_uid == tHalSignalInfo.atv_uid;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.signal_id))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.frame_rate_x100))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.b_interlace))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.color_format))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.color_space))), Integer.valueOf(HidlSupport.deepHashCode(this.resolution)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.hdr_mode))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.b_full_range))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.b_dvi_mode))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.atv_uid))));
    }

    public final String toString() {
        return "{.signal_id = " + TvSignalID.toString(this.signal_id) + ", .frame_rate_x100 = " + this.frame_rate_x100 + ", .b_interlace = " + this.b_interlace + ", .color_format = " + THalColorFormat.toString(this.color_format) + ", .color_space = " + THalColorSpace.toString(this.color_space) + ", .resolution = " + this.resolution + ", .hdr_mode = " + THalVideoHdrMode.toString(this.hdr_mode) + ", .b_full_range = " + this.b_full_range + ", .b_dvi_mode = " + this.b_dvi_mode + ", .atv_uid = " + this.atv_uid + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(44L), 0L);
    }

    public static final ArrayList<THalSignalInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<THalSignalInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 44, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            THalSignalInfo tHalSignalInfo = new THalSignalInfo();
            tHalSignalInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 44);
            arrayList.add(tHalSignalInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.signal_id = hwBlob.getInt32(0 + j);
        this.frame_rate_x100 = hwBlob.getInt32(4 + j);
        this.b_interlace = hwBlob.getBool(8 + j);
        this.color_format = hwBlob.getInt32(12 + j);
        this.color_space = hwBlob.getInt32(16 + j);
        this.resolution.readEmbeddedFromParcel(hwParcel, hwBlob, 20 + j);
        this.hdr_mode = hwBlob.getInt32(28 + j);
        this.b_full_range = hwBlob.getBool(32 + j);
        this.b_dvi_mode = hwBlob.getInt32(36 + j);
        this.atv_uid = hwBlob.getInt32(j + 40);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(44);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<THalSignalInfo> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 44);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 44);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt32(0 + j, this.signal_id);
        hwBlob.putInt32(4 + j, this.frame_rate_x100);
        hwBlob.putBool(8 + j, this.b_interlace);
        hwBlob.putInt32(12 + j, this.color_format);
        hwBlob.putInt32(16 + j, this.color_space);
        this.resolution.writeEmbeddedToBlob(hwBlob, 20 + j);
        hwBlob.putInt32(28 + j, this.hdr_mode);
        hwBlob.putBool(32 + j, this.b_full_range);
        hwBlob.putInt32(36 + j, this.b_dvi_mode);
        hwBlob.putInt32(j + 40, this.atv_uid);
    }
}
