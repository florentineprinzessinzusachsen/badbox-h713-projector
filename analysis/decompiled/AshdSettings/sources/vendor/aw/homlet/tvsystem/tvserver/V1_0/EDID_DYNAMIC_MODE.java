package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class EDID_DYNAMIC_MODE {
    public static final int EDID_DYNAMIC_MODE_ARC = 2;
    public static final int EDID_DYNAMIC_MODE_DTS = 3;
    public static final int EDID_DYNAMIC_MODE_OFF = 1;

    public static final String toString(int i) {
        if (i == 1) {
            return "EDID_DYNAMIC_MODE_OFF";
        }
        if (i == 2) {
            return "EDID_DYNAMIC_MODE_ARC";
        }
        if (i == 3) {
            return "EDID_DYNAMIC_MODE_DTS";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("EDID_DYNAMIC_MODE_OFF");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("EDID_DYNAMIC_MODE_ARC");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("EDID_DYNAMIC_MODE_DTS");
            i2 |= 3;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
