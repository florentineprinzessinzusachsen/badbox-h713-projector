package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ScreenDisplayAspectRatio {
    public static final int DISPLAY_ASPECT_RATIO_16_9 = 1;
    public static final int DISPLAY_ASPECT_RATIO_4_3 = 2;
    public static final int DISPLAY_ASPECT_RATIO_AUTO = 5;
    public static final int DISPLAY_ASPECT_RATIO_CINEMA = 3;
    public static final int DISPLAY_ASPECT_RATIO_DOT_BY_DOT = 6;
    public static final int DISPLAY_ASPECT_RATIO_FULL = 0;
    public static final int DISPLAY_ASPECT_RATIO_MAX = 7;
    public static final int DISPLAY_ASPECT_RATIO_ZOOM = 4;

    public static final String toString(int i) {
        if (i == 0) {
            return "DISPLAY_ASPECT_RATIO_FULL";
        }
        if (i == 1) {
            return "DISPLAY_ASPECT_RATIO_16_9";
        }
        if (i == 2) {
            return "DISPLAY_ASPECT_RATIO_4_3";
        }
        if (i == 3) {
            return "DISPLAY_ASPECT_RATIO_CINEMA";
        }
        if (i == 4) {
            return "DISPLAY_ASPECT_RATIO_ZOOM";
        }
        if (i == 5) {
            return "DISPLAY_ASPECT_RATIO_AUTO";
        }
        if (i == 6) {
            return "DISPLAY_ASPECT_RATIO_DOT_BY_DOT";
        }
        if (i == 7) {
            return "DISPLAY_ASPECT_RATIO_MAX";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("DISPLAY_ASPECT_RATIO_FULL");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("DISPLAY_ASPECT_RATIO_16_9");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("DISPLAY_ASPECT_RATIO_4_3");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("DISPLAY_ASPECT_RATIO_CINEMA");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("DISPLAY_ASPECT_RATIO_ZOOM");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("DISPLAY_ASPECT_RATIO_AUTO");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("DISPLAY_ASPECT_RATIO_DOT_BY_DOT");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("DISPLAY_ASPECT_RATIO_MAX");
            i2 |= 7;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
