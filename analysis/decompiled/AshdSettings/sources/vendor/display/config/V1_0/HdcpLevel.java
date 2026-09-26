package vendor.display.config.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class HdcpLevel {
    public static final int HDCP_NONE = 1;
    public static final int HDCP_NO_OUTPUT = 6;
    public static final int HDCP_UNKNOWN = 0;
    public static final int HDCP_V1 = 2;
    public static final int HDCP_V2 = 3;
    public static final int HDCP_V2_1 = 4;
    public static final int HDCP_V2_2 = 5;

    public static final String toString(int i) {
        if (i == 0) {
            return "HDCP_UNKNOWN";
        }
        if (i == 1) {
            return "HDCP_NONE";
        }
        if (i == 2) {
            return "HDCP_V1";
        }
        if (i == 3) {
            return "HDCP_V2";
        }
        if (i == 4) {
            return "HDCP_V2_1";
        }
        if (i == 5) {
            return "HDCP_V2_2";
        }
        if (i == 6) {
            return "HDCP_NO_OUTPUT";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("HDCP_UNKNOWN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("HDCP_NONE");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("HDCP_V1");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("HDCP_V2");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("HDCP_V2_1");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("HDCP_V2_2");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("HDCP_NO_OUTPUT");
            i2 |= 6;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
