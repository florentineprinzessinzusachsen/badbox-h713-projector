package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TvSignalID {
    public static final int SIGNALID_1080I = 43;
    public static final int SIGNALID_1080P = 44;
    public static final int SIGNALID_1440P2560 = 45;
    public static final int SIGNALID_2160P1920 = 46;
    public static final int SIGNALID_2160P3840 = 47;
    public static final int SIGNALID_2160P4096 = 48;
    public static final int SIGNALID_240P1440 = 36;
    public static final int SIGNALID_240P720 = 35;
    public static final int SIGNALID_288P1440 = 38;
    public static final int SIGNALID_288P720 = 37;
    public static final int SIGNALID_480I = 32;
    public static final int SIGNALID_480P = 33;
    public static final int SIGNALID_480P640 = 34;
    public static final int SIGNALID_576I = 39;
    public static final int SIGNALID_576P = 40;
    public static final int SIGNALID_720P = 41;
    public static final int SIGNALID_960P = 42;
    public static final int SIGNALID_ATV_NTSC = 20;
    public static final int SIGNALID_ATV_NTSC443 = 21;
    public static final int SIGNALID_ATV_PAL = 16;
    public static final int SIGNALID_ATV_PAL60 = 19;
    public static final int SIGNALID_ATV_PALM = 17;
    public static final int SIGNALID_ATV_PALNC = 18;
    public static final int SIGNALID_ATV_SECAM = 22;
    public static final int SIGNALID_MAX = 256;
    public static final int SIGNALID_NOCHANGE = 2;
    public static final int SIGNALID_NOSIGNAL = 0;
    public static final int SIGNALID_PC_1024_768 = 69;
    public static final int SIGNALID_PC_1152_864 = 70;
    public static final int SIGNALID_PC_1280_1024 = 75;
    public static final int SIGNALID_PC_1280_720 = 71;
    public static final int SIGNALID_PC_1280_768 = 72;
    public static final int SIGNALID_PC_1280_800 = 73;
    public static final int SIGNALID_PC_1280_960 = 74;
    public static final int SIGNALID_PC_1360_768 = 76;
    public static final int SIGNALID_PC_1366_768 = 77;
    public static final int SIGNALID_PC_1400_1050 = 79;
    public static final int SIGNALID_PC_1440_900 = 78;
    public static final int SIGNALID_PC_1680_1050 = 84;
    public static final int SIGNALID_PC_1920_1080 = 80;
    public static final int SIGNALID_PC_2560_1440 = 81;
    public static final int SIGNALID_PC_3840_2160 = 82;
    public static final int SIGNALID_PC_4096_2160 = 83;
    public static final int SIGNALID_PC_640_480 = 64;
    public static final int SIGNALID_PC_720_400 = 65;
    public static final int SIGNALID_PC_720_480 = 66;
    public static final int SIGNALID_PC_800_600 = 67;
    public static final int SIGNALID_PC_960_600 = 68;
    public static final int SIGNALID_SCART_MAC1152870 = 145;
    public static final int SIGNALID_SCART_MAC640480 = 144;
    public static final int SIGNALID_SCART_MAC832624 = 146;
    public static final int SIGNALID_SCART_NTSC = 128;
    public static final int SIGNALID_SCART_PAL = 129;
    public static final int SIGNALID_SCART_SECAM = 130;
    public static final int SIGNALID_UNKNOW = 1;

    public static final String toString(int i) {
        if (i == 0) {
            return "SIGNALID_NOSIGNAL";
        }
        if (i == 1) {
            return "SIGNALID_UNKNOW";
        }
        if (i == 2) {
            return "SIGNALID_NOCHANGE";
        }
        if (i == 16) {
            return "SIGNALID_ATV_PAL";
        }
        if (i == 17) {
            return "SIGNALID_ATV_PALM";
        }
        if (i == 18) {
            return "SIGNALID_ATV_PALNC";
        }
        if (i == 19) {
            return "SIGNALID_ATV_PAL60";
        }
        if (i == 20) {
            return "SIGNALID_ATV_NTSC";
        }
        if (i == 21) {
            return "SIGNALID_ATV_NTSC443";
        }
        if (i == 22) {
            return "SIGNALID_ATV_SECAM";
        }
        if (i == 32) {
            return "SIGNALID_480I";
        }
        if (i == 33) {
            return "SIGNALID_480P";
        }
        if (i == 34) {
            return "SIGNALID_480P640";
        }
        if (i == 35) {
            return "SIGNALID_240P720";
        }
        if (i == 36) {
            return "SIGNALID_240P1440";
        }
        if (i == 37) {
            return "SIGNALID_288P720";
        }
        if (i == 38) {
            return "SIGNALID_288P1440";
        }
        if (i == 39) {
            return "SIGNALID_576I";
        }
        if (i == 40) {
            return "SIGNALID_576P";
        }
        if (i == 41) {
            return "SIGNALID_720P";
        }
        if (i == 42) {
            return "SIGNALID_960P";
        }
        if (i == 43) {
            return "SIGNALID_1080I";
        }
        if (i == 44) {
            return "SIGNALID_1080P";
        }
        if (i == 45) {
            return "SIGNALID_1440P2560";
        }
        if (i == 46) {
            return "SIGNALID_2160P1920";
        }
        if (i == 47) {
            return "SIGNALID_2160P3840";
        }
        if (i == 48) {
            return "SIGNALID_2160P4096";
        }
        if (i == 64) {
            return "SIGNALID_PC_640_480";
        }
        if (i == 65) {
            return "SIGNALID_PC_720_400";
        }
        if (i == 66) {
            return "SIGNALID_PC_720_480";
        }
        if (i == 67) {
            return "SIGNALID_PC_800_600";
        }
        if (i == 68) {
            return "SIGNALID_PC_960_600";
        }
        if (i == 69) {
            return "SIGNALID_PC_1024_768";
        }
        if (i == 70) {
            return "SIGNALID_PC_1152_864";
        }
        if (i == 71) {
            return "SIGNALID_PC_1280_720";
        }
        if (i == 72) {
            return "SIGNALID_PC_1280_768";
        }
        if (i == 73) {
            return "SIGNALID_PC_1280_800";
        }
        if (i == 74) {
            return "SIGNALID_PC_1280_960";
        }
        if (i == 75) {
            return "SIGNALID_PC_1280_1024";
        }
        if (i == 76) {
            return "SIGNALID_PC_1360_768";
        }
        if (i == 77) {
            return "SIGNALID_PC_1366_768";
        }
        if (i == 78) {
            return "SIGNALID_PC_1440_900";
        }
        if (i == 79) {
            return "SIGNALID_PC_1400_1050";
        }
        if (i == 80) {
            return "SIGNALID_PC_1920_1080";
        }
        if (i == 81) {
            return "SIGNALID_PC_2560_1440";
        }
        if (i == 82) {
            return "SIGNALID_PC_3840_2160";
        }
        if (i == 83) {
            return "SIGNALID_PC_4096_2160";
        }
        if (i == 84) {
            return "SIGNALID_PC_1680_1050";
        }
        if (i == 128) {
            return "SIGNALID_SCART_NTSC";
        }
        if (i == 129) {
            return "SIGNALID_SCART_PAL";
        }
        if (i == 130) {
            return "SIGNALID_SCART_SECAM";
        }
        if (i == 144) {
            return "SIGNALID_SCART_MAC640480";
        }
        if (i == 145) {
            return "SIGNALID_SCART_MAC1152870";
        }
        if (i == 146) {
            return "SIGNALID_SCART_MAC832624";
        }
        if (i == 256) {
            return "SIGNALID_MAX";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SIGNALID_NOSIGNAL");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("SIGNALID_UNKNOW");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("SIGNALID_NOCHANGE");
            i2 |= 2;
        }
        if ((i & 16) == 16) {
            arrayList.add("SIGNALID_ATV_PAL");
            i2 |= 16;
        }
        if ((i & 17) == 17) {
            arrayList.add("SIGNALID_ATV_PALM");
            i2 |= 17;
        }
        if ((i & 18) == 18) {
            arrayList.add("SIGNALID_ATV_PALNC");
            i2 |= 18;
        }
        if ((i & 19) == 19) {
            arrayList.add("SIGNALID_ATV_PAL60");
            i2 |= 19;
        }
        if ((i & 20) == 20) {
            arrayList.add("SIGNALID_ATV_NTSC");
            i2 |= 20;
        }
        if ((i & 21) == 21) {
            arrayList.add("SIGNALID_ATV_NTSC443");
            i2 |= 21;
        }
        if ((i & 22) == 22) {
            arrayList.add("SIGNALID_ATV_SECAM");
            i2 |= 22;
        }
        if ((i & 32) == 32) {
            arrayList.add("SIGNALID_480I");
            i2 |= 32;
        }
        if ((i & 33) == 33) {
            arrayList.add("SIGNALID_480P");
            i2 |= 33;
        }
        if ((i & 34) == 34) {
            arrayList.add("SIGNALID_480P640");
            i2 |= 34;
        }
        if ((i & 35) == 35) {
            arrayList.add("SIGNALID_240P720");
            i2 |= 35;
        }
        if ((i & 36) == 36) {
            arrayList.add("SIGNALID_240P1440");
            i2 |= 36;
        }
        if ((i & 37) == 37) {
            arrayList.add("SIGNALID_288P720");
            i2 |= 37;
        }
        if ((i & 38) == 38) {
            arrayList.add("SIGNALID_288P1440");
            i2 |= 38;
        }
        if ((i & 39) == 39) {
            arrayList.add("SIGNALID_576I");
            i2 |= 39;
        }
        if ((i & 40) == 40) {
            arrayList.add("SIGNALID_576P");
            i2 |= 40;
        }
        if ((i & 41) == 41) {
            arrayList.add("SIGNALID_720P");
            i2 |= 41;
        }
        if ((i & 42) == 42) {
            arrayList.add("SIGNALID_960P");
            i2 |= 42;
        }
        if ((i & 43) == 43) {
            arrayList.add("SIGNALID_1080I");
            i2 |= 43;
        }
        if ((i & 44) == 44) {
            arrayList.add("SIGNALID_1080P");
            i2 |= 44;
        }
        if ((i & 45) == 45) {
            arrayList.add("SIGNALID_1440P2560");
            i2 |= 45;
        }
        if ((i & 46) == 46) {
            arrayList.add("SIGNALID_2160P1920");
            i2 |= 46;
        }
        if ((i & 47) == 47) {
            arrayList.add("SIGNALID_2160P3840");
            i2 |= 47;
        }
        if ((i & 48) == 48) {
            arrayList.add("SIGNALID_2160P4096");
            i2 |= 48;
        }
        if ((i & 64) == 64) {
            arrayList.add("SIGNALID_PC_640_480");
            i2 |= 64;
        }
        if ((i & 65) == 65) {
            arrayList.add("SIGNALID_PC_720_400");
            i2 |= 65;
        }
        if ((i & 66) == 66) {
            arrayList.add("SIGNALID_PC_720_480");
            i2 |= 66;
        }
        if ((i & 67) == 67) {
            arrayList.add("SIGNALID_PC_800_600");
            i2 |= 67;
        }
        if ((i & 68) == 68) {
            arrayList.add("SIGNALID_PC_960_600");
            i2 |= 68;
        }
        if ((i & 69) == 69) {
            arrayList.add("SIGNALID_PC_1024_768");
            i2 |= 69;
        }
        if ((i & 70) == 70) {
            arrayList.add("SIGNALID_PC_1152_864");
            i2 |= 70;
        }
        if ((i & 71) == 71) {
            arrayList.add("SIGNALID_PC_1280_720");
            i2 |= 71;
        }
        if ((i & 72) == 72) {
            arrayList.add("SIGNALID_PC_1280_768");
            i2 |= 72;
        }
        if ((i & 73) == 73) {
            arrayList.add("SIGNALID_PC_1280_800");
            i2 |= 73;
        }
        if ((i & 74) == 74) {
            arrayList.add("SIGNALID_PC_1280_960");
            i2 |= 74;
        }
        if ((i & 75) == 75) {
            arrayList.add("SIGNALID_PC_1280_1024");
            i2 |= 75;
        }
        if ((i & 76) == 76) {
            arrayList.add("SIGNALID_PC_1360_768");
            i2 |= 76;
        }
        if ((i & 77) == 77) {
            arrayList.add("SIGNALID_PC_1366_768");
            i2 |= 77;
        }
        if ((i & 78) == 78) {
            arrayList.add("SIGNALID_PC_1440_900");
            i2 |= 78;
        }
        if ((i & 79) == 79) {
            arrayList.add("SIGNALID_PC_1400_1050");
            i2 |= 79;
        }
        if ((i & 80) == 80) {
            arrayList.add("SIGNALID_PC_1920_1080");
            i2 |= 80;
        }
        if ((i & 81) == 81) {
            arrayList.add("SIGNALID_PC_2560_1440");
            i2 |= 81;
        }
        if ((i & 82) == 82) {
            arrayList.add("SIGNALID_PC_3840_2160");
            i2 |= 82;
        }
        if ((i & 83) == 83) {
            arrayList.add("SIGNALID_PC_4096_2160");
            i2 |= 83;
        }
        if ((i & 84) == 84) {
            arrayList.add("SIGNALID_PC_1680_1050");
            i2 |= 84;
        }
        if ((i & 128) == 128) {
            arrayList.add("SIGNALID_SCART_NTSC");
            i2 |= 128;
        }
        if ((i & SIGNALID_SCART_PAL) == 129) {
            arrayList.add("SIGNALID_SCART_PAL");
            i2 |= SIGNALID_SCART_PAL;
        }
        if ((i & 130) == 130) {
            arrayList.add("SIGNALID_SCART_SECAM");
            i2 |= 130;
        }
        if ((i & SIGNALID_SCART_MAC640480) == 144) {
            arrayList.add("SIGNALID_SCART_MAC640480");
            i2 |= SIGNALID_SCART_MAC640480;
        }
        if ((i & SIGNALID_SCART_MAC1152870) == 145) {
            arrayList.add("SIGNALID_SCART_MAC1152870");
            i2 |= SIGNALID_SCART_MAC1152870;
        }
        if ((i & SIGNALID_SCART_MAC832624) == 146) {
            arrayList.add("SIGNALID_SCART_MAC832624");
            i2 |= SIGNALID_SCART_MAC832624;
        }
        if ((i & 256) == 256) {
            arrayList.add("SIGNALID_MAX");
            i2 |= 256;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
