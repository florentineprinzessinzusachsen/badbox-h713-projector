package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ControlType {
    public static final int CONTROL_0 = 0;
    public static final int CONTROL_1 = 1;

    public static final String toString(int i) {
        if (i == 0) {
            return "CONTROL_0";
        }
        if (i == 1) {
            return "CONTROL_1";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("CONTROL_0");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("CONTROL_1");
        } else {
            i2 = 0;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
