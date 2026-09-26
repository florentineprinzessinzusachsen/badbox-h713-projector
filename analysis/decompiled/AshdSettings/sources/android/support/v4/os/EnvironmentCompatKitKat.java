package android.support.v4.os;

import android.annotation.TargetApi;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(19)
@TargetApi(19)
class EnvironmentCompatKitKat {
    EnvironmentCompatKitKat() {
    }

    public static String getStorageState(File file) {
        return Environment.getStorageState(file);
    }
}
