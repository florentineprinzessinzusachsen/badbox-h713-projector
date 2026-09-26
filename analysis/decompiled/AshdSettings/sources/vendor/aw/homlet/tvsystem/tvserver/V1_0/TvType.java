package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TvType {
    public static final int TYPE_ANALOG_TV = 2;
    public static final int TYPE_DIGITAL_TV = 1;

    public static final String toString(int i) {
        if (i == 1) {
            return "TYPE_DIGITAL_TV";
        }
        if (i == 2) {
            return "TYPE_ANALOG_TV";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("TYPE_DIGITAL_TV");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("TYPE_ANALOG_TV");
            i2 |= 2;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
