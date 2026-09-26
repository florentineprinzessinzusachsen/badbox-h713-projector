package android.hardware.tv.input.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class Result {
    public static final int INVALID_ARGUMENTS = 3;
    public static final int INVALID_STATE = 4;
    public static final int NO_RESOURCE = 2;
    public static final int OK = 0;
    public static final int UNKNOWN = 1;

    public static final String toString(int i) {
        if (i == 0) {
            return "OK";
        }
        if (i == 1) {
            return "UNKNOWN";
        }
        if (i == 2) {
            return "NO_RESOURCE";
        }
        if (i == 3) {
            return "INVALID_ARGUMENTS";
        }
        if (i == 4) {
            return "INVALID_STATE";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("OK");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("UNKNOWN");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("NO_RESOURCE");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("INVALID_ARGUMENTS");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("INVALID_STATE");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
