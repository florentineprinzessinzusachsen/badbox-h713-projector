package com.blankj.utilcode.util;

import a.b.d;
import a.b.g;
import android.os.Build;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ObjectUtils {
    private ObjectUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static <T> T getOrDefault(T t, T t2) {
        return t == null ? t2 : t;
    }

    public static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if ((obj instanceof CharSequence) && obj.toString().length() == 0) {
            return true;
        }
        if ((obj instanceof Collection) && ((Collection) obj).isEmpty()) {
            return true;
        }
        if ((obj instanceof Map) && ((Map) obj).isEmpty()) {
            return true;
        }
        if ((obj instanceof g) && ((g) obj).isEmpty()) {
            return true;
        }
        if ((obj instanceof SparseArray) && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if ((obj instanceof SparseBooleanArray) && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if ((obj instanceof SparseIntArray) && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 18 && (obj instanceof SparseLongArray) && ((SparseLongArray) obj).size() == 0) {
            return true;
        }
        if ((obj instanceof d) && ((d) obj).b() == 0) {
            return true;
        }
        return Build.VERSION.SDK_INT >= 16 && (obj instanceof LongSparseArray) && ((LongSparseArray) obj).size() == 0;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static void requireNonNull(Object... objArr) {
        if (objArr == null) {
            throw new NullPointerException();
        }
        for (Object obj : objArr) {
            if (obj == null) {
                throw new NullPointerException();
            }
        }
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return !isEmpty(charSequence);
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isNotEmpty(g gVar) {
        return !isEmpty(gVar);
    }

    public static boolean isNotEmpty(SparseArray sparseArray) {
        return !isEmpty(sparseArray);
    }

    public static boolean isNotEmpty(SparseBooleanArray sparseBooleanArray) {
        return !isEmpty(sparseBooleanArray);
    }

    public static boolean isNotEmpty(SparseIntArray sparseIntArray) {
        return !isEmpty(sparseIntArray);
    }

    public static boolean isNotEmpty(d dVar) {
        return !isEmpty(dVar);
    }

    public static boolean isNotEmpty(SparseLongArray sparseLongArray) {
        return !isEmpty(sparseLongArray);
    }

    public static boolean isNotEmpty(LongSparseArray longSparseArray) {
        return !isEmpty(longSparseArray);
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.toString().length() == 0;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(g gVar) {
        return gVar == null || gVar.isEmpty();
    }

    public static boolean isEmpty(SparseArray sparseArray) {
        return sparseArray == null || sparseArray.size() == 0;
    }

    public static boolean isEmpty(SparseBooleanArray sparseBooleanArray) {
        return sparseBooleanArray == null || sparseBooleanArray.size() == 0;
    }

    public static boolean isEmpty(SparseIntArray sparseIntArray) {
        return sparseIntArray == null || sparseIntArray.size() == 0;
    }

    public static boolean isEmpty(d dVar) {
        return dVar == null || dVar.b() == 0;
    }

    public static boolean isEmpty(SparseLongArray sparseLongArray) {
        return sparseLongArray == null || sparseLongArray.size() == 0;
    }

    public static boolean isEmpty(LongSparseArray longSparseArray) {
        return longSparseArray == null || longSparseArray.size() == 0;
    }
}
