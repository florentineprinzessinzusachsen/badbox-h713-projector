package com.umeng.analytics.pro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: UMDBUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    public static boolean a(String str, SQLiteDatabase sQLiteDatabase) {
        boolean z = false;
        if (str == null) {
            return false;
        }
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type ='table' and name ='" + str.trim() + "' ", null);
            if (cursorRawQuery.moveToNext() && cursorRawQuery.getInt(0) > 0) {
                z = true;
            }
        } catch (Exception unused) {
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
        return z;
    }

    public static String b(Context context) {
        return context.getDatabasePath(c.f3588b).getParent() + File.separator;
    }

    public static String c(Context context) {
        return b(context) + "subprocess/";
    }

    public static List<String> b(List<String> list) {
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : list) {
                if (Collections.frequency(arrayList, str) < 1) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            File databasePath = context.getDatabasePath(c.f3588b);
            if (databasePath != null && databasePath.exists()) {
                databasePath.delete();
            }
            d.a(context).a();
        } catch (Throwable unused) {
        }
    }

    public static String a(List<String> list) {
        return TextUtils.join("!", list);
    }

    public static List<String> a(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor cursorRawQuery = null;
        boolean z = false;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", null);
            if (cursorRawQuery != null && cursorRawQuery.getColumnIndex(str2) != -1) {
                z = true;
            }
            if (cursorRawQuery != null && !cursorRawQuery.isClosed()) {
                cursorRawQuery.close();
            }
        } catch (Exception unused) {
            if (cursorRawQuery != null && !cursorRawQuery.isClosed()) {
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null && !cursorRawQuery.isClosed()) {
                cursorRawQuery.close();
            }
            throw th;
        }
        return z;
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        sQLiteDatabase.execSQL("alter table " + str + " add " + str2 + " " + str3);
    }
}
