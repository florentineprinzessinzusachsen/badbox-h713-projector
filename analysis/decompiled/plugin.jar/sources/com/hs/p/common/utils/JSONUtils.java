package com.hs.p.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class JSONUtils {
    public static boolean getBoolean(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str)) {
                    return TextUtils.equals(jSONObject.getString(str), "1");
                }
            } catch (Exception unused) {
            }
        }
        return z;
    }

    public static float getFloat(JSONObject jSONObject, String str, float f) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str)) {
                    return Float.parseFloat(jSONObject.getString(str));
                }
            } catch (Exception unused) {
            }
        }
        return f;
    }

    public static int getInt(JSONObject jSONObject, String str, int i) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str)) {
                    return Integer.parseInt(jSONObject.getString(str));
                }
            } catch (Exception unused) {
            }
        }
        return i;
    }

    public static long getLong(JSONObject jSONObject, String str, long j) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str)) {
                    return Long.parseLong(jSONObject.getString(str));
                }
            } catch (Exception unused) {
            }
        }
        return j;
    }

    public static String getString(JSONObject jSONObject, String str, String str2) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str)) {
                    return jSONObject.getString(str);
                }
            } catch (Exception unused) {
            }
        }
        return str2;
    }

    public static String[] getStringArray(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str)) {
                    ArrayList arrayList = new ArrayList();
                    JSONArray jSONArray = jSONObject.getJSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.getString(i));
                    }
                    return (String[]) arrayList.toArray(new String[0]);
                }
            } catch (Exception unused) {
            }
        }
        return new String[0];
    }

    public static List<String> getStringList(JSONObject jSONObject, String str) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str)) {
                    JSONArray jSONArray = jSONObject.getJSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.getString(i));
                    }
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    public static void put(JSONObject jSONObject, String str, Object obj) {
        String str2;
        if (obj != null) {
            if (obj instanceof String) {
                str2 = (String) obj;
            } else if (obj instanceof Number) {
                str2 = "" + obj;
            } else {
                if (!(obj instanceof Boolean)) {
                    putObject(jSONObject, str, obj);
                    return;
                }
                str2 = ((Boolean) obj).booleanValue() ? "1" : "0";
            }
            putString(jSONObject, str, str2);
        }
    }

    public static <T> void putArray(JSONObject jSONObject, String str, T... tArr) {
        if (jSONObject != null) {
            try {
                if (ObjUtils.empty(tArr)) {
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                for (T t : tArr) {
                    jSONArray.put(t);
                }
                jSONObject.put(str, jSONArray);
            } catch (Exception unused) {
            }
        }
    }

    public static void putBoolean(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject != null) {
            try {
                jSONObject.put(str, z ? "1" : "0");
            } catch (Exception unused) {
            }
        }
    }

    public static <T> void putList(JSONObject jSONObject, String str, List<T> list) {
        if (jSONObject != null) {
            try {
                if (ObjUtils.empty(list)) {
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                jSONObject.put(str, jSONArray);
            } catch (Exception unused) {
            }
        }
    }

    public static void putObject(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject == null || obj == null) {
            return;
        }
        try {
            jSONObject.put(str, obj);
        } catch (Exception unused) {
        }
    }

    public static void putString(JSONObject jSONObject, String str, String str2) {
        if (jSONObject != null) {
            try {
                if (ObjUtils.empty(str2)) {
                    return;
                }
                jSONObject.put(str, str2);
            } catch (Exception unused) {
            }
        }
    }
}
