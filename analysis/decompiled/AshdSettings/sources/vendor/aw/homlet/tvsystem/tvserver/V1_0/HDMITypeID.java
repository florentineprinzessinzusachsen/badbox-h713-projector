package vendor.aw.homlet.tvsystem.tvserver.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class HDMITypeID {
    public static final int HOST_CMD_CHECK_EDID_UPDATE_STATUS = 105;
    public static final int HOST_CMD_GET_HDMI_EDID = 109;
    public static final int HOST_CMD_GET_HDMI_PORT_NUMBER = 103;
    public static final int HOST_CMD_PRINT_EDID_TABLE = 110;
    public static final int HOST_CMD_SET_EDID_DYNAMIC_AUDIO_MODE = 106;
    public static final int HOST_CMD_SET_EDID_VERSION = 107;
    public static final int HOST_CMD_SET_HDMI_5V_Flag = 108;
    public static final int HOST_CMD_UPDATE_EDID = 104;

    public static final String toString(int i) {
        if (i == 103) {
            return "HOST_CMD_GET_HDMI_PORT_NUMBER";
        }
        if (i == 104) {
            return "HOST_CMD_UPDATE_EDID";
        }
        if (i == 105) {
            return "HOST_CMD_CHECK_EDID_UPDATE_STATUS";
        }
        if (i == 106) {
            return "HOST_CMD_SET_EDID_DYNAMIC_AUDIO_MODE";
        }
        if (i == 107) {
            return "HOST_CMD_SET_EDID_VERSION";
        }
        if (i == 108) {
            return "HOST_CMD_SET_HDMI_5V_Flag";
        }
        if (i == 109) {
            return "HOST_CMD_GET_HDMI_EDID";
        }
        if (i == 110) {
            return "HOST_CMD_PRINT_EDID_TABLE";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 103;
        if ((i & 103) == 103) {
            arrayList.add("HOST_CMD_GET_HDMI_PORT_NUMBER");
        } else {
            i2 = 0;
        }
        if ((i & 104) == 104) {
            arrayList.add("HOST_CMD_UPDATE_EDID");
            i2 |= 104;
        }
        if ((i & 105) == 105) {
            arrayList.add("HOST_CMD_CHECK_EDID_UPDATE_STATUS");
            i2 |= 105;
        }
        if ((i & 106) == 106) {
            arrayList.add("HOST_CMD_SET_EDID_DYNAMIC_AUDIO_MODE");
            i2 |= 106;
        }
        if ((i & 107) == 107) {
            arrayList.add("HOST_CMD_SET_EDID_VERSION");
            i2 |= 107;
        }
        if ((i & 108) == 108) {
            arrayList.add("HOST_CMD_SET_HDMI_5V_Flag");
            i2 |= 108;
        }
        if ((i & 109) == 109) {
            arrayList.add("HOST_CMD_GET_HDMI_EDID");
            i2 |= 109;
        }
        if ((i & 110) == 110) {
            arrayList.add("HOST_CMD_PRINT_EDID_TABLE");
            i2 |= 110;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
