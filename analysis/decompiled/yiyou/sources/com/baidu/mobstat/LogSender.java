package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.blankj.utilcode.constant.TimeConstants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.GZIPOutputStream;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LogSender {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static LogSender f3371a = new LogSender();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3372b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3373c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3374d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SendStrategyEnum f3375e = SendStrategyEnum.APP_START;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Timer f3376f;
    private Handler g;

    private LogSender() {
        HandlerThread handlerThread = new HandlerThread("LogSenderThread");
        handlerThread.start();
        this.g = new Handler(handlerThread.getLooper());
    }

    private String e(Context context, String str, String str2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        HttpURLConnection httpURLConnectionD = at.d(context, str);
        httpURLConnectionD.setDoOutput(true);
        httpURLConnectionD.setInstanceFollowRedirects(false);
        httpURLConnectionD.setUseCaches(false);
        httpURLConnectionD.setRequestProperty("Content-Type", "gzip");
        byte[] bArrA = ar.a.a();
        byte[] bArrB = ar.a.b();
        httpURLConnectionD.setRequestProperty("key", ba.a(bArrA));
        httpURLConnectionD.setRequestProperty("iv", ba.a(bArrB));
        byte[] bArrA2 = ar.a.a(bArrA, bArrB, str2.getBytes("utf-8"));
        httpURLConnectionD.connect();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(httpURLConnectionD.getOutputStream());
            gZIPOutputStream.write(bArrA2);
            gZIPOutputStream.flush();
            gZIPOutputStream.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnectionD.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            int contentLength = httpURLConnectionD.getContentLength();
            if (httpURLConnectionD.getResponseCode() == 200 && contentLength == 0) {
                String string = sb.toString();
                httpURLConnectionD.disconnect();
                return string;
            }
            throw new IOException("http code = " + httpURLConnectionD.getResponseCode() + "; contentResponse = " + ((Object) sb));
        } catch (Throwable th) {
            httpURLConnectionD.disconnect();
            throw th;
        }
    }

    public static LogSender instance() {
        return f3371a;
    }

    public void onSend(final Context context) {
        if (context != null) {
            context = context.getApplicationContext();
        }
        if (context == null) {
            return;
        }
        this.g.post(new Runnable() { // from class: com.baidu.mobstat.LogSender.1
            @Override // java.lang.Runnable
            public void run() {
                if (LogSender.this.f3376f != null) {
                    LogSender.this.f3376f.cancel();
                    LogSender.this.f3376f = null;
                }
                LogSender.this.f3375e = SendStrategyEnum.values()[av.a().b(context)];
                LogSender.this.f3374d = av.a().c(context);
                LogSender.this.f3372b = av.a().d(context);
                if (LogSender.this.f3375e.equals(SendStrategyEnum.SET_TIME_INTERVAL) || LogSender.this.f3375e.equals(SendStrategyEnum.ONCE_A_DAY)) {
                    LogSender.this.setSendingLogTimer(context);
                }
                LogSender.this.g.postDelayed(new Runnable() { // from class: com.baidu.mobstat.LogSender.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        LogSender.this.a(context);
                    }
                }, LogSender.this.f3373c * TimeConstants.SEC);
            }
        });
    }

    public void saveLogData(Context context, String str, boolean z) {
        at.a(context, (z ? Config.PREFIX_SEND_DATA_FULL : Config.PREFIX_SEND_DATA) + System.currentTimeMillis(), str, false);
        if (z) {
            a(context, Config.FULL_TRACE_LOG_LIMIT, Config.PREFIX_SEND_DATA_FULL);
        }
    }

    public void sendEmptyLogData(Context context, final String str) {
        final Context applicationContext = context.getApplicationContext();
        this.g.post(new Runnable() { // from class: com.baidu.mobstat.LogSender.7
            @Override // java.lang.Runnable
            public void run() {
                String strConstructLogWithEmptyBody = DataCore.instance().constructLogWithEmptyBody(applicationContext, str);
                if (TextUtils.isEmpty(strConstructLogWithEmptyBody)) {
                    return;
                }
                LogSender.this.c(applicationContext, strConstructLogWithEmptyBody);
            }
        });
    }

    public void sendLogData(Context context, final String str, boolean z) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        if (z) {
            b(applicationContext, str);
        } else {
            this.g.post(new Runnable() { // from class: com.baidu.mobstat.LogSender.6
                @Override // java.lang.Runnable
                public void run() {
                    LogSender.this.b(applicationContext, str);
                }
            });
        }
    }

    public void setLogSenderDelayed(int i) {
        if (i < 0 || i > 30) {
            return;
        }
        this.f3373c = i;
    }

    public void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i, boolean z) {
        if (!sendStrategyEnum.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
            this.f3375e = sendStrategyEnum;
            av.a().a(context, this.f3375e.ordinal());
            if (sendStrategyEnum.equals(SendStrategyEnum.ONCE_A_DAY)) {
                av.a().b(context, 24);
            }
        } else if (i > 0 && i <= 24) {
            this.f3374d = i;
            this.f3375e = SendStrategyEnum.SET_TIME_INTERVAL;
            av.a().a(context, this.f3375e.ordinal());
            av.a().b(context, this.f3374d);
        }
        this.f3372b = z;
        av.a().a(context, this.f3372b);
    }

    public void setSendingLogTimer(Context context) {
        final Context applicationContext = context.getApplicationContext();
        long j = this.f3374d * 3600000;
        try {
            this.f3376f = new Timer();
            this.f3376f.schedule(new TimerTask() { // from class: com.baidu.mobstat.LogSender.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    LogSender.this.a(applicationContext);
                }
            }, j, j);
        } catch (Exception unused) {
        }
    }

    private String d(Context context, String str, String str2) {
        HttpURLConnection httpURLConnectionD = at.d(context, str);
        httpURLConnectionD.setDoOutput(true);
        httpURLConnectionD.setInstanceFollowRedirects(false);
        httpURLConnectionD.setUseCaches(false);
        httpURLConnectionD.setRequestProperty("Content-Type", "gzip");
        try {
            JSONObject jSONObject = new JSONObject(str2).getJSONObject(Config.HEADER_PART);
            httpURLConnectionD.setRequestProperty("mtj_appkey", jSONObject.getString(Config.APP_KEY));
            httpURLConnectionD.setRequestProperty("mtj_appversion", jSONObject.getString("n"));
            httpURLConnectionD.setRequestProperty("mtj_os", jSONObject.getString(Config.OS));
            httpURLConnectionD.setRequestProperty("mtj_pn", jSONObject.getString("pn"));
            httpURLConnectionD.setRequestProperty("mtj_tg", jSONObject.getString(Config.SDK_TAG));
            httpURLConnectionD.setRequestProperty("mtj_ii", jSONObject.getString(Config.CUID_SEC));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        httpURLConnectionD.connect();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(httpURLConnectionD.getOutputStream())));
            bufferedWriter.write(str2);
            bufferedWriter.flush();
            bufferedWriter.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnectionD.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            int contentLength = httpURLConnectionD.getContentLength();
            if (httpURLConnectionD.getResponseCode() == 200 && contentLength == 0) {
                String string = sb.toString();
                httpURLConnectionD.disconnect();
                return string;
            }
            throw new IOException("http code = " + httpURLConnectionD.getResponseCode() + "; contentResponse = " + ((Object) sb));
        } catch (Throwable th) {
            httpURLConnectionD.disconnect();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, String str) {
        String str2 = Config.PREFIX_SEND_DATA + System.currentTimeMillis();
        at.a(context, str2, str, false);
        if (c(context, str)) {
            at.b(context, str2);
        } else {
            b(context, str2, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(Context context, String str) {
        return a(context, str, false);
    }

    private String c(Context context, String str, String str2) {
        if (!str.startsWith("https://")) {
            return e(context, str, str2);
        }
        return d(context, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, String str, String str2) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str2);
        } catch (Exception unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) jSONObject.get(Config.TRACE_PART);
            jSONObject2.put(Config.TRACE_FAILED_CNT, jSONObject2.getLong(Config.TRACE_FAILED_CNT) + 1);
        } catch (Exception unused2) {
        }
        at.a(context, str, jSONObject.toString(), false);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x002e A[EXC_TOP_SPLITTER, PHI: r2 r3
      0x002e: PHI (r2v5 java.io.FileInputStream) = (r2v4 java.io.FileInputStream), (r2v8 java.io.FileInputStream) binds: [B:13:0x002c, B:6:0x0021] A[DONT_GENERATE, DONT_INLINE]
      0x002e: PHI (r3v2 long) = (r3v1 long), (r3v4 long) binds: [B:13:0x002c, B:6:0x0021] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    private void a(Context context, long j, String str) {
        ArrayList<String> arrayListA = a(context, str);
        int size = arrayListA.size() - 1;
        long jAvailable = 0;
        FileInputStream fileInputStreamOpenFileInput = null;
        while (size >= 0) {
            try {
                fileInputStreamOpenFileInput = context.openFileInput(arrayListA.get(size));
                jAvailable += (long) fileInputStreamOpenFileInput.available();
                if (fileInputStreamOpenFileInput != null) {
                    try {
                        fileInputStreamOpenFileInput.close();
                    } catch (Exception unused) {
                    }
                    fileInputStreamOpenFileInput = null;
                }
            } catch (Exception unused2) {
                if (fileInputStreamOpenFileInput != null) {
                    fileInputStreamOpenFileInput.close();
                    fileInputStreamOpenFileInput = null;
                }
            } catch (Throwable th) {
                if (fileInputStreamOpenFileInput != null) {
                    try {
                        fileInputStreamOpenFileInput.close();
                    } catch (Exception unused3) {
                    }
                }
                throw th;
            }
            if (jAvailable > j) {
                break;
            } else {
                size--;
            }
        }
        for (int i = 0; i <= size; i++) {
            at.b(context, arrayListA.get(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<String> a(Context context, final String str) {
        File filesDir;
        ArrayList<String> arrayList = new ArrayList<>();
        if (context != null && (filesDir = context.getFilesDir()) != null && filesDir.exists()) {
            FilenameFilter filenameFilter = new FilenameFilter() { // from class: com.baidu.mobstat.LogSender.3
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str2) {
                    return str2.startsWith(str);
                }
            };
            String[] list = null;
            try {
                list = filesDir.list(filenameFilter);
            } catch (Exception unused) {
            }
            if (list != null && list.length != 0) {
                try {
                    Arrays.sort(list, new Comparator<String>() { // from class: com.baidu.mobstat.LogSender.4
                        @Override // java.util.Comparator
                        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                        public int compare(String str2, String str3) {
                            return str2.compareTo(str3);
                        }
                    });
                } catch (Exception unused2) {
                }
                for (String str2 : list) {
                    arrayList.add(str2);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context) {
        if (!this.f3372b || bb.p(context)) {
            this.g.post(new Runnable() { // from class: com.baidu.mobstat.LogSender.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ArrayList<String> arrayList = new ArrayList();
                        arrayList.addAll(LogSender.this.a(context, Config.PREFIX_SEND_DATA));
                        arrayList.addAll(LogSender.this.a(context, Config.PREFIX_SEND_DATA_FULL));
                        while (true) {
                            int i = 0;
                            for (String str : arrayList) {
                                String strA = at.a(context, str);
                                if (TextUtils.isEmpty(strA)) {
                                    at.b(context, str);
                                } else {
                                    if (LogSender.this.a(context, strA, str.contains(Config.PREFIX_SEND_DATA_FULL))) {
                                        at.b(context, str);
                                    } else {
                                        LogSender.b(context, str, strA);
                                        i++;
                                        if (i >= 5) {
                                            return;
                                        }
                                    }
                                }
                            }
                            return;
                        }
                    } catch (Exception unused) {
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Context context, String str, boolean z) {
        if (!z) {
            am.c().a("Start send log \n" + str);
        }
        boolean z2 = false;
        if (this.f3372b && !bb.p(context)) {
            am.c().a("[WARNING] wifi not available, log will be cached, next time will try to resend");
            return false;
        }
        String str2 = Config.LOG_SEND_URL;
        if (z) {
            str2 = Config.LOG_FULL_SEND_URL;
        }
        try {
            c(context, str2, str);
            z2 = true;
        } catch (Exception e2) {
            am.c().c(e2);
        }
        if (!z) {
            am.c().a("Send log " + (z2 ? "success" : "failed"));
        }
        return z2;
    }
}
