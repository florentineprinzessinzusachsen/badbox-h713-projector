package com.softwinner.tv.common;

import android.media.AudioManagerEx;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvAudioTypes {
    public static final int HEADPHONE_OUTPUT_GAIN_MAX = 7;
    public static final int HEADPHONE_OUTPUT_GAIN_MIN = 0;
    public static final int OWA_OUTPUT_GAIN_MAX = 120;
    public static final int OWA_OUTPUT_GAIN_MIN = -50;
    public static final int PEQ_BAND1_FREQ_MAX = 2047;
    public static final int PEQ_BAND1_FREQ_MIN = 1;
    public static final int PEQ_BAND_FREQ_MAX = 20470;
    public static final int PEQ_BAND_FREQ_MIN = 10;
    public static final int PEQ_BAND_FREQ_STEP = 10;
    public static final int PEQ_BAND_GAIN_MAX = 12;
    public static final int PEQ_BAND_GAIN_MIN = -19;
    public static final int PEQ_BAND_NUM = 10;
    public static final int PEQ_BAND_Q_MAX = 255;
    public static final int PEQ_BAND_Q_MIN = 0;
    public static final int PEQ_HIGH_SHELF_FILTER = 1;
    public static final int PEQ_LOW_SHELF_FILTER = 0;
    public static final int PEQ_OVERALL_GAIN_MAX = 176;
    public static final int PEQ_OVERALL_GAIN_MIN = 0;
    public static final int SPEAKER_OUTPUT_GAIN_MAX = 31;
    public static final int SPEAKER_OUTPUT_GAIN_MIN = 0;

    public enum EnumATVMTSInput {
        E_AW_ATV_MTS_FM_MONO,
        E_AW_ATV_MTS_AM_MONO,
        E_AW_ATV_MTS_A2_MONO,
        E_AW_ATV_MTS_A2_STEREO,
        E_AW_ATV_MTS_A2_DUAL,
        E_AW_ATV_MTS_FM_MONO_NICAM_MONO,
        E_AW_ATV_MTS_FM_MONO_NICAM_STEREO,
        E_AW_ATV_MTS_FM_MONO_NICAM_DUAL,
        E_AW_ATV_MTS_BTSC_MONO,
        E_AW_ATV_MTS_BTSC_STEREO,
        E_AW_ATV_MTS_BTSC_MONO_SAP,
        E_AW_ATV_MTS_BTSC_STEREO_SAP,
        E_AW_ATV_MTS_JAPAN_EIAJ
    }

    public enum EnumATVMtsOutput {
        E_AW_ATV_MTS_OUT_FM_MONO,
        E_AW_ATV_MTS_OUT_AM_MONO,
        E_AW_ATV_MTS_OUT_A2_MONO,
        E_AW_ATV_MTS_OUT_A2_STEREO,
        E_AW_ATV_MTS_OUT_A2_DUAL1,
        E_AW_ATV_MTS_OUT_A2_DUAL2,
        E_AW_ATV_MTS_OUT_A2_DUAL1_PLUS_DUAL2,
        E_AW_ATV_MTS_OUT_NICAM_MONO,
        E_AW_ATV_MTS_OUT_NICAM_STEREO,
        E_AW_ATV_MTS_OUT_NICAM_DUAL1,
        E_AW_ATV_MTS_OUT_NICAM_DUAL2,
        E_AW_ATV_MTS_OUT_NICAM_DUAL1_PLUS_DUAL2,
        E_AW_ATV_MTS_OUT_BTSC_MONO,
        E_AW_ATV_MTS_OUT_BTSC_STEREO,
        E_AW_ATV_MTS_OUT_BTSC_SAP,
        E_AW_ATV_MTS_OUT_BTSC_MONO_SAP,
        E_AW_ATV_MTS_OUT_JAPAN_EIAJ
    }

    public enum EnumAVCEffectType {
        E_AW_AVC_EFFECT_GAIN,
        E_AW_AVC_EFFECT_THRESHOLD,
        E_AW_AVC_EFFECT_LIMIT,
        E_AW_AVC_EFFECT_DECAY
    }

    public enum EnumAudioCurveIdndex {
        CURVE_MAP_OSD_0,
        CURVE_MAP_OSD_1,
        CURVE_MAP_OSD_2,
        CURVE_MAP_OSD_3,
        CURVE_MAP_OSD_4,
        CURVE_MAP_OSD_5,
        CURVE_MAP_OSD_6,
        CURVE_MAP_OSD_7,
        CURVE_MAP_OSD_8,
        CURVE_MAP_OSD_9,
        CURVE_MAP_OSD_10,
        CURVE_MAP_OSD_11,
        CURVE_MAP_OSD_12,
        CURVE_MAP_OSD_13,
        CURVE_MAP_OSD_14,
        CURVE_MAP_OSD_15,
        CURVE_MAP_OSD_16,
        CURVE_MAP_OSD_17,
        CURVE_MAP_OSD_18,
        CURVE_MAP_OSD_19,
        CURVE_MAP_OSD_20,
        CURVE_MAP_OSD_21,
        CURVE_MAP_OSD_22,
        CURVE_MAP_OSD_23,
        CURVE_MAP_OSD_24,
        CURVE_MAP_OSD_25,
        CURVE_MAP_OSD_26,
        CURVE_MAP_OSD_27,
        CURVE_MAP_OSD_28,
        CURVE_MAP_OSD_29,
        CURVE_MAP_OSD_30,
        CURVE_MAP_OSD_31,
        CURVE_MAP_OSD_32,
        CURVE_MAP_OSD_33,
        CURVE_MAP_OSD_34,
        CURVE_MAP_OSD_35,
        CURVE_MAP_OSD_36,
        CURVE_MAP_OSD_37,
        CURVE_MAP_OSD_38,
        CURVE_MAP_OSD_39,
        CURVE_MAP_OSD_40,
        CURVE_MAP_OSD_41,
        CURVE_MAP_OSD_42,
        CURVE_MAP_OSD_43,
        CURVE_MAP_OSD_44,
        CURVE_MAP_OSD_45,
        CURVE_MAP_OSD_46,
        CURVE_MAP_OSD_47,
        CURVE_MAP_OSD_48,
        CURVE_MAP_OSD_49,
        CURVE_MAP_OSD_50,
        CURVE_MAP_OSD_51,
        CURVE_MAP_OSD_52,
        CURVE_MAP_OSD_53,
        CURVE_MAP_OSD_54,
        CURVE_MAP_OSD_55,
        CURVE_MAP_OSD_56,
        CURVE_MAP_OSD_57,
        CURVE_MAP_OSD_58,
        CURVE_MAP_OSD_59,
        CURVE_MAP_OSD_60,
        CURVE_MAP_OSD_61,
        CURVE_MAP_OSD_62,
        CURVE_MAP_OSD_63,
        CURVE_MAP_OSD_64,
        CURVE_MAP_OSD_65,
        CURVE_MAP_OSD_66,
        CURVE_MAP_OSD_67,
        CURVE_MAP_OSD_68,
        CURVE_MAP_OSD_69,
        CURVE_MAP_OSD_70,
        CURVE_MAP_OSD_71,
        CURVE_MAP_OSD_72,
        CURVE_MAP_OSD_73,
        CURVE_MAP_OSD_74,
        CURVE_MAP_OSD_75,
        CURVE_MAP_OSD_76,
        CURVE_MAP_OSD_77,
        CURVE_MAP_OSD_78,
        CURVE_MAP_OSD_79,
        CURVE_MAP_OSD_80,
        CURVE_MAP_OSD_81,
        CURVE_MAP_OSD_82,
        CURVE_MAP_OSD_83,
        CURVE_MAP_OSD_84,
        CURVE_MAP_OSD_85,
        CURVE_MAP_OSD_86,
        CURVE_MAP_OSD_87,
        CURVE_MAP_OSD_88,
        CURVE_MAP_OSD_89,
        CURVE_MAP_OSD_90,
        CURVE_MAP_OSD_91,
        CURVE_MAP_OSD_92,
        CURVE_MAP_OSD_93,
        CURVE_MAP_OSD_94,
        CURVE_MAP_OSD_95,
        CURVE_MAP_OSD_96,
        CURVE_MAP_OSD_97,
        CURVE_MAP_OSD_98,
        CURVE_MAP_OSD_99,
        CURVE_MAP_OSD_100
    }

    public enum EnumAudioOWAOutMode {
        E_AW_AUDIO_DIGITAL_RAW,
        E_AW_AUDIO_DIGITAL_PCM,
        E_AW_AUDIO_DIGITAL_AUTO
    }

    public enum EnumGEQBand {
        E_AW_GEQ_BAND_1,
        E_AW_GEQ_BAND_2,
        E_AW_GEQ_BAND_3,
        E_AW_GEQ_BAND_4,
        E_AW_GEQ_BAND_5,
        E_AW_GEQ_BAND_6,
        E_AW_GEQ_BAND_7
    }

    public enum EnumGEQType {
        E_AW_GEQ_TYPE_STANDARD,
        E_AW_GEQ_TYPE_MUSIC,
        E_AW_GEQ_TYPE_MOVIE,
        E_AW_GEQ_TYPE_NEWS,
        E_AW_GEQ_TYPE_USER
    }

    public enum EnumAudioOutMode {
        E_AW_AUDIO_OUT_MODE_SPEAKER(AudioManagerEx.AUDIO_NAME_SPK),
        E_AW_AUDIO_OUT_MODE_HEADPHONE(AudioManagerEx.AUDIO_NAME_HEADPHONE),
        E_AW_AUDIO_OUT_MODE_OWA(AudioManagerEx.AUDIO_NAME_OWA),
        E_AW_AUDIO_OUT_MODE_ARC(AudioManagerEx.AUDIO_NAME_ARC),
        E_AW_AUDIO_OUT_MODE_A2DP(AudioManagerEx.AUDIO_NAME_A2DP);

        private final String value;

        EnumAudioOutMode(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:20:0x003a  */
        public static EnumAudioOutMode getEnum(String str) {
            switch (str) {
                case "AUDIO_OWA":
                    return E_AW_AUDIO_OUT_MODE_OWA;
                case "AUDIO_ARC":
                    return E_AW_AUDIO_OUT_MODE_ARC;
                case "AUDIO_A2DP":
                    return E_AW_AUDIO_OUT_MODE_A2DP;
                case "AUDIO_HEADPHONE":
                    return E_AW_AUDIO_OUT_MODE_HEADPHONE;
                case "AUDIO_SPEAKER":
                default:
                    return E_AW_AUDIO_OUT_MODE_SPEAKER;
            }
        }
    }

    public enum EnumATVStandardMode {
        E_AW_ATV_STANDARD_MODE_AUTO,
        E_AW_ATV_STANDARD_MODE_BG,
        E_AW_ATV_STANDARD_MODE_I,
        E_AW_ATV_STANDARD_MODE_DK,
        E_AW_ATV_STANDARD_MODE_DK1,
        E_AW_ATV_STANDARD_MODE_DK2,
        E_AW_ATV_STANDARD_MODE_DK3,
        E_AW_ATV_STANDARD_MODE_L,
        E_AW_ATV_STANDARD_MODE_M;

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:32:0x0063  */
        public static EnumATVStandardMode getEnum(String str) {
            switch (str) {
                case "audio_standard_auto":
                    return E_AW_ATV_STANDARD_MODE_AUTO;
                case "audio_standard_bg":
                    return E_AW_ATV_STANDARD_MODE_BG;
                case "audio_standard_i":
                    return E_AW_ATV_STANDARD_MODE_I;
                case "audio_standard_dk":
                    return E_AW_ATV_STANDARD_MODE_DK;
                case "audio_standard_dk1":
                    return E_AW_ATV_STANDARD_MODE_DK1;
                case "audio_standard_dk2":
                    return E_AW_ATV_STANDARD_MODE_DK2;
                case "audio_standard_dk3":
                    return E_AW_ATV_STANDARD_MODE_DK3;
                case "audio_standard_l":
                    return E_AW_ATV_STANDARD_MODE_L;
                case "audio_standard_m":
                    return E_AW_ATV_STANDARD_MODE_M;
                default:
                    return E_AW_ATV_STANDARD_MODE_AUTO;
            }
        }
    }

    public enum EnumSurroundMode {
        E_AW_SURROUND_NONE(0),
        E_AW_SURROUND_MELOD_AROUND(1),
        E_AW_SURROUND_VDS_MAOURND(4),
        E_AW_SURROUND_VDS_PLII_MAOURND(7),
        E_AW_SURROUND_VDD_MA(10);

        private final int value;

        EnumSurroundMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumSurroundMode getEnum(int i) {
            if (i == 1) {
                return E_AW_SURROUND_MELOD_AROUND;
            }
            if (i == 4) {
                return E_AW_SURROUND_VDS_MAOURND;
            }
            if (i == 7) {
                return E_AW_SURROUND_VDS_PLII_MAOURND;
            }
            if (i == 10) {
                return E_AW_SURROUND_VDD_MA;
            }
            return E_AW_SURROUND_NONE;
        }
    }

    public enum EnumAVCGainValue {
        E_AW_AVC_GAIN_0DB(3),
        E_AW_AVC_GAIN_6DB(0),
        E_AW_AVC_GAIN_12DB(1);

        private final int value;

        EnumAVCGainValue(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumAVCGainValue getEnum(int i) {
            switch (i) {
                case 0:
                    return E_AW_AVC_GAIN_6DB;
                case 1:
                    return E_AW_AVC_GAIN_12DB;
                default:
                    return E_AW_AVC_GAIN_0DB;
            }
        }
    }

    public enum EnumAVCThresholdValue {
        E_AW_AVC_THRESHOLD_24DB(0),
        E_AW_AVC_THRESHOLD_18DB(1),
        E_AW_AVC_THRESHOLD_12DB(2);

        private final int value;

        EnumAVCThresholdValue(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumAVCThresholdValue getEnum(int i) {
            switch (i) {
                case 0:
                    return E_AW_AVC_THRESHOLD_24DB;
                case 1:
                    return E_AW_AVC_THRESHOLD_18DB;
                default:
                    return E_AW_AVC_THRESHOLD_12DB;
            }
        }
    }

    public enum EnumAVCDecayTimeValue {
        E_AW_AVC_DECAY_8S(8),
        E_AW_AVC_DECAY_4S(4),
        E_AW_AVC_DECAY_2S(2),
        E_AW_AVC_DECAY_20MS(1);

        private final int value;

        EnumAVCDecayTimeValue(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnumAVCDecayTimeValue getEnum(int i) {
            if (i == 2) {
                return E_AW_AVC_DECAY_2S;
            }
            if (i == 4) {
                return E_AW_AVC_DECAY_4S;
            }
            if (i == 8) {
                return E_AW_AVC_DECAY_8S;
            }
            return E_AW_AVC_DECAY_20MS;
        }
    }

    public enum EnumATVAudioStd {
        E_AW_ATV_NO_STD(0),
        E_AW_ATV_AUTODETECT(1),
        E_AW_ATV_M_A2_FM(2),
        E_AW_ATV_BG_A2_FM(3),
        E_AW_ATV_DK1_A2_FM(4),
        E_AW_ATV_DK2_A2_FM(5),
        E_AW_ATV_DK3_A2_FM(7),
        E_AW_ATV_BG_NICAM_FM(8),
        E_AW_ATV_L_NICAM_AM(9),
        E_AW_ATV_I_NICAM_FM(10),
        E_AW_ATV_DK_NICAM_FM(11),
        E_AW_ATV_M_BTSC(32),
        E_AW_ATV_M_JAPAN(48),
        E_AW_ATV_FM_STEREO_RADIO(64);

        private final int value;

        EnumATVAudioStd(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }
}
