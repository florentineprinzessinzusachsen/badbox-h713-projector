package com.baidu.mobstat;

import android.content.ContentValues;
import android.database.Cursor;
import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
abstract class j implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private m f3507a;

    public j(String str, String str2) {
        l lVar = new l();
        this.f3507a = new m(lVar, str);
        File databasePath = lVar.getDatabasePath(".confd");
        if (databasePath == null || !databasePath.canWrite()) {
            return;
        }
        a(str2);
    }

    private void a(String str) {
        this.f3507a.a(str);
    }

    public abstract long a(String str, String str2);

    public abstract ArrayList<i> a(int i, int i2);

    protected int b() {
        return this.f3507a.b();
    }

    public abstract boolean b(long j);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        try {
            this.f3507a.close();
        } catch (Exception e2) {
            al.c().b(e2);
        }
    }

    public synchronized boolean a() {
        try {
        } catch (Exception e2) {
            al.c().b(e2);
            return false;
        }
        return this.f3507a.a();
    }

    protected Cursor a(String str, int i, int i2) {
        return this.f3507a.a(null, null, null, null, null, str + " desc", i2 + ", " + i);
    }

    protected Cursor a(String str, String str2, String str3, int i) {
        String str4 = str + "=? ";
        String[] strArr = {str2};
        return this.f3507a.a(null, str4, strArr, null, null, str3 + " desc", i + "");
    }

    protected long a(ContentValues contentValues) {
        return this.f3507a.a((String) null, contentValues);
    }

    protected boolean a(long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(j);
        sb.append("");
        return this.f3507a.a("_id=? ", new String[]{sb.toString()}) > 0;
    }
}
