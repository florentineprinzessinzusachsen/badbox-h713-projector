package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioSource {
    public static final int CAMCORDER = 5;
    public static final int CNT = 10;
    public static final int DEFAULT = 0;
    public static final int FM_TUNER = 1998;
    public static final int HOTWORD = 1999;
    public static final int MAX = 9;
    public static final int MIC = 1;
    public static final int REMOTE_SUBMIX = 8;
    public static final int UNPROCESSED = 9;
    public static final int VOICE_CALL = 4;
    public static final int VOICE_COMMUNICATION = 7;
    public static final int VOICE_DOWNLINK = 3;
    public static final int VOICE_RECOGNITION = 6;
    public static final int VOICE_UPLINK = 2;

    public static final String toString(int i) {
        if (i == 0) {
            return "DEFAULT";
        }
        if (i == 1) {
            return "MIC";
        }
        if (i == 2) {
            return "VOICE_UPLINK";
        }
        if (i == 3) {
            return "VOICE_DOWNLINK";
        }
        if (i == 4) {
            return "VOICE_CALL";
        }
        if (i == 5) {
            return "CAMCORDER";
        }
        if (i == 6) {
            return "VOICE_RECOGNITION";
        }
        if (i == 7) {
            return "VOICE_COMMUNICATION";
        }
        if (i == 8) {
            return "REMOTE_SUBMIX";
        }
        if (i == 9) {
            return "UNPROCESSED";
        }
        if (i == 10) {
            return "CNT";
        }
        if (i == 9) {
            return "MAX";
        }
        if (i == 1998) {
            return "FM_TUNER";
        }
        if (i == 1999) {
            return "HOTWORD";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("DEFAULT");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("MIC");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("VOICE_UPLINK");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("VOICE_DOWNLINK");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("VOICE_CALL");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("CAMCORDER");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("VOICE_RECOGNITION");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("VOICE_COMMUNICATION");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("REMOTE_SUBMIX");
            i2 |= 8;
        }
        int i3 = i & 9;
        if (i3 == 9) {
            arrayList.add("UNPROCESSED");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("CNT");
            i2 |= 10;
        }
        if (i3 == 9) {
            arrayList.add("MAX");
            i2 |= 9;
        }
        if ((i & FM_TUNER) == 1998) {
            arrayList.add("FM_TUNER");
            i2 |= FM_TUNER;
        }
        if ((i & HOTWORD) == 1999) {
            arrayList.add("HOTWORD");
            i2 |= HOTWORD;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
