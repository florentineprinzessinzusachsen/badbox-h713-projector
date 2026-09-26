package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TCECVersion {
    public static final int CECVersion_1_1 = 0;
    public static final int CECVersion_1_2 = 1;
    public static final int CECVersion_1_2a = 2;
    public static final int CECVersion_1_3 = 3;
    public static final int CECVersion_1_3a = 4;
    public static final int CECVersion_1_4 = 5;
    public static final int CECVersion_Unknown = 255;

    public static final String toString(int i) {
        if (i == 0) {
            return "CECVersion_1_1";
        }
        if (i == 1) {
            return "CECVersion_1_2";
        }
        if (i == 2) {
            return "CECVersion_1_2a";
        }
        if (i == 3) {
            return "CECVersion_1_3";
        }
        if (i == 4) {
            return "CECVersion_1_3a";
        }
        if (i == 5) {
            return "CECVersion_1_4";
        }
        if (i == 255) {
            return "CECVersion_Unknown";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("CECVersion_1_1");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("CECVersion_1_2");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("CECVersion_1_2a");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("CECVersion_1_3");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("CECVersion_1_3a");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("CECVersion_1_4");
            i2 |= 5;
        }
        if ((i & 255) == 255) {
            arrayList.add("CECVersion_Unknown");
            i2 |= 255;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
