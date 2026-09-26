package com.baidu.mobstat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: loaded from: classes.dex */
class m extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3514a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SQLiteDatabase f3515b;

    public m(Context context, String str) {
        super(context, ".confd", (SQLiteDatabase.CursorFactory) null, 1);
        this.f3514a = str;
    }

    public synchronized boolean a() {
        boolean z;
        z = false;
        if (this.f3515b == null || !this.f3515b.isOpen()) {
            try {
                this.f3515b = getWritableDatabase();
            } catch (NullPointerException unused) {
                throw new NullPointerException("db path is null");
            }
        }
        if (this.f3515b != null && this.f3515b.isOpen()) {
            z = true;
        }
        return z;
    }

    public final int b() {
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = this.f3515b.rawQuery("SELECT COUNT(*) FROM " + this.f3514a, null);
            if (cursorRawQuery == null || !cursorRawQuery.moveToNext()) {
                return 0;
            }
            return cursorRawQuery.getInt(0);
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public synchronized void close() {
        super.close();
        if (this.f3515b != null) {
            this.f3515b.close();
            this.f3515b = null;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public synchronized SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f3515b = sQLiteDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public void a(String str) {
        getWritableDatabase().execSQL(str);
    }

    public Cursor a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        return this.f3515b.query(this.f3514a, strArr, str, strArr2, str2, str3, str4, str5);
    }

    public long a(String str, ContentValues contentValues) {
        return this.f3515b.insert(this.f3514a, str, contentValues);
    }

    public int a(String str, String[] strArr) {
        return this.f3515b.delete(this.f3514a, str, strArr);
    }
}
