package com.hs.p.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class AssetsUtils {
    public static Bitmap getBitmap(Context context, String str) throws Exception {
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = open(context, str);
            try {
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
                IoUtils.close(inputStreamOpen);
                return bitmapDecodeStream;
            } catch (Throwable th) {
                th = th;
                IoUtils.close(inputStreamOpen);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpen = null;
        }
    }

    public static byte[] getByteArray(Context context, String str) throws Exception {
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = open(context, str);
            try {
                byte[] asByteArray = IoUtils.getAsByteArray(inputStreamOpen);
                IoUtils.close(inputStreamOpen);
                return asByteArray;
            } catch (Throwable th) {
                th = th;
                IoUtils.close(inputStreamOpen);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpen = null;
        }
    }

    public static Drawable getDrawable(Context context, String str) throws Exception {
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = open(context, str);
            try {
                Drawable drawableCreateFromStream = Drawable.createFromStream(inputStreamOpen, str);
                IoUtils.close(inputStreamOpen);
                return drawableCreateFromStream;
            } catch (Throwable th) {
                th = th;
                IoUtils.close(inputStreamOpen);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpen = null;
        }
    }

    public static String getString(Context context, String str) throws Exception {
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = open(context, str);
            try {
                String asString = IoUtils.getAsString(inputStreamOpen, StandardCharsets.UTF_8.name());
                IoUtils.close(inputStreamOpen);
                return asString;
            } catch (Throwable th) {
                th = th;
                IoUtils.close(inputStreamOpen);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpen = null;
        }
    }

    private static InputStream open(Context context, String str) throws IOException {
        if (ObjUtils.empty(str)) {
            throw new IllegalArgumentException("empty name");
        }
        InputStream inputStreamOpen = context.getAssets().open(str);
        if (inputStreamOpen != null) {
            return inputStreamOpen;
        }
        throw new IllegalStateException("open " + str + " failed");
    }
}
