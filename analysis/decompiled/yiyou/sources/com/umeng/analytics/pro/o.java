package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: EventTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3707a = "fs_lc_tl_uapp";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f3708f = "-1";
    private static Context g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3709b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3710c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f3711d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f3712e;
    private JSONObject h;

    /* JADX INFO: compiled from: EventTracker.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final o f3713a = new o();

        private a() {
        }
    }

    public static o a(Context context) {
        if (g == null && context != null) {
            g = context.getApplicationContext();
        }
        return a.f3713a;
    }

    private void b(Context context) {
        try {
            String string = PreferenceWrapper.getDefault(context).getString(f3707a, null);
            if (!TextUtils.isEmpty(string)) {
                this.h = new JSONObject(string);
            }
            a();
        } catch (Exception unused) {
        }
    }

    private void c(Context context) {
        try {
            if (this.h != null) {
                PreferenceWrapper.getDefault(g).edit().putString(f3707a, this.h.toString()).commit();
            }
        } catch (Throwable unused) {
        }
    }

    private o() {
        this.f3709b = 128;
        this.f3710c = 256;
        this.f3711d = 1024;
        this.f3712e = 10;
        this.h = null;
        try {
            if (this.h == null) {
                b(g);
            }
        } catch (Throwable unused) {
        }
    }

    private boolean c(String str) {
        if (str == null) {
            return true;
        }
        try {
            return str.trim().getBytes().length <= 1024;
        } catch (Exception unused) {
            return false;
        }
    }

    public void a(String str, String str2, long j, int i, String str3) {
        String strA;
        try {
            if (a(str) && b(str2)) {
                if (Arrays.asList(b.aD).contains(str)) {
                    MLog.e("key is " + str + ", please check key, illegal");
                    UMLog.aq(h.m, 0, "\\|");
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", jCurrentTimeMillis);
                if (j > 0) {
                    jSONObject.put(b.V, j);
                }
                jSONObject.put("__t", g.f3650a);
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject.put(str, str2);
                if (UMGlobalContext.getInstance().isMainProcess(g)) {
                    strA = u.a().d(UMGlobalContext.getAppContext(g));
                } else {
                    strA = u.a().a(UMGlobalContext.getAppContext(g), jCurrentTimeMillis);
                }
                if (TextUtils.isEmpty(strA)) {
                    strA = f3708f;
                }
                jSONObject.put("__i", strA);
                if (!TextUtils.isEmpty(str3)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(str3);
                        if (jSONObject2.length() > 0) {
                            jSONObject.put(b.ar, jSONObject2);
                        }
                    } catch (JSONException unused) {
                    }
                }
                jSONObject.put(b.ac, 0);
                jSONObject.put("pn", UMGlobalContext.getInstance().getProcessName(g));
                a();
                if (this.h != null && this.h.has(str) && !((Boolean) this.h.get(str)).booleanValue()) {
                    jSONObject.put(b.X, 1);
                    this.h.put(str, true);
                    c(g);
                }
                UMWorkDispatch.sendEvent(g, k.a.f3682a, CoreProtocol.getInstance(g), jSONObject);
                return;
            }
            UMLog.aq(h.l, 0, "\\|");
        } catch (Throwable unused2) {
        }
    }

    private boolean b(String str) {
        if (str == null) {
            return true;
        }
        try {
            if (str.trim().getBytes().length <= 256) {
                return true;
            }
        } catch (Exception unused) {
        }
        MLog.e("value is " + str + ", please check value, illegal");
        return false;
    }

    private boolean b(Map<String, Object> map) {
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        if (!a(entry.getKey())) {
                            UMLog.aq(h.h, 0, "\\|");
                            return false;
                        }
                        if (entry.getValue() == null) {
                            UMLog.aq(h.i, 0, "\\|");
                            return false;
                        }
                        if (entry.getValue() instanceof String) {
                            if (b.aB.equals(entry.getKey())) {
                                if (!c(entry.getValue().toString())) {
                                    UMLog.aq(h.O, 0, "\\|");
                                    return false;
                                }
                            } else if (!b(entry.getValue().toString())) {
                                UMLog.aq(h.j, 0, "\\|");
                                return false;
                            }
                        }
                    }
                    return true;
                }
            } catch (Exception unused) {
                return true;
            }
        }
        UMLog.aq(h.g, 0, "\\|");
        return false;
    }

    public void a(String str, Map<String, Object> map, long j, String str2) {
        String strA;
        try {
            if (!a(str)) {
                UMLog.aq(h.f3668f, 0, "\\|");
                return;
            }
            if (b(map)) {
                if (map.size() > 10) {
                    MLog.e("map size is " + map.size() + ", please check");
                    return;
                }
                if (Arrays.asList(b.aD).contains(str)) {
                    MLog.e("key is " + str + ", please check key, illegal");
                    UMLog.aq(h.f3664b, 0, "\\|");
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                if (j > 0) {
                    jSONObject.put(b.V, j);
                }
                jSONObject.put("__t", g.f3650a);
                ULog.i("befort ekv map, event is " + jSONObject.toString());
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (!Arrays.asList(b.aD).contains(entry.getKey())) {
                        Object value = entry.getValue();
                        if (!(value instanceof String) && !(value instanceof Integer) && !(value instanceof Long) && !(value instanceof Short) && !(value instanceof Float) && !(value instanceof Double)) {
                            if (value.getClass().isArray()) {
                                if (value instanceof int[]) {
                                    int[] iArr = (int[]) value;
                                    if (iArr.length > 10) {
                                        MLog.e("please check key or value, size overlength!");
                                        return;
                                    }
                                    JSONArray jSONArray = new JSONArray();
                                    for (int i : iArr) {
                                        jSONArray.put(i);
                                    }
                                    jSONObject.put(entry.getKey(), jSONArray);
                                } else if (value instanceof double[]) {
                                    double[] dArr = (double[]) value;
                                    if (dArr.length > 10) {
                                        MLog.e("please check key or value, size overlength!");
                                        return;
                                    }
                                    JSONArray jSONArray2 = new JSONArray();
                                    for (double d2 : dArr) {
                                        jSONArray2.put(d2);
                                    }
                                    jSONObject.put(entry.getKey(), jSONArray2);
                                } else if (value instanceof long[]) {
                                    long[] jArr = (long[]) value;
                                    if (jArr.length > 10) {
                                        MLog.e("please check key or value, size overlength!");
                                        return;
                                    }
                                    JSONArray jSONArray3 = new JSONArray();
                                    for (long j2 : jArr) {
                                        jSONArray3.put(j2);
                                    }
                                    jSONObject.put(entry.getKey(), jSONArray3);
                                } else if (value instanceof float[]) {
                                    float[] fArr = (float[]) value;
                                    if (fArr.length > 10) {
                                        MLog.e("please check key or value, size overlength!");
                                        return;
                                    }
                                    JSONArray jSONArray4 = new JSONArray();
                                    for (float f2 : fArr) {
                                        jSONArray4.put(f2);
                                    }
                                    jSONObject.put(entry.getKey(), jSONArray4);
                                } else if (value instanceof short[]) {
                                    short[] sArr = (short[]) value;
                                    if (sArr.length > 10) {
                                        MLog.e("please check key or value, size overlength!");
                                        return;
                                    }
                                    JSONArray jSONArray5 = new JSONArray();
                                    for (short s : sArr) {
                                        jSONArray5.put((int) s);
                                    }
                                    jSONObject.put(entry.getKey(), jSONArray5);
                                } else if (value instanceof String[]) {
                                    String[] strArr = (String[]) value;
                                    if (strArr.length > 10) {
                                        MLog.e("please check key or value, size overlength!");
                                        return;
                                    }
                                    JSONArray jSONArray6 = new JSONArray();
                                    for (int i2 = 0; i2 < strArr.length; i2++) {
                                        if (strArr[i2] == null) {
                                            MLog.e("please check array, null item!");
                                            return;
                                        } else {
                                            if (!b(strArr[i2])) {
                                                return;
                                            }
                                            jSONArray6.put(strArr[i2]);
                                        }
                                    }
                                    jSONObject.put(entry.getKey(), jSONArray6);
                                } else {
                                    MLog.e("please check key or value, illegal type!");
                                    return;
                                }
                            } else {
                                MLog.e("please check key or value, illegal type!");
                                return;
                            }
                        } else {
                            jSONObject.put(entry.getKey(), value);
                        }
                    } else {
                        UMLog.aq(h.f3667e, 0, "\\|");
                        return;
                    }
                }
                if (UMGlobalContext.getInstance().isMainProcess(g)) {
                    strA = u.a().d(UMGlobalContext.getAppContext(g));
                } else {
                    strA = u.a().a(UMGlobalContext.getAppContext(g), jSONObject.getLong("ts"));
                }
                if (TextUtils.isEmpty(strA)) {
                    strA = f3708f;
                }
                jSONObject.put("__i", strA);
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(str2);
                        if (jSONObject2.length() > 0) {
                            jSONObject.put(b.ar, jSONObject2);
                        }
                    } catch (JSONException unused) {
                    }
                }
                jSONObject.put(b.ac, 0);
                jSONObject.put("pn", UMGlobalContext.getInstance().getProcessName(g));
                a();
                if (this.h != null && this.h.has(str) && !((Boolean) this.h.get(str)).booleanValue()) {
                    jSONObject.put(b.X, 1);
                    this.h.put(str, true);
                    c(g);
                }
                ULog.i("----->>>>>ekv event json is " + jSONObject.toString());
                UMWorkDispatch.sendEvent(g, k.a.f3682a, CoreProtocol.getInstance(g), jSONObject);
            }
        } catch (Throwable unused2) {
        }
    }

    public void a(String str, Map<String, Object> map, String str2) {
        try {
            if (a(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(b.V, 0);
                jSONObject.put("__t", g.f3651b);
                ULog.i("befort gkv map, event is " + jSONObject.toString());
                Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
                for (int i = 0; i < 10 && it.hasNext(); i++) {
                    Map.Entry<String, Object> next = it.next();
                    if (!b.X.equals(next.getKey()) && !b.V.equals(next.getKey()) && !"id".equals(next.getKey()) && !"ts".equals(next.getKey())) {
                        Object value = next.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject.put(next.getKey(), value);
                        }
                    }
                }
                String strD = u.a().d(UMGlobalContext.getAppContext(g));
                if (TextUtils.isEmpty(strD)) {
                    strD = f3708f;
                }
                jSONObject.put("__i", strD);
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(str2);
                        if (jSONObject2.length() > 0) {
                            jSONObject.put(b.ar, jSONObject2);
                        }
                    } catch (JSONException unused) {
                    }
                }
                jSONObject.put(b.ac, 0);
                jSONObject.put("pn", UMGlobalContext.getInstance().getProcessName(g));
                ULog.i("----->>>>>gkv event json is " + jSONObject.toString());
                UMWorkDispatch.sendEvent(g, k.a.f3683b, CoreProtocol.getInstance(g), jSONObject);
            }
        } catch (Throwable unused2) {
        }
    }

    private void a() {
        try {
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(g, "track_list", "");
            if (TextUtils.isEmpty(strImprintProperty)) {
                return;
            }
            String[] strArrSplit = strImprintProperty.split("!");
            JSONObject jSONObject = new JSONObject();
            int i = 0;
            if (this.h != null) {
                for (String str : strArrSplit) {
                    String strSubStr = HelperUtils.subStr(str, 128);
                    if (this.h.has(strSubStr)) {
                        jSONObject.put(strSubStr, this.h.get(strSubStr));
                    }
                }
            }
            this.h = new JSONObject();
            if (strArrSplit.length >= 10) {
                while (i < 10) {
                    a(strArrSplit[i], jSONObject);
                    i++;
                }
            } else {
                while (i < strArrSplit.length) {
                    a(strArrSplit[i], jSONObject);
                    i++;
                }
            }
            c(g);
        } catch (Exception unused) {
        }
    }

    private void a(String str, JSONObject jSONObject) {
        String strSubStr = HelperUtils.subStr(str, 128);
        if (jSONObject.has(strSubStr)) {
            a(strSubStr, ((Boolean) jSONObject.get(strSubStr)).booleanValue());
        } else {
            a(strSubStr, false);
        }
    }

    private void a(String str, boolean z) {
        try {
            if (b.X.equals(str) || b.V.equals(str) || "id".equals(str) || "ts".equals(str) || this.h.has(str)) {
                return;
            }
            this.h.put(str, z);
        } catch (Exception unused) {
        }
    }

    public void a(List<String> list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    a();
                    if (this.h == null) {
                        this.h = new JSONObject();
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            if (this.h == null) {
                                this.h = new JSONObject();
                            } else if (this.h.length() >= 5) {
                                break;
                            }
                            String str = list.get(i);
                            if (!TextUtils.isEmpty(str)) {
                                a(HelperUtils.subStr(str, 128), false);
                            }
                        }
                        c(g);
                        return;
                    }
                    if (this.h.length() >= 5) {
                        MLog.d("already setFistLaunchEvent, igone.");
                        return;
                    }
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        if (this.h.length() >= 5) {
                            MLog.d(" add setFistLaunchEvent over.");
                            return;
                        }
                        a(HelperUtils.subStr(list.get(i2), 128), false);
                    }
                    c(g);
                    return;
                }
            } catch (Exception unused) {
                return;
            }
        }
        UMLog.aq(h.aj, 0, "\\|");
    }

    private JSONObject a(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                try {
                    String key = entry.getKey();
                    if (key != null) {
                        String strSubStr = HelperUtils.subStr(key, 128);
                        Object value = entry.getValue();
                        if (value != null) {
                            int i = 0;
                            if (value.getClass().isArray()) {
                                if (value instanceof int[]) {
                                    int[] iArr = (int[]) value;
                                    JSONArray jSONArray = new JSONArray();
                                    while (i < iArr.length) {
                                        jSONArray.put(iArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(strSubStr, jSONArray);
                                } else if (value instanceof double[]) {
                                    double[] dArr = (double[]) value;
                                    JSONArray jSONArray2 = new JSONArray();
                                    while (i < dArr.length) {
                                        jSONArray2.put(dArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(strSubStr, jSONArray2);
                                } else if (value instanceof long[]) {
                                    long[] jArr = (long[]) value;
                                    JSONArray jSONArray3 = new JSONArray();
                                    while (i < jArr.length) {
                                        jSONArray3.put(jArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(strSubStr, jSONArray3);
                                } else if (value instanceof float[]) {
                                    float[] fArr = (float[]) value;
                                    JSONArray jSONArray4 = new JSONArray();
                                    while (i < fArr.length) {
                                        jSONArray4.put(fArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(strSubStr, jSONArray4);
                                } else if (value instanceof short[]) {
                                    short[] sArr = (short[]) value;
                                    JSONArray jSONArray5 = new JSONArray();
                                    while (i < sArr.length) {
                                        jSONArray5.put((int) sArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(strSubStr, jSONArray5);
                                }
                            } else if (value instanceof List) {
                                List list = (List) value;
                                JSONArray jSONArray6 = new JSONArray();
                                while (i < list.size()) {
                                    Object obj = list.get(i);
                                    if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                                        jSONArray6.put(list.get(i));
                                    }
                                    i++;
                                }
                                if (jSONArray6.length() > 0) {
                                    jSONObject.put(strSubStr, jSONArray6);
                                }
                            } else if (value instanceof String) {
                                jSONObject.put(strSubStr, HelperUtils.subStr(value.toString(), 256));
                            } else if (!(value instanceof Long) && !(value instanceof Integer) && !(value instanceof Float) && !(value instanceof Double) && !(value instanceof Short)) {
                                MLog.e("The param has not support type. please check !");
                            } else {
                                jSONObject.put(strSubStr, value);
                            }
                        }
                    }
                } catch (Exception e2) {
                    MLog.e(e2);
                }
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private boolean a(String str) {
        if (str != null) {
            try {
                int length = str.trim().getBytes().length;
                if (length > 0 && length <= 128) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        MLog.e("key is " + str + ", please check key, illegal");
        return false;
    }
}
