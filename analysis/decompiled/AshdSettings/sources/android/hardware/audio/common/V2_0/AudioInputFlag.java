package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioInputFlag {
    public static final int FAST = 1;
    public static final int HW_HOTWORD = 2;
    public static final int MMAP_NOIRQ = 16;
    public static final int NONE = 0;
    public static final int RAW = 4;
    public static final int SYNC = 8;
    public static final int VOIP_TX = 32;

    public static final String toString(int i) {
        if (i == 0) {
            return "NONE";
        }
        if (i == 1) {
            return "FAST";
        }
        if (i == 2) {
            return "HW_HOTWORD";
        }
        if (i == 4) {
            return "RAW";
        }
        if (i == 8) {
            return "SYNC";
        }
        if (i == 16) {
            return "MMAP_NOIRQ";
        }
        if (i == 32) {
            return "VOIP_TX";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("NONE");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("FAST");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("HW_HOTWORD");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("RAW");
            i2 |= 4;
        }
        if ((i & 8) == 8) {
            arrayList.add("SYNC");
            i2 |= 8;
        }
        if ((i & 16) == 16) {
            arrayList.add("MMAP_NOIRQ");
            i2 |= 16;
        }
        if ((i & 32) == 32) {
            arrayList.add("VOIP_TX");
            i2 |= 32;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
