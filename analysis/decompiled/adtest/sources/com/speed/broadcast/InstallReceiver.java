package com.speed.broadcast;

import a.a;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import g1.b;
import j2.i;
import t2.e;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class InstallReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f389a = a.a(1, null, 6);

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        i.e(context, "context");
        i.e(intent, "intent");
        int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", -1);
        String stringExtra = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
        if (intExtra == -1) {
            Intent intent2 = Build.VERSION.SDK_INT < 33 ? (Intent) intent.getParcelableExtra("android.intent.extra.INTENT") : (Intent) intent.getParcelableExtra("android.intent.extra.INTENT", Intent.class);
            if (intent2 != null) {
                intent2.addFlags(268435456);
                context.startActivity(intent2);
                return;
            }
            return;
        }
        e eVar = f389a;
        if (intExtra == 0) {
            eVar.h(new b(intExtra, "安装成功"));
        } else {
            if (intExtra == 3) {
                eVar.h(new b(intExtra, "取消安装"));
                return;
            }
            if (stringExtra == null) {
                stringExtra = "安装失败";
            }
            eVar.h(new b(intExtra, stringExtra));
        }
    }
}
