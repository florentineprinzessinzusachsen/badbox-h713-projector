package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioMixLatencyClass {
    public static final int LOW = 0;
    public static final int NORMAL = 1;

    public static final String toString(int i) {
        if (i == 0) {
            return "LOW";
        }
        if (i == 1) {
            return "NORMAL";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("LOW");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("NORMAL");
        } else {
            i2 = 0;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
