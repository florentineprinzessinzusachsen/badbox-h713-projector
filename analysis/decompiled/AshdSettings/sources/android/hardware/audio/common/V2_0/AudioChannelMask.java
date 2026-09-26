package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioChannelMask {
    public static final int COUNT_MAX = 30;
    public static final int INDEX_HDR = Integer.MIN_VALUE;
    public static final int INDEX_MASK_1 = -2147483647;
    public static final int INDEX_MASK_2 = -2147483645;
    public static final int INDEX_MASK_3 = -2147483641;
    public static final int INDEX_MASK_4 = -2147483633;
    public static final int INDEX_MASK_5 = -2147483617;
    public static final int INDEX_MASK_6 = -2147483585;
    public static final int INDEX_MASK_7 = -2147483521;
    public static final int INDEX_MASK_8 = -2147483393;
    public static final int INVALID = -1073741824;
    public static final int IN_6 = 252;
    public static final int IN_ALL = 65532;
    public static final int IN_BACK = 32;
    public static final int IN_BACK_PROCESSED = 512;
    public static final int IN_FRONT = 16;
    public static final int IN_FRONT_BACK = 48;
    public static final int IN_FRONT_PROCESSED = 256;
    public static final int IN_LEFT = 4;
    public static final int IN_LEFT_PROCESSED = 64;
    public static final int IN_MONO = 16;
    public static final int IN_PRESSURE = 1024;
    public static final int IN_RIGHT = 8;
    public static final int IN_RIGHT_PROCESSED = 128;
    public static final int IN_STEREO = 12;
    public static final int IN_VOICE_CALL_MONO = 49168;
    public static final int IN_VOICE_DNLINK = 32768;
    public static final int IN_VOICE_DNLINK_MONO = 32784;
    public static final int IN_VOICE_UPLINK = 16384;
    public static final int IN_VOICE_UPLINK_MONO = 16400;
    public static final int IN_X_AXIS = 2048;
    public static final int IN_Y_AXIS = 4096;
    public static final int IN_Z_AXIS = 8192;
    public static final int NONE = 0;
    public static final int OUT_2POINT1 = 11;
    public static final int OUT_5POINT1 = 63;
    public static final int OUT_5POINT1_BACK = 63;
    public static final int OUT_5POINT1_SIDE = 1551;
    public static final int OUT_6POINT1 = 319;
    public static final int OUT_7POINT1 = 1599;
    public static final int OUT_ALL = 262143;
    public static final int OUT_BACK_CENTER = 256;
    public static final int OUT_BACK_LEFT = 16;
    public static final int OUT_BACK_RIGHT = 32;
    public static final int OUT_FRONT_CENTER = 4;
    public static final int OUT_FRONT_LEFT = 1;
    public static final int OUT_FRONT_LEFT_OF_CENTER = 64;
    public static final int OUT_FRONT_RIGHT = 2;
    public static final int OUT_FRONT_RIGHT_OF_CENTER = 128;
    public static final int OUT_LOW_FREQUENCY = 8;
    public static final int OUT_MONO = 1;
    public static final int OUT_PENTA = 55;
    public static final int OUT_QUAD = 51;
    public static final int OUT_QUAD_BACK = 51;
    public static final int OUT_QUAD_SIDE = 1539;
    public static final int OUT_SIDE_LEFT = 512;
    public static final int OUT_SIDE_RIGHT = 1024;
    public static final int OUT_STEREO = 3;
    public static final int OUT_SURROUND = 263;
    public static final int OUT_TOP_BACK_CENTER = 65536;
    public static final int OUT_TOP_BACK_LEFT = 32768;
    public static final int OUT_TOP_BACK_RIGHT = 131072;
    public static final int OUT_TOP_CENTER = 2048;
    public static final int OUT_TOP_FRONT_CENTER = 8192;
    public static final int OUT_TOP_FRONT_LEFT = 4096;
    public static final int OUT_TOP_FRONT_RIGHT = 16384;
    public static final int REPRESENTATION_INDEX = 2;
    public static final int REPRESENTATION_POSITION = 0;

    public static final String toString(int i) {
        if (i == 0) {
            return "REPRESENTATION_POSITION";
        }
        if (i == 2) {
            return "REPRESENTATION_INDEX";
        }
        if (i == 0) {
            return "NONE";
        }
        if (i == -1073741824) {
            return "INVALID";
        }
        if (i == 1) {
            return "OUT_FRONT_LEFT";
        }
        if (i == 2) {
            return "OUT_FRONT_RIGHT";
        }
        if (i == 4) {
            return "OUT_FRONT_CENTER";
        }
        if (i == 8) {
            return "OUT_LOW_FREQUENCY";
        }
        if (i == 16) {
            return "OUT_BACK_LEFT";
        }
        if (i == 32) {
            return "OUT_BACK_RIGHT";
        }
        if (i == 64) {
            return "OUT_FRONT_LEFT_OF_CENTER";
        }
        if (i == 128) {
            return "OUT_FRONT_RIGHT_OF_CENTER";
        }
        if (i == 256) {
            return "OUT_BACK_CENTER";
        }
        if (i == 512) {
            return "OUT_SIDE_LEFT";
        }
        if (i == 1024) {
            return "OUT_SIDE_RIGHT";
        }
        if (i == 2048) {
            return "OUT_TOP_CENTER";
        }
        if (i == 4096) {
            return "OUT_TOP_FRONT_LEFT";
        }
        if (i == 8192) {
            return "OUT_TOP_FRONT_CENTER";
        }
        if (i == 16384) {
            return "OUT_TOP_FRONT_RIGHT";
        }
        if (i == 32768) {
            return "OUT_TOP_BACK_LEFT";
        }
        if (i == 65536) {
            return "OUT_TOP_BACK_CENTER";
        }
        if (i == 131072) {
            return "OUT_TOP_BACK_RIGHT";
        }
        if (i == 1) {
            return "OUT_MONO";
        }
        if (i == 3) {
            return "OUT_STEREO";
        }
        if (i == 11) {
            return "OUT_2POINT1";
        }
        if (i == 51) {
            return "OUT_QUAD";
        }
        if (i == 51) {
            return "OUT_QUAD_BACK";
        }
        if (i == 1539) {
            return "OUT_QUAD_SIDE";
        }
        if (i == 263) {
            return "OUT_SURROUND";
        }
        if (i == 55) {
            return "OUT_PENTA";
        }
        if (i == 63) {
            return "OUT_5POINT1";
        }
        if (i == 63) {
            return "OUT_5POINT1_BACK";
        }
        if (i == 1551) {
            return "OUT_5POINT1_SIDE";
        }
        if (i == 319) {
            return "OUT_6POINT1";
        }
        if (i == 1599) {
            return "OUT_7POINT1";
        }
        if (i == 262143) {
            return "OUT_ALL";
        }
        if (i == 4) {
            return "IN_LEFT";
        }
        if (i == 8) {
            return "IN_RIGHT";
        }
        if (i == 16) {
            return "IN_FRONT";
        }
        if (i == 32) {
            return "IN_BACK";
        }
        if (i == 64) {
            return "IN_LEFT_PROCESSED";
        }
        if (i == 128) {
            return "IN_RIGHT_PROCESSED";
        }
        if (i == 256) {
            return "IN_FRONT_PROCESSED";
        }
        if (i == 512) {
            return "IN_BACK_PROCESSED";
        }
        if (i == 1024) {
            return "IN_PRESSURE";
        }
        if (i == 2048) {
            return "IN_X_AXIS";
        }
        if (i == 4096) {
            return "IN_Y_AXIS";
        }
        if (i == 8192) {
            return "IN_Z_AXIS";
        }
        if (i == 16384) {
            return "IN_VOICE_UPLINK";
        }
        if (i == 32768) {
            return "IN_VOICE_DNLINK";
        }
        if (i == 16) {
            return "IN_MONO";
        }
        if (i == 12) {
            return "IN_STEREO";
        }
        if (i == 48) {
            return "IN_FRONT_BACK";
        }
        if (i == 252) {
            return "IN_6";
        }
        if (i == 16400) {
            return "IN_VOICE_UPLINK_MONO";
        }
        if (i == 32784) {
            return "IN_VOICE_DNLINK_MONO";
        }
        if (i == 49168) {
            return "IN_VOICE_CALL_MONO";
        }
        if (i == 65532) {
            return "IN_ALL";
        }
        if (i == 30) {
            return "COUNT_MAX";
        }
        if (i == Integer.MIN_VALUE) {
            return "INDEX_HDR";
        }
        if (i == -2147483647) {
            return "INDEX_MASK_1";
        }
        if (i == -2147483645) {
            return "INDEX_MASK_2";
        }
        if (i == -2147483641) {
            return "INDEX_MASK_3";
        }
        if (i == -2147483633) {
            return "INDEX_MASK_4";
        }
        if (i == -2147483617) {
            return "INDEX_MASK_5";
        }
        if (i == -2147483585) {
            return "INDEX_MASK_6";
        }
        if (i == -2147483521) {
            return "INDEX_MASK_7";
        }
        if (i == -2147483393) {
            return "INDEX_MASK_8";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        int i2;
        ArrayList arrayList = new ArrayList();
        arrayList.add("REPRESENTATION_POSITION");
        int i3 = i & 2;
        if (i3 == 2) {
            arrayList.add("REPRESENTATION_INDEX");
            i2 = 2;
        } else {
            i2 = 0;
        }
        arrayList.add("NONE");
        if (((-1073741824) & i) == -1073741824) {
            arrayList.add("INVALID");
            i2 |= -1073741824;
        }
        int i4 = i & 1;
        if (i4 == 1) {
            arrayList.add("OUT_FRONT_LEFT");
            i2 |= 1;
        }
        if (i3 == 2) {
            arrayList.add("OUT_FRONT_RIGHT");
            i2 |= 2;
        }
        int i5 = i & 4;
        if (i5 == 4) {
            arrayList.add("OUT_FRONT_CENTER");
            i2 |= 4;
        }
        int i6 = i & 8;
        if (i6 == 8) {
            arrayList.add("OUT_LOW_FREQUENCY");
            i2 |= 8;
        }
        int i7 = i & 16;
        if (i7 == 16) {
            arrayList.add("OUT_BACK_LEFT");
            i2 |= 16;
        }
        int i8 = i & 32;
        if (i8 == 32) {
            arrayList.add("OUT_BACK_RIGHT");
            i2 |= 32;
        }
        int i9 = i & 64;
        if (i9 == 64) {
            arrayList.add("OUT_FRONT_LEFT_OF_CENTER");
            i2 |= 64;
        }
        int i10 = i & 128;
        if (i10 == 128) {
            arrayList.add("OUT_FRONT_RIGHT_OF_CENTER");
            i2 |= 128;
        }
        int i11 = i & 256;
        if (i11 == 256) {
            arrayList.add("OUT_BACK_CENTER");
            i2 |= 256;
        }
        int i12 = i & 512;
        if (i12 == 512) {
            arrayList.add("OUT_SIDE_LEFT");
            i2 |= 512;
        }
        int i13 = i & 1024;
        if (i13 == 1024) {
            arrayList.add("OUT_SIDE_RIGHT");
            i2 |= 1024;
        }
        int i14 = i & 2048;
        if (i14 == 2048) {
            arrayList.add("OUT_TOP_CENTER");
            i2 |= 2048;
        }
        int i15 = i & 4096;
        if (i15 == 4096) {
            arrayList.add("OUT_TOP_FRONT_LEFT");
            i2 |= 4096;
        }
        int i16 = i & 8192;
        if (i16 == 8192) {
            arrayList.add("OUT_TOP_FRONT_CENTER");
            i2 |= 8192;
        }
        int i17 = i & 16384;
        if (i17 == 16384) {
            arrayList.add("OUT_TOP_FRONT_RIGHT");
            i2 |= 16384;
        }
        int i18 = 32768 & i;
        if (i18 == 32768) {
            arrayList.add("OUT_TOP_BACK_LEFT");
            i2 |= 32768;
        }
        if ((65536 & i) == 65536) {
            arrayList.add("OUT_TOP_BACK_CENTER");
            i2 |= 65536;
        }
        if ((131072 & i) == 131072) {
            arrayList.add("OUT_TOP_BACK_RIGHT");
            i2 |= 131072;
        }
        if (i4 == 1) {
            arrayList.add("OUT_MONO");
            i2 |= 1;
        }
        if ((i & 3) == 3) {
            arrayList.add("OUT_STEREO");
            i2 |= 3;
        }
        if ((i & 11) == 11) {
            arrayList.add("OUT_2POINT1");
            i2 |= 11;
        }
        int i19 = i & 51;
        if (i19 == 51) {
            arrayList.add("OUT_QUAD");
            i2 |= 51;
        }
        if (i19 == 51) {
            arrayList.add("OUT_QUAD_BACK");
            i2 |= 51;
        }
        if ((i & OUT_QUAD_SIDE) == 1539) {
            arrayList.add("OUT_QUAD_SIDE");
            i2 |= OUT_QUAD_SIDE;
        }
        if ((i & OUT_SURROUND) == 263) {
            arrayList.add("OUT_SURROUND");
            i2 |= OUT_SURROUND;
        }
        if ((i & 55) == 55) {
            arrayList.add("OUT_PENTA");
            i2 |= 55;
        }
        int i20 = i & 63;
        if (i20 == 63) {
            arrayList.add("OUT_5POINT1");
            i2 |= 63;
        }
        if (i20 == 63) {
            arrayList.add("OUT_5POINT1_BACK");
            i2 |= 63;
        }
        if ((i & OUT_5POINT1_SIDE) == 1551) {
            arrayList.add("OUT_5POINT1_SIDE");
            i2 |= OUT_5POINT1_SIDE;
        }
        if ((i & OUT_6POINT1) == 319) {
            arrayList.add("OUT_6POINT1");
            i2 |= OUT_6POINT1;
        }
        if ((i & OUT_7POINT1) == 1599) {
            arrayList.add("OUT_7POINT1");
            i2 |= OUT_7POINT1;
        }
        if ((262143 & i) == 262143) {
            arrayList.add("OUT_ALL");
            i2 |= OUT_ALL;
        }
        if (i5 == 4) {
            arrayList.add("IN_LEFT");
            i2 |= 4;
        }
        if (i6 == 8) {
            arrayList.add("IN_RIGHT");
            i2 |= 8;
        }
        if (i7 == 16) {
            arrayList.add("IN_FRONT");
            i2 |= 16;
        }
        if (i8 == 32) {
            arrayList.add("IN_BACK");
            i2 |= 32;
        }
        if (i9 == 64) {
            arrayList.add("IN_LEFT_PROCESSED");
            i2 |= 64;
        }
        if (i10 == 128) {
            arrayList.add("IN_RIGHT_PROCESSED");
            i2 |= 128;
        }
        if (i11 == 256) {
            arrayList.add("IN_FRONT_PROCESSED");
            i2 |= 256;
        }
        if (i12 == 512) {
            arrayList.add("IN_BACK_PROCESSED");
            i2 |= 512;
        }
        if (i13 == 1024) {
            arrayList.add("IN_PRESSURE");
            i2 |= 1024;
        }
        if (i14 == 2048) {
            arrayList.add("IN_X_AXIS");
            i2 |= 2048;
        }
        if (i15 == 4096) {
            arrayList.add("IN_Y_AXIS");
            i2 |= 4096;
        }
        if (i16 == 8192) {
            arrayList.add("IN_Z_AXIS");
            i2 |= 8192;
        }
        if (i17 == 16384) {
            arrayList.add("IN_VOICE_UPLINK");
            i2 |= 16384;
        }
        if (i18 == 32768) {
            arrayList.add("IN_VOICE_DNLINK");
            i2 |= 32768;
        }
        if (i7 == 16) {
            arrayList.add("IN_MONO");
            i2 |= 16;
        }
        if ((i & 12) == 12) {
            arrayList.add("IN_STEREO");
            i2 |= 12;
        }
        if ((i & 48) == 48) {
            arrayList.add("IN_FRONT_BACK");
            i2 |= 48;
        }
        if ((i & IN_6) == 252) {
            arrayList.add("IN_6");
            i2 |= IN_6;
        }
        if ((i & IN_VOICE_UPLINK_MONO) == 16400) {
            arrayList.add("IN_VOICE_UPLINK_MONO");
            i2 |= IN_VOICE_UPLINK_MONO;
        }
        if ((32784 & i) == 32784) {
            arrayList.add("IN_VOICE_DNLINK_MONO");
            i2 |= IN_VOICE_DNLINK_MONO;
        }
        if ((49168 & i) == 49168) {
            arrayList.add("IN_VOICE_CALL_MONO");
            i2 |= IN_VOICE_CALL_MONO;
        }
        if ((65532 & i) == 65532) {
            arrayList.add("IN_ALL");
            i2 |= IN_ALL;
        }
        if ((i & 30) == 30) {
            arrayList.add("COUNT_MAX");
            i2 |= 30;
        }
        if ((Integer.MIN_VALUE & i) == Integer.MIN_VALUE) {
            arrayList.add("INDEX_HDR");
            i2 |= Integer.MIN_VALUE;
        }
        if (((-2147483647) & i) == -2147483647) {
            arrayList.add("INDEX_MASK_1");
            i2 |= -2147483647;
        }
        if (((-2147483645) & i) == -2147483645) {
            arrayList.add("INDEX_MASK_2");
            i2 |= INDEX_MASK_2;
        }
        if (((-2147483641) & i) == -2147483641) {
            arrayList.add("INDEX_MASK_3");
            i2 |= INDEX_MASK_3;
        }
        if (((-2147483633) & i) == -2147483633) {
            arrayList.add("INDEX_MASK_4");
            i2 |= INDEX_MASK_4;
        }
        if (((-2147483617) & i) == -2147483617) {
            arrayList.add("INDEX_MASK_5");
            i2 |= INDEX_MASK_5;
        }
        if (((-2147483585) & i) == -2147483585) {
            arrayList.add("INDEX_MASK_6");
            i2 |= INDEX_MASK_6;
        }
        if (((-2147483521) & i) == -2147483521) {
            arrayList.add("INDEX_MASK_7");
            i2 |= INDEX_MASK_7;
        }
        if (((-2147483393) & i) == -2147483393) {
            arrayList.add("INDEX_MASK_8");
            i2 |= INDEX_MASK_8;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
