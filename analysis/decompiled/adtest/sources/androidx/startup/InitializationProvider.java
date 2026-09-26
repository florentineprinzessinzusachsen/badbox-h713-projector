package androidx.startup;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class InitializationProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new IllegalStateException("Not allowed.");
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x004e */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onCreate() {
        /*
            r4 = this;
            android.content.Context r0 = r4.getContext()
            if (r0 == 0) goto L5d
            android.content.Context r1 = r0.getApplicationContext()
            if (r1 == 0) goto L5b
            a0.a r1 = a0.a.f4d
            if (r1 != 0) goto L25
            java.lang.Object r1 = a0.a.f5e
            monitor-enter(r1)
            a0.a r2 = a0.a.f4d     // Catch: java.lang.Throwable -> L1f
            if (r2 != 0) goto L21
            a0.a r2 = new a0.a     // Catch: java.lang.Throwable -> L1f
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L1f
            a0.a.f4d = r2     // Catch: java.lang.Throwable -> L1f
            goto L21
        L1f:
            r0 = move-exception
            goto L23
        L21:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L1f
            goto L25
        L23:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L1f
            throw r0
        L25:
            a0.a r0 = a0.a.f4d
            java.lang.Class r1 = r4.getClass()
            android.content.Context r2 = r0.f8c
            java.lang.String r3 = "Startup"
            java.lang.String r3 = a.a.I(r3)     // Catch: java.lang.Throwable -> L4e android.content.pm.PackageManager.NameNotFoundException -> L50
            android.os.Trace.beginSection(r3)     // Catch: java.lang.Throwable -> L4e android.content.pm.PackageManager.NameNotFoundException -> L50
            android.content.ComponentName r3 = new android.content.ComponentName     // Catch: java.lang.Throwable -> L4e android.content.pm.PackageManager.NameNotFoundException -> L50
            r3.<init>(r2, r1)     // Catch: java.lang.Throwable -> L4e android.content.pm.PackageManager.NameNotFoundException -> L50
            android.content.pm.PackageManager r1 = r2.getPackageManager()     // Catch: java.lang.Throwable -> L4e android.content.pm.PackageManager.NameNotFoundException -> L50
            r2 = 128(0x80, float:1.8E-43)
            android.content.pm.ProviderInfo r1 = r1.getProviderInfo(r3, r2)     // Catch: java.lang.Throwable -> L4e android.content.pm.PackageManager.NameNotFoundException -> L50
            android.os.Bundle r1 = r1.metaData     // Catch: java.lang.Throwable -> L4e android.content.pm.PackageManager.NameNotFoundException -> L50
            r0.a(r1)     // Catch: java.lang.Throwable -> L4e android.content.pm.PackageManager.NameNotFoundException -> L50
            android.os.Trace.endSection()
            goto L5b
        L4e:
            r0 = move-exception
            goto L57
        L50:
            r0 = move-exception
            a0.c r1 = new a0.c     // Catch: java.lang.Throwable -> L4e
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L4e
            throw r1     // Catch: java.lang.Throwable -> L4e
        L57:
            android.os.Trace.endSection()
            throw r0
        L5b:
            r0 = 1
            return r0
        L5d:
            a0.c r0 = new a0.c
            java.lang.String r1 = "Context cannot be null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.startup.InitializationProvider.onCreate():boolean");
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new IllegalStateException("Not allowed.");
    }
}
