package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class CECPowerStatusID {
    public static final int CEC_PWRSTATUS_ON = 0;
    public static final int CEC_PWRSTATUS_ON2STB = 3;
    public static final int CEC_PWRSTATUS_STB = 1;
    public static final int CEC_PWRSTATUS_STB2ON = 2;

    public static final String toString(int i) {
        if (i == 0) {
            return "CEC_PWRSTATUS_ON";
        }
        if (i == 1) {
            return "CEC_PWRSTATUS_STB";
        }
        if (i == 2) {
            return "CEC_PWRSTATUS_STB2ON";
        }
        if (i == 3) {
            return "CEC_PWRSTATUS_ON2STB";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("CEC_PWRSTATUS_ON");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("CEC_PWRSTATUS_STB");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("CEC_PWRSTATUS_STB2ON");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("CEC_PWRSTATUS_ON2STB");
            i2 |= 3;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
