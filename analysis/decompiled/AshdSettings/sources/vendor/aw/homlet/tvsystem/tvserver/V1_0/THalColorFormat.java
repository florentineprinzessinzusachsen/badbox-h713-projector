package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class THalColorFormat {
    public static final int kHalColorFormat_Max = 15;
    public static final int kHalColorFormat_RGB_101010 = 12;
    public static final int kHalColorFormat_RGB_1088 = 11;
    public static final int kHalColorFormat_RGB_121212 = 13;
    public static final int kHalColorFormat_RGB_565 = 14;
    public static final int kHalColorFormat_YUV420_101010 = 2;
    public static final int kHalColorFormat_YUV420_1088 = 1;
    public static final int kHalColorFormat_YUV420_121212 = 3;
    public static final int kHalColorFormat_YUV420_888 = 0;
    public static final int kHalColorFormat_YUV422_101010 = 6;
    public static final int kHalColorFormat_YUV422_1088 = 5;
    public static final int kHalColorFormat_YUV422_121212 = 7;
    public static final int kHalColorFormat_YUV422_888 = 4;
    public static final int kHalColorFormat_YUV444_101010 = 9;
    public static final int kHalColorFormat_YUV444_1088 = 8;
    public static final int kHalColorFormat_YUV444_121212 = 10;

    public static final String toString(int i) {
        if (i == 0) {
            return "kHalColorFormat_YUV420_888";
        }
        if (i == 1) {
            return "kHalColorFormat_YUV420_1088";
        }
        if (i == 2) {
            return "kHalColorFormat_YUV420_101010";
        }
        if (i == 3) {
            return "kHalColorFormat_YUV420_121212";
        }
        if (i == 4) {
            return "kHalColorFormat_YUV422_888";
        }
        if (i == 5) {
            return "kHalColorFormat_YUV422_1088";
        }
        if (i == 6) {
            return "kHalColorFormat_YUV422_101010";
        }
        if (i == 7) {
            return "kHalColorFormat_YUV422_121212";
        }
        if (i == 8) {
            return "kHalColorFormat_YUV444_1088";
        }
        if (i == 9) {
            return "kHalColorFormat_YUV444_101010";
        }
        if (i == 10) {
            return "kHalColorFormat_YUV444_121212";
        }
        if (i == 11) {
            return "kHalColorFormat_RGB_1088";
        }
        if (i == 12) {
            return "kHalColorFormat_RGB_101010";
        }
        if (i == 13) {
            return "kHalColorFormat_RGB_121212";
        }
        if (i == 14) {
            return "kHalColorFormat_RGB_565";
        }
        if (i == 15) {
            return "kHalColorFormat_Max";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("kHalColorFormat_YUV420_888");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("kHalColorFormat_YUV420_1088");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("kHalColorFormat_YUV420_101010");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("kHalColorFormat_YUV420_121212");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("kHalColorFormat_YUV422_888");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("kHalColorFormat_YUV422_1088");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("kHalColorFormat_YUV422_101010");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("kHalColorFormat_YUV422_121212");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("kHalColorFormat_YUV444_1088");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("kHalColorFormat_YUV444_101010");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("kHalColorFormat_YUV444_121212");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("kHalColorFormat_RGB_1088");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("kHalColorFormat_RGB_101010");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("kHalColorFormat_RGB_121212");
            i2 |= 13;
        }
        if ((i & 14) == 14) {
            arrayList.add("kHalColorFormat_RGB_565");
            i2 |= 14;
        }
        if ((i & 15) == 15) {
            arrayList.add("kHalColorFormat_Max");
            i2 |= 15;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
