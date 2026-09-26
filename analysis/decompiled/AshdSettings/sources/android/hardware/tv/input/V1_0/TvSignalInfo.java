package android.hardware.tv.input.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class TvSignalInfo {
    public int signal_id = 0;
    public int frame_rate_x100 = 0;
    public boolean b_interlace = false;
    public int color_format = 0;
    public int color_space = 0;
    public TvResolution resolution = new TvResolution();
    public int hdr_mode = 0;
    public boolean b_full_range = false;
    public boolean b_dvi_mode = false;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != TvSignalInfo.class) {
            return false;
        }
        TvSignalInfo tvSignalInfo = (TvSignalInfo) obj;
        return this.signal_id == tvSignalInfo.signal_id && this.frame_rate_x100 == tvSignalInfo.frame_rate_x100 && this.b_interlace == tvSignalInfo.b_interlace && this.color_format == tvSignalInfo.color_format && this.color_space == tvSignalInfo.color_space && HidlSupport.deepEquals(this.resolution, tvSignalInfo.resolution) && this.hdr_mode == tvSignalInfo.hdr_mode && this.b_full_range == tvSignalInfo.b_full_range && this.b_dvi_mode == tvSignalInfo.b_dvi_mode;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.signal_id))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.frame_rate_x100))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.b_interlace))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.color_format))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.color_space))), Integer.valueOf(HidlSupport.deepHashCode(this.resolution)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.hdr_mode))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.b_full_range))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.b_dvi_mode))));
    }

    public final String toString() {
        return "{.signal_id = " + this.signal_id + ", .frame_rate_x100 = " + this.frame_rate_x100 + ", .b_interlace = " + this.b_interlace + ", .color_format = " + this.color_format + ", .color_space = " + this.color_space + ", .resolution = " + this.resolution + ", .hdr_mode = " + this.hdr_mode + ", .b_full_range = " + this.b_full_range + ", .b_dvi_mode = " + this.b_dvi_mode + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(36L), 0L);
    }

    public static final ArrayList<TvSignalInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<TvSignalInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 36, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            TvSignalInfo tvSignalInfo = new TvSignalInfo();
            tvSignalInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 36);
            arrayList.add(tvSignalInfo);
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
        this.b_dvi_mode = hwBlob.getBool(j + 33);
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(36);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<TvSignalInfo> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 36);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 36);
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
        hwBlob.putBool(j + 33, this.b_dvi_mode);
    }
}
