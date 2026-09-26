package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class THalAtvStd {
    public static final int kHalAtvStd_All = -1;
    public static final int kHalAtvStd_NTSC443 = 2;
    public static final int kHalAtvStd_NTSCM = 1;
    public static final int kHalAtvStd_PAL50 = 8;
    public static final int kHalAtvStd_PAL60 = 4;
    public static final int kHalAtvStd_PALCN = 64;
    public static final int kHalAtvStd_PALM = 16;
    public static final int kHalAtvStd_SECAM = 32;

    public static final String toString(int i) {
        if (i == 1) {
            return "kHalAtvStd_NTSCM";
        }
        if (i == 2) {
            return "kHalAtvStd_NTSC443";
        }
        if (i == 4) {
            return "kHalAtvStd_PAL60";
        }
        if (i == 8) {
            return "kHalAtvStd_PAL50";
        }
        if (i == 16) {
            return "kHalAtvStd_PALM";
        }
        if (i == 32) {
            return "kHalAtvStd_SECAM";
        }
        if (i == 64) {
            return "kHalAtvStd_PALCN";
        }
        if (i == -1) {
            return "kHalAtvStd_All";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("kHalAtvStd_NTSCM");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("kHalAtvStd_NTSC443");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("kHalAtvStd_PAL60");
            i2 |= 4;
        }
        if ((i & 8) == 8) {
            arrayList.add("kHalAtvStd_PAL50");
            i2 |= 8;
        }
        if ((i & 16) == 16) {
            arrayList.add("kHalAtvStd_PALM");
            i2 |= 16;
        }
        if ((i & 32) == 32) {
            arrayList.add("kHalAtvStd_SECAM");
            i2 |= 32;
        }
        if ((i & 64) == 64) {
            arrayList.add("kHalAtvStd_PALCN");
            i2 |= 64;
        }
        if ((i & (-1)) == -1) {
            arrayList.add("kHalAtvStd_All");
            i2 |= -1;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
