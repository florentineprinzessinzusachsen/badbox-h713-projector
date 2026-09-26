package com.baidu.mobstat;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HeadObject {
    JSONObject A;
    JSONObject B;
    String C;
    int D;
    String F;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    String f3363b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f3364c;
    String h;
    String i;
    int j;
    int k;
    String m;
    String n;
    String o;
    String p;
    String q;
    String r;
    String s;
    String t;
    String u;
    String v;
    String w;
    String x;
    String y;
    String z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f3362a = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f3365d = "0";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String f3366e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    String f3367f = null;
    int g = -1;
    String l = null;
    String E = "";

    private synchronized void a(Context context) {
        if (this.f3362a) {
            return;
        }
        at.e(context, "android.permission.READ_PHONE_STATE");
        at.e(context, "android.permission.INTERNET");
        at.e(context, "android.permission.ACCESS_NETWORK_STATE");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.f3363b = CooperService.instance().getOSVersion();
        this.f3364c = CooperService.instance().getOSSysVersion();
        this.n = CooperService.instance().getPhoneModel();
        this.o = CooperService.instance().getManufacturer();
        this.z = CooperService.instance().getUUID();
        this.A = CooperService.instance().getHeaderExt(context);
        this.B = CooperService.instance().getPushId(context);
        this.i = CooperService.instance().getDeviceId(telephonyManager, context);
        this.f3365d = av.a().i(context) ? "1" : "0";
        if (bb.u(context)) {
            this.f3365d = "2";
        }
        this.f3365d += "-0";
        try {
            this.s = CooperService.instance().getMacAddress(context, CooperService.instance().isDeviceMacEnabled(context));
        } catch (Exception unused) {
        }
        try {
            this.u = bb.f(1, context);
        } catch (Exception unused2) {
        }
        try {
            this.v = bb.a(context, 1);
        } catch (Exception unused3) {
        }
        this.f3367f = CooperService.instance().getCUID(context, true);
        try {
            this.m = CooperService.instance().getOperator(telephonyManager);
        } catch (Exception unused4) {
        }
        try {
            this.j = bb.c(context);
            this.k = bb.d(context);
            if (context.getResources().getConfiguration().orientation == 2) {
                this.j ^= this.k;
                this.k = this.j ^ this.k;
                this.j ^= this.k;
            }
        } catch (Exception unused5) {
        }
        this.l = CooperService.instance().getAppChannel(context);
        this.f3366e = CooperService.instance().getAppKey(context);
        try {
            this.g = CooperService.instance().getAppVersionCode(context);
            this.h = CooperService.instance().getAppVersionName(context);
        } catch (Exception unused6) {
        }
        try {
            if (CooperService.instance().checkCellLocationSetting(context)) {
                this.p = bb.h(context);
            } else {
                this.p = "0_0_0";
            }
        } catch (Exception unused7) {
        }
        try {
            if (CooperService.instance().checkGPSLocationSetting(context)) {
                this.q = bb.i(context);
            } else {
                this.q = "";
            }
        } catch (Exception unused8) {
        }
        try {
            this.r = CooperService.instance().getLinkedWay(context);
        } catch (Exception unused9) {
        }
        this.w = bb.b();
        this.x = android.os.Build.BOARD;
        this.y = android.os.Build.BRAND;
        this.C = CooperService.instance().getUserId(context);
        this.f3362a = true;
        this.E = av.a().q(context);
    }

    public synchronized void installHeader(Context context, JSONObject jSONObject) {
        a(context);
        if (jSONObject.length() > 10) {
            return;
        }
        updateHeader(context, jSONObject);
    }

    public void setHeaderExt(JSONObject jSONObject) {
        this.A = jSONObject;
    }

    public void setPushInfo(JSONObject jSONObject) {
        this.B = jSONObject;
    }

    public void setStartType(boolean z) {
        if (z) {
            this.D = 1;
        } else {
            this.D = 0;
        }
    }

    public void setUserId(String str) {
        this.C = str;
    }

    public void setUserProperty(String str) {
        this.E = str;
    }

    public void setZid(String str) {
        this.F = str;
    }

    public synchronized void updateHeader(Context context, JSONObject jSONObject) {
        try {
            jSONObject.put(Config.OS, "Android");
            int i = 0;
            jSONObject.put("st", 0);
            jSONObject.put("s", this.f3363b == null ? "" : this.f3363b);
            jSONObject.put("sv", this.f3364c == null ? "" : this.f3364c);
            jSONObject.put(Config.APP_KEY, this.f3366e == null ? "" : this.f3366e);
            jSONObject.put(Config.PLATFORM_TYPE, this.f3365d == null ? "0" : this.f3365d);
            jSONObject.put("i", "");
            jSONObject.put("v", "3.9.7.0");
            jSONObject.put(Config.STAT_SDK_CHANNEL, 0);
            jSONObject.put("a", this.g);
            jSONObject.put("n", this.h == null ? "" : this.h);
            jSONObject.put("d", "");
            jSONObject.put("mc", this.s == null ? "" : this.s);
            jSONObject.put(Config.DEVICE_BLUETOOTH_MAC, this.u == null ? "" : this.u);
            jSONObject.put(Config.DEVICE_ID_SEC, this.i == null ? "" : this.i);
            jSONObject.put(Config.CUID_SEC, this.f3367f == null ? "" : this.f3367f);
            jSONObject.put(Config.SDK_TAG, 1);
            jSONObject.put(Config.DEVICE_WIDTH, this.j);
            jSONObject.put("h", this.k);
            jSONObject.put(Config.DEVICE_NAME, this.v == null ? "" : this.v);
            jSONObject.put("c", this.l == null ? "" : this.l);
            jSONObject.put(Config.OPERATOR, this.m == null ? "" : this.m);
            jSONObject.put(Config.MODEL, this.n == null ? "" : this.n);
            jSONObject.put(Config.MANUFACTURER, this.o == null ? "" : this.o);
            jSONObject.put(Config.CELL_LOCATION, this.p == null ? "" : this.p);
            jSONObject.put(Config.GPS_LOCATION, this.q == null ? "" : this.q);
            jSONObject.put("l", this.r == null ? "" : this.r);
            jSONObject.put("t", System.currentTimeMillis());
            jSONObject.put("pn", bb.h(1, context));
            jSONObject.put(Config.ROM, this.w == null ? "" : this.w);
            jSONObject.put(Config.DEVICE_BOARD, this.x == null ? "" : this.x);
            jSONObject.put(Config.DEVICE_BRAND, this.y == null ? "" : this.y);
            jSONObject.put(Config.TEST_DEVICE_ID, bb.b(context));
            if (context != null && context.getApplicationInfo() != null) {
                i = context.getApplicationInfo().targetSdkVersion;
            }
            jSONObject.put(Config.TARGET_SDK_VERSION, i);
            jSONObject.put(Config.USER_PROPERTY, this.E);
            if (!TextUtils.isEmpty(this.C)) {
                JSONObject jSONObject2 = !TextUtils.isEmpty(this.E) ? new JSONObject(this.E) : new JSONObject();
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(this.C);
                jSONArray.put("1");
                jSONObject2.put("uid_", jSONArray);
                jSONObject.put(Config.USER_PROPERTY, jSONObject2.toString());
            }
            jSONObject.put(Config.UID_CHANGE, "");
            jSONObject.put("at", "0");
            String strS = bb.s(context);
            jSONObject.put(Config.PROCESS_LABEL, strS);
            Object objT = TextUtils.isEmpty(strS) ? null : bb.t(context);
            if (objT == null) {
                objT = "";
            }
            jSONObject.put(Config.PROCESS_CLASS, objT);
            jSONObject.put("sign", this.z == null ? "" : this.z);
            if (this.A == null || this.A.length() == 0) {
                jSONObject.remove("ext");
            } else {
                jSONObject.put("ext", this.A);
            }
            if (this.B == null) {
                this.B = new JSONObject();
            }
            jSONObject.put("push", this.B);
            jSONObject.put(Config.CUSTOM_USER_ID, this.C);
            jSONObject.put(Config.START_TYPE, String.valueOf(this.D));
        } catch (Exception unused) {
        }
    }
}
