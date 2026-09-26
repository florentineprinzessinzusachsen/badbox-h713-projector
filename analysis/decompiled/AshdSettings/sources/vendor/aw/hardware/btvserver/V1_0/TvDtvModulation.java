package vendor.aw.hardware.btvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TvDtvModulation {
    public static final int DTV_MOD_QAM128 = 3;
    public static final int DTV_MOD_QAM16 = 0;
    public static final int DTV_MOD_QAM256 = 4;
    public static final int DTV_MOD_QAM32 = 1;
    public static final int DTV_MOD_QAM64 = 2;
    public static final int DTV_MOD_VSB8 = 5;

    public static final String toString(int i) {
        if (i == 0) {
            return "DTV_MOD_QAM16";
        }
        if (i == 1) {
            return "DTV_MOD_QAM32";
        }
        if (i == 2) {
            return "DTV_MOD_QAM64";
        }
        if (i == 3) {
            return "DTV_MOD_QAM128";
        }
        if (i == 4) {
            return "DTV_MOD_QAM256";
        }
        if (i == 5) {
            return "DTV_MOD_VSB8";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("DTV_MOD_QAM16");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("DTV_MOD_QAM32");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("DTV_MOD_QAM64");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("DTV_MOD_QAM128");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("DTV_MOD_QAM256");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("DTV_MOD_VSB8");
            i2 |= 5;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
