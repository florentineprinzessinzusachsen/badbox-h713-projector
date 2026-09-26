package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioDevice {
    public static final int BIT_DEFAULT = 1073741824;
    public static final int BIT_IN = Integer.MIN_VALUE;
    public static final int IN_ALL = -1021313025;
    public static final int IN_ALL_SCO = -2147483640;
    public static final int IN_ALL_USB = -2113923072;
    public static final int IN_AMBIENT = -2147483646;
    public static final int IN_ANLG_DOCK_HEADSET = -2147483136;
    public static final int IN_AUX_DIGITAL = -2147483616;
    public static final int IN_BACK_MIC = -2147483520;
    public static final int IN_BLUETOOTH_A2DP = -2147352576;
    public static final int IN_BLUETOOTH_SCO_HEADSET = -2147483640;
    public static final int IN_BUILTIN_MIC = -2147483644;
    public static final int IN_BUS = -2146435072;
    public static final int IN_COMMUNICATION = -2147483647;
    public static final int IN_DEFAULT = -1073741824;
    public static final int IN_DGTL_DOCK_HEADSET = -2147482624;
    public static final int IN_FM_TUNER = -2147475456;
    public static final int IN_HDMI = -2147483616;
    public static final int IN_IP = -2146959360;
    public static final int IN_LINE = -2147450880;
    public static final int IN_LOOPBACK = -2147221504;
    public static final int IN_PROXY = -2130706432;
    public static final int IN_REMOTE_SUBMIX = -2147483392;
    public static final int IN_SPDIF = -2147418112;
    public static final int IN_TELEPHONY_RX = -2147483584;
    public static final int IN_TV_TUNER = -2147467264;
    public static final int IN_USB_ACCESSORY = -2147481600;
    public static final int IN_USB_DEVICE = -2147479552;
    public static final int IN_USB_HEADSET = -2113929216;
    public static final int IN_VOICE_CALL = -2147483584;
    public static final int IN_WIRED_HEADSET = -2147483632;
    public static final int NONE = 0;
    public static final int OUT_ALL = 1207959551;
    public static final int OUT_ALL_A2DP = 896;
    public static final int OUT_ALL_SCO = 112;
    public static final int OUT_ALL_USB = 67133440;
    public static final int OUT_ANLG_DOCK_HEADSET = 2048;
    public static final int OUT_AUX_DIGITAL = 1024;
    public static final int OUT_AUX_LINE = 2097152;
    public static final int OUT_BLUETOOTH_A2DP = 128;
    public static final int OUT_BLUETOOTH_A2DP_HEADPHONES = 256;
    public static final int OUT_BLUETOOTH_A2DP_SPEAKER = 512;
    public static final int OUT_BLUETOOTH_SCO = 16;
    public static final int OUT_BLUETOOTH_SCO_CARKIT = 64;
    public static final int OUT_BLUETOOTH_SCO_HEADSET = 32;
    public static final int OUT_BUS = 16777216;
    public static final int OUT_DEFAULT = 1073741824;
    public static final int OUT_DGTL_DOCK_HEADSET = 4096;
    public static final int OUT_EARPIECE = 1;
    public static final int OUT_FM = 1048576;
    public static final int OUT_HDMI = 1024;
    public static final int OUT_HDMI_ARC = 262144;
    public static final int OUT_IP = 8388608;
    public static final int OUT_LINE = 131072;
    public static final int OUT_PROXY = 33554432;
    public static final int OUT_REMOTE_SUBMIX = 32768;
    public static final int OUT_SPDIF = 524288;
    public static final int OUT_SPEAKER = 2;
    public static final int OUT_SPEAKER_SAFE = 4194304;
    public static final int OUT_TELEPHONY_TX = 65536;
    public static final int OUT_USB_ACCESSORY = 8192;
    public static final int OUT_USB_DEVICE = 16384;
    public static final int OUT_USB_HEADSET = 67108864;
    public static final int OUT_WIRED_HEADPHONE = 8;
    public static final int OUT_WIRED_HEADSET = 4;

    public static final String toString(int i) {
        if (i == 0) {
            return "NONE";
        }
        if (i == Integer.MIN_VALUE) {
            return "BIT_IN";
        }
        if (i == 1073741824) {
            return "BIT_DEFAULT";
        }
        if (i == 1) {
            return "OUT_EARPIECE";
        }
        if (i == 2) {
            return "OUT_SPEAKER";
        }
        if (i == 4) {
            return "OUT_WIRED_HEADSET";
        }
        if (i == 8) {
            return "OUT_WIRED_HEADPHONE";
        }
        if (i == 16) {
            return "OUT_BLUETOOTH_SCO";
        }
        if (i == 32) {
            return "OUT_BLUETOOTH_SCO_HEADSET";
        }
        if (i == 64) {
            return "OUT_BLUETOOTH_SCO_CARKIT";
        }
        if (i == 128) {
            return "OUT_BLUETOOTH_A2DP";
        }
        if (i == 256) {
            return "OUT_BLUETOOTH_A2DP_HEADPHONES";
        }
        if (i == 512) {
            return "OUT_BLUETOOTH_A2DP_SPEAKER";
        }
        if (i == 1024) {
            return "OUT_AUX_DIGITAL";
        }
        if (i == 1024) {
            return "OUT_HDMI";
        }
        if (i == 2048) {
            return "OUT_ANLG_DOCK_HEADSET";
        }
        if (i == 4096) {
            return "OUT_DGTL_DOCK_HEADSET";
        }
        if (i == 8192) {
            return "OUT_USB_ACCESSORY";
        }
        if (i == 16384) {
            return "OUT_USB_DEVICE";
        }
        if (i == 32768) {
            return "OUT_REMOTE_SUBMIX";
        }
        if (i == 65536) {
            return "OUT_TELEPHONY_TX";
        }
        if (i == 131072) {
            return "OUT_LINE";
        }
        if (i == 262144) {
            return "OUT_HDMI_ARC";
        }
        if (i == 524288) {
            return "OUT_SPDIF";
        }
        if (i == 1048576) {
            return "OUT_FM";
        }
        if (i == 2097152) {
            return "OUT_AUX_LINE";
        }
        if (i == 4194304) {
            return "OUT_SPEAKER_SAFE";
        }
        if (i == 8388608) {
            return "OUT_IP";
        }
        if (i == 16777216) {
            return "OUT_BUS";
        }
        if (i == 33554432) {
            return "OUT_PROXY";
        }
        if (i == 67108864) {
            return "OUT_USB_HEADSET";
        }
        if (i == 1073741824) {
            return "OUT_DEFAULT";
        }
        if (i == 1207959551) {
            return "OUT_ALL";
        }
        if (i == 896) {
            return "OUT_ALL_A2DP";
        }
        if (i == 112) {
            return "OUT_ALL_SCO";
        }
        if (i == 67133440) {
            return "OUT_ALL_USB";
        }
        if (i == -2147483647) {
            return "IN_COMMUNICATION";
        }
        if (i == -2147483646) {
            return "IN_AMBIENT";
        }
        if (i == -2147483644) {
            return "IN_BUILTIN_MIC";
        }
        if (i == -2147483640) {
            return "IN_BLUETOOTH_SCO_HEADSET";
        }
        if (i == -2147483632) {
            return "IN_WIRED_HEADSET";
        }
        if (i == -2147483616) {
            return "IN_AUX_DIGITAL";
        }
        if (i == -2147483616) {
            return "IN_HDMI";
        }
        if (i == -2147483584) {
            return "IN_VOICE_CALL";
        }
        if (i == -2147483584) {
            return "IN_TELEPHONY_RX";
        }
        if (i == -2147483520) {
            return "IN_BACK_MIC";
        }
        if (i == -2147483392) {
            return "IN_REMOTE_SUBMIX";
        }
        if (i == -2147483136) {
            return "IN_ANLG_DOCK_HEADSET";
        }
        if (i == -2147482624) {
            return "IN_DGTL_DOCK_HEADSET";
        }
        if (i == -2147481600) {
            return "IN_USB_ACCESSORY";
        }
        if (i == -2147479552) {
            return "IN_USB_DEVICE";
        }
        if (i == -2147475456) {
            return "IN_FM_TUNER";
        }
        if (i == -2147467264) {
            return "IN_TV_TUNER";
        }
        if (i == -2147450880) {
            return "IN_LINE";
        }
        if (i == -2147418112) {
            return "IN_SPDIF";
        }
        if (i == -2147352576) {
            return "IN_BLUETOOTH_A2DP";
        }
        if (i == -2147221504) {
            return "IN_LOOPBACK";
        }
        if (i == -2146959360) {
            return "IN_IP";
        }
        if (i == -2146435072) {
            return "IN_BUS";
        }
        if (i == -2130706432) {
            return "IN_PROXY";
        }
        if (i == -2113929216) {
            return "IN_USB_HEADSET";
        }
        if (i == -1073741824) {
            return "IN_DEFAULT";
        }
        if (i == -1021313025) {
            return "IN_ALL";
        }
        if (i == -2147483640) {
            return "IN_ALL_SCO";
        }
        if (i == -2113923072) {
            return "IN_ALL_USB";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("NONE");
        int i2 = Integer.MIN_VALUE;
        if ((i & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            arrayList.add("BIT_IN");
        } else {
            i2 = 0;
        }
        int i3 = i & 1073741824;
        if (i3 == 1073741824) {
            arrayList.add("BIT_DEFAULT");
            i2 |= 1073741824;
        }
        if ((i & 1) == 1) {
            arrayList.add("OUT_EARPIECE");
            i2 |= 1;
        }
        if ((i & 2) == 2) {
            arrayList.add("OUT_SPEAKER");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("OUT_WIRED_HEADSET");
            i2 |= 4;
        }
        if ((i & 8) == 8) {
            arrayList.add("OUT_WIRED_HEADPHONE");
            i2 |= 8;
        }
        if ((i & 16) == 16) {
            arrayList.add("OUT_BLUETOOTH_SCO");
            i2 |= 16;
        }
        if ((i & 32) == 32) {
            arrayList.add("OUT_BLUETOOTH_SCO_HEADSET");
            i2 |= 32;
        }
        if ((i & 64) == 64) {
            arrayList.add("OUT_BLUETOOTH_SCO_CARKIT");
            i2 |= 64;
        }
        if ((i & 128) == 128) {
            arrayList.add("OUT_BLUETOOTH_A2DP");
            i2 |= 128;
        }
        if ((i & 256) == 256) {
            arrayList.add("OUT_BLUETOOTH_A2DP_HEADPHONES");
            i2 |= 256;
        }
        if ((i & 512) == 512) {
            arrayList.add("OUT_BLUETOOTH_A2DP_SPEAKER");
            i2 |= 512;
        }
        int i4 = i & 1024;
        if (i4 == 1024) {
            arrayList.add("OUT_AUX_DIGITAL");
            i2 |= 1024;
        }
        if (i4 == 1024) {
            arrayList.add("OUT_HDMI");
            i2 |= 1024;
        }
        if ((i & 2048) == 2048) {
            arrayList.add("OUT_ANLG_DOCK_HEADSET");
            i2 |= 2048;
        }
        if ((i & 4096) == 4096) {
            arrayList.add("OUT_DGTL_DOCK_HEADSET");
            i2 |= 4096;
        }
        if ((i & 8192) == 8192) {
            arrayList.add("OUT_USB_ACCESSORY");
            i2 |= 8192;
        }
        if ((i & 16384) == 16384) {
            arrayList.add("OUT_USB_DEVICE");
            i2 |= 16384;
        }
        if ((32768 & i) == 32768) {
            arrayList.add("OUT_REMOTE_SUBMIX");
            i2 |= 32768;
        }
        if ((65536 & i) == 65536) {
            arrayList.add("OUT_TELEPHONY_TX");
            i2 |= 65536;
        }
        if ((131072 & i) == 131072) {
            arrayList.add("OUT_LINE");
            i2 |= 131072;
        }
        if ((262144 & i) == 262144) {
            arrayList.add("OUT_HDMI_ARC");
            i2 |= 262144;
        }
        if ((524288 & i) == 524288) {
            arrayList.add("OUT_SPDIF");
            i2 |= 524288;
        }
        if ((1048576 & i) == 1048576) {
            arrayList.add("OUT_FM");
            i2 |= 1048576;
        }
        if ((2097152 & i) == 2097152) {
            arrayList.add("OUT_AUX_LINE");
            i2 |= 2097152;
        }
        if ((4194304 & i) == 4194304) {
            arrayList.add("OUT_SPEAKER_SAFE");
            i2 |= 4194304;
        }
        if ((8388608 & i) == 8388608) {
            arrayList.add("OUT_IP");
            i2 |= 8388608;
        }
        if ((16777216 & i) == 16777216) {
            arrayList.add("OUT_BUS");
            i2 |= 16777216;
        }
        if ((33554432 & i) == 33554432) {
            arrayList.add("OUT_PROXY");
            i2 |= 33554432;
        }
        if ((67108864 & i) == 67108864) {
            arrayList.add("OUT_USB_HEADSET");
            i2 |= 67108864;
        }
        if (i3 == 1073741824) {
            arrayList.add("OUT_DEFAULT");
            i2 |= 1073741824;
        }
        if ((1207959551 & i) == 1207959551) {
            arrayList.add("OUT_ALL");
            i2 |= OUT_ALL;
        }
        if ((i & OUT_ALL_A2DP) == 896) {
            arrayList.add("OUT_ALL_A2DP");
            i2 |= OUT_ALL_A2DP;
        }
        if ((i & 112) == 112) {
            arrayList.add("OUT_ALL_SCO");
            i2 |= 112;
        }
        if ((67133440 & i) == 67133440) {
            arrayList.add("OUT_ALL_USB");
            i2 |= OUT_ALL_USB;
        }
        if (((-2147483647) & i) == -2147483647) {
            arrayList.add("IN_COMMUNICATION");
            i2 |= -2147483647;
        }
        if (((-2147483646) & i) == -2147483646) {
            arrayList.add("IN_AMBIENT");
            i2 |= IN_AMBIENT;
        }
        if (((-2147483644) & i) == -2147483644) {
            arrayList.add("IN_BUILTIN_MIC");
            i2 |= IN_BUILTIN_MIC;
        }
        int i5 = i & (-2147483640);
        if (i5 == -2147483640) {
            arrayList.add("IN_BLUETOOTH_SCO_HEADSET");
            i2 |= -2147483640;
        }
        if (((-2147483632) & i) == -2147483632) {
            arrayList.add("IN_WIRED_HEADSET");
            i2 |= IN_WIRED_HEADSET;
        }
        int i6 = i & (-2147483616);
        if (i6 == -2147483616) {
            arrayList.add("IN_AUX_DIGITAL");
            i2 |= -2147483616;
        }
        if (i6 == -2147483616) {
            arrayList.add("IN_HDMI");
            i2 |= -2147483616;
        }
        int i7 = i & (-2147483584);
        if (i7 == -2147483584) {
            arrayList.add("IN_VOICE_CALL");
            i2 |= -2147483584;
        }
        if (i7 == -2147483584) {
            arrayList.add("IN_TELEPHONY_RX");
            i2 |= -2147483584;
        }
        if (((-2147483520) & i) == -2147483520) {
            arrayList.add("IN_BACK_MIC");
            i2 |= IN_BACK_MIC;
        }
        if (((-2147483392) & i) == -2147483392) {
            arrayList.add("IN_REMOTE_SUBMIX");
            i2 |= IN_REMOTE_SUBMIX;
        }
        if (((-2147483136) & i) == -2147483136) {
            arrayList.add("IN_ANLG_DOCK_HEADSET");
            i2 |= IN_ANLG_DOCK_HEADSET;
        }
        if (((-2147482624) & i) == -2147482624) {
            arrayList.add("IN_DGTL_DOCK_HEADSET");
            i2 |= IN_DGTL_DOCK_HEADSET;
        }
        if (((-2147481600) & i) == -2147481600) {
            arrayList.add("IN_USB_ACCESSORY");
            i2 |= IN_USB_ACCESSORY;
        }
        if (((-2147479552) & i) == -2147479552) {
            arrayList.add("IN_USB_DEVICE");
            i2 |= IN_USB_DEVICE;
        }
        if (((-2147475456) & i) == -2147475456) {
            arrayList.add("IN_FM_TUNER");
            i2 |= IN_FM_TUNER;
        }
        if (((-2147467264) & i) == -2147467264) {
            arrayList.add("IN_TV_TUNER");
            i2 |= IN_TV_TUNER;
        }
        if (((-2147450880) & i) == -2147450880) {
            arrayList.add("IN_LINE");
            i2 |= IN_LINE;
        }
        if (((-2147418112) & i) == -2147418112) {
            arrayList.add("IN_SPDIF");
            i2 |= IN_SPDIF;
        }
        if (((-2147352576) & i) == -2147352576) {
            arrayList.add("IN_BLUETOOTH_A2DP");
            i2 |= IN_BLUETOOTH_A2DP;
        }
        if (((-2147221504) & i) == -2147221504) {
            arrayList.add("IN_LOOPBACK");
            i2 |= IN_LOOPBACK;
        }
        if (((-2146959360) & i) == -2146959360) {
            arrayList.add("IN_IP");
            i2 |= IN_IP;
        }
        if (((-2146435072) & i) == -2146435072) {
            arrayList.add("IN_BUS");
            i2 |= IN_BUS;
        }
        if (((-2130706432) & i) == -2130706432) {
            arrayList.add("IN_PROXY");
            i2 |= IN_PROXY;
        }
        if (((-2113929216) & i) == -2113929216) {
            arrayList.add("IN_USB_HEADSET");
            i2 |= IN_USB_HEADSET;
        }
        if (((-1073741824) & i) == -1073741824) {
            arrayList.add("IN_DEFAULT");
            i2 |= -1073741824;
        }
        if (((-1021313025) & i) == -1021313025) {
            arrayList.add("IN_ALL");
            i2 |= IN_ALL;
        }
        if (i5 == -2147483640) {
            arrayList.add("IN_ALL_SCO");
            i2 |= -2147483640;
        }
        if (((-2113923072) & i) == -2113923072) {
            arrayList.add("IN_ALL_USB");
            i2 |= IN_ALL_USB;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
