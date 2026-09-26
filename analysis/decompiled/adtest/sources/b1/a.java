package b1;

import android.content.Context;
import j2.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f336a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile Context f337b;

    public final synchronized Context a() {
        Context context;
        context = f337b;
        if (context == null) {
            throw new IllegalStateException("appContext 未初始化");
        }
        return context;
    }

    public final synchronized void b(Context context) {
        i.e(context, "context");
        if (f337b == null) {
            f337b = context.getApplicationContext();
        }
    }
}
