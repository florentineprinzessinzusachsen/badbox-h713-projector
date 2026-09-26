package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class Result {
    public static final int FAIL = -1;
    public static final int INV_ARG = -2;
    public static final int NOT_IMPLEMENT = -3;
    public static final int OK = 0;

    public static final String toString(int i) {
        if (i == 0) {
            return "OK";
        }
        if (i == -1) {
            return "FAIL";
        }
        if (i == -2) {
            return "INV_ARG";
        }
        if (i == -3) {
            return "NOT_IMPLEMENT";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("OK");
        int i2 = -1;
        if ((i & (-1)) == -1) {
            arrayList.add("FAIL");
        } else {
            i2 = 0;
        }
        if ((i & (-2)) == -2) {
            arrayList.add("INV_ARG");
            i2 |= -2;
        }
        if ((i & (-3)) == -3) {
            arrayList.add("NOT_IMPLEMENT");
            i2 |= -3;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
