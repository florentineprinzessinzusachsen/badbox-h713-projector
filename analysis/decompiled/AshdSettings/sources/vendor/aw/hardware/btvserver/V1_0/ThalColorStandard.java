package vendor.aw.hardware.btvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ThalColorStandard {
    public static final int TV_DEVICE_BASE_STANDARD_AUTO = 0;
    public static final int TV_DEVICE_BASE_STANDARD_NTSC = 2;
    public static final int TV_DEVICE_BASE_STANDARD_PAL = 1;
    public static final int TV_DEVICE_BASE_STANDARD_SECAM = 3;

    public static final String toString(int i) {
        if (i == 0) {
            return "TV_DEVICE_BASE_STANDARD_AUTO";
        }
        if (i == 1) {
            return "TV_DEVICE_BASE_STANDARD_PAL";
        }
        if (i == 2) {
            return "TV_DEVICE_BASE_STANDARD_NTSC";
        }
        if (i == 3) {
            return "TV_DEVICE_BASE_STANDARD_SECAM";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("TV_DEVICE_BASE_STANDARD_AUTO");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("TV_DEVICE_BASE_STANDARD_PAL");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("TV_DEVICE_BASE_STANDARD_NTSC");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("TV_DEVICE_BASE_STANDARD_SECAM");
            i2 |= 3;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
