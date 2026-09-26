package com.hs.p.common.id;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public final class CUID {
    private static final String FILE_PATH = "Android/data/.hs/.cuid.v1";
    private static final String KEY_UUID = "persist.hs.cuid.v1";
    private static final String SF_NAME = "hs.cuid.v1";

    private static void close(Closeable... closeableArr) {
        if (closeableArr != null) {
            for (Closeable closeable : closeableArr) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    private static String createAndSaveUuid(Context context) {
        String strGenUuid = genUuid();
        if (!empty(strGenUuid)) {
            putUuid(context, strGenUuid);
        }
        return strGenUuid;
    }

    private static boolean empty(String str) {
        return str == null || str.length() <= 0;
    }

    private static String genUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase(Locale.getDefault());
    }

    private static String getAndSyncUuid(Context context) {
        String stringFromSD = getStringFromSD(newUuidFile());
        if (!empty(stringFromSD)) {
            if (empty(getStringFromSpf(context, KEY_UUID, ""))) {
                putStringToSpf(context, KEY_UUID, stringFromSD);
            }
            return stringFromSD;
        }
        String stringFromSpf = getStringFromSpf(context, KEY_UUID, "");
        if (!empty(stringFromSpf)) {
            putStringToSD(newUuidFile(), stringFromSpf);
        }
        return stringFromSpf;
    }

    public static String getString(Context context) {
        String uuidInternal;
        synchronized (CUID.class) {
            uuidInternal = getUuidInternal(context);
        }
        return uuidInternal;
    }

    private static String getStringFromSD(File file) {
        ByteArrayOutputStream byteArrayOutputStream;
        FileInputStream fileInputStream = null;
        if (file != null) {
            try {
                if (file.exists()) {
                    byte[] bArr = new byte[256];
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        int i = 0;
                        while (true) {
                            try {
                                int i2 = fileInputStream2.read(bArr);
                                if (-1 == i2) {
                                    break;
                                }
                                if (i2 > 0) {
                                    byteArrayOutputStream.write(bArr, 0, i2);
                                    i += i2;
                                    if (i > 256) {
                                        break;
                                    }
                                }
                            } catch (Throwable unused) {
                                fileInputStream = fileInputStream2;
                                close(fileInputStream, byteArrayOutputStream);
                                return "";
                            }
                        }
                        String str = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
                        close(fileInputStream2, byteArrayOutputStream);
                        return str;
                    } catch (Throwable unused2) {
                        byteArrayOutputStream = null;
                    }
                }
            } catch (Throwable unused3) {
                byteArrayOutputStream = null;
            }
            close(fileInputStream, byteArrayOutputStream);
            return "";
        }
        close(null, null);
        return "";
    }

    private static String getStringFromSpf(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(SF_NAME, 0).getString(str, str2);
        } catch (Throwable unused) {
            return str2;
        }
    }

    private static String getUuidInternal(Context context) {
        try {
            String andSyncUuid = getAndSyncUuid(context);
            return empty(andSyncUuid) ? createAndSaveUuid(context) : andSyncUuid;
        } catch (Throwable unused) {
            return "";
        }
    }

    private static File newUuidFile() {
        return new File(Environment.getExternalStorageDirectory(), FILE_PATH);
    }

    private static boolean putStringToSD(File file, String str) {
        FileOutputStream fileOutputStream = null;
        try {
            if (!empty(str)) {
                recreate(file);
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(str.getBytes(StandardCharsets.UTF_8));
                    fileOutputStream2.flush();
                    fileOutputStream = fileOutputStream2;
                } catch (Throwable unused) {
                    fileOutputStream = fileOutputStream2;
                    close(fileOutputStream);
                    return false;
                }
            }
            close(fileOutputStream);
            return true;
        } catch (Throwable unused2) {
        }
    }

    private static boolean putStringToSpf(Context context, String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SF_NAME, 0).edit();
            editorEdit.putString(str, str2);
            return editorEdit.commit();
        } catch (Throwable unused) {
            return false;
        }
    }

    private static void putUuid(Context context, String str) {
        putStringToSpf(context, KEY_UUID, str);
        putStringToSD(newUuidFile(), str);
    }

    private static void recreate(File file) throws Exception {
        if (file.exists()) {
            file.delete();
        } else {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
        }
        file.createNewFile();
        if (!file.exists()) {
            throw new Exception("create file failed");
        }
    }
}
