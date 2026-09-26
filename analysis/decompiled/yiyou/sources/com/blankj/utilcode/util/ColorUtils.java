package com.blankj.utilcode.util;

import android.graphics.Color;
import androidx.core.content.a;

/* JADX INFO: loaded from: classes.dex */
public final class ColorUtils {
    private ColorUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getColor(int i) {
        return a.a(Utils.getApp(), i);
    }

    public static int getRandomColor() {
        return getRandomColor(true);
    }

    public static String int2ArgbString(int i) {
        String hexString = Integer.toHexString(i);
        while (hexString.length() < 6) {
            hexString = "0" + hexString;
        }
        while (hexString.length() < 8) {
            hexString = "f" + hexString;
        }
        return "#" + hexString;
    }

    public static String int2RgbString(int i) {
        String hexString = Integer.toHexString(i & 16777215);
        while (hexString.length() < 6) {
            hexString = "0" + hexString;
        }
        return "#" + hexString;
    }

    public static int setAlphaComponent(int i, float f2) {
        return (i & 16777215) | (((int) ((f2 * 255.0f) + 0.5f)) << 24);
    }

    public static int setAlphaComponent(int i, int i2) {
        return (i & 16777215) | (i2 << 24);
    }

    public static int setBlueComponent(int i, float f2) {
        return (i & (-256)) | ((int) ((f2 * 255.0f) + 0.5f));
    }

    public static int setBlueComponent(int i, int i2) {
        return (i & (-256)) | i2;
    }

    public static int setGreenComponent(int i, float f2) {
        return (i & (-65281)) | (((int) ((f2 * 255.0f) + 0.5f)) << 8);
    }

    public static int setGreenComponent(int i, int i2) {
        return (i & (-65281)) | (i2 << 8);
    }

    public static int setRedComponent(int i, float f2) {
        return (i & (-16711681)) | (((int) ((f2 * 255.0f) + 0.5f)) << 16);
    }

    public static int setRedComponent(int i, int i2) {
        return (i & (-16711681)) | (i2 << 16);
    }

    public static int string2Int(String str) {
        if (str != null) {
            return Color.parseColor(str);
        }
        throw new NullPointerException("Argument 'colorString' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static int getRandomColor(boolean z) {
        return (z ? ((int) (Math.random() * 256.0d)) << 24 : -16777216) | ((int) (Math.random() * 1.6777216E7d));
    }
}
