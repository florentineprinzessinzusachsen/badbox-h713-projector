package okhttp3.internal.platform;

import a0.b;
import android.content.Context;
import j2.i;
import java.util.List;
import k3.d;
import k3.e;
import v1.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class PlatformInitializer implements b {
    @Override // a0.b
    public final List a() {
        return p.f2517d;
    }

    @Override // a0.b
    public final Object b(Context context) {
        i.e(context, "context");
        e eVar = e.f1300a;
        Object obj = e.f1300a;
        d dVar = obj != null ? (d) obj : null;
        if (dVar != null) {
            dVar.a(context);
        }
        return e.f1300a;
    }
}
