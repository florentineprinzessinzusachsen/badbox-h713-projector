package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.blankj.utilcode.constant.TimeConstants;
import com.umeng.commonsdk.proguard.ap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes.dex */
public final class ConvertUtils {
    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.getApp().getResources(), bitmap);
    }

    public static byte[] bits2Bytes(String str) {
        int length = str.length() % 8;
        int length2 = str.length() / 8;
        if (length != 0) {
            while (length < 8) {
                str = "0" + str;
                length++;
            }
            length2++;
        }
        byte[] bArr = new byte[length2];
        for (int i = 0; i < length2; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                bArr[i] = (byte) (bArr[i] << 1);
                bArr[i] = (byte) (bArr[i] | (str.charAt((i * 8) + i2) - '0'));
            }
        }
        return bArr;
    }

    @SuppressLint({"DefaultLocale"})
    public static String byte2FitMemorySize(long j) {
        if (j < 0) {
            return "shouldn't be less than zero!";
        }
        if (j < 1024) {
            return String.format("%.3fB", Double.valueOf(j));
        }
        if (j < 1048576) {
            double d2 = j;
            Double.isNaN(d2);
            return String.format("%.3fKB", Double.valueOf(d2 / 1024.0d));
        }
        if (j < 1073741824) {
            double d3 = j;
            Double.isNaN(d3);
            return String.format("%.3fMB", Double.valueOf(d3 / 1048576.0d));
        }
        double d4 = j;
        Double.isNaN(d4);
        return String.format("%.3fGB", Double.valueOf(d4 / 1.073741824E9d));
    }

    public static double byte2MemorySize(long j, int i) {
        if (j < 0) {
            return -1.0d;
        }
        double d2 = j;
        double d3 = i;
        Double.isNaN(d2);
        Double.isNaN(d3);
        return d2 / d3;
    }

    public static Bitmap bytes2Bitmap(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static String bytes2Bits(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            for (int i = 7; i >= 0; i--) {
                sb.append(((b2 >> i) & 1) == 0 ? '0' : '1');
            }
        }
        return sb.toString();
    }

    public static char[] bytes2Chars(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return cArr;
    }

    public static Drawable bytes2Drawable(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return bitmap2Drawable(bytes2Bitmap(bArr));
    }

    public static String bytes2HexString(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = hexDigits;
            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & ap.m];
        }
        return new String(cArr);
    }

    public static InputStream bytes2InputStream(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new ByteArrayInputStream(bArr);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static OutputStream bytes2OutputStream(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                try {
                    byteArrayOutputStream.write(bArr);
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return byteArrayOutputStream;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream2 = byteArrayOutputStream;
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e6) {
            e = e6;
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            throw th;
        }
    }

    public static byte[] chars2Bytes(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return null;
        }
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    public static int dp2px(float f2) {
        return (int) ((f2 * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmapCreateBitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmapCreateBitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static byte[] drawable2Bytes(Drawable drawable, Bitmap.CompressFormat compressFormat) {
        if (drawable == null) {
            return null;
        }
        return bitmap2Bytes(drawable2Bitmap(drawable), compressFormat);
    }

    private static int hex2Int(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        if (c2 < 'A' || c2 > 'F') {
            throw new IllegalArgumentException();
        }
        return (c2 - 'A') + 10;
    }

    public static byte[] hexString2Bytes(String str) {
        if (isSpace(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            str = "0" + str;
            length++;
        }
        char[] charArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[length >> 1];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((hex2Int(charArray[i]) << 4) | hex2Int(charArray[i + 1]));
        }
        return bArr;
    }

    public static ByteArrayOutputStream input2OutputStream(InputStream inputStream) {
        try {
            if (inputStream == null) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr, 0, 1024);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                }
            }
            return byteArrayOutputStream;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public static byte[] inputStream2Bytes(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return input2OutputStream(inputStream).toByteArray();
    }

    public static String inputStream2String(InputStream inputStream, String str) {
        if (inputStream != null && !isSpace(str)) {
            try {
                ByteArrayOutputStream byteArrayOutputStreamInput2OutputStream = input2OutputStream(inputStream);
                return byteArrayOutputStreamInput2OutputStream == null ? "" : byteArrayOutputStreamInput2OutputStream.toString(str);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static long memorySize2Byte(long j, int i) {
        if (j < 0) {
            return -1L;
        }
        return j * ((long) i);
    }

    @SuppressLint({"DefaultLocale"})
    public static String millis2FitTimeSpan(long j, int i) {
        if (j <= 0 || i <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = {"天", "小时", "分钟", "秒", "毫秒"};
        int[] iArr = {TimeConstants.DAY, 3600000, TimeConstants.MIN, TimeConstants.SEC, 1};
        int iMin = Math.min(i, 5);
        for (int i2 = 0; i2 < iMin; i2++) {
            if (j >= iArr[i2]) {
                long j2 = j / ((long) iArr[i2]);
                j -= ((long) iArr[i2]) * j2;
                sb.append(j2);
                sb.append(strArr[i2]);
            }
        }
        return sb.toString();
    }

    public static long millis2TimeSpan(long j, int i) {
        return j / ((long) i);
    }

    public static byte[] outputStream2Bytes(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return ((ByteArrayOutputStream) outputStream).toByteArray();
    }

    public static String outputStream2String(OutputStream outputStream, String str) {
        if (outputStream != null && !isSpace(str)) {
            try {
                return new String(outputStream2Bytes(outputStream), str);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    public static int px2dp(float f2) {
        return (int) ((f2 / Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2sp(float f2) {
        return (int) ((f2 / Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2px(float f2) {
        return (int) ((f2 * Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static InputStream string2InputStream(String str, String str2) {
        if (str != null && !isSpace(str2)) {
            try {
                return new ByteArrayInputStream(str.getBytes(str2));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static OutputStream string2OutputStream(String str, String str2) {
        if (str != null && !isSpace(str2)) {
            try {
                return bytes2OutputStream(str.getBytes(str2));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static long timeSpan2Millis(long j, int i) {
        return j * ((long) i);
    }

    public static Bitmap view2Bitmap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return bitmapCreateBitmap;
    }

    public ByteArrayInputStream output2InputStream(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return new ByteArrayInputStream(((ByteArrayOutputStream) outputStream).toByteArray());
    }
}
