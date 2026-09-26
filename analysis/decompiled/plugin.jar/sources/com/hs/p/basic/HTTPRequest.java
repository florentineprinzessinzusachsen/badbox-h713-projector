package com.hs.p.basic;

import android.content.Context;
import com.hs.p.common.http.HTTPBuilder;
import com.hs.p.common.http.HTTPHelper;
import com.hs.p.common.http.HTTPResult;
import com.hs.p.common.utils.JSONUtils;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.ObjUtils;
import com.hs.p.common.utils.SystemUtils;
import com.hs.p.common.utils.TextUtils;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class HTTPRequest {
    public final String TAG;
    public final Context mContext;
    private List<String> mTargetHosts = null;
    private String mRequestPath = null;
    private String mRequestBody = null;
    private String mResponseBody = null;
    private String mResponseData = null;

    public HTTPRequest(Context context, String str) {
        this.mContext = context;
        this.TAG = str;
        SSLUtils.enableSSLIgnore();
    }

    private HTTPHelper createHelper() {
        String str;
        HTTPDNS.URLConvertResult uRLConvertResultConvertUrlToIP;
        String str2;
        String strExtractIP;
        SSLUtils.enableSSLIgnore();
        HTTPHelper hTTPHelperCreateHelper = HTTPBuilder.createHelper(this.mContext);
        List<String> list = this.mTargetHosts;
        if (!ObjUtils.empty(list) && (uRLConvertResultConvertUrlToIP = HTTPDNS.convertUrlToIP((str = this.mTargetHosts.get(0)))) != null && (str2 = uRLConvertResultConvertUrlToIP.url) != null && !str2.equals(str) && (strExtractIP = HTTPDNS.extractIP(uRLConvertResultConvertUrlToIP.url)) != null) {
            LOG.d(this.TAG, "HTTPDNS resolved: " + uRLConvertResultConvertUrlToIP.originalHost + " -> " + strExtractIP);
            hTTPHelperCreateHelper.setHttpDnsIP(strExtractIP);
        }
        hTTPHelperCreateHelper.setHosts(list);
        hTTPHelperCreateHelper.setPath(this.mRequestPath);
        HTTPBuilder.appendCommonParameters(this.mContext, hTTPHelperCreateHelper);
        String str3 = this.mRequestBody;
        if (str3 != null) {
            hTTPHelperCreateHelper.setRequestBody(str3.getBytes(StandardCharsets.UTF_8));
        }
        return hTTPHelperCreateHelper;
    }

    private boolean isNeedNewUrl(String str, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (str.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    private void parseResponseBody(byte[] bArr) throws Exception {
        this.mResponseBody = new String(bArr, StandardCharsets.UTF_8);
        JSONObject jSONObject = new JSONObject(this.mResponseBody);
        if (!CodeEnum.OK.codeEquals(JSONUtils.getInt(jSONObject, "code", -1))) {
            throw new Exception("remote server error(" + this.mResponseBody + ")");
        }
        if (jSONObject.has("data")) {
            String string = JSONUtils.getString(jSONObject, "data", "");
            if (TextUtils.empty(string)) {
                this.mResponseData = "";
            } else {
                this.mResponseData = EncryptUtils.decrypt(string, Constants.API_AESKEY);
            }
        }
    }

    private String printResponse(String str) {
        if (str.length() <= 256) {
            return str;
        }
        return "too large(" + str.length() + ")";
    }

    private void recordDomainFailure(String str) {
        boolean z;
        String str2;
        String str3;
        try {
            List<String> listDecrypt = EncryptUtils.decrypt(Hosts.defaultMasterApiHosts(this.mContext), Constants.HOST_AESKEY);
            List<String> listDecrypt2 = EncryptUtils.decrypt(Hosts.defaultSlaveApiHosts(this.mContext), Constants.HOST_AESKEY);
            List<String> listDecrypt3 = EncryptUtils.decrypt(Hosts.defaultMasterTrackerHosts(this.mContext), Constants.HOST_AESKEY);
            List<String> listDecrypt4 = EncryptUtils.decrypt(Hosts.defaultSlaveTrackerHosts(this.mContext), Constants.HOST_AESKEY);
            boolean z2 = false;
            boolean z3 = true;
            if (!ObjUtils.empty(listDecrypt)) {
                Iterator<String> it = listDecrypt.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (str.contains(it.next().replace("https://", "").replace("http://", "").replace("/", ""))) {
                        z = true;
                        break;
                    }
                }
            } else {
                z = false;
                break;
            }
            if (!z && !ObjUtils.empty(listDecrypt2)) {
                Iterator<String> it2 = listDecrypt2.iterator();
                while (it2.hasNext()) {
                    if (str.contains(it2.next().replace("https://", "").replace("http://", "").replace("/", ""))) {
                        z2 = true;
                        break;
                    }
                }
            }
            if (!z && !z2 && !ObjUtils.empty(listDecrypt3)) {
                Iterator<String> it3 = listDecrypt3.iterator();
                while (it3.hasNext()) {
                    if (str.contains(it3.next().replace("https://", "").replace("http://", "").replace("/", ""))) {
                        z = true;
                        break;
                    }
                }
            }
            if (!z && !z2 && !ObjUtils.empty(listDecrypt4)) {
                Iterator<String> it4 = listDecrypt4.iterator();
                do {
                    if (!it4.hasNext()) {
                        z3 = z2;
                        break;
                    }
                } while (!str.contains(it4.next().replace("https://", "").replace("http://", "").replace("/", "")));
            } else {
                z3 = z2;
                break;
            }
            if (z) {
                DomainFailureTracker.recordMasterFailure(this.mContext);
                str2 = this.TAG;
                str3 = "Master domain failure recorded for: " + str;
            } else {
                if (!z3) {
                    return;
                }
                DomainFailureTracker.recordSlaveFailure(this.mContext);
                str2 = this.TAG;
                str3 = "Slave domain failure recorded for: " + str;
            }
            LOG.w(str2, str3);
        } catch (Exception e) {
            LOG.e(this.TAG, "Error recording domain failure", e);
        }
    }

    private void recordDomainSuccess(String str) {
        boolean z;
        String str2;
        String str3;
        try {
            List<String> listDecrypt = EncryptUtils.decrypt(Hosts.defaultMasterApiHosts(this.mContext), Constants.HOST_AESKEY);
            List<String> listDecrypt2 = EncryptUtils.decrypt(Hosts.defaultSlaveApiHosts(this.mContext), Constants.HOST_AESKEY);
            List<String> listDecrypt3 = EncryptUtils.decrypt(Hosts.defaultMasterTrackerHosts(this.mContext), Constants.HOST_AESKEY);
            List<String> listDecrypt4 = EncryptUtils.decrypt(Hosts.defaultSlaveTrackerHosts(this.mContext), Constants.HOST_AESKEY);
            boolean z2 = false;
            boolean z3 = true;
            if (!ObjUtils.empty(listDecrypt)) {
                Iterator<String> it = listDecrypt.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (str.contains(it.next().replace("https://", "").replace("http://", "").replace("/", ""))) {
                        z = true;
                        break;
                    }
                }
            } else {
                z = false;
                break;
            }
            if (!z && !ObjUtils.empty(listDecrypt2)) {
                Iterator<String> it2 = listDecrypt2.iterator();
                while (it2.hasNext()) {
                    if (str.contains(it2.next().replace("https://", "").replace("http://", "").replace("/", ""))) {
                        z2 = true;
                        break;
                    }
                }
            }
            if (!z && !z2 && !ObjUtils.empty(listDecrypt3)) {
                Iterator<String> it3 = listDecrypt3.iterator();
                while (it3.hasNext()) {
                    if (str.contains(it3.next().replace("https://", "").replace("http://", "").replace("/", ""))) {
                        z = true;
                        break;
                    }
                }
            }
            if (!z && !z2 && !ObjUtils.empty(listDecrypt4)) {
                Iterator<String> it4 = listDecrypt4.iterator();
                do {
                    if (!it4.hasNext()) {
                        z3 = z2;
                        break;
                    }
                } while (!str.contains(it4.next().replace("https://", "").replace("http://", "").replace("/", "")));
            } else {
                z3 = z2;
                break;
            }
            if (z) {
                DomainFailureTracker.recordMasterSuccess(this.mContext);
                str2 = this.TAG;
                str3 = "Master domain success recorded for: " + str;
            } else {
                if (!z3) {
                    return;
                }
                DomainFailureTracker.recordSlaveSuccess(this.mContext);
                str2 = this.TAG;
                str3 = "Slave domain success recorded for: " + str;
            }
            LOG.i(str2, str3);
        } catch (Exception e) {
            LOG.e(this.TAG, "Error recording domain success", e);
        }
    }

    public String getResponseData() {
        return this.mResponseData;
    }

    public void jsonExecute() throws Exception {
        String str;
        StringBuilder sb;
        String strPrintResponse;
        if (ObjUtils.empty(this.mTargetHosts)) {
            throw new Exception("empty hosts");
        }
        if (!SystemUtils.isNetworkAvailable(this.mContext)) {
            throw new Exception("network unavailable");
        }
        HTTPHelper hTTPHelperCreateHelper = createHelper();
        long jCurrentTimeMillis = System.currentTimeMillis();
        HTTPResult hTTPResultPost = hTTPHelperCreateHelper.post();
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        if (!hTTPResultPost.codeEquals(0)) {
            recordDomainFailure(hTTPResultPost.realRequestUrl);
            if (isNeedNewUrl(hTTPResultPost.realRequestUrl, Hosts.apiHosts(this.mContext))) {
                String lastCheckHost = Settings.getLastCheckHost(this.mContext);
                if (lastCheckHost == null || !hTTPResultPost.realRequestUrl.contains(lastCheckHost)) {
                    LOG.d(this.TAG, "Primary/backup domain failed, will try next in sequence...");
                } else {
                    LOG.d(this.TAG, "Random domain failed, generating new random domain...");
                    HostUtils.getSlaveAvailableHost(this.mContext);
                }
            }
            throw new Exception("http(s)(POST) failed: " + hTTPResultPost.error);
        }
        recordDomainSuccess(hTTPResultPost.realRequestUrl);
        byte[] bArr = hTTPResultPost.responseBody;
        if (bArr == null || bArr.length <= 0) {
            str = this.TAG;
            sb = new StringBuilder();
            sb.append("[");
            sb.append(this.mRequestPath);
            sb.append("] http(POST) done, host=");
            sb.append(hTTPResultPost.targetHost);
            sb.append(", ms=");
            sb.append(jCurrentTimeMillis2);
            strPrintResponse = ", resp=none";
        } else {
            parseResponseBody(bArr);
            str = this.TAG;
            sb = new StringBuilder();
            sb.append("[");
            sb.append(this.mRequestPath);
            sb.append("] http(POST) done, host=");
            sb.append(hTTPResultPost.targetHost);
            sb.append(", ms=");
            sb.append(jCurrentTimeMillis2);
            sb.append(", resp=");
            strPrintResponse = printResponse(this.mResponseBody);
        }
        sb.append(strPrintResponse);
        LOG.d(str, sb.toString());
    }

    protected void setCommonParameter(Context context, JSONObject jSONObject) throws Exception {
        ParameterUtils.setCommonParameter(context, jSONObject);
    }

    public void setRequestBody(String str) {
        this.mRequestBody = str;
    }

    public void setRequestPath(String str) {
        this.mRequestPath = str;
    }

    public void setTargetHosts(List<String> list) {
        if (ObjUtils.empty(list)) {
            throw new IllegalArgumentException("empty target hosts");
        }
        this.mTargetHosts = list;
    }
}
