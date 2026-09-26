package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioOutputFlag {
    public static final int COMPRESS_OFFLOAD = 16;
    public static final int DEEP_BUFFER = 8;
    public static final int DIRECT = 1;
    public static final int DIRECT_PCM = 8192;
    public static final int FAST = 4;
    public static final int HW_AV_SYNC = 64;
    public static final int IEC958_NONAUDIO = 1024;
    public static final int MMAP_NOIRQ = 16384;
    public static final int NONE = 0;
    public static final int NON_BLOCKING = 32;
    public static final int PRIMARY = 2;
    public static final int RAW = 256;
    public static final int SYNC = 512;
    public static final int TTS = 128;
    public static final int VOIP_RX = 32768;

    public static final String toString(int i) {
        if (i == 0) {
            return "NONE";
        }
        if (i == 1) {
            return "DIRECT";
        }
        if (i == 2) {
            return "PRIMARY";
        }
        if (i == 4) {
            return "FAST";
        }
        if (i == 8) {
            return "DEEP_BUFFER";
        }
        if (i == 16) {
            return "COMPRESS_OFFLOAD";
        }
        if (i == 32) {
            return "NON_BLOCKING";
        }
        if (i == 64) {
            return "HW_AV_SYNC";
        }
        if (i == 128) {
            return "TTS";
        }
        if (i == 256) {
            return "RAW";
        }
        if (i == 512) {
            return "SYNC";
        }
        if (i == 1024) {
            return "IEC958_NONAUDIO";
        }
        if (i == 8192) {
            return "DIRECT_PCM";
        }
        if (i == 16384) {
            return "MMAP_NOIRQ";
        }
        if (i == 32768) {
            return "VOIP_RX";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("NONE");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("DIRECT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("PRIMARY");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("FAST");
            i2 |= 4;
        }
        if ((i & 8) == 8) {
            arrayList.add("DEEP_BUFFER");
            i2 |= 8;
        }
        if ((i & 16) == 16) {
            arrayList.add("COMPRESS_OFFLOAD");
            i2 |= 16;
        }
        if ((i & 32) == 32) {
            arrayList.add("NON_BLOCKING");
            i2 |= 32;
        }
        if ((i & 64) == 64) {
            arrayList.add("HW_AV_SYNC");
            i2 |= 64;
        }
        if ((i & 128) == 128) {
            arrayList.add("TTS");
            i2 |= 128;
        }
        if ((i & 256) == 256) {
            arrayList.add("RAW");
            i2 |= 256;
        }
        if ((i & 512) == 512) {
            arrayList.add("SYNC");
            i2 |= 512;
        }
        if ((i & 1024) == 1024) {
            arrayList.add("IEC958_NONAUDIO");
            i2 |= 1024;
        }
        if ((i & 8192) == 8192) {
            arrayList.add("DIRECT_PCM");
            i2 |= 8192;
        }
        if ((i & 16384) == 16384) {
            arrayList.add("MMAP_NOIRQ");
            i2 |= 16384;
        }
        if ((i & 32768) == 32768) {
            arrayList.add("VOIP_RX");
            i2 |= 32768;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
