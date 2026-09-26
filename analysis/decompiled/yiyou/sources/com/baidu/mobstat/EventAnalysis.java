package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class EventAnalysis {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, a> f3346a = new HashMap();

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f3347a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f3348b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f3349c;

        private a() {
        }
    }

    private void a(Context context, long j, String str, String str2, int i, long j2, long j3, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        DataCore.instance().putEvent(context, getEvent(context, j, str, str2, i, j2, j3, "", "", 0, 0, extraInfo, map, z));
        DataCore.instance().flush(context);
    }

    private static boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str) || new JSONObject().toString().equals(str)) {
            return (TextUtils.isEmpty(str2) || new JSONArray().toString().equals(str2)) ? false : true;
        }
        return true;
    }

    public static void doEventMerge(JSONArray jSONArray, JSONObject jSONObject) {
        String strOptString;
        String strOptString2;
        JSONArray jSONArrayOptJSONArray;
        JSONArray jSONArrayOptJSONArray2;
        int i;
        Config.EventViewType.EDIT.getValue();
        try {
            long jOptLong = jSONObject.optLong("ss");
            String string = jSONObject.getString("i");
            String string2 = jSONObject.getString("l");
            long j = jSONObject.getLong("t") / 3600000;
            String strOptString3 = jSONObject.optString("s");
            int iOptInt = jSONObject.optInt("at");
            String strOptString4 = jSONObject.optString("h");
            if (iOptInt != 3) {
                jSONArrayOptJSONArray = jSONObject.optJSONArray(Config.EVENT_NATIVE_VIEW_HIERARCHY);
                jSONArrayOptJSONArray2 = jSONObject.optJSONArray(Config.EVENT_H5_VIEW_HIERARCHY);
                strOptString = "";
                strOptString2 = strOptString;
            } else {
                strOptString = jSONObject.optString(Config.EVENT_NATIVE_VIEW_HIERARCHY);
                strOptString2 = jSONObject.optString(Config.EVENT_H5_VIEW_HIERARCHY);
                jSONArrayOptJSONArray = null;
                jSONArrayOptJSONArray2 = null;
            }
            String strOptString5 = jSONObject.optString("p");
            String strOptString6 = jSONObject.optString(Config.EVENT_H5_PAGE);
            String strOptString7 = jSONObject.optString(Config.EVENT_VIEW_RES_NAME);
            int iOptInt2 = jSONObject.optInt("v");
            String strOptString8 = jSONObject.optString("ext");
            String strOptString9 = jSONObject.optString(Config.EVENT_ATTR);
            int iOptInt3 = jSONObject.optInt("h5");
            String strOptString10 = jSONObject.optString("sign");
            try {
                i = jSONObject.getInt("d");
            } catch (JSONException unused) {
                i = 0;
            }
            if (i == 0 && !b(strOptString8, strOptString9)) {
                a(jSONArray, jSONObject, jOptLong, string, string2, strOptString3, j, strOptString4, jSONArrayOptJSONArray, jSONArrayOptJSONArray2, strOptString5, strOptString6, strOptString7, iOptInt2, iOptInt, strOptString, strOptString2, iOptInt3, strOptString10);
                return;
            }
            int length = jSONArray.length();
            jSONObject.put("s", "0");
            jSONArray.put(length, jSONObject);
        } catch (JSONException unused2) {
        }
    }

    public static JSONObject getEvent(Context context, long j, String str, String str2, int i, long j2, long j3, String str3, String str4, int i2, int i3, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        return getEvent(context, j, str, str2, i, j2, j3, str3, null, null, str4, null, null, i2, i3, extraInfo, map, "", "", z);
    }

    public void flushEvent(Context context, long j, String str, String str2, int i, long j2, JSONArray jSONArray, JSONArray jSONArray2, String str3, String str4, String str5, Map<String, String> map, boolean z) {
        DataCore.instance().putEvent(context, getEvent(context, j, str, str2, i, j2, 0L, "", jSONArray, jSONArray2, str3, str4, str5, Config.EventViewType.EDIT.getValue(), 2, null, map, "", "", z));
        DataCore.instance().flush(context);
    }

    public void onEvent(Context context, long j, String str, String str2, int i, long j2, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        a(context, j, str, str2, i, j2, 0L, extraInfo, map, z);
    }

    public void onEventDuration(Context context, long j, String str, String str2, long j2, long j3, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        if (j3 <= 0) {
            return;
        }
        a(context, j, str, str2, 1, j2, j3, extraInfo, map, z);
    }

    public void onEventEnd(Context context, long j, String str, String str2, long j2, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        String strA = a(str, str2);
        a aVar = this.f3346a.get(strA);
        if (aVar == null) {
            am.c().b("[WARNING] eventId: " + str + ", with label: " + str2 + " is not started or alread ended");
            return;
        }
        if ((str != null && !str.equals(aVar.f3347a)) || (str2 != null && !str2.equals(aVar.f3348b))) {
            am.c().b("[WARNING] eventId/label pair not match");
            return;
        }
        this.f3346a.remove(strA);
        long j3 = j2 - aVar.f3349c;
        if (j3 < 0) {
            am.c().b("[WARNING] onEventEnd must be invoked after onEventStart");
        }
        onEventDuration(context, j, str, str2, aVar.f3349c, j3, extraInfo, map, z);
    }

    public void onEventStart(Context context, String str, String str2, long j) {
        a aVar = new a();
        aVar.f3349c = j;
        aVar.f3347a = str;
        aVar.f3348b = str2;
        String strA = a(str, str2);
        if (this.f3346a.containsKey(strA)) {
            am.c().b("[WARNING] eventId: " + str + ", with label: " + str2 + " is duplicated, older is removed");
        }
        this.f3346a.put(strA, aVar);
    }

    public static JSONObject getEvent(Context context, long j, String str, String str2, int i, long j2, long j3, String str3, JSONArray jSONArray, JSONArray jSONArray2, String str4, String str5, String str6, int i2, int i3, ExtraInfo extraInfo, Map<String, String> map, String str7, String str8, boolean z) {
        return getEvent(context, j, str, str2, i, j2, j3, str3, jSONArray, jSONArray2, str4, str5, str6, i2, i3, extraInfo, map, str7, str8, z, null, "");
    }

    public void onEvent(Context context, long j, String str, String str2, int i, long j2, String str3, String str4, int i2, boolean z) {
        a(context, j, str, str2, i, j2, 0L, str3, str4, i2);
    }

    public static JSONObject getEvent(Context context, long j, String str, String str2, int i, long j2, long j3, String str3, JSONArray jSONArray, JSONArray jSONArray2, String str4, String str5, String str6, int i2, int i3, ExtraInfo extraInfo, Map<String, String> map, String str7, String str8, boolean z, JSONObject jSONObject, String str9) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("ss", j);
            jSONObject2.put("i", str);
            jSONObject2.put("l", str2);
            jSONObject2.put("c", i);
            jSONObject2.put("t", j2);
            jSONObject2.put("d", j3);
            jSONObject2.put("h", str3);
            if (i3 != 3) {
                jSONObject2.put(Config.EVENT_NATIVE_VIEW_HIERARCHY, jSONArray);
                jSONObject2.put(Config.EVENT_H5_VIEW_HIERARCHY, jSONArray2);
            } else {
                jSONObject2.put(Config.EVENT_NATIVE_VIEW_HIERARCHY, str7);
                jSONObject2.put(Config.EVENT_H5_VIEW_HIERARCHY, str8);
            }
            jSONObject2.put("p", str4);
            jSONObject2.put(Config.EVENT_H5_PAGE, str5);
            jSONObject2.put(Config.EVENT_VIEW_RES_NAME, str6);
            jSONObject2.put("v", i2);
            jSONObject2.put("at", i3);
            jSONObject2.put("h5", z ? 1 : 0);
            if (extraInfo != null && extraInfo.dumpToJson().length() != 0) {
                jSONObject2.put("ext", extraInfo.dumpToJson());
            }
            if (map != null) {
                JSONArray jSONArray3 = new JSONArray();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value) && !a(value, 1024)) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put(Config.APP_KEY, key);
                        jSONObject3.put("v", value);
                        jSONArray3.put(jSONObject3);
                    }
                }
                if (jSONArray3.length() != 0) {
                    jSONObject2.put(Config.EVENT_ATTR, jSONArray3);
                }
            }
            if (jSONObject != null && jSONObject.length() != 0) {
                JSONArray jSONArray4 = new JSONArray();
                jSONArray4.put(jSONObject);
                jSONObject2.put(Config.EVENT_HEAT_POINT, jSONArray4);
            }
            jSONObject2.put("sign", TextUtils.isEmpty(str9) ? "" : str9);
        } catch (Exception unused) {
        }
        return jSONObject2;
    }

    public void onEvent(Context context, long j, String str, String str2, int i, long j2, JSONArray jSONArray, JSONArray jSONArray2, String str3, String str4, String str5, Map<String, String> map, boolean z) {
        flushEvent(context, j, str, str2, i, j2, jSONArray, jSONArray2, str3, str4, str5, map, z);
    }

    private void a(Context context, long j, String str, String str2, int i, long j2, long j3, String str3, String str4, int i2) {
        DataCore.instance().putEvent(context, getEvent(context, j, str, str2, i, j2, j3, str3, str4, i2, 1, null, null, false));
        DataCore.instance().flush(context);
    }

    private String a(String str, String str2) {
        return "__sdk_" + str + "$|$" + str2;
    }

    private static boolean a(String str, int i) {
        int length;
        if (str == null) {
            return false;
        }
        try {
            length = str.getBytes().length;
        } catch (Exception unused) {
            length = 0;
        }
        return length > i;
    }

    /* JADX WARN: Code duplicated, block: B:6:0x001c A[Catch: JSONException -> 0x001f, TRY_LEAVE, TryCatch #7 {JSONException -> 0x001f, blocks: (B:4:0x0016, B:6:0x001c), top: B:149:0x0016 }] */
    /* JADX WARN: Code duplicated, block: B:84:0x0180  */
    /* JADX WARN: Code duplicated, block: B:85:0x0185  */
    private static void a(JSONArray jSONArray, JSONObject jSONObject, long j, String str, String str2, String str3, long j2, String str4, JSONArray jSONArray2, JSONArray jSONArray3, String str5, String str6, String str7, int i, int i2, String str8, String str9, int i3, String str10) {
        int i4;
        int i5;
        int i6;
        int i7;
        JSONArray jSONArrayOptJSONArray;
        String str11;
        JSONArray jSONArray4;
        String strOptString;
        long j3;
        long j4;
        JSONObject jSONObject2 = jSONObject;
        String str12 = "t";
        String str13 = "c";
        int length = jSONArray.length();
        String str14 = "0|";
        String str15 = "s";
        String str16 = "";
        if (str3 != null) {
            try {
                if (str3.equals("")) {
                    jSONObject2.put("s", "0|");
                }
            } catch (JSONException unused) {
            }
        } else {
            jSONObject2.put("s", "0|");
        }
        int i8 = length;
        int i9 = 0;
        while (true) {
            if (i9 >= length) {
                jSONObject = jSONObject2;
                i4 = length;
                i5 = i8;
                break;
            }
            try {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i9);
                long jOptLong = jSONObject3.optLong("ss");
                String string = jSONObject3.getString("i");
                String string2 = jSONObject3.getString("l");
                long j5 = jSONObject3.getLong(str12) / 3600000;
                try {
                    i7 = jSONObject3.getInt("d");
                } catch (JSONException unused2) {
                    i7 = 0;
                }
                try {
                    String strOptString2 = jSONObject3.optString("h");
                    i6 = i8;
                    try {
                        String strOptString3 = jSONObject3.optString("p");
                        String strOptString4 = jSONObject3.optString(Config.EVENT_H5_PAGE);
                        length = length;
                        try {
                            String strOptString5 = jSONObject3.optString(Config.EVENT_VIEW_RES_NAME);
                            i9 = i9;
                            try {
                                int iOptInt = jSONObject3.optInt("v");
                                str12 = str12;
                                try {
                                    int iOptInt2 = jSONObject3.optInt("at");
                                    str16 = str16;
                                    String str17 = str15;
                                    if (iOptInt2 != 3) {
                                        try {
                                            JSONArray jSONArrayOptJSONArray2 = jSONObject3.optJSONArray(Config.EVENT_NATIVE_VIEW_HIERARCHY);
                                            jSONArrayOptJSONArray = jSONObject3.optJSONArray(Config.EVENT_H5_VIEW_HIERARCHY);
                                            str11 = str16;
                                            jSONArray4 = jSONArrayOptJSONArray2;
                                            strOptString = str11;
                                        } catch (JSONException unused3) {
                                            jSONObject = jSONObject;
                                            str13 = str13;
                                        }
                                    } else {
                                        String strOptString6 = jSONObject3.optString(Config.EVENT_NATIVE_VIEW_HIERARCHY);
                                        jSONArrayOptJSONArray = null;
                                        strOptString = jSONObject3.optString(Config.EVENT_H5_VIEW_HIERARCHY);
                                        str11 = strOptString6;
                                        jSONArray4 = null;
                                    }
                                    try {
                                        String strOptString7 = jSONObject3.optString("ext");
                                        String str18 = str11;
                                        String strOptString8 = jSONObject3.optString(Config.EVENT_ATTR);
                                        int iOptInt3 = jSONObject3.optInt("h5");
                                        String strOptString9 = jSONObject3.optString("sign");
                                        if (j5 == j2 && i7 == 0 && !b(strOptString7, strOptString8) && jOptLong == j && string.equals(str) && string2.equals(str2)) {
                                            try {
                                                if (strOptString2.equals(str4) && strOptString3.equals(str5)) {
                                                    try {
                                                        if (!strOptString4.equals(str6) || !a(jSONArray4, jSONArray2) || !a(jSONArrayOptJSONArray, jSONArray3) || !strOptString5.equals(str7) || iOptInt != i) {
                                                            jSONObject = jSONObject;
                                                            str13 = str13;
                                                            str15 = str17;
                                                        } else if (iOptInt2 == i2) {
                                                            try {
                                                                if (str18.equals(str8)) {
                                                                    try {
                                                                        if (!strOptString.equals(str9)) {
                                                                            jSONObject = jSONObject;
                                                                            str13 = str13;
                                                                            str15 = str17;
                                                                        } else if (iOptInt3 == i3) {
                                                                            try {
                                                                                if (strOptString9.equals(str10)) {
                                                                                    jSONObject = jSONObject;
                                                                                    str13 = str13;
                                                                                    try {
                                                                                        int i10 = jSONObject.getInt(str13) + jSONObject3.getInt(str13);
                                                                                        str15 = str17;
                                                                                        try {
                                                                                            String strOptString10 = jSONObject3.optString(str15);
                                                                                            try {
                                                                                                try {
                                                                                                    if (strOptString10 != null) {
                                                                                                        try {
                                                                                                            if (strOptString10.equalsIgnoreCase(str16)) {
                                                                                                            }
                                                                                                            str16 = str16;
                                                                                                            str12 = str12;
                                                                                                            j3 = jSONObject.getLong(str12) - jSONObject3.getLong(str12);
                                                                                                            if (j3 < 0) {
                                                                                                                j4 = 0;
                                                                                                            } else {
                                                                                                                j4 = j3;
                                                                                                            }
                                                                                                            String str19 = strOptString10 + j4 + "|";
                                                                                                            jSONObject3.remove(str13);
                                                                                                            jSONObject3.put(str13, i10);
                                                                                                            jSONObject3.put(str15, str19);
                                                                                                            a(jSONObject3, jSONObject);
                                                                                                            i4 = length;
                                                                                                            i5 = i9;
                                                                                                            break;
                                                                                                        } catch (JSONException unused4) {
                                                                                                            str16 = str16;
                                                                                                        }
                                                                                                    }
                                                                                                    jSONObject3.remove(str13);
                                                                                                    jSONObject3.put(str13, i10);
                                                                                                    jSONObject3.put(str15, str19);
                                                                                                    a(jSONObject3, jSONObject);
                                                                                                    i4 = length;
                                                                                                    i5 = i9;
                                                                                                    break;
                                                                                                } catch (JSONException unused5) {
                                                                                                    i6 = i9;
                                                                                                }
                                                                                                j3 = jSONObject.getLong(str12) - jSONObject3.getLong(str12);
                                                                                                if (j3 < 0) {
                                                                                                    j4 = 0;
                                                                                                } else {
                                                                                                    j4 = j3;
                                                                                                }
                                                                                                String str110 = strOptString10 + j4 + "|";
                                                                                            } catch (JSONException unused6) {
                                                                                                str12 = str12;
                                                                                            }
                                                                                            strOptString10 = str14;
                                                                                            str16 = str16;
                                                                                            str12 = str12;
                                                                                        } catch (JSONException unused7) {
                                                                                            continue;
                                                                                        }
                                                                                    } catch (JSONException unused8) {
                                                                                        str15 = str17;
                                                                                    }
                                                                                } else {
                                                                                    jSONObject = jSONObject;
                                                                                    str13 = str13;
                                                                                    str15 = str17;
                                                                                }
                                                                            } catch (JSONException unused9) {
                                                                            }
                                                                        } else {
                                                                            jSONObject = jSONObject;
                                                                            str13 = str13;
                                                                            str15 = str17;
                                                                        }
                                                                    } catch (JSONException unused10) {
                                                                    }
                                                                } else {
                                                                    jSONObject = jSONObject;
                                                                    str13 = str13;
                                                                    str15 = str17;
                                                                }
                                                            } catch (JSONException unused11) {
                                                            }
                                                        } else {
                                                            jSONObject = jSONObject;
                                                            str13 = str13;
                                                            str15 = str17;
                                                        }
                                                    } catch (JSONException unused12) {
                                                    }
                                                }
                                            } catch (JSONException unused13) {
                                            }
                                            str13 = str13;
                                            i9++;
                                            str15 = str15;
                                            jSONObject2 = jSONObject;
                                            str14 = str14;
                                            i8 = i6;
                                            length = length;
                                            str12 = str12;
                                            str16 = str16;
                                        }
                                    } catch (JSONException unused14) {
                                    }
                                    str13 = str13;
                                    str15 = str17;
                                } catch (JSONException unused15) {
                                    jSONObject = jSONObject2;
                                    str13 = str13;
                                    str15 = str15;
                                    str16 = str16;
                                }
                            } catch (JSONException unused16) {
                                str12 = str12;
                                str13 = str13;
                                str15 = str15;
                                str16 = str16;
                                jSONObject = jSONObject2;
                            }
                        } catch (JSONException unused17) {
                            jSONObject = jSONObject2;
                            str13 = str13;
                            i9++;
                            str15 = str15;
                            jSONObject2 = jSONObject;
                            str14 = str14;
                            i8 = i6;
                            length = length;
                            str12 = str12;
                            str16 = str16;
                        }
                    } catch (JSONException unused18) {
                        length = length;
                    }
                } catch (JSONException unused19) {
                    i6 = i8;
                    jSONObject = jSONObject2;
                    str13 = str13;
                    i9++;
                    str15 = str15;
                    jSONObject2 = jSONObject;
                    str14 = str14;
                    i8 = i6;
                    length = length;
                    str12 = str12;
                    str16 = str16;
                }
            } catch (JSONException unused20) {
                str14 = str14;
            }
            str13 = str13;
            i9++;
            str15 = str15;
            jSONObject2 = jSONObject;
            str14 = str14;
            i8 = i6;
            length = length;
            str12 = str12;
            str16 = str16;
        }
        if (i5 < i4) {
            return;
        }
        try {
            jSONArray.put(i4, jSONObject);
        } catch (JSONException unused21) {
        }
    }

    private static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray jSONArrayOptJSONArray;
        if (jSONObject == null || jSONObject2 == null) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(Config.EVENT_HEAT_POINT);
        if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() != 0) {
            for (int i = 0; i < jSONArrayOptJSONArray2.length(); i++) {
                try {
                    jSONArray.put(jSONArrayOptJSONArray2.getJSONObject(i));
                } catch (Exception unused) {
                }
            }
        }
        if (jSONArray.length() < 10 && (jSONArrayOptJSONArray = jSONObject2.optJSONArray(Config.EVENT_HEAT_POINT)) != null && jSONArrayOptJSONArray.length() != 0) {
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                try {
                    jSONArray.put(jSONArrayOptJSONArray.getJSONObject(i2));
                } catch (Exception unused2) {
                }
            }
        }
        if (jSONArray.length() != 0) {
            try {
                jSONObject.put(Config.EVENT_HEAT_POINT, jSONArray);
            } catch (Exception unused3) {
            }
        }
    }

    private static boolean a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        return (jSONArray == null || jSONArray2 == null || !jSONArray.toString().equals(jSONArray2.toString())) ? false : true;
    }
}
