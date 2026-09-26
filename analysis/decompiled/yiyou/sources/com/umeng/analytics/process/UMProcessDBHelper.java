package com.umeng.analytics.process;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.umeng.analytics.pro.m;
import com.umeng.analytics.pro.w;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UMProcessDBHelper {
    private static UMProcessDBHelper mInstance;
    private Context mContext;
    private FileLockUtil mFileLock = new FileLockUtil();
    private InsertEventCallback ekvCallBack = new InsertEventCallback();

    private class InsertEventCallback implements FileLockCallback {
        private InsertEventCallback() {
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(File file, int i) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str, Object obj) throws Throwable {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (str.startsWith(com.umeng.analytics.process.a.f3762c)) {
                str = str.replaceFirst(com.umeng.analytics.process.a.f3762c, "");
            }
            UMProcessDBHelper.this.insertEvents(str.replace(com.umeng.analytics.process.a.f3763d, ""), (JSONArray) obj);
            return true;
        }
    }

    private class ProcessToMainCallback implements FileLockCallback {
        private ProcessToMainCallback() {
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(File file, int i) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str) throws Throwable {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (str.startsWith(com.umeng.analytics.process.a.f3762c)) {
                str = str.replaceFirst(com.umeng.analytics.process.a.f3762c, "");
            }
            UMProcessDBHelper.this.processToMain(str.replace(com.umeng.analytics.process.a.f3763d, ""));
            return true;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str, Object obj) {
            return false;
        }
    }

    private class a implements Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3754a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f3755b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f3756c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        String f3757d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f3758e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        String f3759f;
        String g;
        String h;

        private a() {
        }
    }

    private UMProcessDBHelper() {
    }

    private List<a> datasAdapter(String str, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                a aVar = new a();
                aVar.f3756c = jSONObject.optString("id");
                aVar.g = UMUtils.getAppVersionName(this.mContext);
                aVar.h = UMUtils.getAppVersionCode(this.mContext);
                aVar.f3755b = jSONObject.optString("__i");
                aVar.f3758e = jSONObject.optInt("__t");
                aVar.f3759f = str;
                if (jSONObject.has(com.umeng.analytics.pro.b.ac)) {
                    jSONObject.remove(com.umeng.analytics.pro.b.ac);
                }
                jSONObject.put(com.umeng.analytics.pro.b.ac, getDataSource());
                jSONObject.remove("__i");
                jSONObject.remove("__t");
                aVar.f3757d = w.a().a(jSONObject.toString());
                jSONObject.remove(com.umeng.analytics.pro.b.ac);
                arrayList.add(aVar);
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    private boolean dbIsExists(String str) {
        try {
            return new File(b.b(this.mContext, str)).exists();
        } catch (Throwable unused) {
            return false;
        }
    }

    private int getDataSource() {
        return 0;
    }

    public static UMProcessDBHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UMProcessDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new UMProcessDBHelper(context);
                }
            }
        }
        UMProcessDBHelper uMProcessDBHelper = mInstance;
        uMProcessDBHelper.mContext = context;
        return uMProcessDBHelper;
    }

    private boolean insertEvents_(String str, List<a> list) throws Throwable {
        SQLiteDatabase sQLiteDatabaseA;
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            return true;
        }
        try {
            sQLiteDatabaseA = c.a(this.mContext).a(str);
            try {
                try {
                    sQLiteDatabaseA.beginTransaction();
                    for (a aVar : list) {
                        try {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("__i", aVar.f3755b);
                            contentValues.put("__e", aVar.f3756c);
                            contentValues.put("__t", Integer.valueOf(aVar.f3758e));
                            contentValues.put(com.umeng.analytics.process.a.InterfaceC0085a.f3771f, aVar.f3759f);
                            contentValues.put("__av", aVar.g);
                            contentValues.put("__vc", aVar.h);
                            contentValues.put("__s", aVar.f3757d);
                            sQLiteDatabaseA.insert(com.umeng.analytics.process.a.InterfaceC0085a.f3766a, null, contentValues);
                        } catch (Exception unused) {
                        }
                    }
                    sQLiteDatabaseA.setTransactionSuccessful();
                    if (sQLiteDatabaseA != null) {
                        try {
                            sQLiteDatabaseA.endTransaction();
                        } catch (Throwable unused2) {
                        }
                    }
                    c.a(this.mContext).b(str);
                    return true;
                } catch (Throwable th) {
                    th = th;
                    if (sQLiteDatabaseA != null) {
                        try {
                            sQLiteDatabaseA.endTransaction();
                        } catch (Throwable unused3) {
                        }
                    }
                    c.a(this.mContext).b(str);
                    throw th;
                }
            } catch (Exception unused4) {
                if (sQLiteDatabaseA != null) {
                    try {
                        sQLiteDatabaseA.endTransaction();
                    } catch (Throwable unused5) {
                    }
                }
                c.a(this.mContext).b(str);
                return false;
            }
        } catch (Exception unused6) {
            sQLiteDatabaseA = null;
        } catch (Throwable th2) {
            th = th2;
            sQLiteDatabaseA = null;
        }
    }

    private boolean processIsService(Context context) {
        try {
            return context.getPackageManager().getServiceInfo(new ComponentName(context, this.mContext.getClass()), 0) != null;
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processToMain(String str) throws Throwable {
        if (dbIsExists(str)) {
            List<a> eventByProcess = readEventByProcess(str);
            if (!eventByProcess.isEmpty() && insertEvents_(com.umeng.analytics.process.a.h, eventByProcess)) {
                deleteEventDatas(str, null, eventByProcess);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00a9 A[Catch: Exception -> 0x00ac, PHI: r3
      0x00a9: PHI (r3v4 android.database.sqlite.SQLiteDatabase) = (r3v3 android.database.sqlite.SQLiteDatabase), (r3v7 android.database.sqlite.SQLiteDatabase) binds: [B:27:0x00a7, B:14:0x008c] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #0 {Exception -> 0x00ac, blocks: (B:26:0x00a4, B:28:0x00a9, B:13:0x0089), top: B:41:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00be A[Catch: Exception -> 0x00c1, TRY_LEAVE, TryCatch #6 {Exception -> 0x00c1, blocks: (B:33:0x00b9, B:35:0x00be), top: B:46:0x00b9 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v4 */
    private List<a> readEventByProcess(String str) throws Throwable {
        SQLiteDatabase sQLiteDatabaseA;
        Exception e2;
        Cursor cursorRawQuery;
        ?? r0 = "select *  from __et_p";
        ArrayList arrayList = new ArrayList();
        try {
            try {
                try {
                    sQLiteDatabaseA = c.a(this.mContext).a(str);
                    try {
                        sQLiteDatabaseA.beginTransaction();
                        cursorRawQuery = sQLiteDatabaseA.rawQuery("select *  from __et_p", null);
                        if (cursorRawQuery != null) {
                            while (cursorRawQuery.moveToNext()) {
                                try {
                                    a aVar = new a();
                                    aVar.f3754a = cursorRawQuery.getInt(0);
                                    aVar.f3755b = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__i"));
                                    aVar.f3756c = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__e"));
                                    aVar.f3757d = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__s"));
                                    aVar.f3758e = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("__t"));
                                    aVar.f3759f = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(com.umeng.analytics.process.a.InterfaceC0085a.f3771f));
                                    aVar.g = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__av"));
                                    aVar.h = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__vc"));
                                    arrayList.add(aVar);
                                } catch (Exception e3) {
                                    e2 = e3;
                                    e2.printStackTrace();
                                    if (cursorRawQuery != null) {
                                        cursorRawQuery.close();
                                    }
                                    if (sQLiteDatabaseA != null) {
                                        sQLiteDatabaseA.endTransaction();
                                    }
                                }
                            }
                        }
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        if (sQLiteDatabaseA != null) {
                            sQLiteDatabaseA.endTransaction();
                        }
                    } catch (Exception e4) {
                        e2 = e4;
                        cursorRawQuery = null;
                    } catch (Throwable th) {
                        th = th;
                        r0 = 0;
                        if (r0 != 0) {
                            try {
                                r0.close();
                                if (sQLiteDatabaseA != null) {
                                    sQLiteDatabaseA.endTransaction();
                                }
                            } catch (Exception unused) {
                                c.a(this.mContext).b(str);
                                throw th;
                            }
                        } else if (sQLiteDatabaseA != null) {
                            sQLiteDatabaseA.endTransaction();
                        }
                        c.a(this.mContext).b(str);
                        throw th;
                    }
                } catch (Exception unused2) {
                }
            } catch (Exception e5) {
                sQLiteDatabaseA = null;
                e2 = e5;
                cursorRawQuery = null;
            } catch (Throwable th2) {
                th = th2;
                r0 = 0;
                sQLiteDatabaseA = null;
            }
            c.a(this.mContext).b(str);
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public void createDBByProcess(String str) {
        try {
            c.a(this.mContext).a(str);
            c.a(this.mContext).b(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0063 A[PHI: r0
      0x0063: PHI (r0v4 android.database.sqlite.SQLiteDatabase) = (r0v3 android.database.sqlite.SQLiteDatabase), (r0v7 android.database.sqlite.SQLiteDatabase) binds: [B:27:0x0061, B:15:0x0047] A[DONT_GENERATE, DONT_INLINE]] */
    public void deleteEventDatas(String str, String str2, List<a> list) throws Throwable {
        SQLiteDatabase sQLiteDatabaseA;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            sQLiteDatabaseA = c.a(this.mContext).a(str);
            try {
                sQLiteDatabaseA.beginTransaction();
                int size = list.size();
                if (list == null || size <= 0) {
                    sQLiteDatabaseA.delete(com.umeng.analytics.process.a.InterfaceC0085a.f3766a, null, null);
                } else {
                    for (int i = 0; i < size; i++) {
                        sQLiteDatabaseA.execSQL("delete from __et_p where rowid=" + list.get(i).f3754a);
                    }
                }
                sQLiteDatabaseA.setTransactionSuccessful();
                if (sQLiteDatabaseA != null) {
                    sQLiteDatabaseA.endTransaction();
                }
            } catch (Exception unused) {
                if (sQLiteDatabaseA != null) {
                    sQLiteDatabaseA.endTransaction();
                }
            } catch (Throwable th2) {
                th = th2;
                if (sQLiteDatabaseA != null) {
                    sQLiteDatabaseA.endTransaction();
                }
                c.a(this.mContext).b(str);
                throw th;
            }
        } catch (Exception unused2) {
            sQLiteDatabaseA = null;
        } catch (Throwable th3) {
            sQLiteDatabaseA = null;
            th = th3;
        }
        c.a(this.mContext).b(str);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x004b A[DONT_GENERATE, PHI: r1
      0x004b: PHI (r1v5 android.database.sqlite.SQLiteDatabase) = (r1v4 android.database.sqlite.SQLiteDatabase), (r1v6 android.database.sqlite.SQLiteDatabase) binds: [B:16:0x0049, B:8:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
    public void deleteMainProcessEventDatasByIds(List<Integer> list) {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            sQLiteDatabaseA = c.a(this.mContext).a(com.umeng.analytics.process.a.h);
            sQLiteDatabaseA.beginTransaction();
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                sQLiteDatabaseA.delete(com.umeng.analytics.process.a.InterfaceC0085a.f3766a, "id=?", new String[]{String.valueOf(it.next())});
            }
            sQLiteDatabaseA.setTransactionSuccessful();
        } catch (Exception unused) {
        } finally {
            if (sQLiteDatabaseA != null) {
                sQLiteDatabaseA.endTransaction();
            }
            c.a(this.mContext).b(com.umeng.analytics.process.a.h);
        }
    }

    public void insertEvents(String str, JSONArray jSONArray) throws Throwable {
        if (AnalyticsConstants.SUB_PROCESS_EVENT && !TextUtils.isEmpty(str)) {
            insertEvents_(str, datasAdapter(str, jSONArray));
        }
    }

    public void insertEventsInSubProcess(String str, JSONArray jSONArray) throws Throwable {
        if (AnalyticsConstants.SUB_PROCESS_EVENT && !TextUtils.isEmpty(str)) {
            File file = new File(b.b(this.mContext, str));
            if (file.exists()) {
                this.mFileLock.doFileOperateion(file, this.ekvCallBack, jSONArray);
            } else {
                insertEvents(str, jSONArray);
            }
        }
    }

    public void processDBToMain() {
        try {
            DBFileTraversalUtil.traverseDBFiles(b.a(this.mContext), new ProcessToMainCallback(), new DBFileTraversalUtil.a() { // from class: com.umeng.analytics.process.UMProcessDBHelper.1
                @Override // com.umeng.analytics.process.DBFileTraversalUtil.a
                public void a() {
                    if (AnalyticsConstants.SUB_PROCESS_EVENT) {
                        UMWorkDispatch.sendEvent(UMProcessDBHelper.this.mContext, UMProcessDBDatasSender.UM_PROCESS_CONSTRUCTMESSAGE, UMProcessDBDatasSender.getInstance(UMProcessDBHelper.this.mContext), null);
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code duplicated, block: B:68:0x0166  */
    /* JADX WARN: Code duplicated, block: B:77:0x0156 A[EXC_TOP_SPLITTER, PHI: r5
      0x0156: PHI (r5v4 android.database.sqlite.SQLiteDatabase) = (r5v3 android.database.sqlite.SQLiteDatabase), (r5v7 android.database.sqlite.SQLiteDatabase) binds: [B:62:0x0154, B:51:0x0142] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x016b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object, org.json.JSONArray] */
    /* JADX WARN: Type inference failed for: r3v0, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.database.Cursor, android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r6v1, types: [org.json.JSONObject] */
    public JSONObject readMainEvents(long j, List<Integer> list) throws Throwable {
        SQLiteDatabase sQLiteDatabaseA;
        ?? jSONObject = new JSONObject();
        ?? RawQuery = 0;
        RawQuery = 0;
        try {
            try {
                sQLiteDatabaseA = c.a(this.mContext).a(com.umeng.analytics.process.a.h);
                try {
                    sQLiteDatabaseA.beginTransaction();
                    RawQuery = sQLiteDatabaseA.rawQuery("select *  from __et_p", null);
                    if (RawQuery != 0) {
                        ?? jSONObject2 = new JSONObject();
                        String str = "";
                        while (RawQuery.moveToNext()) {
                            int i = RawQuery.getInt(RawQuery.getColumnIndex("id"));
                            int i2 = RawQuery.getInt(RawQuery.getColumnIndex("__t"));
                            String string = RawQuery.getString(RawQuery.getColumnIndex("__i"));
                            String string2 = RawQuery.getString(RawQuery.getColumnIndex("__s"));
                            String string3 = RawQuery.getString(RawQuery.getColumnIndex(com.umeng.analytics.process.a.InterfaceC0085a.f3771f));
                            String string4 = RawQuery.getString(RawQuery.getColumnIndex("__av"));
                            if (!TextUtils.isEmpty(string)) {
                                if (TextUtils.isEmpty(str)) {
                                    str = string4;
                                }
                                if (!TextUtils.isEmpty(string2) && i2 == 2049) {
                                    JSONObject jSONObject3 = new JSONObject(w.a().b(string2));
                                    String strOptString = jSONObject3.optString("pn");
                                    if (TextUtils.isEmpty(strOptString) || "unknown".equals(strOptString)) {
                                        jSONObject3.put("pn", this.mContext.getPackageName() + Config.TRACE_TODAY_VISIT_SPLIT + string3);
                                    }
                                    JSONArray jSONArrayOptJSONArray = jSONObject2.has(string) ? jSONObject2.optJSONArray(string) : new JSONArray();
                                    if (m.a(jSONObject3) + m.a(jSONArrayOptJSONArray) > j || !str.equalsIgnoreCase(string4)) {
                                        break;
                                        break;
                                    }
                                    list.add(Integer.valueOf(i));
                                    jSONArrayOptJSONArray.put(jSONObject3);
                                    jSONObject2.put(string, jSONArrayOptJSONArray);
                                }
                            }
                        }
                        if (jSONObject2.length() > 0) {
                            ?? jSONArray = new JSONArray();
                            Iterator<String> itKeys = jSONObject2.keys();
                            while (itKeys.hasNext()) {
                                JSONObject jSONObject4 = new JSONObject();
                                String next = itKeys.next();
                                jSONObject4.put(next, new JSONArray(jSONObject2.optString(next)));
                                if (jSONObject4.length() > 0) {
                                    jSONArray.put(jSONObject4);
                                }
                            }
                            if (jSONArray.length() > 0) {
                                jSONObject.put(com.umeng.analytics.pro.b.R, jSONArray);
                            }
                        }
                    }
                    sQLiteDatabaseA.setTransactionSuccessful();
                    if (RawQuery != 0) {
                        RawQuery.close();
                    }
                    if (sQLiteDatabaseA != null) {
                        try {
                            sQLiteDatabaseA.endTransaction();
                        } catch (Throwable unused) {
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    if (RawQuery != 0) {
                        RawQuery.close();
                    }
                    if (sQLiteDatabaseA != null) {
                        sQLiteDatabaseA.endTransaction();
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    RawQuery.close();
                }
                if (0 != 0) {
                    try {
                        RawQuery.endTransaction();
                    } catch (Throwable unused2) {
                    }
                }
                c.a(this.mContext).b(com.umeng.analytics.process.a.h);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            sQLiteDatabaseA = null;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                RawQuery.close();
            }
            if (0 != 0) {
                RawQuery.endTransaction();
            }
            c.a(this.mContext).b(com.umeng.analytics.process.a.h);
            throw th;
        }
        c.a(this.mContext).b(com.umeng.analytics.process.a.h);
        return jSONObject;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0069 A[Catch: Exception -> 0x006c, TRY_LEAVE, TryCatch #3 {Exception -> 0x006c, blocks: (B:24:0x0064, B:26:0x0069), top: B:56:0x0064 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x008a A[Catch: Exception -> 0x008d, TRY_LEAVE, TryCatch #1 {Exception -> 0x008d, blocks: (B:36:0x0085, B:38:0x008a), top: B:54:0x0085 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x00a1 A[Catch: Exception -> 0x00a4, TRY_LEAVE, TryCatch #8 {Exception -> 0x00a4, blocks: (B:44:0x009c, B:46:0x00a1), top: B:63:0x009c }] */
    public JSONObject readVersionInfoFromColumId(Integer num) throws Throwable {
        Cursor cursorRawQuery;
        SQLiteDatabase sQLiteDatabaseA;
        JSONObject jSONObject;
        String str = "select *  from __et_p where rowid=" + num;
        Cursor cursor = null;
        jSONObject = null;
        JSONObject jSONObject2 = null;
        cursor = null;
        cursor = null;
        try {
            sQLiteDatabaseA = c.a(this.mContext).a(com.umeng.analytics.process.a.h);
            try {
                try {
                    sQLiteDatabaseA.beginTransaction();
                    cursorRawQuery = sQLiteDatabaseA.rawQuery(str, null);
                    if (cursorRawQuery != null) {
                        try {
                            try {
                                if (cursorRawQuery.moveToNext()) {
                                    jSONObject = new JSONObject();
                                    try {
                                        String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__av"));
                                        String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__vc"));
                                        if (!TextUtils.isEmpty(string)) {
                                            jSONObject.put("__av", string);
                                        }
                                        if (!TextUtils.isEmpty(string2)) {
                                            jSONObject.put("__vc", string2);
                                        }
                                        jSONObject2 = jSONObject;
                                    } catch (Exception e2) {
                                        e = e2;
                                        cursor = cursorRawQuery;
                                        e.printStackTrace();
                                        if (cursor != null) {
                                            try {
                                                cursor.close();
                                                if (sQLiteDatabaseA != null) {
                                                    sQLiteDatabaseA.endTransaction();
                                                }
                                            } catch (Exception unused) {
                                                c.a(this.mContext).b(com.umeng.analytics.process.a.h);
                                                return jSONObject;
                                            }
                                        } else if (sQLiteDatabaseA != null) {
                                            sQLiteDatabaseA.endTransaction();
                                        }
                                        c.a(this.mContext).b(com.umeng.analytics.process.a.h);
                                        return jSONObject;
                                    }
                                }
                            } catch (Exception e3) {
                                e = e3;
                                jSONObject = null;
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
                                    c.a(this.mContext).b(com.umeng.analytics.process.a.h);
                                    throw th;
                                }
                            } else if (sQLiteDatabaseA != null) {
                                sQLiteDatabaseA.endTransaction();
                            }
                            c.a(this.mContext).b(com.umeng.analytics.process.a.h);
                            throw th;
                        }
                    }
                    if (cursorRawQuery != null) {
                        try {
                            cursorRawQuery.close();
                            if (sQLiteDatabaseA != null) {
                                sQLiteDatabaseA.endTransaction();
                            }
                        } catch (Exception unused3) {
                        }
                    } else if (sQLiteDatabaseA != null) {
                        sQLiteDatabaseA.endTransaction();
                    }
                    c.a(this.mContext).b(com.umeng.analytics.process.a.h);
                    return jSONObject2;
                } catch (Throwable th2) {
                    th = th2;
                    cursorRawQuery = cursor;
                }
            } catch (Exception e4) {
                e = e4;
                jSONObject = null;
            }
        } catch (Exception e5) {
            e = e5;
            sQLiteDatabaseA = null;
            jSONObject = null;
        } catch (Throwable th3) {
            th = th3;
            cursorRawQuery = null;
            sQLiteDatabaseA = null;
        }
    }

    private UMProcessDBHelper(Context context) {
        w.a().a(context);
    }
}
