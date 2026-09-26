package com.hs.p.basic;

import android.content.Context;
import com.hs.p.common.id.CUID;
import com.hs.p.common.utils.DeviceUtils;
import com.hs.p.common.utils.ObjUtils;
import com.hs.p.common.utils.SystemUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class ParameterUtils {
    public static void setCommonParameter(Context context, JSONObject jSONObject) throws Exception {
        jSONObject.put("sdk_version", 3L);
        jSONObject.put("channel", ObjUtils.notNull(Media.getChannel(context)));
        jSONObject.put("dev_mfr", ObjUtils.notNull(DeviceUtils.getManufacturer()));
        jSONObject.put("dev_brand", ObjUtils.notNull(DeviceUtils.getBrand()));
        jSONObject.put("dev_model", ObjUtils.notNull(DeviceUtils.getModel()));
        jSONObject.put("dev_m1", ObjUtils.notNull(DeviceUtils.getAndroidId(context)));
        jSONObject.put("dev_uuid", ObjUtils.notNull(CUID.getString(context)));
        jSONObject.put("dev_aid", ObjUtils.notNull(DeviceUtils.getAndroidId(context)));
        jSONObject.put("dev_mac", ObjUtils.notNull(DeviceUtils.getMACAddress(context)));
        jSONObject.put("dev_ip", ObjUtils.notNull(SystemUtils.getIPAddress(context)));
        jSONObject.put("app_bundle", ObjUtils.notNull(SystemUtils.getSelfPackageName(context)));
        jSONObject.put("app_vn", "" + SystemUtils.getSelfVersionName(context));
        jSONObject.put("app_vc", "" + SystemUtils.getSelfVersionCode(context));
        jSONObject.put("os_lang", ObjUtils.notNull(SystemUtils.getLang()));
        jSONObject.put("os_version", ObjUtils.notNull(SystemUtils.getOSRelease()));
        jSONObject.put("os_api", "" + SystemUtils.getOSApiInt());
        jSONObject.put("os_display", ObjUtils.notNull(SystemUtils.getOSBuildDisplay()));
        jSONObject.put("os_incremental", ObjUtils.notNull(SystemUtils.getOSBuildIncremental()));
        jSONObject.put("net", ObjUtils.notNull(SystemUtils.getNetworkInfo(context)));
        jSONObject.put("sm", SystemUtils.isSecurityMode(context) ? "1" : "0");
        jSONObject.put("proxy_enabled", Porting.isProxyEnabled() ? "1" : "0");
        jSONObject.put("vpn_enabled", Porting.isVpnEnabled() ? "1" : "0");
        jSONObject.put("adb_enabled", Porting.isAdbEnabled(context) ? "1" : "0");
        jSONObject.put("wifi_adb_enabled", Porting.isWifiAdbEnabled(context) ? "1" : "0");
        jSONObject.put("dev_root", Porting.isDeviceRoot() ? "1" : "0");
    }
}
