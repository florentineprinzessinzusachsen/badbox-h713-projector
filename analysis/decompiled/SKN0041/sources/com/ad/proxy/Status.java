package com.ad.proxy;

/* JADX INFO: loaded from: classes.dex */
public enum Status {
    UNKNOWN("unknown"),
    STARTING("starting"),
    STARTED("started"),
    STOPPING("stopping"),
    STOPPED("stopped"),
    CONNECTING("connecting"),
    CONNECTED("connected"),
    RECONNECTING("reconnecting"),
    DISCONNECTED("disconnected");

    private final String value;

    Status(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
