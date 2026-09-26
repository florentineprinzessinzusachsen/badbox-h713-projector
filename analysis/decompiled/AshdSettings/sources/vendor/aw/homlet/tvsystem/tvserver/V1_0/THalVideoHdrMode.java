package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class THalVideoHdrMode {
    public static final int kHaVideoHdrMode_Hdr10 = 1;
    public static final int kHaVideoHdrMode_Hdr10Plus = 2;
    public static final int kHaVideoHdrMode_Hlg = 5;
    public static final int kHaVideoHdrMode_HmmixVison = 6;
    public static final int kHaVideoHdrMode_Max = 7;
    public static final int kHaVideoHdrMode_Sdr = 0;
    public static final int kHaVideoHdrMode_Sl_Hdr1 = 3;
    public static final int kHaVideoHdrMode_Sl_Hdr2 = 4;

    public static final String toString(int i) {
        if (i == 0) {
            return "kHaVideoHdrMode_Sdr";
        }
        if (i == 1) {
            return "kHaVideoHdrMode_Hdr10";
        }
        if (i == 2) {
            return "kHaVideoHdrMode_Hdr10Plus";
        }
        if (i == 3) {
            return "kHaVideoHdrMode_Sl_Hdr1";
        }
        if (i == 4) {
            return "kHaVideoHdrMode_Sl_Hdr2";
        }
        if (i == 5) {
            return "kHaVideoHdrMode_Hlg";
        }
        if (i == 6) {
            return "kHaVideoHdrMode_HmmixVison";
        }
        if (i == 7) {
            return "kHaVideoHdrMode_Max";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("kHaVideoHdrMode_Sdr");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("kHaVideoHdrMode_Hdr10");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("kHaVideoHdrMode_Hdr10Plus");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("kHaVideoHdrMode_Sl_Hdr1");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("kHaVideoHdrMode_Sl_Hdr2");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("kHaVideoHdrMode_Hlg");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("kHaVideoHdrMode_HmmixVison");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("kHaVideoHdrMode_Max");
            i2 |= 7;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
