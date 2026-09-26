package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class HDCP {
    public static final int hdcp_14 = 0;
    public static final int hdcp_22 = 1;

    public static final String toString(int i) {
        if (i == 0) {
            return "hdcp_14";
        }
        if (i == 1) {
            return "hdcp_22";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("hdcp_14");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("hdcp_22");
        } else {
            i2 = 0;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
