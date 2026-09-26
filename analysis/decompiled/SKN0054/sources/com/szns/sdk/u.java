package com.szns.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class u {
    private static UUID a;

    public u(Context context) {
        a(context);
    }

    private synchronized void a(Context context) {
        if (a == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("device_id.xml", 0);
            String string = sharedPreferences.getString("device_id", null);
            if (string != null) {
                a = UUID.fromString(string);
                return;
            }
            a = UUID.randomUUID();
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            UUID uuid = a;
            editorEdit.putString("device_id", uuid == null ? "" : uuid.toString()).apply();
        }
    }

    public final synchronized UUID a() {
        return a;
    }
}
