package androidx.work.impl.diagnostics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.workers.DiagnosticsWorker;
import d0.a0;
import d0.c0;
import d0.d0;
import d0.n;
import e0.q;
import e0.y;
import j2.i;
import java.util.List;
import l3.h;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class DiagnosticsReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f329a = a0.g("DiagnosticsRcvr");

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        a0 a0VarE = a0.e();
        String str = f329a;
        a0VarE.a(str, "Requesting diagnostics");
        try {
            i.e(context, "context");
            y yVarS = y.S(context);
            List listS = h.S((d0) new c0(DiagnosticsWorker.class, 0).a());
            if (listS.isEmpty()) {
                throw new IllegalArgumentException("enqueue needs at least one WorkRequest.");
            }
            new q(yVarS, null, n.f484e, listS, 0).J();
        } catch (IllegalStateException e4) {
            a0.e().d(str, "WorkManager is not initialized", e4);
        }
    }
}
