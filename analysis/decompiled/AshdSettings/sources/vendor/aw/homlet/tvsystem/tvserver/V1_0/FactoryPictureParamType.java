package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class FactoryPictureParamType {
    public static final int PQ_ADVANCE_CTI_SETTINGS = 3;
    public static final int PQ_ADVANCE_ENABLE = 0;
    public static final int PQ_ADVANCE_GAMMA_MODE = 5;
    public static final int PQ_ADVANCE_LUMA_SETTINGS = 1;
    public static final int PQ_ADVANCE_NR_SETTINGS = 2;
    public static final int PQ_ADVANCE_PICTURE_PARAM_TYPE_MAX = 6;
    public static final int PQ_ADVANCE_SSR_SETTINGS = 4;

    public static final String toString(int i) {
        if (i == 0) {
            return "PQ_ADVANCE_ENABLE";
        }
        if (i == 1) {
            return "PQ_ADVANCE_LUMA_SETTINGS";
        }
        if (i == 2) {
            return "PQ_ADVANCE_NR_SETTINGS";
        }
        if (i == 3) {
            return "PQ_ADVANCE_CTI_SETTINGS";
        }
        if (i == 4) {
            return "PQ_ADVANCE_SSR_SETTINGS";
        }
        if (i == 5) {
            return "PQ_ADVANCE_GAMMA_MODE";
        }
        if (i == 6) {
            return "PQ_ADVANCE_PICTURE_PARAM_TYPE_MAX";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("PQ_ADVANCE_ENABLE");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("PQ_ADVANCE_LUMA_SETTINGS");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("PQ_ADVANCE_NR_SETTINGS");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("PQ_ADVANCE_CTI_SETTINGS");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("PQ_ADVANCE_SSR_SETTINGS");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("PQ_ADVANCE_GAMMA_MODE");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("PQ_ADVANCE_PICTURE_PARAM_TYPE_MAX");
            i2 |= 6;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
