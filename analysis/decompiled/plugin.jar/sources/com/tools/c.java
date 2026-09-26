package com.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.anlytics.plug.ParserUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f49a = "ParserUtils_PkgDexUtils";

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static void b(String str) {
        String str2;
        String str3;
        try {
            if (c(str)) {
                str2 = " find " + str;
            } else {
                str2 = "don't find " + str;
            }
            Log.d(f49a, str2);
            PackageManager packageManager = ParserUtils.getContext().getPackageManager();
            int applicationEnabledSetting = packageManager.getApplicationEnabledSetting(str);
            if (applicationEnabledSetting == 2 || applicationEnabledSetting == 3 || applicationEnabledSetting == 4) {
                packageManager.setApplicationEnabledSetting("com.android.gallery3d", 1, 0);
                str3 = "已自动恢复被禁用的 gallery3d";
            } else {
                str3 = "dot neet gallery3d";
            }
            Log.d(f49a, str3);
        } catch (Exception e) {
            Log.e(f49a, "恢复 gallery3d 失败", e);
        }
    }

    public static boolean c(String str) {
        if (str != null && !"".equals(str)) {
            try {
                ParserUtils.getContext().getPackageManager().getApplicationInfo(str, com.cloudmedia.tv.server.a.l.r);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                return false;
            }
        }
        Log.i(f49a, str + " is not Exist!");
        return false;
    }

    public static boolean d(String str) {
        try {
            if ((ParserUtils.getContext().getPackageManager().getPackageInfo(str, 0).applicationInfo.flags & 1) <= 0) {
                return false;
            }
            Log.i(f49a, str + " is SystemApp!");
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static File e(Context context, String str) {
        try {
            ZipFile zipFile = new ZipFile(new File(str));
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            String string = null;
            while (true) {
                if (!enumerationEntries.hasMoreElements()) {
                    break;
                }
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                if (!zipEntryNextElement.isDirectory() && zipEntryNextElement.getName().endsWith(".apk")) {
                    if (!"base.apk".equals(zipEntryNextElement.getName()) && zipEntryNextElement.getName().startsWith("split_")) {
                        if (string == null) {
                            string = zipEntryNextElement.getName();
                        }
                    }
                    string = zipEntryNextElement.getName();
                    break;
                }
                if (!zipEntryNextElement.isDirectory() && zipEntryNextElement.getName().endsWith("manifest.json")) {
                    File file = new File(context.getCacheDir(), "manifest.json");
                    try {
                        try {
                            InputStream inputStream = zipFile.getInputStream(zipFile.getEntry(zipEntryNextElement.getName()));
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            byte[] bArr = new byte[com.cloudmedia.tv.server.a.l.r];
                            while (true) {
                                int i = inputStream.read(bArr);
                                if (i == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, i);
                            }
                            f.g();
                            String strX = f.x(file);
                            Log.i(f49a, "str=" + strX);
                            JSONArray jSONArray = new JSONObject(strX).getJSONArray("split_apks");
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                                if ("base".equals(jSONObject.getString("id"))) {
                                    string = jSONObject.getString("file");
                                    Log.i(f49a, "mainApkName=" + string);
                                    break;
                                }
                            }
                            if (file.exists()) {
                                file.delete();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (file.exists()) {
                            }
                        }
                    } catch (Throwable th) {
                        if (file.exists()) {
                            file.delete();
                        }
                        throw th;
                    }
                }
            }
            if (string == null) {
                Log.e("XAPK", "No APK found in XAPK");
                return null;
            }
            File file2 = new File(context.getCacheDir(), "extracted_app.apk");
            InputStream inputStream2 = zipFile.getInputStream(zipFile.getEntry(string));
            FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
            byte[] bArr2 = new byte[com.cloudmedia.tv.server.a.l.r];
            while (true) {
                int i3 = inputStream2.read(bArr2);
                if (i3 == -1) {
                    fileOutputStream2.close();
                    inputStream2.close();
                    zipFile.close();
                    return file2;
                }
                fileOutputStream2.write(bArr2, 0, i3);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int f(String str) {
        try {
            Signature signature = ParserUtils.getContext().getPackageManager().getPackageInfo(str, 64).signatures[0];
            Log.i("test", "hashCode : " + signature.hashCode());
            return signature.hashCode();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String g(String str, String str2) {
        Signature[] signatureArr;
        File fileE = null;
        try {
            if (str.endsWith(".xapk") || str.endsWith(".zip")) {
                fileE = e(ParserUtils.getContext(), str);
                if (fileE == null) {
                    return "";
                }
                str = fileE.getPath();
            }
            PackageInfo packageArchiveInfo = ParserUtils.getContext().getPackageManager().getPackageArchiveInfo(str, 64);
            if (packageArchiveInfo == null || (signatureArr = packageArchiveInfo.signatures) == null || signatureArr.length <= 0) {
                return "";
            }
            byte[] bArrDigest = MessageDigest.getInstance(str2).digest(signatureArr[0].toByteArray());
            StringBuilder sb = new StringBuilder();
            for (byte b2 : bArrDigest) {
                sb.append(String.format("%02X", Byte.valueOf(b2)));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileE != null && fileE.exists()) {
                fileE.delete();
            }
        }
        return "";
    }

    public static String h(Context context) {
        String strI = i(context);
        if (strI == null) {
            return "TEST";
        }
        if (strI.equals("29:B3:1F:E8:E1:6E:14:7E:AB:7A:E1:27:37:CA:A1:AD:76:14:69:46")) {
            Log.i("hello", "Channel=TEST");
            return "TEST";
        }
        if (strI.equals("CD:B1:B7:56:85:FE:16:C2:7E:4C:45:59:54:43:AC:B0:1F:68:40:91")) {
            Log.i("hello", "Channel=ASOS");
            return "ASOS";
        }
        if (strI.equals("6E:40:BA:7F:F7:90:7D:40:C2:F1:6D:12:E4:63:E5:E4:A3:F1:5A:01")) {
            Log.i("hello", "Channel=YIUI");
            return "YIUI";
        }
        Log.i("hello", "Channel=OTHER");
        return "OTHER";
    }

    public static String i(Context context) {
        try {
            return a(MessageDigest.getInstance("SHA1").digest(((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String j(Context context, String str) {
        try {
            return a(MessageDigest.getInstance("SHA1").digest(((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String k(Context context, String str, String str2) {
        String str3;
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
            if (signatureArr == null || signatureArr.length <= 0) {
                return null;
            }
            byte[] bArrDigest = MessageDigest.getInstance(str2).digest(signatureArr[0].toByteArray());
            StringBuilder sb = new StringBuilder();
            for (byte b2 : bArrDigest) {
                sb.append(String.format("%02X", Byte.valueOf(b2)));
            }
            return sb.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e = e;
            str3 = "Package not found: " + str;
            Log.e("SignatureUtils", str3, e);
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
            str3 = "Algorithm not supported: " + str2;
            Log.e("SignatureUtils", str3, e);
            return null;
        }
    }

    public static int m(String str) {
        try {
            PackageInfo packageArchiveInfo = ParserUtils.getContext().getPackageManager().getPackageArchiveInfo(str, 65);
            if (packageArchiveInfo == null) {
                return -1;
            }
            Signature signature = packageArchiveInfo.signatures[0];
            Log.i("test", "hashCode : " + signature.hashCode());
            return signature.hashCode();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int n(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return -1;
            }
            return ParserUtils.getContext().getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void o() {
        try {
            String str = "/data/data/" + ParserUtils.getContext().getPackageName();
            Runtime.getRuntime().exec("chmod 777 " + str + " \n");
            Runtime.getRuntime().exec("chmod 777 " + str + "/files \n");
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("/files/apps");
            File file = new File(sb.toString());
            if (!file.exists()) {
                file.mkdir();
            }
            Runtime.getRuntime().exec("chmod 777 " + str + "/files/apps \n");
        } catch (IOException unused) {
        }
    }

    public static void p() {
        try {
            Log.i(f49a, "initkonkaPath\n");
            Runtime.getRuntime().exec("chmod 777 /data/misc/konka \n");
            Runtime.getRuntime().exec("chmod 777 /data/misc/konka/AdBoot \n");
            Runtime.getRuntime().exec("chmod 777 /data/misc/konka/AdBoot/AdBootMedia \n");
            Runtime.getRuntime().exec("chmod 777 /data/misc/konka/AdBoot/AdBootMedia/bootvideo.ts \n");
        } catch (IOException unused) {
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0096 A[Catch: IOException -> 0x0092, TRY_LEAVE, TryCatch #5 {IOException -> 0x0092, blocks: (B:36:0x008e, B:40:0x0096), top: B:72:0x008e }] */
    /* JADX WARN: Code duplicated, block: B:47:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:61:0x00ef A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:62:0x00f1 A[Catch: IOException -> 0x00ed, TRY_LEAVE, TryCatch #7 {IOException -> 0x00ed, blocks: (B:58:0x00e9, B:62:0x00f1), top: B:74:0x00e9 }] */
    /* JADX WARN: Code duplicated, block: B:66:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:74:0x00e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:? A[SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0067, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0068, code lost:
    
        r5.printStackTrace();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v16, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.ProcessBuilder] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean q(java.lang.String r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tools.c.q(java.lang.String):boolean");
    }

    private static int r(Context context, List<File> list) {
        char c = 2;
        if (list == null || list.isEmpty()) {
            Log.e(f49a, "No APK files to install");
            return 2;
        }
        try {
            int i = 7;
            char c2 = 4;
            char c3 = 5;
            Process processStart = new ProcessBuilder("pm", "install-create", "-i", context.getPackageName(), "--user", "0", "-r").start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processStart.getInputStream()));
            String strSubstring = null;
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                Log.d(f49a, "install-create output: " + line);
                if (line.contains("Success")) {
                    int iIndexOf = line.indexOf("[") + 1;
                    int iIndexOf2 = line.indexOf("]");
                    if (iIndexOf > 0 && iIndexOf2 > iIndexOf) {
                        strSubstring = line.substring(iIndexOf, iIndexOf2);
                    }
                }
            }
            bufferedReader.close();
            processStart.waitFor();
            if (strSubstring == null) {
                Log.e(f49a, "Failed to create install session");
                return 2;
            }
            Log.i(f49a, "Created install session: " + strSubstring);
            for (File file : list) {
                String strReplace = file.getName().replace(".apk", "");
                if (!strReplace.contains(".x86") && !strReplace.contains(".arm64_v8a")) {
                    String[] strArr = new String[i];
                    strArr[0] = "pm";
                    strArr[1] = "install-write";
                    strArr[c] = "-S";
                    strArr[3] = String.valueOf(file.length());
                    strArr[c2] = strSubstring;
                    strArr[c3] = strReplace;
                    strArr[6] = file.getAbsolutePath();
                    Log.i(f49a, "Writing APK to session: " + file.getName());
                    Process processStart2 = new ProcessBuilder(strArr).start();
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(processStart2.getInputStream()));
                    BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(processStart2.getErrorStream()));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line2 = bufferedReader2.readLine();
                        if (line2 == null) {
                            break;
                        }
                        sb.append(line2);
                        Log.d(f49a, "install-write output: " + line2);
                    }
                    while (true) {
                        String line3 = bufferedReader3.readLine();
                        if (line3 == null) {
                            break;
                        }
                        Log.e(f49a, "install-write error: " + line3);
                    }
                    bufferedReader2.close();
                    bufferedReader3.close();
                    if (processStart2.waitFor() != 0 || !sb.toString().contains("Success")) {
                        Log.e(f49a, "Failed to write APK: " + file.getName());
                        new ProcessBuilder("pm", "install-abandon", strSubstring).start().waitFor();
                        return 2;
                    }
                }
                c = 2;
                i = 7;
                c2 = 4;
                c3 = 5;
            }
            Log.i(f49a, "All APKs written to session, committing...");
            Process processStart3 = new ProcessBuilder("pm", "install-commit", strSubstring).start();
            BufferedReader bufferedReader4 = new BufferedReader(new InputStreamReader(processStart3.getInputStream()));
            BufferedReader bufferedReader5 = new BufferedReader(new InputStreamReader(processStart3.getErrorStream()));
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                String line4 = bufferedReader4.readLine();
                if (line4 == null) {
                    break;
                }
                sb2.append(line4);
                Log.d(f49a, "install-commit output: " + line4);
            }
            while (true) {
                String line5 = bufferedReader5.readLine();
                if (line5 == null) {
                    break;
                }
                Log.e(f49a, "install-commit error: " + line5);
            }
            bufferedReader4.close();
            bufferedReader5.close();
            if (processStart3.waitFor() == 0 && sb2.toString().contains("Success")) {
                Log.i(f49a, "Installation committed successfully");
                return 0;
            }
            Log.e(f49a, "Installation commit failed");
            return 2;
        } catch (Exception e) {
            Log.e(f49a, "Error during multi-APK installation", e);
            e.printStackTrace();
            return 2;
        }
    }

    private static void s(ZipFile zipFile, String str) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "Android/obb/" + str);
            Log.i(f49a, "obbDir: " + file.getAbsolutePath());
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            boolean z = false;
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                String name = zipEntryNextElement.getName();
                if (!zipEntryNextElement.isDirectory() && name.endsWith(".obb")) {
                    if (!file.exists()) {
                        Log.i(f49a, "Created OBB directory: " + file.getAbsolutePath() + " - " + file.mkdirs());
                    }
                    String strSubstring = name.substring(name.lastIndexOf("/") + 1);
                    File file2 = new File(file, strSubstring);
                    Log.i(f49a, "Extracting OBB: " + strSubstring + " (size: " + zipEntryNextElement.getSize() + " bytes)");
                    InputStream inputStream = zipFile.getInputStream(zipEntryNextElement);
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    byte[] bArr = new byte[com.cloudmedia.tv.server.a.l.r];
                    long j = 0;
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i);
                        j += (long) i;
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStream.close();
                    Log.i(f49a, "OBB file installed: " + file2.getAbsolutePath() + " (" + j + " bytes)");
                    try {
                        file2.setReadable(true, false);
                        file2.setWritable(true, false);
                        Runtime.getRuntime().exec("chmod 644 " + file2.getAbsolutePath());
                        Log.i(f49a, "OBB file permissions set: " + file2.getAbsolutePath());
                    } catch (Exception e) {
                        Log.w(f49a, "Failed to set OBB permissions", e);
                    }
                    z = true;
                }
            }
            if (!z) {
                Log.w(f49a, "No OBB files found in XAPK - app may download them on first launch");
                return;
            }
            try {
                file.setReadable(true, false);
                file.setExecutable(true, false);
                Runtime.getRuntime().exec("chmod 755 " + file.getAbsolutePath());
                Log.i(f49a, "OBB directory permissions set");
            } catch (Exception e2) {
                Log.w(f49a, "Failed to set OBB directory permissions", e2);
            }
        } catch (Exception e3) {
            Log.e(f49a, "Error installing OBB files", e3);
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Code duplicated, block: B:112:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:0x0101 A[Catch: IOException -> 0x00fd, TRY_LEAVE, TryCatch #14 {IOException -> 0x00fd, blocks: (B:51:0x00f9, B:55:0x0101), top: B:102:0x00f9 }] */
    /* JADX WARN: Code duplicated, block: B:68:0x011d A[Catch: IOException -> 0x0119, TRY_LEAVE, TryCatch #0 {IOException -> 0x0119, blocks: (B:64:0x0115, B:68:0x011d), top: B:95:0x0115 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x0126 A[PHI: r10
      0x0126: PHI (r10v21 ??) = (r10v13 ??), (r10v14 ??), (r10v22 ??), (r10v22 ??) binds: [B:58:0x0108, B:71:0x0124, B:28:0x00c4, B:25:0x00bb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:75:0x0135  */
    /* JADX WARN: Code duplicated, block: B:87:0x016c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x016e A[Catch: IOException -> 0x016a, TRY_LEAVE, TryCatch #11 {IOException -> 0x016a, blocks: (B:84:0x0166, B:88:0x016e), top: B:98:0x0166 }] */
    /* JADX WARN: Code duplicated, block: B:92:0x0177  */
    /* JADX WARN: Code duplicated, block: B:98:0x0166 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v21, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r10v22, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r10v23 */
    /* JADX WARN: Type inference failed for: r10v24 */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.lang.ProcessBuilder] */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v9 */
    public static int t(String str, String str2) throws Throwable {
        ?? r10;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        Exception e;
        IOException e2;
        File file = new File(str2);
        if (str2 != null && str2.length() != 0) {
            ?? bufferedReader3 = 0;
            if (file.length() > 0 && file.exists() && file.isFile()) {
                int i = 0;
                ?? processBuilder = new ProcessBuilder("pm", "install", "-i", str, "--user", "0", "-r", str2);
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                ?? r1 = 0;
                r1 = 0;
                try {
                    try {
                        processBuilder = processBuilder.start();
                        try {
                            bufferedReader3 = new BufferedReader(new InputStreamReader(processBuilder.getInputStream()));
                            try {
                                bufferedReader2 = new BufferedReader(new InputStreamReader(processBuilder.getErrorStream()));
                                while (true) {
                                    try {
                                        String line = bufferedReader3.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                        sb.append(line);
                                        Log.d("installSilent", "while successMsg s:" + line);
                                    } catch (IOException e3) {
                                        e2 = e3;
                                        e2.printStackTrace();
                                        if (bufferedReader3 != 0) {
                                            try {
                                                bufferedReader3.close();
                                                if (bufferedReader2 != null) {
                                                    bufferedReader2.close();
                                                }
                                            } catch (IOException e4) {
                                                e4.printStackTrace();
                                                if (processBuilder != 0) {
                                                    processBuilder.destroy();
                                                }
                                                if (!sb.toString().contains("Success")) {
                                                    i = 2;
                                                }
                                                Log.d(f49a, "successMsg:" + ((Object) sb) + ", ErrorMsg:" + ((Object) sb2));
                                                return i;
                                            }
                                        } else if (bufferedReader2 != null) {
                                            bufferedReader2.close();
                                        }
                                        if (processBuilder != 0) {
                                        }
                                        if (!sb.toString().contains("Success")) {
                                            i = 2;
                                        }
                                        Log.d(f49a, "successMsg:" + ((Object) sb) + ", ErrorMsg:" + ((Object) sb2));
                                        return i;
                                    } catch (Exception e5) {
                                        e = e5;
                                        e.printStackTrace();
                                        if (bufferedReader3 != 0) {
                                            try {
                                                bufferedReader3.close();
                                                if (bufferedReader2 != null) {
                                                    bufferedReader2.close();
                                                }
                                            } catch (IOException e6) {
                                                e6.printStackTrace();
                                                if (processBuilder != 0) {
                                                    processBuilder.destroy();
                                                }
                                                if (!sb.toString().contains("Success")) {
                                                    i = 2;
                                                }
                                                Log.d(f49a, "successMsg:" + ((Object) sb) + ", ErrorMsg:" + ((Object) sb2));
                                                return i;
                                            }
                                        } else if (bufferedReader2 != null) {
                                            bufferedReader2.close();
                                        }
                                        if (processBuilder != 0) {
                                        }
                                        if (!sb.toString().contains("Success")) {
                                            i = 2;
                                        }
                                        Log.d(f49a, "successMsg:" + ((Object) sb) + ", ErrorMsg:" + ((Object) sb2));
                                        return i;
                                    }
                                }
                                while (true) {
                                    String line2 = bufferedReader2.readLine();
                                    if (line2 != null) {
                                        sb2.append(line2);
                                        Log.d("installSilent", "while errorMsg s:" + line2);
                                    } else {
                                        try {
                                            break;
                                        } catch (IOException e7) {
                                            e7.printStackTrace();
                                        }
                                    }
                                }
                                bufferedReader3.close();
                                bufferedReader2.close();
                            } catch (IOException e8) {
                                bufferedReader2 = null;
                                e2 = e8;
                            } catch (Exception e9) {
                                bufferedReader2 = null;
                                e = e9;
                            } catch (Throwable th) {
                                th = th;
                                bufferedReader = null;
                                r1 = bufferedReader3;
                                r10 = processBuilder;
                                if (r1 != 0) {
                                    try {
                                        r1.close();
                                        if (bufferedReader != null) {
                                            bufferedReader.close();
                                        }
                                    } catch (IOException e10) {
                                        e10.printStackTrace();
                                        if (r10 == 0) {
                                            throw th;
                                        }
                                        r10.destroy();
                                        throw th;
                                    }
                                } else if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                if (r10 == 0) {
                                    throw th;
                                }
                                r10.destroy();
                                throw th;
                            }
                        } catch (IOException e11) {
                            bufferedReader2 = null;
                            e2 = e11;
                            bufferedReader3 = 0;
                        } catch (Exception e12) {
                            bufferedReader2 = null;
                            e = e12;
                            bufferedReader3 = 0;
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = null;
                            r10 = processBuilder;
                            if (r1 != 0) {
                                r1.close();
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                            } else if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (r10 == 0) {
                                throw th;
                            }
                            r10.destroy();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (IOException e13) {
                    bufferedReader3 = 0;
                    bufferedReader2 = null;
                    e2 = e13;
                    processBuilder = 0;
                } catch (Exception e14) {
                    bufferedReader3 = 0;
                    bufferedReader2 = null;
                    e = e14;
                    processBuilder = 0;
                } catch (Throwable th4) {
                    th = th4;
                    r10 = 0;
                    bufferedReader = null;
                }
                processBuilder.destroy();
                if (!sb.toString().contains("Success") && !sb.toString().contains("success")) {
                    i = 2;
                }
                Log.d(f49a, "successMsg:" + ((Object) sb) + ", ErrorMsg:" + ((Object) sb2));
                return i;
            }
        }
        return 1;
    }

    public static int u(Context context, String str) throws Throwable {
        String str2;
        ArrayList<File> arrayList = new ArrayList();
        ZipFile zipFile = null;
        String str3 = null;
        ZipFile zipFile2 = null;
        try {
            try {
                File file = new File(str);
                if (file.exists() && file.isFile()) {
                    Log.i(f49a, "Installing XAPK: " + str);
                    ZipFile zipFile3 = new ZipFile(file);
                    try {
                        try {
                            File file2 = new File(context.getCacheDir(), "xapk_temp_" + System.currentTimeMillis());
                            if (!file2.exists()) {
                                file2.mkdirs();
                            }
                            Enumeration<? extends ZipEntry> enumerationEntries = zipFile3.entries();
                            while (enumerationEntries.hasMoreElements()) {
                                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                                String name = zipEntryNextElement.getName();
                                if (!zipEntryNextElement.isDirectory() && name.endsWith(".apk")) {
                                    String strSubstring = name.substring(name.lastIndexOf("/") + 1);
                                    File file3 = new File(file2, strSubstring);
                                    Log.i(f49a, "Extracting APK: " + strSubstring + " (size: " + zipEntryNextElement.getSize() + " bytes)");
                                    InputStream inputStream = zipFile3.getInputStream(zipEntryNextElement);
                                    FileOutputStream fileOutputStream = new FileOutputStream(file3);
                                    byte[] bArr = new byte[com.cloudmedia.tv.server.a.l.r];
                                    while (true) {
                                        int i = inputStream.read(bArr);
                                        if (i == -1) {
                                            break;
                                        }
                                        fileOutputStream.write(bArr, 0, i);
                                    }
                                    fileOutputStream.close();
                                    inputStream.close();
                                    arrayList.add(file3);
                                    Log.i(f49a, "Extracted: " + file3.getAbsolutePath());
                                }
                            }
                            if (arrayList.isEmpty()) {
                                Log.e(f49a, "No APK files found in XAPK");
                                for (File file4 : arrayList) {
                                    if (file4 != null && file4.exists()) {
                                        file4.delete();
                                    }
                                }
                                if (!arrayList.isEmpty() && ((File) arrayList.get(0)).getParentFile() != null) {
                                    File parentFile = ((File) arrayList.get(0)).getParentFile();
                                    if (parentFile.exists()) {
                                        parentFile.delete();
                                    }
                                }
                                try {
                                    zipFile3.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return 1;
                            }
                            Log.i(f49a, "Total APKs extracted: " + arrayList.size());
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                try {
                                    PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(((File) it.next()).getAbsolutePath(), 0);
                                    if (packageArchiveInfo != null && (str2 = packageArchiveInfo.applicationInfo.packageName) != null) {
                                        try {
                                            Log.i(f49a, "Package name: " + str2);
                                            str3 = str2;
                                            break;
                                        } catch (Exception unused) {
                                            str3 = str2;
                                        }
                                    }
                                } catch (Exception unused2) {
                                }
                            }
                            if (str3 != null) {
                                s(zipFile3, str3);
                            }
                            Log.i(f49a, "Installing APKs using session-based installation...");
                            int iR = r(context, arrayList);
                            if (iR == 0) {
                                Log.i(f49a, "XAPK installed successfully");
                            } else {
                                Log.e(f49a, "XAPK installation failed with code: " + iR);
                            }
                            for (File file5 : arrayList) {
                                if (file5 != null && file5.exists()) {
                                    file5.delete();
                                }
                            }
                            if (!arrayList.isEmpty() && ((File) arrayList.get(0)).getParentFile() != null) {
                                File parentFile2 = ((File) arrayList.get(0)).getParentFile();
                                if (parentFile2.exists()) {
                                    parentFile2.delete();
                                }
                            }
                            try {
                                zipFile3.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            return iR;
                        } catch (Exception e3) {
                            e = e3;
                            zipFile2 = zipFile3;
                            Log.e(f49a, "Error installing XAPK", e);
                            e.printStackTrace();
                            for (File file6 : arrayList) {
                                if (file6 != null && file6.exists()) {
                                    file6.delete();
                                }
                            }
                            if (!arrayList.isEmpty() && ((File) arrayList.get(0)).getParentFile() != null) {
                                File parentFile3 = ((File) arrayList.get(0)).getParentFile();
                                if (parentFile3.exists()) {
                                    parentFile3.delete();
                                }
                            }
                            if (zipFile2 == null) {
                                return 2;
                            }
                            try {
                                zipFile2.close();
                                return 2;
                            } catch (IOException e4) {
                                e4.printStackTrace();
                                return 2;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        zipFile = zipFile3;
                        for (File file7 : arrayList) {
                            if (file7 != null && file7.exists()) {
                                file7.delete();
                            }
                        }
                        if (!arrayList.isEmpty() && ((File) arrayList.get(0)).getParentFile() != null) {
                            File parentFile4 = ((File) arrayList.get(0)).getParentFile();
                            if (parentFile4.exists()) {
                                parentFile4.delete();
                            }
                        }
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                Log.e(f49a, "XAPK file not found: " + str);
                for (File file8 : arrayList) {
                    if (file8 != null && file8.exists()) {
                        file8.delete();
                    }
                }
                if (!arrayList.isEmpty() && ((File) arrayList.get(0)).getParentFile() != null) {
                    File parentFile5 = ((File) arrayList.get(0)).getParentFile();
                    if (parentFile5.exists()) {
                        parentFile5.delete();
                    }
                }
                return 1;
            } catch (Exception e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void v(Context context, String str) {
        Method method;
        try {
            PackageManager packageManager = context.getPackageManager();
            Method[] declaredMethods = packageManager != null ? packageManager.getClass().getDeclaredMethods() : null;
            if (declaredMethods != null && declaredMethods.length > 0) {
                int length = declaredMethods.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        method = null;
                        break;
                    }
                    method = declaredMethods[i];
                    if (method.getName().toString().equals("deletePackage")) {
                        break;
                    } else {
                        i++;
                    }
                }
            } else {
                method = null;
                break;
            }
            if (method != null) {
                method.setAccessible(true);
                method.invoke(packageManager, str, null, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String l(String str) {
        PackageInfo packageArchiveInfo = ParserUtils.getContext().getPackageManager().getPackageArchiveInfo(str, 1);
        return packageArchiveInfo != null ? packageArchiveInfo.applicationInfo.packageName : "";
    }
}
