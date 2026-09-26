package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import com.baidu.mobstat.Config;
import com.blankj.utilcode.constant.TimeConstants;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SystemLayerUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3860a = "info";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3861b = "stat";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f3862c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static HandlerThread f3863d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Context f3864e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static int f3865f = 0;
    private static int g = 0;
    private static int h = 0;
    private static int i = 1;
    private static long j = 0;
    private static long k = 0;
    private static final int l = 40;
    private static final int m = 50000;
    private static SensorManager n;
    private static ArrayList<float[]> o = new ArrayList<>();
    private static SensorEventListener p = new SensorEventListener() { // from class: com.umeng.commonsdk.internal.utils.j.1
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (j.g < 15) {
                j.c();
                return;
            }
            if (j.f3865f < 20) {
                j.e();
                j.o.add(sensorEvent.values.clone());
            }
            if (j.f3865f == 20) {
                j.e();
                if (j.i == 1) {
                    long unused = j.j = System.currentTimeMillis();
                }
                if (j.i == 2) {
                    long unused2 = j.k = System.currentTimeMillis();
                }
                j.h();
                j.l();
            }
        }
    };

    /* JADX INFO: compiled from: SystemLayerUtil.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3868a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3869b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f3870c;
    }

    static /* synthetic */ int c() {
        int i2 = g;
        g = i2 + 1;
        return i2;
    }

    static /* synthetic */ int e() {
        int i2 = f3865f;
        f3865f = i2 + 1;
        return i2;
    }

    static /* synthetic */ int h() {
        int i2 = i;
        i = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void l() {
        SensorManager sensorManager = n;
        if (sensorManager != null) {
            sensorManager.unregisterListener(p);
        }
        if (o.size() == 40) {
            f(f3864e);
            ArrayList<float[]> arrayList = o;
            if (arrayList != null) {
                arrayList.clear();
            }
            HandlerThread handlerThread = f3863d;
            if (handlerThread != null) {
                handlerThread.quit();
                f3863d = null;
            }
            f3864e = null;
            f3862c = false;
        }
    }

    public static JSONArray c(Context context) {
        String string;
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences == null || (string = sharedPreferences.getString(f3861b, null)) == null) {
            return null;
        }
        try {
            return new JSONArray(string);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static void d(Context context) {
        if (context == null) {
            return;
        }
        context.getApplicationContext().getSharedPreferences("info", 0).edit().remove(f3861b).commit();
    }

    public static List<a> e(Context context) {
        CameraManager cameraManager;
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.CAMERA")) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            if (Build.VERSION.SDK_INT >= 21 && (cameraManager = (CameraManager) context.getSystemService("camera")) != null) {
                for (String str : cameraManager.getCameraIdList()) {
                    Size size = (Size) cameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
                    if (size != null) {
                        a aVar = new a();
                        aVar.f3868a = size.getWidth();
                        aVar.f3869b = size.getHeight();
                        aVar.f3870c = System.currentTimeMillis();
                        arrayList.add(aVar);
                    }
                }
            }
        } catch (Exception unused) {
            ULog.i("camera access exception");
        }
        return arrayList;
    }

    private static void f(Context context) {
        int i2;
        if (context == null) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i3 = 0; i3 < 2; i3++) {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                int i4 = 20;
                if (i3 == 1) {
                    i2 = 40;
                } else {
                    i4 = 0;
                    i2 = 20;
                }
                while (i4 < i2) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(Config.EVENT_HEAT_X, o.get(i4)[0]);
                    jSONObject2.put("y", o.get(i4)[1]);
                    jSONObject2.put("z", o.get(i4)[2]);
                    jSONArray2.put(jSONObject2);
                    i4++;
                }
                if (h == 4) {
                    jSONObject.put("g", jSONArray2);
                } else if (h == 1) {
                    jSONObject.put("a", jSONArray2);
                }
                if (i3 == 0) {
                    jSONObject.put("ts", j);
                } else {
                    jSONObject.put("ts", k);
                }
                jSONArray.put(jSONObject);
                UMWorkDispatch.sendEvent(context, com.umeng.commonsdk.internal.a.l, com.umeng.commonsdk.internal.b.a(context).a(), jSONArray.toString());
            }
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
        }
    }

    public static List<Sensor> a(Context context) {
        SensorManager sensorManager;
        if (context == null || (sensorManager = (SensorManager) context.getSystemService(com.umeng.commonsdk.proguard.e.aa)) == null) {
            return null;
        }
        return sensorManager.getSensorList(-1);
    }

    public static void b(Context context) {
        if (context == null || a()) {
            return;
        }
        f3862c = true;
        f3864e = context.getApplicationContext();
        String currentProcessName = UMFrUtils.getCurrentProcessName(context);
        String packageName = context.getPackageName();
        if (currentProcessName == null || !currentProcessName.equals(packageName)) {
            return;
        }
        n = (SensorManager) context.getSystemService(com.umeng.commonsdk.proguard.e.aa);
        SensorManager sensorManager = n;
        if (sensorManager != null) {
            final Sensor defaultSensor = sensorManager.getDefaultSensor(4);
            final Sensor defaultSensor2 = n.getDefaultSensor(1);
            if (defaultSensor != null) {
                h = 4;
                n.registerListener(p, defaultSensor, m);
            } else if (defaultSensor2 != null) {
                h = 1;
                n.registerListener(p, defaultSensor2, m);
            }
            int iNextInt = (new Random().nextInt(3) * TimeConstants.SEC) + 4000;
            f3863d = new HandlerThread("sensor_thread");
            f3863d.start();
            new Handler(f3863d.getLooper()).postDelayed(new Runnable() { // from class: com.umeng.commonsdk.internal.utils.j.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        int unused = j.f3865f = 0;
                        if (defaultSensor != null) {
                            j.n.registerListener(j.p, defaultSensor, j.m);
                        } else if (defaultSensor2 != null) {
                            j.n.registerListener(j.p, defaultSensor2, j.m);
                        }
                    } catch (Exception unused2) {
                        ULog.i("sensor exception");
                    }
                }
            }, iNextInt);
        }
    }

    public static synchronized boolean a() {
        return f3862c;
    }
}
