package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class OverScanType {
    public static final int OVERSCAN_DOWN = 3;
    public static final int OVERSCAN_LEFT = 0;
    public static final int OVERSCAN_RIGHT = 1;
    public static final int OVERSCAN_UP = 2;

    public static final String toString(int i) {
        if (i == 0) {
            return "OVERSCAN_LEFT";
        }
        if (i == 1) {
            return "OVERSCAN_RIGHT";
        }
        if (i == 2) {
            return "OVERSCAN_UP";
        }
        if (i == 3) {
            return "OVERSCAN_DOWN";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("OVERSCAN_LEFT");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("OVERSCAN_RIGHT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("OVERSCAN_UP");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("OVERSCAN_DOWN");
            i2 |= 3;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
