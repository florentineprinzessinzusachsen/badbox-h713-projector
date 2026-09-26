package vendor.aw.hardware.btvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TvScanMode {
    public static final int SCAN_MODE_ADD_ON = 4;
    public static final int SCAN_MODE_BGM = 9;
    public static final int SCAN_MODE_DVBC_NIT_SEARCH_MODE_EX_QUICK = 12;
    public static final int SCAN_MODE_DVBC_NIT_SEARCH_MODE_OFF = 10;
    public static final int SCAN_MODE_DVBC_NIT_SEARCH_MODE_QUICK = 11;
    public static final int SCAN_MODE_FULL = 0;
    public static final int SCAN_MODE_MANUAL_FREQ = 8;
    public static final int SCAN_MODE_MANUAL_RF_CHANNEL = 7;
    public static final int SCAN_MODE_NUM = 13;
    public static final int SCAN_MODE_QUICK = 3;
    public static final int SCAN_MODE_RANGE = 2;
    public static final int SCAN_MODE_RANGE_RF_CHANNEL = 6;
    public static final int SCAN_MODE_SINGLE_RF_CHANNEL = 5;
    public static final int SCAN_MODE_UPDATE = 1;

    public static final String toString(int i) {
        if (i == 0) {
            return "SCAN_MODE_FULL";
        }
        if (i == 1) {
            return "SCAN_MODE_UPDATE";
        }
        if (i == 2) {
            return "SCAN_MODE_RANGE";
        }
        if (i == 3) {
            return "SCAN_MODE_QUICK";
        }
        if (i == 4) {
            return "SCAN_MODE_ADD_ON";
        }
        if (i == 5) {
            return "SCAN_MODE_SINGLE_RF_CHANNEL";
        }
        if (i == 6) {
            return "SCAN_MODE_RANGE_RF_CHANNEL";
        }
        if (i == 7) {
            return "SCAN_MODE_MANUAL_RF_CHANNEL";
        }
        if (i == 8) {
            return "SCAN_MODE_MANUAL_FREQ";
        }
        if (i == 9) {
            return "SCAN_MODE_BGM";
        }
        if (i == 10) {
            return "SCAN_MODE_DVBC_NIT_SEARCH_MODE_OFF";
        }
        if (i == 11) {
            return "SCAN_MODE_DVBC_NIT_SEARCH_MODE_QUICK";
        }
        if (i == 12) {
            return "SCAN_MODE_DVBC_NIT_SEARCH_MODE_EX_QUICK";
        }
        if (i == 13) {
            return "SCAN_MODE_NUM";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SCAN_MODE_FULL");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("SCAN_MODE_UPDATE");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("SCAN_MODE_RANGE");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("SCAN_MODE_QUICK");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("SCAN_MODE_ADD_ON");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("SCAN_MODE_SINGLE_RF_CHANNEL");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("SCAN_MODE_RANGE_RF_CHANNEL");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("SCAN_MODE_MANUAL_RF_CHANNEL");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("SCAN_MODE_MANUAL_FREQ");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("SCAN_MODE_BGM");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("SCAN_MODE_DVBC_NIT_SEARCH_MODE_OFF");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("SCAN_MODE_DVBC_NIT_SEARCH_MODE_QUICK");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("SCAN_MODE_DVBC_NIT_SEARCH_MODE_EX_QUICK");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("SCAN_MODE_NUM");
            i2 |= 13;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
