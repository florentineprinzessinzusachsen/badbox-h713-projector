package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class CacheDoubleStaticUtils {
    private static CacheDoubleUtils sDefaultCacheDoubleUtils;

    public static void clear() {
        clear(getDefaultCacheDoubleUtils());
    }

    public static Bitmap getBitmap(String str) {
        if (str != null) {
            return getBitmap(str, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static byte[] getBytes(String str) {
        if (str != null) {
            return getBytes(str, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static int getCacheDiskCount() {
        return getCacheDiskCount(getDefaultCacheDoubleUtils());
    }

    public static long getCacheDiskSize() {
        return getCacheDiskSize(getDefaultCacheDoubleUtils());
    }

    public static int getCacheMemoryCount() {
        return getCacheMemoryCount(getDefaultCacheDoubleUtils());
    }

    private static CacheDoubleUtils getDefaultCacheDoubleUtils() {
        CacheDoubleUtils cacheDoubleUtils = sDefaultCacheDoubleUtils;
        return cacheDoubleUtils != null ? cacheDoubleUtils : CacheDoubleUtils.getInstance();
    }

    public static Drawable getDrawable(String str) {
        if (str != null) {
            return getDrawable(str, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONArray getJSONArray(String str) {
        if (str != null) {
            return getJSONArray(str, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONObject getJSONObject(String str) {
        if (str != null) {
            return getJSONObject(str, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T getParcelable(String str, Parcelable.Creator<T> creator) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (creator != null) {
            return (T) getParcelable(str, (Parcelable.Creator) creator, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Object getSerializable(String str) {
        if (str != null) {
            return getSerializable(str, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getString(String str) {
        if (str != null) {
            return getString(str, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, byte[] bArr) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, bArr, getDefaultCacheDoubleUtils());
    }

    public static void remove(String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        remove(str, getDefaultCacheDoubleUtils());
    }

    public static void setDefaultCacheDoubleUtils(CacheDoubleUtils cacheDoubleUtils) {
        sDefaultCacheDoubleUtils = cacheDoubleUtils;
    }

    public static void clear(CacheDoubleUtils cacheDoubleUtils) {
        if (cacheDoubleUtils == null) {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDoubleUtils.clear();
    }

    public static int getCacheDiskCount(CacheDoubleUtils cacheDoubleUtils) {
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getCacheDiskCount();
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static long getCacheDiskSize(CacheDoubleUtils cacheDoubleUtils) {
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getCacheDiskSize();
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static int getCacheMemoryCount(CacheDoubleUtils cacheDoubleUtils) {
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getCacheMemoryCount();
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Bitmap getBitmap(String str, Bitmap bitmap) {
        if (str != null) {
            return getBitmap(str, bitmap, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static byte[] getBytes(String str, byte[] bArr) {
        if (str != null) {
            return getBytes(str, bArr, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getDrawable(String str, Drawable drawable) {
        if (str != null) {
            return getDrawable(str, drawable, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONArray getJSONArray(String str, JSONArray jSONArray) {
        if (str != null) {
            return getJSONArray(str, jSONArray, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONObject getJSONObject(String str, JSONObject jSONObject) {
        if (str != null) {
            return getJSONObject(str, jSONObject, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Object getSerializable(String str, Object obj) {
        if (str != null) {
            return getSerializable(str, obj, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getString(String str, String str2) {
        if (str != null) {
            return getString(str, str2, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, byte[] bArr, int i) {
        if (str != null) {
            put(str, bArr, i, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void remove(String str, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.remove(str);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T getParcelable(String str, Parcelable.Creator<T> creator, T t) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (creator != null) {
            return (T) getParcelable(str, creator, t, getDefaultCacheDoubleUtils());
        }
        throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Bitmap getBitmap(String str, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getBitmap(str);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static byte[] getBytes(String str, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getBytes(str);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getDrawable(String str, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getDrawable(str);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONArray getJSONArray(String str, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getJSONArray(str);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONObject getJSONObject(String str, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getJSONObject(str);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Object getSerializable(String str, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getSerializable(str);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getString(String str, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getString(str);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, String str2) {
        if (str != null) {
            put(str, str2, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Bitmap getBitmap(String str, Bitmap bitmap, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getBitmap(str, bitmap);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static byte[] getBytes(String str, byte[] bArr, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getBytes(str, bArr);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getDrawable(String str, Drawable drawable, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getDrawable(str, drawable);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONArray getJSONArray(String str, JSONArray jSONArray, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getJSONArray(str, jSONArray);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T getParcelable(String str, Parcelable.Creator<T> creator, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (creator == null) {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return (T) cacheDoubleUtils.getParcelable(str, creator);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Object getSerializable(String str, Object obj, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getSerializable(str, obj);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getString(String str, String str2, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getString(str, str2);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, String str2, int i) {
        if (str != null) {
            put(str, str2, i, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONObject getJSONObject(String str, JSONObject jSONObject, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return cacheDoubleUtils.getJSONObject(str, jSONObject);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONObject jSONObject) {
        if (str != null) {
            put(str, jSONObject, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T getParcelable(String str, Parcelable.Creator<T> creator, T t, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (creator == null) {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            return (T) cacheDoubleUtils.getParcelable(str, creator, t);
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONObject jSONObject, int i) {
        if (str != null) {
            put(str, jSONObject, i, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONArray jSONArray) {
        if (str != null) {
            put(str, jSONArray, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONArray jSONArray, int i) {
        if (str != null) {
            put(str, jSONArray, i, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Bitmap bitmap) {
        if (str != null) {
            put(str, bitmap, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Bitmap bitmap, int i) {
        if (str != null) {
            put(str, bitmap, i, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Drawable drawable) {
        if (str != null) {
            put(str, drawable, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Drawable drawable, int i) {
        if (str != null) {
            put(str, drawable, i, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Parcelable parcelable) {
        if (str != null) {
            put(str, parcelable, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Parcelable parcelable, int i) {
        if (str != null) {
            put(str, parcelable, i, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Serializable serializable) {
        if (str != null) {
            put(str, serializable, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Serializable serializable, int i) {
        if (str != null) {
            put(str, serializable, i, getDefaultCacheDoubleUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, byte[] bArr, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, bArr);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, byte[] bArr, int i, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, bArr, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, String str2, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, str2);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, String str2, int i, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, str2, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONObject jSONObject, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, jSONObject);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONObject jSONObject, int i, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, jSONObject, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONArray jSONArray, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, jSONArray);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONArray jSONArray, int i, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, jSONArray, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Bitmap bitmap, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, bitmap);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Bitmap bitmap, int i, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, bitmap, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Drawable drawable, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, drawable);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Drawable drawable, int i, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, drawable, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Parcelable parcelable, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, parcelable);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Parcelable parcelable, int i, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, parcelable, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Serializable serializable, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, serializable);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Serializable serializable, int i, CacheDoubleUtils cacheDoubleUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDoubleUtils != null) {
            cacheDoubleUtils.put(str, serializable, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }
}
