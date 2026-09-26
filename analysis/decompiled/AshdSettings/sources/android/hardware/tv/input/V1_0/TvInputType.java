package android.hardware.tv.input.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TvInputType {
    public static final int COMPONENT = 6;
    public static final int COMPOSITE = 3;
    public static final int DISPLAY_PORT = 10;
    public static final int DVI = 8;
    public static final int HDMI = 9;
    public static final int OTHER = 1;
    public static final int SCART = 5;
    public static final int SVIDEO = 4;
    public static final int TUNER = 2;
    public static final int VGA = 7;

    public static final String toString(int i) {
        if (i == 1) {
            return "OTHER";
        }
        if (i == 2) {
            return "TUNER";
        }
        if (i == 3) {
            return "COMPOSITE";
        }
        if (i == 4) {
            return "SVIDEO";
        }
        if (i == 5) {
            return "SCART";
        }
        if (i == 6) {
            return "COMPONENT";
        }
        if (i == 7) {
            return "VGA";
        }
        if (i == 8) {
            return "DVI";
        }
        if (i == 9) {
            return "HDMI";
        }
        if (i == 10) {
            return "DISPLAY_PORT";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("OTHER");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("TUNER");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("COMPOSITE");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("SVIDEO");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("SCART");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("COMPONENT");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("VGA");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("DVI");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("HDMI");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("DISPLAY_PORT");
            i2 |= 10;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
