package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class CacheDiskStaticUtils {
    private static CacheDiskUtils sDefaultCacheDiskUtils;

    public static boolean clear() {
        return clear(getDefaultCacheDiskUtils());
    }

    public static Bitmap getBitmap(String str) {
        if (str != null) {
            return getBitmap(str, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static byte[] getBytes(String str) {
        if (str != null) {
            return getBytes(str, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static int getCacheCount() {
        return getCacheCount(getDefaultCacheDiskUtils());
    }

    public static long getCacheSize() {
        return getCacheSize(getDefaultCacheDiskUtils());
    }

    private static CacheDiskUtils getDefaultCacheDiskUtils() {
        CacheDiskUtils cacheDiskUtils = sDefaultCacheDiskUtils;
        return cacheDiskUtils != null ? cacheDiskUtils : CacheDiskUtils.getInstance();
    }

    public static Drawable getDrawable(String str) {
        if (str != null) {
            return getDrawable(str, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONArray getJSONArray(String str) {
        if (str != null) {
            return getJSONArray(str, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONObject getJSONObject(String str) {
        if (str != null) {
            return getJSONObject(str, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T getParcelable(String str, Parcelable.Creator<T> creator) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (creator != null) {
            return (T) getParcelable(str, (Parcelable.Creator) creator, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Object getSerializable(String str) {
        if (str != null) {
            return getSerializable(str, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getString(String str) {
        if (str != null) {
            return getString(str, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, byte[] bArr) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, bArr, getDefaultCacheDiskUtils());
    }

    public static boolean remove(String str) {
        if (str != null) {
            return remove(str, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void setDefaultCacheDiskUtils(CacheDiskUtils cacheDiskUtils) {
        sDefaultCacheDiskUtils = cacheDiskUtils;
    }

    public static boolean clear(CacheDiskUtils cacheDiskUtils) {
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.clear();
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static int getCacheCount(CacheDiskUtils cacheDiskUtils) {
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getCacheCount();
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static long getCacheSize(CacheDiskUtils cacheDiskUtils) {
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getCacheSize();
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Bitmap getBitmap(String str, Bitmap bitmap) {
        if (str != null) {
            return getBitmap(str, bitmap, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static byte[] getBytes(String str, byte[] bArr) {
        if (str != null) {
            return getBytes(str, bArr, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getDrawable(String str, Drawable drawable) {
        if (str != null) {
            return getDrawable(str, drawable, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONArray getJSONArray(String str, JSONArray jSONArray) {
        if (str != null) {
            return getJSONArray(str, jSONArray, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONObject getJSONObject(String str, JSONObject jSONObject) {
        if (str != null) {
            return getJSONObject(str, jSONObject, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Object getSerializable(String str, Object obj) {
        if (str != null) {
            return getSerializable(str, obj, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getString(String str, String str2) {
        if (str != null) {
            return getString(str, str2, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, byte[] bArr, int i) {
        if (str != null) {
            put(str, bArr, i, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean remove(String str, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.remove(str);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T getParcelable(String str, Parcelable.Creator<T> creator, T t) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (creator != null) {
            return (T) getParcelable(str, creator, t, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Bitmap getBitmap(String str, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getBitmap(str);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static byte[] getBytes(String str, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getBytes(str);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getDrawable(String str, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getDrawable(str);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONArray getJSONArray(String str, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getJSONArray(str);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONObject getJSONObject(String str, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getJSONObject(str);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Object getSerializable(String str, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getSerializable(str);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getString(String str, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getString(str);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, String str2) {
        if (str != null) {
            put(str, str2, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Bitmap getBitmap(String str, Bitmap bitmap, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getBitmap(str, bitmap);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static byte[] getBytes(String str, byte[] bArr, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getBytes(str, bArr);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getDrawable(String str, Drawable drawable, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getDrawable(str, drawable);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONArray getJSONArray(String str, JSONArray jSONArray, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getJSONArray(str, jSONArray);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONObject getJSONObject(String str, JSONObject jSONObject, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getJSONObject(str, jSONObject);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T getParcelable(String str, Parcelable.Creator<T> creator, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (creator == null) {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return (T) cacheDiskUtils.getParcelable(str, creator);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Object getSerializable(String str, Object obj, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getSerializable(str, obj);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getString(String str, String str2, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils.getString(str, str2);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, String str2, int i) {
        if (str != null) {
            put(str, str2, i, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONObject jSONObject) {
        if (str != null) {
            put(str, jSONObject, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T getParcelable(String str, Parcelable.Creator<T> creator, T t, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (creator == null) {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            return (T) cacheDiskUtils.getParcelable(str, creator, t);
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONObject jSONObject, int i) {
        if (str != null) {
            put(str, jSONObject, i, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONArray jSONArray) {
        if (str != null) {
            put(str, jSONArray, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONArray jSONArray, int i) {
        if (str != null) {
            put(str, jSONArray, i, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Bitmap bitmap) {
        if (str != null) {
            put(str, bitmap, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Bitmap bitmap, int i) {
        if (str != null) {
            put(str, bitmap, i, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Drawable drawable) {
        if (str != null) {
            put(str, drawable, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Drawable drawable, int i) {
        if (str != null) {
            put(str, drawable, i, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Parcelable parcelable) {
        if (str != null) {
            put(str, parcelable, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Parcelable parcelable, int i) {
        if (str != null) {
            put(str, parcelable, i, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Serializable serializable) {
        if (str != null) {
            put(str, serializable, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Serializable serializable, int i) {
        if (str != null) {
            put(str, serializable, i, getDefaultCacheDiskUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, byte[] bArr, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, bArr);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, byte[] bArr, int i, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, bArr, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, String str2, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, str2);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, String str2, int i, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, str2, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONObject jSONObject, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, jSONObject);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONObject jSONObject, int i, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, jSONObject, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONArray jSONArray, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, jSONArray);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, JSONArray jSONArray, int i, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, jSONArray, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Bitmap bitmap, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, bitmap);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Bitmap bitmap, int i, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, bitmap, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Drawable drawable, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, drawable);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Drawable drawable, int i, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, drawable, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Parcelable parcelable, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, parcelable);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Parcelable parcelable, int i, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, parcelable, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Serializable serializable, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, serializable);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(String str, Serializable serializable, int i, CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils != null) {
            cacheDiskUtils.put(str, serializable, i);
            return;
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }
}
