package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TvSourceType {
    public static final int SOURCE_TYPE_ATV = 2;
    public static final int SOURCE_TYPE_CVBS = 1;
    public static final int SOURCE_TYPE_DTV = 3;
    public static final int SOURCE_TYPE_HDMI = 0;
    public static final int SOURCE_TYPE_MAX = 8;
    public static final int SOURCE_TYPE_SCART = 7;
    public static final int SOURCE_TYPE_SVIDEO = 6;
    public static final int SOURCE_TYPE_VGA = 5;
    public static final int SOURCE_TYPE_VIDEODEC = 4;

    public static final String toString(int i) {
        if (i == 0) {
            return "SOURCE_TYPE_HDMI";
        }
        if (i == 1) {
            return "SOURCE_TYPE_CVBS";
        }
        if (i == 2) {
            return "SOURCE_TYPE_ATV";
        }
        if (i == 3) {
            return "SOURCE_TYPE_DTV";
        }
        if (i == 4) {
            return "SOURCE_TYPE_VIDEODEC";
        }
        if (i == 5) {
            return "SOURCE_TYPE_VGA";
        }
        if (i == 6) {
            return "SOURCE_TYPE_SVIDEO";
        }
        if (i == 7) {
            return "SOURCE_TYPE_SCART";
        }
        if (i == 8) {
            return "SOURCE_TYPE_MAX";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SOURCE_TYPE_HDMI");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("SOURCE_TYPE_CVBS");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("SOURCE_TYPE_ATV");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("SOURCE_TYPE_DTV");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("SOURCE_TYPE_VIDEODEC");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("SOURCE_TYPE_VGA");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("SOURCE_TYPE_SVIDEO");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("SOURCE_TYPE_SCART");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("SOURCE_TYPE_MAX");
            i2 |= 8;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
