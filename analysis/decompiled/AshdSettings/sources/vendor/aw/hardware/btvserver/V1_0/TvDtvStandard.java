package vendor.aw.hardware.btvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TvDtvStandard {
    public static final int DTV_STD_ATSC = 5;
    public static final int DTV_STD_DTMB = 1;
    public static final int DTV_STD_DVBC = 2;
    public static final int DTV_STD_DVBS = 4;
    public static final int DTV_STD_DVBT = 3;
    public static final int DTV_STD_ISDB = 6;

    public static final String toString(int i) {
        if (i == 1) {
            return "DTV_STD_DTMB";
        }
        if (i == 2) {
            return "DTV_STD_DVBC";
        }
        if (i == 3) {
            return "DTV_STD_DVBT";
        }
        if (i == 4) {
            return "DTV_STD_DVBS";
        }
        if (i == 5) {
            return "DTV_STD_ATSC";
        }
        if (i == 6) {
            return "DTV_STD_ISDB";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("DTV_STD_DTMB");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("DTV_STD_DVBC");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("DTV_STD_DVBT");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("DTV_STD_DVBS");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("DTV_STD_ATSC");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("DTV_STD_ISDB");
            i2 |= 6;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
