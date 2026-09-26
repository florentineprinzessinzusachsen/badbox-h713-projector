package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class BTVEvent {
    public static final int BTV_EVENT_ATV_TUNE_FINISH = 7;
    public static final int BTV_EVENT_DEVICE_STATE = 3;
    public static final int BTV_EVENT_EPG_FINISH = 6;
    public static final int BTV_EVENT_EPG_INFO = 5;
    public static final int BTV_EVENT_SCAN_FINISH = 2;
    public static final int BTV_EVENT_SCAN_INFO = 1;
    public static final int BTV_EVENT_URL = 4;

    public static final String toString(int i) {
        if (i == 1) {
            return "BTV_EVENT_SCAN_INFO";
        }
        if (i == 2) {
            return "BTV_EVENT_SCAN_FINISH";
        }
        if (i == 3) {
            return "BTV_EVENT_DEVICE_STATE";
        }
        if (i == 4) {
            return "BTV_EVENT_URL";
        }
        if (i == 5) {
            return "BTV_EVENT_EPG_INFO";
        }
        if (i == 6) {
            return "BTV_EVENT_EPG_FINISH";
        }
        if (i == 7) {
            return "BTV_EVENT_ATV_TUNE_FINISH";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("BTV_EVENT_SCAN_INFO");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("BTV_EVENT_SCAN_FINISH");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("BTV_EVENT_DEVICE_STATE");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("BTV_EVENT_URL");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("BTV_EVENT_EPG_INFO");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("BTV_EVENT_EPG_FINISH");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("BTV_EVENT_ATV_TUNE_FINISH");
            i2 |= 7;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
