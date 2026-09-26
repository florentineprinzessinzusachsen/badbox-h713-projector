package com.blankj.utilcode.util;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.baidu.mobstat.Config;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class UriUtils {
    private UriUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Uri file2Uri(File file) {
        if (file == null) {
            throw new NullPointerException("Argument 'file' of type File (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (Build.VERSION.SDK_INT < 24) {
            return Uri.fromFile(file);
        }
        return FileProvider.getUriForFile(Utils.getApp(), Utils.getApp().getPackageName() + ".utilcode.provider", file);
    }

    private static File getFileFromUri(Uri uri, String str) {
        return getFileFromUri(uri, null, null, str);
    }

    public static File uri2File(Uri uri) {
        Uri uri2;
        String str;
        if (uri == null) {
            throw new NullPointerException("Argument 'uri' of type Uri (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Log.d("UriUtils", uri.toString());
        String authority = uri.getAuthority();
        String scheme = uri.getScheme();
        String path = uri.getPath();
        if (Build.VERSION.SDK_INT >= 24 && path != null && path.startsWith("/external/")) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path.replace("/external", ""));
            if (file.exists()) {
                Log.d("UriUtils", uri.toString() + " -> /external");
                return file;
            }
        }
        if ("file".equals(scheme)) {
            if (path != null) {
                return new File(path);
            }
            Log.d("UriUtils", uri.toString() + " parse failed. -> 0");
            return null;
        }
        if (Build.VERSION.SDK_INT < 19 || !DocumentsContract.isDocumentUri(Utils.getApp(), uri)) {
            if ("content".equals(scheme)) {
                return getFileFromUri(uri, "2");
            }
            Log.d("UriUtils", uri.toString() + " parse failed. -> 3");
            return null;
        }
        if (!"com.android.externalstorage.documents".equals(authority)) {
            if ("com.android.providers.downloads.documents".equals(authority)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (!TextUtils.isEmpty(documentId)) {
                    try {
                        return getFileFromUri(ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), "1_1");
                    } catch (NumberFormatException unused) {
                        if (documentId.startsWith("raw:")) {
                            return new File(documentId.substring(4));
                        }
                    }
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_1");
                return null;
            }
            if (!"com.android.providers.media.documents".equals(authority)) {
                if ("content".equals(scheme)) {
                    return getFileFromUri(uri, "1_3");
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_4");
                return null;
            }
            String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(Config.TRACE_TODAY_VISIT_SPLIT);
            String str2 = strArrSplit[0];
            if ("image".equals(str2)) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(str2)) {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else {
                if (!"audio".equals(str2)) {
                    Log.d("UriUtils", uri.toString() + " parse failed. -> 1_2");
                    return null;
                }
                uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return getFileFromUri(uri2, "_id=?", new String[]{strArrSplit[1]}, "1_2");
        }
        String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(Config.TRACE_TODAY_VISIT_SPLIT);
        String str3 = strArrSplit2[0];
        if ("primary".equalsIgnoreCase(str3)) {
            return new File(Environment.getExternalStorageDirectory() + "/" + strArrSplit2[1]);
        }
        StorageManager storageManager = (StorageManager) Utils.getApp().getSystemService("storage");
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Method method2 = cls.getMethod("getUuid", new Class[0]);
            Method method3 = cls.getMethod("getState", new Class[0]);
            Method method4 = cls.getMethod("getPath", new Class[0]);
            Method method5 = cls.getMethod("isPrimary", new Class[0]);
            Method method6 = cls.getMethod("isEmulated", new Class[0]);
            Object objInvoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(objInvoke);
            int i = 0;
            while (i < length) {
                Object obj = Array.get(objInvoke, i);
                Object obj2 = objInvoke;
                if (("mounted".equals(method3.invoke(obj, new Object[0])) || "mounted_ro".equals(method3.invoke(obj, new Object[0]))) && ((!((Boolean) method5.invoke(obj, new Object[0])).booleanValue() || !((Boolean) method6.invoke(obj, new Object[0])).booleanValue()) && (str = (String) method2.invoke(obj, new Object[0])) != null && str.equals(str3))) {
                    return new File(method4.invoke(obj, new Object[0]) + "/" + strArrSplit2[1]);
                }
                i++;
                objInvoke = obj2;
            }
        } catch (Exception e2) {
            Log.d("UriUtils", uri.toString() + " parse failed. " + e2.toString() + " -> 1_0");
        }
        Log.d("UriUtils", uri.toString() + " parse failed. -> 1_0");
        return null;
    }

    private static File getFileFromUri(Uri uri, String str, String[] strArr, String str2) {
        Cursor cursorQuery = Utils.getApp().getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
        try {
            if (cursorQuery == null) {
                Log.d("UriUtils", uri.toString() + " parse failed(cursor is null). -> " + str2);
                return null;
            }
            if (!cursorQuery.moveToFirst()) {
                Log.d("UriUtils", uri.toString() + " parse failed(moveToFirst return false). -> " + str2);
                return null;
            }
            int columnIndex = cursorQuery.getColumnIndex("_data");
            if (columnIndex > -1) {
                return new File(cursorQuery.getString(columnIndex));
            }
            Log.d("UriUtils", uri.toString() + " parse failed(columnIndex: " + columnIndex + " is wrong). -> " + str2);
            return null;
        } catch (Exception unused) {
            Log.d("UriUtils", uri.toString() + " parse failed. -> " + str2);
            return null;
        } finally {
            cursorQuery.close();
        }
    }
}
