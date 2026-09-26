package vendor.display.config.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class SNRFeatureMode {
    public static final int SNR_CUSTOM = 4;
    public static final int SNR_DEMO = 5;
    public static final int SNR_DISABLE = 0;
    public static final int SNR_LEVEL1 = 1;
    public static final int SNR_LEVEL2 = 2;
    public static final int SNR_LEVEL3 = 3;

    public static final String toString(int i) {
        if (i == 0) {
            return "SNR_DISABLE";
        }
        if (i == 1) {
            return "SNR_LEVEL1";
        }
        if (i == 2) {
            return "SNR_LEVEL2";
        }
        if (i == 3) {
            return "SNR_LEVEL3";
        }
        if (i == 4) {
            return "SNR_CUSTOM";
        }
        if (i == 5) {
            return "SNR_DEMO";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SNR_DISABLE");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("SNR_LEVEL1");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("SNR_LEVEL2");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("SNR_LEVEL3");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("SNR_CUSTOM");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("SNR_DEMO");
            i2 |= 5;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
