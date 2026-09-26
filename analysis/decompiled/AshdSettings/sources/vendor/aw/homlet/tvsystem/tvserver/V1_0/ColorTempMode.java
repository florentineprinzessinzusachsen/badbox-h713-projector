package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ColorTempMode {
    public static final int COLOR_TEMP_COOL = 1;
    public static final int COLOR_TEMP_MAX = 4;
    public static final int COLOR_TEMP_STANDARD = 0;
    public static final int COLOR_TEMP_USER = 3;
    public static final int COLOR_TEMP_WARM = 2;

    public static final String toString(int i) {
        if (i == 0) {
            return "COLOR_TEMP_STANDARD";
        }
        if (i == 1) {
            return "COLOR_TEMP_COOL";
        }
        if (i == 2) {
            return "COLOR_TEMP_WARM";
        }
        if (i == 3) {
            return "COLOR_TEMP_USER";
        }
        if (i == 4) {
            return "COLOR_TEMP_MAX";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("COLOR_TEMP_STANDARD");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("COLOR_TEMP_COOL");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("COLOR_TEMP_WARM");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("COLOR_TEMP_USER");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("COLOR_TEMP_MAX");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
