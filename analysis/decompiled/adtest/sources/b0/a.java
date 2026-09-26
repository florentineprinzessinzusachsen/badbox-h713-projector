package b0;

import android.os.Trace;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static void a(int i4, String str) {
        Trace.beginAsyncSection(str, i4);
    }

    public static void b(int i4, String str) {
        Trace.endAsyncSection(str, i4);
    }

    public static boolean c() {
        return Trace.isEnabled();
    }
}
