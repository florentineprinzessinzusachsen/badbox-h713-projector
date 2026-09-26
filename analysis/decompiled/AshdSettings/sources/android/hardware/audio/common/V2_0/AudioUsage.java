package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioUsage {
    public static final int ALARM = 4;
    public static final int ASSISTANCE_ACCESSIBILITY = 11;
    public static final int ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int ASSISTANCE_SONIFICATION = 13;
    public static final int ASSISTANT = 16;
    public static final int CNT = 17;
    public static final int GAME = 14;
    public static final int MAX = 16;
    public static final int MEDIA = 1;
    public static final int NOTIFICATION = 5;
    public static final int NOTIFICATION_COMMUNICATION_DELAYED = 9;
    public static final int NOTIFICATION_COMMUNICATION_INSTANT = 8;
    public static final int NOTIFICATION_COMMUNICATION_REQUEST = 7;
    public static final int NOTIFICATION_EVENT = 10;
    public static final int NOTIFICATION_TELEPHONY_RINGTONE = 6;
    public static final int UNKNOWN = 0;
    public static final int VIRTUAL_SOURCE = 15;
    public static final int VOICE_COMMUNICATION = 2;
    public static final int VOICE_COMMUNICATION_SIGNALLING = 3;

    public static final String toString(int i) {
        if (i == 0) {
            return "UNKNOWN";
        }
        if (i == 1) {
            return "MEDIA";
        }
        if (i == 2) {
            return "VOICE_COMMUNICATION";
        }
        if (i == 3) {
            return "VOICE_COMMUNICATION_SIGNALLING";
        }
        if (i == 4) {
            return "ALARM";
        }
        if (i == 5) {
            return "NOTIFICATION";
        }
        if (i == 6) {
            return "NOTIFICATION_TELEPHONY_RINGTONE";
        }
        if (i == 7) {
            return "NOTIFICATION_COMMUNICATION_REQUEST";
        }
        if (i == 8) {
            return "NOTIFICATION_COMMUNICATION_INSTANT";
        }
        if (i == 9) {
            return "NOTIFICATION_COMMUNICATION_DELAYED";
        }
        if (i == 10) {
            return "NOTIFICATION_EVENT";
        }
        if (i == 11) {
            return "ASSISTANCE_ACCESSIBILITY";
        }
        if (i == 12) {
            return "ASSISTANCE_NAVIGATION_GUIDANCE";
        }
        if (i == 13) {
            return "ASSISTANCE_SONIFICATION";
        }
        if (i == 14) {
            return "GAME";
        }
        if (i == 15) {
            return "VIRTUAL_SOURCE";
        }
        if (i == 16) {
            return "ASSISTANT";
        }
        if (i == 17) {
            return "CNT";
        }
        if (i == 16) {
            return "MAX";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("UNKNOWN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("MEDIA");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("VOICE_COMMUNICATION");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("VOICE_COMMUNICATION_SIGNALLING");
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
            arrayList.add("NOTIFICATION_TELEPHONY_RINGTONE");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("NOTIFICATION_COMMUNICATION_REQUEST");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("NOTIFICATION_COMMUNICATION_INSTANT");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("NOTIFICATION_COMMUNICATION_DELAYED");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("NOTIFICATION_EVENT");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("ASSISTANCE_ACCESSIBILITY");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("ASSISTANCE_NAVIGATION_GUIDANCE");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("ASSISTANCE_SONIFICATION");
            i2 |= 13;
        }
        if ((i & 14) == 14) {
            arrayList.add("GAME");
            i2 |= 14;
        }
        if ((i & 15) == 15) {
            arrayList.add("VIRTUAL_SOURCE");
            i2 |= 15;
        }
        int i3 = i & 16;
        if (i3 == 16) {
            arrayList.add("ASSISTANT");
            i2 |= 16;
        }
        if ((i & 17) == 17) {
            arrayList.add("CNT");
            i2 |= 17;
        }
        if (i3 == 16) {
            arrayList.add("MAX");
            i2 |= 16;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
