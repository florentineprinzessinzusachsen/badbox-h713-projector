package com.hs.p.q;

import android.content.Context;
import com.hs.p.basic.HTTPRequest;
import com.hs.p.basic.Hosts;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class GetConfigApi extends HTTPRequest {
    public GetConfigApi(Context context) {
        super(context, "GetConfigApi");
        setRequestPath("0x01/ov/x1");
        setTargetHosts(Hosts.apiHosts(context));
    }

    private String buildParameter() throws Exception {
        JSONObject jSONObject = new JSONObject();
        setCommonParameter(this.mContext, jSONObject);
        return jSONObject.toString();
    }

    private ConfigBean handleResponse() throws Exception {
        String responseData = getResponseData();
        LOG.i(this.TAG, "get config: response=" + responseData);
        if (TextUtils.empty(responseData)) {
            return null;
        }
        return ConfigBeanUtils.parse(new JSONObject(responseData));
    }

    public ConfigBean request() throws Exception {
        String strBuildParameter = buildParameter();
        LOG.i(this.TAG, "get config: parameter=" + strBuildParameter);
        setRequestBody(strBuildParameter);
        jsonExecute();
        return handleResponse();
    }
}
