package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class EDID_Version {
    public static final int HDMI_EDID_Version_14 = 0;
    public static final int HDMI_EDID_Version_20 = 1;

    public static final String toString(int i) {
        if (i == 0) {
            return "HDMI_EDID_Version_14";
        }
        if (i == 1) {
            return "HDMI_EDID_Version_20";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("HDMI_EDID_Version_14");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("HDMI_EDID_Version_20");
        } else {
            i2 = 0;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
