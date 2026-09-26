package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioMode {
    public static final int CNT = 4;
    public static final int CURRENT = -1;
    public static final int INVALID = -2;
    public static final int IN_CALL = 2;
    public static final int IN_COMMUNICATION = 3;
    public static final int MAX = 3;
    public static final int NORMAL = 0;
    public static final int RINGTONE = 1;

    public static final String toString(int i) {
        if (i == -2) {
            return "INVALID";
        }
        if (i == -1) {
            return "CURRENT";
        }
        if (i == 0) {
            return "NORMAL";
        }
        if (i == 1) {
            return "RINGTONE";
        }
        if (i == 2) {
            return "IN_CALL";
        }
        if (i == 3) {
            return "IN_COMMUNICATION";
        }
        if (i == 4) {
            return "CNT";
        }
        if (i == 3) {
            return "MAX";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = -2;
        if ((i & (-2)) == -2) {
            arrayList.add("INVALID");
        } else {
            i2 = 0;
        }
        if ((i & (-1)) == -1) {
            arrayList.add("CURRENT");
            i2 |= -1;
        }
        arrayList.add("NORMAL");
        if ((i & 1) == 1) {
            arrayList.add("RINGTONE");
            i2 |= 1;
        }
        if ((i & 2) == 2) {
            arrayList.add("IN_CALL");
            i2 |= 2;
        }
        int i3 = i & 3;
        if (i3 == 3) {
            arrayList.add("IN_COMMUNICATION");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("CNT");
            i2 |= 4;
        }
        if (i3 == 3) {
            arrayList.add("MAX");
            i2 |= 3;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
