package vendor.display.config.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class LayerMode {
    public static final int LAYER_2D_DUAL_STREAM = 5;
    public static final int LAYER_2D_LEFT = 1;
    public static final int LAYER_2D_ORIGINAL = 0;
    public static final int LAYER_2D_TOP = 2;
    public static final int LAYER_3D_DUAL_STREAM = 6;
    public static final int LAYER_3D_LEFT_RIGHT_ALL = 7;
    public static final int LAYER_3D_LEFT_RIGHT_HDMI = 3;
    public static final int LAYER_3D_TOP_BOTTOM_ALL = 8;
    public static final int LAYER_3D_TOP_BOTTOM_HDMI = 4;

    public static final String toString(int i) {
        if (i == 0) {
            return "LAYER_2D_ORIGINAL";
        }
        if (i == 1) {
            return "LAYER_2D_LEFT";
        }
        if (i == 2) {
            return "LAYER_2D_TOP";
        }
        if (i == 3) {
            return "LAYER_3D_LEFT_RIGHT_HDMI";
        }
        if (i == 4) {
            return "LAYER_3D_TOP_BOTTOM_HDMI";
        }
        if (i == 5) {
            return "LAYER_2D_DUAL_STREAM";
        }
        if (i == 6) {
            return "LAYER_3D_DUAL_STREAM";
        }
        if (i == 7) {
            return "LAYER_3D_LEFT_RIGHT_ALL";
        }
        if (i == 8) {
            return "LAYER_3D_TOP_BOTTOM_ALL";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("LAYER_2D_ORIGINAL");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("LAYER_2D_LEFT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("LAYER_2D_TOP");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("LAYER_3D_LEFT_RIGHT_HDMI");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("LAYER_3D_TOP_BOTTOM_HDMI");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("LAYER_2D_DUAL_STREAM");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("LAYER_3D_DUAL_STREAM");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("LAYER_3D_LEFT_RIGHT_ALL");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("LAYER_3D_TOP_BOTTOM_ALL");
            i2 |= 8;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
