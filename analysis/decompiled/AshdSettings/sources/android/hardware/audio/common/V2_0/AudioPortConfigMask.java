package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioPortConfigMask {
    public static final int ALL = 15;
    public static final int CHANNEL_MASK = 2;
    public static final int FORMAT = 4;
    public static final int GAIN = 8;
    public static final int SAMPLE_RATE = 1;

    public static final String toString(int i) {
        if (i == 1) {
            return "SAMPLE_RATE";
        }
        if (i == 2) {
            return "CHANNEL_MASK";
        }
        if (i == 4) {
            return "FORMAT";
        }
        if (i == 8) {
            return "GAIN";
        }
        if (i == 15) {
            return "ALL";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("SAMPLE_RATE");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("CHANNEL_MASK");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("FORMAT");
            i2 |= 4;
        }
        if ((i & 8) == 8) {
            arrayList.add("GAIN");
            i2 |= 8;
        }
        if ((i & 15) == 15) {
            arrayList.add("ALL");
            i2 |= 15;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
