package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioInterleave {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public static final String toString(int i) {
        if (i == 0) {
            return "LEFT";
        }
        if (i == 1) {
            return "RIGHT";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("LEFT");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("RIGHT");
        } else {
            i2 = 0;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
