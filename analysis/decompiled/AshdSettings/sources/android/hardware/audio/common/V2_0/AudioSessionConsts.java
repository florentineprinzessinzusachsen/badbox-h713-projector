package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioSessionConsts {
    public static final int ALLOCATE = 0;
    public static final int NONE = 0;
    public static final int OUTPUT_MIX = 0;
    public static final int OUTPUT_STAGE = -1;

    public static final String toString(int i) {
        if (i == -1) {
            return "OUTPUT_STAGE";
        }
        if (i == 0) {
            return "OUTPUT_MIX";
        }
        if (i == 0) {
            return "ALLOCATE";
        }
        if (i == 0) {
            return "NONE";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = -1;
        if ((i & (-1)) == -1) {
            arrayList.add("OUTPUT_STAGE");
        } else {
            i2 = 0;
        }
        arrayList.add("OUTPUT_MIX");
        arrayList.add("ALLOCATE");
        arrayList.add("NONE");
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
