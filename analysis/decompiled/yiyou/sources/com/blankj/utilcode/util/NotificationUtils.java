package com.blankj.utilcode.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.h;
import androidx.core.app.k;

/* JADX INFO: loaded from: classes.dex */
public class NotificationUtils {
    private NotificationUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void cancel(String str, int i) {
        k.a(Utils.getApp()).a(str, i);
    }

    public static void cancelAll() {
        k.a(Utils.getApp()).b();
    }

    public static void create(Context context, int i, Intent intent, int i2, String str, String str2) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        PendingIntent activity = PendingIntent.getActivity(Utils.getApp(), 0, intent, 134217728);
        h.b bVar = new h.b(context);
        bVar.a(activity);
        bVar.b(str);
        bVar.a((CharSequence) str2);
        bVar.a(i2);
        bVar.a(true);
        notificationManager.notify(i, bVar.a());
    }

    public static void createStackNotification(Context context, int i, String str, Intent intent, int i2, String str2, String str3) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        PendingIntent activity = intent != null ? PendingIntent.getActivity(Utils.getApp(), 0, intent, 134217728) : null;
        h.b bVar = new h.b(context);
        bVar.a(activity);
        bVar.b(str2);
        bVar.a((CharSequence) str3);
        bVar.a(i2);
        bVar.a(str);
        bVar.a(true);
        notificationManager.notify(i, bVar.a());
    }

    public static void cancel(int i) {
        k.a(Utils.getApp()).a(i);
    }

    public static void create(int i, String str, String str2) {
        NotificationManager notificationManager = (NotificationManager) Utils.getApp().getSystemService("notification");
        h.b bVar = new h.b(Utils.getApp());
        bVar.b(str);
        bVar.a((CharSequence) str2);
        bVar.a(i);
        bVar.a(true);
        notificationManager.notify(0, bVar.a());
    }
}
