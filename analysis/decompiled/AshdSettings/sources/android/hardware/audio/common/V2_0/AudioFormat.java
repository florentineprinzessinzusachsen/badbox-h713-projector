package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioFormat {
    public static final int AAC = 67108864;
    public static final int AAC_ADIF = 335544320;
    public static final int AAC_ADTS = 503316480;
    public static final int AAC_ADTS_ELD = 503316992;
    public static final int AAC_ADTS_ERLC = 503316544;
    public static final int AAC_ADTS_HE_V1 = 503316496;
    public static final int AAC_ADTS_HE_V2 = 503316736;
    public static final int AAC_ADTS_LC = 503316482;
    public static final int AAC_ADTS_LD = 503316608;
    public static final int AAC_ADTS_LTP = 503316488;
    public static final int AAC_ADTS_MAIN = 503316481;
    public static final int AAC_ADTS_SCALABLE = 503316512;
    public static final int AAC_ADTS_SSR = 503316484;
    public static final int AAC_ELD = 67109376;
    public static final int AAC_ERLC = 67108928;
    public static final int AAC_HE_V1 = 67108880;
    public static final int AAC_HE_V2 = 67109120;
    public static final int AAC_LC = 67108866;
    public static final int AAC_LD = 67108992;
    public static final int AAC_LTP = 67108872;
    public static final int AAC_MAIN = 67108865;
    public static final int AAC_SCALABLE = 67108896;
    public static final int AAC_SSR = 67108868;
    public static final int AAC_SUB_ELD = 512;
    public static final int AAC_SUB_ERLC = 64;
    public static final int AAC_SUB_HE_V1 = 16;
    public static final int AAC_SUB_HE_V2 = 256;
    public static final int AAC_SUB_LC = 2;
    public static final int AAC_SUB_LD = 128;
    public static final int AAC_SUB_LTP = 8;
    public static final int AAC_SUB_MAIN = 1;
    public static final int AAC_SUB_SCALABLE = 32;
    public static final int AAC_SUB_SSR = 4;
    public static final int AC3 = 150994944;
    public static final int AC4 = 570425344;
    public static final int ALAC = 469762048;
    public static final int AMR_NB = 33554432;
    public static final int AMR_SUB_NONE = 0;
    public static final int AMR_WB = 50331648;
    public static final int AMR_WB_PLUS = 385875968;
    public static final int APE = 486539264;
    public static final int APTX = 536870912;
    public static final int APTX_HD = 553648128;
    public static final int DEFAULT = 0;
    public static final int DOLBY_TRUEHD = 234881024;
    public static final int DSD = 436207616;
    public static final int DTS = 184549376;
    public static final int DTS_HD = 201326592;
    public static final int EVRC = 268435456;
    public static final int EVRCB = 285212672;
    public static final int EVRCNW = 318767104;
    public static final int EVRCWB = 301989888;
    public static final int E_AC3 = 167772160;
    public static final int FLAC = 452984832;
    public static final int HE_AAC_V1 = 83886080;
    public static final int HE_AAC_V2 = 100663296;
    public static final int IEC61937 = 218103808;
    public static final int INVALID = -1;
    public static final int LDAC = 587202560;
    public static final int MAIN_MASK = -16777216;
    public static final int MP2 = 402653184;
    public static final int MP3 = 16777216;
    public static final int MP3_SUB_NONE = 0;
    public static final int OPUS = 134217728;
    public static final int PCM = 0;
    public static final int PCM_16_BIT = 1;
    public static final int PCM_24_BIT_PACKED = 6;
    public static final int PCM_32_BIT = 3;
    public static final int PCM_8_24_BIT = 4;
    public static final int PCM_8_BIT = 2;
    public static final int PCM_FLOAT = 5;
    public static final int PCM_SUB_16_BIT = 1;
    public static final int PCM_SUB_24_BIT_PACKED = 6;
    public static final int PCM_SUB_32_BIT = 3;
    public static final int PCM_SUB_8_24_BIT = 4;
    public static final int PCM_SUB_8_BIT = 2;
    public static final int PCM_SUB_FLOAT = 5;
    public static final int QCELP = 419430400;
    public static final int SBC = 520093696;
    public static final int SUB_MASK = 16777215;
    public static final int VORBIS = 117440512;
    public static final int VORBIS_SUB_NONE = 0;
    public static final int WMA = 352321536;
    public static final int WMA_PRO = 369098752;

    public static final String toString(int i) {
        if (i == -1) {
            return "INVALID";
        }
        if (i == 0) {
            return "DEFAULT";
        }
        if (i == 0) {
            return "PCM";
        }
        if (i == 16777216) {
            return "MP3";
        }
        if (i == 33554432) {
            return "AMR_NB";
        }
        if (i == 50331648) {
            return "AMR_WB";
        }
        if (i == 67108864) {
            return "AAC";
        }
        if (i == 83886080) {
            return "HE_AAC_V1";
        }
        if (i == 100663296) {
            return "HE_AAC_V2";
        }
        if (i == 117440512) {
            return "VORBIS";
        }
        if (i == 134217728) {
            return "OPUS";
        }
        if (i == 150994944) {
            return "AC3";
        }
        if (i == 167772160) {
            return "E_AC3";
        }
        if (i == 184549376) {
            return "DTS";
        }
        if (i == 201326592) {
            return "DTS_HD";
        }
        if (i == 218103808) {
            return "IEC61937";
        }
        if (i == 234881024) {
            return "DOLBY_TRUEHD";
        }
        if (i == 268435456) {
            return "EVRC";
        }
        if (i == 285212672) {
            return "EVRCB";
        }
        if (i == 301989888) {
            return "EVRCWB";
        }
        if (i == 318767104) {
            return "EVRCNW";
        }
        if (i == 335544320) {
            return "AAC_ADIF";
        }
        if (i == 352321536) {
            return "WMA";
        }
        if (i == 369098752) {
            return "WMA_PRO";
        }
        if (i == 385875968) {
            return "AMR_WB_PLUS";
        }
        if (i == 402653184) {
            return "MP2";
        }
        if (i == 419430400) {
            return "QCELP";
        }
        if (i == 436207616) {
            return "DSD";
        }
        if (i == 452984832) {
            return "FLAC";
        }
        if (i == 469762048) {
            return "ALAC";
        }
        if (i == 486539264) {
            return "APE";
        }
        if (i == 503316480) {
            return "AAC_ADTS";
        }
        if (i == 520093696) {
            return "SBC";
        }
        if (i == 536870912) {
            return "APTX";
        }
        if (i == 553648128) {
            return "APTX_HD";
        }
        if (i == 570425344) {
            return "AC4";
        }
        if (i == 587202560) {
            return "LDAC";
        }
        if (i == -16777216) {
            return "MAIN_MASK";
        }
        if (i == 16777215) {
            return "SUB_MASK";
        }
        if (i == 1) {
            return "PCM_SUB_16_BIT";
        }
        if (i == 2) {
            return "PCM_SUB_8_BIT";
        }
        if (i == 3) {
            return "PCM_SUB_32_BIT";
        }
        if (i == 4) {
            return "PCM_SUB_8_24_BIT";
        }
        if (i == 5) {
            return "PCM_SUB_FLOAT";
        }
        if (i == 6) {
            return "PCM_SUB_24_BIT_PACKED";
        }
        if (i == 0) {
            return "MP3_SUB_NONE";
        }
        if (i == 0) {
            return "AMR_SUB_NONE";
        }
        if (i == 1) {
            return "AAC_SUB_MAIN";
        }
        if (i == 2) {
            return "AAC_SUB_LC";
        }
        if (i == 4) {
            return "AAC_SUB_SSR";
        }
        if (i == 8) {
            return "AAC_SUB_LTP";
        }
        if (i == 16) {
            return "AAC_SUB_HE_V1";
        }
        if (i == 32) {
            return "AAC_SUB_SCALABLE";
        }
        if (i == 64) {
            return "AAC_SUB_ERLC";
        }
        if (i == 128) {
            return "AAC_SUB_LD";
        }
        if (i == 256) {
            return "AAC_SUB_HE_V2";
        }
        if (i == 512) {
            return "AAC_SUB_ELD";
        }
        if (i == 0) {
            return "VORBIS_SUB_NONE";
        }
        if (i == 1) {
            return "PCM_16_BIT";
        }
        if (i == 2) {
            return "PCM_8_BIT";
        }
        if (i == 3) {
            return "PCM_32_BIT";
        }
        if (i == 4) {
            return "PCM_8_24_BIT";
        }
        if (i == 5) {
            return "PCM_FLOAT";
        }
        if (i == 6) {
            return "PCM_24_BIT_PACKED";
        }
        if (i == 67108865) {
            return "AAC_MAIN";
        }
        if (i == 67108866) {
            return "AAC_LC";
        }
        if (i == 67108868) {
            return "AAC_SSR";
        }
        if (i == 67108872) {
            return "AAC_LTP";
        }
        if (i == 67108880) {
            return "AAC_HE_V1";
        }
        if (i == 67108896) {
            return "AAC_SCALABLE";
        }
        if (i == 67108928) {
            return "AAC_ERLC";
        }
        if (i == 67108992) {
            return "AAC_LD";
        }
        if (i == 67109120) {
            return "AAC_HE_V2";
        }
        if (i == 67109376) {
            return "AAC_ELD";
        }
        if (i == 503316481) {
            return "AAC_ADTS_MAIN";
        }
        if (i == 503316482) {
            return "AAC_ADTS_LC";
        }
        if (i == 503316484) {
            return "AAC_ADTS_SSR";
        }
        if (i == 503316488) {
            return "AAC_ADTS_LTP";
        }
        if (i == 503316496) {
            return "AAC_ADTS_HE_V1";
        }
        if (i == 503316512) {
            return "AAC_ADTS_SCALABLE";
        }
        if (i == 503316544) {
            return "AAC_ADTS_ERLC";
        }
        if (i == 503316608) {
            return "AAC_ADTS_LD";
        }
        if (i == 503316736) {
            return "AAC_ADTS_HE_V2";
        }
        if (i == 503316992) {
            return "AAC_ADTS_ELD";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = -1;
        if ((i & (-1)) == -1) {
            arrayList.add("INVALID");
        } else {
            i2 = 0;
        }
        arrayList.add("DEFAULT");
        arrayList.add("PCM");
        if ((i & 16777216) == 16777216) {
            arrayList.add("MP3");
            i2 |= 16777216;
        }
        if ((i & 33554432) == 33554432) {
            arrayList.add("AMR_NB");
            i2 |= 33554432;
        }
        if ((i & AMR_WB) == 50331648) {
            arrayList.add("AMR_WB");
            i2 |= AMR_WB;
        }
        if ((i & 67108864) == 67108864) {
            arrayList.add("AAC");
            i2 |= 67108864;
        }
        if ((i & HE_AAC_V1) == 83886080) {
            arrayList.add("HE_AAC_V1");
            i2 |= HE_AAC_V1;
        }
        if ((i & HE_AAC_V2) == 100663296) {
            arrayList.add("HE_AAC_V2");
            i2 |= HE_AAC_V2;
        }
        if ((i & VORBIS) == 117440512) {
            arrayList.add("VORBIS");
            i2 |= VORBIS;
        }
        if ((i & OPUS) == 134217728) {
            arrayList.add("OPUS");
            i2 |= OPUS;
        }
        if ((150994944 & i) == 150994944) {
            arrayList.add("AC3");
            i2 |= AC3;
        }
        if ((167772160 & i) == 167772160) {
            arrayList.add("E_AC3");
            i2 |= E_AC3;
        }
        if ((184549376 & i) == 184549376) {
            arrayList.add("DTS");
            i2 |= DTS;
        }
        if ((201326592 & i) == 201326592) {
            arrayList.add("DTS_HD");
            i2 |= DTS_HD;
        }
        if ((218103808 & i) == 218103808) {
            arrayList.add("IEC61937");
            i2 |= IEC61937;
        }
        if ((234881024 & i) == 234881024) {
            arrayList.add("DOLBY_TRUEHD");
            i2 |= DOLBY_TRUEHD;
        }
        if ((268435456 & i) == 268435456) {
            arrayList.add("EVRC");
            i2 |= EVRC;
        }
        if ((285212672 & i) == 285212672) {
            arrayList.add("EVRCB");
            i2 |= EVRCB;
        }
        if ((301989888 & i) == 301989888) {
            arrayList.add("EVRCWB");
            i2 |= EVRCWB;
        }
        if ((318767104 & i) == 318767104) {
            arrayList.add("EVRCNW");
            i2 |= EVRCNW;
        }
        if ((335544320 & i) == 335544320) {
            arrayList.add("AAC_ADIF");
            i2 |= AAC_ADIF;
        }
        if ((352321536 & i) == 352321536) {
            arrayList.add("WMA");
            i2 |= WMA;
        }
        if ((369098752 & i) == 369098752) {
            arrayList.add("WMA_PRO");
            i2 |= WMA_PRO;
        }
        if ((385875968 & i) == 385875968) {
            arrayList.add("AMR_WB_PLUS");
            i2 |= AMR_WB_PLUS;
        }
        if ((402653184 & i) == 402653184) {
            arrayList.add("MP2");
            i2 |= MP2;
        }
        if ((419430400 & i) == 419430400) {
            arrayList.add("QCELP");
            i2 |= QCELP;
        }
        if ((436207616 & i) == 436207616) {
            arrayList.add("DSD");
            i2 |= DSD;
        }
        if ((452984832 & i) == 452984832) {
            arrayList.add("FLAC");
            i2 |= FLAC;
        }
        if ((469762048 & i) == 469762048) {
            arrayList.add("ALAC");
            i2 |= ALAC;
        }
        if ((486539264 & i) == 486539264) {
            arrayList.add("APE");
            i2 |= APE;
        }
        if ((503316480 & i) == 503316480) {
            arrayList.add("AAC_ADTS");
            i2 |= AAC_ADTS;
        }
        if ((520093696 & i) == 520093696) {
            arrayList.add("SBC");
            i2 |= SBC;
        }
        if ((536870912 & i) == 536870912) {
            arrayList.add("APTX");
            i2 |= APTX;
        }
        if ((553648128 & i) == 553648128) {
            arrayList.add("APTX_HD");
            i2 |= APTX_HD;
        }
        if ((570425344 & i) == 570425344) {
            arrayList.add("AC4");
            i2 |= AC4;
        }
        if ((587202560 & i) == 587202560) {
            arrayList.add("LDAC");
            i2 |= LDAC;
        }
        if (((-16777216) & i) == -16777216) {
            arrayList.add("MAIN_MASK");
            i2 |= -16777216;
        }
        if ((16777215 & i) == 16777215) {
            arrayList.add("SUB_MASK");
            i2 |= 16777215;
        }
        int i3 = i & 1;
        if (i3 == 1) {
            arrayList.add("PCM_SUB_16_BIT");
            i2 |= 1;
        }
        int i4 = i & 2;
        if (i4 == 2) {
            arrayList.add("PCM_SUB_8_BIT");
            i2 |= 2;
        }
        int i5 = i & 3;
        if (i5 == 3) {
            arrayList.add("PCM_SUB_32_BIT");
            i2 |= 3;
        }
        int i6 = i & 4;
        if (i6 == 4) {
            arrayList.add("PCM_SUB_8_24_BIT");
            i2 |= 4;
        }
        int i7 = i & 5;
        if (i7 == 5) {
            arrayList.add("PCM_SUB_FLOAT");
            i2 |= 5;
        }
        int i8 = i & 6;
        if (i8 == 6) {
            arrayList.add("PCM_SUB_24_BIT_PACKED");
            i2 |= 6;
        }
        arrayList.add("MP3_SUB_NONE");
        arrayList.add("AMR_SUB_NONE");
        if (i3 == 1) {
            arrayList.add("AAC_SUB_MAIN");
            i2 |= 1;
        }
        if (i4 == 2) {
            arrayList.add("AAC_SUB_LC");
            i2 |= 2;
        }
        if (i6 == 4) {
            arrayList.add("AAC_SUB_SSR");
            i2 |= 4;
        }
        if ((i & 8) == 8) {
            arrayList.add("AAC_SUB_LTP");
            i2 |= 8;
        }
        if ((i & 16) == 16) {
            arrayList.add("AAC_SUB_HE_V1");
            i2 |= 16;
        }
        if ((i & 32) == 32) {
            arrayList.add("AAC_SUB_SCALABLE");
            i2 |= 32;
        }
        if ((i & 64) == 64) {
            arrayList.add("AAC_SUB_ERLC");
            i2 |= 64;
        }
        if ((i & 128) == 128) {
            arrayList.add("AAC_SUB_LD");
            i2 |= 128;
        }
        if ((i & 256) == 256) {
            arrayList.add("AAC_SUB_HE_V2");
            i2 |= 256;
        }
        if ((i & 512) == 512) {
            arrayList.add("AAC_SUB_ELD");
            i2 |= 512;
        }
        arrayList.add("VORBIS_SUB_NONE");
        if (i3 == 1) {
            arrayList.add("PCM_16_BIT");
            i2 |= 1;
        }
        if (i4 == 2) {
            arrayList.add("PCM_8_BIT");
            i2 |= 2;
        }
        if (i5 == 3) {
            arrayList.add("PCM_32_BIT");
            i2 |= 3;
        }
        if (i6 == 4) {
            arrayList.add("PCM_8_24_BIT");
            i2 |= 4;
        }
        if (i7 == 5) {
            arrayList.add("PCM_FLOAT");
            i2 |= 5;
        }
        if (i8 == 6) {
            arrayList.add("PCM_24_BIT_PACKED");
            i2 |= 6;
        }
        if ((67108865 & i) == 67108865) {
            arrayList.add("AAC_MAIN");
            i2 |= AAC_MAIN;
        }
        if ((67108866 & i) == 67108866) {
            arrayList.add("AAC_LC");
            i2 |= AAC_LC;
        }
        if ((67108868 & i) == 67108868) {
            arrayList.add("AAC_SSR");
            i2 |= AAC_SSR;
        }
        if ((67108872 & i) == 67108872) {
            arrayList.add("AAC_LTP");
            i2 |= AAC_LTP;
        }
        if ((67108880 & i) == 67108880) {
            arrayList.add("AAC_HE_V1");
            i2 |= AAC_HE_V1;
        }
        if ((67108896 & i) == 67108896) {
            arrayList.add("AAC_SCALABLE");
            i2 |= AAC_SCALABLE;
        }
        if ((67108928 & i) == 67108928) {
            arrayList.add("AAC_ERLC");
            i2 |= AAC_ERLC;
        }
        if ((67108992 & i) == 67108992) {
            arrayList.add("AAC_LD");
            i2 |= AAC_LD;
        }
        if ((67109120 & i) == 67109120) {
            arrayList.add("AAC_HE_V2");
            i2 |= AAC_HE_V2;
        }
        if ((67109376 & i) == 67109376) {
            arrayList.add("AAC_ELD");
            i2 |= AAC_ELD;
        }
        if ((503316481 & i) == 503316481) {
            arrayList.add("AAC_ADTS_MAIN");
            i2 |= AAC_ADTS_MAIN;
        }
        if ((503316482 & i) == 503316482) {
            arrayList.add("AAC_ADTS_LC");
            i2 |= AAC_ADTS_LC;
        }
        if ((503316484 & i) == 503316484) {
            arrayList.add("AAC_ADTS_SSR");
            i2 |= AAC_ADTS_SSR;
        }
        if ((503316488 & i) == 503316488) {
            arrayList.add("AAC_ADTS_LTP");
            i2 |= AAC_ADTS_LTP;
        }
        if ((503316496 & i) == 503316496) {
            arrayList.add("AAC_ADTS_HE_V1");
            i2 |= AAC_ADTS_HE_V1;
        }
        if ((503316512 & i) == 503316512) {
            arrayList.add("AAC_ADTS_SCALABLE");
            i2 |= AAC_ADTS_SCALABLE;
        }
        if ((503316544 & i) == 503316544) {
            arrayList.add("AAC_ADTS_ERLC");
            i2 |= AAC_ADTS_ERLC;
        }
        if ((503316608 & i) == 503316608) {
            arrayList.add("AAC_ADTS_LD");
            i2 |= AAC_ADTS_LD;
        }
        if ((503316736 & i) == 503316736) {
            arrayList.add("AAC_ADTS_HE_V2");
            i2 |= AAC_ADTS_HE_V2;
        }
        if ((503316992 & i) == 503316992) {
            arrayList.add("AAC_ADTS_ELD");
            i2 |= AAC_ADTS_ELD;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
