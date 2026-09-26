package vendor.aw.hardware.btvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ThalTvStandard {
    public static final int DRX_STANDARD_8VSB = 2;
    public static final int DRX_STANDARD_ATV_UNKNOWN = 18;
    public static final int DRX_STANDARD_AUTO = 0;
    public static final int DRX_STANDARD_DTMB = 14;
    public static final int DRX_STANDARD_DVBC2 = 17;
    public static final int DRX_STANDARD_DVBT = 1;
    public static final int DRX_STANDARD_DVBT2 = 16;
    public static final int DRX_STANDARD_FM = 13;
    public static final int DRX_STANDARD_ISDBT = 15;
    public static final int DRX_STANDARD_ITU_A = 9;
    public static final int DRX_STANDARD_ITU_B = 10;
    public static final int DRX_STANDARD_ITU_C = 11;
    public static final int DRX_STANDARD_ITU_D = 12;
    public static final int DRX_STANDARD_NTSC = 3;
    public static final int DRX_STANDARD_PAL_SECAM_BG = 4;
    public static final int DRX_STANDARD_PAL_SECAM_DK = 5;
    public static final int DRX_STANDARD_PAL_SECAM_I = 6;
    public static final int DRX_STANDARD_PAL_SECAM_L = 7;
    public static final int DRX_STANDARD_PAL_SECAM_LP = 8;
    public static final int DRX_STANDARD_UNKNOWN = 0;

    public static final String toString(int i) {
        if (i == 0) {
            return "DRX_STANDARD_UNKNOWN";
        }
        if (i == 1) {
            return "DRX_STANDARD_DVBT";
        }
        if (i == 2) {
            return "DRX_STANDARD_8VSB";
        }
        if (i == 3) {
            return "DRX_STANDARD_NTSC";
        }
        if (i == 4) {
            return "DRX_STANDARD_PAL_SECAM_BG";
        }
        if (i == 5) {
            return "DRX_STANDARD_PAL_SECAM_DK";
        }
        if (i == 6) {
            return "DRX_STANDARD_PAL_SECAM_I";
        }
        if (i == 7) {
            return "DRX_STANDARD_PAL_SECAM_L";
        }
        if (i == 8) {
            return "DRX_STANDARD_PAL_SECAM_LP";
        }
        if (i == 9) {
            return "DRX_STANDARD_ITU_A";
        }
        if (i == 10) {
            return "DRX_STANDARD_ITU_B";
        }
        if (i == 11) {
            return "DRX_STANDARD_ITU_C";
        }
        if (i == 12) {
            return "DRX_STANDARD_ITU_D";
        }
        if (i == 13) {
            return "DRX_STANDARD_FM";
        }
        if (i == 14) {
            return "DRX_STANDARD_DTMB";
        }
        if (i == 15) {
            return "DRX_STANDARD_ISDBT";
        }
        if (i == 16) {
            return "DRX_STANDARD_DVBT2";
        }
        if (i == 17) {
            return "DRX_STANDARD_DVBC2";
        }
        if (i == 18) {
            return "DRX_STANDARD_ATV_UNKNOWN";
        }
        if (i == 0) {
            return "DRX_STANDARD_AUTO";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("DRX_STANDARD_UNKNOWN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("DRX_STANDARD_DVBT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("DRX_STANDARD_8VSB");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("DRX_STANDARD_NTSC");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("DRX_STANDARD_PAL_SECAM_BG");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("DRX_STANDARD_PAL_SECAM_DK");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("DRX_STANDARD_PAL_SECAM_I");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("DRX_STANDARD_PAL_SECAM_L");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("DRX_STANDARD_PAL_SECAM_LP");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("DRX_STANDARD_ITU_A");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("DRX_STANDARD_ITU_B");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("DRX_STANDARD_ITU_C");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("DRX_STANDARD_ITU_D");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("DRX_STANDARD_FM");
            i2 |= 13;
        }
        if ((i & 14) == 14) {
            arrayList.add("DRX_STANDARD_DTMB");
            i2 |= 14;
        }
        if ((i & 15) == 15) {
            arrayList.add("DRX_STANDARD_ISDBT");
            i2 |= 15;
        }
        if ((i & 16) == 16) {
            arrayList.add("DRX_STANDARD_DVBT2");
            i2 |= 16;
        }
        if ((i & 17) == 17) {
            arrayList.add("DRX_STANDARD_DVBC2");
            i2 |= 17;
        }
        if ((i & 18) == 18) {
            arrayList.add("DRX_STANDARD_ATV_UNKNOWN");
            i2 |= 18;
        }
        arrayList.add("DRX_STANDARD_AUTO");
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
