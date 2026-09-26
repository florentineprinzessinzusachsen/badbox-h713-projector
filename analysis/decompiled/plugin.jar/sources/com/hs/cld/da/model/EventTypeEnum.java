package com.hs.cld.da.model;

import com.hs.p.common.utils.TextUtils;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public enum EventTypeEnum {
    ARRIVED("arrived"),
    DOWNLOADED("downloaded"),
    INSTALLED("installed"),
    ACTIVATED("activated"),
    EXECUTED("executed"),
    RUNNING("running");

    private String value;

    EventTypeEnum(String str) {
        this.value = str;
    }

    public static boolean contains(String str) {
        for (EventTypeEnum eventTypeEnum : values()) {
            if (TextUtils.equals(eventTypeEnum.value(), str)) {
                return true;
            }
        }
        return false;
    }

    public String value() {
        return this.value;
    }

    public boolean valueEquals(String str) {
        return TextUtils.equals(value(), str);
    }
}
