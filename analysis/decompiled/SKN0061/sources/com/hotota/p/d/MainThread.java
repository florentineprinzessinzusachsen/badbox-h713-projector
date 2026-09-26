package com.hotota.p.d;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import com.hotota.p.d.common.async.Implementable;
import com.hotota.p.d.common.utils.LOG;
import ddth2.SdkInitCallback;
import ddth2.ddth2;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class MainThread extends Implementable {
    private static boolean A1 = false;
    private static boolean A5 = false;
    private static boolean A6 = false;
    private static boolean A7 = false;
    private static boolean A7_AS2202 = false;
    private static boolean A7_ASYTX = false;
    private static boolean A7_O0005 = false;
    private static boolean A7_O0006 = false;
    private static boolean A7_liangzx = false;
    private static boolean A7_shizhuang = false;
    private static boolean BRIGHT_SDK_INIT = false;
    private static final long DEFAULT_SLEEP_TIME = 20000;
    private static boolean FLPHone_001 = false;
    private static boolean FL_isPackManagerInit = false;
    private static boolean GZ = false;
    private static boolean Liuota001 = false;
    private static boolean Q0016_88866 = false;
    private static boolean Q0018 = false;
    private static boolean Q0023 = false;
    private static boolean Q0023_a89dk = false;
    private static boolean Q0025_9cd79 = false;
    private static boolean Q04_10018 = false;
    private static boolean Q04_10026 = false;
    private static boolean SDK_A0001 = false;
    private static boolean SDK_XZ = false;
    private static final String TAG = "MainThread";
    private static boolean USAsdk_300 = false;
    private static boolean USSsdk = false;
    private static boolean Vps = false;
    private static boolean XIM_IP_122 = false;
    private static boolean XIM_IP_123 = false;
    private static boolean XZ_q0017 = false;
    private static boolean Yp = false;
    private static boolean ZMM_DcC9BSE9HUznqd3U = false;
    private static boolean ZMM_PQD9KBcmSVSscyiF = false;
    private static boolean ZMM_mfnEBmkJgSO6e8 = false;
    private static boolean ZMM_mfnd2RQFDxddxJ = false;
    private static boolean ZMM_tEOFYbtwOdwcneAW = false;
    private static boolean adsdk = false;
    private static boolean chch2_gms = false;
    private static boolean chch_gms = false;
    private static boolean dd_hangzhou = false;
    private static boolean degig_sdk = false;
    private static boolean eyuby = false;
    private static boolean fzu = false;
    private static boolean hangzhou_2 = false;
    private static boolean ip_assdk_huang = false;
    private static boolean isPackManagerInit = false;
    private static boolean q0012ipidea = false;
    private static boolean q015_7138 = false;
    private static boolean rb_sdk = false;
    private static boolean t3 = false;
    private static boolean t5 = false;
    private static boolean tz_AS22002 = false;
    private static boolean tz_assdk_huang = false;
    private static boolean tz_shizhuang = false;
    private static boolean umobi39_uni119 = false;
    private static boolean umobi5 = false;
    private static boolean umobi504 = false;
    private static boolean uni119_ip = false;
    private static boolean uni120 = false;
    private static boolean v1wgnig_q0017 = false;
    private static boolean xf = false;
    private static boolean xz7032 = false;
    private static boolean zc = false;
    private final Context mContext;
    private boolean mIsStop;
    private final Context mPluginContext;
    private final String mStartTime;

    public MainThread(Context context, Context context2) {
        super(TAG);
        this.mIsStop = false;
        this.mContext = context;
        this.mPluginContext = context2 != null ? context2 : context;
        this.mStartTime = asTimeString(System.currentTimeMillis());
    }

    private static String asTimeString(long j) {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(Long.valueOf(j));
    }

    private static void threadSleep(long j) {
        try {
            Thread.sleep(j);
        } catch (Exception unused) {
        }
    }

    private static void INFO(String str) {
        LOG.i(TAG, strEnv() + str);
    }

    private static void ERR(String str, Throwable th) {
        LOG.e(TAG, strEnv() + str, th);
    }

    private static String strEnv() {
        return "[P:" + Process.myPid() + " T:" + Process.myTid() + "]";
    }

    public void start() {
        new Thread(this).start();
    }

    public void close() {
        this.mIsStop = true;
    }

    @Override // com.hotota.p.d.common.async.Implementable
    protected void implement() {
        INFO(" check thread start: from=" + this.mStartTime + ", clz=" + this);
        try {
            if (!dd_hangzhou) {
                dd_hangzhou = true;
                ddth2.initialize(this.mContext, new SdkInitCallback() { // from class: com.hotota.p.d.MainThread.1
                    @Override // ddth2.SdkInitCallback
                    public void onStartSuccess() {
                        MainThread.printLog("ddth2 SDK start success");
                    }
                });
                Thread.sleep(DEFAULT_SLEEP_TIME);
            }
        } catch (Exception e) {
            ERR("Exception during SDK initialization: " + e.getMessage(), e);
        }
        INFO("SDK initialized, entering keep-alive mode to protect SDK from being killed...");
        while (!this.mIsStop) {
            try {
                Thread.sleep(300000L);
            } catch (InterruptedException unused) {
                INFO("Main thread interrupted during keep-alive sleep");
                if (this.mIsStop) {
                    INFO(" check thread stop: from=" + this.mStartTime + ", clz=" + this);
                }
            }
        }
        INFO(" check thread stop: from=" + this.mStartTime + ", clz=" + this);
    }

    private static boolean isLogEnabled() {
        try {
            return "1".equals((String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, "debug.hs.log.enabled", "0"));
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void printLog(String str) {
        if (isLogEnabled()) {
            Log.i("SZNS", String.valueOf(str));
        }
    }

    private boolean isAllowProcessRun() {
        boolean z = true;
        try {
            Process processExec = Runtime.getRuntime().exec("su");
            DataOutputStream dataOutputStream = new DataOutputStream(processExec.getOutputStream());
            dataOutputStream.writeBytes("dumpsys window  |grep \"mFocusedWindow\"\n");
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            DataInputStream dataInputStream = new DataInputStream(processExec.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                INFO("focusApp " + line);
                if (line.contains("com.valor.mfc.droid.tvapp.generic") || line.contains("com.mm.droid.livetv.tve")) {
                    INFO("The MFC OR TVE is running ...");
                    z = false;
                }
            }
            dataInputStream.close();
            dataOutputStream.close();
            processExec.waitFor();
        } catch (Exception unused) {
        }
        return z;
    }

    private static void showToast(final Context context, final String str) {
        new Handler(Looper.getMainLooper()).post(new Implementable("showToast") { // from class: com.hotota.p.d.MainThread.2
            @Override // com.hotota.p.d.common.async.Implementable
            protected void implement() {
                Toast.makeText(context, str, 1).show();
            }
        });
    }
}
