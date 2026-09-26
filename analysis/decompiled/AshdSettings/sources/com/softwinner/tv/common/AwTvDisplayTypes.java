package com.softwinner.tv.common;

import android.support.v4.view.InputDeviceCompat;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.TvSignalID;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvDisplayTypes {

    public static class AwOverScanInfo {
        public int cropDown;
        public int cropLeft;
        public int cropRight;
        public int cropUp;
    }

    public static class AwPictureCurveOsdValue {
        public int v0;
        public int v100;
        public int v25;
        public int v50;
        public int v75;
    }

    public enum EnumDlcLevel {
        E_AW_DLC_LEVEL_OFF,
        E_AW_DLC_LEVEL_LOW,
        E_AW_DLC_LEVEL_MID,
        E_AW_DLC_LEVEL_HIGH
    }

    public enum EnumGammaMode {
        E_AW_FAPI_GAMMA_MODE_DEFAULT,
        E_AW_FAPI_GAMMA_MODE_CUT_WHITE,
        E_AW_FAPI_GAMMA_MODE_DARK,
        E_AW_FAPI_GAMMA_MODE_S,
        E_AW_FAPI_GAMMA_MODE_INCREASE_BLACK,
        E_AW_FAPI_GAMMA_MODE_WHOLE_BRIGHT,
        E_AW_FAPI_GAMMA_MODE_MAX
    }

    public enum EnumOverScanType {
        E_AW_OVERSCAN_LEFT,
        E_AW_OVERSCAN_RIGHT,
        E_AW_OVERSCAN_UP,
        E_AW_OVERSCAN_DOWN
    }

    public enum EnumPQBasicType {
        E_AW_PQ_BASIC_TYPE_BRIGHTNESS,
        E_AW_PQ_BASIC_TYPE_CONTRAST,
        E_AW_PQ_BASIC_TYPE_SATURATION,
        E_AW_PQ_BASIC_TYPE_HUE,
        E_AW_PQ_BASIC_TYPE_SHARPNESS
    }

    public enum EnumPQOtherType {
    }

    public enum EnumTvSourceType {
        E_AW_SOURCE_TYPE_HDMI(0),
        E_AW_SOURCE_TYPE_CVBS(1),
        E_AW_SOURCE_TYPE_ATV(2),
        E_AW_SOURCE_TYPE_DTV(3),
        E_AW_SOURCE_TYPE_VIDEODEC(4),
        E_AW_SOURCE_TYPE_VGA(5),
        E_AW_SOURCE_TYPE_SVIDEO(6),
        E_AW_SOURCE_TYPE_SCART(7),
        E_AW_SOURCE_TYPE_MAX(8);

        private final int value;

        EnumTvSourceType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumColorTempMode {
        E_AW_COLOR_TEMP_STANDARD(0),
        E_AW_COLOR_TEMP_COOL(1),
        E_AW_COLOR_TEMP_WARM(2),
        E_AW_COLOR_TEMP_USER(3);

        private final int value;

        EnumColorTempMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumColorTempMode valueOf(int i) {
            EnumColorTempMode[] enumColorTempModeArrValues = values();
            for (int i2 = 0; i2 < enumColorTempModeArrValues.length; i2++) {
                if (enumColorTempModeArrValues[i2].value == i) {
                    return enumColorTempModeArrValues[i2];
                }
            }
            return E_AW_COLOR_TEMP_STANDARD;
        }
    }

    public enum EnumPQRGBOGType {
        E_AW_PQ_OFFSET_R(0),
        E_AW_PQ_OFFSET_G(1),
        E_AW_PQ_OFFSET_B(2),
        E_AW_PQ_GAIN_R(3),
        E_AW_PQ_GAIN_G(4),
        E_AW_PQ_GAIN_B(5);

        private final int value;

        EnumPQRGBOGType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumPictureMode {
        E_AW_PICTURE_MODE_STANDARD("standard"),
        E_AW_PICTURE_MODE_CINEMA("cinema"),
        E_AW_PICTURE_MODE_VIVID("vivid"),
        E_AW_PICTURE_MODE_GAME("game"),
        E_AW_PICTURE_MODE_COMPUTER("computer"),
        E_AW_PICTURE_MODE_HDR("hdr"),
        E_AW_PICTURE_MODE_ENERGY_SAVING("energy_saving"),
        E_AW_PICTURE_MODE_SPORTS("sports"),
        E_AW_PICTURE_MODE_BRIGHT("bright"),
        E_AW_PICTURE_MODE_SOFT("soft"),
        E_AW_PICTURE_MODE_CALIBRATED("calibrated"),
        E_AW_PICTURE_MODE_CALIBRATED_DARK("calibrateddark"),
        E_AW_PICTURE_MODE_HOME("home"),
        E_AW_PICTURE_MODE_SHOP("shop"),
        E_AW_PICTURE_MODE_ANIMATION("animation"),
        E_AW_PICTURE_MODE_MONITOR("monitor"),
        E_AW_PICTURE_MODE_SONY("sony"),
        E_AW_PICTURE_MODE_SAMSUNG("samsung"),
        E_AW_PICTURE_MODE_RESERVE0("reserve0"),
        E_AW_PICTURE_MODE_RESERVE1("reserve1"),
        E_AW_PICTURE_MODE_RESERVE2("reserve2"),
        E_AW_PICTURE_MODE_CUSTOM("custom"),
        E_AW_PICTURE_MODE_DYNAMIC("dynamic");

        private final String value;

        EnumPictureMode(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }

        public static EnumPictureMode EnumValueOf(String str) {
            EnumPictureMode[] enumPictureModeArrValues = values();
            for (int i = 0; i < enumPictureModeArrValues.length; i++) {
                if (enumPictureModeArrValues[i].value.equals(str)) {
                    return enumPictureModeArrValues[i];
                }
            }
            return E_AW_PICTURE_MODE_STANDARD;
        }
    }

    public enum EnumPanelConfigType {
        E_AW_PANEL_CONFIG_COLOR_DEPTH(0),
        E_AW_PANEL_CONFIG_MAPPING(1),
        E_AW_PANEL_CONFIG_ODD_EVEN(2),
        E_AW_PANEL_CONFIG_MIRROR(3),
        E_AW_PANEL_CONFIG_DE_CURRENT(4),
        E_AW_PANEL_CONFIG_ODD_CURRENT(5),
        E_AW_PANEL_CONFIG_EVEN_CURRENT(6),
        E_AW_PANEL_CONFIG_EMC_LVDS_ENABLE(7),
        E_AW_PANEL_CONFIG_EMC_LVDS_STEP(8),
        E_AW_PANEL_CONFIG_EMC_LVDS_SPAN(9),
        E_AW_PANEL_CONFIG_TIMING_WORK_MODE(10),
        E_AW_PANEL_PWM_FREQ(16);

        private final int value;

        EnumPanelConfigType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumPanelTimingWorkMode {
        E_AW_PANEL_TIMING_WORK_MODE_H_TOTAL(0),
        E_AW_PANEL_TIMING_WORK_MODE_V_TOTAL(1),
        E_AW_PANEL_TIMING_WORK_MODE_H_V_TOTAL(2);

        private final int value;

        EnumPanelTimingWorkMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumPanelTimingWorkMode valueOf(int i) {
            EnumPanelTimingWorkMode[] enumPanelTimingWorkModeArrValues = values();
            for (int i2 = 0; i2 < enumPanelTimingWorkModeArrValues.length; i2++) {
                if (enumPanelTimingWorkModeArrValues[i2].value == i) {
                    return enumPanelTimingWorkModeArrValues[i2];
                }
            }
            return E_AW_PANEL_TIMING_WORK_MODE_H_TOTAL;
        }
    }

    public enum EnumPanelColorDepth {
        E_AW_PANEL_COLOR_DEPTH_6BITS(6),
        E_AW_PANEL_COLOR_DEPTH_8BITS(8),
        E_AW_PANEL_COLOR_DEPTH_10BITS(10);

        private final int value;

        EnumPanelColorDepth(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumPanelMapping {
        E_AW_PANEL_MAPPING_VESA(0),
        E_AW_PANEL_MAPPING_THINE(1),
        E_AW_PANEL_MAPPING_JEIDA(2),
        E_AW_PANEL_MAPPING_PDP(3),
        E_AW_PANEL_MAPPING_MAX(4);

        private final int value;

        EnumPanelMapping(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumPanelOddEven {
        E_AW_PANEL_ODD_EVEN_DEFAULT(0),
        E_AW_PANEL_ODD_EVEN_SWAPPED(1);

        private final int value;

        EnumPanelOddEven(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumPanelMirror {
        E_AW_PANEL_MIRROR_DEFAULT(0),
        E_AW_PANEL_MIRROR_HORIZONTAL(1),
        E_AW_PANEL_MIRROR_VERTICAL(2),
        E_AW_PANEL_MIRROR_HV(3);

        private final int value;

        EnumPanelMirror(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumOverScanScreenMode {
        E_AW_OVERSCAN_SCREEN_MODE_FULL,
        E_AW_OVERSCAN_SCREEN_MODE_16X9,
        E_AW_OVERSCAN_SCREEN_MODE_4X3,
        E_AW_OVERSCAN_SCREEN_MODE_CINEMA,
        E_AW_OVERSCAN_SCREEN_MODE_ZOOM,
        E_AW_OVERSCAN_SCREEN_MODE_AUTO,
        E_AW_OVERSCAN_SCREEN_MODE_DOT_BY_DOT,
        E_AW_OVERSCAN_SCREEN_MODE_MAX;

        public static EnumOverScanScreenMode valueOf(int i) {
            return values()[i];
        }
    }

    public enum EnumOverScanTiming {
        E_AW_OVERSCAN_NOSIGNAL(0),
        E_AW_OVERSCAN_UNKNOW(1),
        E_AW_OVERSCAN_NOCHANGE(2),
        E_AW_OVERSCAN_SIG_PAL(16),
        E_AW_OVERSCAN_SIG_PALM(17),
        E_AW_OVERSCAN_SIG_PALNC(18),
        E_AW_OVERSCAN_SIG_PAL60(19),
        E_AW_OVERSCAN_SIG_NTSC(20),
        E_AW_OVERSCAN_SIG_NTSC443(21),
        E_AW_OVERSCAN_480I(32),
        E_AW_OVERSCAN_480P(33),
        E_AW_OVERSCAN_480P640(34),
        E_AW_OVERSCAN_240P720(35),
        E_AW_OVERSCAN_240P1440(36),
        E_AW_OVERSCAN_288P720(37),
        E_AW_OVERSCAN_288P1440(38),
        E_AW_OVERSCAN_576I(39),
        E_AW_OVERSCAN_576P(40),
        E_AW_OVERSCAN_720P(41),
        E_AW_OVERSCAN_960P(42),
        E_AW_OVERSCAN_1080I(43),
        E_AW_OVERSCAN_1080P(44),
        E_AW_OVERSCAN_1440P2560(45),
        E_AW_OVERSCAN_2160P1920(46),
        E_AW_OVERSCAN_2160P3840(47),
        E_AW_OVERSCAN_2160P4096(48),
        E_AW_OVERSCAN_PC_640_480(64),
        E_AW_OVERSCAN_PC_720_400(65),
        E_AW_OVERSCAN_PC_720_480(66),
        E_AW_OVERSCAN_PC_800_600(67),
        E_AW_OVERSCAN_PC_960_600(68),
        E_AW_OVERSCAN_PC_1024_768(69),
        E_AW_OVERSCAN_PC_1152_864(70),
        E_AW_OVERSCAN_PC_1280_720(71),
        E_AW_OVERSCAN_PC_1280_768(72),
        E_AW_OVERSCAN_PC_1280_800(73),
        E_AW_OVERSCAN_PC_1280_960(74),
        E_AW_OVERSCAN_PC_1280_1024(75),
        E_AW_OVERSCAN_PC_1360_768(76),
        E_AW_OVERSCAN_PC_1366_768(77),
        E_AW_OVERSCAN_PC_1440_900(78),
        E_AW_OVERSCAN_PC_1400_1050(79),
        E_AW_OVERSCAN_PC_1920_1080(80),
        E_AW_OVERSCAN_PC_2560_1440(81),
        E_AW_OVERSCAN_PC_3840_2160(82),
        E_AW_OVERSCAN_PC_4096_2160(83),
        E_AW_OVERSCAN_PC_1680_1050(84),
        E_AW_OVERSCAN_SCART_NTSC(128),
        E_AW_OVERSCAN_SCART_PAL(TvSignalID.SIGNALID_SCART_PAL),
        E_AW_OVERSCAN_SCART_SECAM(130),
        E_AW_OVERSCAN_SCART_MAC640480(TvSignalID.SIGNALID_SCART_MAC640480),
        E_AW_OVERSCAN_SCART_MAC1152870(TvSignalID.SIGNALID_SCART_MAC1152870),
        E_AW_OVERSCAN_SCART_MAC832624(TvSignalID.SIGNALID_SCART_MAC832624),
        E_AW_OVERSCAN_TIMING_MAX(256);

        private final int value;

        EnumOverScanTiming(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumOverScanTiming valueOf(int i) {
            EnumOverScanTiming[] enumOverScanTimingArrValues = values();
            for (int i2 = 0; i2 < enumOverScanTimingArrValues.length; i2++) {
                if (enumOverScanTimingArrValues[i2].value == i) {
                    return enumOverScanTimingArrValues[i2];
                }
            }
            return E_AW_OVERSCAN_TIMING_MAX;
        }

        @Override // java.lang.Enum
        public String toString() {
            return name().substring(14);
        }
    }

    public enum FactoryPictureParamType {
        E_AW_PQ_ADVANCE_ENABLE(0),
        E_AW_PQ_ADVANCE_LUMA_SETTINGS(1),
        E_AW_PQ_ADVANCE_NR_SETTINGS(2),
        E_AW_PQ_ADVANCE_CTI_SETTINGS(3),
        E_AW_PQ_ADVANCE_SSR_SETTINGS(4),
        E_AW_PQ_ADVANCE_GAMMA_MODE(5),
        E_AW_PQ_ADVANCE_PICTURE_PARAM_TYPE_MAX(6);

        private final int value;

        FactoryPictureParamType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum FactoryPictureItemType {
        E_AW_PQ_ENABLE_ALL(0),
        E_AW_TNR_SETTINGS_LUMA_ENABLE(1),
        E_AW_TNR_SETTINGS_CHROMA_ENABLE(2),
        E_AW_SNR_SETTINGS_HBLK_FILTER_ENABLE(3),
        E_AW_SNR_SETTINGS_VBNR_FILTER_ENABLE(4),
        E_AW_SNR_SETTINGS_WMNR_FILTER_ENABLE(5),
        E_AW_CTI_SETTINGS_PRE_DCTI_ENABLE(6),
        E_AW_CTI_SETTINGS_POST_DCTI_ENABLE(7),
        E_AW_SSR_SETTINGS_GENERAL_SSR_ENABLE(8),
        E_AW_SSR_SETTINGS_PEAKING_ENBALE(9),
        E_AW_CM_SETTINGS_ENABLE(10),
        E_AW_DCI_SETTINGS_ENABLE(11),
        E_AW_PEDESTAL_SETTINGS_PRE_Y_SETTING_ENABLE(12),
        E_AW_GAMMA_SETTINGS_ENABLE(13),
        E_AW_BLACK_EXTENSION_SETTINGS_ENABLE(14),
        E_AW_PICTURE_PARAM_TSE_ENABLE(15),
        E_AW_LUMA_SETTINGS_PRE_Y_GAIN(256),
        E_AW_LUMA_SETTINGS_PRE_Y_OFFSET(InputDeviceCompat.SOURCE_KEYBOARD),
        E_AW_SNR_SETTINGS_SNR_TABLE(512),
        E_AW_SNR_SETTINGS_HBLK_DETECTION_THR(InputDeviceCompat.SOURCE_DPAD),
        E_AW_SNR_SETTINGS_HBLK_ALPHA_GEN_THR(514),
        E_AW_SNR_SETTINGS_HBLK_ALPHA_GEN_TRANS(515),
        E_AW_SNR_SETTINGS_HBLK_EDGE_ALPHA_GEN_THR(516),
        E_AW_SNR_SETTINGS_HBLK_EDGE_ALPHA_GEN_TRANS(517),
        E_AW_SNR_SETTINGS_VBLK_DETECTION_THR(529),
        E_AW_SNR_SETTINGS_VBLK_ALPHA_GEN_THR(530),
        E_AW_SNR_SETTINGS_VBLK_ALPHA_GEN_TRANS(531),
        E_AW_SNR_SETTINGS_VBLK_EDGE_ALPHA_GEN_THR(532),
        E_AW_SNR_SETTINGS_VBLK_EDGE_ALPHA_GEN_TRANS(533),
        E_AW_SNR_SETTINGS_WNR_ALPHA_THR(545),
        E_AW_SNR_SETTINGS_WNR_ALPHA_TRANS(546),
        E_AW_SNR_SETTINGS_MNR_ALPHA_THR(547),
        E_AW_SNR_SETTINGS_MNR_ALPHA_TRANS(548),
        E_AW_SNR_SETTINGS_WGN_ALPHA_THR(549),
        E_AW_SNR_SETTINGS_WGN_ALPHA_TRANS(550),
        E_AW_SNR_LOW_TABLE_INDEX(551),
        E_AW_SNR_MIDDLE_TABLE_INDEX(552),
        E_AW_SNR_HIGH_TABLE_INDEX(553),
        E_AW_CTI_SETTINGS_PRE_DCTI_GAIN(768),
        E_AW_CTI_SETTINGS_PRE_DCTI_STEP(769),
        E_AW_CTI_SETTINGS_POST_DCTI_GAIN(784),
        E_AW_CTI_SETTINGS_POST_DCTI_STEP(785),
        E_AW_SSR_SETTINGS_GENERAL_POS_GAIN(1024),
        E_AW_SSR_SETTINGS_GENERAL_NEG_GAIN(InputDeviceCompat.SOURCE_GAMEPAD),
        E_AW_SSR_SETTINGS_PEAKING_NORM_H_GAIN_2(1040),
        E_AW_SSR_SETTINGS_PEAKING_NORM_H_GAIN_4(1041),
        E_AW_SSR_SETTINGS_PEAKING_NORM_H_GAIN_6(1042),
        E_AW_SSR_SETTINGS_PEAKING_NORM_H_GAIN_8(1043),
        E_AW_SSR_SETTINGS_PEAKING_NORM_V_GAIN_2(1044),
        E_AW_SSR_SETTINGS_PEAKING_NORM_V_GAIN_4(1045),
        E_AW_SSR_SETTINGS_PEAKING_NORM_V_GAIN_6(1046),
        E_AW_SSR_SETTINGS_PEAKING_NORM_S_GAIN_2(1047),
        E_AW_SSR_SETTINGS_PEAKING_NORM_S_GAIN_4(1048),
        E_AW_SSR_SETTINGS_PEAKING_DEBUG(1056),
        E_AW_SSR_SETTINGS_SSR_TABLE(1057),
        E_AW_SSR_DEFAULT_TABLE_INDEX(1058);

        private final int value;

        FactoryPictureItemType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum VideoRangeMode {
        E_AW_VIDEO_RANGE_MODE_AUTO(0),
        E_AW_VIDEO_RANGE_MODE_LIMIT(1),
        E_AW_VIDEO_RANGE_MODE_FULL(2),
        E_AW_VIDEO_RANGE_MODE_MAX(3);

        private final int value;

        VideoRangeMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumVideoPCMode {
        E_AW_HDMI_VIDEO_MODE_AUTO(0),
        E_AW_HDMI_VIDEO_MODE_HDMI_VIDEO(1),
        E_AW_HDMI_VIDEO_MODE_DVI_PC_GRAPHIC(2);

        private final int value;

        EnumVideoPCMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumVideoPCMode valueOf(int i) {
            EnumVideoPCMode[] enumVideoPCModeArrValues = values();
            for (int i2 = 0; i2 < enumVideoPCModeArrValues.length; i2++) {
                if (enumVideoPCModeArrValues[i2].value == i) {
                    return enumVideoPCModeArrValues[i2];
                }
            }
            return E_AW_HDMI_VIDEO_MODE_AUTO;
        }
    }

    public enum EnumNonLinearPQCurveOsdPoint {
        E_AW_PICTURE_CURVE_OSD_V0(0),
        E_AW_PICTURE_CURVE_OSD_V25(1),
        E_AW_PICTURE_CURVE_OSD_V50(2),
        E_AW_PICTURE_CURVE_OSD_V75(3),
        E_AW_PICTURE_CURVE_OSD_V100(4);

        private final int value;

        EnumNonLinearPQCurveOsdPoint(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumColorManagerGainItem {
        E_AW_CM_GAIN_HUE(0),
        E_AW_CM_GAIN_SATURATION(1),
        E_AW_CM_GAIN_VALUE(2),
        E_AW_CM_GAIN_MAX(3);

        private final int value;

        EnumColorManagerGainItem(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumColorManagerRange {
        E_AW_CM_RANGE_RED(0),
        E_AW_CM_RANGE_LIPS(1),
        E_AW_CM_RANGE_SKINTONE(2),
        E_AW_CM_RANGE_YELLOW(3),
        E_AW_CM_RANGE_YELLOW_GREEN(4),
        E_AW_CM_RANGE_GREEN(5),
        E_AW_CM_RANGE_CYAN(6),
        E_AW_CM_RANGE_BLUE(7),
        E_AW_CM_RANGE_BLUE_PURPLE(8),
        E_AW_CM_RANGE_PURPLE(9),
        E_AW_CM_RANGE_RESERVE0(10),
        E_AW_CM_RANGE_RESERVE1(11),
        E_AW_CM_RANGE_MAX(12);

        private final int value;

        EnumColorManagerRange(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }
}
