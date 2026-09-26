package android.hardware.tv.input.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TvInputEventType {
    public static final int DEVICE_AVAILABLE = 1;
    public static final int DEVICE_UNAVAILABLE = 2;
    public static final int STREAM_CONFIGURATIONS_CHANGED = 3;
    public static final int STREAM_HOTPLUG_CHANGED = 10;
    public static final int STREAM_SIGNAL_CHANGED = 9;

    public static final String toString(int i) {
        if (i == 1) {
            return "DEVICE_AVAILABLE";
        }
        if (i == 2) {
            return "DEVICE_UNAVAILABLE";
        }
        if (i == 3) {
            return "STREAM_CONFIGURATIONS_CHANGED";
        }
        if (i == 9) {
            return "STREAM_SIGNAL_CHANGED";
        }
        if (i == 10) {
            return "STREAM_HOTPLUG_CHANGED";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("DEVICE_AVAILABLE");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("DEVICE_UNAVAILABLE");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("STREAM_CONFIGURATIONS_CHANGED");
            i2 |= 3;
        }
        if ((i & 9) == 9) {
            arrayList.add("STREAM_SIGNAL_CHANGED");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("STREAM_HOTPLUG_CHANGED");
            i2 |= 10;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
