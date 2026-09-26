package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class VideoRangeMode {
    public static final int VIDEO_RANGE_MODE_AUTO = 0;
    public static final int VIDEO_RANGE_MODE_FULL = 2;
    public static final int VIDEO_RANGE_MODE_LIMIT = 1;
    public static final int VIDEO_RANGE_MODE_MAX = 3;

    public static final String toString(int i) {
        if (i == 0) {
            return "VIDEO_RANGE_MODE_AUTO";
        }
        if (i == 1) {
            return "VIDEO_RANGE_MODE_LIMIT";
        }
        if (i == 2) {
            return "VIDEO_RANGE_MODE_FULL";
        }
        if (i == 3) {
            return "VIDEO_RANGE_MODE_MAX";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("VIDEO_RANGE_MODE_AUTO");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("VIDEO_RANGE_MODE_LIMIT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("VIDEO_RANGE_MODE_FULL");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("VIDEO_RANGE_MODE_MAX");
            i2 |= 3;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
