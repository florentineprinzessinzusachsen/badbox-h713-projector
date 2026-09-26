package com.softwinner.tv.common;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvSystemTypes {

    public enum EnumPowerMode {
        E_AW_POWER_MODE_STANDBY(0),
        E_AW_POWER_MODE_DIRECT(1),
        E_AW_POWER_MODE_MEMORY(2);

        private final int value;

        EnumPowerMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumPowerMode valueOf(int i) {
            EnumPowerMode[] enumPowerModeArrValues = values();
            for (int i2 = 0; i2 < enumPowerModeArrValues.length; i2++) {
                if (enumPowerModeArrValues[i2].value == i) {
                    return enumPowerModeArrValues[i2];
                }
            }
            return E_AW_POWER_MODE_STANDBY;
        }
    }

    public enum EnumConfigType {
        E_AW_CONFIG_DDR_ENABLE(0),
        E_AW_CONFIG_DDR_STEP(1),
        E_AW_CONFIG_DDR_SPAN(2);

        private final int value;

        EnumConfigType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumDDRSpanLevel {
        E_AW_DDR_SPAN_LEVEL_4_8(0),
        E_AW_DDR_SPAN_LEVEL_9_6(1),
        E_AW_DDR_SPAN_LEVEL_14_4(2),
        E_AW_DDR_SPAN_LEVEL_19_2(3),
        E_AW_DDR_SPAN_LEVEL_24(4);

        private final int value;

        EnumDDRSpanLevel(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return name().substring(20);
        }
    }

    public enum EnumWakeUpReason {
        E_AW_WAKE_UP_REASON_UNKNOWN(0),
        E_AW_WAKE_UP_REASON_RTC(1),
        E_AW_WAKE_UP_REASON_IR(2),
        E_AW_WAKE_UP_REASON_KEY(3),
        E_AW_WAKE_UP_REASON_HDMI(4),
        E_AW_WAKE_UP_REASON_WIFI(5),
        E_AW_WAKE_UP_REASON_ETH(6),
        E_AW_WAKE_UP_REASON_APP(7),
        E_AW_WAKE_UP_REASON_PLUG(8),
        E_AW_WAKE_UP_REASON_GESTURE(9),
        E_AW_WAKE_UP_REASON_CAMERA(16),
        E_AW_WAKE_UP_REASON_MOTION(17);

        private final int value;

        EnumWakeUpReason(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumWakeUpReason valueOf(int i) {
            EnumWakeUpReason[] enumWakeUpReasonArrValues = values();
            for (int i2 = 0; i2 < enumWakeUpReasonArrValues.length; i2++) {
                if (enumWakeUpReasonArrValues[i2].value == i) {
                    return enumWakeUpReasonArrValues[i2];
                }
            }
            return E_AW_WAKE_UP_REASON_UNKNOWN;
        }
    }

    public enum EnumHdmiRxEdidType {
        E_AW_HDMI_EDID_Version_14(0),
        E_AW_HDMI_EDID_Version_20(1);

        private final int value;

        EnumHdmiRxEdidType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumHdmiRxEdidType valueOf(int i) {
            EnumHdmiRxEdidType[] enumHdmiRxEdidTypeArrValues = values();
            for (int i2 = 0; i2 < enumHdmiRxEdidTypeArrValues.length; i2++) {
                if (enumHdmiRxEdidTypeArrValues[i2].value == i) {
                    return enumHdmiRxEdidTypeArrValues[i2];
                }
            }
            return E_AW_HDMI_EDID_Version_14;
        }
    }
}
