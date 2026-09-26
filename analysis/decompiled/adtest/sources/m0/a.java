package m0;

import android.app.Application;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static String a() {
        String processName = Application.getProcessName();
        j2.i.d(processName, "getProcessName(...)");
        return processName;
    }
}
