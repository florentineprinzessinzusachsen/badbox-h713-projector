package com.softwinner.tv.module;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.TvSignalID;

/* JADX INFO: loaded from: classes.dex */
public class BitmapUtils {
    private static final String TAG = "awapi-BitmapUtils";

    private static int clamp(int i, int i2, int i3) {
        if (i < i2) {
            return i2;
        }
        return i > i3 ? i3 : i;
    }

    public static float getMinScaleFactor() {
        return 0.3f;
    }

    private BitmapUtils() {
    }

    private static Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        return config == null ? Bitmap.Config.ARGB_8888 : config;
    }

    public static byte[] fetchNV21(Bitmap bitmap) {
        if (!isValidBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth() & (-2);
        int height = bitmap.getHeight() & (-2);
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[(i * 3) / 2];
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                int i4 = (i2 * width) + i3;
                int i5 = iArr[i4];
                int i6 = (i5 >> 16) & 255;
                int i7 = (i5 >> 8) & 255;
                int i8 = i5 & 255;
                bArr[i4] = (byte) clamp((((((i6 * 66) + (i7 * TvSignalID.SIGNALID_SCART_PAL)) + (i8 * 25)) + 128) >> 8) + 16, 16, 255);
                if (i2 % 2 == 0 && i3 % 2 == 0) {
                    int iClamp = clamp((((((i6 * (-38)) - (i7 * 74)) + (i8 * 112)) + 128) >> 8) + 128, 0, 255);
                    int iClamp2 = clamp((((((i6 * 112) - (i7 * 94)) - (i8 * 18)) + 128) >> 8) + 128, 0, 255);
                    int i9 = ((i2 / 2) * width) + i + i3;
                    bArr[i9] = (byte) iClamp;
                    bArr[i9 + 1] = (byte) iClamp2;
                }
            }
        }
        Log.d(TAG, "fetchNV21 w = " + width + " h = " + height + " size = " + i);
        return bArr;
    }

    public static int[] getBitmapSize(String str, BitmapFactory.Options options) {
        if (options == null) {
            options = new BitmapFactory.Options();
        }
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new int[]{options.outWidth, options.outHeight};
    }

    public static float getMaxScaleFactor(int i, int i2, int i3, int i4) {
        return (i3 > Math.round(((float) i) / 2.2f) || i4 > Math.round(((float) i2) / 2.2f)) ? 2.0f : 3.0f;
    }

    public static Bitmap decodeFile(String str, BitmapFactory.Options options, int i, int i2) {
        if (options == null) {
            options = new BitmapFactory.Options();
        }
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap decodeToBitmap(String str, BitmapFactory.Options options, int i, int i2) {
        return resizeBitmap(decodeFile(str, options, i, i2), i, i2, true);
    }

    public static int calculateInSampleSize(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i2 > i4 || i > i3) {
            int i6 = i2 / 2;
            int i7 = i / 2;
            while (i6 / i5 >= i4 && i7 / i5 >= i3) {
                i5 *= 2;
            }
        }
        Log.d(TAG, "inSampleSize: " + i5);
        return i5;
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int i, int i2, boolean z) {
        if (!isValidBitmap(bitmap)) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= i && height <= i2) {
            Log.d(TAG, "don't resize bitmap");
            return bitmap;
        }
        float fMin = Math.min(i / width, i2 / height);
        int iRound = Math.round(bitmap.getWidth() * fMin);
        int iRound2 = Math.round(bitmap.getHeight() * fMin);
        Log.d(TAG, "resize bitmap, scale: " + fMin + " ,sw: " + iRound + " ,sh: " + iRound2 + " ,ow: " + width + " ,oh: " + height);
        if (iRound == bitmap.getWidth() && iRound2 == bitmap.getHeight()) {
            return bitmap;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iRound, iRound2, getConfig(bitmap));
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.scale(fMin, fMin);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
        if (z) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap createBlankBitmap(int i, int i2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.eraseColor(-16777216);
        return bitmapCreateBitmap;
    }

    public static Bitmap createNinetyAngleBitmap(Bitmap bitmap) {
        if (!isValidBitmap(bitmap)) {
            return bitmap;
        }
        float height = bitmap.getHeight();
        int width = (int) (height / (bitmap.getWidth() / bitmap.getHeight()));
        float width2 = height / bitmap.getWidth();
        float height2 = width / bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(90.0f);
        matrix.postScale(width2, height2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static void cropCenterBitmap(Bitmap bitmap, Bitmap bitmap2) {
        if (isValidBitmap(bitmap) || isValidBitmap(bitmap2)) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int width2 = bitmap2.getWidth();
            int height2 = bitmap2.getHeight();
            Canvas canvas = new Canvas(bitmap);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            int i = (width - width2) / 2;
            int i2 = (height - height2) / 2;
            canvas.drawBitmap(bitmap2, (Rect) null, new RectF(i, i2, width2 + i, height2 + i2), new Paint(6));
        }
    }

    public static Bitmap createTransformBitmap(Bitmap bitmap, float f, float f2, int i, boolean z) {
        if (!isValidBitmap(bitmap)) {
            return bitmap;
        }
        if (i == 0 && Math.abs(f - 1.0f) == 0.0f && Math.abs(f2 - 1.0f) == 0.0f) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = width / 2;
        int i3 = height / 2;
        Log.d(TAG, "transform bitmap, w: " + width + " ,h: " + height + " ,centerX: " + i2 + " ,centerY: " + i3);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, getConfig(bitmap));
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        float f3 = (float) i2;
        float f4 = (float) i3;
        canvas.scale(f2, f2, f3, f4);
        canvas.rotate((float) i, f3, f4);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
        if (z) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static boolean isValidBitmap(Bitmap bitmap) {
        return (bitmap == null || bitmap.isRecycled()) ? false : true;
    }

    public static void recycleBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }
}
