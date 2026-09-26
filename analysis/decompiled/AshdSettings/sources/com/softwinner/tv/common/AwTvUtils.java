package com.softwinner.tv.common;

import android.content.UriMatcher;
import android.net.Uri;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AwTvUtils {
    private static final boolean DEBUG = false;
    public static final int MATCH_CHANNEL = 2;
    public static final int MATCH_CHANNEL_ID = 3;
    public static final int MATCH_CHANNEL_ID_LOGO = 4;
    public static final int MATCH_PASSTHROUGH_ID = 1;
    public static final int MATCH_PROGRAM = 5;
    public static final int MATCH_PROGRAM_ID = 6;
    public static final int MATCH_WATCHED_PROGRAM = 7;
    public static final int MATCH_WATCHED_PROGRAM_ID = 8;
    public static final int NO_MATCH = -1;
    public static final String TAG = "AwTvUtils";
    private static final UriMatcher sUriMatcher = new UriMatcher(-1);

    static {
        sUriMatcher.addURI("android.media.tv", "passthrough/*", 1);
        sUriMatcher.addURI("android.media.tv", "channel", 2);
        sUriMatcher.addURI("android.media.tv", "channel/#", 3);
        sUriMatcher.addURI("android.media.tv", "channel/#/logo", 4);
        sUriMatcher.addURI("android.media.tv", "program", 5);
        sUriMatcher.addURI("android.media.tv", "program/#", 6);
        sUriMatcher.addURI("android.media.tv", "watched_program", 7);
        sUriMatcher.addURI("android.media.tv", "watched_program/#", 8);
    }

    public static int matchsWhich(Uri uri) {
        return sUriMatcher.match(uri);
    }

    public static String mapToJson(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (!map.isEmpty()) {
            if (str != null && !str.isEmpty()) {
                sb.append("\"");
                sb.append(str);
                sb.append("\":");
            }
            sb.append("{");
            boolean z = false;
            for (String str2 : map.keySet()) {
                if (z) {
                    sb.append(",");
                }
                String str3 = map.get(str2);
                sb.append("\"");
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(str2);
                sb.append("\":");
                if (str3 == null) {
                    str3 = "";
                }
                sb.append(str3);
                z = true;
            }
            sb.append("}");
        }
        return sb.toString();
    }

    public static String mapToJson(Map<String, String> map) {
        return mapToJson(null, map);
    }

    public static Map<String, String> jsonToMap(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                try {
                    map.put(next, jSONObject.get(next).toString());
                } catch (JSONException e) {
                    Log.e(TAG, "Json get fail: [" + next + "]" + e.getMessage());
                }
            }
            return map;
        } catch (JSONException e2) {
            Log.e(TAG, "Json parse fail: [" + str + "]" + e2.getMessage());
            return null;
        }
    }

    public static class TvString {
        public static String toString(String[] strArr) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (strArr != null && strArr.length != 0) {
                int length = strArr.length;
                boolean z = true;
                int i = 0;
                while (i < length) {
                    String str = strArr[i];
                    if (!z) {
                        sb.append(",");
                    }
                    sb.append("\"");
                    sb.append(str);
                    sb.append("\"");
                    i++;
                    z = false;
                }
            }
            sb.append("]");
            return sb.toString();
        }

        public static String toString(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(str == null ? "" : str.toString());
            sb.append("\"");
            return sb.toString();
        }

        public static String fromString(String str) {
            if (str == null) {
                return null;
            }
            return str.replace("\"", "");
        }

        public static String[] fromArrayString(String str) {
            if (str == null) {
                return null;
            }
            return str.replace("[", "").replace("]", "").replace("\"", "").split(",");
        }
    }

    public static boolean isNumericOrPoint(String str) {
        int length = str.length();
        while (true) {
            length--;
            if (length < 0) {
                return true;
            }
            if (!Character.isDigit(str.charAt(length)) && ".".equals(Character.valueOf(str.charAt(length)))) {
                return false;
            }
        }
    }

    public static String[] getDateAndTimeArray(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(j + 0)).split("\\/| |:");
    }
}
