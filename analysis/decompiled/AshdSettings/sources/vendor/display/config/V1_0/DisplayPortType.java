package vendor.display.config.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class DisplayPortType {
    public static final int DISPLAY_PORT_CVBS = 2;
    public static final int DISPLAY_PORT_HDMI = 4;
    public static final int DISPLAY_PORT_LCD = 1;
    public static final int DISPLAY_PORT_NONE = 0;
    public static final int DISPLAY_PORT_VGA = 8;

    public static final String toString(int i) {
        if (i == 0) {
            return "DISPLAY_PORT_NONE";
        }
        if (i == 1) {
            return "DISPLAY_PORT_LCD";
        }
        if (i == 2) {
            return "DISPLAY_PORT_CVBS";
        }
        if (i == 4) {
            return "DISPLAY_PORT_HDMI";
        }
        if (i == 8) {
            return "DISPLAY_PORT_VGA";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("DISPLAY_PORT_NONE");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("DISPLAY_PORT_LCD");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("DISPLAY_PORT_CVBS");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("DISPLAY_PORT_HDMI");
            i2 |= 4;
        }
        if ((i & 8) == 8) {
            arrayList.add("DISPLAY_PORT_VGA");
            i2 |= 8;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
