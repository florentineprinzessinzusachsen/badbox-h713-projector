package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class THalBlueScreenLevel {
    public static final int kHalBlueScreenLevel_Default = 2;
    public static final int kHalBlueScreenLevel_Panel = 1;
    public static final int kHalBlueScreenLevel_ProcBlender = 0;

    public static final String toString(int i) {
        if (i == 0) {
            return "kHalBlueScreenLevel_ProcBlender";
        }
        if (i == 1) {
            return "kHalBlueScreenLevel_Panel";
        }
        if (i == 2) {
            return "kHalBlueScreenLevel_Default";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("kHalBlueScreenLevel_ProcBlender");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("kHalBlueScreenLevel_Panel");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("kHalBlueScreenLevel_Default");
            i2 |= 2;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
