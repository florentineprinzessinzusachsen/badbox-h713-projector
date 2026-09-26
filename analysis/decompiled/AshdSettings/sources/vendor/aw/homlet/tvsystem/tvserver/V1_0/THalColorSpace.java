package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class THalColorSpace {
    public static final int kHalColorSpace_BT2020_CLYCC = 3;
    public static final int kHalColorSpace_BT2020_NCLYCC = 2;
    public static final int kHalColorSpace_BT601 = 0;
    public static final int kHalColorSpace_BT709 = 1;
    public static final int kHalColorSpace_Max = 6;
    public static final int kHalColorSpace_RGB = 5;
    public static final int kHalColorSpace_xvYCC = 4;

    public static final String toString(int i) {
        if (i == 0) {
            return "kHalColorSpace_BT601";
        }
        if (i == 1) {
            return "kHalColorSpace_BT709";
        }
        if (i == 2) {
            return "kHalColorSpace_BT2020_NCLYCC";
        }
        if (i == 3) {
            return "kHalColorSpace_BT2020_CLYCC";
        }
        if (i == 4) {
            return "kHalColorSpace_xvYCC";
        }
        if (i == 5) {
            return "kHalColorSpace_RGB";
        }
        if (i == 6) {
            return "kHalColorSpace_Max";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("kHalColorSpace_BT601");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("kHalColorSpace_BT709");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("kHalColorSpace_BT2020_NCLYCC");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("kHalColorSpace_BT2020_CLYCC");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("kHalColorSpace_xvYCC");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("kHalColorSpace_RGB");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("kHalColorSpace_Max");
            i2 |= 6;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
