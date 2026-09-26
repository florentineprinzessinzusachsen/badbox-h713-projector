package com.umeng.analytics.process;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: UMProcessDBManager.java */
/* JADX INFO: loaded from: classes.dex */
class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c f3772a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ConcurrentHashMap<String, a> f3773b = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3774c;

    private c() {
    }

    static c a(Context context) {
        if (f3772a == null) {
            synchronized (c.class) {
                if (f3772a == null) {
                    f3772a = new c();
                }
            }
        }
        c cVar = f3772a;
        cVar.f3774c = context;
        return cVar;
    }

    private a c(String str) {
        if (this.f3773b.get(str) != null) {
            return this.f3773b.get(str);
        }
        a aVarA = a.a(this.f3774c, str);
        this.f3773b.put(str, aVarA);
        return aVarA;
    }

    synchronized void b(String str) {
        c(str).b();
    }

    /* JADX INFO: compiled from: UMProcessDBManager.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private AtomicInteger f3775a = new AtomicInteger();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private SQLiteOpenHelper f3776b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private SQLiteDatabase f3777c;

        private a() {
        }

        static a a(Context context, String str) {
            Context appContext = UMGlobalContext.getAppContext(context);
            a aVar = new a();
            aVar.f3776b = b.a(appContext, str);
            return aVar;
        }

        synchronized void b() {
            try {
                if (this.f3775a.decrementAndGet() == 0) {
                    this.f3777c.close();
                }
            } catch (Throwable unused) {
            }
        }

        synchronized SQLiteDatabase a() {
            if (this.f3775a.incrementAndGet() == 1) {
                this.f3777c = this.f3776b.getWritableDatabase();
            }
            return this.f3777c;
        }
    }

    synchronized SQLiteDatabase a(String str) {
        return c(str).a();
    }
}
