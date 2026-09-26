package com.baidu.mobstat;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CooperService implements ICooperService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static CooperService f3338a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HeadObject f3339b = new HeadObject();

    private static String a(Context context) {
        String strK = bb.k(context);
        return !TextUtils.isEmpty(strK) ? strK.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "") : strK;
    }

    private static String b(Context context) {
        String strJ = bb.j(context);
        return !TextUtils.isEmpty(strJ) ? strJ.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "") : strJ;
    }

    private static String c(Context context) {
        String strM = bb.m(context);
        return !TextUtils.isEmpty(strM) ? strM.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "") : strM;
    }

    private String d(Context context) {
        String strE = av.a().e(context);
        if (!TextUtils.isEmpty(strE) && !strE.equals(Config.NULL_DEVICE_ID)) {
            return strE;
        }
        String str = "hol" + (new Date().getTime() + "").hashCode() + "mes";
        av.a().a(context, str);
        return str;
    }

    private String e(Context context) {
        try {
            if (this.f3339b.l == null || this.f3339b.l.equals("")) {
                boolean zG = av.a().g(context);
                if (zG) {
                    this.f3339b.l = av.a().f(context);
                }
                if (!zG || this.f3339b.l == null || this.f3339b.l.equals("")) {
                    this.f3339b.l = bb.a(context, Config.CHANNEL_META_NAME);
                }
            }
        } catch (Exception unused) {
        }
        return this.f3339b.l;
    }

    public static synchronized CooperService instance() {
        if (f3338a == null) {
            f3338a = new CooperService();
        }
        return f3338a;
    }

    @Override // com.baidu.mobstat.ICooperService
    public boolean checkCellLocationSetting(Context context) {
        return "true".equalsIgnoreCase(bb.a(context, Config.GET_CELL_LOCATION));
    }

    @Override // com.baidu.mobstat.ICooperService
    public boolean checkGPSLocationSetting(Context context) {
        return "true".equals(bb.a(context, Config.GET_GPS_LOCATION));
    }

    @Override // com.baidu.mobstat.ICooperService
    public boolean checkWifiLocationSetting(Context context) {
        return "true".equalsIgnoreCase(bb.a(context, Config.GET_WIFI_LOCATION));
    }

    public void enableDeviceMac(Context context, boolean z) {
        av.a().d(context, z);
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getAppChannel(Context context) {
        return e(context);
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getAppKey(Context context) {
        HeadObject headObject = this.f3339b;
        if (headObject.f3366e == null) {
            headObject.f3366e = bb.a(context, Config.APPKEY_META_NAME);
        }
        return this.f3339b.f3366e;
    }

    @Override // com.baidu.mobstat.ICooperService
    public int getAppVersionCode(Context context) {
        HeadObject headObject = this.f3339b;
        if (headObject.g == -1) {
            headObject.g = bb.f(context);
        }
        return this.f3339b.g;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getAppVersionName(Context context) {
        if (TextUtils.isEmpty(this.f3339b.h)) {
            this.f3339b.h = bb.g(context);
        }
        return this.f3339b.h;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getCUID(Context context, boolean z) {
        av.a().b(context, "");
        String str = this.f3339b.f3367f;
        if (str == null || "".equalsIgnoreCase(str)) {
            try {
                this.f3339b.f3367f = bc.a(context);
                Matcher matcher = Pattern.compile("\\s*|\t|\r|\n").matcher(this.f3339b.f3367f);
                this.f3339b.f3367f = matcher.replaceAll("");
                this.f3339b.f3367f = getSecretValue(this.f3339b.f3367f);
            } catch (Exception unused) {
            }
        }
        if (z) {
            return this.f3339b.f3367f;
        }
        try {
            String str2 = this.f3339b.f3367f;
            if (TextUtils.isEmpty(str2)) {
                return null;
            }
            return new String(ar.b.b(1, au.a(str2.getBytes())));
        } catch (Exception unused2) {
            return null;
        }
    }

    public String getDevicImei(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getDeviceId(TelephonyManager telephonyManager, Context context) {
        if (!TextUtils.isEmpty(this.f3339b.i)) {
            return this.f3339b.i;
        }
        if (av.a().i(context)) {
            this.f3339b.i = getMacIdForTv(context);
            return this.f3339b.i;
        }
        String strP = av.a().p(context);
        if (!TextUtils.isEmpty(strP)) {
            HeadObject headObject = this.f3339b;
            headObject.i = strP;
            return headObject.i;
        }
        if (telephonyManager == null) {
            return this.f3339b.i;
        }
        Pattern patternCompile = Pattern.compile("\\s*|\t|\r|\n");
        try {
            String deviceId = telephonyManager.getDeviceId();
            if (deviceId != null) {
                strP = patternCompile.matcher(deviceId).replaceAll("");
            }
        } catch (Exception unused) {
        }
        if (strP == null || strP.equals(Config.NULL_DEVICE_ID)) {
            strP = a(context);
        }
        if (bb.u(context) && (TextUtils.isEmpty(strP) || strP.equals(Config.NULL_DEVICE_ID))) {
            try {
                strP = c(context);
            } catch (Exception unused2) {
            }
        }
        if (TextUtils.isEmpty(strP) || strP.equals(Config.NULL_DEVICE_ID)) {
            strP = d(context);
        }
        HeadObject headObject2 = this.f3339b;
        headObject2.i = strP;
        headObject2.i = getSecretValue(headObject2.i);
        return this.f3339b.i;
    }

    public HeadObject getHeadObject() {
        return this.f3339b;
    }

    public JSONObject getHeaderExt(Context context) {
        String strK = av.a().k(context);
        if (!TextUtils.isEmpty(strK)) {
            try {
                return new JSONObject(strK);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getHost() {
        return Config.LOG_SEND_URL;
    }

    public String getLastUserId(Context context) {
        return av.a().o(context);
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getLinkedWay(Context context) {
        if (TextUtils.isEmpty(this.f3339b.r)) {
            this.f3339b.r = bb.q(context);
        }
        return this.f3339b.r;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getMTJSDKVersion() {
        return "3.9.7.0";
    }

    public String getMacAddress(Context context, boolean z) {
        String strReplace = Config.DEF_MAC_ID.replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
        if (!z && android.os.Build.VERSION.SDK_INT >= 23) {
            return getSecretValue(strReplace);
        }
        if (!TextUtils.isEmpty(this.f3339b.s)) {
            return this.f3339b.s;
        }
        String strH = av.a().h(context);
        if (!TextUtils.isEmpty(strH)) {
            HeadObject headObject = this.f3339b;
            headObject.s = strH;
            return headObject.s;
        }
        String strA = a(context, z);
        if (TextUtils.isEmpty(strA) || strReplace.equals(strA)) {
            HeadObject headObject2 = this.f3339b;
            headObject2.s = "";
            return headObject2.s;
        }
        this.f3339b.s = getSecretValue(strA);
        av.a().e(context, this.f3339b.s);
        return this.f3339b.s;
    }

    public String getMacIdForTv(Context context) {
        if (!TextUtils.isEmpty(this.f3339b.t)) {
            return this.f3339b.t;
        }
        String strJ = av.a().j(context);
        if (!TextUtils.isEmpty(strJ)) {
            HeadObject headObject = this.f3339b;
            headObject.t = strJ;
            return headObject.t;
        }
        String strC = bb.c(1, context);
        if (TextUtils.isEmpty(strC)) {
            HeadObject headObject2 = this.f3339b;
            headObject2.t = "";
            return headObject2.t;
        }
        this.f3339b.t = strC;
        av.a().f(context, strC);
        return this.f3339b.t;
    }

    public String getManufacturer() {
        if (TextUtils.isEmpty(this.f3339b.o)) {
            this.f3339b.o = android.os.Build.MANUFACTURER;
        }
        return this.f3339b.o;
    }

    public String getOSSysVersion() {
        if (TextUtils.isEmpty(this.f3339b.f3364c)) {
            this.f3339b.f3364c = android.os.Build.VERSION.RELEASE;
        }
        return this.f3339b.f3364c;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getOSVersion() {
        if (TextUtils.isEmpty(this.f3339b.f3363b)) {
            this.f3339b.f3363b = Integer.toString(android.os.Build.VERSION.SDK_INT);
        }
        return this.f3339b.f3363b;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getOperator(TelephonyManager telephonyManager) {
        if (TextUtils.isEmpty(this.f3339b.m)) {
            this.f3339b.m = telephonyManager.getNetworkOperator();
        }
        return this.f3339b.m;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getPhoneModel() {
        if (TextUtils.isEmpty(this.f3339b.n)) {
            this.f3339b.n = android.os.Build.MODEL;
        }
        return this.f3339b.n;
    }

    public String getPlainDeviceIdForCar(Context context) throws Throwable {
        String strOptUUID = CarUUID.optUUID(context);
        if (TextUtils.isEmpty(strOptUUID)) {
            strOptUUID = d(context);
        }
        return TextUtils.isEmpty(strOptUUID) ? "" : strOptUUID;
    }

    public JSONObject getPushId(Context context) {
        String strL = av.a().l(context);
        if (!TextUtils.isEmpty(strL)) {
            try {
                return new JSONObject(strL);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getSecretValue(String str) {
        return ar.b.c(1, str.getBytes());
    }

    @Override // com.baidu.mobstat.ICooperService
    public int getTagValue() {
        return 1;
    }

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String getUserId(Context context) {
        return av.a().n(context);
    }

    @Override // com.baidu.mobstat.ICooperService
    public void installHeader(Context context, JSONObject jSONObject) {
        this.f3339b.installHeader(context, jSONObject);
    }

    public boolean isDeviceMacEnabled(Context context) {
        return av.a().m(context);
    }

    public void resetHeadSign() {
        this.f3339b.z = instance().getUUID();
    }

    public void setAppVersionName(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f3339b.h = str;
    }

    public void setHeaderExt(Context context, ExtraInfo extraInfo) {
        String str;
        JSONObject jSONObject = new JSONObject();
        if (extraInfo != null) {
            jSONObject = extraInfo.dumpToJson();
        }
        this.f3339b.setHeaderExt(jSONObject);
        av.a().g(context, jSONObject.toString());
        if (extraInfo != null) {
            str = "Set global ExtraInfo: " + jSONObject;
        } else {
            str = "Clear global ExtraInfo";
        }
        am.c().a(str);
    }

    public void setLastUserId(Context context, String str) {
        av.a().j(context, str);
    }

    public void setPushId(Context context, String str, String str2, String str3) {
        String str4;
        JSONObject pushId = getPushId(context);
        if (pushId == null) {
            pushId = new JSONObject();
        }
        try {
            if (TextUtils.isEmpty(str3)) {
                pushId.remove(str);
            } else {
                pushId.put(str, str3);
            }
        } catch (Exception unused) {
        }
        this.f3339b.setPushInfo(pushId);
        av.a().h(context, pushId.toString());
        if (str3 != null) {
            str4 = "Set platform:" + str2 + " pushId: " + str3;
        } else {
            str4 = "Clear platform:" + str2 + " pushId";
        }
        am.c().a(str4);
    }

    public void setStartType(boolean z) {
        this.f3339b.setStartType(z);
    }

    public void setUserId(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        av.a().i(context, str);
        this.f3339b.setUserId(str);
        am.c().a("Set user id " + str);
    }

    public void setUserProperty(Context context, Map<String, String> map) {
        boolean z;
        JSONObject jSONObject = new JSONObject();
        try {
            if (map == null) {
                av.a().l(context, "");
                this.f3339b.setUserProperty("");
                return;
            }
            if (map.size() > 100) {
                am.c().c("[WARNING] setUserProperty failed,map size can not over 100 !");
                return;
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                JSONArray jSONArray = new JSONArray();
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(key) && value != null) {
                    if (key.length() <= 256 && (TextUtils.isEmpty(value) || value.length() <= 256)) {
                        jSONArray.put(value);
                        jSONArray.put("1");
                        jSONObject.put(key, jSONArray);
                    }
                    am.c().c("[WARNING] setUserProperty failed,key or value can not over 256 bytes !");
                    return;
                }
                am.c().c("[WARNING] setUserProperty failed,key or value can not null !");
                return;
            }
            z = true;
            if (z) {
                av.a().l(context, jSONObject.toString());
                this.f3339b.setUserProperty(jSONObject.toString());
            }
        } catch (Exception e2) {
            am.c().c("[Exception] " + e2.getMessage());
            e2.printStackTrace();
            z = false;
        }
    }

    public void setZid(String str) {
    }

    private String a(Context context, boolean z) {
        String strA;
        if (z) {
            strA = b(context);
        } else {
            strA = a(context);
        }
        return TextUtils.isEmpty(strA) ? "" : strA;
    }
}
