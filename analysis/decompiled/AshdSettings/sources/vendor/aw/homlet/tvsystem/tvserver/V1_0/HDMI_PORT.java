package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class HDMI_PORT {
    public static final int HDMI1_PORT = 1;
    public static final int HDMI2_PORT = 2;
    public static final int HDMI3_PORT = 3;
    public static final int HDMI4_PORT = 4;

    public static final String toString(int i) {
        if (i == 1) {
            return "HDMI1_PORT";
        }
        if (i == 2) {
            return "HDMI2_PORT";
        }
        if (i == 3) {
            return "HDMI3_PORT";
        }
        if (i == 4) {
            return "HDMI4_PORT";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("HDMI1_PORT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("HDMI2_PORT");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("HDMI3_PORT");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("HDMI4_PORT");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
