package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ConfigType {
    public static final int CONFIG_BOOTLOGO = 1;
    public static final int CONFIG_CHANNEL = 3;
    public static final int CONFIG_DDR_SSC_ENABLE = 40;
    public static final int CONFIG_DDR_SSC_SPAN = 41;
    public static final int CONFIG_DDR_SSC_STEP = 42;
    public static final int CONFIG_PANEL = 2;
    public static final int CONFIG_PQ_COLOR_TEMP = 9;
    public static final int CONFIG_PQ_CUSTOM_XML = 7;
    public static final int CONFIG_PQ_DB = 5;
    public static final int CONFIG_PQ_DEFAULT_XML = 4;
    public static final int CONFIG_PQ_FACTORY_DB = 6;
    public static final int CONFIG_PQ_FACTORY_EXTERN_INI = 8;
    public static final int CONFIG_PQ_OVERSCAN = 10;
    public static final int CONFIG_TIMER_DIVIDER_AUTOSTANDBY = 35;
    public static final int CONFIG_TIMER_DIVIDER_NOSIGNAL = 33;
    public static final int CONFIG_TIMER_DIVIDER_SLEEP = 34;

    public static final String toString(int i) {
        if (i == 1) {
            return "CONFIG_BOOTLOGO";
        }
        if (i == 2) {
            return "CONFIG_PANEL";
        }
        if (i == 3) {
            return "CONFIG_CHANNEL";
        }
        if (i == 4) {
            return "CONFIG_PQ_DEFAULT_XML";
        }
        if (i == 5) {
            return "CONFIG_PQ_DB";
        }
        if (i == 6) {
            return "CONFIG_PQ_FACTORY_DB";
        }
        if (i == 7) {
            return "CONFIG_PQ_CUSTOM_XML";
        }
        if (i == 8) {
            return "CONFIG_PQ_FACTORY_EXTERN_INI";
        }
        if (i == 9) {
            return "CONFIG_PQ_COLOR_TEMP";
        }
        if (i == 10) {
            return "CONFIG_PQ_OVERSCAN";
        }
        if (i == 33) {
            return "CONFIG_TIMER_DIVIDER_NOSIGNAL";
        }
        if (i == 34) {
            return "CONFIG_TIMER_DIVIDER_SLEEP";
        }
        if (i == 35) {
            return "CONFIG_TIMER_DIVIDER_AUTOSTANDBY";
        }
        if (i == 40) {
            return "CONFIG_DDR_SSC_ENABLE";
        }
        if (i == 41) {
            return "CONFIG_DDR_SSC_SPAN";
        }
        if (i == 42) {
            return "CONFIG_DDR_SSC_STEP";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("CONFIG_BOOTLOGO");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("CONFIG_PANEL");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("CONFIG_CHANNEL");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("CONFIG_PQ_DEFAULT_XML");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("CONFIG_PQ_DB");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("CONFIG_PQ_FACTORY_DB");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("CONFIG_PQ_CUSTOM_XML");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("CONFIG_PQ_FACTORY_EXTERN_INI");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("CONFIG_PQ_COLOR_TEMP");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("CONFIG_PQ_OVERSCAN");
            i2 |= 10;
        }
        if ((i & 33) == 33) {
            arrayList.add("CONFIG_TIMER_DIVIDER_NOSIGNAL");
            i2 |= 33;
        }
        if ((i & 34) == 34) {
            arrayList.add("CONFIG_TIMER_DIVIDER_SLEEP");
            i2 |= 34;
        }
        if ((i & 35) == 35) {
            arrayList.add("CONFIG_TIMER_DIVIDER_AUTOSTANDBY");
            i2 |= 35;
        }
        if ((i & 40) == 40) {
            arrayList.add("CONFIG_DDR_SSC_ENABLE");
            i2 |= 40;
        }
        if ((i & 41) == 41) {
            arrayList.add("CONFIG_DDR_SSC_SPAN");
            i2 |= 41;
        }
        if ((i & 42) == 42) {
            arrayList.add("CONFIG_DDR_SSC_STEP");
            i2 |= 42;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
