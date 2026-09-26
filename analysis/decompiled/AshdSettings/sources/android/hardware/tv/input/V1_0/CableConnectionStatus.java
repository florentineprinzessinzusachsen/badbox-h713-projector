package android.hardware.tv.input.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class CableConnectionStatus {
    public static final int CONNECTED = 1;
    public static final int DISCONNECTED = 2;
    public static final int UNKNOWN = 0;

    public static final String toString(int i) {
        if (i == 0) {
            return "UNKNOWN";
        }
        if (i == 1) {
            return "CONNECTED";
        }
        if (i == 2) {
            return "DISCONNECTED";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("UNKNOWN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("CONNECTED");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("DISCONNECTED");
            i2 |= 2;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
