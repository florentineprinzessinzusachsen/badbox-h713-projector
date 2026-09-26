package vendor.display.config.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class Dataspace {
    public static final int DATASPACE_MODE_AUTO = 0;
    public static final int DATASPACE_MODE_HDR = 1;
    public static final int DATASPACE_MODE_NUM = 5;
    public static final int DATASPACE_MODE_OTHER = 4;
    public static final int DATASPACE_MODE_SDR = 3;
    public static final int DATASPACE_MODE_WCG = 2;

    public static final String toString(int i) {
        if (i == 0) {
            return "DATASPACE_MODE_AUTO";
        }
        if (i == 1) {
            return "DATASPACE_MODE_HDR";
        }
        if (i == 2) {
            return "DATASPACE_MODE_WCG";
        }
        if (i == 3) {
            return "DATASPACE_MODE_SDR";
        }
        if (i == 4) {
            return "DATASPACE_MODE_OTHER";
        }
        if (i == 5) {
            return "DATASPACE_MODE_NUM";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("DATASPACE_MODE_AUTO");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("DATASPACE_MODE_HDR");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("DATASPACE_MODE_WCG");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("DATASPACE_MODE_SDR");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("DATASPACE_MODE_OTHER");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("DATASPACE_MODE_NUM");
            i2 |= 5;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
