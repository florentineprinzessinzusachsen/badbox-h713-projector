package androidx.profileinstaller;

import a3.h;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import c.a;
import java.io.File;
import o.g;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class ProfileInstallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Bundle extras;
        File codeCacheDir;
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if ("androidx.profileinstaller.action.INSTALL_PROFILE".equals(action)) {
            g.t(context, new a(1), new h(6, this), true);
            return;
        }
        if ("androidx.profileinstaller.action.SKIP_FILE".equals(action)) {
            Bundle extras2 = intent.getExtras();
            if (extras2 != null) {
                String string = extras2.getString("EXTRA_SKIP_FILE_OPERATION");
                if (!"WRITE_SKIP_FILE".equals(string)) {
                    if ("DELETE_SKIP_FILE".equals(string)) {
                        new File(context.getFilesDir(), "profileinstaller_profileWrittenFor_lastUpdateTime.dat").delete();
                        Log.d("ProfileInstaller", "RESULT_DELETE_SKIP_FILE_SUCCESS");
                        setResultCode(11);
                        return;
                    }
                    return;
                }
                h hVar = new h(6, this);
                try {
                    g.e(context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0), context.getFilesDir());
                    hVar.f(10, null);
                    return;
                } catch (PackageManager.NameNotFoundException e4) {
                    hVar.f(7, e4);
                    return;
                }
            }
            return;
        }
        if ("androidx.profileinstaller.action.SAVE_PROFILE".equals(action)) {
            h hVar2 = new h(6, this);
            if (Build.VERSION.SDK_INT < 24) {
                hVar2.f(13, null);
                return;
            } else {
                Process.sendSignal(Process.myPid(), 10);
                hVar2.f(12, null);
                return;
            }
        }
        if (!"androidx.profileinstaller.action.BENCHMARK_OPERATION".equals(action) || (extras = intent.getExtras()) == null) {
            return;
        }
        String string2 = extras.getString("EXTRA_BENCHMARK_OPERATION");
        h hVar3 = new h(6, this);
        if (!"DROP_SHADER_CACHE".equals(string2)) {
            hVar3.f(16, null);
            return;
        }
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 34) {
            codeCacheDir = context.createDeviceProtectedStorageContext().getCacheDir();
        } else if (i4 >= 24) {
            codeCacheDir = context.createDeviceProtectedStorageContext().getCodeCacheDir();
        } else {
            codeCacheDir = i4 == 23 ? context.getCodeCacheDir() : context.getCacheDir();
        }
        if (g.c(codeCacheDir)) {
            hVar3.f(14, null);
        } else {
            hVar3.f(15, null);
        }
    }
}
