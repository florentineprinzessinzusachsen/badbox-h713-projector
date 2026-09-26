package vendor.aw.hardware.btvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ThalSoundStandard {
    public static final int TV_DEVICE_SOUND_AUTO = 0;
    public static final int TV_DEVICE_SOUND_M = 1;
    public static final int TV_DEVICE_SOUND_NICAM_BG = 2;
    public static final int TV_DEVICE_SOUND_NICAM_DK = 4;
    public static final int TV_DEVICE_SOUND_NICAM_I = 3;
    public static final int TV_DEVICE_SOUND_NICAM_L = 5;
    public static final int TV_DEVICE_SOUND_UNKNOWN = 6;

    public static final String toString(int i) {
        if (i == 0) {
            return "TV_DEVICE_SOUND_AUTO";
        }
        if (i == 1) {
            return "TV_DEVICE_SOUND_M";
        }
        if (i == 2) {
            return "TV_DEVICE_SOUND_NICAM_BG";
        }
        if (i == 3) {
            return "TV_DEVICE_SOUND_NICAM_I";
        }
        if (i == 4) {
            return "TV_DEVICE_SOUND_NICAM_DK";
        }
        if (i == 5) {
            return "TV_DEVICE_SOUND_NICAM_L";
        }
        if (i == 6) {
            return "TV_DEVICE_SOUND_UNKNOWN";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("TV_DEVICE_SOUND_AUTO");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("TV_DEVICE_SOUND_M");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("TV_DEVICE_SOUND_NICAM_BG");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("TV_DEVICE_SOUND_NICAM_I");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("TV_DEVICE_SOUND_NICAM_DK");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("TV_DEVICE_SOUND_NICAM_L");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("TV_DEVICE_SOUND_UNKNOWN");
            i2 |= 6;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
