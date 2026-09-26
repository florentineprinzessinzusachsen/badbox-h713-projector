package vendor.display.config.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class PixelFormat {
    public static final int PIXEL_FORMAT_AUTO = 0;
    public static final int PIXEL_FORMAT_NUM = 5;
    public static final int PIXEL_FORMAT_RGB888_8bit = 4;
    public static final int PIXEL_FORMAT_YUV420_10bit = 2;
    public static final int PIXEL_FORMAT_YUV422_12bit = 1;
    public static final int PIXEL_FORMAT_YUV444_8bit = 3;

    public static final String toString(int i) {
        if (i == 0) {
            return "PIXEL_FORMAT_AUTO";
        }
        if (i == 1) {
            return "PIXEL_FORMAT_YUV422_12bit";
        }
        if (i == 2) {
            return "PIXEL_FORMAT_YUV420_10bit";
        }
        if (i == 3) {
            return "PIXEL_FORMAT_YUV444_8bit";
        }
        if (i == 4) {
            return "PIXEL_FORMAT_RGB888_8bit";
        }
        if (i == 5) {
            return "PIXEL_FORMAT_NUM";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("PIXEL_FORMAT_AUTO");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("PIXEL_FORMAT_YUV422_12bit");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("PIXEL_FORMAT_YUV420_10bit");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("PIXEL_FORMAT_YUV444_8bit");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("PIXEL_FORMAT_RGB888_8bit");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("PIXEL_FORMAT_NUM");
            i2 |= 5;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
