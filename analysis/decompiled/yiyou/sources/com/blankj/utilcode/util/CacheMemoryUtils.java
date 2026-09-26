package com.blankj.utilcode.util;

import a.b.e;
import com.blankj.utilcode.constant.CacheConstants;
import com.blankj.utilcode.constant.TimeConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class CacheMemoryUtils implements CacheConstants {
    private static final Map<String, CacheMemoryUtils> CACHE_MAP = new HashMap();
    private static final int DEFAULT_MAX_COUNT = 256;
    private final String mCacheKey;
    private final e<String, CacheValue> mMemoryCache;

    private static final class CacheValue {
        long dueTime;
        Object value;

        CacheValue(long j, Object obj) {
            this.dueTime = j;
            this.value = obj;
        }
    }

    private CacheMemoryUtils(String str, e<String, CacheValue> eVar) {
        this.mCacheKey = str;
        this.mMemoryCache = eVar;
    }

    public static CacheMemoryUtils getInstance() {
        return getInstance(256);
    }

    public void clear() {
        this.mMemoryCache.a();
    }

    public <T> T get(String str) {
        if (str != null) {
            return (T) get(str, null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public int getCacheCount() {
        return this.mMemoryCache.b();
    }

    public void put(String str, Object obj) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, obj, -1);
    }

    public Object remove(String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        CacheValue cacheValueC = this.mMemoryCache.c(str);
        if (cacheValueC == null) {
            return null;
        }
        return cacheValueC.value;
    }

    public String toString() {
        return this.mCacheKey + "@" + Integer.toHexString(hashCode());
    }

    public static CacheMemoryUtils getInstance(int i) {
        return getInstance(String.valueOf(i), i);
    }

    public static CacheMemoryUtils getInstance(String str, int i) {
        CacheMemoryUtils cacheMemoryUtils = CACHE_MAP.get(str);
        if (cacheMemoryUtils == null) {
            synchronized (CacheMemoryUtils.class) {
                cacheMemoryUtils = CACHE_MAP.get(str);
                if (cacheMemoryUtils == null) {
                    cacheMemoryUtils = new CacheMemoryUtils(str, new e(i));
                    CACHE_MAP.put(str, cacheMemoryUtils);
                }
            }
        }
        return cacheMemoryUtils;
    }

    public <T> T get(String str, T t) {
        if (str != null) {
            CacheValue cacheValueB = this.mMemoryCache.b(str);
            if (cacheValueB == null) {
                return t;
            }
            long j = cacheValueB.dueTime;
            if (j != -1 && j < System.currentTimeMillis()) {
                this.mMemoryCache.c(str);
                return t;
            }
            return (T) cacheValueB.value;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public void put(String str, Object obj, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (obj == null) {
            return;
        }
        this.mMemoryCache.a(str, new CacheValue(i < 0 ? -1L : System.currentTimeMillis() + ((long) (i * TimeConstants.SEC)), obj));
    }
}
