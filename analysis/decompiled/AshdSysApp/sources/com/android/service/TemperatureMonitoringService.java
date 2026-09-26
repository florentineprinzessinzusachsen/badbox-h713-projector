package com.android.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemProperties;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/* JADX INFO: loaded from: classes.dex */
public class TemperatureMonitoringService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f46a;
    public int c;
    public AlertDialog f;
    public int b = 0;
    public final Handler d = new Handler();
    public final File e = new File("/sys/class/tempcontrolpwm/get_temp");
    public final Runnable g = new a();

    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Code duplicated, block: B:25:0x008e  */
        /* JADX WARN: Code duplicated, block: B:27:0x0094  */
        /* JADX WARN: Code duplicated, block: B:38:0x00b8  */
        /* JADX WARN: Code duplicated, block: B:40:0x00be  */
        /* JADX WARN: Code duplicated, block: B:42:0x00d0 A[PHI: r0
          0x00d0: PHI (r0v21 com.android.service.TemperatureMonitoringService) = (r0v20 com.android.service.TemperatureMonitoringService), (r0v26 com.android.service.TemperatureMonitoringService) binds: [B:41:0x00ce, B:37:0x00b5] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:43:0x00dd  */
        /* JADX WARN: Code duplicated, block: B:45:0x00e1  */
        /* JADX WARN: Code duplicated, block: B:47:0x00ec  */
        /* JADX WARN: Code duplicated, block: B:53:0x0103  */
        /* JADX WARN: Code duplicated, block: B:54:0x0109 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:55:0x010b  */
        /* JADX WARN: Code duplicated, block: B:56:0x0110  */
        @Override // java.lang.Runnable
        public void run() {
            int i;
            TemperatureMonitoringService temperatureMonitoringService;
            TemperatureMonitoringService temperatureMonitoringService2;
            TemperatureMonitoringService temperatureMonitoringService3;
            AlertDialog alertDialog;
            TemperatureMonitoringService temperatureMonitoringService4;
            TemperatureMonitoringService temperatureMonitoringService5;
            int i2;
            Handler handler;
            long j;
            TemperatureMonitoringService temperatureMonitoringService6 = TemperatureMonitoringService.this;
            if (temperatureMonitoringService6.e.exists()) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(temperatureMonitoringService6.e));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        } else {
                            sb.append(line);
                        }
                    }
                    bufferedReader.close();
                    String string = sb.toString();
                    if (string.contains("unknow")) {
                        Log.i("TemperatureMonitoring", "请检查传感器是否正常工作");
                    } else {
                        String strSubstring = string.substring(string.indexOf("temp:") + 5);
                        if (strSubstring.contains(">")) {
                            strSubstring = strSubstring.substring(1);
                        }
                        Log.d("TemperatureMonitoring", "str_temperature:" + strSubstring);
                        i = Integer.parseInt(strSubstring);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                temperatureMonitoringService = TemperatureMonitoringService.this;
                if (i > temperatureMonitoringService.f46a || temperatureMonitoringService.c < 5) {
                    temperatureMonitoringService2 = TemperatureMonitoringService.this;
                    if (i > temperatureMonitoringService2.f46a || temperatureMonitoringService2.c != 4) {
                        temperatureMonitoringService3 = TemperatureMonitoringService.this;
                        if (i > temperatureMonitoringService3.f46a) {
                            temperatureMonitoringService3.f.setMessage("警告！机器温度过高，请检查清理进出风口灰尘，如还出现警告请联系售后。");
                            temperatureMonitoringService4 = TemperatureMonitoringService.this;
                            temperatureMonitoringService4.b++;
                            if (temperatureMonitoringService4.b > 1) {
                            }
                        } else if (temperatureMonitoringService3.b > 1) {
                            Log.i("TemperatureMonitoring", "温度已降低");
                            alertDialog = TemperatureMonitoringService.this.f;
                            if (alertDialog != null && alertDialog.isShowing()) {
                                TemperatureMonitoringService.this.f.dismiss();
                            }
                            TemperatureMonitoringService.this.c = 0;
                        }
                    } else {
                        AlertDialog alertDialog2 = temperatureMonitoringService2.f;
                        if (alertDialog2 != null && alertDialog2.isShowing()) {
                            TemperatureMonitoringService.this.f.dismiss();
                        }
                        AlertDialog alertDialog3 = TemperatureMonitoringService.this.f;
                        if (alertDialog3 != null) {
                            alertDialog3.setMessage("警告！机器温度过高，请检查清理进出风口灰尘，如还出现警告请联系售后。一分钟后温度未降低将会自动关机");
                        }
                        temperatureMonitoringService4 = TemperatureMonitoringService.this;
                    }
                    temperatureMonitoringService4.a(temperatureMonitoringService4.f);
                    TemperatureMonitoringService.this.c++;
                } else {
                    temperatureMonitoringService.a();
                    Log.i("TemperatureMonitoring", "开始关机");
                }
                temperatureMonitoringService5 = TemperatureMonitoringService.this;
                i2 = temperatureMonitoringService5.b;
                if (i2 == 1) {
                    handler = temperatureMonitoringService5.d;
                    j = 60000;
                } else if (i2 > 1) {
                    handler = temperatureMonitoringService5.d;
                    j = 30000;
                } else {
                    handler = temperatureMonitoringService5.d;
                    j = 5000;
                }
                handler.postDelayed(this, j);
            }
            Log.i("TemperatureMonitoring", "设备不支持，文件不存在");
            i = 0;
            temperatureMonitoringService = TemperatureMonitoringService.this;
            if (i > temperatureMonitoringService.f46a) {
                temperatureMonitoringService2 = TemperatureMonitoringService.this;
                if (i > temperatureMonitoringService2.f46a) {
                    temperatureMonitoringService3 = TemperatureMonitoringService.this;
                    if (i > temperatureMonitoringService3.f46a) {
                        temperatureMonitoringService3.f.setMessage("警告！机器温度过高，请检查清理进出风口灰尘，如还出现警告请联系售后。");
                        temperatureMonitoringService4 = TemperatureMonitoringService.this;
                        temperatureMonitoringService4.b++;
                        if (temperatureMonitoringService4.b > 1) {
                            temperatureMonitoringService4.a(temperatureMonitoringService4.f);
                            TemperatureMonitoringService.this.c++;
                        }
                    } else if (temperatureMonitoringService3.b > 1) {
                        Log.i("TemperatureMonitoring", "温度已降低");
                        alertDialog = TemperatureMonitoringService.this.f;
                        if (alertDialog != null) {
                            TemperatureMonitoringService.this.f.dismiss();
                        }
                        TemperatureMonitoringService.this.c = 0;
                    }
                } else {
                    temperatureMonitoringService3 = TemperatureMonitoringService.this;
                    if (i > temperatureMonitoringService3.f46a) {
                        temperatureMonitoringService3.f.setMessage("警告！机器温度过高，请检查清理进出风口灰尘，如还出现警告请联系售后。");
                        temperatureMonitoringService4 = TemperatureMonitoringService.this;
                        temperatureMonitoringService4.b++;
                        if (temperatureMonitoringService4.b > 1) {
                            temperatureMonitoringService4.a(temperatureMonitoringService4.f);
                            TemperatureMonitoringService.this.c++;
                        }
                    } else if (temperatureMonitoringService3.b > 1) {
                        Log.i("TemperatureMonitoring", "温度已降低");
                        alertDialog = TemperatureMonitoringService.this.f;
                        if (alertDialog != null) {
                            TemperatureMonitoringService.this.f.dismiss();
                        }
                        TemperatureMonitoringService.this.c = 0;
                    }
                }
            } else {
                temperatureMonitoringService2 = TemperatureMonitoringService.this;
                if (i > temperatureMonitoringService2.f46a) {
                    temperatureMonitoringService3 = TemperatureMonitoringService.this;
                    if (i > temperatureMonitoringService3.f46a) {
                        temperatureMonitoringService3.f.setMessage("警告！机器温度过高，请检查清理进出风口灰尘，如还出现警告请联系售后。");
                        temperatureMonitoringService4 = TemperatureMonitoringService.this;
                        temperatureMonitoringService4.b++;
                        if (temperatureMonitoringService4.b > 1) {
                            temperatureMonitoringService4.a(temperatureMonitoringService4.f);
                            TemperatureMonitoringService.this.c++;
                        }
                    } else if (temperatureMonitoringService3.b > 1) {
                        Log.i("TemperatureMonitoring", "温度已降低");
                        alertDialog = TemperatureMonitoringService.this.f;
                        if (alertDialog != null) {
                            TemperatureMonitoringService.this.f.dismiss();
                        }
                        TemperatureMonitoringService.this.c = 0;
                    }
                } else {
                    temperatureMonitoringService3 = TemperatureMonitoringService.this;
                    if (i > temperatureMonitoringService3.f46a) {
                        temperatureMonitoringService3.f.setMessage("警告！机器温度过高，请检查清理进出风口灰尘，如还出现警告请联系售后。");
                        temperatureMonitoringService4 = TemperatureMonitoringService.this;
                        temperatureMonitoringService4.b++;
                        if (temperatureMonitoringService4.b > 1) {
                            temperatureMonitoringService4.a(temperatureMonitoringService4.f);
                            TemperatureMonitoringService.this.c++;
                        }
                    } else if (temperatureMonitoringService3.b > 1) {
                        Log.i("TemperatureMonitoring", "温度已降低");
                        alertDialog = TemperatureMonitoringService.this.f;
                        if (alertDialog != null) {
                            TemperatureMonitoringService.this.f.dismiss();
                        }
                        TemperatureMonitoringService.this.c = 0;
                    }
                }
            }
            temperatureMonitoringService5 = TemperatureMonitoringService.this;
            i2 = temperatureMonitoringService5.b;
            if (i2 == 1) {
                handler = temperatureMonitoringService5.d;
                j = 60000;
            } else if (i2 > 1) {
                handler = temperatureMonitoringService5.d;
                j = 30000;
            } else {
                handler = temperatureMonitoringService5.d;
                j = 5000;
            }
            handler.postDelayed(this, j);
        }
    }

    public final void a() {
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        startActivity(intent);
    }

    public final void a(AlertDialog alertDialog) {
        if (alertDialog == null || alertDialog.getWindow() == null) {
            return;
        }
        alertDialog.getWindow().setType(2003);
        alertDialog.show();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (!this.e.exists()) {
            Log.i("TemperatureMonitoring", "节点不存在,关闭服务");
            stopSelf();
        } else {
            Log.i("TemperatureMonitoring", "温度监控已启动");
            this.f = new AlertDialog.Builder(this).setTitle("温度监控").setNeutralButton("确定", new a.a.a.a(this)).create();
            this.f46a = SystemProperties.getInt("persist.sys.abnormaltemperature", 54);
            this.d.postDelayed(this.g, 5000L);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }
}
