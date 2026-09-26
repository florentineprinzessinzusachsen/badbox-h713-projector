package com.baidu.mobstat;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f3516a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static s f3517b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f3518c;

    static {
        f3516a = android.os.Build.VERSION.SDK_INT < 9 ? "http://openrcv.baidu.com/1010/bplus.gif" : "https://openrcv.baidu.com/1010/bplus.gif";
    }

    private s() {
        HandlerThread handlerThread = new HandlerThread("LogSender");
        handlerThread.start();
        this.f3518c = new Handler(handlerThread.getLooper());
    }

    private boolean b(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            if (!bb.c().booleanValue()) {
                return true;
            }
            try {
                a(context, f3516a, str);
                return true;
            } catch (Exception e2) {
                al.c().c(e2);
            }
        }
        return false;
    }

    public static s a() {
        if (f3517b == null) {
            synchronized (s.class) {
                if (f3517b == null) {
                    f3517b = new s();
                }
            }
        }
        return f3517b;
    }

    public void a(final Context context, final String str) {
        al.c().a("data = " + str);
        if (str == null || "".equals(str)) {
            return;
        }
        this.f3518c.post(new Runnable() { // from class: com.baidu.mobstat.s.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    s.this.a(str);
                    if (context == null) {
                        return;
                    }
                    s.this.a(context.getApplicationContext());
                } catch (Throwable th) {
                    al.c().b(th);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) throws Throwable {
        at.a("backups/system" + File.separator + "__send_log_data_" + System.currentTimeMillis(), str, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) throws Throwable {
        File[] fileArrListFiles;
        if ("mounted".equals(at.a())) {
            File file = new File(Environment.getExternalStorageDirectory(), "backups/system");
            if (!file.exists() || (fileArrListFiles = file.listFiles()) == null || fileArrListFiles.length == 0) {
                return;
            }
            try {
                Arrays.sort(fileArrListFiles, new Comparator<File>() { // from class: com.baidu.mobstat.s.2
                    @Override // java.util.Comparator
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(File file2, File file3) {
                        return (int) (file3.lastModified() - file2.lastModified());
                    }
                });
            } catch (Exception e2) {
                al.c().b(e2);
            }
            int i = 0;
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    String name = file2.getName();
                    if (!TextUtils.isEmpty(name) && name.startsWith("__send_log_data_")) {
                        String str = "backups/system" + File.separator + name;
                        String strB = at.b(str);
                        if (b(context, strB)) {
                            at.c(str);
                            i = 0;
                        } else {
                            a(strB, str);
                            i++;
                            if (i >= 5) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(String str, String str2) throws Throwable {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(str);
        } catch (Exception unused) {
        }
        JSONObject jSONObjectA = h.a(jSONObject);
        if (jSONObjectA != null) {
            h.b(jSONObjectA);
            at.a(str2, jSONObject.toString(), false);
        }
    }

    private String a(Context context, String str, String str2) {
        byte[] bytes;
        boolean z = !str.startsWith("https:");
        HttpURLConnection httpURLConnectionD = at.d(context, str);
        httpURLConnectionD.setDoOutput(true);
        httpURLConnectionD.setInstanceFollowRedirects(false);
        httpURLConnectionD.setUseCaches(false);
        httpURLConnectionD.setRequestProperty("Content-Encoding", "gzip");
        try {
            JSONObject jSONObject = new JSONObject(str2).getJSONArray("payload").getJSONObject(0).getJSONObject(Config.HEADER_PART);
            httpURLConnectionD.setRequestProperty("Content-Type", "gzip");
            httpURLConnectionD.setRequestProperty("mtj_appversion", jSONObject.getString("n"));
            httpURLConnectionD.setRequestProperty("mtj_os", "Android");
            httpURLConnectionD.setRequestProperty("mtj_pn", jSONObject.getString("pn"));
            httpURLConnectionD.setRequestProperty("mtj_tg", "1");
            httpURLConnectionD.setRequestProperty("mtj_ii", jSONObject.getString(Config.CUID_SEC));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        httpURLConnectionD.connect();
        try {
            try {
                OutputStream outputStream = httpURLConnectionD.getOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                gZIPOutputStream.write(new byte[]{72, 77, 48, 49});
                gZIPOutputStream.write(new byte[]{0, 0, 0, 1});
                gZIPOutputStream.write(new byte[]{0, 0, 3, -14});
                gZIPOutputStream.write(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});
                gZIPOutputStream.write(new byte[]{0, 2});
                if (z) {
                    gZIPOutputStream.write(new byte[]{0, 1});
                } else {
                    gZIPOutputStream.write(new byte[]{0, 0});
                }
                gZIPOutputStream.write(new byte[]{72, 77, 48, 49});
                if (z) {
                    byte[] bArrA = ar.a.a();
                    byte[] bArrA2 = ba.a(false, aw.a(), bArrA);
                    gZIPOutputStream.write(a(bArrA2.length, 4));
                    gZIPOutputStream.write(bArrA2);
                    bytes = ar.a.a(bArrA, new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, str2.getBytes("utf-8"));
                    gZIPOutputStream.write(a(bytes.length, 2));
                } else {
                    bytes = str2.getBytes("utf-8");
                }
                gZIPOutputStream.write(bytes);
                gZIPOutputStream.close();
                outputStream.close();
                int responseCode = httpURLConnectionD.getResponseCode();
                int contentLength = httpURLConnectionD.getContentLength();
                al.c().c("code: " + responseCode + "; len: " + contentLength);
                if (responseCode == 200 && contentLength == 0) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnectionD.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            String string = sb.toString();
                            httpURLConnectionD.disconnect();
                            return string;
                        }
                        sb.append(line);
                    }
                } else {
                    throw new IOException("Response code = " + httpURLConnectionD.getResponseCode());
                }
            } catch (Exception e3) {
                al.c().b(e3);
                httpURLConnectionD.disconnect();
                return "";
            }
        } catch (Throwable th) {
            httpURLConnectionD.disconnect();
            throw th;
        }
    }

    private static byte[] a(long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[(i - i2) - 1] = (byte) (255 & j);
            j >>= 8;
        }
        return bArr;
    }
}
