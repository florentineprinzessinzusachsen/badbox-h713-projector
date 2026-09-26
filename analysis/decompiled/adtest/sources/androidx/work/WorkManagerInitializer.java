package androidx.work;

import a0.b;
import android.content.Context;
import d0.a0;
import d0.l;
import d0.l0;
import e0.y;
import j2.i;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class WorkManagerInitializer implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f309a = a0.g("WrkMgrInitializer");

    @Override // a0.b
    public final List a() {
        return Collections.EMPTY_LIST;
    }

    @Override // a0.b
    public final Object b(Context context) {
        a0.e().a(f309a, "Initializing WorkManager with default configuration.");
        d0.b bVar = new d0.b(new l());
        i.e(context, "context");
        synchronized (y.f691m) {
            try {
                y yVar = y.f689k;
                if (yVar != null && y.f690l != null) {
                    throw new IllegalStateException("WorkManager is already initialized.  Did you try to initialize it manually without disabling WorkManagerInitializer? See WorkManager#initialize(Context, Configuration) or the class level Javadoc for more information.");
                }
                if (yVar == null) {
                    Context applicationContext = context.getApplicationContext();
                    if (y.f690l == null) {
                        y.f690l = l0.m(applicationContext, bVar);
                    }
                    y.f689k = y.f690l;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return y.S(context);
    }
}
