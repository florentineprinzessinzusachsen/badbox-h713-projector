package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: UMStoreManager.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3650a = 2049;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f3651b = 2050;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f3652c = 1000;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Context f3653d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static String f3654e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f3655f = "umeng+";
    private static final String g = "ek__id";
    private static final String h = "ek_key";
    private List<String> i;
    private List<Integer> j;
    private String k;
    private List<String> l;

    /* JADX INFO: compiled from: UMStoreManager.java */
    public enum a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION,
        INSTANTSESSIONBEGIN
    }

    /* JADX INFO: compiled from: UMStoreManager.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final g f3662a = new g();

        private b() {
        }
    }

    public static g a(Context context) {
        g gVar = b.f3662a;
        if (f3653d == null && context != null) {
            f3653d = context.getApplicationContext();
            gVar.k();
        }
        return gVar;
    }

    private void k() {
        synchronized (this) {
            l();
            this.i.clear();
            this.l.clear();
            this.j.clear();
        }
    }

    private void l() {
        try {
            if (TextUtils.isEmpty(f3654e)) {
                String multiProcessSP = UMUtils.getMultiProcessSP(f3653d, g);
                if (TextUtils.isEmpty(multiProcessSP)) {
                    multiProcessSP = DeviceConfig.getDBencryptID(f3653d);
                    if (!TextUtils.isEmpty(multiProcessSP)) {
                        UMUtils.setMultiProcessSP(f3653d, g, multiProcessSP);
                    }
                }
                if (!TextUtils.isEmpty(multiProcessSP)) {
                    String strSubstring = multiProcessSP.substring(1, 9);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < strSubstring.length(); i++) {
                        char cCharAt = strSubstring.charAt(i);
                        if (!Character.isDigit(cCharAt)) {
                            sb.append(cCharAt);
                        } else if (Integer.parseInt(Character.toString(cCharAt)) == 0) {
                            sb.append(0);
                        } else {
                            sb.append(10 - Integer.parseInt(Character.toString(cCharAt)));
                        }
                    }
                    f3654e = sb.toString();
                }
                if (TextUtils.isEmpty(f3654e)) {
                    return;
                }
                f3654e += new StringBuilder(f3654e).reverse().toString();
                String multiProcessSP2 = UMUtils.getMultiProcessSP(f3653d, h);
                if (TextUtils.isEmpty(multiProcessSP2)) {
                    UMUtils.setMultiProcessSP(f3653d, h, c(f3655f));
                } else {
                    if (f3655f.equals(d(multiProcessSP2))) {
                        return;
                    }
                    b(true, false);
                    a(true, false);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public void b() {
        this.l.clear();
    }

    public boolean c() {
        return this.l.isEmpty();
    }

    /* JADX WARN: Code duplicated, block: B:34:0x006f A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r0
      0x006f: PHI (r0v6 android.database.sqlite.SQLiteDatabase) = 
      (r0v4 android.database.sqlite.SQLiteDatabase)
      (r0v5 android.database.sqlite.SQLiteDatabase)
      (r0v9 android.database.sqlite.SQLiteDatabase)
     binds: [B:16:0x006d, B:20:0x0081, B:14:0x006a] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public void d() {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = e.a(f3653d).a();
                sQLiteDatabaseA.beginTransaction();
                String strC = q.a().c();
                if (TextUtils.isEmpty(strC)) {
                    if (sQLiteDatabaseA != null) {
                        try {
                            sQLiteDatabaseA.endTransaction();
                        } catch (Throwable unused) {
                        }
                    }
                    e.a(f3653d).b();
                    return;
                }
                for (String str : new String[]{"", "-1"}) {
                    sQLiteDatabaseA.execSQL("update __et set __i=\"" + strC + "\" where __i=\"" + str + "\"");
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (sQLiteDatabaseA != null) {
                }
            } finally {
                if (sQLiteDatabaseA != null) {
                    try {
                        sQLiteDatabaseA.endTransaction();
                    } catch (Throwable unused2) {
                    }
                }
                e.a(f3653d).b();
            }
        } catch (SQLiteDatabaseCorruptException unused3) {
            f.a(f3653d);
        } catch (Throwable unused4) {
            if (sQLiteDatabaseA != null) {
            }
        }
    }

    public boolean e() {
        return this.i.isEmpty();
    }

    /* JADX WARN: Code duplicated, block: B:51:0x008d A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r2 r5
      0x008d: PHI (r2v5 android.database.sqlite.SQLiteDatabase) = (r2v4 android.database.sqlite.SQLiteDatabase), (r2v6 android.database.sqlite.SQLiteDatabase) binds: [B:28:0x008b, B:35:0x00a6] A[DONT_GENERATE, DONT_INLINE]
      0x008d: PHI (r5v3 org.json.JSONObject) = (r5v2 org.json.JSONObject), (r5v5 org.json.JSONObject) binds: [B:28:0x008b, B:35:0x00a6] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public JSONObject f() {
        SQLiteDatabase sQLiteDatabaseA;
        JSONObject jSONObject;
        Cursor cursor = null;
        jSONObject = null;
        jSONObject = null;
        jSONObject = null;
        JSONObject jSONObject2 = null;
        cursor = null;
        cursor = null;
        Cursor cursor2 = null;
        if (this.l.isEmpty()) {
            return null;
        }
        try {
            sQLiteDatabaseA = e.a(f3653d).a();
            try {
                sQLiteDatabaseA.beginTransaction();
                Cursor cursorRawQuery = sQLiteDatabaseA.rawQuery("select *  from __is where __ii=\"" + this.l.get(0) + "\"", null);
                if (cursorRawQuery != null) {
                    try {
                        if (cursorRawQuery.moveToNext()) {
                            jSONObject = new JSONObject();
                            try {
                                String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__av"));
                                String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__vc"));
                                jSONObject.put("__av", string);
                                jSONObject.put("__vc", string2);
                                jSONObject2 = jSONObject;
                            } catch (SQLiteDatabaseCorruptException unused) {
                                cursor2 = cursorRawQuery;
                                try {
                                    f.a(f3653d);
                                    return jSONObject;
                                } finally {
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    if (sQLiteDatabaseA != null) {
                                        try {
                                            sQLiteDatabaseA.endTransaction();
                                        } catch (Throwable unused2) {
                                        }
                                    }
                                    e.a(f3653d).b();
                                }
                            } catch (Throwable unused3) {
                                cursor = cursorRawQuery;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (sQLiteDatabaseA != null) {
                                }
                                return jSONObject;
                            }
                        }
                    } catch (SQLiteDatabaseCorruptException unused4) {
                        jSONObject = jSONObject2;
                    } catch (Throwable unused5) {
                        jSONObject = jSONObject2;
                    }
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                if (sQLiteDatabaseA != null) {
                    try {
                        sQLiteDatabaseA.endTransaction();
                    } catch (Throwable unused6) {
                    }
                }
                e.a(f3653d).b();
                return jSONObject2;
            } catch (SQLiteDatabaseCorruptException unused7) {
                jSONObject = null;
            } catch (Throwable unused8) {
                jSONObject = null;
            }
        } catch (SQLiteDatabaseCorruptException unused9) {
            sQLiteDatabaseA = null;
            jSONObject = null;
        } catch (Throwable unused10) {
            sQLiteDatabaseA = null;
            jSONObject = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:51:0x008d A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r2 r5
      0x008d: PHI (r2v5 android.database.sqlite.SQLiteDatabase) = (r2v4 android.database.sqlite.SQLiteDatabase), (r2v6 android.database.sqlite.SQLiteDatabase) binds: [B:28:0x008b, B:35:0x00a6] A[DONT_GENERATE, DONT_INLINE]
      0x008d: PHI (r5v3 org.json.JSONObject) = (r5v2 org.json.JSONObject), (r5v5 org.json.JSONObject) binds: [B:28:0x008b, B:35:0x00a6] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public JSONObject g() {
        SQLiteDatabase sQLiteDatabaseA;
        JSONObject jSONObject;
        Cursor cursor = null;
        jSONObject = null;
        jSONObject = null;
        jSONObject = null;
        JSONObject jSONObject2 = null;
        cursor = null;
        cursor = null;
        Cursor cursor2 = null;
        if (this.i.isEmpty()) {
            return null;
        }
        try {
            sQLiteDatabaseA = e.a(f3653d).a();
            try {
                sQLiteDatabaseA.beginTransaction();
                Cursor cursorRawQuery = sQLiteDatabaseA.rawQuery("select *  from __sd where __ii=\"" + this.i.get(0) + "\"", null);
                if (cursorRawQuery != null) {
                    try {
                        if (cursorRawQuery.moveToNext()) {
                            jSONObject = new JSONObject();
                            try {
                                String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__av"));
                                String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__vc"));
                                jSONObject.put("__av", string);
                                jSONObject.put("__vc", string2);
                                jSONObject2 = jSONObject;
                            } catch (SQLiteDatabaseCorruptException unused) {
                                cursor2 = cursorRawQuery;
                                try {
                                    f.a(f3653d);
                                    return jSONObject;
                                } finally {
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    if (sQLiteDatabaseA != null) {
                                        try {
                                            sQLiteDatabaseA.endTransaction();
                                        } catch (Throwable unused2) {
                                        }
                                    }
                                    e.a(f3653d).b();
                                }
                            } catch (Throwable unused3) {
                                cursor = cursorRawQuery;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (sQLiteDatabaseA != null) {
                                }
                                return jSONObject;
                            }
                        }
                    } catch (SQLiteDatabaseCorruptException unused4) {
                        jSONObject = jSONObject2;
                    } catch (Throwable unused5) {
                        jSONObject = jSONObject2;
                    }
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                if (sQLiteDatabaseA != null) {
                    try {
                        sQLiteDatabaseA.endTransaction();
                    } catch (Throwable unused6) {
                    }
                }
                e.a(f3653d).b();
                return jSONObject2;
            } catch (SQLiteDatabaseCorruptException unused7) {
                jSONObject = null;
            } catch (Throwable unused8) {
                jSONObject = null;
            }
        } catch (SQLiteDatabaseCorruptException unused9) {
            sQLiteDatabaseA = null;
            jSONObject = null;
        } catch (Throwable unused10) {
            sQLiteDatabaseA = null;
            jSONObject = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0049 A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r0
      0x0049: PHI (r0v6 android.database.sqlite.SQLiteDatabase) = 
      (r0v4 android.database.sqlite.SQLiteDatabase)
      (r0v5 android.database.sqlite.SQLiteDatabase)
      (r0v9 android.database.sqlite.SQLiteDatabase)
     binds: [B:12:0x0047, B:16:0x005b, B:10:0x0044] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public void h() {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = e.a(f3653d).a();
                sQLiteDatabaseA.beginTransaction();
                if (this.j.size() > 0) {
                    for (int i = 0; i < this.j.size(); i++) {
                        sQLiteDatabaseA.execSQL("delete from __et where rowid=" + this.j.get(i));
                    }
                }
                this.j.clear();
                sQLiteDatabaseA.setTransactionSuccessful();
                if (sQLiteDatabaseA != null) {
                }
            } finally {
                if (sQLiteDatabaseA != null) {
                    try {
                        sQLiteDatabaseA.endTransaction();
                    } catch (Throwable unused) {
                    }
                }
                e.a(f3653d).b();
            }
        } catch (SQLiteDatabaseCorruptException unused2) {
            f.a(f3653d);
        } catch (Throwable unused3) {
            if (sQLiteDatabaseA != null) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x001b A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r0
      0x001b: PHI (r0v6 android.database.sqlite.SQLiteDatabase) = 
      (r0v4 android.database.sqlite.SQLiteDatabase)
      (r0v5 android.database.sqlite.SQLiteDatabase)
      (r0v9 android.database.sqlite.SQLiteDatabase)
     binds: [B:6:0x0019, B:10:0x002d, B:4:0x0016] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public void i() {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = e.a(f3653d).a();
                sQLiteDatabaseA.beginTransaction();
                sQLiteDatabaseA.execSQL("delete from __er");
                sQLiteDatabaseA.setTransactionSuccessful();
                if (sQLiteDatabaseA != null) {
                }
            } finally {
                if (sQLiteDatabaseA != null) {
                    try {
                        sQLiteDatabaseA.endTransaction();
                    } catch (Throwable unused) {
                    }
                }
                e.a(f3653d).b();
            }
        } catch (SQLiteDatabaseCorruptException unused2) {
            f.a(f3653d);
        } catch (Throwable unused3) {
            if (sQLiteDatabaseA != null) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0053 A[EXC_TOP_SPLITTER, PHI: r1
      0x0053: PHI (r1v8 android.database.sqlite.SQLiteDatabase) = 
      (r1v4 android.database.sqlite.SQLiteDatabase)
      (r1v5 android.database.sqlite.SQLiteDatabase)
      (r1v11 android.database.sqlite.SQLiteDatabase)
     binds: [B:9:0x0051, B:14:0x0066, B:6:0x004d] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public void j() {
        SQLiteDatabase sQLiteDatabaseA;
        if (!TextUtils.isEmpty(this.k)) {
            try {
                sQLiteDatabaseA = e.a(f3653d).a();
                try {
                    sQLiteDatabaseA.beginTransaction();
                    sQLiteDatabaseA.execSQL("delete from __er where __i=\"" + this.k + "\"");
                    sQLiteDatabaseA.execSQL("delete from __et where __i=\"" + this.k + "\"");
                    sQLiteDatabaseA.setTransactionSuccessful();
                    if (sQLiteDatabaseA != null) {
                        try {
                            sQLiteDatabaseA.endTransaction();
                        } catch (Throwable unused) {
                        }
                    }
                } catch (SQLiteDatabaseCorruptException unused2) {
                    try {
                        f.a(f3653d);
                        if (sQLiteDatabaseA != null) {
                            sQLiteDatabaseA.endTransaction();
                        }
                    } catch (Throwable th) {
                        if (sQLiteDatabaseA != null) {
                            try {
                                sQLiteDatabaseA.endTransaction();
                            } catch (Throwable unused3) {
                            }
                        }
                        e.a(f3653d).b();
                        throw th;
                    }
                } catch (Throwable unused4) {
                    if (sQLiteDatabaseA != null) {
                        sQLiteDatabaseA.endTransaction();
                    }
                }
            } catch (SQLiteDatabaseCorruptException unused5) {
                sQLiteDatabaseA = null;
            } catch (Throwable unused6) {
                sQLiteDatabaseA = null;
            }
            e.a(f3653d).b();
        }
        this.k = null;
    }

    private g() {
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = null;
        this.l = new ArrayList();
    }

    private void b(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        try {
            long jLongValue = ((Long) jSONObject.get("__e")).longValue();
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("__pp");
            String strC = "";
            String strC2 = (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() <= 0) ? "" : c(jSONObjectOptJSONObject.toString());
            if (jSONObjectOptJSONObject2 != null && jSONObjectOptJSONObject2.length() > 0) {
                strC = c(jSONObjectOptJSONObject2.toString());
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("__ii", str);
            contentValues.put("__e", String.valueOf(jLongValue));
            contentValues.put("__sp", strC2);
            contentValues.put("__pp", strC);
            contentValues.put("__av", UMGlobalContext.getInstance().getAppVersion());
            contentValues.put("__vc", UMUtils.getAppVersionCode(f3653d));
            sQLiteDatabase.insert(c.C0084c.f3616a, null, contentValues);
        } catch (Throwable unused) {
        }
    }

    private void c(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        Cursor cursorRawQuery;
        String strD = null;
        try {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(c.d.a.f3634e);
            if (jSONObjectOptJSONObject != null) {
                cursorRawQuery = sQLiteDatabase.rawQuery("select __d from __sd where __ii=\"" + str + "\"", null);
                if (cursorRawQuery != null) {
                    while (cursorRawQuery.moveToNext()) {
                        try {
                            strD = d(cursorRawQuery.getString(cursorRawQuery.getColumnIndex(c.d.a.f3634e)));
                        } catch (Throwable unused) {
                            if (cursorRawQuery == null) {
                                return;
                            }
                        }
                    }
                }
            } else {
                cursorRawQuery = null;
            }
            if (jSONObjectOptJSONObject != null) {
                JSONArray jSONArray = new JSONArray();
                if (!TextUtils.isEmpty(strD)) {
                    jSONArray = new JSONArray(strD);
                }
                jSONArray.put(jSONObjectOptJSONObject);
                String strC = c(jSONArray.toString());
                if (!TextUtils.isEmpty(strC)) {
                    sQLiteDatabase.execSQL("update  __sd set __d=\"" + strC + "\" where __ii=\"" + str + "\"");
                }
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(c.d.a.f3633d);
            if (jSONObjectOptJSONObject2 != null) {
                String strC2 = c(jSONObjectOptJSONObject2.toString());
                if (!TextUtils.isEmpty(strC2)) {
                    sQLiteDatabase.execSQL("update  __sd set __c=\"" + strC2 + "\" where __ii=\"" + str + "\"");
                }
            }
            sQLiteDatabase.execSQL("update  __sd set __f=\"" + String.valueOf(jSONObject.optLong(c.d.a.g)) + "\" where __ii=\"" + str + "\"");
            if (cursorRawQuery == null) {
                return;
            }
        } catch (Throwable unused2) {
            cursorRawQuery = null;
        }
        cursorRawQuery.close();
    }

    public void a() {
        this.i.clear();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0090, code lost:
    
        if (r3 != null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0096, code lost:
    
        if (r3 != null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0098, code lost:
    
        r3.endTransaction();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(org.json.JSONArray r11) {
        /*
            r10 = this;
            java.lang.String r0 = "__t"
            java.lang.String r1 = "__i"
            r2 = 0
            android.content.Context r3 = com.umeng.analytics.pro.g.f3653d     // Catch: java.lang.Throwable -> L95 android.database.sqlite.SQLiteDatabaseCorruptException -> La5
            com.umeng.analytics.pro.e r3 = com.umeng.analytics.pro.e.a(r3)     // Catch: java.lang.Throwable -> L95 android.database.sqlite.SQLiteDatabaseCorruptException -> La5
            android.database.sqlite.SQLiteDatabase r3 = r3.a()     // Catch: java.lang.Throwable -> L95 android.database.sqlite.SQLiteDatabaseCorruptException -> La5
            r3.beginTransaction()     // Catch: android.database.sqlite.SQLiteDatabaseCorruptException -> L93 java.lang.Throwable -> L96
            r4 = 0
        L13:
            int r5 = r11.length()     // Catch: android.database.sqlite.SQLiteDatabaseCorruptException -> L93 java.lang.Throwable -> L96
            if (r4 >= r5) goto L8d
            org.json.JSONObject r5 = r11.getJSONObject(r4)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            android.content.ContentValues r6 = new android.content.ContentValues     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            r6.<init>()     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.String r7 = r5.optString(r1)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.String r9 = "-1"
            if (r8 != 0) goto L34
            boolean r8 = r9.equals(r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            if (r8 == 0) goto L43
        L34:
            com.umeng.analytics.pro.q r7 = com.umeng.analytics.pro.q.a()     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.String r7 = r7.b()     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            if (r8 == 0) goto L43
            r7 = r9
        L43:
            r6.put(r1, r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.String r7 = "__e"
            java.lang.String r8 = "id"
            java.lang.String r8 = r5.optString(r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            r6.put(r7, r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            int r7 = r5.optInt(r0)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            r6.put(r0, r7)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.String r7 = "__av"
            android.content.Context r8 = com.umeng.analytics.pro.g.f3653d     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.String r8 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            r6.put(r7, r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.String r7 = "__vc"
            android.content.Context r8 = com.umeng.analytics.pro.g.f3653d     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.String r8 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            r6.put(r7, r8)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            r5.remove(r1)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            r5.remove(r0)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.String r7 = "__s"
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.String r5 = r10.c(r5)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            r6.put(r7, r5)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
            java.lang.String r5 = "__et"
            r3.insert(r5, r2, r6)     // Catch: java.lang.Exception -> L8a java.lang.Throwable -> L96
        L8a:
            int r4 = r4 + 1
            goto L13
        L8d:
            r3.setTransactionSuccessful()     // Catch: android.database.sqlite.SQLiteDatabaseCorruptException -> L93 java.lang.Throwable -> L96
            if (r3 == 0) goto L9b
            goto L98
        L93:
            r2 = r3
            goto La5
        L95:
            r3 = r2
        L96:
            if (r3 == 0) goto L9b
        L98:
            r3.endTransaction()     // Catch: java.lang.Throwable -> L9b
        L9b:
            android.content.Context r11 = com.umeng.analytics.pro.g.f3653d
            com.umeng.analytics.pro.e r11 = com.umeng.analytics.pro.e.a(r11)
            r11.b()
            goto Lb0
        La5:
            android.content.Context r11 = com.umeng.analytics.pro.g.f3653d     // Catch: java.lang.Throwable -> Lb1
            com.umeng.analytics.pro.f.a(r11)     // Catch: java.lang.Throwable -> Lb1
            if (r2 == 0) goto L9b
            r2.endTransaction()     // Catch: java.lang.Throwable -> L9b
            goto L9b
        Lb0:
            return
        Lb1:
            r11 = move-exception
            if (r2 == 0) goto Lb7
            r2.endTransaction()     // Catch: java.lang.Throwable -> Lb7
        Lb7:
            android.content.Context r0 = com.umeng.analytics.pro.g.f3653d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto Lc2
        Lc1:
            throw r11
        Lc2:
            goto Lc1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(org.json.JSONArray):void");
    }

    public JSONObject b(boolean z) {
        JSONObject jSONObject = new JSONObject();
        b(jSONObject, z);
        return jSONObject;
    }

    public String d(String str) {
        try {
            return TextUtils.isEmpty(f3654e) ? str : new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), f3654e.getBytes()));
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:52:0x007a A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r1
      0x007a: PHI (r1v4 android.database.sqlite.SQLiteDatabase) = 
      (r1v2 android.database.sqlite.SQLiteDatabase)
      (r1v3 android.database.sqlite.SQLiteDatabase)
      (r1v7 android.database.sqlite.SQLiteDatabase)
     binds: [B:27:0x0078, B:34:0x0092, B:21:0x006d] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    private void b(JSONObject jSONObject, String str) {
        SQLiteDatabase sQLiteDatabaseA;
        Cursor cursorRawQuery = null;
        try {
            sQLiteDatabaseA = e.a(f3653d).a();
            try {
                sQLiteDatabaseA.beginTransaction();
                String str2 = "select *  from __er";
                if (!TextUtils.isEmpty(str)) {
                    str2 = "select *  from __er where __i=\"" + str + "\"";
                }
                cursorRawQuery = sQLiteDatabaseA.rawQuery(str2, null);
                if (cursorRawQuery != null) {
                    JSONArray jSONArray = new JSONArray();
                    while (cursorRawQuery.moveToNext()) {
                        String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__a"));
                        if (!TextUtils.isEmpty(string)) {
                            jSONArray.put(new JSONObject(d(string)));
                        }
                    }
                    if (jSONArray.length() > 0) {
                        jSONObject.put(com.umeng.analytics.pro.b.N, jSONArray);
                    }
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                if (sQLiteDatabaseA != null) {
                }
            } catch (SQLiteDatabaseCorruptException unused) {
                try {
                    f.a(f3653d);
                } finally {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    if (sQLiteDatabaseA != null) {
                        try {
                            sQLiteDatabaseA.endTransaction();
                        } catch (Throwable unused2) {
                        }
                    }
                    e.a(f3653d).b();
                }
            } catch (Throwable unused3) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                if (sQLiteDatabaseA != null) {
                }
            }
        } catch (SQLiteDatabaseCorruptException unused4) {
            sQLiteDatabaseA = null;
        } catch (Throwable unused5) {
            sQLiteDatabaseA = null;
        }
    }

    public String c(String str) {
        try {
            return TextUtils.isEmpty(f3654e) ? str : Base64.encodeToString(DataHelper.encrypt(str.getBytes(), f3654e.getBytes()), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0054 A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r1
      0x0054: PHI (r1v4 android.database.sqlite.SQLiteDatabase) = 
      (r1v2 android.database.sqlite.SQLiteDatabase)
      (r1v3 android.database.sqlite.SQLiteDatabase)
      (r1v7 android.database.sqlite.SQLiteDatabase)
     binds: [B:11:0x0052, B:16:0x0067, B:8:0x004e] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public boolean a(String str, String str2, int i) {
        SQLiteDatabase sQLiteDatabaseA;
        try {
            sQLiteDatabaseA = e.a(f3653d).a();
            try {
                sQLiteDatabaseA.beginTransaction();
                ContentValues contentValues = new ContentValues();
                contentValues.put("__i", str);
                String strC = c(str2);
                if (!TextUtils.isEmpty(strC)) {
                    contentValues.put("__a", strC);
                    contentValues.put("__t", Integer.valueOf(i));
                    contentValues.put("__av", UMUtils.getAppVersionName(f3653d));
                    contentValues.put("__vc", UMUtils.getAppVersionCode(f3653d));
                    sQLiteDatabaseA.insert(c.a.f3592a, null, contentValues);
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (sQLiteDatabaseA != null) {
                }
            } catch (SQLiteDatabaseCorruptException unused) {
                try {
                    f.a(f3653d);
                } finally {
                    if (sQLiteDatabaseA != null) {
                        try {
                            sQLiteDatabaseA.endTransaction();
                        } catch (Throwable unused2) {
                        }
                    }
                    e.a(f3653d).b();
                }
            } catch (Throwable unused3) {
                if (sQLiteDatabaseA != null) {
                }
            }
        } catch (SQLiteDatabaseCorruptException unused4) {
            sQLiteDatabaseA = null;
        } catch (Throwable unused5) {
            sQLiteDatabaseA = null;
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:60:0x00bb A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r0 r1
      0x00bb: PHI (r0v3 java.lang.String) = (r0v1 java.lang.String), (r0v2 java.lang.String), (r0v10 java.lang.String) binds: [B:35:0x00b9, B:42:0x00d4, B:27:0x00a9] A[DONT_GENERATE, DONT_INLINE]
      0x00bb: PHI (r1v4 android.database.sqlite.SQLiteDatabase) = 
      (r1v2 android.database.sqlite.SQLiteDatabase)
      (r1v3 android.database.sqlite.SQLiteDatabase)
      (r1v7 android.database.sqlite.SQLiteDatabase)
     binds: [B:35:0x00b9, B:42:0x00d4, B:27:0x00a9] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    private String b(JSONObject jSONObject, boolean z) {
        SQLiteDatabase sQLiteDatabaseA;
        Cursor cursorRawQuery;
        String string = null;
        try {
            sQLiteDatabaseA = e.a(f3653d).a();
            try {
                sQLiteDatabaseA.beginTransaction();
                cursorRawQuery = sQLiteDatabaseA.rawQuery("select *  from __is", null);
                if (cursorRawQuery != null) {
                    try {
                        JSONArray jSONArray = new JSONArray();
                        while (cursorRawQuery.moveToNext()) {
                            JSONObject jSONObject2 = new JSONObject();
                            String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__e"));
                            string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__ii"));
                            this.l.add(string);
                            String string3 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__sp"));
                            String string4 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__pp"));
                            if (!TextUtils.isEmpty(string3)) {
                                jSONObject2.put(com.umeng.analytics.pro.b.ar, new JSONObject(d(string3)));
                            }
                            if (!TextUtils.isEmpty(string4)) {
                                jSONObject2.put(com.umeng.analytics.pro.b.as, new JSONObject(d(string4)));
                            }
                            if (!TextUtils.isEmpty(string2)) {
                                jSONObject2.put("id", string);
                                jSONObject2.put(com.umeng.analytics.pro.b.p, string2);
                                if (jSONObject2.length() > 0) {
                                    jSONArray.put(jSONObject2);
                                }
                                if (z) {
                                    break;
                                }
                            }
                        }
                        if (jSONArray.length() > 0) {
                            jSONObject.put(com.umeng.analytics.pro.b.n, jSONArray);
                        }
                        sQLiteDatabaseA.setTransactionSuccessful();
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        if (sQLiteDatabaseA != null) {
                        }
                    } catch (SQLiteDatabaseCorruptException unused) {
                        try {
                            f.a(f3653d);
                        } finally {
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (sQLiteDatabaseA != null) {
                                try {
                                    sQLiteDatabaseA.endTransaction();
                                } catch (Throwable unused2) {
                                }
                            }
                            e.a(f3653d).b();
                        }
                    } catch (Throwable unused3) {
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        if (sQLiteDatabaseA != null) {
                        }
                    }
                } else {
                    sQLiteDatabaseA.setTransactionSuccessful();
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    if (sQLiteDatabaseA != null) {
                    }
                }
            } catch (SQLiteDatabaseCorruptException unused4) {
                cursorRawQuery = null;
            } catch (Throwable unused5) {
                cursorRawQuery = null;
            }
        } catch (SQLiteDatabaseCorruptException unused6) {
            sQLiteDatabaseA = null;
            cursorRawQuery = null;
        } catch (Throwable unused7) {
            sQLiteDatabaseA = null;
            cursorRawQuery = null;
        }
        return string;
    }

    /* JADX WARN: Code duplicated, block: B:49:0x0083 A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r3
      0x0083: PHI (r3v4 android.database.sqlite.SQLiteDatabase) = 
      (r3v2 android.database.sqlite.SQLiteDatabase)
      (r3v3 android.database.sqlite.SQLiteDatabase)
      (r3v7 android.database.sqlite.SQLiteDatabase)
     binds: [B:29:0x0081, B:34:0x0096, B:26:0x007d] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public boolean a(String str, JSONObject jSONObject, a aVar) {
        SQLiteDatabase sQLiteDatabaseA;
        if (jSONObject == null) {
            return false;
        }
        try {
            sQLiteDatabaseA = e.a(f3653d).a();
            try {
                sQLiteDatabaseA.beginTransaction();
                if (aVar == a.BEGIN) {
                    long jLongValue = ((Long) jSONObject.opt("__e")).longValue();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("__ii", str);
                    contentValues.put("__e", String.valueOf(jLongValue));
                    contentValues.put("__av", UMUtils.getAppVersionName(f3653d));
                    contentValues.put("__vc", UMUtils.getAppVersionCode(f3653d));
                    sQLiteDatabaseA.insert(c.d.f3629a, null, contentValues);
                } else if (aVar == a.INSTANTSESSIONBEGIN) {
                    b(str, jSONObject, sQLiteDatabaseA);
                } else if (aVar == a.END) {
                    a(str, jSONObject, sQLiteDatabaseA);
                } else if (aVar == a.PAGE) {
                    a(str, jSONObject, sQLiteDatabaseA, "__a");
                } else if (aVar == a.AUTOPAGE) {
                    a(str, jSONObject, sQLiteDatabaseA, c.d.a.f3632c);
                } else if (aVar == a.NEWSESSION) {
                    c(str, jSONObject, sQLiteDatabaseA);
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (sQLiteDatabaseA != null) {
                }
            } catch (SQLiteDatabaseCorruptException unused) {
                try {
                    f.a(f3653d);
                } finally {
                    if (sQLiteDatabaseA != null) {
                        try {
                            sQLiteDatabaseA.endTransaction();
                        } catch (Throwable unused2) {
                        }
                    }
                    e.a(f3653d).b();
                }
            } catch (Throwable unused3) {
                if (sQLiteDatabaseA != null) {
                }
            }
        } catch (SQLiteDatabaseCorruptException unused4) {
            sQLiteDatabaseA = null;
        } catch (Throwable unused5) {
            sQLiteDatabaseA = null;
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0055 A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r0
      0x0055: PHI (r0v4 android.database.sqlite.SQLiteDatabase) = 
      (r0v2 android.database.sqlite.SQLiteDatabase)
      (r0v3 android.database.sqlite.SQLiteDatabase)
      (r0v5 android.database.sqlite.SQLiteDatabase)
     binds: [B:16:0x0053, B:20:0x0067, B:14:0x0050] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public void b(boolean z, boolean z2) {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = e.a(f3653d).a();
                sQLiteDatabaseA.beginTransaction();
                if (z2) {
                    if (z) {
                        sQLiteDatabaseA.execSQL("delete from __sd");
                    }
                } else if (this.i.size() > 0) {
                    for (int i = 0; i < this.i.size(); i++) {
                        sQLiteDatabaseA.execSQL("delete from __sd where __ii=\"" + this.i.get(i) + "\"");
                    }
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (sQLiteDatabaseA != null) {
                }
            } finally {
                if (sQLiteDatabaseA != null) {
                    try {
                        sQLiteDatabaseA.endTransaction();
                    } catch (Throwable unused) {
                    }
                }
                e.a(f3653d).b();
            }
        } catch (SQLiteDatabaseCorruptException unused2) {
            f.a(f3653d);
        } catch (Throwable unused3) {
            if (sQLiteDatabaseA != null) {
            }
        }
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        try {
            long jLongValue = ((Long) jSONObject.opt(c.d.a.g)).longValue();
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("__pp");
            String strC = "";
            String strC2 = (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() <= 0) ? "" : c(jSONObjectOptJSONObject.toString());
            if (jSONObjectOptJSONObject2 != null && jSONObjectOptJSONObject2.length() > 0) {
                strC = c(jSONObjectOptJSONObject2.toString());
            }
            sQLiteDatabase.execSQL("update __sd set __f=\"" + jLongValue + "\", __sp=\"" + strC2 + "\", __pp=\"" + strC + "\" where __ii=\"" + str + "\"");
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x004c A[Catch: Exception -> 0x004f, TRY_LEAVE, TryCatch #0 {Exception -> 0x004f, blocks: (B:16:0x0047, B:18:0x004c), top: B:31:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:25:0x0061 A[Catch: Exception -> 0x0064, PHI: r1 r3
      0x0061: PHI (r1v4 long) = (r1v1 long), (r1v7 long) binds: [B:24:0x005f, B:9:0x003e] A[DONT_GENERATE, DONT_INLINE]
      0x0061: PHI (r3v4 android.database.sqlite.SQLiteDatabase) = (r3v3 android.database.sqlite.SQLiteDatabase), (r3v7 android.database.sqlite.SQLiteDatabase) binds: [B:24:0x005f, B:9:0x003e] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #5 {Exception -> 0x0064, blocks: (B:8:0x003b, B:25:0x0061, B:23:0x005c), top: B:33:0x0019 }] */
    public long a(String str) throws Throwable {
        SQLiteDatabase sQLiteDatabaseA;
        String str2 = "select __f from __sd where __ii=\"" + str + "\"";
        Cursor cursorRawQuery = null;
        long j = 0;
        try {
            try {
                sQLiteDatabaseA = e.a(f3653d).a();
                try {
                    sQLiteDatabaseA.beginTransaction();
                    cursorRawQuery = sQLiteDatabaseA.rawQuery(str2, null);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.moveToFirst();
                        j = cursorRawQuery.getLong(cursorRawQuery.getColumnIndex(c.d.a.g));
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    if (sQLiteDatabaseA != null) {
                        sQLiteDatabaseA.endTransaction();
                    }
                } catch (Exception unused) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    if (sQLiteDatabaseA != null) {
                        sQLiteDatabaseA.endTransaction();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursorRawQuery != null) {
                        try {
                            cursorRawQuery.close();
                            if (sQLiteDatabaseA != null) {
                                sQLiteDatabaseA.endTransaction();
                            }
                        } catch (Exception unused2) {
                            e.a(f3653d).b();
                            throw th;
                        }
                    } else if (sQLiteDatabaseA != null) {
                        sQLiteDatabaseA.endTransaction();
                    }
                    e.a(f3653d).b();
                    throw th;
                }
            } catch (Exception unused3) {
            }
        } catch (Exception unused4) {
            sQLiteDatabaseA = null;
        } catch (Throwable th2) {
            th = th2;
            sQLiteDatabaseA = null;
        }
        e.a(f3653d).b();
        return j;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0035 A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r0
      0x0035: PHI (r0v6 android.database.sqlite.SQLiteDatabase) = 
      (r0v4 android.database.sqlite.SQLiteDatabase)
      (r0v5 android.database.sqlite.SQLiteDatabase)
      (r0v7 android.database.sqlite.SQLiteDatabase)
     binds: [B:9:0x0033, B:13:0x0047, B:7:0x0030] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public void b(String str) {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = e.a(f3653d).a();
                sQLiteDatabaseA.beginTransaction();
                if (!TextUtils.isEmpty(str)) {
                    sQLiteDatabaseA.execSQL("delete from __is where __ii=\"" + str + "\"");
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (sQLiteDatabaseA != null) {
                }
            } finally {
                if (sQLiteDatabaseA != null) {
                    try {
                        sQLiteDatabaseA.endTransaction();
                    } catch (Throwable unused) {
                    }
                }
                e.a(f3653d).b();
            }
        } catch (SQLiteDatabaseCorruptException unused2) {
            f.a(f3653d);
        } catch (Throwable unused3) {
            if (sQLiteDatabaseA != null) {
            }
        }
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase, String str2) {
        Cursor cursorRawQuery;
        JSONArray jSONArrayOptJSONArray;
        String strD = null;
        try {
            if ("__a".equals(str2)) {
                jSONArrayOptJSONArray = jSONObject.optJSONArray("__a");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                    return;
                }
            } else if (c.d.a.f3632c.equals(str2)) {
                jSONArrayOptJSONArray = jSONObject.optJSONArray(c.d.a.f3632c);
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                    return;
                }
            } else {
                jSONArrayOptJSONArray = null;
            }
            cursorRawQuery = sQLiteDatabase.rawQuery("select " + str2 + " from " + c.d.f3629a + " where __ii=\"" + str + "\"", null);
            if (cursorRawQuery != null) {
                while (cursorRawQuery.moveToNext()) {
                    try {
                        strD = d(cursorRawQuery.getString(cursorRawQuery.getColumnIndex(str2)));
                    } catch (Throwable unused) {
                        if (cursorRawQuery == null) {
                            return;
                        }
                    }
                }
            }
            JSONArray jSONArray = new JSONArray();
            if (!TextUtils.isEmpty(strD)) {
                jSONArray = new JSONArray(strD);
            }
            if (jSONArray.length() > 1000) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                    return;
                }
                return;
            }
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                    if (jSONObject2 != null) {
                        jSONArray.put(jSONObject2);
                    }
                } catch (JSONException unused2) {
                }
            }
            String strC = c(jSONArray.toString());
            if (!TextUtils.isEmpty(strC)) {
                sQLiteDatabase.execSQL("update __sd set " + str2 + "=\"" + strC + "\" where __ii=\"" + str + "\"");
            }
            if (cursorRawQuery == null) {
                return;
            }
            cursorRawQuery.close();
        } catch (Throwable unused3) {
            cursorRawQuery = null;
        }
    }

    public JSONObject a(boolean z) {
        a();
        this.j.clear();
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            a(jSONObject, z);
            b(jSONObject, (String) null);
            a(jSONObject, (String) null);
        } else {
            String strA = a(jSONObject, z);
            if (!TextUtils.isEmpty(strA)) {
                b(jSONObject, strA);
                a(jSONObject, strA);
            }
        }
        return jSONObject;
    }

    /* JADX WARN: Code duplicated, block: B:92:0x0178 A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r1
      0x0178: PHI (r1v4 android.database.sqlite.SQLiteDatabase) = 
      (r1v2 android.database.sqlite.SQLiteDatabase)
      (r1v3 android.database.sqlite.SQLiteDatabase)
      (r1v7 android.database.sqlite.SQLiteDatabase)
     binds: [B:69:0x0176, B:76:0x0190, B:63:0x016b] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    private void a(JSONObject jSONObject, String str) {
        SQLiteDatabase sQLiteDatabaseA;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        Cursor cursorRawQuery = null;
        try {
            sQLiteDatabaseA = e.a(f3653d).a();
            try {
                sQLiteDatabaseA.beginTransaction();
                String str2 = "select *  from __et";
                if (!TextUtils.isEmpty(str)) {
                    str2 = "select *  from __et where __i=\"" + str + "\"";
                }
                cursorRawQuery = sQLiteDatabaseA.rawQuery(str2, null);
                if (cursorRawQuery != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    JSONObject jSONObject3 = new JSONObject();
                    String strB = q.a().b();
                    while (cursorRawQuery.moveToNext()) {
                        int i = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("__t"));
                        String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__i"));
                        String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__s"));
                        if (TextUtils.isEmpty(string) || "-1".equals(string)) {
                            if (!TextUtils.isEmpty(strB)) {
                                string = strB;
                            }
                        }
                        this.j.add(Integer.valueOf(cursorRawQuery.getInt(0)));
                        if (i != 2049) {
                            if (i == 2050 && !TextUtils.isEmpty(string2)) {
                                JSONObject jSONObject4 = new JSONObject(d(string2));
                                if (jSONObject3.has(string)) {
                                    jSONArray = jSONObject3.optJSONArray(string);
                                } else {
                                    jSONArray = new JSONArray();
                                }
                                jSONArray.put(jSONObject4);
                                jSONObject3.put(string, jSONArray);
                            }
                        } else if (!TextUtils.isEmpty(string2)) {
                            JSONObject jSONObject5 = new JSONObject(d(string2));
                            if (jSONObject2.has(string)) {
                                jSONArray2 = jSONObject2.optJSONArray(string);
                            } else {
                                jSONArray2 = new JSONArray();
                            }
                            jSONArray2.put(jSONObject5);
                            jSONObject2.put(string, jSONArray2);
                        }
                    }
                    if (jSONObject2.length() > 0) {
                        JSONArray jSONArray3 = new JSONArray();
                        Iterator<String> itKeys = jSONObject2.keys();
                        while (itKeys.hasNext()) {
                            JSONObject jSONObject6 = new JSONObject();
                            String next = itKeys.next();
                            jSONObject6.put(next, new JSONArray(jSONObject2.optString(next)));
                            if (jSONObject6.length() > 0) {
                                jSONArray3.put(jSONObject6);
                            }
                        }
                        if (jSONArray3.length() > 0) {
                            jSONObject.put(com.umeng.analytics.pro.b.R, jSONArray3);
                        }
                    }
                    if (jSONObject3.length() > 0) {
                        JSONArray jSONArray4 = new JSONArray();
                        Iterator<String> itKeys2 = jSONObject3.keys();
                        while (itKeys2.hasNext()) {
                            JSONObject jSONObject7 = new JSONObject();
                            String next2 = itKeys2.next();
                            jSONObject7.put(next2, new JSONArray(jSONObject3.optString(next2)));
                            if (jSONObject7.length() > 0) {
                                jSONArray4.put(jSONObject7);
                            }
                        }
                        if (jSONArray4.length() > 0) {
                            jSONObject.put(com.umeng.analytics.pro.b.S, jSONArray4);
                        }
                    }
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                if (sQLiteDatabaseA != null) {
                }
            } catch (SQLiteDatabaseCorruptException unused) {
                try {
                    f.a(f3653d);
                } finally {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    if (sQLiteDatabaseA != null) {
                        try {
                            sQLiteDatabaseA.endTransaction();
                        } catch (Throwable unused2) {
                        }
                    }
                    e.a(f3653d).b();
                }
            } catch (Throwable unused3) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                if (sQLiteDatabaseA != null) {
                }
            }
        } catch (SQLiteDatabaseCorruptException unused4) {
            sQLiteDatabaseA = null;
        } catch (Throwable unused5) {
            sQLiteDatabaseA = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:53:0x0178  */
    /* JADX WARN: Code duplicated, block: B:84:0x018d A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r0 r2
      0x018d: PHI (r0v4 java.lang.String) = (r0v1 java.lang.String), (r0v2 java.lang.String), (r0v11 java.lang.String) binds: [B:62:0x018b, B:69:0x01a6, B:54:0x017b] A[DONT_GENERATE, DONT_INLINE]
      0x018d: PHI (r2v6 android.database.sqlite.SQLiteDatabase) = 
      (r2v2 android.database.sqlite.SQLiteDatabase)
      (r2v3 android.database.sqlite.SQLiteDatabase)
      (r2v11 android.database.sqlite.SQLiteDatabase)
     binds: [B:62:0x018b, B:69:0x01a6, B:54:0x017b] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    private String a(JSONObject jSONObject, boolean z) {
        SQLiteDatabase sQLiteDatabaseA;
        Cursor cursorRawQuery;
        String string = null;
        try {
            sQLiteDatabaseA = e.a(f3653d).a();
            try {
                sQLiteDatabaseA.beginTransaction();
                cursorRawQuery = sQLiteDatabaseA.rawQuery("select *  from __sd", null);
                if (cursorRawQuery != null) {
                    try {
                        JSONArray jSONArray = new JSONArray();
                        while (cursorRawQuery.moveToNext()) {
                            JSONObject jSONObject2 = new JSONObject();
                            String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(c.d.a.g));
                            String string3 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__e"));
                            string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__ii"));
                            if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                                if (Long.parseLong(string2) - Long.parseLong(string3) > 0) {
                                    String string4 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__a"));
                                    String string5 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(c.d.a.f3632c));
                                    String string6 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(c.d.a.f3633d));
                                    String string7 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(c.d.a.f3634e));
                                    this.i.add(string);
                                    String string8 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__sp"));
                                    String string9 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__pp"));
                                    jSONObject2.put("id", string);
                                    jSONObject2.put(com.umeng.analytics.pro.b.p, string3);
                                    jSONObject2.put(com.umeng.analytics.pro.b.q, string2);
                                    jSONObject2.put("duration", Long.parseLong(string2) - Long.parseLong(string3));
                                    if (!TextUtils.isEmpty(string4)) {
                                        jSONObject2.put(com.umeng.analytics.pro.b.s, new JSONArray(d(string4)));
                                    }
                                    if (!TextUtils.isEmpty(string5) && AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO) {
                                        jSONObject2.put(com.umeng.analytics.pro.b.t, new JSONArray(d(string5)));
                                    }
                                    if (!TextUtils.isEmpty(string6)) {
                                        jSONObject2.put(com.umeng.analytics.pro.b.E, new JSONObject(d(string6)));
                                    }
                                    if (!TextUtils.isEmpty(string7)) {
                                        jSONObject2.put(com.umeng.analytics.pro.b.A, new JSONArray(d(string7)));
                                    }
                                    if (!TextUtils.isEmpty(string8)) {
                                        jSONObject2.put(com.umeng.analytics.pro.b.ar, new JSONObject(d(string8)));
                                    }
                                    if (!TextUtils.isEmpty(string9)) {
                                        jSONObject2.put(com.umeng.analytics.pro.b.as, new JSONObject(d(string9)));
                                    }
                                    if (jSONObject2.length() > 0) {
                                        jSONArray.put(jSONObject2);
                                    }
                                }
                                if (z) {
                                    break;
                                }
                            }
                        }
                        if (this.i.size() < 1) {
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (sQLiteDatabaseA != null) {
                                try {
                                    sQLiteDatabaseA.endTransaction();
                                } catch (Throwable unused) {
                                }
                            }
                            e.a(f3653d).b();
                            return string;
                        }
                        if (jSONArray.length() > 0) {
                            jSONObject.put(com.umeng.analytics.pro.b.n, jSONArray);
                        }
                        sQLiteDatabaseA.setTransactionSuccessful();
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        if (sQLiteDatabaseA != null) {
                        }
                    } catch (SQLiteDatabaseCorruptException unused2) {
                        try {
                            f.a(f3653d);
                        } finally {
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (sQLiteDatabaseA != null) {
                                try {
                                    sQLiteDatabaseA.endTransaction();
                                } catch (Throwable unused3) {
                                }
                            }
                            e.a(f3653d).b();
                        }
                    } catch (Throwable unused4) {
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        if (sQLiteDatabaseA != null) {
                        }
                    }
                } else {
                    sQLiteDatabaseA.setTransactionSuccessful();
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    if (sQLiteDatabaseA != null) {
                    }
                }
            } catch (SQLiteDatabaseCorruptException unused5) {
                cursorRawQuery = null;
            } catch (Throwable unused6) {
                cursorRawQuery = null;
            }
        } catch (SQLiteDatabaseCorruptException unused7) {
            sQLiteDatabaseA = null;
            cursorRawQuery = null;
        } catch (Throwable unused8) {
            sQLiteDatabaseA = null;
            cursorRawQuery = null;
        }
        return string;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x004f A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r0
      0x004f: PHI (r0v4 android.database.sqlite.SQLiteDatabase) = 
      (r0v2 android.database.sqlite.SQLiteDatabase)
      (r0v3 android.database.sqlite.SQLiteDatabase)
      (r0v5 android.database.sqlite.SQLiteDatabase)
     binds: [B:15:0x004d, B:19:0x0061, B:13:0x004a] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public void a(boolean z, boolean z2) {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = e.a(f3653d).a();
                sQLiteDatabaseA.beginTransaction();
                if (!z2) {
                    int size = this.l.size();
                    if (size > 0) {
                        for (int i = 0; i < size; i++) {
                            sQLiteDatabaseA.execSQL("delete from __is where __ii=\"" + this.l.get(i) + "\"");
                        }
                    }
                } else if (z) {
                    sQLiteDatabaseA.execSQL("delete from __is");
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (sQLiteDatabaseA != null) {
                }
            } finally {
                if (sQLiteDatabaseA != null) {
                    try {
                        sQLiteDatabaseA.endTransaction();
                    } catch (Throwable unused) {
                    }
                }
                e.a(f3653d).b();
            }
        } catch (SQLiteDatabaseCorruptException unused2) {
            f.a(f3653d);
        } catch (Throwable unused3) {
            if (sQLiteDatabaseA != null) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0068 A[DONT_GENERATE, EXC_TOP_SPLITTER, PHI: r0
      0x0068: PHI (r0v4 android.database.sqlite.SQLiteDatabase) = 
      (r0v2 android.database.sqlite.SQLiteDatabase)
      (r0v3 android.database.sqlite.SQLiteDatabase)
      (r0v5 android.database.sqlite.SQLiteDatabase)
     binds: [B:9:0x0066, B:13:0x007a, B:7:0x0063] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    public void a(boolean z, String str) {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = e.a(f3653d).a();
                sQLiteDatabaseA.beginTransaction();
                if (!TextUtils.isEmpty(str)) {
                    sQLiteDatabaseA.execSQL("delete from __er where __i=\"" + str + "\"");
                    sQLiteDatabaseA.execSQL("delete from __et where __i=\"" + str + "\"");
                    this.j.clear();
                    sQLiteDatabaseA.execSQL("delete from __sd where __ii=\"" + str + "\"");
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (sQLiteDatabaseA != null) {
                }
            } finally {
                if (sQLiteDatabaseA != null) {
                    try {
                        sQLiteDatabaseA.endTransaction();
                    } catch (Throwable unused) {
                    }
                }
                e.a(f3653d).b();
            }
        } catch (SQLiteDatabaseCorruptException unused2) {
            f.a(f3653d);
        } catch (Throwable unused3) {
            if (sQLiteDatabaseA != null) {
            }
        }
    }
}
