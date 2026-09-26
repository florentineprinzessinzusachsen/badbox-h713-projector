package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TvSourceID {
    public static final int kHalSourceID_ATV = 10;
    public static final int kHalSourceID_CVBS_1 = 7;
    public static final int kHalSourceID_CVBS_2 = 8;
    public static final int kHalSourceID_CVBS_3 = 9;
    public static final int kHalSourceID_DTV = 11;
    public static final int kHalSourceID_Dummy = 0;
    public static final int kHalSourceID_HDMI_1 = 3;
    public static final int kHalSourceID_HDMI_2 = 4;
    public static final int kHalSourceID_HDMI_3 = 5;
    public static final int kHalSourceID_HDMI_4 = 6;
    public static final int kHalSourceID_Image = 2;
    public static final int kHalSourceID_Max = 12;
    public static final int kHalSourceID_VideoDec = 1;

    public static final String toString(int i) {
        if (i == 0) {
            return "kHalSourceID_Dummy";
        }
        if (i == 1) {
            return "kHalSourceID_VideoDec";
        }
        if (i == 2) {
            return "kHalSourceID_Image";
        }
        if (i == 3) {
            return "kHalSourceID_HDMI_1";
        }
        if (i == 4) {
            return "kHalSourceID_HDMI_2";
        }
        if (i == 5) {
            return "kHalSourceID_HDMI_3";
        }
        if (i == 6) {
            return "kHalSourceID_HDMI_4";
        }
        if (i == 7) {
            return "kHalSourceID_CVBS_1";
        }
        if (i == 8) {
            return "kHalSourceID_CVBS_2";
        }
        if (i == 9) {
            return "kHalSourceID_CVBS_3";
        }
        if (i == 10) {
            return "kHalSourceID_ATV";
        }
        if (i == 11) {
            return "kHalSourceID_DTV";
        }
        if (i == 12) {
            return "kHalSourceID_Max";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("kHalSourceID_Dummy");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("kHalSourceID_VideoDec");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("kHalSourceID_Image");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("kHalSourceID_HDMI_1");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("kHalSourceID_HDMI_2");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("kHalSourceID_HDMI_3");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("kHalSourceID_HDMI_4");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("kHalSourceID_CVBS_1");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("kHalSourceID_CVBS_2");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("kHalSourceID_CVBS_3");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("kHalSourceID_ATV");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("kHalSourceID_DTV");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("kHalSourceID_Max");
            i2 |= 12;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
