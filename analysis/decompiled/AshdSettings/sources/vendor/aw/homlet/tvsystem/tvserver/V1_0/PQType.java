package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class PQType {
    public static final int PQ_TYPE_ASPECT_RATIO = 21;
    public static final int PQ_TYPE_BACKLIGHT = 6;
    public static final int PQ_TYPE_BACKLIGHT_ENABLE = 19;
    public static final int PQ_TYPE_BLACK_EXTENSION = 10;
    public static final int PQ_TYPE_BRIGHTNESS = 1;
    public static final int PQ_TYPE_COLOR_TEMPERATURE = 12;
    public static final int PQ_TYPE_CONTRAST = 2;
    public static final int PQ_TYPE_CVBS_PEDESTAL_MODE = 22;
    public static final int PQ_TYPE_DCI = 9;
    public static final int PQ_TYPE_DEBUG = 0;
    public static final int PQ_TYPE_DISABLE_TEST_PATTERN = 16;
    public static final int PQ_TYPE_DYNAMIC_BACKLIGHT = 13;
    public static final int PQ_TYPE_GAMMA = 11;
    public static final int PQ_TYPE_HDMI_CONTROL_PC_MODE = 20;
    public static final int PQ_TYPE_HUE = 4;
    public static final int PQ_TYPE_MAX = 32;
    public static final int PQ_TYPE_OVERSCAN_ENABLE = 18;
    public static final int PQ_TYPE_SATURATION = 3;
    public static final int PQ_TYPE_SHARPNESS = 5;
    public static final int PQ_TYPE_SMART_MODE_STATUS = 17;
    public static final int PQ_TYPE_SNR = 8;
    public static final int PQ_TYPE_TEST_PATTERN = 15;
    public static final int PQ_TYPE_TNR = 7;

    public static final String toString(int i) {
        if (i == 0) {
            return "PQ_TYPE_DEBUG";
        }
        if (i == 1) {
            return "PQ_TYPE_BRIGHTNESS";
        }
        if (i == 2) {
            return "PQ_TYPE_CONTRAST";
        }
        if (i == 3) {
            return "PQ_TYPE_SATURATION";
        }
        if (i == 4) {
            return "PQ_TYPE_HUE";
        }
        if (i == 5) {
            return "PQ_TYPE_SHARPNESS";
        }
        if (i == 6) {
            return "PQ_TYPE_BACKLIGHT";
        }
        if (i == 7) {
            return "PQ_TYPE_TNR";
        }
        if (i == 8) {
            return "PQ_TYPE_SNR";
        }
        if (i == 9) {
            return "PQ_TYPE_DCI";
        }
        if (i == 10) {
            return "PQ_TYPE_BLACK_EXTENSION";
        }
        if (i == 11) {
            return "PQ_TYPE_GAMMA";
        }
        if (i == 12) {
            return "PQ_TYPE_COLOR_TEMPERATURE";
        }
        if (i == 13) {
            return "PQ_TYPE_DYNAMIC_BACKLIGHT";
        }
        if (i == 15) {
            return "PQ_TYPE_TEST_PATTERN";
        }
        if (i == 16) {
            return "PQ_TYPE_DISABLE_TEST_PATTERN";
        }
        if (i == 17) {
            return "PQ_TYPE_SMART_MODE_STATUS";
        }
        if (i == 18) {
            return "PQ_TYPE_OVERSCAN_ENABLE";
        }
        if (i == 19) {
            return "PQ_TYPE_BACKLIGHT_ENABLE";
        }
        if (i == 20) {
            return "PQ_TYPE_HDMI_CONTROL_PC_MODE";
        }
        if (i == 21) {
            return "PQ_TYPE_ASPECT_RATIO";
        }
        if (i == 22) {
            return "PQ_TYPE_CVBS_PEDESTAL_MODE";
        }
        if (i == 32) {
            return "PQ_TYPE_MAX";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("PQ_TYPE_DEBUG");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("PQ_TYPE_BRIGHTNESS");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("PQ_TYPE_CONTRAST");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("PQ_TYPE_SATURATION");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("PQ_TYPE_HUE");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("PQ_TYPE_SHARPNESS");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("PQ_TYPE_BACKLIGHT");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("PQ_TYPE_TNR");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("PQ_TYPE_SNR");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("PQ_TYPE_DCI");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("PQ_TYPE_BLACK_EXTENSION");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("PQ_TYPE_GAMMA");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("PQ_TYPE_COLOR_TEMPERATURE");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("PQ_TYPE_DYNAMIC_BACKLIGHT");
            i2 |= 13;
        }
        if ((i & 15) == 15) {
            arrayList.add("PQ_TYPE_TEST_PATTERN");
            i2 |= 15;
        }
        if ((i & 16) == 16) {
            arrayList.add("PQ_TYPE_DISABLE_TEST_PATTERN");
            i2 |= 16;
        }
        if ((i & 17) == 17) {
            arrayList.add("PQ_TYPE_SMART_MODE_STATUS");
            i2 |= 17;
        }
        if ((i & 18) == 18) {
            arrayList.add("PQ_TYPE_OVERSCAN_ENABLE");
            i2 |= 18;
        }
        if ((i & 19) == 19) {
            arrayList.add("PQ_TYPE_BACKLIGHT_ENABLE");
            i2 |= 19;
        }
        if ((i & 20) == 20) {
            arrayList.add("PQ_TYPE_HDMI_CONTROL_PC_MODE");
            i2 |= 20;
        }
        if ((i & 21) == 21) {
            arrayList.add("PQ_TYPE_ASPECT_RATIO");
            i2 |= 21;
        }
        if ((i & 22) == 22) {
            arrayList.add("PQ_TYPE_CVBS_PEDESTAL_MODE");
            i2 |= 22;
        }
        if ((i & 32) == 32) {
            arrayList.add("PQ_TYPE_MAX");
            i2 |= 32;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
