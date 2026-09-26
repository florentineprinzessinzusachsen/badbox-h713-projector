package vendor.aw.hardware.btvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ThalTunerStandard {
    public static final int TV_TUNER_TYPE_AIR_STD = 0;
    public static final int TV_TUNER_TYPE_CABLE_AUTO = 4;
    public static final int TV_TUNER_TYPE_CABLE_HRC = 2;
    public static final int TV_TUNER_TYPE_CABLE_IRC = 3;
    public static final int TV_TUNER_TYPE_CABLE_STD = 1;
    public static final int TV_TUNER_TYPE_UNKNOWN = -1;

    public static final String toString(int i) {
        if (i == -1) {
            return "TV_TUNER_TYPE_UNKNOWN";
        }
        if (i == 0) {
            return "TV_TUNER_TYPE_AIR_STD";
        }
        if (i == 1) {
            return "TV_TUNER_TYPE_CABLE_STD";
        }
        if (i == 2) {
            return "TV_TUNER_TYPE_CABLE_HRC";
        }
        if (i == 3) {
            return "TV_TUNER_TYPE_CABLE_IRC";
        }
        if (i == 4) {
            return "TV_TUNER_TYPE_CABLE_AUTO";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = -1;
        if ((i & (-1)) == -1) {
            arrayList.add("TV_TUNER_TYPE_UNKNOWN");
        } else {
            i2 = 0;
        }
        arrayList.add("TV_TUNER_TYPE_AIR_STD");
        if ((i & 1) == 1) {
            arrayList.add("TV_TUNER_TYPE_CABLE_STD");
            i2 |= 1;
        }
        if ((i & 2) == 2) {
            arrayList.add("TV_TUNER_TYPE_CABLE_HRC");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("TV_TUNER_TYPE_CABLE_IRC");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("TV_TUNER_TYPE_CABLE_AUTO");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
