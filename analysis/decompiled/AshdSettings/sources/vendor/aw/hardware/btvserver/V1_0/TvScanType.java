package vendor.aw.hardware.btvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TvScanType {
    public static final int SCAN_TYPE_ATSC = 4;
    public static final int SCAN_TYPE_ATV_AUTO = 12;
    public static final int SCAN_TYPE_CQAM = 6;
    public static final int SCAN_TYPE_DTMB = 9;
    public static final int SCAN_TYPE_DTV_ATUO = 13;
    public static final int SCAN_TYPE_DVBC = 2;
    public static final int SCAN_TYPE_DVBS = 8;
    public static final int SCAN_TYPE_DVBT = 1;
    public static final int SCAN_TYPE_DVBT2 = 7;
    public static final int SCAN_TYPE_ISDB = 5;
    public static final int SCAN_TYPE_NTSC = 3;
    public static final int SCAN_TYPE_NUM = 14;
    public static final int SCAN_TYPE_PAL = 0;
    public static final int SCAN_TYPE_SA = 11;
    public static final int SCAN_TYPE_US = 10;

    public static final String toString(int i) {
        if (i == 0) {
            return "SCAN_TYPE_PAL";
        }
        if (i == 1) {
            return "SCAN_TYPE_DVBT";
        }
        if (i == 2) {
            return "SCAN_TYPE_DVBC";
        }
        if (i == 3) {
            return "SCAN_TYPE_NTSC";
        }
        if (i == 4) {
            return "SCAN_TYPE_ATSC";
        }
        if (i == 5) {
            return "SCAN_TYPE_ISDB";
        }
        if (i == 6) {
            return "SCAN_TYPE_CQAM";
        }
        if (i == 7) {
            return "SCAN_TYPE_DVBT2";
        }
        if (i == 8) {
            return "SCAN_TYPE_DVBS";
        }
        if (i == 9) {
            return "SCAN_TYPE_DTMB";
        }
        if (i == 10) {
            return "SCAN_TYPE_US";
        }
        if (i == 11) {
            return "SCAN_TYPE_SA";
        }
        if (i == 12) {
            return "SCAN_TYPE_ATV_AUTO";
        }
        if (i == 13) {
            return "SCAN_TYPE_DTV_ATUO";
        }
        if (i == 14) {
            return "SCAN_TYPE_NUM";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SCAN_TYPE_PAL");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("SCAN_TYPE_DVBT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("SCAN_TYPE_DVBC");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("SCAN_TYPE_NTSC");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("SCAN_TYPE_ATSC");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("SCAN_TYPE_ISDB");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("SCAN_TYPE_CQAM");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("SCAN_TYPE_DVBT2");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("SCAN_TYPE_DVBS");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("SCAN_TYPE_DTMB");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("SCAN_TYPE_US");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("SCAN_TYPE_SA");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("SCAN_TYPE_ATV_AUTO");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("SCAN_TYPE_DTV_ATUO");
            i2 |= 13;
        }
        if ((i & 14) == 14) {
            arrayList.add("SCAN_TYPE_NUM");
            i2 |= 14;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
