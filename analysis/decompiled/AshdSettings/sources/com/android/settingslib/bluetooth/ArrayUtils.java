package com.android.settingslib.bluetooth;

import android.support.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ArrayUtils {
    public static <T> boolean contains(@Nullable T[] tArr, T t) {
        return indexOf(tArr, t) != -1;
    }

    public static <T> int indexOf(@Nullable T[] tArr, T t) {
        if (tArr == null) {
            return -1;
        }
        for (int i = 0; i < tArr.length; i++) {
            if (Objects.equals(tArr[i], t)) {
                return i;
            }
        }
        return -1;
    }
}
