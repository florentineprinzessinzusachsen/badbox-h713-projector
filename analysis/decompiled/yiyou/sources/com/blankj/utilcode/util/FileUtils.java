package com.blankj.utilcode.util;

import android.content.Intent;
import android.net.Uri;
import com.blankj.utilcode.constant.RegexConstants;
import com.umeng.commonsdk.proguard.ap;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes.dex */
public final class FileUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public interface OnReplaceListener {
        boolean onReplace();
    }

    private FileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static String byte2FitMemorySize(long j) {
        if (j < 0) {
            return "shouldn't be less than zero!";
        }
        if (j < 1024) {
            return String.format(Locale.getDefault(), "%.3fB", Double.valueOf(j));
        }
        if (j < 1048576) {
            Locale locale = Locale.getDefault();
            double d2 = j;
            Double.isNaN(d2);
            return String.format(locale, "%.3fKB", Double.valueOf(d2 / 1024.0d));
        }
        if (j < 1073741824) {
            Locale locale2 = Locale.getDefault();
            double d3 = j;
            Double.isNaN(d3);
            return String.format(locale2, "%.3fMB", Double.valueOf(d3 / 1048576.0d));
        }
        Locale locale3 = Locale.getDefault();
        double d4 = j;
        Double.isNaN(d4);
        return String.format(locale3, "%.3fGB", Double.valueOf(d4 / 1.073741824E9d));
    }

    private static String bytes2HexString(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & ap.m];
        }
        return new String(cArr);
    }

    public static boolean copyDir(String str, String str2) {
        return copyDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean copyFile(String str, String str2) {
        return copyFile(getFileByPath(str), getFileByPath(str2));
    }

    private static boolean copyOrMoveDir(File file, File file2, boolean z) {
        return copyOrMoveDir(file, file2, new OnReplaceListener() { // from class: com.blankj.utilcode.util.FileUtils.1
            @Override // com.blankj.utilcode.util.FileUtils.OnReplaceListener
            public boolean onReplace() {
                return true;
            }
        }, z);
    }

    private static boolean copyOrMoveFile(File file, File file2, boolean z) {
        return copyOrMoveFile(file, file2, new OnReplaceListener() { // from class: com.blankj.utilcode.util.FileUtils.2
            @Override // com.blankj.utilcode.util.FileUtils.OnReplaceListener
            public boolean onReplace() {
                return true;
            }
        }, z);
    }

    public static boolean createFileByDeleteOldFile(String str) {
        return createFileByDeleteOldFile(getFileByPath(str));
    }

    public static boolean createOrExistsDir(String str) {
        return createOrExistsDir(getFileByPath(str));
    }

    public static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    public static boolean delete(String str) {
        return delete(getFileByPath(str));
    }

    public static boolean deleteAllInDir(String str) {
        return deleteAllInDir(getFileByPath(str));
    }

    public static boolean deleteDir(String str) {
        return deleteDir(getFileByPath(str));
    }

    public static boolean deleteFile(String str) {
        return deleteFile(getFileByPath(str));
    }

    public static boolean deleteFilesInDir(String str) {
        return deleteFilesInDir(getFileByPath(str));
    }

    public static boolean deleteFilesInDirWithFilter(String str, FileFilter fileFilter) {
        return deleteFilesInDirWithFilter(getFileByPath(str), fileFilter);
    }

    public static long getDirLength(String str) {
        return getDirLength(getFileByPath(str));
    }

    public static String getDirName(File file) {
        return file == null ? "" : getDirName(file.getAbsolutePath());
    }

    public static String getDirSize(String str) {
        return getDirSize(getFileByPath(str));
    }

    public static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static String getFileCharsetSimple(String str) {
        return getFileCharsetSimple(getFileByPath(str));
    }

    public static String getFileExtension(File file) {
        return file == null ? "" : getFileExtension(file.getPath());
    }

    public static long getFileLastModified(String str) {
        return getFileLastModified(getFileByPath(str));
    }

    public static long getFileLength(String str) {
        if (str.matches(RegexConstants.REGEX_URL)) {
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
                httpsURLConnection.setRequestProperty("Accept-Encoding", "identity");
                httpsURLConnection.connect();
                if (httpsURLConnection.getResponseCode() == 200) {
                    return httpsURLConnection.getContentLength();
                }
                return -1L;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return getFileLength(getFileByPath(str));
    }

    public static int getFileLines(String str) {
        return getFileLines(getFileByPath(str));
    }

    public static byte[] getFileMD5(String str) {
        return getFileMD5(getFileByPath(str));
    }

    public static String getFileMD5ToString(String str) {
        return getFileMD5ToString(isSpace(str) ? null : new File(str));
    }

    public static String getFileName(File file) {
        return file == null ? "" : getFileName(file.getAbsolutePath());
    }

    public static String getFileNameNoExtension(File file) {
        return file == null ? "" : getFileNameNoExtension(file.getPath());
    }

    public static String getFileSize(String str) {
        long fileLength = getFileLength(str);
        return fileLength == -1 ? "" : byte2FitMemorySize(fileLength);
    }

    public static boolean isDir(String str) {
        return isDir(getFileByPath(str));
    }

    public static boolean isFile(String str) {
        return isFile(getFileByPath(str));
    }

    public static boolean isFileExists(String str) {
        return isFileExists(getFileByPath(str));
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

    public static List<File> listFilesInDir(String str) {
        return listFilesInDir(str, false);
    }

    public static List<File> listFilesInDirWithFilter(String str, FileFilter fileFilter) {
        return listFilesInDirWithFilter(getFileByPath(str), fileFilter, false);
    }

    public static boolean moveDir(String str, String str2) {
        return moveDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean moveFile(String str, String str2) {
        return moveFile(getFileByPath(str), getFileByPath(str2));
    }

    public static void notifySystemToScan(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        Utils.getApp().sendBroadcast(intent);
    }

    public static boolean rename(String str, String str2) {
        return rename(getFileByPath(str), str2);
    }

    private static boolean writeFileFromIS(File file, InputStream inputStream) throws Throwable {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = inputStream.read(bArr, 0, 8192);
                    if (i == -1) {
                        break;
                    }
                    bufferedOutputStream2.write(bArr, 0, i);
                }
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return true;
            } catch (IOException e4) {
                e = e4;
                bufferedOutputStream = bufferedOutputStream2;
                e.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                return false;
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
                try {
                    inputStream.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
                if (bufferedOutputStream == null) {
                    throw th;
                }
                try {
                    bufferedOutputStream.close();
                    throw th;
                } catch (IOException e8) {
                    e8.printStackTrace();
                    throw th;
                }
            }
        } catch (IOException e9) {
            e = e9;
        }
    }

    public static boolean copyDir(String str, String str2, OnReplaceListener onReplaceListener) {
        return copyDir(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean copyFile(String str, String str2, OnReplaceListener onReplaceListener) {
        return copyFile(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    private static boolean copyOrMoveDir(File file, File file2, OnReplaceListener onReplaceListener, boolean z) {
        if (file == null || file2 == null) {
            return false;
        }
        String str = file.getPath() + File.separator;
        String str2 = file2.getPath() + File.separator;
        if (str2.contains(str) || !file.exists() || !file.isDirectory()) {
            return false;
        }
        if (file2.exists()) {
            if (onReplaceListener != null && !onReplaceListener.onReplace()) {
                return true;
            }
            if (!deleteAllInDir(file2)) {
                return false;
            }
        }
        if (!createOrExistsDir(file2)) {
            return false;
        }
        for (File file3 : file.listFiles()) {
            File file4 = new File(str2 + file3.getName());
            if (file3.isFile()) {
                if (!copyOrMoveFile(file3, file4, onReplaceListener, z)) {
                    return false;
                }
            } else if (file3.isDirectory() && !copyOrMoveDir(file3, file4, onReplaceListener, z)) {
                return false;
            }
        }
        return !z || deleteDir(file);
    }

    private static boolean copyOrMoveFile(File file, File file2, OnReplaceListener onReplaceListener, boolean z) {
        if (file != null && file2 != null && !file.equals(file2) && file.exists() && file.isFile()) {
            if (file2.exists()) {
                if (onReplaceListener != null && !onReplaceListener.onReplace()) {
                    return true;
                }
                if (!file2.delete()) {
                    return false;
                }
            }
            if (!createOrExistsDir(file2.getParentFile())) {
                return false;
            }
            try {
                if (writeFileFromIS(file2, new FileInputStream(file))) {
                    return !z || deleteFile(file);
                }
                return false;
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static boolean createFileByDeleteOldFile(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && !file.delete()) || !createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static boolean createOrExistsFile(File file) {
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

    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        return file.isDirectory() ? deleteDir(file) : deleteFile(file);
    }

    public static boolean deleteAllInDir(File file) {
        return deleteFilesInDirWithFilter(file, new FileFilter() { // from class: com.blankj.utilcode.util.FileUtils.3
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return true;
            }
        });
    }

    public static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    if (!file2.delete()) {
                        return false;
                    }
                } else if (file2.isDirectory() && !deleteDir(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static boolean deleteFilesInDir(File file) {
        return deleteFilesInDirWithFilter(file, new FileFilter() { // from class: com.blankj.utilcode.util.FileUtils.4
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isFile();
            }
        });
    }

    public static boolean deleteFilesInDirWithFilter(File file, FileFilter fileFilter) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                if (fileFilter.accept(file2)) {
                    if (file2.isFile()) {
                        if (!file2.delete()) {
                            return false;
                        }
                    } else if (file2.isDirectory() && !deleteDir(file2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static long getDirLength(File file) {
        if (!isDir(file)) {
            return -1L;
        }
        long dirLength = 0;
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                dirLength += file2.isDirectory() ? getDirLength(file2) : file2.length();
            }
        }
        return dirLength;
    }

    public static String getDirName(String str) {
        int iLastIndexOf;
        return (isSpace(str) || (iLastIndexOf = str.lastIndexOf(File.separator)) == -1) ? "" : str.substring(0, iLastIndexOf + 1);
    }

    public static String getDirSize(File file) {
        long dirLength = getDirLength(file);
        return dirLength == -1 ? "" : byte2FitMemorySize(dirLength);
    }

    public static String getFileCharsetSimple(File file) throws Throwable {
        int i;
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    i = (bufferedInputStream2.read() << 8) + bufferedInputStream2.read();
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (IOException e3) {
                    e = e3;
                    bufferedInputStream = bufferedInputStream2;
                    e.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    i = 0;
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
            }
            if (i == 61371) {
                return "UTF-8";
            }
            if (i != 65279) {
                return i != 65534 ? "GBK" : "Unicode";
            }
            return "UTF-16BE";
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getFileExtension(String str) {
        if (isSpace(str)) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return (iLastIndexOf == -1 || str.lastIndexOf(File.separator) >= iLastIndexOf) ? "" : str.substring(iLastIndexOf + 1);
    }

    public static long getFileLastModified(File file) {
        if (file == null) {
            return -1L;
        }
        return file.lastModified();
    }

    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0033: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:17:0x0032 */
    public static int getFileLines(File file) throws Throwable {
        BufferedInputStream bufferedInputStream;
        int i;
        int i2 = 1;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    try {
                        try {
                            byte[] bArr = new byte[1024];
                            try {
                                if (!LINE_SEP.endsWith(com.android.umanalytics.utils.ShellUtils.COMMAND_LINE_END)) {
                                    while (true) {
                                        int i3 = bufferedInputStream.read(bArr, 0, 1024);
                                        if (i3 == -1) {
                                            break;
                                        }
                                        int i4 = i2;
                                        for (int i5 = 0; i5 < i3; i5++) {
                                            if (bArr[i5] == 13) {
                                                i4++;
                                            }
                                        }
                                        i2 = i4;
                                    }
                                } else {
                                    while (true) {
                                        int i6 = bufferedInputStream.read(bArr, 0, 1024);
                                        if (i6 == -1) {
                                            break;
                                        }
                                        int i7 = i2;
                                        for (int i8 = 0; i8 < i6; i8++) {
                                            if (bArr[i8] == 10) {
                                                i7++;
                                            }
                                        }
                                        i2 = i7;
                                    }
                                }
                                bufferedInputStream.close();
                            } catch (IOException e2) {
                                e = e2;
                                bufferedInputStream2 = bufferedInputStream;
                                i2 = i;
                                e.printStackTrace();
                                if (bufferedInputStream2 != null) {
                                    bufferedInputStream2.close();
                                }
                                return i2;
                            }
                        } catch (IOException e3) {
                            e = e3;
                            bufferedInputStream2 = bufferedInputStream;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            } catch (IOException e6) {
                e = e6;
            }
            return i2;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = bufferedInputStream2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:46:0x003e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0048: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:34:0x0048 */
    public static byte[] getFileMD5(File file) throws Throwable {
        DigestInputStream digestInputStream;
        DigestInputStream digestInputStream2;
        DigestInputStream digestInputStream3 = null;
        try {
            if (file == null) {
                return null;
            }
            try {
                digestInputStream2 = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
                try {
                    while (digestInputStream2.read(new byte[262144]) > 0) {
                    }
                    byte[] bArrDigest = digestInputStream2.getMessageDigest().digest();
                    try {
                        digestInputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return bArrDigest;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    if (digestInputStream2 != null) {
                        try {
                            digestInputStream2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                } catch (NoSuchAlgorithmException e5) {
                    e = e5;
                    e.printStackTrace();
                    if (digestInputStream2 != null) {
                        digestInputStream2.close();
                    }
                    return null;
                }
            } catch (IOException e6) {
                e = e6;
                digestInputStream2 = null;
                e.printStackTrace();
                if (digestInputStream2 != null) {
                    digestInputStream2.close();
                }
                return null;
            } catch (NoSuchAlgorithmException e7) {
                e = e7;
                digestInputStream2 = null;
                e.printStackTrace();
                if (digestInputStream2 != null) {
                    digestInputStream2.close();
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (digestInputStream3 != null) {
                    try {
                        digestInputStream3.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            digestInputStream3 = digestInputStream;
        }
    }

    public static String getFileName(String str) {
        if (isSpace(str)) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(File.separator);
        return iLastIndexOf == -1 ? str : str.substring(iLastIndexOf + 1);
    }

    public static String getFileNameNoExtension(String str) {
        if (isSpace(str)) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(46);
        int iLastIndexOf2 = str.lastIndexOf(File.separator);
        if (iLastIndexOf2 == -1) {
            return iLastIndexOf == -1 ? str : str.substring(0, iLastIndexOf);
        }
        return (iLastIndexOf == -1 || iLastIndexOf2 > iLastIndexOf) ? str.substring(iLastIndexOf2 + 1) : str.substring(iLastIndexOf2 + 1, iLastIndexOf);
    }

    public static boolean isDir(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static boolean isFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static List<File> listFilesInDir(File file) {
        return listFilesInDir(file, false);
    }

    public static List<File> listFilesInDirWithFilter(File file, FileFilter fileFilter) {
        return listFilesInDirWithFilter(file, fileFilter, false);
    }

    public static boolean moveDir(String str, String str2, OnReplaceListener onReplaceListener) {
        return moveDir(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean moveFile(String str, String str2, OnReplaceListener onReplaceListener) {
        return moveFile(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean rename(File file, String str) {
        if (file == null || !file.exists() || isSpace(str)) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        return !file2.exists() && file.renameTo(file2);
    }

    public static boolean copyDir(File file, File file2) {
        return copyOrMoveDir(file, file2, false);
    }

    public static boolean copyFile(File file, File file2) {
        return copyOrMoveFile(file, file2, false);
    }

    public static String getFileMD5ToString(File file) {
        return bytes2HexString(getFileMD5(file));
    }

    public static String getFileSize(File file) {
        long fileLength = getFileLength(file);
        return fileLength == -1 ? "" : byte2FitMemorySize(fileLength);
    }

    public static List<File> listFilesInDir(String str, boolean z) {
        return listFilesInDir(getFileByPath(str), z);
    }

    public static List<File> listFilesInDirWithFilter(String str, FileFilter fileFilter, boolean z) {
        return listFilesInDirWithFilter(getFileByPath(str), fileFilter, z);
    }

    public static boolean moveDir(File file, File file2) {
        return copyOrMoveDir(file, file2, true);
    }

    public static boolean moveFile(File file, File file2) {
        return copyOrMoveFile(file, file2, true);
    }

    public static boolean copyDir(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveDir(file, file2, onReplaceListener, false);
    }

    public static boolean copyFile(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveFile(file, file2, onReplaceListener, false);
    }

    public static List<File> listFilesInDir(File file, boolean z) {
        return listFilesInDirWithFilter(file, new FileFilter() { // from class: com.blankj.utilcode.util.FileUtils.5
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return true;
            }
        }, z);
    }

    public static List<File> listFilesInDirWithFilter(File file, FileFilter fileFilter, boolean z) {
        if (!isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                if (fileFilter.accept(file2)) {
                    arrayList.add(file2);
                }
                if (z && file2.isDirectory()) {
                    arrayList.addAll(listFilesInDirWithFilter(file2, fileFilter, true));
                }
            }
        }
        return arrayList;
    }

    public static boolean moveDir(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveDir(file, file2, onReplaceListener, true);
    }

    public static boolean moveFile(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveFile(file, file2, onReplaceListener, true);
    }

    public static void notifySystemToScan(String str) {
        notifySystemToScan(getFileByPath(str));
    }

    public static long getFileLength(File file) {
        if (isFile(file)) {
            return file.length();
        }
        return -1L;
    }
}
