package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: UMDBManager.java */
/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static SQLiteOpenHelper f3645b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Context f3646d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AtomicInteger f3647a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SQLiteDatabase f3648c;

    /* JADX INFO: compiled from: UMDBManager.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final e f3649a = new e();

        private a() {
        }
    }

    public static e a(Context context) {
        if (f3646d == null && context != null) {
            f3646d = context.getApplicationContext();
            f3645b = d.a(f3646d);
        }
        return a.f3649a;
    }

    public synchronized void b() {
        try {
            if (this.f3647a.decrementAndGet() == 0) {
                this.f3648c.close();
            }
        } catch (Throwable unused) {
        }
    }

    private e() {
        this.f3647a = new AtomicInteger();
    }

    public synchronized SQLiteDatabase a() {
        if (this.f3647a.incrementAndGet() == 1) {
            this.f3648c = f3645b.getWritableDatabase();
        }
        return this.f3648c;
    }
}
