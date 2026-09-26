package com.softwinner.tv.common;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvSourceTypes {

    public enum EnumHDMITimingType {
        E_AW_TIMING_TYPE_UNKNOWN,
        E_AW_TIMING_TYPE_VIDEO,
        E_AW_TIMING_TYPE_GRAPHIC
    }

    public enum EnumTvSourceID {
        E_AW_SOURCE_ID_Dummy,
        E_AW_SOURCE_ID_VideoDec,
        E_AW_SOURCE_ID_Image,
        E_AW_SOURCE_ID_HDMI_1,
        E_AW_SOURCE_ID_HDMI_2,
        E_AW_SOURCE_ID_HDMI_3,
        E_AW_SOURCE_ID_HDMI_4,
        E_AW_SOURCE_ID_CVBS_1,
        E_AW_SOURCE_ID_CVBS_2,
        E_AW_SOURCE_ID_CVBS_3,
        E_AW_SOURCE_ID_ATV,
        E_AW_SOURCE_ID_DTV,
        E_AW_SOURCE_ID_MAX;

        public static EnumTvSourceID valueOf(int i) {
            return values()[i];
        }

        @Override // java.lang.Enum
        public String toString() {
            return name().substring(15);
        }
    }
}
