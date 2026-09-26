package com.hs.cld.da.t;

import android.content.Context;
import com.hs.cld.da.model.EventTypeEnum;
import com.hs.p.basic.HTTPRequest;
import com.hs.p.basic.Hosts;
import com.hs.p.basic.Media;
import com.hs.p.common.async.AsyncHandler;
import com.hs.p.common.async.Implementable;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.ObjUtils;
import com.hs.p.common.utils.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class Tracker extends HTTPRequest {
    private static final String TAG = "Tracker";
    private static AsyncHandler mAsyncHandler = AsyncHandler.create("tracker");
    private int mEventCode;
    private String mEventExtra;
    private String mEventMessage;
    private long mEventTime;
    private EventTypeEnum mEventType;
    private String mTaskId;
    private String mTrackerId;

    public Tracker(Context context) {
        super(context, TAG);
        this.mEventTime = 0L;
        this.mEventCode = 0;
        this.mEventMessage = "OK";
        setRequestPath("0x01/ov/x3");
        setTargetHosts(Hosts.trackerHosts(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String buildParameter() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("task_id", ObjUtils.notNull(this.mTaskId));
        jSONObject.put("tracker_id", ObjUtils.notNull(this.mTrackerId));
        jSONObject.put("tracker_by", ObjUtils.notNull(Media.getChannel(this.mContext)));
        EventTypeEnum eventTypeEnum = this.mEventType;
        if (eventTypeEnum != null) {
            jSONObject.put("event_type", eventTypeEnum.value());
        }
        jSONObject.put("event_time", "" + this.mEventTime);
        jSONObject.put("event_code", "" + this.mEventCode);
        jSONObject.put("event_message", strLengthOf(this.mEventMessage));
        if (!TextUtils.empty(this.mEventExtra)) {
            jSONObject.put("event_extra", strLengthOf(this.mEventExtra));
        }
        return jSONObject.toString();
    }

    private String strLengthOf(String str) {
        return (TextUtils.empty(str) || str.length() <= 128) ? ObjUtils.notNull(str) : str.substring(0, 128);
    }

    public Tracker setEventCode(int i) {
        this.mEventCode = i;
        return this;
    }

    public Tracker setEventExtra(String str) {
        this.mEventExtra = str;
        return this;
    }

    public Tracker setEventMessage(String str) {
        this.mEventMessage = str;
        return this;
    }

    public Tracker setEventTime(long j) {
        this.mEventTime = j;
        return this;
    }

    public Tracker setEventType(EventTypeEnum eventTypeEnum) {
        this.mEventType = eventTypeEnum;
        return this;
    }

    public Tracker setTaskId(String str) {
        this.mTaskId = str;
        return this;
    }

    public Tracker setTrackerId(String str) {
        this.mTrackerId = str;
        return this;
    }

    public void submit() throws Exception {
        setRequestBody(buildParameter());
        jsonExecute();
    }

    public void submitAsync() {
        mAsyncHandler.post(new Implementable("submit") { // from class: com.hs.cld.da.t.Tracker.1
            @Override // com.hs.p.common.async.Implementable
            protected void implement() {
                try {
                    Tracker.this.setRequestBody(Tracker.this.buildParameter());
                    Tracker.this.jsonExecute();
                } catch (Exception e) {
                    LOG.w(Tracker.TAG, "tracker failed: " + e);
                }
            }
        });
    }
}
