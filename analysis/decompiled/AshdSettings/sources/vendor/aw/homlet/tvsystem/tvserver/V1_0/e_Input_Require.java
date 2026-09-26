package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class e_Input_Require {
    public static final int IR_ARC_Init_ARC = 192;
    public static final int IR_ARC_Term_ARC = 197;

    public static final String toString(int i) {
        if (i == 192) {
            return "IR_ARC_Init_ARC";
        }
        if (i == 197) {
            return "IR_ARC_Term_ARC";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 192;
        if ((i & 192) == 192) {
            arrayList.add("IR_ARC_Init_ARC");
        } else {
            i2 = 0;
        }
        if ((i & IR_ARC_Term_ARC) == 197) {
            arrayList.add("IR_ARC_Term_ARC");
            i2 |= IR_ARC_Term_ARC;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
