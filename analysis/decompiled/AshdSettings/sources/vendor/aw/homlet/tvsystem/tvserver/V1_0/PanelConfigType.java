package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class PanelConfigType {
    public static final int PANEL_CONFIG_COLOR_DEPTH = 0;
    public static final int PANEL_CONFIG_DE_CURRENT = 4;
    public static final int PANEL_CONFIG_EMC_LVDS_ENABLE = 7;
    public static final int PANEL_CONFIG_EMC_LVDS_SPAN = 9;
    public static final int PANEL_CONFIG_EMC_LVDS_STEP = 8;
    public static final int PANEL_CONFIG_EVEN_CURRENT = 6;
    public static final int PANEL_CONFIG_MAPPING = 1;
    public static final int PANEL_CONFIG_MIRROR = 3;
    public static final int PANEL_CONFIG_ODD_CURRENT = 5;
    public static final int PANEL_CONFIG_ODD_EVEN = 2;
    public static final int PANEL_CONFIG_TIMING_WORK_MODE = 10;
    public static final int PANEL_PWM_FREQ = 16;

    public static final String toString(int i) {
        if (i == 0) {
            return "PANEL_CONFIG_COLOR_DEPTH";
        }
        if (i == 1) {
            return "PANEL_CONFIG_MAPPING";
        }
        if (i == 2) {
            return "PANEL_CONFIG_ODD_EVEN";
        }
        if (i == 3) {
            return "PANEL_CONFIG_MIRROR";
        }
        if (i == 4) {
            return "PANEL_CONFIG_DE_CURRENT";
        }
        if (i == 5) {
            return "PANEL_CONFIG_ODD_CURRENT";
        }
        if (i == 6) {
            return "PANEL_CONFIG_EVEN_CURRENT";
        }
        if (i == 7) {
            return "PANEL_CONFIG_EMC_LVDS_ENABLE";
        }
        if (i == 8) {
            return "PANEL_CONFIG_EMC_LVDS_STEP";
        }
        if (i == 9) {
            return "PANEL_CONFIG_EMC_LVDS_SPAN";
        }
        if (i == 10) {
            return "PANEL_CONFIG_TIMING_WORK_MODE";
        }
        if (i == 16) {
            return "PANEL_PWM_FREQ";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("PANEL_CONFIG_COLOR_DEPTH");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("PANEL_CONFIG_MAPPING");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("PANEL_CONFIG_ODD_EVEN");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("PANEL_CONFIG_MIRROR");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("PANEL_CONFIG_DE_CURRENT");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("PANEL_CONFIG_ODD_CURRENT");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("PANEL_CONFIG_EVEN_CURRENT");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("PANEL_CONFIG_EMC_LVDS_ENABLE");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("PANEL_CONFIG_EMC_LVDS_STEP");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("PANEL_CONFIG_EMC_LVDS_SPAN");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("PANEL_CONFIG_TIMING_WORK_MODE");
            i2 |= 10;
        }
        if ((i & 16) == 16) {
            arrayList.add("PANEL_PWM_FREQ");
            i2 |= 16;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
