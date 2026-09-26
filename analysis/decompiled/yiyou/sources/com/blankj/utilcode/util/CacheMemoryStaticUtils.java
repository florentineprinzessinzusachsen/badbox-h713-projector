package com.blankj.utilcode.util;

/* JADX INFO: loaded from: classes.dex */
public final class CacheMemoryStaticUtils {
    private static CacheMemoryUtils sDefaultCacheMemoryUtils;

    public static void clear() {
        clear(getDefaultCacheMemoryUtils());
    }

    public static <T> T get(String str) {
        if (str != null) {
            return (T) get(str, getDefaultCacheMemoryUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static int getCacheCount() {
        return getCacheCount(getDefaultCacheMemoryUtils());
    }

    private static CacheMemoryUtils getDefaultCacheMemoryUtils() {
        CacheMemoryUtils cacheMemoryUtils = sDefaultCacheMemoryUtils;
        return cacheMemoryUtils != null ? cacheMemoryUtils : CacheMemoryUtils.getInstance();
    }

    public static void put(String str, Object obj) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, obj, getDefaultCacheMemoryUtils());
    }

    public static Object remove(String str) {
        if (str != null) {
            return remove(str, getDefaultCacheMemoryUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void setDefaultCacheMemoryUtils(CacheMemoryUtils cacheMemoryUtils) {
        sDefaultCacheMemoryUtils = cacheMemoryUtils;
    }

    public static void clear(CacheMemoryUtils cacheMemoryUtils) {
        if (cacheMemoryUtils == null) {
            throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheMemoryUtils.clear();
    }

    public static int getCacheCount(CacheMemoryUtils cacheMemoryUtils) {
        if (cacheMemoryUtils != null) {
            return cacheMemoryUtils.getCacheCount();
        }
        throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T get(String str, T t) {
        if (str != null) {
            return (T) get(str, t, getDefaultCacheMemoryUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Object obj, int i) {
        if (str != null) {
            put(str, obj, i, getDefaultCacheMemoryUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Object remove(String str, CacheMemoryUtils cacheMemoryUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheMemoryUtils != null) {
            return cacheMemoryUtils.remove(str);
        }
        throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T get(String str, CacheMemoryUtils cacheMemoryUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheMemoryUtils != null) {
            return (T) cacheMemoryUtils.get(str);
        }
        throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Object obj, CacheMemoryUtils cacheMemoryUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheMemoryUtils != null) {
            cacheMemoryUtils.put(str, obj);
            return;
        }
        throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T get(String str, T t, CacheMemoryUtils cacheMemoryUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheMemoryUtils != null) {
            return (T) cacheMemoryUtils.get(str, t);
        }
        throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Object obj, int i, CacheMemoryUtils cacheMemoryUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheMemoryUtils != null) {
            cacheMemoryUtils.put(str, obj, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }
}
