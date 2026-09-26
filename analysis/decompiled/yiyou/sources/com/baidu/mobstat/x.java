package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.blankj.utilcode.constant.TimeConstants;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.jar.JarFile;

/* JADX INFO: loaded from: classes.dex */
class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile DexClassLoader f3534a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile boolean f3535b = false;

    static class a extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Context f3536a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private com.baidu.mobstat.a f3537b;

        public a(Context context, com.baidu.mobstat.a aVar) {
            this.f3536a = context;
            this.f3537b = aVar;
        }

        private void a(Context context) {
            this.f3537b.a(context, System.currentTimeMillis());
        }

        /* JADX WARN: Code duplicated, block: B:11:0x003f  */
        private String b(Context context) throws Throwable {
            String strB;
            File fileStreamPath;
            File fileStreamPath2 = context.getFileStreamPath(".remote.jar");
            if (fileStreamPath2 == null || !fileStreamPath2.exists() || (fileStreamPath = context.getFileStreamPath(".remote.jar")) == null) {
                strB = "33";
            } else {
                strB = x.b(fileStreamPath.getAbsolutePath());
                al.c().a("startDownload remote jar file version = " + strB);
                if (TextUtils.isEmpty(strB)) {
                    strB = "33";
                }
            }
            ArrayList<Pair> arrayList = new ArrayList();
            arrayList.add(new Pair("dynamicVersion", "" + strB));
            arrayList.add(new Pair("packageName", bb.r(context)));
            arrayList.add(new Pair("appVersion", bb.g(context)));
            arrayList.add(new Pair("cuid", bb.a(context)));
            arrayList.add(new Pair("platform", "Android"));
            arrayList.add(new Pair(Config.MODEL, android.os.Build.MODEL));
            arrayList.add(new Pair("s", android.os.Build.VERSION.SDK_INT + ""));
            arrayList.add(new Pair(Config.OS, android.os.Build.VERSION.RELEASE));
            arrayList.add(new Pair("i", "33"));
            StringBuilder sb = new StringBuilder();
            for (Pair pair : arrayList) {
                try {
                    String strEncode = URLEncoder.encode(((String) pair.first).toString(), "UTF-8");
                    String strEncode2 = URLEncoder.encode(((String) pair.second).toString(), "UTF-8");
                    if (TextUtils.isEmpty(sb.toString())) {
                        sb.append(strEncode + "=" + strEncode2);
                    } else {
                        sb.append("&" + strEncode + "=" + strEncode2);
                    }
                } catch (Exception unused) {
                }
            }
            return aa.f3424c + "?" + sb.toString();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                int i = aa.f3422a ? 3 : 10;
                al.c().a("start version check in " + i + "s");
                Thread.sleep((long) (i * TimeConstants.SEC));
                a();
                a(this.f3536a);
            } catch (Exception e2) {
                al.c().a(e2);
            }
            boolean unused = x.f3535b = false;
        }

        private synchronized void a() {
            FileOutputStream fileOutputStreamOpenFileOutput;
            al.c().a("start get config and download jar");
            Context context = this.f3536a;
            com.baidu.mobstat.a aVar = this.f3537b;
            String strB = b(context);
            al.c().c("update req url is:" + strB);
            HttpURLConnection httpURLConnectionD = at.d(context, strB);
            try {
                httpURLConnectionD.connect();
                String headerField = httpURLConnectionD.getHeaderField("X-CONFIG");
                al.c().a("config is: " + headerField);
                String headerField2 = httpURLConnectionD.getHeaderField("X-SIGN");
                al.c().a("sign is: " + headerField2);
                int responseCode = httpURLConnectionD.getResponseCode();
                al.c().a("update response code is: " + responseCode);
                int contentLength = httpURLConnectionD.getContentLength();
                al.c().a("update response content length is: " + contentLength);
                if (responseCode == 200 && contentLength > 0) {
                    try {
                        fileOutputStreamOpenFileOutput = context.openFileOutput(".remote.jar", 0);
                        try {
                            try {
                                if (az.a(httpURLConnectionD.getInputStream(), fileOutputStreamOpenFileOutput)) {
                                    al.c().a("save remote jar success");
                                }
                            } catch (IOException e2) {
                                e = e2;
                                al.c().b(e);
                            }
                        } catch (Throwable th) {
                            th = th;
                            az.a(fileOutputStreamOpenFileOutput);
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        fileOutputStreamOpenFileOutput = null;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStreamOpenFileOutput = null;
                        az.a(fileOutputStreamOpenFileOutput);
                        throw th;
                    }
                    az.a(fileOutputStreamOpenFileOutput);
                }
                DexClassLoader unused = x.f3534a = null;
                u.a();
                if (!TextUtils.isEmpty(headerField)) {
                    aVar.a(context, headerField);
                }
                if (!TextUtils.isEmpty(headerField2)) {
                    aVar.b(context, headerField2);
                }
                httpURLConnectionD.disconnect();
                al.c().a("finish get config and download jar");
            } catch (Throwable th3) {
                httpURLConnectionD.disconnect();
                throw th3;
            }
        }
    }

    private static boolean b(Context context, String str) throws Throwable {
        int iIntValue;
        String strB = b(str);
        if (TextUtils.isEmpty(strB)) {
            return false;
        }
        try {
            iIntValue = Integer.valueOf(strB).intValue();
        } catch (Exception e2) {
            al.c().b(e2);
            iIntValue = 0;
        }
        return iIntValue >= 4;
    }

    private static boolean c(Context context, String str) throws Throwable {
        String strA = ay.b.a(new File(str));
        al.c().a("remote.jar local file digest value digest = " + strA);
        if (TextUtils.isEmpty(strA)) {
            al.c().a("remote.jar local file digest value fail");
            return false;
        }
        String strB = b(str);
        al.c().a("remote.jar local file digest value version = " + strB);
        if (TextUtils.isEmpty(strB)) {
            return false;
        }
        String strD = d(context, strB);
        al.c().a("remote.jar config digest value remoteJarMd5 = " + strD);
        if (!TextUtils.isEmpty(strD)) {
            return strA.equals(strD);
        }
        al.c().a("remote.jar config digest value lost");
        return false;
    }

    private static String d(Context context, String str) {
        return y.a(context).c(str);
    }

    public static Class<?> a(Context context, String str) {
        DexClassLoader dexClassLoaderA = a(context);
        if (dexClassLoaderA == null) {
            return null;
        }
        return dexClassLoaderA.loadClass(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str) throws Throwable {
        JarFile jarFile = null;
        try {
            try {
                File file = new File(str);
                if (file.exists()) {
                    al.c().b("file size: " + file.length());
                }
                JarFile jarFile2 = new JarFile(str);
                try {
                    String value = jarFile2.getManifest().getMainAttributes().getValue("Plugin-Version");
                    try {
                        jarFile2.close();
                    } catch (Exception unused) {
                    }
                    return value;
                } catch (Exception e2) {
                    e = e2;
                    jarFile = jarFile2;
                    al.c().a(e);
                    al.c().a("baidu remote sdk is not ready" + str);
                    if (jarFile == null) {
                        return "";
                    }
                    try {
                        jarFile.close();
                        return "";
                    } catch (Exception unused2) {
                        return "";
                    }
                } catch (Throwable th) {
                    th = th;
                    jarFile = jarFile2;
                    if (jarFile != null) {
                        try {
                            jarFile.close();
                        } catch (Exception unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static synchronized DexClassLoader a(Context context) {
        if (f3534a != null) {
            return f3534a;
        }
        File fileStreamPath = context.getFileStreamPath(".remote.jar");
        if (fileStreamPath != null && !fileStreamPath.isFile()) {
            return null;
        }
        if (!b(context, fileStreamPath.getAbsolutePath())) {
            al.c().a("remote jar version lower than min limit, need delete");
            if (fileStreamPath.isFile()) {
                fileStreamPath.delete();
            }
            return null;
        }
        if (!c(context, fileStreamPath.getAbsolutePath())) {
            al.c().a("remote jar md5 is not right, need delete");
            if (fileStreamPath.isFile()) {
                fileStreamPath.delete();
            }
            return null;
        }
        try {
            f3534a = new DexClassLoader(fileStreamPath.getAbsolutePath(), context.getDir("outdex", 0).getAbsolutePath(), null, context.getClassLoader());
        } catch (Exception e2) {
            al.c().a(e2);
        }
        return f3534a;
    }

    public static synchronized void a(Context context, com.baidu.mobstat.a aVar) {
        if (f3535b) {
            return;
        }
        if (!bb.p(context)) {
            al.c().a("isWifiAvailable = false, will not to update");
        } else {
            if (!aVar.a(context)) {
                al.c().a("check time, will not to update");
                return;
            }
            al.c().a("can start update config");
            new a(context, aVar).start();
            f3535b = true;
        }
    }
}
