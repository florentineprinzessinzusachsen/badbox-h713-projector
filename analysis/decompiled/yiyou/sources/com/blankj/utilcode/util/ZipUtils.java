package com.blankj.utilcode.util;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class ZipUtils {
    private static final int BUFFER_LEN = 8192;

    private ZipUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static List<String> getComments(String str) {
        return getComments(getFileByPath(str));
    }

    private static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static List<String> getFilesPath(String str) {
        return getFilesPath(getFileByPath(str));
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

    private static boolean unzipChildFile(File file, List<File> list, ZipFile zipFile, ZipEntry zipEntry, String str) throws Throwable {
        BufferedInputStream bufferedInputStream;
        File file2 = new File(file, str);
        list.add(file2);
        if (zipEntry.isDirectory()) {
            return createOrExistsDir(file2);
        }
        if (!createOrExistsFile(file2)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file2));
                try {
                    byte[] bArr = new byte[BUFFER_LEN];
                    while (true) {
                        int i = bufferedInputStream.read(bArr);
                        if (i == -1) {
                            bufferedInputStream.close();
                            bufferedOutputStream2.close();
                            return true;
                        }
                        bufferedOutputStream2.write(bArr, 0, i);
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
        }
    }

    public static List<File> unzipFile(String str, String str2) {
        return unzipFileByKeyword(str, str2, (String) null);
    }

    public static List<File> unzipFileByKeyword(String str, String str2, String str3) {
        return unzipFileByKeyword(getFileByPath(str), getFileByPath(str2), str3);
    }

    public static boolean zipFile(String str, String str2) {
        return zipFile(getFileByPath(str), getFileByPath(str2), (String) null);
    }

    public static boolean zipFiles(Collection<String> collection, String str) {
        return zipFiles(collection, str, (String) null);
    }

    public static List<String> getComments(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            arrayList.add(enumerationEntries.nextElement().getComment());
        }
        zipFile.close();
        return arrayList;
    }

    public static List<String> getFilesPath(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            String strReplace = enumerationEntries.nextElement().getName().replace("\\", "/");
            if (strReplace.contains("../")) {
                Log.e("ZipUtils", "entryName: " + strReplace + " is dangerous!");
                arrayList.add(strReplace);
            } else {
                arrayList.add(strReplace);
            }
        }
        zipFile.close();
        return arrayList;
    }

    public static List<File> unzipFile(File file, File file2) {
        return unzipFileByKeyword(file, file2, (String) null);
    }

    public static List<File> unzipFileByKeyword(File file, File file2, String str) throws IOException {
        if (file == null || file2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        try {
            if (isSpace(str)) {
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    String strReplace = zipEntryNextElement.getName().replace("\\", "/");
                    if (strReplace.contains("../")) {
                        Log.e("ZipUtils", "entryName: " + strReplace + " is dangerous!");
                    } else if (!unzipChildFile(file2, arrayList, zipFile, zipEntryNextElement, strReplace)) {
                        zipFile.close();
                        return arrayList;
                    }
                }
            } else {
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement2 = enumerationEntries.nextElement();
                    String strReplace2 = zipEntryNextElement2.getName().replace("\\", "/");
                    if (strReplace2.contains("../")) {
                        Log.e("ZipUtils", "entryName: " + strReplace2 + " is dangerous!");
                    } else if (strReplace2.contains(str) && !unzipChildFile(file2, arrayList, zipFile, zipEntryNextElement2, strReplace2)) {
                        zipFile.close();
                        return arrayList;
                    }
                }
            }
            zipFile.close();
            return arrayList;
        } catch (Throwable th) {
            zipFile.close();
            throw th;
        }
    }

    public static boolean zipFile(String str, String str2, String str3) {
        return zipFile(getFileByPath(str), getFileByPath(str2), str3);
    }

    public static boolean zipFiles(Collection<String> collection, String str, String str2) throws Throwable {
        ZipOutputStream zipOutputStream;
        if (collection == null || str == null) {
            return false;
        }
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(str));
            try {
                Iterator<String> it = collection.iterator();
                while (it.hasNext()) {
                    if (!zipFile(getFileByPath(it.next()), "", zipOutputStream, str2)) {
                        zipOutputStream.finish();
                        zipOutputStream.close();
                        return false;
                    }
                }
                zipOutputStream.finish();
                zipOutputStream.close();
                return true;
            } catch (Throwable th) {
                th = th;
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            zipOutputStream = null;
        }
    }

    public static boolean zipFile(File file, File file2) {
        return zipFile(file, file2, (String) null);
    }

    public static boolean zipFile(File file, File file2, String str) throws Throwable {
        if (file == null || file2 == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = null;
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(file2));
            try {
                boolean zZipFile = zipFile(file, "", zipOutputStream2, str);
                zipOutputStream2.close();
                return zZipFile;
            } catch (Throwable th) {
                th = th;
                zipOutputStream = zipOutputStream2;
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean zipFile(File file, String str, ZipOutputStream zipOutputStream, String str2) throws Throwable {
        BufferedInputStream bufferedInputStream;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(isSpace(str) ? "" : File.separator);
        sb.append(file.getName());
        String string = sb.toString();
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                for (File file2 : fileArrListFiles) {
                    if (!zipFile(file2, string, zipOutputStream, str2)) {
                        return false;
                    }
                }
                return true;
            }
            ZipEntry zipEntry = new ZipEntry(string + '/');
            zipEntry.setComment(str2);
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.closeEntry();
            return true;
        }
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                ZipEntry zipEntry2 = new ZipEntry(string);
                zipEntry2.setComment(str2);
                zipOutputStream.putNextEntry(zipEntry2);
                byte[] bArr = new byte[BUFFER_LEN];
                while (true) {
                    int i = bufferedInputStream.read(bArr, 0, BUFFER_LEN);
                    if (i != -1) {
                        zipOutputStream.write(bArr, 0, i);
                    } else {
                        zipOutputStream.closeEntry();
                        bufferedInputStream.close();
                        return true;
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
        }
    }

    public static boolean zipFiles(Collection<File> collection, File file) {
        return zipFiles(collection, file, (String) null);
    }

    public static boolean zipFiles(Collection<File> collection, File file, String str) throws Throwable {
        ZipOutputStream zipOutputStream;
        if (collection == null || file == null) {
            return false;
        }
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
            try {
                Iterator<File> it = collection.iterator();
                while (it.hasNext()) {
                    if (!zipFile(it.next(), "", zipOutputStream, str)) {
                        zipOutputStream.finish();
                        zipOutputStream.close();
                        return false;
                    }
                }
                zipOutputStream.finish();
                zipOutputStream.close();
                return true;
            } catch (Throwable th) {
                th = th;
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            zipOutputStream = null;
        }
    }
}
