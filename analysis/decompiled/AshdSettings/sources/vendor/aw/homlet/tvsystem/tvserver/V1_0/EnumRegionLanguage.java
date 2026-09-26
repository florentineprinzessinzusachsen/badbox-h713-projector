package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class EnumRegionLanguage {
    public static final int REGION_LANGUAGE_ARABIC = 4;
    public static final int REGION_LANGUAGE_BALTIC = 2;
    public static final int REGION_LANGUAGE_CENTER_EUROPE = 1;
    public static final int REGION_LANGUAGE_CHINESE_SIMPLIFIED = 10;
    public static final int REGION_LANGUAGE_CYRILLIC = 3;
    public static final int REGION_LANGUAGE_DEFAULT = 255;
    public static final int REGION_LANGUAGE_GREEK = 5;
    public static final int REGION_LANGUAGE_HEBREW = 6;
    public static final int REGION_LANGUAGE_THAL = 8;
    public static final int REGION_LANGUAGE_TURKEY = 7;
    public static final int REGION_LANGUAGE_VIETNAMESE = 9;
    public static final int REGION_LANGUAGE_WEST_EUROPE = 0;

    public static final String toString(int i) {
        if (i == 0) {
            return "REGION_LANGUAGE_WEST_EUROPE";
        }
        if (i == 1) {
            return "REGION_LANGUAGE_CENTER_EUROPE";
        }
        if (i == 2) {
            return "REGION_LANGUAGE_BALTIC";
        }
        if (i == 3) {
            return "REGION_LANGUAGE_CYRILLIC";
        }
        if (i == 4) {
            return "REGION_LANGUAGE_ARABIC";
        }
        if (i == 5) {
            return "REGION_LANGUAGE_GREEK";
        }
        if (i == 6) {
            return "REGION_LANGUAGE_HEBREW";
        }
        if (i == 7) {
            return "REGION_LANGUAGE_TURKEY";
        }
        if (i == 8) {
            return "REGION_LANGUAGE_THAL";
        }
        if (i == 9) {
            return "REGION_LANGUAGE_VIETNAMESE";
        }
        if (i == 10) {
            return "REGION_LANGUAGE_CHINESE_SIMPLIFIED";
        }
        if (i == 255) {
            return "REGION_LANGUAGE_DEFAULT";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("REGION_LANGUAGE_WEST_EUROPE");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("REGION_LANGUAGE_CENTER_EUROPE");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("REGION_LANGUAGE_BALTIC");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("REGION_LANGUAGE_CYRILLIC");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("REGION_LANGUAGE_ARABIC");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("REGION_LANGUAGE_GREEK");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("REGION_LANGUAGE_HEBREW");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("REGION_LANGUAGE_TURKEY");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("REGION_LANGUAGE_THAL");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("REGION_LANGUAGE_VIETNAMESE");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("REGION_LANGUAGE_CHINESE_SIMPLIFIED");
            i2 |= 10;
        }
        if ((i & 255) == 255) {
            arrayList.add("REGION_LANGUAGE_DEFAULT");
            i2 |= 255;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
