package com.hs.p.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class TextUtils {
    public static String NOW() {
        try {
            return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        } catch (Exception unused) {
            return "";
        }
    }

    public static String TSTR(long j) {
        try {
            return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Long.valueOf(j));
        } catch (Exception unused) {
            return "";
        }
    }

    public static StringBuilder append(StringBuilder sb, String str) {
        if (sb != null && !empty(str)) {
            sb.append(str);
        }
        return sb;
    }

    public static int compare(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        return str.compareTo(str2);
    }

    public static boolean contains(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.contains(str2);
    }

    public static boolean empty(String str) {
        return str == null || str.length() <= 0;
    }

    public static boolean equals(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        return str.equals(str2);
    }

    public static boolean equalsIgnoreCase(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equalsIgnoreCase(str2);
    }

    public static int parseInt(String str) {
        try {
            if (empty(str)) {
                return 0;
            }
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static List<String> split2List(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (!empty(str)) {
            for (String str3 : str.split(str2)) {
                if (!empty(str3)) {
                    arrayList.add(str3.trim());
                }
            }
        }
        return arrayList;
    }

    public static boolean startsWith(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    public static String tidy(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (char c : str.toCharArray()) {
                if (31 < c && c < 127) {
                    stringBuffer.append(c);
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    public static String[] toArray(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (!empty(str)) {
            for (String str3 : str.split(str2)) {
                if (!empty(str3)) {
                    arrayList.add(str3.trim());
                }
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static <T> String toText(Set<T> set) {
        if (set == null || set.size() <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        for (T t : set) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(";");
            }
            stringBuffer.append(t);
        }
        return stringBuffer.toString();
    }

    public static String trim(String str) {
        return !empty(str) ? str.trim() : str;
    }

    public static String TSTR(String str, long j) {
        try {
            return new SimpleDateFormat(str, Locale.getDefault()).format(Long.valueOf(j));
        } catch (Exception unused) {
            return "";
        }
    }

    public static <T> boolean contains(T[] tArr, T t) {
        if (tArr != null && tArr.length > 0) {
            for (T t2 : tArr) {
                if (t2 == t) {
                    return true;
                }
                if (t != null && t2 != null && t.equals(t2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean empty(byte[] bArr) {
        return bArr == null || bArr.length <= 0;
    }

    public static <T> String toText(T[] tArr) {
        if (tArr == null || tArr.length <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        for (T t : tArr) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(";");
            }
            stringBuffer.append(t);
        }
        return stringBuffer.toString();
    }

    public static <T> boolean empty(T[] tArr) {
        return tArr == null || tArr.length <= 0;
    }
}
