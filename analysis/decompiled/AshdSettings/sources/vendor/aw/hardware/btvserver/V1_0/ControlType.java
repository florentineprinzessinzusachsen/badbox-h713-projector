package vendor.aw.hardware.btvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ControlType {
    public static final int TYPE_START_ATV_FINE_TUNE = 3;
    public static final int TYPE_START_SEARCH_PROGRAM = 1;
    public static final int TYPE_STOP_SEARCH_EPG = 2;
    public static final int TYPE_STOP_SEARCH_PROGRAM = 0;

    public static final String toString(int i) {
        if (i == 0) {
            return "TYPE_STOP_SEARCH_PROGRAM";
        }
        if (i == 1) {
            return "TYPE_START_SEARCH_PROGRAM";
        }
        if (i == 2) {
            return "TYPE_STOP_SEARCH_EPG";
        }
        if (i == 3) {
            return "TYPE_START_ATV_FINE_TUNE";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("TYPE_STOP_SEARCH_PROGRAM");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("TYPE_START_SEARCH_PROGRAM");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("TYPE_STOP_SEARCH_EPG");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("TYPE_START_ATV_FINE_TUNE");
            i2 |= 3;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
