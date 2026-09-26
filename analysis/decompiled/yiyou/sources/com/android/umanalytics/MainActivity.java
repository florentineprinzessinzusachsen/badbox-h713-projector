package com.android.umanalytics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.R;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.umeng.analytics.MobclickAgent;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends Activity {
    /* JADX WARN: Code duplicated, block: B:4:0x0010  */
    private void a() {
        String str = "60:35:FD:9E:68:E9:20:C2:B5:B0:7C:F2:B5:10:F2:83:47:06:DE:15";
        if (TextUtils.equals(getPackageName(), "com.android.umanalytics")) {
            str = "27:19:6E:38:6B:87:5E:76:AD:F7:00:E7:EA:84:E4:C6:EE:E3:3D:FA";
        } else if (TextUtils.equals(getPackageName(), "com.android.umanalytics.ashd")) {
            str = "04:2F:44:65:7D:1D:8D:6B:42:50:8A:5B:3C:FD:15:65:67:E6:74:CE";
        } else if (!TextUtils.equals(getPackageName(), "com.android.umanalytics.javoda")) {
            if (TextUtils.equals(getPackageName(), "com.android.umanalytics.wanjiang")) {
                str = "27:19:6E:38:6B:87:5E:76:AD:F7:00:E7:EA:84:E4:C6:EE:E3:3D:FA";
            } else if (!TextUtils.equals(getPackageName(), "com.android.umanalytics.tianxing")) {
                if (TextUtils.equals(getPackageName(), "com.android.umanalytics.rger")) {
                    str = "41:79:1C:9B:8F:AF:15:E1:AC:D5:AA:F5:92:10:FD:42:46:7D:82:77";
                } else if (TextUtils.equals(getPackageName(), "com.android.umanalytics.asos")) {
                    str = "CD:B1:B7:56:85:FE:16:C2:7E:4C:45:59:54:43:AC:B0:1F:68:40:91";
                } else if (TextUtils.equals(getPackageName(), "com.android.umanalytics.yiyou")) {
                    str = "6E:40:BA:7F:F7:90:7D:40:C2:F1:6D:12:E4:63:E5:E4:A3:F1:5A:01";
                } else {
                    str = "27:19:6E:38:6B:87:5E:76:AD:F7:00:E7:EA:84:E4:C6:EE:E3:3D:FA";
                }
            }
        }
        if (TextUtils.equals(str, AppUtils.getAppSignatureSHA1())) {
            LogUtils.iTag("MainActivity", "校验签名成功");
            return;
        }
        LogUtils.eTag("MainActivity", "SHA1-PKG: " + str + "\nSHA1-CUR: " + AppUtils.getAppSignatureSHA1());
        LogUtils.eTag("MainActivity", "校验签名错误，上报到友盟...");
        MobclickAgent.onEvent(getApplicationContext(), "check_sig_error");
    }

    private void b() {
        LogUtils.i(AppUtils.getAppInfo().toString());
        LogUtils.i("pkgName=" + AppUtils.getAppPackageName() + ", appVersionCode=" + AppUtils.getAppVersionCode() + ", appVersionName=" + AppUtils.getAppVersionName());
    }

    private void c() {
        String str;
        Log.d("qzr", "uploadEvent");
        int i = SPUtils.getInstance().getInt("useTimeCount", 30);
        if (i <= 180) {
            str = "use_0_30_m";
        } else if (i > 180 && i <= 360) {
            str = "use_30_60_m";
        } else if (i > 360 && i <= 720) {
            str = "use_1_2_h";
        } else if (i <= 720 || i > 1080) {
            str = (i <= 1080 || i > 1440) ? "use_above_4_h" : "use_3_4_h";
        } else {
            str = "use_2_3_h";
        }
        int i2 = i * 10000;
        MobclickAgent.onEventValue(this, str, null, i2);
        MobclickAgent.onEventValue(this, "use_avg", null, i2);
        Log.d("qzr", "上报使用时长事件: " + i2);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(67108864);
        setContentView(R.layout.activity_main);
        LogUtils.i("启动Activity");
        App.b().f3199b = true;
        startService(new Intent(this, (Class<?>) MyService.class));
        LogUtils.i("启动Service");
        c();
        b();
        a();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("onDestroy");
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        LogUtils.i("onPause");
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        finish();
        LogUtils.i("onResume");
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        LogUtils.i("onStart");
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        LogUtils.i("onStop");
    }
}
