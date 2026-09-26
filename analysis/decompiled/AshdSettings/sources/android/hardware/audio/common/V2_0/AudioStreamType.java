package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioStreamType {
    public static final int ACCESSIBILITY = 10;
    public static final int ALARM = 4;
    public static final int BLUETOOTH_SCO = 6;
    public static final int CNT = 13;
    public static final int DEFAULT = -1;
    public static final int DTMF = 8;
    public static final int ENFORCED_AUDIBLE = 7;
    public static final int FOR_POLICY_CNT = 12;
    public static final int MIN = 0;
    public static final int MUSIC = 3;
    public static final int NOTIFICATION = 5;
    public static final int PATCH = 12;
    public static final int PUBLIC_CNT = 11;
    public static final int REROUTING = 11;
    public static final int RING = 2;
    public static final int SYSTEM = 1;
    public static final int TTS = 9;
    public static final int VOICE_CALL = 0;

    public static final String toString(int i) {
        if (i == -1) {
            return "DEFAULT";
        }
        if (i == 0) {
            return "MIN";
        }
        if (i == 0) {
            return "VOICE_CALL";
        }
        if (i == 1) {
            return "SYSTEM";
        }
        if (i == 2) {
            return "RING";
        }
        if (i == 3) {
            return "MUSIC";
        }
        if (i == 4) {
            return "ALARM";
        }
        if (i == 5) {
            return "NOTIFICATION";
        }
        if (i == 6) {
            return "BLUETOOTH_SCO";
        }
        if (i == 7) {
            return "ENFORCED_AUDIBLE";
        }
        if (i == 8) {
            return "DTMF";
        }
        if (i == 9) {
            return "TTS";
        }
        if (i == 10) {
            return "ACCESSIBILITY";
        }
        if (i == 11) {
            return "REROUTING";
        }
        if (i == 12) {
            return "PATCH";
        }
        if (i == 11) {
            return "PUBLIC_CNT";
        }
        if (i == 12) {
            return "FOR_POLICY_CNT";
        }
        if (i == 13) {
            return "CNT";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = -1;
        if ((i & (-1)) == -1) {
            arrayList.add("DEFAULT");
        } else {
            i2 = 0;
        }
        arrayList.add("MIN");
        arrayList.add("VOICE_CALL");
        if ((i & 1) == 1) {
            arrayList.add("SYSTEM");
            i2 |= 1;
        }
        if ((i & 2) == 2) {
            arrayList.add("RING");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("MUSIC");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("ALARM");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("NOTIFICATION");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("BLUETOOTH_SCO");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("ENFORCED_AUDIBLE");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("DTMF");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("TTS");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("ACCESSIBILITY");
            i2 |= 10;
        }
        int i3 = i & 11;
        if (i3 == 11) {
            arrayList.add("REROUTING");
            i2 |= 11;
        }
        int i4 = i & 12;
        if (i4 == 12) {
            arrayList.add("PATCH");
            i2 |= 12;
        }
        if (i3 == 11) {
            arrayList.add("PUBLIC_CNT");
            i2 |= 11;
        }
        if (i4 == 12) {
            arrayList.add("FOR_POLICY_CNT");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("CNT");
            i2 |= 13;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
