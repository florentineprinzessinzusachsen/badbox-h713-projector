package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class THalAtvRegion {
    public static final int kHalAtvRegion_CN = 2;
    public static final int kHalAtvRegion_EU = 1;
    public static final int kHalAtvRegion_LA = 3;
    public static final int kHalAtvRegion_Max = 5;
    public static final int kHalAtvRegion_SA = 4;
    public static final int kHalAtvRegion_US = 0;

    public static final String toString(int i) {
        if (i == 0) {
            return "kHalAtvRegion_US";
        }
        if (i == 1) {
            return "kHalAtvRegion_EU";
        }
        if (i == 2) {
            return "kHalAtvRegion_CN";
        }
        if (i == 3) {
            return "kHalAtvRegion_LA";
        }
        if (i == 4) {
            return "kHalAtvRegion_SA";
        }
        if (i == 5) {
            return "kHalAtvRegion_Max";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("kHalAtvRegion_US");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("kHalAtvRegion_EU");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("kHalAtvRegion_CN");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("kHalAtvRegion_LA");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("kHalAtvRegion_SA");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("kHalAtvRegion_Max");
            i2 |= 5;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
