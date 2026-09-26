package android.hardware.audio.common.V2_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AudioHandleConsts {
    public static final int AUDIO_IO_HANDLE_NONE = 0;
    public static final int AUDIO_MODULE_HANDLE_NONE = 0;
    public static final int AUDIO_PATCH_HANDLE_NONE = 0;
    public static final int AUDIO_PORT_HANDLE_NONE = 0;

    public static final String toString(int i) {
        if (i == 0) {
            return "AUDIO_IO_HANDLE_NONE";
        }
        if (i == 0) {
            return "AUDIO_MODULE_HANDLE_NONE";
        }
        if (i == 0) {
            return "AUDIO_PORT_HANDLE_NONE";
        }
        if (i == 0) {
            return "AUDIO_PATCH_HANDLE_NONE";
        }
        return "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("AUDIO_IO_HANDLE_NONE");
        arrayList.add("AUDIO_MODULE_HANDLE_NONE");
        arrayList.add("AUDIO_PORT_HANDLE_NONE");
        arrayList.add("AUDIO_PATCH_HANDLE_NONE");
        if (i != 0) {
            arrayList.add("0x" + Integer.toHexString(i & (-1)));
        }
        return String.join(" | ", arrayList);
    }
}
