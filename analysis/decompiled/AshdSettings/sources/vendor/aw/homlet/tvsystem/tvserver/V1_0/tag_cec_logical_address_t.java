package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class tag_cec_logical_address_t {
    public static final int CEC_ADDR_AUDIO_SYSTEM = 5;
    public static final int CEC_ADDR_BROADCAST = 15;
    public static final int CEC_ADDR_FREE_USE = 14;
    public static final int CEC_ADDR_PLAYBACK_1 = 4;
    public static final int CEC_ADDR_PLAYBACK_2 = 8;
    public static final int CEC_ADDR_PLAYBACK_3 = 11;
    public static final int CEC_ADDR_RECORDER_1 = 1;
    public static final int CEC_ADDR_RECORDER_2 = 2;
    public static final int CEC_ADDR_RECORDER_3 = 9;
    public static final int CEC_ADDR_RESERVED_1 = 12;
    public static final int CEC_ADDR_RESERVED_2 = 13;
    public static final int CEC_ADDR_TUNER_1 = 3;
    public static final int CEC_ADDR_TUNER_2 = 6;
    public static final int CEC_ADDR_TUNER_3 = 7;
    public static final int CEC_ADDR_TUNER_4 = 10;
    public static final int CEC_ADDR_TV = 0;
    public static final int CEC_ADDR_UNREGISTERED = 15;

    public static final String toString(int i) {
        if (i == 0) {
            return "CEC_ADDR_TV";
        }
        if (i == 1) {
            return "CEC_ADDR_RECORDER_1";
        }
        if (i == 2) {
            return "CEC_ADDR_RECORDER_2";
        }
        if (i == 3) {
            return "CEC_ADDR_TUNER_1";
        }
        if (i == 4) {
            return "CEC_ADDR_PLAYBACK_1";
        }
        if (i == 5) {
            return "CEC_ADDR_AUDIO_SYSTEM";
        }
        if (i == 6) {
            return "CEC_ADDR_TUNER_2";
        }
        if (i == 7) {
            return "CEC_ADDR_TUNER_3";
        }
        if (i == 8) {
            return "CEC_ADDR_PLAYBACK_2";
        }
        if (i == 9) {
            return "CEC_ADDR_RECORDER_3";
        }
        if (i == 10) {
            return "CEC_ADDR_TUNER_4";
        }
        if (i == 11) {
            return "CEC_ADDR_PLAYBACK_3";
        }
        if (i == 12) {
            return "CEC_ADDR_RESERVED_1";
        }
        if (i == 13) {
            return "CEC_ADDR_RESERVED_2";
        }
        if (i == 14) {
            return "CEC_ADDR_FREE_USE";
        }
        if (i == 15) {
            return "CEC_ADDR_UNREGISTERED";
        }
        if (i == 15) {
            return "CEC_ADDR_BROADCAST";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("CEC_ADDR_TV");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("CEC_ADDR_RECORDER_1");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("CEC_ADDR_RECORDER_2");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("CEC_ADDR_TUNER_1");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("CEC_ADDR_PLAYBACK_1");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("CEC_ADDR_AUDIO_SYSTEM");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("CEC_ADDR_TUNER_2");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("CEC_ADDR_TUNER_3");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("CEC_ADDR_PLAYBACK_2");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("CEC_ADDR_RECORDER_3");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("CEC_ADDR_TUNER_4");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("CEC_ADDR_PLAYBACK_3");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("CEC_ADDR_RESERVED_1");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("CEC_ADDR_RESERVED_2");
            i2 |= 13;
        }
        if ((i & 14) == 14) {
            arrayList.add("CEC_ADDR_FREE_USE");
            i2 |= 14;
        }
        int i3 = i & 15;
        if (i3 == 15) {
            arrayList.add("CEC_ADDR_UNREGISTERED");
            i2 |= 15;
        }
        if (i3 == 15) {
            arrayList.add("CEC_ADDR_BROADCAST");
            i2 |= 15;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
