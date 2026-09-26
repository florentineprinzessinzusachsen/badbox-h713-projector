package com.hs.p.q;

import com.hs.p.common.utils.JSONUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class ConfigBeanUtils {
    public static ConfigBean parse(JSONObject jSONObject) {
        ConfigBean configBean = new ConfigBean();
        configBean.silent = JSONUtils.getInt(jSONObject, "silent", 0);
        configBean.silent_to = JSONUtils.getLong(jSONObject, "silent_to", System.currentTimeMillis() + 259200000);
        configBean.check_security_mode = JSONUtils.getInt(jSONObject, "check_security_mode", 0);
        configBean.log_enable = JSONUtils.getInt(jSONObject, "log_enable", 0);
        configBean.api_hosts = JSONUtils.getString(jSONObject, "api_hosts", "");
        configBean.tracker_hosts = JSONUtils.getString(jSONObject, "tracker_hosts", "");
        configBean.periods = JSONUtils.getLong(jSONObject, "periods", 21600L);
        return configBean;
    }
}
