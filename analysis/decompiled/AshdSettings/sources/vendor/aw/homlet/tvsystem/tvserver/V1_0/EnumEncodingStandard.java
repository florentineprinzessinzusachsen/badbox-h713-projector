package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class EnumEncodingStandard {
    public static final int ENCODING_STANDARD_ISO = 0;
    public static final int ENCODING_STANDARD_WINDOWS = 1;

    public static final String toString(int i) {
        if (i == 0) {
            return "ENCODING_STANDARD_ISO";
        }
        if (i == 1) {
            return "ENCODING_STANDARD_WINDOWS";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("ENCODING_STANDARD_ISO");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("ENCODING_STANDARD_WINDOWS");
        } else {
            i2 = 0;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
