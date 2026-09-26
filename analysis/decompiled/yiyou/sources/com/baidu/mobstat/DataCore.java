package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DataCore {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JSONObject f3340a = new JSONObject();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static DataCore f3341b = new DataCore();
    private StatService.WearListener h;
    private JSONObject i;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private JSONArray f3342c = new JSONArray();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private JSONArray f3343d = new JSONArray();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private JSONArray f3344e = new JSONArray();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f3345f = false;
    private volatile int g = 0;
    private Object j = new Object();

    private DataCore() {
    }

    private void a(Context context, JSONObject jSONObject) {
    }

    private boolean a(String str) {
        return (str.getBytes().length + BDStatCore.instance().getSessionSize()) + this.g > 184320;
    }

    private void b(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
    }

    private void b(JSONObject jSONObject) {
    }

    public static DataCore instance() {
        return f3341b;
    }

    public void clearCache(Context context) {
        a(false);
        synchronized (f3340a) {
            f3340a = new JSONObject();
        }
        installHeader(context);
        a(context);
    }

    public String constructLogWithEmptyBody(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        HeadObject headObject = CooperService.instance().getHeadObject();
        if (TextUtils.isEmpty(headObject.f3366e)) {
            headObject.installHeader(context, jSONObject2);
        } else {
            headObject.updateHeader(context, jSONObject2);
        }
        JSONArray jSONArray = new JSONArray();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject2.put("t", jCurrentTimeMillis);
            jSONObject2.put("ss", jCurrentTimeMillis);
            jSONObject2.put(Config.WIFI_LOCATION, jSONArray);
            jSONObject2.put(Config.SEQUENCE_INDEX, 0);
            jSONObject2.put("sign", CooperService.instance().getUUID());
            jSONObject2.put(Config.APP_KEY, str);
            jSONObject.put(Config.HEADER_PART, jSONObject2);
            jSONObject.put(Config.PRINCIPAL_PART, jSONArray);
            jSONObject.put(Config.EVENT_PART, jSONArray);
            jSONObject.put(Config.EXCEPTION_PART, jSONArray);
            return jSONObject.toString();
        } catch (JSONException | Exception unused) {
            return null;
        }
    }

    public void flush(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            synchronized (this.f3342c) {
                jSONObject.put(Config.PRINCIPAL_PART, new JSONArray(this.f3342c.toString()));
            }
            synchronized (this.f3343d) {
                jSONObject.put(Config.EVENT_PART, new JSONArray(this.f3343d.toString()));
            }
            synchronized (f3340a) {
                jSONObject.put(Config.HEADER_PART, new JSONObject(f3340a.toString()));
            }
        } catch (Exception unused) {
        }
        String string = jSONObject.toString();
        if (a()) {
            am.c().a("[WARNING] stat cache exceed 184320 Bytes, ignored");
            return;
        }
        int length = string.getBytes().length;
        if (length >= 184320) {
            a(true);
            return;
        }
        this.g = length;
        at.a(context, bb.s(context) + Config.STAT_CACHE_FILE_NAME, string, false);
        synchronized (this.f3344e) {
            at.a(context, Config.LAST_AP_INFO_FILE_NAME, this.f3344e.toString(), false);
        }
    }

    public int getCacheFileSzie() {
        return this.g;
    }

    public JSONObject getLogData() {
        return this.i;
    }

    public void init(Context context) {
        instance().loadStatData(context);
        instance().loadLastSession(context);
        instance().installHeader(context);
    }

    public void installHeader(Context context) {
        synchronized (f3340a) {
            CooperService.instance().getHeadObject().installHeader(context, f3340a);
        }
    }

    public void loadLastSession(Context context) {
        if (context == null) {
            return;
        }
        String str = bb.s(context) + Config.LAST_SESSION_FILE_NAME;
        if (at.c(context, str)) {
            String strA = at.a(context, str);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            at.a(context, str, new JSONObject().toString(), false);
            putSession(strA);
            flush(context);
        }
    }

    public void loadStatData(Context context) {
        if (context == null) {
            return;
        }
        String str = bb.s(context) + Config.STAT_CACHE_FILE_NAME;
        if (at.c(context, str)) {
            String strA = at.a(context, str);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(strA);
            } catch (Exception unused) {
            }
            if (jSONObject == null) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(Config.PRINCIPAL_PART);
                if (jSONArray != null) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (jCurrentTimeMillis - jSONObject2.getLong("s") <= Config.MAX_LOG_DATA_EXSIT_TIME) {
                            putSession(jSONObject2);
                        }
                    }
                }
            } catch (Exception unused2) {
            }
            try {
                JSONArray jSONArray2 = jSONObject.getJSONArray(Config.EVENT_PART);
                if (jSONArray2 != null) {
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        JSONObject jSONObject3 = jSONArray2.getJSONObject(i2);
                        if (jCurrentTimeMillis - jSONObject3.getLong("t") <= Config.MAX_LOG_DATA_EXSIT_TIME) {
                            putEvent(context, jSONObject3);
                        }
                    }
                }
            } catch (Exception unused3) {
            }
            try {
                JSONObject jSONObject4 = jSONObject.getJSONObject(Config.HEADER_PART);
                if (jSONObject4 != null) {
                    synchronized (f3340a) {
                        f3340a = jSONObject4;
                        if (TextUtils.isEmpty(av.a().p(context))) {
                            String string = f3340a.getString(Config.DEVICE_ID_SEC);
                            if (!TextUtils.isEmpty(string)) {
                                av.a().k(context, string);
                            }
                        }
                    }
                }
            } catch (Exception unused4) {
            }
        }
    }

    public void loadWifiData(Context context) {
        if (context != null && at.c(context, Config.LAST_AP_INFO_FILE_NAME)) {
            try {
                JSONArray jSONArray = new JSONArray(at.a(context, Config.LAST_AP_INFO_FILE_NAME));
                int length = jSONArray.length();
                if (length >= 10) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i = length - 10; i < length; i++) {
                        jSONArray2.put(jSONArray.get(i));
                    }
                    jSONArray = jSONArray2;
                }
                String strG = bb.g(1, context);
                if (!TextUtils.isEmpty(strG)) {
                    jSONArray.put(strG);
                }
                synchronized (this.f3344e) {
                    try {
                        this.f3344e = jSONArray;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void putEvent(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (a(jSONObject.toString())) {
            am.c().b("[WARNING] data to put exceed limit, ignored");
            return;
        }
        synchronized (this.f3343d) {
            EventAnalysis.doEventMerge(this.f3343d, jSONObject);
        }
    }

    public void putSession(Session session) {
        putSession(session.constructJSONObject());
    }

    public void saveLogData(Context context, boolean z, boolean z2, long j, boolean z3) {
        saveLogData(context, z, z2, j, z3, null);
    }

    public void saveLogDataAndSendForRaven(Context context) {
        synchronized (this.j) {
        }
    }

    public void sendDataForDueros(Context context) {
    }

    public void putSession(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (a(jSONObject.toString())) {
            am.c().b("[WARNING] data to put exceed limit, ignored");
            return;
        }
        synchronized (this.f3342c) {
            try {
                this.f3342c.put(this.f3342c.length(), jSONObject);
            } catch (JSONException unused) {
            }
        }
    }

    public void saveLogData(Context context, boolean z, boolean z2, long j, boolean z3, JSONObject jSONObject) {
        HeadObject headObject = CooperService.instance().getHeadObject();
        if (headObject != null) {
            synchronized (f3340a) {
                if (TextUtils.isEmpty(headObject.f3366e)) {
                    headObject.installHeader(context, f3340a);
                } else {
                    headObject.updateHeader(context, f3340a);
                }
            }
            if (TextUtils.isEmpty(headObject.f3366e)) {
                am.c().c("[WARNING] 无法找到有效APP Key, 请参考文档配置");
                return;
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        synchronized (f3340a) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                String strOptString = f3340a.optString("at");
                String strOptString2 = f3340a.optString(Config.CUSTOM_USER_ID);
                if (!TextUtils.isEmpty(strOptString) && strOptString.equals("0")) {
                    if (strOptString2.equals(CooperService.instance().getLastUserId(context))) {
                        f3340a.put(Config.UID_CHANGE, "");
                    } else {
                        f3340a.put(Config.UID_CHANGE, strOptString2);
                    }
                    CooperService.instance().setLastUserId(context, strOptString2);
                }
                f3340a.put("t", jCurrentTimeMillis);
                f3340a.put(Config.SEQUENCE_INDEX, z ? 0 : 1);
                f3340a.put("ss", j);
                synchronized (this.f3344e) {
                    f3340a.put(Config.WIFI_LOCATION, this.f3344e);
                }
                f3340a.put("sign", CooperService.instance().getUUID());
                b(context, f3340a, jSONObject);
                jSONObject2.put(Config.HEADER_PART, f3340a);
                synchronized (this.f3342c) {
                    try {
                        try {
                            jSONObject2.put(Config.PRINCIPAL_PART, this.f3342c);
                            synchronized (this.f3343d) {
                                try {
                                    jSONObject2.put(Config.EVENT_PART, this.f3343d);
                                    try {
                                        jSONObject2.put(Config.EXCEPTION_PART, new JSONArray());
                                        a(context, jSONObject2, z2);
                                        b(jSONObject2);
                                        a(context, jSONObject2);
                                        a(context, jSONObject2.toString(), z, z3);
                                        this.i = jSONObject2;
                                        clearCache(context);
                                    } catch (JSONException unused) {
                                    }
                                } catch (JSONException unused2) {
                                }
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    } catch (JSONException unused3) {
                    }
                }
            } catch (Exception unused4) {
            }
        }
    }

    private void a(boolean z) {
        this.f3345f = z;
    }

    private boolean a() {
        return this.f3345f;
    }

    private void a(Context context, JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return;
        }
        JSONObject jSONObject2 = new JSONObject();
        boolean z2 = true;
        try {
            jSONObject2.put(Config.TRACE_APPLICATION_SESSION, z ? 1 : 0);
        } catch (Exception unused) {
        }
        try {
            jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
        } catch (Exception unused2) {
        }
        try {
            jSONObject2.put(Config.TRACE_CIRCLE, af.a());
        } catch (Exception unused3) {
        }
        try {
            jSONObject.put(Config.TRACE_PART, jSONObject2);
        } catch (Exception unused4) {
            z2 = false;
        }
        if (z2) {
            a(context, jSONObject, jSONObject2);
        }
    }

    public void putSession(String str) {
        if (TextUtils.isEmpty(str) || str.equals(new JSONObject().toString())) {
            return;
        }
        try {
            putSession(new JSONObject(str));
        } catch (JSONException unused) {
        }
    }

    private void a(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        long j;
        int iA = a(jSONObject);
        try {
            JSONObject jSONObject3 = jSONObject.getJSONObject(Config.HEADER_PART);
            j = jSONObject3 != null ? jSONObject3.getLong("ss") : 0L;
        } catch (Exception unused) {
        }
        a(context, jSONObject2, j == 0 ? System.currentTimeMillis() : j, iA);
    }

    private int a(JSONObject jSONObject) {
        int i;
        if (jSONObject == null) {
            return 0;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.HEADER_PART);
            i = (jSONObject2.getLong("ss") <= 0 || jSONObject2.getLong(Config.SEQUENCE_INDEX) != 0) ? 0 : 1;
        } catch (Exception unused) {
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Config.PRINCIPAL_PART);
            if (jSONArray != null && jSONArray.length() != 0) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject3 = (JSONObject) jSONArray.get(i2);
                    long j = jSONObject3.getLong("c");
                    if (jSONObject3.getLong("e") != 0 && j == 0) {
                        i++;
                    }
                }
            }
        } catch (Exception unused2) {
        }
        return i;
    }

    private void a(Context context, JSONObject jSONObject, long j, int i) {
        long jLongValue;
        String str;
        long jIntValue;
        String[] strArrSplit;
        long jLongValue2 = ae.a().b(context).longValue();
        if (jLongValue2 <= 0 && i != 0) {
            ae.a().a(context, j);
            jLongValue2 = j;
        }
        a(jSONObject, Config.TRACE_VISIT_FIRST, Long.valueOf(jLongValue2));
        if (i != 0) {
            long jLongValue3 = ae.a().c(context).longValue();
            long j2 = j - jLongValue3;
            if (jLongValue3 == 0 || j2 > 0) {
                jLongValue = jLongValue3 == 0 ? 0L : j2;
            } else {
                jLongValue = -1;
            }
            ae.a().b(context, j);
            ae.a().c(context, jLongValue);
        } else {
            jLongValue = ae.a().d(context).longValue();
        }
        a(jSONObject, Config.TRACE_VISIT_SESSION_LAST_INTERVAL, Long.valueOf(jLongValue));
        String strE = ae.a().e(context);
        int iIntValue = 0;
        String str2 = "";
        if (TextUtils.isEmpty(strE) || !strE.contains(Config.TRACE_TODAY_VISIT_SPLIT) || (strArrSplit = strE.split(Config.TRACE_TODAY_VISIT_SPLIT)) == null || strArrSplit.length != 2) {
            str = "";
        } else {
            String str3 = strArrSplit[0];
            str2 = strArrSplit[1];
            str = str3;
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                iIntValue = Integer.valueOf(str2).intValue();
            } catch (Exception unused) {
            }
        }
        String strA = bc.a(j);
        int i2 = (TextUtils.isEmpty(str) || strA.equals(str)) ? i + iIntValue : i;
        if (i != 0) {
            ae.a().a(context, strA + Config.TRACE_TODAY_VISIT_SPLIT + i2);
        }
        a(jSONObject, Config.TRACE_VISIT_SESSION_TODAY_COUNT, Integer.valueOf(i2));
        if (TextUtils.isEmpty(str)) {
            jIntValue = 0;
        } else {
            try {
                jIntValue = Integer.valueOf(str).intValue();
            } catch (Exception unused2) {
                jIntValue = 0;
            }
        }
        if (jIntValue != 0 && !TextUtils.isEmpty(str) && !strA.equals(str) && i != 0) {
            JSONArray jSONArrayA = a(context, jIntValue, iIntValue);
            ae.a().b(context, jSONArrayA.toString());
            a(jSONObject, Config.TRACE_VISIT_RECENT, jSONArrayA);
            return;
        }
        String strF = ae.a().f(context);
        Object jSONArray = null;
        if (!TextUtils.isEmpty(strF)) {
            try {
                jSONArray = new JSONArray(strF);
            } catch (Exception unused3) {
            }
        }
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        a(jSONObject, Config.TRACE_VISIT_RECENT, jSONArray);
    }

    private JSONArray a(Context context, long j, long j2) {
        List arrayList = new ArrayList();
        String strF = ae.a().f(context);
        boolean z = false;
        if (!TextUtils.isEmpty(strF)) {
            try {
                JSONArray jSONArray = new JSONArray(strF);
                if (jSONArray.length() != 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add((JSONObject) jSONArray.get(i));
                    }
                }
            } catch (Exception unused) {
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            try {
                if (((JSONObject) it.next()).getLong(Config.TRACE_VISIT_RECENT_DAY) == j) {
                    break;
                }
            } catch (Exception unused2) {
            }
        }
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Config.TRACE_VISIT_RECENT_DAY, j);
                jSONObject.put(Config.TRACE_VISIT_RECENT_COUNT, j2);
                arrayList.add(jSONObject);
            } catch (Exception unused3) {
            }
        }
        int size = arrayList.size();
        if (size > 5) {
            arrayList = arrayList.subList(size - 5, size);
        }
        return new JSONArray((Collection) arrayList);
    }

    private void a(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject == null) {
            return;
        }
        if (!jSONObject.has(Config.TRACE_VISIT)) {
            try {
                jSONObject.put(Config.TRACE_VISIT, new JSONObject());
            } catch (Exception unused) {
            }
        }
        try {
            ((JSONObject) jSONObject.get(Config.TRACE_VISIT)).put(str, obj);
        } catch (Exception unused2) {
        }
    }

    private void a(Context context, String str, boolean z, boolean z2) {
        StatService.WearListener wearListener = this.h;
        if (wearListener != null && wearListener.onSendLogData(str)) {
            am.c().a("Log has been passed to app level, log: " + str);
            return;
        }
        LogSender.instance().saveLogData(context, str, false);
        am.c().a("Save log: " + str);
    }

    private void a(Context context) {
        synchronized (this.f3343d) {
            this.f3343d = new JSONArray();
        }
        synchronized (this.f3342c) {
            this.f3342c = new JSONArray();
        }
        synchronized (this.f3344e) {
            this.f3344e = new JSONArray();
        }
        flush(context);
    }
}
