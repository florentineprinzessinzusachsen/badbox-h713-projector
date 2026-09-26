package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TMenuRequestType {
    public static final int MenuRequest_Activate = 0;
    public static final int MenuRequest_Deactivate = 1;
    public static final int MenuRequest_Query = 2;

    public static final String toString(int i) {
        if (i == 0) {
            return "MenuRequest_Activate";
        }
        if (i == 1) {
            return "MenuRequest_Deactivate";
        }
        if (i == 2) {
            return "MenuRequest_Query";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("MenuRequest_Activate");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("MenuRequest_Deactivate");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("MenuRequest_Query");
            i2 |= 2;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
