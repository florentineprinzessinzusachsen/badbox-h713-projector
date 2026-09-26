package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class PQRGBOGType {
    public static final int PQ_GAIN_B = 5;
    public static final int PQ_GAIN_G = 4;
    public static final int PQ_GAIN_R = 3;
    public static final int PQ_OFFSET_B = 2;
    public static final int PQ_OFFSET_G = 1;
    public static final int PQ_OFFSET_R = 0;

    public static final String toString(int i) {
        if (i == 0) {
            return "PQ_OFFSET_R";
        }
        if (i == 1) {
            return "PQ_OFFSET_G";
        }
        if (i == 2) {
            return "PQ_OFFSET_B";
        }
        if (i == 3) {
            return "PQ_GAIN_R";
        }
        if (i == 4) {
            return "PQ_GAIN_G";
        }
        if (i == 5) {
            return "PQ_GAIN_B";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("PQ_OFFSET_R");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("PQ_OFFSET_G");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("PQ_OFFSET_B");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("PQ_GAIN_R");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("PQ_GAIN_G");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("PQ_GAIN_B");
            i2 |= 5;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
