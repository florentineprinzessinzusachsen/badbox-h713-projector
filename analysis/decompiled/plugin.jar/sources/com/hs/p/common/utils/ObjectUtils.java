package com.hs.p.common.utils;

import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class ObjectUtils {
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

    public static boolean empty(Object obj) {
        return length(obj) <= 0;
    }

    public static String join(Object... objArr) {
        return joins(",", objArr);
    }

    public static String joins(String str, Object... objArr) {
        String str2 = (String) notNull(str, "");
        StringBuilder sb = new StringBuilder();
        if (objArr != null) {
            boolean z = true;
            for (Object obj : objArr) {
                if (z) {
                    z = false;
                } else {
                    sb.append(str2);
                }
                sb.append("" + obj);
            }
        }
        return sb.toString();
    }

    public static int length(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length();
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        if (obj instanceof Map) {
            return ((Map) obj).size();
        }
        if (obj instanceof byte[]) {
            return ((byte[]) obj).length;
        }
        if (obj instanceof short[]) {
            return ((short[]) obj).length;
        }
        if (obj instanceof int[]) {
            return ((int[]) obj).length;
        }
        if (obj instanceof long[]) {
            return ((long[]) obj).length;
        }
        if (obj instanceof float[]) {
            return ((float[]) obj).length;
        }
        if (obj instanceof double[]) {
            return ((double[]) obj).length;
        }
        if (obj instanceof boolean[]) {
            return ((boolean[]) obj).length;
        }
        if (obj instanceof char[]) {
            return ((char[]) obj).length;
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length;
        }
        throw new IllegalStateException("unknown type");
    }

    public static <T> T notNull(T t, T t2) {
        return t == null ? t2 : t;
    }

    public static String stringOf(Object obj) {
        return stringOf(obj, "");
    }

    public static String join(String... strArr) {
        return joins(",", strArr);
    }

    public static String joins(String str, String... strArr) {
        String str2 = (String) notNull(str, "");
        StringBuilder sb = new StringBuilder();
        if (strArr != null) {
            boolean z = true;
            for (String str3 : strArr) {
                if (z) {
                    z = false;
                } else {
                    sb.append(str2);
                }
                sb.append("" + ((Object) str3));
            }
        }
        return sb.toString();
    }

    public static String notNull(String str) {
        return (String) notNull(str, "");
    }

    public static String stringOf(Object obj, String str) {
        return obj == null ? str : String.valueOf(obj);
    }
}
