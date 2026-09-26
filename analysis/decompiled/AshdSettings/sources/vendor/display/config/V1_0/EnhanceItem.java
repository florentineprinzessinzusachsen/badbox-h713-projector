package vendor.display.config.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class EnhanceItem {
    public static final int ENHANCE_BRIGHT = 1;
    public static final int ENHANCE_CONTRAST = 2;
    public static final int ENHANCE_DENOISE = 3;
    public static final int ENHANCE_DETAIL = 4;
    public static final int ENHANCE_EDGE = 5;
    public static final int ENHANCE_MODE = 0;
    public static final int ENHANCE_SATURATION = 6;

    public static final String toString(int i) {
        if (i == 0) {
            return "ENHANCE_MODE";
        }
        if (i == 1) {
            return "ENHANCE_BRIGHT";
        }
        if (i == 2) {
            return "ENHANCE_CONTRAST";
        }
        if (i == 3) {
            return "ENHANCE_DENOISE";
        }
        if (i == 4) {
            return "ENHANCE_DETAIL";
        }
        if (i == 5) {
            return "ENHANCE_EDGE";
        }
        if (i == 6) {
            return "ENHANCE_SATURATION";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("ENHANCE_MODE");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("ENHANCE_BRIGHT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("ENHANCE_CONTRAST");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("ENHANCE_DENOISE");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("ENHANCE_DETAIL");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("ENHANCE_EDGE");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("ENHANCE_SATURATION");
            i2 |= 6;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
