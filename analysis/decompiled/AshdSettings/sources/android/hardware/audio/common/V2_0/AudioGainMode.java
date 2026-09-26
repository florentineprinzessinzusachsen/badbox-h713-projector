package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioGainMode {
    public static final int CHANNELS = 2;
    public static final int JOINT = 1;
    public static final int RAMP = 4;

    public static final String toString(int i) {
        if (i == 1) {
            return "JOINT";
        }
        if (i == 2) {
            return "CHANNELS";
        }
        if (i == 4) {
            return "RAMP";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("JOINT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("CHANNELS");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("RAMP");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
