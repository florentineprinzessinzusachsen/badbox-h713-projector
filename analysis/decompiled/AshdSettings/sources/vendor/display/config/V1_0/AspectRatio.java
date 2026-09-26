package vendor.display.config.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AspectRatio {
    public static final int ASPECT_RATIO_16_9 = 3;
    public static final int ASPECT_RATIO_4_3 = 2;
    public static final int ASPECT_RATIO_AUTO = 0;
    public static final int ASPECT_RATIO_FULL = 1;
    public static final int ASPECT_RATIO_FULL_ONCE = 7;
    public static final int ASPECT_RATIO_MATCH_HEIGHT = 5;
    public static final int ASPECT_RATIO_MATCH_WIDTH = 4;
    public static final int ASPECT_RATIO_RATIO_LOAD = 8;

    public static final String toString(int i) {
        if (i == 0) {
            return "ASPECT_RATIO_AUTO";
        }
        if (i == 1) {
            return "ASPECT_RATIO_FULL";
        }
        if (i == 2) {
            return "ASPECT_RATIO_4_3";
        }
        if (i == 3) {
            return "ASPECT_RATIO_16_9";
        }
        if (i == 4) {
            return "ASPECT_RATIO_MATCH_WIDTH";
        }
        if (i == 5) {
            return "ASPECT_RATIO_MATCH_HEIGHT";
        }
        if (i == 7) {
            return "ASPECT_RATIO_FULL_ONCE";
        }
        if (i == 8) {
            return "ASPECT_RATIO_RATIO_LOAD";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("ASPECT_RATIO_AUTO");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("ASPECT_RATIO_FULL");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("ASPECT_RATIO_4_3");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("ASPECT_RATIO_16_9");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("ASPECT_RATIO_MATCH_WIDTH");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("ASPECT_RATIO_MATCH_HEIGHT");
            i2 |= 5;
        }
        if ((i & 7) == 7) {
            arrayList.add("ASPECT_RATIO_FULL_ONCE");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("ASPECT_RATIO_RATIO_LOAD");
            i2 |= 8;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
