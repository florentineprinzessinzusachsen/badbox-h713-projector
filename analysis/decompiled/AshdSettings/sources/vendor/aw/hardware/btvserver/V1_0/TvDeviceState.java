package vendor.aw.hardware.btvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TvDeviceState {
    public static final int ATV_HAS_SIGNAL = 4;
    public static final int ATV_NO_SIGNAL = 3;
    public static final int DTV_HAS_SIGNAL = 2;
    public static final int DTV_NO_SIGNAL = 1;

    public static final String toString(int i) {
        if (i == 1) {
            return "DTV_NO_SIGNAL";
        }
        if (i == 2) {
            return "DTV_HAS_SIGNAL";
        }
        if (i == 3) {
            return "ATV_NO_SIGNAL";
        }
        if (i == 4) {
            return "ATV_HAS_SIGNAL";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("DTV_NO_SIGNAL");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("DTV_HAS_SIGNAL");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("ATV_NO_SIGNAL");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("ATV_HAS_SIGNAL");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
